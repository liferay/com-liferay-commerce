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

package com.liferay.commerce.data.integration.apio.internal.resource;

import static com.liferay.commerce.data.integration.headless.compat.apio.idempotent.Idempotent.idempotent;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAccountIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAddressIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CommerceCountryIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CommerceRegionIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAddressUpdaterForm;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAddressWebSiteCreatorForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAddressHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.commerce.data.integration.headless.compat.apio.user.CurrentUser;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceAddressNestedCollectionResource
	implements NestedCollectionResource
		<CommerceAddress, Long, CommerceAddressIdentifier,
		 ClassPKExternalReferenceCode, CommerceAccountIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommerceAddress, Long, ClassPKExternalReferenceCode> collectionRoutes(
			NestedCollectionRoutes.Builder
				<CommerceAddress, Long, ClassPKExternalReferenceCode>
					builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			_commerceAddressHelper::addCommerceAddress, CurrentUser.class,
			_hasPermission.forAddingIn(CommerceAccountIdentifier.class),
			CommerceAddressWebSiteCreatorForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-address";
	}

	@Override
	public ItemRoutes<CommerceAddress, Long> itemRoutes(
		ItemRoutes.Builder<CommerceAddress, Long> builder) {

		return builder.addGetter(
			_commerceAddressService::getCommerceAddress
		).addRemover(
			idempotent(_commerceAddressService::deleteCommerceAddress),
			_hasPermission::forDeleting
		).addUpdater(
			_commerceAddressHelper::updateCommerceAddress, CurrentUser.class,
			_hasPermission::forUpdating, CommerceAddressUpdaterForm::buildForm
		).build();
	}

	@Override
	public Representor<CommerceAddress> representor(
		Representor.Builder<CommerceAddress, Long> builder) {

		return builder.types(
			"CommerceAddress"
		).identifier(
			CommerceAddress::getCommerceAddressId
		).addBidirectionalModel(
			"commerceAccount", "commerceAddresses",
			CommerceAccountIdentifier.class, this::_getCommerceAccountCPKERC
		).addBidirectionalModel(
			"commerceWebSite", "commerceAddresses",
			CommerceWebSiteIdentifier.class, CommerceAddress::getGroupId
		).addBoolean(
			"defaultBilling", CommerceAddress::getDefaultBilling
		).addBoolean(
			"defaultShipping", CommerceAddress::getDefaultShipping
		).addLinkedModel(
			"commerceCountry", CommerceCountryIdentifier.class,
			CommerceAddress::getCommerceCountryId
		).addLinkedModel(
			"commerceRegion", CommerceRegionIdentifier.class,
			CommerceAddress::getCommerceRegionId
		).addNumber(
			"latitude", CommerceAddress::getLatitude
		).addNumber(
			"longitude", CommerceAddress::getLongitude
		).addString(
			"city", CommerceAddress::getCity
		).addString(
			"description", CommerceAddress::getDescription
		).addString(
			"name", CommerceAddress::getName
		).addString(
			"phoneNumber", CommerceAddress::getPhoneNumber
		).addString(
			"street1", CommerceAddress::getStreet1
		).addString(
			"street2", CommerceAddress::getStreet2
		).addString(
			"street3", CommerceAddress::getStreet3
		).addString(
			"zip", CommerceAddress::getZip
		).build();
	}

	private ClassPKExternalReferenceCode _getCommerceAccountCPKERC(
		CommerceAddress commerceAddress) {

		long groupId = commerceAddress.getGroupId();

		Long organizationId = Try.fromFallible(
			() -> _groupLocalService.getGroup(groupId)
		).map(
			Group::getOrganizationId
		).getUnchecked();

		return _commerceAccountHelper.
			organizationIdToClassPKExternalReferenceCode(organizationId);
	}

	private PageItems<CommerceAddress> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		Organization organization =
			_commerceOrganizationService.getOrganization(
				classPKExternalReferenceCode.getClassPK());

		Group group = organization.getGroup();

		List<CommerceAddress> commerceAddresses =
			_commerceAddressService.getCommerceAddresses(
				group.getGroupId(), group.getClassName(), group.getClassPK(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int total = _commerceAddressService.getCommerceAddressesCount(
			group.getGroupId(), group.getClassName(), group.getClassPK());

		return new PageItems<>(commerceAddresses, total);
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceAddressHelper _commerceAddressHelper;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceAddress)"
	)
	private HasPermission<Long> _hasPermission;

}