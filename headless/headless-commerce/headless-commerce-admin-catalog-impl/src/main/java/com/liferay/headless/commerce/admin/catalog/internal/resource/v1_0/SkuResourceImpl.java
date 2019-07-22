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
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.internal.util.DateConfigUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SkuUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/sku.properties",
	scope = ServiceScope.PROTOTYPE, service = SkuResource.class
)
public class SkuResourceImpl extends BaseSkuResourceImpl {

	@Override
	public Response deleteSku(Long id) throws Exception {
		_cpInstanceService.deleteCPInstance(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteSkuByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CPInstance cpInstance = _cpInstanceService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpInstance == null) {
			throw new NoSuchCPInstanceException(
				"Unable to find Sku with externalReferenceCode: " +
					externalReferenceCode);
		}

		_cpInstanceService.deleteCPInstance(cpInstance.getCPInstanceId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<Sku> getProductByExternalReferenceCodeSkusPage(
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

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpInstanceService.getCPDefinitionInstancesCount(
			cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_APPROVED);

		return Page.of(_toSKUs(cpInstances), pagination, totalItems);
	}

	@Override
	public Page<Sku> getProductIdSkusPage(Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpInstanceService.getCPDefinitionInstancesCount(
			cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_APPROVED);

		return Page.of(_toSKUs(cpInstances), pagination, totalItems);
	}

	@Override
	public Sku getSku(Long id) throws Exception {
		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());

		return (Sku)skuDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public Sku getSkuByExternalReferenceCode(String externalReferenceCode)
		throws Exception {

		CPInstance cpInstance = _cpInstanceService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpInstance == null) {
			throw new NoSuchCPInstanceException(
				"Unable to find Sku with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());

		return (Sku)skuDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpInstance.getCPInstanceId()));
	}

	@Override
	public Response patchSku(Long id, Sku sku) throws Exception {
		_updateSKU(_cpInstanceService.getCPInstance(id), sku);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchSkuByExternalReferenceCode(
			String externalReferenceCode, Sku sku)
		throws Exception {

		CPInstance cpInstance = _cpInstanceService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpInstance == null) {
			throw new NoSuchCPInstanceException(
				"Unable to find Sku with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateSKU(cpInstance, sku);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Sku postProductByExternalReferenceCodeSku(
			String externalReferenceCode, Sku sku)
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

		return _upsertSKU(cpDefinition, sku);
	}

	@Override
	public Sku postProductIdSku(Long id, Sku sku) throws Exception {
		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return _upsertSKU(cpDefinition, sku);
	}

	private List<Sku> _toSKUs(List<CPInstance> cpInstances) throws Exception {
		List<Sku> skus = new ArrayList<>();

		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());

		for (CPInstance cpInstance : cpInstances) {
			skus.add(
				(Sku)skuDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpInstance.getCPInstanceId())));
		}

		return skus;
	}

	private Sku _updateSKU(CPInstance cpInstance, Sku sku) throws Exception {
		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpInstance.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (sku.getDisplayDate() != null) {
			displayCalendar = DateConfigUtil.convertDateToCalendar(
				sku.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (sku.getExpirationDate() != null) {
			expirationCalendar = DateConfigUtil.convertDateToCalendar(
				sku.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		cpInstance = _cpInstanceService.updateCPInstance(
			cpInstance.getCPInstanceId(), sku.getSku(), sku.getGtin(),
			sku.getManufacturerPartNumber(),
			GetterUtil.get(sku.getPurchasable(), cpInstance.isPurchasable()),
			GetterUtil.get(sku.getPublished(), cpInstance.isPublished()),
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			GetterUtil.get(
				sku.getNeverExpire(),
				(cpInstance.getExpirationDate() == null) ? true : false),
			serviceContext);

		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());

		return (Sku)skuDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpInstance.getCPInstanceId()));
	}

	private Sku _upsertSKU(CPDefinition cpDefinition, Sku sku)
		throws Exception {

		CPInstance cpInstance = SkuUtil.upsertCPInstance(
			_cpInstanceService, sku, cpDefinition,
			_serviceContextHelper.getServiceContext(cpDefinition.getGroupId()));

		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());

		return (Sku)skuDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpInstance.getCPInstanceId()));
	}

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}