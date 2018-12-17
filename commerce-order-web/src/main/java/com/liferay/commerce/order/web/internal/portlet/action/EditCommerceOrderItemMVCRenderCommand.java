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

package com.liferay.commerce.order.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.order.web.internal.display.context.CommerceOrderEditDisplayContext;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
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
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_ORDER,
		"mvc.command.name=editCommerceOrderItem"
	},
	service = MVCRenderCommand.class
)
public class EditCommerceOrderItemMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			CommerceOrderEditDisplayContext commerceOrderEditDisplayContext =
				new CommerceOrderEditDisplayContext(
					_commerceOrderService, _commerceOrderItemService,
					_commerceOrderNoteService,
					_commercePaymentMethodGroupRelService,
					_commerceProductPriceCalculation, _itemSelector,
					renderRequest);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceOrderEditDisplayContext);
		}
		catch (Exception e) {
			if (e instanceof NoSuchOrderException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/error.jsp";
			}
			else {
				throw new PortletException(e);
			}
		}

		return "/edit_order_item.jsp";
	}

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private ItemSelector _itemSelector;

}