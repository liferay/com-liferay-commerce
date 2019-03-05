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

import com.liferay.commerce.openapi.util.config.ConfigurationFactory;
import com.liferay.commerce.openapi.util.importer.exception.ReaderException;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class OpenApiReaderFactory {

	public static OpenApiReader getOpenApiReader(Properties configuration) {
		String hostName = configuration.getProperty("openapi.host.name");

		if ((hostName != null) && (hostName.length() != 0)) {
			return new URLOpenApiReader(configuration);
		}

		String openApiUrl = configuration.getProperty("openapi.url");

		return new FileOpenApiReader(
			openApiUrl, ConfigurationFactory.getPath());
	}

	public static OpenApiReader getOpenApiReader(String externalReference) {
		return getOpenApiReader(externalReference, null);
	}

	public static OpenApiReader getOpenApiReader(
		String externalReference, String restConfigDirName) {

		if (!isExternalReference(externalReference)) {
			throw new ReaderException(
				"No reader implementation for reference " + externalReference);
		}

		String externalUrl = externalReference;

		if (externalReference.indexOf("#") > 0) {
			externalUrl = externalReference.substring(
				0, externalReference.indexOf("#"));
		}

		_logger.info("Valid external reference to {}", externalUrl);

		if (!externalUrl.startsWith("http")) {
			if (restConfigDirName != null) {
				return new FileOpenApiReader(externalUrl, restConfigDirName);
			}

			return new FileOpenApiReader(
				externalUrl, ConfigurationFactory.getPath());
		}

		Properties configuration = ConfigurationFactory.getConfigurationFor(
			externalReference);

		if (configuration == null) {
			throw new ReaderException("Unable to create reader configuration");
		}

		return new URLOpenApiReader(configuration);
	}

	public static boolean isExternalReference(String reference) {
		if ((reference == null) || reference.startsWith("#")) {
			return false;
		}

		return true;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		OpenApiReaderFactory.class);

}