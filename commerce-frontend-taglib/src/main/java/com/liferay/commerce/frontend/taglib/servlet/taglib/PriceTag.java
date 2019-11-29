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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.PriceItemRenderer;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.PriceItemRendererUtil;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Gianmarco Brunialti Masera
 */
public class PriceTag extends IncludeTag {

	@Override
	public void cleanUp() {
		super.cleanUp();

		request.removeAttribute("id");
		request.removeAttribute("quantity");
		request.removeAttribute("CPInstanceId");
		request.removeAttribute("additionalDiscountClasses");
		request.removeAttribute("additionalPriceClasses");
		request.removeAttribute("additionalPromoPriceClasses");
	}

	@Override
	public int doEndTag() throws JspException {
		HttpServletResponse response =
			(HttpServletResponse)pageContext.getResponse();

		CPCatalogEntry cpCatalogEntry = _cpContentHelper.getCPCatalogEntry(
			request);

		_priceItemRenderer.render(cpCatalogEntry, request, response);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		_cpContentHelper = ServletContextUtil.getCPContentHelper();
		_priceItemRenderer = PriceItemRendererUtil.getRenderer();

		return super.doStartTag();
	}

	public void setAdditionalDiscountClasses(String additionalDiscountClasses) {
		request.setAttribute(
			"additionalDiscountClasses", additionalDiscountClasses);
	}

	public void setAdditionalPriceClasses(String additionalPriceClasses) {
		request.setAttribute("additionalPriceClasses", additionalPriceClasses);
	}

	public void setAdditionalPromoPriceClasses(
		String additionalPromoPriceClasses) {

		request.setAttribute(
			"additionalPromoPriceClasses", additionalPromoPriceClasses);
	}

	public void setCPInstanceId(long cpInstanceId) {
		request.setAttribute("CPInstanceId", cpInstanceId);
	}

	public void setQuantity(String quantity) {
		request.setAttribute("quantity", quantity);
	}

	private CPContentHelper _cpContentHelper;
	private PriceItemRenderer _priceItemRenderer;

}