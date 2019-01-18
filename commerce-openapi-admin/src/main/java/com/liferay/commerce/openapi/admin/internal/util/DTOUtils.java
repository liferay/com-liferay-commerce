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

package com.liferay.commerce.openapi.admin.internal.util;

import com.liferay.commerce.openapi.admin.model.WebSiteDTO;
import com.liferay.portal.kernel.model.Group;

import java.util.Locale;

/**
 * @author Zoltán Takács
 */
public class DTOUtils {

	public static WebSiteDTO modelToDTO(Group group, Locale locale) {
		WebSiteDTO webSiteDTO = new WebSiteDTO();

		webSiteDTO.setDescription(group.getDescription(locale));
		webSiteDTO.setId(group.getGroupId());
		webSiteDTO.setName(group.getName(locale));

		return webSiteDTO;
	}

	private DTOUtils() {
	}

}