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

import com.liferay.commerce.product.model.CPRuleChannelRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cp rule channel rel service. This utility wraps {@link com.liferay.commerce.product.service.persistence.impl.CPRuleChannelRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleChannelRelPersistence
 * @see com.liferay.commerce.product.service.persistence.impl.CPRuleChannelRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CPRuleChannelRelUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CPRuleChannelRel cpRuleChannelRel) {
		getPersistence().clearCache(cpRuleChannelRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CPRuleChannelRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPRuleChannelRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPRuleChannelRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPRuleChannelRel update(CPRuleChannelRel cpRuleChannelRel) {
		return getPersistence().update(cpRuleChannelRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPRuleChannelRel update(CPRuleChannelRel cpRuleChannelRel,
		ServiceContext serviceContext) {
		return getPersistence().update(cpRuleChannelRel, serviceContext);
	}

	/**
	* Returns all the cp rule channel rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the matching cp rule channel rels
	*/
	public static List<CPRuleChannelRel> findByCPRuleId(long CPRuleId) {
		return getPersistence().findByCPRuleId(CPRuleId);
	}

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
	public static List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end) {
		return getPersistence().findByCPRuleId(CPRuleId, start, end);
	}

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
	public static List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .findByCPRuleId(CPRuleId, start, end, orderByComparator);
	}

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
	public static List<CPRuleChannelRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCPRuleId(CPRuleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel findByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence().findByCPRuleId_First(CPRuleId, orderByComparator);
	}

	/**
	* Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel fetchByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .fetchByCPRuleId_First(CPRuleId, orderByComparator);
	}

	/**
	* Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel findByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence().findByCPRuleId_Last(CPRuleId, orderByComparator);
	}

	/**
	* Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel fetchByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence().fetchByCPRuleId_Last(CPRuleId, orderByComparator);
	}

	/**
	* Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public static CPRuleChannelRel[] findByCPRuleId_PrevAndNext(
		long CPRuleChannelRelId, long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence()
				   .findByCPRuleId_PrevAndNext(CPRuleChannelRelId, CPRuleId,
			orderByComparator);
	}

	/**
	* Removes all the cp rule channel rels where CPRuleId = &#63; from the database.
	*
	* @param CPRuleId the cp rule ID
	*/
	public static void removeByCPRuleId(long CPRuleId) {
		getPersistence().removeByCPRuleId(CPRuleId);
	}

	/**
	* Returns the number of cp rule channel rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the number of matching cp rule channel rels
	*/
	public static int countByCPRuleId(long CPRuleId) {
		return getPersistence().countByCPRuleId(CPRuleId);
	}

	/**
	* Returns all the cp rule channel rels where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the matching cp rule channel rels
	*/
	public static List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId) {
		return getPersistence().findByCommerceChannelId(commerceChannelId);
	}

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
	public static List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end);
	}

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
	public static List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator);
	}

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
	public static List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel findByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence()
				   .findByCommerceChannelId_First(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel fetchByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceChannelId_First(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel findByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence()
				   .findByCommerceChannelId_Last(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	*/
	public static CPRuleChannelRel fetchByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceChannelId_Last(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	*
	* @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public static CPRuleChannelRel[] findByCommerceChannelId_PrevAndNext(
		long CPRuleChannelRelId, long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence()
				   .findByCommerceChannelId_PrevAndNext(CPRuleChannelRelId,
			commerceChannelId, orderByComparator);
	}

	/**
	* Removes all the cp rule channel rels where commerceChannelId = &#63; from the database.
	*
	* @param commerceChannelId the commerce channel ID
	*/
	public static void removeByCommerceChannelId(long commerceChannelId) {
		getPersistence().removeByCommerceChannelId(commerceChannelId);
	}

	/**
	* Returns the number of cp rule channel rels where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the number of matching cp rule channel rels
	*/
	public static int countByCommerceChannelId(long commerceChannelId) {
		return getPersistence().countByCommerceChannelId(commerceChannelId);
	}

	/**
	* Caches the cp rule channel rel in the entity cache if it is enabled.
	*
	* @param cpRuleChannelRel the cp rule channel rel
	*/
	public static void cacheResult(CPRuleChannelRel cpRuleChannelRel) {
		getPersistence().cacheResult(cpRuleChannelRel);
	}

	/**
	* Caches the cp rule channel rels in the entity cache if it is enabled.
	*
	* @param cpRuleChannelRels the cp rule channel rels
	*/
	public static void cacheResult(List<CPRuleChannelRel> cpRuleChannelRels) {
		getPersistence().cacheResult(cpRuleChannelRels);
	}

	/**
	* Creates a new cp rule channel rel with the primary key. Does not add the cp rule channel rel to the database.
	*
	* @param CPRuleChannelRelId the primary key for the new cp rule channel rel
	* @return the new cp rule channel rel
	*/
	public static CPRuleChannelRel create(long CPRuleChannelRelId) {
		return getPersistence().create(CPRuleChannelRelId);
	}

	/**
	* Removes the cp rule channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel that was removed
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public static CPRuleChannelRel remove(long CPRuleChannelRelId)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence().remove(CPRuleChannelRelId);
	}

	public static CPRuleChannelRel updateImpl(CPRuleChannelRel cpRuleChannelRel) {
		return getPersistence().updateImpl(cpRuleChannelRel);
	}

	/**
	* Returns the cp rule channel rel with the primary key or throws a {@link NoSuchCPRuleChannelRelException} if it could not be found.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel
	* @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	*/
	public static CPRuleChannelRel findByPrimaryKey(long CPRuleChannelRelId)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException {
		return getPersistence().findByPrimaryKey(CPRuleChannelRelId);
	}

	/**
	* Returns the cp rule channel rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel, or <code>null</code> if a cp rule channel rel with the primary key could not be found
	*/
	public static CPRuleChannelRel fetchByPrimaryKey(long CPRuleChannelRelId) {
		return getPersistence().fetchByPrimaryKey(CPRuleChannelRelId);
	}

	public static java.util.Map<java.io.Serializable, CPRuleChannelRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cp rule channel rels.
	*
	* @return the cp rule channel rels
	*/
	public static List<CPRuleChannelRel> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CPRuleChannelRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CPRuleChannelRel> findAll(int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CPRuleChannelRel> findAll(int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cp rule channel rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cp rule channel rels.
	*
	* @return the number of cp rule channel rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CPRuleChannelRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPRuleChannelRelPersistence, CPRuleChannelRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPRuleChannelRelPersistence.class);

		ServiceTracker<CPRuleChannelRelPersistence, CPRuleChannelRelPersistence> serviceTracker =
			new ServiceTracker<CPRuleChannelRelPersistence, CPRuleChannelRelPersistence>(bundle.getBundleContext(),
				CPRuleChannelRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}