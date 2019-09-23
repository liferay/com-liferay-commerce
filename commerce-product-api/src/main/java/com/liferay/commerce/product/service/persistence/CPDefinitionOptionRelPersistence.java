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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionOptionRelException;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the cp definition option rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionOptionRelUtil
 * @generated
 */
@ProviderType
public interface CPDefinitionOptionRelPersistence
	extends BasePersistence<CPDefinitionOptionRel> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionOptionRelUtil} to access the cp definition option rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CPDefinitionOptionRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the cp definition option rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid(String uuid);

	/**
	 * Returns a range of all the cp definition option rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where uuid = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByUuid_PrevAndNext(
			long CPDefinitionOptionRelId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of cp definition option rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp definition option rels
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the cp definition option rel where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDefinitionOptionRelException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the cp definition option rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the cp definition option rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the cp definition option rel where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp definition option rel that was removed
	 */
	public CPDefinitionOptionRel removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the number of cp definition option rels where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the cp definition option rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the cp definition option rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByUuid_C_PrevAndNext(
			long CPDefinitionOptionRelId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of cp definition option rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the cp definition option rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByGroupId(long groupId);

	/**
	 * Returns a range of all the cp definition option rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where groupId = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByGroupId_PrevAndNext(
			long CPDefinitionOptionRelId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of cp definition option rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the cp definition option rels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCompanyId(
		long companyId);

	/**
	 * Returns a range of all the cp definition option rels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where companyId = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByCompanyId_PrevAndNext(
			long CPDefinitionOptionRelId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of cp definition option rels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the cp definition option rels where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCPDefinitionId(
		long CPDefinitionId);

	/**
	 * Returns a range of all the cp definition option rels where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCPDefinitionId(
		long CPDefinitionId, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByCPDefinitionId_First(
			long CPDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByCPDefinitionId_First(
		long CPDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByCPDefinitionId_Last(
			long CPDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByCPDefinitionId_Last(
		long CPDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByCPDefinitionId_PrevAndNext(
			long CPDefinitionOptionRelId, long CPDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 */
	public void removeByCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the number of cp definition option rels where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the cp definition option rel where CPDefinitionId = &#63; and CPOptionId = &#63; or throws a <code>NoSuchCPDefinitionOptionRelException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionId the cp option ID
	 * @return the matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByC_C(long CPDefinitionId, long CPOptionId)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the cp definition option rel where CPDefinitionId = &#63; and CPOptionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionId the cp option ID
	 * @return the matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByC_C(
		long CPDefinitionId, long CPOptionId);

	/**
	 * Returns the cp definition option rel where CPDefinitionId = &#63; and CPOptionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionId the cp option ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByC_C(
		long CPDefinitionId, long CPOptionId, boolean useFinderCache);

	/**
	 * Removes the cp definition option rel where CPDefinitionId = &#63; and CPOptionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionId the cp option ID
	 * @return the cp definition option rel that was removed
	 */
	public CPDefinitionOptionRel removeByC_C(
			long CPDefinitionId, long CPOptionId)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the number of cp definition option rels where CPDefinitionId = &#63; and CPOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionId the cp option ID
	 * @return the number of matching cp definition option rels
	 */
	public int countByC_C(long CPDefinitionId, long CPOptionId);

	/**
	 * Returns all the cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @return the matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByC_SC(
		long CPDefinitionId, boolean skuContributor);

	/**
	 * Returns a range of all the cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByC_SC(
		long CPDefinitionId, boolean skuContributor, int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByC_SC(
		long CPDefinitionId, boolean skuContributor, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findByC_SC(
		long CPDefinitionId, boolean skuContributor, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp definition option rel in the ordered set where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByC_SC_First(
			long CPDefinitionId, boolean skuContributor,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the first cp definition option rel in the ordered set where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByC_SC_First(
		long CPDefinitionId, boolean skuContributor,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the last cp definition option rel in the ordered set where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel findByC_SC_Last(
			long CPDefinitionId, boolean skuContributor,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the last cp definition option rel in the ordered set where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition option rel, or <code>null</code> if a matching cp definition option rel could not be found
	 */
	public CPDefinitionOptionRel fetchByC_SC_Last(
		long CPDefinitionId, boolean skuContributor,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns the cp definition option rels before and after the current cp definition option rel in the ordered set where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the current cp definition option rel
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel[] findByC_SC_PrevAndNext(
			long CPDefinitionOptionRelId, long CPDefinitionId,
			boolean skuContributor,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPDefinitionOptionRel> orderByComparator)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Removes all the cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 */
	public void removeByC_SC(long CPDefinitionId, boolean skuContributor);

	/**
	 * Returns the number of cp definition option rels where CPDefinitionId = &#63; and skuContributor = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param skuContributor the sku contributor
	 * @return the number of matching cp definition option rels
	 */
	public int countByC_SC(long CPDefinitionId, boolean skuContributor);

	/**
	 * Caches the cp definition option rel in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionOptionRel the cp definition option rel
	 */
	public void cacheResult(CPDefinitionOptionRel cpDefinitionOptionRel);

	/**
	 * Caches the cp definition option rels in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionOptionRels the cp definition option rels
	 */
	public void cacheResult(
		java.util.List<CPDefinitionOptionRel> cpDefinitionOptionRels);

	/**
	 * Creates a new cp definition option rel with the primary key. Does not add the cp definition option rel to the database.
	 *
	 * @param CPDefinitionOptionRelId the primary key for the new cp definition option rel
	 * @return the new cp definition option rel
	 */
	public CPDefinitionOptionRel create(long CPDefinitionOptionRelId);

	/**
	 * Removes the cp definition option rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the cp definition option rel
	 * @return the cp definition option rel that was removed
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel remove(long CPDefinitionOptionRelId)
		throws NoSuchCPDefinitionOptionRelException;

	public CPDefinitionOptionRel updateImpl(
		CPDefinitionOptionRel cpDefinitionOptionRel);

	/**
	 * Returns the cp definition option rel with the primary key or throws a <code>NoSuchCPDefinitionOptionRelException</code> if it could not be found.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the cp definition option rel
	 * @return the cp definition option rel
	 * @throws NoSuchCPDefinitionOptionRelException if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel findByPrimaryKey(long CPDefinitionOptionRelId)
		throws NoSuchCPDefinitionOptionRelException;

	/**
	 * Returns the cp definition option rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDefinitionOptionRelId the primary key of the cp definition option rel
	 * @return the cp definition option rel, or <code>null</code> if a cp definition option rel with the primary key could not be found
	 */
	public CPDefinitionOptionRel fetchByPrimaryKey(
		long CPDefinitionOptionRelId);

	/**
	 * Returns all the cp definition option rels.
	 *
	 * @return the cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findAll();

	/**
	 * Returns a range of all the cp definition option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @return the range of cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cp definition option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp definition option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionOptionRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition option rels
	 * @param end the upper bound of the range of cp definition option rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp definition option rels
	 */
	public java.util.List<CPDefinitionOptionRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDefinitionOptionRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cp definition option rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cp definition option rels.
	 *
	 * @return the number of cp definition option rels
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}