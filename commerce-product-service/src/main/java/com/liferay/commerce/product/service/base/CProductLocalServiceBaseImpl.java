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

package com.liferay.commerce.product.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.service.persistence.CPAttachmentFileEntryFinder;
import com.liferay.commerce.product.service.persistence.CPAttachmentFileEntryPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionFinder;
import com.liferay.commerce.product.service.persistence.CPDefinitionLinkPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionLocalizationPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionOptionRelPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionOptionValueRelPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionSpecificationOptionValuePersistence;
import com.liferay.commerce.product.service.persistence.CPDisplayLayoutPersistence;
import com.liferay.commerce.product.service.persistence.CPFriendlyURLEntryPersistence;
import com.liferay.commerce.product.service.persistence.CPInstanceFinder;
import com.liferay.commerce.product.service.persistence.CPInstancePersistence;
import com.liferay.commerce.product.service.persistence.CPMeasurementUnitPersistence;
import com.liferay.commerce.product.service.persistence.CPOptionCategoryPersistence;
import com.liferay.commerce.product.service.persistence.CPOptionPersistence;
import com.liferay.commerce.product.service.persistence.CPOptionValuePersistence;
import com.liferay.commerce.product.service.persistence.CPRuleAssetCategoryRelPersistence;
import com.liferay.commerce.product.service.persistence.CPRuleCommerceAccountGroupRelPersistence;
import com.liferay.commerce.product.service.persistence.CPRulePersistence;
import com.liferay.commerce.product.service.persistence.CPSpecificationOptionPersistence;
import com.liferay.commerce.product.service.persistence.CPTaxCategoryPersistence;
import com.liferay.commerce.product.service.persistence.CProductPersistence;
import com.liferay.commerce.product.service.persistence.CommerceCatalogPersistence;
import com.liferay.commerce.product.service.persistence.CommerceChannelPersistence;

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
 * Provides the base implementation for the c product local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.service.impl.CProductLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.impl.CProductLocalServiceImpl
 * @see com.liferay.commerce.product.service.CProductLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CProductLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements CProductLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.product.service.CProductLocalServiceUtil} to access the c product local service.
	 */

	/**
	 * Adds the c product to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CProduct addCProduct(CProduct cProduct) {
		cProduct.setNew(true);

		return cProductPersistence.update(cProduct);
	}

	/**
	 * Creates a new c product with the primary key. Does not add the c product to the database.
	 *
	 * @param CProductId the primary key for the new c product
	 * @return the new c product
	 */
	@Override
	@Transactional(enabled = false)
	public CProduct createCProduct(long CProductId) {
		return cProductPersistence.create(CProductId);
	}

	/**
	 * Deletes the c product with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CProductId the primary key of the c product
	 * @return the c product that was removed
	 * @throws PortalException if a c product with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CProduct deleteCProduct(long CProductId) throws PortalException {
		return cProductPersistence.remove(CProductId);
	}

	/**
	 * Deletes the c product from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CProduct deleteCProduct(CProduct cProduct) {
		return cProductPersistence.remove(cProduct);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CProduct.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cProductPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CProductModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return cProductPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CProductModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return cProductPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return cProductPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return cProductPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CProduct fetchCProduct(long CProductId) {
		return cProductPersistence.fetchByPrimaryKey(CProductId);
	}

	/**
	 * Returns the c product matching the UUID and group.
	 *
	 * @param uuid the c product's UUID
	 * @param groupId the primary key of the group
	 * @return the matching c product, or <code>null</code> if a matching c product could not be found
	 */
	@Override
	public CProduct fetchCProductByUuidAndGroupId(String uuid, long groupId) {
		return cProductPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the c product with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the c product's external reference code
	 * @return the matching c product, or <code>null</code> if a matching c product could not be found
	 */
	@Override
	public CProduct fetchCProductByReferenceCode(long companyId,
		String externalReferenceCode) {
		return cProductPersistence.fetchByC_ERC(companyId, null);
	}

	/**
	 * Returns the c product with the primary key.
	 *
	 * @param CProductId the primary key of the c product
	 * @return the c product
	 * @throws PortalException if a c product with the primary key could not be found
	 */
	@Override
	public CProduct getCProduct(long CProductId) throws PortalException {
		return cProductPersistence.findByPrimaryKey(CProductId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(cProductLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CProduct.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CProductId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(cProductLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CProduct.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("CProductId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(cProductLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CProduct.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CProductId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CProduct>() {
				@Override
				public void performAction(CProduct cProduct)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						cProduct);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(CProduct.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return cProductLocalService.deleteCProduct((CProduct)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return cProductPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the c products matching the UUID and company.
	 *
	 * @param uuid the UUID of the c products
	 * @param companyId the primary key of the company
	 * @return the matching c products, or an empty list if no matches were found
	 */
	@Override
	public List<CProduct> getCProductsByUuidAndCompanyId(String uuid,
		long companyId) {
		return cProductPersistence.findByUuid_C(uuid, companyId);
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
	public List<CProduct> getCProductsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CProduct> orderByComparator) {
		return cProductPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
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
	public CProduct getCProductByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return cProductPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the c products.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CProductModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of c products
	 * @param end the upper bound of the range of c products (not inclusive)
	 * @return the range of c products
	 */
	@Override
	public List<CProduct> getCProducts(int start, int end) {
		return cProductPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of c products.
	 *
	 * @return the number of c products
	 */
	@Override
	public int getCProductsCount() {
		return cProductPersistence.countAll();
	}

	/**
	 * Updates the c product in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cProduct the c product
	 * @return the c product that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CProduct updateCProduct(CProduct cProduct) {
		return cProductPersistence.update(cProduct);
	}

	/**
	 * Returns the commerce catalog local service.
	 *
	 * @return the commerce catalog local service
	 */
	public com.liferay.commerce.product.service.CommerceCatalogLocalService getCommerceCatalogLocalService() {
		return commerceCatalogLocalService;
	}

	/**
	 * Sets the commerce catalog local service.
	 *
	 * @param commerceCatalogLocalService the commerce catalog local service
	 */
	public void setCommerceCatalogLocalService(
		com.liferay.commerce.product.service.CommerceCatalogLocalService commerceCatalogLocalService) {
		this.commerceCatalogLocalService = commerceCatalogLocalService;
	}

	/**
	 * Returns the commerce catalog persistence.
	 *
	 * @return the commerce catalog persistence
	 */
	public CommerceCatalogPersistence getCommerceCatalogPersistence() {
		return commerceCatalogPersistence;
	}

	/**
	 * Sets the commerce catalog persistence.
	 *
	 * @param commerceCatalogPersistence the commerce catalog persistence
	 */
	public void setCommerceCatalogPersistence(
		CommerceCatalogPersistence commerceCatalogPersistence) {
		this.commerceCatalogPersistence = commerceCatalogPersistence;
	}

	/**
	 * Returns the commerce channel local service.
	 *
	 * @return the commerce channel local service
	 */
	public com.liferay.commerce.product.service.CommerceChannelLocalService getCommerceChannelLocalService() {
		return commerceChannelLocalService;
	}

	/**
	 * Sets the commerce channel local service.
	 *
	 * @param commerceChannelLocalService the commerce channel local service
	 */
	public void setCommerceChannelLocalService(
		com.liferay.commerce.product.service.CommerceChannelLocalService commerceChannelLocalService) {
		this.commerceChannelLocalService = commerceChannelLocalService;
	}

	/**
	 * Returns the commerce channel persistence.
	 *
	 * @return the commerce channel persistence
	 */
	public CommerceChannelPersistence getCommerceChannelPersistence() {
		return commerceChannelPersistence;
	}

	/**
	 * Sets the commerce channel persistence.
	 *
	 * @param commerceChannelPersistence the commerce channel persistence
	 */
	public void setCommerceChannelPersistence(
		CommerceChannelPersistence commerceChannelPersistence) {
		this.commerceChannelPersistence = commerceChannelPersistence;
	}

	/**
	 * Returns the cp attachment file entry local service.
	 *
	 * @return the cp attachment file entry local service
	 */
	public com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService getCPAttachmentFileEntryLocalService() {
		return cpAttachmentFileEntryLocalService;
	}

	/**
	 * Sets the cp attachment file entry local service.
	 *
	 * @param cpAttachmentFileEntryLocalService the cp attachment file entry local service
	 */
	public void setCPAttachmentFileEntryLocalService(
		com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService cpAttachmentFileEntryLocalService) {
		this.cpAttachmentFileEntryLocalService = cpAttachmentFileEntryLocalService;
	}

	/**
	 * Returns the cp attachment file entry persistence.
	 *
	 * @return the cp attachment file entry persistence
	 */
	public CPAttachmentFileEntryPersistence getCPAttachmentFileEntryPersistence() {
		return cpAttachmentFileEntryPersistence;
	}

	/**
	 * Sets the cp attachment file entry persistence.
	 *
	 * @param cpAttachmentFileEntryPersistence the cp attachment file entry persistence
	 */
	public void setCPAttachmentFileEntryPersistence(
		CPAttachmentFileEntryPersistence cpAttachmentFileEntryPersistence) {
		this.cpAttachmentFileEntryPersistence = cpAttachmentFileEntryPersistence;
	}

	/**
	 * Returns the cp attachment file entry finder.
	 *
	 * @return the cp attachment file entry finder
	 */
	public CPAttachmentFileEntryFinder getCPAttachmentFileEntryFinder() {
		return cpAttachmentFileEntryFinder;
	}

	/**
	 * Sets the cp attachment file entry finder.
	 *
	 * @param cpAttachmentFileEntryFinder the cp attachment file entry finder
	 */
	public void setCPAttachmentFileEntryFinder(
		CPAttachmentFileEntryFinder cpAttachmentFileEntryFinder) {
		this.cpAttachmentFileEntryFinder = cpAttachmentFileEntryFinder;
	}

	/**
	 * Returns the cp definition local service.
	 *
	 * @return the cp definition local service
	 */
	public com.liferay.commerce.product.service.CPDefinitionLocalService getCPDefinitionLocalService() {
		return cpDefinitionLocalService;
	}

	/**
	 * Sets the cp definition local service.
	 *
	 * @param cpDefinitionLocalService the cp definition local service
	 */
	public void setCPDefinitionLocalService(
		com.liferay.commerce.product.service.CPDefinitionLocalService cpDefinitionLocalService) {
		this.cpDefinitionLocalService = cpDefinitionLocalService;
	}

	/**
	 * Returns the cp definition persistence.
	 *
	 * @return the cp definition persistence
	 */
	public CPDefinitionPersistence getCPDefinitionPersistence() {
		return cpDefinitionPersistence;
	}

	/**
	 * Sets the cp definition persistence.
	 *
	 * @param cpDefinitionPersistence the cp definition persistence
	 */
	public void setCPDefinitionPersistence(
		CPDefinitionPersistence cpDefinitionPersistence) {
		this.cpDefinitionPersistence = cpDefinitionPersistence;
	}

	/**
	 * Returns the cp definition finder.
	 *
	 * @return the cp definition finder
	 */
	public CPDefinitionFinder getCPDefinitionFinder() {
		return cpDefinitionFinder;
	}

	/**
	 * Sets the cp definition finder.
	 *
	 * @param cpDefinitionFinder the cp definition finder
	 */
	public void setCPDefinitionFinder(CPDefinitionFinder cpDefinitionFinder) {
		this.cpDefinitionFinder = cpDefinitionFinder;
	}

	/**
	 * Returns the cp definition link local service.
	 *
	 * @return the cp definition link local service
	 */
	public com.liferay.commerce.product.service.CPDefinitionLinkLocalService getCPDefinitionLinkLocalService() {
		return cpDefinitionLinkLocalService;
	}

	/**
	 * Sets the cp definition link local service.
	 *
	 * @param cpDefinitionLinkLocalService the cp definition link local service
	 */
	public void setCPDefinitionLinkLocalService(
		com.liferay.commerce.product.service.CPDefinitionLinkLocalService cpDefinitionLinkLocalService) {
		this.cpDefinitionLinkLocalService = cpDefinitionLinkLocalService;
	}

	/**
	 * Returns the cp definition link persistence.
	 *
	 * @return the cp definition link persistence
	 */
	public CPDefinitionLinkPersistence getCPDefinitionLinkPersistence() {
		return cpDefinitionLinkPersistence;
	}

	/**
	 * Sets the cp definition link persistence.
	 *
	 * @param cpDefinitionLinkPersistence the cp definition link persistence
	 */
	public void setCPDefinitionLinkPersistence(
		CPDefinitionLinkPersistence cpDefinitionLinkPersistence) {
		this.cpDefinitionLinkPersistence = cpDefinitionLinkPersistence;
	}

	/**
	 * Returns the cp definition localization persistence.
	 *
	 * @return the cp definition localization persistence
	 */
	public CPDefinitionLocalizationPersistence getCPDefinitionLocalizationPersistence() {
		return cpDefinitionLocalizationPersistence;
	}

	/**
	 * Sets the cp definition localization persistence.
	 *
	 * @param cpDefinitionLocalizationPersistence the cp definition localization persistence
	 */
	public void setCPDefinitionLocalizationPersistence(
		CPDefinitionLocalizationPersistence cpDefinitionLocalizationPersistence) {
		this.cpDefinitionLocalizationPersistence = cpDefinitionLocalizationPersistence;
	}

	/**
	 * Returns the cp definition option rel local service.
	 *
	 * @return the cp definition option rel local service
	 */
	public com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService getCPDefinitionOptionRelLocalService() {
		return cpDefinitionOptionRelLocalService;
	}

	/**
	 * Sets the cp definition option rel local service.
	 *
	 * @param cpDefinitionOptionRelLocalService the cp definition option rel local service
	 */
	public void setCPDefinitionOptionRelLocalService(
		com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService cpDefinitionOptionRelLocalService) {
		this.cpDefinitionOptionRelLocalService = cpDefinitionOptionRelLocalService;
	}

	/**
	 * Returns the cp definition option rel persistence.
	 *
	 * @return the cp definition option rel persistence
	 */
	public CPDefinitionOptionRelPersistence getCPDefinitionOptionRelPersistence() {
		return cpDefinitionOptionRelPersistence;
	}

	/**
	 * Sets the cp definition option rel persistence.
	 *
	 * @param cpDefinitionOptionRelPersistence the cp definition option rel persistence
	 */
	public void setCPDefinitionOptionRelPersistence(
		CPDefinitionOptionRelPersistence cpDefinitionOptionRelPersistence) {
		this.cpDefinitionOptionRelPersistence = cpDefinitionOptionRelPersistence;
	}

	/**
	 * Returns the cp definition option value rel local service.
	 *
	 * @return the cp definition option value rel local service
	 */
	public com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService getCPDefinitionOptionValueRelLocalService() {
		return cpDefinitionOptionValueRelLocalService;
	}

	/**
	 * Sets the cp definition option value rel local service.
	 *
	 * @param cpDefinitionOptionValueRelLocalService the cp definition option value rel local service
	 */
	public void setCPDefinitionOptionValueRelLocalService(
		com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService cpDefinitionOptionValueRelLocalService) {
		this.cpDefinitionOptionValueRelLocalService = cpDefinitionOptionValueRelLocalService;
	}

	/**
	 * Returns the cp definition option value rel persistence.
	 *
	 * @return the cp definition option value rel persistence
	 */
	public CPDefinitionOptionValueRelPersistence getCPDefinitionOptionValueRelPersistence() {
		return cpDefinitionOptionValueRelPersistence;
	}

	/**
	 * Sets the cp definition option value rel persistence.
	 *
	 * @param cpDefinitionOptionValueRelPersistence the cp definition option value rel persistence
	 */
	public void setCPDefinitionOptionValueRelPersistence(
		CPDefinitionOptionValueRelPersistence cpDefinitionOptionValueRelPersistence) {
		this.cpDefinitionOptionValueRelPersistence = cpDefinitionOptionValueRelPersistence;
	}

	/**
	 * Returns the cp definition specification option value local service.
	 *
	 * @return the cp definition specification option value local service
	 */
	public com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService getCPDefinitionSpecificationOptionValueLocalService() {
		return cpDefinitionSpecificationOptionValueLocalService;
	}

	/**
	 * Sets the cp definition specification option value local service.
	 *
	 * @param cpDefinitionSpecificationOptionValueLocalService the cp definition specification option value local service
	 */
	public void setCPDefinitionSpecificationOptionValueLocalService(
		com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService cpDefinitionSpecificationOptionValueLocalService) {
		this.cpDefinitionSpecificationOptionValueLocalService = cpDefinitionSpecificationOptionValueLocalService;
	}

	/**
	 * Returns the cp definition specification option value persistence.
	 *
	 * @return the cp definition specification option value persistence
	 */
	public CPDefinitionSpecificationOptionValuePersistence getCPDefinitionSpecificationOptionValuePersistence() {
		return cpDefinitionSpecificationOptionValuePersistence;
	}

	/**
	 * Sets the cp definition specification option value persistence.
	 *
	 * @param cpDefinitionSpecificationOptionValuePersistence the cp definition specification option value persistence
	 */
	public void setCPDefinitionSpecificationOptionValuePersistence(
		CPDefinitionSpecificationOptionValuePersistence cpDefinitionSpecificationOptionValuePersistence) {
		this.cpDefinitionSpecificationOptionValuePersistence = cpDefinitionSpecificationOptionValuePersistence;
	}

	/**
	 * Returns the cp display layout local service.
	 *
	 * @return the cp display layout local service
	 */
	public com.liferay.commerce.product.service.CPDisplayLayoutLocalService getCPDisplayLayoutLocalService() {
		return cpDisplayLayoutLocalService;
	}

	/**
	 * Sets the cp display layout local service.
	 *
	 * @param cpDisplayLayoutLocalService the cp display layout local service
	 */
	public void setCPDisplayLayoutLocalService(
		com.liferay.commerce.product.service.CPDisplayLayoutLocalService cpDisplayLayoutLocalService) {
		this.cpDisplayLayoutLocalService = cpDisplayLayoutLocalService;
	}

	/**
	 * Returns the cp display layout persistence.
	 *
	 * @return the cp display layout persistence
	 */
	public CPDisplayLayoutPersistence getCPDisplayLayoutPersistence() {
		return cpDisplayLayoutPersistence;
	}

	/**
	 * Sets the cp display layout persistence.
	 *
	 * @param cpDisplayLayoutPersistence the cp display layout persistence
	 */
	public void setCPDisplayLayoutPersistence(
		CPDisplayLayoutPersistence cpDisplayLayoutPersistence) {
		this.cpDisplayLayoutPersistence = cpDisplayLayoutPersistence;
	}

	/**
	 * Returns the cp friendly url entry local service.
	 *
	 * @return the cp friendly url entry local service
	 */
	public com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService getCPFriendlyURLEntryLocalService() {
		return cpFriendlyURLEntryLocalService;
	}

	/**
	 * Sets the cp friendly url entry local service.
	 *
	 * @param cpFriendlyURLEntryLocalService the cp friendly url entry local service
	 */
	public void setCPFriendlyURLEntryLocalService(
		com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService cpFriendlyURLEntryLocalService) {
		this.cpFriendlyURLEntryLocalService = cpFriendlyURLEntryLocalService;
	}

	/**
	 * Returns the cp friendly url entry persistence.
	 *
	 * @return the cp friendly url entry persistence
	 */
	public CPFriendlyURLEntryPersistence getCPFriendlyURLEntryPersistence() {
		return cpFriendlyURLEntryPersistence;
	}

	/**
	 * Sets the cp friendly url entry persistence.
	 *
	 * @param cpFriendlyURLEntryPersistence the cp friendly url entry persistence
	 */
	public void setCPFriendlyURLEntryPersistence(
		CPFriendlyURLEntryPersistence cpFriendlyURLEntryPersistence) {
		this.cpFriendlyURLEntryPersistence = cpFriendlyURLEntryPersistence;
	}

	/**
	 * Returns the cp instance local service.
	 *
	 * @return the cp instance local service
	 */
	public com.liferay.commerce.product.service.CPInstanceLocalService getCPInstanceLocalService() {
		return cpInstanceLocalService;
	}

	/**
	 * Sets the cp instance local service.
	 *
	 * @param cpInstanceLocalService the cp instance local service
	 */
	public void setCPInstanceLocalService(
		com.liferay.commerce.product.service.CPInstanceLocalService cpInstanceLocalService) {
		this.cpInstanceLocalService = cpInstanceLocalService;
	}

	/**
	 * Returns the cp instance persistence.
	 *
	 * @return the cp instance persistence
	 */
	public CPInstancePersistence getCPInstancePersistence() {
		return cpInstancePersistence;
	}

	/**
	 * Sets the cp instance persistence.
	 *
	 * @param cpInstancePersistence the cp instance persistence
	 */
	public void setCPInstancePersistence(
		CPInstancePersistence cpInstancePersistence) {
		this.cpInstancePersistence = cpInstancePersistence;
	}

	/**
	 * Returns the cp instance finder.
	 *
	 * @return the cp instance finder
	 */
	public CPInstanceFinder getCPInstanceFinder() {
		return cpInstanceFinder;
	}

	/**
	 * Sets the cp instance finder.
	 *
	 * @param cpInstanceFinder the cp instance finder
	 */
	public void setCPInstanceFinder(CPInstanceFinder cpInstanceFinder) {
		this.cpInstanceFinder = cpInstanceFinder;
	}

	/**
	 * Returns the cp measurement unit local service.
	 *
	 * @return the cp measurement unit local service
	 */
	public com.liferay.commerce.product.service.CPMeasurementUnitLocalService getCPMeasurementUnitLocalService() {
		return cpMeasurementUnitLocalService;
	}

	/**
	 * Sets the cp measurement unit local service.
	 *
	 * @param cpMeasurementUnitLocalService the cp measurement unit local service
	 */
	public void setCPMeasurementUnitLocalService(
		com.liferay.commerce.product.service.CPMeasurementUnitLocalService cpMeasurementUnitLocalService) {
		this.cpMeasurementUnitLocalService = cpMeasurementUnitLocalService;
	}

	/**
	 * Returns the cp measurement unit persistence.
	 *
	 * @return the cp measurement unit persistence
	 */
	public CPMeasurementUnitPersistence getCPMeasurementUnitPersistence() {
		return cpMeasurementUnitPersistence;
	}

	/**
	 * Sets the cp measurement unit persistence.
	 *
	 * @param cpMeasurementUnitPersistence the cp measurement unit persistence
	 */
	public void setCPMeasurementUnitPersistence(
		CPMeasurementUnitPersistence cpMeasurementUnitPersistence) {
		this.cpMeasurementUnitPersistence = cpMeasurementUnitPersistence;
	}

	/**
	 * Returns the cp option local service.
	 *
	 * @return the cp option local service
	 */
	public com.liferay.commerce.product.service.CPOptionLocalService getCPOptionLocalService() {
		return cpOptionLocalService;
	}

	/**
	 * Sets the cp option local service.
	 *
	 * @param cpOptionLocalService the cp option local service
	 */
	public void setCPOptionLocalService(
		com.liferay.commerce.product.service.CPOptionLocalService cpOptionLocalService) {
		this.cpOptionLocalService = cpOptionLocalService;
	}

	/**
	 * Returns the cp option persistence.
	 *
	 * @return the cp option persistence
	 */
	public CPOptionPersistence getCPOptionPersistence() {
		return cpOptionPersistence;
	}

	/**
	 * Sets the cp option persistence.
	 *
	 * @param cpOptionPersistence the cp option persistence
	 */
	public void setCPOptionPersistence(CPOptionPersistence cpOptionPersistence) {
		this.cpOptionPersistence = cpOptionPersistence;
	}

	/**
	 * Returns the cp option category local service.
	 *
	 * @return the cp option category local service
	 */
	public com.liferay.commerce.product.service.CPOptionCategoryLocalService getCPOptionCategoryLocalService() {
		return cpOptionCategoryLocalService;
	}

	/**
	 * Sets the cp option category local service.
	 *
	 * @param cpOptionCategoryLocalService the cp option category local service
	 */
	public void setCPOptionCategoryLocalService(
		com.liferay.commerce.product.service.CPOptionCategoryLocalService cpOptionCategoryLocalService) {
		this.cpOptionCategoryLocalService = cpOptionCategoryLocalService;
	}

	/**
	 * Returns the cp option category persistence.
	 *
	 * @return the cp option category persistence
	 */
	public CPOptionCategoryPersistence getCPOptionCategoryPersistence() {
		return cpOptionCategoryPersistence;
	}

	/**
	 * Sets the cp option category persistence.
	 *
	 * @param cpOptionCategoryPersistence the cp option category persistence
	 */
	public void setCPOptionCategoryPersistence(
		CPOptionCategoryPersistence cpOptionCategoryPersistence) {
		this.cpOptionCategoryPersistence = cpOptionCategoryPersistence;
	}

	/**
	 * Returns the cp option value local service.
	 *
	 * @return the cp option value local service
	 */
	public com.liferay.commerce.product.service.CPOptionValueLocalService getCPOptionValueLocalService() {
		return cpOptionValueLocalService;
	}

	/**
	 * Sets the cp option value local service.
	 *
	 * @param cpOptionValueLocalService the cp option value local service
	 */
	public void setCPOptionValueLocalService(
		com.liferay.commerce.product.service.CPOptionValueLocalService cpOptionValueLocalService) {
		this.cpOptionValueLocalService = cpOptionValueLocalService;
	}

	/**
	 * Returns the cp option value persistence.
	 *
	 * @return the cp option value persistence
	 */
	public CPOptionValuePersistence getCPOptionValuePersistence() {
		return cpOptionValuePersistence;
	}

	/**
	 * Sets the cp option value persistence.
	 *
	 * @param cpOptionValuePersistence the cp option value persistence
	 */
	public void setCPOptionValuePersistence(
		CPOptionValuePersistence cpOptionValuePersistence) {
		this.cpOptionValuePersistence = cpOptionValuePersistence;
	}

	/**
	 * Returns the c product local service.
	 *
	 * @return the c product local service
	 */
	public CProductLocalService getCProductLocalService() {
		return cProductLocalService;
	}

	/**
	 * Sets the c product local service.
	 *
	 * @param cProductLocalService the c product local service
	 */
	public void setCProductLocalService(
		CProductLocalService cProductLocalService) {
		this.cProductLocalService = cProductLocalService;
	}

	/**
	 * Returns the c product persistence.
	 *
	 * @return the c product persistence
	 */
	public CProductPersistence getCProductPersistence() {
		return cProductPersistence;
	}

	/**
	 * Sets the c product persistence.
	 *
	 * @param cProductPersistence the c product persistence
	 */
	public void setCProductPersistence(CProductPersistence cProductPersistence) {
		this.cProductPersistence = cProductPersistence;
	}

	/**
	 * Returns the cp rule local service.
	 *
	 * @return the cp rule local service
	 */
	public com.liferay.commerce.product.service.CPRuleLocalService getCPRuleLocalService() {
		return cpRuleLocalService;
	}

	/**
	 * Sets the cp rule local service.
	 *
	 * @param cpRuleLocalService the cp rule local service
	 */
	public void setCPRuleLocalService(
		com.liferay.commerce.product.service.CPRuleLocalService cpRuleLocalService) {
		this.cpRuleLocalService = cpRuleLocalService;
	}

	/**
	 * Returns the cp rule persistence.
	 *
	 * @return the cp rule persistence
	 */
	public CPRulePersistence getCPRulePersistence() {
		return cpRulePersistence;
	}

	/**
	 * Sets the cp rule persistence.
	 *
	 * @param cpRulePersistence the cp rule persistence
	 */
	public void setCPRulePersistence(CPRulePersistence cpRulePersistence) {
		this.cpRulePersistence = cpRulePersistence;
	}

	/**
	 * Returns the cp rule asset category rel local service.
	 *
	 * @return the cp rule asset category rel local service
	 */
	public com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalService getCPRuleAssetCategoryRelLocalService() {
		return cpRuleAssetCategoryRelLocalService;
	}

	/**
	 * Sets the cp rule asset category rel local service.
	 *
	 * @param cpRuleAssetCategoryRelLocalService the cp rule asset category rel local service
	 */
	public void setCPRuleAssetCategoryRelLocalService(
		com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalService cpRuleAssetCategoryRelLocalService) {
		this.cpRuleAssetCategoryRelLocalService = cpRuleAssetCategoryRelLocalService;
	}

	/**
	 * Returns the cp rule asset category rel persistence.
	 *
	 * @return the cp rule asset category rel persistence
	 */
	public CPRuleAssetCategoryRelPersistence getCPRuleAssetCategoryRelPersistence() {
		return cpRuleAssetCategoryRelPersistence;
	}

	/**
	 * Sets the cp rule asset category rel persistence.
	 *
	 * @param cpRuleAssetCategoryRelPersistence the cp rule asset category rel persistence
	 */
	public void setCPRuleAssetCategoryRelPersistence(
		CPRuleAssetCategoryRelPersistence cpRuleAssetCategoryRelPersistence) {
		this.cpRuleAssetCategoryRelPersistence = cpRuleAssetCategoryRelPersistence;
	}

	/**
	 * Returns the cp rule commerce account group rel local service.
	 *
	 * @return the cp rule commerce account group rel local service
	 */
	public com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalService getCPRuleCommerceAccountGroupRelLocalService() {
		return cpRuleCommerceAccountGroupRelLocalService;
	}

	/**
	 * Sets the cp rule commerce account group rel local service.
	 *
	 * @param cpRuleCommerceAccountGroupRelLocalService the cp rule commerce account group rel local service
	 */
	public void setCPRuleCommerceAccountGroupRelLocalService(
		com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalService cpRuleCommerceAccountGroupRelLocalService) {
		this.cpRuleCommerceAccountGroupRelLocalService = cpRuleCommerceAccountGroupRelLocalService;
	}

	/**
	 * Returns the cp rule commerce account group rel persistence.
	 *
	 * @return the cp rule commerce account group rel persistence
	 */
	public CPRuleCommerceAccountGroupRelPersistence getCPRuleCommerceAccountGroupRelPersistence() {
		return cpRuleCommerceAccountGroupRelPersistence;
	}

	/**
	 * Sets the cp rule commerce account group rel persistence.
	 *
	 * @param cpRuleCommerceAccountGroupRelPersistence the cp rule commerce account group rel persistence
	 */
	public void setCPRuleCommerceAccountGroupRelPersistence(
		CPRuleCommerceAccountGroupRelPersistence cpRuleCommerceAccountGroupRelPersistence) {
		this.cpRuleCommerceAccountGroupRelPersistence = cpRuleCommerceAccountGroupRelPersistence;
	}

	/**
	 * Returns the cp specification option local service.
	 *
	 * @return the cp specification option local service
	 */
	public com.liferay.commerce.product.service.CPSpecificationOptionLocalService getCPSpecificationOptionLocalService() {
		return cpSpecificationOptionLocalService;
	}

	/**
	 * Sets the cp specification option local service.
	 *
	 * @param cpSpecificationOptionLocalService the cp specification option local service
	 */
	public void setCPSpecificationOptionLocalService(
		com.liferay.commerce.product.service.CPSpecificationOptionLocalService cpSpecificationOptionLocalService) {
		this.cpSpecificationOptionLocalService = cpSpecificationOptionLocalService;
	}

	/**
	 * Returns the cp specification option persistence.
	 *
	 * @return the cp specification option persistence
	 */
	public CPSpecificationOptionPersistence getCPSpecificationOptionPersistence() {
		return cpSpecificationOptionPersistence;
	}

	/**
	 * Sets the cp specification option persistence.
	 *
	 * @param cpSpecificationOptionPersistence the cp specification option persistence
	 */
	public void setCPSpecificationOptionPersistence(
		CPSpecificationOptionPersistence cpSpecificationOptionPersistence) {
		this.cpSpecificationOptionPersistence = cpSpecificationOptionPersistence;
	}

	/**
	 * Returns the cp tax category local service.
	 *
	 * @return the cp tax category local service
	 */
	public com.liferay.commerce.product.service.CPTaxCategoryLocalService getCPTaxCategoryLocalService() {
		return cpTaxCategoryLocalService;
	}

	/**
	 * Sets the cp tax category local service.
	 *
	 * @param cpTaxCategoryLocalService the cp tax category local service
	 */
	public void setCPTaxCategoryLocalService(
		com.liferay.commerce.product.service.CPTaxCategoryLocalService cpTaxCategoryLocalService) {
		this.cpTaxCategoryLocalService = cpTaxCategoryLocalService;
	}

	/**
	 * Returns the cp tax category persistence.
	 *
	 * @return the cp tax category persistence
	 */
	public CPTaxCategoryPersistence getCPTaxCategoryPersistence() {
		return cpTaxCategoryPersistence;
	}

	/**
	 * Sets the cp tax category persistence.
	 *
	 * @param cpTaxCategoryPersistence the cp tax category persistence
	 */
	public void setCPTaxCategoryPersistence(
		CPTaxCategoryPersistence cpTaxCategoryPersistence) {
		this.cpTaxCategoryPersistence = cpTaxCategoryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
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
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
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
		persistedModelLocalServiceRegistry.register("com.liferay.commerce.product.model.CProduct",
			cProductLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.product.model.CProduct");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CProductLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CProduct.class;
	}

	protected String getModelClassName() {
		return CProduct.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = cProductPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.commerce.product.service.CommerceCatalogLocalService.class)
	protected com.liferay.commerce.product.service.CommerceCatalogLocalService commerceCatalogLocalService;
	@BeanReference(type = CommerceCatalogPersistence.class)
	protected CommerceCatalogPersistence commerceCatalogPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CommerceChannelLocalService.class)
	protected com.liferay.commerce.product.service.CommerceChannelLocalService commerceChannelLocalService;
	@BeanReference(type = CommerceChannelPersistence.class)
	protected CommerceChannelPersistence commerceChannelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService.class)
	protected com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService cpAttachmentFileEntryLocalService;
	@BeanReference(type = CPAttachmentFileEntryPersistence.class)
	protected CPAttachmentFileEntryPersistence cpAttachmentFileEntryPersistence;
	@BeanReference(type = CPAttachmentFileEntryFinder.class)
	protected CPAttachmentFileEntryFinder cpAttachmentFileEntryFinder;
	@BeanReference(type = com.liferay.commerce.product.service.CPDefinitionLocalService.class)
	protected com.liferay.commerce.product.service.CPDefinitionLocalService cpDefinitionLocalService;
	@BeanReference(type = CPDefinitionPersistence.class)
	protected CPDefinitionPersistence cpDefinitionPersistence;
	@BeanReference(type = CPDefinitionFinder.class)
	protected CPDefinitionFinder cpDefinitionFinder;
	@BeanReference(type = com.liferay.commerce.product.service.CPDefinitionLinkLocalService.class)
	protected com.liferay.commerce.product.service.CPDefinitionLinkLocalService cpDefinitionLinkLocalService;
	@BeanReference(type = CPDefinitionLinkPersistence.class)
	protected CPDefinitionLinkPersistence cpDefinitionLinkPersistence;
	@BeanReference(type = CPDefinitionLocalizationPersistence.class)
	protected CPDefinitionLocalizationPersistence cpDefinitionLocalizationPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService.class)
	protected com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService cpDefinitionOptionRelLocalService;
	@BeanReference(type = CPDefinitionOptionRelPersistence.class)
	protected CPDefinitionOptionRelPersistence cpDefinitionOptionRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService.class)
	protected com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService cpDefinitionOptionValueRelLocalService;
	@BeanReference(type = CPDefinitionOptionValueRelPersistence.class)
	protected CPDefinitionOptionValueRelPersistence cpDefinitionOptionValueRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService.class)
	protected com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService cpDefinitionSpecificationOptionValueLocalService;
	@BeanReference(type = CPDefinitionSpecificationOptionValuePersistence.class)
	protected CPDefinitionSpecificationOptionValuePersistence cpDefinitionSpecificationOptionValuePersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPDisplayLayoutLocalService.class)
	protected com.liferay.commerce.product.service.CPDisplayLayoutLocalService cpDisplayLayoutLocalService;
	@BeanReference(type = CPDisplayLayoutPersistence.class)
	protected CPDisplayLayoutPersistence cpDisplayLayoutPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService.class)
	protected com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService cpFriendlyURLEntryLocalService;
	@BeanReference(type = CPFriendlyURLEntryPersistence.class)
	protected CPFriendlyURLEntryPersistence cpFriendlyURLEntryPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPInstanceLocalService.class)
	protected com.liferay.commerce.product.service.CPInstanceLocalService cpInstanceLocalService;
	@BeanReference(type = CPInstancePersistence.class)
	protected CPInstancePersistence cpInstancePersistence;
	@BeanReference(type = CPInstanceFinder.class)
	protected CPInstanceFinder cpInstanceFinder;
	@BeanReference(type = com.liferay.commerce.product.service.CPMeasurementUnitLocalService.class)
	protected com.liferay.commerce.product.service.CPMeasurementUnitLocalService cpMeasurementUnitLocalService;
	@BeanReference(type = CPMeasurementUnitPersistence.class)
	protected CPMeasurementUnitPersistence cpMeasurementUnitPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPOptionLocalService.class)
	protected com.liferay.commerce.product.service.CPOptionLocalService cpOptionLocalService;
	@BeanReference(type = CPOptionPersistence.class)
	protected CPOptionPersistence cpOptionPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPOptionCategoryLocalService.class)
	protected com.liferay.commerce.product.service.CPOptionCategoryLocalService cpOptionCategoryLocalService;
	@BeanReference(type = CPOptionCategoryPersistence.class)
	protected CPOptionCategoryPersistence cpOptionCategoryPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPOptionValueLocalService.class)
	protected com.liferay.commerce.product.service.CPOptionValueLocalService cpOptionValueLocalService;
	@BeanReference(type = CPOptionValuePersistence.class)
	protected CPOptionValuePersistence cpOptionValuePersistence;
	@BeanReference(type = CProductLocalService.class)
	protected CProductLocalService cProductLocalService;
	@BeanReference(type = CProductPersistence.class)
	protected CProductPersistence cProductPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPRuleLocalService.class)
	protected com.liferay.commerce.product.service.CPRuleLocalService cpRuleLocalService;
	@BeanReference(type = CPRulePersistence.class)
	protected CPRulePersistence cpRulePersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalService.class)
	protected com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalService cpRuleAssetCategoryRelLocalService;
	@BeanReference(type = CPRuleAssetCategoryRelPersistence.class)
	protected CPRuleAssetCategoryRelPersistence cpRuleAssetCategoryRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalService.class)
	protected com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalService cpRuleCommerceAccountGroupRelLocalService;
	@BeanReference(type = CPRuleCommerceAccountGroupRelPersistence.class)
	protected CPRuleCommerceAccountGroupRelPersistence cpRuleCommerceAccountGroupRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPSpecificationOptionLocalService.class)
	protected com.liferay.commerce.product.service.CPSpecificationOptionLocalService cpSpecificationOptionLocalService;
	@BeanReference(type = CPSpecificationOptionPersistence.class)
	protected CPSpecificationOptionPersistence cpSpecificationOptionPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CPTaxCategoryLocalService.class)
	protected com.liferay.commerce.product.service.CPTaxCategoryLocalService cpTaxCategoryLocalService;
	@BeanReference(type = CPTaxCategoryPersistence.class)
	protected CPTaxCategoryPersistence cpTaxCategoryPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}