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

import com.liferay.commerce.product.exception.NoSuchChannelFilterException;
import com.liferay.commerce.product.model.CommerceChannelFilter;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce channel filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CommerceChannelFilterPersistenceImpl
 * @see CommerceChannelFilterUtil
 * @generated
 */
@ProviderType
public interface CommerceChannelFilterPersistence extends BasePersistence<CommerceChannelFilter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceChannelFilterUtil} to access the commerce channel filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce channel filters where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the matching commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId);

	/**
	* Returns a range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @return the range of matching commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end);

	/**
	* Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator);

	/**
	* Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceChannelId the commerce channel ID
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce channel filter
	* @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	*/
	public CommerceChannelFilter findByCommerceChannelId_First(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException;

	/**
	* Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	*/
	public CommerceChannelFilter fetchByCommerceChannelId_First(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator);

	/**
	* Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce channel filter
	* @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	*/
	public CommerceChannelFilter findByCommerceChannelId_Last(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException;

	/**
	* Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	*/
	public CommerceChannelFilter fetchByCommerceChannelId_Last(
		long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator);

	/**
	* Returns the commerce channel filters before and after the current commerce channel filter in the ordered set where commerceChannelId = &#63;.
	*
	* @param commerceChannelFilterId the primary key of the current commerce channel filter
	* @param commerceChannelId the commerce channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce channel filter
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public CommerceChannelFilter[] findByCommerceChannelId_PrevAndNext(
		long commerceChannelFilterId, long commerceChannelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException;

	/**
	* Removes all the commerce channel filters where commerceChannelId = &#63; from the database.
	*
	* @param commerceChannelId the commerce channel ID
	*/
	public void removeByCommerceChannelId(long commerceChannelId);

	/**
	* Returns the number of commerce channel filters where commerceChannelId = &#63;.
	*
	* @param commerceChannelId the commerce channel ID
	* @return the number of matching commerce channel filters
	*/
	public int countByCommerceChannelId(long commerceChannelId);

	/**
	* Caches the commerce channel filter in the entity cache if it is enabled.
	*
	* @param commerceChannelFilter the commerce channel filter
	*/
	public void cacheResult(CommerceChannelFilter commerceChannelFilter);

	/**
	* Caches the commerce channel filters in the entity cache if it is enabled.
	*
	* @param commerceChannelFilters the commerce channel filters
	*/
	public void cacheResult(
		java.util.List<CommerceChannelFilter> commerceChannelFilters);

	/**
	* Creates a new commerce channel filter with the primary key. Does not add the commerce channel filter to the database.
	*
	* @param commerceChannelFilterId the primary key for the new commerce channel filter
	* @return the new commerce channel filter
	*/
	public CommerceChannelFilter create(long commerceChannelFilterId);

	/**
	* Removes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter that was removed
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public CommerceChannelFilter remove(long commerceChannelFilterId)
		throws NoSuchChannelFilterException;

	public CommerceChannelFilter updateImpl(
		CommerceChannelFilter commerceChannelFilter);

	/**
	* Returns the commerce channel filter with the primary key or throws a {@link NoSuchChannelFilterException} if it could not be found.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter
	* @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	*/
	public CommerceChannelFilter findByPrimaryKey(long commerceChannelFilterId)
		throws NoSuchChannelFilterException;

	/**
	* Returns the commerce channel filter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceChannelFilterId the primary key of the commerce channel filter
	* @return the commerce channel filter, or <code>null</code> if a commerce channel filter with the primary key could not be found
	*/
	public CommerceChannelFilter fetchByPrimaryKey(long commerceChannelFilterId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceChannelFilter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce channel filters.
	*
	* @return the commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findAll();

	/**
	* Returns a range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @return the range of commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator);

	/**
	* Returns an ordered range of all the commerce channel filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channel filters
	* @param end the upper bound of the range of commerce channel filters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce channel filters
	*/
	public java.util.List<CommerceChannelFilter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannelFilter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce channel filters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce channel filters.
	*
	* @return the number of commerce channel filters
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}