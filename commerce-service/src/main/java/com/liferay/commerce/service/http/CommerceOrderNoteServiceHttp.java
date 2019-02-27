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

import com.liferay.commerce.service.CommerceOrderNoteServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceOrderNoteServiceUtil} service utility. The
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
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteServiceSoap
 * @see HttpPrincipal
 * @see CommerceOrderNoteServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrderNoteServiceHttp {
	public static com.liferay.commerce.model.CommerceOrderNote addCommerceOrderNote(
		HttpPrincipal httpPrincipal, long commerceOrderId, String content,
		boolean restricted,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"addCommerceOrderNote", _addCommerceOrderNoteParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, content, restricted, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceOrderNote(HttpPrincipal httpPrincipal,
		long commerceOrderNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"deleteCommerceOrderNote",
					_deleteCommerceOrderNoteParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderNoteId);

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

	public static com.liferay.commerce.model.CommerceOrderNote fetchByExternalReferenceCode(
		HttpPrincipal httpPrincipal, long companyId,
		String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"fetchByExternalReferenceCode",
					_fetchByExternalReferenceCodeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, externalReferenceCode);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderNote fetchCommerceOrderNote(
		HttpPrincipal httpPrincipal, long commerceOrderNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"fetchCommerceOrderNote",
					_fetchCommerceOrderNoteParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderNoteId);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderNote getCommerceOrderNote(
		HttpPrincipal httpPrincipal, long commerceOrderNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"getCommerceOrderNote", _getCommerceOrderNoteParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderNoteId);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderNote> getCommerceOrderNotes(
		HttpPrincipal httpPrincipal, long commerceOrderId, boolean restricted)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"getCommerceOrderNotes",
					_getCommerceOrderNotesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, restricted);

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

			return (java.util.List<com.liferay.commerce.model.CommerceOrderNote>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderNote> getCommerceOrderNotes(
		HttpPrincipal httpPrincipal, long commerceOrderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"getCommerceOrderNotes",
					_getCommerceOrderNotesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, start, end);

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

			return (java.util.List<com.liferay.commerce.model.CommerceOrderNote>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrderNotesCount(HttpPrincipal httpPrincipal,
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"getCommerceOrderNotesCount",
					_getCommerceOrderNotesCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

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

	public static int getCommerceOrderNotesCount(HttpPrincipal httpPrincipal,
		long commerceOrderId, boolean restricted)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"getCommerceOrderNotesCount",
					_getCommerceOrderNotesCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, restricted);

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

	public static com.liferay.commerce.model.CommerceOrderNote updateCommerceOrderNote(
		HttpPrincipal httpPrincipal, long commerceOrderNoteId, String content,
		boolean restricted)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"updateCommerceOrderNote",
					_updateCommerceOrderNoteParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderNoteId, content, restricted);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderNote upsertCommerceOrderNote(
		HttpPrincipal httpPrincipal, long commerceOrderNoteId,
		long commerceOrderId, String content, boolean restricted,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderNoteServiceUtil.class,
					"upsertCommerceOrderNote",
					_upsertCommerceOrderNoteParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderNoteId, commerceOrderId, content, restricted,
					externalReferenceCode, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrderNote)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrderNoteServiceHttp.class);
	private static final Class<?>[] _addCommerceOrderNoteParameterTypes0 = new Class[] {
			long.class, String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceOrderNoteParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchByExternalReferenceCodeParameterTypes2 =
		new Class[] { long.class, String.class };
	private static final Class<?>[] _fetchCommerceOrderNoteParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderNoteParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderNotesParameterTypes6 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _getCommerceOrderNotesParameterTypes7 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getCommerceOrderNotesCountParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderNotesCountParameterTypes9 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _updateCommerceOrderNoteParameterTypes10 = new Class[] {
			long.class, String.class, boolean.class
		};
	private static final Class<?>[] _upsertCommerceOrderNoteParameterTypes11 = new Class[] {
			long.class, long.class, String.class, boolean.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}