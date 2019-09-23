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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAccountGroupCommerceAccountRelService}.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupCommerceAccountRelService
 * @generated
 */
public class CommerceAccountGroupCommerceAccountRelServiceWrapper
	implements CommerceAccountGroupCommerceAccountRelService,
			   ServiceWrapper<CommerceAccountGroupCommerceAccountRelService> {

	public CommerceAccountGroupCommerceAccountRelServiceWrapper(
		CommerceAccountGroupCommerceAccountRelService
			commerceAccountGroupCommerceAccountRelService) {

		_commerceAccountGroupCommerceAccountRelService =
			commerceAccountGroupCommerceAccountRelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountGroupCommerceAccountRelServiceUtil} to access the commerce account group commerce account rel remote service. Add custom service methods to <code>com.liferay.commerce.account.service.impl.CommerceAccountGroupCommerceAccountRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					addCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupId, long commerceAccountId,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			addCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroupCommerceAccountRel(
			long commerceAccountGroupCommerceAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAccountGroupCommerceAccountRelService.
			deleteCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupCommerceAccountRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel>
					getCommerceAccountGroupCommerceAccountRels(
						long commerceAccountGroupId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			getCommerceAccountGroupCommerceAccountRels(
				commerceAccountGroupId, start, end);
	}

	@Override
	public int getCommerceAccountGroupCommerceAccountRelsCount(
			long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			getCommerceAccountGroupCommerceAccountRelsCount(
				commerceAccountGroupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAccountGroupCommerceAccountRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceAccountGroupCommerceAccountRelService getWrappedService() {
		return _commerceAccountGroupCommerceAccountRelService;
	}

	@Override
	public void setWrappedService(
		CommerceAccountGroupCommerceAccountRelService
			commerceAccountGroupCommerceAccountRelService) {

		_commerceAccountGroupCommerceAccountRelService =
			commerceAccountGroupCommerceAccountRelService;
	}

	private CommerceAccountGroupCommerceAccountRelService
		_commerceAccountGroupCommerceAccountRelService;

}