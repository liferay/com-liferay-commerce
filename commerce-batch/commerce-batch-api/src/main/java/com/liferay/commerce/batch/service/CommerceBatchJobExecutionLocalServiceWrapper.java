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

package com.liferay.commerce.batch.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBatchJobExecutionLocalService}.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecutionLocalService
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionLocalServiceWrapper
	implements CommerceBatchJobExecutionLocalService,
		ServiceWrapper<CommerceBatchJobExecutionLocalService> {
	public CommerceBatchJobExecutionLocalServiceWrapper(
		CommerceBatchJobExecutionLocalService commerceBatchJobExecutionLocalService) {
		_commerceBatchJobExecutionLocalService = commerceBatchJobExecutionLocalService;
	}

	/**
	* Adds the commerce batch job execution to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was added
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution addCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return _commerceBatchJobExecutionLocalService.addCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	/**
	* Creates a new commerce batch job execution with the primary key. Does not add the commerce batch job execution to the database.
	*
	* @param commerceBatchJobExecutionId the primary key for the new commerce batch job execution
	* @return the new commerce batch job execution
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution createCommerceBatchJobExecution(
		long commerceBatchJobExecutionId) {
		return _commerceBatchJobExecutionLocalService.createCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	/**
	* Deletes the commerce batch job execution from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was removed
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution deleteCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return _commerceBatchJobExecutionLocalService.deleteCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	/**
	* Deletes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution that was removed
	* @throws PortalException if a commerce batch job execution with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution deleteCommerceBatchJobExecution(
		long commerceBatchJobExecutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchJobExecutionLocalService.deleteCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchJobExecutionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceBatchJobExecutionLocalService.dynamicQuery();
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
		return _commerceBatchJobExecutionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBatchJobExecutionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBatchJobExecutionLocalService.dynamicQuery(dynamicQuery,
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
		return _commerceBatchJobExecutionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commerceBatchJobExecutionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution fetchCommerceBatchJobExecution(
		long commerceBatchJobExecutionId) {
		return _commerceBatchJobExecutionLocalService.fetchCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceBatchJobExecutionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the commerce batch job execution with the primary key.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution
	* @throws PortalException if a commerce batch job execution with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution getCommerceBatchJobExecution(
		long commerceBatchJobExecutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchJobExecutionLocalService.getCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	/**
	* Returns a range of all the commerce batch job executions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch job executions
	* @param end the upper bound of the range of commerce batch job executions (not inclusive)
	* @return the range of commerce batch job executions
	*/
	@Override
	public java.util.List<com.liferay.commerce.batch.model.CommerceBatchJobExecution> getCommerceBatchJobExecutions(
		int start, int end) {
		return _commerceBatchJobExecutionLocalService.getCommerceBatchJobExecutions(start,
			end);
	}

	/**
	* Returns the number of commerce batch job executions.
	*
	* @return the number of commerce batch job executions
	*/
	@Override
	public int getCommerceBatchJobExecutionsCount() {
		return _commerceBatchJobExecutionLocalService.getCommerceBatchJobExecutionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceBatchJobExecutionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBatchJobExecutionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchJobExecutionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce batch job execution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was updated
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchJobExecution updateCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return _commerceBatchJobExecutionLocalService.updateCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	@Override
	public CommerceBatchJobExecutionLocalService getWrappedService() {
		return _commerceBatchJobExecutionLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceBatchJobExecutionLocalService commerceBatchJobExecutionLocalService) {
		_commerceBatchJobExecutionLocalService = commerceBatchJobExecutionLocalService;
	}

	private CommerceBatchJobExecutionLocalService _commerceBatchJobExecutionLocalService;
}