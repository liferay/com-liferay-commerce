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
 * Provides the local service utility for CommerceAccountGroupCommerceAccountRel. This utility wraps
 * <code>com.liferay.commerce.account.service.impl.CommerceAccountGroupCommerceAccountRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupCommerceAccountRelLocalService
 * @generated
 */
public class CommerceAccountGroupCommerceAccountRelLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.account.service.impl.CommerceAccountGroupCommerceAccountRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce account group commerce account rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupCommerceAccountRel the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was added
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				addCommerceAccountGroupCommerceAccountRel(
					com.liferay.commerce.account.model.
						CommerceAccountGroupCommerceAccountRel
							commerceAccountGroupCommerceAccountRel) {

		return getService().addCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRel);
	}

	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					addCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupId, long commerceAccountId,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupId, commerceAccountId, serviceContext);
	}

	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					addCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupId, long commerceAccountId,
						String externalReferenceCode,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupId, commerceAccountId, externalReferenceCode,
			serviceContext);
	}

	/**
	 * Creates a new commerce account group commerce account rel with the primary key. Does not add the commerce account group commerce account rel to the database.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key for the new commerce account group commerce account rel
	 * @return the new commerce account group commerce account rel
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				createCommerceAccountGroupCommerceAccountRel(
					long commerceAccountGroupCommerceAccountRelId) {

		return getService().createCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRelId);
	}

	/**
	 * Deletes the commerce account group commerce account rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupCommerceAccountRel the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was removed
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				deleteCommerceAccountGroupCommerceAccountRel(
					com.liferay.commerce.account.model.
						CommerceAccountGroupCommerceAccountRel
							commerceAccountGroupCommerceAccountRel) {

		return getService().deleteCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRel);
	}

	/**
	 * Deletes the commerce account group commerce account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was removed
	 * @throws PortalException if a commerce account group commerce account rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					deleteCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupCommerceAccountRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRelId);
	}

	public static void
		deleteCommerceAccountGroupCommerceAccountRelByCAccountGroupId(
			long commerceAccountGroupId) {

		getService().
			deleteCommerceAccountGroupCommerceAccountRelByCAccountGroupId(
				commerceAccountGroupId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				fetchCommerceAccountGroupCommerceAccountRel(
					long commerceAccountGroupCommerceAccountRelId) {

		return getService().fetchCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRelId);
	}

	/**
	 * Returns the commerce account group commerce account rel with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce account group commerce account rel's external reference code
	 * @return the matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				fetchCommerceAccountGroupCommerceAccountRelByReferenceCode(
					long companyId, String externalReferenceCode) {

		return getService().
			fetchCommerceAccountGroupCommerceAccountRelByReferenceCode(
				companyId, externalReferenceCode);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce account group commerce account rel with the primary key.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel
	 * @throws PortalException if a commerce account group commerce account rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					getCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupCommerceAccountRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRelId);
	}

	/**
	 * Returns a range of all the commerce account group commerce account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @return the range of commerce account group commerce account rels
	 */
	public static java.util.List
		<com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel>
				getCommerceAccountGroupCommerceAccountRels(int start, int end) {

		return getService().getCommerceAccountGroupCommerceAccountRels(
			start, end);
	}

	public static java.util.List
		<com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel>
				getCommerceAccountGroupCommerceAccountRels(
					long commerceAccountGroupId, int start, int end) {

		return getService().getCommerceAccountGroupCommerceAccountRels(
			commerceAccountGroupId, start, end);
	}

	/**
	 * Returns the number of commerce account group commerce account rels.
	 *
	 * @return the number of commerce account group commerce account rels
	 */
	public static int getCommerceAccountGroupCommerceAccountRelsCount() {
		return getService().getCommerceAccountGroupCommerceAccountRelsCount();
	}

	public static int getCommerceAccountGroupCommerceAccountRelsCount(
		long commerceAccountGroupId) {

		return getService().getCommerceAccountGroupCommerceAccountRelsCount(
			commerceAccountGroupId);
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
	 * Updates the commerce account group commerce account rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupCommerceAccountRel the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was updated
	 */
	public static
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
				updateCommerceAccountGroupCommerceAccountRel(
					com.liferay.commerce.account.model.
						CommerceAccountGroupCommerceAccountRel
							commerceAccountGroupCommerceAccountRel) {

		return getService().updateCommerceAccountGroupCommerceAccountRel(
			commerceAccountGroupCommerceAccountRel);
	}

	public static CommerceAccountGroupCommerceAccountRelLocalService
		getService() {

		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceAccountGroupCommerceAccountRelLocalService,
		 CommerceAccountGroupCommerceAccountRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceAccountGroupCommerceAccountRelLocalService.class);

		ServiceTracker
			<CommerceAccountGroupCommerceAccountRelLocalService,
			 CommerceAccountGroupCommerceAccountRelLocalService>
				serviceTracker =
					new ServiceTracker
						<CommerceAccountGroupCommerceAccountRelLocalService,
						 CommerceAccountGroupCommerceAccountRelLocalService>(
							 bundle.getBundleContext(),
							 CommerceAccountGroupCommerceAccountRelLocalService.
								 class,
							 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}