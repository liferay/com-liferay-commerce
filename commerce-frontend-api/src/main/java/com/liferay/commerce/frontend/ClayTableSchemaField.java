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
public class ClayTableSchemaField {

	public String getContentRenderer() {
		return _contentRenderer;
	}

	public Map<String, Object> getContentRendererMap() {
		return _contentRendererMap;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public Map<String, Object> getFieldNameMap() {
		return _fieldNameMap;
	}

	public String getLabel() {
		return _label;
	}

	public Map<String, Object> getProperties() {
		return _properties;
	}

	public SortingOrder getSortingOrder() {
		return _sortingOrder;
	}

	public boolean isSortable() {
		return _sortable;
	}

	public void setContentRenderer(String contentRenderer) {
		_contentRenderer = contentRenderer;
	}

	public void setContentRendererMap(Map<String, Object> contentRendererMap) {
		_contentRendererMap = contentRendererMap;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setFieldNameMap(Map<String, Object> fieldNameMap) {
		_fieldNameMap = fieldNameMap;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setProperties(Map<String, Object> properties) {
		_properties = properties;
	}

	public void setSortable(boolean sortable) {
		_sortable = sortable;
	}

	public void setSortingOrder(SortingOrder sortingOrder) {
		_sortingOrder = sortingOrder;
	}

	public enum SortingOrder {

		ASC, DESC

	}

	private String _contentRenderer;
	private Map<String, Object> _contentRendererMap;
	private String _fieldName;
	private Map<String, Object> _fieldNameMap;
	private String _label;
	private Map<String, Object> _properties;
	private boolean _sortable;
	private SortingOrder _sortingOrder;

}