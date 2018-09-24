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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSubscriptionEntryLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryLocalService
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryLocalServiceWrapper
	implements CPSubscriptionEntryLocalService,
		ServiceWrapper<CPSubscriptionEntryLocalService> {
	public CPSubscriptionEntryLocalServiceWrapper(
		CPSubscriptionEntryLocalService cpSubscriptionEntryLocalService) {
		_cpSubscriptionEntryLocalService = cpSubscriptionEntryLocalService;
	}

	/**
	* Adds the cp subscription entry to the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was added
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry addCPSubscriptionEntry(
		com.liferay.commerce.model.CPSubscriptionEntry cpSubscriptionEntry) {
		return _cpSubscriptionEntryLocalService.addCPSubscriptionEntry(cpSubscriptionEntry);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry addCPSubscriptionEntry(
		long cpInstanceId, long commerceOrderItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.addCPSubscriptionEntry(cpInstanceId,
			commerceOrderItemId, serviceContext);
	}

	/**
	* Creates a new cp subscription entry with the primary key. Does not add the cp subscription entry to the database.
	*
	* @param CPSubscriptionEntryId the primary key for the new cp subscription entry
	* @return the new cp subscription entry
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry createCPSubscriptionEntry(
		long CPSubscriptionEntryId) {
		return _cpSubscriptionEntryLocalService.createCPSubscriptionEntry(CPSubscriptionEntryId);
	}

	/**
	* Deletes the cp subscription entry from the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was removed
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry deleteCPSubscriptionEntry(
		com.liferay.commerce.model.CPSubscriptionEntry cpSubscriptionEntry) {
		return _cpSubscriptionEntryLocalService.deleteCPSubscriptionEntry(cpSubscriptionEntry);
	}

	/**
	* Deletes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry that was removed
	* @throws PortalException if a cp subscription entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry deleteCPSubscriptionEntry(
		long CPSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.deleteCPSubscriptionEntry(CPSubscriptionEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpSubscriptionEntryLocalService.dynamicQuery();
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
		return _cpSubscriptionEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _cpSubscriptionEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cpSubscriptionEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry fetchCPSubscriptionEntry(
		long CPSubscriptionEntryId) {
		return _cpSubscriptionEntryLocalService.fetchCPSubscriptionEntry(CPSubscriptionEntryId);
	}

	/**
	* Returns the cp subscription entry matching the UUID and group.
	*
	* @param uuid the cp subscription entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry fetchCPSubscriptionEntryByUuidAndGroupId(
		String uuid, long groupId) {
		return _cpSubscriptionEntryLocalService.fetchCPSubscriptionEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cpSubscriptionEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getActiveCPSubscriptionEntries() {
		return _cpSubscriptionEntryLocalService.getActiveCPSubscriptionEntries();
	}

	/**
	* Returns a range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of cp subscription entries
	*/
	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntries(
		int start, int end) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntries(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CPSubscriptionEntry> orderByComparator) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntries(groupId,
			userId, start, end, orderByComparator);
	}

	/**
	* Returns all the cp subscription entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription entries
	* @param companyId the primary key of the company
	* @return the matching cp subscription entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of cp subscription entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching cp subscription entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CPSubscriptionEntry> orderByComparator) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of cp subscription entries.
	*
	* @return the number of cp subscription entries
	*/
	@Override
	public int getCPSubscriptionEntriesCount() {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesCount();
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId) {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesCount(groupId,
			userId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntriesToRenew() {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntriesToRenew();
	}

	/**
	* Returns the cp subscription entry with the primary key.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry
	* @throws PortalException if a cp subscription entry with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry getCPSubscriptionEntry(
		long CPSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntry(CPSubscriptionEntryId);
	}

	/**
	* Returns the cp subscription entry matching the UUID and group.
	*
	* @param uuid the cp subscription entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription entry
	* @throws PortalException if a matching cp subscription entry could not be found
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry getCPSubscriptionEntryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.getCPSubscriptionEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _cpSubscriptionEntryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cpSubscriptionEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpSubscriptionEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CPSubscriptionEntry> searchCPSubscriptionEntries(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.searchCPSubscriptionEntries(companyId,
			groupId, active, keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry setActive(
		long cpSubscriptionEntryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.setActive(cpSubscriptionEntryId,
			active);
	}

	/**
	* Updates the cp subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was updated
	*/
	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry updateCPSubscriptionEntry(
		com.liferay.commerce.model.CPSubscriptionEntry cpSubscriptionEntry) {
		return _cpSubscriptionEntryLocalService.updateCPSubscriptionEntry(cpSubscriptionEntry);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry updateCPSubscriptionEntry(
		long cpSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryLocalService.updateCPSubscriptionEntry(cpSubscriptionEntryId,
			subscriptionCycleLength, subscriptionCyclePeriod,
			maxSubscriptionCyclesNumber, active);
	}

	@Override
	public CPSubscriptionEntryLocalService getWrappedService() {
		return _cpSubscriptionEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CPSubscriptionEntryLocalService cpSubscriptionEntryLocalService) {
		_cpSubscriptionEntryLocalService = cpSubscriptionEntryLocalService;
	}

	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;
}