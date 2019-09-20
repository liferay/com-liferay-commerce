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

package com.liferay.commerce.payment.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommercePaymentMethodGroupRel. This utility wraps
 * <code>com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelLocalService
 * @generated
 */
public class CommercePaymentMethodGroupRelLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePaymentMethodGroupRelLocalServiceUtil} to access the commerce payment method group rel local service. Add custom service methods to <code>com.liferay.commerce.payment.service.impl.CommercePaymentMethodGroupRelLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.model.CommerceAddressRestriction
			addCommerceAddressRestriction(
				long commercePaymentMethodGroupRelId, long commerceCountryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceAddressRestriction(
			commercePaymentMethodGroupRelId, commerceCountryId, serviceContext);
	}

	/**
	 * Adds the commerce payment method group rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRel the commerce payment method group rel
	 * @return the commerce payment method group rel that was added
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
			addCommercePaymentMethodGroupRel(
				com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
					commercePaymentMethodGroupRel) {

		return getService().addCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRel);
	}

	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				addCommercePaymentMethodGroupRel(
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.io.File imageFile, String engineKey,
					java.util.Map<String, String> engineParameterMap,
					double priority, boolean active,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePaymentMethodGroupRel(
			nameMap, descriptionMap, imageFile, engineKey, engineParameterMap,
			priority, active, serviceContext);
	}

	/**
	 * Creates a new commerce payment method group rel with the primary key. Does not add the commerce payment method group rel to the database.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key for the new commerce payment method group rel
	 * @return the new commerce payment method group rel
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
			createCommercePaymentMethodGroupRel(
				long commercePaymentMethodGroupRelId) {

		return getService().createCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRelId);
	}

	public static void deleteCommerceAddressRestriction(
			long commerceAddressRestrictionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceAddressRestriction(
			commerceAddressRestrictionId);
	}

	/**
	 * Deletes the commerce payment method group rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRel the commerce payment method group rel
	 * @return the commerce payment method group rel that was removed
	 * @throws PortalException
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				deleteCommercePaymentMethodGroupRel(
					com.liferay.commerce.payment.model.
						CommercePaymentMethodGroupRel
							commercePaymentMethodGroupRel)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRel);
	}

	/**
	 * Deletes the commerce payment method group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel that was removed
	 * @throws PortalException if a commerce payment method group rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				deleteCommercePaymentMethodGroupRel(
					long commercePaymentMethodGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRelId);
	}

	public static void deleteCommercePaymentMethodGroupRels(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommercePaymentMethodGroupRels(groupId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
			fetchCommercePaymentMethodGroupRel(
				long commercePaymentMethodGroupRelId) {

		return getService().fetchCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.liferay.commerce.model.CommerceAddressRestriction>
			getCommerceAddressRestrictions(
				long commercePaymentMethodGroupRelId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceAddressRestriction>
						orderByComparator) {

		return getService().getCommerceAddressRestrictions(
			commercePaymentMethodGroupRelId, start, end, orderByComparator);
	}

	public static int getCommerceAddressRestrictionsCount(
		long commercePaymentMethodGroupRelId) {

		return getService().getCommerceAddressRestrictionsCount(
			commercePaymentMethodGroupRelId);
	}

	/**
	 * Returns the commerce payment method group rel with the primary key.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel
	 * @throws PortalException if a commerce payment method group rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				getCommercePaymentMethodGroupRel(
					long commercePaymentMethodGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRelId);
	}

	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				getCommercePaymentMethodGroupRel(long groupId, String engineKey)
			throws com.liferay.commerce.payment.exception.
				NoSuchPaymentMethodGroupRelException {

		return getService().getCommercePaymentMethodGroupRel(
			groupId, engineKey);
	}

	/**
	 * Returns a range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of commerce payment method group rels
	 */
	public static java.util.List
		<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(int start, int end) {

		return getService().getCommercePaymentMethodGroupRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(long groupId) {

		return getService().getCommercePaymentMethodGroupRels(groupId);
	}

	public static java.util.List
		<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(long groupId, boolean active) {

		return getService().getCommercePaymentMethodGroupRels(groupId, active);
	}

	public static java.util.List
		<com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel>
			getCommercePaymentMethodGroupRels(
				long groupId, long commerceCountryId, boolean active) {

		return getService().getCommercePaymentMethodGroupRels(
			groupId, commerceCountryId, active);
	}

	/**
	 * Returns the number of commerce payment method group rels.
	 *
	 * @return the number of commerce payment method group rels
	 */
	public static int getCommercePaymentMethodGroupRelsCount() {
		return getService().getCommercePaymentMethodGroupRelsCount();
	}

	public static int getCommercePaymentMethodGroupRelsCount(
		long groupId, boolean active) {

		return getService().getCommercePaymentMethodGroupRelsCount(
			groupId, active);
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

	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				setActive(long commercePaymentMethodGroupRelId, boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().setActive(commercePaymentMethodGroupRelId, active);
	}

	/**
	 * Updates the commerce payment method group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRel the commerce payment method group rel
	 * @return the commerce payment method group rel that was updated
	 */
	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
			updateCommercePaymentMethodGroupRel(
				com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
					commercePaymentMethodGroupRel) {

		return getService().updateCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRel);
	}

	public static
		com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel
				updateCommercePaymentMethodGroupRel(
					long commercePaymentMethodGroupRelId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.io.File imageFile,
					java.util.Map<String, String> engineParameterMap,
					double priority, boolean active,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommercePaymentMethodGroupRel(
			commercePaymentMethodGroupRelId, nameMap, descriptionMap, imageFile,
			engineParameterMap, priority, active, serviceContext);
	}

	public static CommercePaymentMethodGroupRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePaymentMethodGroupRelLocalService,
		 CommercePaymentMethodGroupRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePaymentMethodGroupRelLocalService.class);

		ServiceTracker
			<CommercePaymentMethodGroupRelLocalService,
			 CommercePaymentMethodGroupRelLocalService> serviceTracker =
				new ServiceTracker
					<CommercePaymentMethodGroupRelLocalService,
					 CommercePaymentMethodGroupRelLocalService>(
						 bundle.getBundleContext(),
						 CommercePaymentMethodGroupRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}