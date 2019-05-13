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

package com.liferay.commerce.shipping.origin.locator.address.internal.display.context;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.shipping.origin.locator.address.internal.configuration.AddressCommerceShippingOriginLocatorGroupServiceConfiguration;
import com.liferay.commerce.shipping.origin.locator.address.internal.constants.AddressCommerceShippingOriginLocatorConstants;
import com.liferay.commerce.util.SuffixParameterMapSettingsLocator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class AddressCommerceShippingOriginLocatorDisplayContext {

	public AddressCommerceShippingOriginLocatorDisplayContext(
			String commerceShippingOriginLocatorKey,
			CommerceCountryLocalService commerceCountryLocalService,
			CommerceInventoryWarehouseService commerceWarehouseService,
			CommerceRegionLocalService commerceRegionLocalService,
			ConfigurationProvider configurationProvider,
			RenderRequest renderRequest)
		throws PortalException {

		_commerceCountryLocalService = commerceCountryLocalService;
		_commerceWarehouseService = commerceWarehouseService;
		_commerceRegionLocalService = commerceRegionLocalService;
		_renderRequest = renderRequest;

		_themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_addressCommerceShippingOriginLocatorGroupServiceConfiguration =
			configurationProvider.getConfiguration(
				AddressCommerceShippingOriginLocatorGroupServiceConfiguration.
					class,
				new SuffixParameterMapSettingsLocator(
					renderRequest.getParameterMap(),
					commerceShippingOriginLocatorKey + "Origin--",
					StringPool.DOUBLE_DASH,
					new GroupServiceSettingsLocator(
						_themeDisplay.getScopeGroupId(),
						AddressCommerceShippingOriginLocatorConstants.
							SERVICE_NAME)));
	}

	public String getCity() throws PortalException {
		String city =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				city();

		if (Validator.isNull(city)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				city = commerceWarehouse.getCity();
			}
		}

		return city;
	}

	public long getCommerceCountryId() throws PortalException {
		long commerceCountryId =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				commerceCountryId();

		if (commerceCountryId <= 0) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if ((commerceWarehouse != null) &&
				Validator.isNotNull(
					commerceWarehouse.getCountryTwoLettersISOCode())) {

				CommerceCountry commerceCountry =
					_commerceCountryLocalService.getCommerceCountry(
						_themeDisplay.getScopeGroupId(),
						commerceWarehouse.getCountryTwoLettersISOCode());

				commerceCountryId = commerceCountry.getCommerceCountryId();
			}
		}

		return commerceCountryId;
	}

	public long getCommerceRegionId() throws PortalException {
		long commerceRegionId =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				commerceRegionId();

		if (commerceRegionId <= 0) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if ((commerceWarehouse != null) &&
				Validator.isNotNull(
					commerceWarehouse.getCommerceRegionCode())) {

				CommerceRegion commerceRegion =
					_commerceRegionLocalService.getCommerceRegion(
						getCommerceCountryId(),
						commerceWarehouse.getCommerceRegionCode());

				commerceRegionId = commerceRegion.getCommerceRegionId();
			}
		}

		return commerceRegionId;
	}

	public double getLatitude() throws PortalException {
		CommerceInventoryWarehouse commerceWarehouse =
			getDefaultCommerceWarehouse();

		if (commerceWarehouse != null) {
			return commerceWarehouse.getLatitude();
		}

		return _addressCommerceShippingOriginLocatorGroupServiceConfiguration.
			latitude();
	}

	public double getLongitude() throws PortalException {
		CommerceInventoryWarehouse commerceWarehouse =
			getDefaultCommerceWarehouse();

		if (commerceWarehouse != null) {
			return commerceWarehouse.getLongitude();
		}

		return _addressCommerceShippingOriginLocatorGroupServiceConfiguration.
			longitude();
	}

	public String getName() throws PortalException {
		String name =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				name();

		if (Validator.isNull(name)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				name = commerceWarehouse.getName();
			}
		}

		return name;
	}

	public String getStreet1() throws PortalException {
		String street1 =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				street1();

		if (Validator.isNull(street1)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				street1 = commerceWarehouse.getStreet1();
			}
		}

		return street1;
	}

	public String getStreet2() throws PortalException {
		String street2 =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				street2();

		if (Validator.isNull(street2)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				street2 = commerceWarehouse.getStreet2();
			}
		}

		return street2;
	}

	public String getStreet3() throws PortalException {
		String street3 =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				street3();

		if (Validator.isNull(street3)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				street3 = commerceWarehouse.getStreet3();
			}
		}

		return street3;
	}

	public String getZip() throws PortalException {
		String zip =
			_addressCommerceShippingOriginLocatorGroupServiceConfiguration.
				zip();

		if (Validator.isNull(zip)) {
			CommerceInventoryWarehouse commerceWarehouse =
				getDefaultCommerceWarehouse();

			if (commerceWarehouse != null) {
				zip = commerceWarehouse.getZip();
			}
		}

		return zip;
	}

	protected CommerceInventoryWarehouse getDefaultCommerceWarehouse()
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _commerceWarehouseService.fetchDefaultCommerceWarehouse(
			themeDisplay.getScopeGroupId());
	}

	private final AddressCommerceShippingOriginLocatorGroupServiceConfiguration
		_addressCommerceShippingOriginLocatorGroupServiceConfiguration;
	private final CommerceCountryLocalService _commerceCountryLocalService;
	private final CommerceRegionLocalService _commerceRegionLocalService;
	private final CommerceInventoryWarehouseService _commerceWarehouseService;
	private final RenderRequest _renderRequest;
	private final ThemeDisplay _themeDisplay;

}