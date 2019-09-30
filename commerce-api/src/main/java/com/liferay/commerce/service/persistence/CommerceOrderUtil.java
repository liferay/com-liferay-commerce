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

import com.liferay.commerce.model.CommerceOrder;
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
 * The persistence utility for the commerce order service. This utility wraps <code>com.liferay.commerce.service.persistence.impl.CommerceOrderPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderPersistence
 * @generated
 */
public class CommerceOrderUtil {

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
	public static void clearCache(CommerceOrder commerceOrder) {
		getPersistence().clearCache(commerceOrder);
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
	public static Map<Serializable, CommerceOrder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceOrder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceOrder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceOrder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceOrder update(CommerceOrder commerceOrder) {
		return getPersistence().update(commerceOrder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceOrder update(
		CommerceOrder commerceOrder, ServiceContext serviceContext) {

		return getPersistence().update(commerceOrder, serviceContext);
	}

	/**
	 * Returns all the commerce orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUuid_First(
			String uuid, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUuid_First(
		String uuid, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUuid_Last(
			String uuid, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUuid_Last(
		String uuid, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByUuid_PrevAndNext(
			long commerceOrderId, String uuid,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_PrevAndNext(
			commerceOrderId, uuid, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce orders
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the commerce order where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce order that was removed
	 */
	public static CommerceOrder removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce orders
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByUuid_C_PrevAndNext(
			long commerceOrderId, String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUuid_C_PrevAndNext(
			commerceOrderId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce orders
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the commerce orders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByGroupId_First(
			long groupId, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByGroupId_First(
		long groupId, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByGroupId_Last(
			long groupId, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByGroupId_Last(
		long groupId, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByGroupId_PrevAndNext(
			long commerceOrderId, long groupId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByGroupId_PrevAndNext(
			commerceOrderId, groupId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce orders
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the commerce orders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUserId_First(
			long userId, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUserId_First(
		long userId, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByUserId_Last(
			long userId, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByUserId_Last(
		long userId, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where userId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByUserId_PrevAndNext(
			long commerceOrderId, long userId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByUserId_PrevAndNext(
			commerceOrderId, userId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of commerce orders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching commerce orders
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the commerce orders where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByCommerceAccountId(
		long commerceAccountId) {

		return getPersistence().findByCommerceAccountId(commerceAccountId);
	}

	/**
	 * Returns a range of all the commerce orders where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByCommerceAccountId(
		long commerceAccountId, int start, int end) {

		return getPersistence().findByCommerceAccountId(
			commerceAccountId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByCommerceAccountId(
			commerceAccountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCommerceAccountId(
			commerceAccountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByCommerceAccountId_First(
			long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByCommerceAccountId_First(
			commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByCommerceAccountId_First(
			commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByCommerceAccountId_Last(
			long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByCommerceAccountId_Last(
			commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByCommerceAccountId_Last(
			commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByCommerceAccountId_PrevAndNext(
			long commerceOrderId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByCommerceAccountId_PrevAndNext(
			commerceOrderId, commerceAccountId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where commerceAccountId = &#63; from the database.
	 *
	 * @param commerceAccountId the commerce account ID
	 */
	public static void removeByCommerceAccountId(long commerceAccountId) {
		getPersistence().removeByCommerceAccountId(commerceAccountId);
	}

	/**
	 * Returns the number of commerce orders where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce orders
	 */
	public static int countByCommerceAccountId(long commerceAccountId) {
		return getPersistence().countByCommerceAccountId(commerceAccountId);
	}

	/**
	 * Returns all the commerce orders where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByBillingAddressId(
		long billingAddressId) {

		return getPersistence().findByBillingAddressId(billingAddressId);
	}

	/**
	 * Returns a range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end) {

		return getPersistence().findByBillingAddressId(
			billingAddressId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByBillingAddressId(
			billingAddressId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByBillingAddressId(
			billingAddressId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByBillingAddressId_First(
			long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByBillingAddressId_First(
			billingAddressId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByBillingAddressId_First(
		long billingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByBillingAddressId_First(
			billingAddressId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByBillingAddressId_Last(
			long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByBillingAddressId_Last(
			billingAddressId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByBillingAddressId_Last(
		long billingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByBillingAddressId_Last(
			billingAddressId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByBillingAddressId_PrevAndNext(
			long commerceOrderId, long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByBillingAddressId_PrevAndNext(
			commerceOrderId, billingAddressId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where billingAddressId = &#63; from the database.
	 *
	 * @param billingAddressId the billing address ID
	 */
	public static void removeByBillingAddressId(long billingAddressId) {
		getPersistence().removeByBillingAddressId(billingAddressId);
	}

	/**
	 * Returns the number of commerce orders where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @return the number of matching commerce orders
	 */
	public static int countByBillingAddressId(long billingAddressId) {
		return getPersistence().countByBillingAddressId(billingAddressId);
	}

	/**
	 * Returns all the commerce orders where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId) {

		return getPersistence().findByShippingAddressId(shippingAddressId);
	}

	/**
	 * Returns a range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end) {

		return getPersistence().findByShippingAddressId(
			shippingAddressId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByShippingAddressId(
			shippingAddressId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByShippingAddressId(
			shippingAddressId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByShippingAddressId_First(
			long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByShippingAddressId_First(
			shippingAddressId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByShippingAddressId_First(
		long shippingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByShippingAddressId_First(
			shippingAddressId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByShippingAddressId_Last(
			long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByShippingAddressId_Last(
			shippingAddressId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByShippingAddressId_Last(
		long shippingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByShippingAddressId_Last(
			shippingAddressId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByShippingAddressId_PrevAndNext(
			long commerceOrderId, long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByShippingAddressId_PrevAndNext(
			commerceOrderId, shippingAddressId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where shippingAddressId = &#63; from the database.
	 *
	 * @param shippingAddressId the shipping address ID
	 */
	public static void removeByShippingAddressId(long shippingAddressId) {
		getPersistence().removeByShippingAddressId(shippingAddressId);
	}

	/**
	 * Returns the number of commerce orders where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @return the number of matching commerce orders
	 */
	public static int countByShippingAddressId(long shippingAddressId) {
		return getPersistence().countByShippingAddressId(shippingAddressId);
	}

	/**
	 * Returns all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId) {

		return getPersistence().findByG_C(groupId, commerceAccountId);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end) {

		return getPersistence().findByG_C(
			groupId, commerceAccountId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByG_C(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C(
			groupId, commerceAccountId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_C_First(
			long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_First(
			groupId, commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_C_First(
		long groupId, long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_C_First(
			groupId, commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_C_Last(
			long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_Last(
			groupId, commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_C_Last(
		long groupId, long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_C_Last(
			groupId, commerceAccountId, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByG_C_PrevAndNext(
			long commerceOrderId, long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_PrevAndNext(
			commerceOrderId, groupId, commerceAccountId, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and commerceAccountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 */
	public static void removeByG_C(long groupId, long commerceAccountId) {
		getPersistence().removeByG_C(groupId, commerceAccountId);
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce orders
	 */
	public static int countByG_C(long groupId, long commerceAccountId) {
		return getPersistence().countByG_C(groupId, commerceAccountId);
	}

	/**
	 * Returns all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus) {

		return getPersistence().findByG_U_O(groupId, userId, orderStatus);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end) {

		return getPersistence().findByG_U_O(
			groupId, userId, orderStatus, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByG_U_O(
			groupId, userId, orderStatus, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U_O(
			groupId, userId, orderStatus, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_U_O_First(
			long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_U_O_First(
			groupId, userId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_U_O_First(
		long groupId, long userId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_U_O_First(
			groupId, userId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_U_O_Last(
			long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_U_O_Last(
			groupId, userId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_U_O_Last(
		long groupId, long userId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_U_O_Last(
			groupId, userId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByG_U_O_PrevAndNext(
			long commerceOrderId, long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_U_O_PrevAndNext(
			commerceOrderId, groupId, userId, orderStatus, orderByComparator);
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 */
	public static void removeByG_U_O(
		long groupId, long userId, int orderStatus) {

		getPersistence().removeByG_U_O(groupId, userId, orderStatus);
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	public static int countByG_U_O(long groupId, long userId, int orderStatus) {
		return getPersistence().countByG_U_O(groupId, userId, orderStatus);
	}

	/**
	 * Returns all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		return getPersistence().findByG_C_O(
			groupId, commerceAccountId, orderStatus);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end) {

		return getPersistence().findByG_C_O(
			groupId, commerceAccountId, orderStatus, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end, OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByG_C_O(
			groupId, commerceAccountId, orderStatus, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end, OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_O(
			groupId, commerceAccountId, orderStatus, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_C_O_First(
			long groupId, long commerceAccountId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_O_First(
			groupId, commerceAccountId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_C_O_First(
		long groupId, long commerceAccountId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_C_O_First(
			groupId, commerceAccountId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByG_C_O_Last(
			long groupId, long commerceAccountId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_O_Last(
			groupId, commerceAccountId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByG_C_O_Last(
		long groupId, long commerceAccountId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByG_C_O_Last(
			groupId, commerceAccountId, orderStatus, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByG_C_O_PrevAndNext(
			long commerceOrderId, long groupId, long commerceAccountId,
			int orderStatus, OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByG_C_O_PrevAndNext(
			commerceOrderId, groupId, commerceAccountId, orderStatus,
			orderByComparator);
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 */
	public static void removeByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		getPersistence().removeByG_C_O(groupId, commerceAccountId, orderStatus);
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	public static int countByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		return getPersistence().countByG_C_O(
			groupId, commerceAccountId, orderStatus);
	}

	/**
	 * Returns all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	public static List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus) {

		return getPersistence().findByU_LtC_O(userId, createDate, orderStatus);
	}

	/**
	 * Returns a range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	public static List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end) {

		return getPersistence().findByU_LtC_O(
			userId, createDate, orderStatus, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findByU_LtC_O(
			userId, createDate, orderStatus, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	public static List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_LtC_O(
			userId, createDate, orderStatus, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByU_LtC_O_First(
			long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByU_LtC_O_First(
			userId, createDate, orderStatus, orderByComparator);
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByU_LtC_O_First(
		long userId, Date createDate, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByU_LtC_O_First(
			userId, createDate, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByU_LtC_O_Last(
			long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByU_LtC_O_Last(
			userId, createDate, orderStatus, orderByComparator);
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByU_LtC_O_Last(
		long userId, Date createDate, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().fetchByU_LtC_O_Last(
			userId, createDate, orderStatus, orderByComparator);
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder[] findByU_LtC_O_PrevAndNext(
			long commerceOrderId, long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByU_LtC_O_PrevAndNext(
			commerceOrderId, userId, createDate, orderStatus,
			orderByComparator);
	}

	/**
	 * Removes all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 */
	public static void removeByU_LtC_O(
		long userId, Date createDate, int orderStatus) {

		getPersistence().removeByU_LtC_O(userId, createDate, orderStatus);
	}

	/**
	 * Returns the number of commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	public static int countByU_LtC_O(
		long userId, Date createDate, int orderStatus) {

		return getPersistence().countByU_LtC_O(userId, createDate, orderStatus);
	}

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	public static CommerceOrder findByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	public static CommerceOrder fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		return getPersistence().fetchByC_ERC(
			companyId, externalReferenceCode, useFinderCache);
	}

	/**
	 * Removes the commerce order where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce order that was removed
	 */
	public static CommerceOrder removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the number of commerce orders where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce orders
	 */
	public static int countByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Caches the commerce order in the entity cache if it is enabled.
	 *
	 * @param commerceOrder the commerce order
	 */
	public static void cacheResult(CommerceOrder commerceOrder) {
		getPersistence().cacheResult(commerceOrder);
	}

	/**
	 * Caches the commerce orders in the entity cache if it is enabled.
	 *
	 * @param commerceOrders the commerce orders
	 */
	public static void cacheResult(List<CommerceOrder> commerceOrders) {
		getPersistence().cacheResult(commerceOrders);
	}

	/**
	 * Creates a new commerce order with the primary key. Does not add the commerce order to the database.
	 *
	 * @param commerceOrderId the primary key for the new commerce order
	 * @return the new commerce order
	 */
	public static CommerceOrder create(long commerceOrderId) {
		return getPersistence().create(commerceOrderId);
	}

	/**
	 * Removes the commerce order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order that was removed
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder remove(long commerceOrderId)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().remove(commerceOrderId);
	}

	public static CommerceOrder updateImpl(CommerceOrder commerceOrder) {
		return getPersistence().updateImpl(commerceOrder);
	}

	/**
	 * Returns the commerce order with the primary key or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder findByPrimaryKey(long commerceOrderId)
		throws com.liferay.commerce.exception.NoSuchOrderException {

		return getPersistence().findByPrimaryKey(commerceOrderId);
	}

	/**
	 * Returns the commerce order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order, or <code>null</code> if a commerce order with the primary key could not be found
	 */
	public static CommerceOrder fetchByPrimaryKey(long commerceOrderId) {
		return getPersistence().fetchByPrimaryKey(commerceOrderId);
	}

	/**
	 * Returns all the commerce orders.
	 *
	 * @return the commerce orders
	 */
	public static List<CommerceOrder> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of commerce orders
	 */
	public static List<CommerceOrder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce orders
	 */
	public static List<CommerceOrder> findAll(
		int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce orders
	 */
	public static List<CommerceOrder> findAll(
		int start, int end, OrderByComparator<CommerceOrder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce orders from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce orders.
	 *
	 * @return the number of commerce orders
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceOrderPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceOrderPersistence, CommerceOrderPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceOrderPersistence.class);

		ServiceTracker<CommerceOrderPersistence, CommerceOrderPersistence>
			serviceTracker =
				new ServiceTracker
					<CommerceOrderPersistence, CommerceOrderPersistence>(
						bundle.getBundleContext(),
						CommerceOrderPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}