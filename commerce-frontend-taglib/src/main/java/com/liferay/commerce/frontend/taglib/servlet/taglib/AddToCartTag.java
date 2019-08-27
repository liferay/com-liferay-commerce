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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.util.ProductHelperProvider;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 * @author Marco Leo
 */
public class AddToCartTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			CommerceAccount commerceAccount =
				commerceContext.getCommerceAccount();

			Map<String, Object> context = getContext();

			long cpInstanceId = GetterUtil.getLong(context.get("productId"));

			String componentId = (String)context.getOrDefault(
				"id", cpInstanceId + "AddToCartButtonId");

			setComponentId(componentId);

			putValue(
				"cartAPI",
				PortalUtil.getPortalURL(request) + "/o/commerce-ui/cart-item");

			if (commerceAccount != null) {
				putValue("accountId", commerceAccount.getCommerceAccountId());
			}

			CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

			int productOrderQuantity = 0;

			if (commerceOrder != null) {
				putValue("orderId", commerceOrder.getCommerceOrderId());

				List<CommerceOrderItem> commerceOrderItems =
					commerceOrder.getCommerceOrderItems(cpInstanceId);

				productOrderQuantity = commerceOrderItems.size();
			}

			putValue("quantity", productOrderQuantity);

			putValue("editMode", false);

			putValue(
				"settings",
				_productHelper.getProductSettingsModel(cpInstanceId));

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

	public void setCPInstanceId(long cpInstanceId) {
		putValue("productId", cpInstanceId);
	}

	public void setId(String id) {
		putValue("id", id);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		_productHelper = ProductHelperProvider.getProductHelper();
	}

	private static final Log _log = LogFactoryUtil.getLog(AddToCartTag.class);

	private ProductHelper _productHelper;

}