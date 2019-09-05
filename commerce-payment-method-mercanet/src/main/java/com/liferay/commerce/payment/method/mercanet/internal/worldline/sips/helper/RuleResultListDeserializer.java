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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model.RuleResult;

import java.io.IOException;

import java.util.Collections;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class RuleResultListDeserializer
	extends JsonDeserializer<List<RuleResult>> {

	public RuleResultListDeserializer() {
		_objectMapper = new ObjectMapper();
	}

	@Override
	public List<RuleResult> deserialize(
			JsonParser jsonParser, DeserializationContext ctxt)
		throws IOException {

		String text = jsonParser.getText();

		if (text == null) {
			return Collections.emptyList();
		}

		return _objectMapper.readValue(
			text.trim(),
			new TypeReference<List<RuleResult>>() {
			});
	}

	private final ObjectMapper _objectMapper;

}