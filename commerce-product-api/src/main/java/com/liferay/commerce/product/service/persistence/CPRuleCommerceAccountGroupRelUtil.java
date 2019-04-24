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

import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cp rule commerce account group rel service. This utility wraps {@link com.liferay.commerce.product.service.persistence.impl.CPRuleCommerceAccountGroupRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRelPersistence
 * @see com.liferay.commerce.product.service.persistence.impl.CPRuleCommerceAccountGroupRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelUtil {
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
	public static void clearCache(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		getPersistence().clearCache(cpRuleCommerceAccountGroupRel);
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
	public static List<CPRuleCommerceAccountGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPRuleCommerceAccountGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPRuleCommerceAccountGroupRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPRuleCommerceAccountGroupRel update(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		return getPersistence().update(cpRuleCommerceAccountGroupRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPRuleCommerceAccountGroupRel update(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(cpRuleCommerceAccountGroupRel, serviceContext);
	}

	/**
	* Returns all the cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the matching cp rule commerce account group rels
	*/
	public static List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId) {
		return getPersistence().findByCPRuleId(CPRuleId);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end) {
		return getPersistence().findByCPRuleId(CPRuleId, start, end);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .findByCPRuleId(CPRuleId, start, end, orderByComparator);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCPRuleId(
		long CPRuleId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCPRuleId(CPRuleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel findByCPRuleId_First(
		long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence().findByCPRuleId_First(CPRuleId, orderByComparator);
	}

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel fetchByCPRuleId_First(
		long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .fetchByCPRuleId_First(CPRuleId, orderByComparator);
	}

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel findByCPRuleId_Last(
		long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence().findByCPRuleId_Last(CPRuleId, orderByComparator);
	}

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel fetchByCPRuleId_Last(
		long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence().fetchByCPRuleId_Last(CPRuleId, orderByComparator);
	}

	/**
	* Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	* @param CPRuleId the cp rule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static CPRuleCommerceAccountGroupRel[] findByCPRuleId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence()
				   .findByCPRuleId_PrevAndNext(CPRuleCommerceAccountGroupRelId,
			CPRuleId, orderByComparator);
	}

	/**
	* Removes all the cp rule commerce account group rels where CPRuleId = &#63; from the database.
	*
	* @param CPRuleId the cp rule ID
	*/
	public static void removeByCPRuleId(long CPRuleId) {
		getPersistence().removeByCPRuleId(CPRuleId);
	}

	/**
	* Returns the number of cp rule commerce account group rels where CPRuleId = &#63;.
	*
	* @param CPRuleId the cp rule ID
	* @return the number of matching cp rule commerce account group rels
	*/
	public static int countByCPRuleId(long CPRuleId) {
		return getPersistence().countByCPRuleId(CPRuleId);
	}

	/**
	* Returns all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @return the matching cp rule commerce account group rels
	*/
	public static List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId) {
		return getPersistence()
				   .findByCommerceAccountGroupId(commerceAccountGroupId);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end) {
		return getPersistence()
				   .findByCommerceAccountGroupId(commerceAccountGroupId, start,
			end);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceAccountGroupId(commerceAccountGroupId, start,
			end, orderByComparator);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceAccountGroupId(commerceAccountGroupId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence()
				   .findByCommerceAccountGroupId_First(commerceAccountGroupId,
			orderByComparator);
	}

	/**
	* Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceAccountGroupId_First(commerceAccountGroupId,
			orderByComparator);
	}

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence()
				   .findByCommerceAccountGroupId_Last(commerceAccountGroupId,
			orderByComparator);
	}

	/**
	* Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	*/
	public static CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceAccountGroupId_Last(commerceAccountGroupId,
			orderByComparator);
	}

	/**
	* Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	* @param commerceAccountGroupId the commerce account group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static CPRuleCommerceAccountGroupRel[] findByCommerceAccountGroupId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence()
				   .findByCommerceAccountGroupId_PrevAndNext(CPRuleCommerceAccountGroupRelId,
			commerceAccountGroupId, orderByComparator);
	}

	/**
	* Removes all the cp rule commerce account group rels where commerceAccountGroupId = &#63; from the database.
	*
	* @param commerceAccountGroupId the commerce account group ID
	*/
	public static void removeByCommerceAccountGroupId(
		long commerceAccountGroupId) {
		getPersistence().removeByCommerceAccountGroupId(commerceAccountGroupId);
	}

	/**
	* Returns the number of cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	*
	* @param commerceAccountGroupId the commerce account group ID
	* @return the number of matching cp rule commerce account group rels
	*/
	public static int countByCommerceAccountGroupId(long commerceAccountGroupId) {
		return getPersistence()
				   .countByCommerceAccountGroupId(commerceAccountGroupId);
	}

	/**
	* Caches the cp rule commerce account group rel in the entity cache if it is enabled.
	*
	* @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	*/
	public static void cacheResult(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		getPersistence().cacheResult(cpRuleCommerceAccountGroupRel);
	}

	/**
	* Caches the cp rule commerce account group rels in the entity cache if it is enabled.
	*
	* @param cpRuleCommerceAccountGroupRels the cp rule commerce account group rels
	*/
	public static void cacheResult(
		List<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels) {
		getPersistence().cacheResult(cpRuleCommerceAccountGroupRels);
	}

	/**
	* Creates a new cp rule commerce account group rel with the primary key. Does not add the cp rule commerce account group rel to the database.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key for the new cp rule commerce account group rel
	* @return the new cp rule commerce account group rel
	*/
	public static CPRuleCommerceAccountGroupRel create(
		long CPRuleCommerceAccountGroupRelId) {
		return getPersistence().create(CPRuleCommerceAccountGroupRelId);
	}

	/**
	* Removes the cp rule commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was removed
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static CPRuleCommerceAccountGroupRel remove(
		long CPRuleCommerceAccountGroupRelId)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence().remove(CPRuleCommerceAccountGroupRelId);
	}

	public static CPRuleCommerceAccountGroupRel updateImpl(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		return getPersistence().updateImpl(cpRuleCommerceAccountGroupRel);
	}

	/**
	* Returns the cp rule commerce account group rel with the primary key or throws a {@link NoSuchCPRuleCommerceAccountGroupRelException} if it could not be found.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel
	* @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static CPRuleCommerceAccountGroupRel findByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId)
		throws com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException {
		return getPersistence().findByPrimaryKey(CPRuleCommerceAccountGroupRelId);
	}

	/**
	* Returns the cp rule commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel, or <code>null</code> if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static CPRuleCommerceAccountGroupRel fetchByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId) {
		return getPersistence()
				   .fetchByPrimaryKey(CPRuleCommerceAccountGroupRelId);
	}

	public static java.util.Map<java.io.Serializable, CPRuleCommerceAccountGroupRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cp rule commerce account group rels.
	*
	* @return the cp rule commerce account group rels
	*/
	public static List<CPRuleCommerceAccountGroupRel> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findAll(int start,
		int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CPRuleCommerceAccountGroupRel> findAll(int start,
		int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cp rule commerce account group rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cp rule commerce account group rels.
	*
	* @return the number of cp rule commerce account group rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CPRuleCommerceAccountGroupRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPRuleCommerceAccountGroupRelPersistence, CPRuleCommerceAccountGroupRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPRuleCommerceAccountGroupRelPersistence.class);

		ServiceTracker<CPRuleCommerceAccountGroupRelPersistence, CPRuleCommerceAccountGroupRelPersistence> serviceTracker =
			new ServiceTracker<CPRuleCommerceAccountGroupRelPersistence, CPRuleCommerceAccountGroupRelPersistence>(bundle.getBundleContext(),
				CPRuleCommerceAccountGroupRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}