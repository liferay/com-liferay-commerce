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
import com.liferay.commerce.payment.result.CommerceSubscriptionStatusResult;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.AgreementDetails;
import com.paypal.api.payments.AgreementStateDescriptor;
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
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

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

		boolean success = false;

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Authorization authorization = Authorization.get(
			apiContext, commercePaymentRequest.getTransactionId());

		Capture capture = new Capture();

		Amount amount = _getAmount(
			commerceOrder, commerceOrder.getCommerceCurrency());

		capture.setAmount(amount);

		capture.setIsFinalCapture(true);

		Capture responseCapture = authorization.capture(apiContext, capture);

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					responseCapture.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(responseCapture.getReasonCode());

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PAID, false, null,
			responseCapture.getId(), messages, success);
	}

	@Override
	public CommercePaymentResult completePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		boolean success = true;

		Payment payment = new Payment();

		PayPalCommercePaymentRequest payPalCommercePaymentRequest =
			(PayPalCommercePaymentRequest)commercePaymentRequest;

		String transactionId = payPalCommercePaymentRequest.getTransactionId();

		payment.setId(transactionId);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				payPalCommercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		PaymentExecution paymentExecution = new PaymentExecution();

		String payerId = payPalCommercePaymentRequest.getPayerId();

		paymentExecution.setPayerId(payerId);

		payment.execute(apiContext, paymentExecution);

		if (PayPalCommercePaymentMethodConstants.PAYMENT_STATE_FAILED.equals(
				payment.getState())) {

			success = false;
		}

		List<String> messages = new ArrayList<>();

		messages.add(payment.getFailureReason());

		return new CommercePaymentResult(
			null, payPalCommercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PAID, false, null, null,
			messages, success);
	}

	@Override
	public CommercePaymentResult completeRecurringPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		boolean success = true;

		Agreement agreement = new Agreement();

		PayPalCommercePaymentRequest payPalCommercePaymentRequest =
			(PayPalCommercePaymentRequest)commercePaymentRequest;

		agreement.setToken(payPalCommercePaymentRequest.getTransactionId());

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Agreement activeAgreement = agreement.execute(
			apiContext, agreement.getToken());

		if (PayPalCommercePaymentMethodConstants.PAYMENT_STATE_FAILED.equals(
				activeAgreement.getState())) {

			success = false;
		}

		List<String> messages = new ArrayList<>();

		messages.add(activeAgreement.getDescription());

		return new CommercePaymentResult(
			activeAgreement.getId(),
			commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PAID, false, null, null,
			messages, success);
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
	public CommerceSubscriptionStatusResult getSubscriptionPaymentDetails(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Agreement agreement = Agreement.get(
			apiContext, commercePaymentRequest.getTransactionId());

		AgreementDetails agreementDetails = agreement.getAgreementDetails();

		Long failedPaymentCount = GetterUtil.getLong(
			agreementDetails.getFailedPaymentCount());
		Long cyclesRemaining = GetterUtil.getLong(
			agreementDetails.getCyclesRemaining());
		Long cyclesCompleted = GetterUtil.getLong(
			agreementDetails.getCyclesCompleted());

		CommerceSubscriptionStatusResult commerceSubscriptionStatusResult =
			new CommerceSubscriptionStatusResult(
				failedPaymentCount, cyclesRemaining, cyclesCompleted);

		return commerceSubscriptionStatusResult;
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

		boolean success = false;

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Sale sale = Sale.get(
			apiContext, commercePaymentRequest.getTransactionId());

		RefundRequest refundRequest = new RefundRequest();

		Amount amount = new Amount();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		amount.setCurrency(StringUtil.toUpperCase(commerceCurrency.getCode()));

		amount.setTotal(
			_payPalDecimalFormat.format(commercePaymentRequest.getAmount()));

		refundRequest.setAmount(amount);

		DetailedRefund detailedRefund = sale.refund(apiContext, refundRequest);

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					detailedRefund.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(detailedRefund.getDescription());

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.ORDER_STATUS_PARTIALLY_REFUNDED, false, null,
			null, messages, success);
	}

	@Override
	public int getOrderStatusUpdateMaxIntervalMinutes() {
		return 2880;
	}

	@Override
	public CommercePaymentResult processPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		boolean success = false;
		String url = null;

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		Payment payment = _getPayment(
			commercePaymentRequest, commerceOrder,
			PayPalCommercePaymentMethodConstants.INTENT_SALE);

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

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_CREATED.equals(payment.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(payment.getFailureReason());

		return new CommercePaymentResult(
			payment.getId(), commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, true, url, null,
			messages, success);
	}

	@Override
	public CommercePaymentResult processRecurringPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Plan plan = _getPlan(commercePaymentRequest, commerceOrder, apiContext);

		if (plan == null) {
			return null;
		}

		boolean success = false;
		String url = null;

		Agreement agreement = _getAgreement(commerceOrder, apiContext, plan);

		for (Links links : agreement.getLinks()) {
			if ("approval_url".equals(links.getRel())) {
				url = links.getHref();

				break;
			}
		}

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_CREATED.equals(agreement.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(agreement.getState());

		return new CommercePaymentResult(
			agreement.getToken(), commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, true, url, null,
			messages, success);
	}

	@Override
	public CommercePaymentResult refundPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		boolean success = false;

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Sale sale = Sale.get(
			apiContext, commercePaymentRequest.getTransactionId());

		RefundRequest refundRequest = new RefundRequest();

		Amount amount = _getAmount(
			commerceOrder, commerceOrder.getCommerceCurrency());

		refundRequest.setAmount(amount);

		DetailedRefund detailedRefund = sale.refund(apiContext, refundRequest);

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_COMPLETED.equals(
					detailedRefund.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(detailedRefund.getDescription());

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.ORDER_STATUS_REFUNDED, false, null, null,
			messages, success);
	}

	@Override
	public boolean suspendSubscription(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		Agreement agreement = new Agreement();

		agreement.setId(commercePaymentRequest.getTransactionId());

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		AgreementStateDescriptor agreementStateDescriptor =
			new AgreementStateDescriptor();

		agreementStateDescriptor.setNote("Suspending the agreement");

		agreement.suspend(apiContext, agreementStateDescriptor);

		Agreement updatedAgreement = Agreement.get(
			apiContext, agreement.getId());

		if ("Suspended".equals(updatedAgreement.getState())) {
			return true;
		}

		return false;
	}

	@Override
	public CommercePaymentResult voidTransaction(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		boolean success = false;

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commercePaymentRequest.getCommerceOrderId());

		APIContext apiContext = _getAPIContext(commerceOrder);

		Authorization authorization = Authorization.get(
			apiContext, commercePaymentRequest.getTransactionId());

		authorization.doVoid(apiContext);

		if (PayPalCommercePaymentMethodConstants.
				AUTHORIZATION_STATE_VOIDED.equals(authorization.getState())) {

			success = true;
		}

		List<String> messages = new ArrayList<>();

		messages.add(authorization.getPendingReason());

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PENDING, false, null, null,
			messages, success);
	}

	private Agreement _getAgreement(
			CommerceOrder commerceOrder, APIContext apiContext, Plan plan)
		throws Exception {

		// Create new agreement

		Agreement agreement = new Agreement();

		agreement.setName("Base Agreement");
		agreement.setDescription("Basic Agreement");

		String pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		calendar.add(Calendar.DAY_OF_MONTH, 1);

		String date = simpleDateFormat.format(calendar.getTime());

		agreement.setStartDate(date);

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
					commerceOrder.getGroupId(),
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

			item.setCurrency(
				StringUtil.toUpperCase(commerceCurrency.getCode()));

			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

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

		Payment payment = new Payment();

		payment.setIntent(intent);

		Payer payer = new Payer();

		payer.setPaymentMethod(KEY);

		payment.setPayer(payer);

		RedirectUrls redirectUrls = new RedirectUrls();

		redirectUrls.setCancelUrl(commercePaymentRequest.getCancelUrl());
		redirectUrls.setReturnUrl(commercePaymentRequest.getReturnUrl());

		payment.setRedirectUrls(redirectUrls);

		payment.setTransactions(
			_getTransactions(
				commerceOrder, commerceOrder.getCommerceCurrency(),
				commercePaymentRequest.getLocale()));

		APIContext apiContext = _getAPIContext(commerceOrder);

		return payment.create(apiContext);
	}

	private Plan _getPlan(
			CommercePaymentRequest commercePaymentRequest,
			CommerceOrder commerceOrder, APIContext apiContext)
		throws PayPalRESTException, PortalException {

		String name = "Payment Plan";
		String description = "Plan with regular payment definitions";

		Plan plan = new Plan(name, description, "fixed");

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<PaymentDefinition> paymentDefinitions = new ArrayList<>(
			commerceOrderItems.size());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPInstance cpInstance = commerceOrderItem.getCPInstance();

			CPSubscriptionInfo cpSubscriptionInfo =
				cpInstance.getCPSubscriptionInfo();

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

			BigDecimal finalPrice = commerceOrderItem.getFinalPrice();

			Currency amount = new Currency(
				commerceCurrency.getCode(),
				_payPalDecimalFormat.format(finalPrice));

			PaymentDefinition paymentDefinition = new PaymentDefinition(
				"Payment Definition", "REGULAR",
				String.valueOf(cpSubscriptionInfo.getSubscriptionLength()),
				subscriptionType,
				String.valueOf(cpSubscriptionInfo.getMaxSubscriptionCycles()),
				amount);

			paymentDefinitions.add(paymentDefinition);
		}

		plan.setPaymentDefinitions(paymentDefinitions);

		MerchantPreferences merchantPreferences = new MerchantPreferences();

		merchantPreferences.setAutoBillAmount("YES");
		merchantPreferences.setCancelUrl(commercePaymentRequest.getCancelUrl());
		merchantPreferences.setInitialFailAmountAction("CONTINUE");
		merchantPreferences.setMaxFailAttempts("0");
		merchantPreferences.setReturnUrl(commercePaymentRequest.getReturnUrl());

		plan.setMerchantPreferences(merchantPreferences);

		plan = plan.create(apiContext);

		return _updatePlan(apiContext, plan);
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

		Patch patch = new Patch();

		patch.setOp("replace");
		patch.setPath("/");

		patch.setValue(Collections.singletonMap("state", "ACTIVE"));

		plan.update(apiContext, Collections.singletonList(patch));

		return plan;
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Http _http;

	private final DecimalFormat _payPalDecimalFormat;

}