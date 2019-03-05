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

package com.liferay.commerce.notification.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.notification.exception.NoSuchNotificationQueueEntryException;
import com.liferay.commerce.notification.service.CommerceNotificationQueueEntryService;
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
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN,
		"mvc.command.name=editCommerceNotificationQueueEntry"
	},
	service = MVCActionCommand.class
)
public class EditCommerceNotificationQueueEntryMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceNotificationQueues(ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCommerceNotificationQueueEntryIds = null;

		long commerceNotificationQueueEntryId = ParamUtil.getLong(
			actionRequest, "commerceNotificationQueueEntryId");

		if (commerceNotificationQueueEntryId > 0) {
			deleteCommerceNotificationQueueEntryIds = new long[] {
				commerceNotificationQueueEntryId
			};
		}
		else {
			deleteCommerceNotificationQueueEntryIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceNotificationQueueEntryIds"),
				0L);
		}

		for (long deleteCommerceNotificationQueueEntryId :
				deleteCommerceNotificationQueueEntryIds) {

			_commerceNotificationQueueEntryService.
				deleteCommerceNotificationQueueEntry(
					deleteCommerceNotificationQueueEntryId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceNotificationQueues(actionRequest);
			}
			else if (cmd.equals("resend")) {
				resendCommerceNotificationQueueEntry(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchNotificationQueueEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	protected void resendCommerceNotificationQueueEntry(
			ActionRequest actionRequest)
		throws PortalException {

		long commerceNotificationQueueEntryId = ParamUtil.getLong(
			actionRequest, "commerceNotificationQueueEntryId");

		_commerceNotificationQueueEntryService.
			resendCommerceNotificationQueueEntry(
				commerceNotificationQueueEntryId);
	}

	@Reference
	private CommerceNotificationQueueEntryService
		_commerceNotificationQueueEntryService;

}