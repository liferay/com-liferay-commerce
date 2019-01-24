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

package com.liferay.commerce.openapi.admin.internal.resource.util;

import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.SkuDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = SKUHelper.class)
public class SKUHelper {

	public void deleteSKU(String id, Company company) throws PortalException {
		CPInstance cpInstance = null;

		try {
			cpInstance = getCPInstanceById(id, company);
		}
		catch (NoSuchCPInstanceException nscpie) {
			if (_log.isDebugEnabled()) {
				_log.debug("SKU does not exist with ID: " + id, nscpie);
			}

			return;
		}

		_cpInstanceService.deleteCPInstance(cpInstance.getCPInstanceId());
	}

	public CPInstance getCPInstanceById(String id, Company company)
		throws PortalException {

		CPInstance cpInstance = null;

		if (IdUtils.isLocalPK(id)) {
			cpInstance = _cpInstanceService.fetchCPInstance(
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
		return DTOUtils.modelToDTO(getCPInstanceById(id, company));
	}

	public CollectionDTO<SkuDTO> getSKUs(
			String productId, int status, Company company,
			Pagination pagination)
		throws PortalException {

		CPDefinition cpDefinition = _productHelper.getProductById(
			productId, company);

		List<CPInstance> cpInstances =
			_cpInstanceService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId(), status,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpInstanceService.getCPDefinitionInstancesCount(
			cpDefinition.getCPDefinitionId(), status);

		Stream<CPInstance> stream = cpInstances.stream();

		return stream.map(
			cpInstance -> DTOUtils.modelToDTO(cpInstance)
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

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (skuDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				skuDTO.getExpirationDate());
		}

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		cpInstance = _cpInstanceService.updateCPInstance(
			cpInstance.getCPInstanceId(), skuDTO.getSku(), skuDTO.getGtin(),
			skuDTO.getManufacturerPartNumber(), skuDTO.isPurchasable(),
			skuDTO.isPublished(), displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, skuDTO.isNeverExpire(),
			serviceContext);

		return DTOUtils.modelToDTO(cpInstance);
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

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (skuDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				skuDTO.getExpirationDate());
		}

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		CPInstance cpInstance = _cpInstanceService.upsertCPInstance(
			cpDefinition.getCPDefinitionId(), skuDTO.getSku(), skuDTO.getGtin(),
			skuDTO.getManufacturerPartNumber(), skuDTO.isPurchasable(), null,
			skuDTO.getWidth(), skuDTO.getHeight(), skuDTO.getDepth(),
			skuDTO.getWeight(), skuDTO.getPrice(), skuDTO.getPromoPrice(),
			skuDTO.getCost(), skuDTO.isPublished(),
			skuDTO.getExternalReferenceCode(), displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, skuDTO.isNeverExpire(),
			serviceContext);

		return DTOUtils.modelToDTO(cpInstance);
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	private static final Log _log = LogFactoryUtil.getLog(SKUHelper.class);

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}