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

package com.liferay.commerce.shipping.engine.fixed.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceShippingFixedOption. This utility wraps
 * <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionLocalService
 * @generated
 */
public class CommerceShippingFixedOptionLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.shipping.engine.fixed.service.impl.CommerceShippingFixedOptionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce shipping fixed option to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOption the commerce shipping fixed option
	 * @return the commerce shipping fixed option that was added
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption addCommerceShippingFixedOption(
				com.liferay.commerce.shipping.engine.fixed.model.
					CommerceShippingFixedOption commerceShippingFixedOption) {

		return getService().addCommerceShippingFixedOption(
			commerceShippingFixedOption);
	}

	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption addCommerceShippingFixedOption(
					long commerceShippingMethodId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceShippingFixedOption(
			commerceShippingMethodId, nameMap, descriptionMap, amount, priority,
			serviceContext);
	}

	/**
	 * Creates a new commerce shipping fixed option with the primary key. Does not add the commerce shipping fixed option to the database.
	 *
	 * @param commerceShippingFixedOptionId the primary key for the new commerce shipping fixed option
	 * @return the new commerce shipping fixed option
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption createCommerceShippingFixedOption(
				long commerceShippingFixedOptionId) {

		return getService().createCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	/**
	 * Deletes the commerce shipping fixed option from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOption the commerce shipping fixed option
	 * @return the commerce shipping fixed option that was removed
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption deleteCommerceShippingFixedOption(
				com.liferay.commerce.shipping.engine.fixed.model.
					CommerceShippingFixedOption commerceShippingFixedOption) {

		return getService().deleteCommerceShippingFixedOption(
			commerceShippingFixedOption);
	}

	/**
	 * Deletes the commerce shipping fixed option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOptionId the primary key of the commerce shipping fixed option
	 * @return the commerce shipping fixed option that was removed
	 * @throws PortalException if a commerce shipping fixed option with the primary key could not be found
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption deleteCommerceShippingFixedOption(
					long commerceShippingFixedOptionId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	public static void deleteCommerceShippingFixedOptions(
		long commerceShippingMethodId) {

		getService().deleteCommerceShippingFixedOptions(
			commerceShippingMethodId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption fetchCommerceShippingFixedOption(
				long commerceShippingFixedOptionId) {

		return getService().fetchCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce shipping fixed option with the primary key.
	 *
	 * @param commerceShippingFixedOptionId the primary key of the commerce shipping fixed option
	 * @return the commerce shipping fixed option
	 * @throws PortalException if a commerce shipping fixed option with the primary key could not be found
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption getCommerceShippingFixedOption(
					long commerceShippingFixedOptionId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	/**
	 * Returns a range of all the commerce shipping fixed options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce shipping fixed options
	 * @param end the upper bound of the range of commerce shipping fixed options (not inclusive)
	 * @return the range of commerce shipping fixed options
	 */
	public static java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
				int start, int end) {

		return getService().getCommerceShippingFixedOptions(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
				long commerceShippingMethodId, int start, int end) {

		return getService().getCommerceShippingFixedOptions(
			commerceShippingMethodId, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
				long commerceShippingMethodId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.shipping.engine.fixed.model.
						CommerceShippingFixedOption> orderByComparator) {

		return getService().getCommerceShippingFixedOptions(
			commerceShippingMethodId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce shipping fixed options.
	 *
	 * @return the number of commerce shipping fixed options
	 */
	public static int getCommerceShippingFixedOptionsCount() {
		return getService().getCommerceShippingFixedOptionsCount();
	}

	public static int getCommerceShippingFixedOptionsCount(
		long commerceShippingMethodId) {

		return getService().getCommerceShippingFixedOptionsCount(
			commerceShippingMethodId);
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
	 * Updates the commerce shipping fixed option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceShippingFixedOption the commerce shipping fixed option
	 * @return the commerce shipping fixed option that was updated
	 */
	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption updateCommerceShippingFixedOption(
				com.liferay.commerce.shipping.engine.fixed.model.
					CommerceShippingFixedOption commerceShippingFixedOption) {

		return getService().updateCommerceShippingFixedOption(
			commerceShippingFixedOption);
	}

	public static
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption updateCommerceShippingFixedOption(
					long commerceShippingFixedOptionId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceShippingFixedOption(
			commerceShippingFixedOptionId, nameMap, descriptionMap, amount,
			priority);
	}

	public static CommerceShippingFixedOptionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceShippingFixedOptionLocalService,
		 CommerceShippingFixedOptionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceShippingFixedOptionLocalService.class);

		ServiceTracker
			<CommerceShippingFixedOptionLocalService,
			 CommerceShippingFixedOptionLocalService> serviceTracker =
				new ServiceTracker
					<CommerceShippingFixedOptionLocalService,
					 CommerceShippingFixedOptionLocalService>(
						 bundle.getBundleContext(),
						 CommerceShippingFixedOptionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}