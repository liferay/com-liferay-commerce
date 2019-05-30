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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.initializer.util.AssetCategoriesImporter;
import com.liferay.commerce.initializer.util.CPDefinitionsImporter;
import com.liferay.commerce.initializer.util.CPOptionCategoriesImporter;
import com.liferay.commerce.initializer.util.CPOptionsImporter;
import com.liferay.commerce.initializer.util.CPSpecificationOptionsImporter;
import com.liferay.commerce.initializer.util.CommerceWarehousesImporter;
import com.liferay.commerce.initializer.util.PortletSettingsImporter;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.product.importer.CPFileImporter;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelConstants;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.ThemeSetting;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 * @author Guy Wandji
 * @author Andrea Di Giorgi
 */
@Component(
	immediate = true,
	property = "site.initializer.key=" + BrecciaSiteInitializer.KEY,
	service = SiteInitializer.class
)
public class BrecciaSiteInitializer implements SiteInitializer {

	public static final String KEY = "breccia-initializer";

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

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() +
			"/images/Breccia-Logo-Square.jpg";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			_initialize(groupId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		Theme theme = _themeLocalService.fetchTheme(
			companyId, _BRECCIA_THEME_ID);

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info(_BRECCIA_THEME_ID + " is not registered");
			}

			return false;
		}

		return true;
	}

	private void _cleanLayouts(ServiceContext serviceContext)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Cleaning layouts...");
		}

		_cpFileImporter.cleanLayouts(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Layouts successfully cleaned");
		}
	}

	private CommerceCatalog _createCatalog(ServiceContext serviceContext)
		throws Exception {

		Group group = serviceContext.getScopeGroup();

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				serviceContext.getCompanyId());

		return _commerceCatalogLocalService.addCommerceCatalog(
			group.getNameMap(), commerceCurrency.getCode(),
			serviceContext.getLanguageId(), StringPool.BLANK, serviceContext);
	}

	private CommerceChannel _createChannel(
			CommerceCatalog commerceCatalog, ServiceContext serviceContext)
		throws Exception {

		Group group = serviceContext.getScopeGroup();

		return _commerceChannelLocalService.addCommerceChannel(
			group.getGroupId(), group.getName(serviceContext.getLanguageId()),
			CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
			commerceCatalog.getCommerceCurrencyCode(), StringPool.BLANK,
			serviceContext);
	}

	private String _getJSON(String name) throws IOException {
		return StringUtil.read(
			BrecciaSiteInitializer.class.getClassLoader(),
			_DEPENDENCIES_PATH + name);
	}

	private JSONArray _getJSONArray(String name) throws Exception {
		String json = _getJSON(name);

		return _jsonFactory.createJSONArray(json);
	}

	private JSONObject _getJSONObject(String name) throws Exception {
		String json = _getJSON(name);

		return _jsonFactory.createJSONObject(json);
	}

	private ServiceContext _getServiceContext(long groupId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Group group = _groupLocalService.getGroup(groupId);

		serviceContext.setCompanyId(group.getCompanyId());

		Locale locale = LocaleUtil.getSiteDefault();

		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));

		serviceContext.setScopeGroupId(groupId);

		User user = _userLocalService.getUser(PrincipalThreadLocal.getUserId());

		serviceContext.setTimeZone(user.getTimeZone());
		serviceContext.setUserId(user.getUserId());

		return serviceContext;
	}

	private void _importAssetCategories(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing asset categories...");
		}

		Group group = serviceContext.getScopeGroup();

		ClassLoader classLoader = BrecciaSiteInitializer.class.getClassLoader();

		String json = StringUtil.read(
			classLoader, _DEPENDENCIES_PATH + "categories.json");

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		_assetCategoriesImporter.importAssetCategories(
			jsonArray,
			_COMMERCE_VOCABULARY + group.getName(serviceContext.getLocale()),
			classLoader, _DEPENDENCIES_PATH + "images/",
			serviceContext.getScopeGroupId(), serviceContext.getUserId(), true);

		if (_log.isInfoEnabled()) {
			_log.info("Asset categories successfully imported");
		}
	}

	private List<CommerceInventoryWarehouse> _importCommerceWarehouses(
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce warehouses...");
		}

		_commerceWarehousesImporter.importDefaultCommerceWarehouse(
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		JSONArray jsonArray = _getJSONArray("warehouses.json");

		List<CommerceInventoryWarehouse> commerceWarehouses =
			_commerceWarehousesImporter.importCommerceWarehouses(
				jsonArray, serviceContext.getScopeGroupId(),
				serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce warehouses successfully imported");
		}

		return commerceWarehouses;
	}

	private void _importCPDefinitions(
			long catalogGroupId, long commerceChannelId,
			List<CommerceInventoryWarehouse> commerceInventoryWarehouses,
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product definitions...");
		}

		Group group = serviceContext.getScopeGroup();

		JSONArray jsonArray = _getJSONArray("products.json");

		long[] commerceInventoryWarehouseIds = ListUtil.toLongArray(
			commerceInventoryWarehouses,
			CommerceInventoryWarehouse.
				COMMERCE_INVENTORY_WAREHOUSE_ID_ACCESSOR);

		_cpDefinitionsImporter.importCPDefinitions(
			jsonArray,
			_COMMERCE_VOCABULARY + group.getName(serviceContext.getLocale()),
			catalogGroupId, commerceChannelId, commerceInventoryWarehouseIds,
			BrecciaSiteInitializer.class.getClassLoader(),
			_DEPENDENCIES_PATH + "images/", serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce product definitions successfully imported");
		}
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

	private void _importCPOptions(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product options...");
		}

		JSONArray jsonArray = _getJSONArray("options.json");

		_cpOptionsImporter.importCPOptions(
			jsonArray, serviceContext.getScopeGroupId(),
			serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Commerce product options successfully imported");
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

	private void _importDefaultCommerceCountries(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing default commerce countries...");
		}

		_commerceCountryLocalService.importDefaultCountries(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Commerce countries successfully imported");
		}
	}

	private void _importDefaultCommerceCurrencies(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing default commerce currencies...");
		}

		_commerceCurrencyLocalService.importDefaultValues(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Commerce currencies successfully imported");
		}
	}

	private void _importDefaultCPMeasurementUnits(ServiceContext serviceContext)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product measurement units...");
		}

		_cpMeasurementUnitLocalService.importDefaultValues(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product measurement units successfully imported");
		}
	}

	private void _importJournalArticles(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing journal articles...");
		}

		JSONArray jsonArray = _getJSONArray("journal-articles.json");

		_cpFileImporter.createJournalArticles(
			jsonArray, BrecciaSiteInitializer.class.getClassLoader(),
			_DEPENDENCIES_PATH + "journal_articles/", serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Journal articles successfully imported");
		}
	}

	private void _importLayouts(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing layouts...");
		}

		ClassLoader classLoader = BrecciaSiteInitializer.class.getClassLoader();

		String json = StringUtil.read(
			classLoader, _DEPENDENCIES_PATH + "layouts.json");

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		_cpFileImporter.createLayouts(
			jsonArray, classLoader, _DEPENDENCIES_PATH, serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Layouts successfully imported");
		}
	}

	private void _importPortletSettings(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing portlet settings...");
		}

		JSONArray jsonArray = _getJSONArray("portlet-settings.json");

		_portletSettingsImporter.importPortletSettings(
			jsonArray, BrecciaSiteInitializer.class.getClassLoader(),
			_DEPENDENCIES_PATH + "display_templates/",
			serviceContext.getScopeGroupId(), serviceContext.getUserId());

		if (_log.isInfoEnabled()) {
			_log.info("Portlet settings successfully imported");
		}
	}

	private void _importSiteNavigationMenuPortletSettings(
			JSONObject jsonObject, String portletName,
			ServiceContext serviceContext)
		throws Exception {

		if (portletName.equals(_SITE_NAVIGATION_MENU_PORTLET_NAME)) {
			String instanceId = jsonObject.getString("instanceId");
			String layoutFriendlyURL = jsonObject.getString(
				"layoutFriendlyURL");
			String rootLayoutFriendlyURL = jsonObject.getString(
				"rootLayoutFriendlyURL");

			JSONObject portletPreferencesJSONObject = jsonObject.getJSONObject(
				"portletPreferences");

			Layout rootLayout = null;

			if (Validator.isNotNull(rootLayoutFriendlyURL)) {
				rootLayout = _layoutLocalService.fetchLayoutByFriendlyURL(
					serviceContext.getScopeGroupId(), false,
					rootLayoutFriendlyURL);
			}

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
				else if (key.equals("rootLayoutUuid")) {
					if (rootLayout == null) {
						value = StringPool.BLANK;
					}
					else {
						value = rootLayout.getUuid();
					}
				}

				portletSetup.setValue(key, value);
			}

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

	private void _importThemePortletSettings(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing theme portlet settings...");
		}

		JSONArray jsonArray = _getJSONArray("theme-portlet-settings.json");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String portletName = jsonObject.getString("portletName");

			_importSiteNavigationMenuPortletSettings(
				jsonObject, portletName, serviceContext);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Theme portlet settings successfully imported");
		}
	}

	private void _importThemeSettings(ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing theme settings...");
		}

		JSONObject themeSettingsJSONObject = _getJSONObject(
			"theme-settings.json");

		Iterator<String> iterator = themeSettingsJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String value = themeSettingsJSONObject.getString(key);

			_updateThemeSetting(key, value, serviceContext);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Theme settings successfully imported");
		}
	}

	private void _initialize(long groupId) throws Exception {
		ServiceContext serviceContext = _getServiceContext(groupId);

		_importDefaultCommerceCountries(serviceContext);
		_importDefaultCommerceCurrencies(serviceContext);
		_importDefaultCPMeasurementUnits(serviceContext);

		_updateThemeLookAndFeel(serviceContext);

		_cleanLayouts(serviceContext);

		_importLayouts(serviceContext);

		_importAssetCategories(serviceContext);

		_importCPOptionCategories(serviceContext);

		_importCPOptions(serviceContext);
		_importCPSpecificationOptions(serviceContext);

		CommerceCatalog commerceCatalog = _createCatalog(serviceContext);

		long catalogGroupId = commerceCatalog.getCommerceCatalogGroupId();

		CommerceChannel commerceChannel = _createChannel(
			commerceCatalog, serviceContext);

		List<CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_importCommerceWarehouses(serviceContext);

		_importCPDefinitions(
			catalogGroupId, commerceChannel.getCommerceChannelId(),
			commerceInventoryWarehouses, serviceContext);

		_importJournalArticles(serviceContext);
		_importPortletSettings(serviceContext);
		_importThemePortletSettings(serviceContext);
		_importThemeSettings(serviceContext);

		_updateLogo(serviceContext);
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

	private void _updateLogo(ServiceContext serviceContext) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Updating logo...");
		}

		ClassLoader classLoader = BrecciaSiteInitializer.class.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			_DEPENDENCIES_PATH + "images/Breccia-Logo.png");

		File file = FileUtil.createTempFile(inputStream);

		_cpFileImporter.updateLogo(file, false, true, serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Logo successfully updated");
		}
	}

	private void _updateThemeLookAndFeel(ServiceContext serviceContext)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Updating theme look and feel...");
		}

		_cpFileImporter.updateLookAndFeel(
			_BRECCIA_THEME_ID, false, serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Theme look and feel successfully updated");
		}
	}

	private void _updateThemeSetting(
		String key, String value, ServiceContext serviceContext) {

		Theme theme = _themeLocalService.fetchTheme(
			serviceContext.getCompanyId(), _BRECCIA_THEME_ID);

		if (theme == null) {
			return;
		}

		Map<String, ThemeSetting> configurableSettings =
			theme.getConfigurableSettings();

		ThemeSetting themeSetting = configurableSettings.get(key);

		themeSetting.setValue(value);
	}

	private static final String _BRECCIA_THEME_ID =
		"breccia_WAR_commercebrecciatheme";

	private static final String _COMMERCE_VOCABULARY = "Commerce_";

	private static final String _DEPENDENCIES_PATH =
		"com/liferay/commerce/initializer/breccia/internal/dependencies/";

	private static final String _SITE_NAVIGATION_MENU_PORTLET_NAME =
		"com_liferay_site_navigation_menu_web_portlet_" +
			"SiteNavigationMenuPortlet";

	private static final Log _log = LogFactoryUtil.getLog(
		BrecciaSiteInitializer.class);

	@Reference
	private AssetCategoriesImporter _assetCategoriesImporter;

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceWarehousesImporter _commerceWarehousesImporter;

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
	private CPSpecificationOptionsImporter _cpSpecificationOptionsImporter;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private PortletSettingsImporter _portletSettingsImporter;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.initializer.breccia)"
	)
	private ServletContext _servletContext;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private UserLocalService _userLocalService;

}