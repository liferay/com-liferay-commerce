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

package com.liferay.commerce.product.type.grouped.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDefinitionGroupedEntryLocalService}.
 *
 * @author Andrea Di Giorgi
 * @see CPDefinitionGroupedEntryLocalService
 * @generated
 */
public class CPDefinitionGroupedEntryLocalServiceWrapper
	implements CPDefinitionGroupedEntryLocalService,
			   ServiceWrapper<CPDefinitionGroupedEntryLocalService> {

	public CPDefinitionGroupedEntryLocalServiceWrapper(
		CPDefinitionGroupedEntryLocalService
			cpDefinitionGroupedEntryLocalService) {

		_cpDefinitionGroupedEntryLocalService =
			cpDefinitionGroupedEntryLocalService;
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public void addCPDefinitionGroupedEntries(
			long cpDefinitionId, long[] entryCPDefinitionIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionGroupedEntryLocalService.addCPDefinitionGroupedEntries(
			cpDefinitionId, entryCPDefinitionIds, serviceContext);
	}

	@Override
	public void addCPDefinitionGroupedEntriesByEntryCProductIds(
			long cpDefinitionId, long[] entryCProductIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionGroupedEntryLocalService.
			addCPDefinitionGroupedEntriesByEntryCProductIds(
				cpDefinitionId, entryCProductIds, serviceContext);
	}

	/**
	 * Adds the cp definition grouped entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was added
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			addCPDefinitionGroupedEntry(
				com.liferay.commerce.product.type.grouped.model.
					CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		return _cpDefinitionGroupedEntryLocalService.
			addCPDefinitionGroupedEntry(cpDefinitionGroupedEntry);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				addCPDefinitionGroupedEntry(
					long cpDefinitionId, long entryCPDefinitionId,
					double priority, int quantity,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			addCPDefinitionGroupedEntry(
				cpDefinitionId, entryCPDefinitionId, priority, quantity,
				serviceContext);
	}

	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				addCPDefinitionGroupedEntryByEntryCProductId(
					long cpDefinitionId, long entryCProductId, double priority,
					int quantity,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			addCPDefinitionGroupedEntryByEntryCProductId(
				cpDefinitionId, entryCProductId, priority, quantity,
				serviceContext);
	}

	@Override
	public void cloneCPDefinitionGroupedEntries(
		long cpDefinitionId, long newCPDefinitionId) {

		_cpDefinitionGroupedEntryLocalService.cloneCPDefinitionGroupedEntries(
			cpDefinitionId, newCPDefinitionId);
	}

	/**
	 * Creates a new cp definition grouped entry with the primary key. Does not add the cp definition grouped entry to the database.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key for the new cp definition grouped entry
	 * @return the new cp definition grouped entry
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			createCPDefinitionGroupedEntry(long CPDefinitionGroupedEntryId) {

		return _cpDefinitionGroupedEntryLocalService.
			createCPDefinitionGroupedEntry(CPDefinitionGroupedEntryId);
	}

	@Override
	public void deleteCPDefinitionGroupedEntries(long cpDefinitionId) {
		_cpDefinitionGroupedEntryLocalService.deleteCPDefinitionGroupedEntries(
			cpDefinitionId);
	}

	/**
	 * Deletes the cp definition grouped entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was removed
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			deleteCPDefinitionGroupedEntry(
				com.liferay.commerce.product.type.grouped.model.
					CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		return _cpDefinitionGroupedEntryLocalService.
			deleteCPDefinitionGroupedEntry(cpDefinitionGroupedEntry);
	}

	/**
	 * Deletes the cp definition grouped entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key of the cp definition grouped entry
	 * @return the cp definition grouped entry that was removed
	 * @throws PortalException if a cp definition grouped entry with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				deleteCPDefinitionGroupedEntry(long CPDefinitionGroupedEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			deleteCPDefinitionGroupedEntry(CPDefinitionGroupedEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpDefinitionGroupedEntryLocalService.dynamicQuery();
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

		return _cpDefinitionGroupedEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cpDefinitionGroupedEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cpDefinitionGroupedEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _cpDefinitionGroupedEntryLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _cpDefinitionGroupedEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			fetchCPDefinitionGroupedEntry(long CPDefinitionGroupedEntryId) {

		return _cpDefinitionGroupedEntryLocalService.
			fetchCPDefinitionGroupedEntry(CPDefinitionGroupedEntryId);
	}

	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			fetchCPDefinitionGroupedEntry(
				long cpDefinitionId, long entryCProductId) {

		return _cpDefinitionGroupedEntryLocalService.
			fetchCPDefinitionGroupedEntry(cpDefinitionId, entryCProductId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			fetchCPDefinitionGroupedEntryByC_E(
				long cpDefinitionId, long entryCPDefinitionId) {

		return _cpDefinitionGroupedEntryLocalService.
			fetchCPDefinitionGroupedEntryByC_E(
				cpDefinitionId, entryCPDefinitionId);
	}

	/**
	 * Returns the cp definition grouped entry matching the UUID and group.
	 *
	 * @param uuid the cp definition grouped entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition grouped entry, or <code>null</code> if a matching cp definition grouped entry could not be found
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			fetchCPDefinitionGroupedEntryByUuidAndGroupId(
				String uuid, long groupId) {

		return _cpDefinitionGroupedEntryLocalService.
			fetchCPDefinitionGroupedEntryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cpDefinitionGroupedEntryLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the cp definition grouped entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition grouped entries
	 * @param end the upper bound of the range of cp definition grouped entries (not inclusive)
	 * @return the range of cp definition grouped entries
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
				int start, int end) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntries(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
				long cpDefinitionId) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntries(cpDefinitionId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
				long cpDefinitionId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.type.grouped.model.
						CPDefinitionGroupedEntry> orderByComparator) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntries(
				cpDefinitionId, start, end, orderByComparator);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry>
				getCPDefinitionGroupedEntriesByCPDefinitionId(
					long cpDefinitionId) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntriesByCPDefinitionId(cpDefinitionId);
	}

	/**
	 * Returns all the cp definition grouped entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition grouped entries
	 * @param companyId the primary key of the company
	 * @return the matching cp definition grouped entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry>
				getCPDefinitionGroupedEntriesByUuidAndCompanyId(
					String uuid, long companyId) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of cp definition grouped entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition grouped entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cp definition grouped entries
	 * @param end the upper bound of the range of cp definition grouped entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cp definition grouped entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.product.type.grouped.model.
			CPDefinitionGroupedEntry>
				getCPDefinitionGroupedEntriesByUuidAndCompanyId(
					String uuid, long companyId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.type.grouped.model.
							CPDefinitionGroupedEntry> orderByComparator) {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntriesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of cp definition grouped entries.
	 *
	 * @return the number of cp definition grouped entries
	 */
	@Override
	public int getCPDefinitionGroupedEntriesCount() {
		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntriesCount();
	}

	@Override
	public int getCPDefinitionGroupedEntriesCount(long cpDefinitionId) {
		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntriesCount(cpDefinitionId);
	}

	/**
	 * Returns the cp definition grouped entry with the primary key.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key of the cp definition grouped entry
	 * @return the cp definition grouped entry
	 * @throws PortalException if a cp definition grouped entry with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				getCPDefinitionGroupedEntry(long CPDefinitionGroupedEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntry(CPDefinitionGroupedEntryId);
	}

	/**
	 * Returns the cp definition grouped entry matching the UUID and group.
	 *
	 * @param uuid the cp definition grouped entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition grouped entry
	 * @throws PortalException if a matching cp definition grouped entry could not be found
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				getCPDefinitionGroupedEntryByUuidAndGroupId(
					String uuid, long groupId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			getCPDefinitionGroupedEntryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _cpDefinitionGroupedEntryLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cpDefinitionGroupedEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDefinitionGroupedEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the cp definition grouped entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was updated
	 */
	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
			updateCPDefinitionGroupedEntry(
				com.liferay.commerce.product.type.grouped.model.
					CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		return _cpDefinitionGroupedEntryLocalService.
			updateCPDefinitionGroupedEntry(cpDefinitionGroupedEntry);
	}

	@Override
	public
		com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry
				updateCPDefinitionGroupedEntry(
					long cpDefinitionGroupedEntryId, double priority,
					int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionGroupedEntryLocalService.
			updateCPDefinitionGroupedEntry(
				cpDefinitionGroupedEntryId, priority, quantity);
	}

	@Override
	public CPDefinitionGroupedEntryLocalService getWrappedService() {
		return _cpDefinitionGroupedEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CPDefinitionGroupedEntryLocalService
			cpDefinitionGroupedEntryLocalService) {

		_cpDefinitionGroupedEntryLocalService =
			cpDefinitionGroupedEntryLocalService;
	}

	private CPDefinitionGroupedEntryLocalService
		_cpDefinitionGroupedEntryLocalService;

}