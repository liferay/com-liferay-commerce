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

package com.liferay.commerce.product.type.grouped.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.definitions.portlet.action.ActionHelper;
import com.liferay.commerce.product.type.grouped.service.CPDefinitionGroupedEntryService;
import com.liferay.commerce.product.type.grouped.web.internal.display.context.CPDefinitionGroupedEntriesDisplayContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=editCPDefinitionGroupedEntry"
	},
	service = MVCRenderCommand.class
)
public class EditCPDefinitionGroupedEntryMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(
				"/edit_definition_grouped_entry.jsp");

		try {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);
			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(renderResponse);

			CPDefinitionGroupedEntriesDisplayContext
				cpDefinitionGroupedEntriesDisplayContext =
					new CPDefinitionGroupedEntriesDisplayContext(
						_actionHelper, httpServletRequest,
						_cpDefinitionGroupedEntryService, _itemSelector);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpDefinitionGroupedEntriesDisplayContext);

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			throw new PortletException(
				"Unable to include edit_definition_grouped_entry.jsp", e);
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private CPDefinitionGroupedEntryService _cpDefinitionGroupedEntryService;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.type.grouped.web)"
	)
	private ServletContext _servletContext;

}