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

package com.liferay.commerce.channel.web.internal.channel;

import com.liferay.commerce.channel.web.internal.display.context.CatalogCommerceChannelTypeDisplayContext;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributor;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;

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
	property = "commerce.product.channel.type.jsp.contributor.key=catalog",
	service = CommerceChannelTypeJSPContributor.class
)
public class CatalogCommerceChannelTypeJSPContributor
	implements CommerceChannelTypeJSPContributor {

	@Override
	public void render(
			long commerceChannelId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CatalogCommerceChannelTypeDisplayContext
			catalogCommerceChannelTypeDisplayContext =
				new CatalogCommerceChannelTypeDisplayContext(
					_commerceCatalogService, _commerceChannelService,
					_commerceChannelTypeRegistry,
					_commerceChannelTypeJSPContributorRegistry,
					httpServletRequest, _itemSelector, _portal,
					_portletResourcePermission);

		httpServletRequest.setAttribute(
			"catalog.jsp-portletDisplayContext",
			catalogCommerceChannelTypeDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/channel/contributor/catalog.jsp");
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceChannelTypeJSPContributorRegistry
		_commerceChannelTypeJSPContributorRegistry;

	@Reference
	private CommerceChannelTypeRegistry _commerceChannelTypeRegistry;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.catalog.web)"
	)
	private ServletContext _servletContext;

}