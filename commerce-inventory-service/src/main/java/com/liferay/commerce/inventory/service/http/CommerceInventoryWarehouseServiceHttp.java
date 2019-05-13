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
 * {@link CommerceInventoryWarehouseServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
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
 * @see HttpPrincipal
 * @see CommerceInventoryWarehouseServiceUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceHttp {
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouse(
		HttpPrincipal httpPrincipal, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"addCommerceWarehouse", _addCommerceWarehouseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, active, street1, street2, street3, city, zip,
					commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
		HttpPrincipal httpPrincipal, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"addCommerceWarehouseAndGroupRel",
					_addCommerceWarehouseAndGroupRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, active, street1, street2, street3, city, zip,
					commerceRegionCode, commerceCountryCode, latitude,
					longitude, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceWarehouse(
		HttpPrincipal httpPrincipal, long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"deleteCommerceWarehouse",
					_deleteCommerceWarehouseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceWarehouseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"fetchDefaultCommerceWarehouse",
					_fetchDefaultCommerceWarehouseParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse geolocateCommerceWarehouse(
		HttpPrincipal httpPrincipal, long commerceWarehouseId, double latitude,
		double longitude)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"geolocateCommerceWarehouse",
					_geolocateCommerceWarehouseParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceWarehouseId, latitude, longitude);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse getCommerceWarehouse(
		HttpPrincipal httpPrincipal, long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"getCommerceWarehouse", _getCommerceWarehouseParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceWarehouseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse setActive(
		HttpPrincipal httpPrincipal, long commerceWarehouseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"setActive", _setActiveParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceWarehouseId, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateCommerceWarehouse(
		HttpPrincipal httpPrincipal, long commerceWarehouseId, String name,
		String description, boolean active, String street1, String street2,
		String street3, String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"updateCommerceWarehouse",
					_updateCommerceWarehouseParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceWarehouseId, name, description, active, street1,
					street2, street3, city, zip, commerceRegionCode,
					commerceCountryCode, latitude, longitude, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateDefaultCommerceWarehouse(
		HttpPrincipal httpPrincipal, String name, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceInventoryWarehouseServiceUtil.class,
					"updateDefaultCommerceWarehouse",
					_updateDefaultCommerceWarehouseParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					street1, street2, street3, city, zip, commerceRegionCode,
					commerceCountryCode, latitude, longitude, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceInventoryWarehouseServiceHttp.class);
	private static final Class<?>[] _addCommerceWarehouseParameterTypes0 = new Class[] {
			String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class, String.class,
			String.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCommerceWarehouseAndGroupRelParameterTypes1 =
		new Class[] {
			String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class, String.class,
			String.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceWarehouseParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchDefaultCommerceWarehouseParameterTypes3 =
		new Class[] { long.class };
	private static final Class<?>[] _geolocateCommerceWarehouseParameterTypes4 = new Class[] {
			long.class, double.class, double.class
		};
	private static final Class<?>[] _getCommerceWarehouseParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _setActiveParameterTypes6 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _updateCommerceWarehouseParameterTypes7 = new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class, String.class,
			String.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateDefaultCommerceWarehouseParameterTypes8 =
		new Class[] {
			String.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}