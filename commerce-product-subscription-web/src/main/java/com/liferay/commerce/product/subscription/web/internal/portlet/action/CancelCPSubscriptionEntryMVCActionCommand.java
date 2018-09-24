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

import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.service.CPSubscriptionEntryLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_SUBSCRIPTION_CONTENT_WEB,
		"mvc.command.name=cancelCPSubscriptionEntry"
	},
	service = MVCActionCommand.class
)
public class CancelCPSubscriptionEntryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = _portal.getUserId(actionRequest);

		long cpSubscriptionEntryId = ParamUtil.getLong(
			actionRequest, "cpSubscriptionEntryId");

		CPSubscriptionEntry cpSubscriptionEntry =
			_cpSubscriptionEntryLocalService.fetchCPSubscriptionEntry(
				cpSubscriptionEntryId);

		if ((cpSubscriptionEntry != null) &&
			(userId == cpSubscriptionEntry.getUserId())) {

			_cpSubscriptionEntryLocalService.setActive(
				cpSubscriptionEntryId, false);
		}
		else {
			throw new PrincipalException();
		}
	}

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

	@Reference
	private Portal _portal;

}