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

package com.liferay.headless.commerce.admin.catalog.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Category")
public class CategoryDTO {

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getGroupId() {
		return _groupId;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@Nullable
	public String getVocabulary() {
		return _vocabulary;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setGroupId(Long groupId) {
		_groupId = groupId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setVocabulary(String vocabulary) {
		_vocabulary = vocabulary;
	}

	private String _externalReferenceCode;

	@Nullable
	private Long _groupId;

	@Nullable
	private Long _id;

	private String _name;

	@Nullable
	private String _vocabulary;

}