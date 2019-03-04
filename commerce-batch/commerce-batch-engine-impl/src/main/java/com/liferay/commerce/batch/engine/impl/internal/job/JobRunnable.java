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

package com.liferay.commerce.batch.engine.impl.internal.job;

import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobInstance;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class JobRunnable implements Runnable {

	public JobRunnable(
		Job job, JobExecution jobExecution,
		Map<String, JobExecution> jobExecutionMap) {

		_job = Objects.requireNonNull(job);
		_jobExecution = Objects.requireNonNull(jobExecution);
		_jobExecutionMap = Objects.requireNonNull(jobExecutionMap);
	}

	@Override
	public void run() {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Job %s launched", _jobExecution.getJobInstance()));
			}

			_job.execute(_jobExecution);
		}
		catch (Exception e) {
			_log.error(
				String.format(
					"Job %s failed to execute", _jobExecution.getJobInstance()),
				e);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Job %s completed", _jobExecution.getJobInstance()));
			}

			JobInstance jobInstance = _jobExecution.getJobInstance();

			_jobExecutionMap.remove(jobInstance.getJobKey());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(JobRunnable.class);

	private final Job _job;
	private final JobExecution _jobExecution;
	private final Map<String, JobExecution> _jobExecutionMap;

}