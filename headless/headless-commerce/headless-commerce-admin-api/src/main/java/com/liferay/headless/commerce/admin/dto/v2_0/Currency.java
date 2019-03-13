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

package com.liferay.headless.commerce.admin.dto.v2_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Map;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 * @generated
 */
@Generated("")
@GraphQLName("Currency")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Currency")
public class Currency {

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public void setCode(UnsafeSupplier<String, Exception> codeUnsafeSupplier) {
		try {
			code = codeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected String code;

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(
		UnsafeSupplier<Map<String, String>, Exception> nameUnsafeSupplier) {

		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Map<String, String> name;

	public java.math.BigDecimal getRate() {
		return rate;
	}

	public void setRate(java.math.BigDecimal rate) {
		this.rate = rate;
	}

	@JsonIgnore
	public void setRate(
		UnsafeSupplier<java.math.BigDecimal, Exception> rateUnsafeSupplier) {

		try {
			rate = rateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected java.math.BigDecimal rate;

	public Map<String, String> getFormatPattern() {
		return formatPattern;
	}

	public void setFormatPattern(Map<String, String> formatPattern) {
		this.formatPattern = formatPattern;
	}

	@JsonIgnore
	public void setFormatPattern(
		UnsafeSupplier<Map<String, String>, Exception>
			formatPatternUnsafeSupplier) {

		try {
			formatPattern = formatPatternUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Map<String, String> formatPattern;

	public Integer getMaxFractionDigits() {
		return maxFractionDigits;
	}

	public void setMaxFractionDigits(Integer maxFractionDigits) {
		this.maxFractionDigits = maxFractionDigits;
	}

	@JsonIgnore
	public void setMaxFractionDigits(
		UnsafeSupplier<Integer, Exception> maxFractionDigitsUnsafeSupplier) {

		try {
			maxFractionDigits = maxFractionDigitsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer maxFractionDigits;

	public Integer getMinFractionDigits() {
		return minFractionDigits;
	}

	public void setMinFractionDigits(Integer minFractionDigits) {
		this.minFractionDigits = minFractionDigits;
	}

	@JsonIgnore
	public void setMinFractionDigits(
		UnsafeSupplier<Integer, Exception> minFractionDigitsUnsafeSupplier) {

		try {
			minFractionDigits = minFractionDigitsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer minFractionDigits;

	public String getRoundingMode() {
		return roundingMode;
	}

	public void setRoundingMode(String roundingMode) {
		this.roundingMode = roundingMode;
	}

	@JsonIgnore
	public void setRoundingMode(
		UnsafeSupplier<String, Exception> roundingModeUnsafeSupplier) {

		try {
			roundingMode = roundingModeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected String roundingMode;

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	@JsonIgnore
	public void setPrimary(
		UnsafeSupplier<Boolean, Exception> primaryUnsafeSupplier) {

		try {
			primary = primaryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean primary;

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"id\": ");

		sb.append(id);
		sb.append(", ");

		sb.append("\"code\": ");

		sb.append("\"");
		sb.append(code);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"name\": ");

		sb.append(name);
		sb.append(", ");

		sb.append("\"rate\": ");

		sb.append(rate);
		sb.append(", ");

		sb.append("\"formatPattern\": ");

		sb.append(formatPattern);
		sb.append(", ");

		sb.append("\"maxFractionDigits\": ");

		sb.append(maxFractionDigits);
		sb.append(", ");

		sb.append("\"minFractionDigits\": ");

		sb.append(minFractionDigits);
		sb.append(", ");

		sb.append("\"roundingMode\": ");

		sb.append("\"");
		sb.append(roundingMode);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"primary\": ");

		sb.append(primary);

		sb.append("}");

		return sb.toString();
	}

}