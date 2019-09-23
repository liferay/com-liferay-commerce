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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceOrderService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderService
 * @generated
 */
public class CommerceOrderServiceWrapper
	implements CommerceOrderService, ServiceWrapper<CommerceOrderService> {

	public CommerceOrderServiceWrapper(
		CommerceOrderService commerceOrderService) {

		_commerceOrderService = commerceOrderService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceOrderServiceUtil} to access the commerce order remote service. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceOrderServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long commerceCurrencyId,
			long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.addCommerceOrder(
			groupId, commerceAccountId, commerceCurrencyId, shippingAddressId,
			purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long shippingAddressId,
			String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.addCommerceOrder(
			groupId, commerceAccountId, shippingAddressId, purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder applyCouponCode(
			long commerceOrderId, String couponCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.applyCouponCode(
			commerceOrderId, couponCode, commerceContext);
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

		return _commerceOrderService.checkoutCommerceOrder(
			commerceOrderId, commerceContext, serviceContext);
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

		return _commerceOrderService.executeWorkflowTransition(
			commerceOrderId, workflowTaskId, transitionName, comment);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
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

		return _commerceOrderService.fetchCommerceOrder(
			commerceAccountId, groupId, orderStatus);
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
	public com.liferay.commerce.model.CommerceOrder
			getCommerceOrderByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrderByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrders(
			groupId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(long groupId, int[] orderStatuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrders(groupId, orderStatuses);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, int[] orderStatuses, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrders(
			groupId, orderStatuses, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, long commerceAccountId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrders(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceOrdersCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrdersCount(groupId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getCommerceOrdersCount(
			groupId, commerceAccountId);
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
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPendingCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getPendingCommerceOrders(
			groupId, commerceAccountId, keywords, start, end);
	}

	@Override
	public int getPendingCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getPendingCommerceOrdersCount(
			groupId, commerceAccountId, keywords);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPlacedCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getPlacedCommerceOrders(
			groupId, commerceAccountId, keywords, start, end);
	}

	@Override
	public int getPlacedCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.getPlacedCommerceOrdersCount(
			groupId, commerceAccountId, keywords);
	}

	@Override
	public void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceOrderService.mergeGuestCommerceOrder(
			guestCommerceOrderId, userCommerceOrderId, commerceContext,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
			long commerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.reorderCommerceOrder(
			commerceOrderId, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.submitCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateBillingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateBillingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
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

		return _commerceOrderService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, commerceContext);
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

		return _commerceOrderService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, externalReferenceCode, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrderPrices(
			long commerceOrderId, java.math.BigDecimal subtotal,
			java.math.BigDecimal subtotalDiscountAmount,
			java.math.BigDecimal subtotalDiscountPercentageLevel1,
			java.math.BigDecimal subtotalDiscountPercentageLevel2,
			java.math.BigDecimal subtotalDiscountPercentageLevel3,
			java.math.BigDecimal subtotalDiscountPercentageLevel4,
			java.math.BigDecimal shippingAmount,
			java.math.BigDecimal shippingDiscountAmount,
			java.math.BigDecimal shippingDiscountPercentageLevel1,
			java.math.BigDecimal shippingDiscountPercentageLevel2,
			java.math.BigDecimal shippingDiscountPercentageLevel3,
			java.math.BigDecimal shippingDiscountPercentageLevel4,
			java.math.BigDecimal taxAmount, java.math.BigDecimal total,
			java.math.BigDecimal totalDiscountAmount,
			java.math.BigDecimal totalDiscountPercentageLevel1,
			java.math.BigDecimal totalDiscountPercentageLevel2,
			java.math.BigDecimal totalDiscountPercentageLevel3,
			java.math.BigDecimal totalDiscountPercentageLevel4)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateCommerceOrderPrices(
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
	public com.liferay.commerce.model.CommerceOrder
			updateCommercePaymentMethodKey(
				long commerceOrderId, String commercePaymentMethodKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateCommercePaymentMethodKey(
			commerceOrderId, commercePaymentMethodKey);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCustomFields(
			long commerceOrderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateCustomFields(
			commerceOrderId, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateInfo(
			long commerceOrderId, String printedNote,
			int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
			int requestedDeliveryDateYear, int requestedDeliveryDateHour,
			int requestedDeliveryDateMinute,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateInfo(
			commerceOrderId, printedNote, requestedDeliveryDateMonth,
			requestedDeliveryDateDay, requestedDeliveryDateYear,
			requestedDeliveryDateHour, requestedDeliveryDateMinute,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateOrderStatus(
			long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateOrderStatus(
			commerceOrderId, orderStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
			long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updatePaymentStatus(
			commerceOrderId, paymentStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder
			updatePaymentStatusAndTransactionId(
				long commerceOrderId, int paymentStatus, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updatePaymentStatusAndTransactionId(
			commerceOrderId, paymentStatus, transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
			long commerceOrderId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updatePurchaseOrderNumber(
			commerceOrderId, purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateShippingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateShippingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateTransactionId(
			long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateTransactionId(
			commerceOrderId, transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateUser(
			long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.updateUser(commerceOrderId, userId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder upsertCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId, long billingAddressId,
			long shippingAddressId, String commercePaymentMethodKey,
			long commerceShippingMethodId, String shippingOptionName,
			String purchaseOrderNumber, java.math.BigDecimal subtotal,
			java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
			int paymentStatus, int orderStatus, String advanceStatus,
			String externalReferenceCode,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderService.upsertCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			advanceStatus, externalReferenceCode, commerceContext,
			serviceContext);
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