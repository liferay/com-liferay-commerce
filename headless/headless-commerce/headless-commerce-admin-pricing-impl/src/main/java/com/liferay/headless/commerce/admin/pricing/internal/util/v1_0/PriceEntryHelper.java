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

package com.liferay.headless.commerce.admin.pricing.internal.util.v1_0;

import com.liferay.commerce.price.list.exception.NoSuchPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = PriceEntryHelper.class)
public class PriceEntryHelper {

	public void deleteCommercePriceEntry(String id, long companyId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry = getCommercePriceEntry(
			id, companyId);

		_commercePriceEntryService.deleteCommercePriceEntry(
			commercePriceEntry.getCommercePriceEntryId());
	}

	public CommercePriceEntry getCommercePriceEntry(String id, long companyId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry;

		if (IdUtils.isLocalPK(id)) {
			commercePriceEntry =
				_commercePriceEntryService.fetchCommercePriceEntry(
					GetterUtil.getLong(id));
		}
		else {
			commercePriceEntry =
				_commercePriceEntryService.fetchByExternalReferenceCode(
					companyId, IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (commercePriceEntry == null) {
			throw new NoSuchPriceEntryException(
				"Unable to find Price Entry with ID " + id);
		}

		return commercePriceEntry;
	}

	public Page<PriceEntry> getPriceEntries(
			String priceListId, Company company, Pagination pagination)
		throws PortalException {

		CommercePriceList commercePriceList = _priceListHelper.getPriceListById(
			priceListId, company);

		List<CommercePriceEntry> commercePriceEntries =
			_commercePriceEntryService.getCommercePriceEntries(
				commercePriceList.getCommercePriceListId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int count = _commercePriceEntryService.getCommercePriceEntriesCount(
			commercePriceList.getCommercePriceListId());

		List<PriceEntry> priceEntries = new ArrayList<>();

		for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
			priceEntries.add(_dtoMapper.modelToDTO(commercePriceEntry));
		}

		return Page.of(priceEntries, pagination, count);
	}

	public PriceEntry getPriceEntry(String id, long companyId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry = getCommercePriceEntry(
			id, companyId);

		return _dtoMapper.modelToDTO(commercePriceEntry);
	}

	public void updateCommercePriceEntry(
			String id, long companyId, PriceEntry priceEntry)
		throws PortalException {

		CommercePriceEntry commercePriceEntry = getCommercePriceEntry(
			id, companyId);

		CommercePriceList commercePriceList =
			commercePriceEntry.getCommercePriceList();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		_commercePriceEntryService.updateCommercePriceEntry(
			commercePriceEntry.getCommercePriceEntryId(), priceEntry.getPrice(),
			priceEntry.getPromoPrice(), serviceContext);
	}

	public PriceEntry upsertCommercePriceEntry(
			String commercePriceListId, PriceEntry priceEntry, Company company)
		throws PortalException {

		CommercePriceList commercePriceList = _priceListHelper.getPriceListById(
			commercePriceListId, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryService.upsertCommercePriceEntry(
				GetterUtil.get(priceEntry.getId(), 0),
				GetterUtil.get(priceEntry.getCommerceProductInstanceId(), 0),
				priceEntry.getCommercePriceListId(),
				priceEntry.getExternalReferenceCode(), priceEntry.getPrice(),
				priceEntry.getPromoPrice(),
				priceEntry.getSkuExternalReferenceCode(), serviceContext);

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		if (priceEntry.getStandardPrice()) {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), priceEntry.getPrice(),
				priceEntry.getPromoPrice(), cpInstance.getCost(),
				serviceContext);
		}
		else {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), new BigDecimal(0),
				priceEntry.getPromoPrice(), cpInstance.getCost(),
				serviceContext);
		}

		return _dtoMapper.modelToDTO(commercePriceEntry);
	}

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private PriceListHelper _priceListHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}