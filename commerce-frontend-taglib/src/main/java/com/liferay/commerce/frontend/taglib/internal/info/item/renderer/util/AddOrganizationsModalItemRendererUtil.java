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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.AddOrganizationsModalItemRenderer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(immediate = true, service = AddOrganizationsModalItemRendererUtil.class)
public class AddOrganizationsModalItemRendererUtil {

    public static final AddOrganizationsModalItemRenderer getRenderer() {
        return _addOrganizationsModalItemRendererUtil._getRenderer();
    }

    private AddOrganizationsModalItemRenderer _getRenderer() {
        return _addOrganizationsModalItemRenderer;
    }

    @Activate
    protected void activate() {
        _addOrganizationsModalItemRendererUtil = this;
    }

    @Deactivate
    protected void deactivate() {
        _addOrganizationsModalItemRendererUtil = null;
    }

    @Reference(unbind = "-")
    protected void setAddOrganizationsModalRenderer(AddOrganizationsModalItemRenderer addOrganizationsModalItemRenderer) {
        _addOrganizationsModalItemRenderer = addOrganizationsModalItemRenderer;
    }

    private static AddOrganizationsModalItemRendererUtil _addOrganizationsModalItemRendererUtil;
    private AddOrganizationsModalItemRenderer _addOrganizationsModalItemRenderer;
}