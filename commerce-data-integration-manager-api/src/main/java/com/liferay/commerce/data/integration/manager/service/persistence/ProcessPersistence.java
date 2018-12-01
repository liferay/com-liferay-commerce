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

import com.liferay.commerce.data.integration.manager.exception.NoSuchProcessException;
import com.liferay.commerce.data.integration.manager.model.Process;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.commerce.data.integration.manager.service.persistence.impl.ProcessPersistenceImpl
 * @see ProcessUtil
 * @generated
 */
@ProviderType
public interface ProcessPersistence extends BasePersistence<Process> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessUtil} to access the process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching processes
	*/
	public java.util.List<Process> findByUuid(String uuid);

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
	public java.util.List<Process> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Process> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public java.util.List<Process> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the first process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the last process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the last process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the processes before and after the current process in the ordered set where uuid = &#63;.
	*
	* @param processId the primary key of the current process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public Process[] findByUuid_PrevAndNext(long processId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Removes all the processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching processes
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessException;

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process that was removed
	*/
	public Process removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessException;

	/**
	* Returns the number of processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching processes
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching processes
	*/
	public java.util.List<Process> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public java.util.List<Process> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the first process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the last process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the last process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public Process[] findByUuid_C_PrevAndNext(long processId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Removes all the processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching processes
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching processes
	*/
	public java.util.List<Process> findByGroupId(long groupId);

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
	public java.util.List<Process> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Process> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public java.util.List<Process> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the first process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the last process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the last process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the processes before and after the current process in the ordered set where groupId = &#63;.
	*
	* @param processId the primary key of the current process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public Process[] findByGroupId_PrevAndNext(long processId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns all the processes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching processes that the user has permission to view
	*/
	public java.util.List<Process> filterFindByGroupId(long groupId);

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
	public java.util.List<Process> filterFindByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Process> filterFindByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the processes before and after the current process in the ordered set of processes that the user has permission to view where groupId = &#63;.
	*
	* @param processId the primary key of the current process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public Process[] filterFindByGroupId_PrevAndNext(long processId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Removes all the processes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching processes
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of processes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching processes that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns the process where companyId = &#63; and name = &#63; or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByCompanyId_Name(long companyId, String name)
		throws NoSuchProcessException;

	/**
	* Returns the process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByCompanyId_Name(long companyId, String name);

	/**
	* Returns the process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByCompanyId_Name(long companyId, String name,
		boolean retrieveFromCache);

	/**
	* Removes the process where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the process that was removed
	*/
	public Process removeByCompanyId_Name(long companyId, String name)
		throws NoSuchProcessException;

	/**
	* Returns the number of processes where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching processes
	*/
	public int countByCompanyId_Name(long companyId, String name);

	/**
	* Returns all the processes where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @return the matching processes
	*/
	public java.util.List<Process> findByProcessType_CompanyId(long companyId,
		String processType);

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
	public java.util.List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end);

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
	public java.util.List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public java.util.List<Process> findByProcessType_CompanyId(long companyId,
		String processType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByProcessType_CompanyId_First(long companyId,
		String processType,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the first process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByProcessType_CompanyId_First(long companyId,
		String processType,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

	/**
	* Returns the last process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process
	* @throws NoSuchProcessException if a matching process could not be found
	*/
	public Process findByProcessType_CompanyId_Last(long companyId,
		String processType,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Returns the last process in the ordered set where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process, or <code>null</code> if a matching process could not be found
	*/
	public Process fetchByProcessType_CompanyId_Last(long companyId,
		String processType,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public Process[] findByProcessType_CompanyId_PrevAndNext(long processId,
		long companyId, String processType,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator)
		throws NoSuchProcessException;

	/**
	* Removes all the processes where companyId = &#63; and processType = &#63; from the database.
	*
	* @param companyId the company ID
	* @param processType the process type
	*/
	public void removeByProcessType_CompanyId(long companyId, String processType);

	/**
	* Returns the number of processes where companyId = &#63; and processType = &#63;.
	*
	* @param companyId the company ID
	* @param processType the process type
	* @return the number of matching processes
	*/
	public int countByProcessType_CompanyId(long companyId, String processType);

	/**
	* Caches the process in the entity cache if it is enabled.
	*
	* @param process the process
	*/
	public void cacheResult(Process process);

	/**
	* Caches the processes in the entity cache if it is enabled.
	*
	* @param processes the processes
	*/
	public void cacheResult(java.util.List<Process> processes);

	/**
	* Creates a new process with the primary key. Does not add the process to the database.
	*
	* @param processId the primary key for the new process
	* @return the new process
	*/
	public Process create(long processId);

	/**
	* Removes the process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processId the primary key of the process
	* @return the process that was removed
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public Process remove(long processId) throws NoSuchProcessException;

	public Process updateImpl(Process process);

	/**
	* Returns the process with the primary key or throws a {@link NoSuchProcessException} if it could not be found.
	*
	* @param processId the primary key of the process
	* @return the process
	* @throws NoSuchProcessException if a process with the primary key could not be found
	*/
	public Process findByPrimaryKey(long processId)
		throws NoSuchProcessException;

	/**
	* Returns the process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processId the primary key of the process
	* @return the process, or <code>null</code> if a process with the primary key could not be found
	*/
	public Process fetchByPrimaryKey(long processId);

	@Override
	public java.util.Map<java.io.Serializable, Process> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the processes.
	*
	* @return the processes
	*/
	public java.util.List<Process> findAll();

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
	public java.util.List<Process> findAll(int start, int end);

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
	public java.util.List<Process> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator);

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
	public java.util.List<Process> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Process> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the processes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of processes.
	*
	* @return the number of processes
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}