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

package com.liferay.headless.commerce.admin.resource.v2_0;

import com.liferay.headless.commerce.admin.dto.v2_0.Address;
import com.liferay.headless.commerce.admin.dto.v2_0.Order;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderItem;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderNote;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin/v2.0
 *
 * @author Igor Beslic
 * @generated
 */
@Generated("")
public interface OrderResource {

	public Response deleteOrder(String id) throws Exception;

	public Response getOrder(String id) throws Exception;

	public Response updateMediaType1Order(String id, Order order)
		throws Exception;

	public Response updateMediaType2Order(String id, Order order)
		throws Exception;

	public Response deleteOrderItem(String id, String orderItemId)
		throws Exception;

	public Response getOrderItem(String id, String orderItemId)
		throws Exception;

	public Response updateMediaType1OrderItem(
			String id, String orderItemId, OrderItem orderItem)
		throws Exception;

	public Response updateMediaType2OrderItem(
			String id, String orderItemId, OrderItem orderItem)
		throws Exception;

	public Response getOrderItems(String id) throws Exception;

	public Response upsertMediaType1OrderItem(String id, OrderItem orderItem)
		throws Exception;

	public Response upsertMediaType2OrderItem(String id, OrderItem orderItem)
		throws Exception;

	public Response deleteOrderNote(String id, String orderNoteId)
		throws Exception;

	public Response getOrderNote(String id, String orderNoteId)
		throws Exception;

	public Response updateMediaType1OrderNote(
			String id, String orderNoteId, OrderNote orderNote)
		throws Exception;

	public Response updateMediaType2OrderNote(
			String id, String orderNoteId, OrderNote orderNote)
		throws Exception;

	public Response getOrderNotes(String id) throws Exception;

	public Response upsertMediaType1OrderNote(String id, OrderNote orderNote)
		throws Exception;

	public Response upsertMediaType2OrderNote(String id, OrderNote orderNote)
		throws Exception;

	public Response getBillingAddress(String id) throws Exception;

	public Response updateMediaType1BillingAddress(String id, Address address)
		throws Exception;

	public Response updateMediaType2BillingAddress(String id, Address address)
		throws Exception;

	public Response getShippingAddress(String id) throws Exception;

	public Response updateMediaType1ShippingAddress(String id, Address address)
		throws Exception;

	public Response updateMediaType2ShippingAddress(String id, Address address)
		throws Exception;

	public Response getOrders(Long groupId) throws Exception;

	public void setContextCompany(Company contextCompany);

}