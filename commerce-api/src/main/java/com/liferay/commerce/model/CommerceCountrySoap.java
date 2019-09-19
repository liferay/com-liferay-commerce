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

package com.liferay.commerce.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceCountryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceCountrySoap implements Serializable {

	public static CommerceCountrySoap toSoapModel(CommerceCountry model) {
		CommerceCountrySoap soapModel = new CommerceCountrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommerceCountryId(model.getCommerceCountryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setBillingAllowed(model.isBillingAllowed());
		soapModel.setShippingAllowed(model.isShippingAllowed());
		soapModel.setTwoLettersISOCode(model.getTwoLettersISOCode());
		soapModel.setThreeLettersISOCode(model.getThreeLettersISOCode());
		soapModel.setNumericISOCode(model.getNumericISOCode());
		soapModel.setSubjectToVAT(model.isSubjectToVAT());
		soapModel.setPriority(model.getPriority());
		soapModel.setActive(model.isActive());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setChannelFilterEnabled(model.isChannelFilterEnabled());

		return soapModel;
	}

	public static CommerceCountrySoap[] toSoapModels(CommerceCountry[] models) {
		CommerceCountrySoap[] soapModels =
			new CommerceCountrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceCountrySoap[][] toSoapModels(
		CommerceCountry[][] models) {

		CommerceCountrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceCountrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceCountrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceCountrySoap[] toSoapModels(
		List<CommerceCountry> models) {

		List<CommerceCountrySoap> soapModels =
			new ArrayList<CommerceCountrySoap>(models.size());

		for (CommerceCountry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceCountrySoap[soapModels.size()]);
	}

	public CommerceCountrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceCountryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceCountryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommerceCountryId() {
		return _commerceCountryId;
	}

	public void setCommerceCountryId(long commerceCountryId) {
		_commerceCountryId = commerceCountryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public boolean getBillingAllowed() {
		return _billingAllowed;
	}

	public boolean isBillingAllowed() {
		return _billingAllowed;
	}

	public void setBillingAllowed(boolean billingAllowed) {
		_billingAllowed = billingAllowed;
	}

	public boolean getShippingAllowed() {
		return _shippingAllowed;
	}

	public boolean isShippingAllowed() {
		return _shippingAllowed;
	}

	public void setShippingAllowed(boolean shippingAllowed) {
		_shippingAllowed = shippingAllowed;
	}

	public String getTwoLettersISOCode() {
		return _twoLettersISOCode;
	}

	public void setTwoLettersISOCode(String twoLettersISOCode) {
		_twoLettersISOCode = twoLettersISOCode;
	}

	public String getThreeLettersISOCode() {
		return _threeLettersISOCode;
	}

	public void setThreeLettersISOCode(String threeLettersISOCode) {
		_threeLettersISOCode = threeLettersISOCode;
	}

	public int getNumericISOCode() {
		return _numericISOCode;
	}

	public void setNumericISOCode(int numericISOCode) {
		_numericISOCode = numericISOCode;
	}

	public boolean getSubjectToVAT() {
		return _subjectToVAT;
	}

	public boolean isSubjectToVAT() {
		return _subjectToVAT;
	}

	public void setSubjectToVAT(boolean subjectToVAT) {
		_subjectToVAT = subjectToVAT;
	}

	public double getPriority() {
		return _priority;
	}

	public void setPriority(double priority) {
		_priority = priority;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public boolean getChannelFilterEnabled() {
		return _channelFilterEnabled;
	}

	public boolean isChannelFilterEnabled() {
		return _channelFilterEnabled;
	}

	public void setChannelFilterEnabled(boolean channelFilterEnabled) {
		_channelFilterEnabled = channelFilterEnabled;
	}

	private String _uuid;
	private long _commerceCountryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private boolean _billingAllowed;
	private boolean _shippingAllowed;
	private String _twoLettersISOCode;
	private String _threeLettersISOCode;
	private int _numericISOCode;
	private boolean _subjectToVAT;
	private double _priority;
	private boolean _active;
	private Date _lastPublishDate;
	private boolean _channelFilterEnabled;

}