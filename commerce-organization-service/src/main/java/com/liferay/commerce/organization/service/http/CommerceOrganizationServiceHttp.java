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

package com.liferay.commerce.organization.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.organization.service.CommerceOrganizationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceOrganizationServiceUtil} service utility. The
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
 * @author Marco Leo
 * @see CommerceOrganizationServiceSoap
 * @see HttpPrincipal
 * @see CommerceOrganizationServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrganizationServiceHttp {
	public static com.liferay.portal.kernel.model.Organization addOrganization(
		HttpPrincipal httpPrincipal, long parentOrganizationId, String name,
		String type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"addOrganization", _addOrganizationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentOrganizationId, name, type, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void addOrganizationUsers(HttpPrincipal httpPrincipal,
		long organizationId, String[] emailAddresses,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"addOrganizationUsers", _addOrganizationUsersParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, emailAddresses, serviceContext);

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

	public static void deleteOrganization(HttpPrincipal httpPrincipal,
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"deleteOrganization", _deleteOrganizationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

	public static com.liferay.portal.kernel.model.Organization fetchOrganization(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"fetchOrganization", _fetchOrganizationParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Organization getOrganization(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"getOrganization", _getOrganizationParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Address getOrganizationPrimaryAddress(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"getOrganizationPrimaryAddress",
					_getOrganizationPrimaryAddressParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.Address)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.EmailAddress getOrganizationPrimaryEmailAddress(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"getOrganizationPrimaryEmailAddress",
					_getOrganizationPrimaryEmailAddressParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.EmailAddress)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization> searchOrganizations(
		HttpPrincipal httpPrincipal, long userId, long organizationId,
		String type, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"searchOrganizations", _searchOrganizationsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					organizationId, type, keywords, start, end, sorts);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization> searchOrganizationsByGroup(
		HttpPrincipal httpPrincipal, long groupId, long userId, String type,
		String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"searchOrganizationsByGroup",
					_searchOrganizationsByGroupParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, type, keywords, start, end, sorts);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portal.kernel.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long searchOrganizationsByGroupCount(
		HttpPrincipal httpPrincipal, long groupId, long userId, String type,
		String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"searchOrganizationsByGroupCount",
					_searchOrganizationsByGroupCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, type, keywords, start, end, sorts);

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

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsetOrganizationUsers(HttpPrincipal httpPrincipal,
		long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"unsetOrganizationUsers",
					_unsetOrganizationUsersParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, userIds);

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

	public static com.liferay.portal.kernel.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId, String name,
		long emailAddressId, String address, long addressId, String street1,
		String street2, String street3, String city, String zip, long regionId,
		long countryId, boolean logo, byte[] logoBytes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrganizationServiceUtil.class,
					"updateOrganization", _updateOrganizationParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, name, emailAddressId, address, addressId,
					street1, street2, street3, city, zip, regionId, countryId,
					logo, logoBytes, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrganizationServiceHttp.class);
	private static final Class<?>[] _addOrganizationParameterTypes0 = new Class[] {
			long.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addOrganizationUsersParameterTypes1 = new Class[] {
			long.class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteOrganizationParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchOrganizationParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOrganizationParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOrganizationPrimaryAddressParameterTypes5 =
		new Class[] { long.class };
	private static final Class<?>[] _getOrganizationPrimaryEmailAddressParameterTypes6 =
		new Class[] { long.class };
	private static final Class<?>[] _searchOrganizationsParameterTypes7 = new Class[] {
			long.class, long.class, String.class, String.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort[].class
		};
	private static final Class<?>[] _searchOrganizationsByGroupParameterTypes8 = new Class[] {
			long.class, long.class, String.class, String.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort[].class
		};
	private static final Class<?>[] _searchOrganizationsByGroupCountParameterTypes9 =
		new Class[] {
			long.class, long.class, String.class, String.class, int.class,
			int.class, com.liferay.portal.kernel.search.Sort[].class
		};
	private static final Class<?>[] _unsetOrganizationUsersParameterTypes10 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _updateOrganizationParameterTypes11 = new Class[] {
			long.class, String.class, long.class, String.class, long.class,
			String.class, String.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, byte[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}