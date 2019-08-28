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

package com.liferay.headless.commerce.admin.order.resource.v1_0;

import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderNote;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-order/v1.0
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public interface OrderNoteResource {

	public Response deleteOrderNoteByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public OrderNote getOrderNoteByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Response patchOrderNoteByExternalReferenceCode(
			String externalReferenceCode, OrderNote orderNote)
		throws Exception;

	public Response deleteOrderNote(Long id) throws Exception;

	public OrderNote getOrderNote(Long id) throws Exception;

	public Response patchOrderNote(Long id, OrderNote orderNote)
		throws Exception;

	public Page<OrderNote> getOrderByExternalReferenceCodeOrderNotesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception;

	public OrderNote postOrderByExternalReferenceCodeOrderNote(
			String externalReferenceCode, OrderNote orderNote)
		throws Exception;

	public Page<OrderNote> getOrderIdOrderNotesPage(
			Long id, Pagination pagination)
		throws Exception;

	public OrderNote postOrderIdOrderNote(Long id, OrderNote orderNote)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}