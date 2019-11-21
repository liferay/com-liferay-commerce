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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.*;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(service = GalleryItemRenderer.class)
public class GalleryItemRenderer implements InfoItemRenderer<CPCatalogEntry> {

    private static final String COMPONENT_NAME = "gallery";

    private static final boolean IS_WRAPPER = true;
    private static final boolean RENDER_JAVASCRIPT = true;
    private static final boolean POSITION_INLINE = true;

    @Override
    public void render(CPCatalogEntry cpCatalogEntry,
                       HttpServletRequest request,
                       HttpServletResponse response) {

        try {
            long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();

            Writer writer = response.getWriter();
            ComponentDescriptor soyComponentDescriptor = getDescriptor(request);
            Map<String, Object> data = getRenderingData(cpDefinitionId, request);

            _soyRenderer.renderSoyComponent(
                    request, writer, soyComponentDescriptor, data);
        } catch(Exception e) {
            _log.error(e, e);
        }
    }

    private Map<String, Object> getRenderingData(long cpDefinitionId,
                                                     HttpServletRequest request) {

        Map<String, Object> data = new HashMap<>();
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);

        try {
            data.put("selected", 0);
            data.put("images", _cpContentHelper.getImages(
                    cpDefinitionId, themeDisplay));
        } catch(PortalException pe) {
            _log.error(pe, pe);
        }

        return data;
    }

    private ComponentDescriptor getDescriptor(HttpServletRequest request) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request), null, IS_WRAPPER,
                RENDER_JAVASCRIPT, POSITION_INLINE
        );
    }

    private String getComponentId(HttpServletRequest request) {
        Optional<String> idFromRequest = Optional.ofNullable(
                (String) request.getAttribute("id"));

        return idFromRequest.orElse(
                PortalUtil.generateRandomKey(request, COMPONENT_NAME));
    }

    @Reference
    private NPMResolver _npmResolver;

    @Reference
    private SoyComponentRenderer _soyRenderer;

    @Reference
    private CPContentHelper _cpContentHelper;

    private static final String MODULE_NAME =
            InfoItemRendererUtil.composeModuleName(COMPONENT_NAME);

    private static final String TEMPLATE_NAMESPACE = StringBundler.concat(
            InfoItemRendererUtil.caseCamelize(COMPONENT_NAME), ".render");

    private static final Log _log =
            LogFactoryUtil.getLog(GalleryItemRenderer.class);
}
