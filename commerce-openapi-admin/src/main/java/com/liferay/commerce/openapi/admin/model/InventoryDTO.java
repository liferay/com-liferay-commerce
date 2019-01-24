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

	public String getAllowedOrderQuantities() {
		return _allowedOrderQuantities;
	}

	public long getId() {
		return _id;
	}

	public String getInventoryEngine() {
		return _inventoryEngine;
	}

	public String getLowStockActivity() {
		return _lowStockActivity;
	}

	public int getMaxOrderQuantity() {
		return _maxOrderQuantity;
	}

	public int getMinOrderQuantity() {
		return _minOrderQuantity;
	}

	public int getMinStockQuantity() {
		return _minStockQuantity;
	}

	public int getMultipleOrderQuantity() {
		return _multipleOrderQuantity;
	}

	public boolean isBackOrders() {
		return _backOrders;
	}

	public boolean isDisplayAvailability() {
		return _displayAvailability;
	}

	public boolean isDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	public void setAllowedOrderQuantities(String allowedOrderQuantities) {
		_allowedOrderQuantities = allowedOrderQuantities;
	}

	public void setBackOrders(boolean backOrders) {
		_backOrders = backOrders;
	}

	public void setDisplayAvailability(boolean displayAvailability) {
		_displayAvailability = displayAvailability;
	}

	public void setDisplayStockQuantity(boolean displayStockQuantity) {
		_displayStockQuantity = displayStockQuantity;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setInventoryEngine(String inventoryEngine) {
		_inventoryEngine = inventoryEngine;
	}

	public void setLowStockActivity(String lowStockActivity) {
		_lowStockActivity = lowStockActivity;
	}

	public void setMaxOrderQuantity(int maxOrderQuantity) {
		_maxOrderQuantity = maxOrderQuantity;
	}

	public void setMinOrderQuantity(int minOrderQuantity) {
		_minOrderQuantity = minOrderQuantity;
	}

	public void setMinStockQuantity(int minStockQuantity) {
		_minStockQuantity = minStockQuantity;
	}

	public void setMultipleOrderQuantity(int multipleOrderQuantity) {
		_multipleOrderQuantity = multipleOrderQuantity;
	}

	private String _allowedOrderQuantities;
	private boolean _backOrders;
	private boolean _displayAvailability;
	private boolean _displayStockQuantity;
	private long _id;
	private String _inventoryEngine;
	private String _lowStockActivity;
	private int _maxOrderQuantity;
	private int _minOrderQuantity;
	private int _minStockQuantity;
	private int _multipleOrderQuantity;

}