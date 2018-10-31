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

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAccountIdentifier;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAccountUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.OrganizationParentException;
import com.liferay.portal.kernel.exception.OrganizationTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceAccountNestedCollectionResource
	implements NestedCollectionResource
		<Organization, ClassPKExternalReferenceCode, CommerceAccountIdentifier,
		 Long, WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes
		<Organization, ClassPKExternalReferenceCode, Long>
			collectionRoutes(
				NestedCollectionRoutes.Builder
					<Organization, ClassPKExternalReferenceCode, Long>
						 builder) {

		return builder.addGetter(
			this::_getPageItems, CurrentUser.class
		).addCreator(
			this::_upsertAccount, CurrentUser.class,
			_hasPermission.forAddingIn(WebSiteIdentifier.class),
			CommerceAccountUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-account";
	}

	@Override
	public ItemRoutes<Organization, ClassPKExternalReferenceCode> itemRoutes(
		ItemRoutes.Builder<Organization, ClassPKExternalReferenceCode>
			builder) {

		return builder.addGetter(
			_commerceAccountHelper::getOrganization, Company.class
		).addUpdater(
			this::_updateAccount, CurrentUser.class,
			_hasPermission::forUpdating, CommerceAccountUpserterForm::buildForm
		).addRemover(
			_commerceAccountHelper::deleteOrganization, Company.class,
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<Organization> representor(
		Representor.Builder<Organization, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommerceAccount"
		).identifier(
			_commerceAccountHelper::organizationToClassPKExternalReferenceCode
		).addBidirectionalModel(
			"webSite", "commerceAccounts", WebSiteIdentifier.class,
			this::_getSiteId
		).addNumberList(
			"members", this::_getUserIds
		).addString(
			"externalReferenceCode", Organization::getExternalReferenceCode
		).addString(
			"name", Organization::getName
		).addNumber(
			"id", Organization::getOrganizationId
		).build();
	}

	private PageItems<Organization> _getPageItems(
			Pagination pagination, Long webSiteId, User currentUser)
		throws PortalException {

		BaseModelSearchResult<Organization> result =
			_commerceOrganizationService.searchOrganizationsByGroup(
				webSiteId, currentUser.getUserId(),
				CommerceOrganizationConstants.TYPE_ACCOUNT, StringPool.BLANK,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		long totalCount =
			_commerceOrganizationService.searchOrganizationsByGroupCount(
				webSiteId, currentUser.getUserId(),
				CommerceOrganizationConstants.TYPE_ACCOUNT, StringPool.BLANK,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		return new PageItems<>(
			result.getBaseModels(), Math.toIntExact(totalCount));
	}

	private Long _getSiteId(Organization organization) {
		return Try.success(
			organization.getGroupId()
		).map(
			_groupService::getGroup
		).map(
			Group::getGroupId
		).orElse(
			null
		);
	}

	private List<Number> _getUserIds(Organization organization) {
		List<Number> userIds = new ArrayList<>();

		try {
			long[] ids = _userService.getOrganizationUserIds(
				organization.getOrganizationId());

			for (long id : ids) {
				userIds.add(id);
			}
		}
		catch (PortalException pe) {
			_log.error("Unable to retrieve users", pe);
		}

		return userIds;
	}

	private Organization _updateAccount(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			CommerceAccountUpserterForm commerceAccountUpserterForm,
			CurrentUser currentUser)
		throws PortalException {

		return _commerceAccountHelper.updateOrganization(
			classPKExternalReferenceCode, commerceAccountUpserterForm.getName(),
			commerceAccountUpserterForm.getRegionId(),
			commerceAccountUpserterForm.getCountryId(),
			commerceAccountUpserterForm.getCommerceUserIds(), currentUser);
	}

	private Organization _upsertAccount(
			Long webSiteId,
			CommerceAccountUpserterForm commerceAccountUpserterForm,
			User currentUser)
		throws Exception {

		long parentOrganizationId =
			_commerceAccountHelper.getParentOrganizationId(webSiteId);

		if (parentOrganizationId == 0) {
			throw new NotFoundException(
				String.format(
					"Unable to add new account to the website with ID: %s " +
						"because it is not a parent organization site",
					webSiteId));
		}

		try {
			return _commerceAccountHelper.upsertOrganization(
				commerceAccountUpserterForm.getExternalReferenceCode(),
				parentOrganizationId, commerceAccountUpserterForm.getName(),
				commerceAccountUpserterForm.getRegionId(),
				commerceAccountUpserterForm.getCountryId(),
				commerceAccountUpserterForm.getCommerceUserIds(), currentUser);
		}
		catch (OrganizationParentException ope) {
			throw new ConflictException(
				String.format(
					"Problem with the actual web site with ID: %d, %s",
					webSiteId, ope.getMessage()),
				Response.Status.CONFLICT.getStatusCode(), ope);
		}
		catch (OrganizationTypeException ote) {
			throw new ConflictException(
				ote.getMessage(), Response.Status.CONFLICT.getStatusCode(),
				ote);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountNestedCollectionResource.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private GroupService _groupService;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.model.Organization)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

	@Reference
	private UserService _userService;

}