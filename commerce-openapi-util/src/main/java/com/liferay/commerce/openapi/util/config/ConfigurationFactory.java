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

package com.liferay.commerce.openapi.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class ConfigurationFactory {

	public static List<Properties> getConfigurations(String[] externalProps)
		throws IOException {

		List<File> configFiles = _getConfigFiles(_getConfigRootPath());

		List<Properties> configurations = new ArrayList<>();

		for (File configFile : configFiles) {
			Properties properties = getPropertiesFor(configFile);

			if (externalProps != null) {
				_mergeExternalProps(properties, externalProps);
			}

			configurations.add(properties);
		}

		return configurations;
	}

	public static Properties getPropertiesFor(File file) throws IOException {
		InputStream resourceAsStream = new FileInputStream(file);

		try {
			Properties properties = new Properties();

			properties.load(resourceAsStream);

			_classProperties.put(file.getAbsolutePath(), properties);

			return properties;
		}
		finally {
			resourceAsStream.close();
		}
	}

	private static List<File> _getConfigFiles(String configRootPath) {
		File configDirectory = new File(configRootPath);

		if (!configDirectory.isDirectory()) {
			return Collections.emptyList();
		}

		String[] configFileNames = configDirectory.list(
			new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".config") && name.startsWith("rest")) {
						return true;
					}

					return false;
				}

			});

		List<File> configFiles = new ArrayList<>();

		for (String configFileName : configFileNames) {
			configFiles.add(new File(configRootPath + configFileName));
		}

		return configFiles;
	}

	private static String _getConfigRootPath() {
		Class<?> clazz = ConfigurationFactory.class;

		URL url = clazz.getResource(clazz.getSimpleName() + ".class");

		String path = url.getPath();

		path = path.substring(0, path.lastIndexOf(clazz.getSimpleName()));

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
		ConfigurationFactory.class);

	private static final Map<String, Properties> _classProperties =
		new ConcurrentHashMap<>();
	private static final Pattern _propertyPattern = Pattern.compile(
		"^(.+)=(.+)$");

}