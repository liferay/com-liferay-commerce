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

package com.liferay.commerce.application.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce application model c product rel service. This utility wraps {@link com.liferay.commerce.application.service.persistence.impl.CommerceApplicationModelCProductRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelPersistence
 * @see com.liferay.commerce.application.service.persistence.impl.CommerceApplicationModelCProductRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelUtil {
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
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
		getPersistence().clearCache(commerceApplicationModelCProductRel);
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
	public static List<CommerceApplicationModelCProductRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceApplicationModelCProductRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceApplicationModelCProductRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceApplicationModelCProductRel update(
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
		return getPersistence().update(commerceApplicationModelCProductRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceApplicationModelCProductRel update(
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceApplicationModelCProductRel, serviceContext);
	}

	/**
	* Returns all the commerce application model c product rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId);
	}

	/**
	* Returns a range of all the commerce application model c product rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @return the range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce application model c product rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel findByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_First(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the first commerce application model c product rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model c product rel, or <code>null</code> if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel fetchByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationModelId_First(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce application model c product rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel findByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_Last(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce application model c product rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model c product rel, or <code>null</code> if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel fetchByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationModelId_Last(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the commerce application model c product rels before and after the current commerce application model c product rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelCProductRelId the primary key of the current commerce application model c product rel
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a commerce application model c product rel with the primary key could not be found
	*/
	public static CommerceApplicationModelCProductRel[] findByCommerceApplicationModelId_PrevAndNext(
		long commerceApplicationModelCProductRelId,
		long commerceApplicationModelId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_PrevAndNext(commerceApplicationModelCProductRelId,
			commerceApplicationModelId, orderByComparator);
	}

	/**
	* Removes all the commerce application model c product rels where commerceApplicationModelId = &#63; from the database.
	*
	* @param commerceApplicationModelId the commerce application model ID
	*/
	public static void removeByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		getPersistence()
			.removeByCommerceApplicationModelId(commerceApplicationModelId);
	}

	/**
	* Returns the number of commerce application model c product rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the number of matching commerce application model c product rels
	*/
	public static int countByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		return getPersistence()
				   .countByCommerceApplicationModelId(commerceApplicationModelId);
	}

	/**
	* Returns all the commerce application model c product rels where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @return the matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCProductId(
		long CProductId) {
		return getPersistence().findByCProductId(CProductId);
	}

	/**
	* Returns a range of all the commerce application model c product rels where CProductId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CProductId the c product ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @return the range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCProductId(
		long CProductId, int start, int end) {
		return getPersistence().findByCProductId(CProductId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels where CProductId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CProductId the c product ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .findByCProductId(CProductId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels where CProductId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param CProductId the c product ID
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCProductId(CProductId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce application model c product rel in the ordered set where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel findByCProductId_First(
		long CProductId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCProductId_First(CProductId, orderByComparator);
	}

	/**
	* Returns the first commerce application model c product rel in the ordered set where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model c product rel, or <code>null</code> if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel fetchByCProductId_First(
		long CProductId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .fetchByCProductId_First(CProductId, orderByComparator);
	}

	/**
	* Returns the last commerce application model c product rel in the ordered set where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel findByCProductId_Last(
		long CProductId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCProductId_Last(CProductId, orderByComparator);
	}

	/**
	* Returns the last commerce application model c product rel in the ordered set where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model c product rel, or <code>null</code> if a matching commerce application model c product rel could not be found
	*/
	public static CommerceApplicationModelCProductRel fetchByCProductId_Last(
		long CProductId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence()
				   .fetchByCProductId_Last(CProductId, orderByComparator);
	}

	/**
	* Returns the commerce application model c product rels before and after the current commerce application model c product rel in the ordered set where CProductId = &#63;.
	*
	* @param commerceApplicationModelCProductRelId the primary key of the current commerce application model c product rel
	* @param CProductId the c product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a commerce application model c product rel with the primary key could not be found
	*/
	public static CommerceApplicationModelCProductRel[] findByCProductId_PrevAndNext(
		long commerceApplicationModelCProductRelId, long CProductId,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByCProductId_PrevAndNext(commerceApplicationModelCProductRelId,
			CProductId, orderByComparator);
	}

	/**
	* Removes all the commerce application model c product rels where CProductId = &#63; from the database.
	*
	* @param CProductId the c product ID
	*/
	public static void removeByCProductId(long CProductId) {
		getPersistence().removeByCProductId(CProductId);
	}

	/**
	* Returns the number of commerce application model c product rels where CProductId = &#63;.
	*
	* @param CProductId the c product ID
	* @return the number of matching commerce application model c product rels
	*/
	public static int countByCProductId(long CProductId) {
		return getPersistence().countByCProductId(CProductId);
	}

	/**
	* Caches the commerce application model c product rel in the entity cache if it is enabled.
	*
	* @param commerceApplicationModelCProductRel the commerce application model c product rel
	*/
	public static void cacheResult(
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
		getPersistence().cacheResult(commerceApplicationModelCProductRel);
	}

	/**
	* Caches the commerce application model c product rels in the entity cache if it is enabled.
	*
	* @param commerceApplicationModelCProductRels the commerce application model c product rels
	*/
	public static void cacheResult(
		List<CommerceApplicationModelCProductRel> commerceApplicationModelCProductRels) {
		getPersistence().cacheResult(commerceApplicationModelCProductRels);
	}

	/**
	* Creates a new commerce application model c product rel with the primary key. Does not add the commerce application model c product rel to the database.
	*
	* @param commerceApplicationModelCProductRelId the primary key for the new commerce application model c product rel
	* @return the new commerce application model c product rel
	*/
	public static CommerceApplicationModelCProductRel create(
		long commerceApplicationModelCProductRelId) {
		return getPersistence().create(commerceApplicationModelCProductRelId);
	}

	/**
	* Removes the commerce application model c product rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModelCProductRelId the primary key of the commerce application model c product rel
	* @return the commerce application model c product rel that was removed
	* @throws NoSuchApplicationModelCProductRelException if a commerce application model c product rel with the primary key could not be found
	*/
	public static CommerceApplicationModelCProductRel remove(
		long commerceApplicationModelCProductRelId)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence().remove(commerceApplicationModelCProductRelId);
	}

	public static CommerceApplicationModelCProductRel updateImpl(
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
		return getPersistence().updateImpl(commerceApplicationModelCProductRel);
	}

	/**
	* Returns the commerce application model c product rel with the primary key or throws a {@link NoSuchApplicationModelCProductRelException} if it could not be found.
	*
	* @param commerceApplicationModelCProductRelId the primary key of the commerce application model c product rel
	* @return the commerce application model c product rel
	* @throws NoSuchApplicationModelCProductRelException if a commerce application model c product rel with the primary key could not be found
	*/
	public static CommerceApplicationModelCProductRel findByPrimaryKey(
		long commerceApplicationModelCProductRelId)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelCProductRelException {
		return getPersistence()
				   .findByPrimaryKey(commerceApplicationModelCProductRelId);
	}

	/**
	* Returns the commerce application model c product rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceApplicationModelCProductRelId the primary key of the commerce application model c product rel
	* @return the commerce application model c product rel, or <code>null</code> if a commerce application model c product rel with the primary key could not be found
	*/
	public static CommerceApplicationModelCProductRel fetchByPrimaryKey(
		long commerceApplicationModelCProductRelId) {
		return getPersistence()
				   .fetchByPrimaryKey(commerceApplicationModelCProductRelId);
	}

	public static java.util.Map<java.io.Serializable, CommerceApplicationModelCProductRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce application model c product rels.
	*
	* @return the commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce application model c product rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @return the range of commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findAll(int start,
		int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application model c product rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelCProductRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application model c product rels
	* @param end the upper bound of the range of commerce application model c product rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce application model c product rels
	*/
	public static List<CommerceApplicationModelCProductRel> findAll(int start,
		int end,
		OrderByComparator<CommerceApplicationModelCProductRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce application model c product rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce application model c product rels.
	*
	* @return the number of commerce application model c product rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceApplicationModelCProductRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationModelCProductRelPersistence, CommerceApplicationModelCProductRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationModelCProductRelPersistence.class);

		ServiceTracker<CommerceApplicationModelCProductRelPersistence, CommerceApplicationModelCProductRelPersistence> serviceTracker =
			new ServiceTracker<CommerceApplicationModelCProductRelPersistence, CommerceApplicationModelCProductRelPersistence>(bundle.getBundleContext(),
				CommerceApplicationModelCProductRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}