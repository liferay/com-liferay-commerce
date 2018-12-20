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

import com.liferay.commerce.account.exception.NoSuchAccountUserRelException;
import com.liferay.commerce.account.model.CommerceAccountUserRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce account user rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.persistence.impl.CommerceAccountUserRelPersistenceImpl
 * @see CommerceAccountUserRelUtil
 * @generated
 */
@ProviderType
public interface CommerceAccountUserRelPersistence extends BasePersistence<CommerceAccountUserRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountUserRelUtil} to access the commerce account user rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce account user rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByUserId(long userId);

	/**
	* Returns a range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce account user rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Returns the first commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns the last commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Returns the last commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where userId = &#63;.
	*
	* @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public CommerceAccountUserRel[] findByUserId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Removes all the commerce account user rels where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of commerce account user rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching commerce account user rels
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the commerce account user rels where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @return the matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId);

	/**
	* Returns a range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end);

	/**
	* Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceAccountId the commerce account ID
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel findByCommerceAccountId_First(
		long commerceAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel fetchByCommerceAccountId_First(
		long commerceAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel
	* @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel findByCommerceAccountId_Last(
		long commerceAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	*/
	public CommerceAccountUserRel fetchByCommerceAccountId_Last(
		long commerceAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where commerceAccountId = &#63;.
	*
	* @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	* @param commerceAccountId the commerce account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public CommerceAccountUserRel[] findByCommerceAccountId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK,
		long commerceAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException;

	/**
	* Removes all the commerce account user rels where commerceAccountId = &#63; from the database.
	*
	* @param commerceAccountId the commerce account ID
	*/
	public void removeByCommerceAccountId(long commerceAccountId);

	/**
	* Returns the number of commerce account user rels where commerceAccountId = &#63;.
	*
	* @param commerceAccountId the commerce account ID
	* @return the number of matching commerce account user rels
	*/
	public int countByCommerceAccountId(long commerceAccountId);

	/**
	* Caches the commerce account user rel in the entity cache if it is enabled.
	*
	* @param commerceAccountUserRel the commerce account user rel
	*/
	public void cacheResult(CommerceAccountUserRel commerceAccountUserRel);

	/**
	* Caches the commerce account user rels in the entity cache if it is enabled.
	*
	* @param commerceAccountUserRels the commerce account user rels
	*/
	public void cacheResult(
		java.util.List<CommerceAccountUserRel> commerceAccountUserRels);

	/**
	* Creates a new commerce account user rel with the primary key. Does not add the commerce account user rel to the database.
	*
	* @param commerceAccountUserRelPK the primary key for the new commerce account user rel
	* @return the new commerce account user rel
	*/
	public CommerceAccountUserRel create(
		CommerceAccountUserRelPK commerceAccountUserRelPK);

	/**
	* Removes the commerce account user rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel that was removed
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public CommerceAccountUserRel remove(
		CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws NoSuchAccountUserRelException;

	public CommerceAccountUserRel updateImpl(
		CommerceAccountUserRel commerceAccountUserRel);

	/**
	* Returns the commerce account user rel with the primary key or throws a {@link NoSuchAccountUserRelException} if it could not be found.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel
	* @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	*/
	public CommerceAccountUserRel findByPrimaryKey(
		CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws NoSuchAccountUserRelException;

	/**
	* Returns the commerce account user rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel, or <code>null</code> if a commerce account user rel with the primary key could not be found
	*/
	public CommerceAccountUserRel fetchByPrimaryKey(
		CommerceAccountUserRelPK commerceAccountUserRelPK);

	@Override
	public java.util.Map<java.io.Serializable, CommerceAccountUserRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce account user rels.
	*
	* @return the commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findAll();

	/**
	* Returns a range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce account user rels
	*/
	public java.util.List<CommerceAccountUserRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce account user rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce account user rels.
	*
	* @return the number of commerce account user rels
	*/
	public int countAll();

	public java.util.Set<String> getCompoundPKColumnNames();
}