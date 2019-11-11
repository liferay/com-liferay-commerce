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

import com.liferay.commerce.bom.model.CommerceBOMFolder;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBOMFolder in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolder
 * @generated
 */
@ProviderType
public class CommerceBOMFolderCacheModel implements CacheModel<CommerceBOMFolder>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBOMFolderCacheModel)) {
			return false;
		}

		CommerceBOMFolderCacheModel commerceBOMFolderCacheModel = (CommerceBOMFolderCacheModel)obj;

		if (commerceBOMFolderId == commerceBOMFolderCacheModel.commerceBOMFolderId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBOMFolderId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{commerceBOMFolderId=");
		sb.append(commerceBOMFolderId);
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
		sb.append(", parentCommerceBOMFolderId=");
		sb.append(parentCommerceBOMFolderId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", treePath=");
		sb.append(treePath);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBOMFolder toEntityModel() {
		CommerceBOMFolderImpl commerceBOMFolderImpl = new CommerceBOMFolderImpl();

		commerceBOMFolderImpl.setCommerceBOMFolderId(commerceBOMFolderId);
		commerceBOMFolderImpl.setCompanyId(companyId);
		commerceBOMFolderImpl.setUserId(userId);

		if (userName == null) {
			commerceBOMFolderImpl.setUserName("");
		}
		else {
			commerceBOMFolderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceBOMFolderImpl.setCreateDate(null);
		}
		else {
			commerceBOMFolderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBOMFolderImpl.setModifiedDate(null);
		}
		else {
			commerceBOMFolderImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceBOMFolderImpl.setParentCommerceBOMFolderId(parentCommerceBOMFolderId);

		if (name == null) {
			commerceBOMFolderImpl.setName("");
		}
		else {
			commerceBOMFolderImpl.setName(name);
		}

		commerceBOMFolderImpl.setLogoId(logoId);

		if (treePath == null) {
			commerceBOMFolderImpl.setTreePath("");
		}
		else {
			commerceBOMFolderImpl.setTreePath(treePath);
		}

		commerceBOMFolderImpl.resetOriginalValues();

		return commerceBOMFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBOMFolderId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentCommerceBOMFolderId = objectInput.readLong();
		name = objectInput.readUTF();

		logoId = objectInput.readLong();
		treePath = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBOMFolderId);

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

		objectOutput.writeLong(parentCommerceBOMFolderId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(logoId);

		if (treePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(treePath);
		}
	}

	public long commerceBOMFolderId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentCommerceBOMFolderId;
	public String name;
	public long logoId;
	public String treePath;
}