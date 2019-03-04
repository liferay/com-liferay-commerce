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

import com.liferay.commerce.batch.model.CommerceBatchJobExecution;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce batch job execution service. This utility wraps {@link com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobExecutionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecutionPersistence
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobExecutionPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionUtil {
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
		CommerceBatchJobExecution commerceBatchJobExecution) {
		getPersistence().clearCache(commerceBatchJobExecution);
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
	public static List<CommerceBatchJobExecution> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBatchJobExecution> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBatchJobExecution> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBatchJobExecution> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBatchJobExecution update(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		return getPersistence().update(commerceBatchJobExecution);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBatchJobExecution update(
		CommerceBatchJobExecution commerceBatchJobExecution,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceBatchJobExecution, serviceContext);
	}

	/**
	* Caches the commerce batch job execution in the entity cache if it is enabled.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	*/
	public static void cacheResult(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		getPersistence().cacheResult(commerceBatchJobExecution);
	}

	/**
	* Caches the commerce batch job executions in the entity cache if it is enabled.
	*
	* @param commerceBatchJobExecutions the commerce batch job executions
	*/
	public static void cacheResult(
		List<CommerceBatchJobExecution> commerceBatchJobExecutions) {
		getPersistence().cacheResult(commerceBatchJobExecutions);
	}

	/**
	* Creates a new commerce batch job execution with the primary key. Does not add the commerce batch job execution to the database.
	*
	* @param commerceBatchJobExecutionId the primary key for the new commerce batch job execution
	* @return the new commerce batch job execution
	*/
	public static CommerceBatchJobExecution create(
		long commerceBatchJobExecutionId) {
		return getPersistence().create(commerceBatchJobExecutionId);
	}

	/**
	* Removes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution that was removed
	* @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	*/
	public static CommerceBatchJobExecution remove(
		long commerceBatchJobExecutionId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobExecutionException {
		return getPersistence().remove(commerceBatchJobExecutionId);
	}

	public static CommerceBatchJobExecution updateImpl(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		return getPersistence().updateImpl(commerceBatchJobExecution);
	}

	/**
	* Returns the commerce batch job execution with the primary key or throws a {@link NoSuchBatchJobExecutionException} if it could not be found.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution
	* @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	*/
	public static CommerceBatchJobExecution findByPrimaryKey(
		long commerceBatchJobExecutionId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchJobExecutionException {
		return getPersistence().findByPrimaryKey(commerceBatchJobExecutionId);
	}

	/**
	* Returns the commerce batch job execution with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution, or <code>null</code> if a commerce batch job execution with the primary key could not be found
	*/
	public static CommerceBatchJobExecution fetchByPrimaryKey(
		long commerceBatchJobExecutionId) {
		return getPersistence().fetchByPrimaryKey(commerceBatchJobExecutionId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBatchJobExecution> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce batch job executions.
	*
	* @return the commerce batch job executions
	*/
	public static List<CommerceBatchJobExecution> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce batch job executions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job executions
	* @param end the upper bound of the range of commerce batch job executions (not inclusive)
	* @return the range of commerce batch job executions
	*/
	public static List<CommerceBatchJobExecution> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce batch job executions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job executions
	* @param end the upper bound of the range of commerce batch job executions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce batch job executions
	*/
	public static List<CommerceBatchJobExecution> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobExecution> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce batch job executions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job executions
	* @param end the upper bound of the range of commerce batch job executions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce batch job executions
	*/
	public static List<CommerceBatchJobExecution> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobExecution> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce batch job executions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce batch job executions.
	*
	* @return the number of commerce batch job executions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceBatchJobExecutionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchJobExecutionPersistence, CommerceBatchJobExecutionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchJobExecutionPersistence.class);

		ServiceTracker<CommerceBatchJobExecutionPersistence, CommerceBatchJobExecutionPersistence> serviceTracker =
			new ServiceTracker<CommerceBatchJobExecutionPersistence, CommerceBatchJobExecutionPersistence>(bundle.getBundleContext(),
				CommerceBatchJobExecutionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}