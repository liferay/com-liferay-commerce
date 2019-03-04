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
import com.liferay.commerce.batch.engine.api.job.JobLauncher;
import com.liferay.commerce.batch.engine.api.job.JobParameters;
import com.liferay.commerce.batch.engine.impl.internal.concurrent.BlockingExecutor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = JobLauncher.class)
public class JobLauncherImpl implements JobLauncher {

	public JobLauncherImpl() {
		_blockingExecutor = new BlockingExecutor(2, 10);
	}

	public JobLauncherImpl(BlockingExecutor blockingExecutor) {
		_blockingExecutor = Objects.requireNonNull(blockingExecutor);
	}

	@Override
	public boolean isJobActive(String key) {
		return _jobExecutionMap.containsKey(Objects.requireNonNull(key));
	}

	@Override
	public JobExecution run(Job job, JobParameters jobParameters) {
		Objects.requireNonNull(job);

		JobExecution jobExecution = new JobExecution(
			new JobInstance(job.getKey(), job.getName()), jobParameters);

		_blockingExecutor.execute(
			new JobRunnable(job, jobExecution, _jobExecutionMap));

		_jobExecutionMap.put(job.getKey(), jobExecution);

		return jobExecution;
	}

	@Deactivate
	protected void deactivate() {
		_blockingExecutor.destroy();
	}

	private final BlockingExecutor _blockingExecutor;
	private final Map<String, JobExecution> _jobExecutionMap =
		new ConcurrentHashMap<>();

}