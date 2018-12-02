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

import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.service.base.ProcessLocalServiceBaseImpl;
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
 * The implementation of the process local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link ProcessLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessLocalServiceBaseImpl
 * @see ProcessLocalServiceUtil
 */
public class ProcessLocalServiceImpl extends ProcessLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link ProcessLocalServiceUtil} to access the process local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Process addProcess(
			String name, String className, String processType, String version,
			long contextPropertiesFileEntryId, long srcArchiveFileEntryId,
			ServiceContext serviceContext)
		throws PortalException {

		_validateProcess(contextPropertiesFileEntryId, srcArchiveFileEntryId);

		long processId = counterLocalService.increment(Process.class.getName());

		User user = userLocalService.getUser(serviceContext.getUserId());

		Process process = processPersistence.create(processId);

		Date now = new Date();

		process.setClassName(className);
		process.setCompanyId(serviceContext.getCompanyId());
		process.setCreateDate(now);
		process.setGroupId(serviceContext.getScopeGroupId());
		process.setModifiedDate(now);
		process.setName(name);
		process.setProcessType(processType);
		process.setUserId(serviceContext.getUserId());
		process.setUserName(user.getFullName());
		process.setContextPropertiesFileEntryId(contextPropertiesFileEntryId);
		process.setSrcArchiveFileEntryId(srcArchiveFileEntryId);
		process.setVersion(version);

		resourceLocalService.addModelResources(process, serviceContext);

		return processPersistence.update(process);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Process deleteProcess(long processId) throws PortalException {
		Process process = processPersistence.fetchByPrimaryKey(processId);

		if (process.getContextPropertiesFileEntryId() > 0) {
			_dlFileEntryLocalService.deleteDLFileEntry(
				process.getContextPropertiesFileEntryId());
		}

		_dlFileEntryLocalService.deleteDLFileEntry(
			process.getSrcArchiveFileEntryId());

		resourceLocalService.deleteResource(
			process, ResourceConstants.SCOPE_INDIVIDUAL);

		return processPersistence.remove(processId);
	}

	public Process getProcess(long processId) {
		return processPersistence.fetchByPrimaryKey(processId);
	}

	public List<Process> getProcessesByGroupId(
		long groupId, int start, int end) {

		return processPersistence.findByGroupId(groupId, start, end);
	}

	public int getProcessesByGroupIdCount(long groupId) {
		return processPersistence.countByGroupId(groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Process updateProcess(
			long processId, String name, String className, String processType,
			String version, long contextPropertiesFileEntryId,
			long srcArchiveFileEntryId, ServiceContext serviceContext)
		throws PortalException {

		_validateProcess(contextPropertiesFileEntryId, srcArchiveFileEntryId);

		User user = userLocalService.getUser(serviceContext.getUserId());

		Process process = processPersistence.fetchByPrimaryKey(processId);

		Date now = new Date();

		process.setUserId(serviceContext.getUserId());
		process.setUserName(user.getFullName());
		process.setModifiedDate(now);
		process.setName(name);
		process.setVersion(version);
		process.setClassName(className);
		process.setProcessType(processType);
		process.setContextPropertiesFileEntryId(contextPropertiesFileEntryId);
		process.setSrcArchiveFileEntryId(srcArchiveFileEntryId);

		return processPersistence.update(process);
	}

	private boolean _validateProcess(
			long contextPropertiesFileEntryId, long srcArchiveFileEntryId)
		throws PortalException {

		if ((contextPropertiesFileEntryId == 0) &&
			(srcArchiveFileEntryId == 0)) {

			throw new PortalException("source file is mandatory");
		}

		if (contextPropertiesFileEntryId > 0) {
			_dlFileEntryLocalService.fetchDLFileEntry(
				contextPropertiesFileEntryId);
		}

		if (srcArchiveFileEntryId > 0) {
			_dlFileEntryLocalService.getDLFileEntry(srcArchiveFileEntryId);
		}

		return true;
	}

	@ServiceReference(type = DLFileEntryLocalService.class)
	private DLFileEntryLocalService _dlFileEntryLocalService;

}