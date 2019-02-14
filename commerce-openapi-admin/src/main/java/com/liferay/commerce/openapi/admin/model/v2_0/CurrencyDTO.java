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

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Currency")
public class CurrencyDTO {

	public String getCode() {
		return _code;
	}

	public Map<String, String> getFormatPattern() {
		return _formatPattern;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Integer getMaxFractionDigits() {
		return _maxFractionDigits;
	}

	@Nullable
	public Integer getMinFractionDigits() {
		return _minFractionDigits;
	}

	public Map<String, String> getName() {
		return _name;
	}

	public BigDecimal getRate() {
		return _rate;
	}

	public String getRoundingMode() {
		return _roundingMode;
	}

	@Nullable
	public Boolean isPrimary() {
		return _primary;
	}

	public void setCode(String code) {
		_code = code;
	}

	public void setFormatPattern(Map<String, String> formatPattern) {
		_formatPattern = formatPattern;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setMaxFractionDigits(Integer maxFractionDigits) {
		_maxFractionDigits = maxFractionDigits;
	}

	public void setMinFractionDigits(Integer minFractionDigits) {
		_minFractionDigits = minFractionDigits;
	}

	public void setName(Map<String, String> name) {
		_name = name;
	}

	public void setPrimary(Boolean primary) {
		_primary = primary;
	}

	public void setRate(BigDecimal rate) {
		_rate = rate;
	}

	public void setRoundingMode(String roundingMode) {
		_roundingMode = roundingMode;
	}

	private String _code;
	private Map<String, String> _formatPattern;

	@Nullable
	private Long _id;

	@Nullable
	private Integer _maxFractionDigits;

	@Nullable
	private Integer _minFractionDigits;

	private Map<String, String> _name;

	@Nullable
	private Boolean _primary;

	private BigDecimal _rate;
	private String _roundingMode;

}