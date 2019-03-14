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
@JacksonXmlRootElement(localName = "ProductConfiguration")
public class ProductConfigurationDTO {

	@Nullable
	public Integer[] getAllowedOrderQuantities() {
		return _allowedOrderQuantities;
	}

	@Nullable
	public String getInventoryEngine() {
		return _inventoryEngine;
	}

	@Nullable
	public String getLowStockAction() {
		return _lowStockAction;
	}

	@Nullable
	public Integer getMaxOrderQuantity() {
		return _maxOrderQuantity;
	}

	@Nullable
	public Integer getMinOrderQuantity() {
		return _minOrderQuantity;
	}

	@Nullable
	public Integer getMinStockQuantity() {
		return _minStockQuantity;
	}

	@Nullable
	public Integer getMultipleOrderQuantity() {
		return _multipleOrderQuantity;
	}

	@Nullable
	public Boolean isAllowBackOrder() {
		return _allowBackOrder;
	}

	@Nullable
	public Boolean isDisplayAvailability() {
		return _displayAvailability;
	}

	@Nullable
	public Boolean isDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	public void setAllowBackOrder(Boolean allowBackOrder) {
		_allowBackOrder = allowBackOrder;
	}

	public void setAllowedOrderQuantities(Integer[] allowedOrderQuantities) {
		_allowedOrderQuantities = allowedOrderQuantities;
	}

	public void setDisplayAvailability(Boolean displayAvailability) {
		_displayAvailability = displayAvailability;
	}

	public void setDisplayStockQuantity(Boolean displayStockQuantity) {
		_displayStockQuantity = displayStockQuantity;
	}

	public void setInventoryEngine(String inventoryEngine) {
		_inventoryEngine = inventoryEngine;
	}

	public void setLowStockAction(String lowStockAction) {
		_lowStockAction = lowStockAction;
	}

	public void setMaxOrderQuantity(Integer maxOrderQuantity) {
		_maxOrderQuantity = maxOrderQuantity;
	}

	public void setMinOrderQuantity(Integer minOrderQuantity) {
		_minOrderQuantity = minOrderQuantity;
	}

	public void setMinStockQuantity(Integer minStockQuantity) {
		_minStockQuantity = minStockQuantity;
	}

	public void setMultipleOrderQuantity(Integer multipleOrderQuantity) {
		_multipleOrderQuantity = multipleOrderQuantity;
	}

	@Nullable
	private Boolean _allowBackOrder;

	@Nullable
	private Integer[] _allowedOrderQuantities;

	@Nullable
	private Boolean _displayAvailability;

	@Nullable
	private Boolean _displayStockQuantity;

	@Nullable
	private String _inventoryEngine;

	@Nullable
	private String _lowStockAction;

	@Nullable
	private Integer _maxOrderQuantity;

	@Nullable
	private Integer _minOrderQuantity;

	@Nullable
	private Integer _minStockQuantity;

	@Nullable
	private Integer _multipleOrderQuantity;

}