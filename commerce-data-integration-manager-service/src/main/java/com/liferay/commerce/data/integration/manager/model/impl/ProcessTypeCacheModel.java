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

import com.liferay.commerce.data.integration.manager.model.ProcessType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessType
 * @generated
 */
@ProviderType
public class ProcessTypeCacheModel implements CacheModel<ProcessType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessTypeCacheModel)) {
			return false;
		}

		ProcessTypeCacheModel processTypeCacheModel = (ProcessTypeCacheModel)obj;

		if (processTypeId == processTypeCacheModel.processTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processTypeId=");
		sb.append(processTypeId);
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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessType toEntityModel() {
		ProcessTypeImpl processTypeImpl = new ProcessTypeImpl();

		if (uuid == null) {
			processTypeImpl.setUuid("");
		}
		else {
			processTypeImpl.setUuid(uuid);
		}

		processTypeImpl.setProcessTypeId(processTypeId);
		processTypeImpl.setGroupId(groupId);
		processTypeImpl.setCompanyId(companyId);
		processTypeImpl.setUserId(userId);

		if (userName == null) {
			processTypeImpl.setUserName("");
		}
		else {
			processTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processTypeImpl.setCreateDate(null);
		}
		else {
			processTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processTypeImpl.setModifiedDate(null);
		}
		else {
			processTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			processTypeImpl.setName("");
		}
		else {
			processTypeImpl.setName(name);
		}

		processTypeImpl.resetOriginalValues();

		return processTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
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

		objectOutput.writeLong(processTypeId);

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
	}

	public String uuid;
	public long processTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
}