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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinitionLocalization;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CPDefinitionLocalization in entity cache.
 *
 * @author Marco Leo
 * @see CPDefinitionLocalization
 * @generated
 */
@ProviderType
public class CPDefinitionLocalizationCacheModel implements CacheModel<CPDefinitionLocalization>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionLocalizationCacheModel)) {
			return false;
		}

		CPDefinitionLocalizationCacheModel cpDefinitionLocalizationCacheModel = (CPDefinitionLocalizationCacheModel)obj;

		if ((cpDefinitionLocalizationId == cpDefinitionLocalizationCacheModel.cpDefinitionLocalizationId) &&
				(mvccVersion == cpDefinitionLocalizationCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, cpDefinitionLocalizationId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", cpDefinitionLocalizationId=");
		sb.append(cpDefinitionLocalizationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", shortDescription=");
		sb.append(shortDescription);
		sb.append(", description=");
		sb.append(description);
		sb.append(", metaTitle=");
		sb.append(metaTitle);
		sb.append(", metaDescription=");
		sb.append(metaDescription);
		sb.append(", metaKeywords=");
		sb.append(metaKeywords);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinitionLocalization toEntityModel() {
		CPDefinitionLocalizationImpl cpDefinitionLocalizationImpl = new CPDefinitionLocalizationImpl();

		cpDefinitionLocalizationImpl.setMvccVersion(mvccVersion);
		cpDefinitionLocalizationImpl.setCpDefinitionLocalizationId(cpDefinitionLocalizationId);
		cpDefinitionLocalizationImpl.setCompanyId(companyId);
		cpDefinitionLocalizationImpl.setCPDefinitionId(CPDefinitionId);

		if (languageId == null) {
			cpDefinitionLocalizationImpl.setLanguageId("");
		}
		else {
			cpDefinitionLocalizationImpl.setLanguageId(languageId);
		}

		if (name == null) {
			cpDefinitionLocalizationImpl.setName("");
		}
		else {
			cpDefinitionLocalizationImpl.setName(name);
		}

		if (shortDescription == null) {
			cpDefinitionLocalizationImpl.setShortDescription("");
		}
		else {
			cpDefinitionLocalizationImpl.setShortDescription(shortDescription);
		}

		if (description == null) {
			cpDefinitionLocalizationImpl.setDescription("");
		}
		else {
			cpDefinitionLocalizationImpl.setDescription(description);
		}

		if (metaTitle == null) {
			cpDefinitionLocalizationImpl.setMetaTitle("");
		}
		else {
			cpDefinitionLocalizationImpl.setMetaTitle(metaTitle);
		}

		if (metaDescription == null) {
			cpDefinitionLocalizationImpl.setMetaDescription("");
		}
		else {
			cpDefinitionLocalizationImpl.setMetaDescription(metaDescription);
		}

		if (metaKeywords == null) {
			cpDefinitionLocalizationImpl.setMetaKeywords("");
		}
		else {
			cpDefinitionLocalizationImpl.setMetaKeywords(metaKeywords);
		}

		cpDefinitionLocalizationImpl.resetOriginalValues();

		return cpDefinitionLocalizationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		cpDefinitionLocalizationId = objectInput.readLong();

		companyId = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();
		languageId = objectInput.readUTF();
		name = objectInput.readUTF();
		shortDescription = objectInput.readUTF();
		description = objectInput.readUTF();
		metaTitle = objectInput.readUTF();
		metaDescription = objectInput.readUTF();
		metaKeywords = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(cpDefinitionLocalizationId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(CPDefinitionId);

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (shortDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shortDescription);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (metaTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaTitle);
		}

		if (metaDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaDescription);
		}

		if (metaKeywords == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaKeywords);
		}
	}

	public long mvccVersion;
	public long cpDefinitionLocalizationId;
	public long companyId;
	public long CPDefinitionId;
	public String languageId;
	public String name;
	public String shortDescription;
	public String description;
	public String metaTitle;
	public String metaDescription;
	public String metaKeywords;
}