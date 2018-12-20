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

import com.liferay.commerce.account.model.CommerceAccount;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce account service. This utility wraps {@link com.liferay.commerce.account.service.persistence.impl.CommerceAccountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountPersistence
 * @see com.liferay.commerce.account.service.persistence.impl.CommerceAccountPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountUtil {
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
	public static void clearCache(CommerceAccount commerceAccount) {
		getPersistence().clearCache(commerceAccount);
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
	public static List<CommerceAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceAccount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceAccount update(CommerceAccount commerceAccount) {
		return getPersistence().update(commerceAccount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceAccount update(CommerceAccount commerceAccount,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceAccount, serviceContext);
	}

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching commerce account
	* @throws NoSuchAccountException if a matching commerce account could not be found
	*/
	public static CommerceAccount findByC_N(long companyId, String name)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().findByC_N(companyId, name);
	}

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public static CommerceAccount fetchByC_N(long companyId, String name) {
		return getPersistence().fetchByC_N(companyId, name);
	}

	/**
	* Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public static CommerceAccount fetchByC_N(long companyId, String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByC_N(companyId, name, retrieveFromCache);
	}

	/**
	* Removes the commerce account where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the commerce account that was removed
	*/
	public static CommerceAccount removeByC_N(long companyId, String name)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().removeByC_N(companyId, name);
	}

	/**
	* Returns the number of commerce accounts where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching commerce accounts
	*/
	public static int countByC_N(long companyId, String name) {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account
	* @throws NoSuchAccountException if a matching commerce account could not be found
	*/
	public static CommerceAccount findByC_ERC(long companyId,
		String externalReferenceCode)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public static CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	public static CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_ERC(companyId, externalReferenceCode,
			retrieveFromCache);
	}

	/**
	* Removes the commerce account where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the commerce account that was removed
	*/
	public static CommerceAccount removeByC_ERC(long companyId,
		String externalReferenceCode)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the number of commerce accounts where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching commerce accounts
	*/
	public static int countByC_ERC(long companyId, String externalReferenceCode) {
		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Caches the commerce account in the entity cache if it is enabled.
	*
	* @param commerceAccount the commerce account
	*/
	public static void cacheResult(CommerceAccount commerceAccount) {
		getPersistence().cacheResult(commerceAccount);
	}

	/**
	* Caches the commerce accounts in the entity cache if it is enabled.
	*
	* @param commerceAccounts the commerce accounts
	*/
	public static void cacheResult(List<CommerceAccount> commerceAccounts) {
		getPersistence().cacheResult(commerceAccounts);
	}

	/**
	* Creates a new commerce account with the primary key. Does not add the commerce account to the database.
	*
	* @param commerceAccountId the primary key for the new commerce account
	* @return the new commerce account
	*/
	public static CommerceAccount create(long commerceAccountId) {
		return getPersistence().create(commerceAccountId);
	}

	/**
	* Removes the commerce account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account that was removed
	* @throws NoSuchAccountException if a commerce account with the primary key could not be found
	*/
	public static CommerceAccount remove(long commerceAccountId)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().remove(commerceAccountId);
	}

	public static CommerceAccount updateImpl(CommerceAccount commerceAccount) {
		return getPersistence().updateImpl(commerceAccount);
	}

	/**
	* Returns the commerce account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account
	* @throws NoSuchAccountException if a commerce account with the primary key could not be found
	*/
	public static CommerceAccount findByPrimaryKey(long commerceAccountId)
		throws com.liferay.commerce.account.exception.NoSuchAccountException {
		return getPersistence().findByPrimaryKey(commerceAccountId);
	}

	/**
	* Returns the commerce account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account, or <code>null</code> if a commerce account with the primary key could not be found
	*/
	public static CommerceAccount fetchByPrimaryKey(long commerceAccountId) {
		return getPersistence().fetchByPrimaryKey(commerceAccountId);
	}

	public static java.util.Map<java.io.Serializable, CommerceAccount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce accounts.
	*
	* @return the commerce accounts
	*/
	public static List<CommerceAccount> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommerceAccount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommerceAccount> findAll(int start, int end,
		OrderByComparator<CommerceAccount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommerceAccount> findAll(int start, int end,
		OrderByComparator<CommerceAccount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce accounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce accounts.
	*
	* @return the number of commerce accounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceAccountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountPersistence, CommerceAccountPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountPersistence.class);

		ServiceTracker<CommerceAccountPersistence, CommerceAccountPersistence> serviceTracker =
			new ServiceTracker<CommerceAccountPersistence, CommerceAccountPersistence>(bundle.getBundleContext(),
				CommerceAccountPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}