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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceSubscriptionEntryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.http.CommerceSubscriptionEntryServiceSoap
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntrySoap implements Serializable {
	public static CommerceSubscriptionEntrySoap toSoapModel(
		CommerceSubscriptionEntry model) {
		CommerceSubscriptionEntrySoap soapModel = new CommerceSubscriptionEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommerceSubscriptionEntryId(model.getCommerceSubscriptionEntryId());
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

	public static CommerceSubscriptionEntrySoap[] toSoapModels(
		CommerceSubscriptionEntry[] models) {
		CommerceSubscriptionEntrySoap[] soapModels = new CommerceSubscriptionEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceSubscriptionEntrySoap[][] toSoapModels(
		CommerceSubscriptionEntry[][] models) {
		CommerceSubscriptionEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceSubscriptionEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceSubscriptionEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceSubscriptionEntrySoap[] toSoapModels(
		List<CommerceSubscriptionEntry> models) {
		List<CommerceSubscriptionEntrySoap> soapModels = new ArrayList<CommerceSubscriptionEntrySoap>(models.size());

		for (CommerceSubscriptionEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceSubscriptionEntrySoap[soapModels.size()]);
	}

	public CommerceSubscriptionEntrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceSubscriptionEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceSubscriptionEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommerceSubscriptionEntryId() {
		return _commerceSubscriptionEntryId;
	}

	public void setCommerceSubscriptionEntryId(long commerceSubscriptionEntryId) {
		_commerceSubscriptionEntryId = commerceSubscriptionEntryId;
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
	private long _commerceSubscriptionEntryId;
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