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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPOptionCategory;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPOptionCategory in entity cache.
 *
 * @author Marco Leo
 * @see CPOptionCategory
 * @generated
 */
@ProviderType
public class CPOptionCategoryCacheModel implements CacheModel<CPOptionCategory>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPOptionCategoryCacheModel)) {
			return false;
		}

		CPOptionCategoryCacheModel cpOptionCategoryCacheModel = (CPOptionCategoryCacheModel)obj;

		if (CPOptionCategoryId == cpOptionCategoryCacheModel.CPOptionCategoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPOptionCategoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPOptionCategoryId=");
		sb.append(CPOptionCategoryId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", key=");
		sb.append(key);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPOptionCategory toEntityModel() {
		CPOptionCategoryImpl cpOptionCategoryImpl = new CPOptionCategoryImpl();

		if (uuid == null) {
			cpOptionCategoryImpl.setUuid("");
		}
		else {
			cpOptionCategoryImpl.setUuid(uuid);
		}

		cpOptionCategoryImpl.setCPOptionCategoryId(CPOptionCategoryId);
		cpOptionCategoryImpl.setGroupId(groupId);
		cpOptionCategoryImpl.setCompanyId(companyId);
		cpOptionCategoryImpl.setUserId(userId);

		if (userName == null) {
			cpOptionCategoryImpl.setUserName("");
		}
		else {
			cpOptionCategoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpOptionCategoryImpl.setCreateDate(null);
		}
		else {
			cpOptionCategoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpOptionCategoryImpl.setModifiedDate(null);
		}
		else {
			cpOptionCategoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			cpOptionCategoryImpl.setTitle("");
		}
		else {
			cpOptionCategoryImpl.setTitle(title);
		}

		if (description == null) {
			cpOptionCategoryImpl.setDescription("");
		}
		else {
			cpOptionCategoryImpl.setDescription(description);
		}

		cpOptionCategoryImpl.setPriority(priority);

		if (key == null) {
			cpOptionCategoryImpl.setKey("");
		}
		else {
			cpOptionCategoryImpl.setKey(key);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			cpOptionCategoryImpl.setLastPublishDate(null);
		}
		else {
			cpOptionCategoryImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		cpOptionCategoryImpl.resetOriginalValues();

		return cpOptionCategoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPOptionCategoryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		priority = objectInput.readDouble();
		key = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(CPOptionCategoryId);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeDouble(priority);

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long CPOptionCategoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public double priority;
	public String key;
	public long lastPublishDate;
}