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

package com.liferay.headless.commerce.admin.order.internal.resource.v1_0;

import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.exception.NoSuchOrderNoteException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderNote;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderNoteResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/order-note.properties",
	scope = ServiceScope.PROTOTYPE, service = OrderNoteResource.class
)
public class OrderNoteResourceImpl extends BaseOrderNoteResourceImpl {

	@Override
	public Response deleteOrderNoteByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderNote commerceOrderNote =
			_commerceOrderNoteService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderNote == null) {
			throw new NoSuchOrderNoteException(
				"Unable to find OrderNote with externalReferenceCode: " +
					externalReferenceCode);
		}

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<OrderNote> getOrderByExternalReferenceCodeOrderNotesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CommerceOrderNote> commerceOrderNotes =
			_commerceOrderNoteService.getCommerceOrderNotes(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrder.getCommerceOrderId());

		return Page.of(
			_toOrderNotes(commerceOrderNotes), pagination, totalItems);
	}

	@Override
	public Page<OrderNote> getOrderIdOrderNotesPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceOrderNote> commerceOrderNotes =
			_commerceOrderNoteService.getCommerceOrderNotes(
				id, pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderNoteService.getCommerceOrderNotesCount(
			id);

		return Page.of(
			_toOrderNotes(commerceOrderNotes), pagination, totalItems);
	}

	@Override
	public OrderNote getOrderNote(Long id) throws Exception {
		DTOConverter orderNoteDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderNote.class.getName());

		return (OrderNote)orderNoteDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public OrderNote getOrderNoteByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderNote commerceOrderNote =
			_commerceOrderNoteService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderNote == null) {
			throw new NoSuchOrderNoteException(
				"Unable to find OrderNote with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter orderNoteDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderNote.class.getName());

		return (OrderNote)orderNoteDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderNote.getCommerceOrderNoteId()));
	}

	@Override
	public Response patchOrderNote(Long id, OrderNote orderNote)
		throws Exception {

		_updateOrderNote(
			_commerceOrderNoteService.getCommerceOrderNote(id), orderNote);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderNoteByExternalReferenceCode(
			String externalReferenceCode, OrderNote orderNote)
		throws Exception {

		CommerceOrderNote commerceOrderNote =
			_commerceOrderNoteService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderNote == null) {
			throw new NoSuchOrderNoteException(
				"Unable to find OrderNote with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateOrderNote(commerceOrderNote, orderNote);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public OrderNote postOrderByExternalReferenceCodeOrderNote(
			String externalReferenceCode, OrderNote orderNote)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _upsertOrderNote(commerceOrder, orderNote);
	}

	@Override
	public OrderNote postOrderIdOrderNote(Long id, OrderNote orderNote)
		throws Exception {

		return _upsertOrderNote(
			_commerceOrderService.getCommerceOrder(id), orderNote);
	}

	private List<OrderNote> _toOrderNotes(
			List<CommerceOrderNote> commerceOrderNotes)
		throws Exception {

		List<OrderNote> orders = new ArrayList<>();

		DTOConverter orderNoteDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderNote.class.getName());

		for (CommerceOrderNote commerceOrderNote : commerceOrderNotes) {
			orders.add(
				(OrderNote)orderNoteDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceOrderNote.getCommerceOrderNoteId())));
		}

		return orders;
	}

	private OrderNote _updateOrderNote(
			CommerceOrderNote commerceOrderNote, OrderNote orderNote)
		throws Exception {

		commerceOrderNote = _commerceOrderNoteService.updateCommerceOrderNote(
			orderNote.getOrderId(),
			GetterUtil.get(
				orderNote.getContent(), commerceOrderNote.getContent()),
			GetterUtil.get(
				orderNote.getRestricted(), commerceOrderNote.isRestricted()));

		DTOConverter orderNoteDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderNote.class.getName());

		return (OrderNote)orderNoteDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderNote.getCommerceOrderNoteId()));
	}

	private OrderNote _upsertOrderNote(
			CommerceOrder commerceOrder, OrderNote orderNote)
		throws Exception {

		CommerceOrderNote commerceOrderNote =
			_commerceOrderNoteService.upsertCommerceOrderNote(
				GetterUtil.get(orderNote.getId(), 0L),
				commerceOrder.getCommerceOrderId(), orderNote.getContent(),
				GetterUtil.get(orderNote.getRestricted(), false),
				orderNote.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext(
					commerceOrder.getGroupId()));

		DTOConverter orderNoteDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderNote.class.getName());

		return (OrderNote)orderNoteDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderNote.getCommerceOrderNoteId()));
	}

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}