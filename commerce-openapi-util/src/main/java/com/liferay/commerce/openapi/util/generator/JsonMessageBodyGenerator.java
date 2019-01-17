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

/**
 * @author Ivica Cardic
 */
public class JsonMessageBodyGenerator extends BaseSourceGenerator {

	public JsonMessageBodyGenerator(
		String author, String applicationName, String jaxRSJSONPackagePath,
		String moduleOutputPath) {

		_author = author;
		_applicationName = applicationName;
		_jaxRSJSONPackagePath = jaxRSJSONPackagePath;
		_moduleOutputPath = moduleOutputPath;
	}

	public void writeJsonMessageBodySources() throws IOException {
		_writeJsonMessageBodyReaderSource();

		_writeJsonMessageBodyWriterSource();
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

	private static final String _TEMPLATE_FILE_JSON_MESSAGE_BODY_READER =
		"JsonMessageBodyReader.java.tpl";

	private static final String _TEMPLATE_FILE_JSON_MESSAGE_BODY_WRITER =
		"JsonMessageBodyWriter.java.tpl";

	private final String _applicationName;
	private final String _author;
	private final String _jaxRSJSONPackagePath;
	private final String _moduleOutputPath;

}