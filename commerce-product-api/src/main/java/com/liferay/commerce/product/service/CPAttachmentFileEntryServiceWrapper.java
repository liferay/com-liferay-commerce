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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPAttachmentFileEntryService}.
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntryService
 * @generated
 */
public class CPAttachmentFileEntryServiceWrapper
	implements CPAttachmentFileEntryService,
			   ServiceWrapper<CPAttachmentFileEntryService> {

	public CPAttachmentFileEntryServiceWrapper(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPAttachmentFileEntryServiceUtil} to access the cp attachment file entry remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
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

		return _cpAttachmentFileEntryService.addCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, serviceContext);
	}

	@Override
	public void deleteCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.
							CPAttachmentFileEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, int type, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
			classNameId, classPK, type, status);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			getCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpAttachmentFileEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
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

		return _cpAttachmentFileEntryService.updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, fileEntryId, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
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

		return _cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, externalReferenceCode, serviceContext);
	}

	@Override
	public CPAttachmentFileEntryService getWrappedService() {
		return _cpAttachmentFileEntryService;
	}

	@Override
	public void setWrappedService(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

}