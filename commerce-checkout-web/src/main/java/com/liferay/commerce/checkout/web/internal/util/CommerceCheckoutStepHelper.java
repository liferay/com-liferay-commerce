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

package com.liferay.commerce.checkout.web.internal.util;

import com.liferay.commerce.checkout.web.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.organization.util.CommerceOrganizationHelper;
import com.liferay.commerce.service.CommercePaymentMethodLocalService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceCheckoutStepHelper.class)
public class CommerceCheckoutStepHelper {

	public boolean isActiveBillingAddressCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		Organization organization =
			_commerceOrganizationHelper.getCurrentOrganization(
				httpServletRequest);

		if (organization != null) {
			return false;
		}

		return true;
	}

	public boolean isActivePaymentMethodCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (_commercePaymentMethodLocalService.getCommercePaymentMethodsCount(
				commerceOrder.getSiteGroupId(), true) > 0) {

			return true;
		}

		return false;
	}

	public boolean isActiveShippingMethodCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (!_commerceShippingHelper.isShippable(commerceOrder) ||
			_commerceShippingHelper.isFreeShipping(commerceOrder)) {

			return false;
		}

		if (_commerceShippingMethodLocalService.getCommerceShippingMethodsCount(
				commerceOrder.getSiteGroupId(), true) > 0) {

			return true;
		}

		return false;
	}

	@Reference
	private CommerceOrganizationHelper _commerceOrganizationHelper;

	@Reference
	private CommercePaymentMethodLocalService
		_commercePaymentMethodLocalService;

	@Reference
	private CommerceShippingHelper _commerceShippingHelper;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

}