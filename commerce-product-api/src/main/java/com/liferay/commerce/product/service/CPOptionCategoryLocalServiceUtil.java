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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CPOptionCategory. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPOptionCategoryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CPOptionCategoryLocalService
 * @generated
 */
@ProviderType
public class CPOptionCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPOptionCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cp option category to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpOptionCategory the cp option category
	 * @return the cp option category that was added
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
		addCPOptionCategory(
			com.liferay.commerce.product.model.CPOptionCategory
				cpOptionCategory) {

		return getService().addCPOptionCategory(cpOptionCategory);
	}

	public static com.liferay.commerce.product.model.CPOptionCategory
			addCPOptionCategory(
				long userId, java.util.Map<java.util.Locale, String> titleMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				double priority, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPOptionCategory(
			userId, titleMap, descriptionMap, priority, key, serviceContext);
	}

	/**
	 * Creates a new cp option category with the primary key. Does not add the cp option category to the database.
	 *
	 * @param CPOptionCategoryId the primary key for the new cp option category
	 * @return the new cp option category
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
		createCPOptionCategory(long CPOptionCategoryId) {

		return getService().createCPOptionCategory(CPOptionCategoryId);
	}

	public static void deleteCPOptionCategories(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPOptionCategories(companyId);
	}

	/**
	 * Deletes the cp option category from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpOptionCategory the cp option category
	 * @return the cp option category that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
			deleteCPOptionCategory(
				com.liferay.commerce.product.model.CPOptionCategory
					cpOptionCategory)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPOptionCategory(cpOptionCategory);
	}

	/**
	 * Deletes the cp option category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPOptionCategoryId the primary key of the cp option category
	 * @return the cp option category that was removed
	 * @throws PortalException if a cp option category with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
			deleteCPOptionCategory(long CPOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPOptionCategory(CPOptionCategoryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.product.model.CPOptionCategory
		fetchCPOptionCategory(long CPOptionCategoryId) {

		return getService().fetchCPOptionCategory(CPOptionCategoryId);
	}

	public static com.liferay.commerce.product.model.CPOptionCategory
		fetchCPOptionCategory(long companyId, String key) {

		return getService().fetchCPOptionCategory(companyId, key);
	}

	/**
	 * Returns the cp option category with the matching UUID and company.
	 *
	 * @param uuid the cp option category's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
		fetchCPOptionCategoryByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchCPOptionCategoryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the cp option categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of cp option categories
	 */
	public static java.util.List
		<com.liferay.commerce.product.model.CPOptionCategory>
			getCPOptionCategories(int start, int end) {

		return getService().getCPOptionCategories(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPOptionCategory>
			getCPOptionCategories(long companyId, int start, int end) {

		return getService().getCPOptionCategories(companyId, start, end);
	}

	/**
	 * Returns the number of cp option categories.
	 *
	 * @return the number of cp option categories
	 */
	public static int getCPOptionCategoriesCount() {
		return getService().getCPOptionCategoriesCount();
	}

	/**
	 * Returns the cp option category with the primary key.
	 *
	 * @param CPOptionCategoryId the primary key of the cp option category
	 * @return the cp option category
	 * @throws PortalException if a cp option category with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
			getCPOptionCategory(long CPOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPOptionCategory(CPOptionCategoryId);
	}

	public static com.liferay.commerce.product.model.CPOptionCategory
			getCPOptionCategory(long companyId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPOptionCategory(companyId, key);
	}

	/**
	 * Returns the cp option category with the matching UUID and company.
	 *
	 * @param uuid the cp option category's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cp option category
	 * @throws PortalException if a matching cp option category could not be found
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
			getCPOptionCategoryByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPOptionCategoryByUuidAndCompanyId(
			uuid, companyId);
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

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPOptionCategory>
				searchCPOptionCategories(
					long companyId, String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPOptionCategories(
			companyId, keywords, start, end, sort);
	}

	/**
	 * Updates the cp option category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpOptionCategory the cp option category
	 * @return the cp option category that was updated
	 */
	public static com.liferay.commerce.product.model.CPOptionCategory
		updateCPOptionCategory(
			com.liferay.commerce.product.model.CPOptionCategory
				cpOptionCategory) {

		return getService().updateCPOptionCategory(cpOptionCategory);
	}

	public static com.liferay.commerce.product.model.CPOptionCategory
			updateCPOptionCategory(
				long cpOptionCategoryId,
				java.util.Map<java.util.Locale, String> titleMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				double priority, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPOptionCategory(
			cpOptionCategoryId, titleMap, descriptionMap, priority, key);
	}

	public static CPOptionCategoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPOptionCategoryLocalService, CPOptionCategoryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPOptionCategoryLocalService.class);

		ServiceTracker
			<CPOptionCategoryLocalService, CPOptionCategoryLocalService>
				serviceTracker =
					new ServiceTracker
						<CPOptionCategoryLocalService,
						 CPOptionCategoryLocalService>(
							 bundle.getBundleContext(),
							 CPOptionCategoryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}