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

package com.liferay.commerce.frontend.internal.clay.table;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.frontend.ClayTableDataJSONBuilder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ClayTableDataJSONBuilder.class)
public class ClayTableDataJSONBuilderImpl implements ClayTableDataJSONBuilder {

	@Override
	public String build(
			long groupId, String tableName, List<Object> items,
			HttpServletRequest httpServletRequest)
		throws Exception {

		List<ClayTableRow> clayTableRows = _clayTableUtil.getClayTableRows(
			items, tableName, httpServletRequest, groupId);

		return _OBJECT_MAPPER.writeValueAsString(clayTableRows);
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			disable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	@Reference
	private ClayTableUtil _clayTableUtil;

}