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

package com.liferay.commerce.frontend.util;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Gianmarco Brunialti Masera
 */
public class ItemRendererUtil {

    private static final String COMMERCE_ICONS = "commerce-icons.svg";
    private static final String COMMERCE_FRONTEND_TAGLIB =
            "commerce-frontend-taglib";

    public static final String getSpritemapPath(HttpServletRequest request) {
        ThemeDisplay themeDisplay = getThemeDisplay(request);

        return StringBundler.concat(
                themeDisplay.getPathThemeImages(),
                StringPool.FORWARD_SLASH, COMMERCE_ICONS);
    }

    public static final String composeModuleName(String moduleDirectory) {
        return StringBundler.concat(
                COMMERCE_FRONTEND_TAGLIB, StringPool.FORWARD_SLASH,
                moduleDirectory, StringPool.FORWARD_SLASH,
                caseCamelize(moduleDirectory), ".es");
    }

    public static String caseCamelize(String input) {
        String output = "";

        String[] splitString = Optional.of(input.split(StringPool.UNDERLINE))
                .orElse(input.split(StringPool.DASH));

        if (splitString.length > 0) {
            for (String inputChunk : splitString) {
                output += _caseCamelize(inputChunk);
            }
        } else {
            output = _caseCamelize(input);
        }

        return output;
    }

    public static ThemeDisplay getThemeDisplay(HttpServletRequest request) {
        return (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public static CommerceContext getCommerceContext(
            HttpServletRequest request) {

        return (CommerceContext) request.getAttribute(
                CommerceWebKeys.COMMERCE_CONTEXT);
    }

    private static String _caseCamelize(String input) {
        return StringBundler.concat(
                input.substring(0, 1).toUpperCase(),
                input.substring(1));
    }
}