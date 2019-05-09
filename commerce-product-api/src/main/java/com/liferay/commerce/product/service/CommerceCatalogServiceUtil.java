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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceCatalog. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CommerceCatalogServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceCatalogService
 * @see com.liferay.commerce.product.service.base.CommerceCatalogServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CommerceCatalogServiceImpl
 * @generated
 */
@ProviderType
public class CommerceCatalogServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CommerceCatalogServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog addCommerceCatalog(
		java.util.Map<java.util.Locale, String> nameMap,
		String catalogDefaultLanguageId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceCatalog(nameMap, catalogDefaultLanguageId,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog deleteCommerceCatalog(
		long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceCatalog(commerceCatalogId);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog fetchCommerceCatalog(
		long commerceCatalogId) {
		return getService().fetchCommerceCatalog(commerceCatalogId);
	}

	public static com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
		long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceCatalogGroup(commerceCatalogId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCommerceCatalogs(companyId);
	}

	public static java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCommerceCatalogs(companyId, keywords, start, end, sort);
	}

	public static int searchCommerceCatalogsCount(long companyId,
		String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCommerceCatalogsCount(companyId, keywords);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog updateCommerceCatalog(
		long commerceCatalogId, String catalogDefaultLanguageId,
		java.util.Map<java.util.Locale, String> nameMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceCatalog(commerceCatalogId,
			catalogDefaultLanguageId, nameMap, serviceContext);
	}

	public static CommerceCatalogService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceCatalogService, CommerceCatalogService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceCatalogService.class);

		ServiceTracker<CommerceCatalogService, CommerceCatalogService> serviceTracker =
			new ServiceTracker<CommerceCatalogService, CommerceCatalogService>(bundle.getBundleContext(),
				CommerceCatalogService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}