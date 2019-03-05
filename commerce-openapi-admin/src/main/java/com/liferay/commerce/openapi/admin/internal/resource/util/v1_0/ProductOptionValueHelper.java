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

import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v1_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v1_0.ProductOptionValueDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.product.exception.NoSuchCPOptionValueException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = ProductOptionValueHelper.class)
public class ProductOptionValueHelper {

	public void deleteProductOptionValue(String id, Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = null;

		try {
			cpOptionValue = getCPOptionValueById(id, company);
		}
		catch (NoSuchCPOptionValueException nscpove) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Product Option Value does not exist with ID: " + id,
					nscpove);
			}

			return;
		}

		_cpOptionValueService.deleteCPOptionValue(
			cpOptionValue.getCPOptionValueId());
	}

	public CPOptionValue getCPOptionValueById(String id, Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = null;

		if (IdUtils.isLocalPK(id)) {
			cpOptionValue = _cpOptionValueService.fetchCPOptionValue(
				GetterUtil.getLong(id));
		}
		else {
			cpOptionValue = _cpOptionValueService.fetchByExternalReferenceCode(
				company.getCompanyId(),
				IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (cpOptionValue == null) {
			throw new NoSuchCPOptionValueException(
				"Unable to find Product Option Value with ID: " + id);
		}

		return cpOptionValue;
	}

	public ProductOptionValueDTO getProductOptionValue(
			String id, Language language, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			getCPOptionValueById(id, company), language.getLanguageId());
	}

	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			String productOptionId, Language language, Company company,
			Pagination pagination)
		throws PortalException {

		CPOption cpOption = _productOptionHelper.getCPOptionById(
			productOptionId, company);

		List<CPOptionValue> cpOptions = _cpOptionValueService.getCPOptionValues(
			cpOption.getCPOptionId(), pagination.getStartPosition(),
			pagination.getEndPosition());

		int totalItems = _cpOptionValueService.getCPOptionValuesCount(
			cpOption.getCPOptionId());

		Stream<CPOptionValue> stream = cpOptions.stream();

		return stream.map(
			cpOptionValue -> DTOUtils.modelToDTO(
				cpOptionValue, language.getLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productOptionValueDTOs ->
					new CollectionDTO<>(productOptionValueDTOs, totalItems))
		);
	}

	public ProductOptionValueDTO updateProductOptionValue(
			String id, long groupId,
			ProductOptionValueDTO productOptionValueDTO, Language language,
			Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = getCPOptionValueById(id, company);

		Map<Locale, String> nameMap = cpOptionValue.getNameMap();

		nameMap.put(
			LocaleUtil.fromLanguageId(language.getLanguageId()),
			productOptionValueDTO.getName());

		cpOptionValue = _cpOptionValueService.updateCPOptionValue(
			cpOptionValue.getCPOptionValueId(), nameMap,
			productOptionValueDTO.getPriority(), productOptionValueDTO.getKey(),
			_serviceContextHelper.getServiceContext(groupId));

		return DTOUtils.modelToDTO(cpOptionValue, language.getLanguageId());
	}

	public ProductOptionValueDTO upsertProductOptionValue(
			String productOptionId, long groupId,
			ProductOptionValueDTO productOptionValueDTO, Language language,
			Company company)
		throws PortalException {

		CPOption cpOption = _productOptionHelper.getCPOptionById(
			productOptionId, company);

		Map<Locale, String> nameMap = new HashMap<Locale, String>() {
			{
				put(
					LocaleUtil.fromLanguageId(language.getLanguageId()),
					productOptionValueDTO.getName());
			}
		};

		CPOptionValue cpOptionValue = _cpOptionValueService.upsertCPOptionValue(
			cpOption.getCPOptionId(), nameMap,
			productOptionValueDTO.getPriority(), productOptionValueDTO.getKey(),
			productOptionValueDTO.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext(groupId));

		return DTOUtils.modelToDTO(cpOptionValue, language.getLanguageId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductOptionValueHelper.class);

	@Reference
	private CPOptionValueService _cpOptionValueService;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}