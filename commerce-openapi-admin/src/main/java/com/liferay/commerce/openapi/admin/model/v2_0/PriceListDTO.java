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

import java.util.Date;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "PriceList")
public class PriceListDTO {

	@Nullable
	public Long getCommercePriceListId() {
		return _commercePriceListId;
	}

	public String getCurrency() {
		return _currency;
	}

	@Nullable
	public Date getDisplayDate() {
		return _displayDate;
	}

	@Nullable
	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public Double getPriority() {
		return _priority;
	}

	public Boolean isActive() {
		return _active;
	}

	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setCommercePriceListId(Long commercePriceListId) {
		_commercePriceListId = commercePriceListId;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	private Boolean _active;

	@Nullable
	private Long _commercePriceListId;

	private String _currency;

	@Nullable
	private Date _displayDate;

	@Nullable
	private Date _expirationDate;

	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	private String _name;
	private Boolean _neverExpire;
	private Double _priority;

}