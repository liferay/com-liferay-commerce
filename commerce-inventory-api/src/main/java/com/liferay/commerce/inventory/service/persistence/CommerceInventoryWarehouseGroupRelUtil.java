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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce inventory warehouse group rel service. This utility wraps {@link com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehouseGroupRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRelPersistence
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryWarehouseGroupRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelUtil {
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
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		getPersistence().clearCache(commerceInventoryWarehouseGroupRel);
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
	public static List<CommerceInventoryWarehouseGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryWarehouseGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryWarehouseGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryWarehouseGroupRel update(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return getPersistence().update(commerceInventoryWarehouseGroupRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryWarehouseGroupRel update(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceInventoryWarehouseGroupRel, serviceContext);
	}

	/**
	* Returns all the commerce inventory warehouse group rels where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching commerce inventory warehouse group rels
	*/
	public static List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end) {
		return getPersistence().findBygroupId(groupId, start, end);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence()
				   .findBygroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBygroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findBygroupId_First(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchBygroupId_First(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence().fetchBygroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findBygroupId_Last(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchBygroupId_Last(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the commerce inventory warehouse group rels before and after the current commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the current commerce inventory warehouse group rel
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel[] findBygroupId_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findBygroupId_PrevAndNext(commerceInventoryWarehouseGroupRelId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the commerce inventory warehouse group rels where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findByG_CWI(long groupId,
		long commerceWarehouseId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence().findByG_CWI(groupId, commerceWarehouseId);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_CWI(
		long groupId, long commerceWarehouseId) {
		return getPersistence().fetchByG_CWI(groupId, commerceWarehouseId);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_CWI(
		long groupId, long commerceWarehouseId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_CWI(groupId, commerceWarehouseId, retrieveFromCache);
	}

	/**
	* Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the commerce inventory warehouse group rel that was removed
	*/
	public static CommerceInventoryWarehouseGroupRel removeByG_CWI(
		long groupId, long commerceWarehouseId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence().removeByG_CWI(groupId, commerceWarehouseId);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63;.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public static int countByG_CWI(long groupId, long commerceWarehouseId) {
		return getPersistence().countByG_CWI(groupId, commerceWarehouseId);
	}

	/**
	* Returns all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rels
	*/
	public static List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary) {
		return getPersistence().findByG_P(groupId, primary);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end) {
		return getPersistence().findByG_P(groupId, primary, start, end);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, primary, start, end, orderByComparator);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findByG_P(
		long groupId, boolean primary, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, primary, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findByG_P_First(
		long groupId, boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findByG_P_First(groupId, primary, orderByComparator);
	}

	/**
	* Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_P_First(
		long groupId, boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, primary, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findByG_P_Last(
		long groupId, boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findByG_P_Last(groupId, primary, orderByComparator);
	}

	/**
	* Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_P_Last(
		long groupId, boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Last(groupId, primary, orderByComparator);
	}

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
	public static CommerceInventoryWarehouseGroupRel[] findByG_P_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findByG_P_PrevAndNext(commerceInventoryWarehouseGroupRelId,
			groupId, primary, orderByComparator);
	}

	/**
	* Removes all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63; from the database.
	*
	* @param groupId the group ID
	* @param primary the primary
	*/
	public static void removeByG_P(long groupId, boolean primary) {
		getPersistence().removeByG_P(groupId, primary);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param primary the primary
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public static int countByG_P(long groupId, boolean primary) {
		return getPersistence().countByG_P(groupId, primary);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findByG_CWI_P(
		long groupId, long commerceWarehouseId, boolean primary)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findByG_CWI_P(groupId, commerceWarehouseId, primary);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(
		long groupId, long commerceWarehouseId, boolean primary) {
		return getPersistence()
				   .fetchByG_CWI_P(groupId, commerceWarehouseId, primary);
	}

	/**
	* Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(
		long groupId, long commerceWarehouseId, boolean primary,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_CWI_P(groupId, commerceWarehouseId, primary,
			retrieveFromCache);
	}

	/**
	* Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; from the database.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the commerce inventory warehouse group rel that was removed
	*/
	public static CommerceInventoryWarehouseGroupRel removeByG_CWI_P(
		long groupId, long commerceWarehouseId, boolean primary)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .removeByG_CWI_P(groupId, commerceWarehouseId, primary);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63;.
	*
	* @param groupId the group ID
	* @param commerceWarehouseId the commerce warehouse ID
	* @param primary the primary
	* @return the number of matching commerce inventory warehouse group rels
	*/
	public static int countByG_CWI_P(long groupId, long commerceWarehouseId,
		boolean primary) {
		return getPersistence()
				   .countByG_CWI_P(groupId, commerceWarehouseId, primary);
	}

	/**
	* Caches the commerce inventory warehouse group rel in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	*/
	public static void cacheResult(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		getPersistence().cacheResult(commerceInventoryWarehouseGroupRel);
	}

	/**
	* Caches the commerce inventory warehouse group rels in the entity cache if it is enabled.
	*
	* @param commerceInventoryWarehouseGroupRels the commerce inventory warehouse group rels
	*/
	public static void cacheResult(
		List<CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels) {
		getPersistence().cacheResult(commerceInventoryWarehouseGroupRels);
	}

	/**
	* Creates a new commerce inventory warehouse group rel with the primary key. Does not add the commerce inventory warehouse group rel to the database.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key for the new commerce inventory warehouse group rel
	* @return the new commerce inventory warehouse group rel
	*/
	public static CommerceInventoryWarehouseGroupRel create(
		long commerceInventoryWarehouseGroupRelId) {
		return getPersistence().create(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Removes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel remove(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence().remove(commerceInventoryWarehouseGroupRelId);
	}

	public static CommerceInventoryWarehouseGroupRel updateImpl(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return getPersistence().updateImpl(commerceInventoryWarehouseGroupRel);
	}

	/**
	* Returns the commerce inventory warehouse group rel with the primary key or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel
	* @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel findByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException {
		return getPersistence()
				   .findByPrimaryKey(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Returns the commerce inventory warehouse group rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel, or <code>null</code> if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	public static CommerceInventoryWarehouseGroupRel fetchByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId) {
		return getPersistence()
				   .fetchByPrimaryKey(commerceInventoryWarehouseGroupRelId);
	}

	public static java.util.Map<java.io.Serializable, CommerceInventoryWarehouseGroupRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce inventory warehouse group rels.
	*
	* @return the commerce inventory warehouse group rels
	*/
	public static List<CommerceInventoryWarehouseGroupRel> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findAll(int start,
		int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommerceInventoryWarehouseGroupRel> findAll(int start,
		int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce inventory warehouse group rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce inventory warehouse group rels.
	*
	* @return the number of commerce inventory warehouse group rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryWarehouseGroupRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryWarehouseGroupRelPersistence, CommerceInventoryWarehouseGroupRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryWarehouseGroupRelPersistence.class);

		ServiceTracker<CommerceInventoryWarehouseGroupRelPersistence, CommerceInventoryWarehouseGroupRelPersistence> serviceTracker =
			new ServiceTracker<CommerceInventoryWarehouseGroupRelPersistence, CommerceInventoryWarehouseGroupRelPersistence>(bundle.getBundleContext(),
				CommerceInventoryWarehouseGroupRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}