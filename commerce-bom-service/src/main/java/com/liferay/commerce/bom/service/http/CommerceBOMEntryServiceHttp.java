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

package com.liferay.commerce.bom.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.service.CommerceBOMEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceBOMEntryServiceUtil} service utility. The
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
 * @see CommerceBOMEntryServiceSoap
 * @see HttpPrincipal
 * @see CommerceBOMEntryServiceUtil
 * @generated
 */
@ProviderType
public class CommerceBOMEntryServiceHttp {
	public static com.liferay.commerce.bom.model.CommerceBOMEntry addCommerceBOMEntry(
		HttpPrincipal httpPrincipal, long userId, int number,
		String cpInstanceUuid, long cProductId, long commerceBOMDefinitionId,
		double positionX, double positionY, double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"addCommerceBOMEntry", _addCommerceBOMEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					number, cpInstanceUuid, cProductId,
					commerceBOMDefinitionId, positionX, positionY, radius);

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

			return (com.liferay.commerce.bom.model.CommerceBOMEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceBOMEntry(HttpPrincipal httpPrincipal,
		long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"deleteCommerceBOMEntry",
					_deleteCommerceBOMEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMEntryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry getCommerceBOMEntry(
		HttpPrincipal httpPrincipal, long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"getCommerceBOMEntry", _getCommerceBOMEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMEntryId);

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

			return (com.liferay.commerce.bom.model.CommerceBOMEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry> getCommerceBOMEntries(
		HttpPrincipal httpPrincipal, long commerceBOMDefinitionId, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"getCommerceBOMEntries",
					_getCommerceBOMEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMDefinitionId, start, end);

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

			return (java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceBOMEntriesCount(HttpPrincipal httpPrincipal,
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"getCommerceBOMEntriesCount",
					_getCommerceBOMEntriesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMDefinitionId);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry updateCommerceBOMEntry(
		HttpPrincipal httpPrincipal, long commerceBOMEntryId, int number,
		String cpInstanceUuid, long cProductId, double positionX,
		double positionY, double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceBOMEntryServiceUtil.class,
					"updateCommerceBOMEntry",
					_updateCommerceBOMEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceBOMEntryId, number, cpInstanceUuid, cProductId,
					positionX, positionY, radius);

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

			return (com.liferay.commerce.bom.model.CommerceBOMEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceBOMEntryServiceHttp.class);
	private static final Class<?>[] _addCommerceBOMEntryParameterTypes0 = new Class[] {
			long.class, int.class, String.class, long.class, long.class,
			double.class, double.class, double.class
		};
	private static final Class<?>[] _deleteCommerceBOMEntryParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceBOMEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceBOMEntriesParameterTypes3 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getCommerceBOMEntriesCountParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateCommerceBOMEntryParameterTypes5 = new Class[] {
			long.class, int.class, String.class, long.class, double.class,
			double.class, double.class
		};
}