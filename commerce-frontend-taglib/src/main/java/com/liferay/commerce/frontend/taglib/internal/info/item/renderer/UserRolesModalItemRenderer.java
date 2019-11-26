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
import com.liferay.commerce.account.service.CommerceAccountServiceUtil;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.taglib.internal.model.AccountRole;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;


import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = UserRolesModalItemRenderer.class)
public class UserRolesModalItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "user_roles_modal";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(UserRolesModalItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
            throws PortalException {

        Map<String, Object> data = new HashMap<>();

        long userId = GetterUtil.getLong(request.getAttribute(("userId")));
        long commerceAccountId = GetterUtil.getLong(request.getAttribute("commerceAccountId"));

        data.put("availableRoles", _getAvailableRoles(request));
        data.put("selectedRoles", _getSelectedRoles(userId, commerceAccountId));

        data.put("spritemap", InfoItemRendererUtil.getSpritemapPath(request));

        return data;
    }

    private List<AccountRole> _getAvailableRoles(HttpServletRequest request)
            throws PortalException {

        List<AccountRole> availableRoles = new ArrayList<>();

        List<Role> roles = RoleServiceUtil.getRoles(
                PortalUtil.getCompanyId(request),
                new int[]{RoleConstants.TYPE_SITE});

        for (Role role : roles) {
            availableRoles.add(new AccountRole(
                    role.getRoleId(),
                    role.getTitle(InfoItemRendererUtil
                            .getThemeDisplay(request).getLocale())));
        }

        return availableRoles;
    }

    private List<AccountRole> _getSelectedRoles(long userId, long commerceAccountId)
            throws PortalException {

        CommerceAccount commerceAccount = CommerceAccountServiceUtil
                .getCommerceAccount(commerceAccountId);

        List<AccountRole> selectedRoles = new ArrayList<>();

        List<UserGroupRole> userGroupRoles =
                UserGroupRoleLocalServiceUtil.getUserGroupRoles(
                        userId, commerceAccount.getCommerceAccountGroupId());

        for (UserGroupRole userGroupRole : userGroupRoles) {
            Role role = userGroupRole.getRole();

            selectedRoles.add(
                    new AccountRole(role.getRoleId(), role.getName()));
        }

        return selectedRoles;
    }
}
