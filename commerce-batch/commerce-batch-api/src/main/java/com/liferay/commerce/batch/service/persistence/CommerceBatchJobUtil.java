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

import com.liferay.commerce.batch.model.CommerceBatchJob;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce batch job service. This utility wraps {@link com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobPersistence
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchJobUtil {
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
	public static void clearCache(CommerceBatchJob commerceBatchJob) {
		getPersistence().clearCache(commerceBatchJob);
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
	public static List<CommerceBatchJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBatchJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBatchJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBatchJob> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBatchJob update(CommerceBatchJob commerceBatchJob) {
		return getPersistence().update(commerceBatchJob);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBatchJob update(CommerceBatchJob commerceBatchJob,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceBatchJob, serviceContext);
	}

	/**
	* Returns the commerce batch job where key = &#63; or throws a {@link NoSuchBatchJobException} if it could not be found.
	*
	* @param key the key
	* @return the matching commerce batch job
	* @throws NoSuchBatchJobException if a matching commerce batch job could not be found
	*/
	public static CommerceBatchJob findByKey(String key)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the commerce batch job where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching commerce batch job, or <code>null</code> if a matching commerce batch job could not be found
	*/
	public static CommerceBatchJob fetchByKey(String key) {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the commerce batch job where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce batch job, or <code>null</code> if a matching commerce batch job could not be found
	*/
	public static CommerceBatchJob fetchByKey(String key,
		boolean retrieveFromCache) {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Removes the commerce batch job where key = &#63; from the database.
	*
	* @param key the key
	* @return the commerce batch job that was removed
	*/
	public static CommerceBatchJob removeByKey(String key)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Returns the number of commerce batch jobs where key = &#63;.
	*
	* @param key the key
	* @return the number of matching commerce batch jobs
	*/
	public static int countByKey(String key) {
		return getPersistence().countByKey(key);
	}

	/**
	* Caches the commerce batch job in the entity cache if it is enabled.
	*
	* @param commerceBatchJob the commerce batch job
	*/
	public static void cacheResult(CommerceBatchJob commerceBatchJob) {
		getPersistence().cacheResult(commerceBatchJob);
	}

	/**
	* Caches the commerce batch jobs in the entity cache if it is enabled.
	*
	* @param commerceBatchJobs the commerce batch jobs
	*/
	public static void cacheResult(List<CommerceBatchJob> commerceBatchJobs) {
		getPersistence().cacheResult(commerceBatchJobs);
	}

	/**
	* Creates a new commerce batch job with the primary key. Does not add the commerce batch job to the database.
	*
	* @param commerceBatchJobId the primary key for the new commerce batch job
	* @return the new commerce batch job
	*/
	public static CommerceBatchJob create(long commerceBatchJobId) {
		return getPersistence().create(commerceBatchJobId);
	}

	/**
	* Removes the commerce batch job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job that was removed
	* @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	*/
	public static CommerceBatchJob remove(long commerceBatchJobId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobException {
		return getPersistence().remove(commerceBatchJobId);
	}

	public static CommerceBatchJob updateImpl(CommerceBatchJob commerceBatchJob) {
		return getPersistence().updateImpl(commerceBatchJob);
	}

	/**
	* Returns the commerce batch job with the primary key or throws a {@link NoSuchBatchJobException} if it could not be found.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job
	* @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	*/
	public static CommerceBatchJob findByPrimaryKey(long commerceBatchJobId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobException {
		return getPersistence().findByPrimaryKey(commerceBatchJobId);
	}

	/**
	* Returns the commerce batch job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job, or <code>null</code> if a commerce batch job with the primary key could not be found
	*/
	public static CommerceBatchJob fetchByPrimaryKey(long commerceBatchJobId) {
		return getPersistence().fetchByPrimaryKey(commerceBatchJobId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBatchJob> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce batch jobs.
	*
	* @return the commerce batch jobs
	*/
	public static List<CommerceBatchJob> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce batch jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch jobs
	* @param end the upper bound of the range of commerce batch jobs (not inclusive)
	* @return the range of commerce batch jobs
	*/
	public static List<CommerceBatchJob> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce batch jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch jobs
	* @param end the upper bound of the range of commerce batch jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce batch jobs
	*/
	public static List<CommerceBatchJob> findAll(int start, int end,
		OrderByComparator<CommerceBatchJob> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce batch jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch jobs
	* @param end the upper bound of the range of commerce batch jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce batch jobs
	*/
	public static List<CommerceBatchJob> findAll(int start, int end,
		OrderByComparator<CommerceBatchJob> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce batch jobs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce batch jobs.
	*
	* @return the number of commerce batch jobs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceBatchJobPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchJobPersistence, CommerceBatchJobPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchJobPersistence.class);

		ServiceTracker<CommerceBatchJobPersistence, CommerceBatchJobPersistence> serviceTracker =
			new ServiceTracker<CommerceBatchJobPersistence, CommerceBatchJobPersistence>(bundle.getBundleContext(),
				CommerceBatchJobPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}