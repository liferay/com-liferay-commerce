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

import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiFormat;
import com.liferay.commerce.openapi.util.OpenApiProperty;
import com.liferay.commerce.openapi.util.util.PackageUtils;
import com.liferay.commerce.openapi.util.util.Provider;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.Collections;
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
		OpenApi openApi) {

		_author = author;
		_moduleOutputPath = moduleOutputPath;

		_modelPackagePath =
			modelPackagePath + "." +
				PackageUtils.toPackageName(openApi.getVersion());

		_openApi = openApi;
	}

	public void writeClassSources() throws IOException {
		for (OpenApiComponent openApiComponent :
				_openApi.getOpenApiComponents()) {

			if (!openApiComponent.isObject()) {
				continue;
			}

			writeClassSource(openApiComponent);
		}
	}

	protected String getClassSource(OpenApiComponent openApiComponent)
		throws IOException {

		String dtoSource = getTemplate(_TEMPLATE_FILE_MODEL);

		dtoSource = dtoSource.replace("${PACKAGE}", _modelPackagePath);

		dtoSource = dtoSource.replace("${AUTHOR}", _author);

		dtoSource = dtoSource.replace(
			"${MODEL}", _getModelName(openApiComponent));

		dtoSource = dtoSource.replace(
			"${DTO_CLASS}", _getDTOClassName(openApiComponent));

		List<OpenApiProperty> openApiProperties =
			openApiComponent.getOpenApiProperties();

		Iterator<OpenApiProperty> iterator = openApiProperties.iterator();

		StringBuilder methodsSb = new StringBuilder();
		StringBuilder variablesSb = new StringBuilder();

		Set<String> importableJavaTypes = new HashSet<>();

		while (iterator.hasNext()) {
			OpenApiProperty openApiProperty = iterator.next();

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, _openApi.getOpenApiComponents());

			String name = javaTypeProvider.decorateVariable(
				openApiProperty.getName());

			importableJavaTypes.addAll(
				javaTypeProvider.getAdditionalImportableJavaTypes());
			importableJavaTypes.add(javaTypeProvider.getModelFQCN());

			String javaType = javaTypeProvider.getModelName();

			if (!openApiProperty.isRequired()) {
				importableJavaTypes.add(
					GeneratorConstants.NULLABLE_ANNOTATION_FQCN);

				methodsSb.append("\t");
				methodsSb.append(GeneratorConstants.NULLABLE_ANNOTATION);
				methodsSb.append("\n");
			}

			methodsSb.append(javaTypeProvider.getGetterMethodAnnotation());

			methodsSb.append("\tpublic ");
			methodsSb.append(javaType);
			methodsSb.append(" ");
			methodsSb.append(openApiProperty.getGetterSyntax());
			methodsSb.append(StringUtils.upperCaseFirstChar(name));
			methodsSb.append("() {\n\t\treturn _");
			methodsSb.append(name);
			methodsSb.append(";\n\t}\n\n");

			methodsSb.append(javaTypeProvider.getSetterMethodAnnotation());

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

			if (!openApiProperty.isRequired()) {
				variablesSb.append("\t");
				variablesSb.append(GeneratorConstants.NULLABLE_ANNOTATION);
				variablesSb.append("\n");
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

		dtoSource = dtoSource.replace(
			"${MODEL_IMPORT_STATEMENTS}",
			_toImportStatements(importableJavaTypes));

		dtoSource = dtoSource.replace("${METHODS}", methodsSb.toString());
		dtoSource = dtoSource.replace("${VARIABLES}", variablesSb.toString());

		return dtoSource;
	}

	protected void writeClassSource(OpenApiComponent openApiComponent)
		throws IOException {

		String dtoSourcePath = getClassSourcePath(
			_moduleOutputPath, _getDTOClassName(openApiComponent) + ".java",
			_modelPackagePath);

		writeSource(getClassSource(openApiComponent), dtoSourcePath);
	}

	private String _getDTOClassName(OpenApiComponent openApiComponent) {
		return _getModelName(openApiComponent) + "DTO";
	}

	private String _getModelName(OpenApiComponent openApiComponent) {
		return StringUtils.upperCaseFirstChar(openApiComponent.getName());
	}

	private String _toImportStatements(Set<String> importableJavaTypes) {
		importableJavaTypes.removeAll(Collections.singleton(null));

		StringBuilder sb = new StringBuilder();

		Iterator<String> iterator = importableJavaTypes.iterator();

		while (iterator.hasNext()) {
			sb.append("import ");
			sb.append(iterator.next());
			sb.append(";");

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private static final String _TEMPLATE_FILE_MODEL = "Model.java.tpl";

	private final String _author;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;
	private final OpenApi _openApi;

}