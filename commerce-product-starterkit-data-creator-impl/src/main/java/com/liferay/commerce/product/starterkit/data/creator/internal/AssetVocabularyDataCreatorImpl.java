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

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.product.starterkit.data.creator.AssetCategoryDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.AssetVocabularyDataCreator;
import com.liferay.commerce.product.starterkit.data.creator.internal.util.BaseCPDemoDataCreatorHelper;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 */
@Component(immediate = true, service = AssetVocabularyDataCreator.class)
public class AssetVocabularyDataCreatorImpl
	implements AssetVocabularyDataCreator {

	@Override
	public Map addAssetVocabularies(
			long userId, long groupId, JSONObject assetVocabularyJSONObject)
		throws Exception {

		ServiceContext serviceContext =
			_baseCPDemoDataCreatorHelper.getServiceContext(userId, groupId);

		// Asset vocabularies

		return createAssetVocabulary(assetVocabularyJSONObject, serviceContext);
	}

	@Override
	public Map createAssetVocabulary(
			JSONObject assetVocabularyJSONObject, ServiceContext serviceContext)
		throws Exception {

		String title = assetVocabularyJSONObject.getString("vocabulary");

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				serviceContext.getScopeGroupId(), title);

		if (assetVocabulary != null) {

			// Vocabulary already created. No need for further processing.

			return new HashMap();
		}

		assetVocabulary = _assetVocabularyLocalService.addVocabulary(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), title,
			serviceContext);

		JSONArray categoriesJSONArray = assetVocabularyJSONObject.getJSONArray(
			"categories");

		Map assetCategoryIds = _assetCategoryDemoDataCreator.addAssetCategories(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			assetVocabulary.getVocabularyId(),
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
			categoriesJSONArray);

		return assetCategoryIds;
	}

	@Reference
	private AssetCategoryDataCreator _assetCategoryDemoDataCreator;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private BaseCPDemoDataCreatorHelper _baseCPDemoDataCreatorHelper;

}