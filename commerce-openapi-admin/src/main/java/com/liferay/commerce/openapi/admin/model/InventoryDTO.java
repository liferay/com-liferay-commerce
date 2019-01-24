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

	public long getId() {
		return _id;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String getSkuExternalReferenceCode() {
		return _skuExternalReferenceCode;
	}

	public long getSkuId() {
		return _skuId;
	}

	public long getWarehouseId() {
		return _warehouseId;
	}

	public String getWarehouseName() {
		return _warehouseName;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		_skuExternalReferenceCode = skuExternalReferenceCode;
	}

	public void setSkuId(long skuId) {
		_skuId = skuId;
	}

	public void setWarehouseId(long warehouseId) {
		_warehouseId = warehouseId;
	}

	public void setWarehouseName(String warehouseName) {
		_warehouseName = warehouseName;
	}

	private long _id;
	private int _quantity;
	private String _skuExternalReferenceCode;
	private long _skuId;
	private long _warehouseId;
	private String _warehouseName;

}