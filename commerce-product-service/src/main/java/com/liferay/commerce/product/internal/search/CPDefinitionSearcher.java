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

package com.liferay.commerce.product.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.search.CPDefinitionIndexer;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.asset.util.AssetUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPDefinitionSearcher extends BaseSearcher {

	public CPDefinitionSearcher(CPQuery cpQuery) {
		_cpQuery = cpQuery;

		setDefaultSelectedFieldNames(
			Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.UID, Field.NAME,
			Field.DESCRIPTION, Field.URL,
			CPDefinitionIndexer.FIELD_SHORT_DESCRIPTION,
			CPDefinitionIndexer.FIELD_DEFAULT_IMAGE_FILE_URL,
			CPDefinitionIndexer.FIELD_DEPTH, CPDefinitionIndexer.FIELD_HEIGHT,
			CPDefinitionIndexer.FIELD_IS_IGNORE_SKU_COMBINATIONS,
			CPDefinitionIndexer.FIELD_PRODUCT_TYPE_NAME,
			CPDefinitionIndexer.FIELD_DEFAULT_IMAGE_FILE_URL);
	}

	@Override
	public String[] getSearchClassNames() {
		return new String[] {CPDefinition.class.getName()};
	}

	protected void addImpossibleTerm(
			BooleanFilter queryBooleanFilter, String field)
		throws Exception {

		queryBooleanFilter.addTerm(field, "-1", BooleanClauseOccur.MUST);
	}

	protected void addSearchAllCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] allCategoryIds = _cpQuery.getAllCategoryIds();

		if (allCategoryIds.length == 0) {
			return;
		}

		long[] filteredAllCategoryIds = AssetUtil.filterCategoryIds(
			PermissionThreadLocal.getPermissionChecker(), allCategoryIds);

		if (allCategoryIds.length != filteredAllCategoryIds.length) {
			addImpossibleTerm(queryBooleanFilter, Field.ASSET_CATEGORY_IDS);

			return;
		}

		BooleanFilter categoryIdsBooleanFilter = new BooleanFilter();

		for (long allCategoryId : filteredAllCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(allCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						allCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(allCategoryId);
			}

			TermsFilter categoryIdTermsFilter = new TermsFilter(
				Field.ASSET_CATEGORY_IDS);

			categoryIdTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));

			categoryIdsBooleanFilter.add(
				categoryIdTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			categoryIdsBooleanFilter, BooleanClauseOccur.MUST);
	}

	protected void addSearchAllTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[][] allTagIdsArray = _cpQuery.getAllTagIdsArray();

		if (allTagIdsArray.length == 0) {
			return;
		}

		BooleanFilter tagIdsArrayBooleanFilter = new BooleanFilter();

		for (long[] allTagIds : allTagIdsArray) {
			if (allTagIds.length == 0) {
				continue;
			}

			TermsFilter tagIdsTermsFilter = new TermsFilter(
				Field.ASSET_TAG_IDS);

			tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(allTagIds));

			tagIdsArrayBooleanFilter.add(
				tagIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			tagIdsArrayBooleanFilter, BooleanClauseOccur.MUST);
	}

	protected void addSearchAnyCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] anyCategoryIds = _cpQuery.getAnyCategoryIds();

		if (anyCategoryIds.length == 0) {
			return;
		}

		long[] filteredAnyCategoryIds = AssetUtil.filterCategoryIds(
			PermissionThreadLocal.getPermissionChecker(), anyCategoryIds);

		if (filteredAnyCategoryIds.length == 0) {
			addImpossibleTerm(queryBooleanFilter, Field.ASSET_CATEGORY_IDS);

			return;
		}

		TermsFilter categoryIdsTermsFilter = new TermsFilter(
			Field.ASSET_CATEGORY_IDS);

		for (long anyCategoryId : filteredAnyCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(anyCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						anyCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(anyCategoryId);
			}

			categoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));
		}

		queryBooleanFilter.add(categoryIdsTermsFilter, BooleanClauseOccur.MUST);
	}

	protected void addSearchAnyTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] anyTagIds = _cpQuery.getAnyTagIds();

		if (anyTagIds.length == 0) {
			return;
		}

		TermsFilter tagIdsTermsFilter = new TermsFilter(Field.ASSET_TAG_IDS);

		tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(anyTagIds));

		queryBooleanFilter.add(tagIdsTermsFilter, BooleanClauseOccur.MUST);
	}

	@Override
	protected void addSearchAssetCategoryIds(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		addSearchAllCategories(queryBooleanFilter);
		addSearchAnyCategories(queryBooleanFilter);
		addSearchNotAnyCategories(queryBooleanFilter);
		addSearchNotAllCategories(queryBooleanFilter);
	}

	@Override
	protected void addSearchAssetTagNames(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		addSearchAllTags(queryBooleanFilter);
		addSearchAnyTags(queryBooleanFilter);
		addSearchNotAllTags(queryBooleanFilter);
		addSearchNotAnyTags(queryBooleanFilter);
	}

	@Override
	protected Map<String, Query> addSearchKeywords(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNull(keywords)) {
			return Collections.emptyMap();
		}

		Map<String, Query> queries = super.addSearchKeywords(
			searchQuery, searchContext);

		String field = Field.getLocalizedName(
			searchContext.getLocale(), "localized_title");

		Query query = searchQuery.addTerm(field, keywords, true);

		queries.put(field, query);

		return queries;
	}

	@Override
	protected void addSearchLayout(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		String layoutUuid = (String)searchContext.getAttribute(
			Field.LAYOUT_UUID);

		if (Validator.isNotNull(layoutUuid)) {
			queryBooleanFilter.addRequiredTerm(Field.LAYOUT_UUID, layoutUuid);
		}
	}

	protected void addSearchNotAllCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAllCategoryIds = _cpQuery.getNotAllCategoryIds();

		if (notAllCategoryIds.length == 0) {
			return;
		}

		BooleanFilter categoryIdsBooleanFilter = new BooleanFilter();

		for (long notAllCategoryId : notAllCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(
					notAllCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						notAllCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(notAllCategoryId);
			}

			TermsFilter categoryIdTermsFilter = new TermsFilter(
				Field.ASSET_CATEGORY_IDS);

			categoryIdTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));

			categoryIdsBooleanFilter.add(
				categoryIdTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			categoryIdsBooleanFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAllTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[][] notAllTagIdsArray = _cpQuery.getNotAllTagIdsArray();

		if (notAllTagIdsArray.length == 0) {
			return;
		}

		BooleanFilter tagIdsArrayBooleanFilter = new BooleanFilter();

		for (long[] notAllTagIds : notAllTagIdsArray) {
			if (notAllTagIds.length == 0) {
				continue;
			}

			TermsFilter tagIdsTermsFilter = new TermsFilter(
				Field.ASSET_TAG_IDS);

			tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(notAllTagIds));

			tagIdsArrayBooleanFilter.add(
				tagIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			tagIdsArrayBooleanFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAnyCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAnyCategoryIds = _cpQuery.getNotAnyCategoryIds();

		if (notAnyCategoryIds.length == 0) {
			return;
		}

		TermsFilter categoryIdsTermsFilter = new TermsFilter(
			Field.ASSET_CATEGORY_IDS);

		for (long notAnyCategoryId : notAnyCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(
					notAnyCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						notAnyCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(notAnyCategoryId);
			}

			categoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));
		}

		queryBooleanFilter.add(
			categoryIdsTermsFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAnyTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAnyTagIds = _cpQuery.getNotAnyTagIds();

		if (notAnyTagIds.length == 0) {
			return;
		}

		TermsFilter tagIgsTermsFilter = new TermsFilter(Field.ASSET_TAG_IDS);

		tagIgsTermsFilter.addValues(ArrayUtil.toStringArray(notAnyTagIds));

		queryBooleanFilter.add(tagIgsTermsFilter, BooleanClauseOccur.MUST_NOT);
	}

	private final CPQuery _cpQuery;

}