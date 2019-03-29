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

import com.liferay.commerce.product.exception.NoSuchCPSpecificationOptionException;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SpecificationHelper.class)
public class SpecificationHelper {

	public void deleteSpecification(Long id) throws PortalException {
		_cpSpecificationOptionService.deleteCPSpecificationOption(id);
	}

	public Specification getSpecification(Long id) throws PortalException {
		return _dtoMapper.modelToDTO(
			_cpSpecificationOptionService.getCPSpecificationOption(id));
	}

	public Page<Specification> getSpecifications(
			long groupId, Pagination pagination)
		throws PortalException {

		List<CPSpecificationOption> cpSpecificationOptions =
			_cpSpecificationOptionService.getCPSpecificationOptions(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems =
			_cpSpecificationOptionService.getCPSpecificationOptionsCount(
				groupId);

		Stream<CPSpecificationOption> stream = cpSpecificationOptions.stream();

		return stream.map(
			cpSpecificationOption -> _dtoMapper.modelToDTO(
				cpSpecificationOption)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				specifications -> Page.of(
					specifications, pagination, totalItems))
		);
	}

	public CPSpecificationOption updateSpecification(
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
			_serviceContextHelper.getServiceContext(
				cpSpecificationOption.getGroupId()));
	}

	public Specification upsertSpecification(
			long groupId, Specification specification)
		throws PortalException {

		try {
			CPSpecificationOption cpSpecificationOption = updateSpecification(
				specification.getId(), specification);

			return _dtoMapper.modelToDTO(cpSpecificationOption);
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
				_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(cpSpecificationOption);
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

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationHelper.class);

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}