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

package com.liferay.commerce.notification.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceNotificationTemplateUserSegmentRelLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationTemplateUserSegmentRelLocalService
 * @generated
 */
@ProviderType
public class CommerceNotificationTemplateUserSegmentRelLocalServiceWrapper
	implements CommerceNotificationTemplateUserSegmentRelLocalService,
			   ServiceWrapper
				   <CommerceNotificationTemplateUserSegmentRelLocalService> {

	public CommerceNotificationTemplateUserSegmentRelLocalServiceWrapper(
		CommerceNotificationTemplateUserSegmentRelLocalService
			commerceNotificationTemplateUserSegmentRelLocalService) {

		_commerceNotificationTemplateUserSegmentRelLocalService =
			commerceNotificationTemplateUserSegmentRelLocalService;
	}

	/**
	 * Adds the commerce notification template user segment rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceNotificationTemplateUserSegmentRel the commerce notification template user segment rel
	 * @return the commerce notification template user segment rel that was added
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			addCommerceNotificationTemplateUserSegmentRel(
				com.liferay.commerce.notification.model.
					CommerceNotificationTemplateUserSegmentRel
						commerceNotificationTemplateUserSegmentRel) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			addCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRel);
	}

	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
				addCommerceNotificationTemplateUserSegmentRel(
					long commerceNotificationTemplateId,
					long commerceUserSegmentEntryId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			addCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateId, commerceUserSegmentEntryId,
				serviceContext);
	}

	/**
	 * Creates a new commerce notification template user segment rel with the primary key. Does not add the commerce notification template user segment rel to the database.
	 *
	 * @param commerceNotificationTemplateUserSegmentRelId the primary key for the new commerce notification template user segment rel
	 * @return the new commerce notification template user segment rel
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			createCommerceNotificationTemplateUserSegmentRel(
				long commerceNotificationTemplateUserSegmentRelId) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			createCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRelId);
	}

	@Override
	public void deleteCNTemplateUserSegmentRelsByCommerceNotificationTemplateId(
		long commerceNotificationTemplateId) {

		_commerceNotificationTemplateUserSegmentRelLocalService.
			deleteCNTemplateUserSegmentRelsByCommerceNotificationTemplateId(
				commerceNotificationTemplateId);
	}

	@Override
	public void deleteCNTemplateUserSegmentRelsByCommerceUserSegmentEntryId(
		long commerceUserSegmentEntryId) {

		_commerceNotificationTemplateUserSegmentRelLocalService.
			deleteCNTemplateUserSegmentRelsByCommerceUserSegmentEntryId(
				commerceUserSegmentEntryId);
	}

	/**
	 * Deletes the commerce notification template user segment rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceNotificationTemplateUserSegmentRel the commerce notification template user segment rel
	 * @return the commerce notification template user segment rel that was removed
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			deleteCommerceNotificationTemplateUserSegmentRel(
				com.liferay.commerce.notification.model.
					CommerceNotificationTemplateUserSegmentRel
						commerceNotificationTemplateUserSegmentRel) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			deleteCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRel);
	}

	/**
	 * Deletes the commerce notification template user segment rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceNotificationTemplateUserSegmentRelId the primary key of the commerce notification template user segment rel
	 * @return the commerce notification template user segment rel that was removed
	 * @throws PortalException if a commerce notification template user segment rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
				deleteCommerceNotificationTemplateUserSegmentRel(
					long commerceNotificationTemplateUserSegmentRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			deleteCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQuery();
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

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQueryCount(dynamicQuery);
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

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			fetchCommerceNotificationTemplateUserSegmentRel(
				long commerceNotificationTemplateUserSegmentRelId) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			fetchCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRelId);
	}

	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			fetchCommerceNotificationTemplateUserSegmentRel(
				long commerceNotificationTemplateId,
				long commerceUserSegmentEntryId) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			fetchCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateId, commerceUserSegmentEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce notification template user segment rel with the primary key.
	 *
	 * @param commerceNotificationTemplateUserSegmentRelId the primary key of the commerce notification template user segment rel
	 * @return the commerce notification template user segment rel
	 * @throws PortalException if a commerce notification template user segment rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
				getCommerceNotificationTemplateUserSegmentRel(
					long commerceNotificationTemplateUserSegmentRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRelId);
	}

	/**
	 * Returns a range of all the commerce notification template user segment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce notification template user segment rels
	 * @param end the upper bound of the range of commerce notification template user segment rels (not inclusive)
	 * @return the range of commerce notification template user segment rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.notification.model.
			CommerceNotificationTemplateUserSegmentRel>
				getCommerceNotificationTemplateUserSegmentRels(
					int start, int end) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getCommerceNotificationTemplateUserSegmentRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.notification.model.
			CommerceNotificationTemplateUserSegmentRel>
				getCommerceNotificationTemplateUserSegmentRels(
					long commerceNotificationTemplateId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.notification.model.
							CommerceNotificationTemplateUserSegmentRel>
								orderByComparator) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getCommerceNotificationTemplateUserSegmentRels(
				commerceNotificationTemplateId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce notification template user segment rels.
	 *
	 * @return the number of commerce notification template user segment rels
	 */
	@Override
	public int getCommerceNotificationTemplateUserSegmentRelsCount() {
		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getCommerceNotificationTemplateUserSegmentRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce notification template user segment rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceNotificationTemplateUserSegmentRel the commerce notification template user segment rel
	 * @return the commerce notification template user segment rel that was updated
	 */
	@Override
	public com.liferay.commerce.notification.model.
		CommerceNotificationTemplateUserSegmentRel
			updateCommerceNotificationTemplateUserSegmentRel(
				com.liferay.commerce.notification.model.
					CommerceNotificationTemplateUserSegmentRel
						commerceNotificationTemplateUserSegmentRel) {

		return _commerceNotificationTemplateUserSegmentRelLocalService.
			updateCommerceNotificationTemplateUserSegmentRel(
				commerceNotificationTemplateUserSegmentRel);
	}

	@Override
	public CommerceNotificationTemplateUserSegmentRelLocalService
		getWrappedService() {

		return _commerceNotificationTemplateUserSegmentRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceNotificationTemplateUserSegmentRelLocalService
			commerceNotificationTemplateUserSegmentRelLocalService) {

		_commerceNotificationTemplateUserSegmentRelLocalService =
			commerceNotificationTemplateUserSegmentRelLocalService;
	}

	private CommerceNotificationTemplateUserSegmentRelLocalService
		_commerceNotificationTemplateUserSegmentRelLocalService;

}