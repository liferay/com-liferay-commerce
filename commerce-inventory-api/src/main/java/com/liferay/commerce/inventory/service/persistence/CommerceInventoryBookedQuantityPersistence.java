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

import com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the commerce inventory booked quantity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryBookedQuantityPersistenceImpl
 * @see CommerceInventoryBookedQuantityUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryBookedQuantityPersistence
	extends BasePersistence<CommerceInventoryBookedQuantity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryBookedQuantityUtil} to access the commerce inventory booked quantity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce inventory booked quantities where sku = &#63;.
	*
	* @param sku the sku
	* @return the matching commerce inventory booked quantities
	*/
	public java.util.List<CommerceInventoryBookedQuantity> findBysku(String sku);

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
	public java.util.List<CommerceInventoryBookedQuantity> findBysku(
		String sku, int start, int end);

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
	public java.util.List<CommerceInventoryBookedQuantity> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

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
	public java.util.List<CommerceInventoryBookedQuantity> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity findBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity fetchBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity findBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity fetchBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

	/**
	* Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where sku = &#63;.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public CommerceInventoryBookedQuantity[] findBysku_PrevAndNext(
		long commerceInventoryBookedQuantityId, String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Removes all the commerce inventory booked quantities where sku = &#63; from the database.
	*
	* @param sku the sku
	*/
	public void removeBysku(String sku);

	/**
	* Returns the number of commerce inventory booked quantities where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory booked quantities
	*/
	public int countBysku(String sku);

	/**
	* Returns all the commerce inventory booked quantities where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @return the matching commerce inventory booked quantities
	*/
	public java.util.List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate);

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
	public java.util.List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end);

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
	public java.util.List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

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
	public java.util.List<CommerceInventoryBookedQuantity> findByexpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity findByexpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Returns the first commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity fetchByexpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity findByexpireDate_Last(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Returns the last commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	*/
	public CommerceInventoryBookedQuantity fetchByexpireDate_Last(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

	/**
	* Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where expireDate = &#63;.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public CommerceInventoryBookedQuantity[] findByexpireDate_PrevAndNext(
		long commerceInventoryBookedQuantityId, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Removes all the commerce inventory booked quantities where expireDate = &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public void removeByexpireDate(Date expireDate);

	/**
	* Returns the number of commerce inventory booked quantities where expireDate = &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching commerce inventory booked quantities
	*/
	public int countByexpireDate(Date expireDate);

	/**
	* Caches the commerce inventory booked quantity in the entity cache if it is enabled.
	*
	* @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	*/
	public void cacheResult(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity);

	/**
	* Caches the commerce inventory booked quantities in the entity cache if it is enabled.
	*
	* @param commerceInventoryBookedQuantities the commerce inventory booked quantities
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryBookedQuantity> commerceInventoryBookedQuantities);

	/**
	* Creates a new commerce inventory booked quantity with the primary key. Does not add the commerce inventory booked quantity to the database.
	*
	* @param commerceInventoryBookedQuantityId the primary key for the new commerce inventory booked quantity
	* @return the new commerce inventory booked quantity
	*/
	public CommerceInventoryBookedQuantity create(
		long commerceInventoryBookedQuantityId);

	/**
	* Removes the commerce inventory booked quantity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity that was removed
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public CommerceInventoryBookedQuantity remove(
		long commerceInventoryBookedQuantityId)
		throws NoSuchInventoryBookedQuantityException;

	public CommerceInventoryBookedQuantity updateImpl(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity);

	/**
	* Returns the commerce inventory booked quantity with the primary key or throws a {@link NoSuchInventoryBookedQuantityException} if it could not be found.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity
	* @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	*/
	public CommerceInventoryBookedQuantity findByPrimaryKey(
		long commerceInventoryBookedQuantityId)
		throws NoSuchInventoryBookedQuantityException;

	/**
	* Returns the commerce inventory booked quantity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	* @return the commerce inventory booked quantity, or <code>null</code> if a commerce inventory booked quantity with the primary key could not be found
	*/
	public CommerceInventoryBookedQuantity fetchByPrimaryKey(
		long commerceInventoryBookedQuantityId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryBookedQuantity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory booked quantities.
	*
	* @return the commerce inventory booked quantities
	*/
	public java.util.List<CommerceInventoryBookedQuantity> findAll();

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
	public java.util.List<CommerceInventoryBookedQuantity> findAll(int start,
		int end);

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
	public java.util.List<CommerceInventoryBookedQuantity> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator);

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
	public java.util.List<CommerceInventoryBookedQuantity> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory booked quantities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory booked quantities.
	*
	* @return the number of commerce inventory booked quantities
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}