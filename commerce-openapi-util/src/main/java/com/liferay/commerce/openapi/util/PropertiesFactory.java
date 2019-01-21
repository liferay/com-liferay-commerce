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

package com.liferay.commerce.openapi.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class PropertiesFactory {

	public static Properties getPropertiesFor(Class clazz) throws IOException {
		String configFileName = clazz.getName() + ".config";

		if (_classProperties.containsKey(configFileName)) {
			return _classProperties.get(configFileName);
		}

		String configFileNamePath = _getConfigPath(clazz) + configFileName;

		InputStream resourceAsStream = new FileInputStream(configFileNamePath);

		try {
			Properties properties = new Properties();

			properties.load(resourceAsStream);

			_classProperties.put(configFileName, properties);

			return properties;
		}
		finally {
			resourceAsStream.close();
		}
	}

	public static Properties getPropertiesFor(
			Class clazz, String[] externalProps)
		throws IOException {

		Properties properties = getPropertiesFor(clazz);

		if (externalProps != null) {
			_mergeExternalProps(properties, externalProps);
		}

		return properties;
	}

	private static String _getConfigPath(Class clazz) {
		URL url = clazz.getResource(clazz.getSimpleName() + ".class");

		String path = url.getPath();

		String clazzName = clazz.getName();

		String clazzRootPath = clazzName.substring(
			0, clazzName.indexOf(clazz.getSimpleName()));

		clazzRootPath = clazzRootPath.replace(".", "/");

		if (path.contains(clazzRootPath)) {
			path = path.substring(0, path.lastIndexOf(clazzRootPath));
		}

		return path;
	}

	private static void _mergeExternalProps(
		Properties properties, String[] externalProps) {

		for (String property : externalProps) {
			Matcher matcher = _propertyPattern.matcher(property);

			if (matcher.find()) {
				properties.put(matcher.group(1), matcher.group(2));

				_logger.trace(
					"Merged property {} = {}", matcher.group(1),
					matcher.group(2));
			}
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		PropertiesFactory.class);

	private static final Map<String, Properties> _classProperties =
		new ConcurrentHashMap<>();
	private static final Pattern _propertyPattern = Pattern.compile(
		"^(.+)=(.+)$");

}