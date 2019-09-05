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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Luca Pellizzon
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class CustomerContact extends Contact {

	public String getInitials() {
		return initials;
	}

	public String getLegalId() {
		return legalId;
	}

	public String getPositionOccupied() {
		return positionOccupied;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void setLegalId(String legalId) {
		this.legalId = legalId;
	}

	public void setPositionOccupied(String positionOccupied) {
		this.positionOccupied = positionOccupied;
	}

	protected String initials;
	protected String legalId;
	protected String positionOccupied;

}