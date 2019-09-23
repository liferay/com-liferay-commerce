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
 * Provides the remote service utility for CPDefinitionOptionRel. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionOptionRelService
 * @generated
 */
public class CPDefinitionOptionRelServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionOptionRelServiceUtil} to access the cp definition option rel remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			addCPDefinitionOptionRel(
				long cpDefinitionId, long cpOptionId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				String ddmFormFieldTypeName, double priority, boolean facetable,
				boolean required, boolean skuContributor,
				boolean importOptionValue,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionOptionRel(
			cpDefinitionId, cpOptionId, nameMap, descriptionMap,
			ddmFormFieldTypeName, priority, facetable, required, skuContributor,
			importOptionValue, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			addCPDefinitionOptionRel(
				long cpDefinitionId, long cpOptionId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionOptionRel(
			cpDefinitionId, cpOptionId, serviceContext);
	}

	public static void deleteCPDefinitionOptionRel(long cpDefinitionOptionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionOptionRel(cpDefinitionOptionRelId);
	}

	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			fetchCPDefinitionOptionRel(long cpDefinitionOptionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPDefinitionOptionRel(cpDefinitionOptionRelId);
	}

	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			fetchCPDefinitionOptionRel(long cpDefinitionId, long cpOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPDefinitionOptionRel(
			cpDefinitionId, cpOptionId);
	}

	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			getCPDefinitionOptionRel(long cpDefinitionOptionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRel(cpDefinitionOptionRelId);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionRel>
				getCPDefinitionOptionRels(
					long cpDefinitionId, boolean skuContributor)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRels(
			cpDefinitionId, skuContributor);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionRel>
				getCPDefinitionOptionRels(
					long cpDefinitionId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRels(
			cpDefinitionId, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionRel>
				getCPDefinitionOptionRels(
					long cpDefinitionId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.
							CPDefinitionOptionRel> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRels(
			cpDefinitionId, start, end, orderByComparator);
	}

	public static int getCPDefinitionOptionRelsCount(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRelsCount(cpDefinitionId);
	}

	public static int getCPDefinitionOptionRelsCount(
			long cpDefinitionId, boolean skuContributor)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionOptionRelsCount(
			cpDefinitionId, skuContributor);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPDefinitionOptionRel>
				searchCPDefinitionOptionRels(
					long companyId, long groupId, long cpDefinitionId,
					String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPDefinitionOptionRels(
			companyId, groupId, cpDefinitionId, keywords, start, end, sort);
	}

	public static com.liferay.commerce.product.model.CPDefinitionOptionRel
			updateCPDefinitionOptionRel(
				long cpDefinitionOptionRelId, long cpOptionId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				String ddmFormFieldTypeName, double priority, boolean facetable,
				boolean required, boolean skuContributor,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDefinitionOptionRel(
			cpDefinitionOptionRelId, cpOptionId, nameMap, descriptionMap,
			ddmFormFieldTypeName, priority, facetable, required, skuContributor,
			serviceContext);
	}

	public static CPDefinitionOptionRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPDefinitionOptionRelService, CPDefinitionOptionRelService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPDefinitionOptionRelService.class);

		ServiceTracker
			<CPDefinitionOptionRelService, CPDefinitionOptionRelService>
				serviceTracker =
					new ServiceTracker
						<CPDefinitionOptionRelService,
						 CPDefinitionOptionRelService>(
							 bundle.getBundleContext(),
							 CPDefinitionOptionRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}