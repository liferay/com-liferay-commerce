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

package com.liferay.commerce.batch.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBatchEntryLocalService}.
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntryLocalService
 * @generated
 */
@ProviderType
public class CommerceBatchEntryLocalServiceWrapper
	implements CommerceBatchEntryLocalService,
		ServiceWrapper<CommerceBatchEntryLocalService> {
	public CommerceBatchEntryLocalServiceWrapper(
		CommerceBatchEntryLocalService commerceBatchEntryLocalService) {
		_commerceBatchEntryLocalService = commerceBatchEntryLocalService;
	}

	/**
	* Adds the commerce batch entry to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntry the commerce batch entry
	* @return the commerce batch entry that was added
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry addCommerceBatchEntry(
		com.liferay.commerce.batch.model.CommerceBatchEntry commerceBatchEntry) {
		return _commerceBatchEntryLocalService.addCommerceBatchEntry(commerceBatchEntry);
	}

	/**
	* Creates a new commerce batch entry with the primary key. Does not add the commerce batch entry to the database.
	*
	* @param commerceBatchEntryId the primary key for the new commerce batch entry
	* @return the new commerce batch entry
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry createCommerceBatchEntry(
		long commerceBatchEntryId) {
		return _commerceBatchEntryLocalService.createCommerceBatchEntry(commerceBatchEntryId);
	}

	/**
	* Deletes the commerce batch entry from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntry the commerce batch entry
	* @return the commerce batch entry that was removed
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry deleteCommerceBatchEntry(
		com.liferay.commerce.batch.model.CommerceBatchEntry commerceBatchEntry) {
		return _commerceBatchEntryLocalService.deleteCommerceBatchEntry(commerceBatchEntry);
	}

	/**
	* Deletes the commerce batch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry that was removed
	* @throws PortalException if a commerce batch entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry deleteCommerceBatchEntry(
		long commerceBatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchEntryLocalService.deleteCommerceBatchEntry(commerceBatchEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceBatchEntryLocalService.dynamicQuery();
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
		return _commerceBatchEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBatchEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceBatchEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _commerceBatchEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commerceBatchEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry fetchCommerceBatchEntry(
		long commerceBatchEntryId) {
		return _commerceBatchEntryLocalService.fetchCommerceBatchEntry(commerceBatchEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceBatchEntryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the commerce batch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce batch entries
	* @param end the upper bound of the range of commerce batch entries (not inclusive)
	* @return the range of commerce batch entries
	*/
	@Override
	public java.util.List<com.liferay.commerce.batch.model.CommerceBatchEntry> getCommerceBatchEntries(
		int start, int end) {
		return _commerceBatchEntryLocalService.getCommerceBatchEntries(start,
			end);
	}

	/**
	* Returns the number of commerce batch entries.
	*
	* @return the number of commerce batch entries
	*/
	@Override
	public int getCommerceBatchEntriesCount() {
		return _commerceBatchEntryLocalService.getCommerceBatchEntriesCount();
	}

	/**
	* Returns the commerce batch entry with the primary key.
	*
	* @param commerceBatchEntryId the primary key of the commerce batch entry
	* @return the commerce batch entry
	* @throws PortalException if a commerce batch entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry getCommerceBatchEntry(
		long commerceBatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchEntryLocalService.getCommerceBatchEntry(commerceBatchEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceBatchEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBatchEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBatchEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the commerce batch entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceBatchEntry the commerce batch entry
	* @return the commerce batch entry that was updated
	*/
	@Override
	public com.liferay.commerce.batch.model.CommerceBatchEntry updateCommerceBatchEntry(
		com.liferay.commerce.batch.model.CommerceBatchEntry commerceBatchEntry) {
		return _commerceBatchEntryLocalService.updateCommerceBatchEntry(commerceBatchEntry);
	}

	@Override
	public CommerceBatchEntryLocalService getWrappedService() {
		return _commerceBatchEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceBatchEntryLocalService commerceBatchEntryLocalService) {
		_commerceBatchEntryLocalService = commerceBatchEntryLocalService;
	}

	private CommerceBatchEntryLocalService _commerceBatchEntryLocalService;
}