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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce inventory warehouse group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehouseGroupRelPersistenceImpl
 * @see CommerceInventoryWarehouseGroupRelUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseGroupRelPersistence
	extends BasePersistence<CommerceInventoryWarehouseGroupRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseGroupRelUtil} to access the commerce inventory warehouse group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce inventory warehouse group rels where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId);

	/**
	* Returns a range of all the commerce inventory warehouse group rels where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @return the range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findBygroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns the commerce inventory warehouse group rels before and after the current commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the current commerce inventory warehouse group rel
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public CommerceInventoryWarehouseGroupRel[] findBygroupId_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Removes all the commerce inventory warehouse group rels where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeBygroupId(long groupId);

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public int countBygroupId(long groupId);

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findByG_CWI(long groupId,
		long commerceWarehouseId)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI(long groupId,
		long commerceWarehouseId);

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI(long groupId,
		long commerceWarehouseId, boolean retrieveFromCache);

	/**
	* Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the commerce inventory warehouse group rel that was removed
	*/
	public CommerceInventoryWarehouseGroupRel removeByG_CWI(long groupId,
		long commerceWarehouseId)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63;.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public int countByG_CWI(long groupId, long commerceWarehouseId);

	/**
	* Returns all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary);

	/**
	* Returns a range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @return the range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findByG_P_First(long groupId,
		boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_P_First(long groupId,
		boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findByG_P_Last(long groupId,
		boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_P_Last(long groupId,
		boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns the commerce inventory warehouse group rels before and after the current commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the current commerce inventory warehouse group rel
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public CommerceInventoryWarehouseGroupRel[] findByG_P_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Removes all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63; from the database.
	*
	* @param groupId the group ID
	* @param primary the primary
	*/
	public void removeByG_P(long groupId, boolean primary);

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public int countByG_P(long groupId, boolean primary);

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary);

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary, boolean retrieveFromCache);

	/**
	* Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; from the database.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the commerce inventory warehouse group rel that was removed
	*/
	public CommerceInventoryWarehouseGroupRel removeByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public int countByG_CWI_P(long groupId, long commerceWarehouseId,
		boolean primary);

	/**
	* Caches the commerce inventory warehouse group rel in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	*/
	public void cacheResult(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel);

	/**
	* Caches the commerce inventory warehouse group rels in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseGroupRels the commerce inventory warehouse group rels
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels);

	/**
	* Creates a new commerce inventory warehouse group rel with the primary key. Does not add the commerce inventory warehouse group rel to the database.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key for the new commerce inventory warehouse group rel
	* @return the new commerce inventory warehouse group rel
	*/
	public CommerceInventoryWarehouseGroupRel create(
		long commerceInventoryWarehouseGroupRelId);

	/**
	* Removes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public CommerceInventoryWarehouseGroupRel remove(
		long commerceInventoryWarehouseGroupRelId)
		throws NoSuchInventoryWarehouseGroupRelException;

	public CommerceInventoryWarehouseGroupRel updateImpl(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel);

	/**
	* Returns the commerce inventory warehouse group rel with the primary key or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public CommerceInventoryWarehouseGroupRel findByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId)
		throws NoSuchInventoryWarehouseGroupRelException;

	/**
	* Returns the commerce inventory warehouse group rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel, or <code>null</code> if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public CommerceInventoryWarehouseGroupRel fetchByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryWarehouseGroupRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory warehouse group rels.
	*
	* @return the commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findAll();

	/**
	* Returns a range of all the commerce inventory warehouse group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @return the range of commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce inventory warehouse group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory warehouse group rels
	*/
	public java.util.List<CommerceInventoryWarehouseGroupRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory warehouse group rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory warehouse group rels.
	*
	* @return the number of commerce inventory warehouse group rels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}