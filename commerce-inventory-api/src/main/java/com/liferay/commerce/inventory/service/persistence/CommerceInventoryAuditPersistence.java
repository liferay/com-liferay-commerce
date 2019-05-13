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

import com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException;
import com.liferay.commerce.inventory.model.CommerceInventoryAudit;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce inventory audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.inventory.service.persistence.impl.CommerceInventoryAuditPersistenceImpl
 * @see CommerceInventoryAuditUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryAuditPersistence extends BasePersistence<CommerceInventoryAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryAuditUtil} to access the commerce inventory audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the commerce inventory audit where sku = &#63; or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	*
	* @param sku the sku
	* @return the matching commerce inventory audit
	* @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	*/
	public CommerceInventoryAudit findBysku(String sku)
		throws NoSuchInventoryAuditException;

	/**
	* Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sku the sku
	* @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	*/
	public CommerceInventoryAudit fetchBysku(String sku);

	/**
	* Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sku the sku
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	*/
	public CommerceInventoryAudit fetchBysku(String sku,
		boolean retrieveFromCache);

	/**
	* Removes the commerce inventory audit where sku = &#63; from the database.
	*
	* @param sku the sku
	* @return the commerce inventory audit that was removed
	*/
	public CommerceInventoryAudit removeBysku(String sku)
		throws NoSuchInventoryAuditException;

	/**
	* Returns the number of commerce inventory audits where sku = &#63;.
	*
	* @param sku the sku
	* @return the number of matching commerce inventory audits
	*/
	public int countBysku(String sku);

	/**
	* Caches the commerce inventory audit in the entity cache if it is enabled.
	*
	* @param commerceInventoryAudit the commerce inventory audit
	*/
	public void cacheResult(CommerceInventoryAudit commerceInventoryAudit);

	/**
	* Caches the commerce inventory audits in the entity cache if it is enabled.
	*
	* @param commerceInventoryAudits the commerce inventory audits
	*/
	public void cacheResult(
		java.util.List<CommerceInventoryAudit> commerceInventoryAudits);

	/**
	* Creates a new commerce inventory audit with the primary key. Does not add the commerce inventory audit to the database.
	*
	* @param commerceInventoryAuditId the primary key for the new commerce inventory audit
	* @return the new commerce inventory audit
	*/
	public CommerceInventoryAudit create(long commerceInventoryAuditId);

	/**
	* Removes the commerce inventory audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit that was removed
	* @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	*/
	public CommerceInventoryAudit remove(long commerceInventoryAuditId)
		throws NoSuchInventoryAuditException;

	public CommerceInventoryAudit updateImpl(
		CommerceInventoryAudit commerceInventoryAudit);

	/**
	* Returns the commerce inventory audit with the primary key or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit
	* @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	*/
	public CommerceInventoryAudit findByPrimaryKey(
		long commerceInventoryAuditId) throws NoSuchInventoryAuditException;

	/**
	* Returns the commerce inventory audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceInventoryAuditId the primary key of the commerce inventory audit
	* @return the commerce inventory audit, or <code>null</code> if a commerce inventory audit with the primary key could not be found
	*/
	public CommerceInventoryAudit fetchByPrimaryKey(
		long commerceInventoryAuditId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceInventoryAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce inventory audits.
	*
	* @return the commerce inventory audits
	*/
	public java.util.List<CommerceInventoryAudit> findAll();

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
	public java.util.List<CommerceInventoryAudit> findAll(int start, int end);

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
	public java.util.List<CommerceInventoryAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryAudit> orderByComparator);

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
	public java.util.List<CommerceInventoryAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceInventoryAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce inventory audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce inventory audits.
	*
	* @return the number of commerce inventory audits
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}