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

import com.liferay.commerce.data.integration.manager.model.History;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the history service. This utility wraps {@link com.liferay.commerce.data.integration.manager.service.persistence.impl.HistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoryPersistence
 * @see com.liferay.commerce.data.integration.manager.service.persistence.impl.HistoryPersistenceImpl
 * @generated
 */
@ProviderType
public class HistoryUtil {
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
	public static void clearCache(History history) {
		getPersistence().clearCache(history);
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
	public static List<History> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<History> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<History> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static History update(History history) {
		return getPersistence().update(history);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static History update(History history, ServiceContext serviceContext) {
		return getPersistence().update(history, serviceContext);
	}

	/**
	* Returns all the histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching histories
	*/
	public static List<History> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories
	*/
	public static List<History> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories
	*/
	public static List<History> findByUuid(String uuid, int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching histories
	*/
	public static List<History> findByUuid(String uuid, int start, int end,
		OrderByComparator<History> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByUuid_First(String uuid,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUuid_First(String uuid,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByUuid_Last(String uuid,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUuid_Last(String uuid,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set where uuid = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] findByUuid_PrevAndNext(long historyId, String uuid,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(historyId, uuid, orderByComparator);
	}

	/**
	* Removes all the histories where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching histories
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHistoryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the history where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the history that was removed
	*/
	public static History removeByUUID_G(String uuid, long groupId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of histories where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching histories
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching histories
	*/
	public static List<History> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories
	*/
	public static List<History> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories
	*/
	public static List<History> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching histories
	*/
	public static List<History> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] findByUuid_C_PrevAndNext(long historyId,
		String uuid, long companyId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(historyId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the histories where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching histories
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching histories
	*/
	public static List<History> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories
	*/
	public static List<History> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories
	*/
	public static List<History> findByGroupId(long groupId, int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching histories
	*/
	public static List<History> findByGroupId(long groupId, int start, int end,
		OrderByComparator<History> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByGroupId_First(long groupId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByGroupId_First(long groupId,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByGroupId_Last(long groupId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByGroupId_Last(long groupId,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set where groupId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] findByGroupId_PrevAndNext(long historyId,
		long groupId, OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(historyId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the histories that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the histories that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the histories that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set of histories that the user has permission to view where groupId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] filterFindByGroupId_PrevAndNext(long historyId,
		long groupId, OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(historyId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the histories where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching histories
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of histories that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching histories that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the histories where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching histories
	*/
	public static List<History> findByGroupId_Status(long groupId, int status) {
		return getPersistence().findByGroupId_Status(groupId, status);
	}

	/**
	* Returns a range of all the histories where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories
	*/
	public static List<History> findByGroupId_Status(long groupId, int status,
		int start, int end) {
		return getPersistence().findByGroupId_Status(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the histories where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories
	*/
	public static List<History> findByGroupId_Status(long groupId, int status,
		int start, int end, OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .findByGroupId_Status(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching histories
	*/
	public static List<History> findByGroupId_Status(long groupId, int status,
		int start, int end, OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId_Status(groupId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByGroupId_Status_First(long groupId, int status,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByGroupId_Status_First(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the first history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByGroupId_Status_First(long groupId, int status,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Status_First(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByGroupId_Status_Last(long groupId, int status,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByGroupId_Status_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByGroupId_Status_Last(long groupId, int status,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Status_Last(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] findByGroupId_Status_PrevAndNext(long historyId,
		long groupId, int status, OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByGroupId_Status_PrevAndNext(historyId, groupId,
			status, orderByComparator);
	}

	/**
	* Returns all the histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId_Status(long groupId,
		int status) {
		return getPersistence().filterFindByGroupId_Status(groupId, status);
	}

	/**
	* Returns a range of all the histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId_Status(long groupId,
		int status, int start, int end) {
		return getPersistence()
				   .filterFindByGroupId_Status(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the histories that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories that the user has permission to view
	*/
	public static List<History> filterFindByGroupId_Status(long groupId,
		int status, int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId_Status(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set of histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] filterFindByGroupId_Status_PrevAndNext(
		long historyId, long groupId, int status,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .filterFindByGroupId_Status_PrevAndNext(historyId, groupId,
			status, orderByComparator);
	}

	/**
	* Removes all the histories where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByGroupId_Status(long groupId, int status) {
		getPersistence().removeByGroupId_Status(groupId, status);
	}

	/**
	* Returns the number of histories where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching histories
	*/
	public static int countByGroupId_Status(long groupId, int status) {
		return getPersistence().countByGroupId_Status(groupId, status);
	}

	/**
	* Returns the number of histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching histories that the user has permission to view
	*/
	public static int filterCountByGroupId_Status(long groupId, int status) {
		return getPersistence().filterCountByGroupId_Status(groupId, status);
	}

	/**
	* Returns all the histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @return the matching histories
	*/
	public static List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId) {
		return getPersistence().findByScheduledTaskId(companyId, scheduledTaskId);
	}

	/**
	* Returns a range of all the histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of matching histories
	*/
	public static List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end) {
		return getPersistence()
				   .findByScheduledTaskId(companyId, scheduledTaskId, start, end);
	}

	/**
	* Returns an ordered range of all the histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching histories
	*/
	public static List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .findByScheduledTaskId(companyId, scheduledTaskId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching histories
	*/
	public static List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end,
		OrderByComparator<History> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByScheduledTaskId(companyId, scheduledTaskId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByScheduledTaskId_First(long companyId,
		long scheduledTaskId, OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByScheduledTaskId_First(companyId, scheduledTaskId,
			orderByComparator);
	}

	/**
	* Returns the first history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByScheduledTaskId_First(long companyId,
		long scheduledTaskId, OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByScheduledTaskId_First(companyId, scheduledTaskId,
			orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public static History findByScheduledTaskId_Last(long companyId,
		long scheduledTaskId, OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByScheduledTaskId_Last(companyId, scheduledTaskId,
			orderByComparator);
	}

	/**
	* Returns the last history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public static History fetchByScheduledTaskId_Last(long companyId,
		long scheduledTaskId, OrderByComparator<History> orderByComparator) {
		return getPersistence()
				   .fetchByScheduledTaskId_Last(companyId, scheduledTaskId,
			orderByComparator);
	}

	/**
	* Returns the histories before and after the current history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History[] findByScheduledTaskId_PrevAndNext(long historyId,
		long companyId, long scheduledTaskId,
		OrderByComparator<History> orderByComparator)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence()
				   .findByScheduledTaskId_PrevAndNext(historyId, companyId,
			scheduledTaskId, orderByComparator);
	}

	/**
	* Removes all the histories where companyId = &#63; and scheduledTaskId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	*/
	public static void removeByScheduledTaskId(long companyId,
		long scheduledTaskId) {
		getPersistence().removeByScheduledTaskId(companyId, scheduledTaskId);
	}

	/**
	* Returns the number of histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @return the number of matching histories
	*/
	public static int countByScheduledTaskId(long companyId,
		long scheduledTaskId) {
		return getPersistence()
				   .countByScheduledTaskId(companyId, scheduledTaskId);
	}

	/**
	* Caches the history in the entity cache if it is enabled.
	*
	* @param history the history
	*/
	public static void cacheResult(History history) {
		getPersistence().cacheResult(history);
	}

	/**
	* Caches the histories in the entity cache if it is enabled.
	*
	* @param histories the histories
	*/
	public static void cacheResult(List<History> histories) {
		getPersistence().cacheResult(histories);
	}

	/**
	* Creates a new history with the primary key. Does not add the history to the database.
	*
	* @param historyId the primary key for the new history
	* @return the new history
	*/
	public static History create(long historyId) {
		return getPersistence().create(historyId);
	}

	/**
	* Removes the history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param historyId the primary key of the history
	* @return the history that was removed
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History remove(long historyId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().remove(historyId);
	}

	public static History updateImpl(History history) {
		return getPersistence().updateImpl(history);
	}

	/**
	* Returns the history with the primary key or throws a {@link NoSuchHistoryException} if it could not be found.
	*
	* @param historyId the primary key of the history
	* @return the history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public static History findByPrimaryKey(long historyId)
		throws com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException {
		return getPersistence().findByPrimaryKey(historyId);
	}

	/**
	* Returns the history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param historyId the primary key of the history
	* @return the history, or <code>null</code> if a history with the primary key could not be found
	*/
	public static History fetchByPrimaryKey(long historyId) {
		return getPersistence().fetchByPrimaryKey(historyId);
	}

	public static java.util.Map<java.io.Serializable, History> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the histories.
	*
	* @return the histories
	*/
	public static List<History> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of histories
	*/
	public static List<History> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of histories
	*/
	public static List<History> findAll(int start, int end,
		OrderByComparator<History> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of histories
	*/
	public static List<History> findAll(int start, int end,
		OrderByComparator<History> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the histories from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of histories.
	*
	* @return the number of histories
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistoryPersistence, HistoryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HistoryPersistence.class);

		ServiceTracker<HistoryPersistence, HistoryPersistence> serviceTracker = new ServiceTracker<HistoryPersistence, HistoryPersistence>(bundle.getBundleContext(),
				HistoryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}