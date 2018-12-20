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

package com.liferay.commerce.account.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommerceAccount}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccount
 * @generated
 */
@ProviderType
public class CommerceAccountWrapper implements CommerceAccount,
	ModelWrapper<CommerceAccount> {
	public CommerceAccountWrapper(CommerceAccount commerceAccount) {
		_commerceAccount = commerceAccount;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccount.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("commerceAccountId", getCommerceAccountId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("parentCommerceAccountId", getParentCommerceAccountId());
		attributes.put("taxId", getTaxId());
		attributes.put("active", isActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String externalReferenceCode = (String)attributes.get(
				"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long commerceAccountId = (Long)attributes.get("commerceAccountId");

		if (commerceAccountId != null) {
			setCommerceAccountId(commerceAccountId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long parentCommerceAccountId = (Long)attributes.get(
				"parentCommerceAccountId");

		if (parentCommerceAccountId != null) {
			setParentCommerceAccountId(parentCommerceAccountId);
		}

		String taxId = (String)attributes.get("taxId");

		if (taxId != null) {
			setTaxId(taxId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public Object clone() {
		return new CommerceAccountWrapper((CommerceAccount)_commerceAccount.clone());
	}

	@Override
	public int compareTo(CommerceAccount commerceAccount) {
		return _commerceAccount.compareTo(commerceAccount);
	}

	/**
	* Returns the active of this commerce account.
	*
	* @return the active of this commerce account
	*/
	@Override
	public boolean getActive() {
		return _commerceAccount.getActive();
	}

	/**
	* Returns the commerce account ID of this commerce account.
	*
	* @return the commerce account ID of this commerce account
	*/
	@Override
	public long getCommerceAccountId() {
		return _commerceAccount.getCommerceAccountId();
	}

	/**
	* Returns the company ID of this commerce account.
	*
	* @return the company ID of this commerce account
	*/
	@Override
	public long getCompanyId() {
		return _commerceAccount.getCompanyId();
	}

	/**
	* Returns the create date of this commerce account.
	*
	* @return the create date of this commerce account
	*/
	@Override
	public Date getCreateDate() {
		return _commerceAccount.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceAccount.getExpandoBridge();
	}

	/**
	* Returns the external reference code of this commerce account.
	*
	* @return the external reference code of this commerce account
	*/
	@Override
	public String getExternalReferenceCode() {
		return _commerceAccount.getExternalReferenceCode();
	}

	/**
	* Returns the modified date of this commerce account.
	*
	* @return the modified date of this commerce account
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceAccount.getModifiedDate();
	}

	/**
	* Returns the name of this commerce account.
	*
	* @return the name of this commerce account
	*/
	@Override
	public String getName() {
		return _commerceAccount.getName();
	}

	/**
	* Returns the parent commerce account ID of this commerce account.
	*
	* @return the parent commerce account ID of this commerce account
	*/
	@Override
	public long getParentCommerceAccountId() {
		return _commerceAccount.getParentCommerceAccountId();
	}

	/**
	* Returns the primary key of this commerce account.
	*
	* @return the primary key of this commerce account
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceAccount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAccount.getPrimaryKeyObj();
	}

	/**
	* Returns the tax ID of this commerce account.
	*
	* @return the tax ID of this commerce account
	*/
	@Override
	public String getTaxId() {
		return _commerceAccount.getTaxId();
	}

	/**
	* Returns the user ID of this commerce account.
	*
	* @return the user ID of this commerce account
	*/
	@Override
	public long getUserId() {
		return _commerceAccount.getUserId();
	}

	/**
	* Returns the user name of this commerce account.
	*
	* @return the user name of this commerce account
	*/
	@Override
	public String getUserName() {
		return _commerceAccount.getUserName();
	}

	/**
	* Returns the user uuid of this commerce account.
	*
	* @return the user uuid of this commerce account
	*/
	@Override
	public String getUserUuid() {
		return _commerceAccount.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commerceAccount.hashCode();
	}

	/**
	* Returns <code>true</code> if this commerce account is active.
	*
	* @return <code>true</code> if this commerce account is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _commerceAccount.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceAccount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceAccount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceAccount.isNew();
	}

	@Override
	public void persist() {
		_commerceAccount.persist();
	}

	/**
	* Sets whether this commerce account is active.
	*
	* @param active the active of this commerce account
	*/
	@Override
	public void setActive(boolean active) {
		_commerceAccount.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceAccount.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce account ID of this commerce account.
	*
	* @param commerceAccountId the commerce account ID of this commerce account
	*/
	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		_commerceAccount.setCommerceAccountId(commerceAccountId);
	}

	/**
	* Sets the company ID of this commerce account.
	*
	* @param companyId the company ID of this commerce account
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceAccount.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce account.
	*
	* @param createDate the create date of this commerce account
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceAccount.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceAccount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceAccount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceAccount.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the external reference code of this commerce account.
	*
	* @param externalReferenceCode the external reference code of this commerce account
	*/
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_commerceAccount.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	* Sets the modified date of this commerce account.
	*
	* @param modifiedDate the modified date of this commerce account
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceAccount.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce account.
	*
	* @param name the name of this commerce account
	*/
	@Override
	public void setName(String name) {
		_commerceAccount.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_commerceAccount.setNew(n);
	}

	/**
	* Sets the parent commerce account ID of this commerce account.
	*
	* @param parentCommerceAccountId the parent commerce account ID of this commerce account
	*/
	@Override
	public void setParentCommerceAccountId(long parentCommerceAccountId) {
		_commerceAccount.setParentCommerceAccountId(parentCommerceAccountId);
	}

	/**
	* Sets the primary key of this commerce account.
	*
	* @param primaryKey the primary key of this commerce account
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceAccount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceAccount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the tax ID of this commerce account.
	*
	* @param taxId the tax ID of this commerce account
	*/
	@Override
	public void setTaxId(String taxId) {
		_commerceAccount.setTaxId(taxId);
	}

	/**
	* Sets the user ID of this commerce account.
	*
	* @param userId the user ID of this commerce account
	*/
	@Override
	public void setUserId(long userId) {
		_commerceAccount.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce account.
	*
	* @param userName the user name of this commerce account
	*/
	@Override
	public void setUserName(String userName) {
		_commerceAccount.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce account.
	*
	* @param userUuid the user uuid of this commerce account
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_commerceAccount.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceAccount> toCacheModel() {
		return _commerceAccount.toCacheModel();
	}

	@Override
	public CommerceAccount toEscapedModel() {
		return new CommerceAccountWrapper(_commerceAccount.toEscapedModel());
	}

	@Override
	public String toString() {
		return _commerceAccount.toString();
	}

	@Override
	public CommerceAccount toUnescapedModel() {
		return new CommerceAccountWrapper(_commerceAccount.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _commerceAccount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceAccountWrapper)) {
			return false;
		}

		CommerceAccountWrapper commerceAccountWrapper = (CommerceAccountWrapper)obj;

		if (Objects.equals(_commerceAccount,
					commerceAccountWrapper._commerceAccount)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceAccount getWrappedModel() {
		return _commerceAccount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceAccount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceAccount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceAccount.resetOriginalValues();
	}

	private final CommerceAccount _commerceAccount;
}