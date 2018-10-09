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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zoltán Takács
 */
public abstract class ApioBaseResponse implements ApioResponse {

	public ApioBaseResponse(JsonNode responseJsonNode) throws IOException {
		this.responseJsonNode = responseJsonNode;
	}

	@Override
	public JsonNode getContextJsonNode() {
		return findJsonNode(JSONLDConstants.CONTEXT);
	}

	/**
	 * Parses the responseJsonNode JsonNode of the form
	 *
	 * @return description of the Form or empty string if not present in the
	 *         String
	 */
	public String getDescription() {
		JsonNode jsonNode = responseJsonNode.path(
			HydraConstants.FieldNames.DESCRIPTION);

		return jsonNode.asText();
	}

	@Override
	public JsonNode getIdJsonNode() {
		return findJsonNode(JSONLDConstants.ID);
	}

	/**
	 * Parses the responseJsonNode JsonNode of the form
	 *
	 * @return title of the Form or empty string if not present in the String
	 */
	public String getTitle() {
		JsonNode jsonNode = responseJsonNode.path(
			HydraConstants.FieldNames.TITLE);

		return jsonNode.asText();
	}

	@Override
	public JsonNode getTypeJsonNode() {
		return findJsonNode(JSONLDConstants.TYPE);
	}

	protected static boolean hasValueOf(String value, JsonNode jsonNode) {
		return ApioUtils.hasValueOf(value, jsonNode);
	}

	protected JsonNode findJsonNode(JsonNode resource, String nodeName) {
		JsonNode jsonNode = resource.path(nodeName);

		if (_log.isDebugEnabled()) {
			if (jsonNode.isMissingNode()) {
				_log.debug("Unable to find the \"{}\" node", nodeName);
			}

			if (jsonNode.isArray() && (jsonNode.size() == 0)) {
				_log.debug("The \"{}\" array node is empty", jsonNode);
			}
		}

		return jsonNode;
	}

	protected JsonNode findJsonNode(String nodeName) {
		return findJsonNode(responseJsonNode, nodeName);
	}

	protected final JsonNode responseJsonNode;

	private static final Logger _log = LoggerFactory.getLogger(
		ApioBaseResponse.class);

}