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
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.commerce.frontend.FilterFactoryRegistry;
import com.liferay.commerce.frontend.PaginationImpl;
import com.liferay.commerce.frontend.taglib.internal.model.ClayPaginationEntry;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

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

		String dataProviderKey = GetterUtil.getString(
			context.get("dataProviderKey"));

		Filter filter = (Filter)context.get("filter");

		if (filter == null) {
			FilterFactory filterFactory =
				_filterFactoryRegistry.getFilterFactory(dataProviderKey);

			filter = filterFactory.create(request);
		}

		String deltaParam = GetterUtil.getString(
			context.get("deltaParam"), SearchContainer.DEFAULT_DELTA_PARAM);

		int itemPerPage = GetterUtil.getInteger(context.get("itemPerPage"));

		String namespace = GetterUtil.getString(context.get("namespace"));

		int pageNumber = GetterUtil.getInteger(context.get("pageNumber"));

		PortletURL portletURL = (PortletURL)context.get("portletURL");

		String tableName = GetterUtil.getString(context.get("tableName"));

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
				themeDisplay.getScopeGroupId(), filter,
				new PaginationImpl(itemPerPage, pageNumber), null);

			String json = _clayTableDataJSONBuilder.build(
				themeDisplay.getScopeGroupId(), tableName, items, request);

			putValue("items", JSONFactoryUtil.looseDeserialize(json));

			int totalItems = commerceDataSetDataProvider.countItems(
				themeDisplay.getScopeGroupId(), filter);

			putValue("totalItems", totalItems);

			List<ClayPaginationEntry> clayPaginationEntries =
				getClayPaginationEntries(portletURL, namespace, deltaParam);

			putValue("paginationEntries", clayPaginationEntries);

			Stream<ClayPaginationEntry> stream = clayPaginationEntries.stream();

			ClayPaginationEntry clayPaginationEntry = stream.filter(
				entry -> entry.getLabel() == itemPerPage
			).findAny(
			).orElse(
				null
			);

			int paginationSelectedEntry = clayPaginationEntries.indexOf(
				clayPaginationEntry);

			putValue("paginationSelectedEntry", paginationSelectedEntry);

			putValue("id", clayTable.getId());

			setComponentId(clayTable.getId());

			putValue("currentPage", pageNumber);

			putValue(
				"dataSetAPI",
				PortalUtil.getPortalURL(request) +
					"/o/commerce-ui/commerce-data-set");
			putValue("groupId", themeDisplay.getScopeGroupId());
			putValue("pageSize", itemPerPage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		putValue(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");

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

	public void setDeltaParam(String deltaParam) {
		putValue("deltaParam", deltaParam);
	}

	public void setDisableAJAX(boolean disableAJAX) {
		putValue("disableAJAX", disableAJAX);
	}

	public void setFilter(Filter filter) {
		putValue("filter", filter);
	}

	public void setItemPerPage(int itemPerPage) {
		putValue("itemPerPage", itemPerPage);
	}

	public void setNamespace(String namespace) {
		putValue("namespace", namespace);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		_clayTableRegistry = ServletContextUtil.getClayTableRegistry();
		_clayTableSerializer = ServletContextUtil.getClayTableSerializer();
		_clayTableDataJSONBuilder =
			ServletContextUtil.getClayTableDataJSONBuilder();
		_commerceDataProviderRegistry =
			ServletContextUtil.getCommerceDataProviderRegistry();
		_filterFactoryRegistry = ServletContextUtil.getFilterFactoryRegistry();

		super.setPageContext(pageContext);
	}

	public void setPageNumber(int pageNumber) {
		putValue("pageNumber", pageNumber);
	}

	public void setPortletURL(PortletURL portletURL) {
		putValue("portletURL", portletURL);
	}

	public void setTableName(String tableName) {
		putValue("tableName", tableName);
	}

	protected List<ClayPaginationEntry> getClayPaginationEntries(
		PortletURL portletURL, String namespace, String deltaParam) {

		String portletURLString = portletURL.toString();

		portletURLString = HttpUtil.removeParameter(
			portletURLString, namespace + deltaParam);

		List<ClayPaginationEntry> clayPaginationEntries = new ArrayList<>();

		for (int curDelta : PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES) {
			if (curDelta > SearchContainer.MAX_DELTA) {
				continue;
			}

			String curDeltaURL = HttpUtil.addParameter(
				portletURLString, namespace + deltaParam, curDelta);

			clayPaginationEntries.add(
				new ClayPaginationEntry(curDeltaURL, curDelta));
		}

		return clayPaginationEntries;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTableTag.class);

	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private FilterFactoryRegistry _filterFactoryRegistry;

}