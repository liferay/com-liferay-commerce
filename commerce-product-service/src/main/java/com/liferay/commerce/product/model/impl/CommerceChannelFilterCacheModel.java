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

import com.liferay.commerce.product.model.CommerceChannelFilter;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceChannelFilter in entity cache.
 *
 * @author Marco Leo
 * @see CommerceChannelFilter
 * @generated
 */
@ProviderType
public class CommerceChannelFilterCacheModel implements CacheModel<CommerceChannelFilter>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceChannelFilterCacheModel)) {
			return false;
		}

		CommerceChannelFilterCacheModel commerceChannelFilterCacheModel = (CommerceChannelFilterCacheModel)obj;

		if (commerceChannelFilterId == commerceChannelFilterCacheModel.commerceChannelFilterId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceChannelFilterId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceChannelFilterId=");
		sb.append(commerceChannelFilterId);
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
		sb.append(", commerceChannelId=");
		sb.append(commerceChannelId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceChannelFilter toEntityModel() {
		CommerceChannelFilterImpl commerceChannelFilterImpl = new CommerceChannelFilterImpl();

		commerceChannelFilterImpl.setCommerceChannelFilterId(commerceChannelFilterId);
		commerceChannelFilterImpl.setCompanyId(companyId);
		commerceChannelFilterImpl.setUserId(userId);

		if (userName == null) {
			commerceChannelFilterImpl.setUserName("");
		}
		else {
			commerceChannelFilterImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceChannelFilterImpl.setCreateDate(null);
		}
		else {
			commerceChannelFilterImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceChannelFilterImpl.setModifiedDate(null);
		}
		else {
			commerceChannelFilterImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceChannelFilterImpl.setCommerceChannelId(commerceChannelId);

		if (type == null) {
			commerceChannelFilterImpl.setType("");
		}
		else {
			commerceChannelFilterImpl.setType(type);
		}

		if (typeSettings == null) {
			commerceChannelFilterImpl.setTypeSettings("");
		}
		else {
			commerceChannelFilterImpl.setTypeSettings(typeSettings);
		}

		commerceChannelFilterImpl.resetOriginalValues();

		return commerceChannelFilterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceChannelFilterId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceChannelId = objectInput.readLong();
		type = objectInput.readUTF();
		typeSettings = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceChannelFilterId);

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

		objectOutput.writeLong(commerceChannelId);

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

	public long commerceChannelFilterId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceChannelId;
	public String type;
	public String typeSettings;
}