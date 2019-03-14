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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.util.Date;
import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Attachment")
public class AttachmentDTO {

	@Nullable
	public String getAttachment() {
		return _attachment;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@Nullable
	public Date getDisplayDate() {
		return _displayDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@Nullable
	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public Map<String, String> getOptions() {
		return _options;
	}

	@Nullable
	public Double getPriority() {
		return _priority;
	}

	@Nullable
	public String getSrc() {
		return _src;
	}

	@Nullable
	public Map<String, String> getTitle() {
		return _title;
	}

	@Nullable
	public Integer getType() {
		return _type;
	}

	@Nullable
	public Boolean isNeverExpire() {
		return _neverExpire;
	}

	public void setAttachment(String attachment) {
		_attachment = attachment;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setNeverExpire(Boolean neverExpire) {
		_neverExpire = neverExpire;
	}

	public void setOptions(Map<String, String> options) {
		_options = options;
	}

	public void setPriority(Double priority) {
		_priority = priority;
	}

	public void setSrc(String src) {
		_src = src;
	}

	public void setTitle(Map<String, String> title) {
		_title = title;
	}

	public void setType(Integer type) {
		_type = type;
	}

	@Nullable
	private String _attachment;

	@Nullable
	private Date _displayDate;

	@Nullable
	private Date _expirationDate;

	private String _externalReferenceCode;

	@Nullable
	private Long _id;

	@Nullable
	private Boolean _neverExpire;

	@Nullable
	private Map<String, String> _options;

	@Nullable
	private Double _priority;

	@Nullable
	private String _src;

	@Nullable
	private Map<String, String> _title;

	@Nullable
	private Integer _type;

}