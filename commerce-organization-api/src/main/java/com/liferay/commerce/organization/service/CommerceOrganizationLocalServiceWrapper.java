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

package com.liferay.commerce.organization.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceOrganizationLocalService}.
 *
 * @author Marco Leo
 * @see CommerceOrganizationLocalService
 * @generated
 */
@ProviderType
public class CommerceOrganizationLocalServiceWrapper
	implements CommerceOrganizationLocalService,
		ServiceWrapper<CommerceOrganizationLocalService> {
	public CommerceOrganizationLocalServiceWrapper(
		CommerceOrganizationLocalService commerceOrganizationLocalService) {
		_commerceOrganizationLocalService = commerceOrganizationLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.Organization addOrganization(
		long parentOrganizationId, String name, String type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.addOrganization(parentOrganizationId,
			name, type, serviceContext);
	}

	@Override
	public void addOrganizationUsers(long organizationId,
		String[] emailAddresses,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceOrganizationLocalService.addOrganizationUsers(organizationId,
			emailAddresses, serviceContext);
	}

	@Override
	public void configureB2BSite(long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceOrganizationLocalService.configureB2BSite(groupId,
			serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getAccountOrganization(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.getAccountOrganization(organizationId);
	}

	@Override
	public com.liferay.portal.kernel.model.Address getOrganizationPrimaryAddress(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.getOrganizationPrimaryAddress(organizationId);
	}

	@Override
	public com.liferay.portal.kernel.model.EmailAddress getOrganizationPrimaryEmailAddress(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.getOrganizationPrimaryEmailAddress(organizationId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceOrganizationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public boolean hasGroupOrganization(long siteGroupId, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.hasGroupOrganization(siteGroupId,
			organizationId);
	}

	@Override
	public boolean isB2BOrganization(long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.isB2BOrganization(organizationId);
	}

	@Override
	public long searchOrganizationCount(long userId, long parentOrganizationId,
		String type, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.searchOrganizationCount(userId,
			parentOrganizationId, type, keywords, start, end, sorts);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization> searchOrganizations(
		long userId, long parentOrganizationId, String type, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.searchOrganizations(userId,
			parentOrganizationId, type, keywords, start, end, sorts);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization> searchOrganizationsByGroup(
		long groupId, long userId, String type, String keywords, int start,
		int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.searchOrganizationsByGroup(groupId,
			userId, type, keywords, start, end, sorts);
	}

	@Override
	public long searchOrganizationsByGroupCount(long groupId, long userId,
		String type, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.searchOrganizationsByGroupCount(groupId,
			userId, type, keywords, start, end, sorts);
	}

	@Override
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceOrganizationLocalService.unsetOrganizationUsers(organizationId,
			userIds);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization updateOrganization(
		long organizationId, long parentOrganizationId, String name,
		String type, long regionId, long countryId, long statusId,
		String comments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.updateOrganization(organizationId,
			parentOrganizationId, name, type, regionId, countryId, statusId,
			comments, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization updateOrganization(
		long organizationId, String name, long emailAddressId, String address,
		long addressId, String street1, String street2, String street3,
		String city, String zip, long regionId, long countryId, boolean logo,
		byte[] logoBytes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrganizationLocalService.updateOrganization(organizationId,
			name, emailAddressId, address, addressId, street1, street2,
			street3, city, zip, regionId, countryId, logo, logoBytes,
			serviceContext);
	}

	@Override
	public CommerceOrganizationLocalService getWrappedService() {
		return _commerceOrganizationLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceOrganizationLocalService commerceOrganizationLocalService) {
		_commerceOrganizationLocalService = commerceOrganizationLocalService;
	}

	private CommerceOrganizationLocalService _commerceOrganizationLocalService;
}