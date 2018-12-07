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

package com.liferay.commerce.frontend.taglib.internal.table;

import com.liferay.commerce.frontend.taglib.table.ClayTableSchema;
import com.liferay.commerce.frontend.taglib.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.taglib.table.ClayTableSchemaField;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class ClayTableSchemaBuilderImpl implements ClayTableSchemaBuilder {

	public ClayTableSchemaBuilderImpl() {
		_clayTableSchema = new ClayTableSchema();
		_fields = new HashMap<>();
		_properties = new HashMap<>();
		_inputNamesMap = new HashMap<>();
	}

	@Override
	public void addField(ClayTableSchemaField clayTableSchemaField) {
		_fields.put(clayTableSchemaField.getFieldName(), clayTableSchemaField);
	}

	@Override
	public ClayTableSchemaField addField(String fieldName) {
		ClayTableSchemaField clayTableSchemaField = new ClayTableSchemaField();

		clayTableSchemaField.setFieldName(fieldName);

		_fields.put(fieldName, clayTableSchemaField);

		return clayTableSchemaField;
	}

	@Override
	public ClayTableSchemaField addField(String fieldName, String label) {
		ClayTableSchemaField clayTableSchemaField = addField(fieldName);

		clayTableSchemaField.setLabel(label);

		return clayTableSchemaField;
	}

	@Override
	public void addInputNamesMap(String inputName, Object object) {
		_inputNamesMap.put(inputName, object);
	}

	@Override
	public void addProperties(String name, Object value) {
		_properties.put(name, value);
	}

	@Override
	public ClayTableSchema build() {
		_clayTableSchema.setFields(_fields);
		_clayTableSchema.setInputNamesMap(_inputNamesMap);
		_clayTableSchema.setProperties(_properties);

		return _clayTableSchema;
	}

	@Override
	public void setInputNameField(String inputNameField) {
		_clayTableSchema.setInputNameField(inputNameField);
	}

	@Override
	public void setInputValueField(String inputValueField) {
		_clayTableSchema.setInputValueField(inputValueField);
	}

	private final ClayTableSchema _clayTableSchema;
	private final Map<String, ClayTableSchemaField> _fields;
	private final Map<String, Object> _inputNamesMap;
	private final Map<String, Object> _properties;

}