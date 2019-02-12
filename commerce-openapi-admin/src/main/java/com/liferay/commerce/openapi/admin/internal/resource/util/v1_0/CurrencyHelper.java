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

import com.liferay.commerce.currency.exception.NoSuchCurrencyException;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v1_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v1_0.CurrencyDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
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
@Component(immediate = true, service = CurrencyHelper.class)
public class CurrencyHelper {

	public void deleteCurrency(String id) throws PortalException {
		_commerceCurrencyService.deleteCommerceCurrency(GetterUtil.getLong(id));
	}

	public CurrencyDTO getCurrencyDTO(String id) throws PortalException {
		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(
				GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceCurrency);
	}

	public CollectionDTO<CurrencyDTO> getCurrencyDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceCurrency> commerceCurrencies =
			_commerceCurrencyService.getCommerceCurrencies(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _commerceCurrencyService.getCommerceCurrenciesCount(
			groupId);

		List<CurrencyDTO> currencyDTOs = new ArrayList<>();

		for (CommerceCurrency commerceCurrency : commerceCurrencies) {
			currencyDTOs.add(DTOUtils.modelToDTO(commerceCurrency));
		}

		return new CollectionDTO<>(currencyDTOs, count);
	}

	public CommerceCurrency updateCurrency(
			Long groupId, String id, CurrencyDTO currencyDTO, User user)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		return _commerceCurrencyService.updateCommerceCurrency(
			GetterUtil.getLong(id), currencyDTO.getCode(),
			LanguageUtils.getLocalizedMap(currencyDTO.getName()),
			currencyDTO.getRate(),
			LanguageUtils.getLocalizedMap(currencyDTO.getFormatPattern()),
			currencyDTO.getMaxFractionDigits(),
			currencyDTO.getMinFractionDigits(), currencyDTO.getRoundingMode(),
			currencyDTO.isPrimary(), 0D, true, serviceContext);
	}

	public CurrencyDTO upsertCurrency(
			Long groupId, CurrencyDTO currencyDTO, User user)
		throws PortalException {

		try {
			CommerceCurrency commerceCurrency = updateCurrency(
				groupId, String.valueOf(currencyDTO.getId()), currencyDTO,
				user);

			return DTOUtils.modelToDTO(commerceCurrency);
		}
		catch (NoSuchCurrencyException nsce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find currency with ID: " + currencyDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.addCommerceCurrency(
				currencyDTO.getCode(),
				LanguageUtils.getLocalizedMap(currencyDTO.getName()),
				currencyDTO.getRate(),
				LanguageUtils.getLocalizedMap(currencyDTO.getFormatPattern()),
				currencyDTO.getMaxFractionDigits(),
				currencyDTO.getMinFractionDigits(),
				currencyDTO.getRoundingMode(), currencyDTO.isPrimary(), 0D,
				true, serviceContext);

		return DTOUtils.modelToDTO(commerceCurrency);
	}

	private static final Log _log = LogFactoryUtil.getLog(CurrencyHelper.class);

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}