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

package com.liferay.data.integration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.integration.model.ScheduledTask;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service interface for ScheduledTask. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskServiceUtil
 * @see com.liferay.data.integration.service.base.ScheduledTaskServiceBaseImpl
 * @see com.liferay.data.integration.service.impl.ScheduledTaskServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=data_integration", "json.web.service.context.path=ScheduledTask"}, service = ScheduledTaskService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ScheduledTaskService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduledTaskServiceUtil} to access the scheduled task remote service. Add custom service methods to {@link com.liferay.data.integration.service.impl.ScheduledTaskServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ScheduledTaskServiceUtil} to access the scheduled task remote service.
	*/
	public ScheduledTask addScheduledTask(long processId, String frequency,
		Date startDate, String startHour, String name,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScheduledTask> getScheduledTaskByGroupId(long groupId,
		int start, int end) throws PrincipalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getScheduledTaskByGroupIdCount(long groupId)
		throws PrincipalException;

	public ScheduledTask updateScheduledTask(long scheduledTaskId,
		long processId, String frequency, Date startDate, String startHour,
		String name, ServiceContext serviceContext) throws PortalException;
}