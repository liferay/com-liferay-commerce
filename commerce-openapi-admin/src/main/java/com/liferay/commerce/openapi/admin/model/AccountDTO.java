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

package com.liferay.commerce.openapi.admin.model;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "Account")
public class AccountDTO {

	public boolean getActive() {
		return _active;
	}

	public boolean getBusiness() {
		return _business;
	}

	public String getEmail() {
		return _email;
	}

	public String[] getEmailAddresses() {
		return _emailAddresses;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public long getId() {
		return _id;
	}

	public long getLogoId() {
		return _logoId;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public boolean getPersonal() {
		return _personal;
	}

	public boolean getRoot() {
		return _root;
	}

	public String getTaxId() {
		return _taxId;
	}

	public String getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public long[] getUserIds() {
		return _userIds;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setBusiness(boolean business) {
		_business = business;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setEmailAddresses(String[] emailAddresses) {
		_emailAddresses = emailAddresses;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public void setPersonal(boolean personal) {
		_personal = personal;
	}

	public void setRoot(boolean root) {
		_root = root;
	}

	public void setTaxId(String taxId) {
		_taxId = taxId;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserIds(long[] userIds) {
		_userIds = userIds;
	}

	private boolean _active;
	private boolean _business;
	private String _email;
	private String[] _emailAddresses;
	private String _externalReferenceCode;
	private long _id;
	private long _logoId;
	private String _name;
	private long _organizationId;
	private boolean _personal;
	private boolean _root;
	private String _taxId;
	private String _type;
	private long _userId;
	private long[] _userIds;

}