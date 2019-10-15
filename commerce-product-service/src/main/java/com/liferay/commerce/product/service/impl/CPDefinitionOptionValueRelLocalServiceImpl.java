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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.exception.CPDefinitionOptionValueRelKeyException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.base.CPDefinitionOptionValueRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPDefinitionOptionValueRelLocalServiceImpl
	extends CPDefinitionOptionValueRelLocalServiceBaseImpl {

	@Override
	public CPDefinitionOptionValueRel addCPDefinitionOptionValueRel(
			long cpDefinitionOptionRelId, CPOptionValue cpOptionValue,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionOptionValueRelLocalService.
			addCPDefinitionOptionValueRel(
				cpDefinitionOptionRelId, cpOptionValue.getNameMap(),
				cpOptionValue.getPriority(), cpOptionValue.getKey(),
				serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionOptionValueRel addCPDefinitionOptionValueRel(
			long cpDefinitionOptionRelId, Map<Locale, String> nameMap,
			double priority, String key, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition option value rel

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		key = FriendlyURLNormalizerUtil.normalize(key);

		validate(0, cpDefinitionOptionRelId, key);

		long cpDefinitionOptionValueRelId = counterLocalService.increment();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			cpDefinitionOptionValueRelPersistence.create(
				cpDefinitionOptionValueRelId);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionRelLocalService.getCPDefinitionOptionRel(
				cpDefinitionOptionRelId);

		if (cpDefinitionLocalService.isVersionable(
				cpDefinitionOptionRel.getCPDefinitionId(),
				serviceContext.getRequest())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionOptionRel.getCPDefinitionId());

			cpDefinitionOptionRel = cpDefinitionOptionRelPersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpDefinitionOptionRel.getCPOptionId());

			cpDefinitionOptionRelId =
				cpDefinitionOptionRel.getCPDefinitionOptionRelId();
		}

		cpDefinitionOptionValueRel.setUuid(serviceContext.getUuid());
		cpDefinitionOptionValueRel.setGroupId(groupId);
		cpDefinitionOptionValueRel.setCompanyId(user.getCompanyId());
		cpDefinitionOptionValueRel.setUserId(user.getUserId());
		cpDefinitionOptionValueRel.setUserName(user.getFullName());
		cpDefinitionOptionValueRel.setCPDefinitionOptionRelId(
			cpDefinitionOptionRelId);
		cpDefinitionOptionValueRel.setNameMap(nameMap);
		cpDefinitionOptionValueRel.setPriority(priority);
		cpDefinitionOptionValueRel.setKey(key);
		cpDefinitionOptionValueRel.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionOptionValueRelPersistence.update(
			cpDefinitionOptionValueRel);

		// Commerce product definition

		reindexCPDefinition(cpDefinitionOptionRel);

		return cpDefinitionOptionValueRel;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinitionOptionValueRel deleteCPDefinitionOptionValueRel(
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel)
		throws PortalException {

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		if (cpDefinitionLocalService.isVersionable(
				cpDefinitionOptionRel.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionOptionRel.getCPDefinitionId());

			cpDefinitionOptionRel = cpDefinitionOptionRelPersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpDefinitionOptionRel.getCPOptionId());

			cpDefinitionOptionValueRel =
				cpDefinitionOptionValueRelPersistence.findByC_K(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
					cpDefinitionOptionValueRel.getKey());
		}

		// Commerce product definition option value rel

		cpDefinitionOptionValueRelPersistence.remove(
			cpDefinitionOptionValueRel);

		// Expando

		expandoRowLocalService.deleteRows(
			cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId());

		// Commerce product definition

		reindexCPDefinition(cpDefinitionOptionRel);

		return cpDefinitionOptionValueRel;
	}

	@Override
	public CPDefinitionOptionValueRel deleteCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId)
		throws PortalException {

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			cpDefinitionOptionValueRelPersistence.findByPrimaryKey(
				cpDefinitionOptionValueRelId);

		return cpDefinitionOptionValueRelLocalService.
			deleteCPDefinitionOptionValueRel(cpDefinitionOptionValueRel);
	}

	@Override
	public void deleteCPDefinitionOptionValueRels(long cpDefinitionOptionRelId)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionValueRelLocalService.
				getCPDefinitionOptionValueRels(
					cpDefinitionOptionRelId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

		for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
				cpDefinitionOptionValueRels) {

			cpDefinitionOptionValueRelLocalService.
				deleteCPDefinitionOptionValueRel(cpDefinitionOptionValueRel);
		}
	}

	@Override
	public CPDefinitionOptionValueRel fetchCPDefinitionOptionValueRel(
		long cpDefinitionOptionRelId, String key) {

		return cpDefinitionOptionValueRelPersistence.fetchByC_K(
			cpDefinitionOptionRelId, key);
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
		long cpDefinitionOptionRelId) {

		return cpDefinitionOptionValueRelPersistence.
			findByCPDefinitionOptionRelId(cpDefinitionOptionRelId);
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
		long cpDefinitionOptionRelId, int start, int end) {

		return cpDefinitionOptionValueRelPersistence.
			findByCPDefinitionOptionRelId(cpDefinitionOptionRelId, start, end);
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
		long cpDefinitionOptionRelId, int start, int end,
		OrderByComparator<CPDefinitionOptionValueRel> orderByComparator) {

		return cpDefinitionOptionValueRelPersistence.
			findByCPDefinitionOptionRelId(
				cpDefinitionOptionRelId, start, end, orderByComparator);
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
			long[] cpDefinitionOptionValueRelsId)
		throws PortalException {

		if ((cpDefinitionOptionValueRelsId == null) ||
			(cpDefinitionOptionValueRelsId.length == 0)) {

			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = dynamicQuery();

		Property property = PropertyFactoryUtil.forName(
			"CPDefinitionOptionValueRelId");

		Criterion criterion = property.in(cpDefinitionOptionValueRelsId);

		dynamicQuery.add(criterion);

		dynamicQuery.addOrder(OrderFactoryUtil.asc("priority"));

		return cpDefinitionOptionValueRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
		String key, int start, int end) {

		return cpDefinitionOptionValueRelPersistence.findByKey(key, start, end);
	}

	@Override
	public int getCPDefinitionOptionValueRelsCount(
		long cpDefinitionOptionRelId) {

		return cpDefinitionOptionValueRelPersistence.
			countByCPDefinitionOptionRelId(cpDefinitionOptionRelId);
	}

	@Override
	public void importCPDefinitionOptionRels(
			long cpDefinitionOptionRelId, ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionRelLocalService.getCPDefinitionOptionRel(
				cpDefinitionOptionRelId);

		CPOption cpOption = cpOptionLocalService.fetchCPOption(
			cpDefinitionOptionRel.getCPOptionId());

		if (cpOption == null) {
			return;
		}

		List<CPOptionValue> cpOptionValues =
			cpOptionValueLocalService.getCPOptionValues(
				cpOption.getCPOptionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CPOptionValue cpOptionValue : cpOptionValues) {
			cpDefinitionOptionValueRelLocalService.
				addCPDefinitionOptionValueRel(
					cpDefinitionOptionRelId, cpOptionValue, serviceContext);
		}
	}

	@Override
	public Hits search(SearchContext searchContext) {
		try {
			Indexer<CPDefinitionOptionValueRel> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(
					CPDefinitionOptionValueRel.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public BaseModelSearchResult<CPDefinitionOptionValueRel>
			searchCPDefinitionOptionValueRels(
				long companyId, long groupId, long cpDefinitionOptionRelId,
				String keywords, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, cpDefinitionOptionRelId, keywords, start, end,
			sort);

		return searchCPOptions(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionOptionValueRel updateCPDefinitionOptionValueRel(
			long cpDefinitionOptionValueRelId, Map<Locale, String> nameMap,
			double priority, String key, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition option value rel

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			cpDefinitionOptionValueRelPersistence.findByPrimaryKey(
				cpDefinitionOptionValueRelId);

		key = FriendlyURLNormalizerUtil.normalize(key);

		validate(
			cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId(),
			cpDefinitionOptionValueRel.getCPDefinitionOptionRelId(), key);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		if (cpDefinitionLocalService.isVersionable(
				cpDefinitionOptionRel.getCPDefinitionId(),
				serviceContext.getRequest())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionOptionRel.getCPDefinitionId());

			cpDefinitionOptionRel = cpDefinitionOptionRelPersistence.findByC_C(
				newCPDefinition.getCPDefinitionId(),
				cpDefinitionOptionRel.getCPOptionId());

			cpDefinitionOptionValueRel =
				cpDefinitionOptionValueRelPersistence.findByC_K(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
					cpDefinitionOptionValueRel.getKey());
		}

		cpDefinitionOptionValueRel.setNameMap(nameMap);
		cpDefinitionOptionValueRel.setPriority(priority);
		cpDefinitionOptionValueRel.setKey(key);
		cpDefinitionOptionValueRel.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionOptionValueRelPersistence.update(
			cpDefinitionOptionValueRel);

		// Commerce product definition

		reindexCPDefinition(cpDefinitionOptionRel);

		return cpDefinitionOptionValueRel;
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, long cpDefinitionOptionRelId,
		String keywords, int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(_FIELD_KEY, keywords);
		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(Field.NAME, keywords);
		attributes.put("CPDefinitionOptionRelId", cpDefinitionOptionRelId);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {groupId});

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		return searchContext;
	}

	protected List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRels(
			Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			new ArrayList<>(documents.size());

		for (Document document : documents) {
			long cpDefinitionOptionValueRelId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
				fetchCPDefinitionOptionValueRel(cpDefinitionOptionValueRelId);

			if (cpDefinitionOptionValueRel == null) {
				cpDefinitionOptionValueRels = null;

				Indexer<CPDefinitionOptionValueRel> indexer =
					IndexerRegistryUtil.getIndexer(
						CPDefinitionOptionValueRel.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (cpDefinitionOptionValueRels != null) {
				cpDefinitionOptionValueRels.add(cpDefinitionOptionValueRel);
			}
		}

		return cpDefinitionOptionValueRels;
	}

	protected void reindexCPDefinition(
			CPDefinitionOptionRel cpDefinitionOptionRel)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(
			CPDefinition.class.getName(),
			cpDefinitionOptionRel.getCPDefinitionId());
	}

	protected BaseModelSearchResult<CPDefinitionOptionValueRel> searchCPOptions(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CPDefinitionOptionValueRel> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(
				CPDefinitionOptionValueRel.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
				getCPDefinitionOptionValueRels(hits);

			if (cpDefinitionOptionValueRels != null) {
				return new BaseModelSearchResult<>(
					cpDefinitionOptionValueRels, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validate(
			long cpDefinitionOptionValueRelId, long cpDefinitionOptionRelId,
			String key)
		throws PortalException {

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			cpDefinitionOptionValueRelPersistence.fetchByC_K(
				cpDefinitionOptionRelId, key);

		if ((cpDefinitionOptionValueRel != null) &&
			(cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId() !=
				cpDefinitionOptionValueRelId)) {

			throw new CPDefinitionOptionValueRelKeyException();
		}
	}

	private static final String _FIELD_KEY = "key";

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID
	};

}