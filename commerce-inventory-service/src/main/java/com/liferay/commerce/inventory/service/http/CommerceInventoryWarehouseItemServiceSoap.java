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

import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceInventoryWarehouseItemServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem</code>, that is translated to a
 * <code>com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap</code>. Methods that SOAP
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
 * @see CommerceInventoryWarehouseItemServiceHttp
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseItemServiceSoap {

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap
				addCommerceInventoryWarehouseItem(
					long userId, long commerceInventoryWarehouseId, String sku,
					int quantity)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				returnValue =
					CommerceInventoryWarehouseItemServiceUtil.
						addCommerceInventoryWarehouseItem(
							userId, commerceInventoryWarehouseId, sku,
							quantity);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceInventoryWarehouseItem(
			long commerceInventoryWarehouseItemId)
		throws RemoteException {

		try {
			CommerceInventoryWarehouseItemServiceUtil.
				deleteCommerceInventoryWarehouseItem(
					commerceInventoryWarehouseItemId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap
				fetchCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseId, String sku)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				returnValue =
					CommerceInventoryWarehouseItemServiceUtil.
						fetchCommerceInventoryWarehouseItem(
							commerceInventoryWarehouseId, sku);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap
				getCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseItemId)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				returnValue =
					CommerceInventoryWarehouseItemServiceUtil.
						getCommerceInventoryWarehouseItem(
							commerceInventoryWarehouseItemId);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseItemSoap[]
					getCommerceInventoryWarehouseItems(
						long commerceInventoryWarehouseId, int start, int end)
				throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouseItem> returnValue =
						CommerceInventoryWarehouseItemServiceUtil.
							getCommerceInventoryWarehouseItems(
								commerceInventoryWarehouseId, start, end);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceInventoryWarehouseItemsCount(
			long commerceInventoryWarehouseId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceInventoryWarehouseItemServiceUtil.
					getCommerceInventoryWarehouseItemsCount(
						commerceInventoryWarehouseId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap
				updateCommerceInventoryWarehouseItem(
					long commerceInventoryWarehouseItemId, int quantity)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				returnValue =
					CommerceInventoryWarehouseItemServiceUtil.
						updateCommerceInventoryWarehouseItem(
							commerceInventoryWarehouseItemId, quantity);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItemSoap
				upsertCommerceInventoryWarehouseItem(
					long userId, long commerceInventoryWarehouseId, String sku,
					int quantity)
			throws RemoteException {

		try {
			com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem
				returnValue =
					CommerceInventoryWarehouseItemServiceUtil.
						upsertCommerceInventoryWarehouseItem(
							userId, commerceInventoryWarehouseId, sku,
							quantity);

			return com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseItemSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseItemServiceSoap.class);

}