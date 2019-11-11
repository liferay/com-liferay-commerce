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

package com.liferay.commerce.data.integration.service.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author guywandji
 */
public class DLManagementUtil {

	public static DLFileEntry addOrUpdateFile(
			long folderId, long fileEntryId, String fileName,
			InputStream inStream, String mimeType,
			ServiceContext serviceContext)
		throws PortalException {

		long groupId = serviceContext.getScopeGroupId();
		DLFileEntry fileEntry = null;

		if (fileEntryId == 0) {
			fileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
				serviceContext.getUserId(), groupId, groupId, folderId,
				fileName, mimeType, fileName, fileName, null, 0, null, null,
				inStream, 0, serviceContext);
		}
		else {
			fileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(
				serviceContext.getUserId(), fileEntryId, fileName, mimeType,
				fileName, fileName, "", true, 0L, null, null, inStream, 0L,
				serviceContext);
		}

		return fileEntry;
	}

	public static DLFileEntry addOrUpdateFile(
			long fileEntryId, String processName, File file,
			ServiceContext serviceContext)
		throws PortalException {

		Folder folder = getOrCreateFolder(
			serviceContext.getScopeGroupId(), 0, "PROCESS_" + processName,
			serviceContext);

		String mimeType = MimeTypesUtil.getContentType(file);
		String sourceFileName = file.getName();
		long groupId = serviceContext.getScopeGroupId();
		DLFileEntry fileEntry = null;

		if (fileEntryId == 0) {
			fileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
				serviceContext.getUserId(), groupId, groupId,
				folder.getFolderId(), sourceFileName, mimeType, sourceFileName,
				sourceFileName, null, 0, null, file, null, 0, serviceContext);
		}
		else {
			fileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(
				serviceContext.getUserId(), fileEntryId, sourceFileName,
				mimeType, sourceFileName, sourceFileName, "", true, 0L, null,
				file, null, 0L, serviceContext);
		}

		return fileEntry;
	}

	public static Folder getOrCreateFolder(
			long repositoryId, long parentFolderId, String folderName,
			ServiceContext serviceContext)
		throws PortalException {

		Folder folder = null;

		try {
			folder = DLAppLocalServiceUtil.getFolder(
				repositoryId, parentFolderId, folderName);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			folder = null;
		}

		if (folder == null) {
			folder = DLAppLocalServiceUtil.addFolder(
				serviceContext.getUserId(), repositoryId, parentFolderId,
				folderName, "", serviceContext);
		}

		return folder;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLManagementUtil.class);

}