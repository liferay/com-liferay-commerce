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

package com.liferay.commerce.openapi.admin.model;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "ProductOption")
public class ProductOptionDTO {

	public String getDescription() {
		return _description;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getFieldType() {
		return _fieldType;
	}

	public long getId() {
		return _id;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public boolean isFacetable() {
		return _facetable;
	}

	public boolean isRequired() {
		return _required;
	}

	public boolean isSkuContributor() {
		return _skuContributor;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setFacetable(boolean facetable) {
		_facetable = facetable;
	}

	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public void setSkuContributor(boolean skuContributor) {
		_skuContributor = skuContributor;
	}

	private String _description;
	private String _externalReferenceCode;
	private boolean _facetable;
	private String _fieldType;
	private long _id;
	private String _key;
	private String _name;
	private boolean _required;
	private boolean _skuContributor;

}