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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce subscription cycle entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.persistence.impl.CommerceSubscriptionCycleEntryPersistenceImpl
 * @see CommerceSubscriptionCycleEntryUtil
 * @generated
 */
@ProviderType
public interface CommerceSubscriptionCycleEntryPersistence
	extends BasePersistence<CommerceSubscriptionCycleEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceSubscriptionCycleEntryUtil} to access the commerce subscription cycle entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid(
		String uuid);

	/**
	* Returns a range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid(
		String uuid, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Removes all the commerce subscription cycle entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId);

	/**
	* Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the commerce subscription cycle entry that was removed
	*/
	public CommerceSubscriptionCycleEntry removeByUUID_G(String uuid,
		long groupId) throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	* Returns a range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Removes all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the commerce subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId);

	/**
	* Returns a range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long commerceSubscriptionCycleEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Removes all the commerce subscription cycle entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of commerce subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByCommerceOrderItemId(
		long commerceOrderItemId) throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId);

	/**
	* Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId, boolean retrieveFromCache);

	/**
	* Removes the commerce subscription cycle entry where commerceOrderItemId = &#63; from the database.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the commerce subscription cycle entry that was removed
	*/
	public CommerceSubscriptionCycleEntry removeByCommerceOrderItemId(
		long commerceOrderItemId) throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the number of commerce subscription cycle entries where commerceOrderItemId = &#63;.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByCommerceOrderItemId(long commerceOrderItemId);

	/**
	* Returns all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @return the matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId);

	/**
	* Returns a range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry[] findByCommerceSubscriptionEntryId_PrevAndNext(
		long commerceSubscriptionCycleEntryId,
		long commerceSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Removes all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63; from the database.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	*/
	public void removeByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId);

	/**
	* Returns the number of commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	*
	* @param commerceSubscriptionEntryId the commerce subscription entry ID
	* @return the number of matching commerce subscription cycle entries
	*/
	public int countByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId);

	/**
	* Caches the commerce subscription cycle entry in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	*/
	public void cacheResult(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry);

	/**
	* Caches the commerce subscription cycle entries in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionCycleEntries the commerce subscription cycle entries
	*/
	public void cacheResult(
		java.util.List<CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries);

	/**
	* Creates a new commerce subscription cycle entry with the primary key. Does not add the commerce subscription cycle entry to the database.
	*
	* @param commerceSubscriptionCycleEntryId the primary key for the new commerce subscription cycle entry
	* @return the new commerce subscription cycle entry
	*/
	public CommerceSubscriptionCycleEntry create(
		long commerceSubscriptionCycleEntryId);

	/**
	* Removes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry remove(
		long commerceSubscriptionCycleEntryId)
		throws NoSuchSubscriptionCycleEntryException;

	public CommerceSubscriptionCycleEntry updateImpl(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry);

	/**
	* Returns the commerce subscription cycle entry with the primary key or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry
	* @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry findByPrimaryKey(
		long commerceSubscriptionCycleEntryId)
		throws NoSuchSubscriptionCycleEntryException;

	/**
	* Returns the commerce subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry, or <code>null</code> if a commerce subscription cycle entry with the primary key could not be found
	*/
	public CommerceSubscriptionCycleEntry fetchByPrimaryKey(
		long commerceSubscriptionCycleEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceSubscriptionCycleEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce subscription cycle entries.
	*
	* @return the commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findAll();

	/**
	* Returns a range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce subscription cycle entries
	*/
	public java.util.List<CommerceSubscriptionCycleEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce subscription cycle entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce subscription cycle entries.
	*
	* @return the number of commerce subscription cycle entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}