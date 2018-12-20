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

package com.liferay.commerce.payment.servlet;

import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.result.CommercePaymentResult;
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
		"osgi.http.whiteboard.context.path=/" + CommercePaymentConstants.SERVLET_PATH,
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.payment.servlet.CommercePaymentServlet",
		"osgi.http.whiteboard.servlet.pattern=/" + CommercePaymentConstants.SERVLET_PATH + "/*"
	},
	service = Servlet.class
)
public class CommercePaymentServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			HttpSession session = request.getSession();

			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(session);
			}

			User user = _portal.getUser(request);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			_commerceOrderId = ParamUtil.getLong(request, "commerceOrderId");

			_confirmationStepUrl = ParamUtil.getString(
				request, "confirmationStep");

			_startPayment(request);

			if (_onlineRedirect) {
				response.sendRedirect(_url);
			}

			// Offline methods, payment complete

			_commercePaymentEngine.completePayment(
				_commerceOrderId, null, request);

			response.sendRedirect(_confirmationStepUrl);
		}
		catch (Exception e) {
			_portal.sendError(e, request, response);
		}
	}

	private void _startPayment(HttpServletRequest request) throws Exception {
		CommercePaymentResult commercePaymentResult =
			_commercePaymentEngine.startPayment(
				_commerceOrderId, _confirmationStepUrl, request);

		_onlineRedirect = commercePaymentResult.isOnlineRedirect();
		_url = commercePaymentResult.getRedirectUrl();
	}

	private long _commerceOrderId;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	private String _confirmationStepUrl;
	private boolean _onlineRedirect;

	@Reference
	private Portal _portal;

	private String _url;

}