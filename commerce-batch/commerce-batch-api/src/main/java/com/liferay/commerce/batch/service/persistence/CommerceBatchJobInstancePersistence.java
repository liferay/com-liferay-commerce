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

import com.liferay.commerce.batch.exception.NoSuchBatchJobInstanceException;
import com.liferay.commerce.batch.model.CommerceBatchJobInstance;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce batch job instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchJobInstancePersistenceImpl
 * @see CommerceBatchJobInstanceUtil
 * @generated
 */
@ProviderType
public interface CommerceBatchJobInstancePersistence extends BasePersistence<CommerceBatchJobInstance> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBatchJobInstanceUtil} to access the commerce batch job instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commerce batch job instance in the entity cache if it is enabled.
	*
	* @param commerceBatchJobInstance the commerce batch job instance
	*/
	public void cacheResult(CommerceBatchJobInstance commerceBatchJobInstance);

	/**
	* Caches the commerce batch job instances in the entity cache if it is enabled.
	*
	* @param commerceBatchJobInstances the commerce batch job instances
	*/
	public void cacheResult(
		java.util.List<CommerceBatchJobInstance> commerceBatchJobInstances);

	/**
	* Creates a new commerce batch job instance with the primary key. Does not add the commerce batch job instance to the database.
	*
	* @param commerceBatchJobInstanceId the primary key for the new commerce batch job instance
	* @return the new commerce batch job instance
	*/
	public CommerceBatchJobInstance create(long commerceBatchJobInstanceId);

	/**
	* Removes the commerce batch job instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance that was removed
	* @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	*/
	public CommerceBatchJobInstance remove(long commerceBatchJobInstanceId)
		throws NoSuchBatchJobInstanceException;

	public CommerceBatchJobInstance updateImpl(
		CommerceBatchJobInstance commerceBatchJobInstance);

	/**
	* Returns the commerce batch job instance with the primary key or throws a {@link NoSuchBatchJobInstanceException} if it could not be found.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance
	* @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	*/
	public CommerceBatchJobInstance findByPrimaryKey(
		long commerceBatchJobInstanceId) throws NoSuchBatchJobInstanceException;

	/**
	* Returns the commerce batch job instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	* @return the commerce batch job instance, or <code>null</code> if a commerce batch job instance with the primary key could not be found
	*/
	public CommerceBatchJobInstance fetchByPrimaryKey(
		long commerceBatchJobInstanceId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBatchJobInstance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce batch job instances.
	*
	* @return the commerce batch job instances
	*/
	public java.util.List<CommerceBatchJobInstance> findAll();

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
	public java.util.List<CommerceBatchJobInstance> findAll(int start, int end);

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
	public java.util.List<CommerceBatchJobInstance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJobInstance> orderByComparator);

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
	public java.util.List<CommerceBatchJobInstance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchJobInstance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce batch job instances from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce batch job instances.
	*
	* @return the number of commerce batch job instances
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}