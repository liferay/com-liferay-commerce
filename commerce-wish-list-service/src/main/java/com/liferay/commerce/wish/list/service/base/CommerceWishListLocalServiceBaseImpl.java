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

package com.liferay.commerce.wish.list.service.base;

import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.commerce.wish.list.service.CommerceWishListLocalService;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListItemPersistence;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce wish list local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.wish.list.service.impl.CommerceWishListLocalServiceImpl}.
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see com.liferay.commerce.wish.list.service.impl.CommerceWishListLocalServiceImpl
 * @generated
 */
public abstract class CommerceWishListLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceWishListLocalService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceWishListLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.wish.list.service.CommerceWishListLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce wish list to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishList the commerce wish list
	 * @return the commerce wish list that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceWishList addCommerceWishList(
		CommerceWishList commerceWishList) {

		commerceWishList.setNew(true);

		return commerceWishListPersistence.update(commerceWishList);
	}

	/**
	 * Creates a new commerce wish list with the primary key. Does not add the commerce wish list to the database.
	 *
	 * @param commerceWishListId the primary key for the new commerce wish list
	 * @return the new commerce wish list
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceWishList createCommerceWishList(long commerceWishListId) {
		return commerceWishListPersistence.create(commerceWishListId);
	}

	/**
	 * Deletes the commerce wish list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishListId the primary key of the commerce wish list
	 * @return the commerce wish list that was removed
	 * @throws PortalException if a commerce wish list with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceWishList deleteCommerceWishList(long commerceWishListId)
		throws PortalException {

		return commerceWishListPersistence.remove(commerceWishListId);
	}

	/**
	 * Deletes the commerce wish list from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishList the commerce wish list
	 * @return the commerce wish list that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceWishList deleteCommerceWishList(
		CommerceWishList commerceWishList) {

		return commerceWishListPersistence.remove(commerceWishList);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceWishList.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceWishListPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return commerceWishListPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return commerceWishListPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return commerceWishListPersistence.countWithDynamicQuery(dynamicQuery);
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
		DynamicQuery dynamicQuery, Projection projection) {

		return commerceWishListPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceWishList fetchCommerceWishList(long commerceWishListId) {
		return commerceWishListPersistence.fetchByPrimaryKey(
			commerceWishListId);
	}

	/**
	 * Returns the commerce wish list matching the UUID and group.
	 *
	 * @param uuid the commerce wish list's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce wish list, or <code>null</code> if a matching commerce wish list could not be found
	 */
	@Override
	public CommerceWishList fetchCommerceWishListByUuidAndGroupId(
		String uuid, long groupId) {

		return commerceWishListPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce wish list with the primary key.
	 *
	 * @param commerceWishListId the primary key of the commerce wish list
	 * @return the commerce wish list
	 * @throws PortalException if a commerce wish list with the primary key could not be found
	 */
	@Override
	public CommerceWishList getCommerceWishList(long commerceWishListId)
		throws PortalException {

		return commerceWishListPersistence.findByPrimaryKey(commerceWishListId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceWishListLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceWishList.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceWishListId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceWishListLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceWishList.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceWishListId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceWishListLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceWishList.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceWishListId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CommerceWishList>() {

				@Override
				public void performAction(CommerceWishList commerceWishList)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commerceWishList);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CommerceWishList.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceWishListLocalService.deleteCommerceWishList(
			(CommerceWishList)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceWishListPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the commerce wish lists matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce wish lists
	 * @param companyId the primary key of the company
	 * @return the matching commerce wish lists, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceWishList> getCommerceWishListsByUuidAndCompanyId(
		String uuid, long companyId) {

		return commerceWishListPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of commerce wish lists matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce wish lists
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce wish lists, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceWishList> getCommerceWishListsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceWishList> orderByComparator) {

		return commerceWishListPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce wish list matching the UUID and group.
	 *
	 * @param uuid the commerce wish list's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce wish list
	 * @throws PortalException if a matching commerce wish list could not be found
	 */
	@Override
	public CommerceWishList getCommerceWishListByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return commerceWishListPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce wish lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish lists
	 * @param end the upper bound of the range of commerce wish lists (not inclusive)
	 * @return the range of commerce wish lists
	 */
	@Override
	public List<CommerceWishList> getCommerceWishLists(int start, int end) {
		return commerceWishListPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce wish lists.
	 *
	 * @return the number of commerce wish lists
	 */
	@Override
	public int getCommerceWishListsCount() {
		return commerceWishListPersistence.countAll();
	}

	/**
	 * Updates the commerce wish list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWishList the commerce wish list
	 * @return the commerce wish list that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceWishList updateCommerceWishList(
		CommerceWishList commerceWishList) {

		return commerceWishListPersistence.update(commerceWishList);
	}

	/**
	 * Returns the commerce wish list local service.
	 *
	 * @return the commerce wish list local service
	 */
	public CommerceWishListLocalService getCommerceWishListLocalService() {
		return commerceWishListLocalService;
	}

	/**
	 * Sets the commerce wish list local service.
	 *
	 * @param commerceWishListLocalService the commerce wish list local service
	 */
	public void setCommerceWishListLocalService(
		CommerceWishListLocalService commerceWishListLocalService) {

		this.commerceWishListLocalService = commerceWishListLocalService;
	}

	/**
	 * Returns the commerce wish list persistence.
	 *
	 * @return the commerce wish list persistence
	 */
	public CommerceWishListPersistence getCommerceWishListPersistence() {
		return commerceWishListPersistence;
	}

	/**
	 * Sets the commerce wish list persistence.
	 *
	 * @param commerceWishListPersistence the commerce wish list persistence
	 */
	public void setCommerceWishListPersistence(
		CommerceWishListPersistence commerceWishListPersistence) {

		this.commerceWishListPersistence = commerceWishListPersistence;
	}

	/**
	 * Returns the commerce wish list item local service.
	 *
	 * @return the commerce wish list item local service
	 */
	public
		com.liferay.commerce.wish.list.service.CommerceWishListItemLocalService
			getCommerceWishListItemLocalService() {

		return commerceWishListItemLocalService;
	}

	/**
	 * Sets the commerce wish list item local service.
	 *
	 * @param commerceWishListItemLocalService the commerce wish list item local service
	 */
	public void setCommerceWishListItemLocalService(
		com.liferay.commerce.wish.list.service.CommerceWishListItemLocalService
			commerceWishListItemLocalService) {

		this.commerceWishListItemLocalService =
			commerceWishListItemLocalService;
	}

	/**
	 * Returns the commerce wish list item persistence.
	 *
	 * @return the commerce wish list item persistence
	 */
	public CommerceWishListItemPersistence
		getCommerceWishListItemPersistence() {

		return commerceWishListItemPersistence;
	}

	/**
	 * Sets the commerce wish list item persistence.
	 *
	 * @param commerceWishListItemPersistence the commerce wish list item persistence
	 */
	public void setCommerceWishListItemPersistence(
		CommerceWishListItemPersistence commerceWishListItemPersistence) {

		this.commerceWishListItemPersistence = commerceWishListItemPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.wish.list.model.CommerceWishList",
			commerceWishListLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.wish.list.model.CommerceWishList");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceWishListLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceWishList.class;
	}

	protected String getModelClassName() {
		return CommerceWishList.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceWishListPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = CommerceWishListLocalService.class)
	protected CommerceWishListLocalService commerceWishListLocalService;

	@BeanReference(type = CommerceWishListPersistence.class)
	protected CommerceWishListPersistence commerceWishListPersistence;

	@BeanReference(
		type = com.liferay.commerce.wish.list.service.CommerceWishListItemLocalService.class
	)
	protected
		com.liferay.commerce.wish.list.service.CommerceWishListItemLocalService
			commerceWishListItemLocalService;

	@BeanReference(type = CommerceWishListItemPersistence.class)
	protected CommerceWishListItemPersistence commerceWishListItemPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}