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
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceCountry",
	service = HasPermission.class
)
public class CommerceCountryPermissionImpl implements HasPermission<Long> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(WebSiteIdentifier.class)) {
			return (credentials, groupId) ->
				_portletResourcePermission.contains(
					(PermissionChecker)credentials.get(), (Long)groupId,
					CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);
		}

		return (credentials, groupId) -> false;
	}

	@Override
	public Boolean forDeleting(Credentials credentials, Long commerceCountryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, commerceCountryId,
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);
	}

	@Override
	public Boolean forUpdating(Credentials credentials, Long commerceCountryId)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, commerceCountryId,
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);
	}

	private ThrowableTriFunction<Credentials, Long, String, Boolean>
		_forItemRoutesOperations() {

		return (credentials, commerceCountryId, actionId) -> {
			CommerceCountry commerceCountry =
				_commerceCountryService.getCommerceCountry(commerceCountryId);

			return _portletResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				(Long)commerceCountry.getGroupId(), actionId);
		};
	}

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference(
		target = "(resource.name=" + CommerceConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}