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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceInventoryWarehouseServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseServiceSoap
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceHttp {

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				addCommerceInventoryWarehouse(
					HttpPrincipal httpPrincipal, String name,
					String description, boolean active, String street1,
					String street2, String street3, String city, String zip,
					String commerceRegionCode, String commerceCountryCode,
					double latitude, double longitude,
					String externalReferenceCode,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"addCommerceInventoryWarehouse",
				_addCommerceInventoryWarehouseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, active, street1, street2, street3,
				city, zip, commerceRegionCode, commerceCountryCode, latitude,
				longitude, externalReferenceCode, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				fetchByExternalReferenceCode(
					HttpPrincipal httpPrincipal, long companyId,
					String externalReferenceCode)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"fetchByExternalReferenceCode",
				_fetchByExternalReferenceCodeParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				deleteCommerceInventoryWarehouse(
					HttpPrincipal httpPrincipal,
					long commerceInventoryWarehouseId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"deleteCommerceInventoryWarehouse",
				_deleteCommerceInventoryWarehouseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				geolocateCommerceInventoryWarehouse(
					HttpPrincipal httpPrincipal,
					long commerceInventoryWarehouseId, double latitude,
					double longitude)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"geolocateCommerceInventoryWarehouse",
				_geolocateCommerceInventoryWarehouseParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, latitude, longitude);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				getCommerceInventoryWarehouse(
					HttpPrincipal httpPrincipal,
					long commerceInventoryWarehouseId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehouse",
				_getCommerceInventoryWarehouseParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse>
				getCommerceInventoryWarehouses(
					HttpPrincipal httpPrincipal, long companyId, boolean active,
					String commerceCountryCode, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.inventory.model.
							CommerceInventoryWarehouse> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehouses",
				_getCommerceInventoryWarehousesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, active, commerceCountryCode, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse>
				getCommerceInventoryWarehouses(
					HttpPrincipal httpPrincipal, long companyId, int start,
					int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.inventory.model.
							CommerceInventoryWarehouse> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehouses",
				_getCommerceInventoryWarehousesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse>
				getCommerceInventoryWarehouses(
					HttpPrincipal httpPrincipal, long companyId, long groupId,
					boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehouses",
				_getCommerceInventoryWarehousesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceInventoryWarehousesCount(
			HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehousesCount",
				_getCommerceInventoryWarehousesCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceInventoryWarehousesCount(
			HttpPrincipal httpPrincipal, long companyId, boolean active,
			String commerceCountryCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"getCommerceInventoryWarehousesCount",
				_getCommerceInventoryWarehousesCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, active, commerceCountryCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse>
				searchCommerceInventoryWarehouses(
					HttpPrincipal httpPrincipal, long companyId, Boolean active,
					String commerceCountryCode, String keywords, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"searchCommerceInventoryWarehouses",
				_searchCommerceInventoryWarehousesParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, active, commerceCountryCode, keywords,
				start, end, sort);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCommerceInventoryWarehousesCount(
			HttpPrincipal httpPrincipal, long companyId, Boolean active,
			String commerceCountryCode, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"searchCommerceInventoryWarehousesCount",
				_searchCommerceInventoryWarehousesCountParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, active, commerceCountryCode, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				setActive(
					HttpPrincipal httpPrincipal,
					long commerceInventoryWarehouseId, boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class, "setActive",
				_setActiveParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouse
				updateCommerceInventoryWarehouse(
					HttpPrincipal httpPrincipal,
					long commerceInventoryWarehouseId, String name,
					String description, boolean active, String street1,
					String street2, String street3, String city, String zip,
					String commerceRegionCode, String commerceCountryCode,
					double latitude, double longitude,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseServiceUtil.class,
				"updateCommerceInventoryWarehouse",
				_updateCommerceInventoryWarehouseParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, name, description,
				active, street1, street2, street3, city, zip,
				commerceRegionCode, commerceCountryCode, latitude, longitude,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseServiceHttp.class);

	private static final Class<?>[]
		_addCommerceInventoryWarehouseParameterTypes0 = new Class[] {
			String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, double.class, double.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_fetchByExternalReferenceCodeParameterTypes1 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[]
		_deleteCommerceInventoryWarehouseParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_geolocateCommerceInventoryWarehouseParameterTypes3 = new Class[] {
			long.class, double.class, double.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehouseParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehousesParameterTypes5 = new Class[] {
			long.class, boolean.class, String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehousesParameterTypes6 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehousesParameterTypes7 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehousesCountParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getCommerceInventoryWarehousesCountParameterTypes9 = new Class[] {
			long.class, boolean.class, String.class
		};
	private static final Class<?>[]
		_searchCommerceInventoryWarehousesParameterTypes10 = new Class[] {
			long.class, Boolean.class, String.class, String.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[]
		_searchCommerceInventoryWarehousesCountParameterTypes11 = new Class[] {
			long.class, Boolean.class, String.class, String.class
		};
	private static final Class<?>[] _setActiveParameterTypes12 = new Class[] {
		long.class, boolean.class
	};
	private static final Class<?>[]
		_updateCommerceInventoryWarehouseParameterTypes13 = new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}