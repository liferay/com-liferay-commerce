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

package com.liferay.commerce.inventory.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce inventory replenishment item service. This utility wraps <code>com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryReplenishmentItemPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemPersistence
 * @generated
 */
@ProviderType
public class CommerceInventoryReplenishmentItemUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {

		getPersistence().clearCache(commerceInventoryReplenishmentItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommerceInventoryReplenishmentItem>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceInventoryReplenishmentItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryReplenishmentItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryReplenishmentItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryReplenishmentItem update(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {

		return getPersistence().update(commerceInventoryReplenishmentItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryReplenishmentItem update(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commerceInventoryReplenishmentItem, serviceContext);
	}

	/**
	 * Returns all the commerce inventory replenishment items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByCommerceInventoryWarehouseId(long commerceInventoryWarehouseId) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
			findByCommerceInventoryWarehouseId_First(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_First(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
		fetchByCommerceInventoryWarehouseId_First(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().fetchByCommerceInventoryWarehouseId_First(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
			findByCommerceInventoryWarehouseId_Last(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_Last(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
		fetchByCommerceInventoryWarehouseId_Last(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().fetchByCommerceInventoryWarehouseId_Last(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem[]
			findByCommerceInventoryWarehouseId_PrevAndNext(
				long commerceInventoryReplenishmentItemId,
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_PrevAndNext(
			commerceInventoryReplenishmentItemId, commerceInventoryWarehouseId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce inventory replenishment items where commerceInventoryWarehouseId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 */
	public static void removeByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		getPersistence().removeByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns the number of commerce inventory replenishment items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the number of matching commerce inventory replenishment items
	 */
	public static int countByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		return getPersistence().countByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findBySku(
		String sku) {

		return getPersistence().findBySku(sku);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findBySku(
		String sku, int start, int end) {

		return getPersistence().findBySku(sku, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().findBySku(sku, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBySku(
			sku, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem findBySku_First(
			String sku,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findBySku_First(sku, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem fetchBySku_First(
		String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().fetchBySku_First(sku, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem findBySku_Last(
			String sku,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findBySku_Last(sku, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem fetchBySku_Last(
		String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().fetchBySku_Last(sku, orderByComparator);
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem[] findBySku_PrevAndNext(
			long commerceInventoryReplenishmentItemId, String sku,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findBySku_PrevAndNext(
			commerceInventoryReplenishmentItemId, sku, orderByComparator);
	}

	/**
	 * Removes all the commerce inventory replenishment items where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	public static void removeBySku(String sku) {
		getPersistence().removeBySku(sku);
	}

	/**
	 * Returns the number of commerce inventory replenishment items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory replenishment items
	 */
	public static int countBySku(String sku) {
		return getPersistence().countBySku(sku);
	}

	/**
	 * Returns all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @return the matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByAvailabilityDate(Date availabilityDate) {

		return getPersistence().findByAvailabilityDate(availabilityDate);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByAvailabilityDate(Date availabilityDate, int start, int end) {

		return getPersistence().findByAvailabilityDate(
			availabilityDate, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByAvailabilityDate(
			Date availabilityDate, int start, int end,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().findByAvailabilityDate(
			availabilityDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem>
		findByAvailabilityDate(
			Date availabilityDate, int start, int end,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByAvailabilityDate(
			availabilityDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
			findByAvailabilityDate_First(
				Date availabilityDate,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByAvailabilityDate_First(
			availabilityDate, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
		fetchByAvailabilityDate_First(
			Date availabilityDate,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().fetchByAvailabilityDate_First(
			availabilityDate, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
			findByAvailabilityDate_Last(
				Date availabilityDate,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByAvailabilityDate_Last(
			availabilityDate, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem
		fetchByAvailabilityDate_Last(
			Date availabilityDate,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return getPersistence().fetchByAvailabilityDate_Last(
			availabilityDate, orderByComparator);
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem[]
			findByAvailabilityDate_PrevAndNext(
				long commerceInventoryReplenishmentItemId,
				Date availabilityDate,
				OrderByComparator<CommerceInventoryReplenishmentItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByAvailabilityDate_PrevAndNext(
			commerceInventoryReplenishmentItemId, availabilityDate,
			orderByComparator);
	}

	/**
	 * Removes all the commerce inventory replenishment items where availabilityDate = &#63; from the database.
	 *
	 * @param availabilityDate the availability date
	 */
	public static void removeByAvailabilityDate(Date availabilityDate) {
		getPersistence().removeByAvailabilityDate(availabilityDate);
	}

	/**
	 * Returns the number of commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @return the number of matching commerce inventory replenishment items
	 */
	public static int countByAvailabilityDate(Date availabilityDate) {
		return getPersistence().countByAvailabilityDate(availabilityDate);
	}

	/**
	 * Returns all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @return the matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate) {

		return getPersistence().findByS_AD(sku, availabilityDate);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end) {

		return getPersistence().findByS_AD(sku, availabilityDate, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().findByS_AD(
			sku, availabilityDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByS_AD(
			sku, availabilityDate, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem findByS_AD_First(
			String sku, Date availabilityDate,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByS_AD_First(
			sku, availabilityDate, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem fetchByS_AD_First(
		String sku, Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().fetchByS_AD_First(
			sku, availabilityDate, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem findByS_AD_Last(
			String sku, Date availabilityDate,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByS_AD_Last(
			sku, availabilityDate, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	public static CommerceInventoryReplenishmentItem fetchByS_AD_Last(
		String sku, Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().fetchByS_AD_Last(
			sku, availabilityDate, orderByComparator);
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem[] findByS_AD_PrevAndNext(
			long commerceInventoryReplenishmentItemId, String sku,
			Date availabilityDate,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByS_AD_PrevAndNext(
			commerceInventoryReplenishmentItemId, sku, availabilityDate,
			orderByComparator);
	}

	/**
	 * Removes all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63; from the database.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 */
	public static void removeByS_AD(String sku, Date availabilityDate) {
		getPersistence().removeByS_AD(sku, availabilityDate);
	}

	/**
	 * Returns the number of commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @return the number of matching commerce inventory replenishment items
	 */
	public static int countByS_AD(String sku, Date availabilityDate) {
		return getPersistence().countByS_AD(sku, availabilityDate);
	}

	/**
	 * Caches the commerce inventory replenishment item in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	 */
	public static void cacheResult(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {

		getPersistence().cacheResult(commerceInventoryReplenishmentItem);
	}

	/**
	 * Caches the commerce inventory replenishment items in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryReplenishmentItems the commerce inventory replenishment items
	 */
	public static void cacheResult(
		List<CommerceInventoryReplenishmentItem>
			commerceInventoryReplenishmentItems) {

		getPersistence().cacheResult(commerceInventoryReplenishmentItems);
	}

	/**
	 * Creates a new commerce inventory replenishment item with the primary key. Does not add the commerce inventory replenishment item to the database.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key for the new commerce inventory replenishment item
	 * @return the new commerce inventory replenishment item
	 */
	public static CommerceInventoryReplenishmentItem create(
		long commerceInventoryReplenishmentItemId) {

		return getPersistence().create(commerceInventoryReplenishmentItemId);
	}

	/**
	 * Removes the commerce inventory replenishment item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was removed
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem remove(
			long commerceInventoryReplenishmentItemId)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().remove(commerceInventoryReplenishmentItemId);
	}

	public static CommerceInventoryReplenishmentItem updateImpl(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {

		return getPersistence().updateImpl(commerceInventoryReplenishmentItem);
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or throws a <code>NoSuchInventoryReplenishmentItemException</code> if it could not be found.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem findByPrimaryKey(
			long commerceInventoryReplenishmentItemId)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryReplenishmentItemException {

		return getPersistence().findByPrimaryKey(
			commerceInventoryReplenishmentItemId);
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item, or <code>null</code> if a commerce inventory replenishment item with the primary key could not be found
	 */
	public static CommerceInventoryReplenishmentItem fetchByPrimaryKey(
		long commerceInventoryReplenishmentItemId) {

		return getPersistence().fetchByPrimaryKey(
			commerceInventoryReplenishmentItemId);
	}

	/**
	 * Returns all the commerce inventory replenishment items.
	 *
	 * @return the commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem>
			orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryReplenishmentItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce inventory replenishment items
	 */
	public static List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce inventory replenishment items from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce inventory replenishment items.
	 *
	 * @return the number of commerce inventory replenishment items
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryReplenishmentItemPersistence
		getPersistence() {

		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceInventoryReplenishmentItemPersistence,
		 CommerceInventoryReplenishmentItemPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceInventoryReplenishmentItemPersistence.class);

		ServiceTracker
			<CommerceInventoryReplenishmentItemPersistence,
			 CommerceInventoryReplenishmentItemPersistence> serviceTracker =
				new ServiceTracker
					<CommerceInventoryReplenishmentItemPersistence,
					 CommerceInventoryReplenishmentItemPersistence>(
						 bundle.getBundleContext(),
						 CommerceInventoryReplenishmentItemPersistence.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}