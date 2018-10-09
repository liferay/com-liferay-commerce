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