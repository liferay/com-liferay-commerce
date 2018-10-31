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

package com.liferay.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.data.integration.service.http.ProcessTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.integration.service.http.ProcessTypeServiceSoap
 * @generated
 */
@ProviderType
public class ProcessTypeSoap implements Serializable {
	public static ProcessTypeSoap toSoapModel(ProcessType model) {
		ProcessTypeSoap soapModel = new ProcessTypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessTypeId(model.getProcessTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ProcessTypeSoap[] toSoapModels(ProcessType[] models) {
		ProcessTypeSoap[] soapModels = new ProcessTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessTypeSoap[][] toSoapModels(ProcessType[][] models) {
		ProcessTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessTypeSoap[] toSoapModels(List<ProcessType> models) {
		List<ProcessTypeSoap> soapModels = new ArrayList<ProcessTypeSoap>(models.size());

		for (ProcessType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessTypeSoap[soapModels.size()]);
	}

	public ProcessTypeSoap() {
	}

	public long getPrimaryKey() {
		return _processTypeId;
	}

	public void setPrimaryKey(long pk) {
		setProcessTypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessTypeId() {
		return _processTypeId;
	}

	public void setProcessTypeId(long processTypeId) {
		_processTypeId = processTypeId;
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

	private String _uuid;
	private long _processTypeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
}