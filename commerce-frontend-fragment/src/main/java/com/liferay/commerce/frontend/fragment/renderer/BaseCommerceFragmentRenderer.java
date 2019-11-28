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

package com.liferay.commerce.frontend.fragment.renderer;

import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Locale;

/**
 * @author Gianmarco Brunialti Masera
 */
public abstract class BaseCommerceFragmentRenderer
        implements FragmentRenderer {

    @Override
    public String getCollectionKey() {
        return COLLECTION_KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, getFragmentName());
    }

    protected abstract String getFragmentName();

    protected String INFO_ITEM_RENDERER_PACKAGE = StringBundler.concat(
            "com.liferay.commerce.frontend.taglib.internal.info.item.renderer.",
            ItemRendererUtil.caseCamelize(getFragmentName()),
            ITEM_RENDERER_SUFFIX);

    private static final String COLLECTION_KEY = "commerce-components";

    protected static final String ITEM_RENDERER_SUFFIX =
            "ItemRenderer";
}
