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

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 * @generated
 */
@Generated("")
@GraphQLName("AccountOrganization")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "AccountOrganization")
public class AccountOrganization {

	public Long getCommerceAccountId() {
		return commerceAccountId;
	}

	public void setCommerceAccountId(Long commerceAccountId) {
		this.commerceAccountId = commerceAccountId;
	}

	@JsonIgnore
	public void setCommerceAccountId(
		UnsafeSupplier<Long, Exception> commerceAccountIdUnsafeSupplier) {

		try {
			commerceAccountId = commerceAccountIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long commerceAccountId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@JsonIgnore
	public void setOrganizationId(
		UnsafeSupplier<Long, Exception> organizationIdUnsafeSupplier) {

		try {
			organizationId = organizationIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long organizationId;

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	@JsonIgnore
	public void setTreePath(
		UnsafeSupplier<String, Exception> treePathUnsafeSupplier) {

		try {
			treePath = treePathUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String treePath;

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"commerceAccountId\": ");

		sb.append(commerceAccountId);
		sb.append(", ");

		sb.append("\"name\": ");

		sb.append("\"");
		sb.append(name);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"organizationId\": ");

		sb.append(organizationId);
		sb.append(", ");

		sb.append("\"treePath\": ");

		sb.append("\"");
		sb.append(treePath);
		sb.append("\"");

		sb.append("}");

		return sb.toString();
	}

}