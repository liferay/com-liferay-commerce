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

import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(service = KBArticleImporter.class)
public class KBArticleImporter {

	public void importKBArticles(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_addKBArticle(jsonObject, userId, serviceContext);
		}
	}

	private void _addKBArticle(
			JSONObject jsonObject, long userId, ServiceContext serviceContext)
		throws Exception {

		// KB Article

		String content = jsonObject.getString("content");
		String title = jsonObject.getString("title");

		String[] sections = {};
		String[] selectedFileNames = {};

		long folderClassNameId = _classNameLocalService.getClassNameId(
			KBFolderConstants.getClassName());

		KBArticle kbArticle = _kbArticleLocalService.addKBArticle(
			userId, folderClassNameId,
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID, title, null, content,
			StringPool.BLANK, null, sections, selectedFileNames,
			serviceContext);

		JSONArray tagsJSONArray = jsonObject.getJSONArray("tags");

		if (tagsJSONArray.length() > 0) {
			String[] assetTagNames = ArrayUtil.toStringArray(tagsJSONArray);

			_kbArticleLocalService.updateKBArticleAsset(
				userId, kbArticle, new long[0], assetTagNames, new long[0]);
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private KBArticleLocalService _kbArticleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}