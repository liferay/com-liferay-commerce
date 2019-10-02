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
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
<<<<<<< HEAD:commerce-frontend-taglib/src/main/java/com/liferay/commerce/frontend/taglib/servlet/taglib/TableTag.java
public class TableTag extends ComponentRendererTag {
=======
public class CommerceTableTag extends IncludeTag {
>>>>>>> c668d9e4b... COMMERCE-609 Refactor commerce table tag:commerce-frontend-taglib/src/main/java/com/liferay/commerce/frontend/taglib/servlet/taglib/CommerceTableTag.java

	@Override
	public int doStartTag() throws JspException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			_setTableContext();
			_setItems();
			_setPagination();

			StringBundler sb = new StringBundler(11);

			sb.append(PortalUtil.getPortalURL(request));
			sb.append("/o/commerce-ui/commerce-data-set/");
			sb.append(themeDisplay.getScopeGroupId());
			sb.append(StringPool.FORWARD_SLASH);
			sb.append(_tableName);
			sb.append(StringPool.FORWARD_SLASH);
			sb.append(_dataProviderKey);
			sb.append("?plid=");
			sb.append(layout.getPlid());
			sb.append("&portletId=");
			sb.append(portletDisplay.getId());

			_dataSetAPI = sb.toString();

			_spritemap =
				themeDisplay.getPathThemeImages() + "/lexicon/icons.svg";
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return super.doStartTag();
	}

	public void setDeltaParam(String deltaParam) {
		_deltaParam = deltaParam;
	}

	public void setDisableAJAX(boolean disableAJAX) {
		_disableAJAX = disableAJAX;
	}

	public void setFilter(Filter filter) {
		_filter = filter;
	}

	public void setItemPerPage(int itemPerPage) {
		_itemPerPage = itemPerPage;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		_clayTableDataJSONBuilder =
			ServletContextUtil.getClayTableDataJSONBuilder();
		_clayTableRegistry = ServletContextUtil.getClayTableRegistry();
		_clayTableSerializer = ServletContextUtil.getClayTableSerializer();
		_commerceDataProviderRegistry =
			ServletContextUtil.getCommerceDataProviderRegistry();
		_filterFactoryRegistry = ServletContextUtil.getFilterFactoryRegistry();

		super.setPageContext(pageContext);
	}

	public void setPageNumber(int pageNumber) {
		_pageNumber = pageNumber;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setTableName(String tableName) {
		_tableName = tableName;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_clayTableContext = null;
		_dataProviderKey = null;
		_dataSetAPI = null;
		_deltaParam = null;
		_disableAJAX = false;
		_filter = null;
		_id = null;
		_itemPerPage = 0;
		_items = null;
		_namespace = null;
		_pageNumber = 0;
		_paginationEntries = null;
		_paginationSelectedEntry = 0;
		_portletURL = null;
		_showPagination = false;
		_spritemap = null;
		_tableName = null;
		_totalItems = 0;
	}

	protected List<ClayPaginationEntry> getClayPaginationEntries() {
		String portletURLString = _portletURL.toString();

		portletURLString = HttpUtil.removeParameter(
			portletURLString, _namespace + _deltaParam);

		List<ClayPaginationEntry> clayPaginationEntries = new ArrayList<>();

		for (int curDelta : PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES) {
			if (curDelta > SearchContainer.MAX_DELTA) {
				continue;
			}

			String curDeltaURL = HttpUtil.addParameter(
				portletURLString, _namespace + _deltaParam, curDelta);

			clayPaginationEntries.add(
				new ClayPaginationEntry(curDeltaURL, curDelta));
		}

		return clayPaginationEntries;
	}

	protected int getMinPageSize() {
		int[] searchContainerPageDeltaValues =
			PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES;

		if (searchContainerPageDeltaValues.length > 0) {
			return searchContainerPageDeltaValues[0];
		}

		return 5;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute(
			"liferay-commerce:table:clayTableContext", _clayTableContext);
		request.setAttribute(
			"liferay-commerce:table:dataProviderKey", _dataProviderKey);
		request.setAttribute("liferay-commerce:table:dataSetAPI", _dataSetAPI);
		request.setAttribute("liferay-commerce:table:deltaParam", _deltaParam);
		request.setAttribute(
			"liferay-commerce:table:disableAJAX", _disableAJAX);
		request.setAttribute("liferay-commerce:table:filter", _filter);
		request.setAttribute(
			"liferay-commerce:table:itemPerPage", _itemPerPage);
		request.setAttribute("liferay-commerce:table:items", _items);
		request.setAttribute("liferay-commerce:table:namespace", _namespace);
		request.setAttribute("liferay-commerce:table:pageNumber", _pageNumber);
		request.setAttribute(
			"liferay-commerce:table:paginationEntries", _paginationEntries);
		request.setAttribute(
			"liferay-commerce:table:paginationSelectedEntry",
			_paginationSelectedEntry);
		request.setAttribute("liferay-commerce:table:portletURL", _portletURL);
		request.setAttribute(
			"liferay-commerce:table:showPagination", _showPagination);
		request.setAttribute("liferay-commerce:table:spritemap", _spritemap);
		request.setAttribute("liferay-commerce:table:tableName", _tableName);
		request.setAttribute("liferay-commerce:table:totalItems", _totalItems);
	}

	private void _setItems() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		CommerceDataSetDataProvider commerceDataSetDataProvider =
			_commerceDataProviderRegistry.getCommerceDataProvider(
				_dataProviderKey);

		if (_filter == null) {
			FilterFactory filterFactory =
				_filterFactoryRegistry.getFilterFactory(_dataProviderKey);

			_filter = filterFactory.create(request);
		}

		List<Object> items = commerceDataSetDataProvider.getItems(
			request, _filter, new PaginationImpl(_itemPerPage, _pageNumber),
			null);

		String json = _clayTableDataJSONBuilder.build(
			themeDisplay.getScopeGroupId(), _tableName, items, request);

		_items = JSONFactoryUtil.looseDeserialize(json);

		_totalItems = commerceDataSetDataProvider.countItems(request, _filter);

		if (_totalItems > getMinPageSize()) {
			_showPagination = true;
		}
		else {
			_showPagination = false;
		}
	}

	private void _setPagination() {
		_paginationEntries = getClayPaginationEntries();

		Stream<ClayPaginationEntry> stream = _paginationEntries.stream();

		ClayPaginationEntry clayPaginationEntry = stream.filter(
			entry -> entry.getLabel() == _itemPerPage
		).findAny(
		).orElse(
			null
		);

		_paginationSelectedEntry = _paginationEntries.indexOf(
			clayPaginationEntry);
	}

	private void _setTableContext() {
		ClayTable clayTable = _clayTableRegistry.getClayTable(_tableName);

		_clayTableContext = _clayTableSerializer.serialize(clayTable);

		_id = clayTable.getId() + "CommerceTable";
	}

	private static final String _PAGE = "/table/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		TableTag.class);

	private Map<String, Object> _clayTableContext;
	private ClayTableDataJSONBuilder _clayTableDataJSONBuilder;
	private ClayTableRegistry _clayTableRegistry;
	private ClayTableSerializer _clayTableSerializer;
	private CommerceDataProviderRegistry _commerceDataProviderRegistry;
	private String _dataProviderKey;
	private String _dataSetAPI;
	private String _deltaParam;
	private boolean _disableAJAX;
	private Filter _filter;
	private FilterFactoryRegistry _filterFactoryRegistry;
	private String _id;
	private int _itemPerPage;
	private Object _items;
	private String _namespace;
	private int _pageNumber;
	private List<ClayPaginationEntry> _paginationEntries;
	private int _paginationSelectedEntry;
	private PortletURL _portletURL;
	private boolean _showPagination;
	private String _spritemap;
	private String _tableName;
	private int _totalItems;

}