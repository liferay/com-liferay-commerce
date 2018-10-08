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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = CommerceOrderItemHelper.class)
public class CommerceOrderItemHelper {

	public ClassPKExternalReferenceCode
		commerceOrderItemToClassPKExternalReferenceCode(
			CommerceOrderItem commerceOrderItem) {

		if (commerceOrderItem != null) {
			return ClassPKExternalReferenceCode.create(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceOrderItem.getExternalReferenceCode());
		}

		return null;
	}

	public CommerceOrderItem getCommerceOrderItemByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode
				commerceOrderItemClassPKExternalReferenceCode)
		throws PortalException {

		long commerceOrderItemId =
			commerceOrderItemClassPKExternalReferenceCode.getClassPK();

		if (commerceOrderItemId > 0) {
			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemLocalService.fetchCommerceOrderItem(
					commerceOrderItemId);

			if (commerceOrderItem == null) {
				throw new NotFoundException(
					"Unable to find order item with ID " + commerceOrderItemId);
			}

			return commerceOrderItem;
		}
		else {
			String externalReferenceCode =
				commerceOrderItemClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _commerceOrderItemLocalService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

}