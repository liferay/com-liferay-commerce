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
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class BillingAddressUtil {

	public static CommerceOrder updateBillingAddress(
			CommerceAddressService commerceAddressService,
			CommerceOrderService commerceOrderService,
			CommerceRegionLocalService commerceRegionLocalService,
			CommerceOrder commerceOrder, BillingAddress billingAddress,
			ServiceContext serviceContext)
		throws Exception {

		CommerceCountry commerceCountry = null;

		CommerceAddress commerceAddress =
			commerceAddressService.fetchCommerceAddress(
				commerceOrder.getShippingAddressId());

		if (commerceAddress != null) {
			commerceCountry = commerceAddress.getCommerceCountry();
		}

		return commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), billingAddress.getName(),
			GetterUtil.getString(
				billingAddress.getDescription(),
				_getDescription(commerceAddress)),
			billingAddress.getStreet1(),
			GetterUtil.getString(
				billingAddress.getStreet2(), _getStreet2(commerceAddress)),
			GetterUtil.getString(
				billingAddress.getStreet3(), _getStreet3(commerceAddress)),
			billingAddress.getCity(),
			GetterUtil.getString(
				billingAddress.getZip(), _getZip(commerceAddress)),
			_getCommerceRegionId(
				commerceAddress, commerceCountry, billingAddress,
				commerceRegionLocalService),
			_getCommerceCountryId(commerceCountry),
			GetterUtil.getString(
				billingAddress.getPhoneNumber(),
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
			BillingAddress billingAddress,
			CommerceRegionLocalService commerceRegionLocalService)
		throws PortalException {

		if (Validator.isNull(billingAddress.getRegionISOCode()) &&
			(commerceAddress != null)) {

			return commerceAddress.getCommerceRegionId();
		}

		if (commerceCountry == null) {
			return 0;
		}

		CommerceRegion commerceRegion =
			commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				billingAddress.getRegionISOCode());

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