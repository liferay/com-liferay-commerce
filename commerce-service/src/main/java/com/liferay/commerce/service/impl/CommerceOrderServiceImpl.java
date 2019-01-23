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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.internal.security.permission.CommerceOrderWorkflowPermissionChecker;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.base.CommerceOrderServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderServiceImpl extends CommerceOrderServiceBaseImpl {

	@Override
	public CommerceOrder addCommerceOrder(
			long groupId, long userId, long commerceAccountId,
			long commerceCurrencyId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.ADD_COMMERCE_ORDER);

		return commerceOrderLocalService.addCommerceOrder(
			groupId, userId, commerceAccountId, commerceCurrencyId);
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
			groupId, getUserId(), commerceAccountId, commerceCurrencyId,
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
			groupId, getUserId(), commerceAccountId, shippingAddressId,
			purchaseOrderNumber);
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

		CommerceOrder commerceOrder = getCommerceOrder(commerceOrderId);

		WorkflowTask workflowTask = _workflowTaskManager.getWorkflowTask(
			commerceOrder.getCompanyId(), workflowTaskId);

		if (!_commerceOrderWorkflowPermissionChecker.hasPermission(
				commerceOrder, workflowTask, getPermissionChecker())) {

			throw new PrincipalException();
		}

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
	public int getCommerceOrdersCount(long groupId) throws PortalException {
		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);

		return commerceOrderLocalService.getCommerceOrdersCount(groupId);
	}

	@Override
	public List<CommerceOrder> getPendingCommerceOrders(
			long groupId, long commerceAccountId, String keywords, int start,
			int end)
		throws PortalException {

		checkAccountOrderPermissions(groupId, commerceAccountId);

		return commerceOrderLocalService.getUserCommerceOrders(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, false, keywords, start,
			end);
	}

	@Override
	public int getPendingCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws PortalException {

		checkAccountOrderPermissions(groupId, commerceAccountId);

		return commerceOrderLocalService.getUserCommerceOrdersCount(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, false, keywords);
	}

	@Override
	public List<CommerceOrder> getPlacedCommerceOrders(
			long groupId, long commerceAccountId, String keywords, int start,
			int end)
		throws PortalException {

		checkAccountOrderPermissions(groupId, commerceAccountId);

		return commerceOrderLocalService.getUserCommerceOrders(
			groupId, getUserId(), commerceAccountId,
			CommerceOrderConstants.ORDER_STATUS_OPEN, false, keywords, start,
			end);
	}

	@Override
	public int getPlacedCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws PortalException {

		checkAccountOrderPermissions(groupId, commerceAccountId);

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

	private void checkAccountOrderPermissions(
			long groupId, long commerceAccountId)
		throws PortalException {

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.fetchCommerceAccount(
				commerceAccountId);

		if (commerceAccount == null) {
			_portletResourcePermission.check(
				getPermissionChecker(), groupId,
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);
		}
		else if (commerceAccount.isBusinessAccount()) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commerceAccount.getCommerceAccountGroup(),
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);
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

	@ServiceReference(type = CommerceOrderWorkflowPermissionChecker.class)
	private CommerceOrderWorkflowPermissionChecker
		_commerceOrderWorkflowPermissionChecker;

	@ServiceReference(type = WorkflowTaskManager.class)
	private WorkflowTaskManager _workflowTaskManager;

}