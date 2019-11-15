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

package com.liferay.commerce.data.integration.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceDataIntegrationProcess in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceDataIntegrationProcessCacheModel
	implements CacheModel<CommerceDataIntegrationProcess>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceDataIntegrationProcessCacheModel)) {
			return false;
		}

		CommerceDataIntegrationProcessCacheModel
			commerceDataIntegrationProcessCacheModel =
				(CommerceDataIntegrationProcessCacheModel)obj;

		if (commerceDataIntegrationProcessId ==
				commerceDataIntegrationProcessCacheModel.
					commerceDataIntegrationProcessId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceDataIntegrationProcessId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{commerceDataIntegrationProcessId=");
		sb.append(commerceDataIntegrationProcessId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", system=");
		sb.append(system);
		sb.append(", active=");
		sb.append(active);
		sb.append(", cronExpression=");
		sb.append(cronExpression);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDataIntegrationProcess toEntityModel() {
		CommerceDataIntegrationProcessImpl commerceDataIntegrationProcessImpl =
			new CommerceDataIntegrationProcessImpl();

		commerceDataIntegrationProcessImpl.setCommerceDataIntegrationProcessId(
			commerceDataIntegrationProcessId);
		commerceDataIntegrationProcessImpl.setCompanyId(companyId);
		commerceDataIntegrationProcessImpl.setUserId(userId);

		if (userName == null) {
			commerceDataIntegrationProcessImpl.setUserName("");
		}
		else {
			commerceDataIntegrationProcessImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessImpl.setCreateDate(null);
		}
		else {
			commerceDataIntegrationProcessImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessImpl.setModifiedDate(null);
		}
		else {
			commerceDataIntegrationProcessImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		if (name == null) {
			commerceDataIntegrationProcessImpl.setName("");
		}
		else {
			commerceDataIntegrationProcessImpl.setName(name);
		}

		if (type == null) {
			commerceDataIntegrationProcessImpl.setType("");
		}
		else {
			commerceDataIntegrationProcessImpl.setType(type);
		}

		if (typeSettings == null) {
			commerceDataIntegrationProcessImpl.setTypeSettings("");
		}
		else {
			commerceDataIntegrationProcessImpl.setTypeSettings(typeSettings);
		}

		commerceDataIntegrationProcessImpl.setSystem(system);
		commerceDataIntegrationProcessImpl.setActive(active);

		if (cronExpression == null) {
			commerceDataIntegrationProcessImpl.setCronExpression("");
		}
		else {
			commerceDataIntegrationProcessImpl.setCronExpression(
				cronExpression);
		}

		if (startDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessImpl.setStartDate(null);
		}
		else {
			commerceDataIntegrationProcessImpl.setStartDate(
				new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			commerceDataIntegrationProcessImpl.setEndDate(null);
		}
		else {
			commerceDataIntegrationProcessImpl.setEndDate(new Date(endDate));
		}

		commerceDataIntegrationProcessImpl.resetOriginalValues();

		return commerceDataIntegrationProcessImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceDataIntegrationProcessId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();
		typeSettings = objectInput.readUTF();

		system = objectInput.readBoolean();

		active = objectInput.readBoolean();
		cronExpression = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceDataIntegrationProcessId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (typeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeSettings);
		}

		objectOutput.writeBoolean(system);

		objectOutput.writeBoolean(active);

		if (cronExpression == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cronExpression);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
	}

	public long commerceDataIntegrationProcessId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String type;
	public String typeSettings;
	public boolean system;
	public boolean active;
	public String cronExpression;
	public long startDate;
	public long endDate;

}