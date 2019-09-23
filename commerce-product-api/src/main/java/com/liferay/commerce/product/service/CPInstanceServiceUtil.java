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
 * Provides the remote service utility for CPInstance. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPInstanceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPInstanceService
 * @generated
 */
public class CPInstanceServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPInstanceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPInstanceServiceUtil} to access the cp instance remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPInstanceServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.product.model.CPInstance addCPInstance(
			long cpDefinitionId, long groupId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, String json,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, published, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static void buildCPInstances(
			long cpDefinitionId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().buildCPInstances(cpDefinitionId, serviceContext);
	}

	public static void deleteCPInstance(long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPInstance(cpInstanceId);
	}

	public static com.liferay.commerce.product.model.CPInstance
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.commerce.product.model.CPInstance fetchCPInstance(
			long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPInstance(cpInstanceId);
	}

	public static com.liferay.commerce.product.model.CPInstance
			fetchCProductInstance(long cProductId, String cpInstanceUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCProductInstance(cProductId, cpInstanceUuid);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPInstance>
			getCPDefinitionInstances(
				long cpDefinitionId, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPInstance>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionInstances(
			cpDefinitionId, status, start, end, orderByComparator);
	}

	public static int getCPDefinitionInstancesCount(
			long cpDefinitionId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionInstancesCount(
			cpDefinitionId, status);
	}

	public static com.liferay.commerce.product.model.CPInstance getCPInstance(
			long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPInstance(cpInstanceId);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPInstance>
			getCPInstances(
				long groupId, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPInstance>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPInstances(
			groupId, status, start, end, orderByComparator);
	}

	public static int getCPInstancesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPInstancesCount(groupId, status);
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
		<com.liferay.commerce.product.model.CPInstance>
				searchCPDefinitionInstances(
					long companyId, long cpDefinitionId, String keywords,
					int status, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPDefinitionInstances(
			companyId, cpDefinitionId, keywords, status, start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPInstance> searchCPInstances(
				long companyId, long groupId, String keywords, int status,
				int start, int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPInstances(
			companyId, groupId, keywords, status, start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPInstance> searchCPInstances(
				long companyId, String keywords, int status, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPInstances(
			companyId, keywords, status, start, end, sort);
	}

	public static com.liferay.commerce.product.model.CPInstance
			updateCPInstance(
				long cpInstanceId, String sku, String gtin,
				String manufacturerPartNumber, boolean purchasable,
				boolean published, int displayDateMonth, int displayDateDay,
				int displayDateYear, int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPInstance(
			cpInstanceId, sku, gtin, manufacturerPartNumber, purchasable,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPInstance
			updatePricingInfo(
				long cpInstanceId, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice, java.math.BigDecimal cost,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePricingInfo(
			cpInstanceId, price, promoPrice, cost, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPInstance
			updateShippingInfo(
				long cpInstanceId, double width, double height, double depth,
				double weight,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateShippingInfo(
			cpInstanceId, width, height, depth, weight, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPInstance
			updateSubscriptionInfo(
				long cpInstanceId, boolean overrideSubscriptionInfo,
				boolean subscriptionEnabled, int subscriptionLength,
				String subscriptionType,
				com.liferay.portal.kernel.util.UnicodeProperties
					subscriptionTypeSettingsProperties,
				long maxSubscriptionCycles,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateSubscriptionInfo(
			cpInstanceId, overrideSubscriptionInfo, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsProperties, maxSubscriptionCycles,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CPInstance
			upsertCPInstance(
				long cpDefinitionId, long groupId, String sku, String gtin,
				String manufacturerPartNumber, boolean purchasable, String json,
				double width, double height, double depth, double weight,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				java.math.BigDecimal cost, boolean published,
				String externalReferenceCode, int displayDateMonth,
				int displayDateDay, int displayDateYear, int displayDateHour,
				int displayDateMinute, int expirationDateMonth,
				int expirationDateDay, int expirationDateYear,
				int expirationDateHour, int expirationDateMinute,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCPInstance(
			cpDefinitionId, groupId, sku, gtin, manufacturerPartNumber,
			purchasable, json, width, height, depth, weight, price, promoPrice,
			cost, published, externalReferenceCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static CPInstanceService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPInstanceService, CPInstanceService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPInstanceService.class);

		ServiceTracker<CPInstanceService, CPInstanceService> serviceTracker =
			new ServiceTracker<CPInstanceService, CPInstanceService>(
				bundle.getBundleContext(), CPInstanceService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}