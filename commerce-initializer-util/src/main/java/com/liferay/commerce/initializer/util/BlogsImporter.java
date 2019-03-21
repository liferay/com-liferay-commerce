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

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.InputStream;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(service = BlogsImporter.class)
public class BlogsImporter {

	public void importBlogsEntries(
			JSONArray jsonArray, ClassLoader classLoader,
			String imageDependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		Date now = new Date();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_addBlogsEntry(
				jsonObject, classLoader, imageDependenciesPath, userId, now,
				serviceContext);
		}
	}

	private void _addBlogsEntry(
			JSONObject jsonObject, ClassLoader classLoader,
			String imageDependenciesPath, long userId, Date displayDate,
			ServiceContext serviceContext)
		throws Exception {

		// Blogs Entry

		String content = jsonObject.getString("content");
		String title = jsonObject.getString("title");

		BlogsEntry blogsEntry = _blogsEntryLocalService.addEntry(
			userId, title, content, displayDate, serviceContext);

		// Add Cover Image

		String fileName = jsonObject.getString("coverImageFileName");

		InputStream inputStream = classLoader.getResourceAsStream(
			imageDependenciesPath + fileName);

		byte[] bytes = FileUtil.getBytes(inputStream);

		ImageSelector imageSelector = new ImageSelector(
			bytes, fileName, MimeTypesUtil.getContentType(fileName),
			StringPool.BLANK);

		_blogsEntryLocalService.addCoverImage(
			blogsEntry.getEntryId(), imageSelector);

		//Add Tags

		JSONArray tagsJSONArray = jsonObject.getJSONArray("tags");

		String[] assetTagNames = ArrayUtil.toStringArray(tagsJSONArray);

		if (assetTagNames.length > 0) {
			_blogsEntryLocalService.updateAsset(
				userId, blogsEntry, new long[0], assetTagNames, new long[0],
				null);
		}
	}

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}