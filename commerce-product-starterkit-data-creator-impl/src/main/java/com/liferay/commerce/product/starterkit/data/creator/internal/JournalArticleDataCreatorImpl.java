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

package com.liferay.commerce.product.starterkit.data.creator.internal;

import com.liferay.commerce.product.starterkit.data.creator.JournalArticleDataCreator;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel de Francisco
 */
@Component(immediate = true, service = JournalArticleDataCreator.class)
public class JournalArticleDataCreatorImpl
	implements JournalArticleDataCreator {

	public static final String EMPTY_CONTENT = StringBundler.concat(
		"<?xml version=\"1.0\"?>",
		"<root available-locales=\"en_US\" default-locale=\"en_US\">",
		"<dynamic-element name=\"content\" type=\"text_area\" ",
		"index-type=\"text\" instance-id=\"grgk\">",
		"<dynamic-content language-id=\"en_US\">",
		"<![CDATA[]]></dynamic-content></dynamic-element></root>");

	public static final String IMG_TAG =
		"<img alt='' src='%s' data-fileentryid='%s' />";

	public static final String IMG_TAG_DATA = StringBundler.concat(
		"<![CDATA[{\"groupId\":\"%s\",\"name\":\"%s\",\"alt\":\"\",\"title\":",
		"\"%s\",\"type\":\"journal\",\"uuid\":\"%s\",\"fileEntryId\":",
		"\"%s\",\"resourcePrimKey\":\"%s\"}]]>");

	public static final String LOCALE_PLACEHOLDER = "[$LOCALE$]";

	@Override
	public void createJournalArticles(
			JSONArray journalArticleJSONArray, ClassLoader classLoader,
			String dependenciesFilePath, ServiceContext serviceContext)
		throws Exception {

		for (int i = 0; i < journalArticleJSONArray.length(); i++) {
			JSONObject journalArticleJSONObject =
				journalArticleJSONArray.getJSONObject(i);

			createJournalArticle(
				journalArticleJSONObject, classLoader, dependenciesFilePath,
				serviceContext);
		}
	}

	protected JournalArticle createJournalArticle(
			JSONObject jsonObject, ClassLoader classLoader,
			String dependenciesFilePath, ServiceContext serviceContext)
		throws Exception {

		String articleId = jsonObject.getString("articleId");
		String content = jsonObject.getString("content");
		String ddmStructureKey = jsonObject.getString("ddmStructureKey");
		String ddmTemplateKey = jsonObject.getString("ddmTemplateKey");
		String description = jsonObject.getString("description");
		String title = jsonObject.getString("title");

		articleId = StringUtil.toUpperCase(StringUtil.trim(articleId));

		JournalArticle journalArticle =
			_journalArticleLocalService.fetchArticle(
				serviceContext.getScopeGroupId(), articleId);

		if (journalArticle != null) {
			return journalArticle;
		}

		fetchDDMStructure(ddmStructureKey, classLoader, serviceContext);

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> titleMap = new HashMap<>();
		Map<Locale, String> descriptionMap = new HashMap<>();

		titleMap.put(locale, title);
		descriptionMap.put(locale, description);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		journalArticle = _journalArticleLocalService.addArticle(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), 0L,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, 0L, articleId, false,
			1, titleMap, descriptionMap, EMPTY_CONTENT, ddmStructureKey,
			ddmTemplateKey, StringPool.BLANK, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute, 0, 0, 0, 0, 0,
			true, 0, 0, 0, 0, 0, true, true, false, StringPool.BLANK, null,
			null, StringPool.BLANK, serviceContext);

		content = getNormalizedContent(
			content, classLoader, dependenciesFilePath, journalArticle,
			serviceContext);

		return _journalArticleLocalService.updateArticle(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), 0L,
			journalArticle.getArticleId(), 1, content, serviceContext);
	}

	protected DDMStructure fetchDDMStructure(
			String ddmStructureKey, ClassLoader classLoader,
			ServiceContext serviceContext)
		throws Exception {

		long classNameId = _portal.getClassNameId(
			"com.liferay.journal.model.JournalArticle");

		DDMStructure ddmStructure = _ddmStructureLocalService.fetchStructure(
			serviceContext.getScopeGroupId(), classNameId, ddmStructureKey,
			true);

		if (ddmStructure != null) {
			return ddmStructure;
		}
		else {
			throw new Exception("DDMStructure does not exist");
		}
	}

	protected FileEntry fetchOrAddFileEntry(
			ClassLoader classLoader, String dependenciesFilePath,
			String fileName, ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry = null;

		try {
			fileEntry = _dlAppLocalService.getFileEntry(
				serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		if (fileEntry != null) {
			return fileEntry;
		}

		String filePath = dependenciesFilePath + fileName;

		InputStream inputStream = classLoader.getResourceAsStream(filePath);

		String mimeType = MimeTypesUtil.getContentType(fileName);

		byte[] byteArray = FileUtil.getBytes(inputStream);

		return _dlAppLocalService.addFileEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName, mimeType,
			fileName, StringPool.BLANK, StringPool.BLANK, byteArray,
			serviceContext);
	}

	protected String getNormalizedContent(
			String content, ClassLoader classLoader,
			String dependenciesFilePath, JournalArticle journalArticle,
			ServiceContext serviceContext)
		throws Exception {

		Set<String> placeHolders = new HashSet<>();

		Matcher matcher = _placeholderPattern.matcher(content);

		while (matcher.find()) {
			placeHolders.add(matcher.group());
		}

		if (journalArticle != null) {
			for (String placeHolder : placeHolders) {
				String fileName = placeHolder.substring(
					2, placeHolder.length() - 2);

				if ((fileName != null) && !fileName.isEmpty()) {
					FileEntry fileEntry = fetchOrAddFileEntry(
						classLoader, dependenciesFilePath, fileName,
						serviceContext);

					String imgHtmlTag = String.format(
						IMG_TAG_DATA, serviceContext.getScopeGroupId(),
						fileName, fileName, fileEntry.getUuid(),
						String.valueOf(fileEntry.getFileEntryId()),
						String.valueOf(fileEntry.getFileEntryId()),
						String.valueOf(journalArticle.getResourcePrimKey()));

					content = content.replace(placeHolder, imgHtmlTag);
				}
			}
		}

		content = content.replace(
			LOCALE_PLACEHOLDER, String.valueOf(serviceContext.getLocale()));

		return content;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleDataCreatorImpl.class);

	private static final Pattern _placeholderPattern = Pattern.compile(
		"\\[%[^\\[%]+%\\]", Pattern.CASE_INSENSITIVE);

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private Portal _portal;

}