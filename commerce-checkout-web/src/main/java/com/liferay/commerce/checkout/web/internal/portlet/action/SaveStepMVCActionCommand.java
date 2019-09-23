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

package com.liferay.commerce.checkout.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStepServicesTracker;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_CHECKOUT,
		"mvc.command.name=saveStep"
	},
	service = MVCActionCommand.class
)
public class SaveStepMVCActionCommand extends BaseMVCActionCommand {

	public String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String checkoutStepName)
		throws Exception {

		String redirect = GetterUtil.getString(
			actionRequest.getAttribute(WebKeys.REDIRECT));

		if (Validator.isNotNull(redirect)) {
			return redirect;
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return getPortletURL(
				actionRequest, actionResponse, checkoutStepName);
		}

		CommerceCheckoutStep commerceCheckoutStep =
			_commerceCheckoutStepServicesTracker.getNextCommerceCheckoutStep(
				checkoutStepName, _portal.getHttpServletRequest(actionRequest),
				_portal.getHttpServletResponse(actionResponse));

		if (commerceCheckoutStep == null) {
			return ParamUtil.getString(actionRequest, "redirect");
		}

		return getPortletURL(
			actionRequest, actionResponse, commerceCheckoutStep.getName());
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String checkoutStepName = ParamUtil.getString(
			actionRequest, "checkoutStepName");

		CommerceCheckoutStep commerceCheckoutStep =
			_commerceCheckoutStepServicesTracker.getCommerceCheckoutStep(
				checkoutStepName);

		commerceCheckoutStep.processAction(actionRequest, actionResponse);

		hideDefaultSuccessMessage(actionRequest);

		String redirect = getRedirect(
			actionRequest, actionResponse, checkoutStepName);

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	protected String getPortletURL(
		ActionRequest actionRequest, ActionResponse actionResponse,
		String checkoutStepName) {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		long commerceOrderId = ParamUtil.getLong(
			actionRequest, "commerceOrderId");

		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		portletURL.setParameter("checkoutStepName", checkoutStepName);

		return portletURL.toString();
	}

	@Reference
	private CommerceCheckoutStepServicesTracker
		_commerceCheckoutStepServicesTracker;

	@Reference
	private Portal _portal;

}