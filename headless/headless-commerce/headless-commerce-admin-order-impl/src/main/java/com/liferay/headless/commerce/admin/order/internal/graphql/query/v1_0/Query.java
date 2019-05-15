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

package com.liferay.headless.commerce.admin.order.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderNote;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderItemResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderNoteResource;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class Query {

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

	@GraphQLField
	@GraphQLInvokeDetached
	public BillingAddress getOrderByExternalReferenceCodeBillingAddres(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource ->
				orderResource.getOrderByExternalReferenceCodeBillingAddres(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ShippingAddress getOrderByExternalReferenceCodeShippingAddres(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource ->
				orderResource.getOrderByExternalReferenceCodeShippingAddres(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Order> getOrderBySiteIdSite(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("siteId") Long siteId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> {
				Page paginationPage = orderResource.getOrderBySiteIdSite(
					accountId, siteId, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public BillingAddress getOrderBillingAddres(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.getOrderBillingAddres(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ShippingAddress getOrderShippingAddres(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderResource -> orderResource.getOrderShippingAddres(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderItem getOrderItemByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource ->
				orderItemResource.getOrderItemByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderItem getOrderItem(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> orderItemResource.getOrderItem(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OrderItem> getOrderByExternalReferenceCodeOrderItemsPage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> {
				Page paginationPage =
					orderItemResource.
						getOrderByExternalReferenceCodeOrderItemsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OrderItem> getOrderIdOrderItemsPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderItemResource -> {
				Page paginationPage =
					orderItemResource.getOrderIdOrderItemsPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderNote getOrderNoteByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource ->
				orderNoteResource.getOrderNoteByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OrderNote getOrderNote(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> orderNoteResource.getOrderNote(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OrderNote> getOrderByExternalReferenceCodeOrderNotesPage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> {
				Page paginationPage =
					orderNoteResource.
						getOrderByExternalReferenceCodeOrderNotesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OrderNote> getOrderIdOrderNotesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderNoteResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderNoteResource -> {
				Page paginationPage =
					orderNoteResource.getOrderIdOrderNotesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
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

	private static ComponentServiceObjects<OrderResource>
		_orderResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderItemResource>
		_orderItemResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderNoteResource>
		_orderNoteResourceComponentServiceObjects;

}