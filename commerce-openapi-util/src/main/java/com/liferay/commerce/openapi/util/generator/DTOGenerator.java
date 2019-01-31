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

import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiFormat;
import com.liferay.commerce.openapi.util.OpenApiProperty;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class DTOGenerator extends BaseSourceGenerator {

	public DTOGenerator(
		String author, String moduleOutputPath, String modelPackagePath,
		OpenApiComponent openApiComponent,
		Set<OpenApiComponent> openApiComponents) {

		_author = author;
		_moduleOutputPath = moduleOutputPath;
		_modelPackagePath = modelPackagePath;
		_openApiComponent = openApiComponent;

		_openApiComponents.addAll(openApiComponents);
	}

	public void writeClassSource() throws IOException {
		String dtoSourcePath = getClassSourcePath(
			_moduleOutputPath, _getDTOClassName() + ".java", _modelPackagePath);

		writeSource(getClassSource(), dtoSourcePath);
	}

	protected String getClassSource() throws IOException {
		String dtoSource = getTemplate(_TEMPLATE_FILE_MODEL);

		dtoSource = dtoSource.replace("${PACKAGE}", _modelPackagePath);

		dtoSource = dtoSource.replace("${AUTHOR}", _author);

		dtoSource = dtoSource.replace("${MODEL}", _getModelName());

		dtoSource = dtoSource.replace("${DTO_CLASS}", _getDTOClassName());

		List<OpenApiProperty> openApiProperties =
			_openApiComponent.getOpenApiProperties();

		Iterator<OpenApiProperty> iterator = openApiProperties.iterator();

		StringBuilder methodsSb = new StringBuilder();
		StringBuilder variablesSb = new StringBuilder();

		while (iterator.hasNext()) {
			OpenApiProperty openApiProperty = iterator.next();

			String name = openApiProperty.getName();
			String javaType = OpenApiFormat.getJavaType(
				openApiProperty, _openApiComponents);

			methodsSb.append("\tpublic ");
			methodsSb.append(javaType);
			methodsSb.append(" ");
			methodsSb.append(openApiProperty.getGetterSyntax());
			methodsSb.append(StringUtils.upperCaseFirstChar(name));
			methodsSb.append("() {\n\t\treturn _");
			methodsSb.append(name);
			methodsSb.append(";\n\t}\n\n");

			methodsSb.append("\tpublic void ");
			methodsSb.append(openApiProperty.getSetterSyntax());
			methodsSb.append(StringUtils.upperCaseFirstChar(name));
			methodsSb.append("(");
			methodsSb.append(javaType);
			methodsSb.append(" ");
			methodsSb.append(name);
			methodsSb.append(") {\n\t\t_");
			methodsSb.append(name);
			methodsSb.append(" = ");
			methodsSb.append(name);
			methodsSb.append(";\n\t}");

			if (iterator.hasNext()) {
				methodsSb.append("\n\n");
			}

			variablesSb.append("\tprivate ");
			variablesSb.append(javaType);
			variablesSb.append(" _");
			variablesSb.append(name);
			variablesSb.append(";");

			if (iterator.hasNext()) {
				variablesSb.append("\n");
			}
		}

		dtoSource = dtoSource.replace("${METHODS}", methodsSb.toString());
		dtoSource = dtoSource.replace("${VARIABLES}", variablesSb.toString());

		return dtoSource;
	}

	private String _getDTOClassName() {
		return _getModelName() + "DTO";
	}

	private String _getModelName() {
		return StringUtils.upperCaseFirstChar(_openApiComponent.getName());
	}

	private static final String _TEMPLATE_FILE_MODEL = "Model.java.tpl";

	private final String _author;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;
	private final OpenApiComponent _openApiComponent;
	private final Set<OpenApiComponent> _openApiComponents = new HashSet<>();

}