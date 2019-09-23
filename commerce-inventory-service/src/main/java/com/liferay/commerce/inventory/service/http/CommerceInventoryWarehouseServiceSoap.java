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

package com.liferay.commerce.inventory.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceInventoryWarehouseServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouse</code>, that is translated to a
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap</code>. Methods that SOAP
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
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseServiceHttp
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceSoap {

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				addCommerceInventoryWarehouse(
					String name, String description, boolean active,
					String street1, String street2, String street3, String city,
					String zip, String commerceRegionCode,
					String commerceCountryCode, double latitude,
					double longitude, String externalReferenceCode,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						addCommerceInventoryWarehouse(
							name, description, active, street1, street2,
							street3, city, zip, commerceRegionCode,
							commerceCountryCode, latitude, longitude,
							externalReferenceCode, serviceContext);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				fetchByExternalReferenceCode(
					long companyId, String externalReferenceCode)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						fetchByExternalReferenceCode(
							companyId, externalReferenceCode);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				deleteCommerceInventoryWarehouse(
					long commerceInventoryWarehouseId)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						deleteCommerceInventoryWarehouse(
							commerceInventoryWarehouseId);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				geolocateCommerceInventoryWarehouse(
					long commerceInventoryWarehouseId, double latitude,
					double longitude)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						geolocateCommerceInventoryWarehouse(
							commerceInventoryWarehouseId, latitude, longitude);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				getCommerceInventoryWarehouse(long commerceInventoryWarehouseId)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						getCommerceInventoryWarehouse(
							commerceInventoryWarehouseId);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap[]
				getCommerceInventoryWarehouses(
					long companyId, boolean active, String commerceCountryCode,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.inventory.model.
							CommerceInventoryWarehouse> orderByComparator)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse> returnValue =
						CommerceInventoryWarehouseServiceUtil.
							getCommerceInventoryWarehouses(
								companyId, active, commerceCountryCode, start,
								end, orderByComparator);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap[]
				getCommerceInventoryWarehouses(
					long companyId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.inventory.model.
							CommerceInventoryWarehouse> orderByComparator)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse> returnValue =
						CommerceInventoryWarehouseServiceUtil.
							getCommerceInventoryWarehouses(
								companyId, start, end, orderByComparator);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap[]
				getCommerceInventoryWarehouses(
					long companyId, long groupId, boolean active)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse> returnValue =
						CommerceInventoryWarehouseServiceUtil.
							getCommerceInventoryWarehouses(
								companyId, groupId, active);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceInventoryWarehousesCount(long companyId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceInventoryWarehouseServiceUtil.
					getCommerceInventoryWarehousesCount(companyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceInventoryWarehousesCount(
			long companyId, boolean active, String commerceCountryCode)
		throws RemoteException {

		try {
			int returnValue =
				CommerceInventoryWarehouseServiceUtil.
					getCommerceInventoryWarehousesCount(
						companyId, active, commerceCountryCode);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap[]
				searchCommerceInventoryWarehouses(
					long companyId, Boolean active, String commerceCountryCode,
					String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse> returnValue =
						CommerceInventoryWarehouseServiceUtil.
							searchCommerceInventoryWarehouses(
								companyId, active, commerceCountryCode,
								keywords, start, end, sort);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCommerceInventoryWarehousesCount(
			long companyId, Boolean active, String commerceCountryCode,
			String keywords)
		throws RemoteException {

		try {
			int returnValue =
				CommerceInventoryWarehouseServiceUtil.
					searchCommerceInventoryWarehousesCount(
						companyId, active, commerceCountryCode, keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				setActive(long commerceInventoryWarehouseId, boolean active)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue = CommerceInventoryWarehouseServiceUtil.setActive(
					commerceInventoryWarehouseId, active);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
				updateCommerceInventoryWarehouse(
					long commerceInventoryWarehouseId, String name,
					String description, boolean active, String street1,
					String street2, String street3, String city, String zip,
					String commerceRegionCode, String commerceCountryCode,
					double latitude, double longitude,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				returnValue =
					CommerceInventoryWarehouseServiceUtil.
						updateCommerceInventoryWarehouse(
							commerceInventoryWarehouseId, name, description,
							active, street1, street2, street3, city, zip,
							commerceRegionCode, commerceCountryCode, latitude,
							longitude, serviceContext);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseServiceSoap.class);

}