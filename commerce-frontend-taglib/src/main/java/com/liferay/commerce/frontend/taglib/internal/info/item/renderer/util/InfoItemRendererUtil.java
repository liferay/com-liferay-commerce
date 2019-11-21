package com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

public class InfoItemRendererUtil {

    // TODO Add fallback like in InfoBoxTag Component
    private static final String CLAY_ICONS = "/clay/icons.svg";

    private static final String COMMERCE_ICONS = "commerce-icons.svg";
    private static final String COMMERCE_FRONTEND_TAGLIB = "commerce-frontend-taglib";

    public static final String getSpritemapPath(HttpServletRequest request) {
        // TODO
        return StringBundler.concat(getThemeDisplay(request).getPathThemeImages(), "/", COMMERCE_ICONS);
    }

    public static final String composeModuleName(String moduleDirectory) {
        return StringBundler.concat(
                COMMERCE_FRONTEND_TAGLIB, "/",
                moduleDirectory, "/",
                caseCamelize(moduleDirectory), ".es");
    }

    public static String caseCamelize(String input) {
        String[] splitString = input.split("_");

        String output = "";

        for (String chunk : splitString) {
            output += StringBundler.concat(
                    chunk.substring(0, 1).toUpperCase(),
                    chunk.substring(1)
            );
        }

        return output;
    }

    public static ThemeDisplay getThemeDisplay(HttpServletRequest request) {
        return (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public static CommerceContext getCommerceContext(HttpServletRequest request) {
        return (CommerceContext) request.getAttribute(
                CommerceWebKeys.COMMERCE_CONTEXT);
    }
}