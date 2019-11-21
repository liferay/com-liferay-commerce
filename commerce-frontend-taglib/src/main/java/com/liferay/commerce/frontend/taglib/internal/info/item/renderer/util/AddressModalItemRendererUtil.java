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

package com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util;

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.AddressModalItemRenderer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(immediate = true, service = AddressModalItemRendererUtil.class)
public class AddressModalItemRendererUtil {

    public static final AddressModalItemRenderer getRenderer() {
        return _addressModalItemRendererUtil._getRenderer();
    }

    private AddressModalItemRenderer _getRenderer() {
        return _addressModalItemRenderer;
    }

    @Activate
    protected void activate() {
        _addressModalItemRendererUtil = this;
    }

    @Deactivate
    protected void deactivate() {
        _addressModalItemRendererUtil = null;
    }

    @Reference(unbind = "-")
    protected void setAddressModalItemRenderer(AddressModalItemRenderer addressModalItemRenderer) {
        _addressModalItemRenderer = addressModalItemRenderer;
    }

    private static AddressModalItemRendererUtil _addressModalItemRendererUtil;
    private AddressModalItemRenderer _addressModalItemRenderer;
}