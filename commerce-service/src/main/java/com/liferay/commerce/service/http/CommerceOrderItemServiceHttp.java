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

import com.liferay.commerce.service.CommerceOrderItemServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceOrderItemServiceUtil</code> service
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
 * @see CommerceOrderItemServiceSoap
 * @generated
 */
public class CommerceOrderItemServiceHttp {

	public static com.liferay.commerce.model.CommerceOrderItem
			addCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderId,
				long cpInstanceId, int quantity, int shippedQuantity,
				String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "addCommerceOrderItem",
				_addCommerceOrderItemParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, cpInstanceId, quantity,
				shippedQuantity, json, commerceContext, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceOrderItem(
			HttpPrincipal httpPrincipal, long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "deleteCommerceOrderItem",
				_deleteCommerceOrderItemParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId);

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

	public static void deleteCommerceOrderItem(
			HttpPrincipal httpPrincipal, long commerceOrderItemId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "deleteCommerceOrderItem",
				_deleteCommerceOrderItemParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, commerceContext);

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

	public static com.liferay.commerce.model.CommerceOrderItem
			fetchByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long companyId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"fetchByExternalReferenceCode",
				_fetchByExternalReferenceCodeParameterTypes3);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			fetchCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "fetchCommerceOrderItem",
				_fetchCommerceOrderItemParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
			getAvailableForShipmentCommerceOrderItems(
				HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"getAvailableForShipmentCommerceOrderItems",
				_getAvailableForShipmentCommerceOrderItemsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId);

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
				<com.liferay.commerce.model.CommerceOrderItem>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceInventoryWarehouseItemQuantity(
			HttpPrincipal httpPrincipal, long commerceOrderItemId,
			long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"getCommerceInventoryWarehouseItemQuantity",
				_getCommerceInventoryWarehouseItemQuantityParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, commerceInventoryWarehouseId);

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

	public static com.liferay.commerce.model.CommerceOrderItem
			getCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "getCommerceOrderItem",
				_getCommerceOrderItemParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
			getCommerceOrderItems(
				HttpPrincipal httpPrincipal, long commerceOrderId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "getCommerceOrderItems",
				_getCommerceOrderItemsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, start, end);

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
				<com.liferay.commerce.model.CommerceOrderItem>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrderItemsCount(
			HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"getCommerceOrderItemsCount",
				_getCommerceOrderItemsCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId);

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

	public static int getCommerceOrderItemsCount(
			HttpPrincipal httpPrincipal, long commerceOrderId,
			long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"getCommerceOrderItemsCount",
				_getCommerceOrderItemsCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, cpInstanceId);

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

	public static int getCommerceOrderItemsQuantity(
			HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"getCommerceOrderItemsQuantity",
				_getCommerceOrderItemsQuantityParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId);

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

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceOrderItem> search(
				HttpPrincipal httpPrincipal, long commerceOrderId,
				String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "search",
				_searchParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, keywords, start, end, sort);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult
				<com.liferay.commerce.model.CommerceOrderItem>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceOrderItem> search(
				HttpPrincipal httpPrincipal, long commerceOrderId, String sku,
				String name, boolean andOperator, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "search",
				_searchParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, sku, name, andOperator, start, end,
				sort);

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

			return (com.liferay.portal.kernel.search.BaseModelSearchResult
				<com.liferay.commerce.model.CommerceOrderItem>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderItemId,
				int quantity,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "updateCommerceOrderItem",
				_updateCommerceOrderItemParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, quantity, commerceContext,
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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderItemId,
				int quantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "updateCommerceOrderItem",
				_updateCommerceOrderItemParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, quantity, json, commerceContext,
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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemInfo(
				HttpPrincipal httpPrincipal, long commerceOrderItemId,
				String deliveryGroup, long shippingAddressId,
				String printedNote, int requestedDeliveryDateMonth,
				int requestedDeliveryDateDay, int requestedDeliveryDateYear,
				int requestedDeliveryDateHour, int requestedDeliveryDateMinute,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"updateCommerceOrderItemInfo",
				_updateCommerceOrderItemInfoParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, deliveryGroup,
				shippingAddressId, printedNote, requestedDeliveryDateMonth,
				requestedDeliveryDateDay, requestedDeliveryDateYear,
				requestedDeliveryDateHour, requestedDeliveryDateMinute,
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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemPrices(
				HttpPrincipal httpPrincipal, long commerceOrderItemId,
				java.math.BigDecimal unitPrice, java.math.BigDecimal promoPrice,
				java.math.BigDecimal discountAmount,
				java.math.BigDecimal finalPrice,
				java.math.BigDecimal discountPercentageLevel1,
				java.math.BigDecimal discountPercentageLevel2,
				java.math.BigDecimal discountPercentageLevel3,
				java.math.BigDecimal discountPercentageLevel4)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"updateCommerceOrderItemPrices",
				_updateCommerceOrderItemPricesParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, unitPrice, promoPrice,
				discountAmount, finalPrice, discountPercentageLevel1,
				discountPercentageLevel2, discountPercentageLevel3,
				discountPercentageLevel4);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemUnitPrice(
				HttpPrincipal httpPrincipal, long commerceOrderItemId,
				java.math.BigDecimal unitPrice)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class,
				"updateCommerceOrderItemUnitPrice",
				_updateCommerceOrderItemUnitPriceParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderItemId, unitPrice);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			upsertCommerceOrderItem(
				HttpPrincipal httpPrincipal, long commerceOrderId,
				long cpInstanceId, int quantity, int shippedQuantity,
				String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceOrderItemServiceUtil.class, "upsertCommerceOrderItem",
				_upsertCommerceOrderItemParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceOrderId, cpInstanceId, quantity,
				shippedQuantity, json, commerceContext, serviceContext);

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

			return (com.liferay.commerce.model.CommerceOrderItem)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemServiceHttp.class);

	private static final Class<?>[] _addCommerceOrderItemParameterTypes0 =
		new Class[] {
			long.class, long.class, int.class, int.class, String.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceOrderItemParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteCommerceOrderItemParameterTypes2 =
		new Class[] {
			long.class, com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[]
		_fetchByExternalReferenceCodeParameterTypes3 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _fetchCommerceOrderItemParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getAvailableForShipmentCommerceOrderItemsParameterTypes5 =
			new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceInventoryWarehouseItemQuantityParameterTypes6 =
			new Class[] {long.class, long.class};
	private static final Class<?>[] _getCommerceOrderItemParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceOrderItemsParameterTypes8 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getCommerceOrderItemsCountParameterTypes9 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceOrderItemsCountParameterTypes10 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[]
		_getCommerceOrderItemsQuantityParameterTypes11 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchParameterTypes12 = new Class[] {
		long.class, String.class, int.class, int.class,
		com.liferay.portal.kernel.search.Sort.class
	};
	private static final Class<?>[] _searchParameterTypes13 = new Class[] {
		long.class, String.class, String.class, boolean.class, int.class,
		int.class, com.liferay.portal.kernel.search.Sort.class
	};
	private static final Class<?>[] _updateCommerceOrderItemParameterTypes14 =
		new Class[] {
			long.class, int.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCommerceOrderItemParameterTypes15 =
		new Class[] {
			long.class, int.class, String.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateCommerceOrderItemInfoParameterTypes16 = new Class[] {
			long.class, String.class, long.class, String.class, int.class,
			int.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateCommerceOrderItemPricesParameterTypes17 = new Class[] {
			long.class, java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class
		};
	private static final Class<?>[]
		_updateCommerceOrderItemUnitPriceParameterTypes18 = new Class[] {
			long.class, java.math.BigDecimal.class
		};
	private static final Class<?>[] _upsertCommerceOrderItemParameterTypes19 =
		new Class[] {
			long.class, long.class, int.class, int.class, String.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}