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

package com.liferay.commerce.data.integration.trigger;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.util.Date;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceDataIntegrationProcessTriggerHelper {

	public void addScheduledTask(
			long commerceDataIntegrationProcessId, String cronExpression,
			Date startDate, Date endDate)
		throws SchedulerException;

	public void deleteScheduledTask(long commerceDataIntegrationProcessId)
		throws SchedulerException;

	public Date getNextFireTime(long commerceDataIntegrationProcessId);

	public Date getPreviousFireTime(long commerceDataIntegrationProcessId);

	public SchedulerResponse getScheduledJob(
			long commerceDataIntegrationProcessId)
		throws SchedulerException;

}