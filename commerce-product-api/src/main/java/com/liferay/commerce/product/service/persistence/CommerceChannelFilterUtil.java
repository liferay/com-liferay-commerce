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

import com.liferay.commerce.product.model.CommerceChannelFilter;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce channel filter service. This utility wraps {@link com.liferay.commerce.product.service.persistence.impl.CommerceChannelFilterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelFilterPersistence
 * @see com.liferay.commerce.product.service.persistence.impl.CommerceChannelFilterPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceChannelFilterUtil {
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
	public static void clearCache(CommerceChannelFilter commerceChannelFilter) {
		getPersistence().clearCache(commerceChannelFilter);
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
	public static List<CommerceChannelFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceChannelFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceChannelFilter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceChannelFilter update(
		CommerceChannelFilter commerceChannelFilter) {
		return getPersistence().update(commerceChannelFilter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceChannelFilter update(
		CommerceChannelFilter commerceChannelFilter,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceChannelFilter, serviceContext);
	}

	/**
	* Returns all the commerce channel filters where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the matching commerce channel filters
	*/
	public static List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId) {
		return getPersistence().findByCommerceChannelId(commerceChannelId);
	}

	/**
	* Returns a range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @return the range of matching commerce channel filters
	*/
	public static List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce channel filters
	*/
	public static List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce channel filters
	*/
	public static List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce channel filter
	* @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	*/
	public static CommerceChannelFilter findByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelFilterException {
		return getPersistence()
				   .findByCommerceChannelId_First(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	*/
	public static CommerceChannelFilter fetchByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceChannelId_First(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce channel filter
	* @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	*/
	public static CommerceChannelFilter findByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelFilterException {
		return getPersistence()
				   .findByCommerceChannelId_Last(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	*/
	public static CommerceChannelFilter fetchByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceChannelId_Last(commerceChannelId,
			orderByComparator);
	}

	/**
	* Returns the commerce channel filters before and after the current commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelFilterId the primary key of the current commerce channel filter
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce channel filter
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public static CommerceChannelFilter[] findByCommerceChannelId_PrevAndNext(
		long commerceChannelFilterId, long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelFilterException {
		return getPersistence()
				   .findByCommerceChannelId_PrevAndNext(commerceChannelFilterId,
			commerceChannelId, orderByComparator);
	}

	/**
	* Removes all the commerce channel filters where commerceChannelId = &#63; from the database.
	*
	* @param commerceChannelId the commerce channel ID
	*/
	public static void removeByCommerceChannelId(long commerceChannelId) {
		getPersistence().removeByCommerceChannelId(commerceChannelId);
	}

	/**
	* Returns the number of commerce channel filters where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the number of matching commerce channel filters
	*/
	public static int countByCommerceChannelId(long commerceChannelId) {
		return getPersistence().countByCommerceChannelId(commerceChannelId);
	}

	/**
	* Caches the commerce channel filter in the entity cache if it is enabled.
	*
	* @param commerceChannelFilter the commerce channel filter
	*/
	public static void cacheResult(CommerceChannelFilter commerceChannelFilter) {
		getPersistence().cacheResult(commerceChannelFilter);
	}

	/**
	* Caches the commerce channel filters in the entity cache if it is enabled.
	*
	* @param commerceChannelFilters the commerce channel filters
	*/
	public static void cacheResult(
		List<CommerceChannelFilter> commerceChannelFilters) {
		getPersistence().cacheResult(commerceChannelFilters);
	}

	/**
	* Creates a new commerce channel filter with the primary key. Does not add the commerce channel filter to the database.
	*
	* @param commerceChannelFilterId the primary key for the new commerce channel filter
	* @return the new commerce channel filter
	*/
	public static CommerceChannelFilter create(long commerceChannelFilterId) {
		return getPersistence().create(commerceChannelFilterId);
	}

	/**
	* Removes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter that was removed
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public static CommerceChannelFilter remove(long commerceChannelFilterId)
		throws com.liferay.commerce.product.exception.NoSuchChannelFilterException {
		return getPersistence().remove(commerceChannelFilterId);
	}

	public static CommerceChannelFilter updateImpl(
		CommerceChannelFilter commerceChannelFilter) {
		return getPersistence().updateImpl(commerceChannelFilter);
	}

	/**
	* Returns the commerce channel filter with the primary key or throws a {@link NoSuchChannelFilterException} if it could not be found.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public static CommerceChannelFilter findByPrimaryKey(
		long commerceChannelFilterId)
		throws com.liferay.commerce.product.exception.NoSuchChannelFilterException {
		return getPersistence().findByPrimaryKey(commerceChannelFilterId);
	}

	/**
	* Returns the commerce channel filter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter, or <code>null</code> if a commerce channel filter with the primary key could not be found
	*/
	public static CommerceChannelFilter fetchByPrimaryKey(
		long commerceChannelFilterId) {
		return getPersistence().fetchByPrimaryKey(commerceChannelFilterId);
	}

	public static java.util.Map<java.io.Serializable, CommerceChannelFilter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce channel filters.
	*
	* @return the commerce channel filters
	*/
	public static List<CommerceChannelFilter> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @return the range of commerce channel filters
	*/
	public static List<CommerceChannelFilter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce channel filters
	*/
	public static List<CommerceChannelFilter> findAll(int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce channel filters
	*/
	public static List<CommerceChannelFilter> findAll(int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce channel filters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce channel filters.
	*
	* @return the number of commerce channel filters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceChannelFilterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceChannelFilterPersistence, CommerceChannelFilterPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceChannelFilterPersistence.class);

		ServiceTracker<CommerceChannelFilterPersistence, CommerceChannelFilterPersistence> serviceTracker =
			new ServiceTracker<CommerceChannelFilterPersistence, CommerceChannelFilterPersistence>(bundle.getBundleContext(),
				CommerceChannelFilterPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}