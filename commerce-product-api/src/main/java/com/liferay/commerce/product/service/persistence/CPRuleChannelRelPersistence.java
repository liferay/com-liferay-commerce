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

import com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException;
import com.liferay.commerce.product.model.CPRuleChannelRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the cp rule channel rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CPRuleChannelRelPersistenceImpl
 * @see CPRuleChannelRelUtil
 * @generated
 */
@ProviderType
public interface CPRuleChannelRelPersistence extends BasePersistence<CPRuleChannelRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPRuleChannelRelUtil} to access the cp rule channel rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cp rule channel rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCPRuleId(long CPRuleId);

	/**
	* Returns a range of all the cp rule channel rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @return the range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end);

	/**
	* Returns an ordered range of all the cp rule channel rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule channel rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel findByCPRuleId_First(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel fetchByCPRuleId_First(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel findByCPRuleId_Last(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel fetchByCPRuleId_Last(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public CPRuleChannelRel[] findByCPRuleId_PrevAndNext(
		long CPRuleChannelRelId, long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Removes all the cp rule channel rels where CPRuleId = &#63; from the database.
	*
	* @param CPRuleId the cp rule ID
	*/
	public void removeByCPRuleId(long CPRuleId);

	/**
	* Returns the number of cp rule channel rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the number of matching cp rule channel rels
	*/
	public int countByCPRuleId(long CPRuleId);

	/**
	* Returns all the cp rule channel rels where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId);

	/**
	* Returns a range of all the cp rule channel rels where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @return the range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end);

	/**
	* Returns an ordered range of all the cp rule channel rels where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule channel rels where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel findByCommerceChannelId_First(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel fetchByCommerceChannelId_First(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel findByCommerceChannelId_Last(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public CPRuleChannelRel fetchByCommerceChannelId_Last(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public CPRuleChannelRel[] findByCommerceChannelId_PrevAndNext(
		long CPRuleChannelRelId, long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Removes all the cp rule channel rels where commerceChannelId = &#63; from the database.
	*
	* @param commerceChannelId the commerce channel ID
	*/
	public void removeByCommerceChannelId(long commerceChannelId);

	/**
	* Returns the number of cp rule channel rels where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the number of matching cp rule channel rels
	*/
	public int countByCommerceChannelId(long commerceChannelId);

	/**
	* Caches the cp rule channel rel in the entity cache if it is enabled.
	*
	* @param cpRuleChannelRel the cp rule channel rel
	*/
	public void cacheResult(CPRuleChannelRel cpRuleChannelRel);

	/**
	* Caches the cp rule channel rels in the entity cache if it is enabled.
	*
	* @param cpRuleChannelRels the cp rule channel rels
	*/
	public void cacheResult(java.util.List<CPRuleChannelRel> cpRuleChannelRels);

	/**
	* Creates a new cp rule channel rel with the primary key. Does not add the cp rule channel rel to the database.
	*
	* @param CPRuleChannelRelId the primary key for the new cp rule channel rel
	* @return the new cp rule channel rel
	*/
	public CPRuleChannelRel create(long CPRuleChannelRelId);

	/**
	* Removes the cp rule channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel that was removed
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public CPRuleChannelRel remove(long CPRuleChannelRelId)
		throws NoSuchCPRuleChannelRelException;

	public CPRuleChannelRel updateImpl(CPRuleChannelRel cpRuleChannelRel);

	/**
	* Returns the cp rule channel rel with the primary key or throws a {@link NoSuchCPRuleChannelRelException} if it could not be found.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public CPRuleChannelRel findByPrimaryKey(long CPRuleChannelRelId)
		throws NoSuchCPRuleChannelRelException;

	/**
	* Returns the cp rule channel rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel, or <code>null</code> if a cp rule channel rel with the primary key could not be found
	*/
	public CPRuleChannelRel fetchByPrimaryKey(long CPRuleChannelRelId);

	@Override
	public java.util.Map<java.io.Serializable, CPRuleChannelRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cp rule channel rels.
	*
	* @return the cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findAll();

	/**
	* Returns a range of all the cp rule channel rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @return the range of cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cp rule channel rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule channel rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp rule channel rels
	*/
	public java.util.List<CPRuleChannelRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cp rule channel rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cp rule channel rels.
	*
	* @return the number of cp rule channel rels
	*/
	public int countAll();
}