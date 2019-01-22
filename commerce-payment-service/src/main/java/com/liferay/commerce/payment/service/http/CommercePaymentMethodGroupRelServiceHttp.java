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

package com.liferay.commerce.payment.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommercePaymentMethodGroupRelServiceUtil} service utility. The
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
 * @see CommercePaymentMethodGroupRelServiceSoap
 * @see HttpPrincipal
 * @see CommercePaymentMethodGroupRelServiceUtil
 * @generated
 */
@ProviderType
public class CommercePaymentMethodGroupRelServiceHttp {
	public static com.liferay.commerce.model.CommerceAddressRestriction addCommerceAddressRestriction(
		HttpPrincipal httpPrincipal, long classPK, long commerceCountryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"addCommerceAddressRestriction",
					_addCommerceAddressRestrictionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK,
					commerceCountryId, serviceContext);

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

			return (com.liferay.commerce.model.CommerceAddressRestriction)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel addCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal,
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile, String engineKey,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"addCommercePaymentMethodGroupRel",
					_addCommercePaymentMethodGroupRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, nameMap,
					descriptionMap, imageFile, engineKey, engineParameterMap,
					priority, active, serviceContext);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"createCommercePaymentMethodGroupRel",
					_createCommercePaymentMethodGroupRelParameterTypes2);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceAddressRestriction(
		HttpPrincipal httpPrincipal, long commerceAddressRestrictionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"deleteCommerceAddressRestriction",
					_deleteCommerceAddressRestrictionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceAddressRestrictionId);

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

	public static void deleteCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal, long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"deleteCommercePaymentMethodGroupRel",
					_deleteCommercePaymentMethodGroupRelParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commercePaymentMethodGroupRelId);

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

	public static java.util.List<com.liferay.commerce.model.CommerceAddressRestriction> getCommerceAddressRestrictions(
		HttpPrincipal httpPrincipal, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddressRestriction> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommerceAddressRestrictions",
					_getCommerceAddressRestrictionsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK,
					start, end, orderByComparator);

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

			return (java.util.List<com.liferay.commerce.model.CommerceAddressRestriction>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceAddressRestrictionsCount(
		HttpPrincipal httpPrincipal, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommerceAddressRestrictionsCount",
					_getCommerceAddressRestrictionsCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK);

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

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal, long commercePaymentMethodGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRel",
					_getCommercePaymentMethodGroupRelParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commercePaymentMethodGroupRelId);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel getCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal, long groupId, String engineKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRel",
					_getCommercePaymentMethodGroupRelParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					engineKey);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRels",
					_getCommercePaymentMethodGroupRelsParameterTypes9);

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

			return (java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		HttpPrincipal httpPrincipal, long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRels",
					_getCommercePaymentMethodGroupRelsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					active);

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

			return (java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel> getCommercePaymentMethodGroupRels(
		HttpPrincipal httpPrincipal, long groupId, long commerceCountryId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRels",
					_getCommercePaymentMethodGroupRelsParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					commerceCountryId, active);

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

			return (java.util.List<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommercePaymentMethodGroupRelsCount(
		HttpPrincipal httpPrincipal, long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"getCommercePaymentMethodGroupRelsCount",
					_getCommercePaymentMethodGroupRelsCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					active);

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

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel setActive(
		HttpPrincipal httpPrincipal, long commercePaymentMethodGroupRelId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"setActive", _setActiveParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commercePaymentMethodGroupRelId, active);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel updateCommercePaymentMethodGroupRel(
		HttpPrincipal httpPrincipal, long commercePaymentMethodGroupRelId,
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.io.File imageFile,
		java.util.Map<String, String> engineParameterMap, double priority,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommercePaymentMethodGroupRelServiceUtil.class,
					"updateCommercePaymentMethodGroupRel",
					_updateCommercePaymentMethodGroupRelParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commercePaymentMethodGroupRelId, nameMap, descriptionMap,
					imageFile, engineParameterMap, priority, active,
					serviceContext);

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

			return (com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommercePaymentMethodGroupRelServiceHttp.class);
	private static final Class<?>[] _addCommerceAddressRestrictionParameterTypes0 =
		new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCommercePaymentMethodGroupRelParameterTypes1 =
		new Class[] {
			java.util.Map.class, java.util.Map.class, java.io.File.class,
			String.class, java.util.Map.class, double.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _createCommercePaymentMethodGroupRelParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _deleteCommerceAddressRestrictionParameterTypes3 =
		new Class[] { long.class };
	private static final Class<?>[] _deleteCommercePaymentMethodGroupRelParameterTypes4 =
		new Class[] { long.class };
	private static final Class<?>[] _getCommerceAddressRestrictionsParameterTypes5 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceAddressRestrictionsCountParameterTypes6 =
		new Class[] { long.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelParameterTypes7 =
		new Class[] { long.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelParameterTypes8 =
		new Class[] { long.class, String.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelsParameterTypes9 =
		new Class[] { long.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelsParameterTypes10 =
		new Class[] { long.class, boolean.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelsParameterTypes11 =
		new Class[] { long.class, long.class, boolean.class };
	private static final Class<?>[] _getCommercePaymentMethodGroupRelsCountParameterTypes12 =
		new Class[] { long.class, boolean.class };
	private static final Class<?>[] _setActiveParameterTypes13 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _updateCommercePaymentMethodGroupRelParameterTypes14 =
		new Class[] {
			long.class, java.util.Map.class, java.util.Map.class,
			java.io.File.class, java.util.Map.class, double.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}