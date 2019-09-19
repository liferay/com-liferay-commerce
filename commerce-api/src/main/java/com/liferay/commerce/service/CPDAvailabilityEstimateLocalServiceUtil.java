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

package com.liferay.commerce.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CPDAvailabilityEstimate. This utility wraps
 * <code>com.liferay.commerce.service.impl.CPDAvailabilityEstimateLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CPDAvailabilityEstimateLocalService
 * @generated
 */
public class CPDAvailabilityEstimateLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CPDAvailabilityEstimateLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cpd availability estimate to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was added
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		addCPDAvailabilityEstimate(
			com.liferay.commerce.model.CPDAvailabilityEstimate
				cpdAvailabilityEstimate) {

		return getService().addCPDAvailabilityEstimate(cpdAvailabilityEstimate);
	}

	/**
	 * Creates a new cpd availability estimate with the primary key. Does not add the cpd availability estimate to the database.
	 *
	 * @param CPDAvailabilityEstimateId the primary key for the new cpd availability estimate
	 * @return the new cpd availability estimate
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		createCPDAvailabilityEstimate(long CPDAvailabilityEstimateId) {

		return getService().createCPDAvailabilityEstimate(
			CPDAvailabilityEstimateId);
	}

	/**
	 * Deletes the cpd availability estimate from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		deleteCPDAvailabilityEstimate(
			com.liferay.commerce.model.CPDAvailabilityEstimate
				cpdAvailabilityEstimate) {

		return getService().deleteCPDAvailabilityEstimate(
			cpdAvailabilityEstimate);
	}

	/**
	 * Deletes the cpd availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 * @throws PortalException if a cpd availability estimate with the primary key could not be found
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			deleteCPDAvailabilityEstimate(long CPDAvailabilityEstimateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPDAvailabilityEstimate(
			CPDAvailabilityEstimateId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static void deleteCPDAvailabilityEstimateByCPDefinitionId(
		long cpDefinitionId) {

		getService().deleteCPDAvailabilityEstimateByCPDefinitionId(
			cpDefinitionId);
	}

	public static void deleteCPDAvailabilityEstimateByCProductId(
		long cProductId) {

		getService().deleteCPDAvailabilityEstimateByCProductId(cProductId);
	}

	public static void deleteCPDAvailabilityEstimates(
		long commerceAvailabilityEstimateId) {

		getService().deleteCPDAvailabilityEstimates(
			commerceAvailabilityEstimateId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		fetchCPDAvailabilityEstimate(long CPDAvailabilityEstimateId) {

		return getService().fetchCPDAvailabilityEstimate(
			CPDAvailabilityEstimateId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		fetchCPDAvailabilityEstimateByCPDefinitionId(long cpDefinitionId) {

		return getService().fetchCPDAvailabilityEstimateByCPDefinitionId(
			cpDefinitionId);
	}

	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		fetchCPDAvailabilityEstimateByCProductId(long cProductId) {

		return getService().fetchCPDAvailabilityEstimateByCProductId(
			cProductId);
	}

	/**
	 * Returns the cpd availability estimate with the matching UUID and company.
	 *
	 * @param uuid the cpd availability estimate's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		fetchCPDAvailabilityEstimateByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().fetchCPDAvailabilityEstimateByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cpd availability estimate with the primary key.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate
	 * @throws PortalException if a cpd availability estimate with the primary key could not be found
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			getCPDAvailabilityEstimate(long CPDAvailabilityEstimateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDAvailabilityEstimate(
			CPDAvailabilityEstimateId);
	}

	/**
	 * Returns the cpd availability estimate with the matching UUID and company.
	 *
	 * @param uuid the cpd availability estimate's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cpd availability estimate
	 * @throws PortalException if a matching cpd availability estimate could not be found
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			getCPDAvailabilityEstimateByUuidAndCompanyId(
				String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDAvailabilityEstimateByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the cpd availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of cpd availability estimates
	 */
	public static java.util.List
		<com.liferay.commerce.model.CPDAvailabilityEstimate>
			getCPDAvailabilityEstimates(int start, int end) {

		return getService().getCPDAvailabilityEstimates(start, end);
	}

	/**
	 * Returns the number of cpd availability estimates.
	 *
	 * @return the number of cpd availability estimates
	 */
	public static int getCPDAvailabilityEstimatesCount() {
		return getService().getCPDAvailabilityEstimatesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cpd availability estimate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was updated
	 */
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
		updateCPDAvailabilityEstimate(
			com.liferay.commerce.model.CPDAvailabilityEstimate
				cpdAvailabilityEstimate) {

		return getService().updateCPDAvailabilityEstimate(
			cpdAvailabilityEstimate);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			updateCPDAvailabilityEstimate(
				long cpdAvailabilityEstimateId, long cpDefinitionId,
				long commerceAvailabilityEstimateId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDAvailabilityEstimate(
			cpdAvailabilityEstimateId, cpDefinitionId,
			commerceAvailabilityEstimateId, serviceContext);
	}

	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			updateCPDAvailabilityEstimateByCProductId(
				long cpdAvailabilityEstimateId, long cProductId,
				long commerceAvailabilityEstimateId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDAvailabilityEstimateByCProductId(
			cpdAvailabilityEstimateId, cProductId,
			commerceAvailabilityEstimateId, serviceContext);
	}

	public static CPDAvailabilityEstimateLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPDAvailabilityEstimateLocalService,
		 CPDAvailabilityEstimateLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPDAvailabilityEstimateLocalService.class);

		ServiceTracker
			<CPDAvailabilityEstimateLocalService,
			 CPDAvailabilityEstimateLocalService> serviceTracker =
				new ServiceTracker
					<CPDAvailabilityEstimateLocalService,
					 CPDAvailabilityEstimateLocalService>(
						 bundle.getBundleContext(),
						 CPDAvailabilityEstimateLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}