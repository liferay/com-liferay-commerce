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

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.base.CommerceAccountGroupLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;

/**

 * @author Marco Leo
 */
public class CommerceAccountGroupLocalServiceImpl
	extends CommerceAccountGroupLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccountGroup addCommerceAccountGroup(
			String name, int type, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce Account Group

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceAccountGroupId = counterLocalService.increment();

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.create(commerceAccountGroupId);

		commerceAccountGroup.setCompanyId(user.getCompanyId());
		commerceAccountGroup.setUserId(user.getUserId());
		commerceAccountGroup.setUserName(user.getFullName());
		commerceAccountGroup.setName(name);
		commerceAccountGroup.setType(type);
		commerceAccountGroup.setExternalReferenceCode(externalReferenceCode);
		commerceAccountGroup.setExpandoBridgeAttributes(serviceContext);

		commerceAccountGroupPersistence.update(commerceAccountGroup);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceAccountGroup.class.getName(),
			commerceAccountGroup.getCommerceAccountGroupId(), false, false,
			false);

		return commerceAccountGroup;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceAccountGroup deleteCommerceAccountGroup(
			CommerceAccountGroup commerceAccountGroup)
		throws PortalException {

		// Commerce account rels

		commerceAccountGroupCommerceAccountRelLocalService.
			deleteCommerceAccountGroupCommerceAccountRelByCAccountGroupId(
				commerceAccountGroup.getCommerceAccountGroupId());

		// Commerce account group

		commerceAccountGroupPersistence.remove(commerceAccountGroup);

		// Resources

		resourceLocalService.deleteResource(
			commerceAccountGroup, ResourceConstants.SCOPE_INDIVIDUAL);

		// Expando

		expandoRowLocalService.deleteRows(
			commerceAccountGroup.getCommerceAccountGroupId());

		return commerceAccountGroup;
	}

	@Override
	public CommerceAccountGroup deleteCommerceAccountGroup(
			long commerceAccountGroupId)
		throws PortalException {

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.findByPrimaryKey(
				commerceAccountGroupId);

		return commerceAccountGroupLocalService.deleteCommerceAccountGroup(
			commerceAccountGroup);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccountGroup updateCommerceAccountGroup(
			long commerceAccountGroupId, String name)
		throws PortalException {

		CommerceAccountGroup commerceAccountGroup =
			commerceAccountGroupPersistence.findByPrimaryKey(
				commerceAccountGroupId);

		commerceAccountGroup.setName(name);

		return commerceAccountGroupPersistence.update(commerceAccountGroup);
	}

}