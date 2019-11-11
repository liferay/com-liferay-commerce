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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceBOMFolder. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.bom.model.CommerceBOMFolder addCommerceBOMFolder(
		long userId, long parentCommerceBOMFolderId, String name, boolean logo,
		byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMFolder(userId, parentCommerceBOMFolderId,
			name, logo, logoBytes);
	}

	public static void deleteCommerceBOMFolder(long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceBOMFolder(commerceBOMFolderId);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder getCommerceBOMFolder(
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMFolder(commerceBOMFolderId);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, int start, int end) {
		return getService().getCommerceBOMFolders(companyId, start, end);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, long parentCommerceBOMFolderId, int start, int end) {
		return getService()
				   .getCommerceBOMFolders(companyId, parentCommerceBOMFolderId,
			start, end);
	}

	public static int getCommerceBOMFoldersCount(long companyId) {
		return getService().getCommerceBOMFoldersCount(companyId);
	}

	public static int getCommerceBOMFoldersCount(long companyId,
		long parentCommerceBOMFolderId) {
		return getService()
				   .getCommerceBOMFoldersCount(companyId,
			parentCommerceBOMFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder updateCommerceBOMFolder(
		long commerceBOMFolderId, String name, boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceBOMFolder(commerceBOMFolderId, name, logo,
			logoBytes);
	}

	public static CommerceBOMFolderService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderService, CommerceBOMFolderService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderService.class);

		ServiceTracker<CommerceBOMFolderService, CommerceBOMFolderService> serviceTracker =
			new ServiceTracker<CommerceBOMFolderService, CommerceBOMFolderService>(bundle.getBundleContext(),
				CommerceBOMFolderService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}