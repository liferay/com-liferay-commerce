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

package com.liferay.commerce.payment.method.paypal.internal.servlet;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.paypal.internal.constants.PayPalCommercePaymentMethodConstants;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/" + PayPalCommercePaymentMethodConstants.SERVLET_PATH,
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.payment.method.paypal.internal.servlet.CommercePaymentMethodPayPalServlet",
		"osgi.http.whiteboard.servlet.pattern=/" + PayPalCommercePaymentMethodConstants.SERVLET_PATH + "/*"
	},
	service = Servlet.class
)
public class CommercePaymentMethodPayPalServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			HttpSession httpSession = request.getSession();

			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(httpSession);
			}

			User user = _portal.getUser(request);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			boolean cancel = ParamUtil.getBoolean(request, "cancel");
			String redirect = ParamUtil.getString(request, "redirect");
			String token = ParamUtil.getString(request, "token");
			String paymentId = ParamUtil.getString(request, "paymentId");

			long groupId = ParamUtil.getLong(request, "groupId");
			String uuid = ParamUtil.getString(request, "uuid");

			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrderByUuidAndGroupId(
					uuid, groupId);

			if (cancel) {
				_commercePaymentEngine.cancelPayment(
					commerceOrder.getCommerceOrderId(), paymentId, request);
			}

			if (paymentId.isEmpty() && !token.isEmpty()) {
				_commercePaymentEngine.completeRecurringPayment(
					commerceOrder.getCommerceOrderId(), token, request);
			}
			else {
				_commercePaymentEngine.completePayment(
					commerceOrder.getCommerceOrderId(), paymentId, request);
			}

			response.sendRedirect(redirect);
		}
		catch (Exception e) {
			_portal.sendError(e, request, response);
		}
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	@Reference
	private Portal _portal;

}