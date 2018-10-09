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
import com.liferay.commerce.apio.jsonld.representation.util.constants.JSONLDConstants;
import com.liferay.commerce.apio.jsonld.representation.util.form.Property;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the Json-LD+Hydra response of the Apio Architect for a given
 * ApiDocumentation
 *
 * @author Zoltán Takács
 */
public class ApioApiDocumentation extends ApioBaseResponse {

	public ApioApiDocumentation(JsonNode responseJsonNode) throws IOException {
		super(responseJsonNode);

		_validateApiDocumentation();
	}

	public List<SupportedClass> getSupportedClasses() {
		JsonNode supportedClassesJsonNode = getSupportedClassJsonNode();

		if (!supportedClassesJsonNode.isArray() ||
			(supportedClassesJsonNode.size() == 0)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to fetch the API documentation's supported " +
						"classes");
			}

			return Collections.emptyList();
		}

		List<SupportedClass> supportedClasses = new ArrayList<>();

		for (final JsonNode jsonNode : supportedClassesJsonNode) {
			JsonNode typeJsonNode = jsonNode.path(JSONLDConstants.TYPE);

			if (!hasValueOf(HydraConstants.FieldTypes.CLASS, typeJsonNode)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Skipping unexpected type: " + typeJsonNode.toString());
				}

				continue;
			}

			JsonNode titleJsonNode = jsonNode.path(
				HydraConstants.FieldNames.TITLE);
			JsonNode supportedPropertyJsonNode = jsonNode.path(
				HydraConstants.FieldNames.SUPPORTED_PROPERTY);

			try {
				List<Property> supportedProperties =
					ApioUtils.getSupportedProperties(supportedPropertyJsonNode);

				SupportedClass supportedClass = new SupportedClass(
					titleJsonNode.asText(), supportedProperties);

				supportedClasses.add(supportedClass);
			}
			catch (IOException ioe) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unsupported class: " + ioe.getMessage(), ioe);
				}
			}
		}

		return Collections.unmodifiableList(supportedClasses);
	}

	public JsonNode getSupportedClassJsonNode() {
		return findJsonNode(HydraConstants.FieldNames.SUPPORTED_CLASS);
	}

	public class SupportedClass {

		public SupportedClass(String name, List<Property> supportedProperties)
			throws IOException {

			try {
				_name = Objects.requireNonNull(
					name, "Name of the class must not be null");
				_supportedProperties = Objects.requireNonNull(
					supportedProperties,
					"Supported properties of a class must not be null");
			}
			catch (NullPointerException npe) {
				throw new IOException(npe);
			}
		}

		public String getName() {
			return _name;
		}

		public List<Property> getSupportedProperties() {
			return _supportedProperties;
		}

		private final String _name;
		private final List<Property> _supportedProperties;

	}

	private void _validateApiDocumentation() throws IOException {
		if (!hasValueOf(
				HydraConstants.FieldTypes.API_DOCUMENTATION,
				getTypeJsonNode())) {

			throw new IOException(
				"The type of the given resource is not an instance of " +
					"ApiDocumentation");
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		ApioApiDocumentation.class);

}