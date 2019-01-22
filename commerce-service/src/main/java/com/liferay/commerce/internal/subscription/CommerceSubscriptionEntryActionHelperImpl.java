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

package com.liferay.commerce.internal.subscription;

import com.liferay.commerce.configuration.CommerceSubscriptionConfiguration;
import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryActionHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true, service = CommerceSubscriptionEntryActionHelper.class
)
public class CommerceSubscriptionEntryActionHelperImpl
	implements CommerceSubscriptionEntryActionHelper {

	@Override
	public void activateCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws PortalException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.getCommerceSubscriptionEntry(
				commerceSubscriptionEntryId);

		if (CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_SUSPENDED !=
				commerceSubscriptionEntry.getSubscriptionStatus()) {

			return;
		}

		_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
			commerceSubscriptionEntryId,
			CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE);
	}

	@Override
	public void cancelCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws PortalException {

		CommerceSubscriptionConfiguration commerceSubscriptionConfiguration =
			_configurationProvider.getSystemConfiguration(
				CommerceSubscriptionConfiguration.class);

		if (commerceSubscriptionConfiguration.
				subscriptionCancellationAllowed()) {

			_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
				commerceSubscriptionEntryId,
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_CANCELLED);
		}
	}

	@Override
	public void suspendCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws PortalException {

		CommerceSubscriptionConfiguration commerceSubscriptionConfiguration =
			_configurationProvider.getSystemConfiguration(
				CommerceSubscriptionConfiguration.class);

		if (commerceSubscriptionConfiguration.subscriptionSuspensionAllowed()) {
			_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
				commerceSubscriptionEntryId,
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_SUSPENDED);
		}
	}

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

}