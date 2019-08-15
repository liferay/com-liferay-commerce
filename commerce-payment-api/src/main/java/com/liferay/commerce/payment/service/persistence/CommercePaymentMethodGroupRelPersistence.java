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

package com.liferay.commerce.payment.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.payment.exception.NoSuchPaymentMethodGroupRelException;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the commerce payment method group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelUtil
 * @generated
 */
@ProviderType
public interface CommercePaymentMethodGroupRelPersistence
	extends BasePersistence<CommercePaymentMethodGroupRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePaymentMethodGroupRelUtil} to access the commerce payment method group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CommercePaymentMethodGroupRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the commerce payment method group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId);

	/**
	 * Returns a range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns the commerce payment method group rels before and after the current commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the current commerce payment method group rel
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	public CommercePaymentMethodGroupRel[] findByGroupId_PrevAndNext(
			long commercePaymentMethodGroupRelId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Removes all the commerce payment method group rels where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce payment method group rels
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or throws a <code>NoSuchPaymentMethodGroupRelException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel findByG_E(
			long groupId, String engineKey)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByG_E(
		long groupId, String engineKey);

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByG_E(
		long groupId, String engineKey, boolean useFinderCache);

	/**
	 * Removes the commerce payment method group rel where groupId = &#63; and engineKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the commerce payment method group rel that was removed
	 */
	public CommercePaymentMethodGroupRel removeByG_E(
			long groupId, String engineKey)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63; and engineKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the number of matching commerce payment method group rels
	 */
	public int countByG_E(long groupId, String engineKey);

	/**
	 * Returns all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active);

	/**
	 * Returns a range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel findByG_A_First(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByG_A_First(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel findByG_A_Last(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByG_A_Last(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns the commerce payment method group rels before and after the current commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the current commerce payment method group rel
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	public CommercePaymentMethodGroupRel[] findByG_A_PrevAndNext(
			long commercePaymentMethodGroupRelId, long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Removes all the commerce payment method group rels where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 */
	public void removeByG_A(long groupId, boolean active);

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching commerce payment method group rels
	 */
	public int countByG_A(long groupId, boolean active);

	/**
	 * Caches the commerce payment method group rel in the entity cache if it is enabled.
	 *
	 * @param commercePaymentMethodGroupRel the commerce payment method group rel
	 */
	public void cacheResult(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel);

	/**
	 * Caches the commerce payment method group rels in the entity cache if it is enabled.
	 *
	 * @param commercePaymentMethodGroupRels the commerce payment method group rels
	 */
	public void cacheResult(
		java.util.List<CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels);

	/**
	 * Creates a new commerce payment method group rel with the primary key. Does not add the commerce payment method group rel to the database.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key for the new commerce payment method group rel
	 * @return the new commerce payment method group rel
	 */
	public CommercePaymentMethodGroupRel create(
		long commercePaymentMethodGroupRelId);

	/**
	 * Removes the commerce payment method group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel that was removed
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	public CommercePaymentMethodGroupRel remove(
			long commercePaymentMethodGroupRelId)
		throws NoSuchPaymentMethodGroupRelException;

	public CommercePaymentMethodGroupRel updateImpl(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel);

	/**
	 * Returns the commerce payment method group rel with the primary key or throws a <code>NoSuchPaymentMethodGroupRelException</code> if it could not be found.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	public CommercePaymentMethodGroupRel findByPrimaryKey(
			long commercePaymentMethodGroupRelId)
		throws NoSuchPaymentMethodGroupRelException;

	/**
	 * Returns the commerce payment method group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel, or <code>null</code> if a commerce payment method group rel with the primary key could not be found
	 */
	public CommercePaymentMethodGroupRel fetchByPrimaryKey(
		long commercePaymentMethodGroupRelId);

	/**
	 * Returns all the commerce payment method group rels.
	 *
	 * @return the commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findAll();

	/**
	 * Returns a range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce payment method group rels
	 */
	public java.util.List<CommercePaymentMethodGroupRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommercePaymentMethodGroupRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce payment method group rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce payment method group rels.
	 *
	 * @return the number of commerce payment method group rels
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}