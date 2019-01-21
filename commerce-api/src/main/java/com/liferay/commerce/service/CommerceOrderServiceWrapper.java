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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceOrderService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderService
 * @generated
 */
@ProviderType
public class CommerceOrderServiceWrapper implements CommerceOrderService,
	ServiceWrapper<CommerceOrderService> {
	public CommerceOrderServiceWrapper(
		CommerceOrderService commerceOrderService) {
		_commerceOrderService = commerceOrderService;
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long userId, long commerceAccountId,
		long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.addCommerceOrder(groupId, userId,
			commerceAccountId, commerceCurrencyId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long commerceAccountId, long commerceCurrencyId,
		long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.addCommerceOrder(groupId,
			commerceAccountId, commerceCurrencyId, shippingAddressId,
			purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long commerceAccountId, long shippingAddressId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.addCommerceOrder(groupId,
			commerceAccountId, shippingAddressId, purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.approveCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder checkoutCommerceOrder(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.checkoutCommerceOrder(commerceOrderId,
			commerceContext, serviceContext);
	}

	@Override
	public void deleteCommerceOrder(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceOrderService.deleteCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder executeWorkflowTransition(
		long commerceOrderId, long workflowTaskId, String transitionName,
		String comment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.executeWorkflowTransition(commerceOrderId,
			workflowTaskId, transitionName, comment);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.fetchByExternalReferenceCode(companyId,
			externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.fetchCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceAccountId, long groupId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.fetchCommerceOrder(commerceAccountId,
			groupId, orderStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.fetchCommerceOrder(uuid, groupId);
	}

	@Override
	public int[] getAvailableOrderStatuses(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getAvailableOrderStatuses(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder getCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder getCommerceOrderByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrderByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrders(groupId, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, int[] orderStatuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrders(groupId, orderStatuses);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, long commerceAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrders(groupId,
			commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceOrdersCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrdersCount(groupId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getCommerceOrdersCount(groupId,
			commerceAccountId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceOrderService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		long groupId, long userId, int orderStatus, String keywords, int start,
		int end) {
		return _commerceOrderService.getUserCommerceOrders(groupId, userId,
			orderStatus, keywords, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		long groupId, long userId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords, int start, int end) {
		return _commerceOrderService.getUserCommerceOrders(groupId, userId,
			orderStatus, excludeOrderStatus, keywords, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		long groupId, long userId, String keywords, int start, int end) {
		return _commerceOrderService.getUserCommerceOrders(groupId, userId,
			keywords, start, end);
	}

	@Override
	public int getUserCommerceOrdersCount(long groupId, long userId,
		int orderStatus, boolean excludeOrderStatus, String keywords) {
		return _commerceOrderService.getUserCommerceOrdersCount(groupId,
			userId, orderStatus, excludeOrderStatus, keywords);
	}

	@Override
	public int getUserCommerceOrdersCount(long groupId, long userId,
		int orderStatus, String keywords) {
		return _commerceOrderService.getUserCommerceOrdersCount(groupId,
			userId, orderStatus, keywords);
	}

	@Override
	public int getUserCommerceOrdersCount(long groupId, long userId,
		String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.getUserCommerceOrdersCount(groupId,
			userId, keywords);
	}

	@Override
	public void mergeGuestCommerceOrder(long guestCommerceOrderId,
		long userCommerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceOrderService.mergeGuestCommerceOrder(guestCommerceOrderId,
			userCommerceOrderId, commerceContext, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.reorderCommerceOrder(commerceOrderId,
			commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.submitCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateBillingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateBillingAddress(commerceOrderId,
			name, description, street1, street2, street3, city, zip,
			commerceRegionId, commerceCountryId, phoneNumber, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateCommerceOrder(commerceOrderId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, advanceStatus, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateCommerceOrder(commerceOrderId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, advanceStatus,
			externalReferenceCode, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateOrderStatus(
		long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateOrderStatus(commerceOrderId,
			orderStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
		long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updatePaymentStatus(commerceOrderId,
			paymentStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePaymentStatusAndTransactionId(
		long commerceOrderId, int paymentStatus, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updatePaymentStatusAndTransactionId(commerceOrderId,
			paymentStatus, transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
		long commerceOrderId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updatePurchaseOrderNumber(commerceOrderId,
			purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateShippingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateShippingAddress(commerceOrderId,
			name, description, street1, street2, street3, city, zip,
			commerceRegionId, commerceCountryId, phoneNumber, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateTransactionId(
		long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateTransactionId(commerceOrderId,
			transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateUser(
		long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceOrderService.updateUser(commerceOrderId, userId);
	}

	@Override
	public CommerceOrderService getWrappedService() {
		return _commerceOrderService;
	}

	@Override
	public void setWrappedService(CommerceOrderService commerceOrderService) {
		_commerceOrderService = commerceOrderService;
	}

	private CommerceOrderService _commerceOrderService;
}