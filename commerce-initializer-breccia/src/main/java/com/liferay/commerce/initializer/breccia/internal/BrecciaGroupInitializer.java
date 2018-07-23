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

package com.liferay.commerce.initializer.breccia.internal;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.initializer.breccia.internal.configuration.BrecciaInitializerConfiguration;
import com.liferay.commerce.initializer.breccia.internal.product.renderer.BrecciaCPContentRenderer;
import com.liferay.commerce.initializer.breccia.internal.product.renderer.list.entry.BrecciaSearchResultCPContentListEntryRenderer;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationLocalService;
import com.liferay.commerce.product.importer.CPFileImporter;
import com.liferay.commerce.product.starterkit.data.creator.CPDemoDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.JournalArticleDataCreator;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterionConstants;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;

import javax.servlet.ServletContext;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 * @author Guy Wandji
 */
@Component(
	configurationPid = "com.liferay.commerce.initializer.breccia.internal.configuration.BrecciaInitializerConfiguration",
	immediate = true,
	property = "site.initializer.key=" + BrecciaGroupInitializer.KEY,
	service = SiteInitializer.class
)
public class BrecciaGroupInitializer implements SiteInitializer {

	public static final String KEY = "breccia-initializer";

	public void createSampleData(ServiceContext serviceContext)
		throws Exception {

		_cpDemoDataCreator.create(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), true);
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "breccia-description");
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "breccia");
	}

	public ServiceContext getServiceContext(long groupId)
		throws PortalException {

		User user = _userLocalService.getUser(PrincipalThreadLocal.getUserId());
		Group group = _groupLocalService.getGroup(groupId);

		Locale locale = LocaleUtil.getSiteDefault();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(group.getCompanyId());
		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(user.getUserId());
		serviceContext.setTimeZone(user.getTimeZone());

		return serviceContext;
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() +
			"/images/Breccia-Logo-Square.png";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			ServiceContext serviceContext = getServiceContext(groupId);

			configureB2BSite(groupId, serviceContext);

			_cpFileImporter.updateLookAndFeel(
				_CUSTOMER_PORTAL_THEME_ID, true, serviceContext);

			_cpFileImporter.cleanLayouts(serviceContext);

			createLayouts(serviceContext);

			configureAccounts(groupId, serviceContext);

			createRoles(serviceContext);

			createSampleData(serviceContext);

			addCommerceWarehouses(serviceContext);

			setThemePortletSettings(serviceContext);

			setThemeWebContent(serviceContext);

			createJournalArticles(serviceContext);

			updateSiteLogo(serviceContext);

			updateCompany(serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		Theme theme = _themeLocalService.fetchTheme(
			companyId, _CUSTOMER_PORTAL_THEME_ID);

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info(_CUSTOMER_PORTAL_THEME_ID + " is not registered");
			}

			return false;
		}

		return true;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			BrecciaInitializerConfiguration.class, properties);
	}

	protected void addCommerceWarehouses(ServiceContext serviceContext)
		throws PortalException {

		CommerceWarehouse defaultWarehouse =
			_commerceWarehouseLocalService.fetchDefaultCommerceWarehouse(
				serviceContext.getScopeGroupId());

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.fetchCommerceCountry(
				serviceContext.getScopeGroupId(), 840);

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(), "IL");

		if (defaultWarehouse != null) {
			_commerceWarehouseLocalService.updateCommerceWarehouse(
				defaultWarehouse.getCommerceWarehouseId(), "Chicago Depot",
				StringPool.BLANK, true, "2372-2300 N Kilbourn Ave", "", "",
				"Chicago", "60639", commerceRegion.getCommerceRegionId(),
				commerceCountry.getCommerceCountryId(), 41.923684, -87.738975,
				serviceContext);
		}
		else {
			_commerceWarehouseLocalService.addCommerceWarehouse(
				"Chicago Depot", StringPool.BLANK, true,
				"2372-2300 N Kilbourn Ave", "", "", "Chicago", "60639",
				commerceRegion.getCommerceRegionId(),
				commerceCountry.getCommerceCountryId(), 41.923684, -87.738975,
				serviceContext);
		}
	}

	protected DDMStructure addDDMStructure(
			long userId, long groupId, String name, String structureKey,
			DDMForm ddmForm, DDMFormLayout ddmFormLayout,
			ServiceContext serviceContext)
		throws Exception {

		long parentStructureId = 0L;
		long journalClassnameId = _portal.getClassNameId(
			"com.liferay.journal.model.JournalArticle");
		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(serviceContext.getLocale(), name);

		DDMStructure journalStructure = _ddmStructureLocalService.addStructure(
			userId, groupId, parentStructureId, journalClassnameId,
			structureKey, nameMap, null, ddmForm, ddmFormLayout, "json",
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);

		return journalStructure;
	}

	protected void configureAccounts(
			long groupId, ServiceContext serviceContext)
		throws Exception {

		Group group = _groupLocalService.getGroup(groupId);

		_addDemoAccountOrganizations(
			group.getOrganizationId(), group.getNameCurrentValue(),
			serviceContext);
	}

	protected void configureB2BSite(long groupId, ServiceContext serviceContext)
		throws Exception {

		_commerceOrganizationLocalService.configureB2BSite(
			groupId, serviceContext);

		_commerceCountryLocalService.importDefaultCountries(serviceContext);

		_commerceCurrencyLocalService.importDefaultValues(serviceContext);
	}

	protected void createCommerceRoles(JSONArray jsonArray) throws Exception {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String name = jsonObject.getString("name");

			_createConfiguration(name);
		}
	}

	protected FileEntry createFile(
			String filePath, String fileName, ServiceContext serviceContext)
		throws IOException, PortalException {

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		FileEntry fileEntry =
			(FileEntry)_dlDLFileEntryLocalService.fetchFileEntry(
				serviceContext.getScopeGroupId(), 0, fileName);

		if (fileEntry != null) {
			return fileEntry;
		}

		String newFilePath = filePath + fileName;

		InputStream inputStream = classLoader.getResourceAsStream(newFilePath);

		String mimeType = MimeTypesUtil.getContentType(newFilePath);

		byte[] byteArray = FileUtil.getBytes(inputStream);

		return _dlAppLocalService.addFileEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName, mimeType,
			fileName, com.liferay.portal.kernel.util.StringPool.BLANK,
			com.liferay.portal.kernel.util.StringPool.BLANK, byteArray,
			serviceContext);
	}

	protected void createJournalArticles(ServiceContext serviceContext)
		throws Exception {

		Class<?> clazz = getClass();

		String journalArticleJSON = StringUtil.read(
			clazz.getClassLoader(), _DEPENDENCY_PATH + "journal-articles.json",
			false);

		JSONArray jsonArray = _jsonFactory.createJSONArray(journalArticleJSON);

		_journalArticleDataCreator.createJournalArticles(
			jsonArray, clazz.getClassLoader(), _DEPENDENCY_PATH,
			serviceContext);
	}

	protected void createLayouts(ServiceContext serviceContext)
		throws Exception {

		Class<?> clazz = getClass();

		String layoutsJSON = StringUtil.read(
			clazz.getClassLoader(), _DEPENDENCY_PATH + "layouts.json", false);

		JSONArray jsonArray = _jsonFactory.createJSONArray(layoutsJSON);

		_cpFileImporter.createLayouts(
			jsonArray, clazz.getClassLoader(), _DEPENDENCY_PATH,
			serviceContext);
	}

	protected void createRoles(ServiceContext serviceContext) throws Exception {
		Class<?> clazz = getClass();

		String rolesJSON = StringUtil.read(
			clazz.getClassLoader(), _DEPENDENCY_PATH + "roles.json", true);

		JSONArray jsonArray = _jsonFactory.createJSONArray(rolesJSON);

		_cpFileImporter.createRoles(jsonArray, serviceContext);

		createCommerceRoles(jsonArray);
	}

	protected DDMTemplate getDDMTemplate(
			String portletClassName, String filePath, String name,
			ServiceContext serviceContext)
		throws Exception {

		long classNameId = _portal.getClassNameId(portletClassName);
		long resourceClassNameId = _portal.getClassNameId(
			PortletDisplayTemplate.class);

		return _cpFileImporter.getDDMTemplate(
			_getFile(filePath), classNameId, 0L, resourceClassNameId, name,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
			TemplateConstants.LANG_TYPE_FTL, serviceContext);
	}

	protected JSONArray getThemePortletSettingJSONArray() throws Exception {
		Class<?> clazz = getClass();

		String themePortletSettingsJSON = StringUtil.read(
			clazz.getClassLoader(),
			_DEPENDENCY_PATH + "theme-portlet-settings.json", true);

		return _jsonFactory.createJSONArray(themePortletSettingsJSON);
	}

	protected JSONArray getThemeStructuresJSONArray() throws Exception {
		Class<?> clazz = getClass();

		String themeStructuresJSON = StringUtil.read(
			clazz.getClassLoader(),
			_DEPENDENCY_PATH + "structures-templates.json", true);

		return _jsonFactory.createJSONArray(themeStructuresJSON);
	}

	protected void setBreadCrumbPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_BREADCRUMB_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup = null;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);

				if (layout != null) {
					portletSetup =
						PortletPreferencesFactoryUtil.getLayoutPortletSetup(
							layout, portletId);
				}
				else {
					portletSetup =
						PortletPreferencesFactoryUtil.getLayoutPortletSetup(
							serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(),
							PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
							LayoutConstants.DEFAULT_PLID, portletId,
							StringPool.BLANK);
				}
			}

			if (portletSetup != null) {
				Iterator<String> iterator = portletPreferencesJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					String value = portletPreferencesJSONObject.getString(key);

					if (key.equals("displayStyleGroupId")) {
						value = String.valueOf(
							serviceContext.getScopeGroupId());
					}

					portletSetup.setValue(key, value);
				}

				String adtPath =
					_DEPENDENCY_PATH_ADT + "BRECCIA_BREADCRUMB.ftl";

				String portletClassName =
					"com.liferay.portal.kernel.servlet.taglib.ui." +
						"BreadcrumbEntry";

				DDMTemplate ddmTemplate = getDDMTemplate(
					portletClassName, adtPath, "BRECCIA", serviceContext);

				String ddmTemplateKey =
					"ddmTemplate_" + ddmTemplate.getTemplateKey();

				portletSetup.setValue("displayStyle", ddmTemplateKey);

				portletSetup.store();
			}
		}
	}

	protected void setCommerceCartContentPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(CommercePortletKeys.COMMERCE_CART_CONTENT)) {
			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_GROUP,
					LayoutConstants.DEFAULT_PLID,
					CommercePortletKeys.COMMERCE_CART_CONTENT,
					StringPool.BLANK);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = portletPreferencesJSONObject.getString(key);

				if (key.equals("displayStyleGroupId")) {
					value = String.valueOf(serviceContext.getScopeGroupId());
				}

				portletSetup.setValue(key, value);
			}

			String adtPath =
				_DEPENDENCY_PATH_ADT + "BRECCIA_COMMERCE_CART_CONTENT.ftl";

			DDMTemplate ddmTemplate = getDDMTemplate(
				_COMMERCE_CART_CONTENT_PORTLET_CLASS_NAME, adtPath,
				"BRECCIA COMMERCE CONTENT CART", serviceContext);

			String ddmTemplateKey =
				"ddmTemplate_" + ddmTemplate.getTemplateKey();

			portletSetup.setValue("displayStyle", ddmTemplateKey);

			portletSetup.store();
		}
	}

	protected void setCommerceCartContentTotalPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(
				CommercePortletKeys.COMMERCE_CART_CONTENT_TOTAL)) {

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_GROUP,
					LayoutConstants.DEFAULT_PLID,
					CommercePortletKeys.COMMERCE_CART_CONTENT_TOTAL,
					StringPool.BLANK);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = portletPreferencesJSONObject.getString(key);

				if (key.equals("displayStyleGroupId")) {
					value = String.valueOf(serviceContext.getScopeGroupId());
				}

				portletSetup.setValue(key, value);
			}

			String adtPath =
				_DEPENDENCY_PATH_ADT +
					"BRECCIA_COMMERCE_CART_CONTENT_TOTAL.ftl";

			DDMTemplate ddmTemplate = getDDMTemplate(
				_COMMERCE_CART_CONTENT_TOTAL_PORTLET_CLASS_NAME, adtPath,
				"BRECCIA COMMERCE CONTENT CART TOTAL", serviceContext);

			String ddmTemplateKey =
				"ddmTemplate_" + ddmTemplate.getTemplateKey();

			portletSetup.setValue("displayStyle", ddmTemplateKey);

			portletSetup.store();
		}
	}

	protected void setCPCategoryContentPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_CP_CATEGORY_CONTENT_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup = null;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);

				if (layout != null) {
					portletSetup =
						PortletPreferencesFactoryUtil.getLayoutPortletSetup(
							layout, portletId);
				}
				else {
					portletSetup =
						PortletPreferencesFactoryUtil.getLayoutPortletSetup(
							serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(),
							PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
							LayoutConstants.DEFAULT_PLID, portletId,
							StringPool.BLANK);
				}
			}

			if (portletSetup != null) {
				Iterator<String> iterator = portletPreferencesJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					String value = portletPreferencesJSONObject.getString(key);

					if (key.equals("displayStyleGroupId")) {
						value = String.valueOf(
							serviceContext.getScopeGroupId());
					}

					portletSetup.setValue(key, value);
				}

				String adtPath =
					_DEPENDENCY_PATH_ADT +
						"BRECCIA_COMMERCE_CATEGORY_CONTENT_TITLE_DESC.ftl";

				DDMTemplate ddmTemplate = getDDMTemplate(
					_CP_CATEGORY_CONTENT_PORTLET_CLASS_NAME, adtPath,
					"BRECCIA_CATEGORY_CONTENT_TITLE", serviceContext);

				String ddmTemplateKey =
					"ddmTemplate_" + ddmTemplate.getTemplateKey();

				portletSetup.setValue("displayStyle", ddmTemplateKey);

				portletSetup.store();
			}
		}
	}

	protected void setCPCategoryNavigationPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_CP_ASSET_CATEGORIES_NAVIGATION_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String vocabularyName = jsonObject.getString("vocabularyName");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
					LayoutConstants.DEFAULT_PLID, portletId, StringPool.BLANK);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = portletPreferencesJSONObject.getString(key);

				if (key.equals("displayStyleGroupId")) {
					value = String.valueOf(serviceContext.getScopeGroupId());
				}

				if (key.equals("assetVocabularyId")) {
					AssetVocabulary assetVocabulary = null;

					if (Validator.isNotNull(vocabularyName)) {
						assetVocabulary =
							_assetVocabularyLocalService.fetchGroupVocabulary(
								serviceContext.getScopeGroupId(),
								vocabularyName);
					}

					if (assetVocabulary == null) {
						value = StringPool.BLANK;
					}
					else {
						value = String.valueOf(
							assetVocabulary.getVocabularyId());
					}
				}

				portletSetup.setValue(key, value);
			}

			DDMTemplate ddmTemplate;
			String adtPath;

			if (instanceId.contains("navigation")) {
				adtPath =
					_DEPENDENCY_PATH_ADT +
						"BRECCIA_COMMERCE_CATEGORY_NAVIGATION_DROPDOWN.ftl";

				ddmTemplate = getDDMTemplate(
					_CP_ASSET_CATEGORIES_NAVIGATION_PORTLET_CLASS_NAME, adtPath,
					"COMMERCE CATEGORY NAVIGATION DROPDOWN", serviceContext);
			}
			else {
				adtPath = StringBundler.concat(
					_DEPENDENCY_PATH_ADT,
					"BRECCIA_COMMERCE_CATEGORY_NAVIGATION_SIMPLE_INLINE.ftl");

				ddmTemplate = getDDMTemplate(
					_CP_ASSET_CATEGORIES_NAVIGATION_PORTLET_CLASS_NAME, adtPath,
					"COMMERCE CATEGORY NAVIGATION SIMPLE", serviceContext);
			}

			String ddmTemplateKey =
				"ddmTemplate_" + ddmTemplate.getTemplateKey();

			portletSetup.setValue("displayStyle", ddmTemplateKey);

			portletSetup.store();

			long plid = LayoutConstants.DEFAULT_PLID;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);

				if (layout != null) {
					plid = layout.getPlid();
				}
			}

			if (plid > LayoutConstants.DEFAULT_PLID) {
				_setPlidPortletPreferences(plid, portletId, serviceContext);
			}
		}
	}

	protected void setCPContentPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_CP_CONTENT_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
					LayoutConstants.DEFAULT_PLID, portletId, StringPool.BLANK);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = portletPreferencesJSONObject.getString(key);

				if (key.equals("displayStyleGroupId")) {
					value = String.valueOf(serviceContext.getScopeGroupId());
				}

				portletSetup.setValue(key, value);
			}

			DDMTemplate ddmTemplate = getDDMTemplate(
				_SIMPLE_CP_TYPE_CLASS_NAME,
				_DEPENDENCY_PATH_ADT + "product_display_template.ftl",
				"COMMERCE PRODUCT DETAIL", serviceContext);

			String ddmTemplateKey =
				"ddmTemplate_" + ddmTemplate.getTemplateKey();

			portletSetup.setValue("displayStyle", ddmTemplateKey);

			portletSetup.setValue(
				"simple--cpTypeRendererKey", BrecciaCPContentRenderer.KEY);

			portletSetup.store();

			long plid = LayoutConstants.DEFAULT_PLID;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);

				if (layout != null) {
					plid = layout.getPlid();
				}
			}

			if (plid > LayoutConstants.DEFAULT_PLID) {
				_setPlidPortletPreferences(plid, portletId, serviceContext);
			}
		}
	}

	protected void setCPSearchResultPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_CP_SEARCH_RESULT_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
					LayoutConstants.DEFAULT_PLID, portletId, StringPool.BLANK);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = portletPreferencesJSONObject.getString(key);

				if (key.equals("displayStyleGroupId")) {
					value = String.valueOf(serviceContext.getScopeGroupId());
				}

				portletSetup.setValue(key, value);
			}

			DDMTemplate ddmTemplate = getDDMTemplate(
				_CP_SEARCH_RESULT_PORTLET_CLASS_NAME,
				_DEPENDENCY_PATH_ADT + "catalog_display_template.ftl",
				"COMMERCE CATALOG OFFICE SUPPLY", serviceContext);

			String ddmTemplateKey =
				"ddmTemplate_" + ddmTemplate.getTemplateKey();

			portletSetup.setValue("displayStyle", ddmTemplateKey);

			portletSetup.setValue(
				"simple--cpTypeListEntryRendererKey",
				BrecciaSearchResultCPContentListEntryRenderer.KEY);

			portletSetup.setValue(
				"cpContentListRendererKey", _CP_SEARCH_RESULT_PORTLET_NAME);

			portletSetup.store();

			long plid = LayoutConstants.DEFAULT_PLID;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);

				if (layout != null) {
					plid = layout.getPlid();
				}
			}

			if (plid > LayoutConstants.DEFAULT_PLID) {
				_setPlidPortletPreferences(plid, portletId, serviceContext);
			}
		}
	}

	protected void setSearchBarPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_SEARCH_BAR_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			String portletId = PortletIdCodec.encode(portletName, instanceId);

			PortletPreferences portletSetup = null;
			Layout layout = null;

			if (Validator.isNotNull(layoutFriendlyURL)) {
				layout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false, layoutFriendlyURL);
			}

			if (layout != null) {
				portletSetup =
					PortletPreferencesFactoryUtil.getLayoutPortletSetup(
						layout, portletId);
			}
			else {
				portletSetup =
					PortletPreferencesFactoryUtil.getLayoutPortletSetup(
						serviceContext.getCompanyId(),
						serviceContext.getScopeGroupId(),
						PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
						LayoutConstants.DEFAULT_PLID, portletId,
						StringPool.BLANK);
			}

			if (portletSetup != null) {
				Iterator<String> iterator = portletPreferencesJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					String value = portletPreferencesJSONObject.getString(key);

					portletSetup.setValue(key, value);
				}

				portletSetup.store();
			}
		}
	}

	protected void setThemePortletSettings(ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = getThemePortletSettingJSONArray();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String portletName = jsonObject.getString("portletName");

			setCPContentPortletSettings(
				jsonObject, portletName, serviceContext);

			setCPSearchResultPortletSettings(
				jsonObject, portletName, serviceContext);

			setCPCategoryNavigationPortletSettings(
				jsonObject, portletName, serviceContext);

			setCPCategoryContentPortletSettings(
				jsonObject, portletName, serviceContext);

			setBreadCrumbPortletSettings(
				jsonObject, portletName, serviceContext);

			setCommerceCartContentPortletSettings(
				jsonObject, portletName, serviceContext);

			setCommerceCartContentTotalPortletSettings(
				jsonObject, portletName, serviceContext);

			setSearchBarPortletSettings(
				jsonObject, portletName, serviceContext);
		}
	}

	protected void setThemeWebContent(ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = getThemeStructuresJSONArray();
		long ddmStructureClassNameId = _portal.getClassNameId(
			DDMStructure.class);
		long journalArticleClassNameId = _portal.getClassNameId(
			"com.liferay.journal.model.JournalArticle");
		Class<?> clazz = getClass();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String structureName = jsonObject.getString("structureName");
			String structureFileName = jsonObject.getString(
				"structureFileName");
			String templateName = jsonObject.getString("templateName");
			String templateFileName = jsonObject.getString("templateFileName");

			String structureDefinition = StringUtil.read(
				clazz.getClassLoader(),
				_STRUCTURES_DEPENDENCY_PATH + structureFileName, true);

			DDMForm ddmForm = _ddmFormJSONDeserializer.deserialize(
				structureDefinition);

			DDMFormLayout ddmFormLayout = _ddm.getDefaultDDMFormLayout(ddmForm);

			DDMStructure ddmStructure = addDDMStructure(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				structureName, structureName, ddmForm, ddmFormLayout,
				serviceContext);

			_cpFileImporter.getDDMTemplate(
				_getFile(_TEMPLATES_DEPENDENCY_PATH + templateFileName),
				ddmStructureClassNameId, ddmStructure.getStructureId(),
				journalArticleClassNameId, templateName,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
				TemplateConstants.LANG_TYPE_FTL, serviceContext);
		}
	}

	protected void updateCompany(ServiceContext serviceContext)
		throws Exception {

		byte[] logoBytes = null;

		FileEntry fileEntry = createFile(
			_getImagesPath(), _COMPANY_LOGO_IMAGE_NAME, serviceContext);

		logoBytes = FileUtil.getBytes(fileEntry.getContentStream());

		Locale locale = LocaleUtil.getSiteDefault();

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		_companyService.updateCompany(
			serviceContext.getCompanyId(), "localhost", "liferay.com", null,
			true, logoBytes, LanguageUtil.get(resourceBundle, "breccia"), null,
			null, null, null, null, "Breccia", "Commerce", null);
	}

	protected void updateSiteLogo(ServiceContext serviceContext)
		throws Exception {

		byte[] logoBytes = null;

		FileEntry fileEntry = createFile(
			_getImagesPath(), _LOGO_IMAGE_NAME, serviceContext);

		logoBytes = FileUtil.getBytes(fileEntry.getContentStream());

		_layoutSetService.updateLogo(
			serviceContext.getScopeGroupId(), false, true, logoBytes);
	}

	private void _addCommerceUserSegment(
			String organizationId, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(
			serviceContext.getLocale(), _configuration.userSegmentName());

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				nameMap, "gold-program", true, true, 1, serviceContext);

		_commerceUserSegmentCriterionLocalService.addCommerceUserSegmentCriterion(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
			CommerceUserSegmentCriterionConstants.TYPE_ORGANIZATION,
			organizationId, 1, serviceContext);
	}

	private void _addDemoAccountOrganizations(
			long organizationId, String groupName,
			ServiceContext serviceContext)
		throws Exception {

		int i = 0;

		String[] accounts = _configuration.accounts();

		while (i < accounts.length) {
			String name = _configuration.accounts()[i] + " at " + groupName;

			Organization org = _organizationLocalService.fetchOrganization(
				serviceContext.getCompanyId(), name);

			if (org == null) {
				org = _organizationLocalService.addOrganization(
					serviceContext.getUserId(), organizationId, name,
					CommerceOrganizationConstants.TYPE_ACCOUNT, 0L, 0L,
					ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
					StringPool.BLANK, false, serviceContext);
			}

			_addNewAddress(
				serviceContext.getUserId(), org.getOrganizationId(),
				Organization.class.getName(), org.getGroupId(),
				serviceContext.getScopeGroupId());

			_createDefaultAccountUsers(org, serviceContext);

			if (i == 0) {
				_addCommerceUserSegment(
					String.valueOf(org.getOrganizationId()), serviceContext);
			}

			i++;
		}
	}

	private void _addNewAddress(
			long userId, long classPK, String className, long groupId,
			long commerceSiteGroupId)
		throws PortalException {

		ServiceContext newOrgServiceContext = getServiceContext(groupId);

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.fetchCommerceCountry(
				commerceSiteGroupId, _configuration.countryISOCode());

		long commerceCountryId = commerceCountry.getCommerceCountryId();

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountryId, _configuration.regionCode());

		long commerceRegionId = commerceRegion.getCommerceRegionId();

		Country country = _countryService.fetchCountryByA2(
			_configuration.country2LettersISOCode());

		long countryId = country.getCountryId();

		Region region = _regionService.getRegion(
			countryId, _configuration.regionCode());

		long regionId = region.getRegionId();

		_commerceAddressService.addCommerceAddress(
			Organization.class.getName(), classPK, "Account Address", "",
			_configuration.street1(), StringPool.BLANK, StringPool.BLANK,
			_configuration.city(), _configuration.zip(), commerceRegionId,
			commerceCountryId, "123456789", true, true, newOrgServiceContext);

		_addressLocalService.addAddress(
			userId, className, classPK, _configuration.street1(),
			StringPool.BLANK, StringPool.BLANK, _configuration.city(),
			_configuration.zip(), regionId, countryId,
			_configuration.addressTypeId(), false, true, newOrgServiceContext);
	}

	private Role _addOrganizationRole(
			String roleName, ServiceContext serviceContext)
		throws PortalException {

		Role role = _roleLocalService.fetchRole(
			serviceContext.getCompanyId(), roleName);

		if (role == null) {
			Map<Locale, String> titleMap = new HashMap<>();

			titleMap.put(
				serviceContext.getLocale(), roleName.replace("-", " "));

			role = _roleLocalService.addRole(serviceContext.getUserId(), null, 0, roleName, titleMap, null,
				RoleConstants.TYPE_ORGANIZATION, null, serviceContext);
		}

		return role;
	}

	private void _createConfiguration(String name) throws Exception {
		Configuration[] configurations = _configurationAdmin.listConfigurations(
			_getConfigurationFilter());

		if (configurations != null) {
			for (Configuration configuration : configurations) {
				Dictionary<String, Object> props =
					configuration.getProperties();

				String roleName = (String)props.get("roleName");

				if (name.equals(roleName)) {
					return;
				}
			}
		}

		Configuration configuration =
			_configurationAdmin.createFactoryConfiguration(
				_COMMERCE_ROLE_CONFIGURATION_PID, StringPool.QUESTION);

		Dictionary<String, Object> props = configuration.getProperties();

		if (props == null) {
			props = new Hashtable<>();
		}

		props.put("roleName", name);

		configuration.update(props);
	}

	private void _createDefaultAccountUsers(
			Organization account, ServiceContext serviceContext)
		throws PortalException {

		int i = 0;
		String emailAddress = StringPool.BLANK;
		String firstName = StringPool.BLANK;
		long roleId = 0;
		String roleName = StringPool.BLANK;
		User user = null;

		while (i < 2) {
			if (i == 0) {
				String accountName = account.getName();

				emailAddress =
					"buyer." + accountName.replace(" ", "") + "@liferay.com";
				roleName = _configuration.buyerRoleName();
				firstName = "Buyer";
			}
			else {
				String accountName = account.getName();

				emailAddress =
					"accountmanager." + accountName.replace(" ", "") +
						"@liferay.com";
				roleName = _configuration.accountManagerRoleName();
				firstName = "Account Manager";
			}

			roleId = _addOrganizationRole(roleName, serviceContext).getRoleId();
			user = _userLocalService.fetchUserByEmailAddress(
				serviceContext.getCompanyId(), emailAddress);

			if (user == null) {
				user = _userLocalService.addUserWithWorkflow(
					serviceContext.getUserId(), serviceContext.getCompanyId(),
					false, "test", "test", true, null, emailAddress, 0L, null,
					serviceContext.getLocale(), firstName, "",
					account.getName(), 0, 0, true, 1, 1, 1970, null, null,
					new long[] {account.getOrganizationId()},
					new long[] {roleId}, null, false, serviceContext);
			}

			if (!_userGroupRoleLocalService.hasUserGroupRole(
					user.getUserId(), account.getGroupId(), roleId)) {

				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), account.getGroupId(),
					new long[] {roleId});
			}

			if (!_organizationLocalService.hasUserOrganization(
					user.getUserId(), account.getOrganizationId())) {

				_organizationLocalService.addUserOrganization(
					user.getUserId(), account.getOrganizationId());
			}

			i++;
		}
	}

	private String _getConfigurationFilter() {
		StringBundler sb = new StringBundler(5);

		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(ConfigurationAdmin.SERVICE_FACTORYPID);
		sb.append(StringPool.EQUAL);
		sb.append(_COMMERCE_ROLE_CONFIGURATION_PID);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private File _getFile(String location) throws IOException {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(location);

		return FileUtil.createTempFile(inputStream);
	}

	private String _getImagesPath() {
		return "com/liferay/commerce/initializer/internal/dependencies/images/";
	}

	private void _setPlidPortletPreferences(
			long plid, String portletId, ServiceContext serviceContext)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				serviceContext.getCompanyId(),
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, plid, portletId,
				StringPool.BLANK);

		portletSetup.store();
	}

	private static final String _BREADCRUMB_PORTLET_NAME =
		"com_liferay_site_navigation_breadcrumb_web_portlet_" +
			"SiteNavigationBreadcrumbPortlet";

	private static final String _COMMERCE_CART_CONTENT_PORTLET_CLASS_NAME =
		"com.liferay.commerce.cart.content.web.internal.portlet." +
			"CommerceCartContentPortlet";

	private static final String _COMMERCE_CART_CONTENT_TOTAL_PORTLET_CLASS_NAME =
		"com.liferay.commerce.cart.content.web.internal.portlet." +
			"CommerceCartContentTotalPortlet";

	private static final String _COMMERCE_ROLE_CONFIGURATION_PID =
		"com.liferay.commerce.user.web.internal.configuration." +
			"CommerceRoleGroupServiceConfiguration";

	private static final String _COMPANY_LOGO_IMAGE_NAME =
		"Breccia-Company-Logo.png";

	private static final String _CP_ASSET_CATEGORIES_NAVIGATION_PORTLET_CLASS_NAME =
		"com.liferay.commerce.product.asset.categories.navigation.web." +
			"internal.portlet.CPAssetCategoriesNavigationPortlet";

	private static final String _CP_ASSET_CATEGORIES_NAVIGATION_PORTLET_NAME =
		"com_liferay_commerce_product_asset_categories_navigation_web" +
			"_internal_portlet_CPAssetCategoriesNavigationPortlet";

	private static final String _CP_CATEGORY_CONTENT_PORTLET_CLASS_NAME =
		"com.liferay.commerce.product.content.category.web.internal.portlet." +
			"CPCategoryContentPortlet";

	private static final String _CP_CATEGORY_CONTENT_PORTLET_NAME =
		"com_liferay_commerce_product_content_web_" +
			"internal_portlet_CPCategoryContentPortlet";

	private static final String _CP_CONTENT_PORTLET_NAME =
		"com_liferay_commerce_product_content_web_internal_portlet_" +
			"CPContentPortlet";

	private static final String _CP_SEARCH_RESULT_PORTLET_CLASS_NAME =
		"com.liferay.commerce.product.content.search.web.internal.portlet." +
			"CPSearchResultsPortlet";

	private static final String _CP_SEARCH_RESULT_PORTLET_NAME =
		"com_liferay_commerce_product_content_search_web_internal_portlet_" +
			"CPSearchResultsPortlet";

	private static final String _CUSTOMER_PORTAL_THEME_ID =
		"commercethememinette_WAR_commercethememinettetheme";

	private static final String _DEPENDENCY_PATH =
		"com/liferay/commerce/initializer/internal/dependencies/data/";

	private static final String _DEPENDENCY_PATH_ADT =
		_DEPENDENCY_PATH + "adts/";

	private static final String _LOGO_IMAGE_NAME = "Breccia-Logo-Square.png";

	private static final String _SEARCH_BAR_PORTLET_NAME =
		"com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet";

	private static final String _SIMPLE_CP_TYPE_CLASS_NAME =
		"com.liferay.commerce.product.type.simple.internal.SimpleCPType";

	private static final String _STRUCTURES_DEPENDENCY_PATH =
		_DEPENDENCY_PATH + "structures/";

	private static final String _TEMPLATES_DEPENDENCY_PATH =
		_DEPENDENCY_PATH + "templates/";

	private static final Log _log = LogFactoryUtil.getLog(
		BrecciaGroupInitializer.class);

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private CommerceAddressLocalService _commerceAddressService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceOrganizationLocalService _commerceOrganizationLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private CommerceUserSegmentCriterionLocalService
		_commerceUserSegmentCriterionLocalService;

	@Reference
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@Reference
	private CommerceWarehouseLocalService _commerceWarehouseLocalService;

	@Reference
	private CompanyLocalService _companyService;

	private volatile BrecciaInitializerConfiguration _configuration;

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private CountryService _countryService;

	@Reference(target = "(demo.name=breccia)")
	private CPDemoDataCreator _cpDemoDataCreator;

	@Reference
	private CPFileImporter _cpFileImporter;

	@Reference
	private DDM _ddm;

	@Reference
	private DDMFormJSONDeserializer _ddmFormJSONDeserializer;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlDLFileEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JournalArticleDataCreator _journalArticleDataCreator;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RegionService _regionService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.initializer.breccia)"
	)
	private ServletContext _servletContext;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}