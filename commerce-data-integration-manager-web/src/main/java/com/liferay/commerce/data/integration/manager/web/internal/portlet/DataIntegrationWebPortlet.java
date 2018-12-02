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

package com.liferay.commerce.data.integration.manager.web.internal.portlet;

import com.liferay.commerce.data.integration.manager.service.ProcessService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskExectutorService;
import com.liferay.commerce.data.integration.manager.web.internal.display.context.DataIntegrationProcessListDisplayContext;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.commerce.data.integration.manager.web.internal.util.DataIntegrationAdminModuleRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.Portlet;
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
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Data Integration Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.LR_DATA_INTEGRATION_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supports.mime-type=text/html",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class DataIntegrationWebPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			String mvcRenderCommandName = ParamUtil.getString(
				renderRequest, "mvcRenderCommandName");

			if (Validator.isNull(mvcRenderCommandName)) {
				DataIntegrationProcessListDisplayContext
					dataIntegrationDisplayContext =
						new DataIntegrationProcessListDisplayContext(
							_portletResourcePermission, _processService,
							_portal, _actionHelper, renderRequest);

				renderRequest.setAttribute(
					WebKeys.PORTLET_DISPLAY_CONTEXT,
					dataIntegrationDisplayContext);

				renderRequest.setAttribute("processTypes", processTypes);
				renderRequest.setAttribute(
					DataIntegrationWebPortletKeys.
						LR_DATA_INTEGRATION_ADMIN_MODULE_REGISTRY,
					_dataIntegrationAdminModuleRegistry);
			}
		}
		catch (Exception ex) {
		}

		super.render(renderRequest, renderResponse);
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
		_dataIntegrationAdminModuleRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private ProcessService _processService;

	private ServiceTrackerMap<String, ScheduledTaskExectutorService>
		_scheduledTaskExectutorServiceTrackerMap;

}