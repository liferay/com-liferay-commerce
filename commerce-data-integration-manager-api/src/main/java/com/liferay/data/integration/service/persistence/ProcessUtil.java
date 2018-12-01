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

package com.liferay.data.integration.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.integration.model.Process;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process service. This utility wraps {@link com.liferay.data.integration.service.persistence.impl.ProcessPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessPersistence
 * @see com.liferay.data.integration.service.persistence.impl.ProcessPersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessUtil {
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
	public static void clearCache(Process process) {
		getPersistence().clearCache(process);
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
	public static List<Process> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Process> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Process> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Process update(Process process) {
		return getPersistence().update(process);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Process update(Process process, ServiceContext serviceContext) {
		return getPersistence().update(process, serviceContext);
	}

	/**
	* Returns all the processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching processes
	*/
	public static List<Process> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of matching processes
	*/
	public static List<Process> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByUuid(String uuid, int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByUuid(String uuid, int start, int end,
		OrderByComparator<Process> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByUuid_First(String uuid,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUuid_First(String uuid,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByUuid_Last(String uuid,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUuid_Last(String uuid,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the processes before and after the current process in the ordered set where uuid = &#63;.
	*
	* @param processId the primary key of the current process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process[] findByUuid_PrevAndNext(long processId, String uuid,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processId, uuid, orderByComparator);
	}

	/**
	* Removes all the processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching processes
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByUUID_G(String uuid, long groupId)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process that was removed
	*/
	public static Process removeByUUID_G(String uuid, long groupId)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching processes
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching processes
	*/
	public static List<Process> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of matching processes
	*/
	public static List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the processes before and after the current process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processId the primary key of the current process
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process[] findByUuid_C_PrevAndNext(long processId,
		String uuid, long companyId,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching processes
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching processes
	*/
	public static List<Process> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of matching processes
	*/
	public static List<Process> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Process> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByGroupId_First(long groupId,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByGroupId_First(long groupId,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByGroupId_Last(long groupId,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByGroupId_Last(long groupId,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the processes before and after the current process in the ordered set where groupId = &#63;.
	*
	* @param processId the primary key of the current process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process[] findByGroupId_PrevAndNext(long processId,
		long groupId, OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(processId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the processes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching processes that the user has permission to view
	*/
	public static List<Process> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the processes that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of matching processes that the user has permission to view
	*/
	public static List<Process> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the processes that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching processes that the user has permission to view
	*/
	public static List<Process> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the processes before and after the current process in the ordered set of processes that the user has permission to view where groupId = &#63;.
	*
	* @param processId the primary key of the current process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process[] filterFindByGroupId_PrevAndNext(long processId,
		long groupId, OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(processId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the processes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching processes
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of processes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching processes that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns the process where companyId = &#63; and name = &#63; or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByCompanyId_Name(long companyId, String name)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByCompanyId_Name(companyId, name);
	}

	/**
	* Returns the process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByCompanyId_Name(long companyId, String name) {
		return getPersistence().fetchByCompanyId_Name(companyId, name);
	}

	/**
	* Returns the process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByCompanyId_Name(long companyId, String name,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCompanyId_Name(companyId, name, retrieveFromCache);
	}

	/**
	* Removes the process where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the process that was removed
	*/
	public static Process removeByCompanyId_Name(long companyId, String name)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().removeByCompanyId_Name(companyId, name);
	}

	/**
	* Returns the number of processes where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching processes
	*/
	public static int countByCompanyId_Name(long companyId, String name) {
		return getPersistence().countByCompanyId_Name(companyId, name);
	}

	/**
	* Returns all the processes where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @return the matching processes
	*/
	public static List<Process> findByProcessType_CompanyId(long companyId,
		String processType) {
		return getPersistence()
				   .findByProcessType_CompanyId(companyId, processType);
	}

	/**
	* Returns a range of all the processes where companyId = &#63; and processType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of matching processes
	*/
	public static List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end) {
		return getPersistence()
				   .findByProcessType_CompanyId(companyId, processType, start,
			end);
	}

	/**
	* Returns an ordered range of all the processes where companyId = &#63; and processType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .findByProcessType_CompanyId(companyId, processType, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the processes where companyId = &#63; and processType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching processes
	*/
	public static List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end,
		OrderByComparator<Process> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByProcessType_CompanyId(companyId, processType, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByProcessType_CompanyId_First(long companyId,
		String processType, OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByProcessType_CompanyId_First(companyId, processType,
			orderByComparator);
	}

	/**
	* Returns the first process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByProcessType_CompanyId_First(long companyId,
		String processType, OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .fetchByProcessType_CompanyId_First(companyId, processType,
			orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public static Process findByProcessType_CompanyId_Last(long companyId,
		String processType, OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByProcessType_CompanyId_Last(companyId, processType,
			orderByComparator);
	}

	/**
	* Returns the last process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public static Process fetchByProcessType_CompanyId_Last(long companyId,
		String processType, OrderByComparator<Process> orderByComparator) {
		return getPersistence()
				   .fetchByProcessType_CompanyId_Last(companyId, processType,
			orderByComparator);
	}

	/**
	* Returns the processes before and after the current process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param processId the primary key of the current process
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process[] findByProcessType_CompanyId_PrevAndNext(
		long processId, long companyId, String processType,
		OrderByComparator<Process> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence()
				   .findByProcessType_CompanyId_PrevAndNext(processId,
			companyId, processType, orderByComparator);
	}

	/**
	* Removes all the processes where companyId = &#63; and processType = &#63; from the database.
	*
	* @param companyId the company ID
	* @param processType the process type
	*/
	public static void removeByProcessType_CompanyId(long companyId,
		String processType) {
		getPersistence().removeByProcessType_CompanyId(companyId, processType);
	}

	/**
	* Returns the number of processes where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @return the number of matching processes
	*/
	public static int countByProcessType_CompanyId(long companyId,
		String processType) {
		return getPersistence()
				   .countByProcessType_CompanyId(companyId, processType);
	}

	/**
	* Caches the process in the entity cache if it is enabled.
	*
	* @param process the process
	*/
	public static void cacheResult(Process process) {
		getPersistence().cacheResult(process);
	}

	/**
	* Caches the processes in the entity cache if it is enabled.
	*
	* @param processes the processes
	*/
	public static void cacheResult(List<Process> processes) {
		getPersistence().cacheResult(processes);
	}

	/**
	* Creates a new process with the primary key. Does not add the process to the database.
	*
	* @param processId the primary key for the new process
	* @return the new process
	*/
	public static Process create(long processId) {
		return getPersistence().create(processId);
	}

	/**
	* Removes the process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processId the primary key of the process
	* @return the process that was removed
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process remove(long processId)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().remove(processId);
	}

	public static Process updateImpl(Process process) {
		return getPersistence().updateImpl(process);
	}

	/**
	* Returns the process with the primary key or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param processId the primary key of the process
	* @return the process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public static Process findByPrimaryKey(long processId)
		throws com.liferay.data.integration.exception.NoSuchProcessException {
		return getPersistence().findByPrimaryKey(processId);
	}

	/**
	* Returns the process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processId the primary key of the process
	* @return the process, or <code>null</code> if a process with the primary key could not be found
	*/
	public static Process fetchByPrimaryKey(long processId) {
		return getPersistence().fetchByPrimaryKey(processId);
	}

	public static java.util.Map<java.io.Serializable, Process> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the processes.
	*
	* @return the processes
	*/
	public static List<Process> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @return the range of processes
	*/
	public static List<Process> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of processes
	*/
	public static List<Process> findAll(int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of processes
	* @param end the upper bound of the range of processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of processes
	*/
	public static List<Process> findAll(int start, int end,
		OrderByComparator<Process> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the processes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of processes.
	*
	* @return the number of processes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessPersistence, ProcessPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessPersistence.class);

		ServiceTracker<ProcessPersistence, ProcessPersistence> serviceTracker = new ServiceTracker<ProcessPersistence, ProcessPersistence>(bundle.getBundleContext(),
				ProcessPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}