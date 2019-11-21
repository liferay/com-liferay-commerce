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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.GalleryItemRenderer;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.GalleryItemRendererUtil;
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
public class GalleryTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		_cpContentHelper = ServletContextUtil.getCPContentHelper();
		_galleryItemRenderer = GalleryItemRendererUtil.getRenderer();

		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		HttpServletResponse response =
				(HttpServletResponse) pageContext.getResponse();

		CPCatalogEntry cpCatalogEntry =
				_cpContentHelper.getCPCatalogEntry(request);

		_galleryItemRenderer.render(cpCatalogEntry, request, response);

		return super.doEndTag();
	}

	@Override
	public void cleanUp() {
		request.removeAttribute("id");
		request.removeAttribute("cpDefinitionId");
	}

	public void setId(String id) {
		request.setAttribute("id", id);
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		request.setAttribute("cpDefinitionId", cpDefinitionId);
	}

	private GalleryItemRenderer _galleryItemRenderer;

	private CPContentHelper _cpContentHelper;
}