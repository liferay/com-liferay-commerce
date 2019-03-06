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

import com.liferay.commerce.batch.model.CommerceBatchEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBatchEntry in entity cache.
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntry
 * @generated
 */
@ProviderType
public class CommerceBatchEntryCacheModel implements CacheModel<CommerceBatchEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchEntryCacheModel)) {
			return false;
		}

		CommerceBatchEntryCacheModel commerceBatchEntryCacheModel = (CommerceBatchEntryCacheModel)obj;

		if (commerceBatchEntryId == commerceBatchEntryCacheModel.commerceBatchEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBatchEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{commerceBatchEntryId=");
		sb.append(commerceBatchEntryId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", key=");
		sb.append(key);
		sb.append(", name=");
		sb.append(name);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBatchEntry toEntityModel() {
		CommerceBatchEntryImpl commerceBatchEntryImpl = new CommerceBatchEntryImpl();

		commerceBatchEntryImpl.setCommerceBatchEntryId(commerceBatchEntryId);

		if (createDate == Long.MIN_VALUE) {
			commerceBatchEntryImpl.setCreateDate(null);
		}
		else {
			commerceBatchEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBatchEntryImpl.setModifiedDate(null);
		}
		else {
			commerceBatchEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (key == null) {
			commerceBatchEntryImpl.setKey("");
		}
		else {
			commerceBatchEntryImpl.setKey(key);
		}

		if (name == null) {
			commerceBatchEntryImpl.setName("");
		}
		else {
			commerceBatchEntryImpl.setName(name);
		}

		if (startTime == Long.MIN_VALUE) {
			commerceBatchEntryImpl.setStartTime(null);
		}
		else {
			commerceBatchEntryImpl.setStartTime(new Date(startTime));
		}

		if (endTime == Long.MIN_VALUE) {
			commerceBatchEntryImpl.setEndTime(null);
		}
		else {
			commerceBatchEntryImpl.setEndTime(new Date(endTime));
		}

		if (status == null) {
			commerceBatchEntryImpl.setStatus("");
		}
		else {
			commerceBatchEntryImpl.setStatus(status);
		}

		commerceBatchEntryImpl.resetOriginalValues();

		return commerceBatchEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBatchEntryId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		key = objectInput.readUTF();
		name = objectInput.readUTF();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBatchEntryId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(startTime);
		objectOutput.writeLong(endTime);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long commerceBatchEntryId;
	public long createDate;
	public long modifiedDate;
	public String key;
	public String name;
	public long startTime;
	public long endTime;
	public String status;
}