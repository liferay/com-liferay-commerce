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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.CategoryDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.ProductDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CategoryResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
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
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

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

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

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
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		_updateProductCategories(cpDefinition, categories);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdCategory(Long id, Category[] categories)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		_updateProductCategories(cpDefinition, categories);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	private List<Category> _toProductCategories(
		List<AssetCategory> assetCategories) {

		List<Category> categories = new ArrayList<>();

		for (AssetCategory category : assetCategories) {
			categories.add(_categoryDTOMapper.toCategory(category));
		}

		return categories;
	}

	private Product _updateProductCategories(
			CPDefinition cpDefinition, Category[] categories)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		// TODO upsert categories

		cpDefinition = _cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinition.getCPDefinitionId(), serviceContext);

		return _productDTOMapper.toProduct(
			cpDefinition, contextAcceptLanguage.getPreferredLanguageId());
	}

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private CategoryDTOMapper _categoryDTOMapper;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ProductDTOMapper _productDTOMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}