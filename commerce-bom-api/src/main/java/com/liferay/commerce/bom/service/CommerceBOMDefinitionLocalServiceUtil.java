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
 * Provides the local service utility for CommerceBOMDefinition. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionLocalService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMDefinitionLocalServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce bom definition to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was added
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition) {
		return getService().addCommerceBOMDefinition(commerceBOMDefinition);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
		String name, String friendlyUrl)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMDefinition(userId, commerceBOMFolderId,
			cpAttachmentFileEntryId, name, friendlyUrl);
	}

	/**
	* Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	*
	* @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	* @return the new commerce bom definition
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition createCommerceBOMDefinition(
		long commerceBOMDefinitionId) {
		return getService().createCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	/**
	* Deletes the commerce bom definition from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws PortalException
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition deleteCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceBOMDefinition(commerceBOMDefinition);
	}

	/**
	* Deletes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws PortalException if a commerce bom definition with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition deleteCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static void deleteCommerceBOMDefinitions(long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceBOMDefinitions(commerceBOMFolderId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.bom.model.CommerceBOMDefinition fetchCommerceBOMDefinition(
		long commerceBOMDefinitionId) {
		return getService().fetchCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the commerce bom definition with the primary key.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition
	* @throws PortalException if a commerce bom definition with the primary key could not be found
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition getCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	/**
	* Returns a range of all the commerce bom definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom definitions
	* @param end the upper bound of the range of commerce bom definitions (not inclusive)
	* @return the range of commerce bom definitions
	*/
	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMDefinition> getCommerceBOMDefinitions(
		int start, int end) {
		return getService().getCommerceBOMDefinitions(start, end);
	}

	/**
	* Returns the number of commerce bom definitions.
	*
	* @return the number of commerce bom definitions
	*/
	public static int getCommerceBOMDefinitionsCount() {
		return getService().getCommerceBOMDefinitionsCount();
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
	* Updates the commerce bom definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was updated
	*/
	public static com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition) {
		return getService().updateCommerceBOMDefinition(commerceBOMDefinition);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		long commerceBOMDefinitionId, long cpAttachmentFileEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceBOMDefinition(commerceBOMDefinitionId,
			cpAttachmentFileEntryId, name);
	}

	public static CommerceBOMDefinitionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMDefinitionLocalService, CommerceBOMDefinitionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMDefinitionLocalService.class);

		ServiceTracker<CommerceBOMDefinitionLocalService, CommerceBOMDefinitionLocalService> serviceTracker =
			new ServiceTracker<CommerceBOMDefinitionLocalService, CommerceBOMDefinitionLocalService>(bundle.getBundleContext(),
				CommerceBOMDefinitionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}