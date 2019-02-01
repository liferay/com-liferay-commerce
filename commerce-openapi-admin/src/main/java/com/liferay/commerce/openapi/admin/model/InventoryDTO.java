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
@XmlRootElement(name = "Inventory")
public class InventoryDTO {

	public Long getId() {
		return _id;
	}

	public Integer getQuantity() {
		return _quantity;
	}

	public String getSkuExternalReferenceCode() {
		return _skuExternalReferenceCode;
	}

	public Long getSkuId() {
		return _skuId;
	}

	public Long getWarehouseId() {
		return _warehouseId;
	}

	public String getWarehouseName() {
		return _warehouseName;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setQuantity(Integer quantity) {
		_quantity = quantity;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		_skuExternalReferenceCode = skuExternalReferenceCode;
	}

	public void setSkuId(Long skuId) {
		_skuId = skuId;
	}

	public void setWarehouseId(Long warehouseId) {
		_warehouseId = warehouseId;
	}

	public void setWarehouseName(String warehouseName) {
		_warehouseName = warehouseName;
	}

	private Long _id;
	private Integer _quantity;
	private String _skuExternalReferenceCode;
	private Long _skuId;
	private Long _warehouseId;
	private String _warehouseName;

}