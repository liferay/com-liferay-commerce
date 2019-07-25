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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.exception.CPAttachmentFileEntryDisplayDateException;
import com.liferay.commerce.product.exception.CPAttachmentFileEntryExpirationDateException;
import com.liferay.commerce.product.exception.DuplicateCPAttachmentFileEntryException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.base.CPAttachmentFileEntryLocalServiceBaseImpl;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPAttachmentFileEntryLocalServiceImpl
	extends CPAttachmentFileEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry addCPAttachmentFileEntry(
			long classNameId, long classPK, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, Map<Locale, String> titleMap, String json,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product attachment file entry

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		Locale locale = LocaleUtil.getSiteDefault();

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPAttachmentFileEntryDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPAttachmentFileEntryExpirationDateException.class);
		}

		FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

		if (Validator.isNull(titleMap.get(locale))) {
			titleMap.put(locale, fileEntry.getFileName());
		}

		long cpAttachmentFileEntryId = counterLocalService.increment();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.create(cpAttachmentFileEntryId);

		if ((classNameId == classNameLocalService.getClassNameId(
				CPDefinition.class)) &&
			cpDefinitionLocalService.isVersionable(classPK)) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(classPK);

			classPK = newCPDefinition.getCPDefinitionId();
		}

		cpAttachmentFileEntry.setUuid(serviceContext.getUuid());
		cpAttachmentFileEntry.setGroupId(groupId);
		cpAttachmentFileEntry.setCompanyId(user.getCompanyId());
		cpAttachmentFileEntry.setUserId(user.getUserId());
		cpAttachmentFileEntry.setUserName(user.getFullName());
		cpAttachmentFileEntry.setClassNameId(classNameId);
		cpAttachmentFileEntry.setClassPK(classPK);

		fileEntryId = _getFileEntryId(
			fileEntry, user.getUserId(), groupId,
			cpAttachmentFileEntry.getClassName(),
			cpAttachmentFileEntry.getClassPK());

		if (cpAttachmentFileEntryPersistence.fetchByC_C_F(
				classNameId, classPK, fileEntryId) != null) {

			throw new DuplicateCPAttachmentFileEntryException();
		}

		cpAttachmentFileEntry.setFileEntryId(fileEntryId);

		cpAttachmentFileEntry.setDisplayDate(displayDate);
		cpAttachmentFileEntry.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpAttachmentFileEntry.setTitleMap(titleMap);
		cpAttachmentFileEntry.setJson(json);
		cpAttachmentFileEntry.setPriority(priority);
		cpAttachmentFileEntry.setType(type);
		cpAttachmentFileEntry.setExpandoBridgeAttributes(serviceContext);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		reindex(classNameId, classPK);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpAttachmentFileEntry, serviceContext);
	}

	@Override
	public void checkCPAttachmentFileEntries() throws PortalException {
		checkCPAttachmentFileEntriesByDisplayDate();
		checkCPAttachmentFileEntriesByExpirationDate();
	}

	@Override
	public void checkCPAttachmentFileEntriesByDisplayDate(
			long classNameId, long classPK)
		throws PortalException {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries = null;

		if (classPK > 0) {
			cpAttachmentFileEntries =
				cpAttachmentFileEntryPersistence.findByC_C_LtD_S(
					classNameId, classPK, new Date(),
					WorkflowConstants.STATUS_SCHEDULED);
		}
		else {
			cpAttachmentFileEntries =
				cpAttachmentFileEntryPersistence.findByLtD_S(
					new Date(), WorkflowConstants.STATUS_SCHEDULED);
		}

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			long userId = PortalUtil.getValidUserId(
				cpAttachmentFileEntry.getCompanyId(),
				cpAttachmentFileEntry.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(cpAttachmentFileEntry.getGroupId());

			cpAttachmentFileEntryLocalService.updateStatus(
				userId, cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());
		}
	}

	@Override
	public void deleteCPAttachmentFileEntries(String className, long classPK)
		throws PortalException {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryPersistence.findByC_C(
				classNameLocalService.getClassNameId(className), classPK);

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
				cpAttachmentFileEntry);
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws PortalException {

		long cpDefinitionClassNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		if ((cpAttachmentFileEntry.getClassNameId() ==
				cpDefinitionClassNameId) &&
			cpDefinitionLocalService.isVersionable(
				cpAttachmentFileEntry.getClassPK())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpAttachmentFileEntry.getClassPK());

			cpAttachmentFileEntry =
				cpAttachmentFileEntryPersistence.findByC_C_F(
					cpDefinitionClassNameId,
					newCPDefinition.getCPDefinitionId(),
					cpAttachmentFileEntry.getFileEntryId());
		}

		// Commerce product attachment file entry

		cpAttachmentFileEntryPersistence.remove(cpAttachmentFileEntry);

		// Expando

		expandoRowLocalService.deleteRows(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());

		reindex(
			cpAttachmentFileEntry.getClassNameId(),
			cpAttachmentFileEntry.getClassPK());

		return cpAttachmentFileEntry;
	}

	@Override
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

		return cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntry);
	}

	@Override
	public CPAttachmentFileEntry fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return cpAttachmentFileEntryPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public Folder getAttachmentsFolder(
			long userId, long groupId, String className, long classPK)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, CPConstants.SERVICE_NAME, serviceContext);

		Folder classNameFolder = PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, className,
			serviceContext);

		return PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(), classNameFolder.getFolderId(),
			String.valueOf(classPK), serviceContext);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.findByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH,
				start, end);
		}

		return cpAttachmentFileEntryPersistence.findByC_C_T_ST(
			classNameId, classPK, type, status, start, end);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end, OrderByComparator<CPAttachmentFileEntry> orderByComparator)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.findByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH,
				start, end, orderByComparator);
		}

		return cpAttachmentFileEntryPersistence.findByC_C_T_ST(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
		long classNameId, long classPK, int type, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.countByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH);
		}

		return cpAttachmentFileEntryPersistence.countByC_C_T_ST(
			classNameId, classPK, type, status);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry updateCPAttachmentFileEntry(
			long cpAttachmentFileEntryId, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, Map<Locale, String> titleMap, String json,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		Locale locale = LocaleUtil.getSiteDefault();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

		long cpDefinitionClassNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		if ((cpAttachmentFileEntry.getClassNameId() ==
				cpDefinitionClassNameId) &&
			cpDefinitionLocalService.isVersionable(
				cpAttachmentFileEntry.getClassPK())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpAttachmentFileEntry.getClassPK());

			cpAttachmentFileEntry =
				cpAttachmentFileEntryPersistence.findByC_C_F(
					cpDefinitionClassNameId,
					newCPDefinition.getCPDefinitionId(),
					cpAttachmentFileEntry.getFileEntryId());
		}

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPAttachmentFileEntryDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPAttachmentFileEntryExpirationDateException.class);
		}

		FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

		HashMap<Locale, String> validTitleMap = new HashMap<>(titleMap);

		if (Validator.isNull(validTitleMap.get(locale))) {
			validTitleMap.put(locale, fileEntry.getFileName());
		}

		fileEntryId = _getFileEntryId(
			fileEntry, user.getUserId(), cpAttachmentFileEntry.getGroupId(),
			cpAttachmentFileEntry.getClassName(),
			cpAttachmentFileEntry.getClassPK());

		cpAttachmentFileEntry.setFileEntryId(fileEntryId);

		cpAttachmentFileEntry.setDisplayDate(displayDate);
		cpAttachmentFileEntry.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpAttachmentFileEntry.setTitleMap(validTitleMap);
		cpAttachmentFileEntry.setJson(json);
		cpAttachmentFileEntry.setPriority(priority);
		cpAttachmentFileEntry.setType(type);
		cpAttachmentFileEntry.setExpandoBridgeAttributes(serviceContext);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpAttachmentFileEntry, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry updateStatus(
			long userId, long cpAttachmentFileEntryId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(cpAttachmentFileEntry.getDisplayDate() != null) &&
			now.before(cpAttachmentFileEntry.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpAttachmentFileEntry.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = cpAttachmentFileEntry.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				cpAttachmentFileEntry.setExpirationDate(null);
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			cpAttachmentFileEntry.setExpirationDate(now);
		}

		cpAttachmentFileEntry.setStatus(status);
		cpAttachmentFileEntry.setStatusByUserId(user.getUserId());
		cpAttachmentFileEntry.setStatusByUserName(user.getFullName());
		cpAttachmentFileEntry.setStatusDate(modifiedDate);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		reindex(
			cpAttachmentFileEntry.getClassNameId(),
			cpAttachmentFileEntry.getClassPK());

		return cpAttachmentFileEntry;
	}

	@Override
	public CPAttachmentFileEntry upsertCPAttachmentFileEntry(
			long classNameId, long classPK, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, Map<Locale, String> titleMap, String json,
			double priority, int type, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.fetchByC_ERC(
				serviceContext.getCompanyId(), externalReferenceCode);

		if (cpAttachmentFileEntry == null) {
			cpAttachmentFileEntry =
				cpAttachmentFileEntryLocalService.addCPAttachmentFileEntry(
					classNameId, classPK, fileEntryId, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, titleMap, json, priority,
					type, serviceContext);
		}
		else {
			cpAttachmentFileEntry =
				cpAttachmentFileEntryLocalService.updateCPAttachmentFileEntry(
					cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
					fileEntryId, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					titleMap, json, priority, type, serviceContext);
		}

		return cpAttachmentFileEntry;
	}

	protected void checkCPAttachmentFileEntriesByDisplayDate()
		throws PortalException {

		checkCPAttachmentFileEntriesByDisplayDate(0, 0);
	}

	protected void checkCPAttachmentFileEntriesByExpirationDate()
		throws PortalException {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryFinder.findByExpirationDate(
				new Date(),
				new QueryDefinition<>(WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + cpAttachmentFileEntries.size() +
					" commerce product attachment file entries");
		}

		if ((cpAttachmentFileEntries != null) &&
			!cpAttachmentFileEntries.isEmpty()) {

			for (CPAttachmentFileEntry cpAttachmentFileEntry :
					cpAttachmentFileEntries) {

				long userId = PortalUtil.getValidUserId(
					cpAttachmentFileEntry.getCompanyId(),
					cpAttachmentFileEntry.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(
					cpAttachmentFileEntry.getGroupId());

				cpAttachmentFileEntryLocalService.updateStatus(
					userId, cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());
			}
		}
	}

	protected void reindex(long classNameId, long classPK)
		throws PortalException {

		ClassName className = classNameLocalService.getClassName(classNameId);

		String classNameValue = className.getValue();

		if (classNameValue.equals(CPDefinition.class.getName())) {
			Indexer<CPDefinition> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(CPDefinition.class);

			indexer.reindex(CPDefinition.class.getName(), classPK);
		}
	}

	protected CPAttachmentFileEntry startWorkflowInstance(
			long userId, CPAttachmentFileEntry cpAttachmentFileEntry,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			cpAttachmentFileEntry.getCompanyId(),
			cpAttachmentFileEntry.getGroupId(), userId,
			CPAttachmentFileEntry.class.getName(),
			cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
			cpAttachmentFileEntry, serviceContext, workflowContext);
	}

	private long _getFileEntryId(
			FileEntry fileEntry, long userId, long groupId, String className,
			long classPK)
		throws PortalException {

		boolean tempFile = fileEntry.isRepositoryCapabilityProvided(
			TemporaryFileEntriesCapability.class);

		if (!tempFile) {
			return fileEntry.getFileEntryId();
		}

		Folder folder = cpAttachmentFileEntryLocalService.getAttachmentsFolder(
			userId, groupId, className, classPK);

		String uniqueFileName = PortletFileRepositoryUtil.getUniqueFileName(
			groupId, folder.getFolderId(), fileEntry.getFileName());

		FileEntry newFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			groupId, userId, className, classPK, CPConstants.SERVICE_NAME,
			folder.getFolderId(), fileEntry.getContentStream(), uniqueFileName,
			fileEntry.getMimeType(), true);

		TempFileEntryUtil.deleteTempFileEntry(fileEntry.getFileEntryId());

		return newFileEntry.getFileEntryId();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPAttachmentFileEntryLocalServiceImpl.class);

}