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

package com.liferay.commerce.notification.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.notification.service.http.CommerceNotificationTemplateCommerceAccountGroupRelServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceNotificationTemplateCommerceAccountGroupRelSoap
	implements Serializable {

	public static CommerceNotificationTemplateCommerceAccountGroupRelSoap
		toSoapModel(CommerceNotificationTemplateCommerceAccountGroupRel model) {

		CommerceNotificationTemplateCommerceAccountGroupRelSoap soapModel =
			new CommerceNotificationTemplateCommerceAccountGroupRelSoap();

		soapModel.setCommerceNotificationTemplateCommerceAccountGroupRelId(
			model.getCommerceNotificationTemplateCommerceAccountGroupRelId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceNotificationTemplateId(
			model.getCommerceNotificationTemplateId());
		soapModel.setCommerceAccountGroupId(model.getCommerceAccountGroupId());

		return soapModel;
	}

	public static CommerceNotificationTemplateCommerceAccountGroupRelSoap[]
		toSoapModels(
			CommerceNotificationTemplateCommerceAccountGroupRel[] models) {

		CommerceNotificationTemplateCommerceAccountGroupRelSoap[] soapModels =
			new CommerceNotificationTemplateCommerceAccountGroupRelSoap
				[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceNotificationTemplateCommerceAccountGroupRelSoap[][]
		toSoapModels(
			CommerceNotificationTemplateCommerceAccountGroupRel[][] models) {

		CommerceNotificationTemplateCommerceAccountGroupRelSoap[][] soapModels =
			null;

		if (models.length > 0) {
			soapModels =
				new CommerceNotificationTemplateCommerceAccountGroupRelSoap
					[models.length][models[0].length];
		}
		else {
			soapModels =
				new CommerceNotificationTemplateCommerceAccountGroupRelSoap
					[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceNotificationTemplateCommerceAccountGroupRelSoap[]
		toSoapModels(
			List<CommerceNotificationTemplateCommerceAccountGroupRel> models) {

		List<CommerceNotificationTemplateCommerceAccountGroupRelSoap>
			soapModels =
				new ArrayList
					<CommerceNotificationTemplateCommerceAccountGroupRelSoap>(
						models.size());

		for (CommerceNotificationTemplateCommerceAccountGroupRel model :
				models) {

			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceNotificationTemplateCommerceAccountGroupRelSoap
				[soapModels.size()]);
	}

	public CommerceNotificationTemplateCommerceAccountGroupRelSoap() {
	}

	public long getPrimaryKey() {
		return _commerceNotificationTemplateCommerceAccountGroupRelId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceNotificationTemplateCommerceAccountGroupRelId(pk);
	}

	public long getCommerceNotificationTemplateCommerceAccountGroupRelId() {
		return _commerceNotificationTemplateCommerceAccountGroupRelId;
	}

	public void setCommerceNotificationTemplateCommerceAccountGroupRelId(
		long commerceNotificationTemplateCommerceAccountGroupRelId) {

		_commerceNotificationTemplateCommerceAccountGroupRelId =
			commerceNotificationTemplateCommerceAccountGroupRelId;
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

	public long getCommerceNotificationTemplateId() {
		return _commerceNotificationTemplateId;
	}

	public void setCommerceNotificationTemplateId(
		long commerceNotificationTemplateId) {

		_commerceNotificationTemplateId = commerceNotificationTemplateId;
	}

	public long getCommerceAccountGroupId() {
		return _commerceAccountGroupId;
	}

	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		_commerceAccountGroupId = commerceAccountGroupId;
	}

	private long _commerceNotificationTemplateCommerceAccountGroupRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceNotificationTemplateId;
	private long _commerceAccountGroupId;

}