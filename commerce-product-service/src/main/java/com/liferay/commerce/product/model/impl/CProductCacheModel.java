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

import com.liferay.commerce.product.model.CProduct;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CProduct in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CProductCacheModel
	implements CacheModel<CProduct>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CProductCacheModel)) {
			return false;
		}

		CProductCacheModel cProductCacheModel = (CProductCacheModel)obj;

		if (CProductId == cProductCacheModel.CProductId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CProductId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", CProductId=");
		sb.append(CProductId);
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
		sb.append(", publishedCPDefinitionId=");
		sb.append(publishedCPDefinitionId);
		sb.append(", latestVersion=");
		sb.append(latestVersion);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CProduct toEntityModel() {
		CProductImpl cProductImpl = new CProductImpl();

		if (uuid == null) {
			cProductImpl.setUuid("");
		}
		else {
			cProductImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			cProductImpl.setExternalReferenceCode("");
		}
		else {
			cProductImpl.setExternalReferenceCode(externalReferenceCode);
		}

		cProductImpl.setCProductId(CProductId);
		cProductImpl.setGroupId(groupId);
		cProductImpl.setCompanyId(companyId);
		cProductImpl.setUserId(userId);

		if (userName == null) {
			cProductImpl.setUserName("");
		}
		else {
			cProductImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cProductImpl.setCreateDate(null);
		}
		else {
			cProductImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cProductImpl.setModifiedDate(null);
		}
		else {
			cProductImpl.setModifiedDate(new Date(modifiedDate));
		}

		cProductImpl.setPublishedCPDefinitionId(publishedCPDefinitionId);
		cProductImpl.setLatestVersion(latestVersion);

		cProductImpl.resetOriginalValues();

		return cProductImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		CProductId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		publishedCPDefinitionId = objectInput.readLong();

		latestVersion = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(CProductId);

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

		objectOutput.writeLong(publishedCPDefinitionId);

		objectOutput.writeInt(latestVersion);
	}

	public String uuid;
	public String externalReferenceCode;
	public long CProductId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long publishedCPDefinitionId;
	public int latestVersion;

}