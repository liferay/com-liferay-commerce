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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceCatalog. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CommerceCatalogLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceCatalogLocalService
 * @generated
 */
public class CommerceCatalogLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceCatalogLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce catalog to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was added
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
		addCommerceCatalog(
			com.liferay.commerce.product.model.CommerceCatalog
				commerceCatalog) {

		return getService().addCommerceCatalog(commerceCatalog);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
			addCommerceCatalog(
				String name, String commerceCurrencyCode,
				String catalogDefaultLanguageId, boolean system,
				String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceCatalog(
			name, commerceCurrencyCode, catalogDefaultLanguageId, system,
			externalReferenceCode, serviceContext);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
			addCommerceCatalog(
				String name, String commerceCurrencyCode,
				String catalogDefaultLanguageId, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceCatalog(
			name, commerceCurrencyCode, catalogDefaultLanguageId,
			externalReferenceCode, serviceContext);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
			addDefaultCommerceCatalog(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addDefaultCommerceCatalog(companyId);
	}

	/**
	 * Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	 *
	 * @param commerceCatalogId the primary key for the new commerce catalog
	 * @return the new commerce catalog
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
		createCommerceCatalog(long commerceCatalogId) {

		return getService().createCommerceCatalog(commerceCatalogId);
	}

	/**
	 * Deletes the commerce catalog from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
			deleteCommerceCatalog(
				com.liferay.commerce.product.model.CommerceCatalog
					commerceCatalog)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceCatalog(commerceCatalog);
	}

	/**
	 * Deletes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws PortalException if a commerce catalog with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
			deleteCommerceCatalog(long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceCatalog(commerceCatalogId);
	}

	public static void deleteCommerceCatalogs(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceCatalogs(companyId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.product.model.CommerceCatalog
		fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalog(long commerceCatalogId) {

		return getService().fetchCommerceCatalog(commerceCatalogId);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalogByGroupId(long groupId) {

		return getService().fetchCommerceCatalogByGroupId(groupId);
	}

	/**
	 * Returns the commerce catalog with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce catalog's external reference code
	 * @return the matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalogByReferenceCode(
			long companyId, String externalReferenceCode) {

		return getService().fetchCommerceCatalogByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce catalog with the primary key.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws PortalException if a commerce catalog with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
			getCommerceCatalog(long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceCatalog(commerceCatalogId);
	}

	public static com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
			long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceCatalogGroup(commerceCatalogId);
	}

	/**
	 * Returns a range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of commerce catalogs
	 */
	public static java.util.List
		<com.liferay.commerce.product.model.CommerceCatalog>
			getCommerceCatalogs(int start, int end) {

		return getService().getCommerceCatalogs(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceCatalog>
			getCommerceCatalogs(long companyId, boolean system) {

		return getService().getCommerceCatalogs(companyId, system);
	}

	/**
	 * Returns the number of commerce catalogs.
	 *
	 * @return the number of commerce catalogs
	 */
	public static int getCommerceCatalogsCount() {
		return getService().getCommerceCatalogsCount();
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

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceCatalog>
				searchCommerceCatalogs(long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCommerceCatalogs(companyId);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceCatalog>
				searchCommerceCatalogs(
					long companyId, String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCommerceCatalogs(
			companyId, keywords, start, end, sort);
	}

	public static int searchCommerceCatalogsCount(
			long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCommerceCatalogsCount(companyId, keywords);
	}

	/**
	 * Updates the commerce catalog in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was updated
	 */
	public static com.liferay.commerce.product.model.CommerceCatalog
		updateCommerceCatalog(
			com.liferay.commerce.product.model.CommerceCatalog
				commerceCatalog) {

		return getService().updateCommerceCatalog(commerceCatalog);
	}

	public static com.liferay.commerce.product.model.CommerceCatalog
			updateCommerceCatalog(
				long commerceCatalogId, String name,
				String commerceCurrencyCode, String catalogDefaultLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceCatalog(
			commerceCatalogId, name, commerceCurrencyCode,
			catalogDefaultLanguageId);
	}

	public static CommerceCatalogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceCatalogLocalService, CommerceCatalogLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceCatalogLocalService.class);

		ServiceTracker<CommerceCatalogLocalService, CommerceCatalogLocalService>
			serviceTracker =
				new ServiceTracker
					<CommerceCatalogLocalService, CommerceCatalogLocalService>(
						bundle.getBundleContext(),
						CommerceCatalogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}