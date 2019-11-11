/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.bom.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
@GraphQLName("Area")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Area")
public class Area {

	@Schema
	public Breadcrumb[] getBreadcrumbs() {
		return breadcrumbs;
	}

	public void setBreadcrumbs(Breadcrumb[] breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	@JsonIgnore
	public void setBreadcrumbs(
		UnsafeSupplier<Breadcrumb[], Exception> breadcrumbsUnsafeSupplier) {

		try {
			breadcrumbs = breadcrumbsUnsafeSupplier.get();
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
	protected Breadcrumb[] breadcrumbs;

	@Schema
	public AreaData getData() {
		return data;
	}

	public void setData(AreaData data) {
		this.data = data;
	}

	@JsonIgnore
	public void setData(
		UnsafeSupplier<AreaData, Exception> dataUnsafeSupplier) {

		try {
			data = dataUnsafeSupplier.get();
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
	protected AreaData data;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Area)) {
			return false;
		}

		Area area = (Area)object;

		return Objects.equals(toString(), area.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (breadcrumbs != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"breadcrumbs\": ");

			sb.append("[");

			for (int i = 0; i < breadcrumbs.length; i++) {
				sb.append(String.valueOf(breadcrumbs[i]));

				if ((i + 1) < breadcrumbs.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (data != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"data\": ");

			sb.append(String.valueOf(data));
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