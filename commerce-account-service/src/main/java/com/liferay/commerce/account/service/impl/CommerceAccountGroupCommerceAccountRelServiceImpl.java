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

import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.service.base.CommerceAccountGroupCommerceAccountRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupCommerceAccountRelServiceImpl
	extends CommerceAccountGroupCommerceAccountRelServiceBaseImpl {

	@Override
	public CommerceAccountGroupCommerceAccountRel
			addCommerceAccountGroupCommerceAccountRel(
				long commerceAccountGroupId, long commerceAccountId,
				ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountGroupCommerceAccountRelLocalService.
			addCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroupCommerceAccountRel(
			long commerceAccountGroupCommerceAccountRelId)
		throws PortalException {

		commerceAccountGroupCommerceAccountRelLocalService.
			deleteCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupCommerceAccountRelId);
	}

	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
			getCommerceAccountGroupCommerceAccountRels(
				long commerceAccountGroupId, int start, int end)
		throws PortalException {

		return commerceAccountGroupCommerceAccountRelLocalService.
			getCommerceAccountGroupCommerceAccountRels(
				commerceAccountGroupId, start, end);
	}

	@Override
	public int getCommerceAccountGroupCommerceAccountRelsCount(
			long commerceAccountGroupId)
		throws PortalException {

		return commerceAccountGroupCommerceAccountRelLocalService.
			getCommerceAccountGroupCommerceAccountRelsCount(
				commerceAccountGroupId);
	}

}