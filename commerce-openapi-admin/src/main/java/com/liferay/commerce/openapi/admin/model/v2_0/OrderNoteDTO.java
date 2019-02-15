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
@JacksonXmlRootElement(localName = "OrderNote")
public class OrderNoteDTO {

	public String getAuthor() {
		return _author;
	}

	public Long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public String getContent() {
		return _content;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public Long getId() {
		return _id;
	}

	public Boolean isRestricted() {
		return _restricted;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public void setCommerceOrderId(Long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setRestricted(Boolean restricted) {
		_restricted = restricted;
	}

	private String _author;
	private Long _commerceOrderId;
	private String _content;
	private String _externalReferenceCode;
	private Long _id;
	private Boolean _restricted;

}