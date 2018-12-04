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

import com.liferay.commerce.data.integration.manager.constants.DataIntegrationConstants;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import java.text.DateFormat;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.DATA_INTEGRATION_WEB,
		"mvc.command.name=editScheduledTask"
	},
	service = MVCActionCommand.class
)
public class EditScheduledTaskActionCommand extends BaseMVCActionCommand {

	protected void deleteScheduledTask(ActionRequest actionRequest) {
		long[] deleteScheduledTaskIds = ParamUtil.getLongValues(
			actionRequest, "deleteScheduledTaskIds");

		try {
			if ((deleteScheduledTaskIds != null) &&
				(deleteScheduledTaskIds.length > 0)) {

				for (long scheduledTaskId : deleteScheduledTaskIds) {
					_scheduledTaskLocalService.deleteScheduledTask(
						scheduledTaskId);
				}
			}
			else {
				long scheduledTaskId = ParamUtil.getLong(
					actionRequest, "scheduledTaskId");

				_scheduledTaskLocalService.deleteScheduledTask(scheduledTaskId);
			}
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, "errorDeletingScheduledTask");
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Constants.DELETE.equals(cmd)) {
			deleteScheduledTask(actionRequest);
		}
		else if (Constants.UPDATE.equals(cmd)) {
			editScheduledTask(actionRequest);
		}
		else {
			runTask(actionRequest);
		}
	}

	protected void editScheduledTask(ActionRequest actionRequest)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		long scheduledTaskId = ParamUtil.getLong(
			actionRequest, "scheduledTaskId");

		String name = ParamUtil.getString(actionRequest, "name");

		String startHour = ParamUtil.getString(actionRequest, "startHour");

		Date startDate = ParamUtil.getDate(
			actionRequest, "startDate", _dateFormat);

		String frequency = ParamUtil.getString(actionRequest, "frequency");

		long processId = ParamUtil.getLong(actionRequest, "processId");

		if (scheduledTaskId > 0) {
			_scheduledTaskLocalService.updateScheduledTask(
				scheduledTaskId, processId, frequency, startDate, startHour,
				name, serviceContext);
		}
		else {
			_scheduledTaskLocalService.addScheduledTask(
				processId, frequency, startDate, startHour, name,
				serviceContext);
		}
	}

	protected void runTask(ActionRequest actionRequest)
		throws InvalidSyntaxException, IOException, PortalException {

		long scheduledTaskId = ParamUtil.getLong(
			actionRequest, "scheduledTaskId");

		ScheduledTask scheduledTask =
			_scheduledTaskLocalService.getScheduledTask(scheduledTaskId);

		if (scheduledTask.isActive()) {
			SessionMessages.add(actionRequest, "taskAlreadyRunning");

			return;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_sendMessage(serviceContext.getUserId(), scheduledTaskId);

		_scheduledTaskLocalService.startScheduledTask(
			serviceContext.getUserId(), scheduledTaskId);
	}

	private void _sendMessage(long userId, long scheduledTaskId) {
		Message message = new Message();

		JSONObject payLoad = JSONFactoryUtil.createJSONObject();

		payLoad.put(
			"executionType", DataIntegrationWebPortletKeys.MANUAL_EXECUTION);
		payLoad.put("scheduledTaskId", scheduledTaskId);
		payLoad.put("userId", userId);

		message.setPayload(payLoad);

		SingleDestinationMessageSender messageSender =
			_messageSenderFactory.createSingleDestinationMessageSender(
				DataIntegrationConstants.DESTINATION_NAME);

		messageSender.send(message);
	}

	private final DateFormat _dateFormat = DateUtil.getUTCFormat();

	@Reference
	private SingleDestinationMessageSenderFactory _messageSenderFactory;

	@Reference
	private ScheduledTaskLocalService _scheduledTaskLocalService;

}