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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;

import java.math.BigDecimal;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommercePriceEntryHelper.class)
public class CommercePriceEntryHelper {

	public static String getSKU(CommercePriceEntry commercePriceEntry) {
		CPInstance cpInstance = _getCPInstance(commercePriceEntry);

		return Try.fromFallible(
			cpInstance::getSku
		).orElse(
			null
		);
	}

	public static String getSKUExternalReferenceCode(
		CommercePriceEntry commercePriceEntry) {

		CPInstance cpInstance = _getCPInstance(commercePriceEntry);

		return Try.fromFallible(
			cpInstance::getExternalReferenceCode
		).orElse(
			null
		);
	}

	public ClassPKExternalReferenceCode
		commercePriceEntryIdToClassPKExternalReferenceCode(
			long commercePriceEntryId) {

		try {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryService.fetchCommercePriceEntry(
					commercePriceEntryId);

			return commercePriceEntryToClassPKExternalReferenceCode(
				commercePriceEntry);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find Price Entry with ID " + commercePriceEntryId,
				pe);
		}

		return null;
	}

	public ClassPKExternalReferenceCode
		commercePriceEntryToClassPKExternalReferenceCode(
			CommercePriceEntry commercePriceEntry) {

		if (commercePriceEntry != null) {
			return ClassPKExternalReferenceCode.create(
				commercePriceEntry.getCommercePriceEntryId(),
				commercePriceEntry.getExternalReferenceCode());
		}

		return null;
	}

	public void deletePriceEntry(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			getCommercePriceEntryByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		if (commercePriceEntry != null) {
			_commercePriceEntryService.deleteCommercePriceEntry(
				commercePriceEntry.getCommercePriceEntryId());
		}
	}

	public CommercePriceEntry
			getCommercePriceEntryByClassPKExternalReferenceCode(
				ClassPKExternalReferenceCode
					commercePriceEntryClassPKExternalReferenceCode)
		throws PortalException {

		long commercePriceEntryId =
			commercePriceEntryClassPKExternalReferenceCode.getClassPK();

		if (commercePriceEntryId > 0) {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryService.fetchCommercePriceEntry(
					commercePriceEntryId);

			if (commercePriceEntry == null) {
				throw new NotFoundException(
					"Unable to find price entry with ID " +
						commercePriceEntryId);
			}

			return commercePriceEntry;
		}
		else {
			String externalReferenceCode =
				commercePriceEntryClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _commercePriceEntryService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	public CommercePriceEntry updateCommercePriceEntry(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Double price, Double promoPrice)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			getCommercePriceEntryByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceEntry.getGroupId());

		return _commercePriceEntryService.updateCommercePriceEntry(
			commercePriceEntry.getCommercePriceEntryId(),
			BigDecimal.valueOf(price), BigDecimal.valueOf(promoPrice),
			serviceContext);
	}

	public CommercePriceEntry upsertCommercePriceEntry(
			Long commercePriceEntryId, Long commerceProductInstanceId,
			Long commercePriceListId, String externalReferenceCode,
			String skuExternalReferenceCode, Double price, Double promoPrice,
			Boolean standardPrice)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListHelper.getCommercePriceList(commercePriceListId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryService.upsertCommercePriceEntry(
				commercePriceEntryId, commerceProductInstanceId,
				commercePriceListId, externalReferenceCode,
				BigDecimal.valueOf(price), BigDecimal.valueOf(promoPrice),
				skuExternalReferenceCode, serviceContext);

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		if (standardPrice) {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), new BigDecimal(price),
				new BigDecimal(promoPrice), cpInstance.getCost(),
				serviceContext);
		}
		else {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), new BigDecimal(0),
				new BigDecimal(promoPrice), cpInstance.getCost(),
				serviceContext);
		}

		return commercePriceEntry;
	}

	private static CPInstance _getCPInstance(
		CommercePriceEntry commercePriceEntry) {

		CPInstance cpInstance = null;

		try {
			cpInstance = commercePriceEntry.getCPInstance();
		}
		catch (PortalException pe) {
			throw new NotFoundException(
				"Unable to find Product Instance for Price Entry with ID " +
					commercePriceEntry.getCommercePriceEntryId(),
				pe);
		}

		return cpInstance;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceEntryHelper.class);

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CommercePriceListHelper _commercePriceListHelper;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}