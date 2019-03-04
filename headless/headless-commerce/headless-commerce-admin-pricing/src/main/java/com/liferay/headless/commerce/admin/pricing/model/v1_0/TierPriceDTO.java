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

package com.liferay.headless.commerce.admin.pricing.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.math.BigDecimal;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "TierPrice")
public class TierPriceDTO {

	public Long getCommercePriceEntryId() {
		return _commercePriceEntryId;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Integer getMinimumQuantity() {
		return _minimumQuantity;
	}

	@Nullable
	public BigDecimal getPrice() {
		return _price;
	}

	@Nullable
	public String getPriceEntryExternalReferenceCode() {
		return _priceEntryExternalReferenceCode;
	}

	@Nullable
	public BigDecimal getPromoPrice() {
		return _promoPrice;
	}

	public void setCommercePriceEntryId(Long commercePriceEntryId) {
		_commercePriceEntryId = commercePriceEntryId;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		_minimumQuantity = minimumQuantity;
	}

	public void setPrice(BigDecimal price) {
		_price = price;
	}

	public void setPriceEntryExternalReferenceCode(
		String priceEntryExternalReferenceCode) {

		_priceEntryExternalReferenceCode = priceEntryExternalReferenceCode;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		_promoPrice = promoPrice;
	}

	private Long _commercePriceEntryId;
	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Integer _minimumQuantity;

	@Nullable
	private BigDecimal _price;

	@Nullable
	private String _priceEntryExternalReferenceCode;

	@Nullable
	private BigDecimal _promoPrice;

}