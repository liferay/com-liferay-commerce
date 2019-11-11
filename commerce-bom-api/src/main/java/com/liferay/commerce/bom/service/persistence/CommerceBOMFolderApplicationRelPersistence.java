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

import com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException;
import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce bom folder application rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.persistence.impl.CommerceBOMFolderApplicationRelPersistenceImpl
 * @see CommerceBOMFolderApplicationRelUtil
 * @generated
 */
@ProviderType
public interface CommerceBOMFolderApplicationRelPersistence
	extends BasePersistence<CommerceBOMFolderApplicationRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceBOMFolderApplicationRelUtil} to access the commerce bom folder application rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId);

	/**
	* Returns a range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @return the range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end);

	/**
	* Returns an ordered range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public CommerceBOMFolderApplicationRel[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMFolderApplicationRelId, long commerceBOMFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Removes all the commerce bom folder application rels where commerceBOMFolderId = &#63; from the database.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	*/
	public void removeByCommerceBOMFolderId(long commerceBOMFolderId);

	/**
	* Returns the number of commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom folder application rels
	*/
	public int countByCommerceBOMFolderId(long commerceBOMFolderId);

	/**
	* Returns all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId);

	/**
	* Returns a range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @return the range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end);

	/**
	* Returns an ordered range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public CommerceBOMFolderApplicationRel[] findByCommerceApplicationModelId_PrevAndNext(
		long commerceBOMFolderApplicationRelId,
		long commerceApplicationModelId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Removes all the commerce bom folder application rels where commerceApplicationModelId = &#63; from the database.
	*
	* @param commerceApplicationModelId the commerce application model ID
	*/
	public void removeByCommerceApplicationModelId(
		long commerceApplicationModelId);

	/**
	* Returns the number of commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the number of matching commerce bom folder application rels
	*/
	public int countByCommerceApplicationModelId(
		long commerceApplicationModelId);

	/**
	* Caches the commerce bom folder application rel in the entity cache if it is enabled.
	*
	* @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	*/
	public void cacheResult(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel);

	/**
	* Caches the commerce bom folder application rels in the entity cache if it is enabled.
	*
	* @param commerceBOMFolderApplicationRels the commerce bom folder application rels
	*/
	public void cacheResult(
		java.util.List<CommerceBOMFolderApplicationRel> commerceBOMFolderApplicationRels);

	/**
	* Creates a new commerce bom folder application rel with the primary key. Does not add the commerce bom folder application rel to the database.
	*
	* @param commerceBOMFolderApplicationRelId the primary key for the new commerce bom folder application rel
	* @return the new commerce bom folder application rel
	*/
	public CommerceBOMFolderApplicationRel create(
		long commerceBOMFolderApplicationRelId);

	/**
	* Removes the commerce bom folder application rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel that was removed
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public CommerceBOMFolderApplicationRel remove(
		long commerceBOMFolderApplicationRelId)
		throws NoSuchBOMFolderApplicationRelException;

	public CommerceBOMFolderApplicationRel updateImpl(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel);

	/**
	* Returns the commerce bom folder application rel with the primary key or throws a {@link NoSuchBOMFolderApplicationRelException} if it could not be found.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public CommerceBOMFolderApplicationRel findByPrimaryKey(
		long commerceBOMFolderApplicationRelId)
		throws NoSuchBOMFolderApplicationRelException;

	/**
	* Returns the commerce bom folder application rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel, or <code>null</code> if a commerce bom folder application rel with the primary key could not be found
	*/
	public CommerceBOMFolderApplicationRel fetchByPrimaryKey(
		long commerceBOMFolderApplicationRelId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceBOMFolderApplicationRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce bom folder application rels.
	*
	* @return the commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findAll();

	/**
	* Returns a range of all the commerce bom folder application rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @return the range of commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the commerce bom folder application rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator);

	/**
	* Returns an ordered range of all the commerce bom folder application rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce bom folder application rels
	*/
	public java.util.List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce bom folder application rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce bom folder application rels.
	*
	* @return the number of commerce bom folder application rels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}