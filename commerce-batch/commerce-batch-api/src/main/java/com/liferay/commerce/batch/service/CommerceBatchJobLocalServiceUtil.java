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
 * Provides the local service utility for CommerceBatchJob. This utility wraps
 * {@link com.liferay.commerce.batch.service.impl.CommerceBatchJobLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobLocalService
 * @see com.liferay.commerce.batch.service.base.CommerceBatchJobLocalServiceBaseImpl
 * @see com.liferay.commerce.batch.service.impl.CommerceBatchJobLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBatchJobLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.batch.service.impl.CommerceBatchJobLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce batch job to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJob the commerce batch job
	* @return the commerce batch job that was added
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob addCommerceBatchJob(
		com.liferay.commerce.batch.model.CommerceBatchJob commerceBatchJob) {
		return getService().addCommerceBatchJob(commerceBatchJob);
	}

	/**
	* Creates a new commerce batch job with the primary key. Does not add the commerce batch job to the database.
	*
	* @param commerceBatchJobId the primary key for the new commerce batch job
	* @return the new commerce batch job
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob createCommerceBatchJob(
		long commerceBatchJobId) {
		return getService().createCommerceBatchJob(commerceBatchJobId);
	}

	/**
	* Deletes the commerce batch job from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJob the commerce batch job
	* @return the commerce batch job that was removed
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob deleteCommerceBatchJob(
		com.liferay.commerce.batch.model.CommerceBatchJob commerceBatchJob) {
		return getService().deleteCommerceBatchJob(commerceBatchJob);
	}

	/**
	* Deletes the commerce batch job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job that was removed
	* @throws PortalException if a commerce batch job with the primary key could not be found
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob deleteCommerceBatchJob(
		long commerceBatchJobId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceBatchJob(commerceBatchJobId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.batch.model.CommerceBatchJob fetchCommerceBatchJob(
		long commerceBatchJobId) {
		return getService().fetchCommerceBatchJob(commerceBatchJobId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce batch job with the primary key.
	*
	* @param commerceBatchJobId the primary key of the commerce batch job
	* @return the commerce batch job
	* @throws PortalException if a commerce batch job with the primary key could not be found
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob getCommerceBatchJob(
		long commerceBatchJobId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBatchJob(commerceBatchJobId);
	}

	/**
	* Returns a range of all the commerce batch jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch jobs
	* @param end the upper bound of the range of commerce batch jobs (not inclusive)
	* @return the range of commerce batch jobs
	*/
	public static java.util.List<com.liferay.commerce.batch.model.CommerceBatchJob> getCommerceBatchJobs(
		int start, int end) {
		return getService().getCommerceBatchJobs(start, end);
	}

	/**
	* Returns the number of commerce batch jobs.
	*
	* @return the number of commerce batch jobs
	*/
	public static int getCommerceBatchJobsCount() {
		return getService().getCommerceBatchJobsCount();
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
	* Updates the commerce batch job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchJob the commerce batch job
	* @return the commerce batch job that was updated
	*/
	public static com.liferay.commerce.batch.model.CommerceBatchJob updateCommerceBatchJob(
		com.liferay.commerce.batch.model.CommerceBatchJob commerceBatchJob) {
		return getService().updateCommerceBatchJob(commerceBatchJob);
	}

	public static CommerceBatchJobLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBatchJobLocalService, CommerceBatchJobLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBatchJobLocalService.class);

		ServiceTracker<CommerceBatchJobLocalService, CommerceBatchJobLocalService> serviceTracker =
			new ServiceTracker<CommerceBatchJobLocalService, CommerceBatchJobLocalService>(bundle.getBundleContext(),
				CommerceBatchJobLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}