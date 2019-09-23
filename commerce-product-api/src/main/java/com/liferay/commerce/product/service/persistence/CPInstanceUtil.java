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

import com.liferay.commerce.product.model.CPInstance;
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
 * The persistence utility for the cp instance service. This utility wraps <code>com.liferay.commerce.product.service.persistence.impl.CPInstancePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPInstancePersistence
 * @generated
 */
public class CPInstanceUtil {

	/**
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
	public static void clearCache(CPInstance cpInstance) {
		getPersistence().clearCache(cpInstance);
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
	public static Map<Serializable, CPInstance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CPInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPInstance update(CPInstance cpInstance) {
		return getPersistence().update(cpInstance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPInstance update(
		CPInstance cpInstance, ServiceContext serviceContext) {

		return getPersistence().update(cpInstance, serviceContext);
	}

	/**
	 * Returns all the cp instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByUuid_First(
			String uuid, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUuid_First(
		String uuid, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByUuid_Last(
			String uuid, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUuid_Last(
		String uuid, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByUuid_PrevAndNext(
			long CPInstanceId, String uuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_PrevAndNext(
			CPInstanceId, uuid, orderByComparator);
	}

	/**
	 * Removes all the cp instances where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of cp instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp instances
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the cp instance where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp instance that was removed
	 */
	public static CPInstance removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of cp instances where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp instances
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByUuid_C_PrevAndNext(
			long CPInstanceId, String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByUuid_C_PrevAndNext(
			CPInstanceId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the cp instances where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp instances
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the cp instances where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByGroupId_First(
			long groupId, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByGroupId_First(
		long groupId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByGroupId_Last(
			long groupId, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByGroupId_Last(
		long groupId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByGroupId_PrevAndNext(
			long CPInstanceId, long groupId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByGroupId_PrevAndNext(
			CPInstanceId, groupId, orderByComparator);
	}

	/**
	 * Removes all the cp instances where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of cp instances where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp instances
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the cp instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCompanyId_First(
			long companyId, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCompanyId_First(
		long companyId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCompanyId_Last(
			long companyId, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCompanyId_Last(
		long companyId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByCompanyId_PrevAndNext(
			long CPInstanceId, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCompanyId_PrevAndNext(
			CPInstanceId, companyId, orderByComparator);
	}

	/**
	 * Removes all the cp instances where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of cp instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp instances
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByCPDefinitionId(long CPDefinitionId) {
		return getPersistence().findByCPDefinitionId(CPDefinitionId);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end) {

		return getPersistence().findByCPDefinitionId(
			CPDefinitionId, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByCPDefinitionId(
			CPDefinitionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCPDefinitionId(
			CPDefinitionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCPDefinitionId_First(
			long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPDefinitionId_First(
			CPDefinitionId, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCPDefinitionId_First(
		long CPDefinitionId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCPDefinitionId_First(
			CPDefinitionId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCPDefinitionId_Last(
			long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPDefinitionId_Last(
			CPDefinitionId, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCPDefinitionId_Last(
		long CPDefinitionId, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCPDefinitionId_Last(
			CPDefinitionId, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByCPDefinitionId_PrevAndNext(
			long CPInstanceId, long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPDefinitionId_PrevAndNext(
			CPInstanceId, CPDefinitionId, orderByComparator);
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 */
	public static void removeByCPDefinitionId(long CPDefinitionId) {
		getPersistence().removeByCPDefinitionId(CPDefinitionId);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp instances
	 */
	public static int countByCPDefinitionId(long CPDefinitionId) {
		return getPersistence().countByCPDefinitionId(CPDefinitionId);
	}

	/**
	 * Returns all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByCPInstanceUuid(String CPInstanceUuid) {
		return getPersistence().findByCPInstanceUuid(CPInstanceUuid);
	}

	/**
	 * Returns a range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end) {

		return getPersistence().findByCPInstanceUuid(
			CPInstanceUuid, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByCPInstanceUuid(
			CPInstanceUuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCPInstanceUuid(
			CPInstanceUuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCPInstanceUuid_First(
			String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPInstanceUuid_First(
			CPInstanceUuid, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCPInstanceUuid_First(
		String CPInstanceUuid,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCPInstanceUuid_First(
			CPInstanceUuid, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByCPInstanceUuid_Last(
			String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPInstanceUuid_Last(
			CPInstanceUuid, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByCPInstanceUuid_Last(
		String CPInstanceUuid,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByCPInstanceUuid_Last(
			CPInstanceUuid, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByCPInstanceUuid_PrevAndNext(
			long CPInstanceId, String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByCPInstanceUuid_PrevAndNext(
			CPInstanceId, CPInstanceUuid, orderByComparator);
	}

	/**
	 * Removes all the cp instances where CPInstanceUuid = &#63; from the database.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 */
	public static void removeByCPInstanceUuid(String CPInstanceUuid) {
		getPersistence().removeByCPInstanceUuid(CPInstanceUuid);
	}

	/**
	 * Returns the number of cp instances where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching cp instances
	 */
	public static int countByCPInstanceUuid(String CPInstanceUuid) {
		return getPersistence().countByCPInstanceUuid(CPInstanceUuid);
	}

	/**
	 * Returns all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByG_ST(long groupId, int status) {
		return getPersistence().findByG_ST(groupId, status);
	}

	/**
	 * Returns a range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end) {

		return getPersistence().findByG_ST(groupId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByG_ST(
			groupId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_ST(
			groupId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByG_ST_First(
			long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByG_ST_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByG_ST_First(
		long groupId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByG_ST_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByG_ST_Last(
			long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByG_ST_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByG_ST_Last(
		long groupId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByG_ST_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByG_ST_PrevAndNext(
			long CPInstanceId, long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByG_ST_PrevAndNext(
			CPInstanceId, groupId, status, orderByComparator);
	}

	/**
	 * Removes all the cp instances where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	public static void removeByG_ST(long groupId, int status) {
		getPersistence().removeByG_ST(groupId, status);
	}

	/**
	 * Returns the number of cp instances where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	public static int countByG_ST(long groupId, int status) {
		return getPersistence().countByG_ST(groupId, status);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_C(
			long CPDefinitionId, String CPInstanceUuid)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_C(CPDefinitionId, CPInstanceUuid);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_C(
		long CPDefinitionId, String CPInstanceUuid) {

		return getPersistence().fetchByC_C(CPDefinitionId, CPInstanceUuid);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_C(
		long CPDefinitionId, String CPInstanceUuid, boolean useFinderCache) {

		return getPersistence().fetchByC_C(
			CPDefinitionId, CPInstanceUuid, useFinderCache);
	}

	/**
	 * Removes the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the cp instance that was removed
	 */
	public static CPInstance removeByC_C(
			long CPDefinitionId, String CPInstanceUuid)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().removeByC_C(CPDefinitionId, CPInstanceUuid);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching cp instances
	 */
	public static int countByC_C(long CPDefinitionId, String CPInstanceUuid) {
		return getPersistence().countByC_C(CPDefinitionId, CPInstanceUuid);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_S(long CPDefinitionId, String sku)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_S(CPDefinitionId, sku);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_S(long CPDefinitionId, String sku) {
		return getPersistence().fetchByC_S(CPDefinitionId, sku);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_S(
		long CPDefinitionId, String sku, boolean useFinderCache) {

		return getPersistence().fetchByC_S(CPDefinitionId, sku, useFinderCache);
	}

	/**
	 * Removes the cp instance where CPDefinitionId = &#63; and sku = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the cp instance that was removed
	 */
	public static CPInstance removeByC_S(long CPDefinitionId, String sku)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().removeByC_S(CPDefinitionId, sku);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and sku = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the number of matching cp instances
	 */
	public static int countByC_S(long CPDefinitionId, String sku) {
		return getPersistence().countByC_S(CPDefinitionId, sku);
	}

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByC_ST(long CPDefinitionId, int status) {
		return getPersistence().findByC_ST(CPDefinitionId, status);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end) {

		return getPersistence().findByC_ST(CPDefinitionId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByC_ST(
			CPDefinitionId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_ST(
			CPDefinitionId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_ST_First(
			long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_ST_First(
			CPDefinitionId, status, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_ST_First(
		long CPDefinitionId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByC_ST_First(
			CPDefinitionId, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_ST_Last(
			long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_ST_Last(
			CPDefinitionId, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_ST_Last(
		long CPDefinitionId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByC_ST_Last(
			CPDefinitionId, status, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByC_ST_PrevAndNext(
			long CPInstanceId, long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_ST_PrevAndNext(
			CPInstanceId, CPDefinitionId, status, orderByComparator);
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; and status = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 */
	public static void removeByC_ST(long CPDefinitionId, int status) {
		getPersistence().removeByC_ST(CPDefinitionId, status);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	public static int countByC_ST(long CPDefinitionId, int status) {
		return getPersistence().countByC_ST(CPDefinitionId, status);
	}

	/**
	 * Returns all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByLtD_S(Date displayDate, int status) {
		return getPersistence().findByLtD_S(displayDate, status);
	}

	/**
	 * Returns a range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end) {

		return getPersistence().findByLtD_S(displayDate, status, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByLtD_S(
			displayDate, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLtD_S(
			displayDate, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByLtD_S_First(
			Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByLtD_S_First(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByLtD_S_First(
		Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByLtD_S_First(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByLtD_S_Last(
			Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByLtD_S_Last(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByLtD_S_Last(
		Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByLtD_S_Last(
			displayDate, status, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByLtD_S_PrevAndNext(
			long CPInstanceId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByLtD_S_PrevAndNext(
			CPInstanceId, displayDate, status, orderByComparator);
	}

	/**
	 * Removes all the cp instances where displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 */
	public static void removeByLtD_S(Date displayDate, int status) {
		getPersistence().removeByLtD_S(displayDate, status);
	}

	/**
	 * Returns the number of cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	public static int countByLtD_S(Date displayDate, int status) {
		return getPersistence().countByLtD_S(displayDate, status);
	}

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching cp instances
	 */
	public static List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		return getPersistence().findByC_LtD_S(
			CPDefinitionId, displayDate, status);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	public static List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end) {

		return getPersistence().findByC_LtD_S(
			CPDefinitionId, displayDate, status, start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findByC_LtD_S(
			CPDefinitionId, displayDate, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp instances
	 */
	public static List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_LtD_S(
			CPDefinitionId, displayDate, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_LtD_S_First(
			long CPDefinitionId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_LtD_S_First(
			CPDefinitionId, displayDate, status, orderByComparator);
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_LtD_S_First(
		long CPDefinitionId, Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByC_LtD_S_First(
			CPDefinitionId, displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_LtD_S_Last(
			long CPDefinitionId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_LtD_S_Last(
			CPDefinitionId, displayDate, status, orderByComparator);
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_LtD_S_Last(
		long CPDefinitionId, Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().fetchByC_LtD_S_Last(
			CPDefinitionId, displayDate, status, orderByComparator);
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance[] findByC_LtD_S_PrevAndNext(
			long CPInstanceId, long CPDefinitionId, Date displayDate,
			int status, OrderByComparator<CPInstance> orderByComparator)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_LtD_S_PrevAndNext(
			CPInstanceId, CPDefinitionId, displayDate, status,
			orderByComparator);
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 */
	public static void removeByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		getPersistence().removeByC_LtD_S(CPDefinitionId, displayDate, status);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	public static int countByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		return getPersistence().countByC_LtD_S(
			CPDefinitionId, displayDate, status);
	}

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	public static CPInstance findByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	public static CPInstance fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		return getPersistence().fetchByC_ERC(
			companyId, externalReferenceCode, useFinderCache);
	}

	/**
	 * Removes the cp instance where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the cp instance that was removed
	 */
	public static CPInstance removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the number of cp instances where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching cp instances
	 */
	public static int countByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Caches the cp instance in the entity cache if it is enabled.
	 *
	 * @param cpInstance the cp instance
	 */
	public static void cacheResult(CPInstance cpInstance) {
		getPersistence().cacheResult(cpInstance);
	}

	/**
	 * Caches the cp instances in the entity cache if it is enabled.
	 *
	 * @param cpInstances the cp instances
	 */
	public static void cacheResult(List<CPInstance> cpInstances) {
		getPersistence().cacheResult(cpInstances);
	}

	/**
	 * Creates a new cp instance with the primary key. Does not add the cp instance to the database.
	 *
	 * @param CPInstanceId the primary key for the new cp instance
	 * @return the new cp instance
	 */
	public static CPInstance create(long CPInstanceId) {
		return getPersistence().create(CPInstanceId);
	}

	/**
	 * Removes the cp instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance that was removed
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance remove(long CPInstanceId)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().remove(CPInstanceId);
	}

	public static CPInstance updateImpl(CPInstance cpInstance) {
		return getPersistence().updateImpl(cpInstance);
	}

	/**
	 * Returns the cp instance with the primary key or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	public static CPInstance findByPrimaryKey(long CPInstanceId)
		throws com.liferay.commerce.product.exception.
			NoSuchCPInstanceException {

		return getPersistence().findByPrimaryKey(CPInstanceId);
	}

	/**
	 * Returns the cp instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance, or <code>null</code> if a cp instance with the primary key could not be found
	 */
	public static CPInstance fetchByPrimaryKey(long CPInstanceId) {
		return getPersistence().fetchByPrimaryKey(CPInstanceId);
	}

	/**
	 * Returns all the cp instances.
	 *
	 * @return the cp instances
	 */
	public static List<CPInstance> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of cp instances
	 */
	public static List<CPInstance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp instances
	 */
	public static List<CPInstance> findAll(
		int start, int end, OrderByComparator<CPInstance> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp instances
	 */
	public static List<CPInstance> findAll(
		int start, int end, OrderByComparator<CPInstance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cp instances from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cp instances.
	 *
	 * @return the number of cp instances
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CPInstancePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPInstancePersistence, CPInstancePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPInstancePersistence.class);

		ServiceTracker<CPInstancePersistence, CPInstancePersistence>
			serviceTracker =
				new ServiceTracker
					<CPInstancePersistence, CPInstancePersistence>(
						bundle.getBundleContext(), CPInstancePersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}