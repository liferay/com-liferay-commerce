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

import java.math.BigDecimal;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("ProductShippingConfiguration")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ProductShippingConfiguration")
public class ProductShippingConfiguration {

	public BigDecimal getDepth() {
		return depth;
	}

	public void setDepth(BigDecimal depth) {
		this.depth = depth;
	}

	@JsonIgnore
	public void setDepth(
		UnsafeSupplier<BigDecimal, Exception> depthUnsafeSupplier) {

		try {
			depth = depthUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal depth;

	public Boolean getFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(Boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	@JsonIgnore
	public void setFreeShipping(
		UnsafeSupplier<Boolean, Exception> freeShippingUnsafeSupplier) {

		try {
			freeShipping = freeShippingUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean freeShipping;

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	@JsonIgnore
	public void setHeight(
		UnsafeSupplier<BigDecimal, Exception> heightUnsafeSupplier) {

		try {
			height = heightUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal height;

	public Boolean getShippable() {
		return shippable;
	}

	public void setShippable(Boolean shippable) {
		this.shippable = shippable;
	}

	@JsonIgnore
	public void setShippable(
		UnsafeSupplier<Boolean, Exception> shippableUnsafeSupplier) {

		try {
			shippable = shippableUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean shippable;

	public BigDecimal getShippingExtraPrice() {
		return shippingExtraPrice;
	}

	public void setShippingExtraPrice(BigDecimal shippingExtraPrice) {
		this.shippingExtraPrice = shippingExtraPrice;
	}

	@JsonIgnore
	public void setShippingExtraPrice(
		UnsafeSupplier<BigDecimal, Exception>
			shippingExtraPriceUnsafeSupplier) {

		try {
			shippingExtraPrice = shippingExtraPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal shippingExtraPrice;

	public Boolean getShippingSeparately() {
		return shippingSeparately;
	}

	public void setShippingSeparately(Boolean shippingSeparately) {
		this.shippingSeparately = shippingSeparately;
	}

	@JsonIgnore
	public void setShippingSeparately(
		UnsafeSupplier<Boolean, Exception> shippingSeparatelyUnsafeSupplier) {

		try {
			shippingSeparately = shippingSeparatelyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean shippingSeparately;

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@JsonIgnore
	public void setWeight(
		UnsafeSupplier<BigDecimal, Exception> weightUnsafeSupplier) {

		try {
			weight = weightUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal weight;

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	@JsonIgnore
	public void setWidth(
		UnsafeSupplier<BigDecimal, Exception> widthUnsafeSupplier) {

		try {
			width = widthUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal width;

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"depth\": ");

		sb.append(depth);
		sb.append(", ");

		sb.append("\"freeShipping\": ");

		sb.append(freeShipping);
		sb.append(", ");

		sb.append("\"height\": ");

		sb.append(height);
		sb.append(", ");

		sb.append("\"shippable\": ");

		sb.append(shippable);
		sb.append(", ");

		sb.append("\"shippingExtraPrice\": ");

		sb.append(shippingExtraPrice);
		sb.append(", ");

		sb.append("\"shippingSeparately\": ");

		sb.append(shippingSeparately);
		sb.append(", ");

		sb.append("\"weight\": ");

		sb.append(weight);
		sb.append(", ");

		sb.append("\"width\": ");

		sb.append(width);

		sb.append("}");

		return sb.toString();
	}

}