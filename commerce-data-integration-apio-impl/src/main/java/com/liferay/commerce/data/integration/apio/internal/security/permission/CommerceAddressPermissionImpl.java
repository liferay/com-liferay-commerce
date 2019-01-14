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
import com.liferay.apio.architect.function.throwable.ThrowableBiFunction;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceAccountIdentifier;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceAddress",
	service = HasPermission.class
)
public class CommerceAddressPermissionImpl implements HasPermission<Long> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CommerceAccountIdentifier.class)) {
			return (credentials, commerceAccountCPKERC) -> {
				CommerceAccount commerceAccount =
					_commerceAccountHelper.getCommerceAccount(
						(ClassPKExternalReferenceCode)commerceAccountCPKERC,
						CompanyThreadLocal.getCompanyId());

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
					commerceAccount.getCommerceAccountId(), ActionKeys.UPDATE);
			};
		}
		else if (identifierClass.equals(CommerceWebSiteIdentifier.class)) {
			return (credentials, groupId) ->
				_portletResourcePermission.contains(
					(PermissionChecker)credentials.get(), (Long)groupId,
					CommerceActionKeys.MANAGE_COMMERCE_ADDRESSES);
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(Credentials credentials, Long commerceAddressId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, commerceAddressId);
	}

	@Override
	public Boolean forUpdating(Credentials credentials, Long commerceAddressId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, commerceAddressId);
	}

	private ThrowableBiFunction<Credentials, Long, Boolean>
		_forItemRoutesOperations() {

		return (credentials, commerceAddressId) -> {
			CommerceAddress commerceAddress =
				_commerceAddressService.fetchCommerceAddress(commerceAddressId);

			if (commerceAddress == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No CommerceAddress exists with primary key " +
							commerceAddressId);
				}

				return false;
			}

			return _portletResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				commerceAddress.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_ADDRESSES);
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAddressPermissionImpl.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ModelResourcePermission<CommerceAccount> _modelResourcePermission;

	@Reference(
		target = "(resource.name=" + CommerceConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}