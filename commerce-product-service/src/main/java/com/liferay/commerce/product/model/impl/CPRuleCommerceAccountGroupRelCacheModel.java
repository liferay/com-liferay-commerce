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

import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPRuleCommerceAccountGroupRel in entity cache.
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRel
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelCacheModel implements CacheModel<CPRuleCommerceAccountGroupRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPRuleCommerceAccountGroupRelCacheModel)) {
			return false;
		}

		CPRuleCommerceAccountGroupRelCacheModel cpRuleCommerceAccountGroupRelCacheModel =
			(CPRuleCommerceAccountGroupRelCacheModel)obj;

		if (CPRuleCommerceAccountGroupRelId == cpRuleCommerceAccountGroupRelCacheModel.CPRuleCommerceAccountGroupRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPRuleCommerceAccountGroupRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{CPRuleCommerceAccountGroupRelId=");
		sb.append(CPRuleCommerceAccountGroupRelId);
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
		sb.append(", commerceAccountGroupId=");
		sb.append(commerceAccountGroupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPRuleCommerceAccountGroupRel toEntityModel() {
		CPRuleCommerceAccountGroupRelImpl cpRuleCommerceAccountGroupRelImpl = new CPRuleCommerceAccountGroupRelImpl();

		cpRuleCommerceAccountGroupRelImpl.setCPRuleCommerceAccountGroupRelId(CPRuleCommerceAccountGroupRelId);
		cpRuleCommerceAccountGroupRelImpl.setGroupId(groupId);
		cpRuleCommerceAccountGroupRelImpl.setCompanyId(companyId);
		cpRuleCommerceAccountGroupRelImpl.setUserId(userId);

		if (userName == null) {
			cpRuleCommerceAccountGroupRelImpl.setUserName("");
		}
		else {
			cpRuleCommerceAccountGroupRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpRuleCommerceAccountGroupRelImpl.setCreateDate(null);
		}
		else {
			cpRuleCommerceAccountGroupRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpRuleCommerceAccountGroupRelImpl.setModifiedDate(null);
		}
		else {
			cpRuleCommerceAccountGroupRelImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		cpRuleCommerceAccountGroupRelImpl.setCPRuleId(CPRuleId);
		cpRuleCommerceAccountGroupRelImpl.setCommerceAccountGroupId(commerceAccountGroupId);

		cpRuleCommerceAccountGroupRelImpl.resetOriginalValues();

		return cpRuleCommerceAccountGroupRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		CPRuleCommerceAccountGroupRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPRuleId = objectInput.readLong();

		commerceAccountGroupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(CPRuleCommerceAccountGroupRelId);

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

		objectOutput.writeLong(commerceAccountGroupId);
	}

	public long CPRuleCommerceAccountGroupRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPRuleId;
	public long commerceAccountGroupId;
}