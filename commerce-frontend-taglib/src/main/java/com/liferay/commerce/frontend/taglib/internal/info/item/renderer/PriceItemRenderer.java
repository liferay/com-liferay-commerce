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

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.util.ItemRendererUtil;

import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = PriceItemRenderer.class)
public class PriceItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "price";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(PriceItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
            throws PortalException {

        Map<String, Object> data = new HashMap<>();

        CommerceContext commerceContext =
                ItemRendererUtil.getCommerceContext(request);
        ThemeDisplay themeDisplay =
                ItemRendererUtil.getThemeDisplay(request);

        long cpInstanceId = Optional.ofNullable(
                (Long) request.getAttribute("CPInstanceId"))
                .orElse(0L);

        int quantity = Optional.ofNullable(
                (Integer) request.getAttribute("quantity"))
                .orElse(1);

        if (quantity <= 0) {
            ProductSettingsModel productSettingsModel =
                    _productHelper.getProductSettingsModel(cpInstanceId);

            quantity = productSettingsModel.getMinQuantity();
        }

        PriceModel priceModel = _productHelper.getPrice(
                cpInstanceId, quantity, commerceContext,
                themeDisplay.getLocale());

        data.put("prices", priceModel);

        data.putAll(_getAdditionalClasses(request));

        return data;
    }

    private Map<String, Object> _getAdditionalClasses(HttpServletRequest request) {
        return new HashMap<String, Object>() {{
            put("additionalPriceClasses",
                    request.getAttribute("additionalPriceClasses"));
            put("additionalDiscountClasses",
                    request.getAttribute("additionalDiscountClasses"));
            put("additionalPromoPriceClasses",
                    request.getAttribute("additionalPromoPriceClasses"));
        }};
    }

    @Reference
    private ProductHelper _productHelper;

}
