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

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentBase64;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentUrl;
import com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.MethodRequiredParameterMissingException;
import com.liferay.headless.commerce.admin.catalog.internal.util.DateConfigUtil;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.File;

import java.net.URL;
import java.net.URLConnection;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class AttachmentUtil {

	public static FileEntry addFileEntry(
			Attachment attachment, long groupId, long userId,
			UniqueFileNameProvider uniqueFileNameProvider)
		throws Exception {

		if (Validator.isNotNull(attachment.getAttachment())) {
			return addFileEntry(
				attachment.getAttachment(), groupId, userId,
				uniqueFileNameProvider);
		}

		if (Validator.isNotNull(attachment.getSrc())) {
			URL url = new URL(attachment.getSrc());

			URLConnection urlConnection = url.openConnection();

			urlConnection.connect();

			File file = FileUtil.createTempFile(urlConnection.getInputStream());

			return _addFileEntry(
				groupId, userId, file, MimeTypesUtil.getContentType(file),
				uniqueFileNameProvider);
		}

		return null;
	}

	public static FileEntry addFileEntry(
			AttachmentUrl attachmentUrl, long groupId, long userId,
			UniqueFileNameProvider uniqueFileNameProvider)
		throws Exception {

		if (Validator.isNull(attachmentUrl.getSrc())) {
			return null;
		}

		File file = FileUtil.createTempFile(
			HttpUtil.URLtoInputStream(attachmentUrl.getSrc()));

		return _addFileEntry(
			groupId, userId, file, MimeTypesUtil.getContentType(file),
			uniqueFileNameProvider);
	}

	public static FileEntry addFileEntry(
			String base64EncodedContent, long groupId, long userId,
			UniqueFileNameProvider uniqueFileNameProvider)
		throws Exception {

		if (Validator.isNull(base64EncodedContent)) {
			return null;
		}

		byte[] attachmentBytes = Base64.decode(base64EncodedContent);

		File file = FileUtil.createTempFile(attachmentBytes);

		return _addFileEntry(
			groupId, userId, file, MimeTypesUtil.getContentType(file),
			uniqueFileNameProvider);
	}

	public static Map<Locale, String> getTitleMap(
			CPAttachmentFileEntry cpAttachmentFileEntry,
			Map<String, String> titleMap)
		throws PortalException {

		if (titleMap != null) {
			return LanguageUtils.getLocalizedMap(titleMap);
		}

		if (cpAttachmentFileEntry == null) {
			return null;
		}

		return cpAttachmentFileEntry.getTitleMap();
	}

	public static CPAttachmentFileEntry upsertCPAttachmentFileEntry(
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			UniqueFileNameProvider uniqueFileNameProvider,
			AttachmentBase64 attachmentBase64, long classNameId, long classPK,
			int type, ServiceContext serviceContext)
		throws Exception {

		_validateMethodRequiredParams(
			attachmentBase64.getId(),
			attachmentBase64.getExternalReferenceCode());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachmentBase64.getDisplayDate() != null) {
			displayCalendar = DateConfigUtil.convertDateToCalendar(
				attachmentBase64.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachmentBase64.getExpirationDate() != null) {
			expirationCalendar = DateConfigUtil.convertDateToCalendar(
				attachmentBase64.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		long fileEntryId = 0;

		FileEntry fileEntry = addFileEntry(
			attachmentBase64.getAttachment(), serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), uniqueFileNameProvider);

		if (fileEntry != null) {
			fileEntryId = fileEntry.getFileEntryId();
		}

		return cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			serviceContext.getScopeGroupId(), classNameId, classPK,
			GetterUtil.getLong(attachmentBase64.getId()), fileEntryId,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			GetterUtil.get(attachmentBase64.getNeverExpire(), false),
			getTitleMap(null, attachmentBase64.getTitle()),
			GetterUtil.getString(attachmentBase64.getOptions()),
			GetterUtil.getDouble(attachmentBase64.getPriority()), type,
			attachmentBase64.getExternalReferenceCode(), serviceContext);
	}

	public static CPAttachmentFileEntry upsertCPAttachmentFileEntry(
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			UniqueFileNameProvider uniqueFileNameProvider,
			AttachmentUrl attachmentUrl, long classNameId, long classPK,
			int type, ServiceContext serviceContext)
		throws Exception {

		_validateMethodRequiredParams(
			attachmentUrl.getId(), attachmentUrl.getExternalReferenceCode());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachmentUrl.getDisplayDate() != null) {
			displayCalendar = DateConfigUtil.convertDateToCalendar(
				attachmentUrl.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachmentUrl.getExpirationDate() != null) {
			expirationCalendar = DateConfigUtil.convertDateToCalendar(
				attachmentUrl.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		long fileEntryId = 0;

		FileEntry fileEntry = addFileEntry(
			attachmentUrl, serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), uniqueFileNameProvider);

		if (fileEntry != null) {
			fileEntryId = fileEntry.getFileEntryId();
		}

		return cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			serviceContext.getScopeGroupId(), classNameId, classPK,
			GetterUtil.getLong(attachmentUrl.getId()), fileEntryId,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			GetterUtil.get(attachmentUrl.getNeverExpire(), false),
			getTitleMap(null, attachmentUrl.getTitle()),
			GetterUtil.getString(attachmentUrl.getOptions()),
			GetterUtil.getDouble(attachmentUrl.getPriority()), type,
			attachmentUrl.getExternalReferenceCode(), serviceContext);
	}

	public static CPAttachmentFileEntry upsertCPAttachmentFileEntry(
			long groupId,
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			UniqueFileNameProvider uniqueFileNameProvider,
			Attachment attachment, long classNameId, long classPK, int type,
			ServiceContext serviceContext)
		throws Exception {

		_validateMethodRequiredParams(
			attachment.getId(), attachment.getExternalReferenceCode());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachment.getDisplayDate() != null) {
			displayCalendar = DateConfigUtil.convertDateToCalendar(
				attachment.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachment.getExpirationDate() != null) {
			expirationCalendar = DateConfigUtil.convertDateToCalendar(
				attachment.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		long fileEntryId = 0;

		FileEntry fileEntry = addFileEntry(
			attachment, serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), uniqueFileNameProvider);

		if (fileEntry != null) {
			fileEntryId = fileEntry.getFileEntryId();
		}

		return cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			groupId, classNameId, classPK,
			GetterUtil.getLong(attachment.getId()), fileEntryId,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			GetterUtil.get(attachment.getNeverExpire(), false),
			getTitleMap(null, attachment.getTitle()),
			GetterUtil.getString(attachment.getOptions()),
			GetterUtil.getDouble(attachment.getPriority()), type,
			attachment.getExternalReferenceCode(), serviceContext);
	}

	private static FileEntry _addFileEntry(
			long groupId, long userId, File file, String contentType,
			UniqueFileNameProvider uniqueFileNameProvider)
		throws PortalException {

		String uniqueFileName = uniqueFileNameProvider.provide(
			file.getName(),
			curFileName -> _exists(groupId, userId, curFileName));

		FileEntry fileEntry = TempFileEntryUtil.addTempFileEntry(
			groupId, userId, _TEMP_FILE_NAME, uniqueFileName, file,
			contentType);

		FileUtil.delete(file);

		return fileEntry;
	}

	private static boolean _exists(
		long groupId, long userId, String curFileName) {

		try {
			if (TempFileEntryUtil.getTempFileEntry(
					groupId, userId, _TEMP_FILE_NAME, curFileName) != null) {

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

	private static void _validateMethodRequiredParams(
			Long id, String externalReferenceCode)
		throws MethodRequiredParameterMissingException {

		if (Validator.isNull(id) && Validator.isNull(externalReferenceCode)) {
			throw new MethodRequiredParameterMissingException(
				"Unable to complete operation if attachment misses both ID " +
					"and externalReferenceCode");
		}
	}

	private static final String _TEMP_FILE_NAME =
		AttachmentUtil.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(AttachmentUtil.class);

}