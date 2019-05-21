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

package com.liferay.commerce.user.segment.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.user.segment.service.http.CommerceUserSegmentEntryServiceSoap}.
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceUserSegmentEntrySoap implements Serializable {

	public static CommerceUserSegmentEntrySoap toSoapModel(
		CommerceUserSegmentEntry model) {

		CommerceUserSegmentEntrySoap soapModel =
			new CommerceUserSegmentEntrySoap();

		soapModel.setCommerceUserSegmentEntryId(
			model.getCommerceUserSegmentEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setKey(model.getKey());
		soapModel.setActive(model.isActive());
		soapModel.setSystem(model.isSystem());
		soapModel.setPriority(model.getPriority());

		return soapModel;
	}

	public static CommerceUserSegmentEntrySoap[] toSoapModels(
		CommerceUserSegmentEntry[] models) {

		CommerceUserSegmentEntrySoap[] soapModels =
			new CommerceUserSegmentEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceUserSegmentEntrySoap[][] toSoapModels(
		CommerceUserSegmentEntry[][] models) {

		CommerceUserSegmentEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceUserSegmentEntrySoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceUserSegmentEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceUserSegmentEntrySoap[] toSoapModels(
		List<CommerceUserSegmentEntry> models) {

		List<CommerceUserSegmentEntrySoap> soapModels =
			new ArrayList<CommerceUserSegmentEntrySoap>(models.size());

		for (CommerceUserSegmentEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceUserSegmentEntrySoap[soapModels.size()]);
	}

	public CommerceUserSegmentEntrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceUserSegmentEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceUserSegmentEntryId(pk);
	}

	public long getCommerceUserSegmentEntryId() {
		return _commerceUserSegmentEntryId;
	}

	public void setCommerceUserSegmentEntryId(long commerceUserSegmentEntryId) {
		_commerceUserSegmentEntryId = commerceUserSegmentEntryId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
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

	public boolean getSystem() {
		return _system;
	}

	public boolean isSystem() {
		return _system;
	}

	public void setSystem(boolean system) {
		_system = system;
	}

	public double getPriority() {
		return _priority;
	}

	public void setPriority(double priority) {
		_priority = priority;
	}

	private long _commerceUserSegmentEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _key;
	private boolean _active;
	private boolean _system;
	private double _priority;

}