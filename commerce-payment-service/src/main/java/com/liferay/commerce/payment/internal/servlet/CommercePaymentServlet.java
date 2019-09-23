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

package com.liferay.commerce.payment.internal.servlet;

import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import java.math.BigDecimal;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.RequestDispatcher;
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
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.payment.internal.servlet.CommercePaymentServlet",
		"osgi.http.whiteboard.servlet.pattern=/" + CommercePaymentConstants.SERVLET_PATH + "/*"
	},
	service = Servlet.class
)
public class CommercePaymentServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			HttpSession httpSession = httpServletRequest.getSession();

			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(httpSession);
			}

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(
					_portal.getUser(httpServletRequest));

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			long groupId = ParamUtil.getLong(httpServletRequest, "groupId");
			String uuid = ParamUtil.getString(httpServletRequest, "uuid");

			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrderByUuidAndGroupId(
					uuid, groupId);

			_nextUrl = ParamUtil.getString(httpServletRequest, "nextStep");

			if (BigDecimal.ZERO.compareTo(commerceOrder.getTotal()) == 0) {
				httpServletResponse.sendRedirect(_nextUrl);

				return;
			}

			_commerceOrderId = commerceOrder.getCommerceOrderId();

			CommercePaymentResult commercePaymentResult = _startPayment(
				httpServletRequest);

			if (!commercePaymentResult.isSuccess()) {
				httpServletResponse.sendRedirect(_nextUrl);

				return;
			}

			if (commercePaymentResult.isOnlineRedirect()) {
				URL portalURL = new URL(
					_portal.getPortalURL(httpServletRequest));

				URL redirectUrl = new URL(
					commercePaymentResult.getRedirectUrl());

				if (Objects.equals(
						portalURL.getHost(), redirectUrl.getHost())) {

					Map<String, String> paramsMap = _getQueryMap(
						redirectUrl.getQuery());

					Set<Map.Entry<String, String>> entries =
						paramsMap.entrySet();

					for (Map.Entry<String, String> param : entries) {
						httpServletRequest.setAttribute(
							param.getKey(), param.getValue());
					}

					RequestDispatcher requestDispatcher =
						httpServletRequest.getRequestDispatcher(
							redirectUrl.getPath());

					requestDispatcher.forward(
						httpServletRequest, httpServletResponse);
				}
				else {
					httpServletResponse.sendRedirect(redirectUrl.toString());
				}
			}

			// Offline methods, payment complete

			int commercePaymentMethodType =
				_commercePaymentEngine.getCommercePaymentMethodType(
					_commerceOrderId);

			if ((CommercePaymentConstants.
					COMMERCE_PAYMENT_METHOD_TYPE_OFFLINE ==
						commercePaymentMethodType) ||
				(commercePaymentMethodType == -1)) {

				_commercePaymentEngine.completePayment(
					_commerceOrderId, null, httpServletRequest);

				httpServletResponse.sendRedirect(_nextUrl);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			// Payment Failed

			try {
				_commercePaymentEngine.updateOrderPaymentStatus(
					_commerceOrderId,
					CommerceOrderPaymentConstants.STATUS_FAILED,
					StringPool.BLANK);

				httpServletResponse.sendRedirect(_nextUrl);
			}
			catch (PortalException pe) {
				_log.error(pe, pe);
			}
		}
	}

	private Map<String, String> _getQueryMap(String query) {
		String[] params = query.split(StringPool.AMPERSAND);

		Map<String, String> map = new HashMap<>();

		for (String param : params) {
			String name = param.split(StringPool.EQUAL)[0];
			String value = param.split(StringPool.EQUAL)[1];

			map.put(name, value);
		}

		return map;
	}

	private CommercePaymentResult _startPayment(
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _commercePaymentEngine.startPayment(
			_commerceOrderId, _nextUrl, httpServletRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePaymentServlet.class);

	private long _commerceOrderId;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	private String _nextUrl;

	@Reference
	private Portal _portal;

}