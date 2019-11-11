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

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce bom folder application rel service. This utility wraps {@link com.liferay.commerce.bom.service.persistence.impl.CommerceBOMFolderApplicationRelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelPersistence
 * @see com.liferay.commerce.bom.service.persistence.impl.CommerceBOMFolderApplicationRelPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelUtil {
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
	public static void clearCache(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		getPersistence().clearCache(commerceBOMFolderApplicationRel);
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
	public static List<CommerceBOMFolderApplicationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBOMFolderApplicationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBOMFolderApplicationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBOMFolderApplicationRel update(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		return getPersistence().update(commerceBOMFolderApplicationRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBOMFolderApplicationRel update(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(commerceBOMFolderApplicationRel, serviceContext);
	}

	/**
	* Returns all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom folder application rels
	*/
	public static List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return getPersistence().findByCommerceBOMFolderId(commerceBOMFolderId);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceBOMFolderId_First(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceBOMFolderId_First(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceBOMFolderId_Last(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceBOMFolderId_Last(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static CommerceBOMFolderApplicationRel[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMFolderApplicationRelId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceBOMFolderId_PrevAndNext(commerceBOMFolderApplicationRelId,
			commerceBOMFolderId, orderByComparator);
	}

	/**
	* Removes all the commerce bom folder application rels where commerceBOMFolderId = &#63; from the database.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	*/
	public static void removeByCommerceBOMFolderId(long commerceBOMFolderId) {
		getPersistence().removeByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns the number of commerce bom folder application rels where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom folder application rels
	*/
	public static int countByCommerceBOMFolderId(long commerceBOMFolderId) {
		return getPersistence().countByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the matching commerce bom folder application rels
	*/
	public static List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, orderByComparator);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_First(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationModelId_First(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_Last(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	*/
	public static CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationModelId_Last(commerceApplicationModelId,
			orderByComparator);
	}

	/**
	* Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	* @param commerceApplicationModelId the commerce application model ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static CommerceBOMFolderApplicationRel[] findByCommerceApplicationModelId_PrevAndNext(
		long commerceBOMFolderApplicationRelId,
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByCommerceApplicationModelId_PrevAndNext(commerceBOMFolderApplicationRelId,
			commerceApplicationModelId, orderByComparator);
	}

	/**
	* Removes all the commerce bom folder application rels where commerceApplicationModelId = &#63; from the database.
	*
	* @param commerceApplicationModelId the commerce application model ID
	*/
	public static void removeByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		getPersistence()
			.removeByCommerceApplicationModelId(commerceApplicationModelId);
	}

	/**
	* Returns the number of commerce bom folder application rels where commerceApplicationModelId = &#63;.
	*
	* @param commerceApplicationModelId the commerce application model ID
	* @return the number of matching commerce bom folder application rels
	*/
	public static int countByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		return getPersistence()
				   .countByCommerceApplicationModelId(commerceApplicationModelId);
	}

	/**
	* Caches the commerce bom folder application rel in the entity cache if it is enabled.
	*
	* @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	*/
	public static void cacheResult(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		getPersistence().cacheResult(commerceBOMFolderApplicationRel);
	}

	/**
	* Caches the commerce bom folder application rels in the entity cache if it is enabled.
	*
	* @param commerceBOMFolderApplicationRels the commerce bom folder application rels
	*/
	public static void cacheResult(
		List<CommerceBOMFolderApplicationRel> commerceBOMFolderApplicationRels) {
		getPersistence().cacheResult(commerceBOMFolderApplicationRels);
	}

	/**
	* Creates a new commerce bom folder application rel with the primary key. Does not add the commerce bom folder application rel to the database.
	*
	* @param commerceBOMFolderApplicationRelId the primary key for the new commerce bom folder application rel
	* @return the new commerce bom folder application rel
	*/
	public static CommerceBOMFolderApplicationRel create(
		long commerceBOMFolderApplicationRelId) {
		return getPersistence().create(commerceBOMFolderApplicationRelId);
	}

	/**
	* Removes the commerce bom folder application rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel that was removed
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static CommerceBOMFolderApplicationRel remove(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence().remove(commerceBOMFolderApplicationRelId);
	}

	public static CommerceBOMFolderApplicationRel updateImpl(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		return getPersistence().updateImpl(commerceBOMFolderApplicationRel);
	}

	/**
	* Returns the commerce bom folder application rel with the primary key or throws a {@link NoSuchBOMFolderApplicationRelException} if it could not be found.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel
	* @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static CommerceBOMFolderApplicationRel findByPrimaryKey(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException {
		return getPersistence()
				   .findByPrimaryKey(commerceBOMFolderApplicationRelId);
	}

	/**
	* Returns the commerce bom folder application rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel, or <code>null</code> if a commerce bom folder application rel with the primary key could not be found
	*/
	public static CommerceBOMFolderApplicationRel fetchByPrimaryKey(
		long commerceBOMFolderApplicationRelId) {
		return getPersistence()
				   .fetchByPrimaryKey(commerceBOMFolderApplicationRelId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBOMFolderApplicationRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce bom folder application rels.
	*
	* @return the commerce bom folder application rels
	*/
	public static List<CommerceBOMFolderApplicationRel> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommerceBOMFolderApplicationRel> findAll(int start,
		int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce bom folder application rels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce bom folder application rels.
	*
	* @return the number of commerce bom folder application rels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceBOMFolderApplicationRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderApplicationRelPersistence, CommerceBOMFolderApplicationRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderApplicationRelPersistence.class);

		ServiceTracker<CommerceBOMFolderApplicationRelPersistence, CommerceBOMFolderApplicationRelPersistence> serviceTracker =
			new ServiceTracker<CommerceBOMFolderApplicationRelPersistence, CommerceBOMFolderApplicationRelPersistence>(bundle.getBundleContext(),
				CommerceBOMFolderApplicationRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}