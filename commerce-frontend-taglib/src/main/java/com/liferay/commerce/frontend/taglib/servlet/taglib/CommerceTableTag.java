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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.data.provider.CommerceDataProvider;
import com.liferay.commerce.data.provider.CommerceDataProviderRegistry;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.frontend.taglib.internal.table.ClayTableRow;
import com.liferay.commerce.frontend.taglib.internal.table.ClayTableUtil;
import com.liferay.commerce.frontend.taglib.table.ClayTable;
import com.liferay.commerce.frontend.taglib.table.ClayTableRegistry;
import com.liferay.commerce.frontend.taglib.table.ClayTableSerializer;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 */
public class CommerceTableTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		Map<String, Object> context = getContext();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tableName = GetterUtil.getString(context.get("tableName"));

		String dataProviderKey = GetterUtil.getString(
			context.get("dataProviderKey"));

		int itemPerPage = GetterUtil.getInteger(context.get("itemPerPage"));

		int pageNumber = GetterUtil.getInteger(context.get("pageNumber"));

		CommerceDataProvider commerceDataProvider =
			_commerceDataProviderRegistry.getCommerceDataProvider(
				dataProviderKey);

		ClayTable clayTable = _clayTableRegistry.getClayTable(tableName);

		Map<String, Object> clayTableContext = _clayTableSerializer.serialize(
			clayTable);

		for (Map.Entry<String, Object> entry : clayTableContext.entrySet()) {
			putValue(entry.getKey(), entry.getValue());
		}

		try {
			List<Object> items = commerceDataProvider.getItems(
				themeDisplay.getScopeGroupId(), itemPerPage, pageNumber, null);

			List<ClayTableRow> clayTableRows = _clayTableUtil.getClayTableRows(
				items, tableName, request, themeDisplay.getScopeGroupId());

			Object looseDeserializedClayTableRows =
				JSONFactoryUtil.looseDeserialize(
					_OBJECT_MAPPER.writeValueAsString(clayTableRows));

			putValue("items", looseDeserializedClayTableRows);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		setTemplateNamespace("CommerceTable.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		return "commerce-frontend-taglib/clay_table/CommerceTable.es";
	}

	public void setDataProviderKey(String dataProviderKey) {
		putValue("dataProviderKey", dataProviderKey);
	}

	public void setItemPerPage(int itemPerPage) {
		putValue("itemPerPage", itemPerPage);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		_clayTableRegistry = ServletContextUtil.getClayTableRegistry();
		_clayTableSerializer = ServletContextUtil.getClayTableSerializer();
		_clayTableUtil = ServletContextUtil.getClayTableUtil();
		_commerceDataProviderRegistry =
			ServletContextUtil.getCommerceDataProviderRegistry();

		super.setPageContext(pageContext);
	}

	public void setPageNumber(String pageNumber) {
		putValue("pageNumber", pageNumber);
	}

	public void setTableName(String tableName) {
		putValue("tableName", tableName);
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private ClayTableUtil _clayTableUtil;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;

}