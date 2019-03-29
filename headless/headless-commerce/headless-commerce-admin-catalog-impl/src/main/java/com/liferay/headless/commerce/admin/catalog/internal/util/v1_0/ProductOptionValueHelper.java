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

import com.liferay.commerce.product.exception.NoSuchCPOptionValueException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductOptionValueHelper.class)
public class ProductOptionValueHelper {

	public void deleteProductOptionValue(String id, Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = getCPOptionValueById(id, company);

		_cpOptionValueService.deleteCPOptionValue(
			cpOptionValue.getCPOptionValueId());
	}

	public CPOptionValue getCPOptionValueById(String id, Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = null;

		if (IdUtils.isLocalPK(id)) {
			cpOptionValue = _cpOptionValueService.getCPOptionValue(
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

	public ProductOptionValue getProductOptionValue(
			String id, AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			getCPOptionValueById(id, company),
			acceptLanguage.getPreferredLanguageId());
	}

	public Page<ProductOptionValue> getProductOptionValues(
			String productOptionId, AcceptLanguage acceptLanguage,
			Company company, Pagination pagination)
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
			cpOptionValue -> _dtoMapper.modelToDTO(
				cpOptionValue, acceptLanguage.getPreferredLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				productOptionValues -> Page.of(
					productOptionValues, pagination, totalItems))
		);
	}

	public ProductOptionValue updateProductOptionValue(
			String id, long groupId, ProductOptionValue productOptionValue,
			AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		CPOptionValue cpOptionValue = getCPOptionValueById(id, company);

		Map<Locale, String> nameMap = cpOptionValue.getNameMap();

		nameMap.put(
			LocaleUtil.fromLanguageId(acceptLanguage.getPreferredLanguageId()),
			productOptionValue.getName());

		cpOptionValue = _cpOptionValueService.updateCPOptionValue(
			cpOptionValue.getCPOptionValueId(), nameMap,
			_getPriority(productOptionValue), productOptionValue.getKey(),
			_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(
			cpOptionValue, acceptLanguage.getPreferredLanguageId());
	}

	public ProductOptionValue upsertProductOptionValue(
			String productOptionId, long groupId,
			ProductOptionValue productOptionValue,
			AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		CPOption cpOption = _productOptionHelper.getCPOptionById(
			productOptionId, company);

		Map<Locale, String> nameMap = new HashMap<Locale, String>() {
			{
				put(
					LocaleUtil.fromLanguageId(
						acceptLanguage.getPreferredLanguageId()),
					productOptionValue.getName());
			}
		};

		CPOptionValue cpOptionValue = _cpOptionValueService.upsertCPOptionValue(
			cpOption.getCPOptionId(), nameMap, _getPriority(productOptionValue),
			productOptionValue.getKey(),
			productOptionValue.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext(groupId));

		return _dtoMapper.modelToDTO(
			cpOptionValue, acceptLanguage.getPreferredLanguageId());
	}

	private double _getPriority(ProductOptionValue productOptionValue) {
		double priority = 0;

		if (productOptionValue.getPriority() != null) {
			priority = productOptionValue.getPriority();
		}

		return priority;
	}

	@Reference
	private CPOptionValueService _cpOptionValueService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}