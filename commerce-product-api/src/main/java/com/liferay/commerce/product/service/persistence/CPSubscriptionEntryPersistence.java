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

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionEntryException;
import com.liferay.commerce.product.model.CPSubscriptionEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the cp subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CPSubscriptionEntryPersistenceImpl
 * @see CPSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public interface CPSubscriptionEntryPersistence extends BasePersistence<CPSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPSubscriptionEntryUtil} to access the cp subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cp subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid(String uuid);

	/**
	* Returns a range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionEntryId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Removes all the cp subscription entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of cp subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching cp subscription entries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the cp subscription entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the cp subscription entry that was removed
	*/
	public CPSubscriptionEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the number of cp subscription entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching cp subscription entries
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionEntryId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Removes all the cp subscription entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of cp subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching cp subscription entries
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the cp subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByGroupId(long groupId);

	/**
	* Returns a range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Removes all the cp subscription entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of cp subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching cp subscription entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the cp subscription entries where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @return the matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByCPInstanceId(
		long CPInstanceId);

	/**
	* Returns a range of all the cp subscription entries where CPInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPInstanceId the cp instance ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByCPInstanceId(
		long CPInstanceId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries where CPInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPInstanceId the cp instance ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByCPInstanceId(
		long CPInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries where CPInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPInstanceId the cp instance ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByCPInstanceId(
		long CPInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription entry in the ordered set where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByCPInstanceId_First(long CPInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the first cp subscription entry in the ordered set where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByCPInstanceId_First(long CPInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the last cp subscription entry in the ordered set where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByCPInstanceId_Last(long CPInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the last cp subscription entry in the ordered set where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByCPInstanceId_Last(long CPInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where CPInstanceId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param CPInstanceId the cp instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry[] findByCPInstanceId_PrevAndNext(
		long CPSubscriptionEntryId, long CPInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Removes all the cp subscription entries where CPInstanceId = &#63; from the database.
	*
	* @param CPInstanceId the cp instance ID
	*/
	public void removeByCPInstanceId(long CPInstanceId);

	/**
	* Returns the number of cp subscription entries where CPInstanceId = &#63;.
	*
	* @param CPInstanceId the cp instance ID
	* @return the number of matching cp subscription entries
	*/
	public int countByCPInstanceId(long CPInstanceId);

	/**
	* Returns all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId);

	/**
	* Returns a range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry findByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	public CPSubscriptionEntry fetchByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry[] findByG_U_PrevAndNext(
		long CPSubscriptionEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Removes all the cp subscription entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_U(long groupId, long userId);

	/**
	* Returns the number of cp subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching cp subscription entries
	*/
	public int countByG_U(long groupId, long userId);

	/**
	* Caches the cp subscription entry in the entity cache if it is enabled.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	*/
	public void cacheResult(CPSubscriptionEntry cpSubscriptionEntry);

	/**
	* Caches the cp subscription entries in the entity cache if it is enabled.
	*
	* @param cpSubscriptionEntries the cp subscription entries
	*/
	public void cacheResult(
		java.util.List<CPSubscriptionEntry> cpSubscriptionEntries);

	/**
	* Creates a new cp subscription entry with the primary key. Does not add the cp subscription entry to the database.
	*
	* @param CPSubscriptionEntryId the primary key for the new cp subscription entry
	* @return the new cp subscription entry
	*/
	public CPSubscriptionEntry create(long CPSubscriptionEntryId);

	/**
	* Removes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry that was removed
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry remove(long CPSubscriptionEntryId)
		throws NoSuchCPSubscriptionEntryException;

	public CPSubscriptionEntry updateImpl(
		CPSubscriptionEntry cpSubscriptionEntry);

	/**
	* Returns the cp subscription entry with the primary key or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry
	* @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry findByPrimaryKey(long CPSubscriptionEntryId)
		throws NoSuchCPSubscriptionEntryException;

	/**
	* Returns the cp subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry, or <code>null</code> if a cp subscription entry with the primary key could not be found
	*/
	public CPSubscriptionEntry fetchByPrimaryKey(long CPSubscriptionEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CPSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cp subscription entries.
	*
	* @return the cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findAll();

	/**
	* Returns a range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp subscription entries
	*/
	public java.util.List<CPSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cp subscription entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cp subscription entries.
	*
	* @return the number of cp subscription entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}