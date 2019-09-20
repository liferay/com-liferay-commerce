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

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceDiscountCommerceAccountGroupRel in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceDiscountCommerceAccountGroupRelCacheModel
	implements CacheModel<CommerceDiscountCommerceAccountGroupRel>,
			   Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof
				CommerceDiscountCommerceAccountGroupRelCacheModel)) {

			return false;
		}

		CommerceDiscountCommerceAccountGroupRelCacheModel
			commerceDiscountCommerceAccountGroupRelCacheModel =
				(CommerceDiscountCommerceAccountGroupRelCacheModel)obj;

		if (commerceDiscountCommerceAccountGroupRelId ==
				commerceDiscountCommerceAccountGroupRelCacheModel.
					commerceDiscountCommerceAccountGroupRelId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{commerceDiscountCommerceAccountGroupRelId=");
		sb.append(commerceDiscountCommerceAccountGroupRelId);
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
		sb.append(", commerceDiscountId=");
		sb.append(commerceDiscountId);
		sb.append(", commerceAccountGroupId=");
		sb.append(commerceAccountGroupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRel toEntityModel() {
		CommerceDiscountCommerceAccountGroupRelImpl
			commerceDiscountCommerceAccountGroupRelImpl =
				new CommerceDiscountCommerceAccountGroupRelImpl();

		commerceDiscountCommerceAccountGroupRelImpl.
			setCommerceDiscountCommerceAccountGroupRelId(
				commerceDiscountCommerceAccountGroupRelId);
		commerceDiscountCommerceAccountGroupRelImpl.setCompanyId(companyId);
		commerceDiscountCommerceAccountGroupRelImpl.setUserId(userId);

		if (userName == null) {
			commerceDiscountCommerceAccountGroupRelImpl.setUserName("");
		}
		else {
			commerceDiscountCommerceAccountGroupRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDiscountCommerceAccountGroupRelImpl.setCreateDate(null);
		}
		else {
			commerceDiscountCommerceAccountGroupRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDiscountCommerceAccountGroupRelImpl.setModifiedDate(null);
		}
		else {
			commerceDiscountCommerceAccountGroupRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceDiscountCommerceAccountGroupRelImpl.setCommerceDiscountId(
			commerceDiscountId);
		commerceDiscountCommerceAccountGroupRelImpl.setCommerceAccountGroupId(
			commerceAccountGroupId);

		commerceDiscountCommerceAccountGroupRelImpl.resetOriginalValues();

		return commerceDiscountCommerceAccountGroupRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceDiscountCommerceAccountGroupRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceDiscountId = objectInput.readLong();

		commerceAccountGroupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceDiscountCommerceAccountGroupRelId);

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

		objectOutput.writeLong(commerceDiscountId);

		objectOutput.writeLong(commerceAccountGroupId);
	}

	public long commerceDiscountCommerceAccountGroupRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceDiscountId;
	public long commerceAccountGroupId;

}