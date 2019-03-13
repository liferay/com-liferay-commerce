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
@GraphQLName("Country")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Country")
public class Country {

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

	public Boolean getBillingAllowed() {
		return billingAllowed;
	}

	public void setBillingAllowed(Boolean billingAllowed) {
		this.billingAllowed = billingAllowed;
	}

	@JsonIgnore
	public void setBillingAllowed(
		UnsafeSupplier<Boolean, Exception> billingAllowedUnsafeSupplier) {

		try {
			billingAllowed = billingAllowedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean billingAllowed;

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

	public Integer getNumericISOCode() {
		return numericISOCode;
	}

	public void setNumericISOCode(Integer numericISOCode) {
		this.numericISOCode = numericISOCode;
	}

	@JsonIgnore
	public void setNumericISOCode(
		UnsafeSupplier<Integer, Exception> numericISOCodeUnsafeSupplier) {

		try {
			numericISOCode = numericISOCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Integer numericISOCode;

	public Region[] getRegions() {
		return regions;
	}

	public void setRegions(Region[] regions) {
		this.regions = regions;
	}

	@JsonIgnore
	public void setRegions(
		UnsafeSupplier<Region[], Exception> regionsUnsafeSupplier) {

		try {
			regions = regionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Region[] regions;

	public Boolean getShippingAllowed() {
		return shippingAllowed;
	}

	public void setShippingAllowed(Boolean shippingAllowed) {
		this.shippingAllowed = shippingAllowed;
	}

	@JsonIgnore
	public void setShippingAllowed(
		UnsafeSupplier<Boolean, Exception> shippingAllowedUnsafeSupplier) {

		try {
			shippingAllowed = shippingAllowedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean shippingAllowed;

	public Boolean getSubjectToVAT() {
		return subjectToVAT;
	}

	public void setSubjectToVAT(Boolean subjectToVAT) {
		this.subjectToVAT = subjectToVAT;
	}

	@JsonIgnore
	public void setSubjectToVAT(
		UnsafeSupplier<Boolean, Exception> subjectToVATUnsafeSupplier) {

		try {
			subjectToVAT = subjectToVATUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean subjectToVAT;

	public String getThreeLettersISOCode() {
		return threeLettersISOCode;
	}

	public void setThreeLettersISOCode(String threeLettersISOCode) {
		this.threeLettersISOCode = threeLettersISOCode;
	}

	@JsonIgnore
	public void setThreeLettersISOCode(
		UnsafeSupplier<String, Exception> threeLettersISOCodeUnsafeSupplier) {

		try {
			threeLettersISOCode = threeLettersISOCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected String threeLettersISOCode;

	public String getTwoLettersISOCode() {
		return twoLettersISOCode;
	}

	public void setTwoLettersISOCode(String twoLettersISOCode) {
		this.twoLettersISOCode = twoLettersISOCode;
	}

	@JsonIgnore
	public void setTwoLettersISOCode(
		UnsafeSupplier<String, Exception> twoLettersISOCodeUnsafeSupplier) {

		try {
			twoLettersISOCode = twoLettersISOCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected String twoLettersISOCode;

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"id\": ");

		sb.append(id);
		sb.append(", ");

		sb.append("\"billingAllowed\": ");

		sb.append(billingAllowed);
		sb.append(", ");

		sb.append("\"name\": ");

		sb.append(name);
		sb.append(", ");

		sb.append("\"numericISOCode\": ");

		sb.append(numericISOCode);
		sb.append(", ");

		sb.append("\"regions\": ");

		if (regions == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0; i < regions.length; i++) {
				sb.append(regions[i]);

				if ((i + 1) < regions.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append(", ");

		sb.append("\"shippingAllowed\": ");

		sb.append(shippingAllowed);
		sb.append(", ");

		sb.append("\"subjectToVAT\": ");

		sb.append(subjectToVAT);
		sb.append(", ");

		sb.append("\"threeLettersISOCode\": ");

		sb.append("\"");
		sb.append(threeLettersISOCode);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"twoLettersISOCode\": ");

		sb.append("\"");
		sb.append(twoLettersISOCode);
		sb.append("\"");

		sb.append("}");

		return sb.toString();
	}

}