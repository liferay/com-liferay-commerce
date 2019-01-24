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
public class OSGiRESTModuleGenerator extends BaseSourceGenerator {

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

		if ("true".equals(
				properties.getProperty(
					"osgi.module.application.security.basic.auth.allowed"))) {

			_basicSecurityAllowed = true;
		}
		else {
			_basicSecurityAllowed = false;
		}

		if ("true".equals(
				properties.getProperty(
					"osgi.module.application.security.oauth2.auth.allowed"))) {

			_oauth2SecurityAllowed = true;
		}
		else {
			_oauth2SecurityAllowed = false;
		}

		if ("true".equals(
				properties.getProperty(
					"osgi.module.application.security.guests.allowed"))) {

			_guestsAllowed = true;
		}
		else {
			_guestsAllowed = false;
		}

		_bundleName = properties.getProperty("osgi.module.bundle.name");
		_bundleSynbolicName = properties.getProperty(
			"osgi.module.bundle.symbolic.name");
		_bundleVersion = properties.getProperty("osgi.module.bundle.version");
		_modelPackagePath = properties.getProperty("osgi.module.model.package");

		String moduleRootPath = properties.getProperty("osgi.module.root.path");

		if (StringUtils.isEmpty(moduleRootPath)) {
			throw new GeneratorException(
				"The generated module root path must be specified");
		}

		_moduleOutputPath = String.format(
			"%s/%s", moduleRootPath,
			properties.getProperty("osgi.module.name"));

		boolean overwriteImplementation = false;

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.implementation"))) {

			overwriteImplementation = true;
		}

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.bnd"))) {

			_overwriteBND = true;
		}
		else {
			_overwriteBND = false;
		}

		_jsonMessageBodyGenerator = new JsonMessageBodyGenerator(
			_author, _applicationName,
			properties.getProperty("osgi.module.jaxrs.json.package"),
			_moduleOutputPath);

		_resourceGenerator = new ResourceGenerator(
			_applicationName, _author,
			_bundleSynbolicName + ".internal.context", _moduleOutputPath,
			_modelPackagePath, overwriteImplementation,
			properties.getProperty("osgi.module.resource.interface.package"),
			properties.getProperty("osgi.module.resource.package"));
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

			_writeGradleSource(definition);

			Set<ComponentDefinition> componentDefinitions =
				definition.getComponentDefinitions();

			Set<String> referencedModels = new HashSet<>();

			for (Path path : definition.getPaths()) {
				referencedModels.addAll(path.getReferencedModels());

				_resourceGenerator.writeResourceSources(
					definition.getVersion(), path, componentDefinitions);
			}

			Stream<ComponentDefinition> stream = componentDefinitions.stream();

			stream.filter(
				componentDefinition -> componentDefinition.isObject()
			).forEach(
				componentDefinition -> {
					DTOGenerator dtoGenerator = new DTOGenerator(
						_author, _moduleOutputPath, _modelPackagePath,
						componentDefinition);

					try {
						dtoGenerator.writeClassSource();
					}
					catch (IOException ioe) {
						_logger.error(
							"Unable to write DTO source for {}",
							componentDefinition, ioe);
					}
				}
			);

			_writeApplicationSource();

			CollectionDTOGenerator collectionDTOGenerator =
				new CollectionDTOGenerator(
					_author, _moduleOutputPath, _modelPackagePath,
					referencedModels);

			collectionDTOGenerator.writeCollectionDTOSource();

			Properties properties = PropertiesFactory.getPropertiesFor(
				OSGiRESTModuleGenerator.class);

			if ("true".equals(
					properties.getProperty(
						"osgi.module.generator.embed.message.body." +
							"converters"))) {

				_jsonMessageBodyGenerator.writeJsonMessageBodySources();
			}
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
			sb.append(getTemplate("auth/basic.verifier.tpl"));
		}

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${BASIC_AUTH_VERIFIER}", sb.toString());

		sb = new StringBuilder();

		if (_oauth2SecurityAllowed) {
			sb.append(getTemplate("auth/oauth2.verifier.tpl"));
		}
		else {
			sb.append("\"liferay.oauth2=false\"");
		}

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${OAUTH2_AUTH_VERIFIER}", sb.toString());

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${PORTAL_SESSION_AUTH_VERIFIER}",
			getTemplate("auth/portal.session.verifier.tpl"));

		sb = new StringBuilder();

		if (_guestsAllowed) {
			sb.append(getTemplate("auth/guest.verifier.tpl"));
		}

		osgiApplicationComponent = osgiApplicationComponent.replace(
			"${GUEST_ALLOWED}", sb.toString());

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

	private void _writeGradleSource(Definition definition) throws IOException {
		Properties properties = PropertiesFactory.getPropertiesFor(
			OSGiRESTModuleGenerator.class);

		boolean overwriteBuildGradle = false;

		if ("true".equals(
				properties.getProperty(
					"osgi.module.generator.overwrite.gradle"))) {

			overwriteBuildGradle = true;
		}

		BuildGradleGenerator buildGradleGenerator = new BuildGradleGenerator(
			definition.hasContextExtensions(), _moduleOutputPath,
			overwriteBuildGradle);

		buildGradleGenerator.writeSource();
	}

	private static final String _TEMPLATE_FILE_APPLICATION =
		"Application.java.tpl";

	private static final String _TEMPLATE_FILE_BND = "bnd.bnd.tpl";

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
	private final boolean _guestsAllowed;
	private final JsonMessageBodyGenerator _jsonMessageBodyGenerator;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;
	private final boolean _oauth2SecurityAllowed;
	private final boolean _overwriteBND;
	private final ResourceGenerator _resourceGenerator;

}