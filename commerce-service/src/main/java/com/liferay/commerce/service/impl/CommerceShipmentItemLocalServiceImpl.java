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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.constants.CommerceDestinationNames;
import com.liferay.commerce.exception.CommerceShipmentItemQuantityException;
import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalService;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.base.CommerceShipmentItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentItemLocalServiceImpl
	extends CommerceShipmentItemLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceShipmentItem addCommerceShipmentItem(
			long commerceShipmentId, long commerceOrderItemId,
			long commerceWarehouseId, int quantity,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce shipment item

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemLocalService.getCommerceOrderItem(
				commerceOrderItemId);

		validate(commerceOrderItem, commerceWarehouseId, quantity, quantity);

		long commerceShipmentItemId = counterLocalService.increment();

		CommerceShipmentItem commerceShipmentItem =
			commerceShipmentItemPersistence.create(commerceShipmentItemId);

		commerceShipmentItem.setGroupId(groupId);
		commerceShipmentItem.setCompanyId(user.getCompanyId());
		commerceShipmentItem.setUserId(user.getUserId());
		commerceShipmentItem.setUserName(user.getFullName());
		commerceShipmentItem.setCommerceShipmentId(commerceShipmentId);
		commerceShipmentItem.setCommerceOrderItemId(commerceOrderItemId);
		commerceShipmentItem.setCommerceWarehouseId(commerceWarehouseId);
		commerceShipmentItem.setQuantity(quantity);

		commerceShipmentItem = commerceShipmentItemPersistence.update(
			commerceShipmentItem);

		// Commerce order item

		commerceOrderItem =
			commerceOrderItemLocalService.incrementShippedQuantity(
				commerceOrderItemId, quantity);

		// Stock quantity

		_updateStockQuantity(
			commerceOrderItem,
			commerceShipmentItem.getCommerceShipmentItemId());

		return commerceShipmentItem;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceShipmentItem deleteCommerceShipmentItem(
		CommerceShipmentItem commerceShipmentItem) {

		// Commerce order item

		CommerceOrderItem commerceOrderItem = null;

		int shippedQuantity = commerceShipmentItem.getQuantity() * -1;

		try {
			commerceOrderItem =
				commerceOrderItemLocalService.incrementShippedQuantity(
					commerceShipmentItem.getCommerceOrderItemId(),
					shippedQuantity);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		// Stock quantity

		try {
			_updateStockQuantity(
				commerceOrderItem,
				commerceShipmentItem.getCommerceShipmentItemId());
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		// Commerce shipment item

		commerceShipmentItemPersistence.remove(commerceShipmentItem);

		return commerceShipmentItem;
	}

	@Override
	public CommerceShipmentItem deleteCommerceShipmentItem(
			long commerceShipmentItemId)
		throws PortalException {

		CommerceShipmentItem commerceShipmentItem =
			commerceShipmentItemPersistence.findByPrimaryKey(
				commerceShipmentItemId);

		return commerceShipmentItemLocalService.deleteCommerceShipmentItem(
			commerceShipmentItem);
	}

	@Override
	public void deleteCommerceShipmentItems(long commerceShipment) {
		List<CommerceShipmentItem> commerceShipmentItems =
			commerceShipmentItemPersistence.findByCommerceShipment(
				commerceShipment);

		for (CommerceShipmentItem commerceShipmentItem :
				commerceShipmentItems) {

			commerceShipmentItemLocalService.deleteCommerceShipmentItem(
				commerceShipmentItem);
		}
	}

	@Override
	public List<CommerceShipmentItem> getCommerceShipmentItems(
		long commerceOrderItemId) {

		return commerceShipmentItemFinder.findByCommerceOrderItemId(
			commerceOrderItemId);
	}

	@Override
	public List<CommerceShipmentItem> getCommerceShipmentItems(
		long commerceShipmentId, int start, int end,
		OrderByComparator<CommerceShipmentItem> orderByComparator) {

		return commerceShipmentItemPersistence.findByCommerceShipment(
			commerceShipmentId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShipmentItemsCount(long commerceShipmentId) {
		return commerceShipmentItemPersistence.countByCommerceShipment(
			commerceShipmentId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceShipmentItem updateCommerceShipmentItem(
			long commerceShipmentItemId, int quantity)
		throws PortalException {

		// Commerce shipment item

		CommerceShipmentItem commerceShipmentItem =
			commerceShipmentItemPersistence.findByPrimaryKey(
				commerceShipmentItemId);

		int newQuantity = quantity - commerceShipmentItem.getQuantity();

		commerceShipmentItem.setQuantity(quantity);

		commerceShipmentItem = commerceShipmentItemPersistence.update(
			commerceShipmentItem);

		// Commerce order item

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemLocalService.getCommerceOrderItem(
				commerceShipmentItem.getCommerceOrderItemId());

		validate(
			commerceOrderItem, commerceShipmentItem.getCommerceWarehouseId(),
			quantity, newQuantity);

		commerceOrderItemLocalService.incrementShippedQuantity(
			commerceOrderItem.getCommerceOrderItemId(), newQuantity);

		// Stock quantity

		_updateStockQuantity(
			commerceOrderItem,
			commerceShipmentItem.getCommerceShipmentItemId());

		return commerceShipmentItem;
	}

	protected void validate(
			CommerceOrderItem commerceOrderItem, long commerceWarehouseId,
			int quantity, int newQuantity)
		throws PortalException {

		int availableQuantity =
			commerceOrderItem.getQuantity() -
				commerceOrderItem.getShippedQuantity();

		int commerceWarehouseQuantity =
			commerceOrderItemLocalService.getCommerceWarehouseItemQuantity(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceWarehouseId);

		if ((quantity <= 0) || (newQuantity > availableQuantity) ||
			(newQuantity > commerceWarehouseQuantity)) {

			throw new CommerceShipmentItemQuantityException();
		}
	}

	private CommerceInventoryWarehouseItem _fetchCommerceWarehouseItem(
			long commerceShipmentItemId, String sku)
		throws PortalException {

		CommerceShipmentItem commerceShipmentItem =
			commerceShipmentItemPersistence.findByPrimaryKey(
				commerceShipmentItemId);

		return _commerceInventoryWarehouseItemLocalService.
			fetchCommerceWarehouseItem(
				commerceShipmentItem.getCommerceWarehouseId(), sku);
	}

	private void _updateStockQuantity(
			CommerceOrderItem commerceOrderItem, long commerceShipmentItemId)
		throws PortalException {

		if (commerceOrderItem == null) {
			return;
		}

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		CommerceInventoryWarehouseItem commerceWarehouseItem =
			_fetchCommerceWarehouseItem(
				commerceShipmentItemId, cpInstance.getSku());

		if (commerceWarehouseItem == null) {
			return;
		}

		CommerceShipmentItem commerceShipmentItem =
			commerceShipmentItemPersistence.findByPrimaryKey(
				commerceShipmentItemId);

		Map<String, String> context = new HashMap<>();

		context.put(
			"OrderId ", String.valueOf(commerceOrderItem.getCommerceOrderId()));
		context.put(
			"OrderItemId ",
			String.valueOf(commerceOrderItem.getCommerceOrderItemId()));
		context.put(
			"ShipmentId",
			String.valueOf(commerceShipmentItem.getCommerceShipmentId()));
		context.put("ShipmentItemId", String.valueOf(commerceShipmentItemId));

		_commerceInventoryEngine.consumeQuantity(
			commerceShipmentItem.getUserId(), commerceShipmentItem.getGroupId(),
			commerceOrderItem.getSku(), commerceShipmentItem.getQuantity(),
			commerceShipmentItem.getCommerceWarehouseId(),
			commerceOrderItem.getBookedQuantityId(), context);

		TransactionCommitCallbackUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Message message = new Message();

					message.put("cpInstanceId", cpInstance.getCPInstanceId());

					MessageBusUtil.sendMessage(
						CommerceDestinationNames.STOCK_QUANTITY, message);

					return null;
				}

			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentItemLocalServiceImpl.class);

	@ServiceReference(type = CommerceInventoryEngine.class)
	private CommerceInventoryEngine _commerceInventoryEngine;

	@ServiceReference(type = CommerceInventoryWarehouseItemLocalService.class)
	private CommerceInventoryWarehouseItemLocalService
		_commerceInventoryWarehouseItemLocalService;

}