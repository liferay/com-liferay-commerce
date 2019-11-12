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

package com.liferay.commerce.data.integration.model.impl;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceDataIntegrationProcessLog in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceDataIntegrationProcessLogCacheModel
	implements CacheModel<CommerceDataIntegrationProcessLog>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceDataIntegrationProcessLogCacheModel)) {
			return false;
		}

		CommerceDataIntegrationProcessLogCacheModel
			commerceDataIntegrationProcessLogCacheModel =
				(CommerceDataIntegrationProcessLogCacheModel)obj;

		if (commerceDataIntegrationProcessLogId ==
				commerceDataIntegrationProcessLogCacheModel.
					commerceDataIntegrationProcessLogId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceDataIntegrationProcessLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{commerceDataIntegrationProcessLogId=");
		sb.append(commerceDataIntegrationProcessLogId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", CDataIntegrationProcessId=");
		sb.append(CDataIntegrationProcessId);
		sb.append(", error=");
		sb.append(error);
		sb.append(", output=");
		sb.append(output);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDataIntegrationProcessLog toEntityModel() {
		CommerceDataIntegrationProcessLogImpl
			commerceDataIntegrationProcessLogImpl =
				new CommerceDataIntegrationProcessLogImpl();

		commerceDataIntegrationProcessLogImpl.
			setCommerceDataIntegrationProcessLogId(
				commerceDataIntegrationProcessLogId);
		commerceDataIntegrationProcessLogImpl.setCompanyId(companyId);
		commerceDataIntegrationProcessLogImpl.setUserId(userId);

		if (userName == null) {
			commerceDataIntegrationProcessLogImpl.setUserName("");
		}
		else {
			commerceDataIntegrationProcessLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessLogImpl.setCreateDate(null);
		}
		else {
			commerceDataIntegrationProcessLogImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessLogImpl.setModifiedDate(null);
		}
		else {
			commerceDataIntegrationProcessLogImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceDataIntegrationProcessLogImpl.setCDataIntegrationProcessId(
			CDataIntegrationProcessId);

		if (error == null) {
			commerceDataIntegrationProcessLogImpl.setError("");
		}
		else {
			commerceDataIntegrationProcessLogImpl.setError(error);
		}

		if (output == null) {
			commerceDataIntegrationProcessLogImpl.setOutput("");
		}
		else {
			commerceDataIntegrationProcessLogImpl.setOutput(output);
		}

		if (startDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessLogImpl.setStartDate(null);
		}
		else {
			commerceDataIntegrationProcessLogImpl.setStartDate(
				new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessLogImpl.setEndDate(null);
		}
		else {
			commerceDataIntegrationProcessLogImpl.setEndDate(new Date(endDate));
		}

		commerceDataIntegrationProcessLogImpl.setStatus(status);

		commerceDataIntegrationProcessLogImpl.resetOriginalValues();

		return commerceDataIntegrationProcessLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceDataIntegrationProcessLogId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CDataIntegrationProcessId = objectInput.readLong();
		error = objectInput.readUTF();
		output = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceDataIntegrationProcessLogId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(CDataIntegrationProcessId);

		if (error == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(error);
		}

		if (output == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(output);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(status);
	}

	public long commerceDataIntegrationProcessLogId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CDataIntegrationProcessId;
	public String error;
	public String output;
	public long startDate;
	public long endDate;
	public int status;

}