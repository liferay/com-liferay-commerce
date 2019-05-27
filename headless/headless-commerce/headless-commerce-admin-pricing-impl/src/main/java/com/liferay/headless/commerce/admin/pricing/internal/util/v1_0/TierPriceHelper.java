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

import com.liferay.commerce.price.list.exception.NoSuchTierPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
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
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = TierPriceHelper.class)
public class TierPriceHelper {

	public void deleteCommerceTierPriceEntry(String id, long companyId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			getCommerceTierPriceEntry(id, companyId);

		_commerceTierPriceEntryService.deleteCommerceTierPriceEntry(
			commerceTierPriceEntry.getCommerceTierPriceEntryId());
	}

	public CommerceTierPriceEntry getCommerceTierPriceEntry(
			String id, long companyId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry;

		if (IdUtils.isLocalPK(id)) {
			commerceTierPriceEntry =
				_commerceTierPriceEntryService.fetchCommerceTierPriceEntry(
					GetterUtil.getLong(id));
		}
		else {
			commerceTierPriceEntry =
				_commerceTierPriceEntryService.fetchByExternalReferenceCode(
					companyId, IdUtils.getExternalReferenceCodeFromId(id));
		}

		if (commerceTierPriceEntry == null) {
			throw new NoSuchTierPriceEntryException(
				"Unable to find Tier Price with ID " + id);
		}

		return commerceTierPriceEntry;
	}

	public TierPrice getTierPrice(String id, long companyId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			getCommerceTierPriceEntry(id, companyId);

		return _dtoMapper.modelToDTO(commerceTierPriceEntry);
	}

	public Page<TierPrice> getTierPrices(
			String priceEntryId, Company company, Pagination pagination)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			_priceEntryHelper.getCommercePriceEntry(
				priceEntryId, company.getCompanyId());

		List<CommerceTierPriceEntry> commerceTierPriceEntries =
			_commerceTierPriceEntryService.getCommerceTierPriceEntries(
				commercePriceEntry.getCommercePriceEntryId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int count =
			_commerceTierPriceEntryService.getCommerceTierPriceEntriesCount(
				commercePriceEntry.getCommercePriceEntryId());

		List<TierPrice> tierPrices = new ArrayList<>();

		for (CommerceTierPriceEntry commerceTierPriceEntry :
				commerceTierPriceEntries) {

			tierPrices.add(_dtoMapper.modelToDTO(commerceTierPriceEntry));
		}

		return Page.of(tierPrices, pagination, count);
	}

	public void updateCommerceTierPriceEntry(
			String id, long companyId, TierPrice tierPrice)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			getCommerceTierPriceEntry(id, companyId);

		CommercePriceEntry commercePriceEntry =
			commerceTierPriceEntry.getCommercePriceEntry();

		CommercePriceList commercePriceList =
			commercePriceEntry.getCommercePriceList();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		_commerceTierPriceEntryService.updateCommerceTierPriceEntry(
			commerceTierPriceEntry.getCommerceTierPriceEntryId(),
			(BigDecimal)GetterUtil.get(
				tierPrice.getPrice(), commerceTierPriceEntry.getPrice()),
			(BigDecimal)GetterUtil.get(
				tierPrice.getPromoPrice(),
				commerceTierPriceEntry.getPromoPrice()),
			GetterUtil.get(
				tierPrice.getMinimumQuantity(),
				commerceTierPriceEntry.getMinQuantity()),
			serviceContext);
	}

	public TierPrice upsertCommerceTierPriceEntry(
			String commercePriceEntryId, TierPrice tierPrice, Company company)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			_priceEntryHelper.getCommercePriceEntry(
				commercePriceEntryId, company.getCompanyId());

		CommercePriceList commercePriceList =
			commercePriceEntry.getCommercePriceList();

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		CommerceTierPriceEntry commerceTierPriceEntry =
			_commerceTierPriceEntryService.upsertCommerceTierPriceEntry(
				GetterUtil.get(tierPrice.getId(), 0),
				tierPrice.getCommercePriceEntryId(),
				tierPrice.getExternalReferenceCode(), tierPrice.getPrice(),
				tierPrice.getPromoPrice(),
				GetterUtil.get(tierPrice.getMinimumQuantity(), 0),
				tierPrice.getPriceEntryExternalReferenceCode(), serviceContext);

		return _dtoMapper.modelToDTO(commerceTierPriceEntry);
	}

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private PriceEntryHelper _priceEntryHelper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}