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
public enum CardCSCResultCode {

	CORRECT_CRYPTOGRAM("4D"), COULD_NOT_PREFORM("55"), CRYPTOGRAM_MISSING("53"),
	CRYPTOGRAM_NOT_PROCESSED("50"), INCORRECT_CRYPTOGRAM("4E");

	@JsonCreator
	public static CardCSCResultCode fromCode(String code)
		throws UnknownStatusException {

		for (CardCSCResultCode cardCSCResultCode : CardCSCResultCode.values()) {
			String cardResultCode = cardCSCResultCode.getCode();

			if (cardResultCode.equals(code)) {
				return cardCSCResultCode;
			}
		}

		throw new UnknownStatusException(
			code + " is an unknown card CSC result code!");
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	protected final String code;

	private CardCSCResultCode(String code) {
		this.code = code;
	}

}