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

import com.liferay.commerce.inventory.exception.NoSuchInventoryReplenishmentItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the commerce inventory replenishment item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryReplenishmentItemPersistenceImpl
 * @see CommerceInventoryReplenishmentItemUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryReplenishmentItemPersistence
	extends BasePersistence<CommerceInventoryReplenishmentItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryReplenishmentItemUtil} to access the commerce inventory replenishment item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId);

	/**
	* Returns a range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @return the range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByCommerceWarehouseId_First(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByCommerceWarehouseId_First(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	* @param commerceWarehouseId the commerce warehouse ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem[] findByCommerceWarehouseId_PrevAndNext(
		long commerceInventoryReplenishmentItemId, long commerceWarehouseId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Removes all the commerce inventory replenishment items where commerceWarehouseId = &#63; from the database.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	*/
	public void removeByCommerceWarehouseId(long commerceWarehouseId);

	/**
	* Returns the number of commerce inventory replenishment items where commerceWarehouseId = &#63;.
	*
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the number of matching commerce inventory replenishment items
	*/
	public int countByCommerceWarehouseId(long commerceWarehouseId);

	/**
	* Returns all the commerce inventory replenishment items where sku = &#63;.
	*
	* @param sku the sku
	* @return the matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findBysku(
		String sku);

	/**
	* Returns a range of all the commerce inventory replenishment items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @return the range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findBysku(
		String sku, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findBysku(
		String sku, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchBysku_First(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	*
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchBysku_Last(String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where sku = &#63;.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	* @param sku the sku
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem[] findBysku_PrevAndNext(
		long commerceInventoryReplenishmentItemId, String sku,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Removes all the commerce inventory replenishment items where sku = &#63; from the database.
	*
	* @param sku the sku
	*/
	public void removeBysku(String sku);

	/**
	* Returns the number of commerce inventory replenishment items where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory replenishment items
	*/
	public int countBysku(String sku);

	/**
	* Returns all the commerce inventory replenishment items where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @return the matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate);

	/**
	* Returns a range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @return the range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByavailabilityDate_First(
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByavailabilityDate_First(
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByavailabilityDate_Last(
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByavailabilityDate_Last(
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem[] findByavailabilityDate_PrevAndNext(
		long commerceInventoryReplenishmentItemId, Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Removes all the commerce inventory replenishment items where availabilityDate = &#63; from the database.
	*
	* @param availabilityDate the availability date
	*/
	public void removeByavailabilityDate(Date availabilityDate);

	/**
	* Returns the number of commerce inventory replenishment items where availabilityDate = &#63;.
	*
	* @param availabilityDate the availability date
	* @return the number of matching commerce inventory replenishment items
	*/
	public int countByavailabilityDate(Date availabilityDate);

	/**
	* Returns all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @return the matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate);

	/**
	* Returns a range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @return the range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findByS_AD(
		String sku, Date availabilityDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByS_AD_First(String sku,
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByS_AD_First(String sku,
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem findByS_AD_Last(String sku,
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByS_AD_Last(String sku,
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

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
	public CommerceInventoryReplenishmentItem[] findByS_AD_PrevAndNext(
		long commerceInventoryReplenishmentItemId, String sku,
		Date availabilityDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Removes all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63; from the database.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	*/
	public void removeByS_AD(String sku, Date availabilityDate);

	/**
	* Returns the number of commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	*
	* @param sku the sku
	* @param availabilityDate the availability date
	* @return the number of matching commerce inventory replenishment items
	*/
	public int countByS_AD(String sku, Date availabilityDate);

	/**
	* Caches the commerce inventory replenishment item in the entity cache if it is enabled.
	*
	* @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	*/
	public void cacheResult(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem);

	/**
	* Caches the commerce inventory replenishment items in the entity cache if it is enabled.
	*
	* @param commerceInventoryReplenishmentItems the commerce inventory replenishment items
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryReplenishmentItem> commerceInventoryReplenishmentItems);

	/**
	* Creates a new commerce inventory replenishment item with the primary key. Does not add the commerce inventory replenishment item to the database.
	*
	* @param commerceInventoryReplenishmentItemId the primary key for the new commerce inventory replenishment item
	* @return the new commerce inventory replenishment item
	*/
	public CommerceInventoryReplenishmentItem create(
		long commerceInventoryReplenishmentItemId);

	/**
	* Removes the commerce inventory replenishment item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	* @return the commerce inventory replenishment item that was removed
	* @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem remove(
		long commerceInventoryReplenishmentItemId)
		throws NoSuchInventoryReplenishmentItemException;

	public CommerceInventoryReplenishmentItem updateImpl(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem);

	/**
	* Returns the commerce inventory replenishment item with the primary key or throws a {@link NoSuchInventoryReplenishmentItemException} if it could not be found.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	* @return the commerce inventory replenishment item
	* @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem findByPrimaryKey(
		long commerceInventoryReplenishmentItemId)
		throws NoSuchInventoryReplenishmentItemException;

	/**
	* Returns the commerce inventory replenishment item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	* @return the commerce inventory replenishment item, or <code>null</code> if a commerce inventory replenishment item with the primary key could not be found
	*/
	public CommerceInventoryReplenishmentItem fetchByPrimaryKey(
		long commerceInventoryReplenishmentItemId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryReplenishmentItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory replenishment items.
	*
	* @return the commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findAll();

	/**
	* Returns a range of all the commerce inventory replenishment items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @return the range of commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory replenishment items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory replenishment items
	* @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory replenishment items
	*/
	public java.util.List<CommerceInventoryReplenishmentItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory replenishment items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory replenishment items.
	*
	* @return the number of commerce inventory replenishment items
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}