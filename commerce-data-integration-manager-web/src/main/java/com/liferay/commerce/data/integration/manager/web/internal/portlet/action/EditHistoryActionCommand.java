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

package com.liferay.commerce.data.integration.manager.web.internal.portlet.action;

import com.liferay.commerce.data.integration.manager.service.HistoryLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	property = {
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.DATA_INTEGRATION_WEB,
		"mvc.command.name=editHistory"
	},
	service = MVCActionCommand.class
)
public class EditHistoryActionCommand extends BaseMVCActionCommand {

	protected void deleteHistory(ActionRequest actionRequest) {
		long[] deleteHistoryIds = ParamUtil.getLongValues(
			actionRequest, "deleteHistoryIds");

		try {
			if ((deleteHistoryIds != null) && (deleteHistoryIds.length > 0)) {
				for (long historyId : deleteHistoryIds) {
					_historyLocalService.deleteHistory(historyId);
				}
			}
			else {
				long historyId = ParamUtil.getLong(
					actionRequest, "historyId", 0L);

				_historyLocalService.deleteHistory(historyId);
			}
		}
		catch (PortalException pe) {
			pe.printStackTrace();
			SessionErrors.add(actionRequest, "errorDeletingHistory");
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Constants.DELETE.equals(cmd)) {
			deleteHistory(actionRequest);
		}
	}

	@Reference
	private HistoryLocalService _historyLocalService;

}