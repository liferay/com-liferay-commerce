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

package com.liferay.commerce.data.integration.manager.web.internal.display.context;

import com.liferay.commerce.data.integration.manager.model.History;
import com.liferay.commerce.data.integration.manager.service.HistoryLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.admin.HistoryAdminModule;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.util.DataIntegrationRequestHelper;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.action.ActionHelper;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guywandji
 */
public class HistoryDataIntegrationDisplayContext {

	public HistoryDataIntegrationDisplayContext(
		PortletResourcePermission portletResourcePermission,
		HistoryLocalService historyLocalService,
		DLFileEntryService dlFileEntryLocalService, Portal portal,
		ActionHelper actionHelper, RenderRequest renderRequest) {

		_portletResourcePermission = portletResourcePermission;
		_historyLocalService = historyLocalService;

		_dataIntegrationRequestHelper = new DataIntegrationRequestHelper(
			renderRequest);

		_portal = portal;

		_actionHelper = actionHelper;

		_dlFileEntryService = dlFileEntryLocalService;

		DataIntegrationRequestHelper dataIntegrationRequestHelper =
			new DataIntegrationRequestHelper(renderRequest);

		liferayPortletRequest =
			dataIntegrationRequestHelper.getLiferayPortletRequest();

		liferayPortletResponse =
			dataIntegrationRequestHelper.getLiferayPortletResponse();

		httpServletRequest = _portal.getHttpServletRequest(renderRequest);

		_defaultOrderByCol = "modified-date";

		_defaultOrderByType = "desc";

		_simpleDateFormat = new SimpleDateFormat(_datePattern);
	}

	public String getDeleteHistoryURL(long historyId) throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(ActionRequest.ACTION_NAME, "editHistory");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter("historyId", String.valueOf(historyId));

		return portletURL.toString();
	}

	public String getErroLogFileEntryURL() throws PortalException {
		String url = "";
		History history = getHistory();

		if (history.getErrorLogFileEntryId() > 0) {
			url = _getFileEntryURL(history.getErrorLogFileEntryId());
		}

		return url;
	}

	public String getFormattedDate(Date date) {
		if (date != null) {
			return _simpleDateFormat.format(date);
		}

		return "";
	}

	public History getHistory() throws PortalException {
		return _actionHelper.getHistory(liferayPortletRequest);
	}

	public long getHistoryId() throws PortalException {
		History history = getHistory();

		if (history != null) {
			return history.getHistoryId();
		}

		return 0L;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(httpServletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			_defaultOrderByCol);
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			_defaultOrderByType);
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			_dataIntegrationRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String delta = ParamUtil.getString(
			_dataIntegrationRequestHelper.getRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			_dataIntegrationRequestHelper.getRequest(), "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", HistoryAdminModule.KEY);

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(liferayPortletResponse);
		}

		return _rowChecker;
	}

	public String getRuntimeLogFileEntryURL() throws PortalException {
		String url = "";
		History history = getHistory();

		if (history.getRuntimeLogFileEntryId() > 0) {
			url = _getFileEntryURL(history.getRuntimeLogFileEntryId());
		}

		return url;
	}

	public SearchContainer<History> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_dataIntegrationRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("no-items-were-found");
		_searchContainer.setOrderByCol(getOrderByCol());
		_searchContainer.setOrderByComparator(null);
		_searchContainer.setOrderByType(getOrderByType());
		_searchContainer.setRowChecker(getRowChecker());

		int total = _historyLocalService.getHistoriesByGoupIdCount(
			_dataIntegrationRequestHelper.getScopeGroupId());

		_searchContainer.setTotal(total);

		List<History> results = _historyLocalService.getHistoriesByGoupId(
			_dataIntegrationRequestHelper.getScopeGroupId(),
			_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public String getViewHistoryDetailsURL(long historyId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "viewHistoryDetails");

		portletURL.setParameter("historyId", String.valueOf(historyId));

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", HistoryAdminModule.KEY);

		portletURL.setParameter("backURL", themeDisplay.getURLCurrent());

		return portletURL.toString();
	}

	public boolean hasAdminPermission() {
		return true;
	}

	protected HttpServletRequest httpServletRequest;
	protected LiferayPortletRequest liferayPortletRequest;
	protected LiferayPortletResponse liferayPortletResponse;

	private String _getFileEntryURL(long fileEntryId) throws PortalException {
		DLFileEntry fileEntry = _dlFileEntryService.getFileEntry(fileEntryId);

		long folderId = fileEntry.getFolderId();
		String title = fileEntry.getTitle();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		StringBundler urlSB = new StringBundler(8);

		urlSB.append(themeDisplay.getPortalURL());
		urlSB.append(themeDisplay.getPathContext());
		urlSB.append("/documents/");
		urlSB.append(themeDisplay.getScopeGroupId());
		urlSB.append("/");
		urlSB.append(folderId);
		urlSB.append("/");

		String unescapeTitle = HtmlUtil.unescape(title);

		String encodeTitle = HttpUtil.encodeURL(unescapeTitle);

		urlSB.append(encodeTitle);

		return urlSB.toString();
	}

	private final ActionHelper _actionHelper;
	private final DataIntegrationRequestHelper _dataIntegrationRequestHelper;
	private String _datePattern = "yyyy-dd-MM HH:mm:ss";
	private final String _defaultOrderByCol;
	private final String _defaultOrderByType;
	private final DLFileEntryService _dlFileEntryService;
	private final HistoryLocalService _historyLocalService;
	private String _keywords;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<History> _searchContainer;
	private final SimpleDateFormat _simpleDateFormat;

}