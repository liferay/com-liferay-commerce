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

package com.liferay.commerce.account.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccountUserRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce account user rel service. This utility wraps {@link com.liferay.commerce.account.service.persistence.impl.CommerceAccountUserRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelPersistence
 * @see com.liferay.commerce.account.service.persistence.impl.CommerceAccountUserRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelUtil {
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
	public static void clearCache(CommerceAccountUserRel commerceAccountUserRel) {
		getPersistence().clearCache(commerceAccountUserRel);
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
	public static List<CommerceAccountUserRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceAccountUserRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceAccountUserRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceAccountUserRel update(
		CommerceAccountUserRel commerceAccountUserRel) {
		return getPersistence().update(commerceAccountUserRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceAccountUserRel update(
		CommerceAccountUserRel commerceAccountUserRel,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceAccountUserRel, serviceContext);
	}

	/**
	* Returns all the commerce account user rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel findByUserId_First(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel fetchByUserId_First(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel findByUserId_Last(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel fetchByUserId_Last(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public static CommerceAccountUserRel[] findByUserId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK, long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence()
				   .findByUserId_PrevAndNext(commerceAccountUserRelPK, userId,
			orderByComparator);
	}

	/**
	* Removes all the commerce account user rels where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of commerce account user rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching commerce account user rels
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the commerce account user rels where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @return the matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId) {
		return getPersistence().findByCommerceAccountId(commerceAccountId);
	}

	/**
	* Returns a range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end) {
		return getPersistence()
				   .findByCommerceAccountId(commerceAccountId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceAccountId(commerceAccountId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceAccountId(commerceAccountId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel findByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence()
				   .findByCommerceAccountId_First(commerceAccountId,
			orderByComparator);
	}

	/**
	* Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel fetchByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceAccountId_First(commerceAccountId,
			orderByComparator);
	}

	/**
	* Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel findByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence()
				   .findByCommerceAccountId_Last(commerceAccountId,
			orderByComparator);
	}

	/**
	* Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public static CommerceAccountUserRel fetchByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceAccountId_Last(commerceAccountId,
			orderByComparator);
	}

	/**
	* Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public static CommerceAccountUserRel[] findByCommerceAccountId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK,
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence()
				   .findByCommerceAccountId_PrevAndNext(commerceAccountUserRelPK,
			commerceAccountId, orderByComparator);
	}

	/**
	* Removes all the commerce account user rels where commerceAccountId = &#63; from the database.
	*
	* @param commerceAccountId the commerce account ID
	*/
	public static void removeByCommerceAccountId(long commerceAccountId) {
		getPersistence().removeByCommerceAccountId(commerceAccountId);
	}

	/**
	* Returns the number of commerce account user rels where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @return the number of matching commerce account user rels
	*/
	public static int countByCommerceAccountId(long commerceAccountId) {
		return getPersistence().countByCommerceAccountId(commerceAccountId);
	}

	/**
	* Caches the commerce account user rel in the entity cache if it is enabled.
	*
	* @param commerceAccountUserRel the commerce account user rel
	*/
	public static void cacheResult(
		CommerceAccountUserRel commerceAccountUserRel) {
		getPersistence().cacheResult(commerceAccountUserRel);
	}

	/**
	* Caches the commerce account user rels in the entity cache if it is enabled.
	*
	* @param commerceAccountUserRels the commerce account user rels
	*/
	public static void cacheResult(
		List<CommerceAccountUserRel> commerceAccountUserRels) {
		getPersistence().cacheResult(commerceAccountUserRels);
	}

	/**
	* Creates a new commerce account user rel with the primary key. Does not add the commerce account user rel to the database.
	*
	* @param commerceAccountUserRelPK the primary key for the new commerce account user rel
	* @return the new commerce account user rel
	*/
	public static CommerceAccountUserRel create(
		CommerceAccountUserRelPK commerceAccountUserRelPK) {
		return getPersistence().create(commerceAccountUserRelPK);
	}

	/**
	* Removes the commerce account user rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel that was removed
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public static CommerceAccountUserRel remove(
		CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence().remove(commerceAccountUserRelPK);
	}

	public static CommerceAccountUserRel updateImpl(
		CommerceAccountUserRel commerceAccountUserRel) {
		return getPersistence().updateImpl(commerceAccountUserRel);
	}

	/**
	* Returns the commerce account user rel with the primary key or throws a {@link NoSuchAccountUserRelException} if it could not be found.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public static CommerceAccountUserRel findByPrimaryKey(
		CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws com.liferay.commerce.account.exception.NoSuchAccountUserRelException {
		return getPersistence().findByPrimaryKey(commerceAccountUserRelPK);
	}

	/**
	* Returns the commerce account user rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel, or <code>null</code> if a commerce account user rel with the primary key could not be found
	*/
	public static CommerceAccountUserRel fetchByPrimaryKey(
		CommerceAccountUserRelPK commerceAccountUserRelPK) {
		return getPersistence().fetchByPrimaryKey(commerceAccountUserRelPK);
	}

	public static java.util.Map<java.io.Serializable, CommerceAccountUserRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce account user rels.
	*
	* @return the commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce account user rels
	*/
	public static List<CommerceAccountUserRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce account user rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce account user rels.
	*
	* @return the number of commerce account user rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static CommerceAccountUserRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountUserRelPersistence, CommerceAccountUserRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountUserRelPersistence.class);

		ServiceTracker<CommerceAccountUserRelPersistence, CommerceAccountUserRelPersistence> serviceTracker =
			new ServiceTracker<CommerceAccountUserRelPersistence, CommerceAccountUserRelPersistence>(bundle.getBundleContext(),
				CommerceAccountUserRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}