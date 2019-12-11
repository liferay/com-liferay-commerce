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

package com.liferay.commerce.shipment.test.util;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceChannelRelLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.service.CommerceShipmentItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceShipmentLocalServiceUtil;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

/**
 * @author Luca Pellizzon
 */
public class CommerceShipmentTestUtil {

	public static CommerceShipmentItem addCommerceShipmentItem(
			CommerceContext commerceContext, CPInstance cpInstance,
			long groupId, long userId, long commerceOrderId,
			long commerceShipmentId)
		throws Exception {

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(groupId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceChannel commerceChannel =
			CommerceChannelLocalServiceUtil.fetchCommerceChannelBySiteGroupId(
				groupId);

		if (commerceChannel == null) {
			commerceChannel =
				CommerceChannelLocalServiceUtil.addCommerceChannel(
					groupId, "Test Channel",
					CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
					commerceCurrency.getCode(), null, serviceContext);
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(groupId);

		CommerceChannelRelLocalServiceUtil.addCommerceChannelRel(
			CommerceInventoryWarehouse.class.getName(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			commerceChannel.getCommerceChannelId(), serviceContext);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			userId, commerceInventoryWarehouse, cpInstance.getSku(), 2);

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrderId, cpInstance.getCPInstanceId(), 2,
				commerceContext);

		return CommerceShipmentItemLocalServiceUtil.addCommerceShipmentItem(
			commerceShipmentId, commerceOrderItem.getCommerceOrderItemId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(), 1,
			serviceContext);
	}

	public static CommerceShipment createEmptyOrderShipment(
			long groupId, long orderId)
		throws PortalException {

		return CommerceShipmentLocalServiceUtil.addCommerceShipment(
			orderId, ServiceContextTestUtil.getServiceContext(groupId));
	}

	public static void createOrderItemsOnlyShipment(
			long groupId, long orderId, long warehouseId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(orderId);

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			CommerceShipmentItemLocalServiceUtil.addCommerceShipmentItem(
				0, commerceOrderItem.getCommerceOrderItemId(), warehouseId,
				commerceOrderItem.getQuantity(), serviceContext);
		}
	}

	public static CommerceShipment createOrderShipment(
			long commerceOrderId, long commerceWarehouseId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		CommerceShipment commerceShipment =
			CommerceShipmentLocalServiceUtil.addCommerceShipment(
				commerceOrderId, serviceContext);

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(commerceOrderId);

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			CommerceShipmentItemLocalServiceUtil.addCommerceShipmentItem(
				commerceShipment.getCommerceShipmentId(),
				commerceOrderItem.getCommerceOrderItemId(), commerceWarehouseId,
				commerceOrderItem.getQuantity(), serviceContext);
		}

		return commerceShipment;
	}

	public static CommerceShipment createOrderShipment(
			long groupId, long commerceOrderId, long commerceWarehouseId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceShipment commerceShipment =
			CommerceShipmentLocalServiceUtil.addCommerceShipment(
				commerceOrderId, serviceContext);

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(commerceOrderId);

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			CommerceShipmentItemLocalServiceUtil.addCommerceShipmentItem(
				commerceShipment.getCommerceShipmentId(),
				commerceOrderItem.getCommerceOrderItemId(), commerceWarehouseId,
				commerceOrderItem.getQuantity(), serviceContext);
		}

		return commerceShipment;
	}

}