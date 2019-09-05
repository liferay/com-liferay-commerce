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

/**
 * @author Luca Pellizzon
 */
public enum ResponseCode {

	ACCEPTED("00"), AUTHORIZATION_REFUSED("05"), CARD_CEILING_EXCEEDED("02"),
	CUSTOMER_CANCELLATION("17"), DUPLICATED_TRANSACTION("94"),
	FRAUD_SUSPECTED("34"), INCORRECT_FORMAT("30"), INTERNAL_ERROR("99"),
	INVALID_DATA("14"), INVALID_MERCHANT_CONTRACT("03"),
	INVALID_TRANSACTION("12"), MAX_ATTEMPTS_REACHED("75"), PAN_BLOCKED("11"),
	PAYMENT_MEAN_EXPIRED("54"), SERVICE_UNAVAILABLE("90"),
	TIMEFRAME_EXCEEDED("97");

	@JsonCreator
	public static ResponseCode fromCode(String code)
		throws UnknownStatusException {

		for (ResponseCode responseCode : ResponseCode.values()) {
			String respCode = responseCode.getCode();

			if (respCode.equals(code)) {
				return responseCode;
			}
		}

		throw new UnknownStatusException(
			code + " is an unknown response code!");
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	protected final String code;

	private ResponseCode(String code) {
		this.code = code;
	}

}