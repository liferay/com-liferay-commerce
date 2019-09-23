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

import com.liferay.commerce.product.exception.NoSuchCPFriendlyURLEntryException;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the cp friendly url entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPFriendlyURLEntryUtil
 * @generated
 */
@ProviderType
public interface CPFriendlyURLEntryPersistence
	extends BasePersistence<CPFriendlyURLEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPFriendlyURLEntryUtil} to access the cp friendly url entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CPFriendlyURLEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the cp friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid(String uuid);

	/**
	 * Returns a range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByUuid_PrevAndNext(
			long CPFriendlyURLEntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp friendly url entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the cp friendly url entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp friendly url entry that was removed
	 */
	public CPFriendlyURLEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp friendly url entries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByUuid_C_PrevAndNext(
			long CPFriendlyURLEntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp friendly url entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the cp friendly url entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByC_C(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the cp friendly url entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByC_C_PrevAndNext(
			long CPFriendlyURLEntryId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of cp friendly url entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching cp friendly url entries
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK);

	/**
	 * Returns a range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_First(
			long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_First(
		long groupId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_Last(
			long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_Last(
		long groupId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByG_C_C_PrevAndNext(
			long CPFriendlyURLEntryId, long groupId, long classNameId,
			long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByG_C_C(long groupId, long classNameId, long classPK);

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_C(long groupId, long classNameId, long classPK);

	/**
	 * Returns all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_U(
		long groupId, long classNameId, String urlTitle);

	/**
	 * Returns a range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_U(
		long groupId, long classNameId, String urlTitle, int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_U(
		long groupId, long classNameId, String urlTitle, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_U(
		long groupId, long classNameId, String urlTitle, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_U_First(
			long groupId, long classNameId, String urlTitle,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_U_First(
		long groupId, long classNameId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_U_Last(
			long groupId, long classNameId, String urlTitle,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_U_Last(
		long groupId, long classNameId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByG_C_U_PrevAndNext(
			long CPFriendlyURLEntryId, long groupId, long classNameId,
			String urlTitle,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 */
	public void removeByG_C_U(long groupId, long classNameId, String urlTitle);

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param urlTitle the url title
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_U(long groupId, long classNameId, String urlTitle);

	/**
	 * Returns all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @return the matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main);

	/**
	 * Returns a range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main, int start,
		int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_M_First(
			long groupId, long classNameId, long classPK, boolean main,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the first cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_M_First(
		long groupId, long classNameId, long classPK, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_M_Last(
			long groupId, long classNameId, long classPK, boolean main,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the last cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_M_Last(
		long groupId, long classNameId, long classPK, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the current cp friendly url entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry[] findByG_C_C_M_PrevAndNext(
			long CPFriendlyURLEntryId, long groupId, long classNameId,
			long classPK, boolean main,
			com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
				orderByComparator)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Removes all the cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 */
	public void removeByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main);

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param main the main
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_C_M(
		long groupId, long classNameId, long classPK, boolean main);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and languageId = &#63; and urlTitle = &#63; or throws a <code>NoSuchCPFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_L_U(
			long groupId, long classNameId, String languageId, String urlTitle)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and languageId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_L_U(
		long groupId, long classNameId, String languageId, String urlTitle);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and languageId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_L_U(
		long groupId, long classNameId, String languageId, String urlTitle,
		boolean useFinderCache);

	/**
	 * Removes the cp friendly url entry where groupId = &#63; and classNameId = &#63; and languageId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the cp friendly url entry that was removed
	 */
	public CPFriendlyURLEntry removeByG_C_L_U(
			long groupId, long classNameId, String languageId, String urlTitle)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and languageId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_L_U(
		long groupId, long classNameId, String languageId, String urlTitle);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and urlTitle = &#63; or throws a <code>NoSuchCPFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_L_U(
			long groupId, long classNameId, long classPK, String languageId,
			String urlTitle)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_L_U(
		long groupId, long classNameId, long classPK, String languageId,
		String urlTitle);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_L_U(
		long groupId, long classNameId, long classPK, String languageId,
		String urlTitle, boolean useFinderCache);

	/**
	 * Removes the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the cp friendly url entry that was removed
	 */
	public CPFriendlyURLEntry removeByG_C_C_L_U(
			long groupId, long classNameId, long classPK, String languageId,
			String urlTitle)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param urlTitle the url title
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_C_L_U(
		long groupId, long classNameId, long classPK, String languageId,
		String urlTitle);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and main = &#63; or throws a <code>NoSuchCPFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param main the main
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry findByG_C_C_L_M(
			long groupId, long classNameId, long classPK, String languageId,
			boolean main)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and main = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param main the main
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_L_M(
		long groupId, long classNameId, long classPK, String languageId,
		boolean main);

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and main = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param main the main
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	public CPFriendlyURLEntry fetchByG_C_C_L_M(
		long groupId, long classNameId, long classPK, String languageId,
		boolean main, boolean useFinderCache);

	/**
	 * Removes the cp friendly url entry where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param main the main
	 * @return the cp friendly url entry that was removed
	 */
	public CPFriendlyURLEntry removeByG_C_C_L_M(
			long groupId, long classNameId, long classPK, String languageId,
			boolean main)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; and languageId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param languageId the language ID
	 * @param main the main
	 * @return the number of matching cp friendly url entries
	 */
	public int countByG_C_C_L_M(
		long groupId, long classNameId, long classPK, String languageId,
		boolean main);

	/**
	 * Caches the cp friendly url entry in the entity cache if it is enabled.
	 *
	 * @param cpFriendlyURLEntry the cp friendly url entry
	 */
	public void cacheResult(CPFriendlyURLEntry cpFriendlyURLEntry);

	/**
	 * Caches the cp friendly url entries in the entity cache if it is enabled.
	 *
	 * @param cpFriendlyURLEntries the cp friendly url entries
	 */
	public void cacheResult(
		java.util.List<CPFriendlyURLEntry> cpFriendlyURLEntries);

	/**
	 * Creates a new cp friendly url entry with the primary key. Does not add the cp friendly url entry to the database.
	 *
	 * @param CPFriendlyURLEntryId the primary key for the new cp friendly url entry
	 * @return the new cp friendly url entry
	 */
	public CPFriendlyURLEntry create(long CPFriendlyURLEntryId);

	/**
	 * Removes the cp friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry that was removed
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry remove(long CPFriendlyURLEntryId)
		throws NoSuchCPFriendlyURLEntryException;

	public CPFriendlyURLEntry updateImpl(CPFriendlyURLEntry cpFriendlyURLEntry);

	/**
	 * Returns the cp friendly url entry with the primary key or throws a <code>NoSuchCPFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry
	 * @throws NoSuchCPFriendlyURLEntryException if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry findByPrimaryKey(long CPFriendlyURLEntryId)
		throws NoSuchCPFriendlyURLEntryException;

	/**
	 * Returns the cp friendly url entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPFriendlyURLEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry, or <code>null</code> if a cp friendly url entry with the primary key could not be found
	 */
	public CPFriendlyURLEntry fetchByPrimaryKey(long CPFriendlyURLEntryId);

	/**
	 * Returns all the cp friendly url entries.
	 *
	 * @return the cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findAll();

	/**
	 * Returns a range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPFriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp friendly url entries
	 */
	public java.util.List<CPFriendlyURLEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPFriendlyURLEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cp friendly url entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cp friendly url entries.
	 *
	 * @return the number of cp friendly url entries
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}