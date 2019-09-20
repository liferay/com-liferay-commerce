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

package com.liferay.commerce.price.list.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommercePriceListAccountRelLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelLocalService
 * @generated
 */
public class CommercePriceListAccountRelLocalServiceWrapper
	implements CommercePriceListAccountRelLocalService,
			   ServiceWrapper<CommercePriceListAccountRelLocalService> {

	public CommercePriceListAccountRelLocalServiceWrapper(
		CommercePriceListAccountRelLocalService
			commercePriceListAccountRelLocalService) {

		_commercePriceListAccountRelLocalService =
			commercePriceListAccountRelLocalService;
	}

	/**
	 * Adds the commerce price list account rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was added
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		addCommercePriceListAccountRel(
			com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				commercePriceListAccountRel) {

		return _commercePriceListAccountRelLocalService.
			addCommercePriceListAccountRel(commercePriceListAccountRel);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			addCommercePriceListAccountRel(
				long commercePriceListId, long commerceAccountId, int order,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.
			addCommercePriceListAccountRel(
				commercePriceListId, commerceAccountId, order, serviceContext);
	}

	/**
	 * Creates a new commerce price list account rel with the primary key. Does not add the commerce price list account rel to the database.
	 *
	 * @param commercePriceListAccountRelId the primary key for the new commerce price list account rel
	 * @return the new commerce price list account rel
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		createCommercePriceListAccountRel(long commercePriceListAccountRelId) {

		return _commercePriceListAccountRelLocalService.
			createCommercePriceListAccountRel(commercePriceListAccountRelId);
	}

	/**
	 * Deletes the commerce price list account rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			deleteCommercePriceListAccountRel(
				com.liferay.commerce.price.list.model.
					CommercePriceListAccountRel commercePriceListAccountRel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.
			deleteCommercePriceListAccountRel(commercePriceListAccountRel);
	}

	/**
	 * Deletes the commerce price list account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws PortalException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			deleteCommercePriceListAccountRel(
				long commercePriceListAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.
			deleteCommercePriceListAccountRel(commercePriceListAccountRelId);
	}

	@Override
	public void deleteCommercePriceListAccountRels(long commercePriceListId) {
		_commercePriceListAccountRelLocalService.
			deleteCommercePriceListAccountRels(commercePriceListId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commercePriceListAccountRelLocalService.dynamicQuery();
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

		return _commercePriceListAccountRelLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commercePriceListAccountRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _commercePriceListAccountRelLocalService.dynamicQuery(
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

		return _commercePriceListAccountRelLocalService.dynamicQueryCount(
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

		return _commercePriceListAccountRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		fetchCommercePriceListAccountRel(long commercePriceListAccountRelId) {

		return _commercePriceListAccountRelLocalService.
			fetchCommercePriceListAccountRel(commercePriceListAccountRelId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		fetchCommercePriceListAccountRel(
			long commercePriceListId, long commerceAccountId) {

		return _commercePriceListAccountRelLocalService.
			fetchCommercePriceListAccountRel(
				commercePriceListId, commerceAccountId);
	}

	/**
	 * Returns the commerce price list account rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list account rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		fetchCommercePriceListAccountRelByUuidAndCompanyId(
			String uuid, long companyId) {

		return _commercePriceListAccountRelLocalService.
			fetchCommercePriceListAccountRelByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commercePriceListAccountRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce price list account rel with the primary key.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel
	 * @throws PortalException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			getCommercePriceListAccountRel(long commercePriceListAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.
			getCommercePriceListAccountRel(commercePriceListAccountRelId);
	}

	/**
	 * Returns the commerce price list account rel with the matching UUID and company.
	 *
	 * @param uuid the commerce price list account rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce price list account rel
	 * @throws PortalException if a matching commerce price list account rel could not be found
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			getCommercePriceListAccountRelByUuidAndCompanyId(
				String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.
			getCommercePriceListAccountRelByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce price list account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of commerce price list account rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
			getCommercePriceListAccountRels(int start, int end) {

		return _commercePriceListAccountRelLocalService.
			getCommercePriceListAccountRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
			getCommercePriceListAccountRels(long commercePriceListId) {

		return _commercePriceListAccountRelLocalService.
			getCommercePriceListAccountRels(commercePriceListId);
	}

	/**
	 * Returns the number of commerce price list account rels.
	 *
	 * @return the number of commerce price list account rels
	 */
	@Override
	public int getCommercePriceListAccountRelsCount() {
		return _commercePriceListAccountRelLocalService.
			getCommercePriceListAccountRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commercePriceListAccountRelLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commercePriceListAccountRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePriceListAccountRelLocalService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce price list account rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 * @return the commerce price list account rel that was updated
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
		updateCommercePriceListAccountRel(
			com.liferay.commerce.price.list.model.CommercePriceListAccountRel
				commercePriceListAccountRel) {

		return _commercePriceListAccountRelLocalService.
			updateCommercePriceListAccountRel(commercePriceListAccountRel);
	}

	@Override
	public CommercePriceListAccountRelLocalService getWrappedService() {
		return _commercePriceListAccountRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommercePriceListAccountRelLocalService
			commercePriceListAccountRelLocalService) {

		_commercePriceListAccountRelLocalService =
			commercePriceListAccountRelLocalService;
	}

	private CommercePriceListAccountRelLocalService
		_commercePriceListAccountRelLocalService;

}