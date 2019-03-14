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

package com.liferay.headless.commerce.admin.catalog.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "ProductSubscriptionConfiguration")
public class ProductSubscriptionConfigurationDTO {

	@Nullable
	public Integer getLength() {
		return _length;
	}

	@Nullable
	public Long getNumberOfLength() {
		return _numberOfLength;
	}

	@Nullable
	public String getSubscriptionType() {
		return _subscriptionType;
	}

	@Nullable
	public Map<String, String> getSubscriptionTypeSettings() {
		return _subscriptionTypeSettings;
	}

	@Nullable
	public Boolean isEnable() {
		return _enable;
	}

	public void setEnable(Boolean enable) {
		_enable = enable;
	}

	public void setLength(Integer length) {
		_length = length;
	}

	public void setNumberOfLength(Long numberOfLength) {
		_numberOfLength = numberOfLength;
	}

	public void setSubscriptionType(String subscriptionType) {
		_subscriptionType = subscriptionType;
	}

	public void setSubscriptionTypeSettings(
		Map<String, String> subscriptionTypeSettings) {

		_subscriptionTypeSettings = subscriptionTypeSettings;
	}

	@Nullable
	private Boolean _enable;

	@Nullable
	private Integer _length;

	@Nullable
	private Long _numberOfLength;

	@Nullable
	private String _subscriptionType;

	@Nullable
	private Map<String, String> _subscriptionTypeSettings;

}