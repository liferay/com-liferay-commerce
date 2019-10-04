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

package com.liferay.commerce.product.type.virtual.order.content.web.internal.portlet.action;

import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.product.type.virtual.order.constants.CommerceVirtualOrderPortletKeys;
import com.liferay.commerce.product.type.virtual.order.content.web.internal.display.context.CommerceVirtualOrderItemContentDisplayContext;
import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalService;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
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
		"javax.portlet.name=" + CommerceVirtualOrderPortletKeys.COMMERCE_VIRTUAL_ORDER_ITEM_CONTENT,
		"mvc.command.name=viewCommerceVirtualOrderItemTermsOfUse"
	},
	service = MVCRenderCommand.class
)
public class ViewCommerceVirtualOrderItemTermsOfUseMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			CommerceVirtualOrderItemContentDisplayContext
				commerceVirtualOrderItemContentDisplayContext =
					new CommerceVirtualOrderItemContentDisplayContext(
						_commerceVirtualOrderItemLocalService,
						_cpDefinitionHelper, _commerceAccountHelper,
						_cpDefinitionVirtualSettingService, _cpInstanceHelper,
						_portal.getHttpServletRequest(renderRequest),
						_modelResourcePermission);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceVirtualOrderItemContentDisplayContext);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return "/terms_of_use.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewCommerceVirtualOrderItemTermsOfUseMVCRenderCommand.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceVirtualOrderItemLocalService
		_commerceVirtualOrderItemLocalService;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionVirtualSettingService
		_cpDefinitionVirtualSettingService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem)"
	)
	private ModelResourcePermission<CommerceVirtualOrderItem>
		_modelResourcePermission;

	@Reference
	private Portal _portal;

}