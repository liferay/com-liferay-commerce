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
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.AttachmentHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductOptionHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.SKUHelper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.AttachmentDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductShippingConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductSubscriptionConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductTaxConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SkuDTO;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
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
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductResourceImpl implements ProductResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response deleteProduct(String id) throws Exception {
		_productHelper.deleteProduct(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductDTO getProduct(String id, Language language)
		throws Exception {

		return _productHelper.getProduct(id, _company, language);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<AttachmentDTO> getProductAttachments(
			String id, Pagination pagination)
		throws Exception {

		return _attachmentHelper.getAttachments(id, _company, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<CategoryDTO> getProductCategories(
			String id, Pagination pagination)
		throws Exception {

		return _productHelper.getProductCategories(id, _company, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductConfigurationDTO getProductConfiguration(String id)
		throws Exception {

		return _productHelper.getProductConfiguration(id, _company);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<AttachmentDTO> getProductImages(
			String id, Pagination pagination)
		throws Exception {

		return _attachmentHelper.getImages(id, _company, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<ProductOptionDTO> getProductOptions(
			String id, Language language, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			id, _company, language, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductShippingConfigurationDTO getProductShippingConfiguration(
			String id)
		throws Exception {

		return _productHelper.getProductShippingConfiguration(id, _company);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<SkuDTO> getProductSkus(
			String id, Pagination pagination)
		throws Exception {

		return _skuHelper.getSKUs(id, _company, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductSubscriptionConfigurationDTO
			getProductSubscriptionConfiguration(String id)
		throws Exception {

		return _productHelper.getProductSubscriptionConfiguration(id, _company);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductTaxConfigurationDTO getProductTaxConfiguration(
			String id, Language language)
		throws Exception {

		return _productHelper.getProductTaxConfiguration(
			id, _company, language);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProduct(
			String id, ProductDTO productDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductDTO(
							id, productDTO, _company, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductDTO(id, productDTO, _company, language);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductCategory(
			String id, CategoryDTO[] categoryDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductCategories(
							id, _company, categoryDTO, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductCategories(
			id, _company, categoryDTO, language);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductConfiguraton(
			String id, ProductConfigurationDTO productConfigurationDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductConfiguration(
							id, _company, productConfigurationDTO);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductConfiguration(
			id, _company, productConfigurationDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductOptions(
			String id, ProductOptionDTO[] productOptionDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productOptionHelper.updateProductOptions(
							id, productOptionDTO, language, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductShippingConfiguration(
			String id,
			ProductShippingConfigurationDTO productShippingConfigurationDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductShippingConfiguration(
							id, _company, productShippingConfigurationDTO);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductShippingConfiguration(
			id, _company, productShippingConfigurationDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductSubscriptionConfiguration(
			String id,
			ProductSubscriptionConfigurationDTO
				productSubscriptionConfigurationDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductSubscriptionConfiguration(
							id, _company, productSubscriptionConfigurationDTO);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductSubscriptionConfiguration(
			id, _company, productSubscriptionConfigurationDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductTaxConfiguration(
			String id, ProductTaxConfigurationDTO productTaxConfigurationDTO,
			Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productHelper.updateProductTaxConfiguration(
							id, _company, productTaxConfigurationDTO, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productHelper.updateProductTaxConfiguration(
			id, _company, productTaxConfigurationDTO, language);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public AttachmentDTO upsertProductAttachment(
			String id, AttachmentDTO attachmentDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_attachmentHelper.upsertAttachment(
							id, attachmentDTO, _company);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}
			).start();

			return null;
		}

		return _attachmentHelper.upsertAttachment(id, attachmentDTO, _company);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public AttachmentDTO upsertProductImage(
			String id, AttachmentDTO attachmentDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_attachmentHelper.upsertImage(
							id, attachmentDTO, _company);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}
			).start();

			return null;
		}

		return _attachmentHelper.upsertImage(id, attachmentDTO, _company);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public SkuDTO upsertProductSku(String id, SkuDTO skuDTO) throws Exception {
		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_skuHelper.upsertSKU(id, skuDTO, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _skuHelper.upsertSKU(id, skuDTO, _company);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductResourceImpl.class);

	@Context
	private Async _async;

	@Reference
	private AttachmentHelper _attachmentHelper;

	@Context
	private Company _company;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private SKUHelper _skuHelper;

	@Context
	private User _user;

}