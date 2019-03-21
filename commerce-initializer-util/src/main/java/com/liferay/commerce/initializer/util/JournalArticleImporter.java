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

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(service = JournalArticleImporter.class)
public class JournalArticleImporter {

	public void importJournalArticles(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_addJournalArticle(jsonObject, userId, serviceContext);
		}
	}

	private JournalArticle _addJournalArticle(
			JSONObject jsonObject, long userId, ServiceContext serviceContext)
		throws Exception {

		// Journal Article

		String title = jsonObject.getString("title");
		String content = jsonObject.getString("content");

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(locale, title);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(locale, StringPool.BLANK);

		String ddmStructureKey = "BASIC-WEB-CONTENT";
		String ddmTemplateKey = "BASIC-WEB-CONTENT";

		return _journalArticleLocalService.addArticle(
			userId, serviceContext.getScopeGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID, titleMap,
			descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			serviceContext);
	}

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}