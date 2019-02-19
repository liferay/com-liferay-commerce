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

package com.liferay.headless.commerce.admin.site.setting.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Warehouse")
public class WarehouseDTO {

	@Nullable
	public String getCity() {
		return _city;
	}

	public Long getCommerceCountryId() {
		return _commerceCountryId;
	}

	@Nullable
	public Long getCommerceRegionId() {
		return _commerceRegionId;
	}

	@Nullable
	public String getDescription() {
		return _description;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Double getLatitude() {
		return _latitude;
	}

	@Nullable
	public Double getLongitude() {
		return _longitude;
	}

	public String getName() {
		return _name;
	}

	@Nullable
	public String getStreet1() {
		return _street1;
	}

	@Nullable
	public String getStreet2() {
		return _street2;
	}

	@Nullable
	public String getStreet3() {
		return _street3;
	}

	@Nullable
	public String getZip() {
		return _zip;
	}

	@Nullable
	public Boolean isActive() {
		return _active;
	}

	@Nullable
	public Boolean isPrimary() {
		return _primary;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setCommerceCountryId(Long commerceCountryId) {
		_commerceCountryId = commerceCountryId;
	}

	public void setCommerceRegionId(Long commerceRegionId) {
		_commerceRegionId = commerceRegionId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setLatitude(Double latitude) {
		_latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		_longitude = longitude;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPrimary(Boolean primary) {
		_primary = primary;
	}

	public void setStreet1(String street1) {
		_street1 = street1;
	}

	public void setStreet2(String street2) {
		_street2 = street2;
	}

	public void setStreet3(String street3) {
		_street3 = street3;
	}

	public void setZip(String zip) {
		_zip = zip;
	}

	@Nullable
	private Boolean _active;

	@Nullable
	private String _city;

	private Long _commerceCountryId;

	@Nullable
	private Long _commerceRegionId;

	@Nullable
	private String _description;

	@Nullable
	private Long _id;

	@Nullable
	private Double _latitude;

	@Nullable
	private Double _longitude;

	private String _name;

	@Nullable
	private Boolean _primary;

	@Nullable
	private String _street1;

	@Nullable
	private String _street2;

	@Nullable
	private String _street3;

	@Nullable
	private String _zip;

}