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

package com.liferay.headless.commerce.admin.order.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderNote;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.admin.order.resource.v1_0.BillingAddressResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderItemResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderNoteResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.ShippingAddressResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setBillingAddressResourceComponentServiceObjects(
		ComponentServiceObjects<BillingAddressResource>
			billingAddressResourceComponentServiceObjects) {

		_billingAddressResourceComponentServiceObjects =
			billingAddressResourceComponentServiceObjects;
	}

	public static void setOrderResourceComponentServiceObjects(
		ComponentServiceObjects<OrderResource>
			orderResourceComponentServiceObjects) {

		_orderResourceComponentServiceObjects =
			orderResourceComponentServiceObjects;
	}

	public static void setOrderItemResourceComponentServiceObjects(
		ComponentServiceObjects<OrderItemResource>
			orderItemResourceComponentServiceObjects) {

		_orderItemResourceComponentServiceObjects =
			orderItemResourceComponentServiceObjects;
	}

	public static void setOrderNoteResourceComponentServiceObjects(
		ComponentServiceObjects<OrderNoteResource>
			orderNoteResourceComponentServiceObjects) {

		_orderNoteResourceComponentServiceObjects =
			orderNoteResourceComponentServiceObjects;
	}

	public static void setShippingAddressResourceComponentServiceObjects(
		ComponentServiceObjects<ShippingAddressResource>
			shippingAddressResourceComponentServiceObjects) {

		_shippingAddressResourceComponentServiceObjects =
			shippingAddressResourceComponentServiceObjects;
	}

	@GraphQLInvokeDetached
	public Response patchOrderByExternalReferenceCodeBillingAddress(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("billingAddress") BillingAddress billingAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_billingAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			billingAddressResource ->
				billingAddressResource.
					patchOrderByExternalReferenceCodeBillingAddress(
						externalReferenceCode, billingAddress));
	}

	@GraphQLInvokeDetached
	public Response patchOrderIdBillingAddress(
			@GraphQLName("id") Long id,
			@GraphQLName("billingAddress") BillingAddress billingAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_billingAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			billingAddressResource ->
				billingAddressResource.patchOrderIdBillingAddress(
					id, billingAddress));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Order postOrder(@GraphQLName("order") Order order) throws Exception {
		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.postOrder(order));
	}

	@GraphQLInvokeDetached
	public Response deleteOrderByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.deleteOrderByExternalReferenceCode(
				externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchOrderByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("order") Order order)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.patchOrderByExternalReferenceCode(
				externalReferenceCode, order));
	}

	@GraphQLInvokeDetached
	public Response deleteOrder(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.deleteOrder(id));
	}

	@GraphQLInvokeDetached
	public Response patchOrder(
			@GraphQLName("id") Long id, @GraphQLName("order") Order order)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.patchOrder(id, order));
	}

	@GraphQLInvokeDetached
	public Response deleteOrderItemByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource ->
				orderItemResource.deleteOrderItemByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchOrderItemByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("orderItem") OrderItem orderItem)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource ->
				orderItemResource.patchOrderItemByExternalReferenceCode(
					externalReferenceCode, orderItem));
	}

	@GraphQLInvokeDetached
	public Response deleteOrderItem(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> orderItemResource.deleteOrderItem(id));
	}

	@GraphQLInvokeDetached
	public Response patchOrderItem(
			@GraphQLName("id") Long id,
			@GraphQLName("orderItem") OrderItem orderItem)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> orderItemResource.patchOrderItem(
				id, orderItem));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderItem postOrderByExternalReferenceCodeOrderItem(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("orderItem") OrderItem orderItem)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource ->
				orderItemResource.postOrderByExternalReferenceCodeOrderItem(
					externalReferenceCode, orderItem));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderItem postOrderIdOrderItem(
			@GraphQLName("id") Long id,
			@GraphQLName("orderItem") OrderItem orderItem)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> orderItemResource.postOrderIdOrderItem(
				id, orderItem));
	}

	@GraphQLInvokeDetached
	public Response deleteOrderNoteByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource ->
				orderNoteResource.deleteOrderNoteByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchOrderNoteByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("orderNote") OrderNote orderNote)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource ->
				orderNoteResource.patchOrderNoteByExternalReferenceCode(
					externalReferenceCode, orderNote));
	}

	@GraphQLInvokeDetached
	public Response deleteOrderNote(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> orderNoteResource.deleteOrderNote(id));
	}

	@GraphQLInvokeDetached
	public Response patchOrderNote(
			@GraphQLName("id") Long id,
			@GraphQLName("orderNote") OrderNote orderNote)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> orderNoteResource.patchOrderNote(
				id, orderNote));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderNote postOrderByExternalReferenceCodeOrderNote(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("orderNote") OrderNote orderNote)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource ->
				orderNoteResource.postOrderByExternalReferenceCodeOrderNote(
					externalReferenceCode, orderNote));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderNote postOrderIdOrderNote(
			@GraphQLName("id") Long id,
			@GraphQLName("orderNote") OrderNote orderNote)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> orderNoteResource.postOrderIdOrderNote(
				id, orderNote));
	}

	@GraphQLInvokeDetached
	public Response patchOrderByExternalReferenceCodeShippingAddress(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("shippingAddress") ShippingAddress shippingAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_shippingAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			shippingAddressResource ->
				shippingAddressResource.
					patchOrderByExternalReferenceCodeShippingAddress(
						externalReferenceCode, shippingAddress));
	}

	@GraphQLInvokeDetached
	public Response patchOrderIdShippingAddress(
			@GraphQLName("id") Long id,
			@GraphQLName("shippingAddress") ShippingAddress shippingAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_shippingAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			shippingAddressResource ->
				shippingAddressResource.patchOrderIdShippingAddress(
					id, shippingAddress));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			BillingAddressResource billingAddressResource)
		throws Exception {

		billingAddressResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(OrderResource orderResource)
		throws Exception {

		orderResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(OrderItemResource orderItemResource)
		throws Exception {

		orderItemResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(OrderNoteResource orderNoteResource)
		throws Exception {

		orderNoteResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ShippingAddressResource shippingAddressResource)
		throws Exception {

		shippingAddressResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<BillingAddressResource>
		_billingAddressResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderResource>
		_orderResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderItemResource>
		_orderItemResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderNoteResource>
		_orderNoteResourceComponentServiceObjects;
	private static ComponentServiceObjects<ShippingAddressResource>
		_shippingAddressResourceComponentServiceObjects;

}