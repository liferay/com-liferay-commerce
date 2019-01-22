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

package com.liferay.commerce.payment.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommercePaymentMethodGroupRelService}.
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelService
 * @generated
 */
@ProviderType
public class CommercePaymentMethodGroupRelServiceWrapper
	implements CommercePaymentMethodGroupRelService,
		ServiceWrapper<CommercePaymentMethodGroupRelService> {
	public CommercePaymentMethodGroupRelServiceWrapper(
		CommercePaymentMethodGroupRelService commercePaymentMethodGroupRelService) {
		_commercePaymentMethodGroupRelService = commercePaymentMethodGroupRelService;
	}

	@Override
	public com.liferay.commerce.model.CommerceAddressRestriction addCommerceAddressRestriction(
		long classPK, long commerceCountryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.addCommerceAddressRestriction(classPK,
			commerceCountryId, serviceContext);
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel addCommercePaymentMethodGroupRel(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile, String engineKey,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.addCommercePaymentMethodGroupRel(nameMap,
			descriptionMap, imageFile, engineKey, engineParameterMap, priority,
			active, serviceContext);
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.createCommercePaymentMethodGroupRel(groupId);
	}

	@Override
	public void deleteCommerceAddressRestriction(
		long commerceAddressRestrictionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commercePaymentMethodGroupRelService.deleteCommerceAddressRestriction(commerceAddressRestrictionId);
	}

	@Override
	public void deleteCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commercePaymentMethodGroupRelService.deleteCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddressRestriction> getCommerceAddressRestrictions(
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddressRestriction> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommerceAddressRestrictions(classPK,
			start, end, orderByComparator);
	}

	@Override
	public int getCommerceAddressRestrictionsCount(long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommerceAddressRestrictionsCount(classPK);
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId);
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		long groupId, String engineKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(groupId,
			engineKey);
	}

	@Override
	public java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(groupId);
	}

	@Override
	public java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(groupId,
			active);
	}

	@Override
	public java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		long groupId, long commerceCountryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(groupId,
			commerceCountryId, active);
	}

	@Override
	public int getCommercePaymentMethodGroupRelsCount(long groupId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRelsCount(groupId,
			active);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePaymentMethodGroupRelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel setActive(
		long commercePaymentMethodGroupRelId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.setActive(commercePaymentMethodGroupRelId,
			active);
	}

	@Override
	public com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel updateCommercePaymentMethodGroupRel(
		long commercePaymentMethodGroupRelId,
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commercePaymentMethodGroupRelService.updateCommercePaymentMethodGroupRel(commercePaymentMethodGroupRelId,
			nameMap, descriptionMap, imageFile, engineParameterMap, priority,
			active, serviceContext);
	}

	@Override
	public CommercePaymentMethodGroupRelService getWrappedService() {
		return _commercePaymentMethodGroupRelService;
	}

	@Override
	public void setWrappedService(
		CommercePaymentMethodGroupRelService commercePaymentMethodGroupRelService) {
		_commercePaymentMethodGroupRelService = commercePaymentMethodGroupRelService;
	}

	private CommercePaymentMethodGroupRelService _commercePaymentMethodGroupRelService;
}