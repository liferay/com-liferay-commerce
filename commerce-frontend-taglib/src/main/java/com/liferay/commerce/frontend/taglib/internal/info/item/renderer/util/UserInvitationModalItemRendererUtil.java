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

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.UserInvitationModalItemRenderer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(immediate = true, service = UserInvitationModalItemRendererUtil.class)
public class UserInvitationModalItemRendererUtil {

    public static final UserInvitationModalItemRenderer getRenderer() {
        return _userInvitationModalItemRendererUtil._getRenderer();
    }

    private UserInvitationModalItemRenderer _getRenderer() {
        return _userInvitationModalItemRenderer;
    }

    @Activate
    protected void activate() {
        _userInvitationModalItemRendererUtil = this;
    }

    @Deactivate
    protected void deactivate() {
        _userInvitationModalItemRendererUtil = null;
    }

    @Reference(unbind = "-")
    protected void setUserInvitationModalItemRenderer(UserInvitationModalItemRenderer userInvitationModalItemRenderer) {
        _userInvitationModalItemRenderer = userInvitationModalItemRenderer;
    }

    private static UserInvitationModalItemRendererUtil _userInvitationModalItemRendererUtil;
    private UserInvitationModalItemRenderer _userInvitationModalItemRenderer;
}