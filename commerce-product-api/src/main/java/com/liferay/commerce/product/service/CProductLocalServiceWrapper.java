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
 * Provides a wrapper for {@link CProductLocalService}.
 *
 * @author Marco Leo
 * @see CProductLocalService
 * @generated
 */
public class CProductLocalServiceWrapper
	implements CProductLocalService, ServiceWrapper<CProductLocalService> {

	public CProductLocalServiceWrapper(
		CProductLocalService cProductLocalService) {

		_cProductLocalService = cProductLocalService;
	}

	/**
	 * Adds the c product to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was added
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct addCProduct(
		com.liferay.commerce.product.model.CProduct cProduct) {

		return _cProductLocalService.addCProduct(cProduct);
	}

	@Override
	public com.liferay.commerce.product.model.CProduct addCProduct(
			long groupId, long userId, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.addCProduct(
			groupId, userId, externalReferenceCode, serviceContext);
	}

	/**
	 * Creates a new c product with the primary key. Does not add the c product to the database.
	 *
	 * @param CProductId the primary key for the new c product
	 * @return the new c product
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct createCProduct(
		long CProductId) {

		return _cProductLocalService.createCProduct(CProductId);
	}

	/**
	 * Deletes the c product from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was removed
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct deleteCProduct(
		com.liferay.commerce.product.model.CProduct cProduct) {

		return _cProductLocalService.deleteCProduct(cProduct);
	}

	/**
	 * Deletes the c product with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CProductId the primary key of the c product
	 * @return the c product that was removed
	 * @throws PortalException if a c product with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct deleteCProduct(
			long CProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.deleteCProduct(CProductId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cProductLocalService.dynamicQuery();
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

		return _cProductLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CProductModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cProductLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CProductModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cProductLocalService.dynamicQuery(
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

		return _cProductLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cProductLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.product.model.CProduct fetchCProduct(
		long CProductId) {

		return _cProductLocalService.fetchCProduct(CProductId);
	}

	/**
	 * Returns the c product with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the c product's external reference code
	 * @return the matching c product, or <code>null</code> if a matching c product could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct
		fetchCProductByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _cProductLocalService.fetchCProductByReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the c product matching the UUID and group.
	 *
	 * @param uuid the c product's UUID
	 * @param groupId the primary key of the group
	 * @return the matching c product, or <code>null</code> if a matching c product could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct
		fetchCProductByUuidAndGroupId(String uuid, long groupId) {

		return _cProductLocalService.fetchCProductByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cProductLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the c product with the primary key.
	 *
	 * @param CProductId the primary key of the c product
	 * @return the c product
	 * @throws PortalException if a c product with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct getCProduct(
			long CProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.getCProduct(CProductId);
	}

	@Override
	public com.liferay.commerce.product.model.CProduct
			getCProductByCPInstanceUuid(String cpInstanceUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.getCProductByCPInstanceUuid(
			cpInstanceUuid);
	}

	/**
	 * Returns the c product matching the UUID and group.
	 *
	 * @param uuid the c product's UUID
	 * @param groupId the primary key of the group
	 * @return the matching c product
	 * @throws PortalException if a matching c product could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct
			getCProductByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.getCProductByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the c products.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CProductModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of c products
	 * @param end the upper bound of the range of c products (not inclusive)
	 * @return the range of c products
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CProduct>
		getCProducts(int start, int end) {

		return _cProductLocalService.getCProducts(start, end);
	}

	/**
	 * Returns all the c products matching the UUID and company.
	 *
	 * @param uuid the UUID of the c products
	 * @param companyId the primary key of the company
	 * @return the matching c products, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CProduct>
		getCProductsByUuidAndCompanyId(String uuid, long companyId) {

		return _cProductLocalService.getCProductsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of c products matching the UUID and company.
	 *
	 * @param uuid the UUID of the c products
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of c products
	 * @param end the upper bound of the range of c products (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching c products, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CProduct>
		getCProductsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CProduct>
					orderByComparator) {

		return _cProductLocalService.getCProductsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of c products.
	 *
	 * @return the number of c products
	 */
	@Override
	public int getCProductsCount() {
		return _cProductLocalService.getCProductsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _cProductLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cProductLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cProductLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int increment(long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.increment(cProductId);
	}

	/**
	 * Updates the c product in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was updated
	 */
	@Override
	public com.liferay.commerce.product.model.CProduct updateCProduct(
		com.liferay.commerce.product.model.CProduct cProduct) {

		return _cProductLocalService.updateCProduct(cProduct);
	}

	@Override
	public com.liferay.commerce.product.model.CProduct
			updatePublishedCPDefinitionId(
				long cProductId, long publishedCPDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cProductLocalService.updatePublishedCPDefinitionId(
			cProductId, publishedCPDefinitionId);
	}

	@Override
	public CProductLocalService getWrappedService() {
		return _cProductLocalService;
	}

	@Override
	public void setWrappedService(CProductLocalService cProductLocalService) {
		_cProductLocalService = cProductLocalService;
	}

	private CProductLocalService _cProductLocalService;

}