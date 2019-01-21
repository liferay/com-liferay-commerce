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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class BuildGradleGenerator extends BaseSourceGenerator {

	public BuildGradleGenerator(
		boolean includeOpenApiCoreDependencies, String moduleOutputPath,
		boolean overwriteBuildGradle) {

		_includeOpenApiCoreDependencies = includeOpenApiCoreDependencies;
		_moduleOutputPath = moduleOutputPath;
		_overwriteBuildGradle = overwriteBuildGradle;
	}

	public void writeSource() throws IOException {
		String gradleSourcePath =
			_moduleOutputPath + "/" + _TEMPLATE_FILE_GRADLE.replace(".tpl", "");

		if (!_overwriteBuildGradle && exists(gradleSourcePath)) {
			_logger.warn(
				"Gradle build script file {} is not generated. Configure" +
					"overwrite mode in config file.",
				gradleSourcePath);

			return;
		}

		String buildGradleTemplate = getTemplate(gradleSourcePath);

		StringBuilder compileOnlyDependenciesSb = new StringBuilder();
		StringBuilder runtimeDependenciesSb = new StringBuilder();

		if (_includeOpenApiCoreDependencies) {
			compileOnlyDependenciesSb.append(_COMPILE_ONLY_DEPENDENCY_CORE);
			compileOnlyDependenciesSb.append("\n");

			runtimeDependenciesSb.append(_RUNTIME_DEPENDENCY_CORE);
			runtimeDependenciesSb.append("\n");
		}

		buildGradleTemplate = buildGradleTemplate.replace(
			"${COMPILE_ONLY_DEPENDENCIES}",
			compileOnlyDependenciesSb.toString());
		buildGradleTemplate = buildGradleTemplate.replace(
			"${RUNTIME_DEPENDENCIES}", runtimeDependenciesSb.toString());

		writeSource(buildGradleTemplate, gradleSourcePath);

		_logger.debug(
			"Generated gradle build script file {}", gradleSourcePath);
	}

	private static final String _COMPILE_ONLY_DEPENDENCY_CORE =
		"compileOnly project(\":apps:commerce:commerce-openapi-core-api\")";

	private static final String _RUNTIME_DEPENDENCY_CORE =
		"runtime project(\":apps:commerce:commerce-openapi-core-impl\")";

	private static final String _TEMPLATE_FILE_GRADLE = "build.gradle.tpl";

	private static final Logger _logger = LoggerFactory.getLogger(
		BuildGradleGenerator.class);

	private final boolean _includeOpenApiCoreDependencies;
	private final String _moduleOutputPath;
	private final boolean _overwriteBuildGradle;

}