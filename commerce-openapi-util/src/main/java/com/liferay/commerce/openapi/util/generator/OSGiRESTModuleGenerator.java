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

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.Definition;
import com.liferay.commerce.openapi.util.Path;
import com.liferay.commerce.openapi.util.PropertiesFactory;
import com.liferay.commerce.openapi.util.generator.exception.GeneratorException;
import com.liferay.commerce.openapi.util.importer.OpenAPIImporter;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class OSGiRESTModuleGenerator extends SourceGenerator {

	public static void main(String[] args) {
		try {
			OSGiRESTModuleGenerator osgiRESTModuleGenerator =
				new OSGiRESTModuleGenerator(
					PropertiesFactory.getPropertiesFor(
						OSGiRESTModuleGenerator.class, args));

			osgiRESTModuleGenerator.generate();

			System.exit(0);
		}
		catch (Exception e) {
			_logger.error("Unable to generate module source", e);

			e.printStackTrace();

			System.exit(-1);
		}
	}

	public OSGiRESTModuleGenerator() throws IOException {
		this(PropertiesFactory.getPropertiesFor(OSGiRESTModuleGenerator.class));
	}

	public OSGiRESTModuleGenerator(Properties properties) {
		_apiPackagePath = properties.getProperty("osgi.module.api.package");
		_applicationBase = properties.getProperty(
			"osgi.module.application.base");
		_applicationClassName = properties.getProperty(
			"osgi.module.application.class.name");
		_applicationName = properties.getProperty(
			"osgi.module.application.name");
		_author = properties.getProperty("osgi.module.author");

		if ("allowed".equals(
				properties.getProperty(
					"osgi.module.application.security.basic"))) {

			_basicSecurityAllowed = true;
		}
		else {
			_basicSecurityAllowed = false;
		}

		_bundleName = properties.getProperty("osgi.module.bundle.name");
		_bundleSynbolicName = properties.getProperty(
			"osgi.module.bundle.symbolic.name");
		_bundleVersion = properties.getProperty("osgi.module.bundle.version");
		_jaxRSJSONPackagePath = properties.getProperty(
			"osgi.module.jaxrs.json.package");
		_modelPackagePath = properties.getProperty("osgi.module.model.package");

		String moduleRootPath = properties.getProperty("osgi.module.root.path");

		if (StringUtils.isEmpty(moduleRootPath)) {
			_logger.error("You must specify the generated module root path");

			System.exit(-1);
		}

		_moduleOutputPath = String.format(
			"%s/%s", moduleRootPath,
			properties.getProperty("osgi.module.name"));

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.implementation"))) {

			_overwriteImplementation = true;
		}
		else {
			_overwriteImplementation = false;
		}

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.bnd"))) {

			_overwriteBND = true;
		}
		else {
			_overwriteBND = false;
		}

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.gradle"))) {

			_overwriteBuildGradle = true;
		}
		else {
			_overwriteBuildGradle = false;
		}

		_resourceInterfacePackagePath = properties.getProperty(
			"osgi.module.resource.interface.package");
		_resourcePackagePath = properties.getProperty(
			"osgi.module.resource.package");
	}

	public void generate() throws IOException {
		_generateModule();

		_logger.info("Module generated at location {}", _moduleOutputPath);
	}

	private void _generateModule() throws IOException {
		OpenAPIImporter openAPIImporter = new OpenAPIImporter();

		Definition definition = openAPIImporter.getDefinition();

		try {
			checkModuleOutputPaths(_moduleOutputPath);

			_writeBNDSource();

			_writeGradleSource();

			Set<String> referencedModels = new HashSet<>();

			for (Path path : definition.getPaths()) {
				referencedModels.addAll(path.getReferencedModels());

				_writeResourceInterfaceSource(definition.getVersion(), path);

				_writeResourceImplementationSource(
					definition.getVersion(), path);
			}

			Set<ComponentDefinition> componentDefinitions =
				definition.getComponentDefinitions();

			Stream<ComponentDefinition> stream = componentDefinitions.stream();

			stream.filter(
				componentDefinition -> componentDefinition.isObject()
			).forEach(
				componentDefinition -> {
					DTOGenerator dtoGenerator = new DTOGenerator(
						_author, _moduleOutputPath, _modelPackagePath,
						componentDefinition);

					try {
						dtoGenerator.writeModelSource();
					}
					catch (IOException ioe) {
						_logger.error(
							"Unable to write DTO source for {}",
							componentDefinition, ioe);
					}
				}
			);

			_writeApplicationSource();

			_writeJsonMessageBodyReaderSource();

			_writeJsonMessageBodyWriterSource();
		}
		catch (Exception e) {
			throw new GeneratorException("Unable to generate module", e);
		}
	}

	private void _writeApplicationSource() throws IOException {
		String osgiApplicationComponent = getTemplate(
			_TEMPLATE_FILE_APPLICATION);

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${PACKAGE}", _apiPackagePath);

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${AUTHOR}", _author);

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${APPLICATION_BASE}", _applicationBase);

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${APPLICATION_NAME}", _applicationName);

		StringBuilder sb = new StringBuilder();

		if (_basicSecurityAllowed) {
			sb.append(getTemplate("basic.authentication.tpl"));
			sb.append(",");
		}

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${BASIC_AUTHENTICATION}", sb.toString());

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${APPLICATION_CLASS}", _applicationClassName);

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, _applicationClassName + ".java",
			_apiPackagePath);

		writeSource(osgiApplicationComponent, componentSourcePath);
	}

	private void _writeBNDSource() throws IOException {
		String bndSourcePath =
			_moduleOutputPath + "/" + _TEMPLATE_FILE_BND.replace(".tpl", "");

		if (!_overwriteBND && exists(bndSourcePath)) {
			_logger.warn(
				"BND source file {} is not generated. Configure overwrite " +
					"mode in config file.",
				bndSourcePath);

			return;
		}

		String bndTpl = getTemplate(_TEMPLATE_FILE_BND);

		bndTpl = bndTpl.replace("${BUNDLE_NAME}", _bundleName);
		bndTpl = bndTpl.replace("${BUNDLE_SYMBOLIC_NAME}", _bundleSynbolicName);
		bndTpl = bndTpl.replace("${BUNDLE_VERSION}", _bundleVersion);

		writeSource(bndTpl, bndSourcePath);
	}

	private void _writeGradleSource() throws IOException {
		String gradleSourcePath =
			_moduleOutputPath + "/" + _TEMPLATE_FILE_GRADLE.replace(".tpl", "");

		if (!_overwriteBuildGradle && exists(gradleSourcePath)) {
			_logger.warn(
				"Gradle build script file {} is not generated. Configure" +
					"overwrite mode in config file.",
				gradleSourcePath);

			return;
		}

		String gradleTpl = getTemplate(_TEMPLATE_FILE_GRADLE);

		writeSource(gradleTpl, gradleSourcePath);
	}

	private void _writeJsonMessageBodyReaderSource() throws IOException {
		String jsonMessageBodyReaderTpl = getTemplate(
			_TEMPLATE_FILE_JSON_MESSAGE_BODY_READER);

		String jsonMessageBodyReaderSourcePath = getClassSourcePath(
			_moduleOutputPath,
			_TEMPLATE_FILE_JSON_MESSAGE_BODY_READER.replace(".tpl", ""),
			_jaxRSJSONPackagePath);

		jsonMessageBodyReaderTpl = jsonMessageBodyReaderTpl.replace(
			"${PACKAGE}", _jaxRSJSONPackagePath);

		jsonMessageBodyReaderTpl = jsonMessageBodyReaderTpl.replace(
			"${AUTHOR}", _author);

		jsonMessageBodyReaderTpl = jsonMessageBodyReaderTpl.replace(
			"${APPLICATION_NAME}", _applicationName);

		writeSource(jsonMessageBodyReaderTpl, jsonMessageBodyReaderSourcePath);
	}

	private void _writeJsonMessageBodyWriterSource() throws IOException {
		String jsonMessageBodyWriterTpl = getTemplate(
			_TEMPLATE_FILE_JSON_MESSAGE_BODY_WRITER);

		jsonMessageBodyWriterTpl = jsonMessageBodyWriterTpl.replace(
			"${PACKAGE}", _jaxRSJSONPackagePath);

		jsonMessageBodyWriterTpl = jsonMessageBodyWriterTpl.replace(
			"${AUTHOR}", _author);

		jsonMessageBodyWriterTpl = jsonMessageBodyWriterTpl.replace(
			"${APPLICATION_NAME}", _applicationName);

		String jsonMessageBodyWriterSourcePath = getClassSourcePath(
			_moduleOutputPath,
			_TEMPLATE_FILE_JSON_MESSAGE_BODY_WRITER.replace(".tpl", ""),
			_jaxRSJSONPackagePath);

		writeSource(jsonMessageBodyWriterTpl, jsonMessageBodyWriterSourcePath);
	}

	private void _writeResourceImplementationSource(String version, Path path)
		throws IOException {

		String resourceImplementationClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "ResourceImpl");

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, resourceImplementationClassName + ".java",
			_resourcePackagePath);

		if (!_overwriteImplementation && exists(componentSourcePath)) {
			_logger.warn(
				"Resource implementation source file {} is not generated. " +
					"Configure overwrite mode in config file.",
				componentSourcePath);

			return;
		}

		String osgiResourceComponent = getTemplate(
			_TEMPLATE_FILE_RESOURCE_IMPLEMENTATION);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PACKAGE}", _resourcePackagePath);

		StringBuilder sb = new StringBuilder();

		sb.append(
			_resourceGenerator.toModelImportStatements(
				_modelPackagePath, path.getReferencedModels()));

		sb.append("import ");
		sb.append(_resourceInterfacePackagePath);
		sb.append(".");
		sb.append(StringUtils.upperCaseFirstChar(path.getName()));
		sb.append("Resource;");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${IMPORT_STATEMENTS}", sb.toString());

		osgiResourceComponent = osgiResourceComponent.replace(
			"${API_VERSION}", version);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${AUTHOR}", _author);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${APPLICATION_NAME}", _applicationName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS_JAVAX}",
			_resourceGenerator.toJavaxImports(path.getMethods()));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_IMPLEMENTATION_CLASS}",
			resourceImplementationClassName);

		String resourceInterfaceClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "Resource");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_INTERFACE_CLASS}", resourceInterfaceClassName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PATH}", path.getName());

		osgiResourceComponent = osgiResourceComponent.replace(
			"${METHODS}",
			_resourceGenerator.toResourceImplementationMethods(
				path.getMethods()));

		writeSource(osgiResourceComponent, componentSourcePath);
	}

	private void _writeResourceInterfaceSource(String version, Path path)
		throws IOException {

		String osgiResourceComponent = getTemplate(
			_TEMPLATE_FILE_RESOURCE_INTERFACE);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PACKAGE}", _resourceInterfacePackagePath);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS}",
			_resourceGenerator.toModelImportStatements(
				_modelPackagePath, path.getReferencedModels()));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${API_VERSION}", version);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${AUTHOR}", _author);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_IMPORT_STATEMENTS_JAVAX}",
			_resourceGenerator.toJavaxImports(path.getMethods()));

		osgiResourceComponent = osgiResourceComponent.replace(
			"${PATH}", path.getName());

		String resourceInterfaceClassName = StringUtils.upperCaseFirstChar(
			path.getName() + "Resource");

		osgiResourceComponent = osgiResourceComponent.replace(
			"${MODEL_RESOURCE_INTERFACE_CLASS}", resourceInterfaceClassName);

		osgiResourceComponent = osgiResourceComponent.replace(
			"${METHODS}",
			_resourceGenerator.toResourceInterfaceMethods(path.getMethods()));

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, resourceInterfaceClassName + ".java",
			_resourceInterfacePackagePath);

		writeSource(osgiResourceComponent, componentSourcePath);
	}

	private static final String _TEMPLATE_FILE_APPLICATION =
		"Application.java.tpl";

	private static final String _TEMPLATE_FILE_BND = "bnd.bnd.tpl";

	private static final String _TEMPLATE_FILE_GRADLE = "build.gradle.tpl";

	private static final String _TEMPLATE_FILE_JSON_MESSAGE_BODY_READER =
		"JsonMessageBodyReader.java.tpl";

	private static final String _TEMPLATE_FILE_JSON_MESSAGE_BODY_WRITER =
		"JsonMessageBodyWriter.java.tpl";

	private static final String _TEMPLATE_FILE_RESOURCE_IMPLEMENTATION =
		"ResourceImpl.java.tpl";

	private static final String _TEMPLATE_FILE_RESOURCE_INTERFACE =
		"Resource.java.tpl";

	private static final Logger _logger = LoggerFactory.getLogger(
		OSGiRESTModuleGenerator.class);

	private final String _apiPackagePath;
	private final String _applicationBase;
	private final String _applicationClassName;
	private final String _applicationName;
	private final String _author;
	private final boolean _basicSecurityAllowed;
	private final String _bundleName;
	private final String _bundleSynbolicName;
	private final String _bundleVersion;
	private final String _jaxRSJSONPackagePath;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;
	private final boolean _overwriteBND;
	private final boolean _overwriteBuildGradle;
	private final boolean _overwriteImplementation;
	private final ResourceGenerator _resourceGenerator =
		new ResourceGenerator();
	private final String _resourceInterfacePackagePath;
	private final String _resourcePackagePath;

}