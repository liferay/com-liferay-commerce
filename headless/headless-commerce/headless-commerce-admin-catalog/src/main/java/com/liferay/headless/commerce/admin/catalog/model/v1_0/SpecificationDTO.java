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
@JacksonXmlRootElement(localName = "Specification")
public class SpecificationDTO {

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
	public OptionCategoryDTO getOptionCategoryDTO() {
		return _optionCategoryDTO;
	}

	public Map<String, String> getTitle() {
		return _title;
	}

	@Nullable
	public SpecificationValueDTO[] getValues() {
		return _values;
	}

	@Nullable
	public Boolean isFacetable() {
		return _facetable;
	}

	public void setDescription(Map<String, String> description) {
		_description = description;
	}

	public void setFacetable(Boolean facetable) {
		_facetable = facetable;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setOptionCategoryDTO(OptionCategoryDTO optionCategoryDTO) {
		_optionCategoryDTO = optionCategoryDTO;
	}

	public void setTitle(Map<String, String> title) {
		_title = title;
	}

	public void setValues(SpecificationValueDTO[] values) {
		_values = values;
	}

	@Nullable
	private Map<String, String> _description;

	@Nullable
	private Boolean _facetable;

	@Nullable
	private Long _id;

	private String _key;

	@Nullable
	private OptionCategoryDTO _optionCategoryDTO;

	private Map<String, String> _title;

	@Nullable
	private SpecificationValueDTO[] _values;

}