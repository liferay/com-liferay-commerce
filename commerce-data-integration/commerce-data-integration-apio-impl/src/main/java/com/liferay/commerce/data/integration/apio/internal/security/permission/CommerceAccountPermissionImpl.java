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
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceAccountHelper;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.portal.kernel.model.Organization"
)
public class CommerceAccountPermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(WebSiteIdentifier.class)) {
			return (credentials, groupId) ->
				_portletResourcePermission.contains(
					(PermissionChecker)credentials.get(), (Long)groupId,
					ActionKeys.ADD_ORGANIZATION);
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(
			Credentials credentials, ClassPKExternalReferenceCode entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, entryId, ActionKeys.DELETE);
	}

	@Override
	public Boolean forUpdating(
			Credentials credentials, ClassPKExternalReferenceCode entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, entryId, ActionKeys.UPDATE);
	}

	private ThrowableTriFunction
		<Credentials, ClassPKExternalReferenceCode, String, Boolean>
			_forItemRoutesOperations() {

		return (credentials, classPKExternalReferenceCode, actionId) -> {
			Company company = _companyService.getCompanyById(
				CompanyThreadLocal.getCompanyId());

			Organization organization = _commerceAccountHelper.getOrganization(
				classPKExternalReferenceCode, company);

			if (organization == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No Organization exists with identifier: " +
							classPKExternalReferenceCode);
				}

				return false;
			}

			return OrganizationPermissionUtil.contains(
				(PermissionChecker)credentials.get(), organization.getGroupId(),
				actionId);
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountPermissionImpl.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CompanyService _companyService;

	@Reference
	private PortletResourcePermission _portletResourcePermission;

}