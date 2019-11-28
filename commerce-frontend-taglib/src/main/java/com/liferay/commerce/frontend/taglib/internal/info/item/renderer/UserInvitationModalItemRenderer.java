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

import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = UserInvitationModalItemRenderer.class)
public class UserInvitationModalItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "user_invitation_modal";

    private static final String API_ENDPOINT = "/o/commerce-ui/search-users";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }


    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(UserInvitationModalItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request) {

        Map<String, Object> data = new HashMap<>();

        data.put("usersAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
        data.put("query", StringPool.BLANK);
        data.put("spritemap", ItemRendererUtil.getSpritemapPath(request));

        return data;
    }
}
