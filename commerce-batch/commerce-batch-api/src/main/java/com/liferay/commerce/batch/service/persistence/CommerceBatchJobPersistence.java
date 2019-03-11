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

import com.liferay.commerce.batch.exception.NoSuchBatchJobException;
import com.liferay.commerce.batch.model.CommerceBatchJob;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce batch job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobPersistenceImpl
 * @see CommerceBatchJobUtil
 * @generated
 */
@ProviderType
public interface CommerceBatchJobPersistence extends BasePersistence<CommerceBatchJob> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBatchJobUtil} to access the commerce batch job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the commerce batch job where key = &#63; or throws a {@link NoSuchBatchJobException} if it could not be found.
	*
	* @param key the key
	* @return the matching commerce batch job
	* @throws NoSuchBatchJobException if a matching commerce batch job could not be found
	*/
	public CommerceBatchJob findByKey(String key)
		throws NoSuchBatchJobException;

	/**
	* Returns the commerce batch job where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching commerce batch job, or <code>null</code> if a matching commerce batch job could not be found
	*/
	public CommerceBatchJob fetchByKey(String key);

	/**
	* Returns the commerce batch job where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce batch job, or <code>null</code> if a matching commerce batch job could not be found
	*/
	public CommerceBatchJob fetchByKey(String key, boolean retrieveFromCache);

	/**
	* Removes the commerce batch job where key = &#63; from the database.
	*
	* @param key the key
	* @return the commerce batch job that was removed
	*/
	public CommerceBatchJob removeByKey(String key)
		throws NoSuchBatchJobException;

	/**
	* Returns the number of commerce batch jobs where key = &#63;.
	*
	* @param key the key
	* @return the number of matching commerce batch jobs
	*/
	public int countByKey(String key);

	/**
	* Caches the commerce batch job in the entity cache if it is enabled.
	*
	* @param commerceBatchJob the commerce batch job
	*/
	public void cacheResult(CommerceBatchJob commerceBatchJob);

	/**
	* Caches the commerce batch jobs in the entity cache if it is enabled.
	*
	* @param commerceBatchJobs the commerce batch jobs
	*/
	public void cacheResult(java.util.List<CommerceBatchJob> commerceBatchJobs);

	/**
	* Creates a new commerce batch job with the primary key. Does not add the commerce batch job to the database.
	*
	* @param commerceBatchJobId the primary key for the new commerce batch job
	* @return the new commerce batch job
	*/
	public CommerceBatchJob create(long commerceBatchJobId);

	/**
	* Removes the commerce batch job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job that was removed
	* @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	*/
	public CommerceBatchJob remove(long commerceBatchJobId)
		throws NoSuchBatchJobException;

	public CommerceBatchJob updateImpl(CommerceBatchJob commerceBatchJob);

	/**
	* Returns the commerce batch job with the primary key or throws a {@link NoSuchBatchJobException} if it could not be found.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job
	* @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	*/
	public CommerceBatchJob findByPrimaryKey(long commerceBatchJobId)
		throws NoSuchBatchJobException;

	/**
	* Returns the commerce batch job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job, or <code>null</code> if a commerce batch job with the primary key could not be found
	*/
	public CommerceBatchJob fetchByPrimaryKey(long commerceBatchJobId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBatchJob> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce batch jobs.
	*
	* @return the commerce batch jobs
	*/
	public java.util.List<CommerceBatchJob> findAll();

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
	public java.util.List<CommerceBatchJob> findAll(int start, int end);

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
	public java.util.List<CommerceBatchJob> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJob> orderByComparator);

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
	public java.util.List<CommerceBatchJob> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJob> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce batch jobs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce batch jobs.
	*
	* @return the number of commerce batch jobs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}