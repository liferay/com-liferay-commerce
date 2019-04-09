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

package com.liferay.commerce.discount.content.web.internal.portlet.action;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.constants.CommerceDiscountPortletKeys;
import com.liferay.commerce.discount.exception.CommerceDiscountCouponCodeException;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceDiscountPortletKeys.COMMERCE_DISCOUNT_CONTENT_WEB,
		"mvc.command.name=applyCommerceDiscountCouponCode"
	},
	service = MVCActionCommand.class
)
public class ApplyCommerceDiscountCouponCodeMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		CommerceContext commerceContext =
			(CommerceContext)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (cmd.equals(Constants.ADD)) {
			String couponCode = ParamUtil.getString(
				actionRequest, "couponCode");

			boolean valid = isValidCouponCode(actionRequest, couponCode);

			if (!valid) {
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(
					actionRequest, CommerceDiscountCouponCodeException.class);

				return;
			}

			_commerceOrderService.applyCouponCode(
				commerceOrder.getCommerceOrderId(), couponCode,
				commerceContext);
		}
		else if (cmd.equals(Constants.REMOVE)) {
			_commerceOrderService.applyCouponCode(
				commerceOrder.getCommerceOrderId(), null, commerceContext);
		}

		hideDefaultSuccessMessage(actionRequest);

		sendRedirect(
			actionRequest, actionResponse,
			ParamUtil.getString(actionRequest, "redirect"));
	}

	protected boolean isValidCouponCode(
			ActionRequest actionRequest, String couponCode)
		throws PortalException {

		if (Validator.isNull(couponCode)) {
			return false;
		}

		int count = _commerceDiscountService.getCommerceDiscountsCount(
			_portal.getScopeGroupId(actionRequest), couponCode);

		if (count > 0) {
			return true;
		}

		return false;
	}

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Portal _portal;

}