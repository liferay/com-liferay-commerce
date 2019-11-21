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
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = SearchBarItemRenderer.class)
public class SearchBarItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "search_bar";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(SearchBarItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        Locale locale = InfoItemRendererUtil.getThemeDisplay(request).getLocale();

        data.put("spritemap", InfoItemRendererUtil.getSpritemapPath(request));
        data.put("placeholder", LanguageUtil.get(locale, "search"));
        data.put("query", ParamUtil.getString(request, "q"));

        return data;
    }
}
