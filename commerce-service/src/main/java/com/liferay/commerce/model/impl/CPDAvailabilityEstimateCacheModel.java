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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CPDAvailabilityEstimate;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPDAvailabilityEstimate in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @see CPDAvailabilityEstimate
 * @generated
 */
@ProviderType
public class CPDAvailabilityEstimateCacheModel implements CacheModel<CPDAvailabilityEstimate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDAvailabilityEstimateCacheModel)) {
			return false;
		}

		CPDAvailabilityEstimateCacheModel cpdAvailabilityEstimateCacheModel = (CPDAvailabilityEstimateCacheModel)obj;

		if (CPDAvailabilityEstimateId == cpdAvailabilityEstimateCacheModel.CPDAvailabilityEstimateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPDAvailabilityEstimateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPDAvailabilityEstimateId=");
		sb.append(CPDAvailabilityEstimateId);
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
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", commerceAvailabilityEstimateId=");
		sb.append(commerceAvailabilityEstimateId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDAvailabilityEstimate toEntityModel() {
		CPDAvailabilityEstimateImpl cpdAvailabilityEstimateImpl = new CPDAvailabilityEstimateImpl();

		if (uuid == null) {
			cpdAvailabilityEstimateImpl.setUuid("");
		}
		else {
			cpdAvailabilityEstimateImpl.setUuid(uuid);
		}

		cpdAvailabilityEstimateImpl.setCPDAvailabilityEstimateId(CPDAvailabilityEstimateId);
		cpdAvailabilityEstimateImpl.setGroupId(groupId);
		cpdAvailabilityEstimateImpl.setCompanyId(companyId);
		cpdAvailabilityEstimateImpl.setUserId(userId);

		if (userName == null) {
			cpdAvailabilityEstimateImpl.setUserName("");
		}
		else {
			cpdAvailabilityEstimateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpdAvailabilityEstimateImpl.setCreateDate(null);
		}
		else {
			cpdAvailabilityEstimateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpdAvailabilityEstimateImpl.setModifiedDate(null);
		}
		else {
			cpdAvailabilityEstimateImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpdAvailabilityEstimateImpl.setCPDefinitionId(CPDefinitionId);
		cpdAvailabilityEstimateImpl.setCommerceAvailabilityEstimateId(commerceAvailabilityEstimateId);

		if (lastPublishDate == Long.MIN_VALUE) {
			cpdAvailabilityEstimateImpl.setLastPublishDate(null);
		}
		else {
			cpdAvailabilityEstimateImpl.setLastPublishDate(new Date(
					lastPublishDate));
		}

		cpdAvailabilityEstimateImpl.resetOriginalValues();

		return cpdAvailabilityEstimateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPDAvailabilityEstimateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();

		commerceAvailabilityEstimateId = objectInput.readLong();
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

		objectOutput.writeLong(CPDAvailabilityEstimateId);

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

		objectOutput.writeLong(CPDefinitionId);

		objectOutput.writeLong(commerceAvailabilityEstimateId);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long CPDAvailabilityEstimateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPDefinitionId;
	public long commerceAvailabilityEstimateId;
	public long lastPublishDate;
}