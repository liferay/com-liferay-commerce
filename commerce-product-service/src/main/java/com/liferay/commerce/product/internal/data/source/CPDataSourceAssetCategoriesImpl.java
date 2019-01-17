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

package com.liferay.commerce.product.internal.data.source;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "commerce.product.data.source.name=" + CPDataSourceAssetCategoriesImpl.NAME,
	service = CPDataSource.class
)
public class CPDataSourceAssetCategoriesImpl implements CPDataSource {

	public static final String NAME = "assetCategoriesDataSource";

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "products-of-the-same-categories");
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public CPDataSourceResult getResult(
			HttpServletRequest httpServletRequest, int start, int end)
		throws Exception {

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		CPCatalogEntry cpCatalogEntry =
			(CPCatalogEntry)httpServletRequest.getAttribute(
				CPWebKeys.CP_CATALOG_ENTRY);

		if (cpCatalogEntry == null) {
			return new CPDataSourceResult(new ArrayList<>(), 0);
		}

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		attributes.put(
			"excludedCPDefinitionId", cpCatalogEntry.getCPDefinitionId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", StringPool.STAR);

		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(_portal.getCompanyId(httpServletRequest));
		searchContext.setGroupIds(new long[] {groupId});

		searchContext.setKeywords(StringPool.STAR);

		CPQuery cpQuery = new CPQuery();

		AssetEntry assetEntry = _assetEntryLocalService.getEntry(
			CPDefinition.class.getName(), cpCatalogEntry.getCPDefinitionId());

		cpQuery.setAnyCategoryIds(assetEntry.getCategoryIds());

		return _cpDefinitionHelper.search(
			groupId, searchContext, cpQuery, start, end);
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private Portal _portal;

}