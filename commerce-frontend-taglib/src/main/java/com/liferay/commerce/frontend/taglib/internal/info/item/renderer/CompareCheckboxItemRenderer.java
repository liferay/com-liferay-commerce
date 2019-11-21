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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.util.CPCompareHelperUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(service = GalleryItemRenderer.class)
public class CompareCheckboxItemRenderer implements InfoItemRenderer<CPCatalogEntry> {

    private static final String COMPONENT_NAME = "compare_checkbox";

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

    private ComponentDescriptor getDescriptor(HttpServletRequest request) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request), null, IS_WRAPPER,
                RENDER_JAVASCRIPT, POSITION_INLINE
        );
    }

    private String getComponentId(HttpServletRequest request) {
        return PortalUtil.generateRandomKey(request, COMPONENT_NAME);
    }

    private Map<String, Object> getRenderingData(HttpServletRequest request) throws Exception {
        long cpDefinitionId = (long) request.getAttribute("cpDefinitionId");

        Map<String, Object> data = new HashMap<>();

        data.put("productId", cpDefinitionId);
        data.put("checkboxVisible", true);
        data.put("compareAvailable", true);
        data.put("inCompare", false);
        data.put("labelVisible", true);

        try {
            CPDefinition cpDefinition =
                    CPDefinitionLocalServiceUtil.getCPDefinition(cpDefinitionId);

            data.put("pictureUrl", cpDefinition.getDefaultImageThumbnailSrc());

            CommerceContext commerceContext =
                    (CommerceContext) request.getAttribute(
                            CommerceWebKeys.COMMERCE_CONTEXT);

            CommerceAccount commerceAccount =
                    commerceContext.getCommerceAccount();

            long commerceAccountId = 0;

            if (commerceAccount != null) {
                commerceAccountId = commerceAccount.getCommerceAccountId();
            }

            HttpServletRequest originalHttpServletRequest =
                    PortalUtil.getOriginalServletRequest(request);

            List<Long> cpDefinitionIds = CPCompareHelperUtil.getCPDefinitionIds(
                    commerceContext.getCommerceChannelGroupId(), commerceAccountId,
                    originalHttpServletRequest.getSession());

            data.put("inCompare", cpDefinitionIds.contains(cpDefinitionId));
        } catch (PortalException pe) {
            _log.error(pe, pe);
        }

        return data;
    }

    @Reference
    private NPMResolver _npmResolver;

    @Reference
    private SoyComponentRenderer _soyRenderer;

    private static final String MODULE_NAME =
            InfoItemRendererUtil.composeModuleName(COMPONENT_NAME);

    private static final String TEMPLATE_NAMESPACE = StringBundler.concat(
            InfoItemRendererUtil.caseCamelize(COMPONENT_NAME), ".render");

    private static final Log _log = LogFactoryUtil.getLog(CompareCheckboxItemRenderer.class);
}