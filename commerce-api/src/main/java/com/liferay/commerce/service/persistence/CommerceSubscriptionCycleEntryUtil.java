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

import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce subscription cycle entry service. This utility wraps {@link com.liferay.commerce.service.persistence.impl.CommerceSubscriptionCycleEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryPersistence
 * @see com.liferay.commerce.service.persistence.impl.CommerceSubscriptionCycleEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryUtil {
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
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		getPersistence().clearCache(commerceSubscriptionCycleEntry);
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
	public static List<CommerceSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceSubscriptionCycleEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceSubscriptionCycleEntry update(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return getPersistence().update(commerceSubscriptionCycleEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceSubscriptionCycleEntry update(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceSubscriptionCycleEntry, serviceContext);
	}

	/**
	* Returns all the commerce subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByUuid_First(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUuid_First(
		String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByUuid_Last(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(commerceSubscriptionCycleEntryId,
			uuid, orderByComparator);
	}

	/**
	* Removes all the commerce subscription cycle entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByUUID_G(String uuid,
		long groupId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the commerce subscription cycle entry that was removed
	*/
	public static CommerceSubscriptionCycleEntry removeByUUID_G(String uuid,
		long groupId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(commerceSubscriptionCycleEntryId,
			uuid, companyId, orderByComparator);
	}

	/**
	* Removes all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the commerce subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByGroupId_First(
		long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByGroupId_First(
		long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByGroupId_Last(
		long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByGroupId_Last(
		long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long commerceSubscriptionCycleEntryId, long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(commerceSubscriptionCycleEntryId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the commerce subscription cycle entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of commerce subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByCommerceOrderItemId(
		long commerceOrderItemId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().findByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId) {
		return getPersistence().fetchByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCommerceOrderItemId(commerceOrderItemId,
			retrieveFromCache);
	}

	/**
	* Removes the commerce subscription cycle entry where commerceOrderItemId = &#63; from the database.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the commerce subscription cycle entry that was removed
	*/
	public static CommerceSubscriptionCycleEntry removeByCommerceOrderItemId(
		long commerceOrderItemId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().removeByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns the number of commerce subscription cycle entries where commerceOrderItemId = &#63;.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByCommerceOrderItemId(long commerceOrderItemId) {
		return getPersistence().countByCommerceOrderItemId(commerceOrderItemId);
	}

	/**
	* Returns all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @return the matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	/**
	* Returns a range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end) {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId_First(commerceSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceSubscriptionEntryId_First(commerceSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId_Last(commerceSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceSubscriptionEntryId_Last(commerceSubscriptionEntryId,
			orderByComparator);
	}

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry[] findByCommerceSubscriptionEntryId_PrevAndNext(
		long commerceSubscriptionCycleEntryId,
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByCommerceSubscriptionEntryId_PrevAndNext(commerceSubscriptionCycleEntryId,
			commerceSubscriptionEntryId, orderByComparator);
	}

	/**
	* Removes all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63; from the database.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	*/
	public static void removeByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		getPersistence()
			.removeByCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	/**
	* Returns the number of commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public static int countByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		return getPersistence()
				   .countByCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
	}

	/**
	* Caches the commerce subscription cycle entry in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	*/
	public static void cacheResult(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		getPersistence().cacheResult(commerceSubscriptionCycleEntry);
	}

	/**
	* Caches the commerce subscription cycle entries in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionCycleEntries the commerce subscription cycle entries
	*/
	public static void cacheResult(
		List<CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries) {
		getPersistence().cacheResult(commerceSubscriptionCycleEntries);
	}

	/**
	* Creates a new commerce subscription cycle entry with the primary key. Does not add the commerce subscription cycle entry to the database.
	*
	* @param commerceSubscriptionCycleEntryId the primary key for the new commerce subscription cycle entry
	* @return the new commerce subscription cycle entry
	*/
	public static CommerceSubscriptionCycleEntry create(
		long commerceSubscriptionCycleEntryId) {
		return getPersistence().create(commerceSubscriptionCycleEntryId);
	}

	/**
	* Removes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry remove(
		long commerceSubscriptionCycleEntryId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence().remove(commerceSubscriptionCycleEntryId);
	}

	public static CommerceSubscriptionCycleEntry updateImpl(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		return getPersistence().updateImpl(commerceSubscriptionCycleEntry);
	}

	/**
	* Returns the commerce subscription cycle entry with the primary key or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry findByPrimaryKey(
		long commerceSubscriptionCycleEntryId)
		throws com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException {
		return getPersistence()
				   .findByPrimaryKey(commerceSubscriptionCycleEntryId);
	}

	/**
	* Returns the commerce subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry, or <code>null</code> if a commerce subscription cycle entry with the primary key could not be found
	*/
	public static CommerceSubscriptionCycleEntry fetchByPrimaryKey(
		long commerceSubscriptionCycleEntryId) {
		return getPersistence()
				   .fetchByPrimaryKey(commerceSubscriptionCycleEntryId);
	}

	public static java.util.Map<java.io.Serializable, CommerceSubscriptionCycleEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce subscription cycle entries.
	*
	* @return the commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce subscription cycle entries
	*/
	public static List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce subscription cycle entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce subscription cycle entries.
	*
	* @return the number of commerce subscription cycle entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceSubscriptionCycleEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceSubscriptionCycleEntryPersistence, CommerceSubscriptionCycleEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceSubscriptionCycleEntryPersistence.class);

		ServiceTracker<CommerceSubscriptionCycleEntryPersistence, CommerceSubscriptionCycleEntryPersistence> serviceTracker =
			new ServiceTracker<CommerceSubscriptionCycleEntryPersistence, CommerceSubscriptionCycleEntryPersistence>(bundle.getBundleContext(),
				CommerceSubscriptionCycleEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}