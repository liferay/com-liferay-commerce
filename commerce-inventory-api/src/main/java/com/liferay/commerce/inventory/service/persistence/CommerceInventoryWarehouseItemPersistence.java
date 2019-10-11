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

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the commerce inventory warehouse item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseItemPersistence
	extends BasePersistence<CommerceInventoryWarehouseItem> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseItemUtil} to access the commerce inventory warehouse item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CommerceInventoryWarehouseItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the commerce inventory warehouse items where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem> findByCompanyId(
		long companyId);

	/**
	 * Returns a range of all the commerce inventory warehouse items where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of matching commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where companyId = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public CommerceInventoryWarehouseItem[] findByCompanyId_PrevAndNext(
			long commerceInventoryWarehouseItemId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Removes all the commerce inventory warehouse items where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of commerce inventory warehouse items where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce inventory warehouse items
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the matching commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(long commerceInventoryWarehouseId);

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
	public java.util.List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseItem>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem
			findByCommerceInventoryWarehouseId_First(
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem
		fetchByCommerceInventoryWarehouseId_First(
			long commerceInventoryWarehouseId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem
			findByCommerceInventoryWarehouseId_Last(
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem
		fetchByCommerceInventoryWarehouseId_Last(
			long commerceInventoryWarehouseId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseItem> orderByComparator);

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	public CommerceInventoryWarehouseItem[]
			findByCommerceInventoryWarehouseId_PrevAndNext(
				long commerceInventoryWarehouseItemId,
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Removes all the commerce inventory warehouse items where commerceInventoryWarehouseId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 */
	public void removeByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the number of commerce inventory warehouse items where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the number of matching commerce inventory warehouse items
	 */
	public int countByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or throws a <code>NoSuchInventoryWarehouseItemException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem findByC_S(
			long commerceInventoryWarehouseId, String sku)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByC_S(
		long commerceInventoryWarehouseId, String sku);

	/**
	 * Returns the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByC_S(
		long commerceInventoryWarehouseId, String sku, boolean useFinderCache);

	/**
	 * Removes the commerce inventory warehouse item where commerceInventoryWarehouseId = &#63; and sku = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the commerce inventory warehouse item that was removed
	 */
	public CommerceInventoryWarehouseItem removeByC_S(
			long commerceInventoryWarehouseId, String sku)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the number of commerce inventory warehouse items where commerceInventoryWarehouseId = &#63; and sku = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param sku the sku
	 * @return the number of matching commerce inventory warehouse items
	 */
	public int countByC_S(long commerceInventoryWarehouseId, String sku);

	/**
	 * Returns the commerce inventory warehouse item where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchInventoryWarehouseItemException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the commerce inventory warehouse item where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByC_ERC(
		long companyId, String externalReferenceCode);

	/**
	 * Returns the commerce inventory warehouse item where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	public CommerceInventoryWarehouseItem fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache);

	/**
	 * Removes the commerce inventory warehouse item where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce inventory warehouse item that was removed
	 */
	public CommerceInventoryWarehouseItem removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchInventoryWarehouseItemException;

	/**
	 * Returns the number of commerce inventory warehouse items where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce inventory warehouse items
	 */
	public int countByC_ERC(long companyId, String externalReferenceCode);

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
		java.util.List<CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems);

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
	 * Returns the commerce inventory warehouse item with the primary key or throws a <code>NoSuchInventoryWarehouseItemException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of commerce inventory warehouse items
	 */
	public java.util.List<CommerceInventoryWarehouseItem> findAll(
		int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseItem> orderByComparator,
		boolean useFinderCache);

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
	public Set<String> getBadColumnNames();

}