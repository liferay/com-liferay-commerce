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

import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce catalog service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CommerceCatalogPersistenceImpl
 * @see CommerceCatalogUtil
 * @generated
 */
@ProviderType
public interface CommerceCatalogPersistence extends BasePersistence<CommerceCatalog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceCatalogUtil} to access the commerce catalog persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce catalogs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce catalogs
	*/
	public java.util.List<CommerceCatalog> findByCompanyId(long companyId);

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
	public java.util.List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end);

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
	public java.util.List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator);

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
	public java.util.List<CommerceCatalog> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce catalog
	* @throws NoSuchCatalogException if a matching commerce catalog could not be found
	*/
	public CommerceCatalog findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException;

	/**
	* Returns the first commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	*/
	public CommerceCatalog fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator);

	/**
	* Returns the last commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce catalog
	* @throws NoSuchCatalogException if a matching commerce catalog could not be found
	*/
	public CommerceCatalog findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException;

	/**
	* Returns the last commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	*/
	public CommerceCatalog fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator);

	/**
	* Returns the commerce catalogs before and after the current commerce catalog in the ordered set where companyId = &#63;.
	*
	* @param commerceCatalogId the primary key of the current commerce catalog
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public CommerceCatalog[] findByCompanyId_PrevAndNext(
		long commerceCatalogId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException;

	/**
	* Returns all the commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce catalogs that the user has permission to view
	*/
	public java.util.List<CommerceCatalog> filterFindByCompanyId(long companyId);

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
	public java.util.List<CommerceCatalog> filterFindByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<CommerceCatalog> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator);

	/**
	* Returns the commerce catalogs before and after the current commerce catalog in the ordered set of commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param commerceCatalogId the primary key of the current commerce catalog
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public CommerceCatalog[] filterFindByCompanyId_PrevAndNext(
		long commerceCatalogId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException;

	/**
	* Removes all the commerce catalogs where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of commerce catalogs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce catalogs
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of commerce catalogs that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce catalogs that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Caches the commerce catalog in the entity cache if it is enabled.
	*
	* @param commerceCatalog the commerce catalog
	*/
	public void cacheResult(CommerceCatalog commerceCatalog);

	/**
	* Caches the commerce catalogs in the entity cache if it is enabled.
	*
	* @param commerceCatalogs the commerce catalogs
	*/
	public void cacheResult(java.util.List<CommerceCatalog> commerceCatalogs);

	/**
	* Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	*
	* @param commerceCatalogId the primary key for the new commerce catalog
	* @return the new commerce catalog
	*/
	public CommerceCatalog create(long commerceCatalogId);

	/**
	* Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog that was removed
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public CommerceCatalog remove(long commerceCatalogId)
		throws NoSuchCatalogException;

	public CommerceCatalog updateImpl(CommerceCatalog commerceCatalog);

	/**
	* Returns the commerce catalog with the primary key or throws a {@link NoSuchCatalogException} if it could not be found.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog
	* @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	*/
	public CommerceCatalog findByPrimaryKey(long commerceCatalogId)
		throws NoSuchCatalogException;

	/**
	* Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceCatalogId the primary key of the commerce catalog
	* @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	*/
	public CommerceCatalog fetchByPrimaryKey(long commerceCatalogId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceCatalog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce catalogs.
	*
	* @return the commerce catalogs
	*/
	public java.util.List<CommerceCatalog> findAll();

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
	public java.util.List<CommerceCatalog> findAll(int start, int end);

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
	public java.util.List<CommerceCatalog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator);

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
	public java.util.List<CommerceCatalog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceCatalog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce catalogs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce catalogs.
	*
	* @return the number of commerce catalogs
	*/
	public int countAll();
}