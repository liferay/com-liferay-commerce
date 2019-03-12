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
import com.liferay.commerce.batch.engine.api.job.JobFactory;
import com.liferay.commerce.batch.engine.api.job.JobLauncher;
import com.liferay.commerce.batch.engine.api.job.JobParameters;
import com.liferay.commerce.batch.model.CommerceBatchJob;
import com.liferay.commerce.batch.service.CommerceBatchJobLocalService;
import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = JobLauncher.class)
public class JobLauncherImpl implements JobLauncher {

	@Override
	public JobExecution run(Job job, JobParameters jobParameters) {
		Objects.requireNonNull(job);

		CommerceBatchJob commerceBatchJob = _addCommerceBatchJob(
			job.getKey(), job.getName());

		JobExecution jobExecution = new JobExecution(
			commerceBatchJob, jobParameters);

		_submit(job, jobExecution);

		return jobExecution;
	}

	private CommerceBatchJob _addCommerceBatchJob(String key, String name) {
		return _commerceBatchJobLocalService.addCommerceBatchJob(key, name);
	}

	private void _submit(Job job, JobExecution jobExecution) {
		NoticeableExecutorService noticeableExecutorService =
			_portalExecutorManager.getPortalExecutor(
				JobLauncherImpl.class.getName());

		noticeableExecutorService.submit(new JobRunnable(job, jobExecution));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JobLauncherImpl.class);

	@Reference
	private CommerceBatchJobLocalService _commerceBatchJobLocalService;

	@Reference
	private JobFactory _jobFactory;

	@Reference
	private PortalExecutorManager _portalExecutorManager;

	private class JobRunnable implements Runnable {

		@Override
		public void run() {
			try {
				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"Job %s launched",
							_jobExecution.getCommerceBatchJob()));
				}

				_job.execute(_jobExecution);
			}
			catch (Exception e) {
				_log.error(
					String.format(
						"Job %s failed to execute",
						_jobExecution.getCommerceBatchJob()),
					e);
			}
			finally {
				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"Job %s completed",
							_jobExecution.getCommerceBatchJob()));
				}

				_jobFactory.dispose(_job);
			}
		}

		private JobRunnable(Job job, JobExecution jobExecution) {
			_job = job;
			_jobExecution = jobExecution;
		}

		private final Job _job;
		private final JobExecution _jobExecution;

	}

}