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

package com.liferay.commerce.frontend.internal;

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.ClayTableSerializer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ClayTableSerializer.class)
public class ClayTableSerializerImpl implements ClayTableSerializer {

	@Override
	public Map<String, Object> serialize(ClayTable clayTable) {
		Map<String, Object> context = new HashMap<>();

		context.put("elementClasses", clayTable.getElementClasses());
		context.put("id", clayTable.getId());
		context.put("selectable", clayTable.isSelectable());
		context.put("showActionsMenu", clayTable.isShowActionsMenu());

		ClayTable.ResponsiveSize responsiveSize = clayTable.getResponsiveSize();

		context.put("size", StringUtil.toLowerCase(responsiveSize.toString()));

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		JSONArray fieldsJSONArray = _jsonFactory.createJSONArray();

		ClayTableSchema clayTableSchema = clayTable.getClayTableSchema();

		String inputValueField = clayTableSchema.getInputNameField();

		Map<String, ClayTableSchemaField> fieldsMap =
			clayTableSchema.getFields();

		for (Map.Entry<String, ClayTableSchemaField> entry :
				fieldsMap.entrySet()) {

			ClayTableSchemaField clayTableSchemaField = entry.getValue();

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			String label = clayTableSchemaField.getLabel();
			String name = clayTableSchemaField.getFieldName();

			if (Validator.isNull(label)) {
				label = name;
			}

			jsonObject.put(
				"contentRenderer", clayTableSchemaField.getContentRenderer());
			jsonObject.put("fieldName", name);
			jsonObject.put("label", label);
			jsonObject.put("sortable", clayTableSchemaField.isSortable());

			ClayTableSchemaField.SortingOrder sortingOrder =
				clayTableSchemaField.getSortingOrder();

			if (sortingOrder != null) {
				jsonObject.put(
					"sortingOrder",
					StringUtil.toLowerCase(sortingOrder.toString()));
			}

			fieldsJSONArray.put(jsonObject);

			if (name.equals(inputValueField)) {
				schemaJSONObject.put("inputValueField", name);
			}
		}

		schemaJSONObject.put("fields", fieldsJSONArray);
		schemaJSONObject.put(
			"inputNameField", clayTableSchema.getInputNameField());
		schemaJSONObject.put(
			"inputValueField", clayTableSchema.getInputValueField());

		context.put("schema", schemaJSONObject);

		return context;
	}

	@Reference
	private JSONFactory _jsonFactory;

}