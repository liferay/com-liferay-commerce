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

package com.liferay.commerce.batch.engine.api.item;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import com.liferay.commerce.batch.engine.api.exception.ParseException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public abstract class BaseJSONStreamItemReader<T>
	extends BaseItemReader<T> implements ItemReader<T> {

	@Override
	protected T doRead() throws Exception, ParseException {
		try {
			if (_jsonParser == null) {
				_jsonParser = _jsonFactory.createParser(
					new BufferedInputStream(inputStream));
			}

			JsonToken jsonToken = _jsonParser.nextToken();

			if (jsonToken == JsonToken.END_ARRAY) {
				try {
					_jsonParser.close();
				}
				catch (IOException ioe) {
					_log.error(ioe.getMessage(), ioe);
				}

				return null;
			}

			return doRead(_jsonParser);
		}
		catch (IOException ioe) {
			throw new ParseException(ioe.getMessage(), ioe);
		}
	}

	protected abstract T doRead(JsonParser jsonParser) throws IOException;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseJSONStreamItemReader.class);

	private final JsonFactory _jsonFactory = new JsonFactory();
	private JsonParser _jsonParser;

}