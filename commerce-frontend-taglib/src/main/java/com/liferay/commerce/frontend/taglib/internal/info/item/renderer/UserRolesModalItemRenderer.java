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
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
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
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.*;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = UserRolesModalItemRenderer.class)
public class UserRolesModalItemRenderer implements InfoItemRenderer<CPCatalogEntry> {

    private static final String COMPONENT_NAME = "user_roles_modal";

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

    private Map<String, Object> getRenderingData(HttpServletRequest request)
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

    private ComponentDescriptor getDescriptor(HttpServletRequest request) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request), null, IS_WRAPPER,
                RENDER_JAVASCRIPT, POSITION_INLINE
        );
    }

    private String getComponentId(HttpServletRequest request) {
        return Optional.ofNullable((String) request.getAttribute("id"))
                .orElse(PortalUtil.generateRandomKey(request, COMPONENT_NAME));
    }

    @Reference
    private NPMResolver _npmResolver;

    @Reference
    private SoyComponentRenderer _soyRenderer;

    private static final String MODULE_NAME =
            InfoItemRendererUtil.composeModuleName(COMPONENT_NAME);

    private static final String TEMPLATE_NAMESPACE = StringBundler.concat(
            InfoItemRendererUtil.caseCamelize(COMPONENT_NAME), ".render");

    private static final Log _log = LogFactoryUtil.getLog(UserRolesModalItemRenderer.class);
}
