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

package com.liferay.commerce.openapi.util.importer;

import com.liferay.commerce.openapi.util.importer.exception.ReaderException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Igor Beslic
 */
public class FileOpenApiReader implements OpenApiReader {

	public FileOpenApiReader(String path) {
		_path = path;
	}

	@Override
	public String read() {
		try {
			File file = new File(_path);

			if (!file.exists() || !file.isFile()) {
				throw new ReaderException("Invalid input file " + _path);
			}

			BufferedReader reader = new BufferedReader(new FileReader(file));

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);

				line = reader.readLine();
			}

			return sb.toString();
		}
		catch (Exception e) {
			throw new ReaderException("Unable to read open API definition", e);
		}
	}

	private final String _path;

}