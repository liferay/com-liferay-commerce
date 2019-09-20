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

import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.math.BigDecimal;

import java.util.Date;

/**
 * The cache model class for representing CommerceTierPriceEntry in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceTierPriceEntryCacheModel
	implements CacheModel<CommerceTierPriceEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceTierPriceEntryCacheModel)) {
			return false;
		}

		CommerceTierPriceEntryCacheModel commerceTierPriceEntryCacheModel =
			(CommerceTierPriceEntryCacheModel)obj;

		if (commerceTierPriceEntryId ==
				commerceTierPriceEntryCacheModel.commerceTierPriceEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceTierPriceEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commerceTierPriceEntryId=");
		sb.append(commerceTierPriceEntryId);
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
		sb.append(", commercePriceEntryId=");
		sb.append(commercePriceEntryId);
		sb.append(", price=");
		sb.append(price);
		sb.append(", promoPrice=");
		sb.append(promoPrice);
		sb.append(", minQuantity=");
		sb.append(minQuantity);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceTierPriceEntry toEntityModel() {
		CommerceTierPriceEntryImpl commerceTierPriceEntryImpl =
			new CommerceTierPriceEntryImpl();

		if (uuid == null) {
			commerceTierPriceEntryImpl.setUuid("");
		}
		else {
			commerceTierPriceEntryImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			commerceTierPriceEntryImpl.setExternalReferenceCode("");
		}
		else {
			commerceTierPriceEntryImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		commerceTierPriceEntryImpl.setCommerceTierPriceEntryId(
			commerceTierPriceEntryId);
		commerceTierPriceEntryImpl.setCompanyId(companyId);
		commerceTierPriceEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceTierPriceEntryImpl.setUserName("");
		}
		else {
			commerceTierPriceEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceTierPriceEntryImpl.setCreateDate(null);
		}
		else {
			commerceTierPriceEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceTierPriceEntryImpl.setModifiedDate(null);
		}
		else {
			commerceTierPriceEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceTierPriceEntryImpl.setCommercePriceEntryId(
			commercePriceEntryId);
		commerceTierPriceEntryImpl.setPrice(price);
		commerceTierPriceEntryImpl.setPromoPrice(promoPrice);
		commerceTierPriceEntryImpl.setMinQuantity(minQuantity);

		if (lastPublishDate == Long.MIN_VALUE) {
			commerceTierPriceEntryImpl.setLastPublishDate(null);
		}
		else {
			commerceTierPriceEntryImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		commerceTierPriceEntryImpl.resetOriginalValues();

		return commerceTierPriceEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		commerceTierPriceEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commercePriceEntryId = objectInput.readLong();
		price = (BigDecimal)objectInput.readObject();
		promoPrice = (BigDecimal)objectInput.readObject();

		minQuantity = objectInput.readInt();
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

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(commerceTierPriceEntryId);

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

		objectOutput.writeLong(commercePriceEntryId);
		objectOutput.writeObject(price);
		objectOutput.writeObject(promoPrice);

		objectOutput.writeInt(minQuantity);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public String externalReferenceCode;
	public long commerceTierPriceEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commercePriceEntryId;
	public BigDecimal price;
	public BigDecimal promoPrice;
	public int minQuantity;
	public long lastPublishDate;

}