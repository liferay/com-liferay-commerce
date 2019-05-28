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

import com.liferay.commerce.channel.web.internal.display.context.SiteCommerceChannelTypeDisplayContext;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributor;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
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
	property = "commerce.product.channel.type.jsp.contributor.key=site",
	service = CommerceChannelTypeJSPContributor.class
)
public class SiteCommerceChannelTypeJSPContributor
	implements CommerceChannelTypeJSPContributor {

	@Override
	public void render(
			long commerceChannelId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		SiteCommerceChannelTypeDisplayContext
			siteCommerceChannelTypeDisplayContext =
				new SiteCommerceChannelTypeDisplayContext(
					_commerceChannelModelResourcePermission,
					_commerceChannelService, _commerceChannelTypeRegistry,
					_commerceChannelTypeJSPContributorRegistry,
					_commerceCurrencyService, _groupLocalService,
					httpServletRequest, _itemSelector, _portal);

		httpServletRequest.setAttribute(
			"site.jsp-portletDisplayContext",
			siteCommerceChannelTypeDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/contributor/site.jsp");
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CommerceChannel)"
	)
	private ModelResourcePermission<CommerceChannel>
		_commerceChannelModelResourcePermission;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceChannelTypeJSPContributorRegistry
		_commerceChannelTypeJSPContributorRegistry;

	@Reference
	private CommerceChannelTypeRegistry _commerceChannelTypeRegistry;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.channel.web)"
	)
	private ServletContext _servletContext;

}