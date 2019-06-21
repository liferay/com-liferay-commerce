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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.File;
import java.io.FileOutputStream;

import java.net.URI;

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
			byte[] attachmentBytes = Base64.decode(attachment.getAttachment());

			File file = new File(_TEMP_FOLDER_NAME);

			FileOutputStream fileOutputStream = new FileOutputStream(file);

			fileOutputStream.write(attachmentBytes);

			fileOutputStream.close();

			return _addFileEntry(
				groupId, userId, file, MimeTypesUtil.getContentType(file),
				uniqueFileNameProvider);
		}

		if (Validator.isNotNull(attachment.getSrc())) {
			URI uri = new URI(attachment.getSrc());

			File file = new File(uri);

			return _addFileEntry(
				groupId, userId, file, MimeTypesUtil.getContentType(file),
				uniqueFileNameProvider);
		}

		return null;
	}

	public static Map<Locale, String> getTitleMap(
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

	public static CPAttachmentFileEntry upsertCPAttachmentFileEntry(
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			UniqueFileNameProvider uniqueFileNameProvider,
			Attachment attachment, long classNameId, long classPK, int type,
			ServiceContext serviceContext)
		throws Exception {

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
			classNameId, classPK, fileEntryId, displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(),
			GetterUtil.get(attachment.getNeverExpire(), false),
			getTitleMap(null, attachment),
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

		return TempFileEntryUtil.addTempFileEntry(
			groupId, userId, _TEMP_FOLDER_NAME, uniqueFileName, file,
			contentType);
	}

	private static boolean _exists(
		long groupId, long userId, String curFileName) {

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

	private static final String _TEMP_FOLDER_NAME =
		AttachmentUtil.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(AttachmentUtil.class);

}