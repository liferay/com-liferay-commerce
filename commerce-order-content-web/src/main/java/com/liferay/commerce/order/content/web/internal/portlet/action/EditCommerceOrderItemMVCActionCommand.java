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

package com.liferay.commerce.order.content.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_ORDER_CONTENT,
		"mvc.command.name=editCommerceOrderItem"
	},
	service = MVCActionCommand.class
)
public class EditCommerceOrderItemMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceOrderItems(ActionRequest actionRequest)
		throws PortalException {

		long commerceOrderId = ParamUtil.getLong(
			actionRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			_commerceOrderItemService.deleteCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId());
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		CommerceContext commerceContext =
			(CommerceContext)actionRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long commerceOrderItemId = ParamUtil.getLong(
			actionRequest, "commerceOrderItemId");

		try {
			if (cmd.equals(Constants.DELETE)) {
				_commerceOrderItemService.deleteCommerceOrderItem(
					commerceOrderItemId);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				int quantity = ParamUtil.getInteger(actionRequest, "quantity");

				CommerceOrderItem commerceOrderItem =
					_commerceOrderItemService.getCommerceOrderItem(
						commerceOrderItemId);

				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(
						CommerceOrderItem.class.getName(), actionRequest);

				_commerceOrderItemService.updateCommerceOrderItem(
					commerceOrderItem.getCommerceOrderItemId(), quantity,
					commerceOrderItem.getJson(), commerceContext,
					serviceContext);
			}
			else if (cmd.equals(Constants.RESET)) {
				deleteCommerceOrderItems(actionRequest);
			}
		}
		catch (CommerceOrderValidatorException cove) {
			hideDefaultErrorMessage(actionRequest);

			SessionErrors.add(actionRequest, cove.getClass(), cove);
		}
		catch (Exception e) {
			if (e instanceof NoSuchOrderException ||
				e instanceof NoSuchOrderItemException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());
			}
			else {
				throw e;
			}
		}
	}

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

}