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

package com.liferay.commerce.machine.learning.forecast.alert.model.impl;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceMLForecastAlertEntry in entity cache.
 *
 * @author Riccardo Ferrari
 * @generated
 */
public class CommerceMLForecastAlertEntryCacheModel
	implements CacheModel<CommerceMLForecastAlertEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceMLForecastAlertEntryCacheModel)) {
			return false;
		}

		CommerceMLForecastAlertEntryCacheModel
			commerceMLForecastAlertEntryCacheModel =
				(CommerceMLForecastAlertEntryCacheModel)obj;

		if (commerceMLForecastAlertEntryId ==
				commerceMLForecastAlertEntryCacheModel.
					commerceMLForecastAlertEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceMLForecastAlertEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commerceMLForecastAlertEntryId=");
		sb.append(commerceMLForecastAlertEntryId);
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
		sb.append(", actual=");
		sb.append(actual);
		sb.append(", forecast=");
		sb.append(forecast);
		sb.append(", timestamp=");
		sb.append(timestamp);
		sb.append(", relativeChange=");
		sb.append(relativeChange);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceMLForecastAlertEntry toEntityModel() {
		CommerceMLForecastAlertEntryImpl commerceMLForecastAlertEntryImpl =
			new CommerceMLForecastAlertEntryImpl();

		if (uuid == null) {
			commerceMLForecastAlertEntryImpl.setUuid("");
		}
		else {
			commerceMLForecastAlertEntryImpl.setUuid(uuid);
		}

		commerceMLForecastAlertEntryImpl.setCommerceMLForecastAlertEntryId(
			commerceMLForecastAlertEntryId);
		commerceMLForecastAlertEntryImpl.setCompanyId(companyId);
		commerceMLForecastAlertEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceMLForecastAlertEntryImpl.setUserName("");
		}
		else {
			commerceMLForecastAlertEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceMLForecastAlertEntryImpl.setCreateDate(null);
		}
		else {
			commerceMLForecastAlertEntryImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceMLForecastAlertEntryImpl.setModifiedDate(null);
		}
		else {
			commerceMLForecastAlertEntryImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceMLForecastAlertEntryImpl.setCommerceAccountId(
			commerceAccountId);
		commerceMLForecastAlertEntryImpl.setActual(actual);
		commerceMLForecastAlertEntryImpl.setForecast(forecast);

		if (timestamp == Long.MIN_VALUE) {
			commerceMLForecastAlertEntryImpl.setTimestamp(null);
		}
		else {
			commerceMLForecastAlertEntryImpl.setTimestamp(new Date(timestamp));
		}

		commerceMLForecastAlertEntryImpl.setRelativeChange(relativeChange);
		commerceMLForecastAlertEntryImpl.setStatus(status);

		commerceMLForecastAlertEntryImpl.resetOriginalValues();

		return commerceMLForecastAlertEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		commerceMLForecastAlertEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceAccountId = objectInput.readLong();

		actual = objectInput.readDouble();

		forecast = objectInput.readDouble();
		timestamp = objectInput.readLong();

		relativeChange = objectInput.readDouble();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(commerceMLForecastAlertEntryId);

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

		objectOutput.writeDouble(actual);

		objectOutput.writeDouble(forecast);
		objectOutput.writeLong(timestamp);

		objectOutput.writeDouble(relativeChange);

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long commerceMLForecastAlertEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceAccountId;
	public double actual;
	public double forecast;
	public long timestamp;
	public double relativeChange;
	public int status;

}