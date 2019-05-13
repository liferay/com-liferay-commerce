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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce inventory warehouse service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehousePersistenceImpl
 * @see CommerceInventoryWarehouseUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehousePersistence extends BasePersistence<CommerceInventoryWarehouse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseUtil} to access the commerce inventory warehouse persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce inventory warehouses where active = &#63;.
	*
	* @param active the active
	* @return the matching commerce inventory warehouses
	*/
	public java.util.List<CommerceInventoryWarehouse> findByactive(
		boolean active);

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
	public java.util.List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouse> findByactive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findByactive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByactive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findByactive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByactive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where active = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse[] findByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param active the active
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public java.util.List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindByactive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse[] filterFindByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Removes all the commerce inventory warehouses where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByactive(boolean active);

	/**
	* Returns the number of commerce inventory warehouses where active = &#63;.
	*
	* @param active the active
	* @return the number of matching commerce inventory warehouses
	*/
	public int countByactive(boolean active);

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63;.
	*
	* @param active the active
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public int filterCountByactive(boolean active);

	/**
	* Returns all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses
	*/
	public java.util.List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode);

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
	public java.util.List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse[] findBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public java.util.List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse[] filterFindBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Removes all the commerce inventory warehouses where countryTwoLettersISOCode = &#63; from the database.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	*/
	public void removeBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode);

	/**
	* Returns the number of commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses
	*/
	public int countBycountryTwoLettersISOCode(String countryTwoLettersISOCode);

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	*
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public int filterCountBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode);

	/**
	* Returns all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses
	*/
	public java.util.List<CommerceInventoryWarehouse> findByA_C(
		boolean active, String countryTwoLettersISOCode);

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
	public java.util.List<CommerceInventoryWarehouse> findByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> findByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouse> findByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public CommerceInventoryWarehouse[] findByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns all the commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the matching commerce inventory warehouses that the user has permission to view
	*/
	public java.util.List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> filterFindByA_C(
		boolean active, String countryTwoLettersISOCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public CommerceInventoryWarehouse[] filterFindByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException;

	/**
	* Removes all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63; from the database.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	*/
	public void removeByA_C(boolean active, String countryTwoLettersISOCode);

	/**
	* Returns the number of commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses
	*/
	public int countByA_C(boolean active, String countryTwoLettersISOCode);

	/**
	* Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	*
	* @param active the active
	* @param countryTwoLettersISOCode the country two letters iso code
	* @return the number of matching commerce inventory warehouses that the user has permission to view
	*/
	public int filterCountByA_C(boolean active, String countryTwoLettersISOCode);

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchInventoryWarehouseException;

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode);

	/**
	* Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	*/
	public CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache);

	/**
	* Removes the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the commerce inventory warehouse that was removed
	*/
	public CommerceInventoryWarehouse removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchInventoryWarehouseException;

	/**
	* Returns the number of commerce inventory warehouses where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching commerce inventory warehouses
	*/
	public int countByC_ERC(long companyId, String externalReferenceCode);

	/**
	* Caches the commerce inventory warehouse in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouse the commerce inventory warehouse
	*/
	public void cacheResult(
		CommerceInventoryWarehouse commerceInventoryWarehouse);

	/**
	* Caches the commerce inventory warehouses in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouses the commerce inventory warehouses
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryWarehouse> commerceInventoryWarehouses);

	/**
	* Creates a new commerce inventory warehouse with the primary key. Does not add the commerce inventory warehouse to the database.
	*
	* @param commerceInventoryWarehouseId the primary key for the new commerce inventory warehouse
	* @return the new commerce inventory warehouse
	*/
	public CommerceInventoryWarehouse create(long commerceInventoryWarehouseId);

	/**
	* Removes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse that was removed
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse remove(long commerceInventoryWarehouseId)
		throws NoSuchInventoryWarehouseException;

	public CommerceInventoryWarehouse updateImpl(
		CommerceInventoryWarehouse commerceInventoryWarehouse);

	/**
	* Returns the commerce inventory warehouse with the primary key or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse
	* @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse findByPrimaryKey(
		long commerceInventoryWarehouseId)
		throws NoSuchInventoryWarehouseException;

	/**
	* Returns the commerce inventory warehouse with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	* @return the commerce inventory warehouse, or <code>null</code> if a commerce inventory warehouse with the primary key could not be found
	*/
	public CommerceInventoryWarehouse fetchByPrimaryKey(
		long commerceInventoryWarehouseId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryWarehouse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory warehouses.
	*
	* @return the commerce inventory warehouses
	*/
	public java.util.List<CommerceInventoryWarehouse> findAll();

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
	public java.util.List<CommerceInventoryWarehouse> findAll(int start, int end);

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
	public java.util.List<CommerceInventoryWarehouse> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouse> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory warehouses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory warehouses.
	*
	* @return the number of commerce inventory warehouses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}