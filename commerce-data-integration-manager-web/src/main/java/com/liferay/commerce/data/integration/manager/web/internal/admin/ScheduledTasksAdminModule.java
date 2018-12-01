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

package com.liferay.commerce.data.integration.manager.web.internal.admin;

import com.liferay.commerce.data.integration.manager.web.internal.admin.api.DataIntegrationAdminModule;
import com.liferay.commerce.data.integration.manager.model.ProcessConstants;
import com.liferay.commerce.data.integration.manager.service.ProcessLocalService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.ScheduledTasksDataIntegrationDisplayContext;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.action.ActionHelper;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	immediate = true,
	property = "data.integration.admin.module.key=" + ScheduledTasksAdminModule.KEY,
	service = DataIntegrationAdminModule.class
)
public class ScheduledTasksAdminModule implements DataIntegrationAdminModule {

	public static final String KEY = "scheduled-tasks";

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "scheduled-tasks-admin");
	}

	@Override
	public PortletURL getSearchURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return null;
	}

	@Override
	public boolean isVisible(long groupId) throws PortalException {
		return true;
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		ScheduledTasksDataIntegrationDisplayContext
			scheduledTasksDataIntegrationDisplayContext =
				new ScheduledTasksDataIntegrationDisplayContext(
					_portletResourcePermission, _scheduledTaskLocalService,
					_processLocalService, _portal, _actionHelper,
					_schedulerEngineHelper, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			scheduledTasksDataIntegrationDisplayContext);

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(renderResponse);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/scheduled-tasks/scheduled_tasks.jsp");
	}

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + ProcessConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private ProcessLocalService _processLocalService;

	@Reference
	private ScheduledTaskLocalService _scheduledTaskLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.data.integration.manager.web.internal.portlet)"
	)
	private ServletContext _servletContext;

}