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

import com.liferay.commerce.price.list.model.CommercePriceEntry;
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
 * The cache model class for representing CommercePriceEntry in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommercePriceEntryCacheModel
	implements CacheModel<CommercePriceEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommercePriceEntryCacheModel)) {
			return false;
		}

		CommercePriceEntryCacheModel commercePriceEntryCacheModel =
			(CommercePriceEntryCacheModel)obj;

		if (commercePriceEntryId ==
				commercePriceEntryCacheModel.commercePriceEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commercePriceEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commercePriceEntryId=");
		sb.append(commercePriceEntryId);
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
		sb.append(", CPInstanceUuid=");
		sb.append(CPInstanceUuid);
		sb.append(", CProductId=");
		sb.append(CProductId);
		sb.append(", price=");
		sb.append(price);
		sb.append(", promoPrice=");
		sb.append(promoPrice);
		sb.append(", hasTierPrice=");
		sb.append(hasTierPrice);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommercePriceEntry toEntityModel() {
		CommercePriceEntryImpl commercePriceEntryImpl =
			new CommercePriceEntryImpl();

		if (uuid == null) {
			commercePriceEntryImpl.setUuid("");
		}
		else {
			commercePriceEntryImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			commercePriceEntryImpl.setExternalReferenceCode("");
		}
		else {
			commercePriceEntryImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		commercePriceEntryImpl.setCommercePriceEntryId(commercePriceEntryId);
		commercePriceEntryImpl.setCompanyId(companyId);
		commercePriceEntryImpl.setUserId(userId);

		if (userName == null) {
			commercePriceEntryImpl.setUserName("");
		}
		else {
			commercePriceEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commercePriceEntryImpl.setCreateDate(null);
		}
		else {
			commercePriceEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commercePriceEntryImpl.setModifiedDate(null);
		}
		else {
			commercePriceEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		commercePriceEntryImpl.setCommercePriceListId(commercePriceListId);

		if (CPInstanceUuid == null) {
			commercePriceEntryImpl.setCPInstanceUuid("");
		}
		else {
			commercePriceEntryImpl.setCPInstanceUuid(CPInstanceUuid);
		}

		commercePriceEntryImpl.setCProductId(CProductId);
		commercePriceEntryImpl.setPrice(price);
		commercePriceEntryImpl.setPromoPrice(promoPrice);
		commercePriceEntryImpl.setHasTierPrice(hasTierPrice);

		if (lastPublishDate == Long.MIN_VALUE) {
			commercePriceEntryImpl.setLastPublishDate(null);
		}
		else {
			commercePriceEntryImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		commercePriceEntryImpl.resetOriginalValues();

		return commercePriceEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		commercePriceEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commercePriceListId = objectInput.readLong();
		CPInstanceUuid = objectInput.readUTF();

		CProductId = objectInput.readLong();
		price = (BigDecimal)objectInput.readObject();
		promoPrice = (BigDecimal)objectInput.readObject();

		hasTierPrice = objectInput.readBoolean();
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

		objectOutput.writeLong(commercePriceEntryId);

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

		if (CPInstanceUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CPInstanceUuid);
		}

		objectOutput.writeLong(CProductId);
		objectOutput.writeObject(price);
		objectOutput.writeObject(promoPrice);

		objectOutput.writeBoolean(hasTierPrice);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public String externalReferenceCode;
	public long commercePriceEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commercePriceListId;
	public String CPInstanceUuid;
	public long CProductId;
	public BigDecimal price;
	public BigDecimal promoPrice;
	public boolean hasTierPrice;
	public long lastPublishDate;

}