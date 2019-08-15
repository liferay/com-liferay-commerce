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

package com.liferay.commerce.wish.list.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce wish list service. This utility wraps <code>com.liferay.commerce.wish.list.service.persistence.impl.CommerceWishListPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see CommerceWishListPersistence
 * @generated
 */
@ProviderType
public class CommerceWishListUtil {

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
	public static void clearCache(CommerceWishList commerceWishList) {
		getPersistence().clearCache(commerceWishList);
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
	public static Map<Serializable, CommerceWishList> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceWishList> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceWishList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceWishList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceWishList update(CommerceWishList commerceWishList) {
		return getPersistence().update(commerceWishList);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceWishList update(
		CommerceWishList commerceWishList, ServiceContext serviceContext) {

		return getPersistence().update(commerceWishList, serviceContext);
	}

	/**
	 * Returns all the commerce wish lists where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the commerce wish lists where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUuid_First(
			String uuid, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUuid_First(
		String uuid, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUuid_Last(
			String uuid, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUuid_Last(
		String uuid, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where uuid = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByUuid_PrevAndNext(
			long commerceWishListId, String uuid,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_PrevAndNext(
			commerceWishListId, uuid, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of commerce wish lists where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce wish lists
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the commerce wish list where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchWishListException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce wish list where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce wish list where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the commerce wish list where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce wish list that was removed
	 */
	public static CommerceWishList removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of commerce wish lists where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce wish lists
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the commerce wish lists where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce wish lists where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByUuid_C_PrevAndNext(
			long commerceWishListId, String uuid, long companyId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUuid_C_PrevAndNext(
			commerceWishListId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of commerce wish lists where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce wish lists
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the commerce wish lists where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the commerce wish lists where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByGroupId_First(
			long groupId, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByGroupId_First(
		long groupId, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByGroupId_Last(
			long groupId, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByGroupId_Last(
		long groupId, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where groupId = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByGroupId_PrevAndNext(
			long commerceWishListId, long groupId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByGroupId_PrevAndNext(
			commerceWishListId, groupId, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of commerce wish lists where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce wish lists
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the commerce wish lists where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the commerce wish lists where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUserId_First(
			long userId, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUserId_First(
		long userId, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByUserId_Last(
			long userId, OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByUserId_Last(
		long userId, OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where userId = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByUserId_PrevAndNext(
			long commerceWishListId, long userId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByUserId_PrevAndNext(
			commerceWishListId, userId, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of commerce wish lists where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching commerce wish lists
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the commerce wish lists where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U(long groupId, long userId) {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	 * Returns a range of all the commerce wish lists where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U(
		long groupId, long userId, int start, int end) {

		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByG_U_First(
			long groupId, long userId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByG_U_First(
		long groupId, long userId,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByG_U_Last(
			long groupId, long userId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByG_U_Last(
		long groupId, long userId,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByG_U_PrevAndNext(
			long commerceWishListId, long groupId, long userId,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_PrevAndNext(
			commerceWishListId, groupId, userId, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public static void removeByG_U(long groupId, long userId) {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	 * Returns the number of commerce wish lists where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching commerce wish lists
	 */
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	 * Returns all the commerce wish lists where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByU_LtC(
		long userId, Date createDate) {

		return getPersistence().findByU_LtC(userId, createDate);
	}

	/**
	 * Returns a range of all the commerce wish lists where userId = &#63; and createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByU_LtC(
		long userId, Date createDate, int start, int end) {

		return getPersistence().findByU_LtC(userId, createDate, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where userId = &#63; and createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByU_LtC(
		long userId, Date createDate, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByU_LtC(
			userId, createDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where userId = &#63; and createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByU_LtC(
		long userId, Date createDate, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_LtC(
			userId, createDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByU_LtC_First(
			long userId, Date createDate,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByU_LtC_First(
			userId, createDate, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByU_LtC_First(
		long userId, Date createDate,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByU_LtC_First(
			userId, createDate, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByU_LtC_Last(
			long userId, Date createDate,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByU_LtC_Last(
			userId, createDate, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByU_LtC_Last(
		long userId, Date createDate,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByU_LtC_Last(
			userId, createDate, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByU_LtC_PrevAndNext(
			long commerceWishListId, long userId, Date createDate,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByU_LtC_PrevAndNext(
			commerceWishListId, userId, createDate, orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where userId = &#63; and createDate &lt; &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 */
	public static void removeByU_LtC(long userId, Date createDate) {
		getPersistence().removeByU_LtC(userId, createDate);
	}

	/**
	 * Returns the number of commerce wish lists where userId = &#63; and createDate &lt; &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the number of matching commerce wish lists
	 */
	public static int countByU_LtC(long userId, Date createDate) {
		return getPersistence().countByU_LtC(userId, createDate);
	}

	/**
	 * Returns all the commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @return the matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U_D(
		long groupId, long userId, boolean defaultWishList) {

		return getPersistence().findByG_U_D(groupId, userId, defaultWishList);
	}

	/**
	 * Returns a range of all the commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U_D(
		long groupId, long userId, boolean defaultWishList, int start,
		int end) {

		return getPersistence().findByG_U_D(
			groupId, userId, defaultWishList, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U_D(
		long groupId, long userId, boolean defaultWishList, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findByG_U_D(
			groupId, userId, defaultWishList, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce wish lists
	 */
	public static List<CommerceWishList> findByG_U_D(
		long groupId, long userId, boolean defaultWishList, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U_D(
			groupId, userId, defaultWishList, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByG_U_D_First(
			long groupId, long userId, boolean defaultWishList,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_D_First(
			groupId, userId, defaultWishList, orderByComparator);
	}

	/**
	 * Returns the first commerce wish list in the ordered set where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByG_U_D_First(
		long groupId, long userId, boolean defaultWishList,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByG_U_D_First(
			groupId, userId, defaultWishList, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list
	 * @throws NoSuchWishListException if a matching commerce wish list could not be found
	 */
	public static CommerceWishList findByG_U_D_Last(
			long groupId, long userId, boolean defaultWishList,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_D_Last(
			groupId, userId, defaultWishList, orderByComparator);
	}

	/**
	 * Returns the last commerce wish list in the ordered set where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	public static CommerceWishList fetchByG_U_D_Last(
		long groupId, long userId, boolean defaultWishList,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().fetchByG_U_D_Last(
			groupId, userId, defaultWishList, orderByComparator);
	}

	/**
	 * Returns the commerce wish lists before and after the current commerce wish list in the ordered set where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param commerceWishListId the primary key of the current commerce wish list
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList[] findByG_U_D_PrevAndNext(
			long commerceWishListId, long groupId, long userId,
			boolean defaultWishList,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByG_U_D_PrevAndNext(
			commerceWishListId, groupId, userId, defaultWishList,
			orderByComparator);
	}

	/**
	 * Removes all the commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 */
	public static void removeByG_U_D(
		long groupId, long userId, boolean defaultWishList) {

		getPersistence().removeByG_U_D(groupId, userId, defaultWishList);
	}

	/**
	 * Returns the number of commerce wish lists where groupId = &#63; and userId = &#63; and defaultWishList = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param defaultWishList the default wish list
	 * @return the number of matching commerce wish lists
	 */
	public static int countByG_U_D(
		long groupId, long userId, boolean defaultWishList) {

		return getPersistence().countByG_U_D(groupId, userId, defaultWishList);
	}

	/**
	 * Caches the commerce wish list in the entity cache if it is enabled.
	 *
	 * @param commerceWishList the commerce wish list
	 */
	public static void cacheResult(CommerceWishList commerceWishList) {
		getPersistence().cacheResult(commerceWishList);
	}

	/**
	 * Caches the commerce wish lists in the entity cache if it is enabled.
	 *
	 * @param commerceWishLists the commerce wish lists
	 */
	public static void cacheResult(List<CommerceWishList> commerceWishLists) {
		getPersistence().cacheResult(commerceWishLists);
	}

	/**
	 * Creates a new commerce wish list with the primary key. Does not add the commerce wish list to the database.
	 *
	 * @param commerceWishListId the primary key for the new commerce wish list
	 * @return the new commerce wish list
	 */
	public static CommerceWishList create(long commerceWishListId) {
		return getPersistence().create(commerceWishListId);
	}

	/**
	 * Removes the commerce wish list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListId the primary key of the commerce wish list
	 * @return the commerce wish list that was removed
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList remove(long commerceWishListId)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().remove(commerceWishListId);
	}

	public static CommerceWishList updateImpl(
		CommerceWishList commerceWishList) {

		return getPersistence().updateImpl(commerceWishList);
	}

	/**
	 * Returns the commerce wish list with the primary key or throws a <code>NoSuchWishListException</code> if it could not be found.
	 *
	 * @param commerceWishListId the primary key of the commerce wish list
	 * @return the commerce wish list
	 * @throws NoSuchWishListException if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList findByPrimaryKey(long commerceWishListId)
		throws com.liferay.commerce.wish.list.exception.
			NoSuchWishListException {

		return getPersistence().findByPrimaryKey(commerceWishListId);
	}

	/**
	 * Returns the commerce wish list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceWishListId the primary key of the commerce wish list
	 * @return the commerce wish list, or <code>null</code> if a commerce wish list with the primary key could not be found
	 */
	public static CommerceWishList fetchByPrimaryKey(long commerceWishListId) {
		return getPersistence().fetchByPrimaryKey(commerceWishListId);
	}

	/**
	 * Returns all the commerce wish lists.
	 *
	 * @return the commerce wish lists
	 */
	public static List<CommerceWishList> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce wish lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of commerce wish lists
	 */
	public static List<CommerceWishList> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce wish lists
	 */
	public static List<CommerceWishList> findAll(
		int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce wish lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce wish lists
	 */
	public static List<CommerceWishList> findAll(
		int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce wish lists from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce wish lists.
	 *
	 * @return the number of commerce wish lists
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceWishListPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceWishListPersistence, CommerceWishListPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceWishListPersistence.class);

		ServiceTracker<CommerceWishListPersistence, CommerceWishListPersistence>
			serviceTracker =
				new ServiceTracker
					<CommerceWishListPersistence, CommerceWishListPersistence>(
						bundle.getBundleContext(),
						CommerceWishListPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}