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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gianmarco Brunialti Masera
 */

abstract public class AbstractProductItemRenderer
        implements InfoItemRenderer<CPCatalogEntry> {

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
            getLogger().error(e, e);
        }
    }

    protected abstract String getComponentName();

    protected abstract Log getLogger();

    protected abstract Map<String, Object> getRenderingData(HttpServletRequest request)
            throws Exception;

    protected boolean getIsWrapper() { return IS_WRAPPER; }

    protected boolean getRenderJavascript() { return RENDER_JAVASCRIPT; }

    protected boolean getPositionInline() { return POSITION_INLINE; }

    protected ComponentDescriptor getDescriptor(HttpServletRequest request) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request), null, getIsWrapper(),
                getRenderJavascript(), getPositionInline()
        );
    }

    protected String getComponentId(HttpServletRequest request) {
        return Optional.ofNullable((String) request.getAttribute("id"))
                .orElse(PortalUtil.generateRandomKey(request, getComponentName()));
    }

    @Reference
    protected NPMResolver _npmResolver;

    @Reference
    protected SoyComponentRenderer _soyRenderer;

    protected String MODULE_NAME =
            InfoItemRendererUtil.composeModuleName(getComponentName());

    protected String TEMPLATE_NAMESPACE = StringBundler.concat(
            InfoItemRendererUtil.caseCamelize(getComponentName()), ".render");
}
