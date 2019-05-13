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
 * {@link CommerceInventoryWarehouseServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.inventory.model.CommerceInventoryWarehouse}, that is translated to a
 * {@link com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap}. Methods that SOAP cannot
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
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseServiceHttp
 * @see com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap
 * @see CommerceInventoryWarehouseServiceUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceSoap {
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap addCommerceWarehouse(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.addCommerceWarehouse(name,
					description, active, street1, street2, street3, city, zip,
					commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap addCommerceWarehouseAndGroupRel(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.addCommerceWarehouseAndGroupRel(name,
					description, active, street1, street2, street3, city, zip,
					commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap deleteCommerceWarehouse(
		long commerceWarehouseId) throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.deleteCommerceWarehouse(commerceWarehouseId);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap fetchDefaultCommerceWarehouse(
		long groupId) throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.fetchDefaultCommerceWarehouse(groupId);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.geolocateCommerceWarehouse(commerceWarehouseId,
					latitude, longitude);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap getCommerceWarehouse(
		long commerceWarehouseId) throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.getCommerceWarehouse(commerceWarehouseId);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap setActive(
		long commerceWarehouseId, boolean active) throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.setActive(commerceWarehouseId,
					active);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.updateCommerceWarehouse(commerceWarehouseId,
					name, description, active, street1, street2, street3, city,
					zip, commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap updateDefaultCommerceWarehouse(
		String name, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouse returnValue =
				CommerceInventoryWarehouseServiceUtil.updateDefaultCommerceWarehouse(name,
					street1, street2, street3, city, zip, commerceRegionCode,
					commerceCountryCode, latitude, longitude, serviceContext);

			return com.liferay.commerce.inventory.model.CommerceInventoryWarehouseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceInventoryWarehouseServiceSoap.class);
}