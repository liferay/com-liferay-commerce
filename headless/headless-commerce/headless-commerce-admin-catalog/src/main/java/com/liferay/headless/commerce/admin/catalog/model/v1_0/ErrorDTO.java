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

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Error")
public class ErrorDTO {

	public Integer getErrorCode() {
		return _errorCode;
	}

	public String getErrorDescription() {
		return _errorDescription;
	}

	public String getMessage() {
		return _message;
	}

	public Integer getStatus() {
		return _status;
	}

	public void setErrorCode(Integer errorCode) {
		_errorCode = errorCode;
	}

	public void setErrorDescription(String errorDescription) {
		_errorDescription = errorDescription;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setStatus(Integer status) {
		_status = status;
	}

	private Integer _errorCode;
	private String _errorDescription;
	private String _message;
	private Integer _status;

}