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

package com.liferay.commerce.notification.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.notification.model.CommerceNotificationAttachment;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceNotificationAttachment in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationAttachment
 * @generated
 */
@ProviderType
public class CommerceNotificationAttachmentCacheModel implements CacheModel<CommerceNotificationAttachment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceNotificationAttachmentCacheModel)) {
			return false;
		}

		CommerceNotificationAttachmentCacheModel commerceNotificationAttachmentCacheModel =
			(CommerceNotificationAttachmentCacheModel)obj;

		if (commerceNotificationAttachmentId == commerceNotificationAttachmentCacheModel.commerceNotificationAttachmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceNotificationAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commerceNotificationAttachmentId=");
		sb.append(commerceNotificationAttachmentId);
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
		sb.append(", commerceNotificationQueueEntryId=");
		sb.append(commerceNotificationQueueEntryId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", deleteOnSend=");
		sb.append(deleteOnSend);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceNotificationAttachment toEntityModel() {
		CommerceNotificationAttachmentImpl commerceNotificationAttachmentImpl = new CommerceNotificationAttachmentImpl();

		if (uuid == null) {
			commerceNotificationAttachmentImpl.setUuid("");
		}
		else {
			commerceNotificationAttachmentImpl.setUuid(uuid);
		}

		commerceNotificationAttachmentImpl.setCommerceNotificationAttachmentId(commerceNotificationAttachmentId);
		commerceNotificationAttachmentImpl.setGroupId(groupId);
		commerceNotificationAttachmentImpl.setCompanyId(companyId);
		commerceNotificationAttachmentImpl.setUserId(userId);

		if (userName == null) {
			commerceNotificationAttachmentImpl.setUserName("");
		}
		else {
			commerceNotificationAttachmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceNotificationAttachmentImpl.setCreateDate(null);
		}
		else {
			commerceNotificationAttachmentImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceNotificationAttachmentImpl.setModifiedDate(null);
		}
		else {
			commerceNotificationAttachmentImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		commerceNotificationAttachmentImpl.setCommerceNotificationQueueEntryId(commerceNotificationQueueEntryId);
		commerceNotificationAttachmentImpl.setFileEntryId(fileEntryId);
		commerceNotificationAttachmentImpl.setDeleteOnSend(deleteOnSend);

		commerceNotificationAttachmentImpl.resetOriginalValues();

		return commerceNotificationAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commerceNotificationAttachmentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceNotificationQueueEntryId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		deleteOnSend = objectInput.readBoolean();
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

		objectOutput.writeLong(commerceNotificationAttachmentId);

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

		objectOutput.writeLong(commerceNotificationQueueEntryId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeBoolean(deleteOnSend);
	}

	public String uuid;
	public long commerceNotificationAttachmentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceNotificationQueueEntryId;
	public long fileEntryId;
	public boolean deleteOnSend;
}