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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceOrder. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceOrderServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderService
 * @generated
 */
public class CommerceOrderServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceOrderServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceOrderServiceUtil} to access the commerce order remote service. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceOrderServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long commerceCurrencyId,
			long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceOrder(
			groupId, commerceAccountId, commerceCurrencyId, shippingAddressId,
			purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long groupId, long commerceAccountId, long shippingAddressId,
			String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceOrder(
			groupId, commerceAccountId, shippingAddressId, purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder applyCouponCode(
			long commerceOrderId, String couponCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().applyCouponCode(
			commerceOrderId, couponCode, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().approveCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder
			checkoutCommerceOrder(
				long commerceOrderId,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().checkoutCommerceOrder(
			commerceOrderId, commerceContext, serviceContext);
	}

	public static void deleteCommerceOrder(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder
			executeWorkflowTransition(
				long commerceOrderId, long workflowTaskId,
				String transitionName, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().executeWorkflowTransition(
			commerceOrderId, workflowTaskId, transitionName, comment);
	}

	public static com.liferay.commerce.model.CommerceOrder
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
			long commerceAccountId, long groupId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceOrder(
			commerceAccountId, groupId, orderStatus);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCommerceOrder(uuid, groupId);
	}

	public static int[] getAvailableOrderStatuses(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAvailableOrderStatuses(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder getCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder
			getCommerceOrderByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrderByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrders(
			groupId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(long groupId, int[] orderStatuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrders(groupId, orderStatuses);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, int[] orderStatuses, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrders(
			groupId, orderStatuses, start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long groupId, long commerceAccountId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrders(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	public static int getCommerceOrdersCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrdersCount(groupId);
	}

	public static int getCommerceOrdersCount(
			long groupId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrdersCount(groupId, commerceAccountId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPendingCommerceOrders(
				long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPendingCommerceOrders(
			companyId, groupId, start, end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPendingCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPendingCommerceOrders(
			groupId, commerceAccountId, keywords, start, end);
	}

	public static long getPendingCommerceOrdersCount(
			long companyId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPendingCommerceOrdersCount(companyId, groupId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static int getPendingCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPendingCommerceOrdersCount(
			groupId, commerceAccountId, keywords);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPlacedCommerceOrders(
				long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacedCommerceOrders(
			companyId, groupId, start, end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static java.util.List<com.liferay.commerce.model.CommerceOrder>
			getPlacedCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacedCommerceOrders(
			groupId, commerceAccountId, keywords, start, end);
	}

	public static long getPlacedCommerceOrdersCount(
			long companyId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacedCommerceOrdersCount(companyId, groupId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static int getPlacedCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacedCommerceOrdersCount(
			groupId, commerceAccountId, keywords);
	}

	public static void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().mergeGuestCommerceOrder(
			guestCommerceOrderId, userCommerceOrderId, commerceContext,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
			long commerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().reorderCommerceOrder(
			commerceOrderId, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().submitCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder updateBillingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateBillingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
			java.math.BigDecimal total, String advanceStatus,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
			java.math.BigDecimal total, String advanceStatus,
			String externalReferenceCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, externalReferenceCode, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder
			updateCommerceOrderPrices(
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

		return getService().updateCommerceOrderPrices(
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

	public static com.liferay.commerce.model.CommerceOrder
			updateCommercePaymentMethodKey(
				long commerceOrderId, String commercePaymentMethodKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommercePaymentMethodKey(
			commerceOrderId, commercePaymentMethodKey);
	}

	public static com.liferay.commerce.model.CommerceOrder updateCustomFields(
			long commerceOrderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCustomFields(commerceOrderId, serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateInfo(
			long commerceOrderId, String printedNote,
			int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
			int requestedDeliveryDateYear, int requestedDeliveryDateHour,
			int requestedDeliveryDateMinute,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateInfo(
			commerceOrderId, printedNote, requestedDeliveryDateMonth,
			requestedDeliveryDateDay, requestedDeliveryDateYear,
			requestedDeliveryDateHour, requestedDeliveryDateMinute,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateOrderStatus(
			long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateOrderStatus(commerceOrderId, orderStatus);
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
			long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePaymentStatus(commerceOrderId, paymentStatus);
	}

	public static com.liferay.commerce.model.CommerceOrder
			updatePaymentStatusAndTransactionId(
				long commerceOrderId, int paymentStatus, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePaymentStatusAndTransactionId(
			commerceOrderId, paymentStatus, transactionId);
	}

	public static com.liferay.commerce.model.CommerceOrder
			updatePurchaseOrderNumber(
				long commerceOrderId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePurchaseOrderNumber(
			commerceOrderId, purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder
			updateShippingAddress(
				long commerceOrderId, String name, String description,
				String street1, String street2, String street3, String city,
				String zip, long commerceRegionId, long commerceCountryId,
				String phoneNumber,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateShippingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateTransactionId(
			long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTransactionId(commerceOrderId, transactionId);
	}

	public static com.liferay.commerce.model.CommerceOrder updateUser(
			long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateUser(commerceOrderId, userId);
	}

	public static com.liferay.commerce.model.CommerceOrder upsertCommerceOrder(
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

		return getService().upsertCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			advanceStatus, externalReferenceCode, commerceContext,
			serviceContext);
	}

	public static CommerceOrderService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceOrderService, CommerceOrderService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceOrderService.class);

		ServiceTracker<CommerceOrderService, CommerceOrderService>
			serviceTracker =
				new ServiceTracker<CommerceOrderService, CommerceOrderService>(
					bundle.getBundleContext(), CommerceOrderService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}