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

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("PriceEntry")
@JsonFilter("Liferay.Vulcan")
@Schema(requiredProperties = {"price"})
@XmlRootElement(name = "PriceEntry")
public class PriceEntry {

	@Schema
	public Map<String, ?> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, ?> customFields) {
		this.customFields = customFields;
	}

	@JsonIgnore
	public void setCustomFields(
		UnsafeSupplier<Map<String, ?>, Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, ?> customFields;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String externalReferenceCode;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean hasTierPrice;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected BigDecimal price;

	@Schema
	public String getPriceListExternalReferenceCode() {
		return priceListExternalReferenceCode;
	}

	public void setPriceListExternalReferenceCode(
		String priceListExternalReferenceCode) {

		this.priceListExternalReferenceCode = priceListExternalReferenceCode;
	}

	@JsonIgnore
	public void setPriceListExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			priceListExternalReferenceCodeUnsafeSupplier) {

		try {
			priceListExternalReferenceCode =
				priceListExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String priceListExternalReferenceCode;

	@Schema
	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}

	@JsonIgnore
	public void setPriceListId(
		UnsafeSupplier<Long, Exception> priceListIdUnsafeSupplier) {

		try {
			priceListId = priceListIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long priceListId;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected BigDecimal promoPrice;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String sku;

	@Schema
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
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String skuExternalReferenceCode;

	@Schema
	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@JsonIgnore
	public void setSkuId(UnsafeSupplier<Long, Exception> skuIdUnsafeSupplier) {
		try {
			skuId = skuIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long skuId;

	@Schema
	public TierPrice[] getTierPrices() {
		return tierPrices;
	}

	public void setTierPrices(TierPrice[] tierPrices) {
		this.tierPrices = tierPrices;
	}

	@JsonIgnore
	public void setTierPrices(
		UnsafeSupplier<TierPrice[], Exception> tierPricesUnsafeSupplier) {

		try {
			tierPrices = tierPricesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected TierPrice[] tierPrices;

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

		if (customFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append(_toJSON(customFields));
		}

		if (externalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(externalReferenceCode));

			sb.append("\"");
		}

		if (hasTierPrice != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hasTierPrice\": ");

			sb.append(hasTierPrice);
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (price != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"price\": ");

			sb.append(price);
		}

		if (priceListExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceListExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(priceListExternalReferenceCode));

			sb.append("\"");
		}

		if (priceListId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceListId\": ");

			sb.append(priceListId);
		}

		if (promoPrice != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"promoPrice\": ");

			sb.append(promoPrice);
		}

		if (sku != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sku\": ");

			sb.append("\"");

			sb.append(_escape(sku));

			sb.append("\"");
		}

		if (skuExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"skuExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(skuExternalReferenceCode));

			sb.append("\"");
		}

		if (skuId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"skuId\": ");

			sb.append(skuId);
		}

		if (tierPrices != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tierPrices\": ");

			sb.append("[");

			for (int i = 0; i < tierPrices.length; i++) {
				sb.append(String.valueOf(tierPrices[i]));

				if ((i + 1) < tierPrices.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}