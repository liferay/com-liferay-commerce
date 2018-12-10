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

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableDataJSONBuilder;
import com.liferay.commerce.frontend.ClayTableRegistry;
import com.liferay.commerce.frontend.ClayTableSerializer;
import com.liferay.commerce.frontend.CommerceDataProviderRegistry;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.PaginationImpl;
import com.liferay.commerce.frontend.taglib.internal.pagination.model.ClayPaginationEntry;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
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

		try {
			CommerceDataSetDataProvider commerceDataSetDataProvider =
				_commerceDataProviderRegistry.getCommerceDataProvider(
					dataProviderKey);

			ClayTable clayTable = _clayTableRegistry.getClayTable(tableName);

			Map<String, Object> clayTableContext =
				_clayTableSerializer.serialize(clayTable);

			for (Map.Entry<String, Object> entry :
					clayTableContext.entrySet()) {

				putValue(entry.getKey(), entry.getValue());
			}

			List<Object> items = commerceDataSetDataProvider.getItems(
				themeDisplay.getScopeGroupId(),
				new PaginationImpl(itemPerPage, pageNumber), null);

			String json = _clayTableDataJSONBuilder.build(
				themeDisplay.getScopeGroupId(), tableName, items, request);

			putValue("items", JSONFactoryUtil.looseDeserialize(json));

			int totalItems = commerceDataSetDataProvider.countItems(
				themeDisplay.getScopeGroupId());

			List<ClayPaginationEntry> clayPaginationEntries = new ArrayList<>();

			clayPaginationEntries.add(new ClayPaginationEntry("#", 5));
			clayPaginationEntries.add(new ClayPaginationEntry("#", 10));
			clayPaginationEntries.add(new ClayPaginationEntry("#", 100));
			clayPaginationEntries.add(new ClayPaginationEntry("#", 1000));

			putValue("id", clayTable.getId());
			putValue("totalItems", totalItems);
			putValue("currentPage", pageNumber);
			putValue("paginationSelectedEntry", 1);
			putValue("paginationEntries", clayPaginationEntries);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		putValue("spritemap", "");

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
		_clayTableDataJSONBuilder =
			ServletContextUtil.getClayTableDataJSONBuilder();
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

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTableTag.class);

	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;

}