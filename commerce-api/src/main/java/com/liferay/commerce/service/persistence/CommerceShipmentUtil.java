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

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce shipment service. This utility wraps <code>com.liferay.commerce.service.persistence.impl.CommerceShipmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentPersistence
 * @generated
 */
public class CommerceShipmentUtil {

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
	public static void clearCache(CommerceShipment commerceShipment) {
		getPersistence().clearCache(commerceShipment);
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
	public static Map<Serializable, CommerceShipment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceShipment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceShipment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceShipment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceShipment update(CommerceShipment commerceShipment) {
		return getPersistence().update(commerceShipment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceShipment update(
		CommerceShipment commerceShipment, ServiceContext serviceContext) {

		return getPersistence().update(commerceShipment, serviceContext);
	}

	/**
	 * Returns all the commerce shipments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(long groupId) {
		return getPersistence().findByGroupIds(groupId);
	}

	/**
	 * Returns a range of all the commerce shipments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @return the range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long groupId, int start, int end) {

		return getPersistence().findByGroupIds(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long groupId, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findByGroupIds(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long groupId, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupIds(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce shipment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce shipment
	 * @throws NoSuchShipmentException if a matching commerce shipment could not be found
	 */
	public static CommerceShipment findByGroupIds_First(
			long groupId, OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByGroupIds_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the first commerce shipment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce shipment, or <code>null</code> if a matching commerce shipment could not be found
	 */
	public static CommerceShipment fetchByGroupIds_First(
		long groupId, OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().fetchByGroupIds_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce shipment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce shipment
	 * @throws NoSuchShipmentException if a matching commerce shipment could not be found
	 */
	public static CommerceShipment findByGroupIds_Last(
			long groupId, OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByGroupIds_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last commerce shipment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce shipment, or <code>null</code> if a matching commerce shipment could not be found
	 */
	public static CommerceShipment fetchByGroupIds_Last(
		long groupId, OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().fetchByGroupIds_Last(
			groupId, orderByComparator);
	}

	/**
	 * Returns the commerce shipments before and after the current commerce shipment in the ordered set where groupId = &#63;.
	 *
	 * @param commerceShipmentId the primary key of the current commerce shipment
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce shipment
	 * @throws NoSuchShipmentException if a commerce shipment with the primary key could not be found
	 */
	public static CommerceShipment[] findByGroupIds_PrevAndNext(
			long commerceShipmentId, long groupId,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByGroupIds_PrevAndNext(
			commerceShipmentId, groupId, orderByComparator);
	}

	/**
	 * Returns all the commerce shipments where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @return the matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(long[] groupIds) {
		return getPersistence().findByGroupIds(groupIds);
	}

	/**
	 * Returns a range of all the commerce shipments where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @return the range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long[] groupIds, int start, int end) {

		return getPersistence().findByGroupIds(groupIds, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long[] groupIds, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findByGroupIds(
			groupIds, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByGroupIds(
		long[] groupIds, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupIds(
			groupIds, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce shipments where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupIds(long groupId) {
		getPersistence().removeByGroupIds(groupId);
	}

	/**
	 * Returns the number of commerce shipments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce shipments
	 */
	public static int countByGroupIds(long groupId) {
		return getPersistence().countByGroupIds(groupId);
	}

	/**
	 * Returns the number of commerce shipments where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching commerce shipments
	 */
	public static int countByGroupIds(long[] groupIds) {
		return getPersistence().countByGroupIds(groupIds);
	}

	/**
	 * Returns all the commerce shipments where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(long groupId, int status) {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	 * Returns a range of all the commerce shipments where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @return the range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long groupId, int status, int start, int end) {

		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long groupId, int status, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findByG_S(
			groupId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long groupId, int status, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_S(
			groupId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce shipment in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce shipment
	 * @throws NoSuchShipmentException if a matching commerce shipment could not be found
	 */
	public static CommerceShipment findByG_S_First(
			long groupId, int status,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByG_S_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the first commerce shipment in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce shipment, or <code>null</code> if a matching commerce shipment could not be found
	 */
	public static CommerceShipment fetchByG_S_First(
		long groupId, int status,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().fetchByG_S_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce shipment in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce shipment
	 * @throws NoSuchShipmentException if a matching commerce shipment could not be found
	 */
	public static CommerceShipment findByG_S_Last(
			long groupId, int status,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByG_S_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last commerce shipment in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce shipment, or <code>null</code> if a matching commerce shipment could not be found
	 */
	public static CommerceShipment fetchByG_S_Last(
		long groupId, int status,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().fetchByG_S_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the commerce shipments before and after the current commerce shipment in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param commerceShipmentId the primary key of the current commerce shipment
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce shipment
	 * @throws NoSuchShipmentException if a commerce shipment with the primary key could not be found
	 */
	public static CommerceShipment[] findByG_S_PrevAndNext(
			long commerceShipmentId, long groupId, int status,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByG_S_PrevAndNext(
			commerceShipmentId, groupId, status, orderByComparator);
	}

	/**
	 * Returns all the commerce shipments where groupId = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param status the status
	 * @return the matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long[] groupIds, int status) {

		return getPersistence().findByG_S(groupIds, status);
	}

	/**
	 * Returns a range of all the commerce shipments where groupId = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @return the range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long[] groupIds, int status, int start, int end) {

		return getPersistence().findByG_S(groupIds, status, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long[] groupIds, int status, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findByG_S(
			groupIds, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce shipments where groupId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce shipments
	 */
	public static List<CommerceShipment> findByG_S(
		long[] groupIds, int status, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_S(
			groupIds, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce shipments where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	public static void removeByG_S(long groupId, int status) {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	 * Returns the number of commerce shipments where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching commerce shipments
	 */
	public static int countByG_S(long groupId, int status) {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	 * Returns the number of commerce shipments where groupId = any &#63; and status = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param status the status
	 * @return the number of matching commerce shipments
	 */
	public static int countByG_S(long[] groupIds, int status) {
		return getPersistence().countByG_S(groupIds, status);
	}

	/**
	 * Caches the commerce shipment in the entity cache if it is enabled.
	 *
	 * @param commerceShipment the commerce shipment
	 */
	public static void cacheResult(CommerceShipment commerceShipment) {
		getPersistence().cacheResult(commerceShipment);
	}

	/**
	 * Caches the commerce shipments in the entity cache if it is enabled.
	 *
	 * @param commerceShipments the commerce shipments
	 */
	public static void cacheResult(List<CommerceShipment> commerceShipments) {
		getPersistence().cacheResult(commerceShipments);
	}

	/**
	 * Creates a new commerce shipment with the primary key. Does not add the commerce shipment to the database.
	 *
	 * @param commerceShipmentId the primary key for the new commerce shipment
	 * @return the new commerce shipment
	 */
	public static CommerceShipment create(long commerceShipmentId) {
		return getPersistence().create(commerceShipmentId);
	}

	/**
	 * Removes the commerce shipment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShipmentId the primary key of the commerce shipment
	 * @return the commerce shipment that was removed
	 * @throws NoSuchShipmentException if a commerce shipment with the primary key could not be found
	 */
	public static CommerceShipment remove(long commerceShipmentId)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().remove(commerceShipmentId);
	}

	public static CommerceShipment updateImpl(
		CommerceShipment commerceShipment) {

		return getPersistence().updateImpl(commerceShipment);
	}

	/**
	 * Returns the commerce shipment with the primary key or throws a <code>NoSuchShipmentException</code> if it could not be found.
	 *
	 * @param commerceShipmentId the primary key of the commerce shipment
	 * @return the commerce shipment
	 * @throws NoSuchShipmentException if a commerce shipment with the primary key could not be found
	 */
	public static CommerceShipment findByPrimaryKey(long commerceShipmentId)
		throws com.liferay.commerce.exception.NoSuchShipmentException {

		return getPersistence().findByPrimaryKey(commerceShipmentId);
	}

	/**
	 * Returns the commerce shipment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceShipmentId the primary key of the commerce shipment
	 * @return the commerce shipment, or <code>null</code> if a commerce shipment with the primary key could not be found
	 */
	public static CommerceShipment fetchByPrimaryKey(long commerceShipmentId) {
		return getPersistence().fetchByPrimaryKey(commerceShipmentId);
	}

	/**
	 * Returns all the commerce shipments.
	 *
	 * @return the commerce shipments
	 */
	public static List<CommerceShipment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce shipments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @return the range of commerce shipments
	 */
	public static List<CommerceShipment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce shipments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce shipments
	 */
	public static List<CommerceShipment> findAll(
		int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce shipments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceShipmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipments
	 * @param end the upper bound of the range of commerce shipments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce shipments
	 */
	public static List<CommerceShipment> findAll(
		int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce shipments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce shipments.
	 *
	 * @return the number of commerce shipments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceShipmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceShipmentPersistence, CommerceShipmentPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceShipmentPersistence.class);

		ServiceTracker<CommerceShipmentPersistence, CommerceShipmentPersistence>
			serviceTracker =
				new ServiceTracker
					<CommerceShipmentPersistence, CommerceShipmentPersistence>(
						bundle.getBundleContext(),
						CommerceShipmentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}