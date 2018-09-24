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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CPSubscriptionCycleEntryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.http.CPSubscriptionCycleEntryServiceSoap
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntrySoap implements Serializable {
	public static CPSubscriptionCycleEntrySoap toSoapModel(
		CPSubscriptionCycleEntry model) {
		CPSubscriptionCycleEntrySoap soapModel = new CPSubscriptionCycleEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCPSubscriptionCycleEntryId(model.getCPSubscriptionCycleEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCPSubscriptionEntryId(model.getCPSubscriptionEntryId());
		soapModel.setCommerceOrderItemId(model.getCommerceOrderItemId());
		soapModel.setRenew(model.isRenew());

		return soapModel;
	}

	public static CPSubscriptionCycleEntrySoap[] toSoapModels(
		CPSubscriptionCycleEntry[] models) {
		CPSubscriptionCycleEntrySoap[] soapModels = new CPSubscriptionCycleEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPSubscriptionCycleEntrySoap[][] toSoapModels(
		CPSubscriptionCycleEntry[][] models) {
		CPSubscriptionCycleEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPSubscriptionCycleEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPSubscriptionCycleEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPSubscriptionCycleEntrySoap[] toSoapModels(
		List<CPSubscriptionCycleEntry> models) {
		List<CPSubscriptionCycleEntrySoap> soapModels = new ArrayList<CPSubscriptionCycleEntrySoap>(models.size());

		for (CPSubscriptionCycleEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPSubscriptionCycleEntrySoap[soapModels.size()]);
	}

	public CPSubscriptionCycleEntrySoap() {
	}

	public long getPrimaryKey() {
		return _CPSubscriptionCycleEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCPSubscriptionCycleEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCPSubscriptionCycleEntryId() {
		return _CPSubscriptionCycleEntryId;
	}

	public void setCPSubscriptionCycleEntryId(long CPSubscriptionCycleEntryId) {
		_CPSubscriptionCycleEntryId = CPSubscriptionCycleEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getCPSubscriptionEntryId() {
		return _CPSubscriptionEntryId;
	}

	public void setCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		_CPSubscriptionEntryId = CPSubscriptionEntryId;
	}

	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
	}

	public boolean getRenew() {
		return _renew;
	}

	public boolean isRenew() {
		return _renew;
	}

	public void setRenew(boolean renew) {
		_renew = renew;
	}

	private String _uuid;
	private long _CPSubscriptionCycleEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CPSubscriptionEntryId;
	private long _commerceOrderItemId;
	private boolean _renew;
}