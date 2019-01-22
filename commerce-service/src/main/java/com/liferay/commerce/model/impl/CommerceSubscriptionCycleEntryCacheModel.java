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

import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceSubscriptionCycleEntry in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntry
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryCacheModel implements CacheModel<CommerceSubscriptionCycleEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionCycleEntryCacheModel)) {
			return false;
		}

		CommerceSubscriptionCycleEntryCacheModel commerceSubscriptionCycleEntryCacheModel =
			(CommerceSubscriptionCycleEntryCacheModel)obj;

		if (commerceSubscriptionCycleEntryId == commerceSubscriptionCycleEntryCacheModel.commerceSubscriptionCycleEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceSubscriptionCycleEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commerceSubscriptionCycleEntryId=");
		sb.append(commerceSubscriptionCycleEntryId);
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
		sb.append(", commerceOrderItemId=");
		sb.append(commerceOrderItemId);
		sb.append(", commerceSubscriptionEntryId=");
		sb.append(commerceSubscriptionEntryId);
		sb.append(", renew=");
		sb.append(renew);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceSubscriptionCycleEntry toEntityModel() {
		CommerceSubscriptionCycleEntryImpl commerceSubscriptionCycleEntryImpl = new CommerceSubscriptionCycleEntryImpl();

		if (uuid == null) {
			commerceSubscriptionCycleEntryImpl.setUuid("");
		}
		else {
			commerceSubscriptionCycleEntryImpl.setUuid(uuid);
		}

		commerceSubscriptionCycleEntryImpl.setCommerceSubscriptionCycleEntryId(commerceSubscriptionCycleEntryId);
		commerceSubscriptionCycleEntryImpl.setGroupId(groupId);
		commerceSubscriptionCycleEntryImpl.setCompanyId(companyId);
		commerceSubscriptionCycleEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceSubscriptionCycleEntryImpl.setUserName("");
		}
		else {
			commerceSubscriptionCycleEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceSubscriptionCycleEntryImpl.setCreateDate(null);
		}
		else {
			commerceSubscriptionCycleEntryImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceSubscriptionCycleEntryImpl.setModifiedDate(null);
		}
		else {
			commerceSubscriptionCycleEntryImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		commerceSubscriptionCycleEntryImpl.setCommerceOrderItemId(commerceOrderItemId);
		commerceSubscriptionCycleEntryImpl.setCommerceSubscriptionEntryId(commerceSubscriptionEntryId);
		commerceSubscriptionCycleEntryImpl.setRenew(renew);

		commerceSubscriptionCycleEntryImpl.resetOriginalValues();

		return commerceSubscriptionCycleEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commerceSubscriptionCycleEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceOrderItemId = objectInput.readLong();

		commerceSubscriptionEntryId = objectInput.readLong();

		renew = objectInput.readBoolean();
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

		objectOutput.writeLong(commerceSubscriptionCycleEntryId);

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

		objectOutput.writeLong(commerceOrderItemId);

		objectOutput.writeLong(commerceSubscriptionEntryId);

		objectOutput.writeBoolean(renew);
	}

	public String uuid;
	public long commerceSubscriptionCycleEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceOrderItemId;
	public long commerceSubscriptionEntryId;
	public boolean renew;
}