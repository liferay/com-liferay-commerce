/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.apio.jsonld.representation.util;

import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.commerce.apio.jsonld.representation.util.constants.HydraConstants;
import com.liferay.commerce.apio.jsonld.representation.util.form.Property;

import java.io.IOException;

import java.util.List;

/**
 * Represent the Apio Architect Forms
 *
 * @author Zoltán Takács
 */
public class ApioForm extends ApioBaseResponse {

	public ApioForm(JsonNode responseJsonNode) throws IOException {
		super(responseJsonNode);

		_validateForm();
	}

	public List<Property> getSupportedProperties() {
		return ApioUtils.getSupportedProperties(
			getSupportedPropertiesJsonNode());
	}

	public JsonNode getSupportedPropertiesJsonNode() {
		return findJsonNode(HydraConstants.FieldNames.SUPPORTED_PROPERTY);
	}

	private void _validateForm() throws IOException {
		if (!hasValueOf(HydraConstants.FieldTypes.CLASS, getTypeJsonNode())) {
			throw new IOException("The given resource is not a from");
		}
	}

}