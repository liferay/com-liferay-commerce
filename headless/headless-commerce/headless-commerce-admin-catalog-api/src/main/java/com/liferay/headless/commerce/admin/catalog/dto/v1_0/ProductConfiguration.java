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

package com.liferay.headless.commerce.admin.catalog.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("ProductConfiguration")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ProductConfiguration")
public class ProductConfiguration {

	public Boolean getAllowBackOrder() {
		return allowBackOrder;
	}

	public void setAllowBackOrder(Boolean allowBackOrder) {
		this.allowBackOrder = allowBackOrder;
	}

	@JsonIgnore
	public void setAllowBackOrder(
		UnsafeSupplier<Boolean, Exception> allowBackOrderUnsafeSupplier) {

		try {
			allowBackOrder = allowBackOrderUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean allowBackOrder;

	public Integer[] getAllowedOrderQuantities() {
		return allowedOrderQuantities;
	}

	public void setAllowedOrderQuantities(Integer[] allowedOrderQuantities) {
		this.allowedOrderQuantities = allowedOrderQuantities;
	}

	@JsonIgnore
	public void setAllowedOrderQuantities(
		UnsafeSupplier<Integer[], Exception>
			allowedOrderQuantitiesUnsafeSupplier) {

		try {
			allowedOrderQuantities = allowedOrderQuantitiesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer[] allowedOrderQuantities;

	public Boolean getDisplayAvailability() {
		return displayAvailability;
	}

	public void setDisplayAvailability(Boolean displayAvailability) {
		this.displayAvailability = displayAvailability;
	}

	@JsonIgnore
	public void setDisplayAvailability(
		UnsafeSupplier<Boolean, Exception> displayAvailabilityUnsafeSupplier) {

		try {
			displayAvailability = displayAvailabilityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean displayAvailability;

	public Boolean getDisplayStockQuantity() {
		return displayStockQuantity;
	}

	public void setDisplayStockQuantity(Boolean displayStockQuantity) {
		this.displayStockQuantity = displayStockQuantity;
	}

	@JsonIgnore
	public void setDisplayStockQuantity(
		UnsafeSupplier<Boolean, Exception> displayStockQuantityUnsafeSupplier) {

		try {
			displayStockQuantity = displayStockQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean displayStockQuantity;

	@Schema(
		description = "The inventory engine that will be used to manage the product inventory"
	)
	public String getInventoryEngine() {
		return inventoryEngine;
	}

	public void setInventoryEngine(String inventoryEngine) {
		this.inventoryEngine = inventoryEngine;
	}

	@JsonIgnore
	public void setInventoryEngine(
		UnsafeSupplier<String, Exception> inventoryEngineUnsafeSupplier) {

		try {
			inventoryEngine = inventoryEngineUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String inventoryEngine;

	@Schema(
		description = "The low stock action that will be performed when a product is out of stock"
	)
	public String getLowStockAction() {
		return lowStockAction;
	}

	public void setLowStockAction(String lowStockAction) {
		this.lowStockAction = lowStockAction;
	}

	@JsonIgnore
	public void setLowStockAction(
		UnsafeSupplier<String, Exception> lowStockActionUnsafeSupplier) {

		try {
			lowStockAction = lowStockActionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String lowStockAction;

	public Integer getMaxOrderQuantity() {
		return maxOrderQuantity;
	}

	public void setMaxOrderQuantity(Integer maxOrderQuantity) {
		this.maxOrderQuantity = maxOrderQuantity;
	}

	@JsonIgnore
	public void setMaxOrderQuantity(
		UnsafeSupplier<Integer, Exception> maxOrderQuantityUnsafeSupplier) {

		try {
			maxOrderQuantity = maxOrderQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer maxOrderQuantity;

	public Integer getMinOrderQuantity() {
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(Integer minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}

	@JsonIgnore
	public void setMinOrderQuantity(
		UnsafeSupplier<Integer, Exception> minOrderQuantityUnsafeSupplier) {

		try {
			minOrderQuantity = minOrderQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer minOrderQuantity;

	public Integer getMinStockQuantity() {
		return minStockQuantity;
	}

	public void setMinStockQuantity(Integer minStockQuantity) {
		this.minStockQuantity = minStockQuantity;
	}

	@JsonIgnore
	public void setMinStockQuantity(
		UnsafeSupplier<Integer, Exception> minStockQuantityUnsafeSupplier) {

		try {
			minStockQuantity = minStockQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer minStockQuantity;

	public Integer getMultipleOrderQuantity() {
		return multipleOrderQuantity;
	}

	public void setMultipleOrderQuantity(Integer multipleOrderQuantity) {
		this.multipleOrderQuantity = multipleOrderQuantity;
	}

	@JsonIgnore
	public void setMultipleOrderQuantity(
		UnsafeSupplier<Integer, Exception>
			multipleOrderQuantityUnsafeSupplier) {

		try {
			multipleOrderQuantity = multipleOrderQuantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer multipleOrderQuantity;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductConfiguration)) {
			return false;
		}

		ProductConfiguration productConfiguration =
			(ProductConfiguration)object;

		return Objects.equals(toString(), productConfiguration.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"allowBackOrder\": ");

		sb.append(allowBackOrder);
		sb.append(", ");

		sb.append("\"allowedOrderQuantities\": ");

		if (allowedOrderQuantities == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0; i < allowedOrderQuantities.length; i++) {
				sb.append(allowedOrderQuantities[i]);

				if ((i + 1) < allowedOrderQuantities.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append(", ");

		sb.append("\"displayAvailability\": ");

		sb.append(displayAvailability);
		sb.append(", ");

		sb.append("\"displayStockQuantity\": ");

		sb.append(displayStockQuantity);
		sb.append(", ");

		sb.append("\"inventoryEngine\": ");

		sb.append("\"");
		sb.append(inventoryEngine);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"lowStockAction\": ");

		sb.append("\"");
		sb.append(lowStockAction);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"maxOrderQuantity\": ");

		sb.append(maxOrderQuantity);
		sb.append(", ");

		sb.append("\"minOrderQuantity\": ");

		sb.append(minOrderQuantity);
		sb.append(", ");

		sb.append("\"minStockQuantity\": ");

		sb.append(minStockQuantity);
		sb.append(", ");

		sb.append("\"multipleOrderQuantity\": ");

		sb.append(multipleOrderQuantity);

		sb.append("}");

		return sb.toString();
	}

}