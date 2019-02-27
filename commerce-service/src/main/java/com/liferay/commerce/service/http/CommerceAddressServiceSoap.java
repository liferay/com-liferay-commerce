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

import com.liferay.commerce.service.CommerceAddressServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceAddressServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.model.CommerceAddressSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.model.CommerceAddress}, that is translated to a
 * {@link com.liferay.commerce.model.CommerceAddressSoap}. Methods that SOAP cannot
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
 * @see CommerceAddressServiceHttp
 * @see com.liferay.commerce.model.CommerceAddressSoap
 * @see CommerceAddressServiceUtil
 * @generated
 */
@ProviderType
public class CommerceAddressServiceSoap {
	public static com.liferay.commerce.model.CommerceAddressSoap addCommerceAddress(
		String className, long classPK, String name, String description,
		String street1, String street2, String street3, String city,
		String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber, boolean defaultBilling, boolean defaultShipping,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceAddress returnValue = CommerceAddressServiceUtil.addCommerceAddress(className,
					classPK, name, description, street1, street2, street3,
					city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, defaultBilling, defaultShipping, serviceContext);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceAddress(long commerceAddressId)
		throws RemoteException {
		try {
			CommerceAddressServiceUtil.deleteCommerceAddress(commerceAddressId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap fetchCommerceAddress(
		long commerceAddressId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceAddress returnValue = CommerceAddressServiceUtil.fetchCommerceAddress(commerceAddressId);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap getCommerceAddress(
		long commerceAddressId) throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceAddress returnValue = CommerceAddressServiceUtil.getCommerceAddress(commerceAddressId);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap[] getCommerceAddresses(
		long groupId, String className, long classPK) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceAddress> returnValue =
				CommerceAddressServiceUtil.getCommerceAddresses(groupId,
					className, classPK);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap[] getCommerceAddresses(
		long groupId, String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddress> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceAddress> returnValue =
				CommerceAddressServiceUtil.getCommerceAddresses(groupId,
					className, classPK, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap[] getCommerceAddresses(
		String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddress> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.model.CommerceAddress> returnValue =
				CommerceAddressServiceUtil.getCommerceAddresses(className,
					classPK, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceAddressesCount(long groupId, String className,
		long classPK) throws RemoteException {
		try {
			int returnValue = CommerceAddressServiceUtil.getCommerceAddressesCount(groupId,
					className, classPK);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceAddressesCount(String className, long classPK)
		throws RemoteException {
		try {
			int returnValue = CommerceAddressServiceUtil.getCommerceAddressesCount(className,
					classPK);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceAddressSoap updateCommerceAddress(
		long commerceAddressId, String name, String description,
		String street1, String street2, String street3, String city,
		String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber, boolean defaultBilling, boolean defaultShipping,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.model.CommerceAddress returnValue = CommerceAddressServiceUtil.updateCommerceAddress(commerceAddressId,
					name, description, street1, street2, street3, city, zip,
					commerceRegionId, commerceCountryId, phoneNumber,
					defaultBilling, defaultShipping, serviceContext);

			return com.liferay.commerce.model.CommerceAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceAddressServiceSoap.class);
}