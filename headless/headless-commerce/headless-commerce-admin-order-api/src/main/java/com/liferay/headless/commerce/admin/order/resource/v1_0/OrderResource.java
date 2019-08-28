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

import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
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
public interface OrderResource {

	public Page<Order> getOrdersPage(
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Order postOrder(Order order) throws Exception;

	public Response deleteOrderByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Order getOrderByExternalReferenceCode(String externalReferenceCode)
		throws Exception;

	public Response patchOrderByExternalReferenceCode(
			String externalReferenceCode, Order order)
		throws Exception;

	public Response deleteOrder(Long id) throws Exception;

	public Order getOrder(Long id) throws Exception;

	public Response patchOrder(Long id, Order order) throws Exception;

	public void setContextCompany(Company contextCompany);

}