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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model;

import java.net.URL;

/**
 * @author Luca Pellizzon
 *
 * The server's response to a session initialization request.
 */
public class InitializationResponse {

	public String getErrorFieldName() {
		return errorFieldName;
	}

	public String getRedirectionData() {
		return redirectionData;
	}

	public RedirectionStatusCode getRedirectionStatusCode() {
		return redirectionStatusCode;
	}

	public String getRedirectionStatusMessage() {
		return redirectionStatusMessage;
	}

	public URL getRedirectionUrl() {
		return redirectionUrl;
	}

	public String getRedirectionVersion() {
		return redirectionVersion;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public String getSeal() {
		return seal;
	}

	public void setErrorFieldName(String errorFieldName) {
		this.errorFieldName = errorFieldName;
	}

	public void setRedirectionData(String redirectionData) {
		this.redirectionData = redirectionData;
	}

	public void setRedirectionStatusCode(
		RedirectionStatusCode redirectionStatusCode) {

		this.redirectionStatusCode = redirectionStatusCode;
	}

	public void setRedirectionStatusMessage(String redirectionStatusMessage) {
		this.redirectionStatusMessage = redirectionStatusMessage;
	}

	public void setRedirectionUrl(URL redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}

	public void setRedirectionVersion(String redirectionVersion) {
		this.redirectionVersion = redirectionVersion;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	protected String errorFieldName;
	protected String redirectionData;
	protected RedirectionStatusCode redirectionStatusCode;
	protected String redirectionStatusMessage;
	protected URL redirectionUrl;
	protected String redirectionVersion;
	protected ResponseCode responseCode;
	protected String seal;

}