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

package com.liferay.commerce.catalog.web.internal.portlet.action;

import com.liferay.commerce.catalog.web.internal.display.context.CommerceCatalogDisplayContext;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CATALOGS,
		"mvc.command.name=editCommerceCatalog"
	},
	service = MVCRenderCommand.class
)
public class EditCommerceCatalogMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);

			CommerceCatalogDisplayContext commerceCatalogDisplayContext =
				new CommerceCatalogDisplayContext(
					httpServletRequest, _commerceCatalogService, _itemSelector,
					_portal, _portletResourcePermission);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT, commerceCatalogDisplayContext);
		}
		catch (PortalException pe) {
			SessionErrors.add(renderRequest, pe.getClass());

			return "/error.jsp";
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return "/details.jsp";
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}