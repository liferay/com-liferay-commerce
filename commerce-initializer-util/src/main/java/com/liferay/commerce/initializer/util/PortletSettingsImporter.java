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

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.product.importer.CPFileImporter;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.display.template.PortletDisplayTemplate;

import java.io.File;
import java.io.InputStream;

import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(service = PortletSettingsImporter.class)
public class PortletSettingsImporter {

	public void importPortletSettings(
			JSONArray jsonArray, ClassLoader classLoader,
			String displayTemplateDependenciesPath, long scopeGroupId,
			long assetVocabularyGroupId, long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String portletName = jsonObject.getString("portletName");

			JSONArray layoutFriendlyURLJSONArray = jsonObject.getJSONArray(
				"layoutFriendlyURLs");

			if (layoutFriendlyURLJSONArray != null) {
				for (int j = 0; j < layoutFriendlyURLJSONArray.length(); j++) {
					if (jsonObject.has("layoutFriendlyURL")) {
						jsonObject.remove("layoutFriendlyURL");
					}

					jsonObject.put(
						"layoutFriendlyURL", layoutFriendlyURLJSONArray.get(j));

					_importPortletSettings(
						jsonObject, portletName, classLoader,
						displayTemplateDependenciesPath, assetVocabularyGroupId,
						serviceContext);
				}
			}
			else {
				_importPortletSettings(
					jsonObject, portletName, classLoader,
					displayTemplateDependenciesPath, assetVocabularyGroupId,
					serviceContext);
			}
		}
	}

	private String _importDisplayTemplate(
			JSONObject jsonObject, ClassLoader classLoader,
			String displayTemplateDependenciesPath,
			ServiceContext serviceContext)
		throws Exception {

		String fileName = jsonObject.getString("FileName");
		String name = jsonObject.getString("Name");

		String portletClassName = jsonObject.getString("PortletClassName");

		long classNameId = _portal.getClassNameId(portletClassName);

		long resourceClassNameId = _portal.getClassNameId(
			PortletDisplayTemplate.class);

		File file = null;

		if (Validator.isNotNull(fileName)) {
			InputStream inputStream = classLoader.getResourceAsStream(
				displayTemplateDependenciesPath + fileName);

			file = FileUtil.createTempFile(inputStream);
		}

		DDMTemplate ddmTemplate = _cpFileImporter.getDDMTemplate(
			file, classNameId, 0L, resourceClassNameId, name,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
			TemplateConstants.LANG_TYPE_FTL, serviceContext);

		if (ddmTemplate == null) {
			return StringPool.BLANK;
		}

		return "ddmTemplate_" + ddmTemplate.getTemplateKey();
	}

	private void _importPortletSettings(
			JSONObject jsonObject, String portletName, ClassLoader classLoader,
			String displayTemplateDependenciesPath, long assetVocabularyGroupId,
			ServiceContext serviceContext)
		throws Exception {

		long groupId = serviceContext.getScopeGroupId();

		String instanceId = jsonObject.getString("instanceId");
		String layoutFriendlyURL = jsonObject.getString("layoutFriendlyURL");

		JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
			"portletPreferences");

		String portletId = PortletIdCodec.encode(portletName, instanceId);

		Layout layout = null;

		if (Validator.isNotNull(layoutFriendlyURL)) {
			layout = _layoutLocalService.fetchLayoutByFriendlyURL(
				groupId, false, layoutFriendlyURL);

			if (layout == null) {
				layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					groupId, true, layoutFriendlyURL);
			}
		}

		PortletPreferences portletSetup = null;

		if (layout != null) {
			portletSetup = PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);
		}
		else {
			portletSetup = PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				serviceContext.getCompanyId(), groupId,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
				LayoutConstants.DEFAULT_PLID, portletId, StringPool.BLANK);
		}

		Iterator<String> iterator = portletPreferencesJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String value = null;

			if (key.equals("assetEntryId")) {
				value = portletPreferencesJSONObject.getString(key);

				JournalArticle journalArticle =
					_journalArticleLocalService.fetchArticle(
						serviceContext.getScopeGroupId(), value);

				if (journalArticle != null) {
					value = String.valueOf(journalArticle.getPrimaryKey());
				}
				else {
					value = "0";
				}
			}
			else if (key.equals("assetVocabularyId")) {
				value = portletPreferencesJSONObject.getString(key);

				if (Validator.isBlank(value)) {
					Group group = serviceContext.getScopeGroup();

					value = group.getName(serviceContext.getLanguageId());
				}

				AssetVocabulary assetVocabulary =
					_assetVocabularyLocalService.getGroupVocabulary(
						assetVocabularyGroupId, value);

				value = String.valueOf(assetVocabulary.getVocabularyId());
			}
			else if (key.equals("classNameIds") || key.equals("anyAssetType")) {
				String className = portletPreferencesJSONObject.getString(key);

				value = String.valueOf(
					_classNameLocalService.getClassNameId(className));
			}
			else if (key.equals("displayStyle")) {
				JSONObject displayStyleJSONObject =
					portletPreferencesJSONObject.getJSONObject(key);

				value = _importDisplayTemplate(
					displayStyleJSONObject, classLoader,
					displayTemplateDependenciesPath, serviceContext);

				String templateName = displayStyleJSONObject.getString("Name");

				if (Validator.isBlank(value) &&
					templateName.contains("ddmTemplate_")) {

					value = templateName;
				}
			}
			else if (key.equals("displayStyleGroupId")) {
				value = String.valueOf(groupId);
			}
			else if (key.equals("formInstanceId")) {
				DynamicQuery dynamicQuery =
					_ddmFormInstanceLocalService.dynamicQuery();

				Criterion criterion = RestrictionsFactoryUtil.eq(
					"groupId", groupId);

				List<DDMFormInstance> ddmFormInstances =
					_ddmFormInstanceLocalService.dynamicQuery(
						dynamicQuery.add(criterion));

				if (!ddmFormInstances.isEmpty()) {
					DDMFormInstance ddmFormInstance = ddmFormInstances.get(0);

					value = String.valueOf(ddmFormInstance.getFormInstanceId());
				}
			}
			else if (key.equals("groupId")) {
				value = String.valueOf(groupId);
			}
			else if (key.equals("portletSetupTitle")) {
				value = portletPreferencesJSONObject.getString(key);

				key = key + CharPool.UNDERLINE + serviceContext.getLanguageId();
			}
			else if (key.equals("preferenceDisplayStyle")) {
				value = portletPreferencesJSONObject.getString(key);
				key = "displayStyle";
			}
			else {
				value = portletPreferencesJSONObject.getString(key);
			}

			portletSetup.setValue(key, value);
		}

		portletSetup.store();
	}

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPFileImporter _cpFileImporter;

	@Reference
	private DDMFormInstanceLocalService _ddmFormInstanceLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}