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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.base.CommerceAccountServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountServiceImpl extends CommerceAccountServiceBaseImpl {

	@Override
	public CommerceAccount addCommerceAccount(
			long userId, long parentCommerceAccountId, String name,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountLocalService.addCommerceAccount(
			userId, parentCommerceAccountId, name, taxId, active,
			externalReferenceCode, serviceContext);
	}

	@Override
	public BaseModelSearchResult<CommerceAccount> searchCommerceAccounts(
			long companyId, long parentCommerceAccountId, String keywords,
			Boolean active, int start, int end, Sort sort)
		throws PortalException {

		return commerceAccountLocalService.searchCommerceAccounts(
			companyId, parentCommerceAccountId, keywords, active, start, end,
			sort);
	}

	@Override
	public CommerceAccount updateCommerceAccount(
			long commerceAccountId, String name, String taxId, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountLocalService.updateCommerceAccount(
			commerceAccountId, name, taxId, active, serviceContext);
	}

	@Override
	public CommerceAccount upsertCommerceAccount(
			long userId, long parentCommerceAccountId, String name,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountLocalService.upsertCommerceAccount(
			userId, parentCommerceAccountId, name, taxId, active,
			externalReferenceCode, serviceContext);
	}

}