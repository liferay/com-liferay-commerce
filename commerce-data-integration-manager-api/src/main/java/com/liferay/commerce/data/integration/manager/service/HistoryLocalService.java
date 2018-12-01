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

package com.liferay.commerce.data.integration.manager.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.model.History;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for History. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see HistoryLocalServiceUtil
 * @see com.liferay.commerce.data.integration.manager.service.base.HistoryLocalServiceBaseImpl
 * @see com.liferay.commerce.data.integration.manager.service.impl.HistoryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface HistoryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistoryLocalServiceUtil} to access the history local service. Add custom service methods to {@link com.liferay.commerce.data.integration.manager.service.impl.HistoryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the history to the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public History addHistory(History history);

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link HistoryLocalServiceUtil} to access the history local service.
	*
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public History addHistory(long userId, long scheduledTaskId,
		String executionType, Date startDate, Date endDate, int status,
		long errorLogFileEntryId, long runtimeLogFileEntryId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public History addHistory(long scheduledTaskId, String executionType,
		int status, ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new history with the primary key. Does not add the history to the database.
	*
	* @param historyId the primary key for the new history
	* @return the new history
	*/
	@Transactional(enabled = false)
	public History createHistory(long historyId);

	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public History delete(long historyId) throws PortalException;

	/**
	* Deletes the history from the database. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public History deleteHistory(History history);

	/**
	* Deletes the history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param historyId the primary key of the history
	* @return the history that was removed
	* @throws PortalException if a history with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public History deleteHistory(long historyId) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public History fetchHistory(long historyId);

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history, or <code>null</code> if a matching history could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public History fetchHistoryByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	* Returns a range of all the histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.data.integration.manager.model.impl.HistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @return the range of histories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<History> getHistories(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<History> getHistoriesByGoupId(long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getHistoriesByGoupIdCount(long groupId);

	/**
	* Returns all the histories matching the UUID and company.
	*
	* @param uuid the UUID of the histories
	* @param companyId the primary key of the company
	* @return the matching histories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<History> getHistoriesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of histories matching the UUID and company.
	*
	* @param uuid the UUID of the histories
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of histories
	* @param end the upper bound of the range of histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching histories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<History> getHistoriesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<History> orderByComparator);

	/**
	* Returns the number of histories.
	*
	* @return the number of histories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getHistoriesCount();

	/**
	* Returns the history with the primary key.
	*
	* @param historyId the primary key of the history
	* @return the history
	* @throws PortalException if a history with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public History getHistory(long historyId) throws PortalException;

	/**
	* Returns the history matching the UUID and group.
	*
	* @param uuid the history's UUID
	* @param groupId the primary key of the group
	* @return the matching history
	* @throws PortalException if a matching history could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public History getHistoryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	* Updates the history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param history the history
	* @return the history that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public History updateHistory(History history);

	@Indexable(type = IndexableType.REINDEX)
	public History updateHistory(long historyId, long scheduledTaskId,
		String executionType, int status, ServiceContext serviceContext)
		throws PortalException;
}