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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class Definition {

	public Definition(String version, String description, String title) {
		_version = version;
		_description = description;
		_title = title;
	}

	public boolean addMethod(Method method) {
		String modelName = _getModelFromPath(method.getAbsolutePath());

		Path path = new Path(modelName, method.getModelPath());

		if (!_paths.contains(path)) {
			_paths.add(path);
		}
		else {
			Iterator<Path> iterator = _paths.iterator();

			while (iterator.hasNext()) {
				Path next = iterator.next();

				if (modelName.equals(next.getName())) {
					path = next;

					break;
				}
			}
		}

		return path.addMethod(method, _componentDefinitions);
	}

	public Set<ComponentDefinition> getComponentDefinitions() {
		return new HashSet<>(_componentDefinitions);
	}

	public String getDescription() {
		return _description;
	}

	public List<Path> getPaths() {
		return new ArrayList<>(_paths);
	}

	public String getTitle() {
		return _title;
	}

	public String getVersion() {
		return _version;
	}

	public void setComponentDefinitions(
		List<ComponentDefinition> componentDefinitions) {

		_componentDefinitions.addAll(componentDefinitions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Iterator<Path> pathIterator = _paths.iterator();

		while (pathIterator.hasNext()) {
			Path path = pathIterator.next();

			sb.append(path);

			sb.append("\n");

			List<Method> methods = path.getMethods();

			Iterator<Method> methodsIterator = methods.iterator();

			while (methodsIterator.hasNext()) {
				sb.append("\t");
				sb.append(methodsIterator.next());

				if (methodsIterator.hasNext()) {
					sb.append("\n");
				}
			}

			if (pathIterator.hasNext()) {
				sb.append("\n");
			}
		}

		Iterator<ComponentDefinition> componentDefinitionsIterator =
			_componentDefinitions.iterator();

		while (componentDefinitionsIterator.hasNext()) {
			sb.append(componentDefinitionsIterator.next());

			if (componentDefinitionsIterator.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private String _getModelFromPath(String path) {
		Matcher matcher = _schemaReferencePattern.matcher(path);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return path;
	}

	private static final Pattern _schemaReferencePattern = Pattern.compile(
		"^/?(\\w+)/.*$");

	private final Set<ComponentDefinition> _componentDefinitions =
		new HashSet<>();
	private final String _description;
	private final Set<Path> _paths = new HashSet<>();
	private final String _title;
	private final String _version;

}