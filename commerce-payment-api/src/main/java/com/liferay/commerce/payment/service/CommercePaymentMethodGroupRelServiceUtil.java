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

package com.liferay.commerce.payment.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommercePaymentMethodGroupRel. This utility wraps
 * {@link com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelService
 * @see com.liferay.commerce.payment.service.base.CommercePaymentMethodGroupRelServiceBaseImpl
 * @see com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelServiceImpl
 * @generated
 */
@ProviderType
public class CommercePaymentMethodGroupRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.model.CommerceAddressRestriction addCommerceAddressRestriction(
		long classPK, long commerceCountryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAddressRestriction(classPK, commerceCountryId,
			serviceContext);
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel addCommercePaymentMethodGroupRel(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile, String engineKey,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommercePaymentMethodGroupRel(nameMap, descriptionMap,
			imageFile, engineKey, engineParameterMap, priority, active,
			serviceContext);
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createCommercePaymentMethodGroupRel(groupId);
	}

	public static void deleteCommerceAddressRestriction(
		long commerceAddressRestrictionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCommerceAddressRestriction(commerceAddressRestrictionId);
	}

	public static void deleteCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceAddressRestriction> getCommerceAddressRestrictions(
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddressRestriction> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceAddressRestrictions(classPK, start, end,
			orderByComparator);
	}

	public static int getCommerceAddressRestrictionsCount(long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAddressRestrictionsCount(classPK);
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId);
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		long groupId, String engineKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommercePaymentMethodGroupRel(groupId, engineKey);
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommercePaymentMethodGroupRels(groupId);
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommercePaymentMethodGroupRels(groupId, active);
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId, long commerceCountryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommercePaymentMethodGroupRels(groupId,
			commerceCountryId, active);
	}

	public static int getCommercePaymentMethodGroupRelsCount(long groupId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommercePaymentMethodGroupRelsCount(groupId, active);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel setActive(
		long commercePaymentMethodGroupRelId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().setActive(commercePaymentMethodGroupRelId, active);
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel updateCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId,
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId,
			nameMap, descriptionMap, imageFile, engineParameterMap, priority,
			active, serviceContext);
	}

	public static CommercePaymentMethodGroupRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommercePaymentMethodGroupRelService, CommercePaymentMethodGroupRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommercePaymentMethodGroupRelService.class);

		ServiceTracker<CommercePaymentMethodGroupRelService, CommercePaymentMethodGroupRelService> serviceTracker =
			new ServiceTracker<CommercePaymentMethodGroupRelService, CommercePaymentMethodGroupRelService>(bundle.getBundleContext(),
				CommercePaymentMethodGroupRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}