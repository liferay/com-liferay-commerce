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

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.internal.odata.entity.v1_0.OptionCategoryEntityModel;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/option-category.properties",
	scope = ServiceScope.PROTOTYPE, service = OptionCategoryResource.class
)
public class OptionCategoryResourceImpl
	extends BaseOptionCategoryResourceImpl implements EntityModelResource {

	@Override
	public Response deleteOptionCategory(Long id) throws Exception {
		_cpOptionCategoryService.deleteCPOptionCategory(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public Page<OptionCategory> getOptionCategoriesPage(
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			CPOptionCategory.class, StringPool.BLANK, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> _toOptionCategory(
				_cpOptionCategoryService.getCPOptionCategory(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public OptionCategory getOptionCategory(Long id) throws Exception {
		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		return (OptionCategory)optionCategoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public Response patchOptionCategory(Long id, OptionCategory optionCategory)
		throws Exception {

		_updateOptionCategory(id, optionCategory);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public OptionCategory postOptionCategory(OptionCategory optionCategory)
		throws Exception {

		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		CPOptionCategory cpOptionCategory = null;

		if (optionCategory.getId() != null) {
			cpOptionCategory = _cpOptionCategoryService.fetchCPOptionCategory(
				optionCategory.getId());
		}

		if (cpOptionCategory == null) {
			cpOptionCategory = _addOptionCategory(optionCategory);
		}
		else {
			cpOptionCategory = _updateOptionCategory(
				optionCategory.getId(), optionCategory);
		}

		return (OptionCategory)optionCategoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOptionCategory.getCPOptionCategoryId()));
	}

	private CPOptionCategory _addOptionCategory(OptionCategory optionCategory)
		throws Exception {

		return _cpOptionCategoryService.addCPOptionCategory(
			LanguageUtils.getLocalizedMap(optionCategory.getTitle()),
			LanguageUtils.getLocalizedMap(optionCategory.getDescription()),
			GetterUtil.get(optionCategory.getPriority(), 0D),
			optionCategory.getKey(),
			_serviceContextHelper.getServiceContext(_user));
	}

	private OptionCategory _toOptionCategory(CPOptionCategory cpOptionCategory)
		throws Exception {

		DTOConverter optionCategoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPOptionCategory.class.getName());

		return (OptionCategory)optionCategoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpOptionCategory.getCPOptionCategoryId()));
	}

	private CPOptionCategory _updateOptionCategory(
			Long id, OptionCategory optionCategory)
		throws PortalException {

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.getCPOptionCategory(id);

		return _cpOptionCategoryService.updateCPOptionCategory(
			cpOptionCategory.getCPOptionCategoryId(),
			LanguageUtils.getLocalizedMap(optionCategory.getTitle()),
			LanguageUtils.getLocalizedMap(optionCategory.getDescription()),
			GetterUtil.get(
				optionCategory.getPriority(), cpOptionCategory.getPriority()),
			optionCategory.getKey());
	}

	private static final EntityModel _entityModel =
		new OptionCategoryEntityModel();

	@Reference
	private CPOptionCategoryService _cpOptionCategoryService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}