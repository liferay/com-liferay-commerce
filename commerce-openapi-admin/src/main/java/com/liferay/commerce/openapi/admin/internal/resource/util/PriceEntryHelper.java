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

package com.liferay.commerce.openapi.admin.internal.resource.util;

import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.PriceEntryDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
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
			throw new NotFoundException(
				"Unable to find Price Entry with ID " + id);
		}

		return commercePriceEntry;
	}

	public CollectionDTO<PriceEntryDTO> getCommercePriceEntryDTOs(
			long groupId, Pagination pagination)
		throws PortalException {

		List<CommercePriceEntry> commercePriceEntries =
			_commercePriceEntryService.getCommercePriceEntriesByGroupId(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition());

		int count =
			_commercePriceEntryService.getCommercePriceEntriesCountByGroupId(
				groupId);

		List<PriceEntryDTO> priceEntryDTOs = new ArrayList<>();

		for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
			priceEntryDTOs.add(DTOUtils.modelToDTO(commercePriceEntry));
		}

		return new CollectionDTO<>(priceEntryDTOs, count);
	}

	public PriceEntryDTO getPriceEntryDTO(String id, long companyId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry = getCommercePriceEntry(
			id, companyId);

		return DTOUtils.modelToDTO(commercePriceEntry);
	}

	public void updateCommercePriceEntry(
			String id, long companyId, PriceEntryDTO priceEntryDTO)
		throws PortalException {

		CommercePriceEntry commercePriceEntry = getCommercePriceEntry(
			id, companyId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceEntry.getGroupId());

		_commercePriceEntryService.updateCommercePriceEntry(
			commercePriceEntry.getCommercePriceEntryId(),
			priceEntryDTO.getPrice(), priceEntryDTO.getPromoPrice(),
			serviceContext);
	}

	public PriceEntryDTO upsertCommercePriceEntry(
			long groupId, PriceEntryDTO priceEntryDTO)
		throws PortalException {

		CommercePriceEntry commercePriceEntry;

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		commercePriceEntry =
			_commercePriceEntryService.upsertCommercePriceEntry(
				priceEntryDTO.getId(),
				priceEntryDTO.getCommerceProductInstanceId(),
				priceEntryDTO.getCommercePriceListId(),
				priceEntryDTO.getExternalReferenceCode(),
				priceEntryDTO.getPrice(), priceEntryDTO.getPromoPrice(),
				priceEntryDTO.getSkuExternalReferenceCode(), serviceContext);

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		if (priceEntryDTO.getStandardPrice()) {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), priceEntryDTO.getPrice(),
				priceEntryDTO.getPromoPrice(), cpInstance.getCost(),
				serviceContext);
		}
		else {
			_cpInstanceService.updatePricingInfo(
				cpInstance.getCPInstanceId(), new BigDecimal(0),
				priceEntryDTO.getPromoPrice(), cpInstance.getCost(),
				serviceContext);
		}

		return DTOUtils.modelToDTO(commercePriceEntry);
	}

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}