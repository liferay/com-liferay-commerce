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
@JacksonXmlRootElement(localName = "AccountMember")
public class AccountMemberDTO {

	public Long getCommerceAccountId() {
		return _commerceAccountId;
	}

	public String getName() {
		return _name;
	}

	public AccountRoleDTO[] getRoles() {
		return _roles;
	}

	public Long getUserId() {
		return _userId;
	}

	public void setCommerceAccountId(Long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRoles(AccountRoleDTO[] roles) {
		_roles = roles;
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	private Long _commerceAccountId;
	private String _name;
	private AccountRoleDTO[] _roles;
	private Long _userId;

}