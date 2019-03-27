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

package com.liferay.commerce.theme.minium.site.initializer.internal;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.util.CommerceAccountRoleHelper;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.initializer.util.AssetCategoriesImporter;
import com.liferay.commerce.initializer.util.CPDefinitionsImporter;
import com.liferay.commerce.initializer.util.CPOptionCategoriesImporter;
import com.liferay.commerce.initializer.util.CPOptionsImporter;
import com.liferay.commerce.initializer.util.CPRulesImporter;
import com.liferay.commerce.initializer.util.CPSpecificationOptionsImporter;
import com.liferay.commerce.initializer.util.CommerceAccountsImporter;
import com.liferay.commerce.initializer.util.CommercePriceEntriesImporter;
import com.liferay.commerce.initializer.util.CommercePriceListsImporter;
import com.liferay.commerce.initializer.util.CommerceUserSegmentsImporter;
import com.liferay.commerce.initializer.util.CommerceUsersImporter;
import com.liferay.commerce.initializer.util.CommerceWarehousesImporter;
import com.liferay.commerce.initializer.util.OrganizationImporter;
import com.liferay.commerce.initializer.util.PortletSettingsImporter;
import com.liferay.commerce.media.CommerceCatalogDefaultImage;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.product.importer.CPFileImporter;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPDefinitionLinkLocalService;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalService;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionLocalService;
import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolver;
import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolverThreadLocal;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.commerce.util.CommerceShippingEngineRegistry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.ThemeSetting;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;

import java.io.File;
import java.io.InputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "site.initializer.key=" + MiniumSiteInitializer.KEY,
	service = SiteInitializer.class
)
public class MiniumSiteInitializer implements SiteInitializer {

	public static final String KEY = "minium-initializer";

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "minium-description");
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "minium");
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() + "/images/thumbnail.png";
	}

	public void init() {
		_cpDefinitions = new HashMap<>();
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			SiteInitializerDependencyResolver
				siteInitializerDependencyResolver =
					SiteInitializerDependencyResolverThreadLocal.
						getSiteInitializerDependencyResolver();

			if (siteInitializerDependencyResolver != null) {
				_siteInitializerDependencyResolver =
					siteInitializerDependencyResolver;
			}

			ServiceContext serviceContext = getServiceContext(groupId);

			configureB2BSite(groupId, serviceContext);

			_cpFileImporter.updateLookAndFeel(
				_MINIUM_THEME_ID, true, serviceContext);

			updateLogo(serviceContext);

			createRoles(serviceContext);

			_miniumLayoutsInitializer.initialize(serviceContext);

			_importAssetCategories(serviceContext);

			_importCPOptionCategories(serviceContext);

			_importCPSpecificationOptions(serviceContext);

			List<CommerceWarehouse> commerceWarehouses =
				_importCommerceWarehouses(serviceContext);

			_importCPOptions(serviceContext);

			List<CPDefinition> cpDefinitions = _importCPDefinitions(
				commerceWarehouses, serviceContext);

			_importRelatedProducts(cpDefinitions, serviceContext);

			_importCommercePriceLists(serviceContext);

			_importCommercePriceEntries(serviceContext);

			_importCommerceOrganizations(serviceContext);

			_importCommerceAccounts(serviceContext);

			_importCommerceUsers(serviceContext);

			_importCommerceUserSegments(serviceContext);

			_importCPRules(serviceContext);

			_importThemePortletSettings(serviceContext);

			_importPortletSettings(serviceContext);

			setCommerceShippingMethod("fixed", serviceContext);

			setDefaultCatalogImage(serviceContext);

			setThemeSettings(serviceContext);
		}
		catch (InitializationException ie) {
			throw ie;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		Theme theme = _themeLocalService.fetchTheme(
			companyId, _MINIUM_THEME_ID);

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info(_MINIUM_THEME_ID + " is not registered");
			}

			return false;
		}

		return true;
	}

	@Activate
	protected void activate() {
		init();
	}

	protected void configureB2BSite(long groupId, ServiceContext serviceContext)
		throws Exception {

		Group group = _groupLocalService.getGroup(groupId);

		group.setType(GroupConstants.TYPE_SITE_PRIVATE);
		group.setManualMembership(true);
		group.setMembershipRestriction(
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION);

		_groupLocalService.updateGroup(group);

		_commerceCountryLocalService.importDefaultCountries(serviceContext);
		_commerceCurrencyLocalService.importDefaultValues(serviceContext);
		_commerceUserSegmentEntryLocalService.
			importSystemCommerceUserSegmentEntries(serviceContext);
		_commerceWarehousesImporter.importDefaultCommerceWarehouse(
			serviceContext.getScopeGroupId(), serviceContext.getUserId());
		_cpMeasurementUnitLocalService.importDefaultValues(serviceContext);

		_commerceAccountRoleHelper.checkCommerceAccountRoles(serviceContext);

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				groupId, CommerceAccountConstants.SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue(
			"commerceSiteType",
			String.valueOf(CommerceAccountConstants.SITE_TYPE_B2B));

		modifiableSettings.store();
	}

	protected void createRoles(ServiceContext serviceContext) throws Exception {
		JSONArray jsonArray = _getJSONArray("roles.json");

		_cpFileImporter.createRoles(jsonArray, serviceContext);

		updateUserRole(serviceContext);
	}

	@Deactivate
	protected void deactivate() {
		_cpDefinitions = null;
	}

	protected CPDefinition getCPDefinitionByName(String name) {
		return _cpDefinitions.get(name);
	}

	protected ServiceContext getServiceContext(long groupId)
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
		serviceContext.setTimeZone(user.getTimeZone());
		serviceContext.setUserId(user.getUserId());

		return serviceContext;
	}

	protected void setCommerceShippingMethod(
			String shippingMethod, ServiceContext serviceContext)
		throws PortalException {

		Locale locale = serviceContext.getLocale();

		CommerceShippingEngine commerceShippingEngine =
			_commerceShippingEngineRegistry.getCommerceShippingEngine(
				shippingMethod);

		Map<Locale, String> nameMap = new HashMap<>();
		Map<Locale, String> descriptionMap = new HashMap<>();

		nameMap.put(locale, commerceShippingEngine.getName(locale));
		descriptionMap.put(
			locale, commerceShippingEngine.getDescription(locale));

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodLocalService.addCommerceShippingMethod(
				nameMap, descriptionMap, null, shippingMethod, 0, true,
				serviceContext);

		setCommerceShippingOption(
			commerceShippingMethod.getCommerceShippingMethodId(),
			"Standard Delivery", StringPool.BLANK, BigDecimal.valueOf(15),
			serviceContext);

		setCommerceShippingOption(
			commerceShippingMethod.getCommerceShippingMethodId(),
			"Expedited Delivery", StringPool.BLANK, BigDecimal.valueOf(25),
			serviceContext);
	}

	protected void setCommerceShippingOption(
			long commerceShippingMethodId, String name, String description,
			BigDecimal price, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = new HashMap<>();
		Map<Locale, String> descriptionMap = new HashMap<>();

		nameMap.put(serviceContext.getLocale(), name);
		descriptionMap.put(serviceContext.getLocale(), description);

		_commerceShippingFixedOptionLocalService.addCommerceShippingFixedOption(
			commerceShippingMethodId, nameMap, descriptionMap, price, 0,
			serviceContext);
	}

	protected void setDefaultCatalogImage(ServiceContext serviceContext)
		throws Exception {

		ClassLoader classLoader =
			_siteInitializerDependencyResolver.getImageClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			_siteInitializerDependencyResolver.getImageDependencyPath() +
				"Minium_ProductImage_Default.png");

		File file = null;

		try {
			file = FileUtil.createTempFile(inputStream);

			String mimeType = MimeTypesUtil.getContentType(file);

			FileEntry fileEntry = TempFileEntryUtil.addTempFileEntry(
				serviceContext.getScopeGroupId(), serviceContext.getUserId(),
				MiniumSiteInitializer.class.getName(), file.getName(), file,
				mimeType);

			_commerceCatalogDefaultImage.updateDefaultCatalogFileEntryId(
				serviceContext.getScopeGroupId(), fileEntry.getFileEntryId());
		}
		finally {
			if (file != null) {
				FileUtil.delete(file);
			}
		}
	}

	protected void setThemeSettings(ServiceContext serviceContext)
		throws Exception {

		JSONObject themeSettingsJSONObject = _getJSONObject(
			"theme-settings.json");

		Iterator<String> iterator = themeSettingsJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String value = themeSettingsJSONObject.getString(key);

			updateThemeSetting(key, value, serviceContext);
		}
	}

	protected void updateLogo(ServiceContext serviceContext) throws Exception {
		ClassLoader classLoader =
			_siteInitializerDependencyResolver.getImageClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			_siteInitializerDependencyResolver.getImageDependencyPath() +
				"minium-logo.png");

		File file = FileUtil.createTempFile(inputStream);

		_cpFileImporter.updateLogo(file, true, true, serviceContext);
	}

	protected void updateThemeSetting(
		String key, String value, ServiceContext serviceContext) {

		Theme theme = _themeLocalService.fetchTheme(
			serviceContext.getCompanyId(), _MINIUM_THEME_ID);

		if (theme == null) {
			return;
		}

		Map<String, ThemeSetting> configurableSettings =
			theme.getConfigurableSettings();

		ThemeSetting themeSetting = configurableSettings.get(key);

		themeSetting.setValue(value);
	}

	protected void updateUserRole(ServiceContext serviceContext)
		throws PortalException {

		Role role = _roleLocalService.fetchRole(
			serviceContext.getCompanyId(), "User");

		_resourcePermissionLocalService.addResourcePermission(
			serviceContext.getCompanyId(), "com.liferay.commerce.product",
			ResourceConstants.SCOPE_GROUP_TEMPLATE,
			String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
			role.getRoleId(), "VIEW_PRICE");
	}

	private long[] _getCProductIds(JSONArray jsonArray) {
		List<Long> cProductIdsList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CPDefinition cpDefinitionEntry = getCPDefinitionByName(
				jsonArray.getString(i));

			cProductIdsList.add(cpDefinitionEntry.getCProductId());
		}

		return ArrayUtil.toLongArray(cProductIdsList);
	}

	private JSONArray _getJSONArray(String name) throws Exception {
		String json = _siteInitializerDependencyResolver.getJSON(name);

		return _jsonFactory.createJSONArray(json);
	}

	private JSONObject _getJSONObject(String name) throws Exception {
		String json = _siteInitializerDependencyResolver.getJSON(name);

		return _jsonFactory.createJSONObject(json);
	}

	private void _importAssetCategories(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing asset categories...");
		}

		JSONArray jsonArray = _getJSONArray("categories.json");

		_assetCategoriesImporter.importAssetCategories(
			jsonArray, _COMMERCE_VOCABULARY,
			_siteInitializerDependencyResolver.getImageClassLoader(),
			_siteInitializerDependencyResolver.getImageDependencyPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Asset categories successfully imported");
		}
	}

	private void _importCommerceAccounts(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing Commerce Accounts...");
		}

		JSONArray jsonArray = _getJSONArray("accounts.json");

		_commerceAccountsImporter.importCommerceAccounts(
			jsonArray, _siteInitializerDependencyResolver.getImageClassLoader(),
			_siteInitializerDependencyResolver.getDependenciesPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce Accounts successfully imported");
		}
	}

	private void _importCommerceOrganizations(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing organizations...");
		}

		JSONArray jsonArray = _getJSONArray("organizations.json");

		_organizationImporter.importOrganizations(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Organizations successfully imported");
		}
	}

	private void _importCommercePriceEntries(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce price entries...");
		}

		JSONArray jsonArray = _getJSONArray("price-entries.json");

		_commercePriceEntriesImporter.importCommercePriceEntries(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce price entries successfully imported");
		}
	}

	private void _importCommercePriceLists(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce price lists...");
		}

		JSONArray jsonArray = _getJSONArray("price-lists.json");

		_commercePriceListsImporter.importCommercePriceLists(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce price lists successfully imported");
		}
	}

	private void _importCommerceUsers(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing Commerce Users...");
		}

		JSONArray jsonArray = _getJSONArray("users.json");

		_commerceUsersImporter.importCommerceUsers(
			jsonArray, _siteInitializerDependencyResolver.getImageClassLoader(),
			_siteInitializerDependencyResolver.getImageDependencyPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce Users successfully imported");
		}
	}

	private void _importCommerceUserSegments(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing Commerce User Segments...");
		}

		JSONArray jsonArray = _getJSONArray("segments.json");

		_commerceUserSegmentsImporter.importCommerceUserSegments(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce User Segments successfully imported");
		}
	}

	private List<CommerceWarehouse> _importCommerceWarehouses(
			ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = _getJSONArray("warehouses.json");

		return _commerceWarehousesImporter.importCommerceWarehouses(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());
	}

	private List<CPDefinition> _importCPDefinitions(
			List<CommerceWarehouse> commerceWarehouses,
			ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = _getJSONArray("products.json");

		long[] commerceWarehouseIds = ListUtil.toLongArray(
			commerceWarehouses,
			CommerceWarehouse.COMMERCE_WAREHOUSE_ID_ACCESSOR);

		return _cpDefinitionsImporter.importCPDefinitions(
			jsonArray, _COMMERCE_VOCABULARY, commerceWarehouseIds,
			_siteInitializerDependencyResolver.getImageClassLoader(),
			_siteInitializerDependencyResolver.getImageDependencyPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());
	}

	private void _importCPOptionCategories(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product option categories...");
		}

		JSONArray jsonArray = _getJSONArray("option-categories.json");

		_cpOptionCategoriesImporter.importCPOptionCategories(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product option categories successfully imported");
		}
	}

	private List<CPOption> _importCPOptions(ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = _getJSONArray("options.json");

		return _cpOptionsImporter.importCPOptions(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());
	}

	private void _importCPRules(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing CPRules...");
		}

		JSONArray jsonArray = _getJSONArray("catalog-rules.json");

		_cpRulesImporter.importCPRules(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("CPRules imported");
		}
	}

	private void _importCPSpecificationOptions(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product specification options...");
		}

		JSONArray jsonArray = _getJSONArray("specification-options.json");

		_cpSpecificationOptionsImporter.importCPSpecificationOptions(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product specification options successfully imported");
		}
	}

	private void _importPortletSettings(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing portlet settings...");
		}

		JSONArray jsonArray = _getJSONArray("portlet-settings.json");

		_portletSettingsImporter.importPortletSettings(
			jsonArray,
			_siteInitializerDependencyResolver.getDisplayTemplatesClassLoader(),
			_siteInitializerDependencyResolver.
				getDisplayTemplatesDependencyPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Portlet settings successfully imported");
		}
	}

	private void _importRelatedProducts(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws Exception {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject productJSONObject = jsonArray.getJSONObject(i);

			String name = productJSONObject.getString("Name");

			CPDefinition cpDefinition = getCPDefinitionByName(name);

			JSONArray relatedProducts = productJSONObject.getJSONArray(
				"RelatedProducts");

			if (relatedProducts == null) {
				continue;
			}

			_cpDefinitionLinkLocalService.updateCPDefinitionLinkCProductIds(
				cpDefinition.getCPDefinitionId(),
				_getCProductIds(relatedProducts), "related", serviceContext);
		}
	}

	private void _importRelatedProducts(
			List<CPDefinition> cpDefinitions, ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing related products...");
		}

		for (CPDefinition cpDefinition : cpDefinitions) {
			_cpDefinitions.put(
				cpDefinition.getName(serviceContext.getLanguageId()),
				cpDefinition);
		}

		JSONArray jsonArray = _getJSONArray("products.json");

		_importRelatedProducts(jsonArray, serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Related products successfully imported");
		}
	}

	private void _importThemePortletSettings(ServiceContext serviceContext)
		throws Exception {

		JSONArray jsonArray = _getJSONArray("theme-portlet-settings.json");

		_portletSettingsImporter.importPortletSettings(
			jsonArray,
			_siteInitializerDependencyResolver.getDisplayTemplatesClassLoader(),
			_siteInitializerDependencyResolver.
				getDisplayTemplatesDependencyPath(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId());
	}

	private static final String _COMMERCE_VOCABULARY = "Commerce";

	private static final String _MINIUM_THEME_ID = "minium_WAR_miniumtheme";

	private static final Log _log = LogFactoryUtil.getLog(
		MiniumSiteInitializer.class);

	@Reference
	private AssetCategoriesImporter _assetCategoriesImporter;

	@Reference
	private CommerceAccountRoleHelper _commerceAccountRoleHelper;

	@Reference
	private CommerceAccountsImporter _commerceAccountsImporter;

	@Reference
	private CommerceCatalogDefaultImage _commerceCatalogDefaultImage;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommercePriceEntriesImporter _commercePriceEntriesImporter;

	@Reference
	private CommercePriceListsImporter _commercePriceListsImporter;

	@Reference
	private CommerceShippingEngineRegistry _commerceShippingEngineRegistry;

	@Reference
	private CommerceShippingFixedOptionLocalService
		_commerceShippingFixedOptionLocalService;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

	@Reference
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@Reference
	private CommerceUserSegmentsImporter _commerceUserSegmentsImporter;

	@Reference
	private CommerceUsersImporter _commerceUsersImporter;

	@Reference
	private CommerceWarehousesImporter _commerceWarehousesImporter;

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private CPDefinitionLinkLocalService _cpDefinitionLinkLocalService;

	private Map<String, CPDefinition> _cpDefinitions;

	@Reference
	private CPDefinitionsImporter _cpDefinitionsImporter;

	@Reference
	private CPFileImporter _cpFileImporter;

	@Reference
	private CPMeasurementUnitLocalService _cpMeasurementUnitLocalService;

	@Reference
	private CPOptionCategoriesImporter _cpOptionCategoriesImporter;

	@Reference
	private CPOptionsImporter _cpOptionsImporter;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private CPRulesImporter _cpRulesImporter;

	@Reference
	private CPRuleUserSegmentRelLocalService _cpRuleUserSegmentRelLocalService;

	@Reference
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

	@Reference
	private CPSpecificationOptionsImporter _cpSpecificationOptionsImporter;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MiniumLayoutsInitializer _miniumLayoutsInitializer;

	@Reference
	private OrganizationImporter _organizationImporter;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletSettingsImporter _portletSettingsImporter;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.theme.minium.site.initializer)"
	)
	private ServletContext _servletContext;

	@Reference
	private SettingsFactory _settingsFactory;

	@Reference(
		target = "(site.initializer.key=" + MiniumSiteInitializer.KEY + ")"
	)
	private SiteInitializerDependencyResolver
		_siteInitializerDependencyResolver;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private UserLocalService _userLocalService;

}