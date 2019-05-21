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

import com.liferay.commerce.service.CommerceWarehouseServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceWarehouseServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.model.CommerceWarehouseSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.model.CommerceWarehouse</code>, that is translated to a
 * <code>com.liferay.commerce.model.CommerceWarehouseSoap</code>. Methods that SOAP
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
 * @see CommerceWarehouseServiceHttp
 * @generated
 */
@ProviderType
public class CommerceWarehouseServiceSoap {

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			addCommerceWarehouse(
				String name, String description, boolean active, String street1,
				String street2, String street3, String city, String zip,
				long commerceRegionId, long commerceCountryId, double latitude,
				double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.addCommerceWarehouse(
					name, description, active, street1, street2, street3, city,
					zip, commerceRegionId, commerceCountryId, latitude,
					longitude, serviceContext);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceWarehouse(long commerceWarehouseId)
		throws RemoteException {

		try {
			CommerceWarehouseServiceUtil.deleteCommerceWarehouse(
				commerceWarehouseId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			fetchDefaultCommerceWarehouse(long groupId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.fetchDefaultCommerceWarehouse(
					groupId);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			geolocateCommerceWarehouse(long commerceWarehouseId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.geolocateCommerceWarehouse(
					commerceWarehouseId);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			getCommerceWarehouse(long commerceWarehouseId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.getCommerceWarehouse(
					commerceWarehouseId);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[]
			getCommerceWarehouses(
				long groupId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue =
					CommerceWarehouseServiceUtil.getCommerceWarehouses(
						groupId, active, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[]
			getCommerceWarehouses(
				long groupId, boolean active, long commerceCountryId,
				boolean primary, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue =
					CommerceWarehouseServiceUtil.getCommerceWarehouses(
						groupId, active, commerceCountryId, primary, start, end,
						orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[]
			getCommerceWarehouses(
				long groupId, boolean active, long commerceCountryId, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue =
					CommerceWarehouseServiceUtil.getCommerceWarehouses(
						groupId, active, commerceCountryId, start, end,
						orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[]
			getCommerceWarehouses(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue =
					CommerceWarehouseServiceUtil.getCommerceWarehouses(
						groupId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[]
			getCommerceWarehouses(
				long groupId, long commerceCountryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue =
					CommerceWarehouseServiceUtil.getCommerceWarehouses(
						groupId, commerceCountryId, start, end,
						orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceWarehousesCount(long groupId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceWarehouseServiceUtil.getCommerceWarehousesCount(
					groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceWarehousesCount(long groupId, boolean active)
		throws RemoteException {

		try {
			int returnValue =
				CommerceWarehouseServiceUtil.getCommerceWarehousesCount(
					groupId, active);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceWarehousesCount(
			long groupId, boolean active, long commerceCountryId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceWarehouseServiceUtil.getCommerceWarehousesCount(
					groupId, active, commerceCountryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceWarehousesCount(
			long groupId, long commerceCountryId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceWarehouseServiceUtil.getCommerceWarehousesCount(
					groupId, commerceCountryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap[] search(
			long groupId, String keywords, boolean all, long commerceCountryId,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceWarehouse>
					orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceWarehouse>
				returnValue = CommerceWarehouseServiceUtil.search(
					groupId, keywords, all, commerceCountryId, start, end,
					orderByComparator);

			return com.liferay.commerce.model.CommerceWarehouseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(
			long groupId, String keywords, Boolean active,
			long commerceCountryId)
		throws RemoteException {

		try {
			int returnValue = CommerceWarehouseServiceUtil.searchCount(
				groupId, keywords, active, commerceCountryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap setActive(
			long commerceWarehouseId, boolean active)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.setActive(
					commerceWarehouseId, active);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			updateCommerceWarehouse(
				long commerceWarehouseId, String name, String description,
				boolean active, String street1, String street2, String street3,
				String city, String zip, long commerceRegionId,
				long commerceCountryId, double latitude, double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.updateCommerceWarehouse(
					commerceWarehouseId, name, description, active, street1,
					street2, street3, city, zip, commerceRegionId,
					commerceCountryId, latitude, longitude, serviceContext);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouseSoap
			updateDefaultCommerceWarehouse(
				String name, String street1, String street2, String street3,
				String city, String zip, long commerceRegionId,
				long commerceCountryId, double latitude, double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceWarehouse returnValue =
				CommerceWarehouseServiceUtil.updateDefaultCommerceWarehouse(
					name, street1, street2, street3, city, zip,
					commerceRegionId, commerceCountryId, latitude, longitude,
					serviceContext);

			return com.liferay.commerce.model.CommerceWarehouseSoap.toSoapModel(
				returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceWarehouseServiceSoap.class);

}