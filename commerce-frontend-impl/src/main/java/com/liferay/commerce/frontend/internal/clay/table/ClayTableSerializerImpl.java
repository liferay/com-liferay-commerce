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

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.ClayTableSerializer;
import com.liferay.petra.string.StringPool;
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

		context.put("actionsMenuVariant", clayTable.getActionsMenuVariant());
		context.put("elementClasses", clayTable.getElementClasses());
		context.put("id", clayTable.getId());
		context.put("selectable", clayTable.isSelectable());
		context.put("showActionsMenu", clayTable.isShowActionsMenu());
		context.put("tableVariant", clayTable.getTableVariant());

		ClayTable.ResponsiveSize responsiveSize = clayTable.getResponsiveSize();

		context.put("size", StringUtil.toLowerCase(responsiveSize.toString()));

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		JSONArray fieldsJSONArray = _jsonFactory.createJSONArray();

		ClayTableSchema clayTableSchema = clayTable.getClayTableSchema();

		Map<String, ClayTableSchemaField> fieldsMap =
			clayTableSchema.getFields();

		for (ClayTableSchemaField clayTableSchemaField : fieldsMap.values()) {
			JSONObject jsonObject = _jsonFactory.createJSONObject();

			String label = clayTableSchemaField.getLabel();

			if (Validator.isNull(label)) {
				label = StringPool.BLANK;
			}

			jsonObject.put(
				"contentRenderer", clayTableSchemaField.getContentRenderer());

			String name = clayTableSchemaField.getFieldName();

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

			Map<String, Object> properties =
				clayTableSchemaField.getProperties();

			if (properties != null) {
				for (Map.Entry<String, Object> property :
						properties.entrySet()) {

					jsonObject.put(property.getKey(), property.getValue());
				}
			}

			fieldsJSONArray.put(jsonObject);

			if (name.equals(clayTableSchema.getInputNameField())) {
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