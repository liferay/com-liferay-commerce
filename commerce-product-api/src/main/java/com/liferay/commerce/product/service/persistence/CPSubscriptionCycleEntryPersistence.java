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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException;
import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the cp subscription cycle entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CPSubscriptionCycleEntryPersistenceImpl
 * @see CPSubscriptionCycleEntryUtil
 * @generated
 */
@ProviderType
public interface CPSubscriptionCycleEntryPersistence extends BasePersistence<CPSubscriptionCycleEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPSubscriptionCycleEntryUtil} to access the cp subscription cycle entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cp subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid(String uuid);

	/**
	* Returns a range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Removes all the cp subscription cycle entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the cp subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the cp subscription cycle entry that was removed
	*/
	public CPSubscriptionCycleEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Removes all the cp subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the cp subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByGroupId(long groupId);

	/**
	* Returns a range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where groupId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Removes all the cp subscription cycle entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of cp subscription cycle entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @return the matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId);

	/**
	* Returns a range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry[] findByCPSubscriptionEntryId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long CPSubscriptionEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Removes all the cp subscription cycle entries where CPSubscriptionEntryId = &#63; from the database.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	*/
	public void removeByCPSubscriptionEntryId(long CPSubscriptionEntryId);

	/**
	* Returns the number of cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	*
	* @param CPSubscriptionEntryId the cp subscription entry ID
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByCPSubscriptionEntryId(long CPSubscriptionEntryId);

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry findByCommerceOrderItemId(
		long commerceOrderItemId)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId);

	/**
	* Returns the cp subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	public CPSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId, boolean retrieveFromCache);

	/**
	* Removes the cp subscription cycle entry where commerceOrderItemId = &#63; from the database.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the cp subscription cycle entry that was removed
	*/
	public CPSubscriptionCycleEntry removeByCommerceOrderItemId(
		long commerceOrderItemId)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the number of cp subscription cycle entries where commerceOrderItemId = &#63;.
	*
	* @param commerceOrderItemId the commerce order item ID
	* @return the number of matching cp subscription cycle entries
	*/
	public int countByCommerceOrderItemId(long commerceOrderItemId);

	/**
	* Caches the cp subscription cycle entry in the entity cache if it is enabled.
	*
	* @param cpSubscriptionCycleEntry the cp subscription cycle entry
	*/
	public void cacheResult(CPSubscriptionCycleEntry cpSubscriptionCycleEntry);

	/**
	* Caches the cp subscription cycle entries in the entity cache if it is enabled.
	*
	* @param cpSubscriptionCycleEntries the cp subscription cycle entries
	*/
	public void cacheResult(
		java.util.List<CPSubscriptionCycleEntry> cpSubscriptionCycleEntries);

	/**
	* Creates a new cp subscription cycle entry with the primary key. Does not add the cp subscription cycle entry to the database.
	*
	* @param CPSubscriptionCycleEntryId the primary key for the new cp subscription cycle entry
	* @return the new cp subscription cycle entry
	*/
	public CPSubscriptionCycleEntry create(long CPSubscriptionCycleEntryId);

	/**
	* Removes the cp subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry that was removed
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry remove(long CPSubscriptionCycleEntryId)
		throws NoSuchCPSubscriptionCycleEntryException;

	public CPSubscriptionCycleEntry updateImpl(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry);

	/**
	* Returns the cp subscription cycle entry with the primary key or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry
	* @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry findByPrimaryKey(
		long CPSubscriptionCycleEntryId)
		throws NoSuchCPSubscriptionCycleEntryException;

	/**
	* Returns the cp subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry, or <code>null</code> if a cp subscription cycle entry with the primary key could not be found
	*/
	public CPSubscriptionCycleEntry fetchByPrimaryKey(
		long CPSubscriptionCycleEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CPSubscriptionCycleEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cp subscription cycle entries.
	*
	* @return the cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findAll();

	/**
	* Returns a range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp subscription cycle entries
	*/
	public java.util.List<CPSubscriptionCycleEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cp subscription cycle entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cp subscription cycle entries.
	*
	* @return the number of cp subscription cycle entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}