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
public class ClassPropertiesFactory {

	public static Properties getPropertiesFor(Class clazz) throws IOException {
		String configFileName = clazz.getName() + ".config";

		if (_classProperties.containsKey(configFileName)) {
			return _classProperties.get(configFileName);
		}

		String configFileNamePath = _getConfigPath(clazz) + configFileName;

		InputStream resourceAsStream = new FileInputStream(configFileNamePath);

		Properties properties = new Properties();

		properties.load(resourceAsStream);

		_classProperties.put(configFileName, properties);

		return properties;
	}

	public static Properties getPropertiesFor(Class clazz, String[] mergeArgs)
		throws IOException {

		Properties properties = getPropertiesFor(clazz);

		if (mergeArgs != null) {
			_mergeArguments(properties, mergeArgs);
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

	private static void _mergeArguments(
		Properties properties, String[] mergeArgs) {

		for (String argument : mergeArgs) {
			Matcher matcher = _propertyPattern.matcher(argument);

			if (matcher.find()) {
				properties.put(matcher.group(1), matcher.group(2));

				_logger.trace(
					"Merged argument {} = {}", matcher.group(1),
					matcher.group(2));
			}
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ClassPropertiesFactory.class);

	private static final Map<String, Properties> _classProperties =
		new ConcurrentHashMap<>();
	private static final Pattern _propertyPattern = Pattern.compile(
		"^(.+)=(.+)$");

}