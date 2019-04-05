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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.SpecificationDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.SpecificationValueDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SpecificationValueUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationValueResource;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
	properties = "OSGI-INF/liferay/rest/v1_0/specification-value.properties",
	scope = ServiceScope.PROTOTYPE, service = SpecificationValueResource.class
)
public class SpecificationValueResourceImpl
	extends BaseSpecificationValueResourceImpl {

	@Override
	public Page<SpecificationValue> getSpecificationIdSpecificationValuesPage(
			Long id, Pagination pagination)
		throws Exception {

		Specification specification = _specificationDTOMapper.toSpecification(
			_cpSpecificationOptionService.getCPSpecificationOption(id));

		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValues(
						specification.getId(), pagination.getStartPosition(),
						pagination.getEndPosition());

		int totalItems =
			_cpDefinitionSpecificationOptionValueService.
				getCPDefinitionSpecificationOptionValuesCount(
					specification.getId());

		return Page.of(
			_toSpecificationValues(cpDefinitionSpecificationOptionValues),
			pagination, totalItems);
	}

	@Override
	public SpecificationValue postSpecificationIdSpecificationValue(
			Long id, SpecificationValue specificationValue)
		throws Exception {

		return _upsertSpecificationValue(id, specificationValue);
	}

	private List<SpecificationValue> _toSpecificationValues(
		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues) {

		List<SpecificationValue> specificationValues = new ArrayList<>();

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			specificationValues.add(
				_specificationValueDTOMapper.toSpecificationValue(
					cpDefinitionSpecificationOptionValue,
					contextAcceptLanguage.getPreferredLanguageId()));
		}

		return specificationValues;
	}

	private CPDefinitionSpecificationOptionValue _updateSpecificationValue(
			Long id, SpecificationValue specificationValue)
		throws PortalException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValue(id);

		return _cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId(),
				SpecificationValueUtil.getCPOptionCategoryId(
					specificationValue),
				LanguageUtils.getLocalizedMap(specificationValue.getValue()),
				SpecificationValueUtil.getPriority(specificationValue),
				_serviceContextHelper.getServiceContext(
					cpDefinitionSpecificationOptionValue.getGroupId()));
	}

	private SpecificationValue _upsertSpecificationValue(
			Long specificationId, SpecificationValue specificationValue)
		throws PortalException {

		try {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					_updateSpecificationValue(
						specificationValue.getId(), specificationValue);

			return _specificationValueDTOMapper.toSpecificationValue(
				cpDefinitionSpecificationOptionValue,
				contextAcceptLanguage.getPreferredLanguageId());
		}
		catch (NoSuchCPDefinitionSpecificationOptionValueException nscpdsove) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find specificationValue with ID: " +
						specificationValue.getId());
			}
		}

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionService.getCPSpecificationOption(
				specificationId);

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				_cpDefinitionSpecificationOptionValueService.
					addCPDefinitionSpecificationOptionValue(
						SpecificationValueUtil.getCPDefinitionId(
							specificationValue),
						SpecificationValueUtil.getCPSpecificationOptionId(
							specificationValue),
						SpecificationValueUtil.getCPOptionCategoryId(
							specificationValue),
						LanguageUtils.getLocalizedMap(
							specificationValue.getValue()),
						SpecificationValueUtil.getPriority(specificationValue),
						_serviceContextHelper.getServiceContext(
							cpSpecificationOption.getGroupId()));

		return _specificationValueDTOMapper.toSpecificationValue(
			cpDefinitionSpecificationOptionValue,
			contextAcceptLanguage.getPreferredLanguageId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationValueResourceImpl.class);

	@Reference
	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

	@Reference
	private CPSpecificationOptionService _cpSpecificationOptionService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private SpecificationDTOMapper _specificationDTOMapper;

	@Reference
	private SpecificationValueDTOMapper _specificationValueDTOMapper;

}