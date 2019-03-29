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

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
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

	public Sku getSku(String id, Company company) throws PortalException {
		return _dtoMapper.modelToDTO(getCPInstanceById(id, company));
	}

	public Page<Sku> getSKUs(
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
				skus -> Page.of(skus, pagination, totalItems))
		);
	}

	public Sku updateSKU(String id, Sku sku, Company company)
		throws PortalException {

		CPInstance cpInstance = getCPInstanceById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpInstance.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (sku.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(sku.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (sku.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
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

		return _dtoMapper.modelToDTO(cpInstance);
	}

	public Sku upsertSKU(String productId, Sku sku, Company company)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpDefinition.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (sku.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(sku.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (sku.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				sku.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		CPInstance cpInstance = _cpInstanceService.upsertCPInstance(
			cpDefinition.getCPDefinitionId(), sku.getSku(), sku.getGtin(),
			sku.getManufacturerPartNumber(),
			GetterUtil.get(sku.getPurchasable(), false), null,
			GetterUtil.get(sku.getWidth(), 0.0),
			GetterUtil.get(sku.getHeight(), 0.0),
			GetterUtil.get(sku.getDepth(), 0.0),
			GetterUtil.get(sku.getWeight(), 0.0), sku.getPrice(),
			sku.getPromoPrice(), sku.getCost(),
			GetterUtil.get(sku.getPublished(), false),
			sku.getExternalReferenceCode(), displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(),
			GetterUtil.get(sku.getNeverExpire(), false), serviceContext);

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