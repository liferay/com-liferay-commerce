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

package com.liferay.commerce.shipping.origin.locator.warehouse.internal;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceGeocoder;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShippingOriginLocator;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.shipping.origin.locator.warehouse.internal.util.DistanceCalculator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	immediate = true,
	property = "commerce.shipping.origin.locator.key=" + WarehouseCommerceShippingOriginLocator.KEY,
	service = CommerceShippingOriginLocator.class
)
public class WarehouseCommerceShippingOriginLocator
	implements CommerceShippingOriginLocator {

	public static final String KEY = "warehouse";

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(
			resourceBundle, "use-closest-warehouse-description");
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "use-closest-warehouse");
	}

	@Override
	public Map<CommerceAddress, List<CommerceOrderItem>> getOriginAddresses(
			CommerceOrder commerceOrder)
		throws Exception {

		CommerceAddress commerceAddress = commerceOrder.getShippingAddress();

		if (commerceAddress == null) {
			return Collections.emptyMap();
		}

		Map<CommerceInventoryWarehouse, List<CommerceOrderItem>>
			commerceWarehouseOrderItemsMap = new HashMap<>();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPInstance cpInstance = commerceOrderItem.getCPInstance();

			CommerceInventoryWarehouse commerceWarehouse =
				_getClosestCommerceWarehouse(
					commerceAddress, cpInstance.getSku());

			List<CommerceOrderItem> commerceWarehouseOrderItems =
				commerceWarehouseOrderItemsMap.get(commerceWarehouse);

			if (commerceWarehouseOrderItems == null) {
				commerceWarehouseOrderItems = new ArrayList<>(
					commerceOrderItems.size());

				commerceWarehouseOrderItemsMap.put(
					commerceWarehouse, commerceWarehouseOrderItems);
			}

			commerceWarehouseOrderItems.add(commerceOrderItem);
		}

		Map<CommerceAddress, List<CommerceOrderItem>> originAddress =
			new HashMap<>();

		for (Map.Entry<CommerceInventoryWarehouse, List<CommerceOrderItem>>
				entry : commerceWarehouseOrderItemsMap.entrySet()) {

			CommerceInventoryWarehouse commerceWarehouse = entry.getKey();

			originAddress.put(
				_getCommerceAddress(commerceWarehouse), entry.getValue());
		}

		return originAddress;
	}

	@Override
	public void renderConfiguration(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {
	}

	@Override
	public void updateConfiguration(
			Map<String, String> parameterMap, ServiceContext serviceContext)
		throws Exception {
	}

	private CommerceInventoryWarehouse _getClosestCommerceWarehouse(
			CommerceAddress commerceAddress, String sku)
		throws PortalException {

		List<CommerceInventoryWarehouse> commerceWarehouses =
			_commerceWarehouseLocalService.getCommerceWarehousesByGroupIdAndSku(
				commerceAddress.getCompanyId(), commerceAddress.getGroupId(),
				sku);

		CommerceInventoryWarehouse closestCommerceWarehouse = null;
		double closestDistance = Double.MAX_VALUE;

		for (CommerceInventoryWarehouse commerceWarehouse :
				commerceWarehouses) {

			if (!commerceWarehouse.isGeolocated()) {
				CommerceCountry commerceCountry = _getCommerceCountry(
					commerceAddress.getGroupId(),
					commerceWarehouse.getCountryTwoLettersISOCode());

				CommerceRegion commerceRegion = _getCommerceRegion(
					commerceCountry.getCommerceCountryId(),
					commerceWarehouse.getCommerceRegionCode());

				double[] coordinates = _commerceGeocoder.getCoordinates(
					commerceWarehouse.getStreet1(), commerceWarehouse.getCity(),
					commerceWarehouse.getZip(), commerceRegion,
					commerceCountry);

				commerceWarehouse =
					_commerceWarehouseLocalService.geolocateCommerceWarehouse(
						commerceWarehouse.getCommerceInventoryWarehouseId(),
						coordinates[0], coordinates[1]);
			}

			double distance = _distanceCalculator.getDistance(
				commerceAddress.getLatitude(), commerceAddress.getLongitude(),
				commerceWarehouse.getLatitude(),
				commerceWarehouse.getLongitude());

			if (distance < closestDistance) {
				closestCommerceWarehouse = commerceWarehouse;
				closestDistance = distance;
			}
		}

		return closestCommerceWarehouse;
	}

	private CommerceAddress _getCommerceAddress(
			CommerceInventoryWarehouse commerceWarehouse)
		throws PortalException {

		CommerceAddress commerceAddress =
			_commerceAddressLocalService.createCommerceAddress(
				-commerceWarehouse.getCommerceInventoryWarehouseId());

		commerceAddress.setStreet1(commerceWarehouse.getStreet1());
		commerceAddress.setStreet2(commerceWarehouse.getStreet2());
		commerceAddress.setStreet3(commerceWarehouse.getStreet3());
		commerceAddress.setCity(commerceWarehouse.getCity());
		commerceAddress.setZip(commerceWarehouse.getZip());

		CommerceCountry commerceCountry = _getCommerceCountry(
			commerceAddress.getGroupId(),
			commerceWarehouse.getCountryTwoLettersISOCode());

		CommerceRegion commerceRegion = _getCommerceRegion(
			commerceCountry.getCommerceCountryId(),
			commerceWarehouse.getCommerceRegionCode());

		commerceAddress.setCommerceRegionId(
			commerceRegion.getCommerceRegionId());

		commerceAddress.setCommerceCountryId(
			commerceCountry.getCommerceCountryId());

		commerceAddress.setLatitude(commerceWarehouse.getLatitude());
		commerceAddress.setLongitude(commerceWarehouse.getLongitude());

		return commerceAddress;
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

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	@Reference
	private CommerceAddressLocalService _commerceAddressLocalService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceGeocoder _commerceGeocoder;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private CommerceInventoryWarehouseLocalService
		_commerceWarehouseLocalService;

	private final DistanceCalculator _distanceCalculator =
		new DistanceCalculator();

}