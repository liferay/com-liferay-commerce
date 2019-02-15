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

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Account")
public class AccountDTO {

	public AddressDTO[] getAddresses() {
		return _addresses;
	}

	public String[] getEmailAddresses() {
		return _emailAddresses;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Long getId() {
		return _id;
	}

	public Long getLogoId() {
		return _logoId;
	}

	public String getName() {
		return _name;
	}

	public AccountOrganizationDTO[] getOrganizations() {
		return _organizations;
	}

	public String getTaxId() {
		return _taxId;
	}

	public Integer getType() {
		return _type;
	}

	public AccountMemberDTO[] getUsers() {
		return _users;
	}

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

	private AddressDTO[] _addresses;
	private String[] _emailAddresses;
	private String _externalReferenceCode;
	private Long _id;
	private Long _logoId;
	private String _name;
	private AccountOrganizationDTO[] _organizations;
	private Boolean _root;
	private String _taxId;
	private Integer _type;
	private AccountMemberDTO[] _users;

}