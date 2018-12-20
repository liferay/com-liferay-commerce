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

import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.persistence.impl.CommerceAccountPersistenceImpl
 * @see CommerceAccountUtil
 * @generated
 */
@ProviderType
public interface CommerceAccountPersistence extends BasePersistence<CommerceAccount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountUtil} to access the commerce account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching commerce account
	* @throws NoSuchAccountException if a matching commerce account could not be found
	*/
	public CommerceAccount findByC_N(long companyId, String name)
		throws NoSuchAccountException;

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public CommerceAccount fetchByC_N(long companyId, String name);

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public CommerceAccount fetchByC_N(long companyId, String name,
		boolean retrieveFromCache);

	/**
	* Removes the commerce account where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the commerce account that was removed
	*/
	public CommerceAccount removeByC_N(long companyId, String name)
		throws NoSuchAccountException;

	/**
	* Returns the number of commerce accounts where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching commerce accounts
	*/
	public int countByC_N(long companyId, String name);

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account
	* @throws NoSuchAccountException if a matching commerce account could not be found
	*/
	public CommerceAccount findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountException;

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode);

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache);

	/**
	* Removes the commerce account where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the commerce account that was removed
	*/
	public CommerceAccount removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountException;

	/**
	* Returns the number of commerce accounts where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching commerce accounts
	*/
	public int countByC_ERC(long companyId, String externalReferenceCode);

	/**
	* Caches the commerce account in the entity cache if it is enabled.
	*
	* @param commerceAccount the commerce account
	*/
	public void cacheResult(CommerceAccount commerceAccount);

	/**
	* Caches the commerce accounts in the entity cache if it is enabled.
	*
	* @param commerceAccounts the commerce accounts
	*/
	public void cacheResult(java.util.List<CommerceAccount> commerceAccounts);

	/**
	* Creates a new commerce account with the primary key. Does not add the commerce account to the database.
	*
	* @param commerceAccountId the primary key for the new commerce account
	* @return the new commerce account
	*/
	public CommerceAccount create(long commerceAccountId);

	/**
	* Removes the commerce account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account that was removed
	* @throws NoSuchAccountException if a commerce account with the primary key could not be found
	*/
	public CommerceAccount remove(long commerceAccountId)
		throws NoSuchAccountException;

	public CommerceAccount updateImpl(CommerceAccount commerceAccount);

	/**
	* Returns the commerce account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account
	* @throws NoSuchAccountException if a commerce account with the primary key could not be found
	*/
	public CommerceAccount findByPrimaryKey(long commerceAccountId)
		throws NoSuchAccountException;

	/**
	* Returns the commerce account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account, or <code>null</code> if a commerce account with the primary key could not be found
	*/
	public CommerceAccount fetchByPrimaryKey(long commerceAccountId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceAccount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce accounts.
	*
	* @return the commerce accounts
	*/
	public java.util.List<CommerceAccount> findAll();

	/**
	* Returns a range of all the commerce accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce accounts
	* @param end the upper bound of the range of commerce accounts (not inclusive)
	* @return the range of commerce accounts
	*/
	public java.util.List<CommerceAccount> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce accounts
	* @param end the upper bound of the range of commerce accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce accounts
	*/
	public java.util.List<CommerceAccount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccount> orderByComparator);

	/**
	* Returns an ordered range of all the commerce accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce accounts
	* @param end the upper bound of the range of commerce accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce accounts
	*/
	public java.util.List<CommerceAccount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce accounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce accounts.
	*
	* @return the number of commerce accounts
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}