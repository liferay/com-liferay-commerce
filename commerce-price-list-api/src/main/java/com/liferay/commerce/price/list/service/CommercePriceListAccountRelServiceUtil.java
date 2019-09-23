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

package com.liferay.commerce.price.list.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommercePriceListAccountRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelService
 * @generated
 */
public class CommercePriceListAccountRelServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePriceListAccountRelServiceUtil} to access the commerce price list account rel remote service. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				addCommercePriceListAccountRel(
					long commercePriceListId, long commerceAccountId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePriceListAccountRel(
			commercePriceListId, commerceAccountId, order, serviceContext);
	}

	public static void deleteCommercePriceListAccountRel(
			long commercePriceListAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				fetchCommercePriceListAccountRel(
					long commercePriceListId, long commerceAccountId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommercePriceListAccountRel(
			commercePriceListId, commerceAccountId);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
				getCommercePriceListAccountRels(long commercePriceListId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListAccountRels(
			commercePriceListId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommercePriceListAccountRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePriceListAccountRelService, CommercePriceListAccountRelService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePriceListAccountRelService.class);

		ServiceTracker
			<CommercePriceListAccountRelService,
			 CommercePriceListAccountRelService> serviceTracker =
				new ServiceTracker
					<CommercePriceListAccountRelService,
					 CommercePriceListAccountRelService>(
						 bundle.getBundleContext(),
						 CommercePriceListAccountRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}