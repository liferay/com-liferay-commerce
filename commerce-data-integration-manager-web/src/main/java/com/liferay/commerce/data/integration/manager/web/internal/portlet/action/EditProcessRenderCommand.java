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

import com.liferay.commerce.data.integration.manager.model.ProcessConstants;
import com.liferay.commerce.data.integration.manager.service.ProcessService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskExectutorService;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.DataIntegrationProcessListDisplayContext;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.commerce.data.integration.manager.web.internal.util.DataIntegrationAdminModuleRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	property = {
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.LR_DATA_INTEGRATION_WEB,
		"mvc.command.name=editProcess"
	},
	service = MVCRenderCommand.class
)
public class EditProcessRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		DataIntegrationProcessListDisplayContext
			lrDataIntegrationDisplayContext =
				new DataIntegrationProcessListDisplayContext(
					_portletResourcePermission, _processService, _portal,
					_actionHelper, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, lrDataIntegrationDisplayContext);

		renderRequest.setAttribute("processTypes", processTypes);

		renderRequest.setAttribute(
			DataIntegrationWebPortletKeys.
				LR_DATA_INTEGRATION_ADMIN_MODULE_REGISTRY,
			_lrDataIntegrationAdminModuleRegistry);

		return "/processes/edit_process.jsp";
	}

	@Activate
	@Modified
	protected void init(BundleContext bundleContext) {
		processTypes = new HashMap<>();

		_scheduledTaskExectutorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, ScheduledTaskExectutorService.class,
				"data.integration.service.executor.key");

		Class<?> serviceClass;

		if (_scheduledTaskExectutorServiceTrackerMap != null) {
			for (String key :
					_scheduledTaskExectutorServiceTrackerMap.keySet()) {

				ScheduledTaskExectutorService scheduledTaskExectutorService =
					_scheduledTaskExectutorServiceTrackerMap.getService(key);

				serviceClass = scheduledTaskExectutorService.getClass();

				processTypes.put(
					scheduledTaskExectutorService.getName(),
					serviceClass.getName());
			}
		}
	}

	protected Map<String, String> processTypes;

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private DataIntegrationAdminModuleRegistry
		_lrDataIntegrationAdminModuleRegistry;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + ProcessConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private ProcessService _processService;

	private ServiceTrackerMap<String, ScheduledTaskExectutorService>
		_scheduledTaskExectutorServiceTrackerMap;

}