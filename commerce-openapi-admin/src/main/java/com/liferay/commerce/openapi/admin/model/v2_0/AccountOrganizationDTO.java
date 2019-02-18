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
@JacksonXmlRootElement(localName = "AccountOrganization")
public class AccountOrganizationDTO {

	public Long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Nullable
	public String getName() {
		return _name;
	}

	public Long getOrganizationId() {
		return _organizationId;
	}

	@Nullable
	public String getTreePath() {
		return _treePath;
	}

	public void setCommerceAccountId(Long commerceAccountId) {
		_commerceAccountId = commerceAccountId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationId(Long organizationId) {
		_organizationId = organizationId;
	}

	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	private Long _commerceAccountId;

	@Nullable
	private String _name;

	private Long _organizationId;

	@Nullable
	private String _treePath;

}