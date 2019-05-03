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
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Option;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
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
	properties = "OSGI-INF/liferay/rest/v1_0/option.properties",
	scope = ServiceScope.PROTOTYPE, service = OptionResource.class
)
public class OptionResourceImpl extends BaseOptionResourceImpl {

	@Override
	public Response deleteOption(Long id) throws Exception {
		CPOption cpOption = _cpOptionService.getCPOption(id);

		_cpOptionService.deleteCPOption(cpOption.getCPOptionId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteOptionByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		_cpOptionService.deleteCPOption(cpOption.getCPOptionId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<Option> getCatalogSiteOptionsPage(
			Long siteId, Pagination pagination)
		throws Exception {

		List<CPOption> cpOptions = _cpOptionService.getCPOptions(
			siteId, pagination.getStartPosition(), pagination.getEndPosition(),
			null);

		int totalItems = _cpOptionService.getCPOptionsCount(siteId);

		return Page.of(_toOptions(cpOptions), pagination, totalItems);
	}

	@Override
	public Option getOption(Long id) throws Exception {
		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public Option getOptionByExternalReferenceCode(String externalReferenceCode)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
	}

	@Override
	public Response patchOption(Long id, Option option) throws Exception {
		CPOption cpOption = _cpOptionService.getCPOption(id);

		_updateOption(cpOption, option);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOptionByExternalReferenceCode(
			String externalReferenceCode, Option option)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateOption(cpOption, option);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Option postCatalogSiteOption(Long siteId, Option option)
		throws Exception {

		return _upsertOption(siteId, option);
	}

	private List<Option> _toOptions(List<CPOption> cpOptions) throws Exception {
		List<Option> options = new ArrayList<>();

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		for (CPOption cpOption : cpOptions) {
			options.add(
				(Option)optionDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpOption.getCPOptionId())));
		}

		return options;
	}

	private Option _updateOption(CPOption cpOption, Option option)
		throws Exception {

		Option.FieldType fieldType = option.getFieldType();

		cpOption = _cpOptionService.updateCPOption(
			cpOption.getCPOptionId(),
			LanguageUtils.getLocalizedMap(option.getName()),
			LanguageUtils.getLocalizedMap(option.getDescription()),
			fieldType.getValue(),
			GetterUtil.get(option.getFacetable(), cpOption.isFacetable()),
			GetterUtil.get(option.getRequired(), cpOption.isRequired()),
			GetterUtil.get(
				option.getSkuContributor(), cpOption.isSkuContributor()),
			option.getKey(),
			_serviceContextHelper.getServiceContext(cpOption.getGroupId()));

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
	}

	private Option _upsertOption(long siteId, Option option) throws Exception {
		Option.FieldType fieldType = option.getFieldType();

		CPOption cpOption = _cpOptionService.upsertCPOption(
			LanguageUtils.getLocalizedMap(option.getName()),
			LanguageUtils.getLocalizedMap(option.getDescription()),
			fieldType.getValue(), GetterUtil.get(option.getFacetable(), false),
			GetterUtil.get(option.getRequired(), false),
			GetterUtil.get(option.getSkuContributor(), false), option.getKey(),
			option.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext(siteId));

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
	}

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}