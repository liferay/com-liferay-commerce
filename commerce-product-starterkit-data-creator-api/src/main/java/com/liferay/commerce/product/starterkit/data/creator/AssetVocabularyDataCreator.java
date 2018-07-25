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

package com.liferay.commerce.product.starterkit.data.creator;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 */
@ProviderType
public interface AssetVocabularyDataCreator {

	/**
	 * Returns a map with pairs [key,categoryId] of the categories created for
	 * this vocabulary
	 *
	 * @param userId
	 * @param groupId
	 * @param assetVocabularyJSONObject
	 * @return
	 * @throws Exception
	 */
	public Map addAssetVocabularies(
			long userId, long groupId, JSONObject assetVocabularyJSONObject)
		throws Exception;

	public Map createAssetVocabulary(
			JSONObject assetVocabularyJSONObject, ServiceContext serviceContext)
		throws Exception;

}