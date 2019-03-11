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

import com.liferay.commerce.batch.model.CommerceBatchJob;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBatchJob in entity cache.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJob
 * @generated
 */
@ProviderType
public class CommerceBatchJobCacheModel implements CacheModel<CommerceBatchJob>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobCacheModel)) {
			return false;
		}

		CommerceBatchJobCacheModel commerceBatchJobCacheModel = (CommerceBatchJobCacheModel)obj;

		if (commerceBatchJobId == commerceBatchJobCacheModel.commerceBatchJobId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBatchJobId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceBatchJobId=");
		sb.append(commerceBatchJobId);
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
		sb.append(", callbackURL=");
		sb.append(callbackURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBatchJob toEntityModel() {
		CommerceBatchJobImpl commerceBatchJobImpl = new CommerceBatchJobImpl();

		commerceBatchJobImpl.setCommerceBatchJobId(commerceBatchJobId);

		if (createDate == Long.MIN_VALUE) {
			commerceBatchJobImpl.setCreateDate(null);
		}
		else {
			commerceBatchJobImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBatchJobImpl.setModifiedDate(null);
		}
		else {
			commerceBatchJobImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (key == null) {
			commerceBatchJobImpl.setKey("");
		}
		else {
			commerceBatchJobImpl.setKey(key);
		}

		if (name == null) {
			commerceBatchJobImpl.setName("");
		}
		else {
			commerceBatchJobImpl.setName(name);
		}

		if (startTime == Long.MIN_VALUE) {
			commerceBatchJobImpl.setStartTime(null);
		}
		else {
			commerceBatchJobImpl.setStartTime(new Date(startTime));
		}

		if (endTime == Long.MIN_VALUE) {
			commerceBatchJobImpl.setEndTime(null);
		}
		else {
			commerceBatchJobImpl.setEndTime(new Date(endTime));
		}

		if (status == null) {
			commerceBatchJobImpl.setStatus("");
		}
		else {
			commerceBatchJobImpl.setStatus(status);
		}

		if (callbackURL == null) {
			commerceBatchJobImpl.setCallbackURL("");
		}
		else {
			commerceBatchJobImpl.setCallbackURL(callbackURL);
		}

		commerceBatchJobImpl.resetOriginalValues();

		return commerceBatchJobImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBatchJobId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		key = objectInput.readUTF();
		name = objectInput.readUTF();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
		status = objectInput.readUTF();
		callbackURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBatchJobId);
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

		if (callbackURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(callbackURL);
		}
	}

	public long commerceBatchJobId;
	public long createDate;
	public long modifiedDate;
	public String key;
	public String name;
	public long startTime;
	public long endTime;
	public String status;
	public String callbackURL;
}