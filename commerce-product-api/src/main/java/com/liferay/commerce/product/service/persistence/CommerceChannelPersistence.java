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

import com.liferay.commerce.product.exception.NoSuchChannelException;
import com.liferay.commerce.product.model.CommerceChannel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.persistence.impl.CommerceChannelPersistenceImpl
 * @see CommerceChannelUtil
 * @generated
 */
@ProviderType
public interface CommerceChannelPersistence extends BasePersistence<CommerceChannel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceChannelUtil} to access the commerce channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commerce channel in the entity cache if it is enabled.
	*
	* @param commerceChannel the commerce channel
	*/
	public void cacheResult(CommerceChannel commerceChannel);

	/**
	* Caches the commerce channels in the entity cache if it is enabled.
	*
	* @param commerceChannels the commerce channels
	*/
	public void cacheResult(java.util.List<CommerceChannel> commerceChannels);

	/**
	* Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	*
	* @param commerceChannelId the primary key for the new commerce channel
	* @return the new commerce channel
	*/
	public CommerceChannel create(long commerceChannelId);

	/**
	* Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceChannelId the primary key of the commerce channel
	* @return the commerce channel that was removed
	* @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	*/
	public CommerceChannel remove(long commerceChannelId)
		throws NoSuchChannelException;

	public CommerceChannel updateImpl(CommerceChannel commerceChannel);

	/**
	* Returns the commerce channel with the primary key or throws a {@link NoSuchChannelException} if it could not be found.
	*
	* @param commerceChannelId the primary key of the commerce channel
	* @return the commerce channel
	* @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	*/
	public CommerceChannel findByPrimaryKey(long commerceChannelId)
		throws NoSuchChannelException;

	/**
	* Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceChannelId the primary key of the commerce channel
	* @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	*/
	public CommerceChannel fetchByPrimaryKey(long commerceChannelId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceChannel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce channels.
	*
	* @return the commerce channels
	*/
	public java.util.List<CommerceChannel> findAll();

	/**
	* Returns a range of all the commerce channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channels
	* @param end the upper bound of the range of commerce channels (not inclusive)
	* @return the range of commerce channels
	*/
	public java.util.List<CommerceChannel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channels
	* @param end the upper bound of the range of commerce channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce channels
	*/
	public java.util.List<CommerceChannel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce channels
	* @param end the upper bound of the range of commerce channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce channels
	*/
	public java.util.List<CommerceChannel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceChannel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce channels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce channels.
	*
	* @return the number of commerce channels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}