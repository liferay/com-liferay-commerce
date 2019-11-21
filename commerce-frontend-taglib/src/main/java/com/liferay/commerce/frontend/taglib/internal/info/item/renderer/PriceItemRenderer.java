/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = PriceItemRenderer.class)
public class PriceItemRenderer implements InfoItemRenderer<CPCatalogEntry> {

    private static final String COMPONENT_NAME = "price";

    private static final boolean IS_WRAPPER = true;
    private static final boolean RENDER_JAVASCRIPT = true;
    private static final boolean POSITION_INLINE = true;

    @Override
    public void render(CPCatalogEntry cpCatalogEntry,
                       HttpServletRequest request,
                       HttpServletResponse response) {

        try {
            Writer writer = response.getWriter();
            Map<String, Object> data = getRenderingData(request);
            ComponentDescriptor soyComponentDescriptor = getDescriptor(request);

            _soyRenderer.renderSoyComponent(
                    request, writer, soyComponentDescriptor, data);
        } catch (Exception e) {
            _log.error(e, e);
        }
    }

    private Map<String, Object> getRenderingData(HttpServletRequest request)
            throws PortalException {

        Map<String, Object> data = new HashMap<>();

        CommerceContext commerceContext = InfoItemRendererUtil.getCommerceContext(request);
        ThemeDisplay themeDisplay = InfoItemRendererUtil.getThemeDisplay(request);

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

        data.putAll(getAdditionalClasses(request));

        return data;
    }

    private Map<String, Object> getAdditionalClasses(HttpServletRequest request) {
        return new HashMap<String, Object>() {{
            put("additionalPriceClasses", request.getAttribute("additionalPriceClasses"));
            put("additionalDiscountClasses", request.getAttribute("additionalDiscountClasses"));
            put("additionalPromoPriceClasses", request.getAttribute("additionalPromoPriceClasses"));
        }};
    }

    private ComponentDescriptor getDescriptor(HttpServletRequest request) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request), null, IS_WRAPPER,
                RENDER_JAVASCRIPT, POSITION_INLINE
        );
    }

    private String getComponentId(HttpServletRequest request) {
        return Optional.ofNullable((String) request.getAttribute("id"))
                .orElse(PortalUtil.generateRandomKey(request, COMPONENT_NAME));
    }

    @Reference
    private NPMResolver _npmResolver;

    @Reference
    private SoyComponentRenderer _soyRenderer;

    @Reference
    private ProductHelper _productHelper;

    private static final String MODULE_NAME =
            InfoItemRendererUtil.composeModuleName(COMPONENT_NAME);

    private static final String TEMPLATE_NAMESPACE = StringBundler.concat(
            InfoItemRendererUtil.caseCamelize(COMPONENT_NAME), ".render");

    private static final Log _log = LogFactoryUtil.getLog(PriceItemRenderer.class);
}
