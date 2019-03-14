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
@JacksonXmlRootElement(localName = "SpecificationValue")
public class SpecificationValueDTO {

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public OptionCategoryDTO getOptionCategoryDTO() {
		return _optionCategoryDTO;
	}

	@Nullable
	public Double getPriority() {
		return _priority;
	}

	@Nullable
	public ProductDTO getProductDTO() {
		return _productDTO;
	}

	@Nullable
	public SpecificationDTO getSpecificationDTO() {
		return _specificationDTO;
	}

	public Map<String, String> getValue() {
		return _value;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setOptionCategoryDTO(OptionCategoryDTO optionCategoryDTO) {
		_optionCategoryDTO = optionCategoryDTO;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	public void setProductDTO(ProductDTO productDTO) {
		_productDTO = productDTO;
	}

	public void setSpecificationDTO(SpecificationDTO specificationDTO) {
		_specificationDTO = specificationDTO;
	}

	public void setValue(Map<String, String> value) {
		_value = value;
	}

	@Nullable
	private Long _id;

	@Nullable
	private OptionCategoryDTO _optionCategoryDTO;

	@Nullable
	private Double _priority;

	@Nullable
	private ProductDTO _productDTO;

	@Nullable
	private SpecificationDTO _specificationDTO;

	private Map<String, String> _value;

}