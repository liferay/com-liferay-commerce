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

package com.liferay.commerce.openapi.util.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public abstract class BaseSourceGenerator {

	protected void checkModuleOutputPaths(String moduleOutputPath) {
		_checkModuleOutputPath(moduleOutputPath + "/");
		_checkModuleOutputPath(moduleOutputPath + "/src/main/java");
		_checkModuleOutputPath(moduleOutputPath + "/src/main/resources");
	}

	protected boolean exists(String filePath) {
		File file = new File(filePath);

		return file.exists();
	}

	protected String getClassSourcePath(
		String moduleOutputPath, String classSourceName, String classPackage) {

		StringBuilder sb = new StringBuilder();

		sb.append(moduleOutputPath);
		sb.append("/src/main/java/");
		sb.append(classPackage.replace(".", "/"));
		sb.append("/");

		String packagePath = sb.toString();

		_checkModuleOutputPath(packagePath);

		return packagePath + classSourceName;
	}

	protected String getTemplate(String templateName) throws IOException {
		Class<?> clazz = getClass();

		BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				clazz.getResourceAsStream(templateName), "UTF-8"));

		String line = null;

		StringBuilder stringBuilder = new StringBuilder();

		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.setLength(stringBuilder.length() - ls.length());

			return stringBuilder.toString();
		}
		finally {
			reader.close();
		}
	}

	protected void writeSource(String content, String fileName)
		throws IOException {

		File file = new File(fileName);

		BufferedWriter bufferedWriter = Files.newBufferedWriter(
			file.toPath(), StandardCharsets.UTF_8);

		try {
			bufferedWriter.write(content.toCharArray());
		}
		finally {
			bufferedWriter.flush();

			bufferedWriter.close();
		}
	}

	private void _checkModuleOutputPath(String moduleOutputPath) {
		File file = new File(moduleOutputPath);

		if (file.mkdirs()) {
			_logger.info("Created directory {}", file.getAbsolutePath());
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BaseSourceGenerator.class);

}