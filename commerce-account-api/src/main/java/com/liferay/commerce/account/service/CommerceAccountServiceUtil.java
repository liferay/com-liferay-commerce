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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceAccount. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountService
 * @see com.liferay.commerce.account.service.base.CommerceAccountServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		long userId, long parentCommerceAccountId, String name, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAccount(userId, parentCommerceAccountId, name,
			taxId, active, externalReferenceCode, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		long companyId, long parentCommerceAccountId, String keywords,
		Boolean active, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCommerceAccounts(companyId, parentCommerceAccountId,
			keywords, active, start, end, sort);
	}

	public static com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		long commerceAccountId, String name, String taxId, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceAccount(commerceAccountId, name, taxId,
			active, serviceContext);
	}

	public static com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		long userId, long parentCommerceAccountId, String name, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .upsertCommerceAccount(userId, parentCommerceAccountId,
			name, taxId, active, externalReferenceCode, serviceContext);
	}

	public static CommerceAccountService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountService, CommerceAccountService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountService.class);

		ServiceTracker<CommerceAccountService, CommerceAccountService> serviceTracker =
			new ServiceTracker<CommerceAccountService, CommerceAccountService>(bundle.getBundleContext(),
				CommerceAccountService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}