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

package com.liferay.data.integration.web.internal.display.context;

import com.liferay.data.integration.constants.ProcessActionKeys;
import com.liferay.data.integration.model.Process;
import com.liferay.data.integration.service.ProcessService;
import com.liferay.data.integration.web.internal.admin.ProcessListAdminModule;
import com.liferay.data.integration.web.internal.display.context.util.DataIntegrationRequestHelper;
import com.liferay.data.integration.web.internal.portlet.action.ActionHelper;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guywandji
 */
public class DataIntegrationProcessListDisplayContext {

	public DataIntegrationProcessListDisplayContext(
		PortletResourcePermission portletResourcePermission,
		ProcessService processService, Portal portal, ActionHelper actionHelper,
		RenderRequest renderRequest) {

		_portletResourcePermission = portletResourcePermission;
		_processService = processService;

		_dataIntegrationRequestHelper = new DataIntegrationRequestHelper(
			renderRequest);

		_portal = portal;

		_actionHelper = actionHelper;

		DataIntegrationRequestHelper dataIntegrationRequestHelper =
			new DataIntegrationRequestHelper(renderRequest);

		liferayPortletRequest =
			dataIntegrationRequestHelper.getLiferayPortletRequest();

		liferayPortletResponse =
			dataIntegrationRequestHelper.getLiferayPortletResponse();

		httpServletRequest = _portal.getHttpServletRequest(renderRequest);

		_defaultOrderByCol = "modified-date";

		_defaultOrderByType = "desc";
	}

	public PortletURL getAddProcessURL() throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editProcess");

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", ProcessListAdminModule.KEY);

		return portletURL;
	}

	public String getDeleteProcessURL(long processId) throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(ActionRequest.ACTION_NAME, "editProcess");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

		portletURL.setParameter("processId", String.valueOf(processId));

		return portletURL.toString();
	}

	public PortletURL getEditProcessURL(long processId) throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editProcess");

		portletURL.setParameter("processId", String.valueOf(processId));

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", ProcessListAdminModule.KEY);

		return portletURL;
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
			"dataIntegrationAdminModuleKey", ProcessListAdminModule.KEY);

		return portletURL;
	}

	public Process getProcess() throws PortalException {
		return _actionHelper.getProcess(liferayPortletRequest);
	}

	public long getProcessId() throws PortalException {
		Process process = getProcess();

		if (process != null) {
			return process.getProcessId();
		}

		return 0L;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(liferayPortletResponse);
		}

		return _rowChecker;
	}

	public SearchContainer<Process> getSearchContainer()
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

		int total = _processService.getProcessesByGroupIdCount(
			_dataIntegrationRequestHelper.getUserId(),
			_dataIntegrationRequestHelper.getScopeGroupId());

		_searchContainer.setTotal(total);

		List<Process> results = _processService.getProcessesByGroupId(
			_dataIntegrationRequestHelper.getUserId(),
			_dataIntegrationRequestHelper.getScopeGroupId(),
			_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasAdminPermission() {
		return _portletResourcePermission.contains(
			_dataIntegrationRequestHelper.getPermissionChecker(),
			_dataIntegrationRequestHelper.getSiteGroupId(),
			ProcessActionKeys.MANAGE_PROCESS);
	}

	protected HttpServletRequest httpServletRequest;
	protected LiferayPortletRequest liferayPortletRequest;
	protected LiferayPortletResponse liferayPortletResponse;

	private final ActionHelper _actionHelper;
	private final DataIntegrationRequestHelper _dataIntegrationRequestHelper;
	private final String _defaultOrderByCol;
	private final String _defaultOrderByType;
	private String _keywords;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;
	private final ProcessService _processService;
	private RowChecker _rowChecker;
	private SearchContainer<Process> _searchContainer;

}