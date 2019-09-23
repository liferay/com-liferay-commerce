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

import com.liferay.commerce.service.CommerceOrderItemServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceOrderItemServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.model.CommerceOrderItemSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.model.CommerceOrderItem</code>, that is translated to a
 * <code>com.liferay.commerce.model.CommerceOrderItemSoap</code>. Methods that SOAP
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
 * @see CommerceOrderItemServiceHttp
 * @generated
 */
public class CommerceOrderItemServiceSoap {

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			addCommerceOrderItem(
				long commerceOrderId, long cpInstanceId, int quantity,
				int shippedQuantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.addCommerceOrderItem(
					commerceOrderId, cpInstanceId, quantity, shippedQuantity,
					json, commerceContext, serviceContext);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceOrderItem(long commerceOrderItemId)
		throws RemoteException {

		try {
			CommerceOrderItemServiceUtil.deleteCommerceOrderItem(
				commerceOrderItemId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceOrderItem(
			long commerceOrderItemId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws RemoteException {

		try {
			CommerceOrderItemServiceUtil.deleteCommerceOrderItem(
				commerceOrderItemId, commerceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.fetchByExternalReferenceCode(
					companyId, externalReferenceCode);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			fetchCommerceOrderItem(long commerceOrderItemId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.fetchCommerceOrderItem(
					commerceOrderItemId);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap[]
			getAvailableForShipmentCommerceOrderItems(long commerceOrderId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrderItem>
				returnValue =
					CommerceOrderItemServiceUtil.
						getAvailableForShipmentCommerceOrderItems(
							commerceOrderId);

			return com.liferay.commerce.model.CommerceOrderItemSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceInventoryWarehouseItemQuantity(
			long commerceOrderItemId, long commerceInventoryWarehouseId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderItemServiceUtil.
					getCommerceInventoryWarehouseItemQuantity(
						commerceOrderItemId, commerceInventoryWarehouseId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			getCommerceOrderItem(long commerceOrderItemId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.getCommerceOrderItem(
					commerceOrderItemId);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap[]
			getCommerceOrderItems(long commerceOrderId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceOrderItem>
				returnValue =
					CommerceOrderItemServiceUtil.getCommerceOrderItems(
						commerceOrderId, start, end);

			return com.liferay.commerce.model.CommerceOrderItemSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrderItemsCount(long commerceOrderId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderItemServiceUtil.getCommerceOrderItemsCount(
					commerceOrderId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrderItemsCount(
			long commerceOrderId, long cpInstanceId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderItemServiceUtil.getCommerceOrderItemsCount(
					commerceOrderId, cpInstanceId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceOrderItemsQuantity(long commerceOrderId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceOrderItemServiceUtil.getCommerceOrderItemsQuantity(
					commerceOrderId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			updateCommerceOrderItem(
				long commerceOrderItemId, int quantity,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.updateCommerceOrderItem(
					commerceOrderItemId, quantity, commerceContext,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			updateCommerceOrderItem(
				long commerceOrderItemId, int quantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.updateCommerceOrderItem(
					commerceOrderItemId, quantity, json, commerceContext,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			updateCommerceOrderItemInfo(
				long commerceOrderItemId, String deliveryGroup,
				long shippingAddressId, String printedNote,
				int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
				int requestedDeliveryDateYear, int requestedDeliveryDateHour,
				int requestedDeliveryDateMinute,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.updateCommerceOrderItemInfo(
					commerceOrderItemId, deliveryGroup, shippingAddressId,
					printedNote, requestedDeliveryDateMonth,
					requestedDeliveryDateDay, requestedDeliveryDateYear,
					requestedDeliveryDateHour, requestedDeliveryDateMinute,
					serviceContext);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			updateCommerceOrderItemPrices(
				long commerceOrderItemId, java.math.BigDecimal unitPrice,
				java.math.BigDecimal promoPrice,
				java.math.BigDecimal discountAmount,
				java.math.BigDecimal finalPrice,
				java.math.BigDecimal discountPercentageLevel1,
				java.math.BigDecimal discountPercentageLevel2,
				java.math.BigDecimal discountPercentageLevel3,
				java.math.BigDecimal discountPercentageLevel4)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.updateCommerceOrderItemPrices(
					commerceOrderItemId, unitPrice, promoPrice, discountAmount,
					finalPrice, discountPercentageLevel1,
					discountPercentageLevel2, discountPercentageLevel3,
					discountPercentageLevel4);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItemSoap
			upsertCommerceOrderItem(
				long commerceOrderId, long cpInstanceId, int quantity,
				int shippedQuantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceOrderItem returnValue =
				CommerceOrderItemServiceUtil.upsertCommerceOrderItem(
					commerceOrderId, cpInstanceId, quantity, shippedQuantity,
					json, commerceContext, serviceContext);

			return com.liferay.commerce.model.CommerceOrderItemSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemServiceSoap.class);

}