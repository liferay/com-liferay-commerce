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

package com.liferay.commerce.product.options.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.options.web.internal.display.context.CPOptionCategoryDisplayContext;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
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
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_OPTION_CATEGORIES,
		"mvc.command.name=editProductOptionCategory"
	},
	service = MVCRenderCommand.class
)
public class EditCPOptionCategoryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			CPOptionCategoryDisplayContext cpOptionCategoryDisplayContext =
				new CPOptionCategoryDisplayContext(
					_actionHelper, _portal.getHttpServletRequest(renderRequest),
					_cpOptionCategoryService, _modelResourcePermission);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpOptionCategoryDisplayContext);

			setCPOptionCategoryRequestAttribute(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPOptionCategoryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/error.jsp";
			}

			throw new PortletException(e);
		}

		return "/edit_option_category.jsp";
	}

	protected void setCPOptionCategoryRequestAttribute(
			RenderRequest renderRequest)
		throws PortalException {

		long cpOptionCategoryId = ParamUtil.getLong(
			renderRequest, "cpOptionCategoryId");

		CPOptionCategory cpOptionCategory = null;

		if (cpOptionCategoryId > 0) {
			cpOptionCategory = _cpOptionCategoryService.getCPOptionCategory(
				cpOptionCategoryId);
		}

		renderRequest.setAttribute(
			CPWebKeys.CP_OPTION_CATEGORY, cpOptionCategory);
	}

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private CPOptionCategoryService _cpOptionCategoryService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPOptionCategory)"
	)
	private ModelResourcePermission<CPOptionCategory> _modelResourcePermission;

	@Reference
	private Portal _portal;

}