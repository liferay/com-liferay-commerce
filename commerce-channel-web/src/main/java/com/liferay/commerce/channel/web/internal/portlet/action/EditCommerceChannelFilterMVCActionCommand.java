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

package com.liferay.commerce.channel.web.internal.portlet.action;

import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeRegistry;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CHANNELS,
		"mvc.command.name=editCommerceChannelFilter"
	},
	service = MVCActionCommand.class
)
public class EditCommerceChannelFilterMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceChannelFilter(ActionRequest actionRequest)
		throws Exception {

		long[] cpRuleIds = null;

		long cpRuleId = ParamUtil.getLong(actionRequest, "cpRuleId");

		if (cpRuleId > 0) {
			cpRuleIds = new long[] {cpRuleId};
		}
		else {
			cpRuleIds = ParamUtil.getLongValues(actionRequest, "cpRuleIds");
		}

		for (long deleteCPRuleId : cpRuleIds) {
			_cpRuleService.deleteCPRule(deleteCPRuleId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			CPRule cpRule = null;

			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceChannelFilter(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				cpRule = updateCommerceChannelFilter(actionRequest);
			}

			String redirect = getSaveAndContinueRedirect(actionRequest, cpRule);

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (PrincipalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CPRule cpRule)
		throws PortalException {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest, CPPortletKeys.COMMERCE_CHANNELS,
			PortletRequest.RENDER_PHASE);

		long commerceChannelId = ParamUtil.getLong(
			actionRequest, "commerceChannelId");

		portletURL.setParameter(
			"commerceChannelId", String.valueOf(commerceChannelId));

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceChannelFilters");

		if (cpRule != null) {
			portletURL.setParameter(
				"mvcRenderCommandName", "editCommerceChannelFilter");
			portletURL.setParameter(
				"cpRuleId", String.valueOf(cpRule.getCPRuleId()));
		}

		return portletURL.toString();
	}

	protected CPRule updateCommerceChannelFilter(ActionRequest actionRequest)
		throws Exception {

		long cpRuleId = ParamUtil.getLong(actionRequest, "cpRuleId");

		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceChannel.class.getName(), actionRequest);

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);

		CPRuleType cpRuleType = _cpRuleTypeRegistry.getCPRuleType(type);

		UnicodeProperties typeSettingsProperties =
			cpRuleType.getTypeSettingsProperties(httpServletRequest);

		if (cpRuleId <= 0) {
			return _cpRuleService.addCPRule(
				name, true, type, typeSettingsProperties, serviceContext);
		}

		return _cpRuleService.updateCPRule(
			cpRuleId, name, true, type, typeSettingsProperties, serviceContext);
	}

	@Reference
	private CPRuleService _cpRuleService;

	@Reference
	private CPRuleTypeRegistry _cpRuleTypeRegistry;

	@Reference
	private Portal _portal;

}