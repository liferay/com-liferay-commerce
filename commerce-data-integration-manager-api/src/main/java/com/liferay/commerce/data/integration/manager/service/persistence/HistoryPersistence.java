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

import com.liferay.commerce.data.integration.manager.exception.NoSuchHistoryException;
import com.liferay.commerce.data.integration.manager.model.History;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.commerce.data.integration.manager.service.persistence.impl.HistoryPersistenceImpl
 * @see HistoryUtil
 * @generated
 */
@ProviderType
public interface HistoryPersistence extends BasePersistence<History> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistoryUtil} to access the history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching histories
	*/
	public java.util.List<History> findByUuid(String uuid);

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
	public java.util.List<History> findByUuid(String uuid, int start, int end);

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
	public java.util.List<History> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the first history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the last history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the last history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the histories before and after the current history in the ordered set where uuid = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public History[] findByUuid_PrevAndNext(long historyId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Removes all the histories where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching histories
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHistoryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByUUID_G(String uuid, long groupId)
		throws NoSuchHistoryException;

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the history where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the history that was removed
	*/
	public History removeByUUID_G(String uuid, long groupId)
		throws NoSuchHistoryException;

	/**
	* Returns the number of histories where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching histories
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching histories
	*/
	public java.util.List<History> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<History> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<History> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the first history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the last history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the last history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public History[] findByUuid_C_PrevAndNext(long historyId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Removes all the histories where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching histories
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching histories
	*/
	public java.util.List<History> findByGroupId(long groupId);

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
	public java.util.List<History> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<History> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the first history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the last history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the last history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the histories before and after the current history in the ordered set where groupId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public History[] findByGroupId_PrevAndNext(long historyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns all the histories that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching histories that the user has permission to view
	*/
	public java.util.List<History> filterFindByGroupId(long groupId);

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
	public java.util.List<History> filterFindByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<History> filterFindByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the histories before and after the current history in the ordered set of histories that the user has permission to view where groupId = &#63;.
	*
	* @param historyId the primary key of the current history
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public History[] filterFindByGroupId_PrevAndNext(long historyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Removes all the histories where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching histories
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of histories that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching histories that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the histories where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching histories
	*/
	public java.util.List<History> findByGroupId_Status(long groupId, int status);

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
	public java.util.List<History> findByGroupId_Status(long groupId,
		int status, int start, int end);

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
	public java.util.List<History> findByGroupId_Status(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findByGroupId_Status(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByGroupId_Status_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the first history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByGroupId_Status_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the last history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByGroupId_Status_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the last history in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByGroupId_Status_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public History[] findByGroupId_Status_PrevAndNext(long historyId,
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns all the histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching histories that the user has permission to view
	*/
	public java.util.List<History> filterFindByGroupId_Status(long groupId,
		int status);

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
	public java.util.List<History> filterFindByGroupId_Status(long groupId,
		int status, int start, int end);

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
	public java.util.List<History> filterFindByGroupId_Status(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public History[] filterFindByGroupId_Status_PrevAndNext(long historyId,
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Removes all the histories where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByGroupId_Status(long groupId, int status);

	/**
	* Returns the number of histories where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching histories
	*/
	public int countByGroupId_Status(long groupId, int status);

	/**
	* Returns the number of histories that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching histories that the user has permission to view
	*/
	public int filterCountByGroupId_Status(long groupId, int status);

	/**
	* Returns all the histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @return the matching histories
	*/
	public java.util.List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId);

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
	public java.util.List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end);

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
	public java.util.List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findByScheduledTaskId(long companyId,
		long scheduledTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByScheduledTaskId_First(long companyId,
		long scheduledTaskId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the first history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByScheduledTaskId_First(long companyId,
		long scheduledTaskId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

	/**
	* Returns the last history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history
	* @throws NoSuchHistoryException if a matching history could not be found
	*/
	public History findByScheduledTaskId_Last(long companyId,
		long scheduledTaskId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Returns the last history in the ordered set where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching history, or <code>null</code> if a matching history could not be found
	*/
	public History fetchByScheduledTaskId_Last(long companyId,
		long scheduledTaskId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public History[] findByScheduledTaskId_PrevAndNext(long historyId,
		long companyId, long scheduledTaskId,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator)
		throws NoSuchHistoryException;

	/**
	* Removes all the histories where companyId = &#63; and scheduledTaskId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	*/
	public void removeByScheduledTaskId(long companyId, long scheduledTaskId);

	/**
	* Returns the number of histories where companyId = &#63; and scheduledTaskId = &#63;.
	*
	* @param companyId the company ID
	* @param scheduledTaskId the scheduled task ID
	* @return the number of matching histories
	*/
	public int countByScheduledTaskId(long companyId, long scheduledTaskId);

	/**
	* Caches the history in the entity cache if it is enabled.
	*
	* @param history the history
	*/
	public void cacheResult(History history);

	/**
	* Caches the histories in the entity cache if it is enabled.
	*
	* @param histories the histories
	*/
	public void cacheResult(java.util.List<History> histories);

	/**
	* Creates a new history with the primary key. Does not add the history to the database.
	*
	* @param historyId the primary key for the new history
	* @return the new history
	*/
	public History create(long historyId);

	/**
	* Removes the history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param historyId the primary key of the history
	* @return the history that was removed
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public History remove(long historyId) throws NoSuchHistoryException;

	public History updateImpl(History history);

	/**
	* Returns the history with the primary key or throws a {@link NoSuchHistoryException} if it could not be found.
	*
	* @param historyId the primary key of the history
	* @return the history
	* @throws NoSuchHistoryException if a history with the primary key could not be found
	*/
	public History findByPrimaryKey(long historyId)
		throws NoSuchHistoryException;

	/**
	* Returns the history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param historyId the primary key of the history
	* @return the history, or <code>null</code> if a history with the primary key could not be found
	*/
	public History fetchByPrimaryKey(long historyId);

	@Override
	public java.util.Map<java.io.Serializable, History> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the histories.
	*
	* @return the histories
	*/
	public java.util.List<History> findAll();

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
	public java.util.List<History> findAll(int start, int end);

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
	public java.util.List<History> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator);

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
	public java.util.List<History> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<History> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the histories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of histories.
	*
	* @return the number of histories
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}