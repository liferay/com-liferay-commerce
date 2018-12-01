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

import com.liferay.commerce.data.integration.manager.model.Process;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Process in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Process
 * @generated
 */
@ProviderType
public class ProcessCacheModel implements CacheModel<Process>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessCacheModel)) {
			return false;
		}

		ProcessCacheModel processCacheModel = (ProcessCacheModel)obj;

		if (processId == processCacheModel.processId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processId=");
		sb.append(processId);
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
		sb.append(", version=");
		sb.append(version);
		sb.append(", className=");
		sb.append(className);
		sb.append(", processType=");
		sb.append(processType);
		sb.append(", contextPropertiesFileEntryId=");
		sb.append(contextPropertiesFileEntryId);
		sb.append(", srcArchiveFileEntryId=");
		sb.append(srcArchiveFileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Process toEntityModel() {
		ProcessImpl processImpl = new ProcessImpl();

		if (uuid == null) {
			processImpl.setUuid("");
		}
		else {
			processImpl.setUuid(uuid);
		}

		processImpl.setProcessId(processId);
		processImpl.setGroupId(groupId);
		processImpl.setCompanyId(companyId);
		processImpl.setUserId(userId);

		if (userName == null) {
			processImpl.setUserName("");
		}
		else {
			processImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processImpl.setCreateDate(null);
		}
		else {
			processImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processImpl.setModifiedDate(null);
		}
		else {
			processImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			processImpl.setName("");
		}
		else {
			processImpl.setName(name);
		}

		if (version == null) {
			processImpl.setVersion("");
		}
		else {
			processImpl.setVersion(version);
		}

		if (className == null) {
			processImpl.setClassName("");
		}
		else {
			processImpl.setClassName(className);
		}

		if (processType == null) {
			processImpl.setProcessType("");
		}
		else {
			processImpl.setProcessType(processType);
		}

		processImpl.setContextPropertiesFileEntryId(contextPropertiesFileEntryId);
		processImpl.setSrcArchiveFileEntryId(srcArchiveFileEntryId);

		processImpl.resetOriginalValues();

		return processImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
		className = objectInput.readUTF();
		processType = objectInput.readUTF();

		contextPropertiesFileEntryId = objectInput.readLong();

		srcArchiveFileEntryId = objectInput.readLong();
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

		objectOutput.writeLong(processId);

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

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (processType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(processType);
		}

		objectOutput.writeLong(contextPropertiesFileEntryId);

		objectOutput.writeLong(srcArchiveFileEntryId);
	}

	public String uuid;
	public long processId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String version;
	public String className;
	public String processType;
	public long contextPropertiesFileEntryId;
	public long srcArchiveFileEntryId;
}