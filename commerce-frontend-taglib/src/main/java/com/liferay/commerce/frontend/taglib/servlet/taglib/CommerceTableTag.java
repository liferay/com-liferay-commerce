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
import com.liferay.commerce.frontend.ClayTableContextContributor;
import com.liferay.commerce.frontend.ClayTableContextContributorRegistry;
import com.liferay.commerce.frontend.ClayTableDataJSONBuilder;
import com.liferay.commerce.frontend.ClayTableRegistry;
import com.liferay.commerce.frontend.ClayTableSerializer;
import com.liferay.commerce.frontend.CommerceDataProviderRegistry;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.commerce.frontend.FilterFactoryRegistry;
import com.liferay.commerce.frontend.PaginationImpl;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.model.ClayPaginationEntry;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

		String dataProviderKey = GetterUtil.getString(
			context.get("dataProviderKey"));

		String tableName = GetterUtil.getString(context.get("tableName"));

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			_setTableContext(tableName);
			_setItems(dataProviderKey);
			_setPagination();

			setComponentId(tableName + "CommerceTable");

			StringBundler sb = new StringBundler(11);

			sb.append(PortalUtil.getPortalURL(request));
			sb.append("/o/commerce-ui/commerce-data-set/");
			sb.append(themeDisplay.getScopeGroupId());
			sb.append(StringPool.FORWARD_SLASH);
			sb.append(tableName);
			sb.append(StringPool.FORWARD_SLASH);
			sb.append(dataProviderKey);
			sb.append("?plid=");
			sb.append(layout.getPlid());
			sb.append("&portletId=");
			sb.append(portletDisplay.getId());

			putValue("dataSetAPI", sb.toString());

			putValue(
				"spritemap",
				themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		setTemplateNamespace("CommerceTable.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/clay_table/CommerceTable.es");
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
		_clayTableDataJSONBuilder =
			ServletContextUtil.getClayTableDataJSONBuilder();
		_clayTableContextContributorRegistry =
			ServletContextUtil.getClayTableContextContributorRegistry();
		_clayTableRegistry = ServletContextUtil.getClayTableRegistry();
		_clayTableSerializer = ServletContextUtil.getClayTableSerializer();
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

	private void _setItems(String dataProviderKey) throws Exception {
		Map<String, Object> context = getContext();

		int pageNumber = GetterUtil.getInteger(context.get("pageNumber"));

		putValue("currentPage", pageNumber);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tableName = GetterUtil.getString(context.get("tableName"));

		CommerceDataSetDataProvider commerceDataSetDataProvider =
			_commerceDataProviderRegistry.getCommerceDataProvider(
				dataProviderKey);

		Filter filter = (Filter)context.get("filter");

		if (filter == null) {
			FilterFactory filterFactory =
				_filterFactoryRegistry.getFilterFactory(dataProviderKey);

			filter = filterFactory.create(request);
		}

		int itemPerPage = GetterUtil.getInteger(context.get("itemPerPage"));

		List<Object> items = commerceDataSetDataProvider.getItems(
			request, filter, new PaginationImpl(itemPerPage, pageNumber), null);

		String json = _clayTableDataJSONBuilder.build(
			themeDisplay.getScopeGroupId(), tableName, items, request);

		putValue("items", JSONFactoryUtil.looseDeserialize(json));

		putValue("pageSize", itemPerPage);

		int totalItems = commerceDataSetDataProvider.countItems(
			request, filter);

		putValue("totalItems", totalItems);
	}

	private void _setPagination() {
		Map<String, Object> context = getContext();

		PortletURL portletURL = (PortletURL)context.get("portletURL");
		String namespace = GetterUtil.getString(context.get("namespace"));
		String deltaParam = GetterUtil.getString(
			context.get("deltaParam"), SearchContainer.DEFAULT_DELTA_PARAM);

		List<ClayPaginationEntry> clayPaginationEntries =
			getClayPaginationEntries(portletURL, namespace, deltaParam);

		putValue("paginationEntries", clayPaginationEntries);

		Stream<ClayPaginationEntry> stream = clayPaginationEntries.stream();

		int itemPerPage = GetterUtil.getInteger(context.get("itemPerPage"));

		ClayPaginationEntry clayPaginationEntry = stream.filter(
			entry -> entry.getLabel() == itemPerPage
		).findAny(
		).orElse(
			null
		);

		int paginationSelectedEntry = clayPaginationEntries.indexOf(
			clayPaginationEntry);

		putValue("paginationSelectedEntry", paginationSelectedEntry);
	}

	private void _setTableContext(String tableName) {
		ClayTable clayTable = _clayTableRegistry.getClayTable(tableName);

		Map<String, Object> clayTableContext = _clayTableSerializer.serialize(
			clayTable);

		for (Map.Entry<String, Object> entry : clayTableContext.entrySet()) {
			putValue(entry.getKey(), entry.getValue());
		}

		putValue("id", clayTable.getId());

		Set<String> dependencies = new HashSet<>();

		List<ClayTableContextContributor> clayTablePostProcessors =
			_clayTableContextContributorRegistry.
				getClayTableContextContributors(tableName);

		for (ClayTableContextContributor clayTableContextContributor :
				clayTablePostProcessors) {

			clayTableContextContributor.contribute(
				clayTable, getContext(), request);

			Set<String> contextContributorDependencies =
				clayTableContextContributor.getDependencies(clayTable, request);

			if (contextContributorDependencies != null) {
				dependencies.addAll(contextContributorDependencies);
			}
		}

		setDependencies(dependencies);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTableTag.class);

	private ClayTableContextContributorRegistry
		_clayTableContextContributorRegistry;
	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private FilterFactoryRegistry _filterFactoryRegistry;

}