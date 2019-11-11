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
 * Provides the remote service utility for CommerceBOMFolderApplicationRel. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderApplicationRelServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
		long userId, long commerceBOMFolderId, long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMFolderApplicationRel(userId,
			commerceBOMFolderId, commerceApplicationModelId);
	}

	public static void deleteCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMFolderApplicationRelsByCAMId(commerceApplicationModelId,
			start, end);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(commerceBOMFolderId,
			start, end);
	}

	public static int getCommerceBOMFolderApplicationRelsCountByCAMId(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMFolderApplicationRelsCountByCAMId(commerceApplicationModelId);
	}

	public static int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceBOMFolderApplicationRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderApplicationRelService, CommerceBOMFolderApplicationRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderApplicationRelService.class);

		ServiceTracker<CommerceBOMFolderApplicationRelService, CommerceBOMFolderApplicationRelService> serviceTracker =
			new ServiceTracker<CommerceBOMFolderApplicationRelService, CommerceBOMFolderApplicationRelService>(bundle.getBundleContext(),
				CommerceBOMFolderApplicationRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}