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

package com.liferay.commerce.payment.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceAddressRestriction;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.base.CommercePaymentMethodGroupRelServiceBaseImpl;
import com.liferay.commerce.service.CommerceAddressRestrictionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.File;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
public class CommercePaymentMethodGroupRelServiceImpl
	extends CommercePaymentMethodGroupRelServiceBaseImpl {

	@Override
	public CommerceAddressRestriction addCommerceAddressRestriction(
			long classPK, long commerceCountryId, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			addCommerceAddressRestriction(
				classPK, commerceCountryId, serviceContext);
	}

	@Override
	public CommercePaymentMethodGroupRel addCommercePaymentMethodGroupRel(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			File imageFile, String engineKey,
			Map<String, String> engineParameterMap, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			addCommercePaymentMethodGroupRel(
				nameMap, descriptionMap, imageFile, engineKey,
				engineParameterMap, priority, active, serviceContext);
	}

	@Override
	public CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
			long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			createCommercePaymentMethodGroupRel(0);
	}

	@Override
	public void deleteCommerceAddressRestriction(
			long commerceAddressRestrictionId)
		throws PortalException {

		CommerceAddressRestriction commerceAddressRestriction =
			_commerceAddressRestrictionLocalService.
				fetchCommerceAddressRestriction(commerceAddressRestrictionId);

		if (commerceAddressRestriction != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceAddressRestriction.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
		}

		commercePaymentMethodGroupRelLocalService.
			deleteCommerceAddressRestriction(commerceAddressRestrictionId);
	}

	@Override
	public void deleteCommercePaymentMethodGroupRel(
			long commercePaymentMethodGroupRelId)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commercePaymentMethodGroupRelId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePaymentMethodGroupRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		commercePaymentMethodGroupRelLocalService.
			deleteCommercePaymentMethodGroupRel(commercePaymentMethodGroupRel);
	}

	@Override
	public List<CommerceAddressRestriction> getCommerceAddressRestrictions(
			long classPK, int start, int end,
			OrderByComparator<CommerceAddressRestriction> orderByComparator)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				fetchCommercePaymentMethodGroupRel(classPK);

		if (commercePaymentMethodGroupRel == null) {
			return Collections.emptyList();
		}

		_portletResourcePermission.check(
			getPermissionChecker(), commercePaymentMethodGroupRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommerceAddressRestrictions(
				classPK, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAddressRestrictionsCount(long classPK)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				fetchCommercePaymentMethodGroupRel(classPK);

		if (commercePaymentMethodGroupRel == null) {
			return 0;
		}

		_portletResourcePermission.check(
			getPermissionChecker(), commercePaymentMethodGroupRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommerceAddressRestrictionsCount(classPK);
	}

	@Override
	public CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
			long commercePaymentMethodGroupRelId)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commercePaymentMethodGroupRelId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePaymentMethodGroupRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRel;
	}

	@Override
	public CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
			long groupId, String engineKey)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRel(groupId, engineKey);
	}

	@Override
	public List<CommercePaymentMethodGroupRel>
		getCommercePaymentMethodGroupRels(long groupId) throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRels(groupId);
	}

	@Override
	public List<CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(long groupId, boolean active)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRels(groupId, active);
	}

	@Override
	public List<CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(
				long groupId, long commerceCountryId, boolean active)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRels(
				groupId, commerceCountryId, active);
	}

	@Override
	public int getCommercePaymentMethodGroupRelsCount(
			long groupId, boolean active)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRelsCount(groupId, active);
	}

	@Override
	public CommercePaymentMethodGroupRel setActive(
			long commercePaymentMethodGroupRelId, boolean active)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				fetchCommercePaymentMethodGroupRel(
					commercePaymentMethodGroupRelId);

		if (commercePaymentMethodGroupRel != null) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commercePaymentMethodGroupRel.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);
		}

		return commercePaymentMethodGroupRelLocalService.setActive(
			commercePaymentMethodGroupRelId, active);
	}

	@Override
	public CommercePaymentMethodGroupRel updateCommercePaymentMethodGroupRel(
			long commercePaymentMethodGroupRelId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, File imageFile,
			Map<String, String> engineParameterMap, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commercePaymentMethodGroupRelId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePaymentMethodGroupRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PAYMENT_METHODS);

		return commercePaymentMethodGroupRelLocalService.
			updateCommercePaymentMethodGroupRel(
				commercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId(),
				nameMap, descriptionMap, imageFile, engineParameterMap,
				priority, active, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommercePaymentMethodGroupRelServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

	@ServiceReference(type = CommerceAddressRestrictionLocalService.class)
	private CommerceAddressRestrictionLocalService
		_commerceAddressRestrictionLocalService;

}