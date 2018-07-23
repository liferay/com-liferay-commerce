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

import com.liferay.commerce.initializer.breccia.internal.configuration.BrecciaInitializerConfiguration;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPTaxCategoryLocalService;
import com.liferay.commerce.product.starterkit.data.creator.AssetCategoryDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.AssetVocabularyDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.CPAttachmentFileEntryDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.CPDefinitionDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.CPDemoDataCreator;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 */
@Component(
	configurationPid = "com.liferay.commerce.initializer.breccia.internal.configuration.BrecciaInitializerConfiguration",
	immediate = true, property = "demo.name=breccia",
	service = CPDemoDataCreator.class
)
public class OSCPDemoDataCreatorImpl implements CPDemoDataCreator {

	@Override
	public void create(long userId, long groupId, boolean buildSkus)
		throws Exception {

		assetCategoryDataCreator.init();
		cpDefinitionDataCreator.init();

		_addAssetVocabularies(userId, groupId);

		_addCPDefinitions(userId, groupId, buildSkus);
	}

	public JSONArray getAssetVocabulariesJSONArray() throws Exception {
		Class<?> clazz = getClass();

		String assetVocabulariesPath = getAssetVocabulariesPath();

		String assetVocabulariesJSON = StringUtil.read(
			clazz.getClassLoader(), assetVocabulariesPath, false);

		return jsonFactory.createJSONArray(assetVocabulariesJSON);
	}

	public String getAssetVocabulariesPath() {
		return "com/liferay/commerce/initializer/internal/dependencies/data" +
			"/categories.json";
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

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			BrecciaInitializerConfiguration.class, properties);
	}

	protected JSONArray getCatalogJSONArray() throws Exception {
		Class<?> clazz = getClass();

		String catalogPath = _getCatalogPath();

		String catalogJSON = StringUtil.read(
			clazz.getClassLoader(), catalogPath, false);

		JSONArray catalogJSONArray = jsonFactory.createJSONArray(catalogJSON);

		return catalogJSONArray;
	}

	@Reference
	protected AssetCategoryDataCreator assetCategoryDataCreator;

	@Reference
	protected AssetVocabularyDataCreator assetVocabularyDataCreator;

	@Reference
	protected CPAttachmentFileEntryDataCreator cPAttachmentFileEntryDataCreator;

	@Reference
	protected CPDefinitionDataCreator cpDefinitionDataCreator;

	@Reference
	protected JSONFactory jsonFactory;

	private void _addAssetVocabularies(long userId, long groupId)
		throws Exception {

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		// Create Asset vocabulary and categories

		JSONArray assetVocabulariesJSONArray = getAssetVocabulariesJSONArray();
		HashMap categoryIds = new HashMap();

		for (int i = 0; i < assetVocabulariesJSONArray.length(); i++) {
			JSONObject assetVocabularyJSONObject =
				assetVocabulariesJSONArray.getJSONObject(i);

			categoryIds =
				(HashMap)assetVocabularyDataCreator.addAssetVocabularies(
					userId, groupId, assetVocabularyJSONObject);

			JSONArray categoriesJSONArray =
				assetVocabularyJSONObject.getJSONArray("categories");

			// Create category images

			for (int j = 0; j < categoriesJSONArray.length(); j++) {
				JSONObject categoryJSONObject =
					categoriesJSONArray.getJSONObject(j);

				String imageFileName = categoryJSONObject.getString("image");

				if ((imageFileName != null) & !imageFileName.isEmpty()) {
					if (categoryIds.get(categoryJSONObject.getString("key")) !=
							null) {

						long categoryId = (long)categoryIds.get(
							categoryJSONObject.getString("key"));

						InputStream inputStream =
							classLoader.getResourceAsStream(
								_getImagesPath() + imageFileName);

						if (inputStream != null) {
							cPAttachmentFileEntryDataCreator.
								addAssetCategoryAttachmentFileEntry(
									userId, groupId, categoryId, imageFileName,
									inputStream);
						}
						else {
							if (_log.isWarnEnabled()) {
								_log.warn(
									StringBundler.concat(
										"Image:", imageFileName,
										" does not exists for category:",
										categoryJSONObject.getString("key")));
							}
						}
					}
				}
			}
		}
	}

	private void _addCPDefinitions(long userId, long groupId, boolean buildSkus)
		throws Exception {

		JSONArray catalogJSONArray = getCatalogJSONArray();
		CPTaxCategory cpTaxCategory = _addDemoTaxCategory(
			getServiceContext(groupId));

		long cpDefinitionId;

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		CPDefinition cpDefinition = null;

		for (int i = 0; i < catalogJSONArray.length(); i++) {
			JSONObject productJSONObject = catalogJSONArray.getJSONObject(i);

			cpDefinition = cpDefinitionDataCreator.addCPDefinition(
				userId, groupId, buildSkus, productJSONObject);

			if (cpTaxCategory != null) {
				cpDefinition.setCPTaxCategoryId(
					cpTaxCategory.getCPTaxCategoryId());

				_cpDefinitionLocalService.updateCPDefinition(cpDefinition);
			}

			cpDefinitionId = cpDefinition.getCPDefinitionId();

			// Commerce product attachment file entries

			JSONArray cpAttachmentFileEntriesJSONArray =
				productJSONObject.getJSONArray("images");

			for (int j = 0; j < cpAttachmentFileEntriesJSONArray.length();
				j++) {

				String fileName = cpAttachmentFileEntriesJSONArray.getString(j);

				InputStream inputStream = classLoader.getResourceAsStream(
					_getImagesPath() + fileName);

				if (inputStream != null) {
					cPAttachmentFileEntryDataCreator.
						addCPDefinitionAttachmentFileEntry(
							userId, groupId, cpDefinitionId, fileName,
							inputStream, 1);
				}
				else {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Image:", fileName,
								" does not exists for product:",
								String.valueOf(cpDefinitionId)));
					}
				}
			}
		}
	}

	private CPTaxCategory _addDemoTaxCategory(ServiceContext serviceContext)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<>();

		Map<Locale, String> descriptionMap = new HashMap<>();

		nameMap.put(
			serviceContext.getLocale(), _configuration.demoTaxCategoryName());

		descriptionMap.put(
			serviceContext.getLocale(),
			_configuration.demoTaxCategoryDescription());

		return _cpTaxCategoryLocalService.addCPTaxCategory(
			nameMap, descriptionMap, serviceContext);
	}

	private String _getCatalogPath() {
		return "com/liferay/commerce/initializer/internal" +
			"/dependencies/data/catalog.json";
	}

	private String _getImagesPath() {
		return "com/liferay/commerce/initializer/internal/dependencies/images/";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OSCPDemoDataCreatorImpl.class);

	private volatile BrecciaInitializerConfiguration _configuration;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPTaxCategoryLocalService _cpTaxCategoryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private UserLocalService _userLocalService;

}