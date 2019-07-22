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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommercePriceListCommerceAccountGroupRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListCommerceAccountGroupRelService
 * @generated
 */
@ProviderType
public class CommercePriceListCommerceAccountGroupRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				addCommercePriceListCommerceAccountGroupRel(
					long commercePriceListId, long commerceAccountGroupId,
					int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId, order, serviceContext);
	}

	public static void deleteCommercePriceListCommerceAccountGroupRel(
			long commercePriceListCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				fetchCommercePriceListCommerceAccountGroupRel(
					long commercePriceListId, long commerceAccountGroupId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId);
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				getCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccoungGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccoungGroupRelId);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						long commercePriceListId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						long commercePriceListId, int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.price.list.model.
								CommercePriceListCommerceAccountGroupRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId, start, end, orderByComparator);
	}

	public static int getCommercePriceListCommerceAccountGroupRelsCount(
			long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRelsCount(
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

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				updateCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccountGroupRelId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId, order, serviceContext);
	}

	public static CommercePriceListCommerceAccountGroupRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePriceListCommerceAccountGroupRelService,
		 CommercePriceListCommerceAccountGroupRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePriceListCommerceAccountGroupRelService.class);

		ServiceTracker
			<CommercePriceListCommerceAccountGroupRelService,
			 CommercePriceListCommerceAccountGroupRelService> serviceTracker =
				new ServiceTracker
					<CommercePriceListCommerceAccountGroupRelService,
					 CommercePriceListCommerceAccountGroupRelService>(
						 bundle.getBundleContext(),
						 CommercePriceListCommerceAccountGroupRelService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}