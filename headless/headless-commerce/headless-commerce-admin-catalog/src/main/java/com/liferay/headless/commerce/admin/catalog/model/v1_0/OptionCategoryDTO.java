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
@JacksonXmlRootElement(localName = "OptionCategory")
public class OptionCategoryDTO {

	@Nullable
	public Map<String, String> getDescription() {
		return _description;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getKey() {
		return _key;
	}

	@Nullable
	public Double getPriority() {
		return _priority;
	}

	public Map<String, String> getTitle() {
		return _title;
	}

	public void setDescription(Map<String, String> description) {
		_description = description;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	public void setTitle(Map<String, String> title) {
		_title = title;
	}

	@Nullable
	private Map<String, String> _description;

	@Nullable
	private Long _id;

	private String _key;

	@Nullable
	private Double _priority;

	private Map<String, String> _title;

}