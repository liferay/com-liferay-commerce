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

package com.liferay.commerce.payment.method.mercanet.internal.servlet;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.mercanet.internal.configuration.MercanetGroupServiceConfiguration;
import com.liferay.commerce.payment.method.mercanet.internal.connector.Environment;
import com.liferay.commerce.payment.method.mercanet.internal.connector.PaypageClient;
import com.liferay.commerce.payment.method.mercanet.internal.constants.MercanetCommercePaymentMethodConstants;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import com.worldline.sips.model.PaypageResponse;
import com.worldline.sips.model.ResponseCode;
import com.worldline.sips.model.ResponseData;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
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
		"osgi.http.whiteboard.context.path=/" + MercanetCommercePaymentMethodConstants.SERVLET_PATH,
		"osgi.http.whiteboard.servlet.name=com.liferay.commerce.payment.method.mercanet.internal.servlet.MercanetServlet",
		"osgi.http.whiteboard.servlet.pattern=/" + MercanetCommercePaymentMethodConstants.SERVLET_PATH + "/*"
	},
	service = Servlet.class
)
public class MercanetServlet extends HttpServlet {

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

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(
					"/mercanet_form/mercanet-form.jsp");

			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	protected void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			String type = ParamUtil.getString(httpServletRequest, "type");

			String data = ParamUtil.getString(httpServletRequest, "Data");

			Map<String, String> parameterMap = _getResponseParameters(data);

			if (Objects.equals("normal", type)) {
				HttpSession httpSession = httpServletRequest.getSession();

				if (PortalSessionThreadLocal.getHttpSession() == null) {
					PortalSessionThreadLocal.setHttpSession(httpSession);
				}

				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(
						_portal.getUser(httpServletRequest));

				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				String redirect = ParamUtil.getString(
					httpServletRequest, "redirect");

				if (!Objects.equals(parameterMap.get("responseCode"), "00")) {
					String orderId = parameterMap.get("orderId");

					long commerceOrderId = GetterUtil.getLong(orderId);

					String transactionReference = parameterMap.get(
						"transactionReference");

					_commercePaymentEngine.cancelPayment(
						commerceOrderId, transactionReference,
						httpServletRequest);
				}

				httpServletResponse.sendRedirect(redirect);
			}

			if (Objects.equals("automatic", type)) {
				long groupId = ParamUtil.getLong(httpServletRequest, "groupId");
				String uuid = ParamUtil.getString(httpServletRequest, "uuid");

				CommerceOrder commerceOrder =
					_commerceOrderLocalService.getCommerceOrderByUuidAndGroupId(
						uuid, groupId);

				CommerceChannel commerceChannel =
					_commerceChannelLocalService.
						getCommerceChannelByOrderGroupId(
							commerceOrder.getGroupId());

				MercanetGroupServiceConfiguration
					mercanetGroupServiceConfiguration = _getConfiguration(
						commerceChannel.getSiteGroupId());

				String environment = StringUtil.toUpperCase(
					mercanetGroupServiceConfiguration.environment());

				String keyVersion =
					mercanetGroupServiceConfiguration.keyVersion();

				PaypageClient paypageClient = new PaypageClient(
					Environment.valueOf(environment),
					mercanetGroupServiceConfiguration.merchantId(),
					Integer.valueOf(keyVersion),
					mercanetGroupServiceConfiguration.secretKey());

				Map<String, String> verifyMap = new HashMap<>();

				verifyMap.put("Data", data);

				String seal = ParamUtil.getString(httpServletRequest, "Seal");

				verifyMap.put("Seal", seal);

				PaypageResponse paypageResponse = paypageClient.decodeResponse(
					verifyMap);

				ResponseData responseData = paypageResponse.getData();

				ResponseCode responseCode = responseData.getResponseCode();

				if (Objects.equals(responseCode.getCode(), "00") &&
					Objects.equals(
						responseData.getMerchantId(),
						mercanetGroupServiceConfiguration.merchantId()) &&
					Objects.equals(
						parameterMap.get("customerId"),
						String.valueOf(commerceOrder.getUserId())) &&
					Objects.equals(
						parameterMap.get("orderId"),
						String.valueOf(commerceOrder.getCommerceOrderId())) &&
					Objects.equals(
						responseData.getTransactionReference(),
						commerceOrder.getTransactionId())) {

					_commercePaymentEngine.completePayment(
						commerceOrder.getCommerceOrderId(),
						commerceOrder.getTransactionId(), httpServletRequest);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private MercanetGroupServiceConfiguration _getConfiguration(Long groupId)
		throws ConfigurationException {

		return _configurationProvider.getConfiguration(
			MercanetGroupServiceConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, MercanetCommercePaymentMethodConstants.SERVICE_NAME));
	}

	private Map<String, String> _getResponseParameters(String data) {
		Map<String, String> map = new HashMap<>();

		String[] params = data.split(StringPool.BACK_SLASH + StringPool.PIPE);

		for (String param : params) {
			String[] kvp = StringUtil.split(param, CharPool.EQUAL);

			map.put(kvp[0], kvp[1]);
		}

		return map;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MercanetServlet.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.payment.method.mercanet)"
	)
	private ServletContext _servletContext;

}