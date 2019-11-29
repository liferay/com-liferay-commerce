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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.CompareCheckboxItemRenderer;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.CompareCheckboxItemRendererUtil;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Gianmarco Brunialti Masera
 */
public class CompareCheckboxTag extends IncludeTag {

	@Override
	public void cleanUp() {
		super.cleanUp();

		request.removeAttribute("componentId");
		request.removeAttribute("productId");
	}

	@Override
	public int doEndTag() throws JspException {
		HttpServletResponse response =
			(HttpServletResponse)pageContext.getResponse();

		CPCatalogEntry cpCatalogEntry = _cpContentHelper.getCPCatalogEntry(
			request);

		_compareCheckboxItemRenderer.render(cpCatalogEntry, request, response);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		_cpContentHelper = ServletContextUtil.getCPContentHelper();
		_compareCheckboxItemRenderer =
			CompareCheckboxItemRendererUtil.getRenderer();

		return super.doStartTag();
	}

	public void setComponentId(String componentId) {
		request.setAttribute("componentId", componentId);
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		request.setAttribute("cpDefinitionId", cpDefinitionId);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
	}

	private CompareCheckboxItemRenderer _compareCheckboxItemRenderer;
	private CPContentHelper _cpContentHelper;

}