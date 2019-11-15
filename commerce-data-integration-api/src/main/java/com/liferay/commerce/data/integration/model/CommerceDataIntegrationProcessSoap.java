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

package com.liferay.commerce.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.data.integration.service.http.CommerceDataIntegrationProcessServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceDataIntegrationProcessSoap implements Serializable {

	public static CommerceDataIntegrationProcessSoap toSoapModel(
		CommerceDataIntegrationProcess model) {

		CommerceDataIntegrationProcessSoap soapModel =
			new CommerceDataIntegrationProcessSoap();

		soapModel.setCommerceDataIntegrationProcessId(
			model.getCommerceDataIntegrationProcessId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setType(model.getType());
		soapModel.setTypeSettings(model.getTypeSettings());
		soapModel.setSystem(model.isSystem());
		soapModel.setActive(model.isActive());
		soapModel.setCronExpression(model.getCronExpression());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static CommerceDataIntegrationProcessSoap[] toSoapModels(
		CommerceDataIntegrationProcess[] models) {

		CommerceDataIntegrationProcessSoap[] soapModels =
			new CommerceDataIntegrationProcessSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceDataIntegrationProcessSoap[][] toSoapModels(
		CommerceDataIntegrationProcess[][] models) {

		CommerceDataIntegrationProcessSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceDataIntegrationProcessSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceDataIntegrationProcessSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceDataIntegrationProcessSoap[] toSoapModels(
		List<CommerceDataIntegrationProcess> models) {

		List<CommerceDataIntegrationProcessSoap> soapModels =
			new ArrayList<CommerceDataIntegrationProcessSoap>(models.size());

		for (CommerceDataIntegrationProcess model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceDataIntegrationProcessSoap[soapModels.size()]);
	}

	public CommerceDataIntegrationProcessSoap() {
	}

	public long getPrimaryKey() {
		return _commerceDataIntegrationProcessId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceDataIntegrationProcessId(pk);
	}

	public long getCommerceDataIntegrationProcessId() {
		return _commerceDataIntegrationProcessId;
	}

	public void setCommerceDataIntegrationProcessId(
		long commerceDataIntegrationProcessId) {

		_commerceDataIntegrationProcessId = commerceDataIntegrationProcessId;
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public String getCronExpression() {
		return _cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		_cronExpression = cronExpression;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	private long _commerceDataIntegrationProcessId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _type;
	private String _typeSettings;
	private boolean _system;
	private boolean _active;
	private String _cronExpression;
	private Date _startDate;
	private Date _endDate;

}