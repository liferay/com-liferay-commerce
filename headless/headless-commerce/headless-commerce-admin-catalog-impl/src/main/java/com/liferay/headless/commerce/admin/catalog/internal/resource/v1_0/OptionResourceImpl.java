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
import com.liferay.headless.commerce.admin.catalog.internal.odata.entity.v1_0.OptionEntityModel;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import javax.ws.rs.core.MultivaluedMap;
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
public class OptionResourceImpl
	extends BaseOptionResourceImpl implements EntityModelResource {

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
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public Option getOption(Long id) throws Exception {
		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
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
	public Page<Option> getOptionsPage(
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			CPOption.class, StringPool.BLANK, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> _toOption(
				_cpOptionService.getCPOption(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Response patchOption(Long id, Option option) throws Exception {
		_updateOption(_cpOptionService.getCPOption(id), option);

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
	public Option postOption(Option option) throws Exception {
		return _upsertOption(option);
	}

	private Option _toOption(CPOption cpOption) throws Exception {
		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
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
			option.getKey(), _serviceContextHelper.getServiceContext());

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
	}

	private Option _upsertOption(Option option) throws Exception {
		Option.FieldType fieldType = option.getFieldType();

		CPOption cpOption = _cpOptionService.upsertCPOption(
			LanguageUtils.getLocalizedMap(option.getName()),
			LanguageUtils.getLocalizedMap(option.getDescription()),
			fieldType.getValue(), GetterUtil.get(option.getFacetable(), false),
			GetterUtil.get(option.getRequired(), false),
			GetterUtil.get(option.getSkuContributor(), false), option.getKey(),
			option.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext());

		DTOConverter optionDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPOption.class.getName());

		return (Option)optionDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOption.getCPOptionId()));
	}

	private static final EntityModel _entityModel = new OptionEntityModel();

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}