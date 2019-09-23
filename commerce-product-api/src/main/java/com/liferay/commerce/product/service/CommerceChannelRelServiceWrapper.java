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

package com.liferay.commerce.product.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelRelService}.
 *
 * @author Marco Leo
 * @see CommerceChannelRelService
 * @generated
 */
public class CommerceChannelRelServiceWrapper
	implements CommerceChannelRelService,
			   ServiceWrapper<CommerceChannelRelService> {

	public CommerceChannelRelServiceWrapper(
		CommerceChannelRelService commerceChannelRelService) {

		_commerceChannelRelService = commerceChannelRelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceChannelRelServiceUtil} to access the commerce channel rel remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceChannelRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannelRel
			addCommerceChannelRel(
				String className, long classPK, long commerceChannelId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelRelService.addCommerceChannelRel(
			className, classPK, commerceChannelId, serviceContext);
	}

	@Override
	public void deleteCommerceChannelRels(String className, long classPK) {
		_commerceChannelRelService.deleteCommerceChannelRels(
			className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelRel>
		getCommerceChannelRels(
			long commerceChannelId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CommerceChannelRel>
					orderByComparator) {

		return _commerceChannelRelService.getCommerceChannelRels(
			commerceChannelId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannelRel>
		getCommerceChannelRels(
			String className, long classPK, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CommerceChannelRel>
					orderByComparator) {

		return _commerceChannelRelService.getCommerceChannelRels(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public int getCommerceChannelRelsCount(long commerceChannelId) {
		return _commerceChannelRelService.getCommerceChannelRelsCount(
			commerceChannelId);
	}

	@Override
	public int getCommerceChannelRelsCount(String className, long classPK) {
		return _commerceChannelRelService.getCommerceChannelRelsCount(
			className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceChannelRelService getWrappedService() {
		return _commerceChannelRelService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelRelService commerceChannelRelService) {

		_commerceChannelRelService = commerceChannelRelService;
	}

	private CommerceChannelRelService _commerceChannelRelService;

}