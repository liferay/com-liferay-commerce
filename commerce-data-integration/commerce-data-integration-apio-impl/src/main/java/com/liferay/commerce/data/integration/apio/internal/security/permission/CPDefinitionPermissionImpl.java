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
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.util.CPDefinitionHelper;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPDefinition",
	service = HasPermission.class
)
public class CPDefinitionPermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(WebSiteIdentifier.class)) {
			return (credentials, groupId) ->
				_portletResourcePermission.contains(
					(PermissionChecker)credentials.get(), (Long)groupId,
					CPActionKeys.ADD_COMMERCE_PRODUCT_DEFINITION);
		}

		return (credentials, groupId) -> false;
	}

	@Override
	public Boolean forDeleting(
			Credentials credentials,
			ClassPKExternalReferenceCode cpDefinitionCPKERC)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, cpDefinitionCPKERC, ActionKeys.DELETE);
	}

	@Override
	public Boolean forUpdating(
			Credentials credentials,
			ClassPKExternalReferenceCode cpDefinitionCPKERC)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, cpDefinitionCPKERC, ActionKeys.UPDATE);
	}

	private ThrowableTriFunction
		<Credentials, ClassPKExternalReferenceCode, String, Boolean>
			_forItemRoutesOperations() {

		return (credentials, cpDefinitionCPKERC, actionId) ->
			_cpDefinitionModelResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				cpDefinitionCPKERC.getClassPK(), actionId);
	}

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	private ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}