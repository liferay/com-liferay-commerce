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

package com.liferay.commerce.application.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.exception.NoSuchApplicationBrandException;
import com.liferay.commerce.application.model.CommerceApplicationBrand;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce application brand service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.persistence.impl.CommerceApplicationBrandPersistenceImpl
 * @see CommerceApplicationBrandUtil
 * @generated
 */
@ProviderType
public interface CommerceApplicationBrandPersistence extends BasePersistence<CommerceApplicationBrand> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceApplicationBrandUtil} to access the commerce application brand persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce application brands where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findByCompanyId(
		long companyId);

	/**
	* Returns a range of all the commerce application brands where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @return the range of matching commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the commerce application brands where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator);

	/**
	* Returns an ordered range of all the commerce application brands where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce application brand in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application brand
	* @throws NoSuchApplicationBrandException if a matching commerce application brand could not be found
	*/
	public CommerceApplicationBrand findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException;

	/**
	* Returns the first commerce application brand in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application brand, or <code>null</code> if a matching commerce application brand could not be found
	*/
	public CommerceApplicationBrand fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator);

	/**
	* Returns the last commerce application brand in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application brand
	* @throws NoSuchApplicationBrandException if a matching commerce application brand could not be found
	*/
	public CommerceApplicationBrand findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException;

	/**
	* Returns the last commerce application brand in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application brand, or <code>null</code> if a matching commerce application brand could not be found
	*/
	public CommerceApplicationBrand fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator);

	/**
	* Returns the commerce application brands before and after the current commerce application brand in the ordered set where companyId = &#63;.
	*
	* @param commerceApplicationBrandId the primary key of the current commerce application brand
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application brand
	* @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	*/
	public CommerceApplicationBrand[] findByCompanyId_PrevAndNext(
		long commerceApplicationBrandId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException;

	/**
	* Returns all the commerce application brands that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application brands that the user has permission to view
	*/
	public java.util.List<CommerceApplicationBrand> filterFindByCompanyId(
		long companyId);

	/**
	* Returns a range of all the commerce application brands that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @return the range of matching commerce application brands that the user has permission to view
	*/
	public java.util.List<CommerceApplicationBrand> filterFindByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the commerce application brands that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application brands that the user has permission to view
	*/
	public java.util.List<CommerceApplicationBrand> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator);

	/**
	* Returns the commerce application brands before and after the current commerce application brand in the ordered set of commerce application brands that the user has permission to view where companyId = &#63;.
	*
	* @param commerceApplicationBrandId the primary key of the current commerce application brand
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application brand
	* @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	*/
	public CommerceApplicationBrand[] filterFindByCompanyId_PrevAndNext(
		long commerceApplicationBrandId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException;

	/**
	* Removes all the commerce application brands where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of commerce application brands where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application brands
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of commerce application brands that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application brands that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Caches the commerce application brand in the entity cache if it is enabled.
	*
	* @param commerceApplicationBrand the commerce application brand
	*/
	public void cacheResult(CommerceApplicationBrand commerceApplicationBrand);

	/**
	* Caches the commerce application brands in the entity cache if it is enabled.
	*
	* @param commerceApplicationBrands the commerce application brands
	*/
	public void cacheResult(
		java.util.List<CommerceApplicationBrand> commerceApplicationBrands);

	/**
	* Creates a new commerce application brand with the primary key. Does not add the commerce application brand to the database.
	*
	* @param commerceApplicationBrandId the primary key for the new commerce application brand
	* @return the new commerce application brand
	*/
	public CommerceApplicationBrand create(long commerceApplicationBrandId);

	/**
	* Removes the commerce application brand with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationBrandId the primary key of the commerce application brand
	* @return the commerce application brand that was removed
	* @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	*/
	public CommerceApplicationBrand remove(long commerceApplicationBrandId)
		throws NoSuchApplicationBrandException;

	public CommerceApplicationBrand updateImpl(
		CommerceApplicationBrand commerceApplicationBrand);

	/**
	* Returns the commerce application brand with the primary key or throws a {@link NoSuchApplicationBrandException} if it could not be found.
	*
	* @param commerceApplicationBrandId the primary key of the commerce application brand
	* @return the commerce application brand
	* @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	*/
	public CommerceApplicationBrand findByPrimaryKey(
		long commerceApplicationBrandId) throws NoSuchApplicationBrandException;

	/**
	* Returns the commerce application brand with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceApplicationBrandId the primary key of the commerce application brand
	* @return the commerce application brand, or <code>null</code> if a commerce application brand with the primary key could not be found
	*/
	public CommerceApplicationBrand fetchByPrimaryKey(
		long commerceApplicationBrandId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceApplicationBrand> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce application brands.
	*
	* @return the commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findAll();

	/**
	* Returns a range of all the commerce application brands.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @return the range of commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce application brands.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator);

	/**
	* Returns an ordered range of all the commerce application brands.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application brands
	* @param end the upper bound of the range of commerce application brands (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce application brands
	*/
	public java.util.List<CommerceApplicationBrand> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationBrand> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce application brands from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce application brands.
	*
	* @return the number of commerce application brands
	*/
	public int countAll();
}