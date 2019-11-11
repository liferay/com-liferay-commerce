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

package com.liferay.commerce.data.integration.web.internal.portlet.action;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationPortletKeys;
import com.liferay.commerce.data.integration.process.type.ProcessTypeRegistry;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessService;
import com.liferay.commerce.data.integration.trigger.CommerceDataIntegrationProcessTriggerHelper;
import com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"javax.portlet.name=" + CommerceDataIntegrationPortletKeys.COMMERCE_DATA_INTEGRATION,
		"mvc.command.name=editCommerceDataIntegrationProcess"
	},
	service = MVCRenderCommand.class
)
public class EditCommerceDataIntegrationProcessRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		CommerceDataIntegrationProcessDisplayContext
			commerceDataIntegrationProcessDisplayContext =
				new CommerceDataIntegrationProcessDisplayContext(
					_commerceDataIntegrationProcessScheduledTaskHelper,
					_commerceDataIntegrationProcessService,
					_processTypeRegistry, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceDataIntegrationProcessDisplayContext);

		return "/edit_process.jsp";
	}

	@Reference
	private CommerceDataIntegrationProcessTriggerHelper
		_commerceDataIntegrationProcessScheduledTaskHelper;

	@Reference
	private CommerceDataIntegrationProcessService
		_commerceDataIntegrationProcessService;

	@Reference
	private ProcessTypeRegistry _processTypeRegistry;

}