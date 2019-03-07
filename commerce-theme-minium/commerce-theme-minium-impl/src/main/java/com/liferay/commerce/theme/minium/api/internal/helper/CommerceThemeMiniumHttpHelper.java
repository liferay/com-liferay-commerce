/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.theme.minium.api.internal.helper;

import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = CommerceThemeMiniumHttpHelper.class)
public class CommerceThemeMiniumHttpHelper {

	public String getAccountManagementPortletURL(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		return _commerceAccountHelper.getAccountManagementPortletURL(
			httpServletRequest);
	}

	public String getMyListsLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "my-lists");
	}

	public int getNotificationsCount(ThemeDisplay themeDisplay) {
		PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(
			_panelAppRegistry, _panelCategoryRegistry);

		return panelCategoryHelper.getNotificationsCount(
			PanelCategoryKeys.USER, themeDisplay.getPermissionChecker(),
			themeDisplay.getScopeGroup(), themeDisplay.getUser());
	}

	public String getNotificationsURL(HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, UserNotificationEvent.class.getName(),
			PortletProvider.Action.VIEW);

		return portletURL.toString();
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private PanelAppRegistry _panelAppRegistry;

	@Reference
	private PanelCategoryRegistry _panelCategoryRegistry;

}