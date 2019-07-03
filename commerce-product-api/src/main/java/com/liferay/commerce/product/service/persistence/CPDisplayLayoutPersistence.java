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

import com.liferay.commerce.product.exception.NoSuchCPDisplayLayoutException;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the cp display layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPDisplayLayoutUtil
 * @generated
 */
@ProviderType
public interface CPDisplayLayoutPersistence
	extends BasePersistence<CPDisplayLayout> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDisplayLayoutUtil} to access the cp display layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CPDisplayLayout> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the cp display layouts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid(String uuid);

	/**
	 * Returns a range of all the cp display layouts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @return the range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the cp display layouts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp display layouts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cp display layout in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the first cp display layout in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the last cp display layout in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the last cp display layout in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the cp display layouts before and after the current cp display layout in the ordered set where uuid = &#63;.
	 *
	 * @param CPDisplayLayoutId the primary key of the current cp display layout
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout[] findByUuid_PrevAndNext(
			long CPDisplayLayoutId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Removes all the cp display layouts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of cp display layouts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp display layouts
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the cp display layout where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDisplayLayoutException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByUUID_G(String uuid, long groupId)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the cp display layout where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the cp display layout where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the cp display layout where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp display layout that was removed
	 */
	public CPDisplayLayout removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the number of cp display layouts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp display layouts
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the cp display layouts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the cp display layouts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @return the range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the cp display layouts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp display layouts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cp display layout in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the first cp display layout in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the last cp display layout in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the last cp display layout in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the cp display layouts before and after the current cp display layout in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDisplayLayoutId the primary key of the current cp display layout
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout[] findByUuid_C_PrevAndNext(
			long CPDisplayLayoutId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Removes all the cp display layouts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of cp display layouts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp display layouts
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the cp display layouts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByGroupId(long groupId);

	/**
	 * Returns a range of all the cp display layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @return the range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the cp display layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp display layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cp display layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the first cp display layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the last cp display layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the last cp display layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the cp display layouts before and after the current cp display layout in the ordered set where groupId = &#63;.
	 *
	 * @param CPDisplayLayoutId the primary key of the current cp display layout
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout[] findByGroupId_PrevAndNext(
			long CPDisplayLayoutId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Removes all the cp display layouts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of cp display layouts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp display layouts
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the cp display layouts where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByG_C(
		long groupId, long classNameId);

	/**
	 * Returns a range of all the cp display layouts where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @return the range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByG_C(
		long groupId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the cp display layouts where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp display layouts where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cp display layout in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByG_C_First(
			long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the first cp display layout in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the last cp display layout in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByG_C_Last(
			long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the last cp display layout in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns the cp display layouts before and after the current cp display layout in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param CPDisplayLayoutId the primary key of the current cp display layout
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout[] findByG_C_PrevAndNext(
			long CPDisplayLayoutId, long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
				orderByComparator)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Removes all the cp display layouts where groupId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 */
	public void removeByG_C(long groupId, long classNameId);

	/**
	 * Returns the number of cp display layouts where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the number of matching cp display layouts
	 */
	public int countByG_C(long groupId, long classNameId);

	/**
	 * Returns the cp display layout where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchCPDisplayLayoutException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a matching cp display layout could not be found
	 */
	public CPDisplayLayout findByC_C(long classNameId, long classPK)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the cp display layout where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByC_C(long classNameId, long classPK);

	/**
	 * Returns the cp display layout where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp display layout, or <code>null</code> if a matching cp display layout could not be found
	 */
	public CPDisplayLayout fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache);

	/**
	 * Removes the cp display layout where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the cp display layout that was removed
	 */
	public CPDisplayLayout removeByC_C(long classNameId, long classPK)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the number of cp display layouts where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching cp display layouts
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Caches the cp display layout in the entity cache if it is enabled.
	 *
	 * @param cpDisplayLayout the cp display layout
	 */
	public void cacheResult(CPDisplayLayout cpDisplayLayout);

	/**
	 * Caches the cp display layouts in the entity cache if it is enabled.
	 *
	 * @param cpDisplayLayouts the cp display layouts
	 */
	public void cacheResult(java.util.List<CPDisplayLayout> cpDisplayLayouts);

	/**
	 * Creates a new cp display layout with the primary key. Does not add the cp display layout to the database.
	 *
	 * @param CPDisplayLayoutId the primary key for the new cp display layout
	 * @return the new cp display layout
	 */
	public CPDisplayLayout create(long CPDisplayLayoutId);

	/**
	 * Removes the cp display layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDisplayLayoutId the primary key of the cp display layout
	 * @return the cp display layout that was removed
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout remove(long CPDisplayLayoutId)
		throws NoSuchCPDisplayLayoutException;

	public CPDisplayLayout updateImpl(CPDisplayLayout cpDisplayLayout);

	/**
	 * Returns the cp display layout with the primary key or throws a <code>NoSuchCPDisplayLayoutException</code> if it could not be found.
	 *
	 * @param CPDisplayLayoutId the primary key of the cp display layout
	 * @return the cp display layout
	 * @throws NoSuchCPDisplayLayoutException if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout findByPrimaryKey(long CPDisplayLayoutId)
		throws NoSuchCPDisplayLayoutException;

	/**
	 * Returns the cp display layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDisplayLayoutId the primary key of the cp display layout
	 * @return the cp display layout, or <code>null</code> if a cp display layout with the primary key could not be found
	 */
	public CPDisplayLayout fetchByPrimaryKey(long CPDisplayLayoutId);

	/**
	 * Returns all the cp display layouts.
	 *
	 * @return the cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findAll();

	/**
	 * Returns a range of all the cp display layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @return the range of cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cp display layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp display layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDisplayLayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp display layouts
	 * @param end the upper bound of the range of cp display layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp display layouts
	 */
	public java.util.List<CPDisplayLayout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPDisplayLayout>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the cp display layouts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cp display layouts.
	 *
	 * @return the number of cp display layouts
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}