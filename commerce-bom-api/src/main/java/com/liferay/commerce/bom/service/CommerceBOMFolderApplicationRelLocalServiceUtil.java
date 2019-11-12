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

package com.liferay.commerce.bom.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceBOMFolderApplicationRel. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelLocalService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderApplicationRelLocalServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce bom folder application rel to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	* @return the commerce bom folder application rel that was added
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
		com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		return getService()
				   .addCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRel);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
		long userId, long commerceBOMFolderId, long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMFolderApplicationRel(userId,
			commerceBOMFolderId, commerceApplicationModelId);
	}

	/**
	* Creates a new commerce bom folder application rel with the primary key. Does not add the commerce bom folder application rel to the database.
	*
	* @param commerceBOMFolderApplicationRelId the primary key for the new commerce bom folder application rel
	* @return the new commerce bom folder application rel
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel createCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId) {
		return getService()
				   .createCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	/**
	* Deletes the commerce bom folder application rel from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	* @return the commerce bom folder application rel that was removed
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel deleteCommerceBOMFolderApplicationRel(
		com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		return getService()
				   .deleteCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRel);
	}

	/**
	* Deletes the commerce bom folder application rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel that was removed
	* @throws PortalException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel deleteCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	public static void deleteCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId) {
		getService()
			.deleteCommerceBOMFolderApplicationRelsByCAMId(commerceApplicationModelId);
	}

	public static void deleteCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		getService()
			.deleteCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(commerceBOMFolderId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel fetchCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId) {
		return getService()
				   .fetchCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce bom folder application rel with the primary key.
	*
	* @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	* @return the commerce bom folder application rel
	* @throws PortalException if a commerce bom folder application rel with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel getCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	/**
	* Returns a range of all the commerce bom folder application rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folder application rels
	* @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	* @return the range of commerce bom folder application rels
	*/
	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRels(
		int start, int end) {
		return getService().getCommerceBOMFolderApplicationRels(start, end);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId, int start, int end) {
		return getService()
				   .getCommerceBOMFolderApplicationRelsByCAMId(commerceApplicationModelId,
			start, end);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return getService()
				   .getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(commerceBOMFolderId,
			start, end);
	}

	/**
	* Returns the number of commerce bom folder application rels.
	*
	* @return the number of commerce bom folder application rels
	*/
	public static int getCommerceBOMFolderApplicationRelsCount() {
		return getService().getCommerceBOMFolderApplicationRelsCount();
	}

	public static int getCommerceBOMFolderApplicationRelsCountByCAMId(
		long commerceApplicationModelId) {
		return getService()
				   .getCommerceBOMFolderApplicationRelsCountByCAMId(commerceApplicationModelId);
	}

	public static int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return getService()
				   .getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(commerceBOMFolderId);
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
	* Updates the commerce bom folder application rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	* @return the commerce bom folder application rel that was updated
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel updateCommerceBOMFolderApplicationRel(
		com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		return getService()
				   .updateCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRel);
	}

	public static CommerceBOMFolderApplicationRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderApplicationRelLocalService, CommerceBOMFolderApplicationRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderApplicationRelLocalService.class);

		ServiceTracker<CommerceBOMFolderApplicationRelLocalService, CommerceBOMFolderApplicationRelLocalService> serviceTracker =
			new ServiceTracker<CommerceBOMFolderApplicationRelLocalService, CommerceBOMFolderApplicationRelLocalService>(bundle.getBundleContext(),
				CommerceBOMFolderApplicationRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}