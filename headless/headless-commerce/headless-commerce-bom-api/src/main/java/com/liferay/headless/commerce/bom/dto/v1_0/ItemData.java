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

package com.liferay.headless.commerce.bom.dto.v1_0;

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
@GraphQLName("ItemData")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ItemData")
public class ItemData {

	@Schema
	public Brand[] getCompatibilities() {
		return compatibilities;
	}

	public void setCompatibilities(Brand[] compatibilities) {
		this.compatibilities = compatibilities;
	}

	@JsonIgnore
	public void setCompatibilities(
		UnsafeSupplier<Brand[], Exception> compatibilitiesUnsafeSupplier) {

		try {
			compatibilities = compatibilitiesUnsafeSupplier.get();
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
	protected Brand[] compatibilities;

	@Schema
	public Item[] getContent() {
		return content;
	}

	public void setContent(Item[] content) {
		this.content = content;
	}

	@JsonIgnore
	public void setContent(
		UnsafeSupplier<Item[], Exception> contentUnsafeSupplier) {

		try {
			content = contentUnsafeSupplier.get();
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
	protected Item[] content;

	@Schema
	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	@JsonIgnore
	public void setProducts(
		UnsafeSupplier<Product[], Exception> productsUnsafeSupplier) {

		try {
			products = productsUnsafeSupplier.get();
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
	protected Product[] products;

	@Schema
	public Spot[] getSpots() {
		return spots;
	}

	public void setSpots(Spot[] spots) {
		this.spots = spots;
	}

	@JsonIgnore
	public void setSpots(
		UnsafeSupplier<Spot[], Exception> spotsUnsafeSupplier) {

		try {
			spots = spotsUnsafeSupplier.get();
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
	protected Spot[] spots;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItemData)) {
			return false;
		}

		ItemData itemData = (ItemData)object;

		return Objects.equals(toString(), itemData.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (compatibilities != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"compatibilities\": ");

			sb.append("[");

			for (int i = 0; i < compatibilities.length; i++) {
				sb.append(String.valueOf(compatibilities[i]));

				if ((i + 1) < compatibilities.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (content != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"content\": ");

			sb.append("[");

			for (int i = 0; i < content.length; i++) {
				sb.append(String.valueOf(content[i]));

				if ((i + 1) < content.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (products != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"products\": ");

			sb.append("[");

			for (int i = 0; i < products.length; i++) {
				sb.append(String.valueOf(products[i]));

				if ((i + 1) < products.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (spots != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"spots\": ");

			sb.append("[");

			for (int i = 0; i < spots.length; i++) {
				sb.append(String.valueOf(spots[i]));

				if ((i + 1) < spots.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
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