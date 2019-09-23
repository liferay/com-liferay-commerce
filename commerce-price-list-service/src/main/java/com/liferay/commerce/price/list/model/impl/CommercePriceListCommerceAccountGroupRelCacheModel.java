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

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommercePriceListCommerceAccountGroupRel in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommercePriceListCommerceAccountGroupRelCacheModel
	implements CacheModel<CommercePriceListCommerceAccountGroupRel>,
			   Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof
				CommercePriceListCommerceAccountGroupRelCacheModel)) {

			return false;
		}

		CommercePriceListCommerceAccountGroupRelCacheModel
			commercePriceListCommerceAccountGroupRelCacheModel =
				(CommercePriceListCommerceAccountGroupRelCacheModel)obj;

		if (commercePriceListCommerceAccountGroupRelId ==
				commercePriceListCommerceAccountGroupRelCacheModel.
					commercePriceListCommerceAccountGroupRelId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commercePriceListCommerceAccountGroupRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commercePriceListCommerceAccountGroupRelId=");
		sb.append(commercePriceListCommerceAccountGroupRelId);
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
		sb.append(", commercePriceListId=");
		sb.append(commercePriceListId);
		sb.append(", commerceAccountGroupId=");
		sb.append(commerceAccountGroupId);
		sb.append(", order=");
		sb.append(order);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommercePriceListCommerceAccountGroupRel toEntityModel() {
		CommercePriceListCommerceAccountGroupRelImpl
			commercePriceListCommerceAccountGroupRelImpl =
				new CommercePriceListCommerceAccountGroupRelImpl();

		if (uuid == null) {
			commercePriceListCommerceAccountGroupRelImpl.setUuid("");
		}
		else {
			commercePriceListCommerceAccountGroupRelImpl.setUuid(uuid);
		}

		commercePriceListCommerceAccountGroupRelImpl.
			setCommercePriceListCommerceAccountGroupRelId(
				commercePriceListCommerceAccountGroupRelId);
		commercePriceListCommerceAccountGroupRelImpl.setCompanyId(companyId);
		commercePriceListCommerceAccountGroupRelImpl.setUserId(userId);

		if (userName == null) {
			commercePriceListCommerceAccountGroupRelImpl.setUserName("");
		}
		else {
			commercePriceListCommerceAccountGroupRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commercePriceListCommerceAccountGroupRelImpl.setCreateDate(null);
		}
		else {
			commercePriceListCommerceAccountGroupRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commercePriceListCommerceAccountGroupRelImpl.setModifiedDate(null);
		}
		else {
			commercePriceListCommerceAccountGroupRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commercePriceListCommerceAccountGroupRelImpl.setCommercePriceListId(
			commercePriceListId);
		commercePriceListCommerceAccountGroupRelImpl.setCommerceAccountGroupId(
			commerceAccountGroupId);
		commercePriceListCommerceAccountGroupRelImpl.setOrder(order);

		if (lastPublishDate == Long.MIN_VALUE) {
			commercePriceListCommerceAccountGroupRelImpl.setLastPublishDate(
				null);
		}
		else {
			commercePriceListCommerceAccountGroupRelImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		commercePriceListCommerceAccountGroupRelImpl.resetOriginalValues();

		return commercePriceListCommerceAccountGroupRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commercePriceListCommerceAccountGroupRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commercePriceListId = objectInput.readLong();

		commerceAccountGroupId = objectInput.readLong();

		order = objectInput.readInt();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(commercePriceListCommerceAccountGroupRelId);

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

		objectOutput.writeLong(commercePriceListId);

		objectOutput.writeLong(commerceAccountGroupId);

		objectOutput.writeInt(order);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long commercePriceListCommerceAccountGroupRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commercePriceListId;
	public long commerceAccountGroupId;
	public int order;
	public long lastPublishDate;

}