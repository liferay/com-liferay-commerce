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

import com.liferay.commerce.service.CommerceShipmentServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceShipmentServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.model.CommerceShipmentSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.model.CommerceShipment</code>, that is translated to a
 * <code>com.liferay.commerce.model.CommerceShipmentSoap</code>. Methods that SOAP
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
 * @see CommerceShipmentServiceHttp
 * @generated
 */
public class CommerceShipmentServiceSoap {

	public static com.liferay.commerce.model.CommerceShipmentSoap
			addCommerceShipment(
				long commerceOrderId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceShipment returnValue =
				CommerceShipmentServiceUtil.addCommerceShipment(
					commerceOrderId, serviceContext);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceShipment(long commerceShipmentId)
		throws RemoteException {

		try {
			CommerceShipmentServiceUtil.deleteCommerceShipment(
				commerceShipmentId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceShipmentSoap
			getCommerceShipment(long commerceShipmentId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceShipment returnValue =
				CommerceShipmentServiceUtil.getCommerceShipment(
					commerceShipmentId);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceShipmentSoap[]
			getCommerceShipments(
				long companyId, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceShipment>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceShipment>
				returnValue = CommerceShipmentServiceUtil.getCommerceShipments(
					companyId, status, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceShipmentSoap[]
			getCommerceShipments(
				long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceShipment>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceShipment>
				returnValue = CommerceShipmentServiceUtil.getCommerceShipments(
					companyId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModels(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceShipmentsCount(long companyId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceShipmentServiceUtil.getCommerceShipmentsCount(
					companyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceShipmentsCount(long companyId, int status)
		throws RemoteException {

		try {
			int returnValue =
				CommerceShipmentServiceUtil.getCommerceShipmentsCount(
					companyId, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceShipmentSoap
			updateCommerceShipment(
				long commerceShipmentId, String carrier, String trackingNumber,
				int status, int shippingDateMonth, int shippingDateDay,
				int shippingDateYear, int shippingDateHour,
				int shippingDateMinute, int expectedDateMonth,
				int expectedDateDay, int expectedDateYear, int expectedDateHour,
				int expectedDateMinute)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceShipment returnValue =
				CommerceShipmentServiceUtil.updateCommerceShipment(
					commerceShipmentId, carrier, trackingNumber, status,
					shippingDateMonth, shippingDateDay, shippingDateYear,
					shippingDateHour, shippingDateMinute, expectedDateMonth,
					expectedDateDay, expectedDateYear, expectedDateHour,
					expectedDateMinute);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceShipmentSoap
			updateCommerceShipment(
				long commerceShipmentId, String name, String description,
				String street1, String street2, String street3, String city,
				String zip, long commerceRegionId, long commerceCountryId,
				String phoneNumber, String carrier, String trackingNumber,
				int status, int shippingDateMonth, int shippingDateDay,
				int shippingDateYear, int shippingDateHour,
				int shippingDateMinute, int expectedDateMonth,
				int expectedDateDay, int expectedDateYear, int expectedDateHour,
				int expectedDateMinute)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceShipment returnValue =
				CommerceShipmentServiceUtil.updateCommerceShipment(
					commerceShipmentId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, carrier, trackingNumber, status,
					shippingDateMonth, shippingDateDay, shippingDateYear,
					shippingDateHour, shippingDateMinute, expectedDateMonth,
					expectedDateDay, expectedDateYear, expectedDateHour,
					expectedDateMinute);

			return com.liferay.commerce.model.CommerceShipmentSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceShipmentServiceSoap.class);

}