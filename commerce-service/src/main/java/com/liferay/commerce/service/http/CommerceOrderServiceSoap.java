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

package com.liferay.commerce.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.service.CommerceOrderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceOrderServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.model.CommerceOrderSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.model.CommerceOrder}, that is translated to a
 * {@link com.liferay.commerce.model.CommerceOrderSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderServiceHttp
 * @see com.liferay.commerce.model.CommerceOrderSoap
 * @see CommerceOrderServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrderServiceSoap {
	public static com.liferay.commerce.model.CommerceOrderSoap addCommerceOrder(
		long groupId, long userId, long commerceAccountId,
		long commerceCurrencyId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.addCommerceOrder(groupId,
					userId, commerceAccountId, commerceCurrencyId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap addCommerceOrder(
		long groupId, long commerceAccountId, long commerceCurrencyId,
		long shippingAddressId, String purchaseOrderNumber)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.addCommerceOrder(groupId,
					commerceAccountId, commerceCurrencyId, shippingAddressId,
					purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap addCommerceOrder(
		long groupId, long commerceAccountId, long shippingAddressId,
		String purchaseOrderNumber) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.addCommerceOrder(groupId,
					commerceAccountId, shippingAddressId, purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap approveCommerceOrder(
		long commerceOrderId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.approveCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap checkoutCommerceOrder(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.checkoutCommerceOrder(commerceOrderId,
					commerceContext, serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceOrder(long commerceOrderId)
		throws RemoteException {
		try {
			CommerceOrderServiceUtil.deleteCommerceOrder(commerceOrderId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap executeWorkflowTransition(
		long commerceOrderId, long workflowTaskId, String transitionName,
		String comment) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.executeWorkflowTransition(commerceOrderId,
					workflowTaskId, transitionName, comment);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.fetchByExternalReferenceCode(companyId,
					externalReferenceCode);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap fetchCommerceOrder(
		long commerceOrderId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.fetchCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap fetchCommerceOrder(
		long groupId, int orderStatus) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.fetchCommerceOrder(groupId,
					orderStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap fetchCommerceOrder(
		String uuid, long groupId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.fetchCommerceOrder(uuid,
					groupId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int[] getAvailableOrderStatuses(long commerceOrderId)
		throws RemoteException {
		try {
			int[] returnValue = CommerceOrderServiceUtil.getAvailableOrderStatuses(commerceOrderId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap getCommerceOrder(
		long commerceOrderId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.getCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap getCommerceOrderByUuidAndGroupId(
		String uuid, long groupId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.getCommerceOrderByUuidAndGroupId(uuid,
					groupId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getCommerceOrders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getCommerceOrders(groupId, start, end,
					orderByComparator);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getCommerceOrders(
		long groupId, int[] orderStatuses) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getCommerceOrders(groupId,
					orderStatuses);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getCommerceOrders(
		long groupId, long commerceAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getCommerceOrders(groupId,
					commerceAccountId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrdersCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = CommerceOrderServiceUtil.getCommerceOrdersCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrdersCount(long groupId,
		long commerceAccountId) throws RemoteException {
		try {
			int returnValue = CommerceOrderServiceUtil.getCommerceOrdersCount(groupId,
					commerceAccountId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getUserCommerceOrders(
		long groupId, long userId, int orderStatus, String keywords, int start,
		int end) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getUserCommerceOrders(groupId, userId,
					orderStatus, keywords, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getUserCommerceOrders(
		long groupId, long userId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getUserCommerceOrders(groupId, userId,
					orderStatus, excludeOrderStatus, keywords, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[] getUserCommerceOrders(
		long groupId, long userId, String keywords, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder> returnValue =
				CommerceOrderServiceUtil.getUserCommerceOrders(groupId, userId,
					keywords, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getUserCommerceOrdersCount(long groupId, long userId,
		int orderStatus, boolean excludeOrderStatus, String keywords)
		throws RemoteException {
		try {
			int returnValue = CommerceOrderServiceUtil.getUserCommerceOrdersCount(groupId,
					userId, orderStatus, excludeOrderStatus, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getUserCommerceOrdersCount(long groupId, long userId,
		int orderStatus, String keywords) throws RemoteException {
		try {
			int returnValue = CommerceOrderServiceUtil.getUserCommerceOrdersCount(groupId,
					userId, orderStatus, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getUserCommerceOrdersCount(long groupId, long userId,
		String keywords) throws RemoteException {
		try {
			int returnValue = CommerceOrderServiceUtil.getUserCommerceOrdersCount(groupId,
					userId, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void mergeGuestCommerceOrder(long guestCommerceOrderId,
		long userCommerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			CommerceOrderServiceUtil.mergeGuestCommerceOrder(guestCommerceOrderId,
				userCommerceOrderId, commerceContext, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap reorderCommerceOrder(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.reorderCommerceOrder(commerceOrderId,
					commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap submitCommerceOrder(
		long commerceOrderId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.submitCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateBillingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateBillingAddress(commerceOrderId,
					name, description, street1, street2, street3, city, zip,
					commerceRegionId, commerceCountryId, phoneNumber,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateCommerceOrder(commerceOrderId,
					billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus, commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateCommerceOrder(commerceOrderId,
					billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus,
					externalReferenceCode, commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateOrderStatus(
		long commerceOrderId, int orderStatus) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateOrderStatus(commerceOrderId,
					orderStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updatePaymentStatus(
		long commerceOrderId, int paymentStatus) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updatePaymentStatus(commerceOrderId,
					paymentStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updatePaymentStatusAndTransactionId(
		long commerceOrderId, int paymentStatus, String transactionId)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updatePaymentStatusAndTransactionId(commerceOrderId,
					paymentStatus, transactionId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updatePurchaseOrderNumber(
		long commerceOrderId, String purchaseOrderNumber)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updatePurchaseOrderNumber(commerceOrderId,
					purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateShippingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateShippingAddress(commerceOrderId,
					name, description, street1, street2, street3, city, zip,
					commerceRegionId, commerceCountryId, phoneNumber,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateTransactionId(
		long commerceOrderId, String transactionId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateTransactionId(commerceOrderId,
					transactionId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateUser(
		long commerceOrderId, long userId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceOrder returnValue = CommerceOrderServiceUtil.updateUser(commerceOrderId,
					userId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrderServiceSoap.class);
}