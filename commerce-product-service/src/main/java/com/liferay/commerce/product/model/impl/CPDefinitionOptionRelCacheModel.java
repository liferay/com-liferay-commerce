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

import com.liferay.commerce.product.model.CPDefinitionOptionRel;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPDefinitionOptionRel in entity cache.
 *
 * @author Marco Leo
 * @see CPDefinitionOptionRel
 * @generated
 */
@ProviderType
public class CPDefinitionOptionRelCacheModel implements CacheModel<CPDefinitionOptionRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionOptionRelCacheModel)) {
			return false;
		}

		CPDefinitionOptionRelCacheModel cpDefinitionOptionRelCacheModel = (CPDefinitionOptionRelCacheModel)obj;

		if (CPDefinitionOptionRelId == cpDefinitionOptionRelCacheModel.CPDefinitionOptionRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPDefinitionOptionRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPDefinitionOptionRelId=");
		sb.append(CPDefinitionOptionRelId);
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
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", CPOptionId=");
		sb.append(CPOptionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", DDMFormFieldTypeName=");
		sb.append(DDMFormFieldTypeName);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", facetable=");
		sb.append(facetable);
		sb.append(", required=");
		sb.append(required);
		sb.append(", skuContributor=");
		sb.append(skuContributor);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinitionOptionRel toEntityModel() {
		CPDefinitionOptionRelImpl cpDefinitionOptionRelImpl = new CPDefinitionOptionRelImpl();

		if (uuid == null) {
			cpDefinitionOptionRelImpl.setUuid("");
		}
		else {
			cpDefinitionOptionRelImpl.setUuid(uuid);
		}

		cpDefinitionOptionRelImpl.setCPDefinitionOptionRelId(CPDefinitionOptionRelId);
		cpDefinitionOptionRelImpl.setGroupId(groupId);
		cpDefinitionOptionRelImpl.setCompanyId(companyId);
		cpDefinitionOptionRelImpl.setUserId(userId);

		if (userName == null) {
			cpDefinitionOptionRelImpl.setUserName("");
		}
		else {
			cpDefinitionOptionRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpDefinitionOptionRelImpl.setCreateDate(null);
		}
		else {
			cpDefinitionOptionRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpDefinitionOptionRelImpl.setModifiedDate(null);
		}
		else {
			cpDefinitionOptionRelImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpDefinitionOptionRelImpl.setCPDefinitionId(CPDefinitionId);
		cpDefinitionOptionRelImpl.setCPOptionId(CPOptionId);

		if (name == null) {
			cpDefinitionOptionRelImpl.setName("");
		}
		else {
			cpDefinitionOptionRelImpl.setName(name);
		}

		if (description == null) {
			cpDefinitionOptionRelImpl.setDescription("");
		}
		else {
			cpDefinitionOptionRelImpl.setDescription(description);
		}

		if (DDMFormFieldTypeName == null) {
			cpDefinitionOptionRelImpl.setDDMFormFieldTypeName("");
		}
		else {
			cpDefinitionOptionRelImpl.setDDMFormFieldTypeName(DDMFormFieldTypeName);
		}

		cpDefinitionOptionRelImpl.setPriority(priority);
		cpDefinitionOptionRelImpl.setFacetable(facetable);
		cpDefinitionOptionRelImpl.setRequired(required);
		cpDefinitionOptionRelImpl.setSkuContributor(skuContributor);

		cpDefinitionOptionRelImpl.resetOriginalValues();

		return cpDefinitionOptionRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPDefinitionOptionRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();

		CPOptionId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		DDMFormFieldTypeName = objectInput.readUTF();

		priority = objectInput.readDouble();

		facetable = objectInput.readBoolean();

		required = objectInput.readBoolean();

		skuContributor = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(CPDefinitionOptionRelId);

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

		objectOutput.writeLong(CPDefinitionId);

		objectOutput.writeLong(CPOptionId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (DDMFormFieldTypeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(DDMFormFieldTypeName);
		}

		objectOutput.writeDouble(priority);

		objectOutput.writeBoolean(facetable);

		objectOutput.writeBoolean(required);

		objectOutput.writeBoolean(skuContributor);
	}

	public String uuid;
	public long CPDefinitionOptionRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPDefinitionId;
	public long CPOptionId;
	public String name;
	public String description;
	public String DDMFormFieldTypeName;
	public double priority;
	public boolean facetable;
	public boolean required;
	public boolean skuContributor;
}