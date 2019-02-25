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

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "UserSegment")
public class UserSegmentDTO {

	@Nullable
	public UserSegmentCriterionDTO[] getCriteria() {
		return _criteria;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getKey() {
		return _key;
	}

	public Map<String, String> getName() {
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

	@Nullable
	public Boolean isSystem() {
		return _system;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCriteria(UserSegmentCriterionDTO[] criteria) {
		_criteria = criteria;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setName(Map<String, String> name) {
		_name = name;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	public void setSystem(Boolean system) {
		_system = system;
	}

	@Nullable
	private Boolean _active;

	@Nullable
	private UserSegmentCriterionDTO[] _criteria;

	@Nullable
	private Long _id;

	private String _key;
	private Map<String, String> _name;

	@Nullable
	private Double _priority;

	@Nullable
	private Boolean _system;

}