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

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogLocalServiceImpl
	extends CommerceCatalogLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
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

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		// Commerce catalog

		CommerceCatalog commerceCatalog = commerceCatalogPersistence.remove(
			commerceCatalogId);

		// Group

		Group group = commerceCatalogLocalService.getCommerceCatalogGroup(
			commerceCatalogId);

		groupLocalService.deleteGroup(group);

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

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, String catalogDefaultLanguageId,
			Map<Locale, String> nameMap, ServiceContext serviceContext)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogPersistence.findByPrimaryKey(commerceCatalogId);

		commerceCatalog.setNameMap(nameMap);
		commerceCatalog.setCatalogDefaultLanguageId(catalogDefaultLanguageId);

		return commerceCatalogPersistence.update(commerceCatalog);
	}

}