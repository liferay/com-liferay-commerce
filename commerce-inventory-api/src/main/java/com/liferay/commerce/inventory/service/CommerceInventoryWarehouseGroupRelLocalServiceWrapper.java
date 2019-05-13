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

package com.liferay.commerce.inventory.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryWarehouseGroupRelLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRelLocalService
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelLocalServiceWrapper
	implements CommerceInventoryWarehouseGroupRelLocalService,
		ServiceWrapper<CommerceInventoryWarehouseGroupRelLocalService> {
	public CommerceInventoryWarehouseGroupRelLocalServiceWrapper(
		CommerceInventoryWarehouseGroupRelLocalService commerceInventoryWarehouseGroupRelLocalService) {
		_commerceInventoryWarehouseGroupRelLocalService = commerceInventoryWarehouseGroupRelLocalService;
	}

	/**
	* Adds the commerce inventory warehouse group rel to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was added
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel addCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return _commerceInventoryWarehouseGroupRelLocalService.addCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel addCommerceWarehouseGroupRel(
		long commerceWarehouseId, boolean primary,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseGroupRelLocalService.addCommerceWarehouseGroupRel(commerceWarehouseId,
			primary, serviceContext);
	}

	/**
	* Creates a new commerce inventory warehouse group rel with the primary key. Does not add the commerce inventory warehouse group rel to the database.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key for the new commerce inventory warehouse group rel
	* @return the new commerce inventory warehouse group rel
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel createCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId) {
		return _commerceInventoryWarehouseGroupRelLocalService.createCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Deletes the commerce inventory warehouse group rel from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel deleteCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return _commerceInventoryWarehouseGroupRelLocalService.deleteCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	/**
	* Deletes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was removed
	* @throws PortalException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel deleteCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseGroupRelLocalService.deleteCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseGroupRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _commerceInventoryWarehouseGroupRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel fetchCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId) {
		return _commerceInventoryWarehouseGroupRelLocalService.fetchCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceInventoryWarehouseGroupRelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the commerce inventory warehouse group rel with the primary key.
	*
	* @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel
	* @throws PortalException if a commerce inventory warehouse group rel with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel getCommerceInventoryWarehouseGroupRel(
		long commerceInventoryWarehouseGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseGroupRelLocalService.getCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRelId);
	}

	/**
	* Returns a range of all the commerce inventory warehouse group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce inventory warehouse group rels
	* @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	* @return the range of commerce inventory warehouse group rels
	*/
	@Override
	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel> getCommerceInventoryWarehouseGroupRels(
		int start, int end) {
		return _commerceInventoryWarehouseGroupRelLocalService.getCommerceInventoryWarehouseGroupRels(start,
			end);
	}

	/**
	* Returns the number of commerce inventory warehouse group rels.
	*
	* @return the number of commerce inventory warehouse group rels
	*/
	@Override
	public int getCommerceInventoryWarehouseGroupRelsCount() {
		return _commerceInventoryWarehouseGroupRelLocalService.getCommerceInventoryWarehouseGroupRelsCount();
	}

	@Override
	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel> getCommerceWarehouseGroupRels(
		long groupId) {
		return _commerceInventoryWarehouseGroupRelLocalService.getCommerceWarehouseGroupRels(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceInventoryWarehouseGroupRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryWarehouseGroupRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseGroupRelLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public long getPrimaryCommerceWarehouseId(long groupId) {
		return _commerceInventoryWarehouseGroupRelLocalService.getPrimaryCommerceWarehouseId(groupId);
	}

	/**
	* Updates the commerce inventory warehouse group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	* @return the commerce inventory warehouse group rel that was updated
	*/
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel updateCommerceInventoryWarehouseGroupRel(
		com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		return _commerceInventoryWarehouseGroupRelLocalService.updateCommerceInventoryWarehouseGroupRel(commerceInventoryWarehouseGroupRel);
	}

	@Override
	public CommerceInventoryWarehouseGroupRelLocalService getWrappedService() {
		return _commerceInventoryWarehouseGroupRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryWarehouseGroupRelLocalService commerceInventoryWarehouseGroupRelLocalService) {
		_commerceInventoryWarehouseGroupRelLocalService = commerceInventoryWarehouseGroupRelLocalService;
	}

	private CommerceInventoryWarehouseGroupRelLocalService _commerceInventoryWarehouseGroupRelLocalService;
}