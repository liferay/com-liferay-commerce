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

package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = MiniCartItemRenderer.class)
public class MiniCartItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "mini_cart";

    private static final String API_ENDPOINT =
            "/o/commerce-ui/cart";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(MiniCartItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
            throws PortalException {

        Map<String, Object> data = new HashMap<>();

        CommerceContext commerceContext = ItemRendererUtil.getCommerceContext(request);
        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

        if (Validator.isNotNull(commerceOrder)) {
            data.put("orderId", commerceOrder.getCommerceOrderId());
            data.put("commerceAccountId", commerceOrder.getCommerceAccountId());
            data.put("workflowStatus", commerceOrder.getStatus());
        } else {
            CommerceAccount commerceAccount =
                    commerceContext.getCommerceAccount();

            if (commerceAccount != null) {
                data.put("commerceAccountId", commerceAccount.getCommerceAccountId());
            }
        }

        PortletURL commerceCheckoutPortletURL =
                _commerceOrderHttpHelper.getCommerceCheckoutPortletURL(request);

        String checkoutURL = StringPool.BLANK;

        if (commerceCheckoutPortletURL != null) {
            checkoutURL = String.valueOf(commerceCheckoutPortletURL);
        }

        data.put("checkoutUrl", checkoutURL);

        String detailsURL = StringPool.BLANK;

        PortletURL commerceCartPortletURL =
                _commerceOrderHttpHelper.getCommerceCartPortletURL(
                        request, commerceOrder);

        if (commerceCartPortletURL != null) {
            detailsURL = String.valueOf(commerceCartPortletURL);
        }

        data.put("detailsUrl", detailsURL);

        data.put("isDisabled", false);
        data.put("isOpen", false);
        data.put("products", Collections.emptyList());
        data.put("productsCount", 0);

        data.put("cartAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
        data.put("spritemap", ItemRendererUtil.getSpritemapPath(request));

        return data;
    }

    @Reference
    private CommerceOrderHttpHelper _commerceOrderHttpHelper;
}
