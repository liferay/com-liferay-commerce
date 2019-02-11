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

package com.liferay.commerce.openapi.util.importer;

import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiProperty;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.util.GetterUtil;
import com.liferay.commerce.openapi.util.util.OpenApiComponentUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ComponentImporter {

	public List<OpenApiComponent> getComponents(JsonNode componentsJSONNode) {
		List<OpenApiComponent> components = new ArrayList<>();

		if (componentsJSONNode.has("schemas")) {
			components.addAll(_getSchemas(componentsJSONNode));
		}

		if (componentsJSONNode.has("parameters")) {
			components.addAll(_getParameters(componentsJSONNode));
		}

		return _resolveReferences(components);
	}

	private OpenApiComponent _getOpenApiComponent(
		String name, JsonNode schemaEntryJSONNode) {

		OpenApiComponent.OpenApiComponentBuilder openApiComponentBuilder =
			new OpenApiComponent.OpenApiComponentBuilder();

		openApiComponentBuilder.name(name);

		_setIfHas(
			schemaEntryJSONNode, "$ref",
			openApiComponentBuilder :: itemsReference);

		String type = GetterUtil.getAsText(
			"type", schemaEntryJSONNode, "object");

		openApiComponentBuilder.type(type);

		if ("array".equals(type)) {
			JsonNode itemsJSONNode = schemaEntryJSONNode.get("items");

			_setIfHas(
				itemsJSONNode, "$ref",
				openApiComponentBuilder :: itemsReference);
		}
		else if (schemaEntryJSONNode.has("additionalProperties")) {
			JsonNode additionalPropertiesJSONNode = schemaEntryJSONNode.get(
				"additionalProperties");

			_setIfHas(
				additionalPropertiesJSONNode, "$ref",
				openApiComponentBuilder :: itemsReference);
			_setIfHas(
				additionalPropertiesJSONNode, "format",
				openApiComponentBuilder::itemsFormat);
			_setIfHas(
				additionalPropertiesJSONNode, "type",
				openApiComponentBuilder::itemsType);

			openApiComponentBuilder.type("dictionary");
		}

		openApiComponentBuilder.openApiProperties(
			_getPropertyDefinitions(schemaEntryJSONNode));

		return openApiComponentBuilder.build();
	}

	private List<OpenApiComponent> _getParameters(JsonNode componentsJSONNode) {
		List<OpenApiComponent> components = new ArrayList<>();

		JsonNode parametersJSONNode = componentsJSONNode.get("parameters");

		Iterator<Map.Entry<String, JsonNode>> fields =
			parametersJSONNode.fields();

		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> parameterField = fields.next();

			OpenApiComponent.OpenApiComponentBuilder openApiComponentBuilder =
				new OpenApiComponent.OpenApiComponentBuilder();

			openApiComponentBuilder.name(parameterField.getKey());
			openApiComponentBuilder.parameter(
				ParameterImporter.fromJSONNode(parameterField.getValue()));

			components.add(openApiComponentBuilder.build());

			_logger.debug(
				"Resolved parameter {}", components.get(components.size() - 1));
		}

		return components;
	}

	private List<OpenApiProperty> _getPropertyDefinitions(
		JsonNode schemaEntryJSONNode) {

		List<OpenApiProperty> openApiProperties = new ArrayList<>();

		List<String> requiredProperties = new ArrayList<>();

		if (schemaEntryJSONNode.has("required")) {
			requiredProperties.addAll(
				GetterUtil.getAsTextList(schemaEntryJSONNode.get("required")));
		}

		if (schemaEntryJSONNode.has("properties")) {
			JsonNode propertiesJSONNode = schemaEntryJSONNode.get("properties");

			Iterator<Map.Entry<String, JsonNode>> fields =
				propertiesJSONNode.fields();

			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> propertyEntry = fields.next();

				String propertyNameValue = propertyEntry.getKey();

				JsonNode propertyJSONNode = propertyEntry.getValue();

				OpenApiProperty.OpenApiPropertyBuilder openApiPropertyBuilder =
					new OpenApiProperty.OpenApiPropertyBuilder();

				openApiPropertyBuilder.name(propertyNameValue);

				String openApiTypeValue = GetterUtil.getAsText(
					"type", propertyJSONNode, "object");

				openApiPropertyBuilder.openApiTypeValue(openApiTypeValue);

				_setIfHas(
					propertyJSONNode, "example",
					openApiPropertyBuilder :: example);
				_setIfHas(
					propertyJSONNode, "format",
					openApiPropertyBuilder :: openApiFormatValue);

				if (propertyJSONNode.has("items")) {
					JsonNode itemsJSONNode = propertyJSONNode.get("items");

					_setIfHas(
						itemsJSONNode, "type",
						openApiPropertyBuilder ::itemsOpenApiTypeValue);
					_setIfHas(
						itemsJSONNode, "format",
						openApiPropertyBuilder ::itemsOpenApiFormatValue);
					_setIfHas(
						itemsJSONNode, "$ref",
						openApiPropertyBuilder :: componentReference);
				}

				_setIfHas(
					propertyJSONNode, "$ref",
					openApiPropertyBuilder :: componentReference);

				if (requiredProperties.contains(propertyNameValue)) {
					openApiPropertyBuilder.required(true);
				}

				if ("object".equals(openApiTypeValue)) {
					if (propertyJSONNode.has("additionalProperties")) {
						openApiPropertyBuilder.openApiTypeValue("dictionary");

						JsonNode additionalPropertiesJSONNode =
							propertyJSONNode.get("additionalProperties");

						_setIfHas(
							additionalPropertiesJSONNode, "$ref",
							openApiPropertyBuilder :: itemsReference);
						_setIfHas(
							additionalPropertiesJSONNode, "format",
							openApiPropertyBuilder::itemsOpenApiFormatValue);
						_setIfHas(
							additionalPropertiesJSONNode, "type",
							openApiPropertyBuilder::itemsOpenApiTypeValue);
					}
				}

				openApiProperties.add(openApiPropertyBuilder.build());
			}
		}

		return openApiProperties;
	}

	private List<OpenApiComponent> _getSchemas(JsonNode componentsJSONNode) {
		List<OpenApiComponent> components = new ArrayList<>();

		JsonNode schemasJSONNode = componentsJSONNode.get("schemas");

		Iterator<Map.Entry<String, JsonNode>> schemaFields =
			schemasJSONNode.fields();

		while (schemaFields.hasNext()) {
			Map.Entry<String, JsonNode> schemaEntry = schemaFields.next();

			OpenApiComponent openApiComponent = _getOpenApiComponent(
				schemaEntry.getKey(), schemaEntry.getValue());

			components.add(openApiComponent);

			_logger.debug("Resolved schema {}", openApiComponent);
		}

		return components;
	}

	private List<OpenApiComponent> _resolveReferences(
		List<OpenApiComponent> components) {

		List<OpenApiComponent> resolvedComponents = new ArrayList<>();

		Iterator<OpenApiComponent> iterator = components.iterator();

		while (iterator.hasNext()) {
			OpenApiComponent unresolvedOpenApiComponent = iterator.next();

			if (!unresolvedOpenApiComponent.isObject()) {
				resolvedComponents.add(unresolvedOpenApiComponent);

				continue;
			}

			int changedPropertiesCount = 0;
			List<OpenApiProperty> resolvedOpenApiProperties = new ArrayList<>();

			for (OpenApiProperty openApiProperty :
					unresolvedOpenApiComponent.getOpenApiProperties()) {

				if (openApiProperty.getReference() == null) {
					resolvedOpenApiProperties.add(openApiProperty);

					continue;
				}

				OpenApiComponent referredOpenApiComponent =
					OpenApiComponentUtil.getSchemaOpenApiComponent(
						Schema.getReferencedModel(
							openApiProperty.getReference()),
						new HashSet<>(components));

				if (referredOpenApiComponent == null) {
					resolvedOpenApiProperties.add(openApiProperty);

					_logger.warn(
						"No open API component resolution for reference {}",
						openApiProperty.getReference());

					continue;
				}

				if (!referredOpenApiComponent.isDictionary()) {
					resolvedOpenApiProperties.add(openApiProperty);

					continue;
				}

				OpenApiProperty.OpenApiPropertyBuilder openApiPropertyBuilder =
					new OpenApiProperty.OpenApiPropertyBuilder();

				openApiPropertyBuilder.name(openApiProperty.getName());
				openApiPropertyBuilder.openApiTypeValue("dictionary");

				resolvedOpenApiProperties.add(openApiPropertyBuilder.build());

				changedPropertiesCount++;
			}

			if (changedPropertiesCount == 0) {
				resolvedComponents.add(unresolvedOpenApiComponent);

				continue;
			}

			OpenApiComponent.OpenApiComponentBuilder openApiComponentBuilder =
				new OpenApiComponent.OpenApiComponentBuilder();

			openApiComponentBuilder.name(unresolvedOpenApiComponent.getName());

			openApiComponentBuilder.openApiProperties(
				resolvedOpenApiProperties);

			openApiComponentBuilder.type("object");

			resolvedComponents.add(openApiComponentBuilder.build());
		}

		return resolvedComponents;
	}

	private void _setIfHas(
		JsonNode jsonNode, String name, Consumer<String> setter) {

		if (!jsonNode.has(name)) {
			return;
		}

		JsonNode valueJSONNOde = jsonNode.get(name);

		setter.accept(valueJSONNOde.asText());
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ComponentImporter.class);

}