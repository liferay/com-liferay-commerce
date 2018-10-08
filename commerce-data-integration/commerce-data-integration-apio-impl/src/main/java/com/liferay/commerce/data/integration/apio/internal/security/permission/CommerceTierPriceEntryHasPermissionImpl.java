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
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifiers.CommercePriceEntryIdentifier;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceEntryHelper;
import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(
	property = "model.class.name=com.liferay.commerce.price.list.model.CommerceTierPriceEntry",
	service = HasPermission.class
)
public class CommerceTierPriceEntryHasPermissionImpl
	implements HasPermission<Long> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CommercePriceEntryIdentifier.class)) {
			return (credentials, priceEntryClassPKExternalReferenceCode) -> {
				CommercePriceEntry commercePriceEntry =
					_commercePriceEntryHelper.
						getCommercePriceEntryByClassPKExternalReferenceCode(
							(ClassPKExternalReferenceCode)
								priceEntryClassPKExternalReferenceCode);

				if (commercePriceEntry == null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"No CommercePriceEntry exists with external " +
								"reference code " +
									priceEntryClassPKExternalReferenceCode);
					}

					return false;
				}

				return _portletResourcePermission.contains(
					(PermissionChecker)credentials.get(),
					commercePriceEntry.getGroupId(),
					CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
			};
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(Credentials credentials, Long entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, entryId);
	}

	@Override
	public Boolean forUpdating(Credentials credentials, Long entryId)
		throws Exception {

		return _forItemRoutesOperations().apply(credentials, entryId);
	}

	private ThrowableBiFunction<Credentials, Long, Boolean>
		_forItemRoutesOperations() {

		return (credentials, entryId) -> {
			CommerceTierPriceEntry commercePriceEntry =
				_commerceTierPriceEntryService.fetchCommerceTierPriceEntry(
					entryId);

			if (commercePriceEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No CommerceTierPriceEntry exists with primary key " +
							entryId);
				}

				return false;
			}

			return _portletResourcePermission.contains(
				(PermissionChecker)credentials.get(),
				commercePriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTierPriceEntryHasPermissionImpl.class);

	@Reference
	private CommercePriceEntryHelper _commercePriceEntryHelper;

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

	@Reference(
		target = "(resource.name=" + CommercePriceListConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}