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

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.exception.UnsupportedLanguageException;

/**
 * @author Luca Pellizzon
 */
public enum Language {

	BRETON("br"), BULGARIAN("bg"), CHINESE("zh"), CROATIAN("hr"), CZECH("cs"),
	DANISH("da"), DUTCH("nl"), ENGLISH("en"), ESTONIAN("et"), FINNISH("fi"),
	FRENCH("fr"), GERMAN("de"), GREEK("el"), HINDI("hi"), HUNGARIAN("hu"),
	ITALIAN("it"), JAPANESE("ja"), KOREAN("ko"), LATVIAN("lv"),
	LITHUANIAN("lt"), NORWEGIAN("no"), POLISH("pl"), PORTUGUESE("pt"),
	ROMANIAN("ro"), RUSSIAN("ru"), SLOVAK("sk"), SLOVENE("sl"), SPANISH("es"),
	SWEDISH("sv"), TURKISH("tr"), UKRAINIAN("uk");

	@JsonCreator
	public static Language fromCode(String code)
		throws UnsupportedLanguageException {

		for (Language currency : Language.values()) {
			String currencyCode = currency.getCode();

			if (currencyCode.equals(code)) {
				return currency;
			}
		}

		throw new UnsupportedLanguageException(
			"Language code " + code + " is not supported!");
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code;
	}

	protected final String code;

	private Language(String code) {
		this.code = code;
	}

}