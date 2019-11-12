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

package com.liferay.commerce.data.integration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDataIntegrationProcessLogLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogLocalService
 * @generated
 */
@ProviderType
public class CommerceDataIntegrationProcessLogLocalServiceWrapper
	implements CommerceDataIntegrationProcessLogLocalService,
			   ServiceWrapper<CommerceDataIntegrationProcessLogLocalService> {

	public CommerceDataIntegrationProcessLogLocalServiceWrapper(
		CommerceDataIntegrationProcessLogLocalService
			commerceDataIntegrationProcessLogLocalService) {

		_commerceDataIntegrationProcessLogLocalService =
			commerceDataIntegrationProcessLogLocalService;
	}

	/**
	 * Adds the commerce data integration process log to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessLog the commerce data integration process log
	 * @return the commerce data integration process log that was added
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog addCommerceDataIntegrationProcessLog(
			com.liferay.commerce.data.integration.model.
				CommerceDataIntegrationProcessLog
					commerceDataIntegrationProcessLog) {

		return _commerceDataIntegrationProcessLogLocalService.
			addCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLog);
	}

	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status,
				java.util.Date startDate, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.
			addCommerceDataIntegrationProcessLog(
				userId, commerceDataIntegrationProcessId, error, output, status,
				startDate, endDate);
	}

	/**
	 * Creates a new commerce data integration process log with the primary key. Does not add the commerce data integration process log to the database.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key for the new commerce data integration process log
	 * @return the new commerce data integration process log
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
			createCommerceDataIntegrationProcessLog(
				long commerceDataIntegrationProcessLogId) {

		return _commerceDataIntegrationProcessLogLocalService.
			createCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLogId);
	}

	/**
	 * Deletes the commerce data integration process log from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessLog the commerce data integration process log
	 * @return the commerce data integration process log that was removed
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
			deleteCommerceDataIntegrationProcessLog(
				com.liferay.commerce.data.integration.model.
					CommerceDataIntegrationProcessLog
						commerceDataIntegrationProcessLog) {

		return _commerceDataIntegrationProcessLogLocalService.
			deleteCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLog);
	}

	/**
	 * Deletes the commerce data integration process log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the commerce data integration process log
	 * @return the commerce data integration process log that was removed
	 * @throws PortalException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
				deleteCommerceDataIntegrationProcessLog(
					long commerceDataIntegrationProcessLogId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.
			deleteCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLogId);
	}

	@Override
	public void deleteCommerceDataIntegrationProcessLogs(
		long commerceDataIntegrationProcessId) {

		_commerceDataIntegrationProcessLogLocalService.
			deleteCommerceDataIntegrationProcessLogs(
				commerceDataIntegrationProcessId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceDataIntegrationProcessLogLocalService.dynamicQuery();
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

		return _commerceDataIntegrationProcessLogLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceDataIntegrationProcessLogLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceDataIntegrationProcessLogLocalService.dynamicQuery(
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

		return _commerceDataIntegrationProcessLogLocalService.dynamicQueryCount(
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

		return _commerceDataIntegrationProcessLogLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
			fetchCommerceDataIntegrationProcessLog(
				long commerceDataIntegrationProcessLogId) {

		return _commerceDataIntegrationProcessLogLocalService.
			fetchCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLogId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceDataIntegrationProcessLogLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce data integration process log with the primary key.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the commerce data integration process log
	 * @return the commerce data integration process log
	 * @throws PortalException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog getCommerceDataIntegrationProcessLog(
				long commerceDataIntegrationProcessLogId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.
			getCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLogId);
	}

	/**
	 * Returns a range of all the commerce data integration process logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @return the range of commerce data integration process logs
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcessLog>
				getCommerceDataIntegrationProcessLogs(int start, int end) {

		return _commerceDataIntegrationProcessLogLocalService.
			getCommerceDataIntegrationProcessLogs(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcessLog>
				getCommerceDataIntegrationProcessLogs(
					long commerceDataIntegrationProcessId, int start, int end) {

		return _commerceDataIntegrationProcessLogLocalService.
			getCommerceDataIntegrationProcessLogs(
				commerceDataIntegrationProcessId, start, end);
	}

	/**
	 * Returns the number of commerce data integration process logs.
	 *
	 * @return the number of commerce data integration process logs
	 */
	@Override
	public int getCommerceDataIntegrationProcessLogsCount() {
		return _commerceDataIntegrationProcessLogLocalService.
			getCommerceDataIntegrationProcessLogsCount();
	}

	@Override
	public int getCommerceDataIntegrationProcessLogsCount(
		long commerceDataIntegrationProcessId) {

		return _commerceDataIntegrationProcessLogLocalService.
			getCommerceDataIntegrationProcessLogsCount(
				commerceDataIntegrationProcessId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceDataIntegrationProcessLogLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDataIntegrationProcessLogLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce data integration process log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessLog the commerce data integration process log
	 * @return the commerce data integration process log that was updated
	 */
	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
			updateCommerceDataIntegrationProcessLog(
				com.liferay.commerce.data.integration.model.
					CommerceDataIntegrationProcessLog
						commerceDataIntegrationProcessLog) {

		return _commerceDataIntegrationProcessLogLocalService.
			updateCommerceDataIntegrationProcessLog(
				commerceDataIntegrationProcessLog);
	}

	@Override
	public com.liferay.commerce.data.integration.model.
		CommerceDataIntegrationProcessLog
				updateCommerceDataIntegrationProcessLog(
					long cDataIntegrationProcessLogId, String error,
					String output, int status, java.util.Date endDate)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessLogLocalService.
			updateCommerceDataIntegrationProcessLog(
				cDataIntegrationProcessLogId, error, output, status, endDate);
	}

	@Override
	public CommerceDataIntegrationProcessLogLocalService getWrappedService() {
		return _commerceDataIntegrationProcessLogLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceDataIntegrationProcessLogLocalService
			commerceDataIntegrationProcessLogLocalService) {

		_commerceDataIntegrationProcessLogLocalService =
			commerceDataIntegrationProcessLogLocalService;
	}

	private CommerceDataIntegrationProcessLogLocalService
		_commerceDataIntegrationProcessLogLocalService;

}