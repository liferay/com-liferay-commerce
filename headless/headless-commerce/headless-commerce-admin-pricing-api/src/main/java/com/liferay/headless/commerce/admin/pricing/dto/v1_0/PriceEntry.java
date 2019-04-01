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

package com.liferay.headless.commerce.admin.pricing.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import java.math.BigDecimal;

import java.util.Objects;

import javax.annotation.Generated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("PriceEntry")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PriceEntry")
public class PriceEntry {

	public Long getCommercePriceListId() {
		return commercePriceListId;
	}

	public void setCommercePriceListId(Long commercePriceListId) {
		this.commercePriceListId = commercePriceListId;
	}

	@JsonIgnore
	public void setCommercePriceListId(
		UnsafeSupplier<Long, Exception> commercePriceListIdUnsafeSupplier) {

		try {
			commercePriceListId = commercePriceListIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long commercePriceListId;

	public Long getCommerceProductInstanceId() {
		return commerceProductInstanceId;
	}

	public void setCommerceProductInstanceId(Long commerceProductInstanceId) {
		this.commerceProductInstanceId = commerceProductInstanceId;
	}

	@JsonIgnore
	public void setCommerceProductInstanceId(
		UnsafeSupplier<Long, Exception>
			commerceProductInstanceIdUnsafeSupplier) {

		try {
			commerceProductInstanceId =
				commerceProductInstanceIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long commerceProductInstanceId;

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

	public Boolean getHasTierPrice() {
		return hasTierPrice;
	}

	public void setHasTierPrice(Boolean hasTierPrice) {
		this.hasTierPrice = hasTierPrice;
	}

	@JsonIgnore
	public void setHasTierPrice(
		UnsafeSupplier<Boolean, Exception> hasTierPriceUnsafeSupplier) {

		try {
			hasTierPrice = hasTierPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean hasTierPrice;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

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
	@NotNull
	protected BigDecimal price;

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
	@NotNull
	protected BigDecimal promoPrice;

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
	protected String sku;

	public String getSkuExternalReferenceCode() {
		return skuExternalReferenceCode;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		this.skuExternalReferenceCode = skuExternalReferenceCode;
	}

	@JsonIgnore
	public void setSkuExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			skuExternalReferenceCodeUnsafeSupplier) {

		try {
			skuExternalReferenceCode =
				skuExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String skuExternalReferenceCode;

	public Boolean getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(Boolean standardPrice) {
		this.standardPrice = standardPrice;
	}

	@JsonIgnore
	public void setStandardPrice(
		UnsafeSupplier<Boolean, Exception> standardPriceUnsafeSupplier) {

		try {
			standardPrice = standardPriceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Boolean standardPrice;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PriceEntry)) {
			return false;
		}

		PriceEntry priceEntry = (PriceEntry)object;

		return Objects.equals(toString(), priceEntry.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"commercePriceListId\": ");

		sb.append(commercePriceListId);
		sb.append(", ");

		sb.append("\"commerceProductInstanceId\": ");

		sb.append(commerceProductInstanceId);
		sb.append(", ");

		sb.append("\"externalReferenceCode\": ");

		sb.append("\"");
		sb.append(externalReferenceCode);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"hasTierPrice\": ");

		sb.append(hasTierPrice);
		sb.append(", ");

		sb.append("\"id\": ");

		sb.append(id);
		sb.append(", ");

		sb.append("\"price\": ");

		sb.append(price);
		sb.append(", ");

		sb.append("\"promoPrice\": ");

		sb.append(promoPrice);
		sb.append(", ");

		sb.append("\"sku\": ");

		sb.append("\"");
		sb.append(sku);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"skuExternalReferenceCode\": ");

		sb.append("\"");
		sb.append(skuExternalReferenceCode);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"standardPrice\": ");

		sb.append(standardPrice);

		sb.append("}");

		return sb.toString();
	}

}