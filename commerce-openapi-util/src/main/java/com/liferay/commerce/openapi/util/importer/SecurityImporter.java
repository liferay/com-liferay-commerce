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

package com.liferay.commerce.openapi.util.importer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.liferay.commerce.openapi.util.Security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class SecurityImporter {

	public Security getSecurity(ArrayNode securityMethodsArrayNode) {
		if (securityMethodsArrayNode == null) {
			return null;
		}

		for (int i = 0; i < securityMethodsArrayNode.size(); i++) {
			JsonNode securityMethodJSONNode = securityMethodsArrayNode.get(i);

			ArrayNode oauth2ScopesArrayNode = _getSecurityMethodArrayNode(
				"OAuth2", securityMethodJSONNode);

			if (oauth2ScopesArrayNode != null) {
				Security security = new Security();

				List<String> oauth2Scopes = new ArrayList<>();

				for (int j = 0; j < oauth2ScopesArrayNode.size(); j++) {
					JsonNode jsonNode = oauth2ScopesArrayNode.get(j);

					oauth2Scopes.add(jsonNode.textValue());
				}

				security.setOAuth2Scopes(oauth2Scopes);

				return security;
			}
		}

		return null;
	}

	private ArrayNode _getSecurityMethodArrayNode(
		String securityMethodName, JsonNode securityMethodJSONNode) {

		Iterator<String> fieldNamesIterator =
			securityMethodJSONNode.fieldNames();

		while (fieldNamesIterator.hasNext()) {
			String fieldName = fieldNamesIterator.next();

			if (!fieldName.equals("OAuth2")) {
				continue;
			}

			return (ArrayNode)securityMethodJSONNode.get(securityMethodName);
		}

		return null;
	}

}