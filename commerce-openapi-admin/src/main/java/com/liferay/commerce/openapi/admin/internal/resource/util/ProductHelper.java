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
import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = ProductHelper.class)
public class ProductHelper {

	public void deleteProduct(String id, Company company)
		throws PortalException {

		CPDefinition cpDefinition = null;

		try {
			cpDefinition = getProductById(id, company);
		}
		catch (NoSuchCPDefinitionException nscpde) {
			if (_log.isDebugEnabled()) {
				_log.debug("Product not exist with ID: " + id, nscpde);
			}

			return;
		}

		_cpDefinitionService.deleteCPDefinition(
			cpDefinition.getCPDefinitionId());
	}

	public ProductDTO getProduct(String id, Locale locale, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(getProductById(id, company));
	}

	public CPDefinition getProductById(String id, Company company)
		throws PortalException {

		CPDefinition cpDefinition = null;

		if (IdUtils.isLocalPK(id)) {
			cpDefinition = _cpDefinitionService.fetchCPDefinition(
				GetterUtil.getLong(id));
		}
		else {

			// Get Price List by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			cpDefinition = _cpDefinitionService.fetchByExternalReferenceCode(
				company.getCompanyId(), erc);
		}

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return cpDefinition;
	}

	public CollectionDTO<ProductDTO> getProducts(
			long groupId, Locale locale, Pagination pagination)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			_cpDefinitionService.getCPDefinitions(
				groupId, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _cpDefinitionService.getCPDefinitionsCount(
			groupId, WorkflowConstants.STATUS_APPROVED);

		Stream<CPDefinition> stream = cpDefinitions.stream();

		return stream.map(
			DTOUtils::modelToDTO
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productDTOs ->
					new CollectionDTO<>(productDTOs, totalItems))
		);
	}

	public ProductDTO updateProductDTO(
			String id, ProductDTO productDTO, User user, Locale locale,
			Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updateProduct(
				id, company, productDTO.getDescription(),
				productDTO.getShortDescription(), productDTO.getName(),
				locale));
	}

	public ProductDTO upsertProduct(
			long groupId, ProductDTO productDTO, User user, Locale locale)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_upsertProduct(
				groupId, productDTO.isActive(), productDTO.getDefaultSku(),
				productDTO.getDescription(),
				productDTO.getExternalReferenceCode(),
				productDTO.getProductTypeName(),
				productDTO.getShortDescription(), productDTO.getName(), user,
				locale));
	}

	private DateConfig _getDateConfig(Calendar calendar) {
		DateConfig dateConfig = new DateConfig();

		dateConfig._month = calendar.get(Calendar.MONTH);
		dateConfig._day = calendar.get(Calendar.DAY_OF_MONTH);
		dateConfig._year = calendar.get(Calendar.YEAR);
		dateConfig._hour = calendar.get(Calendar.HOUR);
		dateConfig._minute = calendar.get(Calendar.MINUTE);

		int expirationDateAmPm = calendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			dateConfig._hour += 12;
		}

		return dateConfig;
	}

	private Map<Locale, String> _getLocalizedField(
		Locale locale, String field) {

		Map<Locale, String> localizedMap = new HashMap<>();

		localizedMap.put(locale, field);

		return Collections.unmodifiableMap(localizedMap);
	}

	private CPDefinition _updateProduct(
			String id, Company company, String description,
			String shortDescription, String name, Locale locale)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(id, company);

		long groupId = cpDefinition.getGroupId();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = _getDateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = _getDateConfig(expirationCalendar);

		boolean neverExpire = Boolean.TRUE;

		return _cpDefinitionService.updateCPDefinition(
			cpDefinition.getCPDefinitionId(), _getLocalizedField(locale, name),
			_getLocalizedField(locale, shortDescription),
			_getLocalizedField(locale, description),
			cpDefinition.getUrlTitleMap(), cpDefinition.getMetaTitleMap(),
			cpDefinition.getMetaDescriptionMap(),
			cpDefinition.getMetaKeywordsMap(),
			cpDefinition.isIgnoreSKUCombinations(),
			cpDefinition.getDDMStructureKey(), true, displayDateConfig._month,
			displayDateConfig._day, displayDateConfig._year,
			displayDateConfig._hour, displayDateConfig._minute,
			expirationDateConfig._month, expirationDateConfig._day,
			expirationDateConfig._year, expirationDateConfig._hour,
			expirationDateConfig._minute, neverExpire, serviceContext);
	}

	private CPDefinition _upsertProduct(
			Long groupId, boolean active, String defaultSku, String description,
			String externalReferenceCode, String productTypeName,
			String shortDescription, String name, User currentUser,
			Locale locale)
		throws PortalException {

		boolean neverExpire = Boolean.TRUE;

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = _getDateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = _getDateConfig(expirationCalendar);

		String ddmStructureKey = null;

		CPDefinition cpDefinition = _cpDefinitionService.upsertCPDefinition(
			_getLocalizedField(locale, name),
			_getLocalizedField(locale, shortDescription),
			_getLocalizedField(locale, description), null,
			_getLocalizedField(locale, name), null, null, productTypeName, true,
			true, true, true, 0.0, 0.0, 0.0, 0.0, 0.0, 0L, false, false,
			ddmStructureKey, true, displayDateConfig._month,
			displayDateConfig._day, displayDateConfig._year,
			displayDateConfig._hour, displayDateConfig._minute,
			expirationDateConfig._month, expirationDateConfig._day,
			expirationDateConfig._year, expirationDateConfig._hour,
			expirationDateConfig._minute, neverExpire, defaultSku,
			externalReferenceCode, serviceContext);

		if (!active) {
			Map<String, Serializable> workflowContext = new HashMap<>();

			_cpDefinitionService.updateStatus(
				currentUser.getUserId(), cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_INACTIVE, serviceContext,
				workflowContext);
		}

		return cpDefinition;
	}

	private static final Log _log = LogFactoryUtil.getLog(ProductHelper.class);

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	private static class DateConfig {

		private int _day;
		private int _hour;
		private int _minute;
		private int _month;
		private int _year;

	}

}