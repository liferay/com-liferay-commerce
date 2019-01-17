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

import java.util.Iterator;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class CollectionDTOGenerator extends BaseSourceGenerator {

	public CollectionDTOGenerator(
		String author, String moduleOutputPath, String modelPackagePath) {

		_author = author;
		_moduleOutputPath = moduleOutputPath;
		_modelPackagePath = modelPackagePath;
	}

	public void writeCollectionDTOSource(Set<String> referencedModels)
		throws IOException {

		String dtoSource = getTemplate(_TEMPLATE_FILE_COLLECTION_DTO);

		dtoSource = dtoSource.replace("${PACKAGE}", _modelPackagePath);

		dtoSource = dtoSource.replace("${AUTHOR}", _author);

		StringBuilder sb = new StringBuilder();

		sb.append("{\n");

		Iterator<String> iterator = referencedModels.iterator();

		while (iterator.hasNext()) {
			sb.append("\t\t");
			sb.append(iterator.next());

			sb.append("DTO.class");

			if (iterator.hasNext()) {
				sb.append(",\n");
			}
		}

		sb.append("\n\t}");

		dtoSource = dtoSource.replace("${MODEL_CLASSES}", sb.toString());

		String componentSourcePath = getClassSourcePath(
			_moduleOutputPath, "CollectionDTO.java", _modelPackagePath);

		writeSource(dtoSource, componentSourcePath);
	}

	private static final String _TEMPLATE_FILE_COLLECTION_DTO =
		"CollectionDTO.java.tpl";

	private final String _author;
	private final String _modelPackagePath;
	private final String _moduleOutputPath;

}