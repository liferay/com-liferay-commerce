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

package com.liferay.commerce.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAddressService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddressService
 * @generated
 */
public class CommerceAddressServiceWrapper
	implements CommerceAddressService, ServiceWrapper<CommerceAddressService> {

	public CommerceAddressServiceWrapper(
		CommerceAddressService commerceAddressService) {

		_commerceAddressService = commerceAddressService;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), defaultBilling/Shipping exist on Account Entity. Pass type.
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceAddress addCommerceAddress(
			String className, long classPK, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, boolean defaultBilling, boolean defaultShipping,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.addCommerceAddress(
			className, classPK, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber,
			defaultBilling, defaultShipping, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress addCommerceAddress(
			String className, long classPK, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.addCommerceAddress(
			className, classPK, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber, type,
			serviceContext);
	}

	@Override
	public void deleteCommerceAddress(long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAddressService.deleteCommerceAddress(commerceAddressId);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress fetchCommerceAddress(
			long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.fetchCommerceAddress(commerceAddressId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getBillingCommerceAddresses(
				long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getBillingCommerceAddresses(
			companyId, className, classPK);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress getCommerceAddress(
			long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddress(commerceAddressId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getCommerceAddresses(long groupId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddresses(
			groupId, className, classPK);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getCommerceAddresses(
				long groupId, String className, long classPK, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceAddress>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddresses(
			groupId, className, classPK, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getCommerceAddresses(
				String className, long classPK, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceAddress>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddresses(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getCommerceAddressesByCompanyId(
				long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddressesByCompanyId(
			companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getCommerceAddressesByCompanyId(
				long companyId, String className, long classPK, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceAddress>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddressesByCompanyId(
			companyId, className, classPK, start, end, orderByComparator);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public int getCommerceAddressesCount(
			long groupId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddressesCount(
			groupId, className, classPK);
	}

	@Override
	public int getCommerceAddressesCount(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddressesCount(
			className, classPK);
	}

	@Override
	public int getCommerceAddressesCountByCompanyId(
			long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getCommerceAddressesCountByCompanyId(
			companyId, className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAddressService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getShippingCommerceAddresses(
				long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.getShippingCommerceAddresses(
			companyId, className, classPK);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company. Don't need to pass groupId
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceAddress> searchCommerceAddresses(
				long companyId, long groupId, String className, long classPK,
				String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.searchCommerceAddresses(
			companyId, groupId, className, classPK, keywords, start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceAddress> searchCommerceAddresses(
				long companyId, String className, long classPK, String keywords,
				int start, int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.searchCommerceAddresses(
			companyId, className, classPK, keywords, start, end, sort);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), defaultBilling/Shipping exist on Account Entity. Pass type.
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceAddress updateCommerceAddress(
			long commerceAddressId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, boolean defaultBilling, boolean defaultShipping,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.updateCommerceAddress(
			commerceAddressId, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber,
			defaultBilling, defaultShipping, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress updateCommerceAddress(
			long commerceAddressId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressService.updateCommerceAddress(
			commerceAddressId, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber, type,
			serviceContext);
	}

	@Override
	public CommerceAddressService getWrappedService() {
		return _commerceAddressService;
	}

	@Override
	public void setWrappedService(
		CommerceAddressService commerceAddressService) {

		_commerceAddressService = commerceAddressService;
	}

	private CommerceAddressService _commerceAddressService;

}