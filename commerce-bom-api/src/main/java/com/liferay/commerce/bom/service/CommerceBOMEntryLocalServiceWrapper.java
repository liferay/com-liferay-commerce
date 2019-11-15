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
 * Provides a wrapper for {@link CommerceBOMEntryLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryLocalService
 * @generated
 */
@ProviderType
public class CommerceBOMEntryLocalServiceWrapper
	implements CommerceBOMEntryLocalService,
		ServiceWrapper<CommerceBOMEntryLocalService> {
	public CommerceBOMEntryLocalServiceWrapper(
		CommerceBOMEntryLocalService commerceBOMEntryLocalService) {
		_commerceBOMEntryLocalService = commerceBOMEntryLocalService;
	}

	/**
	* Adds the commerce bom entry to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMEntry the commerce bom entry
	* @return the commerce bom entry that was added
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry addCommerceBOMEntry(
		com.liferay.commerce.bom.model.CommerceBOMEntry commerceBOMEntry) {
		return _commerceBOMEntryLocalService.addCommerceBOMEntry(commerceBOMEntry);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry addCommerceBOMEntry(
		long userId, int number, String cpInstanceUuid, long cProductId,
		long commerceBOMDefinitionId, double positionX, double positionY,
		double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.addCommerceBOMEntry(userId,
			number, cpInstanceUuid, cProductId, commerceBOMDefinitionId,
			positionX, positionY, radius);
	}

	/**
	* Creates a new commerce bom entry with the primary key. Does not add the commerce bom entry to the database.
	*
	* @param commerceBOMEntryId the primary key for the new commerce bom entry
	* @return the new commerce bom entry
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry createCommerceBOMEntry(
		long commerceBOMEntryId) {
		return _commerceBOMEntryLocalService.createCommerceBOMEntry(commerceBOMEntryId);
	}

	/**
	* Deletes the commerce bom entry from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMEntry the commerce bom entry
	* @return the commerce bom entry that was removed
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry deleteCommerceBOMEntry(
		com.liferay.commerce.bom.model.CommerceBOMEntry commerceBOMEntry) {
		return _commerceBOMEntryLocalService.deleteCommerceBOMEntry(commerceBOMEntry);
	}

	/**
	* Deletes the commerce bom entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMEntryId the primary key of the commerce bom entry
	* @return the commerce bom entry that was removed
	* @throws PortalException if a commerce bom entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry deleteCommerceBOMEntry(
		long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.deleteCommerceBOMEntry(commerceBOMEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceBOMEntryLocalService.dynamicQuery();
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
		return _commerceBOMEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBOMEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBOMEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _commerceBOMEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commerceBOMEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry fetchCommerceBOMEntry(
		long commerceBOMEntryId) {
		return _commerceBOMEntryLocalService.fetchCommerceBOMEntry(commerceBOMEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceBOMEntryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the commerce bom entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.bom.model.impl.CommerceBOMEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom entries
	* @param end the upper bound of the range of commerce bom entries (not inclusive)
	* @return the range of commerce bom entries
	*/
	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry> getCommerceBOMEntries(
		int start, int end) {
		return _commerceBOMEntryLocalService.getCommerceBOMEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry> getCommerceBOMEntries(
		long commerceBOMDefinitionId, int start, int end) {
		return _commerceBOMEntryLocalService.getCommerceBOMEntries(commerceBOMDefinitionId,
			start, end);
	}

	/**
	* Returns the number of commerce bom entries.
	*
	* @return the number of commerce bom entries
	*/
	@Override
	public int getCommerceBOMEntriesCount() {
		return _commerceBOMEntryLocalService.getCommerceBOMEntriesCount();
	}

	@Override
	public int getCommerceBOMEntriesCount(long commerceBOMDefinitionId) {
		return _commerceBOMEntryLocalService.getCommerceBOMEntriesCount(commerceBOMDefinitionId);
	}

	/**
	* Returns the commerce bom entry with the primary key.
	*
	* @param commerceBOMEntryId the primary key of the commerce bom entry
	* @return the commerce bom entry
	* @throws PortalException if a commerce bom entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry getCommerceBOMEntry(
		long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.getCommerceBOMEntry(commerceBOMEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceBOMEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce bom entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMEntry the commerce bom entry
	* @return the commerce bom entry that was updated
	*/
	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry updateCommerceBOMEntry(
		com.liferay.commerce.bom.model.CommerceBOMEntry commerceBOMEntry) {
		return _commerceBOMEntryLocalService.updateCommerceBOMEntry(commerceBOMEntry);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry updateCommerceBOMEntry(
		long commerceBOMEntryId, int number, String cpInstanceUuid,
		long cProductId, double positionX, double positionY, double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMEntryLocalService.updateCommerceBOMEntry(commerceBOMEntryId,
			number, cpInstanceUuid, cProductId, positionX, positionY, radius);
	}

	@Override
	public CommerceBOMEntryLocalService getWrappedService() {
		return _commerceBOMEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMEntryLocalService commerceBOMEntryLocalService) {
		_commerceBOMEntryLocalService = commerceBOMEntryLocalService;
	}

	private CommerceBOMEntryLocalService _commerceBOMEntryLocalService;
}