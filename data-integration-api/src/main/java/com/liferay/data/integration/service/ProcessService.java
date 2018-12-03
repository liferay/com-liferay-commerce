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

import com.liferay.data.integration.model.Process;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for Process. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessServiceUtil
 * @see com.liferay.data.integration.service.base.ProcessServiceBaseImpl
 * @see com.liferay.data.integration.service.impl.ProcessServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=data_integration", "json.web.service.context.path=Process"}, service = ProcessService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ProcessService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessServiceUtil} to access the process remote service. Add custom service methods to {@link com.liferay.data.integration.service.impl.ProcessServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link ProcessServiceUtil} to access the process remote service.
	*
	* @throws PortalException
	*/
	public Process addProcess(String name, String className,
		String processType, String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId, ServiceContext serviceContext)
		throws PortalException;

	public Process deleteProcess(long userId, long processId,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Process getProcess(long userId, long processId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Process> getProcessesByGroupId(long userId, long groupId,
		int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProcessesByGroupIdCount(long userId, long groupId)
		throws PortalException;

	public Process updateProcess(long processId, String name, String className,
		String processType, String version, long contextPropertiesFileEntryId,
		long srcArchiveFileEntryId, ServiceContext serviceContext)
		throws PortalException;
}