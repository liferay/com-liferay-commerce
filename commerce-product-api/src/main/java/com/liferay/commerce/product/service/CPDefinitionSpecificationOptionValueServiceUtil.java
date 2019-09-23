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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CPDefinitionSpecificationOptionValue. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPDefinitionSpecificationOptionValueServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionSpecificationOptionValueService
 * @generated
 */
public class CPDefinitionSpecificationOptionValueServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionSpecificationOptionValueServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionSpecificationOptionValueServiceUtil} to access the cp definition specification option value remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionSpecificationOptionValueServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue
				addCPDefinitionSpecificationOptionValue(
					long cpDefinitionId, long cpSpecificationOptionId,
					long cpOptionCategoryId,
					java.util.Map<java.util.Locale, String> valueMap,
					double priority,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionSpecificationOptionValue(
			cpDefinitionId, cpSpecificationOptionId, cpOptionCategoryId,
			valueMap, priority, serviceContext);
	}

	public static void deleteCPDefinitionSpecificationOptionValue(
			long cpDefinitionSpecificationOptionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionSpecificationOptionValue(
			cpDefinitionSpecificationOptionValueId);
	}

	public static
		com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue
				fetchCPDefinitionSpecificationOptionValue(
					long cpDefinitionSpecificationOptionValueId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPDefinitionSpecificationOptionValue(
			cpDefinitionSpecificationOptionValueId);
	}

	public static
		com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue
				getCPDefinitionSpecificationOptionValue(
					long cpDefinitionSpecificationOptionValueId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionSpecificationOptionValue(
			cpDefinitionSpecificationOptionValueId);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.
			CPDefinitionSpecificationOptionValue>
					getCPDefinitionSpecificationOptionValues(
						long cpDefinitionId, int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.product.model.
								CPDefinitionSpecificationOptionValue>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionSpecificationOptionValues(
			cpDefinitionId, start, end, orderByComparator);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.
			CPDefinitionSpecificationOptionValue>
					getCPDefinitionSpecificationOptionValues(
						long cpDefinitionId, long cpOptionCategoryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionSpecificationOptionValues(
			cpDefinitionId, cpOptionCategoryId);
	}

	public static int getCPDefinitionSpecificationOptionValuesCount(
			long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionSpecificationOptionValuesCount(
			cpDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static
		com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue
				updateCPDefinitionSpecificationOptionValue(
					long cpDefinitionSpecificationOptionValueId,
					long cpOptionCategoryId,
					java.util.Map<java.util.Locale, String> valueMap,
					double priority,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDefinitionSpecificationOptionValue(
			cpDefinitionSpecificationOptionValueId, cpOptionCategoryId,
			valueMap, priority, serviceContext);
	}

	public static CPDefinitionSpecificationOptionValueService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPDefinitionSpecificationOptionValueService,
		 CPDefinitionSpecificationOptionValueService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPDefinitionSpecificationOptionValueService.class);

		ServiceTracker
			<CPDefinitionSpecificationOptionValueService,
			 CPDefinitionSpecificationOptionValueService> serviceTracker =
				new ServiceTracker
					<CPDefinitionSpecificationOptionValueService,
					 CPDefinitionSpecificationOptionValueService>(
						 bundle.getBundleContext(),
						 CPDefinitionSpecificationOptionValueService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}