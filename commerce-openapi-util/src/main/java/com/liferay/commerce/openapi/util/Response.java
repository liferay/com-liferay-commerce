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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class Response {

	public Response(int status, List<Content> contents) {
		_status = status;

		if (contents != null) {
			_contents.addAll(contents);
		}
	}

	public List<Content> getContents() {
		return _contents;
	}

	public int getStatus() {
		return _status;
	}

	public boolean hasContent() {
		if ((javax.ws.rs.core.Response.Status.Family.familyOf(_status) ==
				javax.ws.rs.core.Response.Status.Family.SUCCESSFUL) &&
			!_contents.isEmpty()) {

			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{content={");

		for (Content content : _contents) {
			sb.append(content);
			sb.append(", ");
		}

		Iterator<Content> iterator = _contents.iterator();

		while (iterator.hasNext()) {
			sb.append(iterator.next());

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}, status=");
		sb.append(_status);

		sb.append("}");

		return sb.toString();
	}

	private final List<Content> _contents = new ArrayList<>();
	private final int _status;

}