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

package com.liferay.commerce.data.integration.manager.service.impl;

import com.liferay.commerce.data.integration.manager.model.History;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.base.HistoryLocalServiceBaseImpl;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the history local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link HistoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoryLocalServiceBaseImpl
 * @see HistoryLocalServiceUtil
 */
public class HistoryLocalServiceImpl extends HistoryLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link HistoryLocalServiceUtil} to access the history local service.
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public History addHistory(
			long userId, long scheduledTaskId, String executionType,
			Date startDate, Date endDate, int status, long errorLogFileEntryId,
			long runtimeLogFileEntryId)
		throws PortalException {

		long historyId = counterLocalService.increment(History.class.getName());

		Date now = new Date();

		User user = userLocalService.getUser(userId);

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		History history = historyPersistence.create(historyId);

		history.setCompanyId(scheduledTask.getCompanyId());
		history.setGroupId(scheduledTask.getGroupId());
		history.setCreateDate(now);
		history.setStatus(status);
		history.setUserName(user.getFullName());
		history.setUserId(user.getUserId());
		history.setScheduledTaskId(scheduledTaskId);
		history.setLaunchType(executionType);
		history.setStartDate(startDate);
		history.setEndDate(endDate);
		history.setErrorLogFileEntryId(errorLogFileEntryId);
		history.setRuntimeLogFileEntryId(runtimeLogFileEntryId);

		resourceLocalService.addResources(
			user.getCompanyId(), scheduledTask.getGroupId(), userId,
			History.class.getName(), historyId, false, true, true);

		return historyPersistence.update(history);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public History addHistory(
			long scheduledTaskId, String executionType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		long historyId = counterLocalService.increment(History.class.getName());

		Date now = new Date();

		User user = userLocalService.getUser(serviceContext.getUserId());

		History history = historyPersistence.create(historyId);

		history.setGroupId(serviceContext.getScopeGroupId());
		history.setCompanyId(serviceContext.getCompanyId());
		history.setUserId(user.getUserId());
		history.setUserName(user.getFullName());
		history.setCreateDate(now);
		history.setScheduledTaskId(scheduledTaskId);
		history.setStatus(status);

		resourceLocalService.addModelResources(history, serviceContext);

		return historyPersistence.update(history);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public History delete(long historyId) throws PortalException {
		History history = historyPersistence.fetchByPrimaryKey(historyId);

		resourceLocalService.deleteResource(
			history, ResourceConstants.SCOPE_INDIVIDUAL);

		if (history.getErrorLogFileEntryId() > 0) {
			_dlFileEntryLocalService.deleteDLFileEntry(
				history.getErrorLogFileEntryId());
		}

		if (history.getRuntimeLogFileEntryId() > 0) {
			_dlFileEntryLocalService.deleteDLFileEntry(
				history.getRuntimeLogFileEntryId());
		}

		return historyPersistence.remove(history);
	}

	public List<History> getHistoriesByGoupId(
		long groupId, int start, int end) {

		return historyPersistence.findByGroupId(groupId, start, end);
	}

	public int getHistoriesByGoupIdCount(long groupId) {
		return historyPersistence.countByGroupId(groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public History updateHistory(
			long historyId, long scheduledTaskId, String executionType,
			int status, ServiceContext serviceContext)
		throws PortalException {

		Date now = new Date();

		User user = userLocalService.getUser(serviceContext.getUserId());

		History history = historyPersistence.fetchByPrimaryKey(historyId);

		history.setUserId(user.getUserId());
		history.setUserName(user.getFullName());
		history.setModifiedDate(now);
		history.setScheduledTaskId(scheduledTaskId);
		history.setStatus(status);

		return historyPersistence.update(history);
	}

	@ServiceReference(type = DLFileEntryLocalService.class)
	private DLFileEntryLocalService _dlFileEntryLocalService;

}