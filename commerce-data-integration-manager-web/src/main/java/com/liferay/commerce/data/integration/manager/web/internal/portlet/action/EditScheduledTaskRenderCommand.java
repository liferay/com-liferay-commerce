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

package com.liferay.commerce.data.integration.manager.web.internal.portlet.action;

import com.liferay.commerce.data.integration.manager.web.internal.util.DataIntegrationAdminModuleRegistry;
import com.liferay.commerce.data.integration.manager.service.ProcessLocalService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.ScheduledTasksDataIntegrationDisplayContext;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.LR_DATA_INTEGRATION_WEB,
		"mvc.command.name=editScheduledTask"
	}
)
public class EditScheduledTaskRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ScheduledTasksDataIntegrationDisplayContext
			scheduledTasksDataIntegrationDisplayContext =
				new ScheduledTasksDataIntegrationDisplayContext(
					_portletResourcePermission, _scheduledTaskLocalService,
					_processLocalService, _portal, _actionHelper,
					_schedulerEngineHelper, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			scheduledTasksDataIntegrationDisplayContext);

		renderRequest.setAttribute(
			DataIntegrationWebPortletKeys.
				LR_DATA_INTEGRATION_ADMIN_MODULE_REGISTRY,
			_lrDataIntegrationAdminModuleRegistry);

		return "/scheduled-tasks/edit_scheduled_task.jsp";
	}

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private DataIntegrationAdminModuleRegistry
		_lrDataIntegrationAdminModuleRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private ProcessLocalService _processLocalService;

	@Reference
	private ScheduledTaskLocalService _scheduledTaskLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

}