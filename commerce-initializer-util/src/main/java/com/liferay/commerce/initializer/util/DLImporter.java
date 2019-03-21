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

package com.liferay.commerce.initializer.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(service = DLImporter.class)
public class DLImporter {

	public void importDocuments(
			JSONArray jsonArray, ClassLoader classLoader,
			String documentsDependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.fetchUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_addDLFileEntries(
				jsonObject, classLoader, documentsDependenciesPath, userId,
				scopeGroupId, serviceContext);
		}
	}

	private void _addDLFileEntries(
			JSONObject jsonObject, ClassLoader classLoader,
			String documentsDependencyPath, long userId, long scopeGroupId,
			ServiceContext serviceContext)
		throws Exception {

		DLFolder dlFolder = _addDLFolder(
			jsonObject, userId, scopeGroupId, serviceContext);

		JSONArray dlFileEntriesJSONArray = jsonObject.getJSONArray("files");

		for (int i = 0; i < dlFileEntriesJSONArray.length(); i++) {
			JSONObject fileJSONObject = dlFileEntriesJSONArray.getJSONObject(i);

			_addDLFileEntry(
				fileJSONObject, classLoader, documentsDependencyPath, userId,
				scopeGroupId, dlFolder, serviceContext);
		}
	}

	private DLFileEntry _addDLFileEntry(
			JSONObject jsonObject, ClassLoader classLoader,
			String documentsDependencyPath, long userId, long scopeGroupId,
			DLFolder dlFolder, ServiceContext serviceContext)
		throws Exception {

		// DL File Entry

		DLFileEntry dlFileEntry = null;

		String description = jsonObject.getString("description");
		String fileName = jsonObject.getString("fileName");
		String title = jsonObject.getString("title");

		if (Validator.isNotNull(fileName)) {
			Repository repository = _repositoryLocalService.fetchRepository(
				dlFolder.getRepositoryId());

			InputStream inputStream = classLoader.getResourceAsStream(
				documentsDependencyPath + fileName);

			File file = FileUtil.createTempFile(inputStream);

			dlFileEntry = _dlFileEntryLocalService.addFileEntry(
				userId, scopeGroupId, repository.getRepositoryId(),
				dlFolder.getFolderId(), fileName,
				MimeTypesUtil.getContentType(file), title, description,
				StringPool.BLANK,
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT,
				null, file, inputStream, file.length(), serviceContext);
		}

		return dlFileEntry;
	}

	private DLFolder _addDLFolder(
			JSONObject jsonObject, long userId, long scopeGroupId,
			ServiceContext serviceContext)
		throws Exception {

		// DL Folder

		String description = jsonObject.getString("description");
		String name = jsonObject.getString("name");

		List<Repository> repositories =
			_repositoryLocalService.getGroupRepositories(scopeGroupId);

		Repository repository = repositories.get(0);

		return _dlFolderLocalService.addFolder(
			userId, scopeGroupId, repository.getRepositoryId(), false,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name, description,
			false, serviceContext);
	}

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

	@Reference
	private RepositoryLocalService _repositoryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}