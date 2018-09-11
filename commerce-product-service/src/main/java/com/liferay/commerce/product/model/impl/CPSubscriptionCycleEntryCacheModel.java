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

import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPSubscriptionCycleEntry in entity cache.
 *
 * @author Marco Leo
 * @see CPSubscriptionCycleEntry
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntryCacheModel implements CacheModel<CPSubscriptionCycleEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPSubscriptionCycleEntryCacheModel)) {
			return false;
		}

		CPSubscriptionCycleEntryCacheModel cpSubscriptionCycleEntryCacheModel = (CPSubscriptionCycleEntryCacheModel)obj;

		if (CPSubscriptionCycleEntryId == cpSubscriptionCycleEntryCacheModel.CPSubscriptionCycleEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPSubscriptionCycleEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPSubscriptionCycleEntryId=");
		sb.append(CPSubscriptionCycleEntryId);
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
		sb.append(", CPSubscriptionEntryId=");
		sb.append(CPSubscriptionEntryId);
		sb.append(", commerceOrderItemId=");
		sb.append(commerceOrderItemId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPSubscriptionCycleEntry toEntityModel() {
		CPSubscriptionCycleEntryImpl cpSubscriptionCycleEntryImpl = new CPSubscriptionCycleEntryImpl();

		if (uuid == null) {
			cpSubscriptionCycleEntryImpl.setUuid("");
		}
		else {
			cpSubscriptionCycleEntryImpl.setUuid(uuid);
		}

		cpSubscriptionCycleEntryImpl.setCPSubscriptionCycleEntryId(CPSubscriptionCycleEntryId);
		cpSubscriptionCycleEntryImpl.setGroupId(groupId);
		cpSubscriptionCycleEntryImpl.setCompanyId(companyId);
		cpSubscriptionCycleEntryImpl.setUserId(userId);

		if (userName == null) {
			cpSubscriptionCycleEntryImpl.setUserName("");
		}
		else {
			cpSubscriptionCycleEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpSubscriptionCycleEntryImpl.setCreateDate(null);
		}
		else {
			cpSubscriptionCycleEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpSubscriptionCycleEntryImpl.setModifiedDate(null);
		}
		else {
			cpSubscriptionCycleEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpSubscriptionCycleEntryImpl.setCPSubscriptionEntryId(CPSubscriptionEntryId);
		cpSubscriptionCycleEntryImpl.setCommerceOrderItemId(commerceOrderItemId);

		cpSubscriptionCycleEntryImpl.resetOriginalValues();

		return cpSubscriptionCycleEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPSubscriptionCycleEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPSubscriptionEntryId = objectInput.readLong();

		commerceOrderItemId = objectInput.readLong();
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

		objectOutput.writeLong(CPSubscriptionCycleEntryId);

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

		objectOutput.writeLong(CPSubscriptionEntryId);

		objectOutput.writeLong(commerceOrderItemId);
	}

	public String uuid;
	public long CPSubscriptionCycleEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPSubscriptionEntryId;
	public long commerceOrderItemId;
}