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

import com.liferay.commerce.account.constants.CommerceAccountPortletKeys;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.taglib.internal.model.CurrentAccountModel;
import com.liferay.commerce.frontend.taglib.internal.model.CurrentOrderModel;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = AccountSelectorItemRenderer.class)
public class AccountSelectorItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "account_selector";

    private static final String API_ENDPOINT =
            "/o/commerce-ui/";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(AccountSelectorItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) throws PortalException {
        Map<String, Object> data = new HashMap<>();

        ThemeDisplay themeDisplay = InfoItemRendererUtil.getThemeDisplay(request);
        CommerceContext commerceContext = InfoItemRendererUtil.getCommerceContext(request);

        LayoutSet layoutSet = themeDisplay.getLayoutSet();

        CommerceAccount commerceAccount =
                commerceContext.getCommerceAccount();

        if (commerceAccount != null) {
            String thumbnailUrl = null;

            if (commerceAccount.getLogoId() == 0) {
                thumbnailUrl =
                        themeDisplay.getPathImage() +
                                "/organization_logo?img_id=0";
            }
            else {
                thumbnailUrl = com.liferay.petra.string.StringBundler.concat(
                        themeDisplay.getPathImage(),
                        "/organization_logo?img_id=",
                        commerceAccount.getLogoId(), "&t=",
                        WebServerServletTokenUtil.getToken(
                                commerceAccount.getLogoId()));
            }

            CurrentAccountModel currentAccountModel =
                    new CurrentAccountModel(
                            commerceAccount.getCommerceAccountId(),
                            commerceAccount.getName(), thumbnailUrl);

            data.put("currentAccount", currentAccountModel);
        }

        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

        if (commerceOrder != null) {
            CurrentOrderModel currentOrderModel = new CurrentOrderModel(
                    commerceOrder.getCommerceOrderId(),
                    WorkflowConstants.getStatusLabel(
                            commerceOrder.getStatus()));

            data.put("currentOrder", currentOrderModel);
        }

        Layout accountManagementLayout = _getAccountManagementLayout(
                themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

        data.put(
                "viewAllAccountsLink",
                PortalUtil.getLayoutFriendlyURL(
                        accountManagementLayout, themeDisplay));

        data.put(
                "createNewOrderLink", _getAddCommerceOrderURL(themeDisplay));

        data.put(
                "viewAllOrdersLink", _getViewCommerceOrdersURL(themeDisplay));

        data.put("accountsAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
        data.put("query", StringPool.BLANK);
        data.put("spritemap", InfoItemRendererUtil.getSpritemapPath(request));

        return data;
    }

    private Layout _getAccountManagementLayout(
            long groupId, boolean privateLayout)
            throws PortalException {

        Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
                groupId, privateLayout, "/accounts");

        if (layout != null) {
            return layout;
        }

        long plid = PortalUtil.getPlidFromPortletId(
                groupId, CommerceAccountPortletKeys.COMMERCE_ACCOUNT);

        if (plid > 0) {
            layout = LayoutLocalServiceUtil.fetchLayout(plid);
        }

        return layout;
    }

    private String _getAddCommerceOrderURL(ThemeDisplay themeDisplay)
            throws PortalException {

        long plid = PortalUtil.getPlidFromPortletId(
                themeDisplay.getScopeGroupId(),
                CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT);

        if (plid > 0) {
            PortletURL portletURL = _getPortletURL(
                    themeDisplay.getRequest(),
                    CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT);

            portletURL.setParameter(
                    ActionRequest.ACTION_NAME, "editCommerceOrder");
            portletURL.setParameter(Constants.CMD, Constants.ADD);

            return portletURL.toString();
        }

        return StringPool.BLANK;
    }

    private PortletURL _getPortletURL(
            HttpServletRequest httpServletRequest, String portletId)
            throws PortalException {

        PortletURL portletURL = null;

        long groupId = PortalUtil.getScopeGroupId(httpServletRequest);

        long plid = PortalUtil.getPlidFromPortletId(groupId, portletId);

        if (plid > 0) {
            portletURL = PortletURLFactoryUtil.create(
                    httpServletRequest, portletId, plid,
                    PortletRequest.ACTION_PHASE);
        }
        else {
            portletURL = PortletURLFactoryUtil.create(
                    httpServletRequest, portletId, PortletRequest.ACTION_PHASE);
        }

        return portletURL;
    }

    private String _getViewCommerceOrdersURL(ThemeDisplay themeDisplay)
            throws PortalException {

        long plid = PortalUtil.getPlidFromPortletId(
                themeDisplay.getScopeGroupId(),
                CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT);

        if (plid > 0) {
            PortletURL portletURL = _getPortletURL(
                    themeDisplay.getRequest(),
                    CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT);

            return portletURL.toString();
        }

        return StringPool.BLANK;
    }
}
