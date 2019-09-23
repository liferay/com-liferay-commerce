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
 * Provides the local service utility for CommercePriceListAccountRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelLocalService
 * @generated
 */
public class CommercePriceListAccountRelLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce price list account rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was added
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			addCommercePriceListAccountRel(
				com.liferay.commerce.price.list.model.
					CommercePriceListAccountRel commercePriceListAccountRel) {

		return getService().addCommercePriceListAccountRel(
			commercePriceListAccountRel);
	}

	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				addCommercePriceListAccountRel(
					long commercePriceListId, long commerceAccountId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePriceListAccountRel(
			commercePriceListId, commerceAccountId, order, serviceContext);
	}

	/**
	 * Creates a new commerce price list account rel with the primary key. Does not add the commerce price list account rel to the database.
	 *
	 * @param commercePriceListAccountRelId the primary key for the new commerce price list account rel
	 * @return the new commerce price list account rel
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			createCommercePriceListAccountRel(
				long commercePriceListAccountRelId) {

		return getService().createCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	/**
	 * Deletes the commerce price list account rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws PortalException
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				deleteCommercePriceListAccountRel(
					com.liferay.commerce.price.list.model.
						CommercePriceListAccountRel commercePriceListAccountRel)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePriceListAccountRel(
			commercePriceListAccountRel);
	}

	/**
	 * Deletes the commerce price list account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws PortalException if a commerce price list account rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				deleteCommercePriceListAccountRel(
					long commercePriceListAccountRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	public static void deleteCommercePriceListAccountRels(
		long commercePriceListId) {

		getService().deleteCommercePriceListAccountRels(commercePriceListId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			fetchCommercePriceListAccountRel(
				long commercePriceListAccountRelId) {

		return getService().fetchCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			fetchCommercePriceListAccountRel(
				long commercePriceListId, long commerceAccountId) {

		return getService().fetchCommercePriceListAccountRel(
			commercePriceListId, commerceAccountId);
	}

	/**
	 * Returns the commerce price list account rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list account rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			fetchCommercePriceListAccountRelByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().fetchCommercePriceListAccountRelByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce price list account rel with the primary key.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel
	 * @throws PortalException if a commerce price list account rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				getCommercePriceListAccountRel(
					long commercePriceListAccountRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	/**
	 * Returns the commerce price list account rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list account rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list account rel
	 * @throws PortalException if a matching commerce price list account rel could not be found
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				getCommercePriceListAccountRelByUuidAndCompanyId(
					String uuid, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePriceListAccountRelByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce price list account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of commerce price list account rels
	 */
	public static java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
			getCommercePriceListAccountRels(int start, int end) {

		return getService().getCommercePriceListAccountRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
			getCommercePriceListAccountRels(long commercePriceListId) {

		return getService().getCommercePriceListAccountRels(
			commercePriceListId);
	}

	/**
	 * Returns the number of commerce price list account rels.
	 *
	 * @return the number of commerce price list account rels
	 */
	public static int getCommercePriceListAccountRelsCount() {
		return getService().getCommercePriceListAccountRelsCount();
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
	 * Updates the commerce price list account rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was updated
	 */
	public static
		com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			updateCommercePriceListAccountRel(
				com.liferay.commerce.price.list.model.
					CommercePriceListAccountRel commercePriceListAccountRel) {

		return getService().updateCommercePriceListAccountRel(
			commercePriceListAccountRel);
	}

	public static CommercePriceListAccountRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePriceListAccountRelLocalService,
		 CommercePriceListAccountRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePriceListAccountRelLocalService.class);

		ServiceTracker
			<CommercePriceListAccountRelLocalService,
			 CommercePriceListAccountRelLocalService> serviceTracker =
				new ServiceTracker
					<CommercePriceListAccountRelLocalService,
					 CommercePriceListAccountRelLocalService>(
						 bundle.getBundleContext(),
						 CommercePriceListAccountRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}