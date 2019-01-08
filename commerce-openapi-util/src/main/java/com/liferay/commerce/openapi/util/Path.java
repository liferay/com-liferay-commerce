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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class Path {

	public Path(String name, String path) {
		_name = name;
		_path = path;
	}

	public boolean addMethod(Method method) {
		for (Parameter parameter : method.getParameters()) {
			if (parameter.getSchema() == null) {
				continue;
			}

			Schema schema = parameter.getSchema();

			if (schema.getReferencedModel() != null) {
				_referencedModels.add(schema.getReferencedModel());
			}
		}

		return _methods.add(method);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Path) {
			Path other = (Path)obj;

			return _name.equals(other.getName());
		}

		return false;
	}

	public List<Method> getMethods() {
		return _methods;
	}

	public String getName() {
		return _name;
	}

	public String getPath() {
		return _path;
	}

	public Set<String> getReferencedModels() {
		return new HashSet<>(_referencedModels);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{name=");
		sb.append(_name);
		sb.append(", path = ");
		sb.append(_path);
		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private final List<Method> _methods = new ArrayList<>();
	private final String _name;
	private final String _path;
	private final Set<String> _referencedModels = new HashSet<>();
	private String _toString;

}