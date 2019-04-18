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

import com.liferay.commerce.product.model.CPRuleChannelRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPRuleChannelRel in entity cache.
 *
 * @author Marco Leo
 * @see CPRuleChannelRel
 * @generated
 */
@ProviderType
public class CPRuleChannelRelCacheModel implements CacheModel<CPRuleChannelRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPRuleChannelRelCacheModel)) {
			return false;
		}

		CPRuleChannelRelCacheModel cpRuleChannelRelCacheModel = (CPRuleChannelRelCacheModel)obj;

		if (CPRuleChannelRelId == cpRuleChannelRelCacheModel.CPRuleChannelRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPRuleChannelRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{CPRuleChannelRelId=");
		sb.append(CPRuleChannelRelId);
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
		sb.append(", CPRuleId=");
		sb.append(CPRuleId);
		sb.append(", commerceChannelId=");
		sb.append(commerceChannelId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPRuleChannelRel toEntityModel() {
		CPRuleChannelRelImpl cpRuleChannelRelImpl = new CPRuleChannelRelImpl();

		cpRuleChannelRelImpl.setCPRuleChannelRelId(CPRuleChannelRelId);
		cpRuleChannelRelImpl.setGroupId(groupId);
		cpRuleChannelRelImpl.setCompanyId(companyId);
		cpRuleChannelRelImpl.setUserId(userId);

		if (userName == null) {
			cpRuleChannelRelImpl.setUserName("");
		}
		else {
			cpRuleChannelRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpRuleChannelRelImpl.setCreateDate(null);
		}
		else {
			cpRuleChannelRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpRuleChannelRelImpl.setModifiedDate(null);
		}
		else {
			cpRuleChannelRelImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpRuleChannelRelImpl.setCPRuleId(CPRuleId);
		cpRuleChannelRelImpl.setCommerceChannelId(commerceChannelId);

		cpRuleChannelRelImpl.resetOriginalValues();

		return cpRuleChannelRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		CPRuleChannelRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPRuleId = objectInput.readLong();

		commerceChannelId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(CPRuleChannelRelId);

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

		objectOutput.writeLong(CPRuleId);

		objectOutput.writeLong(commerceChannelId);
	}

	public long CPRuleChannelRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPRuleId;
	public long commerceChannelId;
}