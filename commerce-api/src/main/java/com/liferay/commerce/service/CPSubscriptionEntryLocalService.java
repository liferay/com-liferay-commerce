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

import com.liferay.commerce.model.CPSubscriptionEntry;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
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
 * Provides the local service interface for CPSubscriptionEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryLocalServiceUtil
 * @see com.liferay.commerce.service.base.CPSubscriptionEntryLocalServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CPSubscriptionEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CPSubscriptionEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPSubscriptionEntryLocalServiceUtil} to access the cp subscription entry local service. Add custom service methods to {@link com.liferay.commerce.service.impl.CPSubscriptionEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the cp subscription entry to the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CPSubscriptionEntry addCPSubscriptionEntry(
		CPSubscriptionEntry cpSubscriptionEntry);

	@Indexable(type = IndexableType.REINDEX)
	public CPSubscriptionEntry addCPSubscriptionEntry(long cpInstanceId,
		long commerceOrderItemId, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new cp subscription entry with the primary key. Does not add the cp subscription entry to the database.
	*
	* @param CPSubscriptionEntryId the primary key for the new cp subscription entry
	* @return the new cp subscription entry
	*/
	@Transactional(enabled = false)
	public CPSubscriptionEntry createCPSubscriptionEntry(
		long CPSubscriptionEntryId);

	/**
	* Deletes the cp subscription entry from the database. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CPSubscriptionEntry deleteCPSubscriptionEntry(
		CPSubscriptionEntry cpSubscriptionEntry);

	/**
	* Deletes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry that was removed
	* @throws PortalException if a cp subscription entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CPSubscriptionEntry deleteCPSubscriptionEntry(
		long CPSubscriptionEntryId) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPSubscriptionEntry fetchCPSubscriptionEntry(
		long CPSubscriptionEntryId);

	/**
	* Returns the cp subscription entry matching the UUID and group.
	*
	* @param uuid the cp subscription entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPSubscriptionEntry fetchCPSubscriptionEntryByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getActiveCPSubscriptionEntries();

	/**
	* Returns a range of all the cp subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @return the range of cp subscription entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getCPSubscriptionEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getCPSubscriptionEntries(long groupId,
		long userId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns all the cp subscription entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription entries
	* @param companyId the primary key of the company
	* @return the matching cp subscription entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getCPSubscriptionEntriesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of cp subscription entries matching the UUID and company.
	*
	* @param uuid the UUID of the cp subscription entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of cp subscription entries
	* @param end the upper bound of the range of cp subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching cp subscription entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getCPSubscriptionEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator);

	/**
	* Returns the number of cp subscription entries.
	*
	* @return the number of cp subscription entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCPSubscriptionEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCPSubscriptionEntriesCount(long groupId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPSubscriptionEntry> getCPSubscriptionEntriesToRenew();

	/**
	* Returns the cp subscription entry with the primary key.
	*
	* @param CPSubscriptionEntryId the primary key of the cp subscription entry
	* @return the cp subscription entry
	* @throws PortalException if a cp subscription entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPSubscriptionEntry getCPSubscriptionEntry(
		long CPSubscriptionEntryId) throws PortalException;

	/**
	* Returns the cp subscription entry matching the UUID and group.
	*
	* @param uuid the cp subscription entry's UUID
	* @param groupId the primary key of the group
	* @return the matching cp subscription entry
	* @throws PortalException if a matching cp subscription entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPSubscriptionEntry getCPSubscriptionEntryByUuidAndGroupId(
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CPSubscriptionEntry> searchCPSubscriptionEntries(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, Sort sort) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public CPSubscriptionEntry setActive(long cpSubscriptionEntryId,
		boolean active) throws PortalException;

	/**
	* Updates the cp subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpSubscriptionEntry the cp subscription entry
	* @return the cp subscription entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CPSubscriptionEntry updateCPSubscriptionEntry(
		CPSubscriptionEntry cpSubscriptionEntry);

	@Indexable(type = IndexableType.REINDEX)
	public CPSubscriptionEntry updateCPSubscriptionEntry(
		long cpSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active) throws PortalException;
}