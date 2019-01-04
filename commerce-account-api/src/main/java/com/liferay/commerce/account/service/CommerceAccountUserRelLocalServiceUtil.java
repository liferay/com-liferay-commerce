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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceAccountUserRel. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountUserRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelLocalService
 * @see com.liferay.commerce.account.service.base.CommerceAccountUserRelLocalServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountUserRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountUserRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce account user rel to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRel the commerce account user rel
	* @return the commerce account user rel that was added
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel addCommerceAccountUserRel(
		com.liferay.commerce.account.model.CommerceAccountUserRel commerceAccountUserRel) {
		return getService().addCommerceAccountUserRel(commerceAccountUserRel);
	}

	public static com.liferay.commerce.account.model.CommerceAccountUserRel addCommerceAccountUserRel(
		long commerceAccountId, long commerceAccountUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAccountUserRel(commerceAccountId,
			commerceAccountUserId, serviceContext);
	}

	public static void addCommerceAccountUserRels(long commerceAccountId,
		String[] emailAddresses, long[] roleIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addCommerceAccountUserRels(commerceAccountId, emailAddresses,
			roleIds, serviceContext);
	}

	/**
	* Creates a new commerce account user rel with the primary key. Does not add the commerce account user rel to the database.
	*
	* @param commerceAccountUserRelPK the primary key for the new commerce account user rel
	* @return the new commerce account user rel
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel createCommerceAccountUserRel(
		com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK commerceAccountUserRelPK) {
		return getService()
				   .createCommerceAccountUserRel(commerceAccountUserRelPK);
	}

	/**
	* Deletes the commerce account user rel from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRel the commerce account user rel
	* @return the commerce account user rel that was removed
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel deleteCommerceAccountUserRel(
		com.liferay.commerce.account.model.CommerceAccountUserRel commerceAccountUserRel) {
		return getService().deleteCommerceAccountUserRel(commerceAccountUserRel);
	}

	/**
	* Deletes the commerce account user rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel that was removed
	* @throws PortalException if a commerce account user rel with the primary key could not be found
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel deleteCommerceAccountUserRel(
		com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceAccountUserRel(commerceAccountUserRelPK);
	}

	public static void deleteCommerceAccountUserRels(long commerceAccountId,
		long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAccountUserRels(commerceAccountId, userIds);
	}

	public static void deleteCommerceAccountUserRelsByCommerceAccountId(
		long commerceAccountId) {
		getService()
			.deleteCommerceAccountUserRelsByCommerceAccountId(commerceAccountId);
	}

	public static void deleteCommerceAccountUserRelsByCommerceAccountUserId(
		long userId) {
		getService().deleteCommerceAccountUserRelsByCommerceAccountUserId(userId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.commerce.account.model.CommerceAccountUserRel fetchCommerceAccountUserRel(
		com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK commerceAccountUserRelPK) {
		return getService().fetchCommerceAccountUserRel(commerceAccountUserRelPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce account user rel with the primary key.
	*
	* @param commerceAccountUserRelPK the primary key of the commerce account user rel
	* @return the commerce account user rel
	* @throws PortalException if a commerce account user rel with the primary key could not be found
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel getCommerceAccountUserRel(
		com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAccountUserRel(commerceAccountUserRelPK);
	}

	/**
	* Returns a range of all the commerce account user rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account user rels
	* @param end the upper bound of the range of commerce account user rels (not inclusive)
	* @return the range of commerce account user rels
	*/
	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRels(
		int start, int end) {
		return getService().getCommerceAccountUserRels(start, end);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRels(
		long commerceAccountId) {
		return getService().getCommerceAccountUserRels(commerceAccountId);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRels(
		long commerceAccountId, int start, int end) {
		return getService()
				   .getCommerceAccountUserRels(commerceAccountId, start, end);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRelsByCommerceAccountUserId(
		long commerceAccountUserId) {
		return getService()
				   .getCommerceAccountUserRelsByCommerceAccountUserId(commerceAccountUserId);
	}

	/**
	* Returns the number of commerce account user rels.
	*
	* @return the number of commerce account user rels
	*/
	public static int getCommerceAccountUserRelsCount() {
		return getService().getCommerceAccountUserRelsCount();
	}

	public static int getCommerceAccountUserRelsCount(long commerceAccountId) {
		return getService().getCommerceAccountUserRelsCount(commerceAccountId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
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

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce account user rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountUserRel the commerce account user rel
	* @return the commerce account user rel that was updated
	*/
	public static com.liferay.commerce.account.model.CommerceAccountUserRel updateCommerceAccountUserRel(
		com.liferay.commerce.account.model.CommerceAccountUserRel commerceAccountUserRel) {
		return getService().updateCommerceAccountUserRel(commerceAccountUserRel);
	}

	public static CommerceAccountUserRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountUserRelLocalService, CommerceAccountUserRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountUserRelLocalService.class);

		ServiceTracker<CommerceAccountUserRelLocalService, CommerceAccountUserRelLocalService> serviceTracker =
			new ServiceTracker<CommerceAccountUserRelLocalService, CommerceAccountUserRelLocalService>(bundle.getBundleContext(),
				CommerceAccountUserRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}