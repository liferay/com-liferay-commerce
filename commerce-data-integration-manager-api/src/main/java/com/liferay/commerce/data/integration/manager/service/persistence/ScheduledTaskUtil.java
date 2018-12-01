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

package com.liferay.commerce.data.integration.manager.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.model.ScheduledTask;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the scheduled task service. This utility wraps {@link com.liferay.commerce.data.integration.manager.service.persistence.impl.ScheduledTaskPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskPersistence
 * @see com.liferay.commerce.data.integration.manager.service.persistence.impl.ScheduledTaskPersistenceImpl
 * @generated
 */
@ProviderType
public class ScheduledTaskUtil {
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
	public static void clearCache(ScheduledTask scheduledTask) {
		getPersistence().clearCache(scheduledTask);
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
	public static List<ScheduledTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ScheduledTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ScheduledTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ScheduledTask update(ScheduledTask scheduledTask) {
		return getPersistence().update(scheduledTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ScheduledTask update(ScheduledTask scheduledTask,
		ServiceContext serviceContext) {
		return getPersistence().update(scheduledTask, serviceContext);
	}

	/**
	* Returns all the scheduled tasks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the scheduled tasks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid(String uuid, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid(String uuid, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByUuid_First(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUuid_First(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByUuid_Last(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUuid_Last(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where uuid = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByUuid_PrevAndNext(long scheduledTaskId,
		String uuid, OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByUuid_PrevAndNext(scheduledTaskId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of scheduled tasks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching scheduled tasks
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the scheduled task where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the scheduled task that was removed
	*/
	public static ScheduledTask removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of scheduled tasks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching scheduled tasks
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByUuid_C_PrevAndNext(
		long scheduledTaskId, String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(scheduledTaskId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching scheduled tasks
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the scheduled tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the scheduled tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_First(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_First(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_Last(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_Last(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByGroupId_PrevAndNext(
		long scheduledTaskId, long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(scheduledTaskId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId(long groupId,
		int start, int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId(long groupId,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] filterFindByGroupId_PrevAndNext(
		long scheduledTaskId, long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(scheduledTaskId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of scheduled tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching scheduled tasks
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the scheduled tasks where active = &#63;.
	*
	* @param active the active
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByActive(boolean active) {
		return getPersistence().findByActive(active);
	}

	/**
	* Returns a range of all the scheduled tasks where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByActive(boolean active, int start,
		int end) {
		return getPersistence().findByActive(active, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByActive(boolean active, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByActive(boolean active, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByActive_First(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByActive_First(active, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByActive_First(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByActive_First(active, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByActive_Last(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByActive_Last(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where active = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByActive_PrevAndNext(
		long scheduledTaskId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByActive_PrevAndNext(scheduledTaskId, active,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where active = &#63; from the database.
	*
	* @param active the active
	*/
	public static void removeByActive(boolean active) {
		getPersistence().removeByActive(active);
	}

	/**
	* Returns the number of scheduled tasks where active = &#63;.
	*
	* @param active the active
	* @return the number of matching scheduled tasks
	*/
	public static int countByActive(boolean active) {
		return getPersistence().countByActive(active);
	}

	/**
	* Returns all the scheduled tasks where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByEnabled(boolean enabled) {
		return getPersistence().findByEnabled(enabled);
	}

	/**
	* Returns a range of all the scheduled tasks where enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByEnabled(boolean enabled, int start,
		int end) {
		return getPersistence().findByEnabled(enabled, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByEnabled(boolean enabled, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByEnabled(enabled, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByEnabled(boolean enabled, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEnabled(enabled, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByEnabled_First(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByEnabled_First(enabled, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByEnabled_First(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByEnabled_First(enabled, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByEnabled_Last(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByEnabled_Last(enabled, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByEnabled_Last(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().fetchByEnabled_Last(enabled, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where enabled = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByEnabled_PrevAndNext(
		long scheduledTaskId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByEnabled_PrevAndNext(scheduledTaskId, enabled,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where enabled = &#63; from the database.
	*
	* @param enabled the enabled
	*/
	public static void removeByEnabled(boolean enabled) {
		getPersistence().removeByEnabled(enabled);
	}

	/**
	* Returns the number of scheduled tasks where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the number of matching scheduled tasks
	*/
	public static int countByEnabled(boolean enabled) {
		return getPersistence().countByEnabled(enabled);
	}

	/**
	* Returns all the scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active) {
		return getPersistence().findByGroupId_Active(groupId, active);
	}

	/**
	* Returns a range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end) {
		return getPersistence().findByGroupId_Active(groupId, active, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByGroupId_Active(groupId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId_Active(groupId, active, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_Active_First(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Active_First(groupId, active,
			orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_Active_First(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Active_First(groupId, active,
			orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_Active_Last(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Active_Last(groupId, active, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_Active_Last(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Active_Last(groupId, active,
			orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Active_PrevAndNext(scheduledTaskId, groupId,
			active, orderByComparator);
	}

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active) {
		return getPersistence().filterFindByGroupId_Active(groupId, active);
	}

	/**
	* Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active, int start, int end) {
		return getPersistence()
				   .filterFindByGroupId_Active(groupId, active, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId_Active(groupId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] filterFindByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .filterFindByGroupId_Active_PrevAndNext(scheduledTaskId,
			groupId, active, orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where groupId = &#63; and active = &#63; from the database.
	*
	* @param groupId the group ID
	* @param active the active
	*/
	public static void removeByGroupId_Active(long groupId, boolean active) {
		getPersistence().removeByGroupId_Active(groupId, active);
	}

	/**
	* Returns the number of scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching scheduled tasks
	*/
	public static int countByGroupId_Active(long groupId, boolean active) {
		return getPersistence().countByGroupId_Active(groupId, active);
	}

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public static int filterCountByGroupId_Active(long groupId, boolean active) {
		return getPersistence().filterCountByGroupId_Active(groupId, active);
	}

	/**
	* Returns all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled) {
		return getPersistence().findByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Returns a range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end) {
		return getPersistence()
				   .findByGroupId_Enabled(groupId, enabled, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByGroupId_Enabled(groupId, enabled, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId_Enabled(groupId, enabled, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_Enabled_First(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Enabled_First(groupId, enabled,
			orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_Enabled_First(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Enabled_First(groupId, enabled,
			orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByGroupId_Enabled_Last(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Enabled_Last(groupId, enabled,
			orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByGroupId_Enabled_Last(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Enabled_Last(groupId, enabled,
			orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByGroupId_Enabled_PrevAndNext(scheduledTaskId, groupId,
			enabled, orderByComparator);
	}

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled) {
		return getPersistence().filterFindByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end) {
		return getPersistence()
				   .filterFindByGroupId_Enabled(groupId, enabled, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks that the user has permission to view
	*/
	public static List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId_Enabled(groupId, enabled, start, end,
			orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] filterFindByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .filterFindByGroupId_Enabled_PrevAndNext(scheduledTaskId,
			groupId, enabled, orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where groupId = &#63; and enabled = &#63; from the database.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	*/
	public static void removeByGroupId_Enabled(long groupId, boolean enabled) {
		getPersistence().removeByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Returns the number of scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the number of matching scheduled tasks
	*/
	public static int countByGroupId_Enabled(long groupId, boolean enabled) {
		return getPersistence().countByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public static int filterCountByGroupId_Enabled(long groupId, boolean enabled) {
		return getPersistence().filterCountByGroupId_Enabled(groupId, enabled);
	}

	/**
	* Returns all the scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching scheduled tasks
	*/
	public static List<ScheduledTask> findByName(long companyId, String name) {
		return getPersistence().findByName(companyId, name);
	}

	/**
	* Returns a range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByName(long companyId, String name,
		int start, int end) {
		return getPersistence().findByName(companyId, name, start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .findByName(companyId, name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching scheduled tasks
	*/
	public static List<ScheduledTask> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(companyId, name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByName_First(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByName_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByName_First(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByName_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public static ScheduledTask findByName_Last(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByName_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public static ScheduledTask fetchByName_Last(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence()
				   .fetchByName_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask[] findByName_PrevAndNext(long scheduledTaskId,
		long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence()
				   .findByName_PrevAndNext(scheduledTaskId, companyId, name,
			orderByComparator);
	}

	/**
	* Removes all the scheduled tasks where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	*/
	public static void removeByName(long companyId, String name) {
		getPersistence().removeByName(companyId, name);
	}

	/**
	* Returns the number of scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching scheduled tasks
	*/
	public static int countByName(long companyId, String name) {
		return getPersistence().countByName(companyId, name);
	}

	/**
	* Caches the scheduled task in the entity cache if it is enabled.
	*
	* @param scheduledTask the scheduled task
	*/
	public static void cacheResult(ScheduledTask scheduledTask) {
		getPersistence().cacheResult(scheduledTask);
	}

	/**
	* Caches the scheduled tasks in the entity cache if it is enabled.
	*
	* @param scheduledTasks the scheduled tasks
	*/
	public static void cacheResult(List<ScheduledTask> scheduledTasks) {
		getPersistence().cacheResult(scheduledTasks);
	}

	/**
	* Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	*
	* @param scheduledTaskId the primary key for the new scheduled task
	* @return the new scheduled task
	*/
	public static ScheduledTask create(long scheduledTaskId) {
		return getPersistence().create(scheduledTaskId);
	}

	/**
	* Removes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task that was removed
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask remove(long scheduledTaskId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().remove(scheduledTaskId);
	}

	public static ScheduledTask updateImpl(ScheduledTask scheduledTask) {
		return getPersistence().updateImpl(scheduledTask);
	}

	/**
	* Returns the scheduled task with the primary key or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask findByPrimaryKey(long scheduledTaskId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException {
		return getPersistence().findByPrimaryKey(scheduledTaskId);
	}

	/**
	* Returns the scheduled task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task, or <code>null</code> if a scheduled task with the primary key could not be found
	*/
	public static ScheduledTask fetchByPrimaryKey(long scheduledTaskId) {
		return getPersistence().fetchByPrimaryKey(scheduledTaskId);
	}

	public static java.util.Map<java.io.Serializable, ScheduledTask> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the scheduled tasks.
	*
	* @return the scheduled tasks
	*/
	public static List<ScheduledTask> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the scheduled tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @return the range of scheduled tasks
	*/
	public static List<ScheduledTask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the scheduled tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of scheduled tasks
	*/
	public static List<ScheduledTask> findAll(int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduled tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduled tasks
	* @param end the upper bound of the range of scheduled tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of scheduled tasks
	*/
	public static List<ScheduledTask> findAll(int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the scheduled tasks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of scheduled tasks.
	*
	* @return the number of scheduled tasks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ScheduledTaskPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ScheduledTaskPersistence, ScheduledTaskPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ScheduledTaskPersistence.class);

		ServiceTracker<ScheduledTaskPersistence, ScheduledTaskPersistence> serviceTracker =
			new ServiceTracker<ScheduledTaskPersistence, ScheduledTaskPersistence>(bundle.getBundleContext(),
				ScheduledTaskPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}