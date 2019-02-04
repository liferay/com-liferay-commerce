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

import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

// import java.util.ArrayList;
import java.util.HashMap;
// import java.util.List;
import java.util.Map;

/**
 * @author Fabio Diego Mastrorilli
 */
public class AddToCartTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

			setComponentId("TemporaryAddToCartButtonId");

			putValue(
				"cartAPI",
				PortalUtil.getPortalURL(request) + "/o/commerce-ui/cart-item");
			putValue("productId", "ASD123456");
			putValue("accountId", commerceAccount.getCommerceAccountId());
			putValue("quantity", 0);
			putValue("editMode", false);

			Map<String, Object> multiMap = new HashMap<String, Object>();
			multiMap.put("maxQuantity", new Integer(1000));
			multiMap.put("minQuantity", new Integer(1));
			multiMap.put("multipleQuantities", new Integer(1));

			putValue("settings", multiMap);

			setTemplateNamespace("AddToCartButton.render");
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
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
			"commerce-frontend-taglib/add_to_cart/AddToCartButton.es");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountSelectorTag.class);

}