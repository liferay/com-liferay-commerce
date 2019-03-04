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

package com.liferay.commerce.batch.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.batch.model.CommerceBatchJobExecution;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBatchJobExecution in entity cache.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecution
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionCacheModel implements CacheModel<CommerceBatchJobExecution>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobExecutionCacheModel)) {
			return false;
		}

		CommerceBatchJobExecutionCacheModel commerceBatchJobExecutionCacheModel = (CommerceBatchJobExecutionCacheModel)obj;

		if (commerceBatchJobExecutionId == commerceBatchJobExecutionCacheModel.commerceBatchJobExecutionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBatchJobExecutionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceBatchJobExecutionId=");
		sb.append(commerceBatchJobExecutionId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", commerceBatchJobInstanceId=");
		sb.append(commerceBatchJobInstanceId);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", status=");
		sb.append(status);
		sb.append(", exitCode=");
		sb.append(exitCode);
		sb.append(", exitMessage=");
		sb.append(exitMessage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBatchJobExecution toEntityModel() {
		CommerceBatchJobExecutionImpl commerceBatchJobExecutionImpl = new CommerceBatchJobExecutionImpl();

		commerceBatchJobExecutionImpl.setCommerceBatchJobExecutionId(commerceBatchJobExecutionId);

		if (createDate == Long.MIN_VALUE) {
			commerceBatchJobExecutionImpl.setCreateDate(null);
		}
		else {
			commerceBatchJobExecutionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBatchJobExecutionImpl.setModifiedDate(null);
		}
		else {
			commerceBatchJobExecutionImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceBatchJobExecutionImpl.setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);

		if (startTime == Long.MIN_VALUE) {
			commerceBatchJobExecutionImpl.setStartTime(null);
		}
		else {
			commerceBatchJobExecutionImpl.setStartTime(new Date(startTime));
		}

		if (endTime == Long.MIN_VALUE) {
			commerceBatchJobExecutionImpl.setEndTime(null);
		}
		else {
			commerceBatchJobExecutionImpl.setEndTime(new Date(endTime));
		}

		if (status == null) {
			commerceBatchJobExecutionImpl.setStatus("");
		}
		else {
			commerceBatchJobExecutionImpl.setStatus(status);
		}

		if (exitCode == null) {
			commerceBatchJobExecutionImpl.setExitCode("");
		}
		else {
			commerceBatchJobExecutionImpl.setExitCode(exitCode);
		}

		if (exitMessage == null) {
			commerceBatchJobExecutionImpl.setExitMessage("");
		}
		else {
			commerceBatchJobExecutionImpl.setExitMessage(exitMessage);
		}

		commerceBatchJobExecutionImpl.resetOriginalValues();

		return commerceBatchJobExecutionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBatchJobExecutionId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceBatchJobInstanceId = objectInput.readLong();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
		status = objectInput.readUTF();
		exitCode = objectInput.readUTF();
		exitMessage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBatchJobExecutionId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(commerceBatchJobInstanceId);
		objectOutput.writeLong(startTime);
		objectOutput.writeLong(endTime);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (exitCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exitCode);
		}

		if (exitMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exitMessage);
		}
	}

	public long commerceBatchJobExecutionId;
	public long createDate;
	public long modifiedDate;
	public long commerceBatchJobInstanceId;
	public long startTime;
	public long endTime;
	public String status;
	public String exitCode;
	public String exitMessage;
}