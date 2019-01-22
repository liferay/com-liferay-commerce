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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceSubscriptionCycleEntryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.http.CommerceSubscriptionCycleEntryServiceSoap
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntrySoap implements Serializable {
	public static CommerceSubscriptionCycleEntrySoap toSoapModel(
		CommerceSubscriptionCycleEntry model) {
		CommerceSubscriptionCycleEntrySoap soapModel = new CommerceSubscriptionCycleEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommerceSubscriptionCycleEntryId(model.getCommerceSubscriptionCycleEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceOrderItemId(model.getCommerceOrderItemId());
		soapModel.setCommerceSubscriptionEntryId(model.getCommerceSubscriptionEntryId());
		soapModel.setRenew(model.isRenew());

		return soapModel;
	}

	public static CommerceSubscriptionCycleEntrySoap[] toSoapModels(
		CommerceSubscriptionCycleEntry[] models) {
		CommerceSubscriptionCycleEntrySoap[] soapModels = new CommerceSubscriptionCycleEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceSubscriptionCycleEntrySoap[][] toSoapModels(
		CommerceSubscriptionCycleEntry[][] models) {
		CommerceSubscriptionCycleEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceSubscriptionCycleEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceSubscriptionCycleEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceSubscriptionCycleEntrySoap[] toSoapModels(
		List<CommerceSubscriptionCycleEntry> models) {
		List<CommerceSubscriptionCycleEntrySoap> soapModels = new ArrayList<CommerceSubscriptionCycleEntrySoap>(models.size());

		for (CommerceSubscriptionCycleEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceSubscriptionCycleEntrySoap[soapModels.size()]);
	}

	public CommerceSubscriptionCycleEntrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceSubscriptionCycleEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceSubscriptionCycleEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommerceSubscriptionCycleEntryId() {
		return _commerceSubscriptionCycleEntryId;
	}

	public void setCommerceSubscriptionCycleEntryId(
		long commerceSubscriptionCycleEntryId) {
		_commerceSubscriptionCycleEntryId = commerceSubscriptionCycleEntryId;
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

	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
	}

	public long getCommerceSubscriptionEntryId() {
		return _commerceSubscriptionEntryId;
	}

	public void setCommerceSubscriptionEntryId(long commerceSubscriptionEntryId) {
		_commerceSubscriptionEntryId = commerceSubscriptionEntryId;
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
	private long _commerceSubscriptionCycleEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceOrderItemId;
	private long _commerceSubscriptionEntryId;
	private boolean _renew;
}