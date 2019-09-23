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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPMeasurementUnit in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CPMeasurementUnitCacheModel
	implements CacheModel<CPMeasurementUnit>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPMeasurementUnitCacheModel)) {
			return false;
		}

		CPMeasurementUnitCacheModel cpMeasurementUnitCacheModel =
			(CPMeasurementUnitCacheModel)obj;

		if (CPMeasurementUnitId ==
				cpMeasurementUnitCacheModel.CPMeasurementUnitId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPMeasurementUnitId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPMeasurementUnitId=");
		sb.append(CPMeasurementUnitId);
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
		sb.append(", key=");
		sb.append(key);
		sb.append(", rate=");
		sb.append(rate);
		sb.append(", primary=");
		sb.append(primary);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", type=");
		sb.append(type);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPMeasurementUnit toEntityModel() {
		CPMeasurementUnitImpl cpMeasurementUnitImpl =
			new CPMeasurementUnitImpl();

		if (uuid == null) {
			cpMeasurementUnitImpl.setUuid("");
		}
		else {
			cpMeasurementUnitImpl.setUuid(uuid);
		}

		cpMeasurementUnitImpl.setCPMeasurementUnitId(CPMeasurementUnitId);
		cpMeasurementUnitImpl.setGroupId(groupId);
		cpMeasurementUnitImpl.setCompanyId(companyId);
		cpMeasurementUnitImpl.setUserId(userId);

		if (userName == null) {
			cpMeasurementUnitImpl.setUserName("");
		}
		else {
			cpMeasurementUnitImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpMeasurementUnitImpl.setCreateDate(null);
		}
		else {
			cpMeasurementUnitImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpMeasurementUnitImpl.setModifiedDate(null);
		}
		else {
			cpMeasurementUnitImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			cpMeasurementUnitImpl.setName("");
		}
		else {
			cpMeasurementUnitImpl.setName(name);
		}

		if (key == null) {
			cpMeasurementUnitImpl.setKey("");
		}
		else {
			cpMeasurementUnitImpl.setKey(key);
		}

		cpMeasurementUnitImpl.setRate(rate);
		cpMeasurementUnitImpl.setPrimary(primary);
		cpMeasurementUnitImpl.setPriority(priority);
		cpMeasurementUnitImpl.setType(type);

		if (lastPublishDate == Long.MIN_VALUE) {
			cpMeasurementUnitImpl.setLastPublishDate(null);
		}
		else {
			cpMeasurementUnitImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		cpMeasurementUnitImpl.resetOriginalValues();

		return cpMeasurementUnitImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPMeasurementUnitId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		key = objectInput.readUTF();

		rate = objectInput.readDouble();

		primary = objectInput.readBoolean();

		priority = objectInput.readDouble();

		type = objectInput.readInt();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(CPMeasurementUnitId);

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

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeDouble(rate);

		objectOutput.writeBoolean(primary);

		objectOutput.writeDouble(priority);

		objectOutput.writeInt(type);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long CPMeasurementUnitId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String key;
	public double rate;
	public boolean primary;
	public double priority;
	public int type;
	public long lastPublishDate;

}