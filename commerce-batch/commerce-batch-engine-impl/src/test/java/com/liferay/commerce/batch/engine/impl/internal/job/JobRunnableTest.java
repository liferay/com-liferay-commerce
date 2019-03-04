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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Ivica Cardic
 */
public class JobRunnableTest {

	@Test
	public void testRun() {
		Job job = Mockito.mock(Job.class);

		JobInstance jobInstance = new JobInstance("1234", "name");

		JobExecution jobExecution = new JobExecution(jobInstance, null);

		Map<String, JobExecution> jobExecutionMap = new HashMap<>();

		jobExecutionMap.put("1234", jobExecution);

		JobRunnable jobRunnable = new JobRunnable(
			job, jobExecution, jobExecutionMap);

		jobRunnable.run();

		Assert.assertEquals(
			jobExecutionMap.toString(), 0, jobExecutionMap.size());
	}

}