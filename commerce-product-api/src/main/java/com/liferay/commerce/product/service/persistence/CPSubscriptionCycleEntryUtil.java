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

package com.liferay.commerce.product.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cp subscription cycle entry service. This utility wraps {@link com.liferay.commerce.product.service.persistence.impl.CPSubscriptionCycleEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPSubscriptionCycleEntryPersistence
 * @see com.liferay.commerce.product.service.persistence.impl.CPSubscriptionCycleEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntryUtil {
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
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		getPersistence().clearCache(cpSubscriptionCycleEntry);
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
	public static List<CPSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPSubscriptionCycleEntry update(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return getPersistence().update(cpSubscriptionCycleEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPSubscriptionCycleEntry update(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(cpSubscriptionCycleEntry, serviceContext);
	}

	/**
	* Returns all the cp subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(CPSubscriptionCycleEntryId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the cp subscription cycle entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByUUID_G(String uuid,
		long groupId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the cp subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the cp subscription cycle entry that was removed
	*/
	public static CPSubscriptionCycleEntry removeByUUID_G(String uuid,
		long groupId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid, long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(CPSubscriptionCycleEntryId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the cp subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the cp subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(CPSubscriptionCycleEntryId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the cp subscription cycle entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of cp subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @return the matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId) {
		return getPersistence()
				   .findByCPSubscriptionEntryId(CPSubscriptionEntryId);
	}

	/**
	* Returns a range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end) {
		return getPersistence()
				   .findByCPSubscriptionEntryId(CPSubscriptionEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByCPSubscriptionEntryId(CPSubscriptionEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCPSubscriptionEntryId(CPSubscriptionEntryId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCPSubscriptionEntryId_First(CPSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCPSubscriptionEntryId_First(CPSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCPSubscriptionEntryId_Last(CPSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCPSubscriptionEntryId_Last(CPSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry[] findByCPSubscriptionEntryId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCPSubscriptionEntryId_PrevAndNext(CPSubscriptionCycleEntryId,
			CPSubscriptionEntryId, orderByComparator);
	}

	/**
	* Removes all the cp subscription cycle entries where CPSubscriptionEntryId = &#63; from the database.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	*/
	public static void removeByCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		getPersistence().removeByCPSubscriptionEntryId(CPSubscriptionEntryId);
	}

	/**
	* Returns the number of cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		return getPersistence()
				   .countByCPSubscriptionEntryId(CPSubscriptionEntryId);
	}

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry findByCommerceOrderItemId(
		long commerceOrderItemId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId) {
		return getPersistence().fetchByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCommerceOrderItemId(commerceOrderItemId,
			retrieveFromCache);
	}

	/**
	* Removes the cp subscription cycle entry where commerceOrderItemId = &#63; from the database.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the cp subscription cycle entry that was removed
	*/
	public static CPSubscriptionCycleEntry removeByCommerceOrderItemId(
		long commerceOrderItemId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().removeByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the number of cp subscription cycle entries where commerceOrderItemId = &#63;.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the number of matching cp subscription cycle entries
	*/
	public static int countByCommerceOrderItemId(long commerceOrderItemId) {
		return getPersistence().countByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Caches the cp subscription cycle entry in the entity cache if it is enabled.
	*
	* @param cpSubscriptionCycleEntry the cp subscription cycle entry
	*/
	public static void cacheResult(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		getPersistence().cacheResult(cpSubscriptionCycleEntry);
	}

	/**
	* Caches the cp subscription cycle entries in the entity cache if it is enabled.
	*
	* @param cpSubscriptionCycleEntries the cp subscription cycle entries
	*/
	public static void cacheResult(
		List<CPSubscriptionCycleEntry> cpSubscriptionCycleEntries) {
		getPersistence().cacheResult(cpSubscriptionCycleEntries);
	}

	/**
	* Creates a new cp subscription cycle entry with the primary key. Does not add the cp subscription cycle entry to the database.
	*
	* @param CPSubscriptionCycleEntryId the primary key for the new cp subscription cycle entry
	* @return the new cp subscription cycle entry
	*/
	public static CPSubscriptionCycleEntry create(
		long CPSubscriptionCycleEntryId) {
		return getPersistence().create(CPSubscriptionCycleEntryId);
	}

	/**
	* Removes the cp subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry that was removed
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry remove(
		long CPSubscriptionCycleEntryId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().remove(CPSubscriptionCycleEntryId);
	}

	public static CPSubscriptionCycleEntry updateImpl(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return getPersistence().updateImpl(cpSubscriptionCycleEntry);
	}

	/**
	* Returns the cp subscription cycle entry with the primary key or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry findByPrimaryKey(
		long CPSubscriptionCycleEntryId)
		throws com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException {
		return getPersistence().findByPrimaryKey(CPSubscriptionCycleEntryId);
	}

	/**
	* Returns the cp subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry, or <code>null</code> if a cp subscription cycle entry with the primary key could not be found
	*/
	public static CPSubscriptionCycleEntry fetchByPrimaryKey(
		long CPSubscriptionCycleEntryId) {
		return getPersistence().fetchByPrimaryKey(CPSubscriptionCycleEntryId);
	}

	public static java.util.Map<java.io.Serializable, CPSubscriptionCycleEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cp subscription cycle entries.
	*
	* @return the cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp subscription cycle entries
	*/
	public static List<CPSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cp subscription cycle entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cp subscription cycle entries.
	*
	* @return the number of cp subscription cycle entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CPSubscriptionCycleEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPSubscriptionCycleEntryPersistence, CPSubscriptionCycleEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPSubscriptionCycleEntryPersistence.class);

		ServiceTracker<CPSubscriptionCycleEntryPersistence, CPSubscriptionCycleEntryPersistence> serviceTracker =
			new ServiceTracker<CPSubscriptionCycleEntryPersistence, CPSubscriptionCycleEntryPersistence>(bundle.getBundleContext(),
				CPSubscriptionCycleEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}