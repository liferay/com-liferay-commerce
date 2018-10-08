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
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifiers.CommerceOrderIdentifier;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderNoteHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceOrderNote",
	service = HasPermission.class
)
public class CommerceOrderNotePermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CommerceOrderIdentifier.class)) {
			return (credentials, commerceOrderClassPKExternalReferenceCode) -> {
				CommerceOrder commerceOrder =
					_commerceOrderHelper.
						getCommerceOrderByClassPKExternalReferenceCode(
							(ClassPKExternalReferenceCode)
								commerceOrderClassPKExternalReferenceCode);

				return _portletResourcePermission.contains(
					(PermissionChecker)credentials.get(),
					commerceOrder.getGroupId(),
					CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_NOTES);
			};
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(
			Credentials credentials, ClassPKExternalReferenceCode entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, entryId);
	}

	@Override
	public Boolean forUpdating(
			Credentials credentials, ClassPKExternalReferenceCode entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, entryId);
	}

	private ThrowableBiFunction
		<Credentials, ClassPKExternalReferenceCode, Boolean>
			_forItemRoutesOperations() {

		return (credentials, commerceOrderNoteClassPKExternalReferenceCode) -> {
			CommerceOrderNote commerceOrderNote =
				_commerceOrderNoteHelper.
					getCommerceOrderNoteByClassPKExternalReferenceCode(
						commerceOrderNoteClassPKExternalReferenceCode);

			return _portletResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				commerceOrderNote.getGroupId(),
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_NOTES);
		};
	}

	@Reference
	private CommerceOrderHelper _commerceOrderHelper;

	@Reference
	private CommerceOrderNoteHelper _commerceOrderNoteHelper;

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference(
		target = "(resource.name=" + CommerceOrderConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}