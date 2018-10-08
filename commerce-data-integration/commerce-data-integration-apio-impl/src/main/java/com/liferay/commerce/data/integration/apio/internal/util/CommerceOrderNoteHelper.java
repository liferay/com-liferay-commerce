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
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderNoteLocalService;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = CommerceOrderNoteHelper.class)
public class CommerceOrderNoteHelper {

	public ClassPKExternalReferenceCode
		commerceOrderNoteToClassPKExternalReferenceCode(
			CommerceOrderNote commerceOrderNote) {

		if (commerceOrderNote != null) {
			return ClassPKExternalReferenceCode.create(
				commerceOrderNote.getCommerceOrderNoteId(),
				commerceOrderNote.getExternalReferenceCode());
		}

		return null;
	}

	public void deleteOrderNote(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		CommerceOrderNote commerceOrderNote =
			getCommerceOrderNoteByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		if (commerceOrderNote != null) {
			_commerceOrderNoteService.deleteCommerceOrderNote(
				commerceOrderNote.getCommerceOrderNoteId());
		}
	}

	public CommerceOrderNote getCommerceOrderNoteByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode
				commerceOrderNoteClassPKExternalReferenceCode)
		throws PortalException {

		long commerceOrderNoteId =
			commerceOrderNoteClassPKExternalReferenceCode.getClassPK();

		if (commerceOrderNoteId > 0) {
			CommerceOrderNote commerceOrderNote =
				_commerceOrderNoteLocalService.getCommerceOrderNote(
					commerceOrderNoteId);

			if (commerceOrderNote == null) {
				throw new NotFoundException(
					"Unable to find order note with ID " + commerceOrderNoteId);
			}

			return commerceOrderNote;
		}
		else {
			String externalReferenceCode =
				commerceOrderNoteClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _commerceOrderNoteLocalService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	public CommerceOrderNote upsertCommerceOrderNote(
			Long commerceOrderNoteId, Long commerceOrderId, String content,
			boolean restricted, String externalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		return _commerceOrderNoteService.upsertCommerceOrderNote(
			commerceOrderNoteId, commerceOrderId, content, restricted,
			externalReferenceCode, serviceContext);
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderNoteLocalService _commerceOrderNoteLocalService;

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}