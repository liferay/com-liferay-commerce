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
 * Provides the remote service utility for CommerceApplicationBrand. This utility wraps
 * {@link com.liferay.commerce.application.service.impl.CommerceApplicationBrandServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandService
 * @see com.liferay.commerce.application.service.base.CommerceApplicationBrandServiceBaseImpl
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationBrandServiceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationBrandServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.application.service.impl.CommerceApplicationBrandServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.application.model.CommerceApplicationBrand addCommerceApplicationBrand(
		long userId, String name, boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceApplicationBrand(userId, name, logo, logoBytes);
	}

	public static void deleteCommerceApplicationBrand(
		long commerceApplicationBrandId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceApplicationBrand(commerceApplicationBrandId);
	}

	public static com.liferay.commerce.application.model.CommerceApplicationBrand getCommerceApplicationBrand(
		long commerceApplicationBrandId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceApplicationBrand(commerceApplicationBrandId);
	}

	public static java.util.List<com.liferay.commerce.application.model.CommerceApplicationBrand> getCommerceApplicationBrands(
		long companyId, int start, int end) {
		return getService().getCommerceApplicationBrands(companyId, start, end);
	}

	public static int getCommerceApplicationBrandsCount(long companyId) {
		return getService().getCommerceApplicationBrandsCount(companyId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.application.model.CommerceApplicationBrand updateCommerceApplicationBrand(
		long commerceApplicationBrandId, String name, boolean logo,
		byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceApplicationBrand(commerceApplicationBrandId,
			name, logo, logoBytes);
	}

	public static CommerceApplicationBrandService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationBrandService, CommerceApplicationBrandService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationBrandService.class);

		ServiceTracker<CommerceApplicationBrandService, CommerceApplicationBrandService> serviceTracker =
			new ServiceTracker<CommerceApplicationBrandService, CommerceApplicationBrandService>(bundle.getBundleContext(),
				CommerceApplicationBrandService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}