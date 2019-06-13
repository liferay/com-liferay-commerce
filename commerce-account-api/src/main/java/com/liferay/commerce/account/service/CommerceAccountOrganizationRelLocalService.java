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

import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CommerceAccountOrganizationRel. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marco Leo
 * @see CommerceAccountOrganizationRelLocalServiceUtil
 * @see com.liferay.commerce.account.service.base.CommerceAccountOrganizationRelLocalServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountOrganizationRelLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceAccountOrganizationRelLocalService
	extends BaseLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountOrganizationRelLocalServiceUtil} to access the commerce account organization rel local service. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountOrganizationRelLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the commerce account organization rel to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountOrganizationRel the commerce account organization rel
	* @return the commerce account organization rel that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceAccountOrganizationRel addCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel);

	public CommerceAccountOrganizationRel addCommerceAccountOrganizationRel(
		long commerceAccountId, long organizationId,
		ServiceContext serviceContext) throws PortalException;

	public void addCommerceAccountOrganizationRels(long commerceAccountId,
		long[] organizationIds, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new commerce account organization rel with the primary key. Does not add the commerce account organization rel to the database.
	*
	* @param commerceAccountOrganizationRelPK the primary key for the new commerce account organization rel
	* @return the new commerce account organization rel
	*/
	@Transactional(enabled = false)
	public CommerceAccountOrganizationRel createCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK);

	/**
	* Deletes the commerce account organization rel from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountOrganizationRel the commerce account organization rel
	* @return the commerce account organization rel that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceAccountOrganizationRel deleteCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel);

	/**
	* Deletes the commerce account organization rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	* @return the commerce account organization rel that was removed
	* @throws PortalException if a commerce account organization rel with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceAccountOrganizationRel deleteCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK)
		throws PortalException;

	public void deleteCommerceAccountOrganizationRels(long commerceAccountId,
		long[] organizationIds) throws PortalException;

	public void deleteCommerceAccountOrganizationRelsByCommerceAccountId(
		long commerceAccountId);

	public void deleteCommerceAccountOrganizationRelsByOrganizationId(
		long organizationId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceAccountOrganizationRel fetchCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the commerce account organization rel with the primary key.
	*
	* @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	* @return the commerce account organization rel
	* @throws PortalException if a commerce account organization rel with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceAccountOrganizationRel getCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK)
		throws PortalException;

	/**
	* Returns a range of all the commerce account organization rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce account organization rels
	* @param end the upper bound of the range of commerce account organization rels (not inclusive)
	* @return the range of commerce account organization rels
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccountOrganizationRel> getCommerceAccountOrganizationRels(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccountOrganizationRel> getCommerceAccountOrganizationRels(
		long commerceAccountId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccountOrganizationRel> getCommerceAccountOrganizationRels(
		long commerceAccountId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccountOrganizationRel> getCommerceAccountOrganizationRelsByOrganizationId(
		long organizationId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceAccountOrganizationRelsByOrganizationIdCount(
		long organizationId);

	/**
	* Returns the number of commerce account organization rels.
	*
	* @return the number of commerce account organization rels
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceAccountOrganizationRelsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceAccountOrganizationRelsCount(long commerceAccountId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the commerce account organization rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceAccountOrganizationRel the commerce account organization rel
	* @return the commerce account organization rel that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceAccountOrganizationRel updateCommerceAccountOrganizationRel(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel);
}