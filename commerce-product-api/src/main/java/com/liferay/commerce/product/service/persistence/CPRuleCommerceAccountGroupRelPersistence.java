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

import com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the cp rule commerce account group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CPRuleCommerceAccountGroupRelPersistenceImpl
 * @see CPRuleCommerceAccountGroupRelUtil
 * @generated
 */
@ProviderType
public interface CPRuleCommerceAccountGroupRelPersistence
	extends BasePersistence<CPRuleCommerceAccountGroupRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPRuleCommerceAccountGroupRelUtil} to access the cp rule commerce account group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId);

	/**
	* Returns a range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @return the range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CPRuleId the cp rule ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel findByCPRuleId_First(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel fetchByCPRuleId_First(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel findByCPRuleId_Last(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel fetchByCPRuleId_Last(long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public CPRuleCommerceAccountGroupRel[] findByCPRuleId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long CPRuleId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Removes all the cp rule commerce account group rels where CPRuleId = &#63; from the database.
	*
	* @param CPRuleId the cp rule ID
	*/
	public void removeByCPRuleId(long CPRuleId);

	/**
	* Returns the number of cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the number of matching cp rule commerce account group rels
	*/
	public int countByCPRuleId(long CPRuleId);

	/**
	* Returns all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @return the matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId);

	/**
	* Returns a range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @return the range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public CPRuleCommerceAccountGroupRel[] findByCommerceAccountGroupId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long commerceAccountGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Removes all the cp rule commerce account group rels where commerceAccountGroupId = &#63; from the database.
	*
	* @param commerceAccountGroupId the commerce account group ID
	*/
	public void removeByCommerceAccountGroupId(long commerceAccountGroupId);

	/**
	* Returns the number of cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @return the number of matching cp rule commerce account group rels
	*/
	public int countByCommerceAccountGroupId(long commerceAccountGroupId);

	/**
	* Caches the cp rule commerce account group rel in the entity cache if it is enabled.
	*
	* @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	*/
	public void cacheResult(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel);

	/**
	* Caches the cp rule commerce account group rels in the entity cache if it is enabled.
	*
	* @param cpRuleCommerceAccountGroupRels the cp rule commerce account group rels
	*/
	public void cacheResult(
		java.util.List<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels);

	/**
	* Creates a new cp rule commerce account group rel with the primary key. Does not add the cp rule commerce account group rel to the database.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key for the new cp rule commerce account group rel
	* @return the new cp rule commerce account group rel
	*/
	public CPRuleCommerceAccountGroupRel create(
		long CPRuleCommerceAccountGroupRelId);

	/**
	* Removes the cp rule commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was removed
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public CPRuleCommerceAccountGroupRel remove(
		long CPRuleCommerceAccountGroupRelId)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	public CPRuleCommerceAccountGroupRel updateImpl(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel);

	/**
	* Returns the cp rule commerce account group rel with the primary key or throws a {@link NoSuchCPRuleCommerceAccountGroupRelException} if it could not be found.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public CPRuleCommerceAccountGroupRel findByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId)
		throws NoSuchCPRuleCommerceAccountGroupRelException;

	/**
	* Returns the cp rule commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel, or <code>null</code> if a cp rule commerce account group rel with the primary key could not be found
	*/
	public CPRuleCommerceAccountGroupRel fetchByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId);

	@Override
	public java.util.Map<java.io.Serializable, CPRuleCommerceAccountGroupRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cp rule commerce account group rels.
	*
	* @return the cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findAll();

	/**
	* Returns a range of all the cp rule commerce account group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @return the range of cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator);

	/**
	* Returns an ordered range of all the cp rule commerce account group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cp rule commerce account group rels
	*/
	public java.util.List<CPRuleCommerceAccountGroupRel> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cp rule commerce account group rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cp rule commerce account group rels.
	*
	* @return the number of cp rule commerce account group rels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}