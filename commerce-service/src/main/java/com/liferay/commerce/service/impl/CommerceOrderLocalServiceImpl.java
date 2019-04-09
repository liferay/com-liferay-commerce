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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.configuration.CommerceOrderConfiguration;
import com.liferay.commerce.constants.CommerceDestinationNames;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.exception.CommerceDiscountCouponCodeException;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.exception.CommerceOrderBillingAddressException;
import com.liferay.commerce.exception.CommerceOrderPurchaseOrderNumberException;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.exception.CommerceOrderShippingMethodException;
import com.liferay.commerce.exception.CommerceOrderStatusException;
import com.liferay.commerce.exception.CommercePaymentEngineException;
import com.liferay.commerce.exception.GuestCartMaxAllowedException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.product.util.DDMFormValuesHelper;
import com.liferay.commerce.service.base.CommerceOrderLocalServiceBaseImpl;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CommerceOrderLocalServiceImpl
	extends CommerceOrderLocalServiceBaseImpl {

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long userId, long commerceAccountId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		if (hasWorkflowDefinition(
				groupId, CommerceOrderConstants.TYPE_PK_APPROVAL)) {

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		return commerceOrderLocalService.addCommerceOrder(
			commerceAccountId, 0, 0, 0, null, 0, null, null, BigDecimal.ZERO,
			BigDecimal.ZERO, BigDecimal.ZERO,
			CommerceOrderConstants.PAYMENT_STATUS_PENDING,
			CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);
	}

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long userId, long commerceAccountId,
			long commerceCurrencyId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		if (hasWorkflowDefinition(
				groupId, CommerceOrderConstants.TYPE_PK_APPROVAL)) {

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		return commerceOrderLocalService.addCommerceOrder(
			commerceAccountId, commerceCurrencyId, 0, 0, null, 0, null, null,
			BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
			CommerceOrderConstants.PAYMENT_STATUS_PENDING,
			CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);
	}

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long userId, long commerceAccountId,
			long commerceCurrencyId, long shippingAddressId,
			String purchaseOrderNumber)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		if (hasWorkflowDefinition(
				groupId, CommerceOrderConstants.TYPE_PK_APPROVAL)) {

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		return commerceOrderLocalService.addCommerceOrder(
			commerceAccountId, commerceCurrencyId, 0, shippingAddressId, null,
			0, null, purchaseOrderNumber, BigDecimal.ZERO, BigDecimal.ZERO,
			BigDecimal.ZERO, CommerceOrderConstants.PAYMENT_STATUS_PENDING,
			CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);
	}

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long userId, long commerceAccountId,
			long shippingAddressId, String purchaseOrderNumber)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		if (hasWorkflowDefinition(
				groupId, CommerceOrderConstants.TYPE_PK_APPROVAL)) {

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		return commerceOrderLocalService.addCommerceOrder(
			commerceAccountId, 0, 0, shippingAddressId, null, 0, null,
			purchaseOrderNumber, BigDecimal.ZERO, BigDecimal.ZERO,
			BigDecimal.ZERO, CommerceOrderConstants.PAYMENT_STATUS_PENDING,
			CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder addCommerceOrder(
			long commerceAccountId, long commerceCurrencyId,
			long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			int paymentStatus, int orderStatus, ServiceContext serviceContext)
		throws PortalException {

		// Commerce order

		long userId = serviceContext.getUserId();

		User user = userLocalService.getUser(userId);

		if (user.isDefaultUser()) {
			userId = 0;
		}

		validateGuestOrders();

		if (commerceCurrencyId <= 0) {
			CommerceCurrency commerceCurrency =
				_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
					serviceContext.getScopeGroupId());

			if (commerceCurrency != null) {
				commerceCurrencyId = commerceCurrency.getCommerceCurrencyId();
			}
		}

		long commerceOrderId = counterLocalService.increment();

		CommerceOrder commerceOrder = commerceOrderPersistence.create(
			commerceOrderId);

		commerceOrder.setUuid(serviceContext.getUuid());
		commerceOrder.setGroupId(serviceContext.getScopeGroupId());
		commerceOrder.setCompanyId(user.getCompanyId());
		commerceOrder.setUserId(userId);
		commerceOrder.setUserName(user.getFullName());
		commerceOrder.setCommerceAccountId(commerceAccountId);
		commerceOrder.setCommerceCurrencyId(commerceCurrencyId);
		commerceOrder.setBillingAddressId(billingAddressId);
		commerceOrder.setShippingAddressId(shippingAddressId);
		commerceOrder.setCommercePaymentMethodKey(commercePaymentMethodKey);
		commerceOrder.setCommerceShippingMethodId(commerceShippingMethodId);
		commerceOrder.setShippingOptionName(shippingOptionName);
		commerceOrder.setPurchaseOrderNumber(purchaseOrderNumber);
		commerceOrder.setSubtotal(subtotal);
		commerceOrder.setShippingAmount(shippingAmount);
		commerceOrder.setTotal(total);
		commerceOrder.setPaymentStatus(paymentStatus);
		commerceOrder.setOrderStatus(orderStatus);
		commerceOrder.setStatus(WorkflowConstants.STATUS_DRAFT);
		commerceOrder.setStatusByUserId(user.getUserId());
		commerceOrder.setStatusByUserName(user.getFullName());
		commerceOrder.setStatusDate(serviceContext.getModifiedDate(null));
		commerceOrder.setExpandoBridgeAttributes(serviceContext);

		commerceOrderPersistence.update(commerceOrder);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), commerceOrder, serviceContext);
	}

	@Override
	public CommerceOrder applyCouponCode(
			long commerceOrderId, String couponCode,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		try {
			boolean hasDiscounts = false;

			if (_commerceDiscountLocalService.getCommerceDiscountsCount(
					commerceOrder.getScopeGroupId(), couponCode) == 0) {

				hasDiscounts = true;
			}

			if (hasDiscounts && Validator.isNotNull(couponCode)) {
				throw new CommerceDiscountCouponCodeException();
			}
		}
		catch (CommerceDiscountCouponCodeException cdcce) {
			throw new CommerceDiscountCouponCodeException(cdcce);
		}

		commerceOrder.setCouponCode(couponCode);

		commerceOrderPersistence.update(commerceOrder);

		return commerceOrderLocalService.recalculatePrice(
			commerceOrderId, commerceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder approveCommerceOrder(long userId, long commerceOrderId)
		throws PortalException {

		// Commerce order

		CommerceOrder commerceOrder = commerceOrderLocalService.updateStatus(
			userId, commerceOrderId, WorkflowConstants.STATUS_APPROVED,
			new ServiceContext(), null);

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			commerceOrder.getCompanyId(), commerceOrder.getGroupId(),
			CommerceOrder.class.getName(), commerceOrder.getCommerceOrderId());

		return commerceOrder;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder checkoutCommerceOrder(
			long commerceOrderId, CommerceContext commerceContext,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce order

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		if (commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_OPEN) {

			commerceOrder = commerceOrderLocalService.recalculatePrice(
				commerceOrder.getCommerceOrderId(), commerceContext);
		}

		commerceOrder = commerceOrderLocalService.approveCommerceOrder(
			serviceContext.getUserId(), commerceOrder.getCommerceOrderId());

		validateCheckout(commerceOrder);

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_IN_PROGRESS);

		// Commerce addresses

		long billingAddressId = commerceOrder.getBillingAddressId();

		if (billingAddressId > 0) {
			CommerceAddress commerceAddress =
				commerceAddressLocalService.copyCommerceAddress(
					billingAddressId, commerceOrder.getModelClassName(),
					commerceOrder.getCommerceOrderId(), serviceContext);

			billingAddressId = commerceAddress.getCommerceAddressId();
		}

		long shippingAddressId = commerceOrder.getShippingAddressId();

		if (shippingAddressId > 0) {
			CommerceAddress commerceAddress =
				commerceAddressLocalService.copyCommerceAddress(
					shippingAddressId, commerceOrder.getModelClassName(),
					commerceOrder.getCommerceOrderId(), serviceContext);

			shippingAddressId = commerceAddress.getCommerceAddressId();
		}

		if ((billingAddressId > 0) || (shippingAddressId > 0)) {
			commerceOrder.setBillingAddressId(billingAddressId);
			commerceOrder.setShippingAddressId(shippingAddressId);
		}

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrder deleteCommerceOrder(CommerceOrder commerceOrder)
		throws PortalException {

		// Commerce order items

		commerceOrderItemLocalService.deleteCommerceOrderItems(
			commerceOrder.getCommerceOrderId());

		// Commerce order notes

		commerceOrderNoteLocalService.deleteCommerceOrderNotes(
			commerceOrder.getCommerceOrderId());

		// Commerce order payments

		commerceOrderPaymentLocalService.deleteCommerceOrderPayments(
			commerceOrder.getCommerceOrderId());

		// Commerce addresses

		commerceAddressLocalService.deleteCommerceAddresses(
			commerceOrder.getModelClassName(),
			commerceOrder.getCommerceOrderId());

		// Commerce order

		commerceOrderPersistence.remove(commerceOrder);

		// Expando

		expandoRowLocalService.deleteRows(commerceOrder.getCommerceOrderId());

		// Workflow

		CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();

		if (hasWorkflowDefinition(
				commerceOrder.getScopeGroupId(),
				commerceOrder.getCommerceOrderId())) {

			workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
				commerceOrder.getCompanyId(),
				commerceAccount.getCommerceAccountGroupId(),
				CommerceOrder.class.getName(),
				commerceOrder.getCommerceOrderId());
		}

		return commerceOrder;
	}

	@Override
	public CommerceOrder deleteCommerceOrder(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		return commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
	}

	@Override
	public void deleteCommerceOrders(long groupId) throws PortalException {
		List<CommerceOrder> commerceOrders =
			commerceOrderPersistence.findByGroupId(groupId);

		for (CommerceOrder commerceOrder : commerceOrders) {
			commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}
	}

	@Override
	public void deleteCommerceOrders(long userId, Date date, int status) {
		commerceOrderPersistence.removeByU_LtC_O(userId, date, status);
	}

	@Override
	public CommerceOrder executeWorkflowTransition(
			long userId, long commerceOrderId, long workflowTaskId,
			String transitionName, String comment)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		long companyId = commerceOrder.getCompanyId();

		WorkflowTask workflowTask = _workflowTaskManager.getWorkflowTask(
			companyId, workflowTaskId);

		if (!workflowTask.isAssignedToSingleUser()) {
			workflowTask = _workflowTaskManager.assignWorkflowTaskToUser(
				companyId, userId, workflowTask.getWorkflowTaskId(), userId,
				comment, null, null);
		}

		_workflowTaskManager.completeWorkflowTask(
			companyId, userId, workflowTask.getWorkflowTaskId(), transitionName,
			comment, null);

		return commerceOrder;
	}

	@Override
	public CommerceOrder fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return commerceOrderPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public CommerceOrder fetchCommerceOrder(
		long commerceAccountId, long groupId, int orderStatus) {

		return commerceOrderPersistence.fetchByG_C_O_First(
			groupId, commerceAccountId, orderStatus, null);
	}

	@Override
	public int[] getAvailableOrderStatuses(long commerceOrderId)
		throws PortalException {

		if (commerceOrderId <= 0) {
			return AVAILABLE_ORDER_STATUSES;
		}

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		if (!commerceOrder.isPending() &&
			ArrayUtil.contains(
				AVAILABLE_ORDER_STATUSES, commerceOrder.getOrderStatus())) {

			return AVAILABLE_ORDER_STATUSES;
		}

		return new int[] {commerceOrder.getOrderStatus()};
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
		long groupId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return commerceOrderPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
		long groupId, int[] orderStatuses) {

		return commerceOrderFinder.findByG_O(groupId, orderStatuses);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
		long groupId, int[] orderStatuses, int start, int end) {

		return commerceOrderFinder.findByG_O(
			groupId, orderStatuses, start, end);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
		long groupId, long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return commerceOrderPersistence.findByG_C(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceOrder> getCommerceOrdersByBillingAddress(
		long billingAddressId) {

		return commerceOrderPersistence.findByBillingAddressId(
			billingAddressId);
	}

	@Override
	public List<CommerceOrder> getCommerceOrdersByShippingAddress(
		long shippingAddressId) {

		return commerceOrderPersistence.findByShippingAddressId(
			shippingAddressId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId) {
		return commerceOrderPersistence.countByGroupId(groupId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId, long commerceAccountId) {
		return commerceOrderPersistence.countByG_C(groupId, commerceAccountId);
	}

	@Override
	public List<CommerceOrder> getUserCommerceOrders(
		long groupId, long userId, long commerceAccountId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords, int start, int end) {

		QueryDefinition<CommerceOrder> queryDefinition =
			new QueryDefinition<>();

		queryDefinition.setAttribute("commerceAccountId", commerceAccountId);
		queryDefinition.setAttribute("excludeOrderStatus", excludeOrderStatus);
		queryDefinition.setAttribute("groupId", groupId);
		queryDefinition.setAttribute("keywords", keywords);
		queryDefinition.setAttribute("orderStatus", orderStatus);
		queryDefinition.setStart(start);
		queryDefinition.setEnd(end);

		return commerceOrderFinder.findByG_U_C_O(userId, queryDefinition);
	}

	@Override
	public int getUserCommerceOrdersCount(
		long groupId, long userId, long commerceAccountId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords) {

		QueryDefinition<CommerceOrder> queryDefinition =
			new QueryDefinition<>();

		queryDefinition.setAttribute("commerceAccountId", commerceAccountId);
		queryDefinition.setAttribute("excludeOrderStatus", excludeOrderStatus);
		queryDefinition.setAttribute("groupId", groupId);
		queryDefinition.setAttribute("keywords", keywords);
		queryDefinition.setAttribute("orderStatus", orderStatus);

		return commerceOrderFinder.countByG_U_C_O(userId, queryDefinition);
	}

	@Override
	public void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		List<CommerceOrderItem> guestCommerceOrderItems =
			commerceOrderItemPersistence.findByCommerceOrderId(
				guestCommerceOrderId);

		for (CommerceOrderItem guestCommerceOrderItem :
				guestCommerceOrderItems) {

			List<CommerceOrderItem> userCommerceOrderItems =
				commerceOrderItemPersistence.findByC_I(
					userCommerceOrderId,
					guestCommerceOrderItem.getCPInstanceId());

			if (!userCommerceOrderItems.isEmpty()) {
				boolean found = false;

				for (CommerceOrderItem userCommerceOrderItem :
						userCommerceOrderItems) {

					if (_ddmFormValuesHelper.equals(
							guestCommerceOrderItem.getJson(),
							userCommerceOrderItem.getJson())) {

						found = true;

						break;
					}
				}

				if (found) {
					break;
				}
			}

			commerceOrderItemLocalService.addCommerceOrderItem(
				userCommerceOrderId, guestCommerceOrderItem.getCPInstanceId(),
				guestCommerceOrderItem.getQuantity(),
				guestCommerceOrderItem.getShippedQuantity(),
				guestCommerceOrderItem.getJson(), commerceContext,
				serviceContext);
		}

		commerceOrderLocalService.deleteCommerceOrder(guestCommerceOrderId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder recalculatePrice(
			long commerceOrderId, CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			commerceOrderItemLocalService.updateCommerceOrderItemPrice(
				commerceOrderItem.getCommerceOrderItemId(), commerceContext);
		}

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, false, commerceContext);

		CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
		CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
		CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
		CommerceMoney total = commerceOrderPrice.getTotal();

		commerceOrder.setSubtotal(subtotal.getPrice());
		commerceOrder.setShippingAmount(shippingValue.getPrice());
		commerceOrder.setTaxAmount(taxValue.getPrice());
		commerceOrder.setTotal(total.getPrice());

		CommerceDiscountValue shippingDiscountValue =
			commerceOrderPrice.getShippingDiscountValue();
		CommerceDiscountValue subtotalDiscountValue =
			commerceOrderPrice.getSubtotalDiscountValue();
		CommerceDiscountValue totalDiscountValue =
			commerceOrderPrice.getTotalDiscountValue();

		_setCommerceOrderShippingDiscountValue(
			commerceOrder, shippingDiscountValue);
		_setCommerceOrderSubtotalDiscountValue(
			commerceOrder, subtotalDiscountValue);
		_setCommerceOrderTotalDiscountValue(commerceOrder, totalDiscountValue);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Override
	public CommerceOrder reorderCommerceOrder(
			long userId, long commerceOrderId, CommerceContext commerceContext)
		throws PortalException {

		// Commerce order

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());
		serviceContext.setUserId(userId);

		long billingAddressId = 0;
		long shippingAddressId = 0;

		CommerceAddress billingAddress = getNewCommerceAddress(
			commerceOrder, commerceOrder.getBillingAddress(), serviceContext);
		CommerceAddress shippingAddress = getNewCommerceAddress(
			commerceOrder, commerceOrder.getShippingAddress(), serviceContext);

		if (billingAddress != null) {
			billingAddressId = billingAddress.getCommerceAddressId();
		}

		if (shippingAddress != null) {
			shippingAddressId = shippingAddress.getCommerceAddressId();
		}

		CommerceOrder newCommerceOrder =
			commerceOrderLocalService.addCommerceOrder(
				commerceOrder.getCommerceAccountId(),
				commerceOrder.getCommerceCurrencyId(), billingAddressId,
				shippingAddressId, commerceOrder.getCommercePaymentMethodKey(),
				commerceOrder.getCommerceShippingMethodId(),
				commerceOrder.getShippingOptionName(), StringPool.BLANK,
				commerceOrder.getSubtotal(), commerceOrder.getShippingAmount(),
				commerceOrder.getTotal(),
				CommerceOrderConstants.PAYMENT_STATUS_PENDING,
				CommerceOrderConstants.ORDER_STATUS_OPEN, serviceContext);

		// Commerce order items

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrderItemLocalService.getCommerceOrderItems(
				commerceOrder.getCommerceOrderId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			commerceOrderItemLocalService.addCommerceOrderItem(
				newCommerceOrder.getCommerceOrderId(),
				commerceOrderItem.getCPInstanceId(),
				commerceOrderItem.getQuantity(), 0, commerceOrderItem.getJson(),
				commerceContext, serviceContext);
		}

		return newCommerceOrder;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder resetCommerceOrderShipping(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		commerceOrder.setCommerceShippingMethodId(0);
		commerceOrder.setShippingOptionName(null);
		commerceOrder.setShippingAmount(BigDecimal.ZERO);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Override
	public BaseModelSearchResult<CommerceOrder> searchCommerceOrders(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceOrder> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CommerceOrder.class.getName());

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext);

			List<CommerceOrder> commerceOrders = getCommerceOrders(hits);

			if (commerceOrders != null) {
				return new BaseModelSearchResult<>(
					commerceOrders, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	@Override
	public long searchCommerceOrdersCount(SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceOrder> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CommerceOrder.class.getName());

		return indexer.searchCount(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder submitCommerceOrder(long userId, long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		if (!commerceOrder.isDraft() || commerceOrder.isEmpty()) {
			return commerceOrder;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());
		serviceContext.setUserId(userId);
		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		return startWorkflowInstance(
			serviceContext.getUserId(), commerceOrder, serviceContext);
	}

	@Override
	public CommerceOrder updateAccount(
			long commerceOrderId, long userId, long commerceAccountId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		User user = userLocalService.getUser(userId);

		commerceOrder.setUserId(user.getUserId());
		commerceOrder.setUserName(user.getFullName());

		commerceOrder.setCommerceAccountId(commerceAccountId);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateBillingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, ServiceContext serviceContext)
		throws PortalException {

		return updateAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			CommerceOrder::getBillingAddressId,
			CommerceOrder::setBillingAddressId, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			String advanceStatus, CommerceContext commerceContext)
		throws PortalException {

		return commerceOrderLocalService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, null, commerceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			String advanceStatus, String externalReferenceCode,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		commerceOrder.setBillingAddressId(billingAddressId);
		commerceOrder.setShippingAddressId(shippingAddressId);
		commerceOrder.setCommercePaymentMethodKey(commercePaymentMethodKey);
		commerceOrder.setCommerceShippingMethodId(commerceShippingMethodId);
		commerceOrder.setShippingOptionName(shippingOptionName);
		commerceOrder.setPurchaseOrderNumber(purchaseOrderNumber);
		commerceOrder.setSubtotal(subtotal);

		if (commerceContext != null) {
			CommerceOrderPrice commerceOrderPrice =
				_commerceOrderPriceCalculation.getCommerceOrderPrice(
					commerceOrder, false, commerceContext);

			CommerceDiscountValue shippingDiscountValue =
				commerceOrderPrice.getShippingDiscountValue();

			if (shippingDiscountValue != null) {
				CommerceMoney shippingDiscountAmount =
					shippingDiscountValue.getDiscountAmount();

				shippingAmount = shippingAmount.subtract(
					shippingDiscountAmount.getPrice());
			}
		}

		commerceOrder.setShippingAmount(shippingAmount);
		commerceOrder.setTotal(total);
		commerceOrder.setAdvanceStatus(advanceStatus);
		commerceOrder.setExternalReferenceCode(externalReferenceCode);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Override
	public CommerceOrder updateCommercePaymentMethodKey(
			long commerceOrderId, String commercePaymentMethodKey)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		commerceOrder.setCommercePaymentMethodKey(commercePaymentMethodKey);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateCustomFields(
			long commerceOrderId, ServiceContext serviceContext)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		commerceOrder.setExpandoBridgeAttributes(serviceContext);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateOrderStatus(
			long commerceOrderId, int orderStatus)
		throws PortalException {

		// Commerce order

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		final int previousOrderStatus = commerceOrder.getOrderStatus();

		validateOrderStatus(commerceOrder.getCommerceOrderId(), orderStatus);

		commerceOrder.setOrderStatus(orderStatus);

		commerceOrderPersistence.update(commerceOrder);

		// Messaging

		sendOrderStatusMessage(
			commerceOrder.getCommerceOrderId(), commerceOrder.getOrderStatus(),
			previousOrderStatus);

		return commerceOrder;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updatePaymentStatus(
			long userId, long commerceOrderId, int paymentStatus)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		int previousPaymentStatus = commerceOrder.getPaymentStatus();

		commerceOrder.setPaymentStatus(paymentStatus);

		commerceOrderPersistence.update(commerceOrder);

		if ((commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_IN_PROGRESS) &&
			(commerceOrder.getPaymentStatus() ==
				CommerceOrderConstants.PAYMENT_STATUS_PAID)) {

			commerceOrder = setCommerceOrderToTransmit(userId, commerceOrder);
		}

		// Messaging

		sendPaymentStatusMessage(
			commerceOrder.getCommerceOrderId(),
			commerceOrder.getPaymentStatus(), previousPaymentStatus);

		return commerceOrder;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updatePaymentStatusAndTransactionId(
			long userId, long commerceOrderId, int paymentStatus,
			String transactionId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		int previousPaymentStatus = commerceOrder.getPaymentStatus();

		commerceOrder.setTransactionId(transactionId);
		commerceOrder.setPaymentStatus(paymentStatus);

		commerceOrderPersistence.update(commerceOrder);

		if ((commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_IN_PROGRESS) &&
			(commerceOrder.getPaymentStatus() ==
				CommerceOrderConstants.PAYMENT_STATUS_PAID)) {

			commerceOrder = setCommerceOrderToTransmit(userId, commerceOrder);
		}

		// Messaging

		sendPaymentStatusMessage(
			commerceOrder.getCommerceOrderId(),
			commerceOrder.getPaymentStatus(), previousPaymentStatus);

		return commerceOrder;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updatePurchaseOrderNumber(
			long commerceOrderId, String purchaseOrderNumber)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		validatePurchaseOrderNumber(purchaseOrderNumber);

		commerceOrder.setPurchaseOrderNumber(purchaseOrderNumber);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateShippingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, ServiceContext serviceContext)
		throws PortalException {

		return updateAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			CommerceOrder::getShippingAddressId,
			CommerceOrder::setShippingAddressId, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateShippingMethod(
			long commerceOrderId, long commerceShippingMethodId,
			String shippingOptionName, BigDecimal shippingAmount,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		commerceOrder.setCommerceShippingMethodId(commerceShippingMethodId);
		commerceOrder.setShippingOptionName(shippingOptionName);
		commerceOrder.setShippingAmount(shippingAmount);

		commerceOrderPersistence.update(commerceOrder);

		return commerceOrderLocalService.recalculatePrice(
			commerceOrder.getCommerceOrderId(), commerceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateStatus(
			long userId, long commerceOrderId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		commerceOrder.setStatus(status);
		commerceOrder.setStatusByUserId(user.getUserId());
		commerceOrder.setStatusByUserName(user.getFullName());
		commerceOrder.setStatusDate(serviceContext.getModifiedDate(now));

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrder updateTransactionId(
			long commerceOrderId, String transactionId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		commerceOrder.setTransactionId(transactionId);

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Override
	public CommerceOrder updateUser(long commerceOrderId, long userId)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		User user = userLocalService.getUser(userId);

		commerceOrder.setUserId(user.getUserId());
		commerceOrder.setUserName(user.getFullName());

		return commerceOrderPersistence.update(commerceOrder);
	}

	@Override
	public CommerceOrder upsertCommerceOrder(
			long commerceAccountId, long commerceCurrencyId,
			long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			int paymentStatus, int orderStatus, String advanceStatus,
			String externalReferenceCode, CommerceContext commerceContext,
			ServiceContext serviceContext)
		throws PortalException {

		// Update

		CommerceOrder commerceOrder = null;

		if (Validator.isNotNull(externalReferenceCode)) {
			commerceOrder = commerceOrderPersistence.fetchByC_ERC(
				serviceContext.getCompanyId(), externalReferenceCode);
		}

		if (commerceOrder != null) {
			return commerceOrderLocalService.updateCommerceOrder(
				commerceOrder.getCommerceOrderId(), billingAddressId,
				shippingAddressId, commercePaymentMethodKey,
				commerceShippingMethodId, shippingOptionName,
				purchaseOrderNumber, subtotal, shippingAmount, total,
				advanceStatus, externalReferenceCode, commerceContext);
		}

		// Add

		return commerceOrderLocalService.addCommerceOrder(
			commerceAccountId, commerceCurrencyId, billingAddressId,
			shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			serviceContext);
	}

	protected String getCommerceOrderPaymentContent(
		CommercePaymentEngineException cpee) {

		return StackTraceUtil.getStackTrace(cpee);
	}

	protected List<CommerceOrder> getCommerceOrders(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceOrder> commerceOrders = new ArrayList<>(documents.size());

		for (Document document : documents) {
			long commerceOrderId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceOrder commerceOrder = fetchCommerceOrder(commerceOrderId);

			if (commerceOrder == null) {
				commerceOrders = null;

				Indexer<CommerceOrder> indexer = IndexerRegistryUtil.getIndexer(
					CommerceOrder.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceOrders != null) {
				commerceOrders.add(commerceOrder);
			}
		}

		return commerceOrders;
	}

	protected CommerceAddress getNewCommerceAddress(
			CommerceOrder commerceOrder, CommerceAddress commerceAddress,
			ServiceContext serviceContext)
		throws PortalException {

		if (commerceAddress == null) {
			return commerceAddress;
		}

		long groupId = serviceContext.getScopeGroupId();

		List<CommerceAddress> commerceAddresses =
			commerceAddressLocalService.getCommerceAddresses(
				groupId, CommerceAccount.class.getName(),
				commerceOrder.getCommerceAccountId());

		for (CommerceAddress newCommerceAddress : commerceAddresses) {
			if (commerceAddress.isSameAddress(newCommerceAddress)) {
				return newCommerceAddress;
			}
		}

		return commerceAddressLocalService.copyCommerceAddress(
			commerceAddress.getCommerceAddressId(),
			CommerceAccount.class.getName(),
			commerceOrder.getCommerceAccountId(), serviceContext);
	}

	protected boolean hasWorkflowDefinition(long groupId, long typePK)
		throws PortalException {

		Group group = groupLocalService.getGroup(groupId);

		return workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
			group.getCompanyId(), group.getGroupId(),
			CommerceOrder.class.getName(), 0, typePK);
	}

	protected void sendOrderStatusMessage(
		long commerceOrderId, int orderStatus, int previousOrderStatus) {

		TransactionCommitCallbackUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Message message = new Message();

					message.put("commerceOrderId", commerceOrderId);
					message.put("orderStatus", orderStatus);
					message.put("previousOrderStatus", previousOrderStatus);

					MessageBusUtil.sendMessage(
						CommerceDestinationNames.ORDER_STATUS, message);

					return null;
				}

			});
	}

	protected void sendPaymentStatusMessage(
		long commerceOrderId, int paymentStatus, int previousPaymentStatus) {

		TransactionCommitCallbackUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Message message = new Message();

					message.put("commerceOrderId", commerceOrderId);
					message.put("paymentStatus", paymentStatus);
					message.put("previousPaymentStatus", previousPaymentStatus);

					MessageBusUtil.sendMessage(
						CommerceDestinationNames.PAYMENT_STATUS, message);

					return null;
				}

			});
	}

	protected CommerceOrder setCommerceOrderToTransmit(
			long userId, CommerceOrder commerceOrder)
		throws PortalException {

		// Commerce order

		int previousOrderStatus = commerceOrder.getOrderStatus();

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT);
		commerceOrder.setStatus(WorkflowConstants.STATUS_PENDING);

		commerceOrderPersistence.update(commerceOrder);

		// Messaging

		sendOrderStatusMessage(
			commerceOrder.getCommerceOrderId(), commerceOrder.getOrderStatus(),
			previousOrderStatus);

		// Workflow

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(commerceOrder.getGroupId());
		serviceContext.setUserId(userId);
		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		return startWorkflowInstance(
			serviceContext.getUserId(), commerceOrder, serviceContext);
	}

	protected CommerceOrder startWorkflowInstance(
			long userId, CommerceOrder commerceOrder,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			commerceOrder.getCompanyId(), commerceOrder.getScopeGroupId(),
			userId, CommerceOrder.class.getName(),
			commerceOrder.getCommerceOrderId(), commerceOrder, serviceContext,
			workflowContext);
	}

	protected CommerceOrder updateAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			Function<CommerceOrder, Long> commerceAddressIdGetter,
			BiConsumer<CommerceOrder, Long> commerceAddressIdSetter,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceOrderPersistence.findByPrimaryKey(
			commerceOrderId);

		CommerceAddress commerceAddress = null;

		long commerceAddressId = commerceAddressIdGetter.apply(commerceOrder);

		if (commerceAddressId > 0) {
			commerceAddress = commerceAddressLocalService.updateCommerceAddress(
				commerceAddressId, name, description, street1, street2, street3,
				city, zip, commerceRegionId, commerceCountryId, phoneNumber,
				false, false, serviceContext);
		}
		else {
			commerceAddress = commerceAddressLocalService.addCommerceAddress(
				commerceOrder.getModelClassName(),
				commerceOrder.getCommerceOrderId(), name, description, street1,
				street2, street3, city, zip, commerceRegionId,
				commerceCountryId, phoneNumber, false, false, serviceContext);
		}

		commerceAddressIdSetter.accept(
			commerceOrder, commerceAddress.getCommerceAddressId());

		return commerceOrderPersistence.update(commerceOrder);
	}

	protected void validateCheckout(CommerceOrder commerceOrder)
		throws PortalException {

		if (commerceOrder.isDraft() ||
			(!commerceOrder.isOpen() && !commerceOrder.isSubscription())) {

			throw new CommerceOrderStatusException();
		}

		if (commerceOrder.isB2B() &&
			(commerceOrder.getBillingAddressId() <= 0)) {

			throw new CommerceOrderBillingAddressException();
		}

		CommerceShippingMethod commerceShippingMethod = null;

		long commerceShippingMethodId =
			commerceOrder.getCommerceShippingMethodId();

		if (commerceShippingMethodId > 0) {
			commerceShippingMethod =
				commerceShippingMethodLocalService.getCommerceShippingMethod(
					commerceShippingMethodId);

			if (!commerceShippingMethod.isActive()) {
				commerceShippingMethod = null;
			}
			else if (commerceOrder.getShippingAddressId() <= 0) {
				throw new CommerceOrderShippingAddressException();
			}
		}

		if ((commerceShippingMethod == null) &&
			(commerceShippingMethodLocalService.getCommerceShippingMethodsCount(
				commerceOrder.getGroupId(), true) > 0) &&
			_commerceShippingHelper.isShippable(commerceOrder)) {

			throw new CommerceOrderShippingMethodException();
		}
	}

	protected void validateGuestOrders() throws PortalException {
		int count = commerceOrderPersistence.countByUserId(
			UserConstants.USER_ID_DEFAULT);

		if (count >= _commerceOrderConfiguration.guestCartMaxAllowed()) {
			throw new GuestCartMaxAllowedException();
		}
	}

	protected void validateOrderStatus(long commerceOrderId, int orderStatus)
		throws PortalException {

		int[] availableOrderStatuses = getAvailableOrderStatuses(
			commerceOrderId);

		if (!ArrayUtil.contains(availableOrderStatuses, orderStatus)) {
			throw new CommerceOrderStatusException();
		}
	}

	protected void validatePurchaseOrderNumber(String purchaseOrderNumber)
		throws PortalException {

		if (Validator.isNull(purchaseOrderNumber)) {
			throw new CommerceOrderPurchaseOrderNumberException();
		}
	}

	protected static final int[] AVAILABLE_ORDER_STATUSES = {
		CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT,
		CommerceOrderConstants.ORDER_STATUS_TRANSMITTED,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_FULFILLMENT,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_PICKUP,
		CommerceOrderConstants.ORDER_STATUS_AWAITING_SHIPMENT,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_REFUNDED,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED,
		CommerceOrderConstants.ORDER_STATUS_REFUNDED,
		CommerceOrderConstants.ORDER_STATUS_SHIPPED,
		CommerceOrderConstants.ORDER_STATUS_COMPLETED,
		CommerceOrderConstants.ORDER_STATUS_CANCELLED,
		CommerceOrderConstants.ORDER_STATUS_DECLINED,
		CommerceOrderConstants.ORDER_STATUS_DISPUTED
	};

	private void _setCommerceOrderShippingDiscountValue(
		CommerceOrder commerceOrder,
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal discountAmount = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			discountAmount = discountAmountCommerceMoney.getPrice();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			if (percentages.length >= 1) {
				discountPercentageLevel1 = percentages[0];
			}

			if (percentages.length >= 2) {
				discountPercentageLevel1 = percentages[1];
			}

			if (percentages.length >= 3) {
				discountPercentageLevel1 = percentages[2];
			}

			if (percentages.length >= 4) {
				discountPercentageLevel1 = percentages[3];
			}
		}

		commerceOrder.setShippingDiscountAmount(discountAmount);
		commerceOrder.setShippingDiscountPercentageLevel1(
			discountPercentageLevel1);
		commerceOrder.setShippingDiscountPercentageLevel2(
			discountPercentageLevel2);
		commerceOrder.setShippingDiscountPercentageLevel3(
			discountPercentageLevel3);
		commerceOrder.setShippingDiscountPercentageLevel4(
			discountPercentageLevel4);
	}

	private void _setCommerceOrderSubtotalDiscountValue(
		CommerceOrder commerceOrder,
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal discountAmount = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			discountAmount = discountAmountCommerceMoney.getPrice();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			if (percentages.length >= 1) {
				discountPercentageLevel1 = percentages[0];
			}

			if (percentages.length >= 2) {
				discountPercentageLevel1 = percentages[1];
			}

			if (percentages.length >= 3) {
				discountPercentageLevel1 = percentages[2];
			}

			if (percentages.length >= 4) {
				discountPercentageLevel1 = percentages[3];
			}
		}

		commerceOrder.setSubtotalDiscountAmount(discountAmount);
		commerceOrder.setSubtotalDiscountPercentageLevel1(
			discountPercentageLevel1);
		commerceOrder.setSubtotalDiscountPercentageLevel2(
			discountPercentageLevel2);
		commerceOrder.setSubtotalDiscountPercentageLevel3(
			discountPercentageLevel3);
		commerceOrder.setSubtotalDiscountPercentageLevel4(
			discountPercentageLevel4);
	}

	private void _setCommerceOrderTotalDiscountValue(
		CommerceOrder commerceOrder,
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal discountAmount = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			discountAmount = discountAmountCommerceMoney.getPrice();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			if (percentages.length >= 1) {
				discountPercentageLevel1 = percentages[0];
			}

			if (percentages.length >= 2) {
				discountPercentageLevel1 = percentages[1];
			}

			if (percentages.length >= 3) {
				discountPercentageLevel1 = percentages[2];
			}

			if (percentages.length >= 4) {
				discountPercentageLevel1 = percentages[3];
			}
		}

		commerceOrder.setTotalDiscountAmount(discountAmount);
		commerceOrder.setTotalDiscountPercentageLevel1(
			discountPercentageLevel1);
		commerceOrder.setTotalDiscountPercentageLevel2(
			discountPercentageLevel2);
		commerceOrder.setTotalDiscountPercentageLevel3(
			discountPercentageLevel3);
		commerceOrder.setTotalDiscountPercentageLevel4(
			discountPercentageLevel4);
	}

	@ServiceReference(type = CommerceCurrencyLocalService.class)
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@ServiceReference(type = CommerceDiscountLocalService.class)
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@ServiceReference(type = CommerceOrderConfiguration.class)
	private CommerceOrderConfiguration _commerceOrderConfiguration;

	@ServiceReference(type = CommerceOrderPriceCalculation.class)
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@ServiceReference(type = CommerceShippingHelper.class)
	private CommerceShippingHelper _commerceShippingHelper;

	@ServiceReference(type = DDMFormValuesHelper.class)
	private DDMFormValuesHelper _ddmFormValuesHelper;

	@ServiceReference(type = WorkflowTaskManager.class)
	private WorkflowTaskManager _workflowTaskManager;

}