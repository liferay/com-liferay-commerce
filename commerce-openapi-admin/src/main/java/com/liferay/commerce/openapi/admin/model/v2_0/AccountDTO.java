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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Account")
public class AccountDTO {

	@Nullable
	public AddressDTO[] getAddresses() {
		return _addresses;
	}

	public String[] getEmailAddresses() {
		return _emailAddresses;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Long getLogoId() {
		return _logoId;
	}

	public String getName() {
		return _name;
	}

	@Nullable
	public Long getOrganizationId() {
		return _organizationId;
	}

	public String getTaxId() {
		return _taxId;
	}

	@Nullable
	public String getType() {
		return _type;
	}

	public Long[] getUserIds() {
		return _userIds;
	}

	@Nullable
	public Boolean isBusiness() {
		return _business;
	}

	@Nullable
	public Boolean isPersonal() {
		return _personal;
	}

	@Nullable
	public Boolean isRoot() {
		return _root;
	}

	public void setAddresses(AddressDTO[] addresses) {
		_addresses = addresses;
	}

	public void setBusiness(Boolean business) {
		_business = business;
	}

	public void setEmailAddresses(String[] emailAddresses) {
		_emailAddresses = emailAddresses;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setLogoId(Long logoId) {
		_logoId = logoId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationId(Long organizationId) {
		_organizationId = organizationId;
	}

	public void setPersonal(Boolean personal) {
		_personal = personal;
	}

	public void setRoot(Boolean root) {
		_root = root;
	}

	public void setTaxId(String taxId) {
		_taxId = taxId;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUserIds(Long[] userIds) {
		_userIds = userIds;
	}

	@Nullable
	private AddressDTO[] _addresses;

	@Nullable
	private Boolean _business;

	private String[] _emailAddresses;
	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Long _logoId;

	private String _name;

	@Nullable
	private Long _organizationId;

	@Nullable
	private Boolean _personal;

	@Nullable
	private Boolean _root;

	private String _taxId;

	@Nullable
	private String _type;

	private Long[] _userIds;

}