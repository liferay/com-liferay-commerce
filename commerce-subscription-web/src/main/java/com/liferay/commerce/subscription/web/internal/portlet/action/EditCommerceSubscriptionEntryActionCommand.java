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

package com.liferay.commerce.subscription.web.internal.portlet.action;

import com.liferay.commerce.exception.NoSuchSubscriptionEntryException;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.service.CommerceSubscriptionEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Calendar;

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
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_SUBSCRIPTION_ENTRY,
		"mvc.command.name=editCommerceSubscriptionEntry"
	},
	service = MVCActionCommand.class
)
public class EditCommerceSubscriptionEntryActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceSubscriptionEntries(
			long commerceSubscriptionEntryId, ActionRequest actionRequest)
		throws Exception {

		long[] deleteCommerceSubscriptionEntryIds = null;

		if (commerceSubscriptionEntryId > 0) {
			deleteCommerceSubscriptionEntryIds =
				new long[] {commerceSubscriptionEntryId};
		}
		else {
			deleteCommerceSubscriptionEntryIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceSubscriptionEntryIds"),
				0L);
		}

		for (long deleteCommerceSubscriptionEntryId :
				deleteCommerceSubscriptionEntryIds) {

			_commerceSubscriptionEntryService.deleteCommerceSubscriptionEntry(
				deleteCommerceSubscriptionEntryId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long commerceSubscriptionEntryId = ParamUtil.getLong(
			actionRequest, "commerceSubscriptionEntryId");

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceSubscriptionEntries(
					commerceSubscriptionEntryId, actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateCommerceSubscriptionEntry(
					commerceSubscriptionEntryId, actionRequest);
			}
			else if (cmd.equals("setActive")) {
				setActive(commerceSubscriptionEntryId, actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchSubscriptionEntryException ||
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
			long commerceSubscriptionEntryId, ActionRequest actionRequest)
		throws PortalException {

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		_commerceSubscriptionEntryService.setActive(
			commerceSubscriptionEntryId, active);
	}

	protected CommerceSubscriptionEntry updateCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId, ActionRequest actionRequest)
		throws Exception {

		int subscriptionLength = ParamUtil.getInteger(
			actionRequest, "subscriptionLength");
		String subscriptionType = ParamUtil.getString(
			actionRequest, "subscriptionType");
		UnicodeProperties subscriptionTypeSettingsProperties =
			PropertiesParamUtil.getProperties(
				actionRequest, "subscriptionTypeSettings--");
		long maxSubscriptionCycles = ParamUtil.getLong(
			actionRequest, "maxSubscriptionCycles");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int nextIterationDateMonth = ParamUtil.getInteger(
			actionRequest, "nextIterationDateMonth");
		int nextIterationDateDay = ParamUtil.getInteger(
			actionRequest, "nextIterationDateDay");
		int nextIterationDateYear = ParamUtil.getInteger(
			actionRequest, "nextIterationDateYear");
		int nextIterationDateHour = ParamUtil.getInteger(
			actionRequest, "nextIterationDateHour");
		int nextIterationDateMinute = ParamUtil.getInteger(
			actionRequest, "nextIterationDateMinute");
		int nextIterationDateAmPm = ParamUtil.getInteger(
			actionRequest, "nextIterationDateAmPm");

		if (nextIterationDateAmPm == Calendar.PM) {
			nextIterationDateHour += 12;
		}

		return
			_commerceSubscriptionEntryService.updateCommerceSubscriptionEntry(
				commerceSubscriptionEntryId, subscriptionLength,
				subscriptionType, subscriptionTypeSettingsProperties,
				maxSubscriptionCycles, active, startDateMonth, startDateDay,
				startDateYear, startDateHour, startDateMinute,
				nextIterationDateMonth, nextIterationDateDay,
				nextIterationDateYear, nextIterationDateHour,
				nextIterationDateMinute);
	}

	@Reference
	private CommerceSubscriptionEntryService _commerceSubscriptionEntryService;

}