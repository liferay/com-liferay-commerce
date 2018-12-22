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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.base.CProductLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

/**
 * @author Ethan Bustad
 */
public class CProductLocalServiceImpl extends CProductLocalServiceBaseImpl {

	@Override
	public CProduct addCProduct(ServiceContext serviceContext)
		throws PortalException {

		CProduct cProduct = cProductLocalService.createCProduct(
			counterLocalService.increment());

		cProduct.setUuid(serviceContext.getUuid());
		cProduct.setGroupId(serviceContext.getScopeGroupId());

		User user = userLocalService.getUser(serviceContext.getUserId());

		cProduct.setCompanyId(user.getCompanyId());
		cProduct.setUserId(user.getUserId());
		cProduct.setUserName(user.getFullName());

		Date now = new Date();

		cProduct.setCreateDate(now);
		cProduct.setModifiedDate(now);

		return cProductPersistence.update(cProduct);
	}

	@Override
	public CProduct updateDraftDefinitionId(
			long cProductId, long draftDefinitionId)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		long originalDraftDefinitionId = cProduct.getDraftDefinitionId();

		if (originalDraftDefinitionId == draftDefinitionId) {
			return cProduct;
		}

		Date now = new Date();

		cProduct.setModifiedDate(now);

		cProduct.setDraftDefinitionId(draftDefinitionId);

		if (draftDefinitionId == cProduct.getPublishedDefinitionId()) {
			cProduct.setPublishedDefinitionId(0);
		}

		cProduct = cProductPersistence.update(cProduct);

		reindexCPDefinition(originalDraftDefinitionId);
		reindexCPDefinition(draftDefinitionId);

		return cProduct;
	}

	@Override
	public CProduct updatePublishedDefinitionId(
			long cProductId, long publishedDefinitionId)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		long originalPublishedDefinitionId =
			cProduct.getPublishedDefinitionId();

		if (originalPublishedDefinitionId == publishedDefinitionId) {
			return cProduct;
		}

		Date now = new Date();

		cProduct.setModifiedDate(now);

		if (publishedDefinitionId == cProduct.getDraftDefinitionId()) {
			cProduct.setDraftDefinitionId(0);
		}

		cProduct.setPublishedDefinitionId(publishedDefinitionId);

		cProduct = cProductPersistence.update(cProduct);

		reindexCPDefinition(originalPublishedDefinitionId);
		reindexCPDefinition(publishedDefinitionId);

		return cProduct;
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

}