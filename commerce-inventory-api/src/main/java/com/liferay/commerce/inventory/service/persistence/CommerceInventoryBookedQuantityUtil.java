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

import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the commerce inventory booked quantity service. This utility wraps {@link com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryBookedQuantityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityPersistence
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryBookedQuantityPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryBookedQuantityUtil {
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
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {
		getPersistence().clearCache(commerceInventoryBookedQuantity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceInventoryBookedQuantity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryBookedQuantity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryBookedQuantity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryBookedQuantity update(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {
		return getPersistence().update(commerceInventoryBookedQuantity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryBookedQuantity update(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceInventoryBookedQuantity, serviceContext);
	}

	/**
	* Returns all the commerce inventory booked quantities where sku = &#63;.
	*
	* @param sku the sku
	* @return the matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findBysku(String sku) {
		return getPersistence().findBysku(sku);
	}

	/**
	* Returns a range of all the commerce inventory booked quantities where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @return the range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findBysku(String sku,
		int start, int end) {
		return getPersistence().findBysku(sku, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence().findBysku(sku, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBysku(sku, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity findBysku_First(String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence().findBysku_First(sku, orderByComparator);
	}

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity fetchBysku_First(String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence().fetchBysku_First(sku, orderByComparator);
	}

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity findBysku_Last(String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence().findBysku_Last(sku, orderByComparator);
	}

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity fetchBysku_Last(String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence().fetchBysku_Last(sku, orderByComparator);
	}

	/**
	* Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public static CommerceInventoryBookedQuantity[] findBysku_PrevAndNext(
		long commerceInventoryBookedQuantityId, String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence()
				   .findBysku_PrevAndNext(commerceInventoryBookedQuantityId,
			sku, orderByComparator);
	}

	/**
	* Removes all the commerce inventory booked quantities where sku = &#63; from the database.
	*
	* @param sku the sku
	*/
	public static void removeBysku(String sku) {
		getPersistence().removeBysku(sku);
	}

	/**
	* Returns the number of commerce inventory booked quantities where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory booked quantities
	*/
	public static int countBysku(String sku) {
		return getPersistence().countBysku(sku);
	}

	/**
	* Returns all the commerce inventory booked quantities where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @return the matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate) {
		return getPersistence().findByexpireDate(expireDate);
	}

	/**
	* Returns a range of all the commerce inventory booked quantities where expireDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @return the range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end) {
		return getPersistence().findByexpireDate(expireDate, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities where expireDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence()
				   .findByexpireDate(expireDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities where expireDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByexpireDate(expireDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity findByexpireDate_First(
		Date expireDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence()
				   .findByexpireDate_First(expireDate, orderByComparator);
	}

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity fetchByexpireDate_First(
		Date expireDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence()
				   .fetchByexpireDate_First(expireDate, orderByComparator);
	}

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity findByexpireDate_Last(
		Date expireDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence()
				   .findByexpireDate_Last(expireDate, orderByComparator);
	}

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public static CommerceInventoryBookedQuantity fetchByexpireDate_Last(
		Date expireDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence()
				   .fetchByexpireDate_Last(expireDate, orderByComparator);
	}

	/**
	* Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public static CommerceInventoryBookedQuantity[] findByexpireDate_PrevAndNext(
		long commerceInventoryBookedQuantityId, Date expireDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence()
				   .findByexpireDate_PrevAndNext(commerceInventoryBookedQuantityId,
			expireDate, orderByComparator);
	}

	/**
	* Removes all the commerce inventory booked quantities where expireDate = &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public static void removeByexpireDate(Date expireDate) {
		getPersistence().removeByexpireDate(expireDate);
	}

	/**
	* Returns the number of commerce inventory booked quantities where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching commerce inventory booked quantities
	*/
	public static int countByexpireDate(Date expireDate) {
		return getPersistence().countByexpireDate(expireDate);
	}

	/**
	* Caches the commerce inventory booked quantity in the entity cache if it is enabled.
	*
	* @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	*/
	public static void cacheResult(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {
		getPersistence().cacheResult(commerceInventoryBookedQuantity);
	}

	/**
	* Caches the commerce inventory booked quantities in the entity cache if it is enabled.
	*
	* @param commerceInventoryBookedQuantities the commerce inventory booked quantities
	*/
	public static void cacheResult(
		List<CommerceInventoryBookedQuantity> commerceInventoryBookedQuantities) {
		getPersistence().cacheResult(commerceInventoryBookedQuantities);
	}

	/**
	* Creates a new commerce inventory booked quantity with the primary key. Does not add the commerce inventory booked quantity to the database.
	*
	* @param commerceInventoryBookedQuantityId the primary key for the new commerce inventory booked quantity
	* @return the new commerce inventory booked quantity
	*/
	public static CommerceInventoryBookedQuantity create(
		long commerceInventoryBookedQuantityId) {
		return getPersistence().create(commerceInventoryBookedQuantityId);
	}

	/**
	* Removes the commerce inventory booked quantity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity that was removed
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public static CommerceInventoryBookedQuantity remove(
		long commerceInventoryBookedQuantityId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence().remove(commerceInventoryBookedQuantityId);
	}

	public static CommerceInventoryBookedQuantity updateImpl(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {
		return getPersistence().updateImpl(commerceInventoryBookedQuantity);
	}

	/**
	* Returns the commerce inventory booked quantity with the primary key or throws a {@link NoSuchInventoryBookedQuantityException} if it could not be found.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public static CommerceInventoryBookedQuantity findByPrimaryKey(
		long commerceInventoryBookedQuantityId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException {
		return getPersistence()
				   .findByPrimaryKey(commerceInventoryBookedQuantityId);
	}

	/**
	* Returns the commerce inventory booked quantity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity, or <code>null</code> if a commerce inventory booked quantity with the primary key could not be found
	*/
	public static CommerceInventoryBookedQuantity fetchByPrimaryKey(
		long commerceInventoryBookedQuantityId) {
		return getPersistence()
				   .fetchByPrimaryKey(commerceInventoryBookedQuantityId);
	}

	public static java.util.Map<java.io.Serializable, CommerceInventoryBookedQuantity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce inventory booked quantities.
	*
	* @return the commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce inventory booked quantities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @return the range of commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findAll(int start,
		int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory booked quantities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryBookedQuantityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory booked quantities
	* @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory booked quantities
	*/
	public static List<CommerceInventoryBookedQuantity> findAll(int start,
		int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce inventory booked quantities from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce inventory booked quantities.
	*
	* @return the number of commerce inventory booked quantities
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryBookedQuantityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryBookedQuantityPersistence, CommerceInventoryBookedQuantityPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryBookedQuantityPersistence.class);

		ServiceTracker<CommerceInventoryBookedQuantityPersistence, CommerceInventoryBookedQuantityPersistence> serviceTracker =
			new ServiceTracker<CommerceInventoryBookedQuantityPersistence, CommerceInventoryBookedQuantityPersistence>(bundle.getBundleContext(),
				CommerceInventoryBookedQuantityPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}