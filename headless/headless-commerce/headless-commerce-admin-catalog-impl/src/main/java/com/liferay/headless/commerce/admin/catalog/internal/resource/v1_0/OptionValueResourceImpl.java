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

import com.liferay.commerce.product.exception.NoSuchCPOptionException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionValue;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.OptionValueDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.OptionValueUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionValueResource;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/option-value.properties",
	scope = ServiceScope.PROTOTYPE, service = OptionValueResource.class
)
public class OptionValueResourceImpl extends BaseOptionValueResourceImpl {

	@Override
	public Page<OptionValue> getOptionByExternalReferenceCodeOptionValuesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CPOptionValue> cpOptionValues =
			_cpOptionValueService.getCPOptionValues(
				cpOption.getCPOptionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionValueService.getCPOptionValuesCount(
			cpOption.getCPOptionId());

		return Page.of(_toOptionValues(cpOptionValues), pagination, totalItems);
	}

	@Override
	public Page<OptionValue> getOptionIdOptionValuesPage(
			Long id, Pagination pagination)
		throws Exception {

		CPOption cpOption = _cpOptionService.getCPOption(id);

		List<CPOptionValue> cpOptionValues =
			_cpOptionValueService.getCPOptionValues(
				cpOption.getCPOptionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionValueService.getCPOptionValuesCount(
			cpOption.getCPOptionId());

		return Page.of(_toOptionValues(cpOptionValues), pagination, totalItems);
	}

	@Override
	public OptionValue postOptionByExternalReferenceCodeOptionValue(
			String externalReferenceCode, OptionValue optionValue)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _upsertOptionValue(cpOption, optionValue);
	}

	@Override
	public OptionValue postOptionIdOptionValue(Long id, OptionValue optionValue)
		throws Exception {

		CPOption cpOption = _cpOptionService.getCPOption(id);

		return _upsertOptionValue(cpOption, optionValue);
	}

	private List<OptionValue> _toOptionValues(
		List<CPOptionValue> cpOptionValues) {

		List<OptionValue> productOptionValues = new ArrayList<>();

		for (CPOptionValue cpOptionValue : cpOptionValues) {
			productOptionValues.add(
				_optionValueDTOMapper.toOptionValue(cpOptionValue));
		}

		return productOptionValues;
	}

	private OptionValue _upsertOptionValue(
			CPOption cpOption, OptionValue optionValue)
		throws PortalException {

		CPOptionValue cpOptionValue = _cpOptionValueService.upsertCPOptionValue(
			cpOption.getCPOptionId(),
			LanguageUtils.getLocalizedMap(optionValue.getName()),
			OptionValueUtil.getPriority(optionValue), optionValue.getKey(),
			optionValue.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext(cpOption.getGroupId()));

		return _optionValueDTOMapper.toOptionValue(cpOptionValue);
	}

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private CPOptionValueService _cpOptionValueService;

	@Reference
	private OptionValueDTOMapper _optionValueDTOMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}