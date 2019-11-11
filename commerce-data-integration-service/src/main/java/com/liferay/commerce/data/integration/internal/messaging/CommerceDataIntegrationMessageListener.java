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

package com.liferay.commerce.data.integration.internal.messaging;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationConstants;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "destination.name=" + CommerceDataIntegrationConstants.EXECUTOR_DESTINATION_NAME,
	service = MessageListener.class
)
public class CommerceDataIntegrationMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		String payLoadString = (String)message.getPayload();

		JSONObject payLoad = null;

		try {
			payLoad = JSONFactoryUtil.createJSONObject(payLoadString);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);

			throw new MessageListenerException(jsone);
		}

		long commerceDataIntegrationProcessId = payLoad.getLong(
			"commerceDataIntegrationProcessId");

		ScheduledTaskExecutorService scheduledTaskExecutorService = null;

		try {
			scheduledTaskExecutorService = getScheduledTaskExecutorService(
				commerceDataIntegrationProcessId);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		if (scheduledTaskExecutorService != null) {
			try {
				scheduledTaskExecutorService.runProcess(
					commerceDataIntegrationProcessId);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_scheduledTaskExecutorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, ScheduledTaskExecutorService.class,
				"data.integration.service.executor.key");
	}

	protected ScheduledTaskExecutorService getScheduledTaskExecutorService(
			long commerceDataIntegrationProcessId)
		throws PortalException {

		ScheduledTaskExecutorService scheduledTaskExecutorService = null;

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			_commerceDataIntegrationProcessLocalService.
				getCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		if (_scheduledTaskExecutorServiceTrackerMap != null) {
			for (String key :
					_scheduledTaskExecutorServiceTrackerMap.keySet()) {

				if (key.equals(commerceDataIntegrationProcess.getType())) {
					scheduledTaskExecutorService =
						_scheduledTaskExecutorServiceTrackerMap.getService(key);

					break;
				}
			}
		}

		return scheduledTaskExecutorService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDataIntegrationMessageListener.class);

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

	private ServiceTrackerMap<String, ScheduledTaskExecutorService>
		_scheduledTaskExecutorServiceTrackerMap;

}