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

package com.liferay.commerce.inventory.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.model.CommerceInventoryAudit;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce inventory audit service. This utility wraps {@link com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryAuditPersistence
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryAuditUtil {
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
	public static void clearCache(CommerceInventoryAudit commerceInventoryAudit) {
		getPersistence().clearCache(commerceInventoryAudit);
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
	public static List<CommerceInventoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceInventoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceInventoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceInventoryAudit update(
		CommerceInventoryAudit commerceInventoryAudit) {
		return getPersistence().update(commerceInventoryAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceInventoryAudit update(
		CommerceInventoryAudit commerceInventoryAudit,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceInventoryAudit, serviceContext);
	}

	/**
	* Returns the commerce inventory audit where sku = &#63; or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	*
	* @param sku the sku
	* @return the matching commerce inventory audit
	* @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	*/
	public static CommerceInventoryAudit findBysku(String sku)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException {
		return getPersistence().findBysku(sku);
	}

	/**
	* Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sku the sku
	* @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	*/
	public static CommerceInventoryAudit fetchBysku(String sku) {
		return getPersistence().fetchBysku(sku);
	}

	/**
	* Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sku the sku
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	*/
	public static CommerceInventoryAudit fetchBysku(String sku,
		boolean retrieveFromCache) {
		return getPersistence().fetchBysku(sku, retrieveFromCache);
	}

	/**
	* Removes the commerce inventory audit where sku = &#63; from the database.
	*
	* @param sku the sku
	* @return the commerce inventory audit that was removed
	*/
	public static CommerceInventoryAudit removeBysku(String sku)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException {
		return getPersistence().removeBysku(sku);
	}

	/**
	* Returns the number of commerce inventory audits where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory audits
	*/
	public static int countBysku(String sku) {
		return getPersistence().countBysku(sku);
	}

	/**
	* Caches the commerce inventory audit in the entity cache if it is enabled.
	*
	* @param commerceInventoryAudit the commerce inventory audit
	*/
	public static void cacheResult(
		CommerceInventoryAudit commerceInventoryAudit) {
		getPersistence().cacheResult(commerceInventoryAudit);
	}

	/**
	* Caches the commerce inventory audits in the entity cache if it is enabled.
	*
	* @param commerceInventoryAudits the commerce inventory audits
	*/
	public static void cacheResult(
		List<CommerceInventoryAudit> commerceInventoryAudits) {
		getPersistence().cacheResult(commerceInventoryAudits);
	}

	/**
	* Creates a new commerce inventory audit with the primary key. Does not add the commerce inventory audit to the database.
	*
	* @param commerceInventoryAuditId the primary key for the new commerce inventory audit
	* @return the new commerce inventory audit
	*/
	public static CommerceInventoryAudit create(long commerceInventoryAuditId) {
		return getPersistence().create(commerceInventoryAuditId);
	}

	/**
	* Removes the commerce inventory audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit that was removed
	* @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	*/
	public static CommerceInventoryAudit remove(long commerceInventoryAuditId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException {
		return getPersistence().remove(commerceInventoryAuditId);
	}

	public static CommerceInventoryAudit updateImpl(
		CommerceInventoryAudit commerceInventoryAudit) {
		return getPersistence().updateImpl(commerceInventoryAudit);
	}

	/**
	* Returns the commerce inventory audit with the primary key or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit
	* @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	*/
	public static CommerceInventoryAudit findByPrimaryKey(
		long commerceInventoryAuditId)
		throws com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException {
		return getPersistence().findByPrimaryKey(commerceInventoryAuditId);
	}

	/**
	* Returns the commerce inventory audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit, or <code>null</code> if a commerce inventory audit with the primary key could not be found
	*/
	public static CommerceInventoryAudit fetchByPrimaryKey(
		long commerceInventoryAuditId) {
		return getPersistence().fetchByPrimaryKey(commerceInventoryAuditId);
	}

	public static java.util.Map<java.io.Serializable, CommerceInventoryAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce inventory audits.
	*
	* @return the commerce inventory audits
	*/
	public static List<CommerceInventoryAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce inventory audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory audits
	* @param end the upper bound of the range of commerce inventory audits (not inclusive)
	* @return the range of commerce inventory audits
	*/
	public static List<CommerceInventoryAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce inventory audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory audits
	* @param end the upper bound of the range of commerce inventory audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce inventory audits
	*/
	public static List<CommerceInventoryAudit> findAll(int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce inventory audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory audits
	* @param end the upper bound of the range of commerce inventory audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce inventory audits
	*/
	public static List<CommerceInventoryAudit> findAll(int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce inventory audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce inventory audits.
	*
	* @return the number of commerce inventory audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceInventoryAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryAuditPersistence, CommerceInventoryAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryAuditPersistence.class);

		ServiceTracker<CommerceInventoryAuditPersistence, CommerceInventoryAuditPersistence> serviceTracker =
			new ServiceTracker<CommerceInventoryAuditPersistence, CommerceInventoryAuditPersistence>(bundle.getBundleContext(),
				CommerceInventoryAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}