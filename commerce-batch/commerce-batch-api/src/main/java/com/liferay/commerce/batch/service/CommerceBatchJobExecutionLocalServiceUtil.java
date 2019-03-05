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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceBatchJobExecution. This utility wraps
 * {@link com.liferay.commerce.batch.service.impl.CommerceBatchJobExecutionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecutionLocalService
 * @see com.liferay.commerce.batch.service.base.CommerceBatchJobExecutionLocalServiceBaseImpl
 * @see com.liferay.commerce.batch.service.impl.CommerceBatchJobExecutionLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.batch.service.impl.CommerceBatchJobExecutionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce batch job execution to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was added
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution addCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return getService()
				   .addCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	/**
	* Creates a new commerce batch job execution with the primary key. Does not add the commerce batch job execution to the database.
	*
	* @param commerceBatchJobExecutionId the primary key for the new commerce batch job execution
	* @return the new commerce batch job execution
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution createCommerceBatchJobExecution(
		long commerceBatchJobExecutionId) {
		return getService()
				   .createCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	/**
	* Deletes the commerce batch job execution from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was removed
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution deleteCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return getService()
				   .deleteCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	/**
	* Deletes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution that was removed
	* @throws PortalException if a commerce batch job execution with the primary key could not be found
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution deleteCommerceBatchJobExecution(
		long commerceBatchJobExecutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution fetchCommerceBatchJobExecution(
		long commerceBatchJobExecutionId) {
		return getService()
				   .fetchCommerceBatchJobExecution(commerceBatchJobExecutionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce batch job execution with the primary key.
	*
	* @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	* @return the commerce batch job execution
	* @throws PortalException if a commerce batch job execution with the primary key could not be found
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution getCommerceBatchJobExecution(
		long commerceBatchJobExecutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBatchJobExecution(commerceBatchJobExecutionId);
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
	public static java.util.List<com.liferay.commerce.batch.model.CommerceBatchJobExecution> getCommerceBatchJobExecutions(
		int start, int end) {
		return getService().getCommerceBatchJobExecutions(start, end);
	}

	/**
	* Returns the number of commerce batch job executions.
	*
	* @return the number of commerce batch job executions
	*/
	public static int getCommerceBatchJobExecutionsCount() {
		return getService().getCommerceBatchJobExecutionsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce batch job execution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobExecution the commerce batch job execution
	* @return the commerce batch job execution that was updated
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJobExecution updateCommerceBatchJobExecution(
		com.liferay.commerce.batch.model.CommerceBatchJobExecution commerceBatchJobExecution) {
		return getService()
				   .updateCommerceBatchJobExecution(commerceBatchJobExecution);
	}

	public static CommerceBatchJobExecutionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchJobExecutionLocalService, CommerceBatchJobExecutionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchJobExecutionLocalService.class);

		ServiceTracker<CommerceBatchJobExecutionLocalService, CommerceBatchJobExecutionLocalService> serviceTracker =
			new ServiceTracker<CommerceBatchJobExecutionLocalService, CommerceBatchJobExecutionLocalService>(bundle.getBundleContext(),
				CommerceBatchJobExecutionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}