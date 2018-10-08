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