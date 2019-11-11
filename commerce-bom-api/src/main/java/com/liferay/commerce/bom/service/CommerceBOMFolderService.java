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

package com.liferay.commerce.bom.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.model.CommerceBOMFolder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for CommerceBOMFolder. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderServiceUtil
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceBOMFolder"}, service = CommerceBOMFolderService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceBOMFolderService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBOMFolderServiceUtil} to access the commerce bom folder remote service. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceBOMFolder addCommerceBOMFolder(long userId,
		long parentCommerceBOMFolderId, String name, boolean logo,
		byte[] logoBytes) throws PortalException;

	public void deleteCommerceBOMFolder(long commerceBOMFolderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceBOMFolder getCommerceBOMFolder(long commerceBOMFolderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceBOMFolder> getCommerceBOMFolders(long companyId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceBOMFolder> getCommerceBOMFolders(long companyId,
		long parentCommerceBOMFolderId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceBOMFoldersCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceBOMFoldersCount(long companyId,
		long parentCommerceBOMFolderId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	public CommerceBOMFolder updateCommerceBOMFolder(long commerceBOMFolderId,
		String name, boolean logo, byte[] logoBytes) throws PortalException;
}