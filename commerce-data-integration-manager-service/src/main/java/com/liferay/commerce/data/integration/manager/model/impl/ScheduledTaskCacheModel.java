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

import com.liferay.commerce.data.integration.manager.model.ScheduledTask;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScheduledTask in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTask
 * @generated
 */
@ProviderType
public class ScheduledTaskCacheModel implements CacheModel<ScheduledTask>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduledTaskCacheModel)) {
			return false;
		}

		ScheduledTaskCacheModel scheduledTaskCacheModel = (ScheduledTaskCacheModel)obj;

		if (scheduledTaskId == scheduledTaskCacheModel.scheduledTaskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, scheduledTaskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", scheduledTaskId=");
		sb.append(scheduledTaskId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", processId=");
		sb.append(processId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", active=");
		sb.append(active);
		sb.append(", runStartDate=");
		sb.append(runStartDate);
		sb.append(", runEndDate=");
		sb.append(runEndDate);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", startHour=");
		sb.append(startHour);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScheduledTask toEntityModel() {
		ScheduledTaskImpl scheduledTaskImpl = new ScheduledTaskImpl();

		if (uuid == null) {
			scheduledTaskImpl.setUuid("");
		}
		else {
			scheduledTaskImpl.setUuid(uuid);
		}

		scheduledTaskImpl.setScheduledTaskId(scheduledTaskId);
		scheduledTaskImpl.setGroupId(groupId);
		scheduledTaskImpl.setCompanyId(companyId);
		scheduledTaskImpl.setUserId(userId);

		if (userName == null) {
			scheduledTaskImpl.setUserName("");
		}
		else {
			scheduledTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scheduledTaskImpl.setCreateDate(null);
		}
		else {
			scheduledTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scheduledTaskImpl.setModifiedDate(null);
		}
		else {
			scheduledTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			scheduledTaskImpl.setName("");
		}
		else {
			scheduledTaskImpl.setName(name);
		}

		if (frequency == null) {
			scheduledTaskImpl.setFrequency("");
		}
		else {
			scheduledTaskImpl.setFrequency(frequency);
		}

		scheduledTaskImpl.setProcessId(processId);
		scheduledTaskImpl.setStatus(status);
		scheduledTaskImpl.setActive(active);

		if (runStartDate == Long.MIN_VALUE) {
			scheduledTaskImpl.setRunStartDate(null);
		}
		else {
			scheduledTaskImpl.setRunStartDate(new Date(runStartDate));
		}

		if (runEndDate == Long.MIN_VALUE) {
			scheduledTaskImpl.setRunEndDate(null);
		}
		else {
			scheduledTaskImpl.setRunEndDate(new Date(runEndDate));
		}

		if (startDate == Long.MIN_VALUE) {
			scheduledTaskImpl.setStartDate(null);
		}
		else {
			scheduledTaskImpl.setStartDate(new Date(startDate));
		}

		if (startHour == null) {
			scheduledTaskImpl.setStartHour("");
		}
		else {
			scheduledTaskImpl.setStartHour(startHour);
		}

		scheduledTaskImpl.setEnabled(enabled);

		scheduledTaskImpl.resetOriginalValues();

		return scheduledTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		scheduledTaskId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		frequency = objectInput.readUTF();

		processId = objectInput.readLong();

		status = objectInput.readInt();

		active = objectInput.readBoolean();
		runStartDate = objectInput.readLong();
		runEndDate = objectInput.readLong();
		startDate = objectInput.readLong();
		startHour = objectInput.readUTF();

		enabled = objectInput.readBoolean();
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

		objectOutput.writeLong(scheduledTaskId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (frequency == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(frequency);
		}

		objectOutput.writeLong(processId);

		objectOutput.writeInt(status);

		objectOutput.writeBoolean(active);
		objectOutput.writeLong(runStartDate);
		objectOutput.writeLong(runEndDate);
		objectOutput.writeLong(startDate);

		if (startHour == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(startHour);
		}

		objectOutput.writeBoolean(enabled);
	}

	public String uuid;
	public long scheduledTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String frequency;
	public long processId;
	public int status;
	public boolean active;
	public long runStartDate;
	public long runEndDate;
	public long startDate;
	public String startHour;
	public boolean enabled;
}