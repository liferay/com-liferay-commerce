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

package com.liferay.commerce.data.integration.manager.internal.messaging;

import com.liferay.commerce.data.integration.manager.constants.DataIntegrationConstants;
import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.ProcessLocalService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskExectutorService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import java.io.IOException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	immediate = true,
	property = "destination.name=" + DataIntegrationConstants.DESTINATION_NAME,
	service = MessageListener.class
)
public class DataIntegrationMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		JSONObject payLoad = (JSONObject)message.getPayload();

		long scheduledTaskId = payLoad.getLong("scheduledTaskId");

		long userId = payLoad.getLong("userId");

		String executionType = payLoad.getString("executionType");

		ScheduledTaskExectutorService scheduledTaskExectutorService = null;

		try {
			scheduledTaskExectutorService = getScheduledTaskExecutor(
				scheduledTaskId);
		}
		catch (InvalidSyntaxException | IOException | PortalException e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		if (scheduledTaskExectutorService != null) {
			try {
				scheduledTaskExectutorService.runProcess(
					userId, scheduledTaskId, executionType);
			}
			catch (IOException | PortalException e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				_stopJob(userId, scheduledTaskId);
			}
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext)
		throws InvalidSyntaxException, IOException, PortalException {

		_scheduledTaskExectutorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, ScheduledTaskExectutorService.class,
				"data.integration.service.executor.key");
	}

	protected ScheduledTaskExectutorService getScheduledTaskExecutor(
			long scheduledTaskId)
		throws InvalidSyntaxException, IOException, PortalException {

		ScheduledTaskExectutorService service = null;

		ScheduledTask scheduledTask =
			_scheduledTaskLocalService.getScheduledTask(scheduledTaskId);

		Process process = _processLocalService.getProcess(
			scheduledTask.getProcessId());

		if (_scheduledTaskExectutorServiceTrackerMap != null) {
			for (String key :
					_scheduledTaskExectutorServiceTrackerMap.keySet()) {

				if (key.equals(process.getProcessType())) {
					service =
						_scheduledTaskExectutorServiceTrackerMap.getService(
							key);

					break;
				}
			}
		}

		return service;
	}

	private void _stopJob(long userId, long scheduledTaskId) {
		try {
			_scheduledTaskLocalService.stopScheduledTask(
				userId, scheduledTaskId);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DataIntegrationMessageListener.class);

	@Reference
	private ProcessLocalService _processLocalService;

	private ServiceTrackerMap<String, ScheduledTaskExectutorService>
		_scheduledTaskExectutorServiceTrackerMap;

	@Reference
	private ScheduledTaskLocalService _scheduledTaskLocalService;

}