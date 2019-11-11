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

import com.liferay.commerce.bom.model.CommerceBOMDefinition;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce bom definition service. This utility wraps {@link com.liferay.commerce.bom.service.persistence.impl.CommerceBOMDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionPersistence
 * @see com.liferay.commerce.bom.service.persistence.impl.CommerceBOMDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionUtil {
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
	public static void clearCache(CommerceBOMDefinition commerceBOMDefinition) {
		getPersistence().clearCache(commerceBOMDefinition);
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
	public static List<CommerceBOMDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBOMDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBOMDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBOMDefinition update(
		CommerceBOMDefinition commerceBOMDefinition) {
		return getPersistence().update(commerceBOMDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBOMDefinition update(
		CommerceBOMDefinition commerceBOMDefinition,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceBOMDefinition, serviceContext);
	}

	/**
	* Returns all the commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom definitions
	*/
	public static List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return getPersistence().findByCommerceBOMFolderId(commerceBOMFolderId);
	}

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
	public static List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end);
	}

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
	public static List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator);
	}

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
	public static List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom definition
	* @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	*/
	public static CommerceBOMDefinition findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence()
				   .findByCommerceBOMFolderId_First(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	*/
	public static CommerceBOMDefinition fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceBOMFolderId_First(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom definition
	* @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	*/
	public static CommerceBOMDefinition findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence()
				   .findByCommerceBOMFolderId_Last(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	*/
	public static CommerceBOMDefinition fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceBOMFolderId_Last(commerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public static CommerceBOMDefinition[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence()
				   .findByCommerceBOMFolderId_PrevAndNext(commerceBOMDefinitionId,
			commerceBOMFolderId, orderByComparator);
	}

	/**
	* Returns all the commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the matching commerce bom definitions that the user has permission to view
	*/
	public static List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return getPersistence()
				   .filterFindByCommerceBOMFolderId(commerceBOMFolderId);
	}

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
	public static List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return getPersistence()
				   .filterFindByCommerceBOMFolderId(commerceBOMFolderId, start,
			end);
	}

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
	public static List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence()
				   .filterFindByCommerceBOMFolderId(commerceBOMFolderId, start,
			end, orderByComparator);
	}

	/**
	* Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	* @param commerceBOMFolderId the commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public static CommerceBOMDefinition[] filterFindByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence()
				   .filterFindByCommerceBOMFolderId_PrevAndNext(commerceBOMDefinitionId,
			commerceBOMFolderId, orderByComparator);
	}

	/**
	* Removes all the commerce bom definitions where commerceBOMFolderId = &#63; from the database.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	*/
	public static void removeByCommerceBOMFolderId(long commerceBOMFolderId) {
		getPersistence().removeByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns the number of commerce bom definitions where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom definitions
	*/
	public static int countByCommerceBOMFolderId(long commerceBOMFolderId) {
		return getPersistence().countByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns the number of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the commerce bom folder ID
	* @return the number of matching commerce bom definitions that the user has permission to view
	*/
	public static int filterCountByCommerceBOMFolderId(long commerceBOMFolderId) {
		return getPersistence()
				   .filterCountByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Caches the commerce bom definition in the entity cache if it is enabled.
	*
	* @param commerceBOMDefinition the commerce bom definition
	*/
	public static void cacheResult(CommerceBOMDefinition commerceBOMDefinition) {
		getPersistence().cacheResult(commerceBOMDefinition);
	}

	/**
	* Caches the commerce bom definitions in the entity cache if it is enabled.
	*
	* @param commerceBOMDefinitions the commerce bom definitions
	*/
	public static void cacheResult(
		List<CommerceBOMDefinition> commerceBOMDefinitions) {
		getPersistence().cacheResult(commerceBOMDefinitions);
	}

	/**
	* Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	*
	* @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	* @return the new commerce bom definition
	*/
	public static CommerceBOMDefinition create(long commerceBOMDefinitionId) {
		return getPersistence().create(commerceBOMDefinitionId);
	}

	/**
	* Removes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public static CommerceBOMDefinition remove(long commerceBOMDefinitionId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence().remove(commerceBOMDefinitionId);
	}

	public static CommerceBOMDefinition updateImpl(
		CommerceBOMDefinition commerceBOMDefinition) {
		return getPersistence().updateImpl(commerceBOMDefinition);
	}

	/**
	* Returns the commerce bom definition with the primary key or throws a {@link NoSuchBOMDefinitionException} if it could not be found.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition
	* @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	*/
	public static CommerceBOMDefinition findByPrimaryKey(
		long commerceBOMDefinitionId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException {
		return getPersistence().findByPrimaryKey(commerceBOMDefinitionId);
	}

	/**
	* Returns the commerce bom definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition, or <code>null</code> if a commerce bom definition with the primary key could not be found
	*/
	public static CommerceBOMDefinition fetchByPrimaryKey(
		long commerceBOMDefinitionId) {
		return getPersistence().fetchByPrimaryKey(commerceBOMDefinitionId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBOMDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce bom definitions.
	*
	* @return the commerce bom definitions
	*/
	public static List<CommerceBOMDefinition> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommerceBOMDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommerceBOMDefinition> findAll(int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommerceBOMDefinition> findAll(int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce bom definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce bom definitions.
	*
	* @return the number of commerce bom definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceBOMDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMDefinitionPersistence, CommerceBOMDefinitionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMDefinitionPersistence.class);

		ServiceTracker<CommerceBOMDefinitionPersistence, CommerceBOMDefinitionPersistence> serviceTracker =
			new ServiceTracker<CommerceBOMDefinitionPersistence, CommerceBOMDefinitionPersistence>(bundle.getBundleContext(),
				CommerceBOMDefinitionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}