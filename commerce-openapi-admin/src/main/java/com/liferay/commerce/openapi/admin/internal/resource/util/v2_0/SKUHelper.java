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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.openapi.admin.internal.mapper.v2_0.DTOMapper;
import com.liferay.commerce.openapi.admin.internal.resource.util.DateConfig;
import com.liferay.commerce.openapi.admin.model.v2_0.SkuDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = SKUHelper.class)
public class SKUHelper {

	public void deleteSKU(String id, Company company) throws PortalException {
		CPInstance cpInstance = getCPInstanceById(id, company);

		_cpInstanceService.deleteCPInstance(cpInstance.getCPInstanceId());
	}

	public CPInstance getCPInstanceById(String id, Company company)
		throws PortalException {

		CPInstance cpInstance = null;

		if (IdUtils.isLocalPK(id)) {
			cpInstance = _cpInstanceService.getCPInstance(
				GetterUtil.getLong(id));
		}
		else {
			cpInstance = _cpInstanceService.fetchByExternalReferenceCode(
				company.getCompanyId(),
				IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (cpInstance == null) {
			throw new NoSuchCPInstanceException(
				"Unable to find SKU with ID: " + id);
		}

		return cpInstance;
	}

	public SkuDTO getSku(String id, Company company) throws PortalException {
		return _dtoMapper.modelToDTO(getCPInstanceById(id, company));
	}

	public CollectionDTO<SkuDTO> getSKUs(
			String productId, Company company, Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpInstanceService.getCPDefinitionInstancesCount(
			cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_APPROVED);

		Stream<CPInstance> stream = cpInstances.stream();

		return stream.map(
			cpInstance -> _dtoMapper.modelToDTO(cpInstance)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				skuDTOs -> new CollectionDTO<>(skuDTOs, totalItems))
		);
	}

	public SkuDTO updateSKU(
			String id, long groupId, SkuDTO skuDTO, Company company)
		throws PortalException {

		CPInstance cpInstance = getCPInstanceById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (skuDTO.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(skuDTO.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (skuDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				skuDTO.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		cpInstance = _cpInstanceService.updateCPInstance(
			cpInstance.getCPInstanceId(), skuDTO.getSku(), skuDTO.getGtin(),
			skuDTO.getManufacturerPartNumber(),
			GetterUtil.get(skuDTO.isPurchasable(), false),
			GetterUtil.get(skuDTO.isPublished(), false),
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			GetterUtil.get(skuDTO.isNeverExpire(), false), serviceContext);

		return _dtoMapper.modelToDTO(cpInstance);
	}

	public SkuDTO upsertSKU(
			String productId, long groupId, SkuDTO skuDTO, Company company)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (skuDTO.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(skuDTO.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (skuDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				skuDTO.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		CPInstance cpInstance = _cpInstanceService.upsertCPInstance(
			cpDefinition.getCPDefinitionId(), skuDTO.getSku(), skuDTO.getGtin(),
			skuDTO.getManufacturerPartNumber(),
			GetterUtil.get(skuDTO.isPurchasable(), false), null,
			GetterUtil.get(skuDTO.getWidth(), 0.0),
			GetterUtil.get(skuDTO.getHeight(), 0.0),
			GetterUtil.get(skuDTO.getDepth(), 0.0),
			GetterUtil.get(skuDTO.getWeight(), 0.0), skuDTO.getPrice(),
			skuDTO.getPromoPrice(), skuDTO.getCost(),
			GetterUtil.get(skuDTO.isPublished(), false),
			skuDTO.getExternalReferenceCode(), displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(),
			GetterUtil.get(skuDTO.isNeverExpire(), false), serviceContext);

		return _dtoMapper.modelToDTO(cpInstance);
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}