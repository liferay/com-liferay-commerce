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

package com.liferay.commerce.frontend;

import java.util.Map;

/**
 * @author Marco Leo
 */
public class ClayTableSchema {

	public Map<String, ClayTableSchemaField> getFields() {
		return _fields;
	}

	public String getInputNameField() {
		return _inputNameField;
	}

	public Map<String, Object> getInputNamesMap() {
		return _inputNamesMap;
	}

	public String getInputValueField() {
		return _inputValueField;
	}

	public Map<String, Object> getProperties() {
		return _properties;
	}

	public void setFields(Map<String, ClayTableSchemaField> fields) {
		_fields = fields;
	}

	public void setInputNameField(String inputNameField) {
		_inputNameField = inputNameField;
	}

	public void setInputNamesMap(Map<String, Object> inputNamesMap) {
		_inputNamesMap = inputNamesMap;
	}

	public void setInputValueField(String inputValueField) {
		_inputValueField = inputValueField;
	}

	public void setProperties(Map<String, Object> properties) {
		_properties = properties;
	}

	private Map<String, ClayTableSchemaField> _fields;
	private String _inputNameField;
	private Map<String, Object> _inputNamesMap;
	private String _inputValueField;
	private Map<String, Object> _properties;

}