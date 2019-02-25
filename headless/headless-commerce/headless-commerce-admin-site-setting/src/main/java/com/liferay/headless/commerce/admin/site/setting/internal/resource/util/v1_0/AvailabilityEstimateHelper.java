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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0;

import com.liferay.commerce.exception.NoSuchAvailabilityEstimateException;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.service.CommerceAvailabilityEstimateService;
import com.liferay.headless.commerce.admin.site.setting.internal.v1_0.DTOUtils;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
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
@Component(immediate = true, service = AvailabilityEstimateHelper.class)
public class AvailabilityEstimateHelper {

	public void deleteAvailabilityEstimate(String id) throws PortalException {
		_commerceAvailabilityEstimateService.deleteCommerceAvailabilityEstimate(
			GetterUtil.getLong(id));
	}

	public AvailabilityEstimateDTO getAvailabilityEstimateDTO(String id)
		throws PortalException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimate(GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceAvailabilityEstimate);
	}

	public CollectionDTO<AvailabilityEstimateDTO> getAvailabilityEstimateDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimates(
					groupId, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

		int count =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimatesCount(groupId);

		List<AvailabilityEstimateDTO> availabilityEstimateDTOs =
			new ArrayList<>();

		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				commerceAvailabilityEstimates) {

			availabilityEstimateDTOs.add(
				DTOUtils.modelToDTO(commerceAvailabilityEstimate));
		}

		return new CollectionDTO<>(availabilityEstimateDTOs, count);
	}

	public CommerceAvailabilityEstimate updateAvailabilityEstimate(
			String id, AvailabilityEstimateDTO availabilityEstimateDTO,
			User user)
		throws PortalException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimate(GetterUtil.getLong(id));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceAvailabilityEstimate.getGroupId(), new long[0], user, true);

		return _commerceAvailabilityEstimateService.
			updateCommerceAvailabilityEstimate(
				commerceAvailabilityEstimate.
					getCommerceAvailabilityEstimateId(),
				LanguageUtils.getLocalizedMap(
					availabilityEstimateDTO.getTitle()),
				GetterUtil.get(
					availabilityEstimateDTO.getPriority(),
					commerceAvailabilityEstimate.getPriority()),
				serviceContext);
	}

	public AvailabilityEstimateDTO upsertAvailabilityEstimate(
			Long groupId, AvailabilityEstimateDTO availabilityEstimateDTO,
			User user)
		throws PortalException {

		try {
			CommerceAvailabilityEstimate commerceAvailabilityEstimate =
				updateAvailabilityEstimate(
					String.valueOf(availabilityEstimateDTO.getId()),
					availabilityEstimateDTO, user);

			return DTOUtils.modelToDTO(commerceAvailabilityEstimate);
		}
		catch (NoSuchAvailabilityEstimateException nsaee) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find availabilityEstimate with ID: " +
						availabilityEstimateDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			_commerceAvailabilityEstimateService.
				addCommerceAvailabilityEstimate(
					LanguageUtils.getLocalizedMap(
						availabilityEstimateDTO.getTitle()),
					GetterUtil.get(availabilityEstimateDTO.getPriority(), 0D),
					serviceContext);

		return DTOUtils.modelToDTO(commerceAvailabilityEstimate);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AvailabilityEstimateHelper.class);

	@Reference
	private CommerceAvailabilityEstimateService
		_commerceAvailabilityEstimateService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}