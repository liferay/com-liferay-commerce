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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.service.base.CPDAvailabilityEstimateServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDAvailabilityEstimateServiceImpl
	extends CPDAvailabilityEstimateServiceBaseImpl {

	@Override
	public CPDAvailabilityEstimate fetchCPDAvailabilityEstimateByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId,
			CPActionKeys.MANAGE_CATALOG);

		return cpdAvailabilityEstimateLocalService.
			fetchCPDAvailabilityEstimateByCPDefinitionId(cpDefinitionId);
	}

	@Override
	public CPDAvailabilityEstimate updateCPDAvailabilityEstimate(
			long cpdAvailabilityEstimateId, long cpDefinitionId,
			long commerceAvailabilityEstimateId, ServiceContext serviceContext)
		throws PortalException {

		_cpDefinitionModelResourcePermission.check(
			getPermissionChecker(), cpDefinitionId,
			CPActionKeys.MANAGE_CATALOG);

		return cpdAvailabilityEstimateLocalService.
			updateCPDAvailabilityEstimate(
				cpdAvailabilityEstimateId, cpDefinitionId,
				commerceAvailabilityEstimateId, serviceContext);
	}

	private static volatile ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPDAvailabilityEstimateServiceImpl.class,
				"_cpDefinitionModelResourcePermission", CPDefinition.class);

}