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

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;

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
 * Provides the remote service interface for CommerceBOMFolderApplicationRel. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelServiceUtil
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderApplicationRelServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceBOMFolderApplicationRel"}, service = CommerceBOMFolderApplicationRelService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceBOMFolderApplicationRelService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBOMFolderApplicationRelServiceUtil} to access the commerce bom folder application rel remote service. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
		long userId, long commerceBOMFolderId, long commerceApplicationModelId)
		throws PortalException;

	public void deleteCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceBOMFolderApplicationRelsCountByCAMId(
		long commerceApplicationModelId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
		long commerceBOMFolderId) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();
}