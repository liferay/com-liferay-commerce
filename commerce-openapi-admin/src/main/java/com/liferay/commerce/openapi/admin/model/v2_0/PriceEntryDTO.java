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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.math.BigDecimal;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "PriceEntry")
public class PriceEntryDTO {

	public Long getCommercePriceListId() {
		return _commercePriceListId;
	}

	@Nullable
	public Long getCommerceProductInstanceId() {
		return _commerceProductInstanceId;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public BigDecimal getPrice() {
		return _price;
	}

	public BigDecimal getPromoPrice() {
		return _promoPrice;
	}

	@Nullable
	public String getSku() {
		return _sku;
	}

	@Nullable
	public String getSkuExternalReferenceCode() {
		return _skuExternalReferenceCode;
	}

	@Nullable
	public Boolean isHasTierPrice() {
		return _hasTierPrice;
	}

	public Boolean isStandardPrice() {
		return _standardPrice;
	}

	public void setCommercePriceListId(Long commercePriceListId) {
		_commercePriceListId = commercePriceListId;
	}

	public void setCommerceProductInstanceId(Long commerceProductInstanceId) {
		_commerceProductInstanceId = commerceProductInstanceId;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setHasTierPrice(Boolean hasTierPrice) {
		_hasTierPrice = hasTierPrice;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setPrice(BigDecimal price) {
		_price = price;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public void setSkuExternalReferenceCode(String skuExternalReferenceCode) {
		_skuExternalReferenceCode = skuExternalReferenceCode;
	}

	public void setStandardPrice(Boolean standardPrice) {
		_standardPrice = standardPrice;
	}

	private Long _commercePriceListId;

	@Nullable
	private Long _commerceProductInstanceId;

	private String _externalReferenceCode;

	@Nullable
	private Boolean _hasTierPrice;

	@Nullable
	private Long _id;

	private BigDecimal _price;
	private BigDecimal _promoPrice;

	@Nullable
	private String _sku;

	@Nullable
	private String _skuExternalReferenceCode;

	private Boolean _standardPrice;

}