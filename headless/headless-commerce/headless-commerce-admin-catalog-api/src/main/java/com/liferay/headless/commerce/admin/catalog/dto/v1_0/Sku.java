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

import java.util.Date;
import java.util.Map;

import javax.annotation.Generated;

import javax.validation.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("Sku")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Sku")
public class Sku {

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@JsonIgnore
	public void setCost(
		UnsafeSupplier<BigDecimal, Exception> costUnsafeSupplier) {

		try {
			cost = costUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal cost;

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	@JsonIgnore
	public void setDepth(
		UnsafeSupplier<Double, Exception> depthUnsafeSupplier) {

		try {
			depth = depthUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double depth;

	public Date getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(Date displayDate) {
		this.displayDate = displayDate;
	}

	@JsonIgnore
	public void setDisplayDate(
		UnsafeSupplier<Date, Exception> displayDateUnsafeSupplier) {

		try {
			displayDate = displayDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date displayDate;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonIgnore
	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date expirationDate;

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonIgnore
	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String externalReferenceCode;

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	@JsonIgnore
	public void setGtin(UnsafeSupplier<String, Exception> gtinUnsafeSupplier) {
		try {
			gtin = gtinUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String gtin;

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@JsonIgnore
	public void setHeight(
		UnsafeSupplier<Double, Exception> heightUnsafeSupplier) {

		try {
			height = heightUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double height;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long id;

	public Integer getInventoryLevel() {
		return inventoryLevel;
	}

	public void setInventoryLevel(Integer inventoryLevel) {
		this.inventoryLevel = inventoryLevel;
	}

	@JsonIgnore
	public void setInventoryLevel(
		UnsafeSupplier<Integer, Exception> inventoryLevelUnsafeSupplier) {

		try {
			inventoryLevel = inventoryLevelUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer inventoryLevel;

	public String getManufacturerPartNumber() {
		return manufacturerPartNumber;
	}

	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		this.manufacturerPartNumber = manufacturerPartNumber;
	}

	@JsonIgnore
	public void setManufacturerPartNumber(
		UnsafeSupplier<String, Exception>
			manufacturerPartNumberUnsafeSupplier) {

		try {
			manufacturerPartNumber = manufacturerPartNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String manufacturerPartNumber;

	public Boolean getNeverExpire() {
		return neverExpire;
	}

	public void setNeverExpire(Boolean neverExpire) {
		this.neverExpire = neverExpire;
	}

	@JsonIgnore
	public void setNeverExpire(
		UnsafeSupplier<Boolean, Exception> neverExpireUnsafeSupplier) {

		try {
			neverExpire = neverExpireUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean neverExpire;

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	@JsonIgnore
	public void setOptions(
		UnsafeSupplier<Map<String, String>, Exception> optionsUnsafeSupplier) {

		try {
			options = optionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, String> options;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JsonIgnore
	public void setPrice(
		UnsafeSupplier<BigDecimal, Exception> priceUnsafeSupplier) {

		try {
			price = priceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal price;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@JsonIgnore
	public void setProductId(
		UnsafeSupplier<Long, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long productId;

	public BigDecimal getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		this.promoPrice = promoPrice;
	}

	@JsonIgnore
	public void setPromoPrice(
		UnsafeSupplier<BigDecimal, Exception> promoPriceUnsafeSupplier) {

		try {
			promoPrice = promoPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal promoPrice;

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	@JsonIgnore
	public void setPublished(
		UnsafeSupplier<Boolean, Exception> publishedUnsafeSupplier) {

		try {
			published = publishedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean published;

	public Boolean getPurchasable() {
		return purchasable;
	}

	public void setPurchasable(Boolean purchasable) {
		this.purchasable = purchasable;
	}

	@JsonIgnore
	public void setPurchasable(
		UnsafeSupplier<Boolean, Exception> purchasableUnsafeSupplier) {

		try {
			purchasable = purchasableUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean purchasable;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@JsonIgnore
	public void setSku(UnsafeSupplier<String, Exception> skuUnsafeSupplier) {
		try {
			sku = skuUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String sku;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@JsonIgnore
	public void setWeight(
		UnsafeSupplier<Double, Exception> weightUnsafeSupplier) {

		try {
			weight = weightUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double weight;

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	@JsonIgnore
	public void setWidth(
		UnsafeSupplier<Double, Exception> widthUnsafeSupplier) {

		try {
			width = widthUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double width;

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"cost\": ");

		sb.append(cost);
		sb.append(", ");

		sb.append("\"depth\": ");

		sb.append(depth);
		sb.append(", ");

		sb.append("\"displayDate\": ");

		sb.append("\"");
		sb.append(displayDate);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"expirationDate\": ");

		sb.append("\"");
		sb.append(expirationDate);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"externalReferenceCode\": ");

		sb.append("\"");
		sb.append(externalReferenceCode);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"gtin\": ");

		sb.append("\"");
		sb.append(gtin);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"height\": ");

		sb.append(height);
		sb.append(", ");

		sb.append("\"id\": ");

		sb.append(id);
		sb.append(", ");

		sb.append("\"inventoryLevel\": ");

		sb.append(inventoryLevel);
		sb.append(", ");

		sb.append("\"manufacturerPartNumber\": ");

		sb.append("\"");
		sb.append(manufacturerPartNumber);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"neverExpire\": ");

		sb.append(neverExpire);
		sb.append(", ");

		sb.append("\"options\": ");

		sb.append(options);
		sb.append(", ");

		sb.append("\"price\": ");

		sb.append(price);
		sb.append(", ");

		sb.append("\"productId\": ");

		sb.append(productId);
		sb.append(", ");

		sb.append("\"promoPrice\": ");

		sb.append(promoPrice);
		sb.append(", ");

		sb.append("\"published\": ");

		sb.append(published);
		sb.append(", ");

		sb.append("\"purchasable\": ");

		sb.append(purchasable);
		sb.append(", ");

		sb.append("\"sku\": ");

		sb.append("\"");
		sb.append(sku);
		sb.append("\"");
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