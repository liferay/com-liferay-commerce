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

import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;

/**
 * @author Marco Leo
 */
public class MiniCartTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		putValue("isOpen", false);
		putValue("isDisabled", false);
		putValue("detailsUrl", "");
		putValue("productsAmount", 0);
		putValue("products", new ArrayList<>());
		putValue(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

		setTemplateNamespace("Cart.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		return "commerce-cart-taglib/mini_cart/Cart.es";
	}

}