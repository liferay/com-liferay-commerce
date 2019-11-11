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

package com.liferay.commerce.bom.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBOMDefinition in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinition
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionCacheModel implements CacheModel<CommerceBOMDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBOMDefinitionCacheModel)) {
			return false;
		}

		CommerceBOMDefinitionCacheModel commerceBOMDefinitionCacheModel = (CommerceBOMDefinitionCacheModel)obj;

		if (commerceBOMDefinitionId == commerceBOMDefinitionCacheModel.commerceBOMDefinitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBOMDefinitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{commerceBOMDefinitionId=");
		sb.append(commerceBOMDefinitionId);
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
		sb.append(", commerceBOMFolderId=");
		sb.append(commerceBOMFolderId);
		sb.append(", CPAttachmentFileEntryId=");
		sb.append(CPAttachmentFileEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", friendlyUrl=");
		sb.append(friendlyUrl);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBOMDefinition toEntityModel() {
		CommerceBOMDefinitionImpl commerceBOMDefinitionImpl = new CommerceBOMDefinitionImpl();

		commerceBOMDefinitionImpl.setCommerceBOMDefinitionId(commerceBOMDefinitionId);
		commerceBOMDefinitionImpl.setCompanyId(companyId);
		commerceBOMDefinitionImpl.setUserId(userId);

		if (userName == null) {
			commerceBOMDefinitionImpl.setUserName("");
		}
		else {
			commerceBOMDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceBOMDefinitionImpl.setCreateDate(null);
		}
		else {
			commerceBOMDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBOMDefinitionImpl.setModifiedDate(null);
		}
		else {
			commerceBOMDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceBOMDefinitionImpl.setCommerceBOMFolderId(commerceBOMFolderId);
		commerceBOMDefinitionImpl.setCPAttachmentFileEntryId(CPAttachmentFileEntryId);

		if (name == null) {
			commerceBOMDefinitionImpl.setName("");
		}
		else {
			commerceBOMDefinitionImpl.setName(name);
		}

		if (friendlyUrl == null) {
			commerceBOMDefinitionImpl.setFriendlyUrl("");
		}
		else {
			commerceBOMDefinitionImpl.setFriendlyUrl(friendlyUrl);
		}

		commerceBOMDefinitionImpl.resetOriginalValues();

		return commerceBOMDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBOMDefinitionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceBOMFolderId = objectInput.readLong();

		CPAttachmentFileEntryId = objectInput.readLong();
		name = objectInput.readUTF();
		friendlyUrl = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBOMDefinitionId);

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

		objectOutput.writeLong(commerceBOMFolderId);

		objectOutput.writeLong(CPAttachmentFileEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (friendlyUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(friendlyUrl);
		}
	}

	public long commerceBOMDefinitionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceBOMFolderId;
	public long CPAttachmentFileEntryId;
	public String name;
	public String friendlyUrl;
}