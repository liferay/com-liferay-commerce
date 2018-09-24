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

package com.liferay.commerce.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CPSubscriptionEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cp subscription entry service. This utility wraps {@link com.liferay.commerce.service.persistence.impl.CPSubscriptionEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryPersistence
 * @see com.liferay.commerce.service.persistence.impl.CPSubscriptionEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryUtil {
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
	public static void clearCache(CPSubscriptionEntry cpSubscriptionEntry) {
		getPersistence().clearCache(cpSubscriptionEntry);
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
	public static List<CPSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPSubscriptionEntry update(
		CPSubscriptionEntry cpSubscriptionEntry) {
		return getPersistence().update(cpSubscriptionEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPSubscriptionEntry update(
		CPSubscriptionEntry cpSubscriptionEntry, ServiceContext serviceContext) {
		return getPersistence().update(cpSubscriptionEntry, serviceContext);
	}

	/**
	* Returns all the cp subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionEntryId, String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(CPSubscriptionEntryId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the cp subscription entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of cp subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching cp subscription entries
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the cp subscription entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the cp subscription entry that was removed
	*/
	public static CPSubscriptionEntry removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of cp subscription entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching cp subscription entries
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionEntryId, String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(CPSubscriptionEntryId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the cp subscription entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching cp subscription entries
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the cp subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionEntryId, long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(CPSubscriptionEntryId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the cp subscription entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of cp subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching cp subscription entries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the cp subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByactive(boolean active) {
		return getPersistence().findByactive(active);
	}

	/**
	* Returns a range of all the cp subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByactive(boolean active,
		int start, int end) {
		return getPersistence().findByactive(active, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByactive(boolean active,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByactive(active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByactive(boolean active,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByactive(active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByactive_First(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByactive_First(active, orderByComparator);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByactive_First(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByactive_First(active, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByactive_Last(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByactive_Last(active, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByactive_Last(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByactive_Last(active, orderByComparator);
	}

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where active = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry[] findByactive_PrevAndNext(
		long CPSubscriptionEntryId, boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByactive_PrevAndNext(CPSubscriptionEntryId, active,
			orderByComparator);
	}

	/**
	* Removes all the cp subscription entries where active = &#63; from the database.
	*
	* @param active the active
	*/
	public static void removeByactive(boolean active) {
		getPersistence().removeByactive(active);
	}

	/**
	* Returns the number of cp subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the number of matching cp subscription entries
	*/
	public static int countByactive(boolean active) {
		return getPersistence().countByactive(active);
	}

	/**
	* Returns all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByG_U(long groupId, long userId) {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end) {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByG_U_First(long groupId,
		long userId, OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByG_U_First(long groupId,
		long userId, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public static CPSubscriptionEntry fetchByG_U_Last(long groupId,
		long userId, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry[] findByG_U_PrevAndNext(
		long CPSubscriptionEntryId, long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(CPSubscriptionEntryId, groupId,
			userId, orderByComparator);
	}

	/**
	* Removes all the cp subscription entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public static void removeByG_U(long groupId, long userId) {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Returns the number of cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching cp subscription entries
	*/
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Caches the cp subscription entry in the entity cache if it is enabled.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	*/
	public static void cacheResult(CPSubscriptionEntry cpSubscriptionEntry) {
		getPersistence().cacheResult(cpSubscriptionEntry);
	}

	/**
	* Caches the cp subscription entries in the entity cache if it is enabled.
	*
	* @param cpSubscriptionEntries the cp subscription entries
	*/
	public static void cacheResult(
		List<CPSubscriptionEntry> cpSubscriptionEntries) {
		getPersistence().cacheResult(cpSubscriptionEntries);
	}

	/**
	* Creates a new cp subscription entry with the primary key. Does not add the cp subscription entry to the database.
	*
	* @param CPSubscriptionEntryId the primary key for the new cp subscription entry
	* @return the new cp subscription entry
	*/
	public static CPSubscriptionEntry create(long CPSubscriptionEntryId) {
		return getPersistence().create(CPSubscriptionEntryId);
	}

	/**
	* Removes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry that was removed
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry remove(long CPSubscriptionEntryId)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().remove(CPSubscriptionEntryId);
	}

	public static CPSubscriptionEntry updateImpl(
		CPSubscriptionEntry cpSubscriptionEntry) {
		return getPersistence().updateImpl(cpSubscriptionEntry);
	}

	/**
	* Returns the cp subscription entry with the primary key or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry findByPrimaryKey(
		long CPSubscriptionEntryId)
		throws com.liferay.commerce.exception.NoSuchCPSubscriptionEntryException {
		return getPersistence().findByPrimaryKey(CPSubscriptionEntryId);
	}

	/**
	* Returns the cp subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry, or <code>null</code> if a cp subscription entry with the primary key could not be found
	*/
	public static CPSubscriptionEntry fetchByPrimaryKey(
		long CPSubscriptionEntryId) {
		return getPersistence().fetchByPrimaryKey(CPSubscriptionEntryId);
	}

	public static java.util.Map<java.io.Serializable, CPSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cp subscription entries.
	*
	* @return the cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp subscription entries
	*/
	public static List<CPSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cp subscription entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cp subscription entries.
	*
	* @return the number of cp subscription entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CPSubscriptionEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPSubscriptionEntryPersistence, CPSubscriptionEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPSubscriptionEntryPersistence.class);

		ServiceTracker<CPSubscriptionEntryPersistence, CPSubscriptionEntryPersistence> serviceTracker =
			new ServiceTracker<CPSubscriptionEntryPersistence, CPSubscriptionEntryPersistence>(bundle.getBundleContext(),
				CPSubscriptionEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}