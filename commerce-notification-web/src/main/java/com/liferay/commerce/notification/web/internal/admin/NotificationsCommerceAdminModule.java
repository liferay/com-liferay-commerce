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

package com.liferay.commerce.notification.web.internal.admin;

import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.admin.constants.CommerceAdminConstants;
import com.liferay.commerce.notification.constants.CommerceNotificationActionKeys;
import com.liferay.commerce.notification.constants.CommerceNotificationConstants;
import com.liferay.commerce.notification.service.CommerceNotificationQueueEntryService;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateService;
import com.liferay.commerce.notification.type.CommerceNotificationTypeRegistry;
import com.liferay.commerce.notification.web.internal.display.context.CommerceNotificationQueueEntriesDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.admin.module.key=" + NotificationsCommerceAdminModule.KEY,
	service = CommerceAdminModule.class
)
public class NotificationsCommerceAdminModule implements CommerceAdminModule {

	public static final String KEY = "notifications";

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public PortletURL getSearchURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return null;
	}

	@Override
	public int getType() {
		return CommerceAdminConstants.COMMERCE_ADMIN_TYPE_GROUP_INSTANCE;
	}

	@Override
	public boolean isVisible(long groupId) throws PortalException {
		return _portletResourcePermission.contains(
			PermissionThreadLocal.getPermissionChecker(), groupId,
			CommerceNotificationActionKeys.
				VIEW_COMMERCE_NOTIFICATION_TEMPLATES);
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		setCommerceNotificationQueueEntriesDisplayContext(httpServletRequest);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/view.jsp");
	}

	protected CommerceNotificationQueueEntriesDisplayContext
		setCommerceNotificationQueueEntriesDisplayContext(
			HttpServletRequest httpServletRequest) {

		CommerceNotificationQueueEntriesDisplayContext
			commerceNotificationQueueEntriesDisplayContext =
				(CommerceNotificationQueueEntriesDisplayContext)
					httpServletRequest.getAttribute(
						WebKeys.PORTLET_DISPLAY_CONTEXT);

		if (commerceNotificationQueueEntriesDisplayContext == null) {
			commerceNotificationQueueEntriesDisplayContext =
				new CommerceNotificationQueueEntriesDisplayContext(
					_commerceNotificationQueueEntryService,
					_commerceNotificationTemplateService,
					_commerceNotificationTypeRegistry, httpServletRequest,
					_portletResourcePermission);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceNotificationQueueEntriesDisplayContext);
		}

		return commerceNotificationQueueEntriesDisplayContext;
	}

	@Reference
	private CommerceNotificationQueueEntryService
		_commerceNotificationQueueEntryService;

	@Reference
	private CommerceNotificationTemplateService
		_commerceNotificationTemplateService;

	@Reference
	private CommerceNotificationTypeRegistry _commerceNotificationTypeRegistry;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + CommerceNotificationConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.notification.web)"
	)
	private ServletContext _servletContext;

}