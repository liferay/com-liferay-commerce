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

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.OptionCategoryHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductOptionHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.SpecificationHelper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CommerceAdminCatalogResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminCatalog.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = CommerceAdminCatalogResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CommerceAdminCatalogResourceImpl
	implements CommerceAdminCatalogResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<OptionCategoryDTO> getOptionCategories(
			Long groupId, Pagination pagination)
		throws Exception {

		return _optionCategoryHelper.getOptionCategories(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<ProductOptionDTO> getOptions(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			groupId, language, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<ProductDTO> getProducts(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _productHelper.getProducts(groupId, language, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<SpecificationDTO> getSpecifications(
			Long groupId, Pagination pagination)
		throws Exception {

		return _specificationHelper.getSpecifications(groupId, pagination);
	}

	@Override
	public OptionCategoryDTO upsertOptionCategory(
			Long groupId, OptionCategoryDTO optionCategoryDTO)
		throws Exception {

		return _optionCategoryHelper.upsertOptionCategory(
			groupId, optionCategoryDTO);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public ProductDTO upsertProduct(
			Long groupId, ProductDTO productDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.upsertProduct(
							groupId, productDTO, language, _user);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _productHelper.upsertProduct(
			groupId, productDTO, language, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public ProductOptionDTO upsertProductOption(
			Long groupId, ProductOptionDTO productOptionDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productOptionHelper.upsertProductOption(
							groupId, productOptionDTO, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _productOptionHelper.upsertProductOption(
			groupId, productOptionDTO, language);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public SpecificationDTO upsertSpecification(
			Long groupId, SpecificationDTO specificationDTO)
		throws Exception {

		return _specificationHelper.upsertSpecification(
			groupId, specificationDTO);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAdminCatalogResourceImpl.class);

	@Context
	private Async _async;

	@Reference
	private OptionCategoryHelper _optionCategoryHelper;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private SpecificationHelper _specificationHelper;

	@Context
	private User _user;

}