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
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifiers.CommerceAccountIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAccountUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
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
			this::_addAccount, CurrentUser.class,
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
			"website", "commerceAccounts", WebSiteIdentifier.class,
			this::_getSiteId
		).addLinkedModel(
			"website", WebSiteIdentifier.class, this::_getSiteId
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

	private Organization _addAccount(
			Long webSiteId,
			CommerceAccountUpserterForm commerceAccountUpserterForm,
			User currentUser)
		throws Exception {

		Group group = _groupLocalService.getGroup(webSiteId);

		return _commerceAccountHelper.upsert(
			commerceAccountUpserterForm.getExternalReferenceCode(),
			group.getClassPK(), commerceAccountUpserterForm.getName(),
			commerceAccountUpserterForm.getRegionId(),
			commerceAccountUpserterForm.getCountryId(),
			commerceAccountUpserterForm.getCommerceUserIds(), currentUser);
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

		return new PageItems<>(result.getBaseModels(), result.getLength());
	}

	private Long _getSiteId(Organization organization) {
		return Try.success(
			organization.getGroupId()
		).map(
			_groupLocalService::getGroup
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

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountNestedCollectionResource.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.model.Organization)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

	@Reference
	private UserService _userService;

}