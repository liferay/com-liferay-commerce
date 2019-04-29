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

import com.liferay.commerce.openapi.util.importer.exception.ImporterException;
import com.liferay.commerce.openapi.util.util.GetterUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.json.web.service.client.internal.JSONWebServiceClientImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author Igor Beslic
 */
public class URLOpenApiReader implements OpenApiReader {

	public URLOpenApiReader(Properties properties) {
		_apiDefinitionURL = properties.getProperty("openapi.url");

		_headers.add(
			new BasicNameValuePair(
				"Authorization",
				properties.getProperty("openapi.authorization.key")));

		_jsonWebServiceClient = new JSONWebServiceClientImpl();

		_jsonWebServiceClient.setHostName(
			properties.getProperty("openapi.host.name"));

		_jsonWebServiceClient.setHostPort(
			GetterUtil.getInteger(properties.getProperty("openapi.host.port")));

		_jsonWebServiceClient.setProtocol(
			properties.getProperty("openapi.protocol"));
	}

	@Override
	public String read() {
		try {
			return _jsonWebServiceClient.doGet(
				_apiDefinitionURL, Collections.emptyList(), _headers);
		}
		catch (JSONWebServiceException jsonwse) {
			throw new ImporterException(
				"Unable to read open API definition", jsonwse);
		}
	}

	private final String _apiDefinitionURL;
	private final List<NameValuePair> _headers = new ArrayList<>();
	private final JSONWebServiceClient _jsonWebServiceClient;

}