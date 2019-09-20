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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryAudit;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceInventoryAudit in entity cache.
 *
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryAuditCacheModel
	implements CacheModel<CommerceInventoryAudit>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceInventoryAuditCacheModel)) {
			return false;
		}

		CommerceInventoryAuditCacheModel commerceInventoryAuditCacheModel =
			(CommerceInventoryAuditCacheModel)obj;

		if (commerceInventoryAuditId ==
				commerceInventoryAuditCacheModel.commerceInventoryAuditId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceInventoryAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceInventoryAuditId=");
		sb.append(commerceInventoryAuditId);
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
		sb.append(", sku=");
		sb.append(sku);
		sb.append(", description=");
		sb.append(description);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceInventoryAudit toEntityModel() {
		CommerceInventoryAuditImpl commerceInventoryAuditImpl =
			new CommerceInventoryAuditImpl();

		commerceInventoryAuditImpl.setCommerceInventoryAuditId(
			commerceInventoryAuditId);
		commerceInventoryAuditImpl.setCompanyId(companyId);
		commerceInventoryAuditImpl.setUserId(userId);

		if (userName == null) {
			commerceInventoryAuditImpl.setUserName("");
		}
		else {
			commerceInventoryAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceInventoryAuditImpl.setCreateDate(null);
		}
		else {
			commerceInventoryAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceInventoryAuditImpl.setModifiedDate(null);
		}
		else {
			commerceInventoryAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (sku == null) {
			commerceInventoryAuditImpl.setSku("");
		}
		else {
			commerceInventoryAuditImpl.setSku(sku);
		}

		if (description == null) {
			commerceInventoryAuditImpl.setDescription("");
		}
		else {
			commerceInventoryAuditImpl.setDescription(description);
		}

		commerceInventoryAuditImpl.setQuantity(quantity);

		commerceInventoryAuditImpl.resetOriginalValues();

		return commerceInventoryAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceInventoryAuditId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		sku = objectInput.readUTF();
		description = objectInput.readUTF();

		quantity = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceInventoryAuditId);

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

		if (sku == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sku);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(quantity);
	}

	public long commerceInventoryAuditId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String sku;
	public String description;
	public int quantity;

}