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

package com.liferay.commerce.batch.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.batch.model.CommerceBatchJobInstance;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce batch job instance service. This utility wraps {@link com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobInstancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobInstancePersistence
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobInstancePersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchJobInstanceUtil {
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
		CommerceBatchJobInstance commerceBatchJobInstance) {
		getPersistence().clearCache(commerceBatchJobInstance);
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
	public static List<CommerceBatchJobInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBatchJobInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBatchJobInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBatchJobInstance> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBatchJobInstance update(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		return getPersistence().update(commerceBatchJobInstance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBatchJobInstance update(
		CommerceBatchJobInstance commerceBatchJobInstance,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceBatchJobInstance, serviceContext);
	}

	/**
	* Caches the commerce batch job instance in the entity cache if it is enabled.
	*
	* @param commerceBatchJobInstance the commerce batch job instance
	*/
	public static void cacheResult(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		getPersistence().cacheResult(commerceBatchJobInstance);
	}

	/**
	* Caches the commerce batch job instances in the entity cache if it is enabled.
	*
	* @param commerceBatchJobInstances the commerce batch job instances
	*/
	public static void cacheResult(
		List<CommerceBatchJobInstance> commerceBatchJobInstances) {
		getPersistence().cacheResult(commerceBatchJobInstances);
	}

	/**
	* Creates a new commerce batch job instance with the primary key. Does not add the commerce batch job instance to the database.
	*
	* @param commerceBatchJobInstanceId the primary key for the new commerce batch job instance
	* @return the new commerce batch job instance
	*/
	public static CommerceBatchJobInstance create(
		long commerceBatchJobInstanceId) {
		return getPersistence().create(commerceBatchJobInstanceId);
	}

	/**
	* Removes the commerce batch job instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance that was removed
	* @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	*/
	public static CommerceBatchJobInstance remove(
		long commerceBatchJobInstanceId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobInstanceException {
		return getPersistence().remove(commerceBatchJobInstanceId);
	}

	public static CommerceBatchJobInstance updateImpl(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		return getPersistence().updateImpl(commerceBatchJobInstance);
	}

	/**
	* Returns the commerce batch job instance with the primary key or throws a {@link NoSuchBatchJobInstanceException} if it could not be found.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance
	* @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	*/
	public static CommerceBatchJobInstance findByPrimaryKey(
		long commerceBatchJobInstanceId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobInstanceException {
		return getPersistence().findByPrimaryKey(commerceBatchJobInstanceId);
	}

	/**
	* Returns the commerce batch job instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance, or <code>null</code> if a commerce batch job instance with the primary key could not be found
	*/
	public static CommerceBatchJobInstance fetchByPrimaryKey(
		long commerceBatchJobInstanceId) {
		return getPersistence().fetchByPrimaryKey(commerceBatchJobInstanceId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBatchJobInstance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce batch job instances.
	*
	* @return the commerce batch job instances
	*/
	public static List<CommerceBatchJobInstance> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce batch job instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job instances
	* @param end the upper bound of the range of commerce batch job instances (not inclusive)
	* @return the range of commerce batch job instances
	*/
	public static List<CommerceBatchJobInstance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce batch job instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job instances
	* @param end the upper bound of the range of commerce batch job instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce batch job instances
	*/
	public static List<CommerceBatchJobInstance> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobInstance> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce batch job instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job instances
	* @param end the upper bound of the range of commerce batch job instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce batch job instances
	*/
	public static List<CommerceBatchJobInstance> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce batch job instances from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce batch job instances.
	*
	* @return the number of commerce batch job instances
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceBatchJobInstancePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchJobInstancePersistence, CommerceBatchJobInstancePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchJobInstancePersistence.class);

		ServiceTracker<CommerceBatchJobInstancePersistence, CommerceBatchJobInstancePersistence> serviceTracker =
			new ServiceTracker<CommerceBatchJobInstancePersistence, CommerceBatchJobInstancePersistence>(bundle.getBundleContext(),
				CommerceBatchJobInstancePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}