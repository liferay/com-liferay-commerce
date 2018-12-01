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

import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.ProcessLocalService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.admin.ScheduledTasksAdminModule;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.util.DataIntegrationRequestHelper;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.action.ActionHelper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
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
public class ScheduledTasksDataIntegrationDisplayContext {

	public ScheduledTasksDataIntegrationDisplayContext(
		PortletResourcePermission portletResourcePermission,
		ScheduledTaskLocalService scheduledTaskLocalService,
		ProcessLocalService processLocalService, Portal portal,
		ActionHelper actionHelper, SchedulerEngineHelper schedulerEngineHelper,
		RenderRequest renderRequest) {

		_portletResourcePermission = portletResourcePermission;
		_scheduledTaskLocalService = scheduledTaskLocalService;
		_processLocalService = processLocalService;

		_dataIntegrationRequestHelper = new DataIntegrationRequestHelper(
			renderRequest);

		_portal = portal;

		_actionHelper = actionHelper;
		_schedulerEngineHelper = schedulerEngineHelper;

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

	public PortletURL getAddScheduledTaskURL(long scheduledTaskId)
		throws PortalException {

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editScheduledTask");

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", ScheduledTasksAdminModule.KEY);

		return portletURL;
	}

	public String getDeleteScheduledTaskURL(long scheduledTaskId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(ActionRequest.ACTION_NAME, "editScheduledTask");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter(
			"scheduledTaskId", String.valueOf(scheduledTaskId));

		return portletURL.toString();
	}

	public PortletURL getEditScheduledTaskURL(long scheduledTaskId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editScheduledTask");

		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

		portletURL.setParameter(
			"scheduledTaskId", String.valueOf(scheduledTaskId));

		portletURL.setParameter(
			"dataIntegrationAdminModuleKey", ScheduledTasksAdminModule.KEY);

		return portletURL;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(httpServletRequest, "keywords");

		return _keywords;
	}

	public String getLastExecutionDate(ScheduledTask scheduledTask) {
		String lastExecutionDate = LanguageUtil.get(
			httpServletRequest, "not-yet-executed");

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getScopeGroup();

		try {
			SchedulerResponse schedulerResponse =
				_schedulerEngineHelper.getScheduledJob(
					scheduledTask.getName(), group.getNameCurrentValue(),
					StorageType.PERSISTED);

			if (schedulerResponse != null) {
				Date lastFireTime = _schedulerEngineHelper.getPreviousFireTime(
					schedulerResponse);

				if (lastFireTime != null) {
					lastExecutionDate = _simpleDateFormat.format(lastFireTime);
				}
			}
		}
		catch (SchedulerException se) {
			se.printStackTrace();
		}

		return lastExecutionDate;
	}

	public String getNextExecutionDate(ScheduledTask scheduledTask) {
		String nextDate = LanguageUtil.get(
			httpServletRequest, "not-yet-scheduled");

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getScopeGroup();

		try {
			SchedulerResponse schedulerResponse =
				_schedulerEngineHelper.getScheduledJob(
					scheduledTask.getName(), group.getNameCurrentValue(),
					StorageType.PERSISTED);

			if (schedulerResponse != null) {
				Date nextFireTime = _schedulerEngineHelper.getNextFireTime(
					schedulerResponse);

				if (nextFireTime != null) {
					nextDate = _simpleDateFormat.format(nextFireTime);
				}
			}
		}
		catch (SchedulerException se) {
			se.printStackTrace();
		}

		return nextDate;
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
			"dataIntegrationAdminModuleKey", ScheduledTasksAdminModule.KEY);

		return portletURL;
	}

	public List<Process> getProcessList() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _processLocalService.getProcessesByGroupId(
			themeDisplay.getScopeGroupId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(liferayPortletResponse);
		}

		return _rowChecker;
	}

	public String getRunScheduledTaskURL(long scheduledTaskId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(Constants.CMD, Constants.TEST);
		portletURL.setParameter(ActionRequest.ACTION_NAME, "editScheduledTask");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter(
			"scheduledTaskId", String.valueOf(scheduledTaskId));

		return portletURL.toString();
	}

	public ScheduledTask getScheduledTask() throws PortalException {
		return _actionHelper.getScheduledTask(liferayPortletRequest);
	}

	public long getScheduledTaskId() throws PortalException {
		ScheduledTask scheduledTask = _actionHelper.getScheduledTask(
			liferayPortletRequest);

		if (scheduledTask != null) {
			return scheduledTask.getScheduledTaskId();
		}

		return 0L;
	}

	public SearchContainer<ScheduledTask> getSearchContainer()
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

		int total = _scheduledTaskLocalService.getScheduledTaskByGroupIdCount(
			_dataIntegrationRequestHelper.getScopeGroupId());

		_searchContainer.setTotal(total);

		List<ScheduledTask> results =
			_scheduledTaskLocalService.getScheduledTaskByGroupId(
				_dataIntegrationRequestHelper.getScopeGroupId(),
				_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasAdminPermission() {
		/*return _portletResourcePermission.contains(
		_lrDataIntegrationRequestHelper.getPermissionChecker(),
		_lrDataIntegrationRequestHelper.getSiteGroupId(),
		ActionKeys.ACCESS_IN_CONTROL_PANEL);*/

		return true;
	}

	protected HttpServletRequest httpServletRequest;
	protected LiferayPortletRequest liferayPortletRequest;
	protected LiferayPortletResponse liferayPortletResponse;

	private final ActionHelper _actionHelper;
	private final DataIntegrationRequestHelper _dataIntegrationRequestHelper;
	private String _datePattern = "yyyy-dd-MM HH:mm";
	private final String _defaultOrderByCol;
	private final String _defaultOrderByType;
	private String _keywords;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;
	private final ProcessLocalService _processLocalService;
	private RowChecker _rowChecker;
	private final ScheduledTaskLocalService _scheduledTaskLocalService;
	private final SchedulerEngineHelper _schedulerEngineHelper;
	private SearchContainer<ScheduledTask> _searchContainer;
	private final SimpleDateFormat _simpleDateFormat;

}