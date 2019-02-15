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

package com.liferay.commerce.openapi.util;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class Security {

	public List<String> getOAuth2Scopes() {
		return _oAuth2Scopes;
	}

	public void setOAuth2Scopes(List<String> oAuth2Scopes) {
		_oAuth2Scopes = oAuth2Scopes;
	}

	private List<String> _oAuth2Scopes = Collections.emptyList();

}