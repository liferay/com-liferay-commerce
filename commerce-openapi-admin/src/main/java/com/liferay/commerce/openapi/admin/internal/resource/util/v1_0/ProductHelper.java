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

package com.liferay.commerce.openapi.admin.internal.resource.util.v1_0;

import com.liferay.commerce.openapi.admin.internal.mapper.v1_0.DTOMapper;
import com.liferay.commerce.openapi.admin.internal.resource.util.DateConfig;
import com.liferay.commerce.openapi.admin.model.v1_0.ProductDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.exception.NoSuchCProductException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CProductLocalService;
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
import java.util.HashMap;
import java.util.List;
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

	public ProductDTO getProduct(String id, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(getProductById(id, company));
	}

	public CPDefinition getProductById(String id, Company company)
		throws PortalException {

		CProduct cProduct = null;

		if (IdUtils.isLocalPK(id)) {
			cProduct = _cProductLocalService.fetchCProduct(
				GetterUtil.getLong(id));
		}
		else {

			// Get Product by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			cProduct = _cProductLocalService.fetchCProductByReferenceCode(
				company.getCompanyId(), erc);
		}

		if (cProduct == null) {
			throw new NoSuchCProductException(
				"Unable to find Product with ID: " + id);
		}

		CPDefinition cpDefinition = _cpDefinitionService.fetchCPDefinition(
			cProduct.getPublishedCPDefinitionId());

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return cpDefinition;
	}

	public CollectionDTO<ProductDTO> getProducts(
			long groupId, Pagination pagination)
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
			_dtoMapper::modelToDTO
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productDTOs -> new CollectionDTO<>(productDTOs, totalItems))
		);
	}

	public ProductDTO updateProductDTO(
			String id, ProductDTO productDTO, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_updateProduct(
				id, company, productDTO.getDescription(),
				productDTO.getShortDescription(), productDTO.getName()));
	}

	public ProductDTO upsertProduct(
			long groupId, ProductDTO productDTO, User user)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_upsertProduct(
				groupId, productDTO.isActive(), productDTO.getDefaultSku(),
				productDTO.getDescription(),
				productDTO.getExternalReferenceCode(),
				productDTO.getProductTypeName(),
				productDTO.getShortDescription(), productDTO.getName(), user));
	}

	private CPDefinition _updateProduct(
			String id, Company company, Map<String, String> description,
			Map<String, String> shortDescription, Map<String, String> name)
		throws PortalException {

		CPDefinition cpDefinition = getProductById(id, company);

		long groupId = cpDefinition.getGroupId();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		boolean neverExpire = Boolean.TRUE;

		return _cpDefinitionService.updateCPDefinition(
			cpDefinition.getCPDefinitionId(),
			LanguageUtils.getLocalizedMap(name),
			LanguageUtils.getLocalizedMap(shortDescription),
			LanguageUtils.getLocalizedMap(description),
			cpDefinition.getUrlTitleMap(), cpDefinition.getMetaTitleMap(),
			cpDefinition.getMetaDescriptionMap(),
			cpDefinition.getMetaKeywordsMap(),
			cpDefinition.isIgnoreSKUCombinations(),
			cpDefinition.getDDMStructureKey(), true,
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			neverExpire, serviceContext);
	}

	private CPDefinition _upsertProduct(
			Long groupId, boolean active, String defaultSku,
			Map<String, String> description, String externalReferenceCode,
			String productTypeName, Map<String, String> shortDescription,
			Map<String, String> name, User currentUser)
		throws PortalException {

		boolean neverExpire = Boolean.TRUE;

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		String ddmStructureKey = null;

		CPDefinition cpDefinition = _cpDefinitionService.upsertCPDefinition(
			LanguageUtils.getLocalizedMap(name),
			LanguageUtils.getLocalizedMap(shortDescription),
			LanguageUtils.getLocalizedMap(description), null,
			LanguageUtils.getLocalizedMap(name), null, null, productTypeName,
			true, true, true, true, 0.0, 0.0, 0.0, 0.0, 0.0, 0L, false, false,
			ddmStructureKey, true, displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(), neverExpire, defaultSku,
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
	private CProductLocalService _cProductLocalService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}