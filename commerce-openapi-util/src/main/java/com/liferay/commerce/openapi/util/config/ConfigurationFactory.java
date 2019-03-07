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

import com.liferay.commerce.openapi.util.config.exception.ConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ConfigurationFactory {

	public static Properties getConfigurationFor(String externalReference) {
		try {
			for (Properties properties : getConfigurations()) {
				if (externalReference.contains(
						properties.getProperty("openapi.url"))) {

					return _clone(properties);
				}
			}
		}
		catch (IOException ioe) {
			throw new ConfigurationException(
				"Unable to get configuration for external reference " +
					externalReference,
				ioe);
		}

		throw new ConfigurationException(
			"No configuration matches external reference " + externalReference);
	}

	public static List<Properties> getConfigurations() throws IOException {
		if (_configRootPath == null) {
			throw new IllegalStateException("Factory is not initialized");
		}

		if (!_configurations.isEmpty()) {
			return _cloneAll(_configurations);
		}

		List<File> configFiles = _getConfigFiles();

		for (File configFile : configFiles) {
			_configurations.add(_getConfiguration(configFile));
		}

		return _cloneAll(_configurations);
	}

	public static List<Properties> getConfigurations(String[] externalProps)
		throws IOException {

		if (!_configurations.isEmpty()) {
			return _cloneAll(_configurations);
		}

		List<Properties> configurations = getConfigurations();

		for (Properties configuration : configurations) {
			if (externalProps != null) {
				_mergeExternalProps(configuration, externalProps);
			}
		}

		return _cloneAll(_configurations);
	}

	public static String getPath() {
		return _configRootPath;
	}

	public static synchronized void initialize() {
		if (_configRootPath != null) {
			throw new IllegalStateException("Factory is already initialized");
		}

		Class<?> clazz = ConfigurationFactory.class;

		initialize(clazz);
	}

	public static synchronized void initialize(Class clazz) {
		if (_configRootPath != null) {
			throw new IllegalStateException("Factory is already intialized");
		}

		URL url = clazz.getResource(clazz.getSimpleName() + ".class");

		String path = url.getPath();

		path = path.substring(0, path.lastIndexOf(clazz.getSimpleName()));

		_configRootPath = path;
	}

	private static Properties _clone(Properties properties) {
		Properties clonedProperties = new Properties();

		clonedProperties.putAll(properties);

		return clonedProperties;
	}

	private static List<Properties> _cloneAll(
		Collection<? extends Properties> propertiesCollection) {

		List<Properties> clonedProperties = new ArrayList<>();

		for (Properties properties : propertiesCollection) {
			clonedProperties.add(_clone(properties));
		}

		return clonedProperties;
	}

	private static List<File> _getConfigFiles() {
		File configDirectory = new File(_configRootPath);

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
			configFiles.add(new File(_configRootPath + configFileName));
		}

		return configFiles;
	}

	private static Properties _getConfiguration(File file) throws IOException {
		InputStream resourceAsStream = new FileInputStream(file);

		try {
			Properties properties = new Properties();

			properties.load(resourceAsStream);

			return properties;
		}
		finally {
			resourceAsStream.close();
		}
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

	private static String _configRootPath;
	private static final List<Properties> _configurations = new ArrayList<>();
	private static final Pattern _propertyPattern = Pattern.compile(
		"^(.+)=(.+)$");

}