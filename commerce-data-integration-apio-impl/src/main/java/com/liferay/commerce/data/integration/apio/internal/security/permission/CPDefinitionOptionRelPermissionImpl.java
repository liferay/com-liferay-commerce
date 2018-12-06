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
import com.liferay.commerce.data.integration.apio.identifier.CPDefinitionIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.util.CPDefinitionHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPDefinitionOptionRel",
	service = HasPermission.class
)
public class CPDefinitionOptionRelPermissionImpl
	implements HasPermission<Long> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CPDefinitionIdentifier.class)) {
			return (credentials, cpDefinitionClassPKExternalReferenceCode) -> {
				CPDefinition cpDefinition =
					_cpDefinitionHelper.
						getCPDefinitionByClassPKExternalReferenceCode(
							(ClassPKExternalReferenceCode)
								cpDefinitionClassPKExternalReferenceCode);

				return _cpDefinitionModelResourcePermission.contains(
					(PermissionChecker)credentials.get(),
					cpDefinition.getGroupId(), ActionKeys.UPDATE);
			};
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(Credentials credentials, Long entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, entryId, ActionKeys.UPDATE);
	}

	@Override
	public Boolean forUpdating(Credentials credentials, Long entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, entryId, ActionKeys.UPDATE);
	}

	private ThrowableTriFunction<Credentials, Long, String, Boolean>
		_forItemRoutesOperations() {

		return (credentials, cpDefinitionOptionRelId, actionId) -> {
			CPDefinitionOptionRel cpDefinitionOptionRel =
				_cpDefinitionOptionRelService.fetchCPDefinitionOptionRel(
					cpDefinitionOptionRelId);

			if (cpDefinitionOptionRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No CPDefinitionOptionRel exists with primary key " +
							cpDefinitionOptionRelId);
				}

				return false;
			}

			return _cpDefinitionModelResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				cpDefinitionOptionRel.getCPDefinitionId(), actionId);
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionOptionRelPermissionImpl.class);

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	private ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission;

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

}