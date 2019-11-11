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

import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceApplicationModelCProductRel in entity cache.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRel
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelCacheModel implements CacheModel<CommerceApplicationModelCProductRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceApplicationModelCProductRelCacheModel)) {
			return false;
		}

		CommerceApplicationModelCProductRelCacheModel commerceApplicationModelCProductRelCacheModel =
			(CommerceApplicationModelCProductRelCacheModel)obj;

		if (commerceApplicationModelCProductRelId == commerceApplicationModelCProductRelCacheModel.commerceApplicationModelCProductRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceApplicationModelCProductRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{commerceApplicationModelCProductRelId=");
		sb.append(commerceApplicationModelCProductRelId);
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
		sb.append(", commerceApplicationModelId=");
		sb.append(commerceApplicationModelId);
		sb.append(", CProductId=");
		sb.append(CProductId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceApplicationModelCProductRel toEntityModel() {
		CommerceApplicationModelCProductRelImpl commerceApplicationModelCProductRelImpl =
			new CommerceApplicationModelCProductRelImpl();

		commerceApplicationModelCProductRelImpl.setCommerceApplicationModelCProductRelId(commerceApplicationModelCProductRelId);
		commerceApplicationModelCProductRelImpl.setCompanyId(companyId);
		commerceApplicationModelCProductRelImpl.setUserId(userId);

		if (userName == null) {
			commerceApplicationModelCProductRelImpl.setUserName("");
		}
		else {
			commerceApplicationModelCProductRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceApplicationModelCProductRelImpl.setCreateDate(null);
		}
		else {
			commerceApplicationModelCProductRelImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceApplicationModelCProductRelImpl.setModifiedDate(null);
		}
		else {
			commerceApplicationModelCProductRelImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		commerceApplicationModelCProductRelImpl.setCommerceApplicationModelId(commerceApplicationModelId);
		commerceApplicationModelCProductRelImpl.setCProductId(CProductId);

		commerceApplicationModelCProductRelImpl.resetOriginalValues();

		return commerceApplicationModelCProductRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceApplicationModelCProductRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceApplicationModelId = objectInput.readLong();

		CProductId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceApplicationModelCProductRelId);

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

		objectOutput.writeLong(commerceApplicationModelId);

		objectOutput.writeLong(CProductId);
	}

	public long commerceApplicationModelCProductRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceApplicationModelId;
	public long CProductId;
}