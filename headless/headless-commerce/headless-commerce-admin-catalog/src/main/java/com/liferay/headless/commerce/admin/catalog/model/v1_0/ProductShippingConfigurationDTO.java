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

import java.math.BigDecimal;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "ProductShippingConfiguration")
public class ProductShippingConfigurationDTO {

	@Nullable
	public BigDecimal getDepth() {
		return _depth;
	}

	@Nullable
	public BigDecimal getHeight() {
		return _height;
	}

	@Nullable
	public BigDecimal getShippingExtraPrice() {
		return _shippingExtraPrice;
	}

	@Nullable
	public BigDecimal getWeight() {
		return _weight;
	}

	@Nullable
	public BigDecimal getWidth() {
		return _width;
	}

	@Nullable
	public Boolean isFreeShipping() {
		return _freeShipping;
	}

	@Nullable
	public Boolean isShippable() {
		return _shippable;
	}

	@Nullable
	public Boolean isShippingSeparately() {
		return _shippingSeparately;
	}

	public void setDepth(BigDecimal depth) {
		_depth = depth;
	}

	public void setFreeShipping(Boolean freeShipping) {
		_freeShipping = freeShipping;
	}

	public void setHeight(BigDecimal height) {
		_height = height;
	}

	public void setShippable(Boolean shippable) {
		_shippable = shippable;
	}

	public void setShippingExtraPrice(BigDecimal shippingExtraPrice) {
		_shippingExtraPrice = shippingExtraPrice;
	}

	public void setShippingSeparately(Boolean shippingSeparately) {
		_shippingSeparately = shippingSeparately;
	}

	public void setWeight(BigDecimal weight) {
		_weight = weight;
	}

	public void setWidth(BigDecimal width) {
		_width = width;
	}

	@Nullable
	private BigDecimal _depth;

	@Nullable
	private Boolean _freeShipping;

	@Nullable
	private BigDecimal _height;

	@Nullable
	private Boolean _shippable;

	@Nullable
	private BigDecimal _shippingExtraPrice;

	@Nullable
	private Boolean _shippingSeparately;

	@Nullable
	private BigDecimal _weight;

	@Nullable
	private BigDecimal _width;

}