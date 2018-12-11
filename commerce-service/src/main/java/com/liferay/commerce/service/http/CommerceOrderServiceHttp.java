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

import com.liferay.commerce.service.CommerceOrderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceOrderServiceUtil} service utility. The
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
 * @see CommerceOrderServiceSoap
 * @see HttpPrincipal
 * @see CommerceOrderServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrderServiceHttp {
	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceOrder> searchCommerceOrders(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		long orderOrganizationId, int orderStatus, String keywords, int start,
		int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"searchCommerceOrders", _searchCommerceOrdersParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, orderOrganizationId, orderStatus,
					keywords, start, end, sort);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder addOrganizationCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long siteGroupId,
		long orderOrganizationId, long commerceCurrencyId,
		long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addOrganizationCommerceOrder",
					_addOrganizationCommerceOrderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					siteGroupId, orderOrganizationId, commerceCurrencyId,
					shippingAddressId, purchaseOrderNumber);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder addOrganizationCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long siteGroupId,
		long orderOrganizationId, long shippingAddressId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addOrganizationCommerceOrder",
					_addOrganizationCommerceOrderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					siteGroupId, orderOrganizationId, shippingAddressId,
					purchaseOrderNumber);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder addUserCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long orderUserId, long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addUserCommerceOrder", _addUserCommerceOrderParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, orderUserId, commerceCurrencyId);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"approveCommerceOrder", _approveCommerceOrderParameterTypes4);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder cancelCommerceOrderPayment(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"cancelCommerceOrderPayment",
					_cancelCommerceOrderPaymentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder checkoutCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"checkoutCommerceOrder",
					_checkoutCommerceOrderParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, commerceContext, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder completeCommerceOrderPayment(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"completeCommerceOrderPayment",
					_completeCommerceOrderPaymentParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceOrder(HttpPrincipal httpPrincipal,
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"deleteCommerceOrder", _deleteCommerceOrderParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

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

	public static com.liferay.commerce.model.CommerceOrder executeWorkflowTransition(
		HttpPrincipal httpPrincipal, long commerceOrderId, long workflowTaskId,
		String transitionName, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"executeWorkflowTransition",
					_executeWorkflowTransitionParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, workflowTaskId, transitionName, comment);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchByExternalReferenceCode(
		HttpPrincipal httpPrincipal, long companyId,
		String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchByExternalReferenceCode",
					_fetchByExternalReferenceCodeParameterTypes10);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes11);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderStatus);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int[] getAvailableOrderStatuses(HttpPrincipal httpPrincipal,
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getAvailableOrderStatuses",
					_getAvailableOrderStatusesParameterTypes14);

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

			return (int[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder getCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrder", _getCommerceOrderParameterTypes15);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder getCommerceOrderByUuidAndGroupId(
		HttpPrincipal httpPrincipal, String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrderByUuidAndGroupId",
					_getCommerceOrderByUuidAndGroupIdParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
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

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long siteGroupId, int[] orderStatuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					siteGroupId, orderStatuses);

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

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, long orderUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderUserId, start, end, orderByComparator);

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

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrdersCount",
					_getCommerceOrdersCountParameterTypes20);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId, long orderUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrdersCount",
					_getCommerceOrdersCountParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderUserId);

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

	public static void mergeGuestCommerceOrder(HttpPrincipal httpPrincipal,
		long guestCommerceOrderId, long userCommerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"mergeGuestCommerceOrder",
					_mergeGuestCommerceOrderParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					guestCommerceOrderId, userCommerceOrderId, commerceContext,
					serviceContext);

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

	public static com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"reorderCommerceOrder",
					_reorderCommerceOrderParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, commerceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String startCommerceOrderPayment(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"startCommerceOrderPayment",
					_startCommerceOrderPaymentParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, serviceContext);

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

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"submitCommerceOrder", _submitCommerceOrderParameterTypes25);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateBillingAddress(
		HttpPrincipal httpPrincipal, long commerceOrderId, String name,
		String description, String street1, String street2, String street3,
		String city, String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateBillingAddress",
					_updateBillingAddressParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		long billingAddressId, long shippingAddressId,
		long commercePaymentMethodId, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateCommerceOrder", _updateCommerceOrderParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodId, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus, commerceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		long billingAddressId, long shippingAddressId,
		long commercePaymentMethodId, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateCommerceOrder", _updateCommerceOrderParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodId, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus,
					externalReferenceCode, commerceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateOrderStatus(
		HttpPrincipal httpPrincipal, long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateOrderStatus", _updateOrderStatusParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, orderStatus);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
		HttpPrincipal httpPrincipal, long commerceOrderId, int paymentStatus,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updatePaymentStatus", _updatePaymentStatusParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, paymentStatus, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updatePurchaseOrderNumber",
					_updatePurchaseOrderNumberParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, purchaseOrderNumber);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateShippingAddress(
		HttpPrincipal httpPrincipal, long commerceOrderId, String name,
		String description, String street1, String street2, String street3,
		String city, String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateShippingAddress",
					_updateShippingAddressParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateUser(
		HttpPrincipal httpPrincipal, long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateUser", _updateUserParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, userId);

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

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrderServiceHttp.class);
	private static final Class<?>[] _searchCommerceOrdersParameterTypes0 = new Class[] {
			long.class, long.class, long.class, int.class, String.class,
			int.class, int.class, com.liferay.portal.kernel.search.Sort.class
		};
	private static final Class<?>[] _addOrganizationCommerceOrderParameterTypes1 =
		new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			String.class
		};
	private static final Class<?>[] _addOrganizationCommerceOrderParameterTypes2 =
		new Class[] { long.class, long.class, long.class, long.class, String.class };
	private static final Class<?>[] _addUserCommerceOrderParameterTypes3 = new Class[] {
			long.class, long.class, long.class, long.class
		};
	private static final Class<?>[] _approveCommerceOrderParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _cancelCommerceOrderPaymentParameterTypes5 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _checkoutCommerceOrderParameterTypes6 = new Class[] {
			long.class, com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _completeCommerceOrderPaymentParameterTypes7 =
		new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceOrderParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _executeWorkflowTransitionParameterTypes9 = new Class[] {
			long.class, long.class, String.class, String.class
		};
	private static final Class<?>[] _fetchByExternalReferenceCodeParameterTypes10 =
		new Class[] { long.class, String.class };
	private static final Class<?>[] _fetchCommerceOrderParameterTypes11 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCommerceOrderParameterTypes12 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _fetchCommerceOrderParameterTypes13 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[] _getAvailableOrderStatusesParameterTypes14 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderParameterTypes15 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderByUuidAndGroupIdParameterTypes16 =
		new Class[] { String.class, long.class };
	private static final Class<?>[] _getCommerceOrdersParameterTypes17 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceOrdersParameterTypes18 = new Class[] {
			long.class, int[].class
		};
	private static final Class<?>[] _getCommerceOrdersParameterTypes19 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceOrdersCountParameterTypes20 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrdersCountParameterTypes21 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _mergeGuestCommerceOrderParameterTypes22 = new Class[] {
			long.class, long.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _reorderCommerceOrderParameterTypes23 = new Class[] {
			long.class, com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _startCommerceOrderPaymentParameterTypes24 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _submitCommerceOrderParameterTypes25 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateBillingAddressParameterTypes26 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, long.class, long.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCommerceOrderParameterTypes27 = new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			String.class, String.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class, String.class,
			com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _updateCommerceOrderParameterTypes28 = new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			String.class, String.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class, String.class,
			String.class, com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _updateOrderStatusParameterTypes29 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updatePaymentStatusParameterTypes30 = new Class[] {
			long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updatePurchaseOrderNumberParameterTypes31 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _updateShippingAddressParameterTypes32 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, long.class, long.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateUserParameterTypes33 = new Class[] {
			long.class, long.class
		};
}