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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.checkout.web.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Objects;

import javax.portlet.ActionRequest;

/**
 * @author Luca Pellizzon
 */
public class AddressCommerceCheckoutStepUtil {

	public AddressCommerceCheckoutStepUtil(
		CommerceOrderService commerceOrderService,
		CommerceAddressService commerceAddressService,
		ModelResourcePermission<CommerceOrder>
			commerceOrderModelResourcePermission) {

		_commerceOrderService = commerceOrderService;
		_commerceAddressService = commerceAddressService;
		_commerceOrderModelResourcePermission =
			commerceOrderModelResourcePermission;
	}

	protected CommerceAddress addCommerceAddress(
			CommerceOrder commerceOrder, ActionRequest actionRequest)
		throws PortalException {

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long commerceRegionId = ParamUtil.getLong(
			actionRequest, "commerceRegionId");
		long commerceCountryId = ParamUtil.getLong(
			actionRequest, "commerceCountryId");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceAddress.class.getName(), actionRequest);

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());

		return _commerceAddressService.addCommerceAddress(
			CommerceAccount.class.getName(),
			commerceOrder.getCommerceAccountId(), name, description, street1,
			street2, street3, city, zip, commerceRegionId, commerceCountryId,
			phoneNumber, false, false, serviceContext);
	}

	protected CommerceOrder updateCommerceOrderAddress(
			ActionRequest actionRequest, String paramName)
		throws Exception {

		CommerceContext commerceContext =
			(CommerceContext)actionRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long commerceOrderId = ParamUtil.getLong(
			actionRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		boolean newAddress = ParamUtil.getBoolean(actionRequest, "newAddress");

		long commerceAddressId = ParamUtil.getLong(actionRequest, paramName);

		if (newAddress) {
			CommerceAddress commerceAddress = addCommerceAddress(
				commerceOrder, actionRequest);

			commerceAddressId = commerceAddress.getCommerceAddressId();
		}

		_commerceOrderModelResourcePermission.check(
			themeDisplay.getPermissionChecker(), commerceOrder,
			CommerceOrderActionKeys.CHECKOUT_COMMERCE_ORDER);

		boolean useAsBilling = ParamUtil.getBoolean(
			actionRequest, "use-as-billing");

		if (Objects.equals(
				CommerceCheckoutWebKeys.SHIPPING_ADDRESS_PARAM_NAME,
				paramName) &&
			useAsBilling) {

			return updateCommerceOrderAddress(
				commerceOrder, commerceAddressId, commerceAddressId,
				commerceContext);
		}

		if (Objects.equals(
				CommerceCheckoutWebKeys.SHIPPING_ADDRESS_PARAM_NAME,
				paramName) &&
			!useAsBilling) {

			return updateCommerceOrderAddress(
				commerceOrder, 0, commerceAddressId, commerceContext);
		}

		CommerceAddress billingAddress = commerceOrder.getBillingAddress();
		long shippingAddressId = commerceOrder.getShippingAddressId();

		if (Objects.equals(
				CommerceCheckoutWebKeys.BILLING_ADDRESS_PARAM_NAME,
				paramName) &&
			((billingAddress == null) ||
			 Objects.equals(
				 billingAddress.getCommerceAddressId(), shippingAddressId))) {

			return updateCommerceOrderAddress(
				commerceOrder, commerceAddressId,
				commerceOrder.getShippingAddressId(), commerceContext);
		}

		return null;
	}

	protected CommerceOrder updateCommerceOrderAddress(
			CommerceOrder commerceOrder, long billingAddressId,
			long shippingAddressId, CommerceContext commerceContext)
		throws Exception {

		return _commerceOrderService.updateCommerceOrder(
			commerceOrder.getCommerceOrderId(), billingAddressId,
			shippingAddressId, commerceOrder.getCommercePaymentMethodKey(),
			commerceOrder.getCommerceShippingMethodId(),
			commerceOrder.getShippingOptionName(),
			commerceOrder.getPurchaseOrderNumber(), commerceOrder.getSubtotal(),
			commerceOrder.getShippingAmount(), commerceOrder.getTotal(),
			commerceOrder.getAdvanceStatus(), commerceContext);
	}

	private final CommerceAddressService _commerceAddressService;
	private final ModelResourcePermission<CommerceOrder>
		_commerceOrderModelResourcePermission;
	private final CommerceOrderService _commerceOrderService;

}