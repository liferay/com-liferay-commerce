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

import com.liferay.data.integration.model.ProcessType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process type service. This utility wraps {@link com.liferay.data.integration.service.persistence.impl.ProcessTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessTypePersistence
 * @see com.liferay.data.integration.service.persistence.impl.ProcessTypePersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessTypeUtil {
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
	public static void clearCache(ProcessType processType) {
		getPersistence().clearCache(processType);
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
	public static List<ProcessType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessType update(ProcessType processType) {
		return getPersistence().update(processType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessType update(ProcessType processType,
		ServiceContext serviceContext) {
		return getPersistence().update(processType, serviceContext);
	}

	/**
	* Returns all the process types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process types
	*/
	public static List<ProcessType> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ProcessType> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ProcessType> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ProcessType> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByUuid_First(String uuid,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUuid_First(String uuid,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByUuid_Last(String uuid,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process types before and after the current process type in the ordered set where uuid = &#63;.
	*
	* @param processTypeId the primary key of the current process type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public static ProcessType[] findByUuid_PrevAndNext(long processTypeId,
		String uuid, OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processTypeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process types
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByUUID_G(String uuid, long groupId)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process type that was removed
	*/
	public static ProcessType removeByUUID_G(String uuid, long groupId)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process types
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process types
	*/
	public static List<ProcessType> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ProcessType> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ProcessType> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ProcessType> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ProcessType[] findByUuid_C_PrevAndNext(long processTypeId,
		String uuid, long companyId,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processTypeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the process types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process types
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process types where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching process types
	*/
	public static List<ProcessType> findByName(long companyId, String name) {
		return getPersistence().findByName(companyId, name);
	}

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
	public static List<ProcessType> findByName(long companyId, String name,
		int start, int end) {
		return getPersistence().findByName(companyId, name, start, end);
	}

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
	public static List<ProcessType> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .findByName(companyId, name, start, end, orderByComparator);
	}

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
	public static List<ProcessType> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(companyId, name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByName_First(long companyId, String name,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByName_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the first process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByName_First(long companyId, String name,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .fetchByName_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type
	* @throws NoSuchProcessTypeException if a matching process type could not be found
	*/
	public static ProcessType findByName_Last(long companyId, String name,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByName_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the last process type in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process type, or <code>null</code> if a matching process type could not be found
	*/
	public static ProcessType fetchByName_Last(long companyId, String name,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence()
				   .fetchByName_Last(companyId, name, orderByComparator);
	}

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
	public static ProcessType[] findByName_PrevAndNext(long processTypeId,
		long companyId, String name,
		OrderByComparator<ProcessType> orderByComparator)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence()
				   .findByName_PrevAndNext(processTypeId, companyId, name,
			orderByComparator);
	}

	/**
	* Removes all the process types where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	*/
	public static void removeByName(long companyId, String name) {
		getPersistence().removeByName(companyId, name);
	}

	/**
	* Returns the number of process types where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching process types
	*/
	public static int countByName(long companyId, String name) {
		return getPersistence().countByName(companyId, name);
	}

	/**
	* Caches the process type in the entity cache if it is enabled.
	*
	* @param processType the process type
	*/
	public static void cacheResult(ProcessType processType) {
		getPersistence().cacheResult(processType);
	}

	/**
	* Caches the process types in the entity cache if it is enabled.
	*
	* @param processTypes the process types
	*/
	public static void cacheResult(List<ProcessType> processTypes) {
		getPersistence().cacheResult(processTypes);
	}

	/**
	* Creates a new process type with the primary key. Does not add the process type to the database.
	*
	* @param processTypeId the primary key for the new process type
	* @return the new process type
	*/
	public static ProcessType create(long processTypeId) {
		return getPersistence().create(processTypeId);
	}

	/**
	* Removes the process type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type that was removed
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public static ProcessType remove(long processTypeId)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().remove(processTypeId);
	}

	public static ProcessType updateImpl(ProcessType processType) {
		return getPersistence().updateImpl(processType);
	}

	/**
	* Returns the process type with the primary key or throws a {@link NoSuchProcessTypeException} if it could not be found.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type
	* @throws NoSuchProcessTypeException if a process type with the primary key could not be found
	*/
	public static ProcessType findByPrimaryKey(long processTypeId)
		throws com.liferay.data.integration.exception.NoSuchProcessTypeException {
		return getPersistence().findByPrimaryKey(processTypeId);
	}

	/**
	* Returns the process type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processTypeId the primary key of the process type
	* @return the process type, or <code>null</code> if a process type with the primary key could not be found
	*/
	public static ProcessType fetchByPrimaryKey(long processTypeId) {
		return getPersistence().fetchByPrimaryKey(processTypeId);
	}

	public static java.util.Map<java.io.Serializable, ProcessType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process types.
	*
	* @return the process types
	*/
	public static List<ProcessType> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ProcessType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ProcessType> findAll(int start, int end,
		OrderByComparator<ProcessType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ProcessType> findAll(int start, int end,
		OrderByComparator<ProcessType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process types.
	*
	* @return the number of process types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessTypePersistence, ProcessTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessTypePersistence.class);

		ServiceTracker<ProcessTypePersistence, ProcessTypePersistence> serviceTracker =
			new ServiceTracker<ProcessTypePersistence, ProcessTypePersistence>(bundle.getBundleContext(),
				ProcessTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}