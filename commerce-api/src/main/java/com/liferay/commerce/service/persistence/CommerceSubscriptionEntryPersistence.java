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

import com.liferay.commerce.exception.NoSuchSubscriptionEntryException;
import com.liferay.commerce.model.CommerceSubscriptionEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.persistence.impl.CommerceSubscriptionEntryPersistenceImpl
 * @see CommerceSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public interface CommerceSubscriptionEntryPersistence extends BasePersistence<CommerceSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceSubscriptionEntryUtil} to access the commerce subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid(String uuid);

	/**
	* Returns a range of all the commerce subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the first commerce subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the last commerce subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the last commerce subscription entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where uuid = &#63;.
	*
	* @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry[] findByUuid_PrevAndNext(
		long commerceSubscriptionEntryId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Removes all the commerce subscription entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of commerce subscription entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching commerce subscription entries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSubscriptionEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the commerce subscription entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the commerce subscription entry that was removed
	*/
	public CommerceSubscriptionEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the number of commerce subscription entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching commerce subscription entries
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the first commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the last commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the last commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry[] findByUuid_C_PrevAndNext(
		long commerceSubscriptionEntryId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Removes all the commerce subscription entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of commerce subscription entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching commerce subscription entries
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the commerce subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByGroupId(long groupId);

	/**
	* Returns a range of all the commerce subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the first commerce subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the last commerce subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the last commerce subscription entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where groupId = &#63;.
	*
	* @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry[] findByGroupId_PrevAndNext(
		long commerceSubscriptionEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Removes all the commerce subscription entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of commerce subscription entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching commerce subscription entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the commerce subscription entries where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @return the matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findBysubscriptionStatus(
		int subscriptionStatus);

	/**
	* Returns a range of all the commerce subscription entries where subscriptionStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subscriptionStatus the subscription status
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findBysubscriptionStatus(
		int subscriptionStatus, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries where subscriptionStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subscriptionStatus the subscription status
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findBysubscriptionStatus(
		int subscriptionStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries where subscriptionStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subscriptionStatus the subscription status
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findBysubscriptionStatus(
		int subscriptionStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findBysubscriptionStatus_First(
		int subscriptionStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the first commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchBysubscriptionStatus_First(
		int subscriptionStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the last commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findBysubscriptionStatus_Last(
		int subscriptionStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the last commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchBysubscriptionStatus_Last(
		int subscriptionStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	*
	* @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	* @param subscriptionStatus the subscription status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry[] findBysubscriptionStatus_PrevAndNext(
		long commerceSubscriptionEntryId, int subscriptionStatus,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Removes all the commerce subscription entries where subscriptionStatus = &#63; from the database.
	*
	* @param subscriptionStatus the subscription status
	*/
	public void removeBysubscriptionStatus(int subscriptionStatus);

	/**
	* Returns the number of commerce subscription entries where subscriptionStatus = &#63;.
	*
	* @param subscriptionStatus the subscription status
	* @return the number of matching commerce subscription entries
	*/
	public int countBysubscriptionStatus(int subscriptionStatus);

	/**
	* Returns all the commerce subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByG_U(long groupId,
		long userId);

	/**
	* Returns a range of all the commerce subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findByG_U(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the first commerce subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByG_U_First(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the last commerce subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry findByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the last commerce subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	*/
	public CommerceSubscriptionEntry fetchByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry[] findByG_U_PrevAndNext(
		long commerceSubscriptionEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	* Removes all the commerce subscription entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_U(long groupId, long userId);

	/**
	* Returns the number of commerce subscription entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching commerce subscription entries
	*/
	public int countByG_U(long groupId, long userId);

	/**
	* Caches the commerce subscription entry in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionEntry the commerce subscription entry
	*/
	public void cacheResult(CommerceSubscriptionEntry commerceSubscriptionEntry);

	/**
	* Caches the commerce subscription entries in the entity cache if it is enabled.
	*
	* @param commerceSubscriptionEntries the commerce subscription entries
	*/
	public void cacheResult(
		java.util.List<CommerceSubscriptionEntry> commerceSubscriptionEntries);

	/**
	* Creates a new commerce subscription entry with the primary key. Does not add the commerce subscription entry to the database.
	*
	* @param commerceSubscriptionEntryId the primary key for the new commerce subscription entry
	* @return the new commerce subscription entry
	*/
	public CommerceSubscriptionEntry create(long commerceSubscriptionEntryId);

	/**
	* Removes the commerce subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	* @return the commerce subscription entry that was removed
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry remove(long commerceSubscriptionEntryId)
		throws NoSuchSubscriptionEntryException;

	public CommerceSubscriptionEntry updateImpl(
		CommerceSubscriptionEntry commerceSubscriptionEntry);

	/**
	* Returns the commerce subscription entry with the primary key or throws a {@link NoSuchSubscriptionEntryException} if it could not be found.
	*
	* @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	* @return the commerce subscription entry
	* @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry findByPrimaryKey(
		long commerceSubscriptionEntryId)
		throws NoSuchSubscriptionEntryException;

	/**
	* Returns the commerce subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	* @return the commerce subscription entry, or <code>null</code> if a commerce subscription entry with the primary key could not be found
	*/
	public CommerceSubscriptionEntry fetchByPrimaryKey(
		long commerceSubscriptionEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce subscription entries.
	*
	* @return the commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findAll();

	/**
	* Returns a range of all the commerce subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @return the range of commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription entries
	* @param end the upper bound of the range of commerce subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce subscription entries
	*/
	public java.util.List<CommerceSubscriptionEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce subscription entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce subscription entries.
	*
	* @return the number of commerce subscription entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}