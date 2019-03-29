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

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPAttachmentFileEntryException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.File;
import java.io.FileOutputStream;

import java.net.URI;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = AttachmentHelper.class)
public class AttachmentHelper {

	public void deleteAttachment(String id, Company company)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			getCPAttachmentFileEntryById(id, company);

		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
	}

	public Attachment getAttachment(String id, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(getCPAttachmentFileEntryById(id, company));
	}

	public Page<Attachment> getAttachments(
			String productId, Company company, Pagination pagination)
		throws PortalException {

		return _getAttachments(
			productId, CPAttachmentFileEntryConstants.TYPE_OTHER, company,
			pagination);
	}

	public CPAttachmentFileEntry getCPAttachmentFileEntryById(
			String id, Company company)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry;

		if (IdUtils.isLocalPK(id)) {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryService.getCPAttachmentFileEntry(
					GetterUtil.getLong(id));
		}
		else {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryService.fetchByExternalReferenceCode(
					company.getCompanyId(),
					IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (cpAttachmentFileEntry == null) {
			throw new NoSuchCPAttachmentFileEntryException(
				"Unable to find Attachment with ID: " + id);
		}

		return cpAttachmentFileEntry;
	}

	public Page<Attachment> getImages(
			String productId, Company company, Pagination pagination)
		throws PortalException {

		return _getAttachments(
			productId, CPAttachmentFileEntryConstants.TYPE_IMAGE, company,
			pagination);
	}

	public Attachment updateAttachment(
			String id, Attachment attachment, Company company)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			getCPAttachmentFileEntryById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpAttachmentFileEntry.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachment.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(
				attachment.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachment.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				attachment.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		FileEntry fileEntry = addFileEntry(
			attachment, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (fileEntry == null) {
			fileEntry = cpAttachmentFileEntry.getFileEntry();
		}

		cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.updateCPAttachmentFileEntry(
				cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
				fileEntry.getFileEntryId(), displayDateConfig.getMonth(),
				displayDateConfig.getDay(), displayDateConfig.getYear(),
				displayDateConfig.getHour(), displayDateConfig.getMinute(),
				expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
				expirationDateConfig.getYear(), expirationDateConfig.getHour(),
				expirationDateConfig.getMinute(),
				GetterUtil.get(
					attachment.getNeverExpire(),
					cpAttachmentFileEntry.getExpirationDate() == null),
				_getTitleMap(cpAttachmentFileEntry, attachment),
				GetterUtil.get(
					attachment.getOptions(), cpAttachmentFileEntry.getJson()),
				GetterUtil.get(
					attachment.getPriority(),
					cpAttachmentFileEntry.getPriority()),
				attachment.getType(), serviceContext);

		return _dtoMapper.modelToDTO(cpAttachmentFileEntry);
	}

	public Attachment upsertAttachment(
			String productId, Attachment attachment, Company company)
		throws Exception {

		return _upsertAttachment(
			productId, CPAttachmentFileEntryConstants.TYPE_OTHER, attachment,
			company);
	}

	public Attachment upsertImage(
			String productId, Attachment attachment, Company company)
		throws Exception {

		return _upsertAttachment(
			productId, CPAttachmentFileEntryConstants.TYPE_IMAGE, attachment,
			company);
	}

	protected FileEntry addFileEntry(
			Attachment attachment, long groupId, long userId)
		throws Exception {

		if (Validator.isNotNull(attachment.getAttachment())) {
			byte[] attachmentBytes = Base64.decode(attachment.getAttachment());

			File file = new File(_TEMP_FOLDER_NAME);

			FileOutputStream fileOutputStream = new FileOutputStream(file);

			fileOutputStream.write(attachmentBytes);

			fileOutputStream.close();

			return _addFileEntry(
				groupId, userId, file, MimeTypesUtil.getContentType(file));
		}

		if (Validator.isNotNull(attachment.getSrc())) {
			URI uri = new URI(attachment.getSrc());

			File file = new File(uri);

			return _addFileEntry(
				groupId, userId, file, MimeTypesUtil.getContentType(file));
		}

		return null;
	}

	private FileEntry _addFileEntry(
			long groupId, long userId, File file, String contentType)
		throws PortalException {

		String uniqueFileName = _uniqueFileNameProvider.provide(
			file.getName(),
			curFileName -> _exists(groupId, userId, curFileName));

		return TempFileEntryUtil.addTempFileEntry(
			groupId, userId, _TEMP_FOLDER_NAME, uniqueFileName, file,
			contentType);
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	private boolean _exists(long groupId, long userId, String curFileName) {
		try {
			if (TempFileEntryUtil.getTempFileEntry(
					groupId, userId, _TEMP_FOLDER_NAME, curFileName) != null) {

				return true;
			}

			return false;
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return false;
		}
	}

	private Page<Attachment> _getAttachments(
			String productId, int type, Company company, Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), type,
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), type,
				WorkflowConstants.STATUS_APPROVED);

		Stream<CPAttachmentFileEntry> stream = cpAttachmentFileEntries.stream();

		return stream.map(
			cpAttachmentFileEntry -> _dtoMapper.modelToDTO(
				cpAttachmentFileEntry)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				Attachments -> Page.of(Attachments, pagination, totalItems))
		);
	}

	private Map<Locale, String> _getTitleMap(
			CPAttachmentFileEntry cpAttachmentFileEntry, Attachment attachment)
		throws PortalException {

		if (attachment.getTitle() != null) {
			return LanguageUtils.getLocalizedMap(attachment.getTitle());
		}

		if (cpAttachmentFileEntry == null) {
			return null;
		}

		return cpAttachmentFileEntry.getTitleMap();
	}

	private Attachment _upsertAttachment(
			String productId, int type, Attachment attachment, Company company)
		throws Exception {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachment.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(
				attachment.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachment.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				attachment.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		long fileEntryId = 0;

		FileEntry fileEntry = addFileEntry(
			attachment, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (fileEntry != null) {
			fileEntryId = fileEntry.getFileEntryId();
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), fileEntryId,
				displayDateConfig.getMonth(), displayDateConfig.getDay(),
				displayDateConfig.getYear(), displayDateConfig.getHour(),
				displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
				expirationDateConfig.getDay(), expirationDateConfig.getYear(),
				expirationDateConfig.getHour(),
				expirationDateConfig.getMinute(),
				GetterUtil.get(attachment.getNeverExpire(), false),
				_getTitleMap(null, attachment),
				GetterUtil.getString(attachment.getOptions()),
				GetterUtil.getDouble(attachment.getPriority()), type,
				attachment.getExternalReferenceCode(), serviceContext);

		return _dtoMapper.modelToDTO(cpAttachmentFileEntry);
	}

	private static final String _TEMP_FOLDER_NAME =
		AttachmentHelper.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(
		AttachmentHelper.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

}