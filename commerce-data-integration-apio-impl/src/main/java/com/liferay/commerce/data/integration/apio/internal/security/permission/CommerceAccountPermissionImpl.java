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

package com.liferay.commerce.data.integration.apio.internal.security.permission;

import com.liferay.apio.architect.alias.routes.permission.HasNestedAddingPermissionFunction;
import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.function.throwable.ThrowableTriFunction;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.CompanyService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccount",
	service = HasPermission.class
)
public class CommerceAccountPermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CommerceWebSiteIdentifier.class)) {
			return (credentials, groupId) -> {
				long parentCommerceAccountId =
					_commerceAccountHelper.getParentCommerceAccountId(
						(Long)groupId);

				if (parentCommerceAccountId ==
						CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID) {

					return _portletResourcePermission.contains(
						(PermissionChecker)credentials.get(),
						GroupConstants.DEFAULT_LIVE_GROUP_ID,
						CommerceAccountActionKeys.MANAGE_ACCOUNTS);
				}

				return _modelResourcePermission.contains(
					(PermissionChecker)credentials.get(),
					parentCommerceAccountId, ActionKeys.UPDATE);
			};
		}

		return (credentials, groupId) -> false;
	}

	@Override
	public Boolean forDeleting(
			Credentials credentials,
			ClassPKExternalReferenceCode commerceAccountCPKERC)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, commerceAccountCPKERC, ActionKeys.DELETE);
	}

	@Override
	public Boolean forUpdating(
			Credentials credentials,
			ClassPKExternalReferenceCode commerceAccountCPKERC)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, commerceAccountCPKERC, ActionKeys.UPDATE);
	}

	private ThrowableTriFunction
		<Credentials, ClassPKExternalReferenceCode, String, Boolean>
			_forItemRoutesOperations() {

		return (credentials, commerceAccountCPKERC, actionId) -> {
			Company company = _companyService.getCompanyById(
				CompanyThreadLocal.getCompanyId());

			CommerceAccount commerceAccount =
				_commerceAccountHelper.getCommerceAccount(
					commerceAccountCPKERC, company);

			if (commerceAccount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No CommerceAccount exists with identifier: " +
							commerceAccountCPKERC);
				}

				return false;
			}

			return _modelResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				commerceAccount.getCommerceAccountId(), actionId);
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountPermissionImpl.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CompanyService _companyService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ModelResourcePermission<CommerceAccount> _modelResourcePermission;

	@Reference(
		target = "(resource.name=" + CommerceAccountConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}