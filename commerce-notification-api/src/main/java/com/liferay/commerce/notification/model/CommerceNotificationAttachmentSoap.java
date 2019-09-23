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
 * This class is used by SOAP remote services.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceNotificationAttachmentSoap implements Serializable {

	public static CommerceNotificationAttachmentSoap toSoapModel(
		CommerceNotificationAttachment model) {

		CommerceNotificationAttachmentSoap soapModel =
			new CommerceNotificationAttachmentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommerceNotificationAttachmentId(
			model.getCommerceNotificationAttachmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceNotificationQueueEntryId(
			model.getCommerceNotificationQueueEntryId());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setDeleteOnSend(model.isDeleteOnSend());

		return soapModel;
	}

	public static CommerceNotificationAttachmentSoap[] toSoapModels(
		CommerceNotificationAttachment[] models) {

		CommerceNotificationAttachmentSoap[] soapModels =
			new CommerceNotificationAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceNotificationAttachmentSoap[][] toSoapModels(
		CommerceNotificationAttachment[][] models) {

		CommerceNotificationAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceNotificationAttachmentSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceNotificationAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceNotificationAttachmentSoap[] toSoapModels(
		List<CommerceNotificationAttachment> models) {

		List<CommerceNotificationAttachmentSoap> soapModels =
			new ArrayList<CommerceNotificationAttachmentSoap>(models.size());

		for (CommerceNotificationAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceNotificationAttachmentSoap[soapModels.size()]);
	}

	public CommerceNotificationAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _commerceNotificationAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceNotificationAttachmentId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommerceNotificationAttachmentId() {
		return _commerceNotificationAttachmentId;
	}

	public void setCommerceNotificationAttachmentId(
		long commerceNotificationAttachmentId) {

		_commerceNotificationAttachmentId = commerceNotificationAttachmentId;
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

	public long getCommerceNotificationQueueEntryId() {
		return _commerceNotificationQueueEntryId;
	}

	public void setCommerceNotificationQueueEntryId(
		long commerceNotificationQueueEntryId) {

		_commerceNotificationQueueEntryId = commerceNotificationQueueEntryId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public boolean getDeleteOnSend() {
		return _deleteOnSend;
	}

	public boolean isDeleteOnSend() {
		return _deleteOnSend;
	}

	public void setDeleteOnSend(boolean deleteOnSend) {
		_deleteOnSend = deleteOnSend;
	}

	private String _uuid;
	private long _commerceNotificationAttachmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceNotificationQueueEntryId;
	private long _fileEntryId;
	private boolean _deleteOnSend;

}