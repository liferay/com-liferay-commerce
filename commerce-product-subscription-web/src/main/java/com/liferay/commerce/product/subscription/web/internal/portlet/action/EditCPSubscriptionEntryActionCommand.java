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

package com.liferay.commerce.product.subscription.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.exception.NoSuchCPSubscriptionEntryException;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_SUBSCRIPTION_ENTRY,
		"mvc.command.name=editCPSubscriptionEntry"
	},
	service = MVCActionCommand.class
)
public class EditCPSubscriptionEntryActionCommand extends BaseMVCActionCommand {

	protected void deleteCPSubscriptionEntries(
			long cpSubscriptionEntryId, ActionRequest actionRequest)
		throws Exception {

		long[] deleteCPSubscriptionEntryIds = null;

		if (cpSubscriptionEntryId > 0) {
			deleteCPSubscriptionEntryIds = new long[] {cpSubscriptionEntryId};
		}
		else {
			deleteCPSubscriptionEntryIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCPSubscriptionEntryIds"),
				0L);
		}

		for (long deleteCPSubscriptionEntryId : deleteCPSubscriptionEntryIds) {
			_cpSubscriptionEntryService.deleteCPSubscriptionEntry(
				deleteCPSubscriptionEntryId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long cpSubscriptionEntryId = ParamUtil.getLong(
			actionRequest, "cpSubscriptionEntryId");

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCPSubscriptionEntries(
					cpSubscriptionEntryId, actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateCPSubscriptionEntry(cpSubscriptionEntryId, actionRequest);
			}
			else if (cmd.equals("setActive")) {
				setActive(cpSubscriptionEntryId, actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPSubscriptionEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	protected void setActive(
			long cpSubscriptionEntryId, ActionRequest actionRequest)
		throws PortalException {

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		_cpSubscriptionEntryService.setActive(cpSubscriptionEntryId, active);
	}

	protected CPSubscriptionEntry updateCPSubscriptionEntry(
			long cpSubscriptionEntryId, ActionRequest actionRequest)
		throws Exception {

		long subscriptionCycleLength = (long)ParamUtil.getNumber(
			actionRequest, "subscriptionCycleLength");
		String subscriptionCyclePeriod = ParamUtil.getString(
			actionRequest, "subscriptionCyclePeriod");
		long maxSubscriptionCyclesNumber = (long)ParamUtil.getNumber(
			actionRequest, "maxSubscriptionCyclesNumber");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		CPSubscriptionEntry cpSubscriptionEntry =
			_cpSubscriptionEntryService.updateCommercePriceEntry(
				cpSubscriptionEntryId, subscriptionCycleLength,
				subscriptionCyclePeriod, maxSubscriptionCyclesNumber, active);

		return cpSubscriptionEntry;
	}

	@Reference
	private CPSubscriptionEntryService _cpSubscriptionEntryService;

}