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
 * Provides the remote service utility for CommerceBOMDefinition. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMDefinitionServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
		String name, String friendlyUrl)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMDefinition(userId, commerceBOMFolderId,
			cpAttachmentFileEntryId, name, friendlyUrl);
	}

	public static void deleteCommerceBOMDefinition(long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMDefinition getCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMDefinition> getCommerceBOMDefinitions(
		long commerceBOMFolderId, int start, int end) {
		return getService()
				   .getCommerceBOMDefinitions(commerceBOMFolderId, start, end);
	}

	public static int getCommerceBOMDefinitionsCount(long commerceBOMFolderId) {
		return getService().getCommerceBOMDefinitionsCount(commerceBOMFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		long commerceBOMDefinitionId, long cpAttachmentFileEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceBOMDefinition(commerceBOMDefinitionId,
			cpAttachmentFileEntryId, name);
	}

	public static CommerceBOMDefinitionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMDefinitionService, CommerceBOMDefinitionService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMDefinitionService.class);

		ServiceTracker<CommerceBOMDefinitionService, CommerceBOMDefinitionService> serviceTracker =
			new ServiceTracker<CommerceBOMDefinitionService, CommerceBOMDefinitionService>(bundle.getBundleContext(),
				CommerceBOMDefinitionService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}