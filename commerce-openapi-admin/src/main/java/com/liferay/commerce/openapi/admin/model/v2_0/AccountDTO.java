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

	@Nullable
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
	public AccountOrganizationDTO[] getOrganizations() {
		return _organizations;
	}

	@Nullable
	public String getTaxId() {
		return _taxId;
	}

	@Nullable
	public Integer getType() {
		return _type;
	}

	@Nullable
	public AccountMemberDTO[] getUsers() {
		return _users;
	}

	@Nullable
	public Boolean isRoot() {
		return _root;
	}

	public void setAddresses(AddressDTO[] addresses) {
		_addresses = addresses;
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

	public void setOrganizations(AccountOrganizationDTO[] organizations) {
		_organizations = organizations;
	}

	public void setRoot(Boolean root) {
		_root = root;
	}

	public void setTaxId(String taxId) {
		_taxId = taxId;
	}

	public void setType(Integer type) {
		_type = type;
	}

	public void setUsers(AccountMemberDTO[] users) {
		_users = users;
	}

	@Nullable
	private AddressDTO[] _addresses;

	@Nullable
	private String[] _emailAddresses;

	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Long _logoId;

	private String _name;

	@Nullable
	private AccountOrganizationDTO[] _organizations;

	@Nullable
	private Boolean _root;

	@Nullable
	private String _taxId;

	@Nullable
	private Integer _type;

	@Nullable
	private AccountMemberDTO[] _users;

}