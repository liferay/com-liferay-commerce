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

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.service.CommerceInventoryBookedQuantityLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.base.CommerceOrderServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderServiceImpl extends CommerceOrderServiceBaseImpl {

	@Override
	public CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.ADD_COMMERCE_ORDER);

		return commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId);
	}

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long commerceCurrencyId,
			long shippingAddressId, String purchaseOrderNumber)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.ADD_COMMERCE_ORDER);

		return commerceOrderLocalService.addCommerceOrder(
			getUserId(), groupId, commerceAccountId, commerceCurrencyId,
			shippingAddressId, purchaseOrderNumber);
	}

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long shippingAddressId,
			String purchaseOrderNumber)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.ADD_COMMERCE_ORDER);

		return commerceOrderLocalService.addCommerceOrder(
			getUserId(), groupId, commerceAccountId, shippingAddressId,
			purchaseOrderNumber);
	}

	@Override
	public CommerceOrder applyCouponCode(
			long commerceOrderId, String couponCode,
			CommerceContext commerceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.applyCouponCode(
			commerceOrderId, couponCode, commerceContext);
	}

	@Override
	public CommerceOrder approveCommerceOrder(long commerceOrderId)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId,
			CommerceOrderActionKeys.APPROVE_COMMERCE_ORDER);

		return commerceOrderLocalService.approveCommerceOrder(
			getUserId(), commerceOrderId);
	}

	@Override
	public CommerceOrder checkoutCommerceOrder(
			long commerceOrderId, CommerceContext commerceContext,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId,
			CommerceOrderActionKeys.CHECKOUT_COMMERCE_ORDER);

		_bookQuantities(getCommerceOrder(commerceOrderId));

		return commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrderId, commerceContext, serviceContext);
	}

	@Override
	public void deleteCommerceOrder(long commerceOrderId)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.DELETE);

		commerceOrderLocalService.deleteCommerceOrder(commerceOrderId);
	}

	@Override
	public CommerceOrder executeWorkflowTransition(
			long commerceOrderId, long workflowTaskId, String transitionName,
			String comment)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.executeWorkflowTransition(
			getUserId(), commerceOrderId, workflowTaskId, transitionName,
			comment);
	}

	@Override
	public CommerceOrder fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (commerceOrder != null) {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrder, ActionKeys.VIEW);
		}

		return commerceOrder;
	}

	@Override
	public CommerceOrder fetchCommerceOrder(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.fetchCommerceOrder(commerceOrderId);

		if (commerceOrder != null) {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrder, ActionKeys.VIEW);
		}

		return commerceOrder;
	}

	@Override
	public CommerceOrder fetchCommerceOrder(
			long commerceAccountId, long groupId, int orderStatus)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.fetchCommerceOrder(
				commerceAccountId, groupId, orderStatus);

		if (commerceOrder != null) {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrder, ActionKeys.VIEW);
		}

		return commerceOrder;
	}

	@Override
	public CommerceOrder fetchCommerceOrder(String uuid, long groupId)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.fetchCommerceOrderByUuidAndGroupId(
				uuid, groupId);

		if (commerceOrder != null) {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrder, ActionKeys.VIEW);
		}

		return commerceOrder;
	}

	@Override
	public int[] getAvailableOrderStatuses(long commerceOrderId)
		throws PortalException {

		if (commerceOrderId > 0) {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);
		}

		return commerceOrderLocalService.getAvailableOrderStatuses(
			commerceOrderId);
	}

	@Override
	public CommerceOrder getCommerceOrder(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrder, ActionKeys.VIEW);

		return commerceOrder;
	}

	@Override
	public CommerceOrder getCommerceOrderByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrderByUuidAndGroupId(
				uuid, groupId);

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrder, ActionKeys.VIEW);

		return commerceOrder;
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
			long groupId, int start, int end,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrders(
			groupId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
			long groupId, int[] orderStatuses)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrders(
			groupId, orderStatuses);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
			long groupId, int[] orderStatuses, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrders(
			groupId, orderStatuses, start, end);
	}

	@Override
	public List<CommerceOrder> getCommerceOrders(
			long groupId, long commerceAccountId, int start, int end,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrders(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceOrdersCount(long groupId) throws PortalException {
		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrdersCount(groupId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId, long commerceAccountId)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrdersCount(
			groupId, commerceAccountId);
	}

	@Override
	public List<CommerceOrder> getPendingCommerceOrders(
			long companyId, long groupId, long[] commerceAccountIds, int start,
			int end)
		throws PortalException {

		if (PortalPermissionUtil.contains(
				getPermissionChecker(),
				CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS)) {

			commerceAccountIds = null;
		}

		return commerceOrderLocalService.getCommerceOrders(
			companyId, groupId, commerceAccountIds,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, false, start,
			end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public List<CommerceOrder> getPendingCommerceOrders(
			long groupId, long commerceAccountId, String keywords, int start,
			int end)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS);

		return commerceOrderLocalService.getUserCommerceOrders(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, false, keywords, start,
			end);
	}

	@Override
	public long getPendingCommerceOrdersCount(
			long companyId, long groupId, long[] commerceAccountIds)
		throws PortalException {

		if (PortalPermissionUtil.contains(
				getPermissionChecker(),
				CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS)) {

			commerceAccountIds = null;
		}

		return commerceOrderLocalService.getCommerceOrdersCount(
			companyId, groupId, commerceAccountIds,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, false);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getPendingCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS);

		return commerceOrderLocalService.getUserCommerceOrdersCount(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, false, keywords);
	}

	@Override
	public List<CommerceOrder> getPlacedCommerceOrders(
			long companyId, long groupId, long[] commerceAccountIds, int start,
			int end)
		throws PortalException {

		if (PortalPermissionUtil.contains(
				getPermissionChecker(),
				CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS)) {

			commerceAccountIds = null;
		}

		return commerceOrderLocalService.getCommerceOrders(
			companyId, groupId, commerceAccountIds,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, true, start,
			end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public List<CommerceOrder> getPlacedCommerceOrders(
			long groupId, long commerceAccountId, String keywords, int start,
			int end)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS);

		return commerceOrderLocalService.getUserCommerceOrders(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, true, keywords, start,
			end);
	}

	@Override
	public long getPlacedCommerceOrdersCount(
			long companyId, long groupId, long[] commerceAccountIds)
		throws PortalException {

		if (PortalPermissionUtil.contains(
				getPermissionChecker(),
				CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS)) {

			commerceAccountIds = null;
		}

		return commerceOrderLocalService.getCommerceOrdersCount(
			companyId, groupId, commerceAccountIds,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, true);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getPlacedCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws PortalException {

		_checkAccountOrderPermissions(
			groupId, commerceAccountId,
			CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS);

		return commerceOrderLocalService.getUserCommerceOrdersCount(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, true, keywords);
	}

	@Override
	public void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), guestCommerceOrderId, ActionKeys.VIEW);
		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), userCommerceOrderId, ActionKeys.UPDATE);

		commerceOrderLocalService.mergeGuestCommerceOrder(
			guestCommerceOrderId, userCommerceOrderId, commerceContext,
			serviceContext);
	}

	@Override
	public CommerceOrder reorderCommerceOrder(
			long commerceOrderId, CommerceContext commerceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.VIEW);

		return commerceOrderLocalService.reorderCommerceOrder(
			getUserId(), commerceOrderId, commerceContext);
	}

	@Override
	public CommerceOrder submitCommerceOrder(long commerceOrderId)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.submitCommerceOrder(
			getUserId(), commerceOrderId);
	}

	@Override
	public CommerceOrder updateBillingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateBillingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	@Override
	public CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			String advanceStatus, CommerceContext commerceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, commerceContext);
	}

	@Override
	public CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			String advanceStatus, String externalReferenceCode,
			CommerceContext commerceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, externalReferenceCode, commerceContext);
	}

	@Override
	public CommerceOrder updateCommerceOrderPrices(
			long commerceOrderId, BigDecimal subtotal,
			BigDecimal subtotalDiscountAmount,
			BigDecimal subtotalDiscountPercentageLevel1,
			BigDecimal subtotalDiscountPercentageLevel2,
			BigDecimal subtotalDiscountPercentageLevel3,
			BigDecimal subtotalDiscountPercentageLevel4,
			BigDecimal shippingAmount, BigDecimal shippingDiscountAmount,
			BigDecimal shippingDiscountPercentageLevel1,
			BigDecimal shippingDiscountPercentageLevel2,
			BigDecimal shippingDiscountPercentageLevel3,
			BigDecimal shippingDiscountPercentageLevel4, BigDecimal taxAmount,
			BigDecimal total, BigDecimal totalDiscountAmount,
			BigDecimal totalDiscountPercentageLevel1,
			BigDecimal totalDiscountPercentageLevel2,
			BigDecimal totalDiscountPercentageLevel3,
			BigDecimal totalDiscountPercentageLevel4)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_ORDER_PRICES);

		return commerceOrderLocalService.updateCommerceOrderPrices(
			commerceOrderId, subtotal, subtotalDiscountAmount,
			subtotalDiscountPercentageLevel1, subtotalDiscountPercentageLevel2,
			subtotalDiscountPercentageLevel3, subtotalDiscountPercentageLevel4,
			shippingAmount, shippingDiscountAmount,
			shippingDiscountPercentageLevel1, shippingDiscountPercentageLevel2,
			shippingDiscountPercentageLevel3, shippingDiscountPercentageLevel4,
			taxAmount, total, totalDiscountAmount,
			totalDiscountPercentageLevel1, totalDiscountPercentageLevel2,
			totalDiscountPercentageLevel3, totalDiscountPercentageLevel4);
	}

	@Override
	public CommerceOrder updateCommercePaymentMethodKey(
			long commerceOrderId, String commercePaymentMethodKey)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId,
			CommerceOrderActionKeys.CHECKOUT_COMMERCE_ORDER);

		return commerceOrderLocalService.updateCommercePaymentMethodKey(
			commerceOrderId, commercePaymentMethodKey);
	}

	@Override
	public CommerceOrder updateCustomFields(
			long commerceOrderId, ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateCustomFields(
			commerceOrderId, serviceContext);
	}

	@Override
	public CommerceOrder updateInfo(
			long commerceOrderId, String printedNote,
			int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
			int requestedDeliveryDateYear, int requestedDeliveryDateHour,
			int requestedDeliveryDateMinute, ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateInfo(
			commerceOrderId, printedNote, requestedDeliveryDateMonth,
			requestedDeliveryDateDay, requestedDeliveryDateYear,
			requestedDeliveryDateHour, requestedDeliveryDateMinute,
			serviceContext);
	}

	@Override
	public CommerceOrder updateOrderStatus(
			long commerceOrderId, int orderStatus)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateOrderStatus(
			commerceOrderId, orderStatus);
	}

	@Override
	public CommerceOrder updatePaymentStatus(
			long commerceOrderId, int paymentStatus)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		_commerceOrderModelResourcePermission.check(
			permissionChecker, commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updatePaymentStatus(
			permissionChecker.getUserId(), commerceOrderId, paymentStatus);
	}

	@Override
	public CommerceOrder updatePaymentStatusAndTransactionId(
			long commerceOrderId, int paymentStatus, String transactionId)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		_commerceOrderModelResourcePermission.check(
			permissionChecker, commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			permissionChecker.getUserId(), commerceOrderId, paymentStatus,
			transactionId);
	}

	@Override
	public CommerceOrder updatePurchaseOrderNumber(
			long commerceOrderId, String purchaseOrderNumber)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updatePurchaseOrderNumber(
			commerceOrderId, purchaseOrderNumber);
	}

	@Override
	public CommerceOrder updateShippingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, ServiceContext serviceContext)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateShippingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	@Override
	public CommerceOrder updateTransactionId(
			long commerceOrderId, String transactionId)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateTransactionId(
			commerceOrderId, transactionId);
	}

	@Override
	public CommerceOrder updateUser(long commerceOrderId, long userId)
		throws PortalException {

		_commerceOrderModelResourcePermission.check(
			getPermissionChecker(), commerceOrderId, ActionKeys.UPDATE);

		return commerceOrderLocalService.updateUser(commerceOrderId, userId);
	}

	@Override
	public CommerceOrder upsertCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId, long billingAddressId,
			long shippingAddressId, String commercePaymentMethodKey,
			long commerceShippingMethodId, String shippingOptionName,
			String purchaseOrderNumber, BigDecimal subtotal,
			BigDecimal shippingAmount, BigDecimal total, int paymentStatus,
			int orderStatus, String advanceStatus, String externalReferenceCode,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.fetchByExternalReferenceCode(
				serviceContext.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			_portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				CommerceOrderActionKeys.ADD_COMMERCE_ORDER);
		}
		else {
			_commerceOrderModelResourcePermission.check(
				getPermissionChecker(), commerceOrder, ActionKeys.UPDATE);
		}

		return commerceOrderLocalService.upsertCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			advanceStatus, externalReferenceCode, commerceContext,
			serviceContext);
	}

	private void _bookQuantities(CommerceOrder commerceOrder)
		throws PortalException {

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			Map<String, String> context = new HashMap<>();

			context.put(
				"OrderId ",
				String.valueOf(commerceOrderItem.getCommerceOrderId()));
			context.put(
				"OrderItemId ",
				String.valueOf(commerceOrderItem.getCommerceOrderItemId()));

			CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
				_commerceInventoryBookedQuantityLocalService.
					addCommerceBookedQuantity(
						commerceOrderItem.getUserId(),
						commerceOrderItem.getSku(),
						commerceOrderItem.getQuantity(), null, context);

			commerceOrderItemLocalService.updateCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceInventoryBookedQuantity.
					getCommerceInventoryBookedQuantityId());
		}
	}

	private void _checkAccountOrderPermissions(
			long groupId, long commerceAccountId, String action)
		throws PortalException {

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.fetchCommerceAccount(
				commerceAccountId);

		if (commerceAccount == null) {
			_portletResourcePermission.check(
				getPermissionChecker(), groupId, action);
		}
		else if (commerceAccount.isBusinessAccount()) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commerceAccount.getCommerceAccountGroup(), action);
		}
	}

	private static volatile ModelResourcePermission<CommerceOrder>
		_commerceOrderModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceOrderServiceImpl.class,
				"_commerceOrderModelResourcePermission", CommerceOrder.class);
	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceOrderServiceImpl.class, "_portletResourcePermission",
				CommerceOrderConstants.RESOURCE_NAME);

	@ServiceReference(type = CommerceAccountLocalService.class)
	private CommerceAccountLocalService _commerceAccountLocalService;

	@ServiceReference(type = CommerceInventoryBookedQuantityLocalService.class)
	private CommerceInventoryBookedQuantityLocalService
		_commerceInventoryBookedQuantityLocalService;

}