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

package com.liferay.commerce.payment.method.authorize.net.internal;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.authorize.net.internal.configuration.AuthorizeNetGroupServiceConfiguration;
import com.liferay.commerce.payment.method.authorize.net.internal.constants.AuthorizeNetCommercePaymentMethodConstants;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.URLEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.GetHostedPaymentPageRequest;
import net.authorize.api.contract.v1.GetHostedPaymentPageResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.SettingType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.GetHostedPaymentPageController;
import net.authorize.api.controller.base.ApiOperationBase;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.payment.engine.method.key=" + AuthorizeNetCommercePaymentMethod.KEY,
	service = CommercePaymentMethod.class
)
public class AuthorizeNetCommercePaymentMethod
	implements CommercePaymentMethod {

	public static final String KEY = "authorize-net";

	@Override
	public CommercePaymentResult cancelPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderPaymentConstants.STATUS_CANCELLED, false, null, null,
			Collections.emptyList(), true);
	}

	@Override
	public CommercePaymentResult completePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		AuthorizeNetCommercePaymentRequest authorizeNetCommercePaymentRequest =
			(AuthorizeNetCommercePaymentRequest)commercePaymentRequest;

		return new CommercePaymentResult(
			null, authorizeNetCommercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PAID, false, null, null,
			Collections.emptyList(), true);
	}

	@Override
	public String getDescription(Locale locale) {
		return null;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, KEY);
	}

	@Override
	public int getPaymentType() {
		return CommercePaymentConstants.
			COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT;
	}

	@Override
	public String getServletPath() {
		return AuthorizeNetCommercePaymentMethodConstants.
			COMPLETE_PAYMENT_SERVLET_PATH;
	}

	@Override
	public boolean isCancelEnabled() {
		return true;
	}

	@Override
	public boolean isCompleteEnabled() {
		return true;
	}

	@Override
	public boolean isProcessPaymentEnabled() {
		return true;
	}

	@Override
	public CommercePaymentResult processPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		AuthorizeNetCommercePaymentRequest authorizeNetCommercePaymentRequest =
			(AuthorizeNetCommercePaymentRequest)commercePaymentRequest;

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			authorizeNetCommercePaymentRequest.getCommerceOrderId());

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByOrderGroupId(
				commerceOrder.getGroupId());

		AuthorizeNetGroupServiceConfiguration configuration = _getConfiguration(
			commerceChannel.getSiteGroupId());

		Environment environment = Environment.valueOf(
			StringUtil.toUpperCase(configuration.environment()));

		ApiOperationBase.setEnvironment(environment);

		MerchantAuthenticationType merchantAuthenticationType =
			new MerchantAuthenticationType();

		merchantAuthenticationType.setName(configuration.apiLoginId());
		merchantAuthenticationType.setTransactionKey(
			configuration.transactionKey());

		ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

		GetHostedPaymentPageRequest getHostedPaymentPageRequest =
			new GetHostedPaymentPageRequest();

		TransactionRequestType transactionRequestType =
			_getTransactionRequestType(commerceOrder);

		getHostedPaymentPageRequest.setTransactionRequest(
			transactionRequestType);

		ArrayOfSetting arrayOfSetting = _getArrayOfSetting(
			commerceOrder.getGroupId(),
			authorizeNetCommercePaymentRequest.getCancelUrl(),
			authorizeNetCommercePaymentRequest.getReturnUrl(),
			authorizeNetCommercePaymentRequest.getLocale());

		getHostedPaymentPageRequest.setHostedPaymentSettings(arrayOfSetting);

		GetHostedPaymentPageController controller =
			new GetHostedPaymentPageController(getHostedPaymentPageRequest);

		controller.execute();

		GetHostedPaymentPageResponse response = controller.getApiResponse();

		if ((response != null) && (response.getToken() != null)) {
			String token = response.getToken();

			String redirectUrl =
				AuthorizeNetCommercePaymentMethodConstants.SANDBOX_REDIRECT_URL;

			String environmentName = environment.name();

			if (environmentName.equals(Environment.PRODUCTION.name())) {
				redirectUrl =
					AuthorizeNetCommercePaymentMethodConstants.
						PRODUCTION_REDIRECT_URL;
			}

			String url = StringBundler.concat(
				_getServletUrl(authorizeNetCommercePaymentRequest),
				StringPool.QUESTION, "redirectUrl=",
				URLCodec.encodeURL(redirectUrl), StringPool.AMPERSAND, "token=",
				URLEncoder.encode(token, "UTF-8"));

			return new CommercePaymentResult(
				token, authorizeNetCommercePaymentRequest.getCommerceOrderId(),
				-1, true, url, null, Collections.emptyList(), true);
		}

		return _emptyResult(
			authorizeNetCommercePaymentRequest.getCommerceOrderId());
	}

	private void _addSetting(
		List<SettingType> settings, String name, String value) {

		SettingType billingAddress = new SettingType();

		billingAddress.setSettingName(name);
		billingAddress.setSettingValue(value);

		settings.add(billingAddress);
	}

	private CommercePaymentResult _emptyResult(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	private String _fixURL(String url) {

		// See https://community.developer.authorize.net/t5/
		// Integration-and-Testing/Unanticipated-Error-Occured-Hosted-Payment
		// /m-p/57815#M32503

		return StringUtil.replace(
			url, new String[] {StringPool.PERCENT, StringPool.AMPERSAND},
			new String[] {"%25", "%26"});
	}

	private ArrayOfSetting _getArrayOfSetting(
			Long groupId, String cancelURL, String returnURL, Locale locale)
		throws PortalException {

		AuthorizeNetGroupServiceConfiguration configuration = _getConfiguration(
			groupId);

		ArrayOfSetting arrayOfSetting = new ArrayOfSetting();

		List<SettingType> settings = arrayOfSetting.getSetting();

		JSONObject hostedPaymentReturnOptionsJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentReturnOptionsJSONObject.put(
			"cancelUrl", _fixURL(cancelURL));
		hostedPaymentReturnOptionsJSONObject.put("cancelUrlText", "Cancel");
		hostedPaymentReturnOptionsJSONObject.put("showReceipt", true);
		hostedPaymentReturnOptionsJSONObject.put("url", _fixURL(returnURL));
		hostedPaymentReturnOptionsJSONObject.put("urlText", "Continue");

		_addSetting(
			settings, "hostedPaymentReturnOptions",
			hostedPaymentReturnOptionsJSONObject.toJSONString());

		JSONObject hostedPaymentPaymentOptionsJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentPaymentOptionsJSONObject.put(
			"cardCodeRequired", configuration.requireCardCodeVerification());
		hostedPaymentPaymentOptionsJSONObject.put(
			"showBankAccount", configuration.showBankAccount());
		hostedPaymentPaymentOptionsJSONObject.put(
			"showCreditCard", configuration.showCreditCard());

		_addSetting(
			settings, "hostedPaymentPaymentOptions",
			hostedPaymentPaymentOptionsJSONObject.toJSONString());

		JSONObject hostedPaymentSecurityOptionsJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentSecurityOptionsJSONObject.put(
			"captcha", configuration.requireCaptcha());

		_addSetting(
			settings, "hostedPaymentSecurityOptions",
			hostedPaymentSecurityOptionsJSONObject.toJSONString());

		JSONObject hostedPaymentShippingAddressOptionsJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentShippingAddressOptionsJSONObject.put("required", false);
		hostedPaymentShippingAddressOptionsJSONObject.put("show", false);

		_addSetting(
			settings, "hostedPaymentShippingAddressOptions",
			hostedPaymentShippingAddressOptionsJSONObject.toJSONString());

		JSONObject hostedPaymentBillingAddressOptionsJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentBillingAddressOptionsJSONObject.put("required", false);
		hostedPaymentBillingAddressOptionsJSONObject.put("show", false);

		_addSetting(
			settings, "hostedPaymentBillingAddressOptions",
			hostedPaymentBillingAddressOptionsJSONObject.toJSONString());

		JSONObject hostedPaymentCustomerOptionsJSJSONObject =
			_jsonFactory.createJSONObject();

		hostedPaymentCustomerOptionsJSJSONObject.put(
			"addPaymentProfile", false);
		hostedPaymentCustomerOptionsJSJSONObject.put("requiredEmail", false);
		hostedPaymentCustomerOptionsJSJSONObject.put("showEmail", false);

		_addSetting(
			settings, "hostedPaymentCustomerOptions",
			hostedPaymentCustomerOptionsJSJSONObject.toJSONString());

		JSONObject hostedPaymentOrderOptionsJSONObject =
			_jsonFactory.createJSONObject();

		Group group = _groupLocalService.getGroup(groupId);

		hostedPaymentOrderOptionsJSONObject.put(
			"merchantName", group.getDescriptiveName(locale));

		hostedPaymentOrderOptionsJSONObject.put(
			"show", configuration.showStoreName());

		_addSetting(
			settings, "hostedPaymentOrderOptions",
			hostedPaymentOrderOptionsJSONObject.toJSONString());

		return arrayOfSetting;
	}

	private AuthorizeNetGroupServiceConfiguration _getConfiguration(
			Long groupId)
		throws ConfigurationException {

		return _configurationProvider.getConfiguration(
			AuthorizeNetGroupServiceConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId,
				AuthorizeNetCommercePaymentMethodConstants.SERVICE_NAME));
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	private String _getServletUrl(
		AuthorizeNetCommercePaymentRequest authorizeNetCommercePaymentRequest) {

		return StringBundler.concat(
			_portal.getPortalURL(
				authorizeNetCommercePaymentRequest.getHttpServletRequest()),
			_portal.getPathModule(), StringPool.SLASH,
			AuthorizeNetCommercePaymentMethodConstants.
				START_PAYMENT_SERVLET_PATH);
	}

	private TransactionRequestType _getTransactionRequestType(
			CommerceOrder commerceOrder)
		throws PortalException {

		TransactionRequestType transactionRequestType =
			new TransactionRequestType();

		String transactionType =
			TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value();

		transactionRequestType.setTransactionType(transactionType);

		BigDecimal amount = commerceOrder.getTotal();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		transactionRequestType.setAmount(
			amount.setScale(
				commerceCurrency.getMaxFractionDigits(),
				RoundingMode.valueOf(commerceCurrency.getRoundingMode())));

		return transactionRequestType;
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}