/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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