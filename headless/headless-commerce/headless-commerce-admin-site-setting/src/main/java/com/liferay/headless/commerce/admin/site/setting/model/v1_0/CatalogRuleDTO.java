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
@JacksonXmlRootElement(localName = "CatalogRule")
public class CatalogRuleDTO {

	@Nullable
	public CategoryDTO[] getCategories() {
		return _categories;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getType() {
		return _type;
	}

	@Nullable
	public Map<String, String> getTypeSettings() {
		return _typeSettings;
	}

	@Nullable
	public UserSegmentDTO[] getUserSegments() {
		return _userSegments;
	}

	@Nullable
	public Boolean isActive() {
		return _active;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCategories(CategoryDTO[] categories) {
		_categories = categories;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setTypeSettings(Map<String, String> typeSettings) {
		_typeSettings = typeSettings;
	}

	public void setUserSegments(UserSegmentDTO[] userSegments) {
		_userSegments = userSegments;
	}

	@Nullable
	private Boolean _active;

	@Nullable
	private CategoryDTO[] _categories;

	@Nullable
	private Long _id;

	private String _name;
	private String _type;

	@Nullable
	private Map<String, String> _typeSettings;

	@Nullable
	private UserSegmentDTO[] _userSegments;

}