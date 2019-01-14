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
@XmlRootElement(name = "Product")
public class ProductDTO {

	public boolean getActive() {
		return _active;
	}

	public int[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	public String getDefaultSku() {
		return _defaultSku;
	}

	public String getDescription() {
		return _description;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public String getShortDescription() {
		return _shortDescription;
	}

	public String getTitle() {
		return _title;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setAssetCategoryIds(int[] assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	public void setDefaultSku(String defaultSku) {
		_defaultSku = defaultSku;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private boolean _active;
	private int[] _assetCategoryIds;
	private String _defaultSku;
	private String _description;
	private String _externalReferenceCode;
	private String _productTypeName;
	private String _shortDescription;
	private String _title;

}