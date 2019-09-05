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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model.ResponseData;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Luca Pellizzon
 */
public class ResponseDataDeserializer extends JsonDeserializer<ResponseData> {

	public ResponseDataDeserializer() {
		_objectMapper = new ObjectMapper();
	}

	@Override
	public ResponseData deserialize(
			JsonParser jsonParser,
			DeserializationContext deserializationContext)
		throws IOException {

		String text = jsonParser.getText();

		if (text == null) {
			return null;
		}

		final String value = text.trim();

		Stream<String> stream = Arrays.stream(value.split("\\|"));

		Stream<String[]> map = stream.map(element -> element.split("=", 2));

		Stream<String[]> filter = map.filter(
			pair -> _isNotNullOrEmpty(pair[1]));

		final Map<String, String> mapped = filter.collect(
			Collectors.toMap(pair -> pair[0], pair -> pair[1]));

		SimpleModule simpleModule = new JavaTimeModule().addDeserializer(
			LocalDateTime.class,
			new LocalDateTimeDeserializer(
				DateTimeFormatter.ISO_OFFSET_DATE_TIME));

		SimpleModule deserializer = simpleModule.addDeserializer(
			LocalDate.class,
			new LocalDateDeserializer(DateTimeFormatter.BASIC_ISO_DATE));

		ObjectMapper registerModule = _objectMapper.registerModule(
			deserializer.addDeserializer(
				YearMonth.class,
				new YearMonthDeserializer(
					DateTimeFormatter.ofPattern("yyyyMM"))));

		return registerModule.convertValue(mapped, ResponseData.class);
	}

	private boolean _isNotNullOrEmpty(final CharSequence cs) {
		if (!StringUtils.isBlank(cs) && !StringUtils.equals(cs, "null")) {
			return true;
		}

		return false;
	}

	private final ObjectMapper _objectMapper;

}