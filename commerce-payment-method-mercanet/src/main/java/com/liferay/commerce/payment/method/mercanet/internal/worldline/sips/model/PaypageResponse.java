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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper.ResponseDataDeserializer;

/**
 * @author Luca Pellizzon
 *
 * The result of payment made via the SIPS payment page.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaypageResponse {

	public ResponseData getData() {
		return data;
	}

	public String getEncode() {
		return encode;
	}

	public String getInterFaceVersion() {
		return interFaceVersion;
	}

	public String getSeal() {
		return seal;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setInterFaceVersion(String interFaceVersion) {
		this.interFaceVersion = interFaceVersion;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	@JsonDeserialize(using = ResponseDataDeserializer.class)
	@JsonProperty("Data")
	@JsonUnwrapped
	protected ResponseData data;

	@JsonProperty("Encode")
	protected String encode;

	@JsonProperty("InterfaceVersion")
	protected String interFaceVersion;

	@JsonProperty("Seal")
	protected String seal;

}