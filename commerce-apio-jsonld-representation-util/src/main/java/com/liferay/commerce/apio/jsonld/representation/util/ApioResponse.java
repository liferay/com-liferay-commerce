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
import com.fasterxml.jackson.databind.node.MissingNode;

import com.liferay.commerce.apio.jsonld.representation.util.operation.Operation;

import java.util.Collections;
import java.util.List;

/**
 * @author Zoltán Takács
 */
public interface ApioResponse {

	public JsonNode getContextJsonNode();

	public String getDescription();

	public JsonNode getIdJsonNode();

	public default JsonNode getOperationJsonNode() {
		return MissingNode.getInstance();
	}

	public default List<Operation> getResourceOperations() {
		return Collections.emptyList();
	}

	public String getTitle();

	public JsonNode getTypeJsonNode();

}