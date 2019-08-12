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

import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Catalog;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CatalogResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
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
	properties = "OSGI-INF/liferay/rest/v1_0/catalog.properties",
	scope = ServiceScope.PROTOTYPE, service = CatalogResource.class
)
public class CatalogResourceImpl extends BaseCatalogResourceImpl {

	@Override
	public Response deleteCatalog(Long id) throws Exception {
		_commerceCatalogService.deleteCommerceCatalog(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteCatalogByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceCatalog == null) {
			throw new NoSuchCatalogException(
				"Unable to find Catalog with externalReferenceCode: " +
					externalReferenceCode);
		}

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Catalog getCatalog(Long id) throws Exception {
		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchCommerceCatalog(id);

		if (commerceCatalog == null) {
			throw new NoSuchCatalogException(
				"Unable to find Catalog with ID: " + id);
		}

		DTOConverter catalogDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceCatalog.class.getName());

		return (Catalog)catalogDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceCatalog.getCommerceCatalogId()));
	}

	@Override
	public Catalog getCatalogByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceCatalog == null) {
			throw new NoSuchCatalogException(
				"Unable to find Catalog with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter catalogDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceCatalog.class.getName());

		return (Catalog)catalogDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceCatalog.getCommerceCatalogId()));
	}

	@Override
	public Page<Catalog> getCatalogsPage(Pagination pagination)
		throws Exception {

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(
				contextCompany.getCompanyId(), null,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _commerceCatalogService.searchCommerceCatalogsCount(
			contextCompany.getCompanyId(), null);

		return Page.of(_toCatalogs(commerceCatalogs), pagination, totalItems);
	}

	@Override
	public Response patchCatalog(Long id, Catalog catalog) throws Exception {
		CommerceCatalog commerceCatalog =
			_commerceCatalogService.getCommerceCatalog(id);

		_commerceCatalogService.updateCommerceCatalog(
			commerceCatalog.getCommerceCatalogId(), catalog.getName(),
			GetterUtil.get(
				catalog.getCurrencyCode(),
				commerceCatalog.getCommerceCurrencyCode()),
			GetterUtil.get(
				catalog.getDefaultLanguageId(),
				commerceCatalog.getCatalogDefaultLanguageId()));

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchCatalogByExternalReferenceCode(
			String externalReferenceCode, Catalog catalog)
		throws Exception {

		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceCatalog == null) {
			throw new NoSuchCatalogException(
				"Unable to find Catalog with externalReferenceCode: " +
					externalReferenceCode);
		}

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Catalog postCatalog(Catalog catalog) throws Exception {
		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(),
				catalog.getExternalReferenceCode());

		if (commerceCatalog == null) {
			commerceCatalog = _commerceCatalogService.addCommerceCatalog(
				catalog.getName(), catalog.getCurrencyCode(),
				catalog.getDefaultLanguageId(),
				catalog.getExternalReferenceCode(),
				_serviceContextHelper.getServiceContext());
		}
		else {
			commerceCatalog = _commerceCatalogService.updateCommerceCatalog(
				commerceCatalog.getCommerceCatalogId(), catalog.getName(),
				GetterUtil.get(
					catalog.getCurrencyCode(),
					commerceCatalog.getCommerceCurrencyCode()),
				GetterUtil.get(
					catalog.getDefaultLanguageId(),
					commerceCatalog.getCatalogDefaultLanguageId()));
		}

		DTOConverter catalogDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceCatalog.class.getName());

		return (Catalog)catalogDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceCatalog.getCommerceCatalogId()));
	}

	private List<Catalog> _toCatalogs(List<CommerceCatalog> commerceCatalogs)
		throws Exception {

		List<Catalog> catalogs = new ArrayList<>();

		DTOConverter catalogDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceCatalog.class.getName());

		for (CommerceCatalog commerceCatalog : commerceCatalogs) {
			catalogs.add(
				(Catalog)catalogDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceCatalog.getCommerceCatalogId())));
		}

		return catalogs;
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}