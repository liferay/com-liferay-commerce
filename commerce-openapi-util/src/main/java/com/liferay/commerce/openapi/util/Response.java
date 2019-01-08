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

package com.liferay.commerce.openapi.util;

/**
 * @author Igor Beslic
 */
public class Response {

	public Response(int status, String content) {
		_status = status;
		_content = content;
	}

	public String getContent() {
		return _content;
	}

	public int getStatus() {
		return _status;
	}

	public boolean hasContent() {
		if (_content != null) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("{content=%s, status=%s}", _content, _status);
	}

	private final String _content;
	private final int _status;

}