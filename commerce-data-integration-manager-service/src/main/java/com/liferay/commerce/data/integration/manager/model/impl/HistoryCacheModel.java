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

package com.liferay.commerce.data.integration.manager.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.model.History;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing History in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see History
 * @generated
 */
@ProviderType
public class HistoryCacheModel implements CacheModel<History>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistoryCacheModel)) {
			return false;
		}

		HistoryCacheModel historyCacheModel = (HistoryCacheModel)obj;

		if (historyId == historyCacheModel.historyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, historyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", historyId=");
		sb.append(historyId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", launchType=");
		sb.append(launchType);
		sb.append(", scheduledTaskId=");
		sb.append(scheduledTaskId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", executionType=");
		sb.append(executionType);
		sb.append(", errorLogFileEntryId=");
		sb.append(errorLogFileEntryId);
		sb.append(", runtimeLogFileEntryId=");
		sb.append(runtimeLogFileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public History toEntityModel() {
		HistoryImpl historyImpl = new HistoryImpl();

		if (uuid == null) {
			historyImpl.setUuid("");
		}
		else {
			historyImpl.setUuid(uuid);
		}

		historyImpl.setHistoryId(historyId);
		historyImpl.setGroupId(groupId);
		historyImpl.setCompanyId(companyId);
		historyImpl.setUserId(userId);

		if (userName == null) {
			historyImpl.setUserName("");
		}
		else {
			historyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			historyImpl.setCreateDate(null);
		}
		else {
			historyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			historyImpl.setModifiedDate(null);
		}
		else {
			historyImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (launchType == null) {
			historyImpl.setLaunchType("");
		}
		else {
			historyImpl.setLaunchType(launchType);
		}

		historyImpl.setScheduledTaskId(scheduledTaskId);
		historyImpl.setStatus(status);

		if (startDate == Long.MIN_VALUE) {
			historyImpl.setStartDate(null);
		}
		else {
			historyImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			historyImpl.setEndDate(null);
		}
		else {
			historyImpl.setEndDate(new Date(endDate));
		}

		if (executionType == null) {
			historyImpl.setExecutionType("");
		}
		else {
			historyImpl.setExecutionType(executionType);
		}

		historyImpl.setErrorLogFileEntryId(errorLogFileEntryId);
		historyImpl.setRuntimeLogFileEntryId(runtimeLogFileEntryId);

		historyImpl.resetOriginalValues();

		return historyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		historyId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		launchType = objectInput.readUTF();

		scheduledTaskId = objectInput.readLong();

		status = objectInput.readInt();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		executionType = objectInput.readUTF();

		errorLogFileEntryId = objectInput.readLong();

		runtimeLogFileEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(historyId);

		objectOutput.writeLong(groupId);

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

		if (launchType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(launchType);
		}

		objectOutput.writeLong(scheduledTaskId);

		objectOutput.writeInt(status);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (executionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(executionType);
		}

		objectOutput.writeLong(errorLogFileEntryId);

		objectOutput.writeLong(runtimeLogFileEntryId);
	}

	public String uuid;
	public long historyId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String launchType;
	public long scheduledTaskId;
	public int status;
	public long startDate;
	public long endDate;
	public String executionType;
	public long errorLogFileEntryId;
	public long runtimeLogFileEntryId;
}