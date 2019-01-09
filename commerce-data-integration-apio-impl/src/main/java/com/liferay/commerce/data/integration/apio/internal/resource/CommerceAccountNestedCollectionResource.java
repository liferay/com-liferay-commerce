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

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAccountIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceAccountUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.commerce.data.integration.headless.compat.apio.user.CurrentUser;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceAccountNestedCollectionResource
	implements NestedCollectionResource
		<CommerceAccount, ClassPKExternalReferenceCode,
		 CommerceAccountIdentifier, Long, CommerceWebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommerceAccount, ClassPKExternalReferenceCode, Long>
			collectionRoutes(
				NestedCollectionRoutes.Builder
					<CommerceAccount, ClassPKExternalReferenceCode, Long>
						 builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertAccount, CurrentUser.class,
			_hasPermission.forAddingIn(CommerceWebSiteIdentifier.class),
			CommerceAccountUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-account";
	}

	@Override
	public ItemRoutes<CommerceAccount, ClassPKExternalReferenceCode> itemRoutes(
		ItemRoutes.Builder<CommerceAccount, ClassPKExternalReferenceCode>
			builder) {

		return builder.addGetter(
			_commerceAccountHelper::getCommerceAccount, Company.class
		).addUpdater(
			this::_updateAccount, CurrentUser.class,
			_hasPermission::forUpdating, CommerceAccountUpserterForm::buildForm
		).addRemover(
			_commerceAccountHelper::deleteOrganization, Company.class,
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CommerceAccount> representor(
		Representor.Builder<CommerceAccount, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommerceAccount"
		).identifier(
			_commerceAccountHelper::
				commerceAccountToClassPKExternalReferenceCode
		).addNumberList(
			"members", this::_getUserIds
		).addString(
			"externalReferenceCode", CommerceAccount::getExternalReferenceCode
		).addString(
			"name", CommerceAccount::getName
		).addNumber(
			"id", CommerceAccount::getCommerceAccountId
		).build();
	}

	private PageItems<CommerceAccount> _getPageItems(
			Pagination pagination, Long parentCommerceAccountId)
		throws PortalException {

		CommerceAccountGroupServiceConfiguration
			commerceAccountGroupServiceConfiguration =
				_configurationProvider.getGroupConfiguration(
					CommerceAccountGroupServiceConfiguration.class, 0);

		List<CommerceAccount> userCommerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				parentCommerceAccountId,
				commerceAccountGroupServiceConfiguration.commerceSiteType(),
				StringPool.BLANK, pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalCount = _commerceAccountService.getUserCommerceAccountsCount(
			parentCommerceAccountId,
			commerceAccountGroupServiceConfiguration.commerceSiteType(),
			StringPool.BLANK);

		return new PageItems<>(userCommerceAccounts, totalCount);
	}

	private List<Number> _getUserIds(CommerceAccount commerceAccount) {
		List<Number> userIds = new ArrayList<>();

		try {
			List<CommerceAccountUserRel> commerceAccountUserRels =
				_commerceAccountUserRelService.getCommerceAccountUserRels(
					commerceAccount.getCommerceAccountId());

			for (CommerceAccountUserRel commerceAccountUserRel :
					commerceAccountUserRels) {

				userIds.add(commerceAccountUserRel.getUserId());
			}
		}
		catch (PortalException pe) {
			_log.error("Unable to retrieve users", pe);
		}

		return userIds;
	}

	private CommerceAccount _updateAccount(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			CommerceAccountUpserterForm commerceAccountUpserterForm,
			CurrentUser currentUser)
		throws PortalException {

		return _commerceAccountHelper.updateCommerceAccount(
			classPKExternalReferenceCode, commerceAccountUpserterForm.getName(),
			commerceAccountUpserterForm.getEmail(),
			commerceAccountUpserterForm.getTaxId(),
			commerceAccountUpserterForm.getActive(),
			commerceAccountUpserterForm.getCommerceUserIds(), currentUser);
	}

	private CommerceAccount _upsertAccount(
			Long parentCommerceAccountId,
			CommerceAccountUpserterForm commerceAccountUpserterForm,
			User currentUser)
		throws Exception {

		return _commerceAccountHelper.upsertCommerceAccount(
			commerceAccountUpserterForm.getName(), parentCommerceAccountId,
			false, null, commerceAccountUpserterForm.getEmail(),
			commerceAccountUpserterForm.getTaxId(),
			commerceAccountUpserterForm.getType(),
			commerceAccountUpserterForm.getActive(),
			commerceAccountUpserterForm.getExternalReferenceCode(),
			commerceAccountUpserterForm.getCommerceUserIds(), currentUser);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountNestedCollectionResource.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}