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

import com.liferay.commerce.batch.model.CommerceBatchEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce batch entry service. This utility wraps {@link com.liferay.commerce.batch.service.persistence.impl.CommerceBatchEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntryPersistence
 * @see com.liferay.commerce.batch.service.persistence.impl.CommerceBatchEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchEntryUtil {
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
	public static void clearCache(CommerceBatchEntry commerceBatchEntry) {
		getPersistence().clearCache(commerceBatchEntry);
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
	public static List<CommerceBatchEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBatchEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBatchEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBatchEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBatchEntry update(
		CommerceBatchEntry commerceBatchEntry) {
		return getPersistence().update(commerceBatchEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBatchEntry update(
		CommerceBatchEntry commerceBatchEntry, ServiceContext serviceContext) {
		return getPersistence().update(commerceBatchEntry, serviceContext);
	}

	/**
	* Caches the commerce batch entry in the entity cache if it is enabled.
	*
	* @param commerceBatchEntry the commerce batch entry
	*/
	public static void cacheResult(CommerceBatchEntry commerceBatchEntry) {
		getPersistence().cacheResult(commerceBatchEntry);
	}

	/**
	* Caches the commerce batch entries in the entity cache if it is enabled.
	*
	* @param commerceBatchEntries the commerce batch entries
	*/
	public static void cacheResult(
		List<CommerceBatchEntry> commerceBatchEntries) {
		getPersistence().cacheResult(commerceBatchEntries);
	}

	/**
	* Creates a new commerce batch entry with the primary key. Does not add the commerce batch entry to the database.
	*
	* @param commerceBatchEntryId the primary key for the new commerce batch entry
	* @return the new commerce batch entry
	*/
	public static CommerceBatchEntry create(long commerceBatchEntryId) {
		return getPersistence().create(commerceBatchEntryId);
	}

	/**
	* Removes the commerce batch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry that was removed
	* @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	*/
	public static CommerceBatchEntry remove(long commerceBatchEntryId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchEntryException {
		return getPersistence().remove(commerceBatchEntryId);
	}

	public static CommerceBatchEntry updateImpl(
		CommerceBatchEntry commerceBatchEntry) {
		return getPersistence().updateImpl(commerceBatchEntry);
	}

	/**
	* Returns the commerce batch entry with the primary key or throws a {@link NoSuchBatchEntryException} if it could not be found.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry
	* @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	*/
	public static CommerceBatchEntry findByPrimaryKey(long commerceBatchEntryId)
		throws com.liferay.commerce.batch.exception.NoSuchBatchEntryException {
		return getPersistence().findByPrimaryKey(commerceBatchEntryId);
	}

	/**
	* Returns the commerce batch entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry, or <code>null</code> if a commerce batch entry with the primary key could not be found
	*/
	public static CommerceBatchEntry fetchByPrimaryKey(
		long commerceBatchEntryId) {
		return getPersistence().fetchByPrimaryKey(commerceBatchEntryId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBatchEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce batch entries.
	*
	* @return the commerce batch entries
	*/
	public static List<CommerceBatchEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommerceBatchEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommerceBatchEntry> findAll(int start, int end,
		OrderByComparator<CommerceBatchEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommerceBatchEntry> findAll(int start, int end,
		OrderByComparator<CommerceBatchEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce batch entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce batch entries.
	*
	* @return the number of commerce batch entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceBatchEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchEntryPersistence, CommerceBatchEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchEntryPersistence.class);

		ServiceTracker<CommerceBatchEntryPersistence, CommerceBatchEntryPersistence> serviceTracker =
			new ServiceTracker<CommerceBatchEntryPersistence, CommerceBatchEntryPersistence>(bundle.getBundleContext(),
				CommerceBatchEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}