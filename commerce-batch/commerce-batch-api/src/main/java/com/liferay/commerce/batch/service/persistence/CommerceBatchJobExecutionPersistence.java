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

import com.liferay.commerce.batch.exception.NoSuchBatchJobExecutionException;
import com.liferay.commerce.batch.model.CommerceBatchJobExecution;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce batch job execution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobExecutionPersistenceImpl
 * @see CommerceBatchJobExecutionUtil
 * @generated
 */
@ProviderType
public interface CommerceBatchJobExecutionPersistence extends BasePersistence<CommerceBatchJobExecution> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBatchJobExecutionUtil} to access the commerce batch job execution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commerce batch job execution in the entity cache if it is enabled.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	*/
	public void cacheResult(CommerceBatchJobExecution commerceBatchJobExecution);

	/**
	* Caches the commerce batch job executions in the entity cache if it is enabled.
	*
	* @param commerceBatchJobExecutions the commerce batch job executions
	*/
	public void cacheResult(
		java.util.List<CommerceBatchJobExecution> commerceBatchJobExecutions);

	/**
	* Creates a new commerce batch job execution with the primary key. Does not add the commerce batch job execution to the database.
	*
	* @param commerceBatchJobExecutionId the primary key for the new commerce batch job execution
	* @return the new commerce batch job execution
	*/
	public CommerceBatchJobExecution create(long commerceBatchJobExecutionId);

	/**
	* Removes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution that was removed
	* @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	*/
	public CommerceBatchJobExecution remove(long commerceBatchJobExecutionId)
		throws NoSuchBatchJobExecutionException;

	public CommerceBatchJobExecution updateImpl(
		CommerceBatchJobExecution commerceBatchJobExecution);

	/**
	* Returns the commerce batch job execution with the primary key or throws a {@link NoSuchBatchJobExecutionException} if it could not be found.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution
	* @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	*/
	public CommerceBatchJobExecution findByPrimaryKey(
		long commerceBatchJobExecutionId)
		throws NoSuchBatchJobExecutionException;

	/**
	* Returns the commerce batch job execution with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution, or <code>null</code> if a commerce batch job execution with the primary key could not be found
	*/
	public CommerceBatchJobExecution fetchByPrimaryKey(
		long commerceBatchJobExecutionId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBatchJobExecution> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce batch job executions.
	*
	* @return the commerce batch job executions
	*/
	public java.util.List<CommerceBatchJobExecution> findAll();

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
	public java.util.List<CommerceBatchJobExecution> findAll(int start, int end);

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
	public java.util.List<CommerceBatchJobExecution> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJobExecution> orderByComparator);

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
	public java.util.List<CommerceBatchJobExecution> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJobExecution> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce batch job executions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce batch job executions.
	*
	* @return the number of commerce batch job executions
	*/
	public int countAll();
}