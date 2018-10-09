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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Json-LD+Hydra response of the Apio Architect for a given
 * RootEndpoint
 *
 * @author Zoltán Takács
 */
public class ApioEntryPoint extends ApioBaseResponse {

	public ApioEntryPoint(JsonNode responseJsonNode) throws IOException {
		super(responseJsonNode);

		_validateEntryPoint();
	}

	public JsonNode getCollectionJsonNode() {
		return findJsonNode(HydraConstants.FieldNames.COLLECTION);
	}

	/**
	 * Returns the exposed entry points in a Map. The key is the ID of a given
	 * resource collection and the value its managed type
	 *
	 * @return Map<String, String> Resource ID / Managed type, empty otherwise
	 */
	public Map<String, String> getRootEndpointMap() {
		JsonNode collectionArrayJsonNode = getCollectionJsonNode();

		if (!collectionArrayJsonNode.isArray() ||
			(collectionArrayJsonNode.size() == 0)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to fetch the resources from the entry point");
			}

			return Collections.emptyMap();
		}

		Map<String, String> rootEndpointMap = new TreeMap<>();

		for (final JsonNode collectionJsonNode : collectionArrayJsonNode) {
			JsonNode idJsonNode = collectionJsonNode.path(JSONLDConstants.ID);
			String managedType = ApioUtils.getManagedType(collectionJsonNode);

			rootEndpointMap.put(idJsonNode.asText(), managedType);
		}

		return Collections.unmodifiableMap(rootEndpointMap);
	}

	public Set<String> getRootEndpointURLs() {
		return getRootEndpointMap().keySet();
	}

	private void _validateEntryPoint() throws IOException {
		if (!hasValueOf(
				HydraConstants.FieldTypes.ENTRY_POINT, getTypeJsonNode())) {

			throw new IOException("The given resource is not an entry point");
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		ApioEntryPoint.class);

}