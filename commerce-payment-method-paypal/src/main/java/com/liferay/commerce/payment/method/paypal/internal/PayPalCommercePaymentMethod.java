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

package com.liferay.commerce.payment.method.paypal.internal;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.paypal.internal.configuration.PayPalGroupServiceConfiguration;
import com.liferay.commerce.payment.method.paypal.internal.constants.PayPalCommercePaymentMethodConstants;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.DetailedRefund;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.RefundRequest;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.payment.engine.method.key=" + PayPalCommercePaymentMethod.KEY,
	service = CommercePaymentMethod.class
)
public class PayPalCommercePaymentMethod implements CommercePaymentMethod {

	public static final String KEY = "paypal";

	public PayPalCommercePaymentMethod() {
		_payPalDecimalFormat = new DecimalFormat("#,###.##");

		DecimalFormatSymbols decimalFormatSymbols =
			_payPalDecimalFormat.getDecimalFormatSymbols();

		decimalFormatSymbols.setDecimalSeparator(CharPool.PERIOD);
		decimalFormatSymbols.setGroupingSeparator(CharPool.COMMA);

		_payPalDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
	}

	@Override
	public CommercePaymentResult authorizePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		return null;
	}

	@Override
	public CommercePaymentResult cancelPayment(
		CommercePaymentRequest commercePaymentRequest) {

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderPaymentConstants.STATUS_CANCELLED, false, null, null,
			Collections.emptyList(), true);
	}

	@Override
	public CommercePaymentResult capturePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		Amount amount = _getAmount(
			commerceOrder, commerceOrder.getCommerceCurrency());

		Capture capture = new Capture();

		capture.setAmount(amount);
		capture.setIsFinalCapture(true);

		APIContext apiContext = _getAPIContext(commerceOrder);

		Authorization authorization = Authorization.get(
			apiContext, commercePaymentRequest.getTransactionId());

		Capture responseCapture = authorization.capture(apiContext, capture);

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					responseCapture.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(responseCapture.getReasonCode());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			false, null, responseCapture.getId(), messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult completePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		PayPalCommercePaymentRequest payPalCommercePaymentRequest =
			(PayPalCommercePaymentRequest)commercePaymentRequest;

		long commerceOrderId =
			payPalCommercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		String payerId = payPalCommercePaymentRequest.getPayerId();

		String transactionId = payPalCommercePaymentRequest.getTransactionId();

		APIContext apiContext = _getAPIContext(commerceOrder);

		Payment payment = new Payment();

		payment.setId(transactionId);

		PaymentExecution paymentExecution = new PaymentExecution();

		paymentExecution.setPayerId(payerId);

		payment.execute(apiContext, paymentExecution);

		boolean success = true;

		if (PayPalCommercePaymentMethodConstants.PAYMENT_STATE_FAILED.equals(
				payment.getState())) {

			success = false;
		}

		List<String> messages = new ArrayList<>();

		messages.add(payment.getFailureReason());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			false, null, null, messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult completeRecurringPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		PayPalCommercePaymentRequest payPalCommercePaymentRequest =
			(PayPalCommercePaymentRequest)commercePaymentRequest;

		String token = payPalCommercePaymentRequest.getTransactionId();

		Agreement agreement = new Agreement();

		agreement.setToken(token);

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		APIContext apiContext = _getAPIContext(commerceOrder);

		Agreement activeAgreement = agreement.execute(
			apiContext, agreement.getToken());

		boolean success = true;

		if (PayPalCommercePaymentMethodConstants.PAYMENT_STATE_FAILED.equals(
				activeAgreement.getState())) {

			success = false;
		}

		List<String> messages = new ArrayList<>();

		messages.add(activeAgreement.getDescription());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			false, null, null, messages, success);

		return commercePaymentResult;
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "paypal-description");
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public int getPaymentType() {
		return CommercePaymentConstants.
			COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT;
	}

	@Override
	public String getServletPath() {
		return PayPalCommercePaymentMethodConstants.SERVLET_PATH;
	}

	@Override
	public boolean isAuthorizeEnabled() {
		return true;
	}

	@Override
	public boolean isCancelEnabled() {
		return true;
	}

	@Override
	public boolean isCaptureEnabled() {
		return true;
	}

	@Override
	public boolean isCompleteEnabled() {
		return true;
	}

	@Override
	public boolean isCompleteRecurringEnabled() {
		return true;
	}

	@Override
	public boolean isPartialRefundEnabled() {
		return true;
	}

	@Override
	public boolean isProcessPaymentEnabled() {
		return true;
	}

	@Override
	public boolean isProcessRecurringEnabled() {
		return true;
	}

	@Override
	public boolean isRefundEnabled() {
		return true;
	}

	@Override
	public boolean isVoidEnabled() {
		return true;
	}

	@Override
	public CommercePaymentResult partiallyRefundPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		APIContext apiContext = _getAPIContext(commerceOrder);

		RefundRequest refundRequest = new RefundRequest();

		Amount amount = new Amount();

		BigDecimal commercePaymentRequestAmount =
			commercePaymentRequest.getAmount();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		amount.setCurrency(StringUtil.toUpperCase(commerceCurrency.getCode()));

		amount.setTotal(
			_payPalDecimalFormat.format(commercePaymentRequestAmount));

		refundRequest.setAmount(amount);

		Sale sale = Sale.get(
			apiContext, commercePaymentRequest.getTransactionId());

		DetailedRefund detailedRefund = sale.refund(apiContext, refundRequest);

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					detailedRefund.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(detailedRefund.getDescription());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId,
			CommerceOrderConstants.ORDER_STATUS_PARTIALLY_REFUNDED, false, null,
			null, messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult processPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		Payment payment = _getPayment(
			commercePaymentRequest, commerceOrder,
			PayPalCommercePaymentMethodConstants.INTENTSALE);

		String url = null;

		for (Links links : payment.getLinks()) {
			if ("approval_url".equals(links.getRel())) {
				url = links.getHref();

				break;
			}
		}

		if (Validator.isNull(url)) {
			throw new PortalException("Unable to get PayPal payment URL");
		}

		url = _http.addParameter(url, "useraction", "commit");

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_CREATED.equals(payment.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(payment.getFailureReason());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			payment.getId(), commerceOrderId,
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, true, url, null,
			messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult processRecurringPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		APIContext apiContext = _getAPIContext(commerceOrder);

		Plan plan = _getPlan(commercePaymentRequest, commerceOrder, apiContext);

		if (plan == null) {
			return null;
		}

		Agreement agreement = _getAgreement(commerceOrder, apiContext, plan);

		String url = null;

		for (Links links : agreement.getLinks()) {
			if ("approval_url".equals(links.getRel())) {
				url = links.getHref();

				break;
			}
		}

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_CREATED.equals(agreement.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(agreement.getState());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			agreement.getToken(), commerceOrderId,
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, true, url, null,
			messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult refundPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		APIContext apiContext = _getAPIContext(commerceOrder);

		RefundRequest refundRequest = new RefundRequest();

		Amount amount = _getAmount(
			commerceOrder, commerceOrder.getCommerceCurrency());

		refundRequest.setAmount(amount);

		Sale sale = Sale.get(
			apiContext, commercePaymentRequest.getTransactionId());

		DetailedRefund detailedRefund = sale.refund(apiContext, refundRequest);

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					detailedRefund.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(detailedRefund.getDescription());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId, CommerceOrderConstants.ORDER_STATUS_REFUNDED,
			false, null, null, messages, success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult voidTransaction(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		APIContext apiContext = _getAPIContext(commerceOrder);

		Authorization authorization = Authorization.get(
			apiContext, commercePaymentRequest.getTransactionId());

		authorization.doVoid(apiContext);

		boolean success = false;

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_VOIDED.equals(authorization.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(authorization.getPendingReason());

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			null, commerceOrderId,
			CommerceOrderConstants.PAYMENT_STATUS_PENDING, false, null, null,
			messages, success);

		return commercePaymentResult;
	}

	private Agreement _getAgreement(
			CommerceOrder commerceOrder, APIContext apiContext, Plan plan)
		throws Exception {

		// Create new agreement

		Agreement agreement = new Agreement();

		agreement.setName("Base Agreement");
		agreement.setDescription("Basic Agreement");
		agreement.setStartDate("2019-06-17T9:45:04Z");

		// Set plan ID

		Plan agreementPlan = new Plan();

		agreementPlan.setId(plan.getId());

		agreement.setPlan(agreementPlan);

		// Add payer details

		Payer payer = new Payer();

		payer.setPaymentMethod(KEY);

		agreement.setPayer(payer);

		ShippingAddress shippingAddress = _getShippingAddress(
			commerceOrder.getShippingAddress());

		shippingAddress.setRecipientName(null);

		agreement.setShippingAddress(shippingAddress);

		return agreement.create(apiContext);
	}

	private Amount _getAmount(
		CommerceOrder commerceOrder, CommerceCurrency commerceCurrency) {

		Amount amount = new Amount();

		amount.setCurrency(StringUtil.toUpperCase(commerceCurrency.getCode()));

		Details details = new Details();

		details.setShipping(
			_payPalDecimalFormat.format(commerceOrder.getShippingAmount()));
		details.setSubtotal(
			_payPalDecimalFormat.format(commerceOrder.getSubtotal()));

		amount.setDetails(details);

		amount.setTotal(_payPalDecimalFormat.format(commerceOrder.getTotal()));

		return amount;
	}

	private APIContext _getAPIContext(CommerceOrder commerceOrder)
		throws PortalException {

		PayPalGroupServiceConfiguration payPalGroupServiceConfiguration =
			_configurationProvider.getConfiguration(
				PayPalGroupServiceConfiguration.class,
				new GroupServiceSettingsLocator(
					commerceOrder.getSiteGroupId(),
					PayPalCommercePaymentMethodConstants.SERVICE_NAME));

		return new APIContext(
			payPalGroupServiceConfiguration.clientId(),
			payPalGroupServiceConfiguration.clientSecret(),
			payPalGroupServiceConfiguration.mode());
	}

	private ItemList _getItemList(
			CommerceOrder commerceOrder, CommerceCurrency commerceCurrency,
			Locale locale)
		throws PortalException {

		ItemList itemList = new ItemList();

		itemList.setItems(_getItems(commerceOrder, commerceCurrency, locale));

		CommerceAddress commerceAddress = commerceOrder.getShippingAddress();

		if (commerceAddress != null) {
			itemList.setShippingAddress(_getShippingAddress(commerceAddress));
		}

		CommerceShippingMethod commerceShippingMethod =
			commerceOrder.getCommerceShippingMethod();

		if (commerceShippingMethod != null) {
			itemList.setShippingMethod(commerceShippingMethod.getName(locale));
		}

		return itemList;
	}

	private List<Item> _getItems(
			CommerceOrder commerceOrder, CommerceCurrency commerceCurrency,
			Locale locale)
		throws PortalException {

		String languageId = LanguageUtil.getLanguageId(locale);

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<Item> items = new ArrayList<>(commerceOrderItems.size());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			Item item = new Item();

			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

			item.setCurrency(
				StringUtil.toUpperCase(commerceCurrency.getCode()));
			item.setDescription(cpDefinition.getShortDescription(languageId));
			item.setName(commerceOrderItem.getName(languageId));
			item.setPrice(
				_payPalDecimalFormat.format(commerceOrderItem.getUnitPrice()));
			item.setQuantity(String.valueOf(commerceOrderItem.getQuantity()));
			item.setSku(commerceOrderItem.getSku());

			items.add(item);
		}

		return items;
	}

	private Payment _getPayment(
			CommercePaymentRequest commercePaymentRequest,
			CommerceOrder commerceOrder, String intent)
		throws PayPalRESTException, PortalException {

		APIContext apiContext = _getAPIContext(commerceOrder);

		Payment payment = new Payment();

		payment.setIntent(intent);

		Payer payer = new Payer();

		payer.setPaymentMethod(KEY);

		payment.setPayer(payer);

		RedirectUrls redirectUrls = new RedirectUrls();

		redirectUrls.setReturnUrl(commercePaymentRequest.getReturnUrl());
		redirectUrls.setCancelUrl(commercePaymentRequest.getCancelUrl());

		payment.setRedirectUrls(redirectUrls);

		payment.setTransactions(
			_getTransactions(
				commerceOrder, commerceOrder.getCommerceCurrency(),
				commercePaymentRequest.getLocale()));

		payment = payment.create(apiContext);

		return payment;
	}

	private Plan _getPlan(
			CommercePaymentRequest commercePaymentRequest,
			CommerceOrder commerceOrder, APIContext apiContext)
		throws PayPalRESTException, PortalException {

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<PaymentDefinition> paymentDefinitions = new ArrayList<>();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPInstance cpInstance = commerceOrderItem.getCPInstance();

			CPSubscriptionInfo cpSubscriptionInfo =
				cpInstance.getCPSubscriptionInfo();

			int subscriptionLength = cpSubscriptionInfo.getSubscriptionLength();

			long maxSubscriptionCycles =
				cpSubscriptionInfo.getMaxSubscriptionCycles();

			String subscriptionType = cpSubscriptionInfo.getSubscriptionType();

			if (subscriptionType.equals(
					CPConstants.MONTHLY_SUBSCRIPTION_TYPE)) {

				subscriptionType = "month";
			}

			if (subscriptionType.equals(CPConstants.DAILY_SUBSCRIPTION_TYPE)) {
				subscriptionType = "day";
			}

			if (subscriptionType.equals(CPConstants.WEEKLY_SUBSCRIPTION_TYPE)) {
				subscriptionType = "week";
			}

			if (subscriptionType.equals(CPConstants.YEARLY_SUBSCRIPTION_TYPE)) {
				subscriptionType = "year";
			}

			CommerceCurrency commerceCurrency =
				commerceOrder.getCommerceCurrency();

			String value = _payPalDecimalFormat.format(
				commerceOrderItem.getFinalPrice());

			Currency amount = new Currency(commerceCurrency.getCode(), value);

			PaymentDefinition paymentDefinition = new PaymentDefinition(
				"Payment Definition", "REGULAR",
				String.valueOf(subscriptionLength), subscriptionType,
				String.valueOf(maxSubscriptionCycles), amount);

			paymentDefinitions.add(paymentDefinition);
		}

		String name = "Payment Plan";
		String description = "Plan with regular payment definitions";

		Plan plan = new Plan(name, description, "fixed");

		plan.setPaymentDefinitions(paymentDefinitions);

		MerchantPreferences merchantPreferences = new MerchantPreferences();

		//merchantPreferences.setSetupFee(currency);
		merchantPreferences.setCancelUrl(commercePaymentRequest.getCancelUrl());
		merchantPreferences.setReturnUrl(commercePaymentRequest.getReturnUrl());
		merchantPreferences.setMaxFailAttempts("0");
		merchantPreferences.setAutoBillAmount("YES");
		merchantPreferences.setInitialFailAmountAction("CONTINUE");

		plan.setMerchantPreferences(merchantPreferences);

		plan = plan.create(apiContext);

		plan = _updatePlan(apiContext, plan);

		return plan;
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	private ShippingAddress _getShippingAddress(CommerceAddress commerceAddress)
		throws PortalException {

		ShippingAddress shippingAddress = new ShippingAddress();

		shippingAddress.setCity(commerceAddress.getCity());

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		shippingAddress.setCountryCode(commerceCountry.getTwoLettersISOCode());

		shippingAddress.setLine1(commerceAddress.getStreet1());
		shippingAddress.setLine2(commerceAddress.getStreet2());
		shippingAddress.setPostalCode(commerceAddress.getZip());
		shippingAddress.setRecipientName(commerceAddress.getName());

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		if (commerceRegion != null) {
			shippingAddress.setState(commerceRegion.getCode());
		}

		return shippingAddress;
	}

	private List<Transaction> _getTransactions(
			CommerceOrder commerceOrder, CommerceCurrency commerceCurrency,
			Locale locale)
		throws PortalException {

		Transaction transaction = new Transaction();

		transaction.setAmount(_getAmount(commerceOrder, commerceCurrency));
		transaction.setItemList(
			_getItemList(commerceOrder, commerceCurrency, locale));

		return Collections.singletonList(transaction);
	}

	private Plan _updatePlan(APIContext apiContext, Plan plan)
		throws PayPalRESTException {

		Map<String, String> value = new HashMap<>();

		value.put("state", "ACTIVE");

		Patch patch = new Patch();

		patch.setOp("replace");
		patch.setPath("/");
		patch.setValue(value);

		List<Patch> patches = new ArrayList<>();

		patches.add(patch);

		plan.update(apiContext, patches);

		return plan;
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Http _http;

	private final DecimalFormat _payPalDecimalFormat;

}