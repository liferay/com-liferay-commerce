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

package com.liferay.commerce.data.integration.manager.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

/**
 * @author guywandji
 */
public interface ScheduledTaskExectutorService {

	/**
	 * This method returns the name of the process type
	 * @return
	 */
	public String getName();

	/**
	 * This method execute the selected process
	 *
	 * @param scheduledTaskId
	 * @throws IOException
	 * @throws PortalException
	 */
	public void runProcess(
			long userId, long scheduledTaskId, String executionType)
		throws IOException, PortalException;

}