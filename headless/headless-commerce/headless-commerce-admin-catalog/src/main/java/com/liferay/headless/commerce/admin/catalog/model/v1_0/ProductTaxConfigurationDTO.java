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

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "ProductTaxConfiguration")
public class ProductTaxConfigurationDTO {

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public String getTaxCategory() {
		return _taxCategory;
	}

	@Nullable
	public Boolean isTaxable() {
		return _taxable;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setTaxable(Boolean taxable) {
		_taxable = taxable;
	}

	public void setTaxCategory(String taxCategory) {
		_taxCategory = taxCategory;
	}

	@Nullable
	private Long _id;

	@Nullable
	private Boolean _taxable;

	@Nullable
	private String _taxCategory;

}