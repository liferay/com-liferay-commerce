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

package com.liferay.commerce.application.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceApplicationModel. This utility wraps
 * {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelService
 * @see com.liferay.commerce.application.service.base.CommerceApplicationModelServiceBaseImpl
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationModelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationModelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.application.model.CommerceApplicationModel addCommerceApplicationModel(
		long userId, long commerceApplicationBrandId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceApplicationModel(userId,
			commerceApplicationBrandId, name, year);
	}

	public static void deleteCommerceApplicationModel(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceApplicationModel(commerceApplicationModelId);
	}

	public static com.liferay.commerce.application.model.CommerceApplicationModel getCommerceApplicationModel(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceApplicationModel(commerceApplicationModelId);
	}

	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationModel> getCommerceApplicationModels(
		long commerceApplicationBrandId, int start, int end) {
		return getService()
				   .getCommerceApplicationModels(commerceApplicationBrandId,
			start, end);
	}

	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationModel> getCommerceApplicationModelsByCompanyId(
		long companyId, int start, int end) {
		return getService()
				   .getCommerceApplicationModelsByCompanyId(companyId, start,
			end);
	}

	public static int getCommerceApplicationModelsCount(
		long commerceApplicationBrandId) {
		return getService()
				   .getCommerceApplicationModelsCount(commerceApplicationBrandId);
	}

	public static int getCommerceApplicationModelsCountByCompanyId(
		long companyId) {
		return getService()
				   .getCommerceApplicationModelsCountByCompanyId(companyId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.application.model.CommerceApplicationModel updateCommerceApplicationModel(
		long commerceApplicationModelId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceApplicationModel(commerceApplicationModelId,
			name, year);
	}

	public static CommerceApplicationModelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationModelService, CommerceApplicationModelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationModelService.class);

		ServiceTracker<CommerceApplicationModelService, CommerceApplicationModelService> serviceTracker =
			new ServiceTracker<CommerceApplicationModelService, CommerceApplicationModelService>(bundle.getBundleContext(),
				CommerceApplicationModelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}