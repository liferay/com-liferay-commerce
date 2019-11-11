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

package com.liferay.commerce.application.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.model.CommerceApplicationModel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceApplicationModel in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModel
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCacheModel implements CacheModel<CommerceApplicationModel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceApplicationModelCacheModel)) {
			return false;
		}

		CommerceApplicationModelCacheModel commerceApplicationModelCacheModel = (CommerceApplicationModelCacheModel)obj;

		if (commerceApplicationModelId == commerceApplicationModelCacheModel.commerceApplicationModelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceApplicationModelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceApplicationModelId=");
		sb.append(commerceApplicationModelId);
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
		sb.append(", commerceApplicationBrandId=");
		sb.append(commerceApplicationBrandId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", year=");
		sb.append(year);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceApplicationModel toEntityModel() {
		CommerceApplicationModelImpl commerceApplicationModelImpl = new CommerceApplicationModelImpl();

		commerceApplicationModelImpl.setCommerceApplicationModelId(commerceApplicationModelId);
		commerceApplicationModelImpl.setCompanyId(companyId);
		commerceApplicationModelImpl.setUserId(userId);

		if (userName == null) {
			commerceApplicationModelImpl.setUserName("");
		}
		else {
			commerceApplicationModelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceApplicationModelImpl.setCreateDate(null);
		}
		else {
			commerceApplicationModelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceApplicationModelImpl.setModifiedDate(null);
		}
		else {
			commerceApplicationModelImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceApplicationModelImpl.setCommerceApplicationBrandId(commerceApplicationBrandId);

		if (name == null) {
			commerceApplicationModelImpl.setName("");
		}
		else {
			commerceApplicationModelImpl.setName(name);
		}

		if (year == null) {
			commerceApplicationModelImpl.setYear("");
		}
		else {
			commerceApplicationModelImpl.setYear(year);
		}

		commerceApplicationModelImpl.resetOriginalValues();

		return commerceApplicationModelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceApplicationModelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceApplicationBrandId = objectInput.readLong();
		name = objectInput.readUTF();
		year = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceApplicationModelId);

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

		objectOutput.writeLong(commerceApplicationBrandId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (year == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(year);
		}
	}

	public long commerceApplicationModelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceApplicationBrandId;
	public String name;
	public String year;
}