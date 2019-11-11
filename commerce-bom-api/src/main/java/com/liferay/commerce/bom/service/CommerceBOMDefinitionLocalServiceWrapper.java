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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBOMDefinitionLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionLocalService
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionLocalServiceWrapper
	implements CommerceBOMDefinitionLocalService,
		ServiceWrapper<CommerceBOMDefinitionLocalService> {
	public CommerceBOMDefinitionLocalServiceWrapper(
		CommerceBOMDefinitionLocalService commerceBOMDefinitionLocalService) {
		_commerceBOMDefinitionLocalService = commerceBOMDefinitionLocalService;
	}

	/**
	* Adds the commerce bom definition to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was added
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition) {
		return _commerceBOMDefinitionLocalService.addCommerceBOMDefinition(commerceBOMDefinition);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
		String name, String friendlyUrl)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.addCommerceBOMDefinition(userId,
			commerceBOMFolderId, cpAttachmentFileEntryId, name, friendlyUrl);
	}

	/**
	* Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	*
	* @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	* @return the new commerce bom definition
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition createCommerceBOMDefinition(
		long commerceBOMDefinitionId) {
		return _commerceBOMDefinitionLocalService.createCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	/**
	* Deletes the commerce bom definition from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition deleteCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(commerceBOMDefinition);
	}

	/**
	* Deletes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition that was removed
	* @throws PortalException if a commerce bom definition with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition deleteCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	@Override
	public void deleteCommerceBOMDefinitions(long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceBOMDefinitionLocalService.deleteCommerceBOMDefinitions(commerceBOMFolderId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceBOMDefinitionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceBOMDefinitionLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _commerceBOMDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _commerceBOMDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceBOMDefinitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _commerceBOMDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition fetchCommerceBOMDefinition(
		long commerceBOMDefinitionId) {
		return _commerceBOMDefinitionLocalService.fetchCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceBOMDefinitionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the commerce bom definition with the primary key.
	*
	* @param commerceBOMDefinitionId the primary key of the commerce bom definition
	* @return the commerce bom definition
	* @throws PortalException if a commerce bom definition with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition getCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.getCommerceBOMDefinition(commerceBOMDefinitionId);
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
	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMDefinition> getCommerceBOMDefinitions(
		int start, int end) {
		return _commerceBOMDefinitionLocalService.getCommerceBOMDefinitions(start,
			end);
	}

	/**
	* Returns the number of commerce bom definitions.
	*
	* @return the number of commerce bom definitions
	*/
	@Override
	public int getCommerceBOMDefinitionsCount() {
		return _commerceBOMDefinitionLocalService.getCommerceBOMDefinitionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceBOMDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce bom definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMDefinition the commerce bom definition
	* @return the commerce bom definition that was updated
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		com.liferay.commerce.bom.model.CommerceBOMDefinition commerceBOMDefinition) {
		return _commerceBOMDefinitionLocalService.updateCommerceBOMDefinition(commerceBOMDefinition);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		long commerceBOMDefinitionId, long cpAttachmentFileEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionLocalService.updateCommerceBOMDefinition(commerceBOMDefinitionId,
			cpAttachmentFileEntryId, name);
	}

	@Override
	public CommerceBOMDefinitionLocalService getWrappedService() {
		return _commerceBOMDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMDefinitionLocalService commerceBOMDefinitionLocalService) {
		_commerceBOMDefinitionLocalService = commerceBOMDefinitionLocalService;
	}

	private CommerceBOMDefinitionLocalService _commerceBOMDefinitionLocalService;
}