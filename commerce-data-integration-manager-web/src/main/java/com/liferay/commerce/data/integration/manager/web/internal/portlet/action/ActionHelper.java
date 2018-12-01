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

package com.liferay.commerce.data.integration.manager.web.internal.portlet.action;

import com.liferay.commerce.data.integration.manager.model.History;
import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.HistoryLocalService;
import com.liferay.commerce.data.integration.manager.service.ProcessLocalService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DIWebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(immediate = true, service = ActionHelper.class)
public class ActionHelper {

	public History getHistory(PortletRequest portletRequest)
		throws PortalException {

		History history = (History)portletRequest.getAttribute(
			DIWebKeys.DI_HISTORY);

		if (history != null) {
			return history;
		}

		long historyId = ParamUtil.getLong(portletRequest, "historyId");

		if (historyId > 0) {
			history = _historyLocalService.fetchHistory(historyId);
		}

		if (history != null) {
			portletRequest.setAttribute(DIWebKeys.DI_HISTORY, history);
		}

		return history;
	}

	public Process getProcess(PortletRequest portletRequest)
		throws PortalException {

		Process process = (Process)portletRequest.getAttribute(
			DIWebKeys.DI_PROCESS);

		if (process != null) {
			return process;
		}

		long processId = ParamUtil.getLong(portletRequest, "processId");

		if (processId > 0) {
			process = _processLocalService.fetchProcess(processId);
		}

		if (process != null) {
			portletRequest.setAttribute(DIWebKeys.DI_PROCESS, process);
		}

		return process;
	}

	public ScheduledTask getScheduledTask(PortletRequest portletRequest)
		throws PortalException {

		ScheduledTask scheduledTask =
			(ScheduledTask)portletRequest.getAttribute(
				DIWebKeys.DI_SCHEDULED_TASK);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		long scheduledTaskId = ParamUtil.getLong(
			portletRequest, "scheduledTaskId");

		if (scheduledTaskId > 0) {
			scheduledTask = _scheduledTaskLocalService.fetchScheduledTask(
				scheduledTaskId);
		}

		if (scheduledTask != null) {
			portletRequest.setAttribute(
				DIWebKeys.DI_SCHEDULED_TASK, scheduledTask);
		}

		return scheduledTask;
	}

	@Reference
	private HistoryLocalService _historyLocalService;

	@Reference
	private ProcessLocalService _processLocalService;

	@Reference
	private ScheduledTaskLocalService _scheduledTaskLocalService;

}