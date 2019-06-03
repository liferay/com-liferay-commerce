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

package com.liferay.commerce.admin.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.admin.constants.CommerceAdminConstants;
import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.admin.web.internal.util.CommerceAdminModuleRegistry;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=1000",
		"panel.category.key=" + CommercePanelCategoryKeys.CONTROL_PANEL_COMMERCE
	},
	service = PanelApp.class
)
public class CommerceAdminVirtualInstancePanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommerceAdminPortletKeys.COMMERCE_ADMIN_VIRTUAL_INSTANCE;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		boolean show = super.isShow(permissionChecker, group);

		if (show) {
			Map<String, CommerceAdminModule> commerceAdminModules =
				_commerceAdminModuleRegistry.getCommerceAdminModules(
					CommerceAdminConstants.
						COMMERCE_ADMIN_TYPE_VIRTUAL_INSTANCE);

			if (commerceAdminModules.isEmpty()) {
				show = false;
			}
		}

		return show;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN_VIRTUAL_INSTANCE + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Reference
	private CommerceAdminModuleRegistry _commerceAdminModuleRegistry;

}