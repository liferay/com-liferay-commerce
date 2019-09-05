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

import com.fasterxml.jackson.annotation.JsonCreator;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Luca Pellizzon
 */
public enum ScoreColor {

	BLACK, EMPTY, GREEN, ORANGE, RED, WHITE;

	@JsonCreator
	public static ScoreColor fromValue(String value) {
		if (StringUtils.isBlank(value)) {
			return ScoreColor.EMPTY;
		}

		return ScoreColor.valueOf(value);
	}

}