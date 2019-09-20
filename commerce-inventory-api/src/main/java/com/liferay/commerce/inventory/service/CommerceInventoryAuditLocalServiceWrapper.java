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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryAuditLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryAuditLocalService
 * @generated
 */
public class CommerceInventoryAuditLocalServiceWrapper
	implements CommerceInventoryAuditLocalService,
			   ServiceWrapper<CommerceInventoryAuditLocalService> {

	public CommerceInventoryAuditLocalServiceWrapper(
		CommerceInventoryAuditLocalService commerceInventoryAuditLocalService) {

		_commerceInventoryAuditLocalService =
			commerceInventoryAuditLocalService;
	}

	/**
	 * Adds the commerce inventory audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryAudit the commerce inventory audit
	 * @return the commerce inventory audit that was added
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
		addCommerceInventoryAudit(
			com.liferay.commerce.inventory.model.CommerceInventoryAudit
				commerceInventoryAudit) {

		return _commerceInventoryAuditLocalService.addCommerceInventoryAudit(
			commerceInventoryAudit);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
			addCommerceInventoryAudit(
				long userId, String sku, int quantity, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryAuditLocalService.addCommerceInventoryAudit(
			userId, sku, quantity, description);
	}

	@Override
	public void checkCommerceInventoryAudit(java.util.Date date) {
		_commerceInventoryAuditLocalService.checkCommerceInventoryAudit(date);
	}

	/**
	 * Creates a new commerce inventory audit with the primary key. Does not add the commerce inventory audit to the database.
	 *
	 * @param commerceInventoryAuditId the primary key for the new commerce inventory audit
	 * @return the new commerce inventory audit
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
		createCommerceInventoryAudit(long commerceInventoryAuditId) {

		return _commerceInventoryAuditLocalService.createCommerceInventoryAudit(
			commerceInventoryAuditId);
	}

	/**
	 * Deletes the commerce inventory audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryAudit the commerce inventory audit
	 * @return the commerce inventory audit that was removed
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
		deleteCommerceInventoryAudit(
			com.liferay.commerce.inventory.model.CommerceInventoryAudit
				commerceInventoryAudit) {

		return _commerceInventoryAuditLocalService.deleteCommerceInventoryAudit(
			commerceInventoryAudit);
	}

	/**
	 * Deletes the commerce inventory audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit that was removed
	 * @throws PortalException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
			deleteCommerceInventoryAudit(long commerceInventoryAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryAuditLocalService.deleteCommerceInventoryAudit(
			commerceInventoryAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceInventoryAuditLocalService.dynamicQuery();
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

		return _commerceInventoryAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceInventoryAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceInventoryAuditLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _commerceInventoryAuditLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _commerceInventoryAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
		fetchCommerceInventoryAudit(long commerceInventoryAuditId) {

		return _commerceInventoryAuditLocalService.fetchCommerceInventoryAudit(
			commerceInventoryAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceInventoryAuditLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce inventory audit with the primary key.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit
	 * @throws PortalException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
			getCommerceInventoryAudit(long commerceInventoryAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryAuditLocalService.getCommerceInventoryAudit(
			commerceInventoryAuditId);
	}

	/**
	 * Returns a range of all the commerce inventory audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @return the range of commerce inventory audits
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.CommerceInventoryAudit>
			getCommerceInventoryAudits(int start, int end) {

		return _commerceInventoryAuditLocalService.getCommerceInventoryAudits(
			start, end);
	}

	/**
	 * Returns the number of commerce inventory audits.
	 *
	 * @return the number of commerce inventory audits
	 */
	@Override
	public int getCommerceInventoryAuditsCount() {
		return _commerceInventoryAuditLocalService.
			getCommerceInventoryAuditsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceInventoryAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryAuditLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce inventory audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryAudit the commerce inventory audit
	 * @return the commerce inventory audit that was updated
	 */
	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryAudit
		updateCommerceInventoryAudit(
			com.liferay.commerce.inventory.model.CommerceInventoryAudit
				commerceInventoryAudit) {

		return _commerceInventoryAuditLocalService.updateCommerceInventoryAudit(
			commerceInventoryAudit);
	}

	@Override
	public CommerceInventoryAuditLocalService getWrappedService() {
		return _commerceInventoryAuditLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryAuditLocalService commerceInventoryAuditLocalService) {

		_commerceInventoryAuditLocalService =
			commerceInventoryAuditLocalService;
	}

	private CommerceInventoryAuditLocalService
		_commerceInventoryAuditLocalService;

}