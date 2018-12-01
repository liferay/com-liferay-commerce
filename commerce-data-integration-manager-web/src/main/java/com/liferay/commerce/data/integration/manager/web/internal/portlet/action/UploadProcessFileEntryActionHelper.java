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

package com.liferay.commerce.data.integration.manager.web.internal.portlet.action;

import com.liferay.commerce.data.integration.manager.web.configuration.DataIntegrationConfiguration;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	configurationPid = "com.liferay.data.integration.web.configuration.DataIntegrationConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	service = UploadProcessFileEntryActionHelper.class
)
public class UploadProcessFileEntryActionHelper {

	public DLFileEntry upload(
			UploadPortletRequest uploadPortletRequest, String fileNameParameter)
		throws IOException, PortalException {

		DLFileEntry dlFileEntry = null;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			uploadPortletRequest);

		String fileName = uploadPortletRequest.getFileName(fileNameParameter);

		if (Validator.isFileName(fileName)) {
			long size = uploadPortletRequest.getSize(fileNameParameter);

			try {
				_validateFile(fileName, size);
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new PortalException(e.getMessage());
			}

			String contentType = uploadPortletRequest.getContentType(
				fileNameParameter);

			try (InputStream inputStream =
					uploadPortletRequest.getFileAsStream(fileNameParameter)) {

				dlFileEntry = addFileEntry(
					fileName, contentType, inputStream, themeDisplay,
					serviceContext);
			}
		}

		return dlFileEntry;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_dataIntegrationConfiguration = ConfigurableUtil.createConfigurable(
			DataIntegrationConfiguration.class, properties);
	}

	protected DLFileEntry addFileEntry(
			String fileName, String contentType, InputStream inputStream,
			ThemeDisplay themeDisplay, ServiceContext serviceContext)
		throws PortalException {

		Folder folder = _getOrCreateFolder(
			themeDisplay.getScopeGroupId(), 0L,
			_PREFIX_FOLDER_NAME_ + themeDisplay.getScopeGroupId(),
			serviceContext);

		long folderId = folder.getFolderId();

		String uniqueFileName = _uniqueFileNameProvider.provide(
			fileName,
			curFileName -> _exists(themeDisplay, curFileName, folderId));

		return _addOrUpdateFile(
			folderId, 0L, uniqueFileName, inputStream, contentType,
			serviceContext);
	}

	@Reference
	protected DLAppLocalService dlAppLocalService;

	@Reference
	protected DLFileEntryLocalService dlFileEntryLocalService;

	private DLFileEntry _addOrUpdateFile(
			long folderId, long fileEntryId, String fileName,
			InputStream inStream, String mimeType,
			ServiceContext serviceContext)
		throws PortalException {

		long groupId = serviceContext.getScopeGroupId();
		DLFileEntry fileEntry = null;

		if (fileEntryId == 0) {
			fileEntry = dlFileEntryLocalService.addFileEntry(
				serviceContext.getUserId(), groupId, groupId, folderId,
				fileName, mimeType, fileName, fileName, null, 0, null, null,
				inStream, 0, serviceContext);
		}
		else {
			fileEntry = dlFileEntryLocalService.updateFileEntry(
				serviceContext.getUserId(), fileEntryId, fileName, mimeType,
				fileName, fileName, "", true, 0L, null, null, inStream, 0L,
				serviceContext);
		}

		return fileEntry;
	}

	private boolean _exists(
		ThemeDisplay themeDisplay, String curFileName, long folderId) {

		try {
			if (dlAppLocalService.getFileEntry(
					themeDisplay.getScopeGroupId(), folderId,
					curFileName) != null) {

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

	private Folder _getOrCreateFolder(
			long repositoryId, long parentFolderId, String folderName,
			ServiceContext serviceContext)
		throws PortalException {

		Folder folder = null;

		try {
			folder = dlAppLocalService.getFolder(
				repositoryId, parentFolderId, folderName);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			folder = null;
		}

		if (folder == null) {
			folder = dlAppLocalService.addFolder(
				serviceContext.getUserId(), repositoryId, parentFolderId,
				folderName, "", serviceContext);
		}

		return folder;
	}

	private void _validateFile(String fileName, long size) throws Exception {
		if ((_dataIntegrationConfiguration.imageMaxSize() > 0) &&
			(size > _dataIntegrationConfiguration.imageMaxSize())) {

			throw new Exception();
		}

		String extension = FileUtil.getExtension(fileName);

		String[] imageExtensions =
			_dataIntegrationConfiguration.imageExtensions();

		for (String imageExtension : imageExtensions) {
			if (StringPool.STAR.equals(imageExtension) ||
				imageExtension.equals(StringPool.PERIOD + extension)) {

				return;
			}
		}

		throw new Exception("Invalid image for file name " + fileName);
	}

	private static final String _PREFIX_FOLDER_NAME_ = "PROCESSES_";

	private static final Log _log = LogFactoryUtil.getLog(
		UploadProcessFileEntryActionHelper.class);

	private volatile DataIntegrationConfiguration _dataIntegrationConfiguration;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

}