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

package com.liferay.commerce.payment.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.payment.web.internal.display.context.CommercePaymentMethodGroupRelsDisplayContext;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN_GROUP_INSTANCE,
		"mvc.command.name=editCommercePaymentMethodGroupRel"
	},
	service = MVCRenderCommand.class
)
public class EditCommercePaymentMethodGroupRelMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/edit_payment_method.jsp");

		try {
			CommercePaymentMethodGroupRelsDisplayContext
				commercePaymentMethodGroupRelsDisplayContext =
					new CommercePaymentMethodGroupRelsDisplayContext(
						_commerceChannelLocalService,
						_commerceOrderLocalService,
						_commercePaymentMethodRegistry,
						_commercePaymentMethodGroupRelService,
						_commerceSubscriptionEntryLocalService,
						_cpDefinitionLocalService, _portletResourcePermission,
						renderRequest, renderResponse);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commercePaymentMethodGroupRelsDisplayContext);

			requestDispatcher.include(
				_portal.getHttpServletRequest(renderRequest),
				_portal.getHttpServletResponse(renderResponse));
		}
		catch (Exception e) {
			throw new PortletException(
				"Unable to include edit_payment_method.jsp", e);
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + CommerceConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.payment.web)"
	)
	private ServletContext _servletContext;

}