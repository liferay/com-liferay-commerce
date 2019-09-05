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

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.exception.UnsupportedCurrencyException;

/**
 * @author Luca Pellizzon
 */
public enum Currency {

	AED("784", "United Arab Emirates Dirham"), ARS("032", "Argentinean Peso"),
	AUD("036", "Australian Dollar"), BGN("975", "Bulgaria Lev"),
	BHD("048", "Bahrain Dinar"), BRL("986", "Brazilian Real"),
	CAD("124", "Canadian Dollar"), CHF("756", "Swiss Franc"),
	CNY("156", "China Yuan Renminbi"), CZK("203", "Czech Republic Koruna"),
	DKK("208", "Danes crown"), EUR("978", "Euro"), GBP("826", "Pound"),
	HKD("344", "Hong Kong dollar"), HRK("191", "Croatia Kuna"),
	HUF("348", "Hungary Forint"), ILS("376", "Israel Shekel"),
	INR("356", "Indian rupee"), ISK("352", "Iceland Rupee"),
	JPY("392", "Japanese Yen"), KHR("116", "Cambodian Riel"),
	KRW("410", "South Korean Won"), KWD("414", "Kuwait Dinar"),
	LKR("144", "Sri Lanka Rupee"), MUR("480", "Mauritius Rupee"),
	MXN("484", "Mexican Peso"), MYR("458", "Malaysia Ringgit"),
	NOK("578", "Norwegian crown"), NPR("524", "Nepal Rupee"),
	NZD("554", "New Zealand Dollar"), PLN("985", "Poland Zloty"),
	QAR("634", "Qatar Riyal"), RON("946", "Roumania New Leu"),
	RUB("643", "Russia Ruble"), SAR("682", "Saudi Arabia Riyal"),
	SEK("752", "Swedish crown"), SGD("702", "Singapore Dollar"),
	THB("764", "Thailand Baht"), TND("788", "Tunisia Dinar"),
	TRY("949", "New Turkish Lira"), TWD("901", "Taiwan Dollar"),
	UAH("980", "Ukraine Hryvnia"), USD("840", "American Dollar"),
	XOF("952", "CFA Franc"), XPF("953", "CFP Franc"),
	ZAR("710", "South Africa Rand");

	@JsonCreator
	public static Currency fromCode(String code)
		throws UnsupportedCurrencyException {

		for (Currency currency : Currency.values()) {
			String currencyCode = currency.getCode();

			if (currencyCode.equals(code)) {
				return currency;
			}
		}

		throw new UnsupportedCurrencyException(
			"Currency code " + code + " is not supported!");
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return code;
	}

	protected final String code;
	protected final String description;

	private Currency(String code, String description) {
		this.code = code;
		this.description = description;
	}

}