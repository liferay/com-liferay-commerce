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

import com.liferay.commerce.batch.exception.NoSuchBatchEntryException;
import com.liferay.commerce.batch.model.CommerceBatchEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce batch entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchEntryPersistenceImpl
 * @see CommerceBatchEntryUtil
 * @generated
 */
@ProviderType
public interface CommerceBatchEntryPersistence extends BasePersistence<CommerceBatchEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBatchEntryUtil} to access the commerce batch entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commerce batch entry in the entity cache if it is enabled.
	*
	* @param commerceBatchEntry the commerce batch entry
	*/
	public void cacheResult(CommerceBatchEntry commerceBatchEntry);

	/**
	* Caches the commerce batch entries in the entity cache if it is enabled.
	*
	* @param commerceBatchEntries the commerce batch entries
	*/
	public void cacheResult(
		java.util.List<CommerceBatchEntry> commerceBatchEntries);

	/**
	* Creates a new commerce batch entry with the primary key. Does not add the commerce batch entry to the database.
	*
	* @param commerceBatchEntryId the primary key for the new commerce batch entry
	* @return the new commerce batch entry
	*/
	public CommerceBatchEntry create(long commerceBatchEntryId);

	/**
	* Removes the commerce batch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry that was removed
	* @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	*/
	public CommerceBatchEntry remove(long commerceBatchEntryId)
		throws NoSuchBatchEntryException;

	public CommerceBatchEntry updateImpl(CommerceBatchEntry commerceBatchEntry);

	/**
	* Returns the commerce batch entry with the primary key or throws a {@link NoSuchBatchEntryException} if it could not be found.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry
	* @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	*/
	public CommerceBatchEntry findByPrimaryKey(long commerceBatchEntryId)
		throws NoSuchBatchEntryException;

	/**
	* Returns the commerce batch entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry, or <code>null</code> if a commerce batch entry with the primary key could not be found
	*/
	public CommerceBatchEntry fetchByPrimaryKey(long commerceBatchEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBatchEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce batch entries.
	*
	* @return the commerce batch entries
	*/
	public java.util.List<CommerceBatchEntry> findAll();

	/**
	* Returns a range of all the commerce batch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch entries
	* @param end the upper bound of the range of commerce batch entries (not inclusive)
	* @return the range of commerce batch entries
	*/
	public java.util.List<CommerceBatchEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce batch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch entries
	* @param end the upper bound of the range of commerce batch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce batch entries
	*/
	public java.util.List<CommerceBatchEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchEntry> orderByComparator);

	/**
	* Returns an ordered range of all the commerce batch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch entries
	* @param end the upper bound of the range of commerce batch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce batch entries
	*/
	public java.util.List<CommerceBatchEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBatchEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce batch entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce batch entries.
	*
	* @return the number of commerce batch entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}