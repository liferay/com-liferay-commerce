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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce inventory warehouse item service. This utility wraps <code>com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehouseItemPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemPersistence
 * @generated
 */
public class CommerceInventoryWarehouseItemUtil {

	/**
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
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {

		getPersistence().clearCache(commerceInventoryWarehouseItem);
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
	public static Map<Serializable, CommerceInventoryWarehouseItem>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceInventoryWarehouseItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryWarehouseItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryWarehouseItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryWarehouseItem update(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {

		return getPersistence().update(commerceInventoryWarehouseItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryWarehouseItem update(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commerceInventoryWarehouseItem, serviceContext);
	}

	/**
	 * Returns all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(long commerceInventoryWarehouseId) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseItem>
				orderByComparator) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem
			findByCommerceInventoryWarehouseId_First(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_First(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem
		fetchByCommerceInventoryWarehouseId_First(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryWarehouseItem>
				orderByComparator) {

		return getPersistence().fetchByCommerceInventoryWarehouseId_First(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem
			findByCommerceInventoryWarehouseId_Last(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_Last(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem
		fetchByCommerceInventoryWarehouseId_Last(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryWarehouseItem>
				orderByComparator) {

		return getPersistence().fetchByCommerceInventoryWarehouseId_Last(
			commerceInventoryWarehouseId, orderByComparator);
	}

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseItem[]
			findByCommerceInventoryWarehouseId_PrevAndNext(
				long commerceInventoryWarehouseItemId,
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseItem>
					orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findByCommerceInventoryWarehouseId_PrevAndNext(
			commerceInventoryWarehouseItemId, commerceInventoryWarehouseId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 */
	public static void removeByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		getPersistence().removeByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns the number of commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the number of matching commerce inventory warehouse items
	 */
	public static int countByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		return getPersistence().countByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
	}

	/**
	 * Returns all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findBySku(String sku) {
		return getPersistence().findBySku(sku);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findBySku(
		String sku, int start, int end) {

		return getPersistence().findBySku(sku, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {

		return getPersistence().findBySku(sku, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBySku(
			sku, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem findBySku_First(
			String sku,
			OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findBySku_First(sku, orderByComparator);
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem fetchBySku_First(
		String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {

		return getPersistence().fetchBySku_First(sku, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem findBySku_Last(
			String sku,
			OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findBySku_Last(sku, orderByComparator);
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem fetchBySku_Last(
		String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {

		return getPersistence().fetchBySku_Last(sku, orderByComparator);
	}

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseItem[] findBySku_PrevAndNext(
			long commerceInventoryWarehouseItemId, String sku,
			OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findBySku_PrevAndNext(
			commerceInventoryWarehouseItemId, sku, orderByComparator);
	}

	/**
	 * Removes all the commerce inventory warehouse items where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	public static void removeBySku(String sku) {
		getPersistence().removeBySku(sku);
	}

	/**
	 * Returns the number of commerce inventory warehouse items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory warehouse items
	 */
	public static int countBySku(String sku) {
		return getPersistence().countBySku(sku);
	}

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or throws a <code>NoSuchInventoryWarehouseItemException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem findByC_S(
			long commerceInventoryWarehouseId, String sku)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findByC_S(commerceInventoryWarehouseId, sku);
	}

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem fetchByC_S(
		long commerceInventoryWarehouseId, String sku) {

		return getPersistence().fetchByC_S(commerceInventoryWarehouseId, sku);
	}

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public static CommerceInventoryWarehouseItem fetchByC_S(
		long commerceInventoryWarehouseId, String sku, boolean useFinderCache) {

		return getPersistence().fetchByC_S(
			commerceInventoryWarehouseId, sku, useFinderCache);
	}

	/**
	 * Removes the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the commerce inventory warehouse item that was removed
	 */
	public static CommerceInventoryWarehouseItem removeByC_S(
			long commerceInventoryWarehouseId, String sku)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().removeByC_S(commerceInventoryWarehouseId, sku);
	}

	/**
	 * Returns the number of commerce inventory warehouse items where commerceInventoryWarehouseId = &#63; and sku = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the number of matching commerce inventory warehouse items
	 */
	public static int countByC_S(
		long commerceInventoryWarehouseId, String sku) {

		return getPersistence().countByC_S(commerceInventoryWarehouseId, sku);
	}

	/**
	 * Caches the commerce inventory warehouse item in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseItem the commerce inventory warehouse item
	 */
	public static void cacheResult(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {

		getPersistence().cacheResult(commerceInventoryWarehouseItem);
	}

	/**
	 * Caches the commerce inventory warehouse items in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseItems the commerce inventory warehouse items
	 */
	public static void cacheResult(
		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems) {

		getPersistence().cacheResult(commerceInventoryWarehouseItems);
	}

	/**
	 * Creates a new commerce inventory warehouse item with the primary key. Does not add the commerce inventory warehouse item to the database.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key for the new commerce inventory warehouse item
	 * @return the new commerce inventory warehouse item
	 */
	public static CommerceInventoryWarehouseItem create(
		long commerceInventoryWarehouseItemId) {

		return getPersistence().create(commerceInventoryWarehouseItemId);
	}

	/**
	 * Removes the commerce inventory warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item that was removed
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseItem remove(
			long commerceInventoryWarehouseItemId)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().remove(commerceInventoryWarehouseItemId);
	}

	public static CommerceInventoryWarehouseItem updateImpl(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {

		return getPersistence().updateImpl(commerceInventoryWarehouseItem);
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or throws a <code>NoSuchInventoryWarehouseItemException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseItem findByPrimaryKey(
			long commerceInventoryWarehouseItemId)
		throws com.liferay.commerce.inventory.exception.
			NoSuchInventoryWarehouseItemException {

		return getPersistence().findByPrimaryKey(
			commerceInventoryWarehouseItemId);
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item, or <code>null</code> if a commerce inventory warehouse item with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseItem fetchByPrimaryKey(
		long commerceInventoryWarehouseItemId) {

		return getPersistence().fetchByPrimaryKey(
			commerceInventoryWarehouseItemId);
	}

	/**
	 * Returns all the commerce inventory warehouse items.
	 *
	 * @return the commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce inventory warehouse items
	 */
	public static List<CommerceInventoryWarehouseItem> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce inventory warehouse items from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce inventory warehouse items.
	 *
	 * @return the number of commerce inventory warehouse items
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryWarehouseItemPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceInventoryWarehouseItemPersistence,
		 CommerceInventoryWarehouseItemPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceInventoryWarehouseItemPersistence.class);

		ServiceTracker
			<CommerceInventoryWarehouseItemPersistence,
			 CommerceInventoryWarehouseItemPersistence> serviceTracker =
				new ServiceTracker
					<CommerceInventoryWarehouseItemPersistence,
					 CommerceInventoryWarehouseItemPersistence>(
						 bundle.getBundleContext(),
						 CommerceInventoryWarehouseItemPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}