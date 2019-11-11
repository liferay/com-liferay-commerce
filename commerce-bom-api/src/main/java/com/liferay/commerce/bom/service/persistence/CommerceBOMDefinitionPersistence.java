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

package com.liferay.commerce.bom.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException;
import com.liferay.commerce.bom.model.CommerceBOMDefinition;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce bom definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.persistence.impl.CommerceBOMDefinitionPersistenceImpl
 * @see CommerceBOMDefinitionUtil
 * @generated
 */
@ProviderType
public interface CommerceBOMDefinitionPersistence extends BasePersistence<CommerceBOMDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBOMDefinitionUtil} to access the commerce bom definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId);

	/**
	* Returns a range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @return the range of matching commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end);

	/**
	* Returns an ordered range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom definition
	* @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	*/
	public CommerceBOMDefinition findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException;

	/**
	* Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	*/
	public CommerceBOMDefinition fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator);

	/**
	* Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom definition
	* @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	*/
	public CommerceBOMDefinition findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException;

	/**
	* Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	*/
	public CommerceBOMDefinition fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator);

	/**
	* Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public CommerceBOMDefinition[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException;

	/**
	* Returns all the commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom definitions that the user has permission to view
	*/
	public java.util.List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId);

	/**
	* Returns a range of all the commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @return the range of matching commerce bom definitions that the user has permission to view
	*/
	public java.util.List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end);

	/**
	* Returns an ordered range of all the commerce bom definitions that the user has permissions to view where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom definitions that the user has permission to view
	*/
	public java.util.List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator);

	/**
	* Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public CommerceBOMDefinition[] filterFindByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException;

	/**
	* Removes all the commerce bom definitions where commerceBOMFolderId = &#63; from the database.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	*/
	public void removeByCommerceBOMFolderId(long commerceBOMFolderId);

	/**
	* Returns the number of commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom definitions
	*/
	public int countByCommerceBOMFolderId(long commerceBOMFolderId);

	/**
	* Returns the number of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom definitions that the user has permission to view
	*/
	public int filterCountByCommerceBOMFolderId(long commerceBOMFolderId);

	/**
	* Caches the commerce bom definition in the entity cache if it is enabled.
	*
	* @param commerceBOMDefinition the commerce bom definition
	*/
	public void cacheResult(CommerceBOMDefinition commerceBOMDefinition);

	/**
	* Caches the commerce bom definitions in the entity cache if it is enabled.
	*
	* @param commerceBOMDefinitions the commerce bom definitions
	*/
	public void cacheResult(
		java.util.List<CommerceBOMDefinition> commerceBOMDefinitions);

	/**
	* Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	*
	* @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	* @return the new commerce bom definition
	*/
	public CommerceBOMDefinition create(long commerceBOMDefinitionId);

	/**
	* Removes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public CommerceBOMDefinition remove(long commerceBOMDefinitionId)
		throws NoSuchBOMDefinitionException;

	public CommerceBOMDefinition updateImpl(
		CommerceBOMDefinition commerceBOMDefinition);

	/**
	* Returns the commerce bom definition with the primary key or throws a {@link NoSuchBOMDefinitionException} if it could not be found.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public CommerceBOMDefinition findByPrimaryKey(long commerceBOMDefinitionId)
		throws NoSuchBOMDefinitionException;

	/**
	* Returns the commerce bom definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition, or <code>null</code> if a commerce bom definition with the primary key could not be found
	*/
	public CommerceBOMDefinition fetchByPrimaryKey(long commerceBOMDefinitionId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBOMDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce bom definitions.
	*
	* @return the commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findAll();

	/**
	* Returns a range of all the commerce bom definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @return the range of commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commerce bom definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the commerce bom definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce bom definitions
	*/
	public java.util.List<CommerceBOMDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce bom definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce bom definitions.
	*
	* @return the number of commerce bom definitions
	*/
	public int countAll();
}