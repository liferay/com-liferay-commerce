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

import com.liferay.commerce.product.model.CommerceChannel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceChannel in entity cache.
 *
 * @author Marco Leo
 * @see CommerceChannel
 * @generated
 */
@ProviderType
public class CommerceChannelCacheModel implements CacheModel<CommerceChannel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceChannelCacheModel)) {
			return false;
		}

		CommerceChannelCacheModel commerceChannelCacheModel = (CommerceChannelCacheModel)obj;

		if (commerceChannelId == commerceChannelCacheModel.commerceChannelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceChannelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{commerceChannelId=");
		sb.append(commerceChannelId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", filterType=");
		sb.append(filterType);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceChannel toEntityModel() {
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setCommerceChannelId(commerceChannelId);
		commerceChannelImpl.setCompanyId(companyId);
		commerceChannelImpl.setUserId(userId);

		if (userName == null) {
			commerceChannelImpl.setUserName("");
		}
		else {
			commerceChannelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceChannelImpl.setCreateDate(null);
		}
		else {
			commerceChannelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceChannelImpl.setModifiedDate(null);
		}
		else {
			commerceChannelImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			commerceChannelImpl.setName("");
		}
		else {
			commerceChannelImpl.setName(name);
		}

		if (filterType == null) {
			commerceChannelImpl.setFilterType("");
		}
		else {
			commerceChannelImpl.setFilterType(filterType);
		}

		if (type == null) {
			commerceChannelImpl.setType("");
		}
		else {
			commerceChannelImpl.setType(type);
		}

		if (typeSettings == null) {
			commerceChannelImpl.setTypeSettings("");
		}
		else {
			commerceChannelImpl.setTypeSettings(typeSettings);
		}

		commerceChannelImpl.resetOriginalValues();

		return commerceChannelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceChannelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		filterType = objectInput.readUTF();
		type = objectInput.readUTF();
		typeSettings = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceChannelId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (filterType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filterType);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (typeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeSettings);
		}
	}

	public long commerceChannelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String filterType;
	public String type;
	public String typeSettings;
}