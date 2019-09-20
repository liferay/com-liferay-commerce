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

package com.liferay.commerce.shipping.engine.fixed.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceShippingFixedOption. This utility wraps
 * <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionService
 * @generated
 */
public class CommerceShippingFixedOptionServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceShippingFixedOptionServiceUtil} to access the commerce shipping fixed option remote service. Add custom service methods to <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption addCommerceShippingFixedOption(
					long commerceShippingMethodId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceShippingFixedOption(
			commerceShippingMethodId, nameMap, descriptionMap, amount, priority,
			serviceContext);
	}

	public static void deleteCommerceShippingFixedOption(
			long commerceShippingFixedOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption fetchCommerceShippingFixedOption(
					long commerceShippingFixedOptionId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	public static java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
					long commerceShippingMethodId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceShippingFixedOptions(
			commerceShippingMethodId, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
					long commerceShippingMethodId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.shipping.engine.fixed.model.
							CommerceShippingFixedOption> orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceShippingFixedOptions(
			commerceShippingMethodId, start, end, orderByComparator);
	}

	public static int getCommerceShippingFixedOptionsCount(
			long commerceShippingMethodId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceShippingFixedOptionsCount(
			commerceShippingMethodId);
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
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption updateCommerceShippingFixedOption(
					long commerceShippingFixedOptionId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceShippingFixedOption(
			commerceShippingFixedOptionId, nameMap, descriptionMap, amount,
			priority);
	}

	public static CommerceShippingFixedOptionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceShippingFixedOptionService, CommerceShippingFixedOptionService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceShippingFixedOptionService.class);

		ServiceTracker
			<CommerceShippingFixedOptionService,
			 CommerceShippingFixedOptionService> serviceTracker =
				new ServiceTracker
					<CommerceShippingFixedOptionService,
					 CommerceShippingFixedOptionService>(
						 bundle.getBundleContext(),
						 CommerceShippingFixedOptionService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}