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

package com.liferay.headless.commerce.admin.site.setting.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "UserSegmentCriterion")
public class UserSegmentCriterionDTO {

	public Long getCommerceUserSegmentEntryId() {
		return _commerceUserSegmentEntryId;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Double getPriority() {
		return _priority;
	}

	public String getType() {
		return _type;
	}

	@Nullable
	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setCommerceUserSegmentEntryId(Long commerceUserSegmentEntryId) {
		_commerceUserSegmentEntryId = commerceUserSegmentEntryId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	private Long _commerceUserSegmentEntryId;

	@Nullable
	private Long _id;

	@Nullable
	private Double _priority;

	private String _type;

	@Nullable
	private String _typeSettings;

}