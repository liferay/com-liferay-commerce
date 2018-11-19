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

package com.liferay.commerce.data.integration.apio.internal.router;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.NestedCollectionRouter;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAddressIdentifier;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAddressWebSiteCreatorForm;
import com.liferay.commerce.data.integration.apio.internal.util.ServiceContextHelper;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionRouter.class)
public class WebSiteCommerceAddressNestedCollectionRouter
	implements NestedCollectionRouter
		<CommerceAddress, Long, CommerceAddressIdentifier, Long,
		 WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CommerceAddress, Long, Long>
		collectionRoutes(NestedCollectionRoutes.Builder
			<CommerceAddress, Long, Long>
				 builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCommerceAddress, CurrentUser.class,
			_hasPermission.forAddingIn(WebSiteIdentifier.class),
			CommerceAddressWebSiteCreatorForm::buildForm
		).build();
	}

	private CommerceAddress _addCommerceAddress(
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

	private List<Long> _getDescendantAccountOrganizationIds(long groupId) {
		Organization organization = null;

		try {
			Group group = _groupLocalService.getGroup(groupId);

			organization = _organizationLocalService.getOrganization(
				group.getOrganizationId());
		}
		catch (PortalException pe) {
			throw new ConflictException(
				String.format(
					"Web site with Id %s does not have any account associated",
					groupId),
				Response.Status.CONFLICT, pe);
		}

		List<Organization> descendantOrganizations =
			organization.getDescendants();

		List<Long> organizationIds = new ArrayList<>();

		for (Organization descendantOrganization : descendantOrganizations) {
			if (CommerceOrganizationConstants.TYPE_ACCOUNT.equals(
					descendantOrganization.getType())) {

				organizationIds.add(descendantOrganization.getOrganizationId());
			}
		}

		return organizationIds;
	}

	private PageItems<CommerceAddress> _getPageItems(
		Pagination pagination, long webSiteId) {

		List<Long> commerceAddressIds = new ArrayList<>();
		List<Long> organizationIds = _getDescendantAccountOrganizationIds(
			webSiteId);

		organizationIds.forEach(
			organizationId -> {
				try {
					Organization organization =
						_organizationLocalService.getOrganization(
							organizationId);

					Group group = _groupLocalService.getGroup(
						organization.getGroupId());

					List<CommerceAddress> commerceAddresses =
						_commerceAddressService.getCommerceAddresses(
							group.getGroupId(), group.getClassName(),
							group.getClassPK());

					Stream<CommerceAddress> stream = commerceAddresses.stream();

					commerceAddressIds.addAll(
						stream.map(
							CommerceAddress::getCommerceAddressId
						).collect(
							Collectors.toList()
						)
					);
				}
				catch (PortalException pe) {
					throw new ConflictException(
						"Unable to get account addresses: " + organizationId,
						Response.Status.CONFLICT, pe);
				}
			});

		int count = commerceAddressIds.size();

		int endPosition = Math.min(count, pagination.getEndPosition());

		List<Long> sublistCommerceAddressIds = commerceAddressIds.subList(
			pagination.getStartPosition(), endPosition);

		Stream<Long> stream = sublistCommerceAddressIds.stream();

		List<CommerceAddress> commerceAddresses = stream.map(
			commerceAddressesId -> Try.fromFallible(
				() -> _commerceAddressService.fetchCommerceAddress(
					commerceAddressesId)
			).getUnchecked()
		).collect(
			Collectors.toList()
		);

		return new PageItems<>(commerceAddresses, count);
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceAddress)"
	)
	private HasPermission<Long> _hasPermission;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}