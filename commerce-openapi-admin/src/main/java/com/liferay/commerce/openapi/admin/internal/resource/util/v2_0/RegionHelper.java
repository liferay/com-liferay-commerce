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

import com.liferay.commerce.exception.NoSuchRegionException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.RegionDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
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
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = RegionHelper.class)
public class RegionHelper {

	public void deleteRegion(String id) throws PortalException {
		_commerceRegionService.deleteCommerceRegion(GetterUtil.getLong(id));
	}

	public RegionDTO getRegionDTO(String id) throws PortalException {
		CommerceRegion commerceRegion =
			_commerceRegionService.getCommerceRegion(GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceRegion);
	}

	public CollectionDTO<RegionDTO> getRegionDTOs(
			String commerceCountryId, Pagination pagination)
		throws PortalException {

		List<CommerceRegion> commerceRegions =
			_commerceRegionService.getCommerceRegions(
				GetterUtil.getLong(commerceCountryId),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int count = _commerceRegionService.getCommerceRegionsCount(
			GetterUtil.getLong(commerceCountryId));

		List<RegionDTO> regionDTOs = new ArrayList<>();

		for (CommerceRegion commerceRegion : commerceRegions) {
			regionDTOs.add(DTOUtils.modelToDTO(commerceRegion));
		}

		return new CollectionDTO<>(regionDTOs, count);
	}

	public CommerceRegion updateRegion(
			String id, RegionDTO regionDTO, User user)
		throws PortalException {

		CommerceRegion commerceRegion =
			_commerceRegionService.getCommerceRegion(GetterUtil.getLong(id));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceRegion.getGroupId(), new long[0], user, true);

		return _commerceRegionService.updateCommerceRegion(
			GetterUtil.getLong(id), regionDTO.getName(), regionDTO.getCode(),
			regionDTO.getPriority(), regionDTO.isActive(), serviceContext);
	}

	public RegionDTO upsertRegion(
			String commerceCountryId, RegionDTO regionDTO, User user)
		throws PortalException {

		try {
			CommerceRegion commerceRegion = updateRegion(
				String.valueOf(regionDTO.getId()), regionDTO, user);

			return DTOUtils.modelToDTO(commerceRegion);
		}
		catch (NoSuchRegionException nsre) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find region with ID: " + regionDTO.getId());
			}
		}

		CommerceCountry commerceCountry =
			_commerceCountryService.getCommerceCountry(
				GetterUtil.getLong(commerceCountryId));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceCountry.getGroupId(), new long[0], user, true);

		CommerceRegion commerceRegion =
			_commerceRegionService.addCommerceRegion(
				regionDTO.getCommerceCountryId(), regionDTO.getName(),
				regionDTO.getCode(), regionDTO.getPriority(),
				regionDTO.isActive(), serviceContext);

		return DTOUtils.modelToDTO(commerceRegion);
	}

	private static final Log _log = LogFactoryUtil.getLog(RegionHelper.class);

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceRegionService _commerceRegionService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}