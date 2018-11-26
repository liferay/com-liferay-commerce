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

import com.liferay.commerce.model.CommerceSubscriptionEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceSubscriptionEntry in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntry
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntryCacheModel implements CacheModel<CommerceSubscriptionEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionEntryCacheModel)) {
			return false;
		}

		CommerceSubscriptionEntryCacheModel commerceSubscriptionEntryCacheModel = (CommerceSubscriptionEntryCacheModel)obj;

		if (commerceSubscriptionEntryId == commerceSubscriptionEntryCacheModel.commerceSubscriptionEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceSubscriptionEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commerceSubscriptionEntryId=");
		sb.append(commerceSubscriptionEntryId);
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
		sb.append(", CPInstanceId=");
		sb.append(CPInstanceId);
		sb.append(", commerceOrderItemId=");
		sb.append(commerceOrderItemId);
		sb.append(", subscriptionCycleLength=");
		sb.append(subscriptionCycleLength);
		sb.append(", subscriptionCyclePeriod=");
		sb.append(subscriptionCyclePeriod);
		sb.append(", maxSubscriptionCyclesNumber=");
		sb.append(maxSubscriptionCyclesNumber);
		sb.append(", active=");
		sb.append(active);
		sb.append(", nextIterationDate=");
		sb.append(nextIterationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceSubscriptionEntry toEntityModel() {
		CommerceSubscriptionEntryImpl commerceSubscriptionEntryImpl = new CommerceSubscriptionEntryImpl();

		if (uuid == null) {
			commerceSubscriptionEntryImpl.setUuid("");
		}
		else {
			commerceSubscriptionEntryImpl.setUuid(uuid);
		}

		commerceSubscriptionEntryImpl.setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
		commerceSubscriptionEntryImpl.setGroupId(groupId);
		commerceSubscriptionEntryImpl.setCompanyId(companyId);
		commerceSubscriptionEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceSubscriptionEntryImpl.setUserName("");
		}
		else {
			commerceSubscriptionEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setCreateDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setModifiedDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceSubscriptionEntryImpl.setCPInstanceId(CPInstanceId);
		commerceSubscriptionEntryImpl.setCommerceOrderItemId(commerceOrderItemId);
		commerceSubscriptionEntryImpl.setSubscriptionCycleLength(subscriptionCycleLength);

		if (subscriptionCyclePeriod == null) {
			commerceSubscriptionEntryImpl.setSubscriptionCyclePeriod("");
		}
		else {
			commerceSubscriptionEntryImpl.setSubscriptionCyclePeriod(subscriptionCyclePeriod);
		}

		commerceSubscriptionEntryImpl.setMaxSubscriptionCyclesNumber(maxSubscriptionCyclesNumber);
		commerceSubscriptionEntryImpl.setActive(active);

		if (nextIterationDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setNextIterationDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setNextIterationDate(new Date(
					nextIterationDate));
		}

		commerceSubscriptionEntryImpl.resetOriginalValues();

		return commerceSubscriptionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commerceSubscriptionEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPInstanceId = objectInput.readLong();

		commerceOrderItemId = objectInput.readLong();

		subscriptionCycleLength = objectInput.readLong();
		subscriptionCyclePeriod = objectInput.readUTF();

		maxSubscriptionCyclesNumber = objectInput.readLong();

		active = objectInput.readBoolean();
		nextIterationDate = objectInput.readLong();
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

		objectOutput.writeLong(commerceSubscriptionEntryId);

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

		objectOutput.writeLong(CPInstanceId);

		objectOutput.writeLong(commerceOrderItemId);

		objectOutput.writeLong(subscriptionCycleLength);

		if (subscriptionCyclePeriod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subscriptionCyclePeriod);
		}

		objectOutput.writeLong(maxSubscriptionCyclesNumber);

		objectOutput.writeBoolean(active);
		objectOutput.writeLong(nextIterationDate);
	}

	public String uuid;
	public long commerceSubscriptionEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPInstanceId;
	public long commerceOrderItemId;
	public long subscriptionCycleLength;
	public String subscriptionCyclePeriod;
	public long maxSubscriptionCyclesNumber;
	public boolean active;
	public long nextIterationDate;
}