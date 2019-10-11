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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceSubscriptionEntryLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryLocalService
 * @generated
 */
public class CommerceSubscriptionEntryLocalServiceWrapper
	implements CommerceSubscriptionEntryLocalService,
			   ServiceWrapper<CommerceSubscriptionEntryLocalService> {

	public CommerceSubscriptionEntryLocalServiceWrapper(
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService) {

		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
	}

	/**
	 * Adds the commerce subscription entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionEntry the commerce subscription entry
	 * @return the commerce subscription entry that was added
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		addCommerceSubscriptionEntry(
			com.liferay.commerce.model.CommerceSubscriptionEntry
				commerceSubscriptionEntry) {

		return _commerceSubscriptionEntryLocalService.
			addCommerceSubscriptionEntry(commerceSubscriptionEntry);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			addCommerceSubscriptionEntry(
				long userId, long groupId, long commerceOrderItemId,
				int subscriptionLength, String subscriptionType,
				long maxSubscriptionCycles,
				com.liferay.portal.kernel.util.UnicodeProperties
					subscriptionTypeSettingsProperties)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			addCommerceSubscriptionEntry(
				userId, groupId, commerceOrderItemId, subscriptionLength,
				subscriptionType, maxSubscriptionCycles,
				subscriptionTypeSettingsProperties);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass userId and groupId
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			addCommerceSubscriptionEntry(
				long cpInstanceId, long commerceOrderItemId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			addCommerceSubscriptionEntry(
				cpInstanceId, commerceOrderItemId, serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass subscription info instead of
	 cpInstanceUuid and cProductId
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			addCommerceSubscriptionEntry(
				long userId, long groupId, String cpInstanceUuid,
				long cProductId, long commerceOrderItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			addCommerceSubscriptionEntry(
				userId, groupId, cpInstanceUuid, cProductId,
				commerceOrderItemId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), pass userId and groupId
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			addCommerceSubscriptionEntry(
				String cpInstanceUuid, long cProductId,
				long commerceOrderItemId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			addCommerceSubscriptionEntry(
				cpInstanceUuid, cProductId, commerceOrderItemId,
				serviceContext);
	}

	/**
	 * Creates a new commerce subscription entry with the primary key. Does not add the commerce subscription entry to the database.
	 *
	 * @param commerceSubscriptionEntryId the primary key for the new commerce subscription entry
	 * @return the new commerce subscription entry
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		createCommerceSubscriptionEntry(long commerceSubscriptionEntryId) {

		return _commerceSubscriptionEntryLocalService.
			createCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	/**
	 * Deletes the commerce subscription entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionEntry the commerce subscription entry
	 * @return the commerce subscription entry that was removed
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		deleteCommerceSubscriptionEntry(
			com.liferay.commerce.model.CommerceSubscriptionEntry
				commerceSubscriptionEntry) {

		return _commerceSubscriptionEntryLocalService.
			deleteCommerceSubscriptionEntry(commerceSubscriptionEntry);
	}

	/**
	 * Deletes the commerce subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	 * @return the commerce subscription entry that was removed
	 * @throws PortalException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			deleteCommerceSubscriptionEntry(long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			deleteCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceSubscriptionEntryLocalService.dynamicQuery();
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

		return _commerceSubscriptionEntryLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceSubscriptionEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceSubscriptionEntryLocalService.dynamicQuery(
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

		return _commerceSubscriptionEntryLocalService.dynamicQueryCount(
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

		return _commerceSubscriptionEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), fetch by commerceOrderItemId instead
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		fetchCommerceSubscriptionEntries(
			String cpInstanceUuid, long cProductId, long commerceOrderItemId) {

		return _commerceSubscriptionEntryLocalService.
			fetchCommerceSubscriptionEntries(
				cpInstanceUuid, cProductId, commerceOrderItemId);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		fetchCommerceSubscriptionEntry(long commerceSubscriptionEntryId) {

		return _commerceSubscriptionEntryLocalService.
			fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	/**
	 * Returns the commerce subscription entry matching the UUID and group.
	 *
	 * @param uuid the commerce subscription entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		fetchCommerceSubscriptionEntryByUuidAndGroupId(
			String uuid, long groupId) {

		return _commerceSubscriptionEntryLocalService.
			fetchCommerceSubscriptionEntryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceSubscriptionEntryLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getActiveCommerceSubscriptionEntries() {

		return _commerceSubscriptionEntryLocalService.
			getActiveCommerceSubscriptionEntries();
	}

	/**
	 * Returns a range of all the commerce subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of commerce subscription entries
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntries(int start, int end) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntries(
			long companyId, long userId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceSubscriptionEntry>
					orderByComparator) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntries(
				companyId, userId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntries(
			long companyId, long groupId, long userId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceSubscriptionEntry>
					orderByComparator) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntries(
				companyId, groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns all the commerce subscription entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce subscription entries
	 * @param companyId the primary key of the company
	 * @return the matching commerce subscription entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntriesByUuidAndCompanyId(
			String uuid, long companyId) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of commerce subscription entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce subscription entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce subscription entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceSubscriptionEntry>
					orderByComparator) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce subscription entries.
	 *
	 * @return the number of commerce subscription entries
	 */
	@Override
	public int getCommerceSubscriptionEntriesCount() {
		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesCount();
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(
		long companyId, long userId) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesCount(companyId, userId);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(
		long companyId, long groupId, long userId) {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesCount(companyId, groupId, userId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
		getCommerceSubscriptionEntriesToRenew() {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntriesToRenew();
	}

	/**
	 * Returns the commerce subscription entry with the primary key.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	 * @return the commerce subscription entry
	 * @throws PortalException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			getCommerceSubscriptionEntry(long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	/**
	 * Returns the commerce subscription entry matching the UUID and group.
	 *
	 * @param uuid the commerce subscription entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce subscription entry
	 * @throws PortalException if a matching commerce subscription entry could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			getCommerceSubscriptionEntryByUuidAndGroupId(
				String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			getCommerceSubscriptionEntryByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commerceSubscriptionEntryLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceSubscriptionEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceSubscriptionEntryLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			incrementCommerceSubscriptionEntryCycle(
				long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			incrementCommerceSubscriptionEntryCycle(
				commerceSubscriptionEntryId);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceSubscriptionEntry>
				searchCommerceSubscriptionEntries(
					long companyId, Long maxSubscriptionCycles,
					Integer subscriptionStatus, String keywords, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			searchCommerceSubscriptionEntries(
				companyId, maxSubscriptionCycles, subscriptionStatus, keywords,
				start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceSubscriptionEntry>
				searchCommerceSubscriptionEntries(
					long companyId, long groupId, Long maxSubscriptionCycles,
					Integer subscriptionStatus, String keywords, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			searchCommerceSubscriptionEntries(
				companyId, groupId, maxSubscriptionCycles, subscriptionStatus,
				keywords, start, end, sort);
	}

	/**
	 * Updates the commerce subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionEntry the commerce subscription entry
	 * @return the commerce subscription entry that was updated
	 */
	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
		updateCommerceSubscriptionEntry(
			com.liferay.commerce.model.CommerceSubscriptionEntry
				commerceSubscriptionEntry) {

		return _commerceSubscriptionEntryLocalService.
			updateCommerceSubscriptionEntry(commerceSubscriptionEntry);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			updateCommerceSubscriptionEntry(
				long commerceSubscriptionEntryId, int subscriptionLength,
				String subscriptionType,
				com.liferay.portal.kernel.util.UnicodeProperties
					subscriptionTypeSettingsProperties,
				long maxSubscriptionCycles, int subscriptionStatus,
				int startDateMonth, int startDateDay, int startDateYear,
				int startDateHour, int startDateMinute,
				int nextIterationDateMonth, int nextIterationDateDay,
				int nextIterationDateYear, int nextIterationDateHour,
				int nextIterationDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			updateCommerceSubscriptionEntry(
				commerceSubscriptionEntryId, subscriptionLength,
				subscriptionType, subscriptionTypeSettingsProperties,
				maxSubscriptionCycles, subscriptionStatus, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				nextIterationDateMonth, nextIterationDateDay,
				nextIterationDateYear, nextIterationDateHour,
				nextIterationDateMinute);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			updateCommerceSubscriptionEntryIterationDates(
				long commerceSubscriptionEntryId,
				java.util.Date lastIterationDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.
			updateCommerceSubscriptionEntryIterationDates(
				commerceSubscriptionEntryId, lastIterationDate);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			updateSubscriptionStatus(
				long commerceSubscriptionEntryId, int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
			commerceSubscriptionEntryId, subscriptionStatus);
	}

	@Override
	public CommerceSubscriptionEntryLocalService getWrappedService() {
		return _commerceSubscriptionEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService) {

		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
	}

	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

}