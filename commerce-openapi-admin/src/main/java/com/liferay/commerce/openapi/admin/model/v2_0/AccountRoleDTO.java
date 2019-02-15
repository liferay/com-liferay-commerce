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

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "AccountRole")
public class AccountRoleDTO {

	public Map<String, String> getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	public Long getRoleId() {
		return _roleId;
	}

	public Map<String, String> getTitle() {
		return _title;
	}

	public void setDescription(Map<String, String> description) {
		_description = description;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRoleId(Long roleId) {
		_roleId = roleId;
	}

	public void setTitle(Map<String, String> title) {
		_title = title;
	}

	private Map<String, String> _description;
	private String _name;
	private Long _roleId;
	private Map<String, String> _title;

}