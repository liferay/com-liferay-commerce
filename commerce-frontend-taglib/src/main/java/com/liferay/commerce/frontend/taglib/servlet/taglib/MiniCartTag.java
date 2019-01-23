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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;

import javax.portlet.PortletURL;

import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class MiniCartTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		CommerceContext commerceContext = (CommerceContext)request.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

			putValue("isOpen", false);
			putValue("isDisabled", false);

			String checkoutURL = StringPool.BLANK;
			String detailsURL = StringPool.BLANK;

			if (commerceOrder != null) {
				PortletURL commerceCheckoutPortletURL =
					_commerceOrderHttpHelper.getCommerceCheckoutPortletURL(
						request);

				if (commerceCheckoutPortletURL != null) {
					checkoutURL = String.valueOf(commerceCheckoutPortletURL);
				}
			}

			PortletURL commerceCartPortletURL =
				_commerceOrderHttpHelper.getCommerceCartPortletURL(
					request, commerceOrder);

			if (commerceCartPortletURL != null) {
				detailsURL = String.valueOf(commerceCartPortletURL);
			}

			putValue("checkoutUrl", checkoutURL);
			putValue("detailsUrl", detailsURL);

			putValue("productsCount", 0);
			putValue(
				"cartAPI",
				PortalUtil.getPortalURL(request) + "/o/commerce-ui/cart");

			if (commerceOrder != null) {
				putValue("cartId", commerceOrder.getCommerceOrderId());
			}

			putValue("products", new ArrayList<>());
			putValue(
				"spritemap",
				themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

			setTemplateNamespace("Cart.render");
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/mini_cart/Cart.es");
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		_commerceOrderHttpHelper =
			ServletContextUtil.getCommerceOrderHttpHelper();

		super.setPageContext(pageContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(MiniCartTag.class);

	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

}