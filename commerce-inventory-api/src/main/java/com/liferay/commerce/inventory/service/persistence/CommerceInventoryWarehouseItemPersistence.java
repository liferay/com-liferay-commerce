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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce inventory warehouse item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehouseItemPersistenceImpl
 * @see CommerceInventoryWarehouseItemUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseItemPersistence
	extends BasePersistence<CommerceInventoryWarehouseItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseItemUtil} to access the commerce inventory warehouse item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId);

	/**
	* Returns a range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @return the range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem findByCommerceWarehouseId_First(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the first commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchByCommerceWarehouseId_First(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem findByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the last commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	*/
	public CommerceInventoryWarehouseItem[] findByCommerceWarehouseId_PrevAndNext(
		long commerceInventoryWarehouseItemId, long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Removes all the commerce inventory warehouse items where commerceWarehouseId = &#63; from the database.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	*/
	public void removeByCommerceWarehouseId(long commerceWarehouseId);

	/**
	* Returns the number of commerce inventory warehouse items where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the number of matching commerce inventory warehouse items
	*/
	public int countByCommerceWarehouseId(long commerceWarehouseId);

	/**
	* Returns all the commerce inventory warehouse items where sku = &#63;.
	*
	* @param sku the sku
	* @return the matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findBysku(String sku);

	/**
	* Returns a range of all the commerce inventory warehouse items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @return the range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findBysku(
		String sku, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem findBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem findBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where sku = &#63;.
	*
	* @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	*/
	public CommerceInventoryWarehouseItem[] findBysku_PrevAndNext(
		long commerceInventoryWarehouseItemId, String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Removes all the commerce inventory warehouse items where sku = &#63; from the database.
	*
	* @param sku the sku
	*/
	public void removeBysku(String sku);

	/**
	* Returns the number of commerce inventory warehouse items where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory warehouse items
	*/
	public int countBysku(String sku);

	/**
	* Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or throws a {@link NoSuchInventoryWarehouseItemException} if it could not be found.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param sku the sku
	* @return the matching commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem findByC_S(long commerceWarehouseId,
		String sku) throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param sku the sku
	* @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchByC_S(long commerceWarehouseId,
		String sku);

	/**
	* Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param sku the sku
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	*/
	public CommerceInventoryWarehouseItem fetchByC_S(long commerceWarehouseId,
		String sku, boolean retrieveFromCache);

	/**
	* Removes the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; from the database.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param sku the sku
	* @return the commerce inventory warehouse item that was removed
	*/
	public CommerceInventoryWarehouseItem removeByC_S(
		long commerceWarehouseId, String sku)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the number of commerce inventory warehouse items where commerceWarehouseId = &#63; and sku = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param sku the sku
	* @return the number of matching commerce inventory warehouse items
	*/
	public int countByC_S(long commerceWarehouseId, String sku);

	/**
	* Caches the commerce inventory warehouse item in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseItem the commerce inventory warehouse item
	*/
	public void cacheResult(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem);

	/**
	* Caches the commerce inventory warehouse items in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseItems the commerce inventory warehouse items
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems);

	/**
	* Creates a new commerce inventory warehouse item with the primary key. Does not add the commerce inventory warehouse item to the database.
	*
	* @param commerceInventoryWarehouseItemId the primary key for the new commerce inventory warehouse item
	* @return the new commerce inventory warehouse item
	*/
	public CommerceInventoryWarehouseItem create(
		long commerceInventoryWarehouseItemId);

	/**
	* Removes the commerce inventory warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	* @return the commerce inventory warehouse item that was removed
	* @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	*/
	public CommerceInventoryWarehouseItem remove(
		long commerceInventoryWarehouseItemId)
		throws NoSuchInventoryWarehouseItemException;

	public CommerceInventoryWarehouseItem updateImpl(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem);

	/**
	* Returns the commerce inventory warehouse item with the primary key or throws a {@link NoSuchInventoryWarehouseItemException} if it could not be found.
	*
	* @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	* @return the commerce inventory warehouse item
	* @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	*/
	public CommerceInventoryWarehouseItem findByPrimaryKey(
		long commerceInventoryWarehouseItemId)
		throws NoSuchInventoryWarehouseItemException;

	/**
	* Returns the commerce inventory warehouse item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	* @return the commerce inventory warehouse item, or <code>null</code> if a commerce inventory warehouse item with the primary key could not be found
	*/
	public CommerceInventoryWarehouseItem fetchByPrimaryKey(
		long commerceInventoryWarehouseItemId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryWarehouseItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory warehouse items.
	*
	* @return the commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findAll();

	/**
	* Returns a range of all the commerce inventory warehouse items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @return the range of commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse items
	* @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory warehouse items
	*/
	public java.util.List<CommerceInventoryWarehouseItem> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory warehouse items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory warehouse items.
	*
	* @return the number of commerce inventory warehouse items
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}