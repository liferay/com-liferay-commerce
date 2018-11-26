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

package com.liferay.commerce.cart.taglib.servlet.taglib;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Marco Leo
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
			putValue("detailsUrl", "");
			putValue("productsAmount", 0);

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
		return "commerce-cart-taglib/mini_cart/Cart.es";
	}

	private static final Log _log = LogFactoryUtil.getLog(MiniCartTag.class);

}