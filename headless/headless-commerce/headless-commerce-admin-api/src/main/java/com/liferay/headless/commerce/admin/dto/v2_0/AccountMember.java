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
@GraphQLName("AccountMember")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "AccountMember")
public class AccountMember {

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public void setUserId(
		UnsafeSupplier<Long, Exception> userIdUnsafeSupplier) {

		try {
			userId = userIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long userId;

	public AccountRole[] getRoles() {
		return roles;
	}

	public void setRoles(AccountRole[] roles) {
		this.roles = roles;
	}

	@JsonIgnore
	public void setRoles(
		UnsafeSupplier<AccountRole[], Exception> rolesUnsafeSupplier) {

		try {
			roles = rolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected AccountRole[] roles;

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

		sb.append("\"userId\": ");

		sb.append(userId);
		sb.append(", ");

		sb.append("\"roles\": ");

		if (roles == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0; i < roles.length; i++) {
				sb.append(roles[i]);

				if ((i + 1) < roles.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

}