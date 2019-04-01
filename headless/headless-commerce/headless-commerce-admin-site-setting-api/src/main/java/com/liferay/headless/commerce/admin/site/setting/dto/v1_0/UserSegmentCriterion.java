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

package com.liferay.headless.commerce.admin.site.setting.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

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
@GraphQLName("UserSegmentCriterion")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "UserSegmentCriterion")
public class UserSegmentCriterion {

	public Long getCommerceUserSegmentEntryId() {
		return commerceUserSegmentEntryId;
	}

	public void setCommerceUserSegmentEntryId(Long commerceUserSegmentEntryId) {
		this.commerceUserSegmentEntryId = commerceUserSegmentEntryId;
	}

	@JsonIgnore
	public void setCommerceUserSegmentEntryId(
		UnsafeSupplier<Long, Exception>
			commerceUserSegmentEntryIdUnsafeSupplier) {

		try {
			commerceUserSegmentEntryId =
				commerceUserSegmentEntryIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long commerceUserSegmentEntryId;

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

	public Double getPriority() {
		return priority;
	}

	public void setPriority(Double priority) {
		this.priority = priority;
	}

	@JsonIgnore
	public void setPriority(
		UnsafeSupplier<Double, Exception> priorityUnsafeSupplier) {

		try {
			priority = priorityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double priority;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		try {
			type = typeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String type;

	public String getTypeSettings() {
		return typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		this.typeSettings = typeSettings;
	}

	@JsonIgnore
	public void setTypeSettings(
		UnsafeSupplier<String, Exception> typeSettingsUnsafeSupplier) {

		try {
			typeSettings = typeSettingsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String typeSettings;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserSegmentCriterion)) {
			return false;
		}

		UserSegmentCriterion userSegmentCriterion =
			(UserSegmentCriterion)object;

		return Objects.equals(toString(), userSegmentCriterion.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		sb.append("\"commerceUserSegmentEntryId\": ");

		sb.append(commerceUserSegmentEntryId);
		sb.append(", ");

		sb.append("\"id\": ");

		sb.append(id);
		sb.append(", ");

		sb.append("\"priority\": ");

		sb.append(priority);
		sb.append(", ");

		sb.append("\"type\": ");

		sb.append("\"");
		sb.append(type);
		sb.append("\"");
		sb.append(", ");

		sb.append("\"typeSettings\": ");

		sb.append("\"");
		sb.append(typeSettings);
		sb.append("\"");

		sb.append("}");

		return sb.toString();
	}

}