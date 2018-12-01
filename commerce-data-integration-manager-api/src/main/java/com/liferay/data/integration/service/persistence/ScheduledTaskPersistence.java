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

import com.liferay.data.integration.exception.NoSuchScheduledTaskException;
import com.liferay.data.integration.model.ScheduledTask;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the scheduled task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.integration.service.persistence.impl.ScheduledTaskPersistenceImpl
 * @see ScheduledTaskUtil
 * @generated
 */
@ProviderType
public interface ScheduledTaskPersistence extends BasePersistence<ScheduledTask> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduledTaskUtil} to access the scheduled task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the scheduled tasks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByUuid(String uuid);

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
	public java.util.List<ScheduledTask> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ScheduledTask> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where uuid = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask[] findByUuid_PrevAndNext(long scheduledTaskId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of scheduled tasks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching scheduled tasks
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByUUID_G(String uuid, long groupId)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the scheduled task where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the scheduled task that was removed
	*/
	public ScheduledTask removeByUUID_G(String uuid, long groupId)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the number of scheduled tasks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching scheduled tasks
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<ScheduledTask> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ScheduledTask> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] findByUuid_C_PrevAndNext(long scheduledTaskId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of scheduled tasks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching scheduled tasks
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the scheduled tasks where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByEnabled(boolean enabled);

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
	public java.util.List<ScheduledTask> findByEnabled(boolean enabled,
		int start, int end);

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
	public java.util.List<ScheduledTask> findByEnabled(boolean enabled,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByEnabled(boolean enabled,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByEnabled_First(boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByEnabled_First(boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByEnabled_Last(boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByEnabled_Last(boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where enabled = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask[] findByEnabled_PrevAndNext(long scheduledTaskId,
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where enabled = &#63; from the database.
	*
	* @param enabled the enabled
	*/
	public void removeByEnabled(boolean enabled);

	/**
	* Returns the number of scheduled tasks where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the number of matching scheduled tasks
	*/
	public int countByEnabled(boolean enabled);

	/**
	* Returns all the scheduled tasks where active = &#63;.
	*
	* @param active the active
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByActive(boolean active);

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
	public java.util.List<ScheduledTask> findByActive(boolean active,
		int start, int end);

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
	public java.util.List<ScheduledTask> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where active = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask[] findByActive_PrevAndNext(long scheduledTaskId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of scheduled tasks where active = &#63;.
	*
	* @param active the active
	* @return the number of matching scheduled tasks
	*/
	public int countByActive(boolean active);

	/**
	* Returns all the scheduled tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByGroupId(long groupId);

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
	public java.util.List<ScheduledTask> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<ScheduledTask> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask[] findByGroupId_PrevAndNext(long scheduledTaskId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public java.util.List<ScheduledTask> filterFindByGroupId(long groupId);

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
	public java.util.List<ScheduledTask> filterFindByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<ScheduledTask> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param scheduledTaskId the primary key of the current scheduled task
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask[] filterFindByGroupId_PrevAndNext(
		long scheduledTaskId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of scheduled tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching scheduled tasks
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active);

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
	public java.util.List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end);

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
	public java.util.List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_Active_First(long groupId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_Active_First(long groupId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_Active_Last(long groupId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_Active_Last(long groupId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] findByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public java.util.List<ScheduledTask> filterFindByGroupId_Active(
		long groupId, boolean active);

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
	public java.util.List<ScheduledTask> filterFindByGroupId_Active(
		long groupId, boolean active, int start, int end);

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
	public java.util.List<ScheduledTask> filterFindByGroupId_Active(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] filterFindByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where groupId = &#63; and active = &#63; from the database.
	*
	* @param groupId the group ID
	* @param active the active
	*/
	public void removeByGroupId_Active(long groupId, boolean active);

	/**
	* Returns the number of scheduled tasks where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching scheduled tasks
	*/
	public int countByGroupId_Active(long groupId, boolean active);

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public int filterCountByGroupId_Active(long groupId, boolean active);

	/**
	* Returns all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled);

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
	public java.util.List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end);

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
	public java.util.List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_Enabled_First(long groupId,
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_Enabled_First(long groupId,
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByGroupId_Enabled_Last(long groupId,
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByGroupId_Enabled_Last(long groupId,
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] findByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the matching scheduled tasks that the user has permission to view
	*/
	public java.util.List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled);

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
	public java.util.List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end);

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
	public java.util.List<ScheduledTask> filterFindByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] filterFindByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where groupId = &#63; and enabled = &#63; from the database.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	*/
	public void removeByGroupId_Enabled(long groupId, boolean enabled);

	/**
	* Returns the number of scheduled tasks where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the number of matching scheduled tasks
	*/
	public int countByGroupId_Enabled(long groupId, boolean enabled);

	/**
	* Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	*
	* @param groupId the group ID
	* @param enabled the enabled
	* @return the number of matching scheduled tasks that the user has permission to view
	*/
	public int filterCountByGroupId_Enabled(long groupId, boolean enabled);

	/**
	* Returns all the scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching scheduled tasks
	*/
	public java.util.List<ScheduledTask> findByName(long companyId, String name);

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
	public java.util.List<ScheduledTask> findByName(long companyId,
		String name, int start, int end);

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
	public java.util.List<ScheduledTask> findByName(long companyId,
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findByName(long companyId,
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByName_First(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByName_First(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

	/**
	* Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task
	* @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	*/
	public ScheduledTask findByName_Last(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	*/
	public ScheduledTask fetchByName_Last(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public ScheduledTask[] findByName_PrevAndNext(long scheduledTaskId,
		long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException;

	/**
	* Removes all the scheduled tasks where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	*/
	public void removeByName(long companyId, String name);

	/**
	* Returns the number of scheduled tasks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching scheduled tasks
	*/
	public int countByName(long companyId, String name);

	/**
	* Caches the scheduled task in the entity cache if it is enabled.
	*
	* @param scheduledTask the scheduled task
	*/
	public void cacheResult(ScheduledTask scheduledTask);

	/**
	* Caches the scheduled tasks in the entity cache if it is enabled.
	*
	* @param scheduledTasks the scheduled tasks
	*/
	public void cacheResult(java.util.List<ScheduledTask> scheduledTasks);

	/**
	* Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	*
	* @param scheduledTaskId the primary key for the new scheduled task
	* @return the new scheduled task
	*/
	public ScheduledTask create(long scheduledTaskId);

	/**
	* Removes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task that was removed
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask remove(long scheduledTaskId)
		throws NoSuchScheduledTaskException;

	public ScheduledTask updateImpl(ScheduledTask scheduledTask);

	/**
	* Returns the scheduled task with the primary key or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task
	* @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask findByPrimaryKey(long scheduledTaskId)
		throws NoSuchScheduledTaskException;

	/**
	* Returns the scheduled task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scheduledTaskId the primary key of the scheduled task
	* @return the scheduled task, or <code>null</code> if a scheduled task with the primary key could not be found
	*/
	public ScheduledTask fetchByPrimaryKey(long scheduledTaskId);

	@Override
	public java.util.Map<java.io.Serializable, ScheduledTask> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the scheduled tasks.
	*
	* @return the scheduled tasks
	*/
	public java.util.List<ScheduledTask> findAll();

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
	public java.util.List<ScheduledTask> findAll(int start, int end);

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
	public java.util.List<ScheduledTask> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator);

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
	public java.util.List<ScheduledTask> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the scheduled tasks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of scheduled tasks.
	*
	* @return the number of scheduled tasks
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}