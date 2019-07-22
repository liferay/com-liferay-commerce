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

import com.liferay.commerce.product.exception.NoSuchCPSpecificationOptionException;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/specification.properties",
	scope = ServiceScope.PROTOTYPE, service = SpecificationResource.class
)
public class SpecificationResourceImpl extends BaseSpecificationResourceImpl {

	@Override
	public Response deleteSpecification(Long id) throws Exception {
		_cpSpecificationOptionService.deleteCPSpecificationOption(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Specification getSpecification(Long id) throws Exception {
		DTOConverter specificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPSpecificationOption.class.getName());

		return (Specification)specificationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public Page<Specification> getSpecificationsPage(Pagination pagination)
		throws Exception {

		BaseModelSearchResult<CPSpecificationOption>
			cpSpecificationOptionBaseModelSearchResult =
				_cpSpecificationOptionService.searchCPSpecificationOptions(
					_user.getCompanyId(), null, null,
					pagination.getStartPosition(), pagination.getEndPosition(),
					null);

		return Page.of(
			_toSpecifications(
				cpSpecificationOptionBaseModelSearchResult.getBaseModels()),
			pagination, cpSpecificationOptionBaseModelSearchResult.getLength());
	}

	@Override
	public Response patchSpecification(Long id, Specification specification)
		throws Exception {

		_updateSpecification(id, specification);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Specification postSpecification(Specification specification)
		throws Exception {

		return _upsertSpecification(specification);
	}

	private long _getCPOptionCategoryId(Specification specification) {
		OptionCategory optionCategory = specification.getOptionCategory();

		if (optionCategory == null) {
			return 0;
		}

		return optionCategory.getId();
	}

	private boolean _isFacetable(Specification specification) {
		boolean facetable = false;

		if (specification.getFacetable() != null) {
			facetable = specification.getFacetable();
		}

		return facetable;
	}

	private List<Specification> _toSpecifications(
			List<CPSpecificationOption> cpSpecificationOptions)
		throws Exception {

		List<Specification> specifications = new ArrayList<>();

		DTOConverter specificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPSpecificationOption.class.getName());

		for (CPSpecificationOption cpSpecificationOption :
				cpSpecificationOptions) {

			specifications.add(
				(Specification)specificationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpSpecificationOption.getCPSpecificationOptionId())));
		}

		return specifications;
	}

	private CPSpecificationOption _updateSpecification(
			Long id, Specification specification)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.getCPSpecificationOption(id);

		return _cpSpecificationOptionService.updateCPSpecificationOption(
			cpSpecificationOption.getCPSpecificationOptionId(),
			_getCPOptionCategoryId(specification),
			LanguageUtils.getLocalizedMap(specification.getTitle()),
			LanguageUtils.getLocalizedMap(specification.getDescription()),
			_isFacetable(specification), specification.getKey(),
			_serviceContextHelper.getServiceContext());
	}

	private Specification _upsertSpecification(Specification specification)
		throws Exception {

		DTOConverter specificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPSpecificationOption.class.getName());

		try {
			CPSpecificationOption cpSpecificationOption = _updateSpecification(
				specification.getId(), specification);

			return (Specification)specificationDTOConverter.toDTO(
				new DefaultDTOConverterContext(
					contextAcceptLanguage.getPreferredLocale(),
					cpSpecificationOption.getCPSpecificationOptionId()));
		}
		catch (NoSuchCPSpecificationOptionException nscpsoe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find specification with ID: " +
						specification.getId());
			}
		}

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.addCPSpecificationOption(
				_getCPOptionCategoryId(specification),
				LanguageUtils.getLocalizedMap(specification.getTitle()),
				LanguageUtils.getLocalizedMap(specification.getDescription()),
				_isFacetable(specification), specification.getKey(),
				_serviceContextHelper.getServiceContext());

		return (Specification)specificationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				cpSpecificationOption.getCPSpecificationOptionId()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationResourceImpl.class);

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}