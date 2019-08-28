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

package com.liferay.headless.commerce.admin.order.internal.util.v1_0;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class ShippingAddressUtil {

	public static CommerceOrder updateShippingAddress(
			CommerceAddressService commerceAddressService,
			CommerceOrderService commerceOrderService,
			CommerceRegionLocalService commerceRegionLocalService,
			CommerceOrder commerceOrder, ShippingAddress shippingAddress,
			ServiceContext serviceContext)
		throws Exception {

		CommerceCountry commerceCountry = null;

		CommerceAddress commerceAddress =
			commerceAddressService.fetchCommerceAddress(
				commerceOrder.getShippingAddressId());

		if (commerceAddress != null) {
			commerceCountry = commerceAddress.getCommerceCountry();
		}

		return commerceOrderService.updateShippingAddress(
			commerceOrder.getCommerceOrderId(), shippingAddress.getName(),
			GetterUtil.getString(
				shippingAddress.getDescription(),
				_getDescription(commerceAddress)),
			shippingAddress.getStreet1(),
			GetterUtil.getString(
				shippingAddress.getStreet2(), _getStreet2(commerceAddress)),
			GetterUtil.getString(
				shippingAddress.getStreet3(), _getStreet3(commerceAddress)),
			shippingAddress.getCity(),
			GetterUtil.getString(
				shippingAddress.getZip(), _getZip(commerceAddress)),
			_getCommerceRegionId(
				commerceAddress, commerceCountry, shippingAddress,
				commerceRegionLocalService),
			_getCommerceCountryId(commerceCountry),
			GetterUtil.getString(
				shippingAddress.getPhoneNumber(),
				_getPhoneNumber(commerceAddress)),
			serviceContext);
	}

	private static long _getCommerceCountryId(CommerceCountry commerceCountry) {
		if (commerceCountry == null) {
			return 0;
		}

		return commerceCountry.getCommerceCountryId();
	}

	private static long _getCommerceRegionId(
			CommerceAddress commerceAddress, CommerceCountry commerceCountry,
			ShippingAddress shippingAddress,
			CommerceRegionLocalService commerceRegionLocalService)
		throws PortalException {

		if (Validator.isNull(shippingAddress.getRegionISOCode()) &&
			(commerceAddress != null)) {

			return commerceAddress.getCommerceRegionId();
		}

		if (commerceCountry == null) {
			return 0;
		}

		CommerceRegion commerceRegion =
			commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				shippingAddress.getRegionISOCode());

		return commerceRegion.getCommerceRegionId();
	}

	private static String _getDescription(CommerceAddress commerceAddress) {
		if (commerceAddress == null) {
			return null;
		}

		return commerceAddress.getDescription();
	}

	private static String _getPhoneNumber(CommerceAddress commerceAddress) {
		if (commerceAddress == null) {
			return null;
		}

		return commerceAddress.getPhoneNumber();
	}

	private static String _getStreet2(CommerceAddress commerceAddress) {
		if (commerceAddress == null) {
			return null;
		}

		return commerceAddress.getStreet2();
	}

	private static String _getStreet3(CommerceAddress commerceAddress) {
		if (commerceAddress == null) {
			return null;
		}

		return commerceAddress.getStreet3();
	}

	private static String _getZip(CommerceAddress commerceAddress) {
		if (commerceAddress == null) {
			return null;
		}

		return commerceAddress.getZip();
	}

}