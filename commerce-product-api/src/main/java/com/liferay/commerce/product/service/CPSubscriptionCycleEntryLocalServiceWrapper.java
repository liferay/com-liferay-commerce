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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSubscriptionCycleEntryLocalService}.
 *
 * @author Marco Leo
 * @see CPSubscriptionCycleEntryLocalService
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntryLocalServiceWrapper
	implements CPSubscriptionCycleEntryLocalService,
		ServiceWrapper<CPSubscriptionCycleEntryLocalService> {
	public CPSubscriptionCycleEntryLocalServiceWrapper(
		CPSubscriptionCycleEntryLocalService cpSubscriptionCycleEntryLocalService) {
		_cpSubscriptionCycleEntryLocalService = cpSubscriptionCycleEntryLocalService;
	}

	/**
	* Adds the cp subscription cycle entry to the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionCycleEntry the cp subscription cycle entry
	* @return the cp subscription cycle entry that was added
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry addCPSubscriptionCycleEntry(
		com.liferay.commerce.product.model.CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return _cpSubscriptionCycleEntryLocalService.addCPSubscriptionCycleEntry(cpSubscriptionCycleEntry);
	}

	/**
	* Creates a new cp subscription cycle entry with the primary key. Does not add the cp subscription cycle entry to the database.
	*
	* @param CPSubscriptionCycleEntryId the primary key for the new cp subscription cycle entry
	* @return the new cp subscription cycle entry
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry createCPSubscriptionCycleEntry(
		long CPSubscriptionCycleEntryId) {
		return _cpSubscriptionCycleEntryLocalService.createCPSubscriptionCycleEntry(CPSubscriptionCycleEntryId);
	}

	/**
	* Deletes the cp subscription cycle entry from the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionCycleEntry the cp subscription cycle entry
	* @return the cp subscription cycle entry that was removed
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry deleteCPSubscriptionCycleEntry(
		com.liferay.commerce.product.model.CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return _cpSubscriptionCycleEntryLocalService.deleteCPSubscriptionCycleEntry(cpSubscriptionCycleEntry);
	}

	/**
	* Deletes the cp subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry that was removed
	* @throws PortalException if a cp subscription cycle entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry deleteCPSubscriptionCycleEntry(
		long CPSubscriptionCycleEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionCycleEntryLocalService.deleteCPSubscriptionCycleEntry(CPSubscriptionCycleEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionCycleEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpSubscriptionCycleEntryLocalService.dynamicQuery();
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
		return _cpSubscriptionCycleEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpSubscriptionCycleEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpSubscriptionCycleEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _cpSubscriptionCycleEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cpSubscriptionCycleEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry fetchCPSubscriptionCycleEntry(
		long CPSubscriptionCycleEntryId) {
		return _cpSubscriptionCycleEntryLocalService.fetchCPSubscriptionCycleEntry(CPSubscriptionCycleEntryId);
	}

	/**
	* Returns the cp subscription cycle entry matching the UUID and group.
	*
	* @param uuid the cp subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry fetchCPSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId) {
		return _cpSubscriptionCycleEntryLocalService.fetchCPSubscriptionCycleEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cpSubscriptionCycleEntryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the cp subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @return the range of cp subscription cycle entries
	*/
	@Override
	public java.util.List<com.liferay.commerce.product.model.CPSubscriptionCycleEntry> getCPSubscriptionCycleEntries(
		int start, int end) {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntries(start,
			end);
	}

	/**
	* Returns all the cp subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription cycle entries
	* @param companyId the primary key of the company
	* @return the matching cp subscription cycle entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.product.model.CPSubscriptionCycleEntry> getCPSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of cp subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription cycle entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of cp subscription cycle entries
	* @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching cp subscription cycle entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.product.model.CPSubscriptionCycleEntry> getCPSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPSubscriptionCycleEntry> orderByComparator) {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of cp subscription cycle entries.
	*
	* @return the number of cp subscription cycle entries
	*/
	@Override
	public int getCPSubscriptionCycleEntriesCount() {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntriesCount();
	}

	/**
	* Returns the cp subscription cycle entry with the primary key.
	*
	* @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	* @return the cp subscription cycle entry
	* @throws PortalException if a cp subscription cycle entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry getCPSubscriptionCycleEntry(
		long CPSubscriptionCycleEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntry(CPSubscriptionCycleEntryId);
	}

	/**
	* Returns the cp subscription cycle entry matching the UUID and group.
	*
	* @param uuid the cp subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription cycle entry
	* @throws PortalException if a matching cp subscription cycle entry could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry getCPSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionCycleEntryLocalService.getCPSubscriptionCycleEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _cpSubscriptionCycleEntryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cpSubscriptionCycleEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpSubscriptionCycleEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionCycleEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cp subscription cycle entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionCycleEntry the cp subscription cycle entry
	* @return the cp subscription cycle entry that was updated
	*/
	@Override
	public com.liferay.commerce.product.model.CPSubscriptionCycleEntry updateCPSubscriptionCycleEntry(
		com.liferay.commerce.product.model.CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		return _cpSubscriptionCycleEntryLocalService.updateCPSubscriptionCycleEntry(cpSubscriptionCycleEntry);
	}

	@Override
	public CPSubscriptionCycleEntryLocalService getWrappedService() {
		return _cpSubscriptionCycleEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CPSubscriptionCycleEntryLocalService cpSubscriptionCycleEntryLocalService) {
		_cpSubscriptionCycleEntryLocalService = cpSubscriptionCycleEntryLocalService;
	}

	private CPSubscriptionCycleEntryLocalService _cpSubscriptionCycleEntryLocalService;
}