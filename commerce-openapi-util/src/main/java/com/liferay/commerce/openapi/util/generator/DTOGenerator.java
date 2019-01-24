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
import com.liferay.commerce.openapi.util.PropertyDefinition;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

/**
 * @author Igor Beslic
 */
public class DTOGenerator extends BaseSourceGenerator {

	public DTOGenerator(
		String author, String moduleOutputPath, String modelPackagePath,
		ComponentDefinition componentDefinition) {

		_author = author;
		_moduleOutputPath = moduleOutputPath;
		_modelPackagePath = modelPackagePath;
		_componentDefinition = componentDefinition;
	}

	public void writeModelSource() throws IOException {
		String dtoSource = getTemplate(_TEMPLATE_FILE_MODEL);

		dtoSource = dtoSource.replace("${PACKAGE}", _modelPackagePath);

		dtoSource = dtoSource.replace("${AUTHOR}", _author);

		String modelName = StringUtils.upperCaseFirstChar(
			_componentDefinition.getName());

		dtoSource = dtoSource.replace("${MODEL}", modelName);

		String dtoClassName = modelName + "DTO";

		dtoSource = dtoSource.replace("${DTO_CLASS}", dtoClassName);

		List<PropertyDefinition> propertyDefinitions =
			_componentDefinition.getPropertyDefinitions();

		Iterator<PropertyDefinition> iterator = propertyDefinitions.iterator();

		StringBuilder methodsSb = new StringBuilder();
		StringBuilder variablesSb = new StringBuilder();

		while (iterator.hasNext()) {
			PropertyDefinition propertyDefinition = iterator.next();

			String name = propertyDefinition.getName();

			methodsSb.append("\tpublic ");
			methodsSb.append(propertyDefinition.getJavaType());
			methodsSb.append(" ");
			methodsSb.append(propertyDefinition.getGetterSyntax());
			methodsSb.append(StringUtils.upperCaseFirstChar(name));
			methodsSb.append("() {\n\t\treturn _");
			methodsSb.append(name);
			methodsSb.append(";\n\t}\n\n");

			methodsSb.append("\tpublic void ");
			methodsSb.append(propertyDefinition.getSetterSyntax());
			methodsSb.append(StringUtils.upperCaseFirstChar(name));
			methodsSb.append("(");
			methodsSb.append(propertyDefinition.getJavaType());
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
			variablesSb.append(propertyDefinition.getJavaType());
			variablesSb.append(" _");
			variablesSb.append(name);
			variablesSb.append(";");

			if (iterator.hasNext()) {
				variablesSb.append("\n");
			}
		}

		dtoSource = dtoSource.replace("${METHODS}", methodsSb.toString());
		dtoSource = dtoSource.replace("${VARIABLES}", variablesSb.toString());

		String dtoSourcePath = getClassSourcePath(
			_moduleOutputPath, dtoClassName + ".java", _modelPackagePath);

		writeSource(dtoSource, dtoSourcePath);
	}

	private static final String _TEMPLATE_FILE_MODEL = "Model.java.tpl";

	private final String _author;
	private final ComponentDefinition _componentDefinition;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;

}