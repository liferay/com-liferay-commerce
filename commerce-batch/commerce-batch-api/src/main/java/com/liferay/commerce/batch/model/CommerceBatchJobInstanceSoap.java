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

package com.liferay.commerce.batch.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.batch.service.http.CommerceBatchJobInstanceServiceSoap}.
 *
 * @author Matija Petanjek
 * @see com.liferay.commerce.batch.service.http.CommerceBatchJobInstanceServiceSoap
 * @generated
 */
@ProviderType
public class CommerceBatchJobInstanceSoap implements Serializable {
	public static CommerceBatchJobInstanceSoap toSoapModel(
		CommerceBatchJobInstance model) {
		CommerceBatchJobInstanceSoap soapModel = new CommerceBatchJobInstanceSoap();

		soapModel.setCommerceBatchJobInstanceId(model.getCommerceBatchJobInstanceId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBatchJobName(model.getBatchJobName());
		soapModel.setKey(model.getKey());

		return soapModel;
	}

	public static CommerceBatchJobInstanceSoap[] toSoapModels(
		CommerceBatchJobInstance[] models) {
		CommerceBatchJobInstanceSoap[] soapModels = new CommerceBatchJobInstanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobInstanceSoap[][] toSoapModels(
		CommerceBatchJobInstance[][] models) {
		CommerceBatchJobInstanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceBatchJobInstanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceBatchJobInstanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceBatchJobInstanceSoap[] toSoapModels(
		List<CommerceBatchJobInstance> models) {
		List<CommerceBatchJobInstanceSoap> soapModels = new ArrayList<CommerceBatchJobInstanceSoap>(models.size());

		for (CommerceBatchJobInstance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceBatchJobInstanceSoap[soapModels.size()]);
	}

	public CommerceBatchJobInstanceSoap() {
	}

	public long getPrimaryKey() {
		return _commerceBatchJobInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceBatchJobInstanceId(pk);
	}

	public long getCommerceBatchJobInstanceId() {
		return _commerceBatchJobInstanceId;
	}

	public void setCommerceBatchJobInstanceId(long commerceBatchJobInstanceId) {
		_commerceBatchJobInstanceId = commerceBatchJobInstanceId;
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

	public String getBatchJobName() {
		return _batchJobName;
	}

	public void setBatchJobName(String batchJobName) {
		_batchJobName = batchJobName;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	private long _commerceBatchJobInstanceId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _batchJobName;
	private String _key;
}