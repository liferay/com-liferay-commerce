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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.exception.UnknownStatusException;

import java.util.Arrays;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public enum AcquirerResponseCode {

	APPROVE_AFTER_IDENTIFICATION("08"), AWAITING_PAYMENT_CONFIRMATION("62"),
	CONTACT_ACQUIRER("60"), CONTACT_ISSUER("02"), CREDIT_UNAVAILABLE("51"),
	CUSTOMER_CANCELLATION("17"), DAILY_TRANSACTION_LIMIT_EXCEEDED("65"),
	DO_NOT_HONOUR("05"), DUPLICATE_TRANSACTION("94"),
	EXCEEDED_AMOUNT_LIMIT("61"), FRAUD_SUSPECTED("34", "59"),
	INCOMPLETE_TRANSACTION_INFO("92"), INITIATOR_DOMAIN_INCIDENT("99"),
	INVALID_ACCEPTOR("03"), INVALID_AMOUNT("13"), INVALID_FORMAT("30"),
	INVALID_PAN("14"), INVALID_TRANSACTION("12"), ISSUER_INACCESSIBLE("91"),
	KEEP_PAYMENT_MEAN("04"), KEEP_PAYMENT_MEAN_UNDER_CONDITION("07"),
	MAX_ATTEMPTS_REACHED("75"), MISSING_RESPONSE("68"), NOT_AUTHORIZED("24"),
	NOT_SECURITY_COMPLIANT("63"), NOT_SUPPORTED("40"),
	PAYMENT_MEAN_EXPIRED("33", "54"), PAYMENT_MEAN_LOST("41"),
	PAYMENT_MEAN_MISSING("56"), PAYMENT_MEAN_STOLEN("43"),
	SERVER_UNAVAILABLE("98"), SYSTEM_MALFUNCTION("96"), SYSTEM_STOPPED("90"),
	TERMINAL_UNKNOWN("87"), TIMEFRAME_EXCEEDED("97"),
	TRANSACTION_FORBIDDEN("58"), TRANSACTION_NOT_FOUND("25"),
	TRANSACTION_SUCCESS("00"), TRANSACTION_UNAUTHORIZED("57"),
	UNKNOWN_ACQUIRER_ID("31"), UNKNOWN_ISSUER("15");

	@JsonCreator
	public static AcquirerResponseCode fromCode(String code)
		throws UnknownStatusException {

		for (AcquirerResponseCode acquirerResponseCode :
				AcquirerResponseCode.values()) {

			List<String> acquirerResponseCodeList = Arrays.asList(
				acquirerResponseCode.getCodes());

			if (acquirerResponseCodeList.contains(code)) {
				return acquirerResponseCode;
			}
		}

		throw new UnknownStatusException(
			code + " is an unknown acquirer response code!");
	}

	@JsonValue
	public String[] getCodes() {
		return _codes;
	}

	private AcquirerResponseCode(String... codes) {
		_codes = codes;
	}

	private final String[] _codes;

}