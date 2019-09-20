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

package com.liferay.commerce.wish.list.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.wish.list.service.http.CommerceWishListItemServiceSoap}.
 *
 * @author Andrea Di Giorgi
 * @generated
 */
public class CommerceWishListItemSoap implements Serializable {

	public static CommerceWishListItemSoap toSoapModel(
		CommerceWishListItem model) {

		CommerceWishListItemSoap soapModel = new CommerceWishListItemSoap();

		soapModel.setCommerceWishListItemId(model.getCommerceWishListItemId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceWishListId(model.getCommerceWishListId());
		soapModel.setCPInstanceUuid(model.getCPInstanceUuid());
		soapModel.setCProductId(model.getCProductId());
		soapModel.setJson(model.getJson());

		return soapModel;
	}

	public static CommerceWishListItemSoap[] toSoapModels(
		CommerceWishListItem[] models) {

		CommerceWishListItemSoap[] soapModels =
			new CommerceWishListItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceWishListItemSoap[][] toSoapModels(
		CommerceWishListItem[][] models) {

		CommerceWishListItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommerceWishListItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceWishListItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceWishListItemSoap[] toSoapModels(
		List<CommerceWishListItem> models) {

		List<CommerceWishListItemSoap> soapModels =
			new ArrayList<CommerceWishListItemSoap>(models.size());

		for (CommerceWishListItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceWishListItemSoap[soapModels.size()]);
	}

	public CommerceWishListItemSoap() {
	}

	public long getPrimaryKey() {
		return _commerceWishListItemId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceWishListItemId(pk);
	}

	public long getCommerceWishListItemId() {
		return _commerceWishListItemId;
	}

	public void setCommerceWishListItemId(long commerceWishListItemId) {
		_commerceWishListItemId = commerceWishListItemId;
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

	public long getCommerceWishListId() {
		return _commerceWishListId;
	}

	public void setCommerceWishListId(long commerceWishListId) {
		_commerceWishListId = commerceWishListId;
	}

	public String getCPInstanceUuid() {
		return _CPInstanceUuid;
	}

	public void setCPInstanceUuid(String CPInstanceUuid) {
		_CPInstanceUuid = CPInstanceUuid;
	}

	public long getCProductId() {
		return _CProductId;
	}

	public void setCProductId(long CProductId) {
		_CProductId = CProductId;
	}

	public String getJson() {
		return _json;
	}

	public void setJson(String json) {
		_json = json;
	}

	private long _commerceWishListItemId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceWishListId;
	private String _CPInstanceUuid;
	private long _CProductId;
	private String _json;

}