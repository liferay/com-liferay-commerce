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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce inventory warehouse service. This utility wraps {@link com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehousePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehousePersistence
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehousePersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseUtil {
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
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		getPersistence().clearCache(commerceInventoryWarehouse);
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
	public static List<CommerceInventoryWarehouse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryWarehouse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryWarehouse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryWarehouse update(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		return getPersistence().update(commerceInventoryWarehouse);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryWarehouse update(
		CommerceInventoryWarehouse commerceInventoryWarehouse,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceInventoryWarehouse, serviceContext);
	}

	/**
	* Returns all the commerce inventory warehouses where active = &#63;.
	*
	* @param active the active
	* @return the matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByactive(boolean active) {
		return getPersistence().findByactive(active);
	}

	/**
	* Returns a range of all the commerce inventory warehouses where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end) {
		return getPersistence().findByactive(active, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .findByactive(active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByactive(active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findByactive_First(
		boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().findByactive_First(active, orderByComparator);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByactive_First(
		boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence().fetchByactive_First(active, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findByactive_Last(boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().findByactive_Last(active, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByactive_Last(
		boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence().fetchByactive_Last(active, orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] findByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findByactive_PrevAndNext(commerceInventoryWarehouseId,
			active, orderByComparator);
	}

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param active the active
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active) {
		return getPersistence().filterFindByactive(active);
	}

	/**
	* Returns a range of all the commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active, int start, int end) {
		return getPersistence().filterFindByactive(active, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .filterFindByactive(active, start, end, orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] filterFindByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .filterFindByactive_PrevAndNext(commerceInventoryWarehouseId,
			active, orderByComparator);
	}

	/**
	* Removes all the commerce inventory warehouses where active = &#63; from the database.
	*
	* @param active the active
	*/
	public static void removeByactive(boolean active) {
		getPersistence().removeByactive(active);
	}

	/**
	* Returns the number of commerce inventory warehouses where active = &#63;.
	*
	* @param active the active
	* @return the number of matching commerce inventory warehouses
	*/
	public static int countByactive(boolean active) {
		return getPersistence().countByactive(active);
	}

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param active the active
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public static int filterCountByactive(boolean active) {
		return getPersistence().filterCountByactive(active);
	}

	/**
	* Returns all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return getPersistence()
				   .findBycountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	* Returns a range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end) {
		return getPersistence()
				   .findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findBycountryTwoLettersISOCode_First(countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .fetchBycountryTwoLettersISOCode_First(countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findBycountryTwoLettersISOCode_Last(countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .fetchBycountryTwoLettersISOCode_Last(countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] findBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findBycountryTwoLettersISOCode_PrevAndNext(commerceInventoryWarehouseId,
			countryTwoLettersISOCode, orderByComparator);
	}

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return getPersistence()
				   .filterFindBycountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	* Returns a range of all the commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end) {
		return getPersistence()
				   .filterFindBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .filterFindBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end, orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] filterFindBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .filterFindBycountryTwoLettersISOCode_PrevAndNext(commerceInventoryWarehouseId,
			countryTwoLettersISOCode, orderByComparator);
	}

	/**
	* Removes all the commerce inventory warehouses where countryTwoLettersISOCode = &#63; from the database.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	*/
	public static void removeBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		getPersistence()
			.removeBycountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	* Returns the number of commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses
	*/
	public static int countBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return getPersistence()
				   .countBycountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public static int filterCountBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return getPersistence()
				   .filterCountBycountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	* Returns all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode) {
		return getPersistence().findByA_C(active, countryTwoLettersISOCode);
	}

	/**
	* Returns a range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end) {
		return getPersistence()
				   .findByA_C(active, countryTwoLettersISOCode, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .findByA_C(active, countryTwoLettersISOCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByA_C(active, countryTwoLettersISOCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findByA_C_First(active, countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .fetchByA_C_First(active, countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findByA_C_Last(active, countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .fetchByA_C_Last(active, countryTwoLettersISOCode,
			orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] findByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .findByA_C_PrevAndNext(commerceInventoryWarehouseId, active,
			countryTwoLettersISOCode, orderByComparator);
	}

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode) {
		return getPersistence().filterFindByA_C(active, countryTwoLettersISOCode);
	}

	/**
	* Returns a range of all the commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end) {
		return getPersistence()
				   .filterFindByA_C(active, countryTwoLettersISOCode, start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	*/
	public static List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence()
				   .filterFindByA_C(active, countryTwoLettersISOCode, start,
			end, orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse[] filterFindByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence()
				   .filterFindByA_C_PrevAndNext(commerceInventoryWarehouseId,
			active, countryTwoLettersISOCode, orderByComparator);
	}

	/**
	* Removes all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63; from the database.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	*/
	public static void removeByA_C(boolean active,
		String countryTwoLettersISOCode) {
		getPersistence().removeByA_C(active, countryTwoLettersISOCode);
	}

	/**
	* Returns the number of commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses
	*/
	public static int countByA_C(boolean active, String countryTwoLettersISOCode) {
		return getPersistence().countByA_C(active, countryTwoLettersISOCode);
	}

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public static int filterCountByA_C(boolean active,
		String countryTwoLettersISOCode) {
		return getPersistence()
				   .filterCountByA_C(active, countryTwoLettersISOCode);
	}

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse findByC_ERC(long companyId,
		String externalReferenceCode)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public static CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_ERC(companyId, externalReferenceCode,
			retrieveFromCache);
	}

	/**
	* Removes the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the commerce inventory warehouse that was removed
	*/
	public static CommerceInventoryWarehouse removeByC_ERC(long companyId,
		String externalReferenceCode)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the number of commerce inventory warehouses where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching commerce inventory warehouses
	*/
	public static int countByC_ERC(long companyId, String externalReferenceCode) {
		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Caches the commerce inventory warehouse in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	*/
	public static void cacheResult(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		getPersistence().cacheResult(commerceInventoryWarehouse);
	}

	/**
	* Caches the commerce inventory warehouses in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouses the commerce inventory warehouses
	*/
	public static void cacheResult(
		List<CommerceInventoryWarehouse> commerceInventoryWarehouses) {
		getPersistence().cacheResult(commerceInventoryWarehouses);
	}

	/**
	* Creates a new commerce inventory warehouse with the primary key. Does not add the commerce inventory warehouse to the database.
	*
	* @param commerceInventoryWarehouseId the primary key for the new commerce inventory warehouse
	* @return the new commerce inventory warehouse
	*/
	public static CommerceInventoryWarehouse create(
		long commerceInventoryWarehouseId) {
		return getPersistence().create(commerceInventoryWarehouseId);
	}

	/**
	* Removes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse remove(
		long commerceInventoryWarehouseId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().remove(commerceInventoryWarehouseId);
	}

	public static CommerceInventoryWarehouse updateImpl(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		return getPersistence().updateImpl(commerceInventoryWarehouse);
	}

	/**
	* Returns the commerce inventory warehouse with the primary key or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse findByPrimaryKey(
		long commerceInventoryWarehouseId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException {
		return getPersistence().findByPrimaryKey(commerceInventoryWarehouseId);
	}

	/**
	* Returns the commerce inventory warehouse with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse, or <code>null</code> if a commerce inventory warehouse with the primary key could not be found
	*/
	public static CommerceInventoryWarehouse fetchByPrimaryKey(
		long commerceInventoryWarehouseId) {
		return getPersistence().fetchByPrimaryKey(commerceInventoryWarehouseId);
	}

	public static java.util.Map<java.io.Serializable, CommerceInventoryWarehouse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce inventory warehouses.
	*
	* @return the commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce inventory warehouses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @return the range of commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory warehouses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouses
	* @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory warehouses
	*/
	public static List<CommerceInventoryWarehouse> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce inventory warehouses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce inventory warehouses.
	*
	* @return the number of commerce inventory warehouses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryWarehousePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryWarehousePersistence, CommerceInventoryWarehousePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryWarehousePersistence.class);

		ServiceTracker<CommerceInventoryWarehousePersistence, CommerceInventoryWarehousePersistence> serviceTracker =
			new ServiceTracker<CommerceInventoryWarehousePersistence, CommerceInventoryWarehousePersistence>(bundle.getBundleContext(),
				CommerceInventoryWarehousePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}