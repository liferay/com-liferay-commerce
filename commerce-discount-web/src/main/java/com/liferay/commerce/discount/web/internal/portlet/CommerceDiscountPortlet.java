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

package com.liferay.commerce.discount.web.internal.portlet;

import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.constants.CommerceDiscountPortletKeys;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.discount.target.CommerceDiscountTargetRegistry;
import com.liferay.commerce.discount.web.internal.display.context.CommerceDiscountDisplayContext;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-discount",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Discounts",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceDiscountPortletKeys.COMMERCE_DISCOUNT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = {CommerceDiscountPortlet.class, Portlet.class}
)
public class CommerceDiscountPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		CommerceDiscountDisplayContext commerceDiscountDisplayContext =
			new CommerceDiscountDisplayContext(
				_commerceChannelRelService, _commerceChannelService,
				_commerceCurrencyLocalService,
				_commerceDiscountModelResourcePermission,
				_commerceDiscountService, _commerceDiscountTargetRegistry,
				_commerceDiscountCommerceAccountGroupRelService,
				_portal.getHttpServletRequest(renderRequest), _itemSelector);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, commerceDiscountDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private CommerceChannelRelService _commerceChannelRelService;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscount)"
	)
	private ModelResourcePermission<CommerceDiscount>
		_commerceDiscountModelResourcePermission;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private CommerceDiscountTargetRegistry _commerceDiscountTargetRegistry;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

}