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

package com.liferay.commerce.discount.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.model.CommerceDiscountUserSegmentRel;
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
 * Provides the local service interface for CommerceDiscountUserSegmentRel. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marco Leo
 * @see CommerceDiscountUserSegmentRelLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceDiscountUserSegmentRelLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDiscountUserSegmentRelLocalServiceUtil} to access the commerce discount user segment rel local service. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountUserSegmentRelLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the commerce discount user segment rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CommerceDiscountUserSegmentRel addCommerceDiscountUserSegmentRel(
		CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel);

	public CommerceDiscountUserSegmentRel addCommerceDiscountUserSegmentRel(
			long commerceDiscountId, long commerceUserSegmentEntryId,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new commerce discount user segment rel with the primary key. Does not add the commerce discount user segment rel to the database.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key for the new commerce discount user segment rel
	 * @return the new commerce discount user segment rel
	 */
	@Transactional(enabled = false)
	public CommerceDiscountUserSegmentRel createCommerceDiscountUserSegmentRel(
		long commerceDiscountUserSegmentRelId);

	/**
	 * Deletes the commerce discount user segment rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CommerceDiscountUserSegmentRel deleteCommerceDiscountUserSegmentRel(
		CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel);

	/**
	 * Deletes the commerce discount user segment rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key of the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was removed
	 * @throws PortalException if a commerce discount user segment rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CommerceDiscountUserSegmentRel deleteCommerceDiscountUserSegmentRel(
			long commerceDiscountUserSegmentRelId)
		throws PortalException;

	public void deleteCommerceDiscountUserSegmentRelsByCommerceDiscountId(
		long commerceDiscountId);

	public void
		deleteCommerceDiscountUserSegmentRelsByCommerceUserSegmentEntryId(
			long commerceUserSegmentEntryId);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

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
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDiscountUserSegmentRel fetchCommerceDiscountUserSegmentRel(
		long commerceDiscountUserSegmentRelId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the commerce discount user segment rel with the primary key.
	 *
	 * @param commerceDiscountUserSegmentRelId the primary key of the commerce discount user segment rel
	 * @return the commerce discount user segment rel
	 * @throws PortalException if a commerce discount user segment rel with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDiscountUserSegmentRel getCommerceDiscountUserSegmentRel(
			long commerceDiscountUserSegmentRelId)
		throws PortalException;

	/**
	 * Returns a range of all the commerce discount user segment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.discount.model.impl.CommerceDiscountUserSegmentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount user segment rels
	 * @param end the upper bound of the range of commerce discount user segment rels (not inclusive)
	 * @return the range of commerce discount user segment rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceDiscountUserSegmentRel>
		getCommerceDiscountUserSegmentRels(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceDiscountUserSegmentRel>
		getCommerceDiscountUserSegmentRels(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountUserSegmentRel>
				orderByComparator);

	/**
	 * Returns the number of commerce discount user segment rels.
	 *
	 * @return the number of commerce discount user segment rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceDiscountUserSegmentRelsCount();

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
	 * Updates the commerce discount user segment rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountUserSegmentRel the commerce discount user segment rel
	 * @return the commerce discount user segment rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CommerceDiscountUserSegmentRel updateCommerceDiscountUserSegmentRel(
		CommerceDiscountUserSegmentRel commerceDiscountUserSegmentRel);

}