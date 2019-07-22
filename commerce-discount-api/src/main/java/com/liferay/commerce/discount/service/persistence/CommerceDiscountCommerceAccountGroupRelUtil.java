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

package com.liferay.commerce.discount.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce discount commerce account group rel service. This utility wraps <code>com.liferay.commerce.discount.service.persistence.impl.CommerceDiscountCommerceAccountGroupRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRelPersistence
 * @generated
 */
@ProviderType
public class CommerceDiscountCommerceAccountGroupRelUtil {

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
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		getPersistence().clearCache(commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findWithDynamicQuery(DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findWithDynamicQuery(
			DynamicQuery dynamicQuery, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceDiscountCommerceAccountGroupRel update(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		return getPersistence().update(commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceDiscountCommerceAccountGroupRel update(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commerceDiscountCommerceAccountGroupRel, serviceContext);
	}

	/**
	 * Returns all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(long commerceDiscountId) {

		return getPersistence().findByCommerceDiscountId(commerceDiscountId);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(long commerceDiscountId, int start, int end) {

		return getPersistence().findByCommerceDiscountId(
			commerceDiscountId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().findByCommerceDiscountId(
			commerceDiscountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator,
			boolean retrieveFromCache) {

		return getPersistence().findByCommerceDiscountId(
			commerceDiscountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
			findByCommerceDiscountId_First(
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceDiscountId_First(
			commerceDiscountId, orderByComparator);
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceDiscountId_First(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().fetchByCommerceDiscountId_First(
			commerceDiscountId, orderByComparator);
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
			findByCommerceDiscountId_Last(
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceDiscountId_Last(
			commerceDiscountId, orderByComparator);
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceDiscountId_Last(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().fetchByCommerceDiscountId_Last(
			commerceDiscountId, orderByComparator);
	}

	/**
	 * Returns the commerce discount commerce account group rels before and after the current commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the current commerce discount commerce account group rel
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel[]
			findByCommerceDiscountId_PrevAndNext(
				long commerceDiscountCommerceAccountGroupRelId,
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceDiscountId_PrevAndNext(
			commerceDiscountCommerceAccountGroupRelId, commerceDiscountId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce discount commerce account group rels where commerceDiscountId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 */
	public static void removeByCommerceDiscountId(long commerceDiscountId) {
		getPersistence().removeByCommerceDiscountId(commerceDiscountId);
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	public static int countByCommerceDiscountId(long commerceDiscountId) {
		return getPersistence().countByCommerceDiscountId(commerceDiscountId);
	}

	/**
	 * Returns all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(long commerceAccountGroupId) {

		return getPersistence().findByCommerceAccountGroupId(
			commerceAccountGroupId);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end) {

		return getPersistence().findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator,
			boolean retrieveFromCache) {

		return getPersistence().findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
			findByCommerceAccountGroupId_First(
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceAccountGroupId_First(
			commerceAccountGroupId, orderByComparator);
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceAccountGroupId_First(
			long commerceAccountGroupId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().fetchByCommerceAccountGroupId_First(
			commerceAccountGroupId, orderByComparator);
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
			findByCommerceAccountGroupId_Last(
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceAccountGroupId_Last(
			commerceAccountGroupId, orderByComparator);
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceAccountGroupId_Last(
			long commerceAccountGroupId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return getPersistence().fetchByCommerceAccountGroupId_Last(
			commerceAccountGroupId, orderByComparator);
	}

	/**
	 * Returns the commerce discount commerce account group rels before and after the current commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the current commerce discount commerce account group rel
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel[]
			findByCommerceAccountGroupId_PrevAndNext(
				long commerceDiscountCommerceAccountGroupRelId,
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByCommerceAccountGroupId_PrevAndNext(
			commerceDiscountCommerceAccountGroupRelId, commerceAccountGroupId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce discount commerce account group rels where commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 */
	public static void removeByCommerceAccountGroupId(
		long commerceAccountGroupId) {

		getPersistence().removeByCommerceAccountGroupId(commerceAccountGroupId);
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	public static int countByCommerceAccountGroupId(
		long commerceAccountGroupId) {

		return getPersistence().countByCommerceAccountGroupId(
			commerceAccountGroupId);
	}

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or throws a <code>NoSuchDiscountCommerceAccountGroupRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel findByC_C(
			long commerceDiscountId, long commerceAccountGroupId)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByC_C(
			commerceDiscountId, commerceAccountGroupId);
	}

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel fetchByC_C(
		long commerceDiscountId, long commerceAccountGroupId) {

		return getPersistence().fetchByC_C(
			commerceDiscountId, commerceAccountGroupId);
	}

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel fetchByC_C(
		long commerceDiscountId, long commerceAccountGroupId,
		boolean retrieveFromCache) {

		return getPersistence().fetchByC_C(
			commerceDiscountId, commerceAccountGroupId, retrieveFromCache);
	}

	/**
	 * Removes the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the commerce discount commerce account group rel that was removed
	 */
	public static CommerceDiscountCommerceAccountGroupRel removeByC_C(
			long commerceDiscountId, long commerceAccountGroupId)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().removeByC_C(
			commerceDiscountId, commerceAccountGroupId);
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceDiscountId = &#63; and commerceAccountGroupId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	public static int countByC_C(
		long commerceDiscountId, long commerceAccountGroupId) {

		return getPersistence().countByC_C(
			commerceDiscountId, commerceAccountGroupId);
	}

	/**
	 * Caches the commerce discount commerce account group rel in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountCommerceAccountGroupRel the commerce discount commerce account group rel
	 */
	public static void cacheResult(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		getPersistence().cacheResult(commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * Caches the commerce discount commerce account group rels in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountCommerceAccountGroupRels the commerce discount commerce account group rels
	 */
	public static void cacheResult(
		List<CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels) {

		getPersistence().cacheResult(commerceDiscountCommerceAccountGroupRels);
	}

	/**
	 * Creates a new commerce discount commerce account group rel with the primary key. Does not add the commerce discount commerce account group rel to the database.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key for the new commerce discount commerce account group rel
	 * @return the new commerce discount commerce account group rel
	 */
	public static CommerceDiscountCommerceAccountGroupRel create(
		long commerceDiscountCommerceAccountGroupRelId) {

		return getPersistence().create(
			commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Removes the commerce discount commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was removed
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel remove(
			long commerceDiscountCommerceAccountGroupRelId)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().remove(
			commerceDiscountCommerceAccountGroupRelId);
	}

	public static CommerceDiscountCommerceAccountGroupRel updateImpl(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		return getPersistence().updateImpl(
			commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or throws a <code>NoSuchDiscountCommerceAccountGroupRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel findByPrimaryKey(
			long commerceDiscountCommerceAccountGroupRelId)
		throws com.liferay.commerce.discount.exception.
			NoSuchDiscountCommerceAccountGroupRelException {

		return getPersistence().findByPrimaryKey(
			commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel, or <code>null</code> if a commerce discount commerce account group rel with the primary key could not be found
	 */
	public static CommerceDiscountCommerceAccountGroupRel fetchByPrimaryKey(
		long commerceDiscountCommerceAccountGroupRelId) {

		return getPersistence().fetchByPrimaryKey(
			commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Returns all the commerce discount commerce account group rels.
	 *
	 * @return the commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
			orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce discount commerce account group rels
	 */
	public static List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
			orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the commerce discount commerce account group rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce discount commerce account group rels.
	 *
	 * @return the number of commerce discount commerce account group rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceDiscountCommerceAccountGroupRelPersistence
		getPersistence() {

		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceDiscountCommerceAccountGroupRelPersistence,
		 CommerceDiscountCommerceAccountGroupRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceDiscountCommerceAccountGroupRelPersistence.class);

		ServiceTracker
			<CommerceDiscountCommerceAccountGroupRelPersistence,
			 CommerceDiscountCommerceAccountGroupRelPersistence>
				serviceTracker =
					new ServiceTracker
						<CommerceDiscountCommerceAccountGroupRelPersistence,
						 CommerceDiscountCommerceAccountGroupRelPersistence>(
							 bundle.getBundleContext(),
							 CommerceDiscountCommerceAccountGroupRelPersistence.
								 class,
							 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}