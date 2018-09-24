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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CPSubscriptionEntryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.http.CPSubscriptionEntryServiceSoap
 * @generated
 */
@ProviderType
public class CPSubscriptionEntrySoap implements Serializable {
	public static CPSubscriptionEntrySoap toSoapModel(CPSubscriptionEntry model) {
		CPSubscriptionEntrySoap soapModel = new CPSubscriptionEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCPSubscriptionEntryId(model.getCPSubscriptionEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCPInstanceId(model.getCPInstanceId());
		soapModel.setCommerceOrderItemId(model.getCommerceOrderItemId());
		soapModel.setSubscriptionCycleLength(model.getSubscriptionCycleLength());
		soapModel.setSubscriptionCyclePeriod(model.getSubscriptionCyclePeriod());
		soapModel.setMaxSubscriptionCyclesNumber(model.getMaxSubscriptionCyclesNumber());
		soapModel.setActive(model.isActive());
		soapModel.setNextIterationDate(model.getNextIterationDate());

		return soapModel;
	}

	public static CPSubscriptionEntrySoap[] toSoapModels(
		CPSubscriptionEntry[] models) {
		CPSubscriptionEntrySoap[] soapModels = new CPSubscriptionEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPSubscriptionEntrySoap[][] toSoapModels(
		CPSubscriptionEntry[][] models) {
		CPSubscriptionEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPSubscriptionEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPSubscriptionEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPSubscriptionEntrySoap[] toSoapModels(
		List<CPSubscriptionEntry> models) {
		List<CPSubscriptionEntrySoap> soapModels = new ArrayList<CPSubscriptionEntrySoap>(models.size());

		for (CPSubscriptionEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPSubscriptionEntrySoap[soapModels.size()]);
	}

	public CPSubscriptionEntrySoap() {
	}

	public long getPrimaryKey() {
		return _CPSubscriptionEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCPSubscriptionEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCPSubscriptionEntryId() {
		return _CPSubscriptionEntryId;
	}

	public void setCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		_CPSubscriptionEntryId = CPSubscriptionEntryId;
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

	public long getCPInstanceId() {
		return _CPInstanceId;
	}

	public void setCPInstanceId(long CPInstanceId) {
		_CPInstanceId = CPInstanceId;
	}

	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
	}

	public long getSubscriptionCycleLength() {
		return _subscriptionCycleLength;
	}

	public void setSubscriptionCycleLength(long subscriptionCycleLength) {
		_subscriptionCycleLength = subscriptionCycleLength;
	}

	public String getSubscriptionCyclePeriod() {
		return _subscriptionCyclePeriod;
	}

	public void setSubscriptionCyclePeriod(String subscriptionCyclePeriod) {
		_subscriptionCyclePeriod = subscriptionCyclePeriod;
	}

	public long getMaxSubscriptionCyclesNumber() {
		return _maxSubscriptionCyclesNumber;
	}

	public void setMaxSubscriptionCyclesNumber(long maxSubscriptionCyclesNumber) {
		_maxSubscriptionCyclesNumber = maxSubscriptionCyclesNumber;
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

	public Date getNextIterationDate() {
		return _nextIterationDate;
	}

	public void setNextIterationDate(Date nextIterationDate) {
		_nextIterationDate = nextIterationDate;
	}

	private String _uuid;
	private long _CPSubscriptionEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CPInstanceId;
	private long _commerceOrderItemId;
	private long _subscriptionCycleLength;
	private String _subscriptionCyclePeriod;
	private long _maxSubscriptionCyclesNumber;
	private boolean _active;
	private Date _nextIterationDate;
}