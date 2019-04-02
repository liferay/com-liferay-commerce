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

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.openapi.admin.internal.mapper.v2_0.DTOMapper;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderItemDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.kernel.exception.PortalException;
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
@Component(immediate = true, service = OrderItemHelper.class)
public class OrderItemHelper {

	public void deleteOrderItem(
			String id, Company company, CommerceContext commerceContext)
		throws PortalException {

		CommerceOrderItem commerceOrderItem = getOrderItemById(id, company);

		_commerceOrderItemService.deleteCommerceOrderItem(
			commerceOrderItem.getCommerceOrderId(), commerceContext);
	}

	public OrderItemDTO getOrderItem(String id, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(getOrderItemById(id, company));
	}

	public CommerceOrderItem getOrderItemById(String id, Company company)
		throws PortalException {

		CommerceOrderItem commerceOrderItem = null;

		if (IdUtils.isLocalPK(id)) {
			commerceOrderItem = _commerceOrderItemService.getCommerceOrderItem(
				GetterUtil.getLong(id));
		}
		else {

			// Get Order Item by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			commerceOrderItem =
				_commerceOrderItemService.fetchByExternalReferenceCode(
					company.getCompanyId(), erc);
		}

		if (commerceOrderItem == null) {
			throw new NoSuchOrderItemException(
				"Unable to find Order Item with ID: " + id);
		}

		return commerceOrderItem;
	}

	public CollectionDTO<OrderItemDTO> getOrderItems(
			String orderId, Pagination pagination, Company company)
		throws PortalException {

		CommerceOrder commerceOrder = _orderHelper.getOrderById(
			orderId, company);

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderItemService.getCommerceOrderItemsCount(
			commerceOrder.getCommerceOrderId());

		Stream<CommerceOrderItem> stream = commerceOrderItems.stream();

		return stream.map(
			commerceOrderItem -> _dtoMapper.modelToDTO(commerceOrderItem)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				orderItemDTOs -> new CollectionDTO<>(orderItemDTOs, totalItems))
		);
	}

	public OrderItemDTO updateOrderItem(
			String id, OrderItemDTO orderItemDTO, Company company,
			CommerceContext commerceContext)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_updateOrderItem(
				id, company, orderItemDTO.getQuantity(), commerceContext));
	}

	public OrderItemDTO upsertOrderItem(
			String orderId, OrderItemDTO orderItemDTO, Company company,
			CommerceContext commerceContext)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_upsertOrderItem(
				orderId, company, orderItemDTO.getSkuId(),
				orderItemDTO.getQuantity(), orderItemDTO.getShippedQuantity(),
				commerceContext));
	}

	private CommerceOrderItem _updateOrderItem(
			String id, Company company, int quantity,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrderItem commerceOrderItem = getOrderItemById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrderItem.getGroupId());

		return _commerceOrderItemService.updateCommerceOrderItem(
			commerceOrderItem.getCommerceOrderItemId(), quantity,
			commerceContext, serviceContext);
	}

	private CommerceOrderItem _upsertOrderItem(
			String orderId, Company company, Long cpInstanceId, int quantity,
			int shippedQuantity, CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = _orderHelper.getOrderById(
			orderId, company);

		CPInstance cpInstance = _cpInstanceService.getCPInstance(cpInstanceId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		return _commerceOrderItemService.upsertCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			quantity, shippedQuantity, cpInstance.getJson(), commerceContext,
			serviceContext);
	}

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private OrderHelper _orderHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}