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

package com.liferay.commerce.inventory.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceInventoryWarehouseGroupRel in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRel
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelCacheModel implements CacheModel<CommerceInventoryWarehouseGroupRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceInventoryWarehouseGroupRelCacheModel)) {
			return false;
		}

		CommerceInventoryWarehouseGroupRelCacheModel commerceInventoryWarehouseGroupRelCacheModel =
			(CommerceInventoryWarehouseGroupRelCacheModel)obj;

		if (commerceInventoryWarehouseGroupRelId == commerceInventoryWarehouseGroupRelCacheModel.commerceInventoryWarehouseGroupRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceInventoryWarehouseGroupRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceInventoryWarehouseGroupRelId=");
		sb.append(commerceInventoryWarehouseGroupRelId);
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
		sb.append(", commerceWarehouseId=");
		sb.append(commerceWarehouseId);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceInventoryWarehouseGroupRel toEntityModel() {
		CommerceInventoryWarehouseGroupRelImpl commerceInventoryWarehouseGroupRelImpl =
			new CommerceInventoryWarehouseGroupRelImpl();

		commerceInventoryWarehouseGroupRelImpl.setCommerceInventoryWarehouseGroupRelId(commerceInventoryWarehouseGroupRelId);
		commerceInventoryWarehouseGroupRelImpl.setGroupId(groupId);
		commerceInventoryWarehouseGroupRelImpl.setCompanyId(companyId);
		commerceInventoryWarehouseGroupRelImpl.setUserId(userId);

		if (userName == null) {
			commerceInventoryWarehouseGroupRelImpl.setUserName("");
		}
		else {
			commerceInventoryWarehouseGroupRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceInventoryWarehouseGroupRelImpl.setCreateDate(null);
		}
		else {
			commerceInventoryWarehouseGroupRelImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceInventoryWarehouseGroupRelImpl.setModifiedDate(null);
		}
		else {
			commerceInventoryWarehouseGroupRelImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		commerceInventoryWarehouseGroupRelImpl.setCommerceWarehouseId(commerceWarehouseId);
		commerceInventoryWarehouseGroupRelImpl.setPrimary(primary);

		commerceInventoryWarehouseGroupRelImpl.resetOriginalValues();

		return commerceInventoryWarehouseGroupRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceInventoryWarehouseGroupRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceWarehouseId = objectInput.readLong();

		primary = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceInventoryWarehouseGroupRelId);

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

		objectOutput.writeLong(commerceWarehouseId);

		objectOutput.writeBoolean(primary);
	}

	public long commerceInventoryWarehouseGroupRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceWarehouseId;
	public boolean primary;
}