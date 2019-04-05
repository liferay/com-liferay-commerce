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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CategoryDTOMapper.class)
public class CategoryDTOMapper {

	public Category toCategory(AssetCategory assetCategory) {
		Category category = new Category();

		if (assetCategory == null) {
			return category;
		}

		try {
			AssetVocabulary assetVocabulary =
				_assetVocabularyLocalService.getAssetVocabulary(
					assetCategory.getVocabularyId());

			category.setVocabulary(assetVocabulary.getName());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate CategoryDTO ", e);

			throw new RuntimeException(e);
		}

		category.setExternalReferenceCode(
			assetCategory.getExternalReferenceCode());
		category.setId(assetCategory.getCategoryId());
		category.setName(assetCategory.getName());
		category.setSiteId(assetCategory.getGroupId());

		return category;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CategoryDTOMapper.class);

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

}