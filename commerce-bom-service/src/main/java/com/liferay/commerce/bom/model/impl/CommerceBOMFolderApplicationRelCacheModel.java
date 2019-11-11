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

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBOMFolderApplicationRel in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRel
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelCacheModel implements CacheModel<CommerceBOMFolderApplicationRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBOMFolderApplicationRelCacheModel)) {
			return false;
		}

		CommerceBOMFolderApplicationRelCacheModel commerceBOMFolderApplicationRelCacheModel =
			(CommerceBOMFolderApplicationRelCacheModel)obj;

		if (commerceBOMFolderApplicationRelId == commerceBOMFolderApplicationRelCacheModel.commerceBOMFolderApplicationRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBOMFolderApplicationRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{commerceBOMFolderApplicationRelId=");
		sb.append(commerceBOMFolderApplicationRelId);
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
		sb.append(", commerceApplicationModelId=");
		sb.append(commerceApplicationModelId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBOMFolderApplicationRel toEntityModel() {
		CommerceBOMFolderApplicationRelImpl commerceBOMFolderApplicationRelImpl = new CommerceBOMFolderApplicationRelImpl();

		commerceBOMFolderApplicationRelImpl.setCommerceBOMFolderApplicationRelId(commerceBOMFolderApplicationRelId);
		commerceBOMFolderApplicationRelImpl.setCompanyId(companyId);
		commerceBOMFolderApplicationRelImpl.setUserId(userId);

		if (userName == null) {
			commerceBOMFolderApplicationRelImpl.setUserName("");
		}
		else {
			commerceBOMFolderApplicationRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceBOMFolderApplicationRelImpl.setCreateDate(null);
		}
		else {
			commerceBOMFolderApplicationRelImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBOMFolderApplicationRelImpl.setModifiedDate(null);
		}
		else {
			commerceBOMFolderApplicationRelImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		commerceBOMFolderApplicationRelImpl.setCommerceBOMFolderId(commerceBOMFolderId);
		commerceBOMFolderApplicationRelImpl.setCommerceApplicationModelId(commerceApplicationModelId);

		commerceBOMFolderApplicationRelImpl.resetOriginalValues();

		return commerceBOMFolderApplicationRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBOMFolderApplicationRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceBOMFolderId = objectInput.readLong();

		commerceApplicationModelId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBOMFolderApplicationRelId);

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

		objectOutput.writeLong(commerceApplicationModelId);
	}

	public long commerceBOMFolderApplicationRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceBOMFolderId;
	public long commerceApplicationModelId;
}