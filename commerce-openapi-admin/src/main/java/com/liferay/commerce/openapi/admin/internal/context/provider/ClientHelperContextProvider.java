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

package com.liferay.commerce.openapi.admin.internal.context.provider;

import com.liferay.commerce.openapi.admin.context.ClientHelper;
import com.liferay.commerce.openapi.admin.context.Pagination;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = ContextProvider.class
)
@Provider
public class ClientHelperContextProvider
	implements ContextProvider<ClientHelper> {

	@Override
	public ClientHelper createContext(Message message) {
		HttpServletRequest httpServletRequest =
			(HttpServletRequest)message.getContextualProperty("HTTP.REQUEST");

		int pageNumber = ParamUtil.getInteger(httpServletRequest, "page", 1);
		int itemsPerPage = ParamUtil.getInteger(
			httpServletRequest, "pageSize", 20);

		Pagination pagination = new Pagination(itemsPerPage, pageNumber);

		String sortString = ParamUtil.getString(
			httpServletRequest, "sortField");
		String sortDir = ParamUtil.getString(httpServletRequest, "sortDir");

		Sort sort = SortFactoryUtil.create(
			StringUtil.trim(sortString), sortDir.equals("desc"));

		if (Validator.isNull(sortString)) {
			sort = null;
		}

		return new ClientHelper(pagination, sort);
	}

}