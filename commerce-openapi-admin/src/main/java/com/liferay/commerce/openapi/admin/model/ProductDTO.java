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

import java.util.Map;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "Product")
public class ProductDTO {

	public Integer[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	public String getDefaultSku() {
		return _defaultSku;
	}

	public Map<String, String> getDescription() {
		return _description;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Long getId() {
		return _id;
	}

	public Map<String, String> getName() {
		return _name;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public Map<String, String> getShortDescription() {
		return _shortDescription;
	}

	public Boolean isActive() {
		return _active;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setAssetCategoryIds(Integer[] assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	public void setDefaultSku(String defaultSku) {
		_defaultSku = defaultSku;
	}

	public void setDescription(Map<String, String> description) {
		_description = description;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(Map<String, String> name) {
		_name = name;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public void setShortDescription(Map<String, String> shortDescription) {
		_shortDescription = shortDescription;
	}

	private Boolean _active;
	private Integer[] _assetCategoryIds;
	private String _defaultSku;
	private Map<String, String> _description;
	private String _externalReferenceCode;
	private Long _id;
	private Map<String, String> _name;
	private String _productTypeName;
	private Map<String, String> _shortDescription;

}