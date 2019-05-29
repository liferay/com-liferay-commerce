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

package com.liferay.commerce.product.measurement.unit.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.product.exception.NoSuchCPMeasurementUnitException;
import com.liferay.commerce.product.measurement.unit.web.internal.display.context.CPMeasurementUnitsDisplayContext;
import com.liferay.commerce.product.service.CPMeasurementUnitService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
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
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN,
		"mvc.command.name=editCPMeasurementUnit"
	},
	service = MVCRenderCommand.class
)
public class EditCPMeasurementUnitMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/edit_measurement_unit.jsp");

		try {
			CPMeasurementUnitsDisplayContext cpMeasurementUnitsDisplayContext =
				new CPMeasurementUnitsDisplayContext(
					_cpMeasurementUnitService, renderRequest, renderResponse);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpMeasurementUnitsDisplayContext);

			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);
			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(renderResponse);

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPMeasurementUnitException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/error.jsp";
			}

			throw new PortletException(
				"Unable to include edit_measurement_unit.jsp", e);
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Reference
	private CPMeasurementUnitService _cpMeasurementUnitService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.measurement.unit.web)"
	)
	private ServletContext _servletContext;

}