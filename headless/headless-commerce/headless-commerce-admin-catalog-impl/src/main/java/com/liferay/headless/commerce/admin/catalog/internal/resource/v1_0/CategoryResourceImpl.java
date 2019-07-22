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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.asset.kernel.exception.NoSuchCategoryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CategoryResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/category.properties",
	scope = ServiceScope.PROTOTYPE, service = CategoryResource.class
)
public class CategoryResourceImpl extends BaseCategoryResourceImpl {

	@Override
	public Page<Category> getProductByExternalReferenceCodeCategoriesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _assetCategoryService.getCategoriesCount(
			_classNameLocalService.getClassNameId(cpDefinition.getModelClass()),
			cpDefinition.getCPDefinitionId());

		return Page.of(
			_toProductCategories(assetCategories), pagination, totalItems);
	}

	@Override
	public Page<Category> getProductIdCategoriesPage(
			Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _assetCategoryService.getCategoriesCount(
			_classNameLocalService.getClassNameId(cpDefinition.getModelClass()),
			cpDefinition.getCPDefinitionId());

		return Page.of(
			_toProductCategories(assetCategories), pagination, totalItems);
	}

	@Override
	public Response patchProductByExternalReferenceCodeCategory(
			String externalReferenceCode, Category[] categories)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					contextCompany.getCompanyId(), externalReferenceCode);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateProductCategories(cpDefinition, categories);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdCategory(Long id, Category[] categories)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		_updateProductCategories(cpDefinition, categories);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	private List<Category> _toProductCategories(
			List<AssetCategory> assetCategories)
		throws Exception {

		List<Category> categories = new ArrayList<>();

		DTOConverter categoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				AssetCategory.class.getName());

		for (AssetCategory category : assetCategories) {
			categories.add(
				(Category)categoryDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						category.getCategoryId())));
		}

		return categories;
	}

	private void _updateProductCategories(
			CPDefinition cpDefinition, Category[] categories)
		throws PortalException {

		long[] assetCategoryIds = new long[0];

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		for (Category category : categories) {
			AssetCategory assetCategory = _assetCategoryService.fetchCategory(
				category.getId());

			if (assetCategory == null) {
				throw new NoSuchCategoryException(
					"Unable to find Category with ID: " + category.getId());
			}

			assetCategoryIds = ArrayUtil.append(
				assetCategoryIds, assetCategory.getCategoryId());
		}

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		_cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinition.getCPDefinitionId(), serviceContext);
	}

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}