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

import com.liferay.commerce.batch.model.CommerceBatchJobInstance;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBatchJobInstance in entity cache.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobInstance
 * @generated
 */
@ProviderType
public class CommerceBatchJobInstanceCacheModel implements CacheModel<CommerceBatchJobInstance>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBatchJobInstanceCacheModel)) {
			return false;
		}

		CommerceBatchJobInstanceCacheModel commerceBatchJobInstanceCacheModel = (CommerceBatchJobInstanceCacheModel)obj;

		if (commerceBatchJobInstanceId == commerceBatchJobInstanceCacheModel.commerceBatchJobInstanceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBatchJobInstanceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{commerceBatchJobInstanceId=");
		sb.append(commerceBatchJobInstanceId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", batchJobName=");
		sb.append(batchJobName);
		sb.append(", key=");
		sb.append(key);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBatchJobInstance toEntityModel() {
		CommerceBatchJobInstanceImpl commerceBatchJobInstanceImpl = new CommerceBatchJobInstanceImpl();

		commerceBatchJobInstanceImpl.setCommerceBatchJobInstanceId(commerceBatchJobInstanceId);

		if (createDate == Long.MIN_VALUE) {
			commerceBatchJobInstanceImpl.setCreateDate(null);
		}
		else {
			commerceBatchJobInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBatchJobInstanceImpl.setModifiedDate(null);
		}
		else {
			commerceBatchJobInstanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (batchJobName == null) {
			commerceBatchJobInstanceImpl.setBatchJobName("");
		}
		else {
			commerceBatchJobInstanceImpl.setBatchJobName(batchJobName);
		}

		if (key == null) {
			commerceBatchJobInstanceImpl.setKey("");
		}
		else {
			commerceBatchJobInstanceImpl.setKey(key);
		}

		commerceBatchJobInstanceImpl.resetOriginalValues();

		return commerceBatchJobInstanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBatchJobInstanceId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		batchJobName = objectInput.readUTF();
		key = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBatchJobInstanceId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (batchJobName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(batchJobName);
		}

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}
	}

	public long commerceBatchJobInstanceId;
	public long createDate;
	public long modifiedDate;
	public String batchJobName;
	public String key;
}