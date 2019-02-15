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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.exception.NoSuchCountryException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.CountryDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(immediate = true, service = CountryHelper.class)
public class CountryHelper {

	public void deleteCountry(String id) throws PortalException {
		_commerceCountryService.deleteCommerceCountry(GetterUtil.getLong(id));
	}

	public CountryDTO getCountryDTO(String id) throws PortalException {
		CommerceCountry commerceCountry =
			_commerceCountryService.getCommerceCountry(GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceCountry);
	}

	public CollectionDTO<CountryDTO> getCountryDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getCommerceCountries(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _commerceCountryService.getCommerceCountriesCount(groupId);

		List<CountryDTO> countryDTOs = new ArrayList<>();

		for (CommerceCountry commerceCountry : commerceCountries) {
			countryDTOs.add(DTOUtils.modelToDTO(commerceCountry));
		}

		return new CollectionDTO<>(countryDTOs, count);
	}

	public CommerceCountry updateCountry(
			Long groupId, String id, CountryDTO countryDTO, User user)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		return _commerceCountryService.updateCommerceCountry(
			GetterUtil.getLong(id),
			LanguageUtils.getLocalizedMap(countryDTO.getName()),
			GetterUtil.get(countryDTO.isBillingAllowed(), false),
			GetterUtil.get(countryDTO.isShippingAllowed(), false),
			countryDTO.getTwoLettersISOCode(),
			countryDTO.getThreeLettersISOCode(), countryDTO.getNumericISOCode(),
			GetterUtil.get(countryDTO.isSubjectToVAT(), false), 0D, true,
			serviceContext);
	}

	public CountryDTO upsertCountry(
			Long groupId, CountryDTO countryDTO, User user)
		throws PortalException {

		try {
			CommerceCountry commerceCountry = updateCountry(
				groupId, String.valueOf(countryDTO.getId()), countryDTO, user);

			return DTOUtils.modelToDTO(commerceCountry);
		}
		catch (NoSuchCountryException nsce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find country with ID: " + countryDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceCountry commerceCountry =
			_commerceCountryService.addCommerceCountry(
				LanguageUtils.getLocalizedMap(countryDTO.getName()),
				GetterUtil.get(countryDTO.isBillingAllowed(), false),
				GetterUtil.get(countryDTO.isShippingAllowed(), false),
				countryDTO.getTwoLettersISOCode(),
				countryDTO.getThreeLettersISOCode(),
				countryDTO.getNumericISOCode(),
				GetterUtil.get(countryDTO.isSubjectToVAT(), false), 0D, true,
				serviceContext);

		return DTOUtils.modelToDTO(commerceCountry);
	}

	private static final Log _log = LogFactoryUtil.getLog(CountryHelper.class);

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}