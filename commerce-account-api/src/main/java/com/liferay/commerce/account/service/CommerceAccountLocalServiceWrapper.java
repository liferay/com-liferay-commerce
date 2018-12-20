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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAccountLocalService}.
 *
 * @author Marco Leo
 * @see CommerceAccountLocalService
 * @generated
 */
@ProviderType
public class CommerceAccountLocalServiceWrapper
	implements CommerceAccountLocalService,
		ServiceWrapper<CommerceAccountLocalService> {
	public CommerceAccountLocalServiceWrapper(
		CommerceAccountLocalService commerceAccountLocalService) {
		_commerceAccountLocalService = commerceAccountLocalService;
	}

	/**
	* Adds the commerce account to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccount the commerce account
	* @return the commerce account that was added
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		com.liferay.commerce.account.model.CommerceAccount commerceAccount) {
		return _commerceAccountLocalService.addCommerceAccount(commerceAccount);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		long userId, long parentCommerceAccountId, String name, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.addCommerceAccount(userId,
			parentCommerceAccountId, name, taxId, active,
			externalReferenceCode, serviceContext);
	}

	/**
	* Creates a new commerce account with the primary key. Does not add the commerce account to the database.
	*
	* @param commerceAccountId the primary key for the new commerce account
	* @return the new commerce account
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount createCommerceAccount(
		long commerceAccountId) {
		return _commerceAccountLocalService.createCommerceAccount(commerceAccountId);
	}

	/**
	* Deletes the commerce account from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccount the commerce account
	* @return the commerce account that was removed
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount deleteCommerceAccount(
		com.liferay.commerce.account.model.CommerceAccount commerceAccount) {
		return _commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
	}

	/**
	* Deletes the commerce account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account that was removed
	* @throws PortalException if a commerce account with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount deleteCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.deleteCommerceAccount(commerceAccountId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceAccountLocalService.dynamicQuery();
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
		return _commerceAccountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceAccountLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commerceAccountLocalService.dynamicQuery(dynamicQuery, start,
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
		return _commerceAccountLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commerceAccountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount fetchCommerceAccount(
		long commerceAccountId) {
		return _commerceAccountLocalService.fetchCommerceAccount(commerceAccountId);
	}

	/**
	* Returns the commerce account with the matching external reference code and company.
	*
	* @param companyId the primary key of the company
	* @param externalReferenceCode the commerce account's external reference code
	* @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount fetchCommerceAccountByReferenceCode(
		long companyId, String externalReferenceCode) {
		return _commerceAccountLocalService.fetchCommerceAccountByReferenceCode(companyId,
			externalReferenceCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceAccountLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the commerce account with the primary key.
	*
	* @param commerceAccountId the primary key of the commerce account
	* @return the commerce account
	* @throws PortalException if a commerce account with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount getCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.getCommerceAccount(commerceAccountId);
	}

	/**
	* Returns a range of all the commerce accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce accounts
	* @param end the upper bound of the range of commerce accounts (not inclusive)
	* @return the range of commerce accounts
	*/
	@Override
	public java.util.List<com.liferay.commerce.account.model.CommerceAccount> getCommerceAccounts(
		int start, int end) {
		return _commerceAccountLocalService.getCommerceAccounts(start, end);
	}

	/**
	* Returns the number of commerce accounts.
	*
	* @return the number of commerce accounts
	*/
	@Override
	public int getCommerceAccountsCount() {
		return _commerceAccountLocalService.getCommerceAccountsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceAccountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAccountLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		long companyId, long parentCommerceAccountId, String keywords,
		Boolean active, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.searchCommerceAccounts(companyId,
			parentCommerceAccountId, keywords, active, start, end, sort);
	}

	/**
	* Updates the commerce account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceAccount the commerce account
	* @return the commerce account that was updated
	*/
	@Override
	public com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		com.liferay.commerce.account.model.CommerceAccount commerceAccount) {
		return _commerceAccountLocalService.updateCommerceAccount(commerceAccount);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		long commerceAccountId, String name, String taxId, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.updateCommerceAccount(commerceAccountId,
			name, taxId, active, serviceContext);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		long userId, long parentCommerceAccountId, String name, String taxId,
		boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceAccountLocalService.upsertCommerceAccount(userId,
			parentCommerceAccountId, name, taxId, active,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CommerceAccountLocalService getWrappedService() {
		return _commerceAccountLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceAccountLocalService commerceAccountLocalService) {
		_commerceAccountLocalService = commerceAccountLocalService;
	}

	private CommerceAccountLocalService _commerceAccountLocalService;
}