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

import com.liferay.commerce.product.model.CPInstance;
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
 * The cache model class for representing CPInstance in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CPInstanceCacheModel
	implements CacheModel<CPInstance>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPInstanceCacheModel)) {
			return false;
		}

		CPInstanceCacheModel cpInstanceCacheModel = (CPInstanceCacheModel)obj;

		if (CPInstanceId == cpInstanceCacheModel.CPInstanceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPInstanceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(75);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", CPInstanceId=");
		sb.append(CPInstanceId);
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
		sb.append(", CPInstanceUuid=");
		sb.append(CPInstanceUuid);
		sb.append(", sku=");
		sb.append(sku);
		sb.append(", gtin=");
		sb.append(gtin);
		sb.append(", manufacturerPartNumber=");
		sb.append(manufacturerPartNumber);
		sb.append(", purchasable=");
		sb.append(purchasable);
		sb.append(", json=");
		sb.append(json);
		sb.append(", width=");
		sb.append(width);
		sb.append(", height=");
		sb.append(height);
		sb.append(", depth=");
		sb.append(depth);
		sb.append(", weight=");
		sb.append(weight);
		sb.append(", price=");
		sb.append(price);
		sb.append(", promoPrice=");
		sb.append(promoPrice);
		sb.append(", cost=");
		sb.append(cost);
		sb.append(", published=");
		sb.append(published);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", overrideSubscriptionInfo=");
		sb.append(overrideSubscriptionInfo);
		sb.append(", subscriptionEnabled=");
		sb.append(subscriptionEnabled);
		sb.append(", subscriptionLength=");
		sb.append(subscriptionLength);
		sb.append(", subscriptionType=");
		sb.append(subscriptionType);
		sb.append(", subscriptionTypeSettings=");
		sb.append(subscriptionTypeSettings);
		sb.append(", maxSubscriptionCycles=");
		sb.append(maxSubscriptionCycles);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPInstance toEntityModel() {
		CPInstanceImpl cpInstanceImpl = new CPInstanceImpl();

		if (uuid == null) {
			cpInstanceImpl.setUuid("");
		}
		else {
			cpInstanceImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			cpInstanceImpl.setExternalReferenceCode("");
		}
		else {
			cpInstanceImpl.setExternalReferenceCode(externalReferenceCode);
		}

		cpInstanceImpl.setCPInstanceId(CPInstanceId);
		cpInstanceImpl.setGroupId(groupId);
		cpInstanceImpl.setCompanyId(companyId);
		cpInstanceImpl.setUserId(userId);

		if (userName == null) {
			cpInstanceImpl.setUserName("");
		}
		else {
			cpInstanceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpInstanceImpl.setCreateDate(null);
		}
		else {
			cpInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpInstanceImpl.setModifiedDate(null);
		}
		else {
			cpInstanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpInstanceImpl.setCPDefinitionId(CPDefinitionId);

		if (CPInstanceUuid == null) {
			cpInstanceImpl.setCPInstanceUuid("");
		}
		else {
			cpInstanceImpl.setCPInstanceUuid(CPInstanceUuid);
		}

		if (sku == null) {
			cpInstanceImpl.setSku("");
		}
		else {
			cpInstanceImpl.setSku(sku);
		}

		if (gtin == null) {
			cpInstanceImpl.setGtin("");
		}
		else {
			cpInstanceImpl.setGtin(gtin);
		}

		if (manufacturerPartNumber == null) {
			cpInstanceImpl.setManufacturerPartNumber("");
		}
		else {
			cpInstanceImpl.setManufacturerPartNumber(manufacturerPartNumber);
		}

		cpInstanceImpl.setPurchasable(purchasable);

		if (json == null) {
			cpInstanceImpl.setJson("");
		}
		else {
			cpInstanceImpl.setJson(json);
		}

		cpInstanceImpl.setWidth(width);
		cpInstanceImpl.setHeight(height);
		cpInstanceImpl.setDepth(depth);
		cpInstanceImpl.setWeight(weight);
		cpInstanceImpl.setPrice(price);
		cpInstanceImpl.setPromoPrice(promoPrice);
		cpInstanceImpl.setCost(cost);
		cpInstanceImpl.setPublished(published);

		if (displayDate == Long.MIN_VALUE) {
			cpInstanceImpl.setDisplayDate(null);
		}
		else {
			cpInstanceImpl.setDisplayDate(new Date(displayDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			cpInstanceImpl.setExpirationDate(null);
		}
		else {
			cpInstanceImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			cpInstanceImpl.setLastPublishDate(null);
		}
		else {
			cpInstanceImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		cpInstanceImpl.setOverrideSubscriptionInfo(overrideSubscriptionInfo);
		cpInstanceImpl.setSubscriptionEnabled(subscriptionEnabled);
		cpInstanceImpl.setSubscriptionLength(subscriptionLength);

		if (subscriptionType == null) {
			cpInstanceImpl.setSubscriptionType("");
		}
		else {
			cpInstanceImpl.setSubscriptionType(subscriptionType);
		}

		if (subscriptionTypeSettings == null) {
			cpInstanceImpl.setSubscriptionTypeSettings("");
		}
		else {
			cpInstanceImpl.setSubscriptionTypeSettings(
				subscriptionTypeSettings);
		}

		cpInstanceImpl.setMaxSubscriptionCycles(maxSubscriptionCycles);
		cpInstanceImpl.setStatus(status);
		cpInstanceImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			cpInstanceImpl.setStatusByUserName("");
		}
		else {
			cpInstanceImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			cpInstanceImpl.setStatusDate(null);
		}
		else {
			cpInstanceImpl.setStatusDate(new Date(statusDate));
		}

		cpInstanceImpl.resetOriginalValues();

		return cpInstanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		CPInstanceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();
		CPInstanceUuid = objectInput.readUTF();
		sku = objectInput.readUTF();
		gtin = objectInput.readUTF();
		manufacturerPartNumber = objectInput.readUTF();

		purchasable = objectInput.readBoolean();
		json = objectInput.readUTF();

		width = objectInput.readDouble();

		height = objectInput.readDouble();

		depth = objectInput.readDouble();

		weight = objectInput.readDouble();
		price = (BigDecimal)objectInput.readObject();
		promoPrice = (BigDecimal)objectInput.readObject();
		cost = (BigDecimal)objectInput.readObject();

		published = objectInput.readBoolean();
		displayDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		overrideSubscriptionInfo = objectInput.readBoolean();

		subscriptionEnabled = objectInput.readBoolean();

		subscriptionLength = objectInput.readInt();
		subscriptionType = objectInput.readUTF();
		subscriptionTypeSettings = objectInput.readUTF();

		maxSubscriptionCycles = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(CPInstanceId);

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

		if (CPInstanceUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CPInstanceUuid);
		}

		if (sku == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sku);
		}

		if (gtin == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(gtin);
		}

		if (manufacturerPartNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manufacturerPartNumber);
		}

		objectOutput.writeBoolean(purchasable);

		if (json == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(json);
		}

		objectOutput.writeDouble(width);

		objectOutput.writeDouble(height);

		objectOutput.writeDouble(depth);

		objectOutput.writeDouble(weight);
		objectOutput.writeObject(price);
		objectOutput.writeObject(promoPrice);
		objectOutput.writeObject(cost);

		objectOutput.writeBoolean(published);
		objectOutput.writeLong(displayDate);
		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeBoolean(overrideSubscriptionInfo);

		objectOutput.writeBoolean(subscriptionEnabled);

		objectOutput.writeInt(subscriptionLength);

		if (subscriptionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subscriptionType);
		}

		if (subscriptionTypeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subscriptionTypeSettings);
		}

		objectOutput.writeLong(maxSubscriptionCycles);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public String externalReferenceCode;
	public long CPInstanceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPDefinitionId;
	public String CPInstanceUuid;
	public String sku;
	public String gtin;
	public String manufacturerPartNumber;
	public boolean purchasable;
	public String json;
	public double width;
	public double height;
	public double depth;
	public double weight;
	public BigDecimal price;
	public BigDecimal promoPrice;
	public BigDecimal cost;
	public boolean published;
	public long displayDate;
	public long expirationDate;
	public long lastPublishDate;
	public boolean overrideSubscriptionInfo;
	public boolean subscriptionEnabled;
	public int subscriptionLength;
	public String subscriptionType;
	public String subscriptionTypeSettings;
	public long maxSubscriptionCycles;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}