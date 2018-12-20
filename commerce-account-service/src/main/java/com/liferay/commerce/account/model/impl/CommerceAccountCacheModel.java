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

package com.liferay.commerce.account.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccount;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceAccount in entity cache.
 *
 * @author Marco Leo
 * @see CommerceAccount
 * @generated
 */
@ProviderType
public class CommerceAccountCacheModel implements CacheModel<CommerceAccount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceAccountCacheModel)) {
			return false;
		}

		CommerceAccountCacheModel commerceAccountCacheModel = (CommerceAccountCacheModel)obj;

		if (commerceAccountId == commerceAccountCacheModel.commerceAccountId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceAccountId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commerceAccountId=");
		sb.append(commerceAccountId);
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
		sb.append(", parentCommerceAccountId=");
		sb.append(parentCommerceAccountId);
		sb.append(", taxId=");
		sb.append(taxId);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceAccount toEntityModel() {
		CommerceAccountImpl commerceAccountImpl = new CommerceAccountImpl();

		if (externalReferenceCode == null) {
			commerceAccountImpl.setExternalReferenceCode("");
		}
		else {
			commerceAccountImpl.setExternalReferenceCode(externalReferenceCode);
		}

		commerceAccountImpl.setCommerceAccountId(commerceAccountId);
		commerceAccountImpl.setCompanyId(companyId);
		commerceAccountImpl.setUserId(userId);

		if (userName == null) {
			commerceAccountImpl.setUserName("");
		}
		else {
			commerceAccountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceAccountImpl.setCreateDate(null);
		}
		else {
			commerceAccountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceAccountImpl.setModifiedDate(null);
		}
		else {
			commerceAccountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			commerceAccountImpl.setName("");
		}
		else {
			commerceAccountImpl.setName(name);
		}

		commerceAccountImpl.setParentCommerceAccountId(parentCommerceAccountId);

		if (taxId == null) {
			commerceAccountImpl.setTaxId("");
		}
		else {
			commerceAccountImpl.setTaxId(taxId);
		}

		commerceAccountImpl.setActive(active);

		commerceAccountImpl.resetOriginalValues();

		return commerceAccountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		externalReferenceCode = objectInput.readUTF();

		commerceAccountId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();

		parentCommerceAccountId = objectInput.readLong();
		taxId = objectInput.readUTF();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(commerceAccountId);

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

		objectOutput.writeLong(parentCommerceAccountId);

		if (taxId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(taxId);
		}

		objectOutput.writeBoolean(active);
	}

	public String externalReferenceCode;
	public long commerceAccountId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public long parentCommerceAccountId;
	public String taxId;
	public boolean active;
}