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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

	private static final Map<String, Properties> _classProperties =
		new HashMap<>();

}