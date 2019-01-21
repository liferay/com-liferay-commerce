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

package com.liferay.commerce.openapi.admin.model;

import java.util.Date;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "PriceList")
public class PriceListDTO {

	public boolean getActive() {
		return _active;
	}

	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	public String getCurrency() {
		return _currency;
	}

	public Date getDisplayDate() {
		return _displayDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public boolean getNeverExpire() {
		return _neverExpire;
	}

	public double getPriority() {
		return _priority;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setCommercePriceListId(long commercePriceListId) {
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

	public void setId(long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNeverExpire(boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setPriority(double priority) {
		_priority = priority;
	}

	private boolean _active;
	private long _commercePriceListId;
	private String _currency;
	private Date _displayDate;
	private Date _expirationDate;
	private String _externalReferenceCode;
	private long _id;
	private String _name;
	private boolean _neverExpire;
	private double _priority;

}