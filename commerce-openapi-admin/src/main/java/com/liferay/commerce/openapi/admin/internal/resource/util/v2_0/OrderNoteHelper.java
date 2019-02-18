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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.exception.NoSuchOrderNoteException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderNoteDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = OrderNoteHelper.class)
public class OrderNoteHelper {

	public void deleteOrderNote(String id, Company company)
		throws PortalException {

		CommerceOrderNote commerceOrderNote = null;

		try {
			commerceOrderNote = getOrderNoteById(id, company);
		}
		catch (NoSuchOrderNoteException nsone) {
			if (_log.isDebugEnabled()) {
				_log.debug("Order Item not exist with ID: " + id, nsone);
			}

			return;
		}

		_commerceOrderNoteService.deleteCommerceOrderNote(
			commerceOrderNote.getCommerceOrderId());
	}

	public OrderNoteDTO getOrderNote(String id, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(getOrderNoteById(id, company));
	}

	public CommerceOrderNote getOrderNoteById(String id, Company company)
		throws PortalException {

		CommerceOrderNote commerceOrderNote = null;

		if (IdUtils.isLocalPK(id)) {
			commerceOrderNote =
				_commerceOrderNoteService.fetchCommerceOrderNote(
					GetterUtil.getLong(id));
		}
		else {

			// Get Order Item by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			commerceOrderNote =
				_commerceOrderNoteService.fetchByExternalReferenceCode(
					company.getCompanyId(), erc);
		}

		if (commerceOrderNote == null) {
			throw new NoSuchOrderNoteException(
				"Unable to find Order Item with ID: " + id);
		}

		return commerceOrderNote;
	}

	public CollectionDTO<OrderNoteDTO> getOrderNotes(
			String orderId, Pagination pagination, Company company)
		throws PortalException {

		CommerceOrder commerceOrder = _orderHelper.getOrderById(
			orderId, company);

		List<CommerceOrderNote> commerceOrderNotes =
			_commerceOrderNoteService.getCommerceOrderNotes(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrder.getCommerceOrderId());

		Stream<CommerceOrderNote> stream = commerceOrderNotes.stream();

		return stream.map(
			commerceOrderNote -> DTOUtils.modelToDTO(commerceOrderNote)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				orderItemDTOs ->
					new CollectionDTO<>(orderItemDTOs, totalItems))
		);
	}

	public OrderNoteDTO updateOrderNote(
			String id, OrderNoteDTO orderItemDTO, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updateOrderNote(
				id, company, orderItemDTO.getContent(),
				orderItemDTO.isRestricted()));
	}

	public OrderNoteDTO upsertOrder(
			String orderId, OrderNoteDTO orderItemDTO, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_upsertOrderNote(
				orderId, orderItemDTO.getId(), company,
				orderItemDTO.getContent(), orderItemDTO.isRestricted(),
				orderItemDTO.getExternalReferenceCode()));
	}

	private CommerceOrderNote _updateOrderNote(
			String id, Company company, String content, boolean restricted)
		throws PortalException {

		CommerceOrderNote commerceOrderNote = getOrderNoteById(id, company);

		return _commerceOrderNoteService.updateCommerceOrderNote(
			commerceOrderNote.getCommerceOrderNoteId(), content, restricted);
	}

	private CommerceOrderNote _upsertOrderNote(
			String orderId, long commerceOrderNoteId, Company company,
			String content, boolean restricted, String externalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder = _orderHelper.getOrderById(
			orderId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		return _commerceOrderNoteService.upsertCommerceOrderNote(
			commerceOrderNoteId, commerceOrder.getCommerceOrderId(), content,
			restricted, externalReferenceCode, serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrderNoteHelper.class);

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private OrderHelper _orderHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}