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

package com.liferay.commerce.price.list.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommercePriceListCommerceAccountGroupRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListCommerceAccountGroupRelLocalService
 * @generated
 */
public class CommercePriceListCommerceAccountGroupRelLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListCommerceAccountGroupRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce price list commerce account group rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListCommerceAccountGroupRel the commerce price list commerce account group rel
	 * @return the commerce price list commerce account group rel that was added
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			addCommercePriceListCommerceAccountGroupRel(
				com.liferay.commerce.price.list.model.
					CommercePriceListCommerceAccountGroupRel
						commercePriceListCommerceAccountGroupRel) {

		return getService().addCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRel);
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				addCommercePriceListCommerceAccountGroupRel(
					long commercePriceListId, long commerceAccountGroupId,
					int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId, order, serviceContext);
	}

	/**
	 * Creates a new commerce price list commerce account group rel with the primary key. Does not add the commerce price list commerce account group rel to the database.
	 *
	 * @param commercePriceListCommerceAccountGroupRelId the primary key for the new commerce price list commerce account group rel
	 * @return the new commerce price list commerce account group rel
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			createCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccountGroupRelId) {

		return getService().createCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	/**
	 * Deletes the commerce price list commerce account group rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListCommerceAccountGroupRel the commerce price list commerce account group rel
	 * @return the commerce price list commerce account group rel that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				deleteCommercePriceListCommerceAccountGroupRel(
					com.liferay.commerce.price.list.model.
						CommercePriceListCommerceAccountGroupRel
							commercePriceListCommerceAccountGroupRel)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRel);
	}

	/**
	 * Deletes the commerce price list commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListCommerceAccountGroupRelId the primary key of the commerce price list commerce account group rel
	 * @return the commerce price list commerce account group rel that was removed
	 * @throws PortalException if a commerce price list commerce account group rel with the primary key could not be found
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				deleteCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccountGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	public static void deleteCommercePriceListCommerceAccountGroupRels(
		long commercePriceListId) {

		getService().deleteCommercePriceListCommerceAccountGroupRels(
			commercePriceListId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			fetchCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccountGroupRelId) {

		return getService().fetchCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			fetchCommercePriceListCommerceAccountGroupRel(
				long commercePriceListId, long commerceAccountGroupId) {

		return getService().fetchCommercePriceListCommerceAccountGroupRel(
			commercePriceListId, commerceAccountGroupId);
	}

	/**
	 * Returns the commerce price list commerce account group rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list commerce account group rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list commerce account group rel, or <code>null</code> if a matching commerce price list commerce account group rel could not be found
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			fetchCommercePriceListCommerceAccountGroupRelByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().
			fetchCommercePriceListCommerceAccountGroupRelByUuidAndCompanyId(
				uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce price list commerce account group rel with the primary key.
	 *
	 * @param commercePriceListCommerceAccountGroupRelId the primary key of the commerce price list commerce account group rel
	 * @return the commerce price list commerce account group rel
	 * @throws PortalException if a commerce price list commerce account group rel with the primary key could not be found
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				getCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccountGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId);
	}

	/**
	 * Returns the commerce price list commerce account group rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list commerce account group rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list commerce account group rel
	 * @throws PortalException if a matching commerce price list commerce account group rel could not be found
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				getCommercePriceListCommerceAccountGroupRelByUuidAndCompanyId(
					String uuid, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().
			getCommercePriceListCommerceAccountGroupRelByUuidAndCompanyId(
				uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce price list commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list commerce account group rels
	 * @param end the upper bound of the range of commerce price list commerce account group rels (not inclusive)
	 * @return the range of commerce price list commerce account group rels
	 */
	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
				getCommercePriceListCommerceAccountGroupRels(
					int start, int end) {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			start, end);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
				getCommercePriceListCommerceAccountGroupRels(
					long commercePriceListId) {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
				getCommercePriceListCommerceAccountGroupRels(
					long commercePriceListId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.price.list.model.
							CommercePriceListCommerceAccountGroupRel>
								orderByComparator) {

		return getService().getCommercePriceListCommerceAccountGroupRels(
			commercePriceListId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce price list commerce account group rels.
	 *
	 * @return the number of commerce price list commerce account group rels
	 */
	public static int getCommercePriceListCommerceAccountGroupRelsCount() {
		return getService().getCommercePriceListCommerceAccountGroupRelsCount();
	}

	public static int getCommercePriceListCommerceAccountGroupRelsCount(
		long commercePriceListId) {

		return getService().getCommercePriceListCommerceAccountGroupRelsCount(
			commercePriceListId);
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
	 * Updates the commerce price list commerce account group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListCommerceAccountGroupRel the commerce price list commerce account group rel
	 * @return the commerce price list commerce account group rel that was updated
	 */
	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
			updateCommercePriceListCommerceAccountGroupRel(
				com.liferay.commerce.price.list.model.
					CommercePriceListCommerceAccountGroupRel
						commercePriceListCommerceAccountGroupRel) {

		return getService().updateCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRel);
	}

	public static com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				updateCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccountGroupRelId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommercePriceListCommerceAccountGroupRel(
			commercePriceListCommerceAccountGroupRelId, order, serviceContext);
	}

	public static CommercePriceListCommerceAccountGroupRelLocalService
		getService() {

		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePriceListCommerceAccountGroupRelLocalService,
		 CommercePriceListCommerceAccountGroupRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePriceListCommerceAccountGroupRelLocalService.class);

		ServiceTracker
			<CommercePriceListCommerceAccountGroupRelLocalService,
			 CommercePriceListCommerceAccountGroupRelLocalService>
				serviceTracker =
					new ServiceTracker
						<CommercePriceListCommerceAccountGroupRelLocalService,
						 CommercePriceListCommerceAccountGroupRelLocalService>(
							 bundle.getBundleContext(),
							 CommercePriceListCommerceAccountGroupRelLocalService.class,
							 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}