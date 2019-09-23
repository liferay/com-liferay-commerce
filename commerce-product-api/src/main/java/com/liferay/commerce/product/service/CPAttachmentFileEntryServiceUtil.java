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
 * Provides the remote service utility for CPAttachmentFileEntry. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntryService
 * @generated
 */
public class CPAttachmentFileEntryServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPAttachmentFileEntryServiceUtil} to access the cp attachment file entry remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			addCPAttachmentFileEntry(
				long classNameId, long classPK, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, serviceContext);
	}

	public static void deleteCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.
							CPAttachmentFileEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	public static int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, int type, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPAttachmentFileEntriesCount(
			classNameId, classPK, type, status);
	}

	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			getCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			updateCPAttachmentFileEntry(
				long cpAttachmentFileEntryId, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, fileEntryId, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPAttachmentFileEntry
			upsertCPAttachmentFileEntry(
				long classNameId, long classPK, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, externalReferenceCode, serviceContext);
	}

	public static CPAttachmentFileEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPAttachmentFileEntryService, CPAttachmentFileEntryService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPAttachmentFileEntryService.class);

		ServiceTracker
			<CPAttachmentFileEntryService, CPAttachmentFileEntryService>
				serviceTracker =
					new ServiceTracker
						<CPAttachmentFileEntryService,
						 CPAttachmentFileEntryService>(
							 bundle.getBundleContext(),
							 CPAttachmentFileEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}