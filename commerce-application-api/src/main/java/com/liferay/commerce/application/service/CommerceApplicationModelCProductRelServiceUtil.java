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
 * Provides the remote service utility for CommerceApplicationModelCProductRel. This utility wraps
 * {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelCProductRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelService
 * @see com.liferay.commerce.application.service.base.CommerceApplicationModelCProductRelServiceBaseImpl
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationModelCProductRelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelCProductRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.application.model.CommerceApplicationModelCProductRel addCommerceApplicationModelCProductRel(
		long userId, long commerceApplicationModelId, long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceApplicationModelCProductRel(userId,
			commerceApplicationModelId, cProductId);
	}

	public static void deleteCommerceApplicationModelCProductRel(
		long commerceApplicationModelCProductRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCommerceApplicationModelCProductRel(commerceApplicationModelCProductRelId);
	}

	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationModelCProductRel> getCommerceApplicationModelCProductRels(
		long commerceApplicationModelId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceApplicationModelCProductRels(commerceApplicationModelId,
			start, end);
	}

	public static int getCommerceApplicationModelCProductRelsCount(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceApplicationModelCProductRelsCount(commerceApplicationModelId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceApplicationModelCProductRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationModelCProductRelService, CommerceApplicationModelCProductRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationModelCProductRelService.class);

		ServiceTracker<CommerceApplicationModelCProductRelService, CommerceApplicationModelCProductRelService> serviceTracker =
			new ServiceTracker<CommerceApplicationModelCProductRelService, CommerceApplicationModelCProductRelService>(bundle.getBundleContext(),
				CommerceApplicationModelCProductRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}