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
 * Provides a wrapper for {@link CPRuleChannelRelLocalService}.
 *
 * @author Marco Leo
 * @see CPRuleChannelRelLocalService
 * @generated
 */
@ProviderType
public class CPRuleChannelRelLocalServiceWrapper
	implements CPRuleChannelRelLocalService,
		ServiceWrapper<CPRuleChannelRelLocalService> {
	public CPRuleChannelRelLocalServiceWrapper(
		CPRuleChannelRelLocalService cpRuleChannelRelLocalService) {
		_cpRuleChannelRelLocalService = cpRuleChannelRelLocalService;
	}

	/**
	* Adds the cp rule channel rel to the database. Also notifies the appropriate model listeners.
	*
	* @param cpRuleChannelRel the cp rule channel rel
	* @return the cp rule channel rel that was added
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel addCPRuleChannelRel(
		com.liferay.commerce.product.model.CPRuleChannelRel cpRuleChannelRel) {
		return _cpRuleChannelRelLocalService.addCPRuleChannelRel(cpRuleChannelRel);
	}

	/**
	* Creates a new cp rule channel rel with the primary key. Does not add the cp rule channel rel to the database.
	*
	* @param CPRuleChannelRelId the primary key for the new cp rule channel rel
	* @return the new cp rule channel rel
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel createCPRuleChannelRel(
		long CPRuleChannelRelId) {
		return _cpRuleChannelRelLocalService.createCPRuleChannelRel(CPRuleChannelRelId);
	}

	/**
	* Deletes the cp rule channel rel from the database. Also notifies the appropriate model listeners.
	*
	* @param cpRuleChannelRel the cp rule channel rel
	* @return the cp rule channel rel that was removed
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel deleteCPRuleChannelRel(
		com.liferay.commerce.product.model.CPRuleChannelRel cpRuleChannelRel) {
		return _cpRuleChannelRelLocalService.deleteCPRuleChannelRel(cpRuleChannelRel);
	}

	/**
	* Deletes the cp rule channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel that was removed
	* @throws PortalException if a cp rule channel rel with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel deleteCPRuleChannelRel(
		long CPRuleChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleChannelRelLocalService.deleteCPRuleChannelRel(CPRuleChannelRelId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleChannelRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpRuleChannelRelLocalService.dynamicQuery();
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
		return _cpRuleChannelRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpRuleChannelRelLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpRuleChannelRelLocalService.dynamicQuery(dynamicQuery, start,
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
		return _cpRuleChannelRelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cpRuleChannelRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel fetchCPRuleChannelRel(
		long CPRuleChannelRelId) {
		return _cpRuleChannelRelLocalService.fetchCPRuleChannelRel(CPRuleChannelRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cpRuleChannelRelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cp rule channel rel with the primary key.
	*
	* @param CPRuleChannelRelId the primary key of the cp rule channel rel
	* @return the cp rule channel rel
	* @throws PortalException if a cp rule channel rel with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel getCPRuleChannelRel(
		long CPRuleChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleChannelRelLocalService.getCPRuleChannelRel(CPRuleChannelRelId);
	}

	/**
	* Returns a range of all the cp rule channel rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule channel rels
	* @param end the upper bound of the range of cp rule channel rels (not inclusive)
	* @return the range of cp rule channel rels
	*/
	@Override
	public java.util.List<com.liferay.commerce.product.model.CPRuleChannelRel> getCPRuleChannelRels(
		int start, int end) {
		return _cpRuleChannelRelLocalService.getCPRuleChannelRels(start, end);
	}

	/**
	* Returns the number of cp rule channel rels.
	*
	* @return the number of cp rule channel rels
	*/
	@Override
	public int getCPRuleChannelRelsCount() {
		return _cpRuleChannelRelLocalService.getCPRuleChannelRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cpRuleChannelRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpRuleChannelRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleChannelRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cp rule channel rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpRuleChannelRel the cp rule channel rel
	* @return the cp rule channel rel that was updated
	*/
	@Override
	public com.liferay.commerce.product.model.CPRuleChannelRel updateCPRuleChannelRel(
		com.liferay.commerce.product.model.CPRuleChannelRel cpRuleChannelRel) {
		return _cpRuleChannelRelLocalService.updateCPRuleChannelRel(cpRuleChannelRel);
	}

	@Override
	public CPRuleChannelRelLocalService getWrappedService() {
		return _cpRuleChannelRelLocalService;
	}

	@Override
	public void setWrappedService(
		CPRuleChannelRelLocalService cpRuleChannelRelLocalService) {
		_cpRuleChannelRelLocalService = cpRuleChannelRelLocalService;
	}

	private CPRuleChannelRelLocalService _cpRuleChannelRelLocalService;
}