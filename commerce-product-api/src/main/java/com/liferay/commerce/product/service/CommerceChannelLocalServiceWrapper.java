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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelLocalService}.
 *
 * @author Marco Leo
 * @see CommerceChannelLocalService
 * @generated
 */
public class CommerceChannelLocalServiceWrapper
	implements CommerceChannelLocalService,
			   ServiceWrapper<CommerceChannelLocalService> {

	public CommerceChannelLocalServiceWrapper(
		CommerceChannelLocalService commerceChannelLocalService) {

		_commerceChannelLocalService = commerceChannelLocalService;
	}

	/**
	 * Adds the commerce channel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannel the commerce channel
	 * @return the commerce channel that was added
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		addCommerceChannel(
			com.liferay.commerce.product.model.CommerceChannel
				commerceChannel) {

		return _commerceChannelLocalService.addCommerceChannel(commerceChannel);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			addCommerceChannel(
				long siteGroupId, String name, String type,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsProperties,
				String commerceCurrencyCode, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.addCommerceChannel(
			siteGroupId, name, type, typeSettingsProperties,
			commerceCurrencyCode, externalReferenceCode, serviceContext);
	}

	/**
	 * Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	 *
	 * @param commerceChannelId the primary key for the new commerce channel
	 * @return the new commerce channel
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		createCommerceChannel(long commerceChannelId) {

		return _commerceChannelLocalService.createCommerceChannel(
			commerceChannelId);
	}

	/**
	 * Deletes the commerce channel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannel the commerce channel
	 * @return the commerce channel that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			deleteCommerceChannel(
				com.liferay.commerce.product.model.CommerceChannel
					commerceChannel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.deleteCommerceChannel(
			commerceChannel);
	}

	/**
	 * Deletes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws PortalException if a commerce channel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			deleteCommerceChannel(long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.deleteCommerceChannel(
			commerceChannelId);
	}

	@Override
	public void deleteCommerceChannels(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceChannelLocalService.deleteCommerceChannels(companyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceChannelLocalService.dynamicQuery();
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

		return _commerceChannelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceChannelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceChannelLocalService.dynamicQuery(
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

		return _commerceChannelLocalService.dynamicQueryCount(dynamicQuery);
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

		return _commerceChannelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		fetchCommerceChannel(long commerceChannelId) {

		return _commerceChannelLocalService.fetchCommerceChannel(
			commerceChannelId);
	}

	/**
	 * Returns the commerce channel with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce channel's external reference code
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		fetchCommerceChannelByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commerceChannelLocalService.fetchCommerceChannelByReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		fetchCommerceChannelBySiteGroupId(long siteGroupId) {

		return _commerceChannelLocalService.fetchCommerceChannelBySiteGroupId(
			siteGroupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceChannelLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce channel with the primary key.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws PortalException if a commerce channel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			getCommerceChannel(long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.getCommerceChannel(
			commerceChannelId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			getCommerceChannelByOrderGroupId(long orderGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.getCommerceChannelByOrderGroupId(
			orderGroupId);
	}

	@Override
	public com.liferay.portal.kernel.model.Group getCommerceChannelGroup(
			long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.getCommerceChannelGroup(
			commerceChannelId);
	}

	@Override
	public long getCommerceChannelGroupIdBySiteGroupId(long siteGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.
			getCommerceChannelGroupIdBySiteGroupId(siteGroupId);
	}

	/**
	 * Returns a range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of commerce channels
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
		getCommerceChannels(int start, int end) {

		return _commerceChannelLocalService.getCommerceChannels(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
		getCommerceChannels(long companyId) {

		return _commerceChannelLocalService.getCommerceChannels(companyId);
	}

	/**
	 * Returns the number of commerce channels.
	 *
	 * @return the number of commerce channels
	 */
	@Override
	public int getCommerceChannelsCount() {
		return _commerceChannelLocalService.getCommerceChannelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceChannelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			searchCommerceChannels(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.searchCommerceChannels(companyId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			searchCommerceChannels(
				long companyId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.searchCommerceChannels(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceChannelsCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.searchCommerceChannelsCount(
			companyId, keywords);
	}

	/**
	 * Updates the commerce channel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannel the commerce channel
	 * @return the commerce channel that was updated
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
		updateCommerceChannel(
			com.liferay.commerce.product.model.CommerceChannel
				commerceChannel) {

		return _commerceChannelLocalService.updateCommerceChannel(
			commerceChannel);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			updateCommerceChannel(
				long commerceChannelId, long siteGroupId, String name,
				String type,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsProperties,
				String commerceCurrencyCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelLocalService.updateCommerceChannel(
			commerceChannelId, siteGroupId, name, type, typeSettingsProperties,
			commerceCurrencyCode);
	}

	@Override
	public CommerceChannelLocalService getWrappedService() {
		return _commerceChannelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelLocalService commerceChannelLocalService) {

		_commerceChannelLocalService = commerceChannelLocalService;
	}

	private CommerceChannelLocalService _commerceChannelLocalService;

}