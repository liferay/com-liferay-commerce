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

package com.liferay.commerce.warehouse.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.exception.CommerceGeocoderException;
import com.liferay.commerce.exception.NoSuchWarehouseException;
import com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseActiveException;
import com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseNameException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceGeocoder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.warehouse.web.internal.admin.WarehousesCommerceAdminModule;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN_VIRTUAL_INSTANCE,
		"mvc.command.name=editCommerceWarehouse"
	},
	service = MVCActionCommand.class
)
public class EditCommerceWarehouseMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceWarehouses(ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCommerceWarehouseIds = null;

		long commerceWarehouseId = ParamUtil.getLong(
			actionRequest, "commerceWarehouseId");

		if (commerceWarehouseId > 0) {
			deleteCommerceWarehouseIds = new long[] {commerceWarehouseId};
		}
		else {
			deleteCommerceWarehouseIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceWarehouseIds"),
				0L);
		}

		for (long deleteCommerceWarehouseId : deleteCommerceWarehouseIds) {
			_commerceWarehouseService.deleteCommerceWarehouse(
				deleteCommerceWarehouseId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceWarehouses(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				updateCommerceWarehouse(actionRequest);
			}
			else if (cmd.equals("geolocate")) {
				geolocateCommerceWarehouse(actionRequest);
			}
			else if (cmd.equals("setActive")) {
				setActive(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof CommerceGeocoderException) {
				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass(), e.getMessage());

				actionResponse.setRenderParameter(
					"commerceAdminModuleKey",
					WarehousesCommerceAdminModule.KEY);
			}
			else if (e instanceof NoSuchWarehouseException ||
					 e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof CommerceInventoryWarehouseActiveException ||
					 e instanceof CommerceInventoryWarehouseNameException) {

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "editCommerceWarehouse");
			}
			else {
				throw e;
			}
		}
	}

	protected void geolocateCommerceWarehouse(ActionRequest actionRequest)
		throws PortalException {

		long commerceWarehouseId = ParamUtil.getLong(
			actionRequest, "commerceWarehouseId");

		CommerceInventoryWarehouse commerceWarehouse =
			_commerceWarehouseService.getCommerceWarehouse(commerceWarehouseId);

		CommerceCountry commerceCountry = _getCommerceCountry(
			_portal.getScopeGroupId(actionRequest),
			commerceWarehouse.getCountryTwoLettersISOCode());

		CommerceRegion commerceRegion = _getCommerceRegion(
			commerceCountry.getCommerceCountryId(),
			commerceWarehouse.getCommerceRegionCode());

		double[] coordinates = _commerceGeocoder.getCoordinates(
			commerceWarehouse.getStreet1(), commerceWarehouse.getCity(),
			commerceWarehouse.getZip(), commerceRegion, commerceCountry);

		_commerceWarehouseService.geolocateCommerceWarehouse(
			commerceWarehouseId, coordinates[0], coordinates[1]);
	}

	protected void setActive(ActionRequest actionRequest) throws Exception {
		long commerceWarehouseId = ParamUtil.getLong(
			actionRequest, "commerceWarehouseId");

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		_commerceWarehouseService.setActive(commerceWarehouseId, active);
	}

	protected CommerceInventoryWarehouse updateCommerceWarehouse(
			ActionRequest actionRequest)
		throws PortalException {

		long commerceWarehouseId = ParamUtil.getLong(
			actionRequest, "commerceWarehouseId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		String commerceRegionCode = ParamUtil.getString(
			actionRequest, "commerceRegionCode");
		String commerceCountryCode = ParamUtil.getString(
			actionRequest, "countryTwoLettersISOCode");
		double latitude = ParamUtil.getDouble(actionRequest, "latitude");
		double longitude = ParamUtil.getDouble(actionRequest, "longitude");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceInventoryWarehouse.class.getName(), actionRequest);

		CommerceInventoryWarehouse commerceWarehouse = null;

		if (commerceWarehouseId <= 0) {
			commerceWarehouse =
				_commerceWarehouseService.addCommerceWarehouseAndGroupRel(
					name, description, active, street1, street2, street3, city,
					zip, commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);
		}
		else {
			commerceWarehouse =
				_commerceWarehouseService.updateCommerceWarehouse(
					commerceWarehouseId, name, description, active, street1,
					street2, street3, city, zip, commerceRegionCode,
					commerceCountryCode, latitude, longitude, serviceContext);
		}

		return commerceWarehouse;
	}

	private CommerceCountry _getCommerceCountry(
			long groupId, String countryCode)
		throws PortalException {

		return _commerceCountryLocalService.getCommerceCountry(
			groupId, countryCode);
	}

	private CommerceRegion _getCommerceRegion(
			long commerceCountryId, String regionCode)
		throws PortalException {

		return _commerceRegionLocalService.getCommerceRegion(
			commerceCountryId, regionCode);
	}

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceGeocoder _commerceGeocoder;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private CommerceInventoryWarehouseService _commerceWarehouseService;

	@Reference
	private Portal _portal;

}