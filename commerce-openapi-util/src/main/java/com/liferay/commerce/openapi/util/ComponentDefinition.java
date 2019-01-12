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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Igor Beslic
 */
public class ComponentDefinition {

	public ComponentDefinition(
		String name, List<PropertyDefinition> propertyDefinitions,
		String type) {

		_name = name;
		_parameter = null;
		_propertyDefinitions = new ArrayList<>(propertyDefinitions);

		ComponentType componentType = ComponentType.OBJECT;

		if ((type != null) && type.equals("array")) {
			componentType = ComponentType.ARRAY;
		}

		_componentType = componentType;
	}

	public ComponentDefinition(String name, Parameter parameter) {
		_name = name;
		_parameter = parameter;
		_componentType = ComponentType.PARAMETER;
		_propertyDefinitions = Collections.emptyList();
	}

	public String getName() {
		return _name;
	}

	public Parameter getParameter() {
		return _parameter;
	}

	public List<PropertyDefinition> getPropertyDefinitions() {
		return new ArrayList<>(_propertyDefinitions);
	}

	public boolean isArray() {
		if (_componentType == ComponentType.ARRAY) {
			return true;
		}

		return false;
	}

	public boolean isObject() {
		if (_componentType == ComponentType.OBJECT) {
			return true;
		}

		return false;
	}

	public boolean isParameter() {
		if (_componentType == ComponentType.PARAMETER) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{name=");
		sb.append(_name);
		sb.append(", propertyDefinitions={");

		Iterator<PropertyDefinition> iterator = _propertyDefinitions.iterator();

		while (iterator.hasNext()) {
			sb.append(iterator.next());

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}}");

		_toString = sb.toString();

		return _toString;
	}

	public enum ComponentType {

		ARRAY, OBJECT, PARAMETER

	}

	private final ComponentType _componentType;
	private final String _name;
	private final Parameter _parameter;
	private final List<PropertyDefinition> _propertyDefinitions;
	private String _toString;

}