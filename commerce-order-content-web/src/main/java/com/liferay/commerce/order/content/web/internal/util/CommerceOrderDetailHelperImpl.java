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

package com.liferay.commerce.order.content.web.internal.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.order.CommerceOrderHelper;
import com.liferay.commerce.order.content.render.CommerceOrderDetailRenderer;
import com.liferay.commerce.order.content.render.CommerceOrderDetailRendererRegistry;
import com.liferay.commerce.order.content.util.CommerceOrderDetailHelper;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceOrderDetailHelper.class)
public class CommerceOrderDetailHelperImpl
	implements CommerceOrderDetailHelper {

	@Override
	public List<CommerceAddress> getCommerceAddresses(
			long commerceAccountId, HttpServletRequest httpServletRequest)
		throws PortalException {

		return _commerceAddressService.getCommerceAddresses(
			_portal.getScopeGroupId(httpServletRequest),
			CommerceAccount.class.getName(), commerceAccountId);
	}

	@Override
	public CommerceOrder getCommerceOrder(
		HttpServletRequest httpServletRequest) {

		return (CommerceOrder)httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_ORDER);
	}

	@Override
	public String getCommerceOrderDate(
		CommerceOrder commerceOrder, HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format commerceOrderDateFormatDate = FastDateFormatFactoryUtil.getDate(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		return commerceOrderDateFormatDate.format(
			commerceOrder.getCreateDate());
	}

	@Override
	public String getCommerceOrderDetailRendererKey(
		String orderDetailStatus, RenderRequest renderRequest) {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String value = portletPreferences.getValue(
			orderDetailStatus + "CommerceOrderDetailRendererKey", null);

		if (Validator.isNotNull(value)) {
			return value;
		}

		List<CommerceOrderDetailRenderer> commerceOrderDetailRenderers =
			getCommerceOrderDetailRenderers(orderDetailStatus);

		if (commerceOrderDetailRenderers.isEmpty()) {
			return StringPool.BLANK;
		}

		CommerceOrderDetailRenderer commerceOrderDetailRenderer =
			commerceOrderDetailRenderers.get(0);

		if (commerceOrderDetailRenderer == null) {
			return StringPool.BLANK;
		}

		return commerceOrderDetailRenderer.getKey();
	}

	@Override
	public List<CommerceOrderDetailRenderer> getCommerceOrderDetailRenderers(
		String orderDetailStatus) {

		return _commerceOrderDetailRendererRegistry.
			getCommerceOrderDetailRenderers(orderDetailStatus);
	}

	@Override
	public CommerceOrderNote getCommerceOrderNote(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		long commerceOrderNoteId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderNoteId");

		if (commerceOrderNoteId > 0) {
			return _commerceOrderNoteService.getCommerceOrderNote(
				commerceOrderNoteId);
		}

		return null;
	}

	@Override
	public List<CommerceOrderNote> getCommerceOrderNotes(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		if (hasPermission(
				commerceOrderId,
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_RESTRICTED_NOTES,
				httpServletRequest)) {

			return _commerceOrderNoteService.getCommerceOrderNotes(
				commerceOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return _commerceOrderNoteService.getCommerceOrderNotes(
			commerceOrderId, false);
	}

	@Override
	public int getCommerceOrderNotesCount(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		if (hasPermission(
				commerceOrderId, ActionKeys.UPDATE_DISCUSSION,
				httpServletRequest)) {

			return _commerceOrderNoteService.getCommerceOrderNotesCount(
				commerceOrderId);
		}

		return _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrderId, false);
	}

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceContext commerceContext =
			(CommerceContext)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		return _commerceOrderPriceCalculation.getCommerceOrderPrice(
			getCommerceOrder(httpServletRequest), commerceContext);
	}

	@Override
	public String getCommerceOrderTime(
		CommerceOrder commerceOrder, HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format commerceOrderDateFormatTime = FastDateFormatFactoryUtil.getTime(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		return commerceOrderDateFormatTime.format(
			commerceOrder.getCreateDate());
	}

	@Override
	public List<CommerceShipmentItem> getCommerceShipmentItems(
			long commerceOrderItemId)
		throws PortalException {

		return _commerceShipmentItemService.getCommerceShipmentItems(
			commerceOrderItemId);
	}

	@Override
	public String getCommerceShipmentStatusLabel(
		int status, HttpServletRequest httpServletRequest) {

		return LanguageUtil.get(
			httpServletRequest,
			CommerceShipmentConstants.getShipmentStatusLabel(status));
	}

	@Override
	public String getFormattedPercentage(
			BigDecimal percentage, HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder = getCommerceOrder(httpServletRequest);

		if (commerceOrder == null) {
			return StringPool.BLANK;
		}

		DecimalFormat decimalFormat = new DecimalFormat();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		decimalFormat.setMaximumFractionDigits(
			commerceCurrency.getMaxFractionDigits());
		decimalFormat.setMinimumFractionDigits(
			commerceCurrency.getMinFractionDigits());

		decimalFormat.setNegativeSuffix(StringPool.PERCENT);
		decimalFormat.setPositiveSuffix(StringPool.PERCENT);

		return decimalFormat.format(percentage);
	}

	@Override
	public String getOrderDetailURL(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceOrderHelper.getFriendlyURL(
			commerceOrderId, themeDisplay);
	}

	@Override
	public boolean hasPermission(
			long commerceOrderId, String actionId,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _modelResourcePermission.contains(
			themeDisplay.getPermissionChecker(), commerceOrderId, actionId);
	}

	@Override
	public void renderCommerceOrderDetail(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CommerceOrder commerceOrder = getCommerceOrder(httpServletRequest);

		if (commerceOrder == null) {
			return;
		}

		String orderDetailStatus = "placed";

		if (commerceOrder.isOpen()) {
			orderDetailStatus = "pending";
		}

		RenderRequest renderRequest =
			(RenderRequest)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		CommerceOrderDetailRenderer commerceOrderDetailRenderer =
			_commerceOrderDetailRendererRegistry.getCommerceOrderDetailRenderer(
				getCommerceOrderDetailRendererKey(
					orderDetailStatus, renderRequest));

		if (commerceOrderDetailRenderer != null) {
			commerceOrderDetailRenderer.render(
				commerceOrder, httpServletRequest, httpServletResponse);
		}
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceOrderDetailRendererRegistry
		_commerceOrderDetailRendererRegistry;

	@Reference
	private CommerceOrderHelper _commerceOrderHelper;

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceShipmentItemService _commerceShipmentItemService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	private ModelResourcePermission<CommerceOrder> _modelResourcePermission;

	@Reference
	private Portal _portal;

}