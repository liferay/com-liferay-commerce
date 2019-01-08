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
	public CProduct updatePublishedCPDefinitionId(
			long cProductId, long publishedCPDefinitionId)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		long originalPublishedCPDefinitionId =
			cProduct.getPublishedCPDefinitionId();

		if (originalPublishedCPDefinitionId == publishedCPDefinitionId) {
			return cProduct;
		}

		Date now = new Date();

		cProduct.setModifiedDate(now);

		cProduct.setPublishedCPDefinitionId(publishedCPDefinitionId);

		cProduct = cProductPersistence.update(cProduct);

		reindexCPDefinition(originalPublishedCPDefinitionId);
		reindexCPDefinition(publishedCPDefinitionId);

		return cProduct;
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

}