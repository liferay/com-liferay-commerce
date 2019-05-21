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
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.product.service.http.CPRuleUserSegmentRelServiceSoap}.
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPRuleUserSegmentRelSoap implements Serializable {

	public static CPRuleUserSegmentRelSoap toSoapModel(
		CPRuleUserSegmentRel model) {

		CPRuleUserSegmentRelSoap soapModel = new CPRuleUserSegmentRelSoap();

		soapModel.setCPRuleUserSegmentRelId(model.getCPRuleUserSegmentRelId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCPRuleId(model.getCPRuleId());
		soapModel.setCommerceUserSegmentEntryId(
			model.getCommerceUserSegmentEntryId());

		return soapModel;
	}

	public static CPRuleUserSegmentRelSoap[] toSoapModels(
		CPRuleUserSegmentRel[] models) {

		CPRuleUserSegmentRelSoap[] soapModels =
			new CPRuleUserSegmentRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPRuleUserSegmentRelSoap[][] toSoapModels(
		CPRuleUserSegmentRel[][] models) {

		CPRuleUserSegmentRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CPRuleUserSegmentRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPRuleUserSegmentRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPRuleUserSegmentRelSoap[] toSoapModels(
		List<CPRuleUserSegmentRel> models) {

		List<CPRuleUserSegmentRelSoap> soapModels =
			new ArrayList<CPRuleUserSegmentRelSoap>(models.size());

		for (CPRuleUserSegmentRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CPRuleUserSegmentRelSoap[soapModels.size()]);
	}

	public CPRuleUserSegmentRelSoap() {
	}

	public long getPrimaryKey() {
		return _CPRuleUserSegmentRelId;
	}

	public void setPrimaryKey(long pk) {
		setCPRuleUserSegmentRelId(pk);
	}

	public long getCPRuleUserSegmentRelId() {
		return _CPRuleUserSegmentRelId;
	}

	public void setCPRuleUserSegmentRelId(long CPRuleUserSegmentRelId) {
		_CPRuleUserSegmentRelId = CPRuleUserSegmentRelId;
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

	public long getCommerceUserSegmentEntryId() {
		return _commerceUserSegmentEntryId;
	}

	public void setCommerceUserSegmentEntryId(long commerceUserSegmentEntryId) {
		_commerceUserSegmentEntryId = commerceUserSegmentEntryId;
	}

	private long _CPRuleUserSegmentRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CPRuleId;
	private long _commerceUserSegmentEntryId;

}