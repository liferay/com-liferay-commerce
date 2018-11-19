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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAddressUpdaterForm;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAddressWebSiteCreatorForm;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommerceAddressHelper.class)
public class CommerceAddressHelper {

	public CommerceAddress addCommerceAddress(
			ClassPKExternalReferenceCode commerceAccountCPKERC,
			CommerceAddressWebSiteCreatorForm commerceAddressWebSiteCreatorForm,
			User currentUser)
		throws PortalException {

		return addCommerceAddress(
			(Long)null, commerceAddressWebSiteCreatorForm, currentUser);
	}

	public CommerceAddress addCommerceAddress(
			Long webSiteId,
			CommerceAddressWebSiteCreatorForm commerceAddressWebSiteCreatorForm,
			User currentUser)
		throws PortalException {

		String accountExternalReferenceCode =
			commerceAddressWebSiteCreatorForm.getAccountExternalReferenceCode();

		Organization organization =
			_organizationLocalService.fetchOrganizationByReferenceCode(
				currentUser.getCompanyId(), accountExternalReferenceCode);

		if ((organization == null) ||
			(organization.getParentOrganization() == null)) {

			throw new ConflictException(
				"Unable to fetch commerce account by reference code: " +
					accountExternalReferenceCode + " not found",
				Response.Status.CONFLICT);
		}

		Group group = organization.getGroup();

		Organization parentOrganization = organization.getParentOrganization();

		Group parentGroup = parentOrganization.getGroup();

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.getCommerceCountry(
				parentGroup.getGroupId(),
				commerceAddressWebSiteCreatorForm.
					getCountryTwoLettersISOCode());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			group.getGroupId(), new long[0], currentUser);

		return _commerceAddressService.addCommerceAddress(
			group.getClassName(), group.getClassPK(),
			commerceAddressWebSiteCreatorForm.getName(),
			commerceAddressWebSiteCreatorForm.getDescription(),
			commerceAddressWebSiteCreatorForm.getStreet1(),
			commerceAddressWebSiteCreatorForm.getStreet2(),
			commerceAddressWebSiteCreatorForm.getStreet3(),
			commerceAddressWebSiteCreatorForm.getCity(),
			commerceAddressWebSiteCreatorForm.getZip(),
			commerceAddressWebSiteCreatorForm.getRegionId(),
			commerceCountry.getCommerceCountryId(),
			commerceAddressWebSiteCreatorForm.getPhoneNumber(),
			commerceAddressWebSiteCreatorForm.getDefaultBilling(),
			commerceAddressWebSiteCreatorForm.getDefaultShipping(),
			serviceContext);
	}

	public CommerceAddress updateCommerceAddress(
			Long commerceAddressId,
			CommerceAddressUpdaterForm commerceAddressUpdaterForm,
			User currentUser)
		throws PortalException {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(commerceAddressId);

		Organization organization = _organizationLocalService.fetchOrganization(
			commerceAddress.getClassPK());

		if ((organization == null) ||
			(organization.getParentOrganization() == null)) {

			throw new ConflictException(
				"Unable to fetch commerce account: " +
					commerceAddress.getClassPK(),
				Response.Status.CONFLICT);
		}

		Group group = organization.getGroup();

		Organization parentOrganization = organization.getParentOrganization();

		Group parentGroup = parentOrganization.getGroup();

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.getCommerceCountry(
				parentGroup.getGroupId(),
				commerceAddressUpdaterForm.getCountryTwoLettersISOCode());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			group.getGroupId(), new long[0], currentUser);

		return _commerceAddressService.updateCommerceAddress(
			commerceAddressId, commerceAddressUpdaterForm.getName(),
			commerceAddressUpdaterForm.getDescription(),
			commerceAddressUpdaterForm.getStreet1(),
			commerceAddressUpdaterForm.getStreet2(),
			commerceAddressUpdaterForm.getStreet3(),
			commerceAddressUpdaterForm.getCity(),
			commerceAddressUpdaterForm.getZip(),
			commerceAddressUpdaterForm.getRegionId(),
			commerceCountry.getCommerceCountryId(),
			commerceAddressUpdaterForm.getPhoneNumber(),
			commerceAddressUpdaterForm.getDefaultBilling(),
			commerceAddressUpdaterForm.getDefaultShipping(), serviceContext);
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}