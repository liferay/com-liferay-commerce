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

package com.liferay.commerce.headless.admin.site.setting.internal.v1_0;

import com.liferay.commerce.headless.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.openapi.core.util.LanguageUtils;

/**
 * @author Alessio Antonio Rendina
 */
public class DTOUtils {

	public static AvailabilityEstimateDTO modelToDTO(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		AvailabilityEstimateDTO availabilityEstimateDTO =
			new AvailabilityEstimateDTO();

		availabilityEstimateDTO.setId(
			commerceAvailabilityEstimate.getCommerceAvailabilityEstimateId());
		availabilityEstimateDTO.setPriority(
			commerceAvailabilityEstimate.getPriority());
		availabilityEstimateDTO.setTitle(
			LanguageUtils.getLanguageIdMap(
				commerceAvailabilityEstimate.getTitleMap()));

		return availabilityEstimateDTO;
	}

	private DTOUtils() {
	}

}