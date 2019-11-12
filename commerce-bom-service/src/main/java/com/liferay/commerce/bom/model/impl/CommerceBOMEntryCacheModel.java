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

import com.liferay.commerce.bom.model.CommerceBOMEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceBOMEntry in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntry
 * @generated
 */
@ProviderType
public class CommerceBOMEntryCacheModel implements CacheModel<CommerceBOMEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBOMEntryCacheModel)) {
			return false;
		}

		CommerceBOMEntryCacheModel commerceBOMEntryCacheModel = (CommerceBOMEntryCacheModel)obj;

		if (commerceBOMEntryId == commerceBOMEntryCacheModel.commerceBOMEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceBOMEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{commerceBOMEntryId=");
		sb.append(commerceBOMEntryId);
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
		sb.append(", number=");
		sb.append(number);
		sb.append(", CPInstanceUuid=");
		sb.append(CPInstanceUuid);
		sb.append(", CProductId=");
		sb.append(CProductId);
		sb.append(", commerceBOMDefinitionId=");
		sb.append(commerceBOMDefinitionId);
		sb.append(", positionX=");
		sb.append(positionX);
		sb.append(", positionY=");
		sb.append(positionY);
		sb.append(", radius=");
		sb.append(radius);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceBOMEntry toEntityModel() {
		CommerceBOMEntryImpl commerceBOMEntryImpl = new CommerceBOMEntryImpl();

		commerceBOMEntryImpl.setCommerceBOMEntryId(commerceBOMEntryId);
		commerceBOMEntryImpl.setCompanyId(companyId);
		commerceBOMEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceBOMEntryImpl.setUserName("");
		}
		else {
			commerceBOMEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceBOMEntryImpl.setCreateDate(null);
		}
		else {
			commerceBOMEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceBOMEntryImpl.setModifiedDate(null);
		}
		else {
			commerceBOMEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceBOMEntryImpl.setNumber(number);

		if (CPInstanceUuid == null) {
			commerceBOMEntryImpl.setCPInstanceUuid("");
		}
		else {
			commerceBOMEntryImpl.setCPInstanceUuid(CPInstanceUuid);
		}

		commerceBOMEntryImpl.setCProductId(CProductId);
		commerceBOMEntryImpl.setCommerceBOMDefinitionId(commerceBOMDefinitionId);
		commerceBOMEntryImpl.setPositionX(positionX);
		commerceBOMEntryImpl.setPositionY(positionY);
		commerceBOMEntryImpl.setRadius(radius);

		commerceBOMEntryImpl.resetOriginalValues();

		return commerceBOMEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceBOMEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		number = objectInput.readInt();
		CPInstanceUuid = objectInput.readUTF();

		CProductId = objectInput.readLong();

		commerceBOMDefinitionId = objectInput.readLong();

		positionX = objectInput.readDouble();

		positionY = objectInput.readDouble();

		radius = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceBOMEntryId);

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

		objectOutput.writeInt(number);

		if (CPInstanceUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CPInstanceUuid);
		}

		objectOutput.writeLong(CProductId);

		objectOutput.writeLong(commerceBOMDefinitionId);

		objectOutput.writeDouble(positionX);

		objectOutput.writeDouble(positionY);

		objectOutput.writeDouble(radius);
	}

	public long commerceBOMEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int number;
	public String CPInstanceUuid;
	public long CProductId;
	public long commerceBOMDefinitionId;
	public double positionX;
	public double positionY;
	public double radius;
}