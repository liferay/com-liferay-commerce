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

package com.liferay.commerce.channel.web.internal.channel.rule;

import com.liferay.commerce.channel.web.internal.display.context.CatalogCPCatalogRuleDisplayContext;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributor;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = "commerce.product.rule.type.jsp.contributor.key=catalogs",
	service = CPRuleTypeJSPContributor.class
)
public class CatalogCPRuleTypeJSPContributor
	implements CPRuleTypeJSPContributor {

	@Override
	public void render(
			long cpRuleId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CatalogCPCatalogRuleDisplayContext catalogCPCatalogRuleDisplayContext =
			new CatalogCPCatalogRuleDisplayContext(
				_commerceCatalogService, _cpRuleService, httpServletRequest,
				_itemSelector, _portletResourcePermission);

		httpServletRequest.setAttribute(
			"catalog.jsp-portletDisplayContext",
			catalogCPCatalogRuleDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/contributor/catalog.jsp");
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CPRuleService _cpRuleService;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.channel.web)"
	)
	private ServletContext _servletContext;

}