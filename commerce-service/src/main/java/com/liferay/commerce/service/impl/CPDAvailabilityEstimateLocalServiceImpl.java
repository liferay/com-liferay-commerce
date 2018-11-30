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

import com.liferay.commerce.exception.NoSuchAvailabilityEstimateException;
import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.service.base.CPDAvailabilityEstimateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.spring.extender.service.ServiceReference;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class CPDAvailabilityEstimateLocalServiceImpl
	extends CPDAvailabilityEstimateLocalServiceBaseImpl {

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDAvailabilityEstimate deleteCPDAvailabilityEstimate(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		return cpdAvailabilityEstimatePersistence.remove(
			cpdAvailabilityEstimate);
	}

	@Override
	public CPDAvailabilityEstimate deleteCPDAvailabilityEstimate(
			long cpdAvailabilityEstimateId)
		throws PortalException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			cpdAvailabilityEstimatePersistence.findByPrimaryKey(
				cpdAvailabilityEstimateId);

		return cpdAvailabilityEstimateLocalService.
			deleteCPDAvailabilityEstimate(cpdAvailabilityEstimate);
	}

	/**
	 * @deprecated As of 1.1
	 */
	@Deprecated
	@Override
	public void deleteCPDAvailabilityEstimateByCPDefinitionId(
		long cpDefinitionId) {

		try {
			CPDAvailabilityEstimate cpdAvailabilityEstimate =
				fetchCPDAvailabilityEstimateByCPDefinitionId(cpDefinitionId);

			if (cpdAvailabilityEstimate != null) {
				cpdAvailabilityEstimateLocalService.
					deleteCPDAvailabilityEstimate(cpdAvailabilityEstimate);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.error(pe, pe);
			}
		}
	}

	@Override
	public void deleteCPDAvailabilityEstimates(
			long commerceAvailabilityEstimateId)
		throws PortalException {

		cpdAvailabilityEstimatePersistence.
			removeByCommerceAvailabilityEstimateId(
				commerceAvailabilityEstimateId);
	}

	/**
	 * @deprecated As of 1.1
	 */
	@Deprecated
	@Override
	public CPDAvailabilityEstimate fetchCPDAvailabilityEstimateByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		return cpdAvailabilityEstimatePersistence.fetchByCProductId(
			cpDefinition.getCProductId());
	}

	@Override
	public CPDAvailabilityEstimate updateCPDAvailabilityEstimate(
			long cpdAvailabilityEstimateId, long cpDefinitionId,
			long commerceAvailabilityEstimateId, ServiceContext serviceContext)
		throws PortalException {

		validate(commerceAvailabilityEstimateId);

		if (cpdAvailabilityEstimateId > 0) {
			CPDAvailabilityEstimate cpdAvailabilityEstimate =
				cpdAvailabilityEstimatePersistence.findByPrimaryKey(
					cpdAvailabilityEstimateId);

			cpdAvailabilityEstimate.setCommerceAvailabilityEstimateId(
				commerceAvailabilityEstimateId);

			return cpdAvailabilityEstimatePersistence.update(
				cpdAvailabilityEstimate);
		}

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			fetchCPDAvailabilityEstimateByCPDefinitionId(cpDefinitionId);

		if (cpdAvailabilityEstimate != null) {
			cpdAvailabilityEstimate.setCommerceAvailabilityEstimateId(
				commerceAvailabilityEstimateId);

			return cpdAvailabilityEstimatePersistence.update(
				cpdAvailabilityEstimate);
		}

		return addCPDAvailabilityEstimate(
			cpDefinitionId, commerceAvailabilityEstimateId, serviceContext);
	}

	protected CPDAvailabilityEstimate addCPDAvailabilityEstimate(
			long cpDefinitionId, long commerceAvailabilityEstimateId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		long cpdAvailabilityEstimateId = counterLocalService.increment();

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			cpdAvailabilityEstimatePersistence.create(
				cpdAvailabilityEstimateId);

		cpdAvailabilityEstimate.setUuid(serviceContext.getUuid());
		cpdAvailabilityEstimate.setGroupId(groupId);
		cpdAvailabilityEstimate.setCompanyId(user.getCompanyId());
		cpdAvailabilityEstimate.setUserId(user.getUserId());
		cpdAvailabilityEstimate.setUserName(user.getFullName());
		cpdAvailabilityEstimate.setCProductId(cpDefinition.getCProductId());
		cpdAvailabilityEstimate.setCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId);

		cpdAvailabilityEstimatePersistence.update(cpdAvailabilityEstimate);

		return cpdAvailabilityEstimate;
	}

	protected void validate(long commerceAvailabilityEstimateId)
		throws PortalException {

		if (commerceAvailabilityEstimateId > 0) {
			CommerceAvailabilityEstimate commerceAvailabilityEstimate =
				commerceAvailabilityEstimateLocalService.
					fetchCommerceAvailabilityEstimate(
						commerceAvailabilityEstimateId);

			if (commerceAvailabilityEstimate == null) {
				throw new NoSuchAvailabilityEstimateException();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDAvailabilityEstimateLocalServiceImpl.class);

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

}