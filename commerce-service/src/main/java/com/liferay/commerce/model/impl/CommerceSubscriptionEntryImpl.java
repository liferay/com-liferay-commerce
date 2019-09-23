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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionEntryImpl
	extends CommerceSubscriptionEntryBaseImpl {

	public CommerceSubscriptionEntryImpl() {
	}

	@Override
	public CommerceOrderItem fetchCommerceOrderItem() throws PortalException {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItem(
			getCommerceOrderItemId());
	}

	@Override
	public CPDefinition fetchCPDefinition() throws PortalException {
		CPInstance cpInstance = fetchCPInstance();

		if (cpInstance == null) {
			return null;
		}

		return cpInstance.getCPDefinition();
	}

	@Override
	public CPInstance fetchCPInstance() {
		return CPInstanceLocalServiceUtil.fetchCProductInstance(
			getCProductId(), getCPInstanceUuid());
	}

	@Override
	public long getCPDefinitionId() {
		CPInstance cpInstance = fetchCPInstance();

		if (cpInstance == null) {
			return 0;
		}

		return cpInstance.getCPDefinitionId();
	}

	@Override
	public long getCPInstanceId() {
		CPInstance cpInstance = fetchCPInstance();

		if (cpInstance == null) {
			return 0;
		}

		return cpInstance.getCPInstanceId();
	}

	@Override
	public UnicodeProperties getSubscriptionTypeSettingsProperties() {
		if (_subscriptionTypeSettingsProperties == null) {
			_subscriptionTypeSettingsProperties = new UnicodeProperties(true);

			_subscriptionTypeSettingsProperties.fastLoad(
				getSubscriptionTypeSettings());
		}

		return _subscriptionTypeSettingsProperties;
	}

	@Override
	public void setSubscriptionTypeSettings(String subscriptionTypeSettings) {
		super.setSubscriptionTypeSettings(subscriptionTypeSettings);

		_subscriptionTypeSettingsProperties = null;
	}

	@Override
	public void setSubscriptionTypeSettingsProperties(
		UnicodeProperties subscriptionTypeSettingsProperties) {

		_subscriptionTypeSettingsProperties =
			subscriptionTypeSettingsProperties;

		if (_subscriptionTypeSettingsProperties == null) {
			_subscriptionTypeSettingsProperties = new UnicodeProperties();
		}

		super.setSubscriptionTypeSettings(
			_subscriptionTypeSettingsProperties.toString());
	}

	private UnicodeProperties _subscriptionTypeSettingsProperties;

}