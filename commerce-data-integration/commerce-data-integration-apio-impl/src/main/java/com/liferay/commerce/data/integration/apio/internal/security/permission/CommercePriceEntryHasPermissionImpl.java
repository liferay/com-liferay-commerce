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
import com.liferay.commerce.data.integration.apio.identifiers.CommercePriceListIdentifier;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceEntryHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceListHelper;
import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
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
	property = "model.class.name=com.liferay.commerce.price.list.model.CommercePriceEntry"
)
public class CommercePriceEntryHasPermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		if (identifierClass.equals(CommercePriceListIdentifier.class)) {
			return (credentials, priceListClassPKExternalReferenceCode) -> {
				CommercePriceList commercePriceList =
					_commercePriceListHelper.
						getCommercePriceListByClassPKExternalReferenceCode(
							(ClassPKExternalReferenceCode)
								priceListClassPKExternalReferenceCode);

				if (commercePriceList == null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"No CommercePriceList exists with external " +
								"reference code " +
									priceListClassPKExternalReferenceCode);
					}

					return false;
				}

				return _portletResourcePermission.contains(
					(PermissionChecker)credentials.get(),
					commercePriceList.getGroupId(),
					CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
			};
		}

		return (credentials, s) -> false;
	}

	@Override
	public Boolean forDeleting(
			Credentials credentials,
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, classPKExternalReferenceCode);
	}

	@Override
	public Boolean forUpdating(
			Credentials credentials,
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws Exception {

		return _forItemRoutesOperations().apply(
			credentials, classPKExternalReferenceCode);
	}

	private ThrowableBiFunction
		<Credentials, ClassPKExternalReferenceCode, Boolean>
			_forItemRoutesOperations() {

		return (credentials, classPKExternalReferenceCode) -> {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryHelper.
					getCommercePriceEntryByClassPKExternalReferenceCode(
						classPKExternalReferenceCode);

			if (commercePriceEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"No CommercePriceEntry exists with external " +
							"reference code " + classPKExternalReferenceCode);
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
		CommercePriceEntryHasPermissionImpl.class);

	@Reference
	private CommercePriceEntryHelper _commercePriceEntryHelper;

	@Reference
	private CommercePriceListHelper _commercePriceListHelper;

	@Reference(
		target = "(resource.name=" + CommercePriceListConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}