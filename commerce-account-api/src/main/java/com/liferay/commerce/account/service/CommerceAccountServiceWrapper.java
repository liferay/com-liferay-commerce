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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAccountService}.
 *
 * @author Marco Leo
 * @see CommerceAccountService
 * @generated
 */
@ProviderType
public class CommerceAccountServiceWrapper implements CommerceAccountService,
	ServiceWrapper<CommerceAccountService> {
	public CommerceAccountServiceWrapper(
		CommerceAccountService commerceAccountService) {
		_commerceAccountService = commerceAccountService;
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		String name, long parentCommerceAccountId, String email, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.addCommerceAccount(name,
			parentCommerceAccountId, email, taxId, active,
			externalReferenceCode, serviceContext);
	}

	@Override
	public void deleteCommerceAccount(long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceAccountService.deleteCommerceAccount(commerceAccountId);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount fetchCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.fetchCommerceAccount(commerceAccountId);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount getCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.getCommerceAccount(commerceAccountId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAccountService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.getUserCommerceAccounts(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		Long parentCommerceAccountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.getUserCommerceAccounts(parentCommerceAccountId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		long groupId, long parentCommerceAccountId, String keywords,
		Boolean active, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.searchCommerceAccounts(groupId,
			parentCommerceAccountId, keywords, active, start, end, sort);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		long commerceAccountId, String name, boolean logo, byte[] logoBytes,
		String email, String taxId, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.updateCommerceAccount(commerceAccountId,
			name, logo, logoBytes, email, taxId, active, serviceContext);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		String name, long parentCommerceAccountId, boolean logo,
		byte[] logoBytes, String email, String taxId, boolean active,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountService.upsertCommerceAccount(name,
			parentCommerceAccountId, logo, logoBytes, email, taxId, active,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CommerceAccountService getWrappedService() {
		return _commerceAccountService;
	}

	@Override
	public void setWrappedService(CommerceAccountService commerceAccountService) {
		_commerceAccountService = commerceAccountService;
	}

	private CommerceAccountService _commerceAccountService;
}