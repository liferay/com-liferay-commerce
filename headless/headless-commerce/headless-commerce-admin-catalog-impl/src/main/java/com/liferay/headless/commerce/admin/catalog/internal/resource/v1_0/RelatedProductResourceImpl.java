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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.RelatedProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.RelatedProductResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
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
	properties = "OSGI-INF/liferay/rest/v1_0/related-product.properties",
	scope = ServiceScope.PROTOTYPE, service = RelatedProductResource.class
)
public class RelatedProductResourceImpl extends BaseRelatedProductResourceImpl {

	@Override
	public Response deleteRelatedProduct(Long id) throws Exception {
		_cpDefinitionLinkService.deleteCPDefinitionLink(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<RelatedProduct>
			getProductByExternalReferenceCodeRelatedProductsPage(
				String externalReferenceCode, String type,
				Pagination pagination)
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

		return _getRelatedProductPage(cpDefinition, type, pagination);
	}

	@NestedField("relatedProducts")
	@Override
	public Page<RelatedProduct> getProductIdRelatedProductsPage(
			@NestedFieldId(value = "productId") Long id, String type,
			Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return _getRelatedProductPage(cpDefinition, type, pagination);
	}

	@Override
	public RelatedProduct getRelatedProduct(Long id) throws Exception {
		DTOConverter relatedProductDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionLink.class.getName());

		return (RelatedProduct)relatedProductDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public RelatedProduct postProductByExternalReferenceCodeRelatedProduct(
			String externalReferenceCode, RelatedProduct relatedProduct)
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

		return _upsertRelatedProduct(cpDefinition, relatedProduct);
	}

	@Override
	public RelatedProduct postProductIdRelatedProduct(
			Long id, RelatedProduct relatedProduct)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return _upsertRelatedProduct(cpDefinition, relatedProduct);
	}

	private Page<RelatedProduct> _getRelatedProductPage(
			CPDefinition cpDefinition, String type, Pagination pagination)
		throws Exception {

		List<CPDefinitionLink> cpDefinitionLinks;
		int totalItems;

		if (Validator.isNull(type)) {
			cpDefinitionLinks = _cpDefinitionLinkService.getCPDefinitionLinks(
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

			totalItems = _cpDefinitionLinkService.getCPDefinitionLinksCount(
				cpDefinition.getCPDefinitionId());
		}
		else {
			cpDefinitionLinks = _cpDefinitionLinkService.getCPDefinitionLinks(
				cpDefinition.getCPDefinitionId(), type,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

			totalItems = _cpDefinitionLinkService.getCPDefinitionLinksCount(
				cpDefinition.getCPDefinitionId(), type);
		}

		return Page.of(
			_toRelatedProducts(cpDefinitionLinks), pagination, totalItems);
	}

	private List<RelatedProduct> _toRelatedProducts(
			List<CPDefinitionLink> cpDefinitionLinks)
		throws Exception {

		List<RelatedProduct> relatedProducts = new ArrayList<>();

		DTOConverter relatedProductDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionLink.class.getName());

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			relatedProducts.add(
				(RelatedProduct)relatedProductDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpDefinitionLink.getCPDefinitionLinkId())));
		}

		return relatedProducts;
	}

	private RelatedProduct _upsertRelatedProduct(
			CPDefinition cpDefinition, RelatedProduct relatedProduct)
		throws Exception {

		CPDefinitionLink cpDefinitionLink =
			RelatedProductUtil.upsertCPDefinitionLink(
				_cpDefinitionLinkService, _cpDefinitionService, relatedProduct,
				cpDefinition.getCPDefinitionId(),
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));

		DTOConverter relatedProductDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionLink.class.getName());

		return (RelatedProduct)relatedProductDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpDefinitionLink.getCPDefinitionLinkId()));
	}

	@Reference
	private CPDefinitionLinkService _cpDefinitionLinkService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}