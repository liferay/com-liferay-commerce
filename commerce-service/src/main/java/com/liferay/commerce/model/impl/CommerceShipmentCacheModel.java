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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceShipment in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceShipmentCacheModel
	implements CacheModel<CommerceShipment>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceShipmentCacheModel)) {
			return false;
		}

		CommerceShipmentCacheModel commerceShipmentCacheModel =
			(CommerceShipmentCacheModel)obj;

		if (commerceShipmentId ==
				commerceShipmentCacheModel.commerceShipmentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceShipmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{commerceShipmentId=");
		sb.append(commerceShipmentId);
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
		sb.append(", commerceAccountId=");
		sb.append(commerceAccountId);
		sb.append(", shippingName=");
		sb.append(shippingName);
		sb.append(", shippingDescription=");
		sb.append(shippingDescription);
		sb.append(", shippingStreet1=");
		sb.append(shippingStreet1);
		sb.append(", shippingStreet2=");
		sb.append(shippingStreet2);
		sb.append(", shippingStreet3=");
		sb.append(shippingStreet3);
		sb.append(", shippingCity=");
		sb.append(shippingCity);
		sb.append(", shippingZip=");
		sb.append(shippingZip);
		sb.append(", shippingRegionId=");
		sb.append(shippingRegionId);
		sb.append(", shippingCountryId=");
		sb.append(shippingCountryId);
		sb.append(", shippingPhoneNumber=");
		sb.append(shippingPhoneNumber);
		sb.append(", commerceShippingMethodId=");
		sb.append(commerceShippingMethodId);
		sb.append(", shippingOptionName=");
		sb.append(shippingOptionName);
		sb.append(", carrier=");
		sb.append(carrier);
		sb.append(", trackingNumber=");
		sb.append(trackingNumber);
		sb.append(", shippingDate=");
		sb.append(shippingDate);
		sb.append(", expectedDate=");
		sb.append(expectedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceShipment toEntityModel() {
		CommerceShipmentImpl commerceShipmentImpl = new CommerceShipmentImpl();

		commerceShipmentImpl.setCommerceShipmentId(commerceShipmentId);
		commerceShipmentImpl.setGroupId(groupId);
		commerceShipmentImpl.setCompanyId(companyId);
		commerceShipmentImpl.setUserId(userId);

		if (userName == null) {
			commerceShipmentImpl.setUserName("");
		}
		else {
			commerceShipmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceShipmentImpl.setCreateDate(null);
		}
		else {
			commerceShipmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceShipmentImpl.setModifiedDate(null);
		}
		else {
			commerceShipmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceShipmentImpl.setCommerceAccountId(commerceAccountId);

		if (shippingName == null) {
			commerceShipmentImpl.setShippingName("");
		}
		else {
			commerceShipmentImpl.setShippingName(shippingName);
		}

		if (shippingDescription == null) {
			commerceShipmentImpl.setShippingDescription("");
		}
		else {
			commerceShipmentImpl.setShippingDescription(shippingDescription);
		}

		if (shippingStreet1 == null) {
			commerceShipmentImpl.setShippingStreet1("");
		}
		else {
			commerceShipmentImpl.setShippingStreet1(shippingStreet1);
		}

		if (shippingStreet2 == null) {
			commerceShipmentImpl.setShippingStreet2("");
		}
		else {
			commerceShipmentImpl.setShippingStreet2(shippingStreet2);
		}

		if (shippingStreet3 == null) {
			commerceShipmentImpl.setShippingStreet3("");
		}
		else {
			commerceShipmentImpl.setShippingStreet3(shippingStreet3);
		}

		if (shippingCity == null) {
			commerceShipmentImpl.setShippingCity("");
		}
		else {
			commerceShipmentImpl.setShippingCity(shippingCity);
		}

		if (shippingZip == null) {
			commerceShipmentImpl.setShippingZip("");
		}
		else {
			commerceShipmentImpl.setShippingZip(shippingZip);
		}

		commerceShipmentImpl.setShippingRegionId(shippingRegionId);
		commerceShipmentImpl.setShippingCountryId(shippingCountryId);

		if (shippingPhoneNumber == null) {
			commerceShipmentImpl.setShippingPhoneNumber("");
		}
		else {
			commerceShipmentImpl.setShippingPhoneNumber(shippingPhoneNumber);
		}

		commerceShipmentImpl.setCommerceShippingMethodId(
			commerceShippingMethodId);

		if (shippingOptionName == null) {
			commerceShipmentImpl.setShippingOptionName("");
		}
		else {
			commerceShipmentImpl.setShippingOptionName(shippingOptionName);
		}

		if (carrier == null) {
			commerceShipmentImpl.setCarrier("");
		}
		else {
			commerceShipmentImpl.setCarrier(carrier);
		}

		if (trackingNumber == null) {
			commerceShipmentImpl.setTrackingNumber("");
		}
		else {
			commerceShipmentImpl.setTrackingNumber(trackingNumber);
		}

		if (shippingDate == Long.MIN_VALUE) {
			commerceShipmentImpl.setShippingDate(null);
		}
		else {
			commerceShipmentImpl.setShippingDate(new Date(shippingDate));
		}

		if (expectedDate == Long.MIN_VALUE) {
			commerceShipmentImpl.setExpectedDate(null);
		}
		else {
			commerceShipmentImpl.setExpectedDate(new Date(expectedDate));
		}

		commerceShipmentImpl.setStatus(status);

		commerceShipmentImpl.resetOriginalValues();

		return commerceShipmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceShipmentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceAccountId = objectInput.readLong();
		shippingName = objectInput.readUTF();
		shippingDescription = objectInput.readUTF();
		shippingStreet1 = objectInput.readUTF();
		shippingStreet2 = objectInput.readUTF();
		shippingStreet3 = objectInput.readUTF();
		shippingCity = objectInput.readUTF();
		shippingZip = objectInput.readUTF();

		shippingRegionId = objectInput.readLong();

		shippingCountryId = objectInput.readLong();
		shippingPhoneNumber = objectInput.readUTF();

		commerceShippingMethodId = objectInput.readLong();
		shippingOptionName = objectInput.readUTF();
		carrier = objectInput.readUTF();
		trackingNumber = objectInput.readUTF();
		shippingDate = objectInput.readLong();
		expectedDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceShipmentId);

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

		objectOutput.writeLong(commerceAccountId);

		if (shippingName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingName);
		}

		if (shippingDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingDescription);
		}

		if (shippingStreet1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingStreet1);
		}

		if (shippingStreet2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingStreet2);
		}

		if (shippingStreet3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingStreet3);
		}

		if (shippingCity == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingCity);
		}

		if (shippingZip == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingZip);
		}

		objectOutput.writeLong(shippingRegionId);

		objectOutput.writeLong(shippingCountryId);

		if (shippingPhoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingPhoneNumber);
		}

		objectOutput.writeLong(commerceShippingMethodId);

		if (shippingOptionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shippingOptionName);
		}

		if (carrier == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(carrier);
		}

		if (trackingNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trackingNumber);
		}

		objectOutput.writeLong(shippingDate);
		objectOutput.writeLong(expectedDate);

		objectOutput.writeInt(status);
	}

	public long commerceShipmentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceAccountId;
	public String shippingName;
	public String shippingDescription;
	public String shippingStreet1;
	public String shippingStreet2;
	public String shippingStreet3;
	public String shippingCity;
	public String shippingZip;
	public long shippingRegionId;
	public long shippingCountryId;
	public String shippingPhoneNumber;
	public long commerceShippingMethodId;
	public String shippingOptionName;
	public String carrier;
	public String trackingNumber;
	public long shippingDate;
	public long expectedDate;
	public int status;

}