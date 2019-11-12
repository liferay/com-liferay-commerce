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
 * Provides the local service utility for CommerceBOMFolder. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderLocalService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMFolderLocalServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce bom folder to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolder the commerce bom folder
	* @return the commerce bom folder that was added
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder addCommerceBOMFolder(
		com.liferay.commerce.bom.model.CommerceBOMFolder commerceBOMFolder) {
		return getService().addCommerceBOMFolder(commerceBOMFolder);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder addCommerceBOMFolder(
		long userId, long parentCommerceBOMFolderId, String name, boolean logo,
		byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMFolder(userId, parentCommerceBOMFolderId,
			name, logo, logoBytes);
	}

	/**
	* Creates a new commerce bom folder with the primary key. Does not add the commerce bom folder to the database.
	*
	* @param commerceBOMFolderId the primary key for the new commerce bom folder
	* @return the new commerce bom folder
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder createCommerceBOMFolder(
		long commerceBOMFolderId) {
		return getService().createCommerceBOMFolder(commerceBOMFolderId);
	}

	/**
	* Deletes the commerce bom folder from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolder the commerce bom folder
	* @return the commerce bom folder that was removed
	* @throws PortalException
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder deleteCommerceBOMFolder(
		com.liferay.commerce.bom.model.CommerceBOMFolder commerceBOMFolder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceBOMFolder(commerceBOMFolder);
	}

	/**
	* Deletes the commerce bom folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderId the primary key of the commerce bom folder
	* @return the commerce bom folder that was removed
	* @throws PortalException if a commerce bom folder with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder deleteCommerceBOMFolder(
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceBOMFolder(commerceBOMFolderId);
	}

	public static void deleteCommerceBOMFolders(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceBOMFolders(companyId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.bom.model.CommerceBOMFolder fetchCommerceBOMFolder(
		long commerceBOMFolderId) {
		return getService().fetchCommerceBOMFolder(commerceBOMFolderId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce bom folder with the primary key.
	*
	* @param commerceBOMFolderId the primary key of the commerce bom folder
	* @return the commerce bom folder
	* @throws PortalException if a commerce bom folder with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder getCommerceBOMFolder(
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMFolder(commerceBOMFolderId);
	}

	/**
	* Returns a range of all the commerce bom folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of commerce bom folders
	*/
	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder> getCommerceBOMFolders(
		int start, int end) {
		return getService().getCommerceBOMFolders(start, end);
	}

	/**
	* Returns the number of commerce bom folders.
	*
	* @return the number of commerce bom folders
	*/
	public static int getCommerceBOMFoldersCount() {
		return getService().getCommerceBOMFoldersCount();
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
	* Updates the commerce bom folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolder the commerce bom folder
	* @return the commerce bom folder that was updated
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMFolder updateCommerceBOMFolder(
		com.liferay.commerce.bom.model.CommerceBOMFolder commerceBOMFolder) {
		return getService().updateCommerceBOMFolder(commerceBOMFolder);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMFolder updateCommerceBOMFolder(
		long commerceBOMFolderId, String name, boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceBOMFolder(commerceBOMFolderId, name, logo,
			logoBytes);
	}

	public static CommerceBOMFolderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderLocalService, CommerceBOMFolderLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderLocalService.class);

		ServiceTracker<CommerceBOMFolderLocalService, CommerceBOMFolderLocalService> serviceTracker =
			new ServiceTracker<CommerceBOMFolderLocalService, CommerceBOMFolderLocalService>(bundle.getBundleContext(),
				CommerceBOMFolderLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}