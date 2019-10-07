/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.order.web.internal.model.Shipment;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShipmentService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceShipmentClayTable.NAME,
		"commerce.table.name=" + CommerceShipmentClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceShipmentClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<Shipment> {

	public static final String NAME = "commerceShipments";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		Shipment shipment = (Shipment)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (PortalPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS)) {

			PortletURL deleteURL = _getShipmentDeleteURL(
				shipment.getShipmentId(), httpServletRequest);

			ClayTableAction deleteClayTableAction = new ClayTableAction(
				deleteURL.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), false, false);

			clayTableActions.add(deleteClayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		return _commerceShipmentService.getCommerceShipmentsCount(
			_portal.getCompanyId(httpServletRequest),
			commerceOrder.getShippingAddressId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField shipmentIdField = clayTableSchemaBuilder.addField(
			"shipmentId", "shipment-id");

		shipmentIdField.setContentRenderer("commerceTableCellSidePanelLink");

		clayTableSchemaBuilder.addField("address", "address");

		clayTableSchemaBuilder.addField("createDate", "create-date");

		ClayTableSchemaField statusField = clayTableSchemaBuilder.addField(
			"status", "status");

		statusField.setContentRenderer("commerceTableCellStatus");

		clayTableSchemaBuilder.addField("tracking", "tracking");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Shipment> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<Shipment> shipments = new ArrayList<>();

		RenderResponse renderResponse =
			(RenderResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		List<CommerceShipment> commerceShipments =
			_commerceShipmentService.getCommerceShipments(
				_portal.getCompanyId(httpServletRequest),
				commerceOrder.getShippingAddressId(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		for (CommerceShipment commerceShipment : commerceShipments) {
			shipments.add(
				new Shipment(
					commerceShipment.getCommerceShipmentId(),
					_getShipmentPanelURL(
						commerceShipment.getCommerceShipmentId(),
						httpServletRequest),
					renderResponse.getNamespace() + "sidePanel",
					_getDescriptiveAddress(commerceShipment),
					commerceShipment.getCreateDate(),
					LanguageUtil.get(
						httpServletRequest,
						CommerceShipmentConstants.getShipmentStatusLabel(
							commerceShipment.getStatus())),
					CommerceShipmentConstants.getShipmentLabelStyle(
						commerceShipment.getStatus()),
					commerceShipment.getTrackingNumber()));
		}

		return shipments;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	private String _getDescriptiveAddress(CommerceShipment commerceShipment)
		throws PortalException {

		CommerceAddress commerceAddress =
			commerceShipment.fetchCommerceAddress();

		if (commerceAddress == null) {
			return StringPool.BLANK;
		}

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		StringBundler sb = new StringBundler((commerceRegion == null) ? 5 : 7);

		sb.append(commerceAddress.getStreet1());
		sb.append(StringPool.SPACE);
		sb.append(commerceAddress.getCity());
		sb.append(StringPool.NEW_LINE);

		if (commerceRegion != null) {
			sb.append(commerceRegion.getCode());
			sb.append(StringPool.SPACE);
		}

		sb.append(commerceAddress.getZip());

		return sb.toString();
	}

	private PortletURL _getShipmentDeleteURL(
			long commerceShipmentId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommerceOrderShipment");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(httpServletRequest));
		portletURL.setParameter(
			"commerceShipmentId", String.valueOf(commerceShipmentId));

		return portletURL;
	}

	private String _getShipmentPanelURL(
			long commerceShipmentId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceOrderShipment");
		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(httpServletRequest));

		long commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		portletURL.setParameter(
			"commerceShipmentId", String.valueOf(commerceShipmentId));

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException wse) {
			_log.error(wse, wse);
		}

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentClayTable.class);

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceShipmentService _commerceShipmentService;

	@Reference
	private Portal _portal;

}