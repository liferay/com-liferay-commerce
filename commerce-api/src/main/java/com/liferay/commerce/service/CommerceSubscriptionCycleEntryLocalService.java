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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CommerceSubscriptionCycleEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryLocalServiceUtil
 * @see com.liferay.commerce.service.base.CommerceSubscriptionCycleEntryLocalServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceSubscriptionCycleEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceSubscriptionCycleEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceSubscriptionCycleEntryLocalServiceUtil} to access the commerce subscription cycle entry local service. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceSubscriptionCycleEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the commerce subscription cycle entry to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry);

	public CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry(
		long commerceSubscriptionEntryId, long commerceOrderItemId,
		boolean renew) throws PortalException;

	/**
	* Creates a new commerce subscription cycle entry with the primary key. Does not add the commerce subscription cycle entry to the database.
	*
	* @param commerceSubscriptionCycleEntryId the primary key for the new commerce subscription cycle entry
	* @return the new commerce subscription cycle entry
	*/
	@Transactional(enabled = false)
	public CommerceSubscriptionCycleEntry createCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId);

	/**
	* Deletes the commerce subscription cycle entry from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceSubscriptionCycleEntry deleteCommerceSubscriptionCycleEntry(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry);

	/**
	* Deletes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was removed
	* @throws PortalException if a commerce subscription cycle entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CommerceSubscriptionCycleEntry deleteCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntryByCommerceOrderItemId(
		long commerceOrderItemId);

	/**
	* Returns the commerce subscription cycle entry matching the UUID and group.
	*
	* @param uuid the commerce subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceSubscriptionCycleEntry fetchCommerceSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns a range of all the commerce subscription cycle entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @return the range of commerce subscription cycle entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntries(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntries(
		long commerceSubscriptionEntryId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns all the commerce subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the commerce subscription cycle entries
	* @param companyId the primary key of the company
	* @return the matching commerce subscription cycle entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of commerce subscription cycle entries matching the UUID and company.
	*
	* @param uuid the UUID of the commerce subscription cycle entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of commerce subscription cycle entries
	* @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching commerce subscription cycle entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceSubscriptionCycleEntry> getCommerceSubscriptionCycleEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator);

	/**
	* Returns the number of commerce subscription cycle entries.
	*
	* @return the number of commerce subscription cycle entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceSubscriptionCycleEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceSubscriptionCycleEntriesCount(
		long commerceSubscriptionEntryId);

	/**
	* Returns the commerce subscription cycle entry with the primary key.
	*
	* @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	* @return the commerce subscription cycle entry
	* @throws PortalException if a commerce subscription cycle entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceSubscriptionCycleEntry getCommerceSubscriptionCycleEntry(
		long commerceSubscriptionCycleEntryId) throws PortalException;

	/**
	* Returns the commerce subscription cycle entry matching the UUID and group.
	*
	* @param uuid the commerce subscription cycle entry's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce subscription cycle entry
	* @throws PortalException if a matching commerce subscription cycle entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceSubscriptionCycleEntry getCommerceSubscriptionCycleEntryByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Updates the commerce subscription cycle entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	* @return the commerce subscription cycle entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CommerceSubscriptionCycleEntry updateCommerceSubscriptionCycleEntry(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry);
}