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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.product.service.http.CPRuleChannelRelServiceSoap}.
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.http.CPRuleChannelRelServiceSoap
 * @generated
 */
@ProviderType
public class CPRuleChannelRelSoap implements Serializable {
	public static CPRuleChannelRelSoap toSoapModel(CPRuleChannelRel model) {
		CPRuleChannelRelSoap soapModel = new CPRuleChannelRelSoap();

		soapModel.setCPRuleChannelRelId(model.getCPRuleChannelRelId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCPRuleId(model.getCPRuleId());
		soapModel.setCommerceChannelId(model.getCommerceChannelId());

		return soapModel;
	}

	public static CPRuleChannelRelSoap[] toSoapModels(CPRuleChannelRel[] models) {
		CPRuleChannelRelSoap[] soapModels = new CPRuleChannelRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPRuleChannelRelSoap[][] toSoapModels(
		CPRuleChannelRel[][] models) {
		CPRuleChannelRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPRuleChannelRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPRuleChannelRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPRuleChannelRelSoap[] toSoapModels(
		List<CPRuleChannelRel> models) {
		List<CPRuleChannelRelSoap> soapModels = new ArrayList<CPRuleChannelRelSoap>(models.size());

		for (CPRuleChannelRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPRuleChannelRelSoap[soapModels.size()]);
	}

	public CPRuleChannelRelSoap() {
	}

	public long getPrimaryKey() {
		return _CPRuleChannelRelId;
	}

	public void setPrimaryKey(long pk) {
		setCPRuleChannelRelId(pk);
	}

	public long getCPRuleChannelRelId() {
		return _CPRuleChannelRelId;
	}

	public void setCPRuleChannelRelId(long CPRuleChannelRelId) {
		_CPRuleChannelRelId = CPRuleChannelRelId;
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

	public long getCPRuleId() {
		return _CPRuleId;
	}

	public void setCPRuleId(long CPRuleId) {
		_CPRuleId = CPRuleId;
	}

	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	public void setCommerceChannelId(long commerceChannelId) {
		_commerceChannelId = commerceChannelId;
	}

	private long _CPRuleChannelRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CPRuleId;
	private long _commerceChannelId;
}