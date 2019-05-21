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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceWarehouseServiceUtil</code> service
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
 * @author Alessio Antonio Rendina
 * @see CommerceWarehouseServiceSoap
 * @generated
 */
@ProviderType
public class CommerceWarehouseServiceHttp {

	public static com.liferay.commerce.model.CommerceWarehouse
			addCommerceWarehouse(
				HttpPrincipal httpPrincipal, String name, String description,
				boolean active, String street1, String street2, String street3,
				String city, String zip, long commerceRegionId,
				long commerceCountryId, double latitude, double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "addCommerceWarehouse",
				_addCommerceWarehouseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, active, street1, street2, street3,
				city, zip, commerceRegionId, commerceCountryId, latitude,
				longitude, serviceContext);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceWarehouse(
			HttpPrincipal httpPrincipal, long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "deleteCommerceWarehouse",
				_deleteCommerceWarehouseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceWarehouseId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouse
			fetchDefaultCommerceWarehouse(
				HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"fetchDefaultCommerceWarehouse",
				_fetchDefaultCommerceWarehouseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouse
			geolocateCommerceWarehouse(
				HttpPrincipal httpPrincipal, long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"geolocateCommerceWarehouse",
				_geolocateCommerceWarehouseParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceWarehouseId);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouse
			getCommerceWarehouse(
				HttpPrincipal httpPrincipal, long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouse",
				_getCommerceWarehouseParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceWarehouseId);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			getCommerceWarehouses(
				HttpPrincipal httpPrincipal, long groupId, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouses",
				_getCommerceWarehousesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, active, start, end, orderByComparator);

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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			getCommerceWarehouses(
				HttpPrincipal httpPrincipal, long groupId, boolean active,
				long commerceCountryId, boolean primary, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouses",
				_getCommerceWarehousesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, active, commerceCountryId, primary, start,
				end, orderByComparator);

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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			getCommerceWarehouses(
				HttpPrincipal httpPrincipal, long groupId, boolean active,
				long commerceCountryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouses",
				_getCommerceWarehousesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, active, commerceCountryId, start, end,
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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			getCommerceWarehouses(
				HttpPrincipal httpPrincipal, long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouses",
				_getCommerceWarehousesParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, start, end, orderByComparator);

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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			getCommerceWarehouses(
				HttpPrincipal httpPrincipal, long groupId,
				long commerceCountryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "getCommerceWarehouses",
				_getCommerceWarehousesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, commerceCountryId, start, end,
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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceWarehousesCount(
			HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"getCommerceWarehousesCount",
				_getCommerceWarehousesCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static int getCommerceWarehousesCount(
			HttpPrincipal httpPrincipal, long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"getCommerceWarehousesCount",
				_getCommerceWarehousesCountParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, active);

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

	public static int getCommerceWarehousesCount(
			HttpPrincipal httpPrincipal, long groupId, boolean active,
			long commerceCountryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"getCommerceWarehousesCount",
				_getCommerceWarehousesCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, active, commerceCountryId);

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

	public static int getCommerceWarehousesCount(
			HttpPrincipal httpPrincipal, long groupId, long commerceCountryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"getCommerceWarehousesCount",
				_getCommerceWarehousesCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, commerceCountryId);

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

	public static java.util.List<com.liferay.commerce.model.CommerceWarehouse>
			search(
				HttpPrincipal httpPrincipal, long groupId, String keywords,
				boolean all, long commerceCountryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceWarehouse>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "search",
				_searchParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, keywords, all, commerceCountryId, start,
				end, orderByComparator);

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
				<com.liferay.commerce.model.CommerceWarehouse>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(
			HttpPrincipal httpPrincipal, long groupId, String keywords,
			Boolean active, long commerceCountryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "searchCount",
				_searchCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, keywords, active, commerceCountryId);

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

	public static com.liferay.commerce.model.CommerceWarehouse setActive(
			HttpPrincipal httpPrincipal, long commerceWarehouseId,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "setActive",
				_setActiveParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceWarehouseId, active);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouse
			updateCommerceWarehouse(
				HttpPrincipal httpPrincipal, long commerceWarehouseId,
				String name, String description, boolean active, String street1,
				String street2, String street3, String city, String zip,
				long commerceRegionId, long commerceCountryId, double latitude,
				double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class, "updateCommerceWarehouse",
				_updateCommerceWarehouseParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceWarehouseId, name, description, active,
				street1, street2, street3, city, zip, commerceRegionId,
				commerceCountryId, latitude, longitude, serviceContext);

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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceWarehouse
			updateDefaultCommerceWarehouse(
				HttpPrincipal httpPrincipal, String name, String street1,
				String street2, String street3, String city, String zip,
				long commerceRegionId, long commerceCountryId, double latitude,
				double longitude,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceWarehouseServiceUtil.class,
				"updateDefaultCommerceWarehouse",
				_updateDefaultCommerceWarehouseParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, street1, street2, street3, city, zip,
				commerceRegionId, commerceCountryId, latitude, longitude,
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

			return (com.liferay.commerce.model.CommerceWarehouse)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceWarehouseServiceHttp.class);

	private static final Class<?>[] _addCommerceWarehouseParameterTypes0 =
		new Class[] {
			String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class, long.class,
			long.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceWarehouseParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[]
		_fetchDefaultCommerceWarehouseParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _geolocateCommerceWarehouseParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceWarehouseParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceWarehousesParameterTypes5 =
		new Class[] {
			long.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceWarehousesParameterTypes6 =
		new Class[] {
			long.class, boolean.class, long.class, boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceWarehousesParameterTypes7 =
		new Class[] {
			long.class, boolean.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceWarehousesParameterTypes8 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceWarehousesParameterTypes9 =
		new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommerceWarehousesCountParameterTypes10 = new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceWarehousesCountParameterTypes11 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[]
		_getCommerceWarehousesCountParameterTypes12 = new Class[] {
			long.class, boolean.class, long.class
		};
	private static final Class<?>[]
		_getCommerceWarehousesCountParameterTypes13 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _searchParameterTypes14 = new Class[] {
		long.class, String.class, boolean.class, long.class, int.class,
		int.class, com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchCountParameterTypes15 = new Class[] {
		long.class, String.class, Boolean.class, long.class
	};
	private static final Class<?>[] _setActiveParameterTypes16 = new Class[] {
		long.class, boolean.class
	};
	private static final Class<?>[] _updateCommerceWarehouseParameterTypes17 =
		new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			String.class, String.class, String.class, String.class, long.class,
			long.class, double.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateDefaultCommerceWarehouseParameterTypes18 = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, long.class, long.class, double.class,
			double.class, com.liferay.portal.kernel.service.ServiceContext.class
		};

}