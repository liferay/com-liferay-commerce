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

import com.liferay.commerce.service.CommerceOrderServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceOrderServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.model.CommerceOrderSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.model.CommerceOrder</code>, that is translated to a
 * <code>com.liferay.commerce.model.CommerceOrderSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
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
 * @generated
 */
public class CommerceOrderServiceSoap {

	public static com.liferay.commerce.model.CommerceOrderSoap addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.addCommerceOrder(
					userId, groupId, commerceAccountId, commerceCurrencyId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
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
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.addCommerceOrder(
					groupId, commerceAccountId, commerceCurrencyId,
					shippingAddressId, purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap addCommerceOrder(
			long groupId, long commerceAccountId, long shippingAddressId,
			String purchaseOrderNumber)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.addCommerceOrder(
					groupId, commerceAccountId, shippingAddressId,
					purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap applyCouponCode(
			long commerceOrderId, String couponCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.applyCouponCode(
					commerceOrderId, couponCode, commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			approveCommerceOrder(long commerceOrderId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.approveCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			checkoutCommerceOrder(
				long commerceOrderId,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.checkoutCommerceOrder(
					commerceOrderId, commerceContext, serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
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

	public static com.liferay.commerce.model.CommerceOrderSoap
			executeWorkflowTransition(
				long commerceOrderId, long workflowTaskId,
				String transitionName, String comment)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.executeWorkflowTransition(
					commerceOrderId, workflowTaskId, transitionName, comment);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.fetchByExternalReferenceCode(
					companyId, externalReferenceCode);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			fetchCommerceOrder(long commerceOrderId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.fetchCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			fetchCommerceOrder(
				long commerceAccountId, long groupId, int orderStatus)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.fetchCommerceOrder(
					commerceAccountId, groupId, orderStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			fetchCommerceOrder(String uuid, long groupId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.fetchCommerceOrder(uuid, groupId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int[] getAvailableOrderStatuses(long commerceOrderId)
		throws RemoteException {

		try {
			int[] returnValue =
				CommerceOrderServiceUtil.getAvailableOrderStatuses(
					commerceOrderId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap getCommerceOrder(
			long commerceOrderId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.getCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			getCommerceOrderByUuidAndGroupId(String uuid, long groupId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.getCommerceOrderByUuidAndGroupId(
					uuid, groupId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getCommerceOrders(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getCommerceOrders(
					groupId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getCommerceOrders(long groupId, int[] orderStatuses)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getCommerceOrders(
					groupId, orderStatuses);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getCommerceOrders(
				long groupId, int[] orderStatuses, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getCommerceOrders(
					groupId, orderStatuses, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getCommerceOrders(
				long groupId, long commerceAccountId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceOrder>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getCommerceOrders(
					groupId, commerceAccountId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrdersCount(long groupId)
		throws RemoteException {

		try {
			int returnValue = CommerceOrderServiceUtil.getCommerceOrdersCount(
				groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrdersCount(
			long groupId, long commerceAccountId)
		throws RemoteException {

		try {
			int returnValue = CommerceOrderServiceUtil.getCommerceOrdersCount(
				groupId, commerceAccountId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getPendingCommerceOrders(
				long companyId, long groupId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getPendingCommerceOrders(
					companyId, groupId, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getPendingCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getPendingCommerceOrders(
					groupId, commerceAccountId, keywords, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long getPendingCommerceOrdersCount(
			long companyId, long groupId)
		throws RemoteException {

		try {
			long returnValue =
				CommerceOrderServiceUtil.getPendingCommerceOrdersCount(
					companyId, groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static int getPendingCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderServiceUtil.getPendingCommerceOrdersCount(
					groupId, commerceAccountId, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getPlacedCommerceOrders(
				long companyId, long groupId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getPlacedCommerceOrders(
					companyId, groupId, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CommerceOrderSoap[]
			getPlacedCommerceOrders(
				long groupId, long commerceAccountId, String keywords,
				int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrder>
				returnValue = CommerceOrderServiceUtil.getPlacedCommerceOrders(
					groupId, commerceAccountId, keywords, start, end);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long getPlacedCommerceOrdersCount(
			long companyId, long groupId)
		throws RemoteException {

		try {
			long returnValue =
				CommerceOrderServiceUtil.getPlacedCommerceOrdersCount(
					companyId, groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static int getPlacedCommerceOrdersCount(
			long groupId, long commerceAccountId, String keywords)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderServiceUtil.getPlacedCommerceOrdersCount(
					groupId, commerceAccountId, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			CommerceOrderServiceUtil.mergeGuestCommerceOrder(
				guestCommerceOrderId, userCommerceOrderId, commerceContext,
				serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			reorderCommerceOrder(
				long commerceOrderId,
				com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.reorderCommerceOrder(
					commerceOrderId, commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			submitCommerceOrder(long commerceOrderId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.submitCommerceOrder(commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateBillingAddress(
				long commerceOrderId, String name, String description,
				String street1, String street2, String street3, String city,
				String zip, long commerceRegionId, long commerceCountryId,
				String phoneNumber,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateBillingAddress(
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateCommerceOrder(
				long commerceOrderId, long billingAddressId,
				long shippingAddressId, String commercePaymentMethodKey,
				long commerceShippingMethodId, String shippingOptionName,
				String purchaseOrderNumber, java.math.BigDecimal subtotal,
				java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
				String advanceStatus,
				com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateCommerceOrder(
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus, commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateCommerceOrder(
				long commerceOrderId, long billingAddressId,
				long shippingAddressId, String commercePaymentMethodKey,
				long commerceShippingMethodId, String shippingOptionName,
				String purchaseOrderNumber, java.math.BigDecimal subtotal,
				java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
				String advanceStatus, String externalReferenceCode,
				com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateCommerceOrder(
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus, externalReferenceCode,
					commerceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
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
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateCommerceOrderPrices(
					commerceOrderId, subtotal, subtotalDiscountAmount,
					subtotalDiscountPercentageLevel1,
					subtotalDiscountPercentageLevel2,
					subtotalDiscountPercentageLevel3,
					subtotalDiscountPercentageLevel4, shippingAmount,
					shippingDiscountAmount, shippingDiscountPercentageLevel1,
					shippingDiscountPercentageLevel2,
					shippingDiscountPercentageLevel3,
					shippingDiscountPercentageLevel4, taxAmount, total,
					totalDiscountAmount, totalDiscountPercentageLevel1,
					totalDiscountPercentageLevel2,
					totalDiscountPercentageLevel3,
					totalDiscountPercentageLevel4);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateCommercePaymentMethodKey(
				long commerceOrderId, String commercePaymentMethodKey)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateCommercePaymentMethodKey(
					commerceOrderId, commercePaymentMethodKey);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateCustomFields(
				long commerceOrderId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateCustomFields(
					commerceOrderId, serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateInfo(
			long commerceOrderId, String printedNote,
			int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
			int requestedDeliveryDateYear, int requestedDeliveryDateHour,
			int requestedDeliveryDateMinute,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateInfo(
					commerceOrderId, printedNote, requestedDeliveryDateMonth,
					requestedDeliveryDateDay, requestedDeliveryDateYear,
					requestedDeliveryDateHour, requestedDeliveryDateMinute,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateOrderStatus(long commerceOrderId, int orderStatus)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateOrderStatus(
					commerceOrderId, orderStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updatePaymentStatus(long commerceOrderId, int paymentStatus)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updatePaymentStatus(
					commerceOrderId, paymentStatus);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updatePaymentStatusAndTransactionId(
				long commerceOrderId, int paymentStatus, String transactionId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updatePaymentStatusAndTransactionId(
					commerceOrderId, paymentStatus, transactionId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updatePurchaseOrderNumber(
				long commerceOrderId, String purchaseOrderNumber)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updatePurchaseOrderNumber(
					commerceOrderId, purchaseOrderNumber);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateShippingAddress(
				long commerceOrderId, String name, String description,
				String street1, String street2, String street3, String city,
				String zip, long commerceRegionId, long commerceCountryId,
				String phoneNumber,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateShippingAddress(
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			updateTransactionId(long commerceOrderId, String transactionId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateTransactionId(
					commerceOrderId, transactionId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap updateUser(
			long commerceOrderId, long userId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.updateUser(commerceOrderId, userId);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderSoap
			upsertCommerceOrder(
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
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrder returnValue =
				CommerceOrderServiceUtil.upsertCommerceOrder(
					userId, groupId, commerceAccountId, commerceCurrencyId,
					billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, paymentStatus, orderStatus,
					advanceStatus, externalReferenceCode, commerceContext,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceOrderServiceSoap.class);

}