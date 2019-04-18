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

import com.liferay.commerce.product.catalog.CommerceCatalogScopeHelperRegistry;
import com.liferay.commerce.product.indexer.CommerceCatalogScopeHelper;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogLocalServiceImpl
	extends CommerceCatalogLocalServiceBaseImpl {

	@Override
	public CommerceCatalog addCommerceCatalog(
			Map<Locale, String> nameMap, String catalogDefaultLanguageId,
			ServiceContext serviceContext)
		throws PortalException {

		long commerceCatalogId = counterLocalService.increment();

		CommerceCatalog commerceCatalog = commerceCatalogPersistence.create(
			commerceCatalogId);

		User user = userLocalService.getUser(serviceContext.getUserId());

		commerceCatalog.setCompanyId(user.getCompanyId());
		commerceCatalog.setUserId(user.getUserId());
		commerceCatalog.setUserName(user.getFullName());

		Date now = new Date();

		commerceCatalog.setCreateDate(now);
		commerceCatalog.setModifiedDate(now);

		commerceCatalog.setNameMap(nameMap);
		commerceCatalog.setCatalogDefaultLanguageId(catalogDefaultLanguageId);

		commerceCatalogPersistence.update(commerceCatalog);

		// Group

		groupLocalService.addGroup(
			user.getUserId(), GroupConstants.DEFAULT_PARENT_GROUP_ID,
			CommerceCatalog.class.getName(), commerceCatalogId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, null,
			GroupConstants.TYPE_SITE_PRIVATE, false,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false, true,
			null);

		// Indexer

		CommerceCatalogScopeHelper commerceCatalogScopeHelper =
			getCommerceCatalogScopeHelper();

		commerceCatalogScopeHelper.reindex(commerceCatalog);

		return commerceCatalog;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCatalog addCommerceCatalog(
			String name, String catalogDefaultLanguageId,
			ServiceContext serviceContext)
		throws PortalException {

		Locale locale = LocaleUtil.fromLanguageId(catalogDefaultLanguageId);

		return commerceCatalogLocalService.addCommerceCatalog(
			Collections.singletonMap(locale, name), catalogDefaultLanguageId,
			serviceContext);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		// Group

		Group group = getCommerceCatalogGroup(commerceCatalogId);

		groupLocalService.deleteGroup(group);

		// Commerce catalog

		CommerceCatalog commerceCatalog = commerceCatalogPersistence.remove(
			commerceCatalogId);

		// Indexer

		CommerceCatalogScopeHelper commerceCatalogScopeHelper =
			getCommerceCatalogScopeHelper();

		commerceCatalogScopeHelper.deleteDocument(commerceCatalog);

		return commerceCatalog;
	}

	@Override
	public Group getCommerceCatalogGroup(long commerceCatalogId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.getCommerceCatalog(commerceCatalogId);

		long classNameId = classNameLocalService.getClassNameId(
			CommerceCatalog.class.getName());

		return groupPersistence.findByC_C_C(
			commerceCatalog.getCompanyId(), classNameId, commerceCatalogId);
	}

	@Override
	public List<CommerceCatalog> searchCommerceCatalogs(long companyId)
		throws PortalException {

		Map<String, String> parameterMap = new HashMap<>();

		parameterMap.put(Field.COMPANY_ID, String.valueOf(companyId));
		parameterMap.put(
			Field.ENTRY_CLASS_NAME, CommerceCatalog.class.getName());

		CommerceCatalogScopeHelper commerceCatalogScopeHelper =
			getCommerceCatalogScopeHelper();

		Hits hits = commerceCatalogScopeHelper.search(companyId, parameterMap);

		if (hits == null) {
			return Collections.emptyList();
		}

		return getCommerceCatalogs(hits);
	}

	@Override
	public List<CommerceCatalog> searchCommerceCatalogs(
			long companyId, String keywords, int start, int end)
		throws PortalException {

		Map<String, String> parameterMap = new HashMap<>();

		parameterMap.put(Field.COMPANY_ID, String.valueOf(companyId));
		parameterMap.put(
			Field.ENTRY_CLASS_NAME, CommerceCatalog.class.getName());

		if (!Validator.isBlank(keywords)) {
			parameterMap.put(Field.NAME, keywords);
		}

		CommerceCatalogScopeHelper commerceCatalogScopeHelper =
			getCommerceCatalogScopeHelper();

		Hits hits = commerceCatalogScopeHelper.search(
			companyId, parameterMap, start, end);

		if (hits == null) {
			return Collections.emptyList();
		}

		return getCommerceCatalogs(hits);
	}

	@Override
	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, String catalogDefaultLanguageId,
			Map<Locale, String> nameMap, ServiceContext serviceContext)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogPersistence.findByPrimaryKey(commerceCatalogId);

		commerceCatalog.setNameMap(nameMap);
		commerceCatalog.setCatalogDefaultLanguageId(catalogDefaultLanguageId);

		commerceCatalog = commerceCatalogPersistence.update(commerceCatalog);

		// Indexer

		CommerceCatalogScopeHelper commerceCatalogScopeHelper =
			getCommerceCatalogScopeHelper();

		commerceCatalogScopeHelper.reindex(commerceCatalog);

		return commerceCatalog;
	}

	protected List<CommerceCatalog> getCommerceCatalogs(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceCatalog> commerceCatalogs = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceCatalogId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceCatalog commerceCatalog = fetchCommerceCatalog(
				commerceCatalogId);

			if (commerceCatalog != null) {
				commerceCatalogs.add(commerceCatalog);
			}
		}

		return commerceCatalogs;
	}

	protected CommerceCatalogScopeHelper getCommerceCatalogScopeHelper() {
		return _commerceCatalogScopeHelperRegistry.
			getCommerceCatalogScopeHelper(CommerceCatalog.class.getName());
	}

	@ServiceReference(type = CommerceCatalogScopeHelperRegistry.class)
	private CommerceCatalogScopeHelperRegistry
		_commerceCatalogScopeHelperRegistry;

}