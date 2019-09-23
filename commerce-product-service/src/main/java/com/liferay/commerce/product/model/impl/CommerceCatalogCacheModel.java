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

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceCatalog in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceCatalogCacheModel
	implements CacheModel<CommerceCatalog>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceCatalogCacheModel)) {
			return false;
		}

		CommerceCatalogCacheModel commerceCatalogCacheModel =
			(CommerceCatalogCacheModel)obj;

		if (commerceCatalogId == commerceCatalogCacheModel.commerceCatalogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceCatalogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commerceCatalogId=");
		sb.append(commerceCatalogId);
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
		sb.append(", commerceCurrencyCode=");
		sb.append(commerceCurrencyCode);
		sb.append(", catalogDefaultLanguageId=");
		sb.append(catalogDefaultLanguageId);
		sb.append(", system=");
		sb.append(system);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceCatalog toEntityModel() {
		CommerceCatalogImpl commerceCatalogImpl = new CommerceCatalogImpl();

		if (externalReferenceCode == null) {
			commerceCatalogImpl.setExternalReferenceCode("");
		}
		else {
			commerceCatalogImpl.setExternalReferenceCode(externalReferenceCode);
		}

		commerceCatalogImpl.setCommerceCatalogId(commerceCatalogId);
		commerceCatalogImpl.setCompanyId(companyId);
		commerceCatalogImpl.setUserId(userId);

		if (userName == null) {
			commerceCatalogImpl.setUserName("");
		}
		else {
			commerceCatalogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceCatalogImpl.setCreateDate(null);
		}
		else {
			commerceCatalogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceCatalogImpl.setModifiedDate(null);
		}
		else {
			commerceCatalogImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			commerceCatalogImpl.setName("");
		}
		else {
			commerceCatalogImpl.setName(name);
		}

		if (commerceCurrencyCode == null) {
			commerceCatalogImpl.setCommerceCurrencyCode("");
		}
		else {
			commerceCatalogImpl.setCommerceCurrencyCode(commerceCurrencyCode);
		}

		if (catalogDefaultLanguageId == null) {
			commerceCatalogImpl.setCatalogDefaultLanguageId("");
		}
		else {
			commerceCatalogImpl.setCatalogDefaultLanguageId(
				catalogDefaultLanguageId);
		}

		commerceCatalogImpl.setSystem(system);

		commerceCatalogImpl.resetOriginalValues();

		return commerceCatalogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		externalReferenceCode = objectInput.readUTF();

		commerceCatalogId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		commerceCurrencyCode = objectInput.readUTF();
		catalogDefaultLanguageId = objectInput.readUTF();

		system = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(commerceCatalogId);

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

		if (commerceCurrencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(commerceCurrencyCode);
		}

		if (catalogDefaultLanguageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(catalogDefaultLanguageId);
		}

		objectOutput.writeBoolean(system);
	}

	public String externalReferenceCode;
	public long commerceCatalogId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String commerceCurrencyCode;
	public String catalogDefaultLanguageId;
	public boolean system;

}