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

import com.liferay.commerce.product.model.CommerceCatalog;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce catalog service. This utility wraps {@link com.liferay.commerce.product.service.persistence.impl.CommerceCatalogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceCatalogPersistence
 * @see com.liferay.commerce.product.service.persistence.impl.CommerceCatalogPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceCatalogUtil {
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
	public static void clearCache(CommerceCatalog commerceCatalog) {
		getPersistence().clearCache(commerceCatalog);
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
	public static List<CommerceCatalog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceCatalog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceCatalog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceCatalog update(CommerceCatalog commerceCatalog) {
		return getPersistence().update(commerceCatalog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceCatalog update(CommerceCatalog commerceCatalog,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceCatalog, serviceContext);
	}

	/**
	* Returns all the commerce catalogs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce catalogs
	*/
	public static List<CommerceCatalog> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce catalogs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @return the range of matching commerce catalogs
	*/
	public static List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce catalogs
	*/
	public static List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce catalogs
	*/
	public static List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce catalog
	* @throws NoSuchCatalogException if a matching commerce catalog could not be found
	*/
	public static CommerceCatalog findByCompanyId_First(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	*/
	public static CommerceCatalog fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce catalog
	* @throws NoSuchCatalogException if a matching commerce catalog could not be found
	*/
	public static CommerceCatalog findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	*/
	public static CommerceCatalog fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the commerce catalogs before and after the current commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param commerceCatalogId the primary key of the current commerce catalog
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public static CommerceCatalog[] findByCompanyId_PrevAndNext(
		long commerceCatalogId, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(commerceCatalogId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce catalogs that the user has permission to view
	*/
	public static List<CommerceCatalog> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @return the range of matching commerce catalogs that the user has permission to view
	*/
	public static List<CommerceCatalog> filterFindByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce catalogs that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce catalogs that the user has permission to view
	*/
	public static List<CommerceCatalog> filterFindByCompanyId(long companyId,
		int start, int end, OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the commerce catalogs before and after the current commerce catalog in the ordered set of commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param commerceCatalogId the primary key of the current commerce catalog
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public static CommerceCatalog[] filterFindByCompanyId_PrevAndNext(
		long commerceCatalogId, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(commerceCatalogId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the commerce catalogs where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce catalogs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce catalogs
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce catalogs that the user has permission to view
	*/
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Caches the commerce catalog in the entity cache if it is enabled.
	*
	* @param commerceCatalog the commerce catalog
	*/
	public static void cacheResult(CommerceCatalog commerceCatalog) {
		getPersistence().cacheResult(commerceCatalog);
	}

	/**
	* Caches the commerce catalogs in the entity cache if it is enabled.
	*
	* @param commerceCatalogs the commerce catalogs
	*/
	public static void cacheResult(List<CommerceCatalog> commerceCatalogs) {
		getPersistence().cacheResult(commerceCatalogs);
	}

	/**
	* Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	*
	* @param commerceCatalogId the primary key for the new commerce catalog
	* @return the new commerce catalog
	*/
	public static CommerceCatalog create(long commerceCatalogId) {
		return getPersistence().create(commerceCatalogId);
	}

	/**
	* Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog that was removed
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public static CommerceCatalog remove(long commerceCatalogId)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence().remove(commerceCatalogId);
	}

	public static CommerceCatalog updateImpl(CommerceCatalog commerceCatalog) {
		return getPersistence().updateImpl(commerceCatalog);
	}

	/**
	* Returns the commerce catalog with the primary key or throws a {@link NoSuchCatalogException} if it could not be found.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public static CommerceCatalog findByPrimaryKey(long commerceCatalogId)
		throws com.liferay.commerce.product.exception.NoSuchCatalogException {
		return getPersistence().findByPrimaryKey(commerceCatalogId);
	}

	/**
	* Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	*/
	public static CommerceCatalog fetchByPrimaryKey(long commerceCatalogId) {
		return getPersistence().fetchByPrimaryKey(commerceCatalogId);
	}

	public static java.util.Map<java.io.Serializable, CommerceCatalog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce catalogs.
	*
	* @return the commerce catalogs
	*/
	public static List<CommerceCatalog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce catalogs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @return the range of commerce catalogs
	*/
	public static List<CommerceCatalog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce catalogs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce catalogs
	*/
	public static List<CommerceCatalog> findAll(int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce catalogs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce catalogs
	* @param end the upper bound of the range of commerce catalogs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce catalogs
	*/
	public static List<CommerceCatalog> findAll(int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce catalogs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce catalogs.
	*
	* @return the number of commerce catalogs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceCatalogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceCatalogPersistence, CommerceCatalogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceCatalogPersistence.class);

		ServiceTracker<CommerceCatalogPersistence, CommerceCatalogPersistence> serviceTracker =
			new ServiceTracker<CommerceCatalogPersistence, CommerceCatalogPersistence>(bundle.getBundleContext(),
				CommerceCatalogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}