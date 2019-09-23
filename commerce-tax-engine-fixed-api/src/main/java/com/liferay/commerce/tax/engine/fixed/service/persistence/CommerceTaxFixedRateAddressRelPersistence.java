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

package com.liferay.commerce.tax.engine.fixed.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.tax.engine.fixed.exception.NoSuchTaxFixedRateAddressRelException;
import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the commerce tax fixed rate address rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRateAddressRelUtil
 * @generated
 */
@ProviderType
public interface CommerceTaxFixedRateAddressRelPersistence
	extends BasePersistence<CommerceTaxFixedRateAddressRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceTaxFixedRateAddressRelUtil} to access the commerce tax fixed rate address rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CommerceTaxFixedRateAddressRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the commerce tax fixed rate address rels where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel>
		findByCommerceTaxMethodId(long commerceTaxMethodId);

	/**
	 * Returns a range of all the commerce tax fixed rate address rels where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @return the range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel>
		findByCommerceTaxMethodId(long commerceTaxMethodId, int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel>
		findByCommerceTaxMethodId(
			long commerceTaxMethodId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel>
		findByCommerceTaxMethodId(
			long commerceTaxMethodId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first commerce tax fixed rate address rel in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel findByCommerceTaxMethodId_First(
			long commerceTaxMethodId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Returns the first commerce tax fixed rate address rel in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate address rel, or <code>null</code> if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel fetchByCommerceTaxMethodId_First(
		long commerceTaxMethodId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns the last commerce tax fixed rate address rel in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel findByCommerceTaxMethodId_Last(
			long commerceTaxMethodId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Returns the last commerce tax fixed rate address rel in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate address rel, or <code>null</code> if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel fetchByCommerceTaxMethodId_Last(
		long commerceTaxMethodId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns the commerce tax fixed rate address rels before and after the current commerce tax fixed rate address rel in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key of the current commerce tax fixed rate address rel
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a commerce tax fixed rate address rel with the primary key could not be found
	 */
	public CommerceTaxFixedRateAddressRel[]
			findByCommerceTaxMethodId_PrevAndNext(
				long commerceTaxFixedRateAddressRelId, long commerceTaxMethodId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Removes all the commerce tax fixed rate address rels where commerceTaxMethodId = &#63; from the database.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 */
	public void removeByCommerceTaxMethodId(long commerceTaxMethodId);

	/**
	 * Returns the number of commerce tax fixed rate address rels where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the number of matching commerce tax fixed rate address rels
	 */
	public int countByCommerceTaxMethodId(long commerceTaxMethodId);

	/**
	 * Returns all the commerce tax fixed rate address rels where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @return the matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findByCPTaxCategoryId(
		long CPTaxCategoryId);

	/**
	 * Returns a range of all the commerce tax fixed rate address rels where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @return the range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce tax fixed rate address rel in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel findByCPTaxCategoryId_First(
			long CPTaxCategoryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Returns the first commerce tax fixed rate address rel in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate address rel, or <code>null</code> if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel fetchByCPTaxCategoryId_First(
		long CPTaxCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns the last commerce tax fixed rate address rel in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel findByCPTaxCategoryId_Last(
			long CPTaxCategoryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Returns the last commerce tax fixed rate address rel in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate address rel, or <code>null</code> if a matching commerce tax fixed rate address rel could not be found
	 */
	public CommerceTaxFixedRateAddressRel fetchByCPTaxCategoryId_Last(
		long CPTaxCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns the commerce tax fixed rate address rels before and after the current commerce tax fixed rate address rel in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key of the current commerce tax fixed rate address rel
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a commerce tax fixed rate address rel with the primary key could not be found
	 */
	public CommerceTaxFixedRateAddressRel[] findByCPTaxCategoryId_PrevAndNext(
			long commerceTaxFixedRateAddressRelId, long CPTaxCategoryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceTaxFixedRateAddressRel> orderByComparator)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Removes all the commerce tax fixed rate address rels where CPTaxCategoryId = &#63; from the database.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 */
	public void removeByCPTaxCategoryId(long CPTaxCategoryId);

	/**
	 * Returns the number of commerce tax fixed rate address rels where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @return the number of matching commerce tax fixed rate address rels
	 */
	public int countByCPTaxCategoryId(long CPTaxCategoryId);

	/**
	 * Caches the commerce tax fixed rate address rel in the entity cache if it is enabled.
	 *
	 * @param commerceTaxFixedRateAddressRel the commerce tax fixed rate address rel
	 */
	public void cacheResult(
		CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel);

	/**
	 * Caches the commerce tax fixed rate address rels in the entity cache if it is enabled.
	 *
	 * @param commerceTaxFixedRateAddressRels the commerce tax fixed rate address rels
	 */
	public void cacheResult(
		java.util.List<CommerceTaxFixedRateAddressRel>
			commerceTaxFixedRateAddressRels);

	/**
	 * Creates a new commerce tax fixed rate address rel with the primary key. Does not add the commerce tax fixed rate address rel to the database.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key for the new commerce tax fixed rate address rel
	 * @return the new commerce tax fixed rate address rel
	 */
	public CommerceTaxFixedRateAddressRel create(
		long commerceTaxFixedRateAddressRelId);

	/**
	 * Removes the commerce tax fixed rate address rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key of the commerce tax fixed rate address rel
	 * @return the commerce tax fixed rate address rel that was removed
	 * @throws NoSuchTaxFixedRateAddressRelException if a commerce tax fixed rate address rel with the primary key could not be found
	 */
	public CommerceTaxFixedRateAddressRel remove(
			long commerceTaxFixedRateAddressRelId)
		throws NoSuchTaxFixedRateAddressRelException;

	public CommerceTaxFixedRateAddressRel updateImpl(
		CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel);

	/**
	 * Returns the commerce tax fixed rate address rel with the primary key or throws a <code>NoSuchTaxFixedRateAddressRelException</code> if it could not be found.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key of the commerce tax fixed rate address rel
	 * @return the commerce tax fixed rate address rel
	 * @throws NoSuchTaxFixedRateAddressRelException if a commerce tax fixed rate address rel with the primary key could not be found
	 */
	public CommerceTaxFixedRateAddressRel findByPrimaryKey(
			long commerceTaxFixedRateAddressRelId)
		throws NoSuchTaxFixedRateAddressRelException;

	/**
	 * Returns the commerce tax fixed rate address rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceTaxFixedRateAddressRelId the primary key of the commerce tax fixed rate address rel
	 * @return the commerce tax fixed rate address rel, or <code>null</code> if a commerce tax fixed rate address rel with the primary key could not be found
	 */
	public CommerceTaxFixedRateAddressRel fetchByPrimaryKey(
		long commerceTaxFixedRateAddressRelId);

	/**
	 * Returns all the commerce tax fixed rate address rels.
	 *
	 * @return the commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findAll();

	/**
	 * Returns a range of all the commerce tax fixed rate address rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @return the range of commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax fixed rate address rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateAddressRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rate address rels
	 * @param end the upper bound of the range of commerce tax fixed rate address rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce tax fixed rate address rels
	 */
	public java.util.List<CommerceTaxFixedRateAddressRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceTaxFixedRateAddressRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce tax fixed rate address rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce tax fixed rate address rels.
	 *
	 * @return the number of commerce tax fixed rate address rels
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}