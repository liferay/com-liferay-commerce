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

package com.liferay.commerce.account.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceAccountOrganizationRel. This utility wraps
 * <code>com.liferay.commerce.account.service.impl.CommerceAccountOrganizationRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceAccountOrganizationRelLocalService
 * @generated
 */
public class CommerceAccountOrganizationRelLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.account.service.impl.CommerceAccountOrganizationRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce account organization rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountOrganizationRel the commerce account organization rel
	 * @return the commerce account organization rel that was added
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
			addCommerceAccountOrganizationRel(
				com.liferay.commerce.account.model.
					CommerceAccountOrganizationRel
						commerceAccountOrganizationRel) {

		return getService().addCommerceAccountOrganizationRel(
			commerceAccountOrganizationRel);
	}

	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
				addCommerceAccountOrganizationRel(
					long commerceAccountId, long organizationId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceAccountOrganizationRel(
			commerceAccountId, organizationId, serviceContext);
	}

	public static void addCommerceAccountOrganizationRels(
			long commerceAccountId, long[] organizationIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().addCommerceAccountOrganizationRels(
			commerceAccountId, organizationIds, serviceContext);
	}

	/**
	 * Creates a new commerce account organization rel with the primary key. Does not add the commerce account organization rel to the database.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key for the new commerce account organization rel
	 * @return the new commerce account organization rel
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
			createCommerceAccountOrganizationRel(
				com.liferay.commerce.account.service.persistence.
					CommerceAccountOrganizationRelPK
						commerceAccountOrganizationRelPK) {

		return getService().createCommerceAccountOrganizationRel(
			commerceAccountOrganizationRelPK);
	}

	/**
	 * Deletes the commerce account organization rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountOrganizationRel the commerce account organization rel
	 * @return the commerce account organization rel that was removed
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
			deleteCommerceAccountOrganizationRel(
				com.liferay.commerce.account.model.
					CommerceAccountOrganizationRel
						commerceAccountOrganizationRel) {

		return getService().deleteCommerceAccountOrganizationRel(
			commerceAccountOrganizationRel);
	}

	/**
	 * Deletes the commerce account organization rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	 * @return the commerce account organization rel that was removed
	 * @throws PortalException if a commerce account organization rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
				deleteCommerceAccountOrganizationRel(
					com.liferay.commerce.account.service.persistence.
						CommerceAccountOrganizationRelPK
							commerceAccountOrganizationRelPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceAccountOrganizationRel(
			commerceAccountOrganizationRelPK);
	}

	public static void deleteCommerceAccountOrganizationRels(
			long commerceAccountId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceAccountOrganizationRels(
			commerceAccountId, organizationIds);
	}

	public static void deleteCommerceAccountOrganizationRelsByCommerceAccountId(
		long commerceAccountId) {

		getService().deleteCommerceAccountOrganizationRelsByCommerceAccountId(
			commerceAccountId);
	}

	public static void deleteCommerceAccountOrganizationRelsByOrganizationId(
		long organizationId) {

		getService().deleteCommerceAccountOrganizationRelsByOrganizationId(
			organizationId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
			fetchCommerceAccountOrganizationRel(
				com.liferay.commerce.account.service.persistence.
					CommerceAccountOrganizationRelPK
						commerceAccountOrganizationRelPK) {

		return getService().fetchCommerceAccountOrganizationRel(
			commerceAccountOrganizationRelPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce account organization rel with the primary key.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	 * @return the commerce account organization rel
	 * @throws PortalException if a commerce account organization rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
				getCommerceAccountOrganizationRel(
					com.liferay.commerce.account.service.persistence.
						CommerceAccountOrganizationRelPK
							commerceAccountOrganizationRelPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceAccountOrganizationRel(
			commerceAccountOrganizationRelPK);
	}

	/**
	 * Returns a range of all the commerce account organization rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @return the range of commerce account organization rels
	 */
	public static java.util.List
		<com.liferay.commerce.account.model.CommerceAccountOrganizationRel>
			getCommerceAccountOrganizationRels(int start, int end) {

		return getService().getCommerceAccountOrganizationRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.account.model.CommerceAccountOrganizationRel>
			getCommerceAccountOrganizationRels(long commerceAccountId) {

		return getService().getCommerceAccountOrganizationRels(
			commerceAccountId);
	}

	public static java.util.List
		<com.liferay.commerce.account.model.CommerceAccountOrganizationRel>
			getCommerceAccountOrganizationRels(
				long commerceAccountId, int start, int end) {

		return getService().getCommerceAccountOrganizationRels(
			commerceAccountId, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.account.model.CommerceAccountOrganizationRel>
			getCommerceAccountOrganizationRelsByOrganizationId(
				long organizationId, int start, int end) {

		return getService().getCommerceAccountOrganizationRelsByOrganizationId(
			organizationId, start, end);
	}

	public static int getCommerceAccountOrganizationRelsByOrganizationIdCount(
		long organizationId) {

		return getService().
			getCommerceAccountOrganizationRelsByOrganizationIdCount(
				organizationId);
	}

	/**
	 * Returns the number of commerce account organization rels.
	 *
	 * @return the number of commerce account organization rels
	 */
	public static int getCommerceAccountOrganizationRelsCount() {
		return getService().getCommerceAccountOrganizationRelsCount();
	}

	public static int getCommerceAccountOrganizationRelsCount(
		long commerceAccountId) {

		return getService().getCommerceAccountOrganizationRelsCount(
			commerceAccountId);
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
	 * Updates the commerce account organization rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountOrganizationRel the commerce account organization rel
	 * @return the commerce account organization rel that was updated
	 */
	public static
		com.liferay.commerce.account.model.CommerceAccountOrganizationRel
			updateCommerceAccountOrganizationRel(
				com.liferay.commerce.account.model.
					CommerceAccountOrganizationRel
						commerceAccountOrganizationRel) {

		return getService().updateCommerceAccountOrganizationRel(
			commerceAccountOrganizationRel);
	}

	public static CommerceAccountOrganizationRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceAccountOrganizationRelLocalService,
		 CommerceAccountOrganizationRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceAccountOrganizationRelLocalService.class);

		ServiceTracker
			<CommerceAccountOrganizationRelLocalService,
			 CommerceAccountOrganizationRelLocalService> serviceTracker =
				new ServiceTracker
					<CommerceAccountOrganizationRelLocalService,
					 CommerceAccountOrganizationRelLocalService>(
						 bundle.getBundleContext(),
						 CommerceAccountOrganizationRelLocalService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}