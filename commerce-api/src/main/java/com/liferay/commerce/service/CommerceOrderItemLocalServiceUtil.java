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

package com.liferay.commerce.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceOrderItem. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceOrderItemLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderItemLocalService
 * @generated
 */
public class CommerceOrderItemLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceOrderItemLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce order item to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was added
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
		addCommerceOrderItem(
			com.liferay.commerce.model.CommerceOrderItem commerceOrderItem) {

		return getService().addCommerceOrderItem(commerceOrderItem);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			addCommerceOrderItem(
				long commerceOrderId, long cpInstanceId, int quantity,
				int shippedQuantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceOrderItem(
			commerceOrderId, cpInstanceId, quantity, shippedQuantity, json,
			commerceContext, serviceContext);
	}

	/**
	 * Creates a new commerce order item with the primary key. Does not add the commerce order item to the database.
	 *
	 * @param commerceOrderItemId the primary key for the new commerce order item
	 * @return the new commerce order item
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
		createCommerceOrderItem(long commerceOrderItemId) {

		return getService().createCommerceOrderItem(commerceOrderItemId);
	}

	/**
	 * Deletes the commerce order item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was removed
	 * @throws PortalException
	 */
	@Deprecated
	public static com.liferay.commerce.model.CommerceOrderItem
			deleteCommerceOrderItem(
				com.liferay.commerce.model.CommerceOrderItem commerceOrderItem)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceOrderItem(commerceOrderItem);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			deleteCommerceOrderItem(
				com.liferay.commerce.model.CommerceOrderItem commerceOrderItem,
				com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceOrderItem(
			commerceOrderItem, commerceContext);
	}

	/**
	 * Deletes the commerce order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item that was removed
	 * @throws PortalException if a commerce order item with the primary key could not be found
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
			deleteCommerceOrderItem(long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceOrderItem(commerceOrderItemId);
	}

	public static void deleteCommerceOrderItems(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceOrderItems(commerceOrderId);
	}

	public static void deleteCommerceOrderItemsByCPInstanceId(long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceOrderItemsByCPInstanceId(cpInstanceId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
		fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
		fetchCommerceOrderItem(long commerceOrderItemId) {

		return getService().fetchCommerceOrderItem(commerceOrderItemId);
	}

	/**
	 * Returns the commerce order item with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order item's external reference code
	 * @return the matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
		fetchCommerceOrderItemByReferenceCode(
			long companyId, String externalReferenceCode) {

		return getService().fetchCommerceOrderItemByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getAvailableForShipmentCommerceOrderItems(long commerceOrderId) {

		return getService().getAvailableForShipmentCommerceOrderItems(
			commerceOrderId);
	}

	public static int getCommerceInventoryWarehouseItemQuantity(
			long commerceOrderItemId, long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceInventoryWarehouseItemQuantity(
			commerceOrderItemId, commerceInventoryWarehouseId);
	}

	/**
	 * Returns the commerce order item with the primary key.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item
	 * @throws PortalException if a commerce order item with the primary key could not be found
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
			getCommerceOrderItem(long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceOrderItem(commerceOrderItemId);
	}

	/**
	 * Returns a range of all the commerce order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of commerce order items
	 */
	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getCommerceOrderItems(int start, int end) {

		return getService().getCommerceOrderItems(start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getCommerceOrderItems(long commerceOrderId, int start, int end) {

		return getService().getCommerceOrderItems(commerceOrderId, start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getCommerceOrderItems(
			long commerceOrderId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrderItem>
					orderByComparator) {

		return getService().getCommerceOrderItems(
			commerceOrderId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getCommerceOrderItems(
			long commerceOrderId, long cpInstanceId, int start, int end) {

		return getService().getCommerceOrderItems(
			commerceOrderId, cpInstanceId, start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getCommerceOrderItems(
			long commerceOrderId, long cpInstanceId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrderItem>
					orderByComparator) {

		return getService().getCommerceOrderItems(
			commerceOrderId, cpInstanceId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce order items.
	 *
	 * @return the number of commerce order items
	 */
	public static int getCommerceOrderItemsCount() {
		return getService().getCommerceOrderItemsCount();
	}

	public static int getCommerceOrderItemsCount(long commerceOrderId) {
		return getService().getCommerceOrderItemsCount(commerceOrderId);
	}

	public static int getCommerceOrderItemsCount(
		long commerceOrderId, long cpInstanceId) {

		return getService().getCommerceOrderItemsCount(
			commerceOrderId, cpInstanceId);
	}

	public static int getCommerceOrderItemsQuantity(long commerceOrderId) {
		return getService().getCommerceOrderItemsQuantity(commerceOrderId);
	}

	public static int getCPInstanceQuantity(
		long cpInstanceId, int orderStatus) {

		return getService().getCPInstanceQuantity(cpInstanceId, orderStatus);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrderItem>
		getSubscriptionCommerceOrderItems(long commerceOrderId) {

		return getService().getSubscriptionCommerceOrderItems(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			incrementShippedQuantity(
				long commerceOrderItemId, int shippedQuantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().incrementShippedQuantity(
			commerceOrderItemId, shippedQuantity);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceOrderItem> search(
				long commerceOrderId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().search(commerceOrderId, keywords, start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceOrderItem> search(
				long commerceOrderId, String sku, String name,
				boolean andOperator, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().search(
			commerceOrderId, sku, name, andOperator, start, end, sort);
	}

	/**
	 * Updates the commerce order item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was updated
	 */
	public static com.liferay.commerce.model.CommerceOrderItem
		updateCommerceOrderItem(
			com.liferay.commerce.model.CommerceOrderItem commerceOrderItem) {

		return getService().updateCommerceOrderItem(commerceOrderItem);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItem(
				long commerceOrderItemId, int quantity,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrderItem(
			commerceOrderItemId, quantity, commerceContext, serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItem(
				long commerceOrderItemId, int quantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrderItem(
			commerceOrderItemId, quantity, json, commerceContext,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItem(
				long commerceOrderItemId, long bookedQuantityId)
		throws com.liferay.commerce.exception.NoSuchOrderItemException {

		return getService().updateCommerceOrderItem(
			commerceOrderItemId, bookedQuantityId);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemInfo(
				long commerceOrderItemId, String deliveryGroup,
				long shippingAddressId, String printedNote,
				int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
				int requestedDeliveryDateYear, int requestedDeliveryDateHour,
				int requestedDeliveryDateMinute,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrderItemInfo(
			commerceOrderItemId, deliveryGroup, shippingAddressId, printedNote,
			requestedDeliveryDateMonth, requestedDeliveryDateDay,
			requestedDeliveryDateYear, requestedDeliveryDateHour,
			requestedDeliveryDateMinute, serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemPrice(
				long commerceOrderItemId,
				com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrderItemPrice(
			commerceOrderItemId, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			updateCommerceOrderItemPrices(
				long commerceOrderItemId, java.math.BigDecimal unitPrice,
				java.math.BigDecimal promoPrice,
				java.math.BigDecimal discountAmount,
				java.math.BigDecimal finalPrice,
				java.math.BigDecimal discountPercentageLevel1,
				java.math.BigDecimal discountPercentageLevel2,
				java.math.BigDecimal discountPercentageLevel3,
				java.math.BigDecimal discountPercentageLevel4)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceOrderItemPrices(
			commerceOrderItemId, unitPrice, promoPrice, discountAmount,
			finalPrice, discountPercentageLevel1, discountPercentageLevel2,
			discountPercentageLevel3, discountPercentageLevel4);
	}

	public static com.liferay.commerce.model.CommerceOrderItem
			upsertCommerceOrderItem(
				long commerceOrderId, long cpInstanceId, int quantity,
				int shippedQuantity, String json,
				com.liferay.commerce.context.CommerceContext commerceContext,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().upsertCommerceOrderItem(
			commerceOrderId, cpInstanceId, quantity, shippedQuantity, json,
			commerceContext, serviceContext);
	}

	public static CommerceOrderItemLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceOrderItemLocalService, CommerceOrderItemLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceOrderItemLocalService.class);

		ServiceTracker
			<CommerceOrderItemLocalService, CommerceOrderItemLocalService>
				serviceTracker =
					new ServiceTracker
						<CommerceOrderItemLocalService,
						 CommerceOrderItemLocalService>(
							 bundle.getBundleContext(),
							 CommerceOrderItemLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}