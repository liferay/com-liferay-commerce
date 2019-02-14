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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Region")
public class RegionDTO {

	@Nullable
	public String getCode() {
		return _code;
	}

	public Long getCommerceCountryId() {
		return _commerceCountryId;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@Nullable
	public Double getPriority() {
		return _priority;
	}

	@Nullable
	public Boolean isActive() {
		return _active;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCode(String code) {
		_code = code;
	}

	public void setCommerceCountryId(Long commerceCountryId) {
		_commerceCountryId = commerceCountryId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	@Nullable
	private Boolean _active;

	@Nullable
	private String _code;

	private Long _commerceCountryId;

	@Nullable
	private Long _id;

	private String _name;

	@Nullable
	private Double _priority;

}