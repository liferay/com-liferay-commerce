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

package com.liferay.commerce.account.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.exception.NoSuchAccountGroupException;
import com.liferay.commerce.account.model.CommerceAccountGroup;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce account group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.persistence.impl.CommerceAccountGroupPersistenceImpl
 * @see CommerceAccountGroupUtil
 * @generated
 */
@ProviderType
public interface CommerceAccountGroupPersistence extends BasePersistence<CommerceAccountGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountGroupUtil} to access the commerce account group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce account groups where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findByCompanyId(long companyId);

	/**
	* Returns a range of all the commerce account groups where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @return the range of matching commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the commerce account groups where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator);

	/**
	* Returns an ordered range of all the commerce account groups where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce account group in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account group
	* @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException;

	/**
	* Returns the first commerce account group in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator);

	/**
	* Returns the last commerce account group in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account group
	* @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException;

	/**
	* Returns the last commerce account group in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator);

	/**
	* Returns the commerce account groups before and after the current commerce account group in the ordered set where companyId = &#63;.
	*
	* @param commerceAccountGroupId the primary key of the current commerce account group
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce account group
	* @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	*/
	public CommerceAccountGroup[] findByCompanyId_PrevAndNext(
		long commerceAccountGroupId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException;

	/**
	* Removes all the commerce account groups where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of commerce account groups where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce account groups
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchAccountGroupException} if it could not be found.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account group
	* @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountGroupException;

	/**
	* Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup fetchByC_ERC(long companyId,
		String externalReferenceCode);

	/**
	* Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	*/
	public CommerceAccountGroup fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache);

	/**
	* Removes the commerce account group where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the commerce account group that was removed
	*/
	public CommerceAccountGroup removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountGroupException;

	/**
	* Returns the number of commerce account groups where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching commerce account groups
	*/
	public int countByC_ERC(long companyId, String externalReferenceCode);

	/**
	* Caches the commerce account group in the entity cache if it is enabled.
	*
	* @param commerceAccountGroup the commerce account group
	*/
	public void cacheResult(CommerceAccountGroup commerceAccountGroup);

	/**
	* Caches the commerce account groups in the entity cache if it is enabled.
	*
	* @param commerceAccountGroups the commerce account groups
	*/
	public void cacheResult(
		java.util.List<CommerceAccountGroup> commerceAccountGroups);

	/**
	* Creates a new commerce account group with the primary key. Does not add the commerce account group to the database.
	*
	* @param commerceAccountGroupId the primary key for the new commerce account group
	* @return the new commerce account group
	*/
	public CommerceAccountGroup create(long commerceAccountGroupId);

	/**
	* Removes the commerce account group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountGroupId the primary key of the commerce account group
	* @return the commerce account group that was removed
	* @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	*/
	public CommerceAccountGroup remove(long commerceAccountGroupId)
		throws NoSuchAccountGroupException;

	public CommerceAccountGroup updateImpl(
		CommerceAccountGroup commerceAccountGroup);

	/**
	* Returns the commerce account group with the primary key or throws a {@link NoSuchAccountGroupException} if it could not be found.
	*
	* @param commerceAccountGroupId the primary key of the commerce account group
	* @return the commerce account group
	* @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	*/
	public CommerceAccountGroup findByPrimaryKey(long commerceAccountGroupId)
		throws NoSuchAccountGroupException;

	/**
	* Returns the commerce account group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceAccountGroupId the primary key of the commerce account group
	* @return the commerce account group, or <code>null</code> if a commerce account group with the primary key could not be found
	*/
	public CommerceAccountGroup fetchByPrimaryKey(long commerceAccountGroupId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceAccountGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce account groups.
	*
	* @return the commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findAll();

	/**
	* Returns a range of all the commerce account groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @return the range of commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce account groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator);

	/**
	* Returns an ordered range of all the commerce account groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account groups
	* @param end the upper bound of the range of commerce account groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce account groups
	*/
	public java.util.List<CommerceAccountGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce account groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce account groups.
	*
	* @return the number of commerce account groups
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}