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

import com.liferay.commerce.data.integration.manager.exception.NoSuchProcessTypeException;
import com.liferay.commerce.data.integration.manager.model.ProcessType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the process type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.integration.service.persistence.impl.ProcessTypePersistenceImpl
 * @see ProcessTypeUtil
 * @generated
 */
@ProviderType
public interface ProcessTypePersistence extends BasePersistence<ProcessType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessTypeUtil} to access the process type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process types
	*/
	public java.util.List<ProcessType> findByUuid(String uuid);

	/**
	* Returns a range of all the process types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @return the range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the process types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns an ordered range of all the process types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the first process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the last process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the last process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the process types before and after the current process type in the ordered set where uuid = &#63;.
	*
	* @param processTypeId the primary key of the current process type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public ProcessType[] findByUuid_PrevAndNext(long processTypeId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Removes all the process types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process types
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessTypeException;

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process type that was removed
	*/
	public ProcessType removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessTypeException;

	/**
	* Returns the number of process types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process types
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the process types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process types
	*/
	public java.util.List<ProcessType> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the process types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @return the range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the process types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns an ordered range of all the process types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the first process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the last process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the last process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the process types before and after the current process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processTypeId the primary key of the current process type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public ProcessType[] findByUuid_C_PrevAndNext(long processTypeId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Removes all the process types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of process types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process types
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the process types where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process types
	*/
	public java.util.List<ProcessType> findByName(long companyId, String name);

	/**
	* Returns a range of all the process types where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @return the range of matching process types
	*/
	public java.util.List<ProcessType> findByName(long companyId, String name,
		int start, int end);

	/**
	* Returns an ordered range of all the process types where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByName(long companyId, String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns an ordered range of all the process types where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process types
	*/
	public java.util.List<ProcessType> findByName(long companyId, String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByName_First(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the first process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByName_First(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the last process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public ProcessType findByName_Last(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Returns the last process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public ProcessType fetchByName_Last(long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns the process types before and after the current process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param processTypeId the primary key of the current process type
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public ProcessType[] findByName_PrevAndNext(long processTypeId,
		long companyId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator)
		throws NoSuchProcessTypeException;

	/**
	* Removes all the process types where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	*/
	public void removeByName(long companyId, String name);

	/**
	* Returns the number of process types where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching process types
	*/
	public int countByName(long companyId, String name);

	/**
	* Caches the process type in the entity cache if it is enabled.
	*
	* @param processType the process type
	*/
	public void cacheResult(ProcessType processType);

	/**
	* Caches the process types in the entity cache if it is enabled.
	*
	* @param processTypes the process types
	*/
	public void cacheResult(java.util.List<ProcessType> processTypes);

	/**
	* Creates a new process type with the primary key. Does not add the process type to the database.
	*
	* @param processTypeId the primary key for the new process type
	* @return the new process type
	*/
	public ProcessType create(long processTypeId);

	/**
	* Removes the process type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type that was removed
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public ProcessType remove(long processTypeId)
		throws NoSuchProcessTypeException;

	public ProcessType updateImpl(ProcessType processType);

	/**
	* Returns the process type with the primary key or throws a {@link NoSuchProcessTypeException} if it could not be found.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public ProcessType findByPrimaryKey(long processTypeId)
		throws NoSuchProcessTypeException;

	/**
	* Returns the process type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type, or <code>null</code> if a process type with the primary key could not be found
	*/
	public ProcessType fetchByPrimaryKey(long processTypeId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process types.
	*
	* @return the process types
	*/
	public java.util.List<ProcessType> findAll();

	/**
	* Returns a range of all the process types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @return the range of process types
	*/
	public java.util.List<ProcessType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the process types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process types
	*/
	public java.util.List<ProcessType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator);

	/**
	* Returns an ordered range of all the process types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process types
	* @param end the upper bound of the range of process types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process types
	*/
	public java.util.List<ProcessType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process types.
	*
	* @return the number of process types
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}